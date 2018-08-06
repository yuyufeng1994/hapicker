package com.hapicker.service.remoting;

import com.hapicker.mapper.UserInfoMapper;
import com.hapicker.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author yuyufeng
 * @date 2018/8/6.
 */
@RestController
public class IndexAction {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    DataSource dataSource;

    @RequestMapping("index")
    UserInfo index() {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1);
        System.out.println(userInfo);
        return userInfo;
    }
}
