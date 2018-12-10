package com.hapicker.common.constant;

/**
 * 消息类型
 * 0系统消息，1用户消息，2服务消息
 *
 * @author yuyufeng
 * @date 2017/8/16
 */
public enum MessageInfoTypeEnum {
    SYSTEM(0, "系统消息"), USER(1, "用户消息"), SERVICE(2, "服务消息");
    private Integer key;
    private String value;

    MessageInfoTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        if (key == null) {
            return "";
        }
        for (MessageInfoTypeEnum item : MessageInfoTypeEnum.values()) {
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
