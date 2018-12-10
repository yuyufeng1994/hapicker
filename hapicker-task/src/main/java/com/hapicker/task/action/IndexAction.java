package com.hapicker.task.action;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuyufeng
 * @date 2018/12/10.
 */
@RestController
public class IndexAction {
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOs;

    @RequestMapping("set")
    public String set(){
        valueOs.set("aa","bb");
        return "success";
    }
}
