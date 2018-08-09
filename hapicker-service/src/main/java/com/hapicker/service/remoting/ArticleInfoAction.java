package com.hapicker.service.remoting;

import com.hapicker.common.dto.CategoryInfoDTO;
import com.hapicker.common.dto.ResponseDTO;
import com.hapicker.common.dto.UserInfoDTO;
import com.hapicker.mapper.CategoryInfoMapper;
import com.hapicker.model.CategoryInfo;
import com.hapicker.model.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyufeng
 * @date 2018/8/9.
 */
@RestController
@Api(description = "Article相关")
@RequestMapping("article")
public class ArticleInfoAction {

    @Autowired
    private CategoryInfoMapper categoryInfoMapper;

    @ApiOperation(value = "Article类目", httpMethod = "POST")
    @RequestMapping(value = "listCategoryInfo", method = RequestMethod.POST)
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
}
