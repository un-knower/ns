/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.dao;

import java.util.List;
import java.util.Map;

import com.newsee.devplatform.entity.JeCoreResourcefield;

public interface JeCoreResourcefieldMapper {
  
    JeCoreResourcefield selectById(String jeCoreResourcefieldId);
    
    List<JeCoreResourcefield> selectByFuncIds(Map<String, Object> map);
    
    int insert(JeCoreResourcefield jeCoreResourcefield);
    
    int updateById(JeCoreResourcefield jeCoreResourcefield);
    
    int deleteById(String jeCoreResourcefieldId);
    
}