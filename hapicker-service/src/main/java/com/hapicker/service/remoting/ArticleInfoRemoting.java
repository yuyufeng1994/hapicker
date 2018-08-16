package com.hapicker.service.remoting;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hapicker.common.constant.StatusEnum;
import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.CategoryInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.common.dto.ResponseDTO;
import com.hapicker.mapper.ArticleInfoMapper;
import com.hapicker.mapper.CategoryInfoMapper;
import com.hapicker.model.ArticleInfo;
import com.hapicker.model.CategoryInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yuyufeng
 * @date 2018/8/9.
 */
@RestController
@Api(description = "Article相关")
@RequestMapping("article")
public class ArticleInfoRemoting {

    @Autowired
    private CategoryInfoMapper categoryInfoMapper;

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @ApiOperation(value = "Article类目列表", httpMethod = "GET")
    @RequestMapping(value = "listCategoryInfo", method = RequestMethod.GET)
    ResponseDTO<List<CategoryInfoDTO>> listCategoryInfo() {
        List<CategoryInfo> categoryInfoList = categoryInfoMapper.selectAll();
        List<CategoryInfoDTO> categoryInfoDTOList = new ArrayList<>();
        for (CategoryInfo categoryInfo : categoryInfoList) {
            CategoryInfoDTO categoryInfoDTO = new CategoryInfoDTO();
            BeanUtils.copyProperties(categoryInfo,categoryInfoDTO);
            categoryInfoDTOList.add(categoryInfoDTO);
        }
        return ResponseDTO.success(categoryInfoDTOList);
    }


    @ApiOperation(value = "获取Article", httpMethod = "GET")
    @RequestMapping(value = "getArticle/{id}", method = RequestMethod.GET)
    ResponseDTO<ArticleInfoDTO> getArticleInfo(@PathVariable("id") Integer id) {
        ArticleInfoDTO articleInfo = articleInfoMapper.getArticleInfoById(id);
        return ResponseDTO.success(articleInfo);
    }


    @ApiOperation(value = "查询Article列表", httpMethod = "POST")
    @RequestMapping(value = "queryArticle", method = RequestMethod.POST)
    ResponseDTO<PageInfo<ArticleInfoDTO>> listArticle(@RequestBody RequestPageDTO<ArticleInfoDTO> requestPageDTO) {
        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(requestPageDTO.getContent(),articleInfo);
        PageHelper.startPage(requestPageDTO.getPageNo(), requestPageDTO.getPageSize(), requestPageDTO.getOrderBy());
        articleInfo.setArticleStatus(StatusEnum.NORMAL.getKey());
        List<ArticleInfoDTO> articleInfoList = articleInfoMapper.selectList(articleInfo);
        PageInfo<ArticleInfoDTO> articleInfoPageInfo = new PageInfo<>(articleInfoList);
        return ResponseDTO.success(articleInfoPageInfo);
    }


    @ApiOperation(value = "插入Article", httpMethod = "POST")
    @RequestMapping(value = "insertArticle", method = RequestMethod.POST)
    ResponseDTO insertArticle(@RequestBody ArticleInfoDTO articleInfoDTO) {
        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(articleInfoDTO, articleInfo);
        articleInfo.setUpdateTime(new Date());
        articleInfo.setCreateTime(new Date());
        articleInfoMapper.insertSelective(articleInfo);
        return ResponseDTO.success();
    }


    @ApiOperation(value = "修改Article", httpMethod = "PUT")
    @RequestMapping(value = "updateArticle", method = RequestMethod.PUT)
    ResponseDTO updateArticle(@RequestBody ArticleInfoDTO articleInfoDTO) {
        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(articleInfoDTO, articleInfo);
        articleInfo.setUpdateTime(new Date());
        articleInfo.setCreateTime(null);
        articleInfoMapper.updateByPrimaryKeySelective(articleInfo);
        return ResponseDTO.success();
    }

    @ApiOperation(value = "删除Article", httpMethod = "DELETE")
    @RequestMapping(value = "deleteArticle", method = RequestMethod.DELETE)
    ResponseDTO deleteArticle(@RequestBody Integer articleId) {
        articleInfoMapper.deleteByPrimaryKey(articleId);
        return ResponseDTO.success();
    }



}
