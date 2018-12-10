package com.hapicker.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "message_info")
public class MessageInfo {
    @Id
    @Column(name = "message_id")
    private Long messageId;

    /**
     * 消息来自
     */
    @Column(name = "message_from")
    private Integer messageFrom;

    /**
     * 消息发送给
     */
    @Column(name = "message_to")
    private Integer messageTo;

    /**
     * 标题
     */
    @Column(name = "message_title")
    private String messageTitle;

    /**
     * x消息内容
     */
    @Column(name = "message_content")
    private String messageContent;

    /**
     * 消息类型(0系统消息，1用户消息，2服务消息）
     */
    @Column(name = "message_type")
    private Integer messageType;

    /**
     * 是否已经读取
     */
    @Column(name = "is_read")
    private Boolean isRead;

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


    public MessageInfo() {
        super();
    }

    /**
     * @return message_id
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * @param messageId
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取消息来自
     *
     * @return message_from - 消息来自
     */
    public Integer getMessageFrom() {
        return messageFrom;
    }

    /**
     * 设置消息来自
     *
     * @param messageFrom 消息来自
     */
    public void setMessageFrom(Integer messageFrom) {
        this.messageFrom = messageFrom;
    }

    /**
     * 获取消息发送给
     *
     * @return message_to - 消息发送给
     */
    public Integer getMessageTo() {
        return messageTo;
    }

    /**
     * 设置消息发送给
     *
     * @param messageTo 消息发送给
     */
    public void setMessageTo(Integer messageTo) {
        this.messageTo = messageTo;
    }

    /**
     * 获取标题
     *
     * @return message_title - 标题
     */
    public String getMessageTitle() {
        return messageTitle;
    }

    /**
     * 设置标题
     *
     * @param messageTitle 标题
     */
    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle == null ? null : messageTitle.trim();
    }

    /**
     * 获取x消息内容
     *
     * @return message_content - x消息内容
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * 设置x消息内容
     *
     * @param messageContent x消息内容
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    /**
     * 获取消息类型(0系统消息，1用户消息，2服务消息）
     *
     * @return message_type - 消息类型(0系统消息，1用户消息，2服务消息）
     */
    public Integer getMessageType() {
        return messageType;
    }

    /**
     * 设置消息类型(0系统消息，1用户消息，2服务消息）
     *
     * @param messageType 消息类型(0系统消息，1用户消息，2服务消息）
     */
    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    /**
     * 获取是否已经读取
     *
     * @return is_read - 是否已经读取
     */
    public Boolean getIsRead() {
        return isRead;
    }

    /**
     * 设置是否已经读取
     *
     * @param isRead 是否已经读取
     */
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
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