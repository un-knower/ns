package com.newsee.charge.service.remote.hystrix;

import com.newsee.charge.service.remote.IWebSocketRemoteService;
import org.springframework.stereotype.Component;

@Component
public class WebSocketRemoteServiceHystrix implements IWebSocketRemoteService {

    @Override
    public String returnProgress(String userId, String message) {
        // TODO Auto-generated method stub
        return null;
    }

}
