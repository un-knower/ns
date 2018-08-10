/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.dao;

import com.newsee.devplatform.entity.JeCorePermission;

public interface JeCorePermissionMapper {
  
    JeCorePermission selectById(String perid);
    
    int insert(JeCorePermission jeCorePermission);
    
    int updateById(JeCorePermission jeCorePermission);
    
    int deleteById(String perid);
}