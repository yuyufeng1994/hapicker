package com.hapicker.web.client;

import com.hapicker.common.dto.ResponseDTO;
import com.hapicker.common.dto.UserConnectInfoDTO;
import com.hapicker.common.dto.UserInfoDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping(value = "getUserInfo")
    ResponseDTO<UserInfoDTO> getUserInfo(@RequestBody UserInfoDTO userInfoDTO);

    @PostMapping(value = "queryUserInfo")
    ResponseDTO<List<UserInfoDTO>> queryUserInfo(@RequestBody UserInfoDTO userInfoDTO);

    @RequestMapping(value = "getUserConnectInfo", method = RequestMethod.POST)
    ResponseDTO<UserConnectInfoDTO> getUserConnectInfo(@RequestBody UserConnectInfoDTO userConnectInfo);

    @RequestMapping(value = "insertUserInfo", method = RequestMethod.POST)
    ResponseDTO<UserInfoDTO> insertUserInfo(@RequestBody UserInfoDTO userInfo);
}
