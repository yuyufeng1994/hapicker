package com.hapicker.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user_connect_info")
public class UserConnectInfo {
    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 平台
     */
    @Column(name = "platform_key")
    private String platformKey;

    /**
     * 平台码
     */
    @Column(name = "open_id")
    private String openId;

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



    public UserConnectInfo() {
        super();
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
     * 获取平台
     *
     * @return platform_key - 平台
     */
    public String getPlatformKey() {
        return platformKey;
    }

    /**
     * 设置平台
     *
     * @param platformKey 平台
     */
    public void setPlatformKey(String platformKey) {
        this.platformKey = platformKey == null ? null : platformKey.trim();
    }

    /**
     * 获取平台码
     *
     * @return open_id - 平台码
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置平台码
     *
     * @param openId 平台码
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
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

    @Override
    public String toString() {
        return "UserConnectInfo{" +
                "userId=" + userId +
                ", platformKey='" + platformKey + '\'' +
                ", openId='" + openId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}