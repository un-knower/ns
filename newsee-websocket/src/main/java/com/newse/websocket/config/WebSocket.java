package com.newse.websocket.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/websocket/{username}")
@Component
public class WebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    //private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    
    //用户昵称
    private String userSessionKey="";

    private static final Map<String,Object> connections = new HashMap<String,Object>(); 
    
    public WebSocket() {  
    }  
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("username")String userId, Session session) {
        
        userSessionKey = userId;
        this.session = session;
        //webSocketSet.add(this);     //加入set中
        connections.put(userSessionKey, this);
        System.out.println(userId+"有新连接加入！当前在线人数"+getOnlineCount());
//        sendMessage(username,"您好，" + username+"，恭喜您成功连接!");
//        addOnlineCount();           //在线数加1
//        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount()); 
        //try {
//        sendMessage("","当前在线人数为" + getOnlineCount());
        
       /* } catch (IOException e) {
            System.out.println("IO异常");
        }*/
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("username")String userId) {
        System.out.println("------------ remove"+userId);
        //webSocketSet.remove(this);  //从set中删除
        connections.remove(userId);
        
        
//        subOnlineCount();           //在线数减1
//        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(@PathParam("username")String userId, String message, Session session) {
        System.out.println("来自客户端"+userId+"的消息:" + message);
        sendAll(userId+"为空(群发):"+"fuwuqi"); 
        //群发消息
        /*for (WebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        //发送消息
        sendMessage(userId,message);
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


/*    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }*/


    /** 
     * 消息发送方法 
     * @param msg 
     */  
    public static void sendMessage(String userId,String msg) {  
        if(StringUtils.isNotEmpty(userId)){  
            sendUser(userId,msg);  
        } else{ 
           
            sendAll("username为空(群发):"+msg);  
        }  
    }   
    
    /** 
     * 向所有用户发送 
     * @param msg 
     */  
    public static void sendAll(String msg){  
        for (String key : connections.keySet()) {  
            WebSocket client = null ;  
            try {  
                client = (WebSocket) connections.get(key);  
                synchronized (client) {  
                    client.session.getBasicRemote().sendText(msg);  
                }  
            } catch (IOException e) {   
                connections.remove(client);  
                try {  
                    client.session.close();  
                } catch (IOException e1) {  
                    // Ignore  
                }  
            }  
        }  
    }  
      
    /** 
     * 向指定用户发送消息  
     * @param msg 
     */  
    public static void sendUser(String userId, String msg){  
        WebSocket c = (WebSocket)connections.get(userId);  
        try {  
        	System.out.println("------------正在发送消息："+msg);
            c.session.getBasicRemote().sendText(msg);  
        } catch (IOException e) {  
            connections.remove(c);  
            try {  
                c.session.close();  
            } catch (IOException e1) {  
                // Ignore  
            }  
        }   
    }  
    
   /* *//**
     * 群发自定义消息
     * *//*
    public static void sendInfo(String message) throws IOException {
        for (WebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }*/

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }
}