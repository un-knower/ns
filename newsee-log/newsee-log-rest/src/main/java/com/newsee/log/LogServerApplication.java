package com.newsee.log;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsee.log.config.CustomDateFormat;

/**
 * 程序启动
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@RefreshScope
@ComponentScan("com.newsee")
public class LogServerApplication extends WebMvcConfigurerAdapter{
	
    public static void main( String[] args ){
    	SpringApplication.run(LogServerApplication.class, args);
    }
    
    @Bean  
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();  
        //设置日期格式  
        ObjectMapper objectMapper = new ObjectMapper(); 
        objectMapper.setDateFormat(CustomDateFormat.instance);  
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);  
        //设置中文编码格式  
        List<MediaType> list = new ArrayList<MediaType>();  
        list.add(MediaType.APPLICATION_JSON_UTF8);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        return mappingJackson2HttpMessageConverter;
    }  

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
    	converters.add(getMappingJackson2HttpMessageConverter());
    }
    
    /**
     * 创建线程池bean
     * @return
     */
    @Bean(name = "executor")
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
    
}
