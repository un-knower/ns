package com.newsee.oauth.dao;

import com.newsee.oauth.entity.AppUserToken;

public interface AppUserTokenMapper {
	
    int deleteById(Long id);

    int insert(AppUserToken record);

    AppUserToken selectById(Long id);

    int updateById(AppUserToken record);

}