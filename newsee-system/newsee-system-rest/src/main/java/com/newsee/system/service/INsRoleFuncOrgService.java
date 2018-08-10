package com.newsee.system.service;

import java.util.List;

import com.newsee.system.vo.NsDataSeeScopeVo;
import com.newsee.system.vo.NsSystemRoleFunctionOrganizationVo;

public interface INsRoleFuncOrgService {

    Boolean add(NsDataSeeScopeVo dataSeeScope);
    
    NsSystemRoleFunctionOrganizationVo detail(Long id);
    
    Boolean edit(NsDataSeeScopeVo dataSeeScope);
    
    List<NsSystemRoleFunctionOrganizationVo> list(Long userId, String funcId);
}
