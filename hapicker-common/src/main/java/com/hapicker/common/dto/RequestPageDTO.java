package com.hapicker.common.dto;

/**
 * @author yuyufeng
 * @date 2018/8/10.
 */
public class RequestPageDTO<T> {
    private Integer pageNo;
    private Integer pageSize;
    private T content;
    private String orderBy;

    public RequestPageDTO() {
        this.pageSize = 10;
    }


    public RequestPageDTO(Integer pageNo) {
        this.pageNo = pageNo;
        this.pageSize = 10;
    }

    public RequestPageDTO(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public RequestPageDTO(Integer pageNo, Integer pageSize, String orderBy) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }

    public RequestPageDTO(Integer pageNo,T content, String orderBy) {
        this.pageNo = pageNo;
        this.pageSize = 10;
        this.orderBy = orderBy;
        this.content = content;
    }

    public RequestPageDTO(Integer pageNo, Integer pageSize, T content) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.content = content;
    }


    public RequestPageDTO(Integer pageNo, Integer pageSize, T content, String orderBy) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.content = content;
        this.orderBy = orderBy;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "RequestPageDTO{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", content=" + content +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }
}
