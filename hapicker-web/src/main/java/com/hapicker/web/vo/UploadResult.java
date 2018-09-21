package com.hapicker.web.vo;

/**
 *
 * @author yuyufeng
 * @date 2017/6/7
 * {
 * // errno 即错误代码，0 表示没有错误。
 * //       如果有错误，errno != 0，可通过下文中的监听函数 fail 拿到该错误码进行自定义处理
 * errno: 0,
 * // data 是一个数组，返回若干图片的线上地址
 * data: [
 * '图片1地址',
 * '图片2地址',
 * '……'
 * ]
 * }
 */
public class UploadResult {
    private int errno;
    private String[] data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
