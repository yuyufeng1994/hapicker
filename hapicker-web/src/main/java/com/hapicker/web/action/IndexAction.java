package com.hapicker.web.action;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yuyufeng
 * @date 2018/7/26.
 */
@Controller
@RefreshScope
public class IndexAction {
    @Value("${base.name}")
    private String param;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    String index() {
        System.out.println(param);
        return "index";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    String home() {
        System.out.println(param);
        return "homepage";
    }


    @RequestMapping(value = "json", method = RequestMethod.GET)
    @ResponseBody
    String json() {
        return "index首页";
    }
}