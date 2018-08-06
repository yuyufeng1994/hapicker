package com.hapicker.service.remoting;

import com.hapicker.common.dto.UserInfoDTO;
import com.hapicker.mapper.UserInfoMapper;
import com.hapicker.model.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author yuyufeng
 * @date 2018/8/6.
 */
@RestController
public class HelloAction {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    DataSource dataSource;

    @RequestMapping("hello")
    UserInfoDTO hello() {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1);
        System.out.println(userInfo);
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtils.copyProperties(userInfo,userInfoDTO);
        return userInfoDTO;
    }
}
