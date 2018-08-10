/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsCoreResourcetable;

public interface NsCoreResourcetableMapper {
  
    NsCoreResourcetable selectById(Long id);
    
    int insert(NsCoreResourcetable nsCoreResourcetable);
    
    int insertBatch(List<NsCoreResourcetable> nsCoreResourcetableList);
    
    int updateById(NsCoreResourcetable nsCoreResourcetable);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
}