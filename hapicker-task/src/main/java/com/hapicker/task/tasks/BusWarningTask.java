package com.hapicker.task.tasks;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hapicker.common.constant.BusWarningStatusEnum;
import com.hapicker.common.constant.RedisPrefix;
import com.hapicker.mapper.BusWarningInfoMapper;
import com.hapicker.model.BusWarningInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 车次监听任务
 *
 * @author yuyufeng
 * @date 2018/12/10.
 */
@Component
public class BusWarningTask {

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOs;

    @Resource(name = "redisTemplate")
    private ListOperations<String, BusWarningInfo> listOperations;

    private static int LIST_MAXSIZE = 1000;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BusWarningInfoMapper busWarningInfoMapper;

    //监听车次监听表 60分钟之内的
    @Scheduled(cron = "0 */1 * * * ?")
    private void selectAndPushNowHour() {
        logger.info("selectAndPush1Hour");
        int upTime = 60;
        pushByUpTime(upTime);
    }

    //监听车次监听表 24小时之内的
    @Scheduled(cron = "0 */15 * * * ?")
    private void selectAndPush1Today() {
        logger.info("selectAndPush1Today");
        int upTime = 1440;
        pushByUpTime(upTime);
    }

    //监听车次监听表 3天之内的
    @Scheduled(cron = "0 */30 * * * ?")
    private void selectAndPush() {
        logger.info("selectAndPush");
        int upTime = 4320;
        pushByUpTime(upTime);
    }

    private void pushByUpTime(int upTime) {
        int pageNo = 0;
        List<BusWarningInfo> busWarningIdList = null;
        while (pageNo == 0 || busWarningIdList != null && busWarningIdList.size() == 10) {
            busWarningIdList = busWarningInfoMapper.selectWarningBus(pageNo * 10, upTime);
            if (listOperations.size(RedisPrefix.BUS_WARNING) < LIST_MAXSIZE) {
                for (BusWarningInfo busWarningInfo : busWarningIdList) {
                    listOperations.rightPush(RedisPrefix.BUS_WARNING, busWarningInfo);
                }
            } else {
                logger.error("监听队列里面数据过多，暂停新增监听数据：" + LIST_MAXSIZE);
                break;
            }
            pageNo++;
        }
    }
}
//每隔5秒执行一次：*/5 * * * * ?
//每隔1分钟执行一次：0 */1 * * * ?
//每天23点执行一次：0 0 23 * * ?
//每天凌晨1点执行一次：0 0 1 * * ?
//每月1号凌晨1点执行一次：0 0 1 1 * ?
//每月最后一天23点执行一次：0 0 23 L * ?
//每周星期天凌晨1点实行一次：0 0 1 ? * L
//在26分、29分、33分执行一次：0 26,29,33 * * * ?
//每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?
