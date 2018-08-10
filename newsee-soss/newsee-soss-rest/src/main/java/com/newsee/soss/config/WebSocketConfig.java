package com.newsee.soss.config;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * //通过EnableWebSocketMessageBroker 开启使用STOMP协议
 * 来传输基于代理(message broker)的消息,此时浏览器支持使用@MessageMapping 就像支持@RequestMapping一样。
 * @author sw
 *
 */
@Configuration
@EnableWebSocketMessageBroker //开启使用STOMP协议来传输基于代理的消息
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	private static int onlineCount = 0;  	  
    private static CopyOnWriteArraySet<WebSocketConfig> webSocketSet = new CopyOnWriteArraySet<>();  
    private Session session;  
    
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	/**
	 * 注册STOMP协议的节点，并指定映射的URL
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		////注册一个名字为"soss-websocket" 的endpoint(端点),并指定 SockJS协议
		registry.addEndpoint("/soss-websocket").withSockJS();
	}

	/**
	 * 配置消息代理
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		//点对点式增加一个/queue 消息代理
		config.enableSimpleBroker("/queue","/topic");
		//配置了以“/soss”开头的websocket请求url
        config.setApplicationDestinationPrefixes("/soss");
	}
	
	/**
	 * 点对点消息
	 * @param message 消息参数
	 * @param toUser 接收消息的用户
	 * @param destinationUrl 浏览器订阅的地址
	 */
	public void handleMessge(String message, String toUser, String destinationUrl) {
		if (toUser == null || toUser.trim().length() == 0)
			throw new NullPointerException("user-to-user websocket toUser is null");
        //通过convertAndSendToUser 向用户发送信息
        // 第一个参数是接收消息的用户,第二个参数是浏览器订阅的地址,第三个参数是消息本身
        messagingTemplate.convertAndSendToUser(toUser, destinationUrl, message);
    }
	
	/**
	 * 广播消息
	 * @param message 消息参数
	 * @param destinationUrl destinationUrl
	 */
	public void handleMessge(String message, String destinationUrl) {
        // 第一个参数是浏览器订阅的地址,第 二个参数是消息本身
        messagingTemplate.convertAndSend(destinationUrl, message);
    }
	
    @OnOpen  
    public void onOpen (Session session){  
        this.session = session;  
        webSocketSet.add(this);  
        addOnlineCount();  
        System.out.println("有新链接加入!当前在线人数为" + getOnlineCount());  
    }  
  
    @OnClose  
    public void onClose (){  
        webSocketSet.remove(this);  
        subOnlineCount();  
        System.out.println("有一链接关闭!当前在线人数为" + getOnlineCount());  
    }  
  
    public static synchronized  int getOnlineCount (){  
        return onlineCount;  
    }  
  
    public static synchronized void addOnlineCount (){  
    	onlineCount++;  
    }  
  
    public static synchronized void subOnlineCount (){  
    	onlineCount--;  
    }  
	
}
