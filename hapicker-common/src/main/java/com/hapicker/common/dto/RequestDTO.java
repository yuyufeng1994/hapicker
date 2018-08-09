package com.hapicker.common.dto;

import java.io.Serializable;

/**
 * @author yuyufeng
 * @date 2018/8/9.
 */
public class RequestDTO<T> implements Serializable{
    private Boolean success;
    private String msg;
    private T content;

    public RequestDTO() {
    }

    public RequestDTO(Boolean success) {
        this.success = success;
    }

    public RequestDTO(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public RequestDTO(Boolean success, String msg, T content) {
        this.success = success;
        this.msg = msg;
        this.content = content;
    }

    public static <T> RequestDTO success() {
        return new RequestDTO(true);
    }

    public static <T> RequestDTO success(String msg) {
        return new RequestDTO(true, msg);
    }

    public static <T> RequestDTO success(T content) {
        return new RequestDTO(true, "", content);
    }

    public static <T> RequestDTO success(String msg,T content) {
        return new RequestDTO(true, msg,content);
    }

    public static <T> RequestDTO fail() {
        return new RequestDTO(false);
    }

    public static <T> RequestDTO fail(String msg) {
        return new RequestDTO(false, msg);
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", content=" + content +
                '}';
    }
}
