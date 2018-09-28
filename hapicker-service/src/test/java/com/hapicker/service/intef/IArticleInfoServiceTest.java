package com.hapicker.service.intef;

import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.CategoryInfoDTO;
import com.hapicker.model.ArticleCategoryInfo;
import com.hapicker.model.ArticleInfo;
import com.hapicker.model.CategoryInfo;
import com.hapicker.service.ApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * @author yuyufeng
 * @date 2018/9/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationService.class)
@WebAppConfiguration
public class IArticleInfoServiceTest {
    @Autowired
    private IArticleInfoService articleInfoService;
    @Test
    public void insertArticle() throws Exception {
        ArticleInfoDTO articleInfo = new ArticleInfoDTO();
        articleInfo.setArticleTitle("标题1");
        CategoryInfoDTO categoryInfo = new CategoryInfoDTO();
        categoryInfo.setCategoryId(1);
        articleInfo.getCategorys().add(categoryInfo);
        articleInfoService.insertArticle(articleInfo);
    }

}