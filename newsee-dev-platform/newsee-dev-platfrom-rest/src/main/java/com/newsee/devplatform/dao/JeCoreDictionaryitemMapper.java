/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.dao;

import java.util.List;

import com.newsee.devplatform.entity.JeCoreDictionaryitem;

public interface JeCoreDictionaryitemMapper {
  
    JeCoreDictionaryitem selectById(String jeCoreDictionaryitemId);
    
    int insert(JeCoreDictionaryitem jeCoreDictionaryitem);
    
    int insertBatch(List<JeCoreDictionaryitem> jeCoreDictionaryitems);
    
    int updateById(JeCoreDictionaryitem jeCoreDictionaryitem);
    
    int deleteById(String dictionaryitemDictionaryId);
}