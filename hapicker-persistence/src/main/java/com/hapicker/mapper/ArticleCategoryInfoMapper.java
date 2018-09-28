package com.hapicker.mapper;

import com.hapicker.model.ArticleCategoryInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yuyufeng
 */
public interface ArticleCategoryInfoMapper extends Mapper<ArticleCategoryInfo> {

    /**
     * 批量插入
     * @param articleCategoryInfoList
     */
    void insertBatch(@Param("articleCategoryInfoList") List<ArticleCategoryInfo> articleCategoryInfoList);
}