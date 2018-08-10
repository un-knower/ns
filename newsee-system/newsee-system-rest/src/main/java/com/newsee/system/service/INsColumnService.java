package com.newsee.system.service;

import java.util.List;
import java.util.Map;

import com.newsee.common.vo.NsCoreResourcecolumnVo;

public interface INsColumnService {

    List<NsCoreResourcecolumnVo> list(Map<String, Object> map);
    
    List<NsCoreResourcecolumnVo> listForRemote(NsCoreResourcecolumnVo nsCoreResourcecolumnVo);

}
