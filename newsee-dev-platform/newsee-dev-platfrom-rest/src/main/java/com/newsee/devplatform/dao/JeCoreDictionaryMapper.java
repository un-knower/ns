/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.dao;

import java.util.List;

import com.newsee.devplatform.entity.JeCoreDictionary;

public interface JeCoreDictionaryMapper {
  
    JeCoreDictionary selectById(String jeCoreDictionaryId);
    
    List<JeCoreDictionary> selectByDictionaryDdcode(String dictionaryDdcode);
    
    int insert(JeCoreDictionary jeCoreDictionary);
    
    int insertBatch(List<JeCoreDictionary> jeCoreDictionaryList);
    
    int updateById(JeCoreDictionary jeCoreDictionary);
    
    int deleteById(String jeCoreDictionaryId);
    
}