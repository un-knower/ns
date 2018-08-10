package com.newsee.charge.service.remote;


import com.newsee.charge.service.remote.hystrix.WebSocketRemoteServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="websocket-server", fallback=WebSocketRemoteServiceHystrix.class)
public interface IWebSocketRemoteService {

    @RequestMapping(value = "/return-progress")
    public String returnProgress(@RequestParam(value = "userId") String userId, @RequestParam(value = "message") String message);
}
