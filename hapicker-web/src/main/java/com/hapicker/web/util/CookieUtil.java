package com.hapicker.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuyufeng
 */
public class CookieUtil {
//    private static String domain = "/";

    /**
     * 关闭浏览器cookie即失效
     */
    private static Integer AGE_CLOSE = -1;


    public static void setCookie(HttpServletResponse httpServletResponse, String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
//        cookie.setDomain(domain);
        cookie.setMaxAge(36000);
        httpServletResponse.addCookie(cookie);
    }

    public static String getCookieValue(HttpServletRequest httpServletRequest, String cookieName) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
//                System.out.println(cookies[i].getName() + " " + cookies[i].getDomain());
                if (cookieName.equals(cookies[i].getName())) {
                    return cookies[i].getValue();
                }
            }
        }
        return null;
    }

    public static void clearCookie(HttpServletResponse httpServletResponse, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setPath("/");
//        cookie.setDomain(domain);
        cookie.setMaxAge(AGE_CLOSE);
        httpServletResponse.addCookie(cookie);
    }

}
