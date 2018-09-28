package com.hapicker.service.intef;

import com.hapicker.common.dto.ArticleInfoDTO; /**
 * @author yuyufeng
 * @date 2018/9/28.
 */
public interface IArticleInfoService {
    /**
     * 插入文章 和 类别
     * @param articleInfoDTO
     */
    void insertArticle(ArticleInfoDTO articleInfoDTO);
}
