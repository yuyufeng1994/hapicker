package com.hapicker.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yuyufeng
 * @date 2018/8/6.
 */
@EnableEurekaServer
@SpringBootApplication
public class ApplicationCloudEureka {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationCloudEureka.class, args);
    }
}
