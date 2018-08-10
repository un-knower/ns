/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.dao;

import com.newsee.devplatform.entity.JeCoreResourcetable;

public interface JeCoreResourcetableMapper {
  
    JeCoreResourcetable selectById(String jeCoreResourcetableId);
    
    int insert(JeCoreResourcetable jeCoreResourcetable);
    
    int updateById(JeCoreResourcetable jeCoreResourcetable);
    
    int deleteById(String jeCoreResourcetableId);
    
}