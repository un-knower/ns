package com.newsee.oauth.dao;

import com.newsee.oauth.entity.AppUserRela;

public interface AppUserRelaMapper {
	
    int deleteById(Long id);

    int insert(AppUserRela record);

    AppUserRela selectById(Long id);

    int updateById(AppUserRela record);
}