package com.hapicker.task.listener;

import com.alibaba.fastjson.JSONObject;
import com.hapicker.common.constant.BusWarningStatusEnum;
import com.hapicker.common.constant.RedisPrefix;
import com.hapicker.common.service.BusServices;
import com.hapicker.common.vo.ScheduleBusVO;
import com.hapicker.mapper.BusWarningInfoMapper;
import com.hapicker.mapper.UserInfoMapper;
import com.hapicker.model.BusWarningInfo;
import com.hapicker.model.UserInfo;
import com.hapicker.task.service.MailService;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yuyufeng
 * @date 2018/12/11.
 */
public class BusWarningListenerTask implements Runnable {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private BusWarningInfoMapper busWarningInfoMapper;

    private UserInfoMapper userInfoMapper;

    private ListOperations<String, BusWarningInfo> listOperations;

    private MailService mailService;

    public BusWarningListenerTask(BusWarningInfoMapper busWarningInfoMapper, UserInfoMapper userInfoMapper, ListOperations<String, BusWarningInfo> listOperations, MailService mailService) {
        this.busWarningInfoMapper = busWarningInfoMapper;
        this.userInfoMapper = userInfoMapper;
        this.listOperations = listOperations;
        this.mailService = mailService;
    }

    @Override
    public void run() {
        while (true) {
            BusWarningInfo busWarningInfo = listOperations.leftPop(RedisPrefix.BUS_WARNING);
            if (busWarningInfo != null) {
                logger.info("收到消息" + JSONObject.toJSONString(busWarningInfo));
                //判断车次是否过期
                Date departDateTime = null;
                try {
                    departDateTime = dateFormat.parse(busWarningInfo.getBusDate() + " " + busWarningInfo.getBusTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (departDateTime.getTime() < System.currentTimeMillis()) {
                    BusWarningInfo record = new BusWarningInfo();
                    record.setBusWarningId(busWarningInfo.getBusWarningId());
                    record.setWarningTimes(busWarningInfo.getWarningTimes() + 1);
                    record.setWarningStatus(BusWarningStatusEnum.WARNING_ABANDON.getKey());
                    record.setUpdateTime(new Date());
                    busWarningInfoMapper.updateByPrimaryKeySelective(record);
                    logger.info("车次已过期，放弃查询。" + JSONObject.toJSON(busWarningInfo) + "");
                    continue;
                }

                //远程获取数据
                try {
                    List<ScheduleBusVO> scheduleBusVOList = BusServices.queryByBababus(busWarningInfo.getDeparture(), busWarningInfo.getDestination(), busWarningInfo.getBusDate());
                    for (ScheduleBusVO scheduleBusVO : scheduleBusVOList) {
                        if (busWarningInfo.getBusNo().equals(scheduleBusVO.getBusNo())) {
                            logger.info(JSONObject.toJSONString(scheduleBusVO));
                            BusWarningInfo record = new BusWarningInfo();
                            record.setBusWarningId(busWarningInfo.getBusWarningId());
                            Integer ticketLeft = -1;
                            try {
                                ticketLeft = Integer.valueOf(scheduleBusVO.getTicketLeft());
                            } catch (Exception e) {
                                logger.info("车次余票获取错误" + JSONObject.toJSON(busWarningInfo) + "");
                            }
                            /*if (ticketLeft.equals(busWarningInfo.getTicketLeft())) {
                                logger.info("车次余票未改变" + JSONObject.toJSON(busWarningInfo) + "");
                                break;
                            }*/
                            record.setTicketLeft(ticketLeft);
                            record.setWarningTimes(busWarningInfo.getWarningTimes() + 1);
                            if (ticketLeft < 10 || busWarningInfo.getTicketLeft() - ticketLeft > 10) {
                                record.setWarningStatus(BusWarningStatusEnum.WARNING_SUCCESS.getKey());
                                logger.info("车票小于10张，或余票相差大于10，预警提醒用户" + JSONObject.toJSON(busWarningInfo) + "");
                                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(busWarningInfo.getUserId());
                                if (!StringUtils.isEmpty(userInfo.getUserEmail())) {
                                    String email = userInfo.getUserEmail();
                                    String subject = "余票只剩【" + busWarningInfo.getTicketLeft() + "】张了 " + "车次：" + busWarningInfo.getDeparture() + "-" + busWarningInfo.getDestination() + " 发车时间：" + busWarningInfo.getBusDate() + " " + busWarningInfo.getBusTime();
                                    String content = "---车次信息---<br />" +
                                            "出发：" + busWarningInfo.getDeparture() + "<br />" +
                                            "到达：" + busWarningInfo.getDestination() + "<br />" +
                                            "发车时间：" + busWarningInfo.getBusDate() + " " + busWarningInfo.getBusTime() + "<br />" +
                                            "当前余票：" + ticketLeft + "<br />" +
                                            "系统共为您监听【" + record.getWarningTimes() + "】次，欢迎再次使用。" + "<br />" +
                                            "备注：数据仅供参考";
                                    String receiverName = userInfo.getUserNick();
                                    try {
                                        mailService.sendEmail(email, content, subject, receiverName);
                                        logger.info("邮件通知发送成功 " + JSONObject.toJSON(userInfo) + " " + JSONObject.toJSON(busWarningInfo));
                                    } catch (Exception e) {
                                        logger.info("邮件通知发送失败 " + JSONObject.toJSON(userInfo) + " " + JSONObject.toJSON(busWarningInfo));
                                    }
                                }

                            }
                            record.setUpdateTime(new Date());
                            busWarningInfoMapper.updateByPrimaryKeySelective(record);
                            break;
                        }
                    }
                } catch (MyBatisSystemException em) {
                    logger.info("数据库异常，车次重新塞入队列 " + em.getMessage() + " " + JSONObject.toJSONString(busWarningInfo));
                    listOperations.rightPush(RedisPrefix.BUS_WARNING, busWarningInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    logger.info(Thread.currentThread().getName() + " 监听中...");
                    TimeUnit.MILLISECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
