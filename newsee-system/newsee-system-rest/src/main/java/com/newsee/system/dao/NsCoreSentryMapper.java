/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsCoreSentry;

public interface NsCoreSentryMapper {
  
    NsCoreSentry selectById(Long id);
    
    int insert(NsCoreSentry nsCoreSentry);
    
    int insertBatch(List<NsCoreSentry> nsCoreSentryList);
    
    int updateById(NsCoreSentry nsCoreSentry);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
}