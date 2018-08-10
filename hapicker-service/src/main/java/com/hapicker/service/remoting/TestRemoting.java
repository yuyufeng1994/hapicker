package com.hapicker.service.remoting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuyufeng
 * @date 2018/3/28
 */
@RestController
@Api(description = "测试")
@RequestMapping(value = "/test")
public class TestRemoting {
    private final  Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DiscoveryClient client;

    @ApiOperation(value = "discovery", httpMethod = "GET")
    @RequestMapping(value = "/discovery")
    public String discovery(){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return "/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId();
    }


}
