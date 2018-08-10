package com.newsee.owner.service.remote.hystrix;

import org.springframework.stereotype.Component;

import com.newsee.owner.service.remote.IWebSocketRemoteService;

@Component
public class WebSocketRemoteServiceHystrix implements IWebSocketRemoteService{

    @Override
    public String returnProgress(String userId, String message) {
        // TODO Auto-generated method stub
        return null;
    }

}
