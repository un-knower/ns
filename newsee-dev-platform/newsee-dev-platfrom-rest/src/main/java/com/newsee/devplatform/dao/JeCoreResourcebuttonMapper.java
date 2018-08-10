/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.dao;

import java.util.List;
import java.util.Map;

import com.newsee.devplatform.entity.JeCoreResourcebutton;

public interface JeCoreResourcebuttonMapper {
  
    JeCoreResourcebutton selectById(String jeCoreResourcebuttonId);
    
    List<JeCoreResourcebutton> selectByFunctionId(String id);
    
    List<JeCoreResourcebutton> selectByFuncIds(Map<String, Object> map);
    
    int insert(JeCoreResourcebutton jeCoreResourcebutton);
    
    int updateById(JeCoreResourcebutton jeCoreResourcebutton);
    
    int deleteById(String jeCoreResourcebuttonId);
    
}