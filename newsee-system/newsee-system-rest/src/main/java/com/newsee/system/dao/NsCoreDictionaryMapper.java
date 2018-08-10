/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.vo.DictionaryDdcodeVo;

public interface NsCoreDictionaryMapper {
  
    NsCoreDictionary selectById(Long id);
    
    NsCoreDictionary selectByDictionaryDdcode(NsCoreDictionary dictionary);
    
    NsCoreDictionary checkOnlyByDictionaryDdcode(NsCoreDictionary dictionary);
    
    NsCoreDictionary checkOnlyByDictionaryDdname(NsCoreDictionary dictionary);
    
    List<NsCoreDictionary> listByDictionaryDdcode(DictionaryDdcodeVo dictionaryDdcodeVo);

    int insert(NsCoreDictionary nsCoreDictionary);
    
    int insertBatch(List<NsCoreDictionary> nsCoreDictionaryList);
    
    int updateById(NsCoreDictionary nsCoreDictionary);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    int updateByDictionaryId (NsCoreDictionary nsCoreDictionary);
    
    int deleteByDictionaryId(String id);
    
    List<NsCoreDictionary> selectByDictionaryGroupId(Map<String, Object> map);
    NsCoreDictionary findById(Map<String, Object> map);
    
    NsCoreDictionary selectByDictionaryId(String id);
}