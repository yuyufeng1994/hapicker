package com.hapicker.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.commons.util.UtilAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author yuyufeng
 * @date 2018/7/25.
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@EnableFeignClients
@EnableDiscoveryClient
public class ApplicationWeb {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationWeb.class, args);
    }
}
