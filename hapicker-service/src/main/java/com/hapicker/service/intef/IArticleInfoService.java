package com.hapicker.service.intef;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;

/**
 * @author yuyufeng
 * @date 2018/9/28.
 */
public interface IArticleInfoService {
    /**
     * 插入文章 和 类别
     * @param articleInfoDTO
     */
    void insertArticle(ArticleInfoDTO articleInfoDTO);

    /**
     * 分页查询
     * @param requestPageDTO
     * @return
     */
    PageInfo<ArticleInfoDTO> listArticle(RequestPageDTO<ArticleInfoDTO> requestPageDTO);
}
