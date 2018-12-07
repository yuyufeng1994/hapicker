package com.hapicker.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hapicker.common.constant.StatusEnum;
import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.CategoryInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
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
import org.springframework.util.StringUtils;

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
        if(StringUtils.isEmpty(articleInfo.getArticleId())){
            articleInfo.setCreateTime(new Date());
            articleInfoMapper.insertSelectiveReturnKey(articleInfo);
        }else{
            articleInfo.setCreateTime(null);
            articleInfoMapper.updateByPrimaryKeySelective(articleInfo);
            ArticleCategoryInfo acRecord = new ArticleCategoryInfo();
            acRecord.setArticleId(articleInfo.getArticleId());
            articleCategoryInfoMapper.delete(acRecord);
        }

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

    @Override
    public PageInfo<ArticleInfoDTO> listArticle(RequestPageDTO<ArticleInfoDTO> requestPageDTO) {
        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(requestPageDTO.getContent(), articleInfo);
        PageHelper.startPage(requestPageDTO.getPageNo(), requestPageDTO.getPageSize(), requestPageDTO.getOrderBy());
        articleInfo.setArticleStatus(StatusEnum.NORMAL.getKey());
        List<ArticleInfoDTO> articleInfoList = articleInfoMapper.selectList(articleInfo);
        PageInfo<ArticleInfoDTO> articleInfoPageInfo = new PageInfo<>(articleInfoList);
        return articleInfoPageInfo;
    }
}
