package com.newsee.oauth.dao;

import java.util.List;
import com.newsee.oauth.entity.App;

public interface AppMapper {
	
    int deleteById(Long id);

    int insert(App app);

    App selectById(Long id);
    
    List<App> selectAll();

    int updateById(App record);
}