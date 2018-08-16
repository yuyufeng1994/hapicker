package com.hapicker.common.constant;

/**
 * 状态
 *
 * @author yuyufeng
 * @date 2017/8/16
 */
public enum StatusEnum {
    NORMAL(0, "正常"), PRIVATE(1, "私有"), LOCKED(2, "锁定"),AUDITING(3,"审核中"),DELETED(4,"已删除");
    private Integer key;
    private String value;

    StatusEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        if (key == null) {
            return "";
        }
        for (StatusEnum item : StatusEnum.values()) {
            if (item.getKey().equals(key)) {
                return item.value;
            }
        }
        return "unknow";
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
