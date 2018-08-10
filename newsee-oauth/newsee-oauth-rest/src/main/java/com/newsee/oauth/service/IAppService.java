package com.newsee.oauth.service;

import com.newsee.oauth.entity.AppClient;

/**
 * Created by niyang on 2017/8/11.
 */
public interface IAppService {

    public final static String APP_CLIENT_SECRET_LIST = "APP_CLIENT_SECRET_LIST";
    
    public final static String APP_LIST = "APP_LIST";

    public Boolean addAppClient(AppClient appClient);
}
