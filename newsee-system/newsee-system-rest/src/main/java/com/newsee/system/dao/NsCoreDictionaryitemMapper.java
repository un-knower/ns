/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.system.entity.NsCoreDictionaryitem;
import com.newsee.system.vo.NsCoreDictionaryitemVo;

public interface NsCoreDictionaryitemMapper {
  
    NsCoreDictionaryitem selectById(Long id);
    
    List<NsCoreDictionaryitem> selectByDictionaryId( Map<String, Object> map);
    
    NsCoreDictionaryitem checkOnlyCode(NsCoreDictionaryitem nsCoreDictionaryitem);
    
    NsCoreDictionaryitem checkOnlyName(NsCoreDictionaryitem nsCoreDictionaryitem);
    
    List<NsCoreDictionaryitem> listByDictionaryId(Map<String, Object> map);

    List<NsCoreDictionaryitem> selectByDictionaryIdForSearch(Map<String, Object> map);
    
    int insert(NsCoreDictionaryitem nsCoreDictionaryitem);
    
    int insertBatch(List<NsCoreDictionaryitem> nsCoreDictionaryitemList);
    
    int updateById(NsCoreDictionaryitem nsCoreDictionaryitem);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    int updateByDictionaryitemId(NsCoreDictionaryitem nsCoreDictionaryitem);
    
    int deleteByDictionaryitemId(String id);
    
    int deleteByDictionaryId(String id);
    
    
    NsCoreDictionaryitem selectByDictionaryitemId(String id);
    NsCoreDictionaryitem findName(Map<String, Object> map);
    
    List<NsCoreDictionaryitemVo> listResultBySearch(Map<String, Object> map);
    List<NsCoreDictionaryitemVo> listResultBySearchSelect(Map<String, Object> map);
}