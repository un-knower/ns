package com.newsee.oauth.service.impl;

import com.newsee.oauth.dao.AppClientMapper;
import com.newsee.oauth.entity.AppClient;
import com.newsee.oauth.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by niyang on 2017/8/11.
 */
@Service
public class AppServiceImpl implements IAppService {

    @Autowired
    private AppClientMapper appClientMapper;

    @Override
    public Boolean addAppClient(AppClient appClient) {
        int addResult = appClientMapper.insert(appClient);
        return addResult > 0;
    }
}
