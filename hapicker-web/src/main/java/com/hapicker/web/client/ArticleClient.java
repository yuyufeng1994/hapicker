package com.hapicker.web.client;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.CategoryInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.common.dto.ResponseDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author yuyufeng
 * @date 2018/8/10.
 */
@FeignClient(value = "hapicker-service")
@RequestMapping(value = "article")
public interface ArticleClient {

    /**
     * 查询文章列表
     *
     * @param requestPageDTO
     * @return
     */
    @RequestMapping(value = "queryArticle", method = RequestMethod.POST)
    ResponseDTO<PageInfo<ArticleInfoDTO>> queryArticle(@RequestBody RequestPageDTO<ArticleInfoDTO> requestPageDTO);

    /**
     * 获取单个文章
     * @param id
     * @return
     */
    @RequestMapping(value = "getArticle/{id}", method = RequestMethod.GET)
    ResponseDTO<ArticleInfoDTO> getArticleByArticleId(@PathVariable("id") Integer id);

    /**
     * 获取类目
     * @return
     */
    @RequestMapping(value = "listCategoryInfo", method = RequestMethod.GET)
    ResponseDTO<List<CategoryInfoDTO>> listCategoryInfo();

    /**
     * 根据分类查询文章列表
     * @param requestPageDTO
     * @return
     */
    @RequestMapping(value = "queryArticleByCategoryId", method = RequestMethod.POST)
    ResponseDTO<PageInfo<ArticleInfoDTO>> listArticleByCategoryId(@RequestBody RequestPageDTO<Integer> requestPageDTO);

    /**
     * 获取类目详情
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "category/{categoryId}", method = RequestMethod.GET)
    ResponseDTO<CategoryInfoDTO> getCategoryInfoById(@PathVariable("categoryId") Integer categoryId);
}
