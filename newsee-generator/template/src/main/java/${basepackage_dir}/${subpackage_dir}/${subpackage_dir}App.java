<#assign subpackage = subpackage_dir?cap_first>
package com.newsee.system;

import com.newsee.common.interceptor.NewseeExceptionHandler;
import com.newsee.common.swagger.SwaggerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.newsee.${subpackage_dir}.dao")
@ComponentScan("com.newsee")
public class ${subpackage}App{
	
    public static void main( String[] args){
        SpringApplication.run(${subpackage}App.class, args);
    }
}
