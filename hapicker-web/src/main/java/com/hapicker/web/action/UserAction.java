package com.hapicker.web.action;

import com.hapicker.common.constant.ArticleInfoTypeEnum;
import com.hapicker.common.constant.SessionConstant;
import com.hapicker.common.constant.StatusEnum;
import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.CategoryInfoDTO;
import com.hapicker.common.dto.ResponseDTO;
import com.hapicker.common.dto.UserInfoDTO;
import com.hapicker.common.exception.BaseException;
import com.hapicker.web.client.ArticleClient;
import com.hapicker.web.client.UserClient;
import com.hapicker.web.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private SessionUtil sessionUtil;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    String info(Model model, @PathVariable("userId") Integer userId) {
        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setUserId(userId);
        userInfo = userClient.getUserInfo(userInfo).getContent();
        model.addAttribute("userInfo", userInfo);
        return "user/info";

    }

    @RequestMapping(value = "/article/create", method = RequestMethod.GET)
    String articleCreate(Model model, @PathVariable("userId") Integer userId, HttpServletRequest request) {
        UserInfoDTO userInfoDTO = (UserInfoDTO) sessionUtil.getSession(request, SessionConstant.SESSION_USER);
        if(userInfoDTO.getUserStatus() != 1){
            throw new BaseException(505, "没有权限");
        }

        Map<String, String> ArticleInfoTypeEnumMap = new LinkedHashMap<>(10);
        for (ArticleInfoTypeEnum item : ArticleInfoTypeEnum.values()) {
            ArticleInfoTypeEnumMap.put(item.getKey(), item.getValue());
        }
        List<CategoryInfoDTO> categoryInfoDTOList = articleClient.listCategoryInfo().getContent();
        model.addAttribute("articleInfoTypes", ArticleInfoTypeEnumMap);
        model.addAttribute("categoryInfos", categoryInfoDTOList);
        return "user/article/create";
    }

    @RequestMapping(value = "/article/update/{articleId}", method = RequestMethod.GET)
    String articleUpdate(Model model, @PathVariable("userId") Integer userId, @PathVariable("articleId") Integer articleId, HttpServletRequest request) {
        UserInfoDTO userInfoDTO = (UserInfoDTO) sessionUtil.getSession(request, SessionConstant.SESSION_USER);
        if(userInfoDTO.getUserStatus() != 1){
            throw new BaseException(505, "没有权限");
        }

        ArticleInfoDTO articleInfo = articleClient.getArticleByArticleId(articleId).getContent();
        Map<String, String> ArticleInfoTypeEnumMap = new LinkedHashMap<>(10);
        for (ArticleInfoTypeEnum item : ArticleInfoTypeEnum.values()) {
            ArticleInfoTypeEnumMap.put(item.getKey(), item.getValue());
        }
        List<CategoryInfoDTO> categoryInfoDTOList = articleClient.listCategoryInfo().getContent();
        for (CategoryInfoDTO categoryInfoDTO : categoryInfoDTOList) {
            for (CategoryInfoDTO infoDTO : articleInfo.getCategorys()) {
                if (categoryInfoDTO.getCategoryId().equals(infoDTO.getCategoryId())) {
                    categoryInfoDTO.setChecked(true);
                }
            }
        }
        model.addAttribute("articleInfoTypes", ArticleInfoTypeEnumMap);
        model.addAttribute("categoryInfos", categoryInfoDTOList);
        model.addAttribute("articleInfo", articleInfo);
        return "user/article/create";
    }

    @RequestMapping(value = "/article/doCreate", method = RequestMethod.POST)
    String articleDoCreate(Model model, @PathVariable("userId") Integer userId, ArticleInfoDTO articleInfoDTO, Integer[] categoryId, HttpServletRequest request) {
        UserInfoDTO userInfoDTO = (UserInfoDTO) sessionUtil.getSession(request, SessionConstant.SESSION_USER);
        if(userInfoDTO.getUserStatus() != 1){
            throw new BaseException(505, "没有权限");
        }

        List<CategoryInfoDTO> categorys = articleInfoDTO.getCategorys();
        if (categoryId != null && categoryId.length > 0) {
            for (Integer item : categoryId) {
                CategoryInfoDTO categoryInfoDTO = new CategoryInfoDTO();
                categoryInfoDTO.setCategoryId(item);
                categorys.add(categoryInfoDTO);
            }
        }
        articleInfoDTO.setUserId(userId);
        articleInfoDTO.setArticleStatus(StatusEnum.NORMAL.getKey());
        articleClient.insertArticle(articleInfoDTO);
        return "user/article/message";

    }
}
