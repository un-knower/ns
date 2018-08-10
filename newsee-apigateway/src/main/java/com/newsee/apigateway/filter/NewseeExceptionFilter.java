package com.newsee.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.newsee.apigateway.util.CommonExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by niyang on 2017/8/28.
 */
@Component
public class NewseeExceptionFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String errorStatusCode = (String) ctx.get("error.status_code");
        if (StringUtils.isEmpty(errorStatusCode)) {
            return ctx;
        }
        ctx.setResponseStatusCode(200);
        String serverId = (String) ctx.get("serverId");
        String requestURI = (String) ctx.get("requestURI");
        String errorException = (String) ctx.get("error.exception");
        StringBuffer sb = new StringBuffer();
        if (!StringUtils.isEmpty(serverId)) {
            sb.append("serverId:" + serverId);
        }
        sb.append("requestURI:" + requestURI);
        sb.append("errorException:" + errorException);
        String errorMessage = sb.toString();
        ctx.setResponseBody(CommonExceptionHandler.handlerExcetpion(null, errorMessage));
        return ctx;
    }
}
