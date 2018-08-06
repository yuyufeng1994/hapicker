package com.hapicker.web.remoting;

import com.hapicker.common.dto.UserInfoDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yuyufeng
 * @date 2018/8/6.
 */
@FeignClient(value = "hapicker-service")
public interface HelloRemoting {
    /**
     * 测试服务
     * @return
     */
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    UserInfoDTO hello();
}