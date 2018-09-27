package com.hapicker.web.action;

import com.hapicker.common.constant.StatusEnum;
import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.UserInfoDTO;
import com.hapicker.web.client.ArticleClient;
import com.hapicker.web.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yuyufeng
 * @date 2018/8/20.
 */
@Controller
@RequestMapping("/user/{userId}")
public class UserAction {
    @Autowired
    private UserClient userClient;
    @Autowired
    private ArticleClient articleClient;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    String info(Model model, @PathVariable("userId") Integer userId) {
        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setUserId(userId);
        userInfo = userClient.getUserInfo(userInfo).getContent();
        model.addAttribute("userInfo", userInfo);
        return "user/info";

    }

    @RequestMapping(value = "/article/create", method = RequestMethod.GET)
    String articleCreate(Model model, @PathVariable("userId") Integer userId) {
        /*UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setUserId(userId);
        userInfo = userClient.getUserInfo(userInfo).getContent();
        model.addAttribute("userInfo", userInfo);*/
        return "user/article/create";

    }
    @RequestMapping(value = "/article/doCreate", method = RequestMethod.POST)
    String articleDoCreate(Model model, @PathVariable("userId") Integer userId, ArticleInfoDTO articleInfoDTO) {
        articleInfoDTO.setUserId(userId);
        articleInfoDTO.setArticleStatus(StatusEnum.NORMAL.getKey());
        System.out.println(articleInfoDTO);
        articleClient.insertArticle(articleInfoDTO);
        return "user/article/message";

    }
}
