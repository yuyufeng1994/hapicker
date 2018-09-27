package com.hapicker.web.client;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.CategoryInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.common.dto.ResponseDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "queryArticle")
    ResponseDTO<PageInfo<ArticleInfoDTO>> queryArticle(@RequestBody RequestPageDTO<ArticleInfoDTO> requestPageDTO);

    /**
     * 获取单个文章
     * @param id
     * @return
     */
    @GetMapping(value = "getArticle/{id}")
    ResponseDTO<ArticleInfoDTO> getArticleByArticleId(@PathVariable("id") Integer id);

    /**
     * 获取类目
     * @return
     */
    @GetMapping(value = "listCategoryInfo")
    ResponseDTO<List<CategoryInfoDTO>> listCategoryInfo();

    /**
     * 根据分类查询文章列表
     * @param requestPageDTO
     * @return
     */
    @PostMapping(value = "queryArticleByCategoryId")
    ResponseDTO<PageInfo<ArticleInfoDTO>> listArticleByCategoryId(@RequestBody RequestPageDTO<Integer> requestPageDTO);

    /**
     * 获取类目详情
     * @param categoryId
     * @return
     */
    @GetMapping(value = "category/{categoryId}")
    ResponseDTO<CategoryInfoDTO> getCategoryInfoById(@PathVariable("categoryId") Integer categoryId);

    /**
     * 插入文章
     * @param articleInfoDTO
     * @return
     */
    @PostMapping(value = "insertArticle")
    ResponseDTO insertArticle(@RequestBody ArticleInfoDTO articleInfoDTO);
}
