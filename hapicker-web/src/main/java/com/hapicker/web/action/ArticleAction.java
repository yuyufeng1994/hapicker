package com.hapicker.web.action;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.constant.ArticleInfoTypeEnum;
import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.CategoryInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.web.client.ArticleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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

        List<CategoryInfoDTO> categoryInfoDTOList = articleClient.listCategoryInfo().getContent();

        model.addAttribute("page", articleInfoDTOPageInfo);
        model.addAttribute("categoryList", categoryInfoDTOList);
        model.addAttribute("articleTypeName", ArticleInfoTypeEnum.getValue("blog"));
        model.addAttribute("pageUrl", "/article/blog/");
        return "article/list";
    }


    @RequestMapping(value = "essay/{pageNo}", method = RequestMethod.GET)
    String essayList(@PathVariable("pageNo") Integer pageNo, Model model) {
        ArticleInfoDTO articleInfoDTO = new ArticleInfoDTO();
        articleInfoDTO.setArticleType(ArticleInfoTypeEnum.ESSAY.getKey());
        RequestPageDTO<ArticleInfoDTO> requestPageDTO = new RequestPageDTO<>(pageNo, articleInfoDTO, "create_time desc");
        PageInfo<ArticleInfoDTO> articleInfoDTOPageInfo = articleClient.queryArticle(requestPageDTO).getContent();
        model.addAttribute("page", articleInfoDTOPageInfo);
        model.addAttribute("articleTypeName", ArticleInfoTypeEnum.getValue("essay"));
        model.addAttribute("pageUrl", "/article/essay/");
        return "article/list";
    }

    @RequestMapping(value = "category/{categoryId}/{pageNo}", method = RequestMethod.GET)
    String categoryList(@PathVariable("categoryId") Integer categoryId, @PathVariable("pageNo") Integer pageNo, Model model) {
        RequestPageDTO<Integer> requestPageDTO = new RequestPageDTO<Integer>(pageNo, categoryId, "create_time desc");
        PageInfo<ArticleInfoDTO> articleInfoDTOPageInfo = articleClient.listArticleByCategoryId(requestPageDTO).getContent();
        model.addAttribute("page", articleInfoDTOPageInfo);
        List<CategoryInfoDTO> categoryInfoDTOList = articleClient.listCategoryInfo().getContent();
        CategoryInfoDTO categoryInfoDTO = articleClient.getCategoryInfoById(categoryId).getContent();
        model.addAttribute("categoryList", categoryInfoDTOList);
        model.addAttribute("category", categoryInfoDTO);
        model.addAttribute("articleTypeName", categoryInfoDTO.getCategoryName());
        model.addAttribute("pageUrl", "/article/category/" + categoryId + "/");
        return "article/list";
    }

    @RequestMapping(value = "content/{id}", method = RequestMethod.GET)
    String content(@PathVariable("id") Integer id, Model model) {
        ArticleInfoDTO articleInfoDTO = articleClient.getArticleByArticleId(id).getContent();
        model.addAttribute("at", articleInfoDTO);
        return "article/content";
    }
}
