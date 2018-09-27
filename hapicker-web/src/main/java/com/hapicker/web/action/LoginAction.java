package com.hapicker.web.action;

import com.hapicker.common.constant.SessionConstant;
import com.hapicker.common.dto.UserInfoDTO;
import com.hapicker.common.exception.BaseException;
import com.hapicker.common.util.MD5Util;
import com.hapicker.common.util.UUIDUtil;
import com.hapicker.common.util.ValidationUtil;
import com.hapicker.web.client.UserClient;
import com.hapicker.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuyufeng
 * @date 2018/8/20.
 */
@Controller
public class LoginAction {
    @Value("${base.salt}")
    private String salt;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOs;

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private UserClient userClient;

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    String login(String returnUrl, Model model, HttpServletRequest httpServletRequest) {
        if (sessionUtil.getSession(httpServletRequest, SessionConstant.SESSION_USER) != null) {
            return "redirect:/index";
        }
        //如果已经登录，则跳转到主页
        model.addAttribute("returnUrl", returnUrl);
        return "login";
    }

    @RequestMapping(value = "logout", method = {RequestMethod.GET, RequestMethod.POST})
    String logout(String returnUrl, Model model, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        //如果已经登录，则跳转到主页
        model.addAttribute("returnUrl", returnUrl);
        sessionUtil.deleteSession(httpServletRequest, httpServletResponse, SessionConstant.SESSION_USER);
        if (!StringUtils.isEmpty(returnUrl)) {
            return "redirect:" + returnUrl;
        }
        return "login";
    }

    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    String doLogin(String userAccount, String userPwd, Boolean rememberMe, String returnUrl, Model model, HttpServletResponse httpServletResponse) {
        model.addAttribute("returnUrl", returnUrl);
        if (!ValidationUtil.checkAccount(userAccount) && ValidationUtil.checkPwd(userPwd)) {
            throw new BaseException(100, "登录数据不正确");
        }

        UserInfoDTO userInfo = new UserInfoDTO();

        try {
            if (ValidationUtil.checkEmail(userAccount)) {
                userInfo.setUserEmail(userAccount);
                userInfo = userClient.getUserInfo(userInfo).getContent();
            } else {
                userInfo.setUserName(userAccount);
                userInfo = userClient.getUserInfo(userInfo).getContent();
            }
            if (!userInfo.getUserPwd().equals(MD5Util.getMd5(salt + userPwd))) {
                model.addAttribute("errorMessage", "密码不正确");
                return "login";
            }
            sessionUtil.setSession(httpServletResponse, userInfo, SessionConstant.SESSION_USER);
//            System.out.println(userInfo);
//            System.out.println(rememberMe);
//            System.out.println(salt);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "用户不存在");
            return "login";
        }

        return "redirect:" + returnUrl;
    }


    @RequestMapping(value = "register", method = RequestMethod.GET)
    String register() {
        return "register";
    }
}
