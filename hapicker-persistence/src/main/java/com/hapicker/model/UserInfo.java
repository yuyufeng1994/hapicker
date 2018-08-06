package com.hapicker.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author yuyufeng
 */
@Table(name = "user_info")
public class UserInfo {
    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名(用户登录）
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户昵称(用户称呼）
     */
    @Column(name = "user_nick")
    private String userNick;

    /**
     * 用户邮箱
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 用户手机号码
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * 用户头像
     */
    @Column(name = "user_avatar")
    private String userAvatar;

    /**
     * 用户简介
     */
    @Column(name = "user_profile")
    private String userProfile;

    /**
     * 用户状态
     */
    @Column(name = "user_status")
    private Integer userStatus;

    /**
     * 用户状态
     */
    @Column(name = "user_pwd")
    private String userPwd;

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


    public UserInfo() {
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
     * 获取用户名(用户登录）
     *
     * @return user_name - 用户名(用户登录）
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名(用户登录）
     *
     * @param userName 用户名(用户登录）
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户昵称(用户称呼）
     *
     * @return user_nick - 用户昵称(用户称呼）
     */
    public String getUserNick() {
        return userNick;
    }

    /**
     * 设置用户昵称(用户称呼）
     *
     * @param userNick 用户昵称(用户称呼）
     */
    public void setUserNick(String userNick) {
        this.userNick = userNick == null ? null : userNick.trim();
    }

    /**
     * 获取用户邮箱
     *
     * @return user_email - 用户邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置用户邮箱
     *
     * @param userEmail 用户邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * 获取用户手机号码
     *
     * @return user_phone - 用户手机号码
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置用户手机号码
     *
     * @param userPhone 用户手机号码
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * 获取用户头像
     *
     * @return user_avatar - 用户头像
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * 设置用户头像
     *
     * @param userAvatar 用户头像
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    /**
     * 获取用户简介
     *
     * @return user_profile - 用户简介
     */
    public String getUserProfile() {
        return userProfile;
    }

    /**
     * 设置用户简介
     *
     * @param userProfile 用户简介
     */
    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile == null ? null : userProfile.trim();
    }

    /**
     * 获取用户状态
     *
     * @return user_status - 用户状态
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 设置用户状态
     *
     * @param userStatus 用户状态
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}