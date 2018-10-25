package com.hapicker.mapper;

import com.hapicker.common.constant.UserConnectInfoPlatformEnum;
import com.hapicker.model.UserConnectInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author yuyufeng
 * @date 2018/10/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class UserConnectInfoMapperTest {

    @Autowired
    private UserConnectInfoMapper userConnectInfoMapper;

    @Test
    public void testInsert(){
        UserConnectInfo userConnectInfo = new UserConnectInfo();
        userConnectInfo.setUserId(1);
        userConnectInfo.setPlatformKey(UserConnectInfoPlatformEnum.TENCENT_QQ.getKey());
        userConnectInfo.setOpenId("1541dDAS54D5SA4D53AS4D5");
        userConnectInfo.setCreateTime(new Date());
        userConnectInfo.setUpdateTime(new Date());
        userConnectInfoMapper.insertSelective(userConnectInfo);
    }


}
