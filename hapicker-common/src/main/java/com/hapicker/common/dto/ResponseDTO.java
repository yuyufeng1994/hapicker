package com.hapicker.common.dto;

import java.io.Serializable;

/**
 * @author yuyufeng
 * @date 2018/8/9.
 */
public class ResponseDTO<T> implements Serializable {
    private Boolean success;
    private String msg;
    private T content;

    public ResponseDTO() {
    }

    public ResponseDTO(Boolean success) {
        this.success = success;
    }

    public ResponseDTO(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ResponseDTO(Boolean success, String msg, T content) {
        this.success = success;
        this.msg = msg;
        this.content = content;
    }

    public static <T> ResponseDTO success() {
        return new ResponseDTO(true);
    }

    public static <T> ResponseDTO success(String msg) {
        return new ResponseDTO(true, msg);
    }

    public static <T> ResponseDTO success(String msg, T content) {
        return new ResponseDTO(true, msg, content);
    }

    public static <T> ResponseDTO success(T content) {
        return new ResponseDTO(true, "", content);
    }

    public static <T> ResponseDTO fail() {
        return new ResponseDTO(false);
    }

    public static <T> ResponseDTO fail(String msg) {
        return new ResponseDTO(false, msg);
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
        return "ResponseDTO{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", content=" + content +
                '}';
    }
}
