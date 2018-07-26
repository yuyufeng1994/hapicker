package com.hapicker.web.action;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yuyufeng
 * @date 2018/7/26.
 */
@Controller
@RefreshScope
public class IndexAction {
    @Value("${base.name}")
    private String param;

    @RequestMapping(value = "index",method = RequestMethod.GET)
    String index(){
        System.out.println(param);
        return "index";
    }
}
