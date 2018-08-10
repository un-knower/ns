/*package com.newsee.apigateway.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.netflix.zuul.context.ContextLifecycleFilter;
import com.netflix.zuul.filters.FilterRegistry;
import com.netflix.zuul.http.ZuulServlet;
import com.netflix.zuul.monitoring.MonitoringHelper;
import com.newsee.apigateway.filter.AccessFilter;

@Component
public class MyCommandLineRunner implements CommandLineRunner{

	@Override
	public void run(String... arg0) throws Exception {
		MonitoringHelper.initMocks();//启动监控
		initJavaFilters();
		
	}
	
	private void initJavaFilters() {
        final FilterRegistry filterRegistry = FilterRegistry.instance();

        filterRegistry.put("accessFilter",new AccessFilter());

    }
	
	*//**
	 *用以进行filter执行调度以及监控等等操作 
	 *访问 http://localhost:port/test 进入该servlet
	 *//*
	@Bean
    public ServletRegistrationBean zuulServlet() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new ZuulServlet());
        servlet.addUrlMappings("/test");
        return servlet;
    }
	
	*//**
	 * ContextLifecycleFilter的核心功能是为了清除RequestContext； 
	 *//*
	@Bean
    public FilterRegistrationBean contextLifecycleFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean(new ContextLifecycleFilter());
        filter.addUrlPatterns("/*");
        return filter;
    }


}

*/