package com.newsee.system.service;

import java.util.List;
import java.util.Map;

import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.system.entity.NsCoreResourcefield;

public interface INsFieldService {

    List<NsCoreResourcefield> list(Map<String, Object> map);
    
    
    Map<String, Object> listField(LoginCommonDataVo commonVo);
}
