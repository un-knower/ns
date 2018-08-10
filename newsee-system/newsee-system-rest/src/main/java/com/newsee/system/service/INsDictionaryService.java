package com.newsee.system.service;

import java.util.List;
import java.util.Map;

import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.entity.NsCoreDictionaryitem;
import com.newsee.system.vo.DictionaryDdcodeVo;
import com.newsee.system.vo.NsCoreDictionaryVo;

public interface INsDictionaryService {

    Boolean add(NsCoreDictionaryVo dictionaryVo);
    
    Boolean edit(NsCoreDictionaryVo dictionaryVo);
    
    Boolean delete(String dictionaryId);
    
    List<NsCoreDictionaryVo> list(Long dictionarygroupId, Long organizationId);
    
    NsCoreDictionaryVo detail(String dictionaryId);
    
    public NsCoreDictionaryVo getNsCoreDictionaryVo(NsCoreDictionary dictionary);
    
    public Map<String, List<NsCoreDictionaryVo>> getNsCoreDictionaryVo(DictionaryDdcodeVo dictionaryDdcodeVo);
    
    public NsCoreDictionaryVo getNsCoreDictionaryVoForSearch(NsCoreDictionary dictionary);
    
    NsCoreDictionaryitem findById(Map<String, Object> map);
}
