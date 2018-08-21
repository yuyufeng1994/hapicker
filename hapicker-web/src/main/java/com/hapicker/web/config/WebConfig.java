package com.hapicker.web.config;

import com.hapicker.web.interceptor.RootInterceptor;
import com.hapicker.web.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yuyufeng
 * @date 2018/8/20.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RootInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/user/**");
    }
}
