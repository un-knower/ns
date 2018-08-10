package com.newsee.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.util.WebUtils;

/**
 * Created by xiaoss on 2017/6/23.
 * 处理Cookie的值
 */
public class CookieUtils {

	/**
	 * 创建cookie
	 * @param response： response
	 * @param name： cookie name
	 * @param value：cookie vakue
	 * @param secure： true：works on https only
	 * @param maxAge：maxAge
	 * @param domain：domain
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
