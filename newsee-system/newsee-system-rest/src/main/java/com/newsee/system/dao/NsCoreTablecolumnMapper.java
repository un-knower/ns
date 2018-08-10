/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsCoreTablecolumn;

public interface NsCoreTablecolumnMapper {
  
    NsCoreTablecolumn selectById(Long id);
    
    int insert(NsCoreTablecolumn nsCoreTablecolumn);
    
    int insertBatch(List<NsCoreTablecolumn> nsCoreTablecolumnList);
    
    int updateById(NsCoreTablecolumn nsCoreTablecolumn);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
}