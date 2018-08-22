package com.hapicker.common.util;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author yuyufeng
 */
public class MD5Util {
    public static String getMd5(String code) throws UnsupportedEncodingException {
        return DigestUtils.md5DigestAsHex(code.getBytes("utf-8"));
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(MD5Util.getMd5("12345"));
    }
}
