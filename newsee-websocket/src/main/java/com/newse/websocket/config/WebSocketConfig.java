package com.newse.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
/**
 * @ClassName: WebSocketConfig 
 * @Description: 首先要注入ServerEndpointExporter，这个bean会自动注册使用了@ServerEndpoint注解声明
 * 的Websocket endpoint。要注意，如果使用独立的servlet容器，而不是直接使用springboot的内置容器，就不
 * 需要注入ServerEndpointExporter，因为它将由容器自己提供和管理。 
 * @author 胡乾亮
 * @date 2017年9月1日 下午5:17:10 
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}	
