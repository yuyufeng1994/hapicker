package com.hapicker.common.dto;

import com.hapicker.common.constant.BusWarningStatusEnum;

import java.util.Date;

public class BusWarningInfoDTO {
    /**
     * 车次监听ID
     */
    private Long busWarningId;

    /**
     * 车次ID
     */
    private String busNo;

    /**
     * 出发
     */
    private String departure;

    /**
     * 到达
     */
    private String destination;

    /**
     * 日期
     */
    private String busDate;

    private String busTime;

    /**
     * 剩余票数
     */
    private Integer ticketLeft;

    /**
     * 监听次数
     */
    private Integer warningTimes;

    private Integer userId;

    /**
     * 状态(0:监听中，1:监听成功，2:监听失败，3:放弃监听)
     */
    private Integer warningStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    public BusWarningInfoDTO() {
        super();
    }

    /**
     * 获取车次监听ID
     *
     * @return bus_warning_id - 车次监听ID
     */
    public Long getBusWarningId() {
        return busWarningId;
    }

    /**
     * 设置车次监听ID
     *
     * @param busWarningId 车次监听ID
     */
    public void setBusWarningId(Long busWarningId) {
        this.busWarningId = busWarningId;
    }

    /**
     * 获取车次ID
     *
     * @return bus_no - 车次ID
     */
    public String getBusNo() {
        return busNo;
    }

    /**
     * 设置车次ID
     *
     * @param busNo 车次ID
     */
    public void setBusNo(String busNo) {
        this.busNo = busNo == null ? null : busNo.trim();
    }

    /**
     * 获取出发
     *
     * @return departure - 出发
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * 设置出发
     *
     * @param departure 出发
     */
    public void setDeparture(String departure) {
        this.departure = departure == null ? null : departure.trim();
    }

    /**
     * 获取到达
     *
     * @return destination - 到达
     */
    public String getDestination() {
        return destination;
    }

    /**
     * 设置到达
     *
     * @param destination 到达
     */
    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    /**
     * 获取日期
     *
     * @return bus_date - 日期
     */
    public String getBusDate() {
        return busDate;
    }

    /**
     * 设置日期
     *
     * @param busDate 日期
     */
    public void setBusDate(String busDate) {
        this.busDate = busDate == null ? null : busDate.trim();
    }

    /**
     * 获取剩余票数
     *
     * @return ticket_left - 剩余票数
     */
    public Integer getTicketLeft() {
        return ticketLeft;
    }

    /**
     * 设置剩余票数
     *
     * @param ticketLeft 剩余票数
     */
    public void setTicketLeft(Integer ticketLeft) {
        this.ticketLeft = ticketLeft;
    }

    /**
     * 获取监听次数
     *
     * @return warning_times - 监听次数
     */
    public Integer getWarningTimes() {
        return warningTimes;
    }

    /**
     * 设置监听次数
     *
     * @param warningTimes 监听次数
     */
    public void setWarningTimes(Integer warningTimes) {
        this.warningTimes = warningTimes;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取状态(0:监听中，1:监听成功，2:监听失败，3:放弃监听)
     *
     * @return warning_status - 状态(0:监听中，1:监听成功，2:监听失败，3:放弃监听)
     */
    public Integer getWarningStatus() {
        return warningStatus;
    }

    public String getWarningStatusString() {
        return BusWarningStatusEnum.getValue(getWarningStatus());
    }

    /**
     * 设置状态(0:监听中，1:监听成功，2:监听失败，3:放弃监听)
     *
     * @param warningStatus 状态(0:监听中，1:监听成功，2:监听失败，3:放弃监听)
     */
    public void setWarningStatus(Integer warningStatus) {
        this.warningStatus = warningStatus;
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

    public String getBusTime() {
        return busTime;
    }

    public void setBusTime(String busTime) {
        this.busTime = busTime;
    }
}