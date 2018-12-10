package com.hapicker.task.tasks;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hapicker.common.constant.BusWarningStatusEnum;
import com.hapicker.common.constant.RedisPrefix;
import com.hapicker.mapper.BusWarningInfoMapper;
import com.hapicker.model.BusWarningInfo;
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
    private ListOperations<String, Long> listOperations;

    @Autowired
    private BusWarningInfoMapper busWarningInfoMapper;

    //监听车次监听表，对更新时间在最近20分钟之外的车次，进行重新筛入队列，来重新查询车次
    @Scheduled(cron = "0 */20 * * * ?")
    private void selectAndPush() {
        System.out.println("BusWarningTask.selectAndPush");
        int pageNo = 0;
        List<Long> busWarningIdList = null;
        while (pageNo == 0 || busWarningIdList != null && busWarningIdList.size() == 10) {
            busWarningIdList = busWarningInfoMapper.selectWarningIds(pageNo);
            for (Long aLong : busWarningIdList) {
                listOperations.rightPush(RedisPrefix.BUS_WARNING, aLong);
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
