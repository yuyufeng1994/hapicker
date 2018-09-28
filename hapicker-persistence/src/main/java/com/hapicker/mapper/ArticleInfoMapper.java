package com.hapicker.mapper;

import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.model.ArticleInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yuyufeng
 */
public interface ArticleInfoMapper extends Mapper<ArticleInfo> {
    /**
     * 获取单个文章
     * @param articleId
     * @return
     */
    ArticleInfoDTO getArticleInfoById(@Param("articleId") Integer articleId);

    /**
     * 获取文章列表
     * @param articleInfo
     * @return
     */
    List<ArticleInfoDTO> selectList(ArticleInfo articleInfo);

    /**
     * 通过分类ID查询文章列表
     * @param categoryId
     * @return
     */
    List<ArticleInfoDTO> selectListByCategoryId(@Param("categoryId") Integer categoryId);

    /**
     * 插入之后返回主键
     * @param articleInfo
     * @return
     */
    int insertSelectiveReturnKey(ArticleInfo articleInfo);
}