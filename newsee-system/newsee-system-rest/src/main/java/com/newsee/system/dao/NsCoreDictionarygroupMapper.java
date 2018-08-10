/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsCoreDictionarygroup;

public interface NsCoreDictionarygroupMapper {
  
    NsCoreDictionarygroup selectById(Long id);
    
    int insert(NsCoreDictionarygroup nsCoreDictionarygroup);
    
    int insertBatch(List<NsCoreDictionarygroup> nsCoreDictionarygroupList);
    
    int updateById(NsCoreDictionarygroup nsCoreDictionarygroup);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    List<NsCoreDictionarygroup> selectByOrganizationId(Long id);
}