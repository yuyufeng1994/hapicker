package com.hapicker.web.config;

import com.hapicker.common.constant.ExceptionCode;
import com.hapicker.common.exception.BaseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author yuyufeng
 * @date 2018/8/20.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception ex, Model model) {
        ex.printStackTrace();
        System.out.println("GlobalExceptionHandler.errorHandler");
        model.addAttribute("code", 500);
        model.addAttribute("msg", ex.getMessage());
        return "error";
    }


    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public String baseErrorHandler(BaseException ex, Model model) {
        //如果未登录，直接跳到登录页面
        if (ExceptionCode.NO_LOGIN == ex.getCode()) {
            return "login";
        }
        System.out.println("GlobalExceptionHandler.baseErrorHandler");
        model.addAttribute("code", ex.getCode());
        model.addAttribute("msg", ex.getMsg());
        return "error";
    }


}
