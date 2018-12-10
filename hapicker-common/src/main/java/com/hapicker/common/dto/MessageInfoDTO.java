package com.hapicker.common.dto;

import java.util.Date;

/**
 * @author yuyufeng
 * @date 2018/12/10.
 */
public class MessageInfoDTO {
    private Long messageId;

    /**
     * 消息来自
     */
    private Integer messageFrom;
    private String messageFromName;


    /**
     * 消息发送给
     */
    private Integer messageTo;
    private String messageToName;

    /**
     * 标题
     */
    private String messageTitle;

    /**
     * x消息内容
     */
    private String messageContent;

    /**
     * 消息类型(0系统消息，1用户消息，2服务消息）
     */
    private Integer messageType;

    /**
     * 是否已经读取
     */
    private Boolean isRead;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(Integer messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getMessageFromName() {
        return messageFromName;
    }

    public void setMessageFromName(String messageFromName) {
        this.messageFromName = messageFromName;
    }

    public Integer getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(Integer messageTo) {
        this.messageTo = messageTo;
    }

    public String getMessageToName() {
        return messageToName;
    }

    public void setMessageToName(String messageToName) {
        this.messageToName = messageToName;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean read) {
        isRead = read;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
