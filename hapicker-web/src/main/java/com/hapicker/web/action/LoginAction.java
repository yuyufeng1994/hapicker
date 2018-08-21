package com.hapicker.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yuyufeng
 * @date 2018/8/20.
 */
@Controller
public class LoginAction {
    @RequestMapping(value = "login", method = RequestMethod.GET)
    String login() {
        return "login";
    }


    @RequestMapping(value = "register", method = RequestMethod.GET)
    String register() {
        return "register";
    }
}
