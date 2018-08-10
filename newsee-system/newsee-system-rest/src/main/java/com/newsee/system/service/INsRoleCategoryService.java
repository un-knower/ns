package com.newsee.system.service;

import java.util.List;

import com.newsee.system.vo.NsCoreRolecategoryVo;

public interface INsRoleCategoryService {

    Boolean add(NsCoreRolecategoryVo rolecategoryVo);
    
    NsCoreRolecategoryVo detail(Long rolecategoryid);
    
    Boolean edit(NsCoreRolecategoryVo rolecategoryVo);
    
    Boolean deleteSoft(Long rolecategoryid);
    
    List<NsCoreRolecategoryVo> listByOrganizationId(Long organizationId);
    
    List<NsCoreRolecategoryVo> listRoleGroupByCategory(Long organizationId);
    
    List<NsCoreRolecategoryVo> listRolecategoryByRoleids(List<String> list);
}
