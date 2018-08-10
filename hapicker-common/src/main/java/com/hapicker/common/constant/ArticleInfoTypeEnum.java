package com.hapicker.common.constant;

/**
 * Article分类
 *
 * @author yuyufeng
 * @date 2017/8/7
 */
public enum ArticleInfoTypeEnum {
    ESSAY("essay", "随笔"), FORK("fork", "收藏"), BLOG("blog", "博客");
    private String key;
    private String value;

    ArticleInfoTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(String key) {
        if (key == null) {
            return "";
        }
        for (ArticleInfoTypeEnum item : ArticleInfoTypeEnum.values()) {
            if (item.getKey().equals(key)) {
                return item.value;
            }
        }
        return "unknow";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
