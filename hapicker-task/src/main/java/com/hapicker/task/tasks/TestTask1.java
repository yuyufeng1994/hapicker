package com.hapicker.task.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yuyufeng
 * @date 2018/12/10.
 */
//@Component
public class TestTask1 {
    private int count = 0;

    @Scheduled(cron = "*/5 * * * * ?")
    private void process() {
        System.out.println("[" + Thread.currentThread().getName() + "]" + "this is scheduler task runing  " + (count++)+" "+System.currentTimeMillis());
    }

}
