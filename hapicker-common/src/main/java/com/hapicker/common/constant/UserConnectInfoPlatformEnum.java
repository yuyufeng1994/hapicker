package com.hapicker.common.constant;

/**
 * 平台
 *
 * @author yuyufeng
 * @date 2017/8/16
 */
public enum UserConnectInfoPlatformEnum {
    TENCENT_QQ("tencent_qq", "腾讯QQ"), TENCENT_WX("tencent_wx", "腾讯微信");
    private String key;
    private String value;

    UserConnectInfoPlatformEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        if (key == null) {
            return "";
        }
        for (UserConnectInfoPlatformEnum item : UserConnectInfoPlatformEnum.values()) {
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
