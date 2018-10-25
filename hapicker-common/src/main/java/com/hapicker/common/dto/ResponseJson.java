package com.hapicker.common.dto;

import java.io.Serializable;

/**
 * @author yuyufeng
 * @date 2018/8/9.
 */
public class ResponseJson<T> implements Serializable {
    private Boolean success;
    private String msg;
    private T content;

    public ResponseJson() {
    }

    public ResponseJson(Boolean success) {
        this.success = success;
    }

    public ResponseJson(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ResponseJson(Boolean success, String msg, T content) {
        this.success = success;
        this.msg = msg;
        this.content = content;
    }

    public static <T> ResponseJson success() {
        return new ResponseJson(true);
    }

    public static <T> ResponseJson success(String msg) {
        return new ResponseJson(true, msg);
    }

    public static <T> ResponseJson success(String msg, T content) {
        return new ResponseJson(true, msg, content);
    }

    public static <T> ResponseJson success(T content) {
        return new ResponseJson(true, "", content);
    }

    public static <T> ResponseJson fail() {
        return new ResponseJson(false);
    }

    public static <T> ResponseJson fail(String msg) {
        return new ResponseJson(false, msg);
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
        return "ResponseJson{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", content=" + content +
                '}';
    }
}
