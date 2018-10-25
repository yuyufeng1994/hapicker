package com.hapicker.service.remoting;

import com.hapicker.common.dto.*;
import com.hapicker.mapper.UserInfoMapper;
import com.hapicker.model.UserConnectInfo;
import com.hapicker.model.UserInfo;
import com.hapicker.service.intef.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuyufeng
 * @date 2018/8/6.
 */
@RestController
@Api(description = "用户")
@RequestMapping(value = "user")
public class UserRemoting {

    @Autowired
    private IUserInfoService userInfoService;

    @ApiOperation(value = "getUserInfo", httpMethod = "POST")
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    ResponseDTO<UserInfoDTO> getUserInfo(@ApiParam(value = "查询对象") @RequestBody UserInfoDTO userInfoDTO) {
        UserInfoDTO userInfoDTOResult = userInfoService.getUserInfoDTO(userInfoDTO);
        return ResponseDTO.success(userInfoDTOResult);
    }

    @ApiOperation(value = "queryUserInfo", httpMethod = "POST")
    @RequestMapping(value = "queryUserInfo", method = RequestMethod.POST)
    ResponseDTO<List<UserInfoDTO>> queryUserInfo(@ApiParam(value = "查询对象") @RequestBody UserInfoDTO userInfoDTO) {
        List<UserInfoDTO> userInfoDTOResult = userInfoService.queryUserInfoDTO(userInfoDTO);
        return ResponseDTO.success(userInfoDTOResult);
    }

    @ApiOperation(value = "getUserConnectInfo", httpMethod = "POST")
    @RequestMapping(value = "getUserConnectInfo", method = RequestMethod.POST)
    ResponseDTO<UserConnectInfoDTO> getUserConnectInfo(@ApiParam(value = "查询用户互联对象") @RequestBody UserConnectInfoDTO userConnectInfo) {
        UserConnectInfoDTO userConnectInfoRecord = userInfoService.getUserConnectInfo(userConnectInfo);
        return ResponseDTO.success(userConnectInfoRecord);
    }

    @ApiOperation(value = "insertUserConnectInfo", httpMethod = "POST")
    @RequestMapping(value = "insertUserConnectInfo", method = RequestMethod.POST)
    ResponseDTO insertUserConnectInfo(@ApiParam(value = "插入用户互联对象") @RequestBody UserConnectInfoDTO userConnectInfo) {
         userInfoService.insertUserConnectInfo(userConnectInfo);
        return ResponseDTO.success();
    }

    @ApiOperation(value = "insertUserInfo", httpMethod = "POST")
    @RequestMapping(value = "insertUserInfo", method = RequestMethod.POST)
    ResponseDTO<UserInfoDTO> insertUserInfo(@ApiParam(value = "插入用户对象") @RequestBody UserInfoDTO userInfo) {
        UserInfoDTO userInfoDTO = userInfoService.insertUserInfo(userInfo);
        return ResponseDTO.success(userInfoDTO);
    }
}
