package com.hapicker.service.remoting;

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

/**
 * @author yuyufeng
 * @date 2018/8/6.
 */
@RestController
@Api(description = "Hello你好")
public class HelloRemoting {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @ApiOperation(value = "hello", httpMethod = "POST")
    @RequestMapping(value = "hello",method = RequestMethod.POST)
    ResponseDTO<UserInfoDTO> hello(@ApiParam(value = "查询对象") @RequestBody UserInfoDTO requestDTO) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(requestDTO.getUserId());
        System.out.println(userInfo);
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo, userInfoDTO);
        return ResponseDTO.success(userInfoDTO);
    }
}
