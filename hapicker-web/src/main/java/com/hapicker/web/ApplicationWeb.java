package com.hapicker.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author yuyufeng
 * @date 2018/7/25.
 */
@SpringBootApplication
@EnableFeignClients
public class ApplicationWeb {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationWeb.class, args);
    }
}
