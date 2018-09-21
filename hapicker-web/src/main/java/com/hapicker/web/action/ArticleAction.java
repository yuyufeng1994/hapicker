package com.hapicker.web.action;

import com.github.pagehelper.PageInfo;
import com.hapicker.common.constant.ArticleInfoTypeEnum;
import com.hapicker.common.dto.ArticleInfoDTO;
import com.hapicker.common.dto.CategoryInfoDTO;
import com.hapicker.common.dto.RequestPageDTO;
import com.hapicker.web.client.ArticleClient;
import com.hapicker.web.vo.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuyufeng
 * @date 2018/8/10.
 */
@Controller
@RequestMapping("article")
public class ArticleAction {
    @Autowired
    private ArticleClient articleClient;
    @Value("${upload.path}")
    private String path;

    @RequestMapping(value = "blog/{pageNo}", method = RequestMethod.GET)
    String blogList(@PathVariable("pageNo") Integer pageNo, Model model) {
        ArticleInfoDTO articleInfoDTO = new ArticleInfoDTO();
        articleInfoDTO.setArticleType(ArticleInfoTypeEnum.BLOG.getKey());
        RequestPageDTO<ArticleInfoDTO> requestPageDTO = new RequestPageDTO<>(pageNo, articleInfoDTO, "create_time desc");
        PageInfo<ArticleInfoDTO> articleInfoDTOPageInfo = articleClient.queryArticle(requestPageDTO).getContent();

        List<CategoryInfoDTO> categoryInfoDTOList = articleClient.listCategoryInfo().getContent();

        model.addAttribute("page", articleInfoDTOPageInfo);
        model.addAttribute("categoryList", categoryInfoDTOList);
        model.addAttribute("articleTypeName", ArticleInfoTypeEnum.getValue("blog"));
        model.addAttribute("pageUrl", "/article/blog/");
        return "article/list";
    }


    @RequestMapping(value = "essay/{pageNo}", method = RequestMethod.GET)
    String essayList(@PathVariable("pageNo") Integer pageNo, Model model) {
        ArticleInfoDTO articleInfoDTO = new ArticleInfoDTO();
        articleInfoDTO.setArticleType(ArticleInfoTypeEnum.ESSAY.getKey());
        RequestPageDTO<ArticleInfoDTO> requestPageDTO = new RequestPageDTO<>(pageNo, articleInfoDTO, "create_time desc");
        PageInfo<ArticleInfoDTO> articleInfoDTOPageInfo = articleClient.queryArticle(requestPageDTO).getContent();
        model.addAttribute("page", articleInfoDTOPageInfo);
        model.addAttribute("articleTypeName", ArticleInfoTypeEnum.getValue("essay"));
        model.addAttribute("pageUrl", "/article/essay/");
        return "article/list";
    }

    @RequestMapping(value = "category/{categoryId}/{pageNo}", method = RequestMethod.GET)
    String categoryList(@PathVariable("categoryId") Integer categoryId, @PathVariable("pageNo") Integer pageNo, Model model) {
        RequestPageDTO<Integer> requestPageDTO = new RequestPageDTO<Integer>(pageNo, categoryId, "create_time desc");
        PageInfo<ArticleInfoDTO> articleInfoDTOPageInfo = articleClient.listArticleByCategoryId(requestPageDTO).getContent();
        model.addAttribute("page", articleInfoDTOPageInfo);
        List<CategoryInfoDTO> categoryInfoDTOList = articleClient.listCategoryInfo().getContent();
        CategoryInfoDTO categoryInfoDTO = articleClient.getCategoryInfoById(categoryId).getContent();
        model.addAttribute("categoryList", categoryInfoDTOList);
        model.addAttribute("category", categoryInfoDTO);
        model.addAttribute("articleTypeName", categoryInfoDTO.getCategoryName());
        model.addAttribute("pageUrl", "/article/category/" + categoryId + "/");
        return "article/list";
    }

    @RequestMapping(value = "content/{id}", method = RequestMethod.GET)
    String content(@PathVariable("id") Integer id, Model model) {
        ArticleInfoDTO articleInfoDTO = articleClient.getArticleByArticleId(id).getContent();
        model.addAttribute("at", articleInfoDTO);
        return "article/content";
    }

    /**
     * 文件流
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/stream/{uuid}/{ext}", method = RequestMethod.GET)
    public String thumbnail(HttpServletRequest request, HttpSession session, HttpServletResponse response,
                            @PathVariable("uuid") String uuid, @PathVariable("ext") String ext) {
        try {
            InputStream inputStream = new FileInputStream(path + uuid + "." + ext);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * 专门针对后台富文本的上传接口
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    UploadResult doUpload(HttpServletRequest request) throws IOException {
        File doc = new File(path);
        if (!doc.exists()) {
            doc.mkdirs();
        }
        UploadResult ur = new UploadResult();
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        String[] strArr = new String[fileMap.size()];
        int i = 0;
        for (String fileName : fileMap.keySet()) {
            String uuid = UUID.randomUUID().toString();
            String ext = StringUtils.getFilenameExtension(fileName);
            MultipartFile file = fileMap.get(fileName);
            File getFile = new File(path + uuid + "." + ext);
            file.transferTo(getFile);
            strArr[i] = "/article/stream/" + uuid + "/" + ext;
            i++;
        }
        ur.setErrno(0);
        ur.setData(strArr);
        return ur;
    }

}
