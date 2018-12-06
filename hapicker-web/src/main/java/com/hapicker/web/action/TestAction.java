package com.hapicker.web.action;

import com.hapicker.common.constant.ExceptionCode;
import com.hapicker.common.exception.BaseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuyufeng
 * @date 2018/12/6.
 */
@RestController
@RequestMapping(value = "test")
public class TestAction {
    @RequestMapping(value = "permission")
    public String permissionTest() {
        if (1 == 1) {
            throw new BaseException(ExceptionCode.NO_LOGIN, "未登录");
        }
        return "";
    }


}
