package com.hapicker.task.listener;

import com.alibaba.fastjson.JSONObject;
import com.hapicker.common.constant.BusWarningStatusEnum;
import com.hapicker.common.constant.RedisPrefix;
import com.hapicker.common.service.BusServices;
import com.hapicker.common.vo.ScheduleBusVO;
import com.hapicker.mapper.BusWarningInfoMapper;
import com.hapicker.mapper.UserInfoMapper;
import com.hapicker.model.BusWarningInfo;
import com.hapicker.task.service.MailService;
import org.assertj.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yuyufeng
 * @date 2018/12/10.
 */
@Component
public class BusWarningListener implements ApplicationContextAware {

    @Autowired
    private BusWarningInfoMapper busWarningInfoMapper;

    @Resource(name = "redisTemplate")
    private ListOperations<String, BusWarningInfo> listOperations;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    //开启监听数
    private static int nThread = 3;

//    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    private ExecutorService executorBusService = Executors.newFixedThreadPool(nThread);

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        for (int i = 0; i < nThread; i++) {
            executorBusService.execute(new BusWarningListenerTask(busWarningInfoMapper, userInfoMapper, listOperations, mailService));
        }
    }

    public static void main(String[] args) {
    }


}
