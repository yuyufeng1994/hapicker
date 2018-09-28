package com.hapicker.service.impl;

import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.CategoryInfoDTO;
import com.hapicker.mapper.ArticleCategoryInfoMapper;
import com.hapicker.mapper.ArticleInfoMapper;
import com.hapicker.mapper.CategoryInfoMapper;
import com.hapicker.model.ArticleCategoryInfo;
import com.hapicker.model.ArticleInfo;
import com.hapicker.service.intef.IArticleInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yuyufeng
 * @date 2018/9/28.
 */
@Service
public class ArticleInfoServiceIImpl implements IArticleInfoService {

    @Autowired
    private CategoryInfoMapper categoryInfoMapper;

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Autowired
    private ArticleCategoryInfoMapper articleCategoryInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertArticle(ArticleInfoDTO articleInfoDTO) {

        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(articleInfoDTO, articleInfo);
        articleInfo.setUpdateTime(new Date());
        articleInfo.setCreateTime(new Date());
        articleInfoMapper.insertSelectiveReturnKey(articleInfo);
        //插入类目
        List<CategoryInfoDTO> categoryInfoDTOList = articleInfoDTO.getCategorys();
        List<ArticleCategoryInfo> articleCategoryInfoList = new ArrayList<>();
        if (categoryInfoDTOList != null && categoryInfoDTOList.size() > 0) {
            for (CategoryInfoDTO categoryInfoDTO : categoryInfoDTOList) {
                ArticleCategoryInfo articleCategoryInfo = new ArticleCategoryInfo();
                articleCategoryInfo.setCategoryId(categoryInfoDTO.getCategoryId());
                articleCategoryInfo.setArticleId(articleInfo.getArticleId());
                articleCategoryInfoList.add(articleCategoryInfo);
            }
            articleCategoryInfoMapper.insertBatch(articleCategoryInfoList);
        }

    }
}
