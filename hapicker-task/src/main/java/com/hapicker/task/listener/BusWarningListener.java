package com.hapicker.task.listener;

import com.hapicker.common.constant.RedisPrefix;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yuyufeng
 * @date 2018/12/10.
 */
@Component
public class BusWarningListener implements ApplicationContextAware {

    @Resource(name = "redisTemplate")
    private ListOperations<String, Long> listOperations;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       /* while (true) {
            Long busWarningId = listOperations.leftPop(RedisPrefix.BUS_WARNING);
            if (busWarningId != null) {
                System.out.println("收到消息" + busWarningId);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/


    }


}
