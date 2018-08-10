package com.newsee.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.base.Predicate;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger接口文档配置类
 * @author xiaosisi add on 2017/09/05
 *
 */
@Configuration
@EnableSwagger2
@ResponseBody
public class SwaggerConfig {

	@Bean
	public Docket restApi() {
		Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                if(declaringClass.isAnnotationPresent(RestController.class)) // 被注解的类
                    return true;
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(restApiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(predicate)
                .build();
	}
	
	private ApiInfo restApiInfo() {
	        return new ApiInfoBuilder()
	            .title("新视窗物业收费SAAS系统API文档")//大标题
	            .description("为建立一个规范实时的API文档管理，特建立此API文档。\n"
	            		+ "此文档详细记录了每个接口类大致功能，接口请求路径，接口请求参数和返回参数。\n"
	            		+ "代码更新后可实时展示最新的接口文档方法及参数。\n"
	            		+ "本文档同时提供接口测试的功能。")//详细描述
	            .version("1.0")//版本
	            .termsOfServiceUrl("http://www.new-see.com")
	            .contact(new Contact("新视窗 2017/09/05", "http://www.new-see.com", "support@new-see.com"))//作者
	            .license("The New-see License, Version 	1.0")
	            .licenseUrl("http://www.new-see.com")
	            .build();
	    }

}
