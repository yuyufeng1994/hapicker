package com.hapicker.mapper;

import com.hapicker.common.dto.CategoryInfoDTO;
import com.hapicker.model.CategoryInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yuyufeng
 */
public interface CategoryInfoMapper extends Mapper<CategoryInfo> {
    List<CategoryInfoDTO> listCategoryInfoByArticleId(@Param("articleId") Integer articleId);
}