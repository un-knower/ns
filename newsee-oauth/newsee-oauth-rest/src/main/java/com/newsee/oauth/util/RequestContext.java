package com.newsee.oauth.util;

import javax.servlet.http.HttpServletRequest;

/**
 * OAuth 用户上下文
 *
 * @author Gavin Hu
 * @version 3.0.0
 */
public class RequestContext {

    private static final ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();

    public static void set(HttpServletRequest request) {
        requestThreadLocal.set(request);
    }

    private RequestContext() {}

    public static HttpServletRequest get() {
        return requestThreadLocal.get();
    }

    public static void clear() {
        requestThreadLocal.remove();
    }

}
