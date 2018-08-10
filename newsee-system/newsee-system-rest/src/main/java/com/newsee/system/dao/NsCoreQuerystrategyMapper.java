/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsCoreQuerystrategy;

public interface NsCoreQuerystrategyMapper {
  
    NsCoreQuerystrategy selectById(Long id);
    
    int insert(NsCoreQuerystrategy nsCoreQuerystrategy);
    
    int insertBatch(List<NsCoreQuerystrategy> nsCoreQuerystrategyList);
    
    int updateById(NsCoreQuerystrategy nsCoreQuerystrategy);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
}