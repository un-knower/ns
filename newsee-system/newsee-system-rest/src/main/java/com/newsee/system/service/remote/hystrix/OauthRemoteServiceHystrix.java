package com.newsee.system.service.remote.hystrix;

import org.springframework.stereotype.Component;

import com.newsee.common.entity.AppUser;
import com.newsee.common.rest.RestResult;
import com.newsee.system.service.remote.IOauthRemoteService;

@Component
public class OauthRemoteServiceHystrix implements IOauthRemoteService {

    @Override
    public RestResult<Boolean> register(AppUser user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestResult<Boolean> edit(AppUser user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestResult<Boolean> delete(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AppUser detail(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

}
