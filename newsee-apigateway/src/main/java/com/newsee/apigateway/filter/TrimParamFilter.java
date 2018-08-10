package com.newsee.apigateway.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="trimParamFilter",urlPatterns="/*")
public class TrimParamFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器");
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest hr=(HttpServletRequest) request;  
        String url = hr.getServletPath().trim();  
        if (url.contains("oauth") || 
            url.contains("log") || 
            url.contains("system") || 
            url.contains("fastdfs") || 
            url.contains("jepf") ||
            url.contains("soss")){//不需要过滤的url，
            chain.doFilter(hr, response);  
        }else{  
           //自己写的param参数去除空格并写回的类
           ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper((HttpServletRequest)request);  
           chain.doFilter(requestWrapper, response);  
        }
    }
  
        

    @Override
    public void destroy() {
        System.out.println("销毁过滤器");
        
    }

}
