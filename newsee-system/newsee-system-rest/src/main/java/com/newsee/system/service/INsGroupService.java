package com.newsee.system.service;

import com.newsee.system.vo.NsSystemGroupVo;

/**
 * @ClassName INsGroupService
 * @Description: 集团接口 
 * @author 胡乾亮
 * @date 2017年11月14日 上午11:47:45
 */
public interface INsGroupService {

    Boolean edit(NsSystemGroupVo groupVo, Long loginUserId);
    
    NsSystemGroupVo detail(Long id);
}
