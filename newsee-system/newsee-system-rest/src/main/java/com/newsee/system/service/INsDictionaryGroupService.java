package com.newsee.system.service;

import java.util.List;

import com.newsee.system.vo.DictionaryTreeVo;
import com.newsee.system.vo.NsCoreDictionarygroupVo;

public interface INsDictionaryGroupService {

    Boolean add(NsCoreDictionarygroupVo dictionarygroupVo);
    
    Boolean edit(NsCoreDictionarygroupVo dictionarygroupVo);
    
    Boolean delete(Long dictionarygroupId, Long organizationId);
    
    List<NsCoreDictionarygroupVo> list(Long organizationId);
    
    NsCoreDictionarygroupVo detail(Long dictionarygroupId);
    
    DictionaryTreeVo listTree(Long organizationId);
}
