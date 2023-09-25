package com.quxue.wedding_host.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    /**
     * 设置cookie
     *
     * @param resp
     * @param cookieName
     * @param cookieValue
     * @param maxAge      秒
     */
    public static void setCookie(HttpServletResponse resp, String cookieName, String cookieValue, Integer maxAge) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        maxAge = Math.max(maxAge, 0);//防止设置maxAge时输入错误,所以设置一个最小值
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");//设置cookie路径
        resp.addCookie(cookie);
        System.out.println("cookie = " + cookie.getName());
        System.out.println("cookie = " + cookie.getValue());
    }

    /**
     * 清除cookie
     *
     * @param resp
     * @param cookieName
     * @param domain
     */
    public static void clearCookie(HttpServletResponse resp, String cookieName, String domain) {
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setMaxAge(0);//把传入cookie的maxAge设置为0，即清空
        cookie.setPath("/");
        cookie.setDomain(domain);
        resp.addCookie(cookie);
    }

    /**
     * 刷新cookie
     *
     * @param req
     * @param resp
     * @param cookieName
     * @param domain
     * @param maxAge
     */
    public static void refreshCookie(HttpServletRequest req, HttpServletResponse resp, String cookieName, String domain, Integer maxAge) {
        Cookie cookie = getCookie(req, cookieName);
        if (cookie != null) {
            cookie.setMaxAge(maxAge);
            cookie.setDomain(domain);
            cookie.setPath("/");
            resp.addCookie(cookie);
        }
    }

    /**
     * 获取cookie的值
     *
     * @param req
     * @param cookieName
     * @return cookieValue
     */
    public static String getCookieValue(HttpServletRequest req, String cookieName) {
        Cookie cookie = getCookie(req, cookieName);
        return cookie != null ? cookie.getValue() : null;
    }


    /**
     * 获取cookie
     * @param req
     * @param cookieName
     * @return Cookie
     */
    private static Cookie getCookie(HttpServletRequest req, String cookieName) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    private CookieUtil() {
    }
}
