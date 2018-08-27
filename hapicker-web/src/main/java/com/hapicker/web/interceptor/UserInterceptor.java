package com.hapicker.web.interceptor;

import com.hapicker.common.constant.SessionConstant;
import com.hapicker.common.dto.UserInfoDTO;
import com.hapicker.common.exception.BaseException;
import com.hapicker.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对用户操作拦截
 *
 * @author yuyufeng
 * @date 2018/8/20.
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    private SessionUtil sessionUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfoDTO userInfoDTO = (UserInfoDTO) sessionUtil.getSession(request, SessionConstant.SESSION_USER);
        if (userInfoDTO == null) {
            throw new BaseException(505, "无权访问");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
