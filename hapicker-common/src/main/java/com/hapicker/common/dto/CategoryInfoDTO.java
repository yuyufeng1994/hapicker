package com.hapicker.common.dto;

/**
 * @author yuyufeng
 * @date 2018/8/9.
 */
public class CategoryInfoDTO {
    private Integer categoryId;
    private String categoryName;
    private String categoryBrief;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryBrief() {
        return categoryBrief;
    }

    public void setCategoryBrief(String categoryBrief) {
        this.categoryBrief = categoryBrief;
    }
}
