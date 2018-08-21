package com.hapicker.web.action;


import com.hapicker.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yuyufeng
 * @date 2018/7/26.
 */
@Controller
@RefreshScope
public class IndexAction {
    @Value("${base.name}")
    private String param;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String root() {
        System.out.println(param);
        return "index";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    String index() {
        System.out.println(param);
        return "index";
    }

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOs;

    @RequestMapping(value = "home", method = RequestMethod.GET)
    String home() {
        valueOs.set("aa","bb",1000, TimeUnit.MILLISECONDS);
        System.out.println(valueOs.get("aa"));
        return "index";
    }


    @RequestMapping(value = "about", method = RequestMethod.GET)
    String about() {
        return "about";
    }


    @RequestMapping(value = "json", method = RequestMethod.GET)
    @ResponseBody
    String json() {
        return "index首页";
    }
}
