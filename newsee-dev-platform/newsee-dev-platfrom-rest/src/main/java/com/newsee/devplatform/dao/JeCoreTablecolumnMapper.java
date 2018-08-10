/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.dao;

import com.newsee.devplatform.entity.JeCoreTablecolumn;

public interface JeCoreTablecolumnMapper {
  
    JeCoreTablecolumn selectById(String jeCoreTablecolumnId);
    
    int insert(JeCoreTablecolumn jeCoreTablecolumn);
    
    int updateById(JeCoreTablecolumn jeCoreTablecolumn);
    
    int deleteById(String jeCoreTablecolumnId);
    
}