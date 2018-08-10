/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.dao;

import java.util.List;

import com.newsee.devplatform.entity.JeCoreFuncinfo;

public interface JeCoreFuncinfoMapper {
  
    JeCoreFuncinfo selectByFuncId(String funcId);
    
    List<JeCoreFuncinfo> selectByFuncCodes(List<String> funcCodes);
    
    int insert(JeCoreFuncinfo jeCoreFuncinfo);
    
    int updateById(JeCoreFuncinfo jeCoreFuncinfo);
    
    int deleteById(String jeCoreFuncinfoId);
    
}