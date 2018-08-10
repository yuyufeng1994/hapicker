package com.hapicker.web.action;

import com.hapicker.common.dto.ResponseDTO;
import com.hapicker.common.dto.UserInfoDTO;
import com.hapicker.web.client.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuyufeng
 * @date 2018/8/6.
 */
@RestController
public class HelloAction {
    @Autowired
    private HelloClient helloClient;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    UserInfoDTO hello() {
        System.out.println("HelloAction.hello");
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUserId(0);
        ResponseDTO<UserInfoDTO> responseDTO = helloClient.hello(userInfoDTO);
        return responseDTO.getContent();
    }
}
