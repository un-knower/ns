package com.newsee.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.newsee.apigateway.filter.AccessFilter;

/**
 * 程序启动
 */
@ServletComponentScan//@ServletComponentScan扫描Servlet组件时 ,Servlet、过滤器和监听器可以是通过@WebServlet、@WebFilter和@WebListener自动注册
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@CrossOrigin
@EnableFeignClients
public class ApiGatewayServerApplication {
	public static void main(String[] args) {
        SpringApplication.run(ApiGatewayServerApplication.class, args);
        
    }
	
	//实例化过滤器
	@Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

}
