package com.hapicker.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author yuyufeng
 */
@Table(name = "category_info")
public class CategoryInfo {
    /**
     * 类别ID
     */
    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 类别名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 类别简介
     */
    @Column(name = "category_brief")
    private String categoryBrief;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;



    public CategoryInfo() {
        super();
    }

    /**
     * 获取类别ID
     *
     * @return category_id - 类别ID
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置类别ID
     *
     * @param categoryId 类别ID
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取类别名称
     *
     * @return category_name - 类别名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置类别名称
     *
     * @param categoryName 类别名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    /**
     * 获取类别简介
     *
     * @return category_brief - 类别简介
     */
    public String getCategoryBrief() {
        return categoryBrief;
    }

    /**
     * 设置类别简介
     *
     * @param categoryBrief 类别简介
     */
    public void setCategoryBrief(String categoryBrief) {
        this.categoryBrief = categoryBrief == null ? null : categoryBrief.trim();
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
}