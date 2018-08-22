package com.hapicker.web.client;

import com.hapicker.common.dto.ResponseDTO;
import com.hapicker.common.dto.UserInfoDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author yuyufeng
 * @date 2018/8/22.
 */
@RequestMapping(value = "user")
@FeignClient(value = "hapicker-service")
public interface UserClient {
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    ResponseDTO<UserInfoDTO> getUserInfo(@RequestBody UserInfoDTO userInfoDTO);

    @RequestMapping(value = "queryUserInfo", method = RequestMethod.POST)
    ResponseDTO<List<UserInfoDTO>> queryUserInfo(@RequestBody UserInfoDTO userInfoDTO);
}
