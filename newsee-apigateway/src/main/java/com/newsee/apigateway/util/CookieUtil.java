package com.newsee.apigateway.util;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by niyang on 2017/8/11.
 */
public class CookieUtil {
    /**
     * 创建cookie
     * @param response： response
     * @param name： cookie name
     * @param value：cookie value
     * @param secure： true：works on https only
     * @param maxAge：maxAge
     */
    public static void create(HttpServletResponse response,
                              String name,
                              String value,
                              Boolean secure,
                              Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        //secure=true => work on HTTPS only.
        cookie.setSecure(secure);
        //对javascript不可见
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
//	        cookie.setDomain(domain);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 清空cookie
     * @param httpServletResponse
     * @param name
     */
    public static void clear(HttpServletResponse httpServletResponse, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }

    /**
     * 获取cookie的值
     * @param httpServletRequest
     * @param name
     * @return
     */
    public static String getValue(HttpServletRequest httpServletRequest, String name) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
        return cookie != null ? cookie.getValue() : null;
    }
}
