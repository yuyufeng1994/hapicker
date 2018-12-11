package com.hapicker.task.action;

import com.hapicker.task.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuyufeng
 * @date 2018/12/10.
 */
@RestController
public class IndexAction {

    @Autowired
    private MailService mailService;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOs;

    @Resource(name = "redisTemplate")
    private ListOperations<String, Long> listOperations;

    @RequestMapping("rp/{id}")
    public String add(@PathVariable("id") Long id) {
        listOperations.rightPush("bus_warning", id);
        return "success";
    }

    @RequestMapping("lp")
    public Long lp() {
        Long id = listOperations.leftPop("bus_warning");
        return id;
    }

    @RequestMapping("set")
    public String set() {
        valueOs.set("aa", "bb");
        return "success";
    }

    @RequestMapping("mail")
    public String mail() {
        String email = "yuyf@bababus.com";
        String content = "你好";
        String subject = "你好啊，yyf！";
        String receiverName = "hyu";
        try {
            mailService.sendEmail(email,content,subject,receiverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
