/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.dao;

import java.util.List;
import java.util.Map;

import com.newsee.devplatform.entity.JeCoreResourcecolumn;

public interface JeCoreResourcecolumnMapper {
  
    JeCoreResourcecolumn selectById(String jeCoreResourcecolumnId);
    
    List<JeCoreResourcecolumn> selectByFuncIds(Map<String, Object> map);
    
    int insert(JeCoreResourcecolumn jeCoreResourcecolumn);
    
    int updateById(JeCoreResourcecolumn jeCoreResourcecolumn);
    
    int deleteById(String jeCoreResourcecolumnId);
    
}