package com.newsee.system.service.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newsee.common.entity.AppUser;
import com.newsee.common.rest.RestResult;
import com.newsee.system.service.remote.hystrix.OauthRemoteServiceHystrix;

@FeignClient(value="oauth-server", fallback=OauthRemoteServiceHystrix.class)
public interface IOauthRemoteService {
    
    @RequestMapping(value = "/oauth/register", method = RequestMethod.POST)
    public RestResult<Boolean> register(@RequestBody AppUser user);
    
    @RequestMapping(value = "/oauth/edit", method = RequestMethod.POST)
    public RestResult<Boolean> edit(@RequestBody AppUser user);
    
    @RequestMapping(value = "/oauth/delete", method = RequestMethod.GET)
    public RestResult<Boolean> delete(@RequestParam(value="userId") Long userId);
    
    @RequestMapping(value = "/oauth/detail", method = RequestMethod.GET)
    public AppUser detail(@RequestParam(value="userId") Long userId);

}
