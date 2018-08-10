package com.newse.websocket.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newse.websocket.config.WebSocket;

@RestController
public class WebSocketSendToClientController {
	/**
	 * 
	* @Title: sendMessage 
	* @Description: username为空：给所有在线的用户发消息；username不为空 ：给指定的用户发消息
	* @author 胡乾亮
	 */
    @RequestMapping(value = "/sendMessage")
    public String sendMessage(@RequestParam(value="username")String username, @RequestParam(value="message") String message){
			WebSocket.sendMessage(username,message);
        return "ok";
    }
    
    @RequestMapping(value = "/return-progress",method=RequestMethod.POST)
    public String returnProgress(@RequestParam(value="userId")String userId, @RequestParam(value="message") String message){
        System.out.println("------------- zhunbei fasong "+message);    
    	WebSocket.sendMessage(userId,message);
        return "ok";
    }
}
