package com.hapicker.common.dto;

/**
 * @author yuyufeng
 * @date 2018/10/25.
 */
public class UserConnectInfoDTO {

    private Integer userId;

    private String platformKey;

    private String openId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPlatformKey() {
        return platformKey;
    }

    public void setPlatformKey(String platformKey) {
        this.platformKey = platformKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
