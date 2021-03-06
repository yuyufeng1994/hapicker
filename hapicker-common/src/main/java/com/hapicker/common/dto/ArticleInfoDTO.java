package com.hapicker.common.dto;

import com.hapicker.common.constant.ArticleInfoTypeEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yuyufeng
 */
public class ArticleInfoDTO {
    /**
     * 文章ID
     */
    private Integer articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章简介
     */
    private String articleBrief;

    /**
     * 封面地址
     */
    private String articleCover;


    /**
     * 文章来源
     */
    private String articleSource;

    /**
     * 文章类别（博客、笔记等）
     */
    private String articleType;

    /**
     * 文章类别名称（博客、笔记等）
     */
    private String articleTypeName;

    /**
     * 文章状态
     */
    private Integer articleStatus;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 用户简介
     */
    private String userProfile;




    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章分类
     */
    private List<CategoryInfoDTO> categorys = new ArrayList<>();



    public ArticleInfoDTO() {
        super();
    }

    /**
     * 获取文章ID
     *
     * @return article_id - 文章ID
     */
    public Integer getArticleId() {
        return articleId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    /**
     * 设置文章ID
     *
     * @param articleId 文章ID
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取文章标题
     *
     * @return article_title - 文章标题
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * 设置文章标题
     *
     * @param articleTitle 文章标题
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    /**
     * 获取文章简介
     *
     * @return article_brief - 文章简介
     */
    public String getArticleBrief() {
        return articleBrief;
    }

    /**
     * 设置文章简介
     *
     * @param articleBrief 文章简介
     */
    public void setArticleBrief(String articleBrief) {
        this.articleBrief = articleBrief == null ? null : articleBrief.trim();
    }

    /**
     * 获取文章来源
     *
     * @return article_source - 文章来源
     */
    public String getArticleSource() {
        return articleSource;
    }

    /**
     * 设置文章来源
     *
     * @param articleSource 文章来源
     */
    public void setArticleSource(String articleSource) {
        this.articleSource = articleSource == null ? null : articleSource.trim();
    }

    /**
     * 获取文章类别（博客、笔记等）
     *
     * @return article_type - 文章类别（博客、笔记等）
     */
    public String getArticleType() {
        return articleType;
    }

    /**
     * 设置文章类别（博客、笔记等）
     *
     * @param articleType 文章类别（博客、笔记等）
     */
    public void setArticleType(String articleType) {
        this.articleType = articleType == null ? null : articleType.trim();
    }

    /**
     * 获取文章状态
     *
     * @return article_status - 文章状态
     */
    public Integer getArticleStatus() {
        return articleStatus;
    }

    /**
     * 设置文章状态
     *
     * @param articleStatus 文章状态
     */
    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取文章内容
     *
     * @return article_content - 文章内容
     */
    public String getArticleContent() {
        return articleContent;
    }

    /**
     * 设置文章内容
     *
     * @param articleContent 文章内容
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    public String getArticleTypeName() {
        return ArticleInfoTypeEnum.getValue(this.articleType);
    }

    public void setArticleTypeName(String articleTypeName) {
        this.articleTypeName = articleTypeName;
    }

    public String getArticleCover() {
        return articleCover;
    }

    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public List<CategoryInfoDTO> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategoryInfoDTO> categorys) {
        this.categorys = categorys;
    }

    @Override
    public String toString() {
        return "ArticleInfoDTO{" +
                "articleId=" + articleId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleBrief='" + articleBrief + '\'' +
                ", articleCover='" + articleCover + '\'' +
                ", articleSource='" + articleSource + '\'' +
                ", articleType='" + articleType + '\'' +
                ", articleTypeName='" + articleTypeName + '\'' +
                ", articleStatus=" + articleStatus +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userNick='" + userNick + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", userProfile='" + userProfile + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", articleContent='" + articleContent + '\'' +
                ", categorys=" + categorys +
                '}';
    }
}