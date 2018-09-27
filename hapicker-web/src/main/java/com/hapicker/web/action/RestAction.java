package com.hapicker.web.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuyufeng
 * @date 2018/9/26.
 */
@RestController
@RequestMapping(value = "rest")
public class RestAction {
    @RequestMapping(value = "json", method = RequestMethod.GET)
    @ResponseBody
    String json() {
        return "你好";
    }
}
