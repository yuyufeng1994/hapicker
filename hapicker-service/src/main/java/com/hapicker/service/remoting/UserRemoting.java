package com.hapicker.service.remoting;

import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.common.dto.ResponseDTO;
import com.hapicker.common.dto.UserInfoDTO;
import com.hapicker.mapper.UserInfoMapper;
import com.hapicker.model.UserInfo;
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
    private UserInfoMapper userInfoMapper;

    @ApiOperation(value = "getUserInfo", httpMethod = "POST")
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    ResponseDTO<UserInfoDTO> getUserInfo(@ApiParam(value = "查询对象") @RequestBody UserInfoDTO userInfoDTO) {
        UserInfo userInfoQuery = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, userInfoQuery);
        UserInfo userInfo = userInfoMapper.selectOne(userInfoQuery);
        UserInfoDTO userInfoDTOResult = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, userInfoDTOResult);
        return ResponseDTO.success(userInfoDTOResult);
    }

    @ApiOperation(value = "queryUserInfo", httpMethod = "POST")
    @RequestMapping(value = "queryUserInfo", method = RequestMethod.POST)
    ResponseDTO<List<UserInfoDTO>> queryUserInfo(@ApiParam(value = "查询对象") @RequestBody UserInfoDTO userInfoDTO) {
        UserInfo userInfoQuery = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, userInfoQuery);
        UserInfo userInfo = userInfoMapper.selectOne(userInfoQuery);
        UserInfoDTO userInfoDTOResult = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, userInfoDTOResult);
        return ResponseDTO.success(userInfoDTOResult);
    }
}
