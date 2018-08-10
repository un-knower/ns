package com.newsee.system.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.system.entity.NsCoreDictionaryitem;
import com.newsee.system.vo.NsCoreDictionaryitemVo;

public interface INsDictionaryItemService {

    Boolean add(NsCoreDictionaryitemVo dictionaryitemVo);
    
    Boolean edit(NsCoreDictionaryitemVo dictionaryitemVo);
    
    Boolean delete(String dictionaryitemId);
    
    List<NsCoreDictionaryitemVo> list(String  dictionaryId,Long organizationId);
    
    NsCoreDictionaryitemVo detail(String dictionaryitemId);
    
    PageInfo<NsCoreDictionaryitemVo> listPage(SearchVo searchVo,String dictionaryitemDictionaryId, String dictionaryGroupId);
    List<NsCoreDictionaryitemVo> listPageSelect(String dictionaryitemDictionaryId, String dictionaryGroupId);
}
