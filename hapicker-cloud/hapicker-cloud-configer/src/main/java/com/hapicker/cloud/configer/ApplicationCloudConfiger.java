package com.hapicker.cloud.configer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心
 * @author yuyufeng
 * @date 2018/7/25.
 */
@EnableConfigServer
@SpringBootApplication
public class ApplicationCloudConfiger {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationCloudConfiger.class, args);
    }
}
