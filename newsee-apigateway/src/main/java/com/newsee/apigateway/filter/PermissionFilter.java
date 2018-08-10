package com.newsee.apigateway.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.newsee.apigateway.service.remote.ISystemRemoteService;
import com.newsee.common.constant.RequestConstants;
import com.newsee.common.rest.RestResult;

@Component
public class PermissionFilter extends ZuulFilter {
    
    @Autowired
    private ISystemRemoteService systemRemoteService;

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String reqUrl = request.getRequestURL().toString();
        if (reqUrl.contains("list-user") || 
            reqUrl.contains("list-role") ||
            reqUrl.contains("house-list") ||
            reqUrl.contains("list-customer") ||
            reqUrl.contains("list-stage-operate") ||
            reqUrl.contains("list-car")) {
            //获取数据权限
            List<Long> seeOrganizationIdList = null;
            Long seeUserId = null;
            String seeUserIdStr =null;
            
            String funcId = request.getHeader(RequestConstants.HEADER_FUNC_ID);
            String userId = ctx.getZuulRequestHeaders().get("userid");
            RestResult<Map<String, Object>> result = systemRemoteService.listDataPerm(Long.parseLong(userId), funcId);
            Map<String, Object> resultMap = result.getResultData();
            if (!CollectionUtils.isEmpty(resultMap)) {
                if (!Objects.isNull(resultMap.get("seeUserId"))) {
                    if (reqUrl.contains("list-role")) {
                        seeUserIdStr = (String)resultMap.get("seeUserId");
                    }else{
                        seeUserId=(Long)resultMap.get("seeUserId");
                    }
                }
                if (!Objects.isNull(resultMap.get("seeOrganizationIdList"))) {
                    seeOrganizationIdList=(List<Long>)resultMap.get("seeOrganizationIdList");
                }
                
                //设置进参数体中
                try {
                    InputStream in = (InputStream) ctx.get("requestEntity");
                    if (in == null) {
                        in = ctx.getRequest().getInputStream();
                    }
                    String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
                    Map<String, Object> searchVo = ( Map<String, Object>)JSON.parse(body);
                    searchVo.put("seeUserId", seeUserId);
                    searchVo.put("seeUserIdStr", seeUserIdStr);
                    searchVo.put("seeOrganizationIdList", seeOrganizationIdList);
                    body = JSON.toJSONString(searchVo);
                    byte[] bytes = body.getBytes("UTF-8");
                    ctx.setRequest(new HttpServletRequestWrapper( RequestContext.getCurrentContext().getRequest()){
                        @Override
                        public ServletInputStream getInputStream() throws IOException {
                            return new ServletInputStreamWrapper(bytes);
                        }
             
                        @Override
                        public int getContentLength() {
                            return bytes.length;
                        }
             
                        @Override
                        public long getContentLengthLong() {
                            return bytes.length;
                        }
                    });
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

}
