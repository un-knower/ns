package com.newsee.oauth.dao;

import com.newsee.oauth.entity.AppClient;

import java.util.List;

public interface AppClientMapper {
	
    int deleteById(Long id);

    int insert(AppClient record);

    AppClient selectById(Long id);

    int updateById(AppClient record);

    List<AppClient> selectAll();
}