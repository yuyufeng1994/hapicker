package com.hapicker.common.util;

import java.util.regex.Pattern;

/**
 * @author yuyufeng
 * @date 2018/8/22.
 */
public class ValidationUtil {
    public static boolean checkEmail(String content) {
        String pattern = "^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
        boolean isMatch = Pattern.matches(pattern, content);
        return isMatch;
    }

    public static boolean checkAccount(String content) {
        String pattern = "^\\w{5,17}$";
        boolean isMatch = Pattern.matches(pattern, content);
        return isMatch || checkEmail(content);
    }

    public static boolean checkPwd(String content) {
        String pattern = "^\\w{5,17}$";
        boolean isMatch = Pattern.matches(pattern, content);
        return isMatch;
    }


    public static void main(String[] args) {
        System.out.println(checkEmail("4165@qq.com"));
        System.out.println(checkAccount("416523"));
    }
}
