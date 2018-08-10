/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.dao;

import java.util.List;

import com.newsee.devplatform.entity.JeCoreMenu;

public interface JeCoreMenuMapper {
  
    JeCoreMenu selectById(String jeCoreMenuId);
    
    JeCoreMenu selectByMenuName(String menuName);
    
    List<JeCoreMenu> selectByMenuPath(String path);
    
    int insert(JeCoreMenu jeCoreMenu);
    
    int updateById(String jeCoreMenuId);
    
    int deleteById(String jeCoreMenuId);
    
}