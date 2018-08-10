package com.newsee.oauth.service.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newsee.common.entity.NsSystemUser;
import com.newsee.oauth.service.remote.hystrix.SystemRemoteServiceHystrix;


@FeignClient(value="system-server", fallback=SystemRemoteServiceHystrix.class)
public interface ISystemRemoteService {
    
    @RequestMapping(value = "/user/detail-user-remote", method = RequestMethod.GET)
    public NsSystemUser detailUserRemote(@RequestParam(value="userId") Long userId);
}
