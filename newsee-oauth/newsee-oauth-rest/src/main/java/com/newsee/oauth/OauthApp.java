package com.newsee.oauth;

import com.alibaba.fastjson.JSONObject;
import com.newsee.oauth.dao.AppClientMapper;
import com.newsee.oauth.dao.AppMapper;
import com.newsee.oauth.entity.App;
import com.newsee.oauth.entity.AppClient;
import com.newsee.oauth.service.IAppService;
import com.newsee.redis.util.RedisUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;
import java.util.List;


@EnableTransactionManagement
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.newsee.oauth.dao")
@ComponentScan("com.newsee")
@EnableHystrix
@RefreshScope
public class OauthApp implements ApplicationListener<ContextRefreshedEvent>
{
    public static void main(String[] args){
        SpringApplication.run(OauthApp.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        RedisUtil redisUtil=event.getApplicationContext().getBean(RedisUtil.class);
        String appClientValue= redisUtil.getStringValue(IAppService.APP_CLIENT_SECRET_LIST);
        if(StringUtils.isEmpty(appClientValue)){
            AppClientMapper appClientMapper=event.getApplicationContext().getBean(AppClientMapper.class);
            List<AppClient> appClientList= appClientMapper.selectAll();
            redisUtil.setStringValue(IAppService.APP_CLIENT_SECRET_LIST, JSONObject.toJSONString(appClientList));
        }
        String appListValue = redisUtil.getStringValue(IAppService.APP_LIST);
        if(StringUtils.isEmpty(appListValue)){
        	AppMapper appMapper=event.getApplicationContext().getBean(AppMapper.class);
        	List<App> appList = appMapper.selectAll();
        	redisUtil.setStringValue(IAppService.APP_LIST, JSONObject.toJSONString(appList));
        }
    }
}
