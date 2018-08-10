package com.hapicker.web.action;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.constant.ArticleInfoTypeEnum;
import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.web.client.ArticleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yuyufeng
 * @date 2018/8/10.
 */
@Controller
@RequestMapping("article")
public class ArticleAction {
    @Autowired
    private ArticleClient articleClient;

    @RequestMapping(value = "blog/{pageNo}", method = RequestMethod.GET)
    String blogList(@PathVariable("pageNo") Integer pageNo, Model model) {
        ArticleInfoDTO articleInfoDTO = new ArticleInfoDTO();
        articleInfoDTO.setArticleType(ArticleInfoTypeEnum.BLOG.getKey());
        RequestPageDTO<ArticleInfoDTO> requestPageDTO = new RequestPageDTO<>(pageNo, articleInfoDTO, "create_time desc");
        PageInfo<ArticleInfoDTO> articleInfoDTOPageInfo = articleClient.queryArticle(requestPageDTO).getContent();
        model.addAttribute("articlePage", articleInfoDTOPageInfo);
        return "article/list";
    }


    @RequestMapping(value = "essay/{pageNo}", method = RequestMethod.GET)
    String essayList(@PathVariable("pageNo") Integer pageNo, Model model) {
        ArticleInfoDTO articleInfoDTO = new ArticleInfoDTO();
        articleInfoDTO.setArticleType(ArticleInfoTypeEnum.ESSAY.getKey());
        RequestPageDTO<ArticleInfoDTO> requestPageDTO = new RequestPageDTO<>(pageNo, articleInfoDTO, "create_time desc");
        PageInfo<ArticleInfoDTO> articleInfoDTOPageInfo = articleClient.queryArticle(requestPageDTO).getContent();
        model.addAttribute("articlePage", articleInfoDTOPageInfo);
        return "article/list";
    }
}
