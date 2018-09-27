package com.hapicker.web.interceptor;

import com.hapicker.common.constant.SessionConstant;
import com.hapicker.common.dto.UserInfoDTO;
import com.hapicker.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuyufeng
 * @date 2018/8/20.
 */
@Component
public class RootInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionUtil sessionUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        System.out.println("RootInterceptor.preHandle " + uri);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserInfoDTO userInfoDTO = (UserInfoDTO) sessionUtil.getSession(request, SessionConstant.SESSION_USER);
        if (userInfoDTO != null && modelAndView != null) {
            modelAndView.addObject(SessionConstant.SESSION_USER, userInfoDTO);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
