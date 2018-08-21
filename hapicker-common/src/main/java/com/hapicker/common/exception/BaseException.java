package com.hapicker.common.exception;

/**
 * 基本异常类
 * @author yuyufeng
 * @date 2018/8/20.
 */
public class BaseException extends RuntimeException {
    private Integer code;
    private String msg;


    public BaseException() {
    }

    public BaseException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String message, Integer code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String message, Throwable cause, Integer code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(Throwable cause, Integer code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
