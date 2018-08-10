package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.system.entity.NsSystemRoleFunctionOrganization;
import com.newsee.system.vo.NsSystemRoleFunctionOrganizationVo;

public interface NsSystemRoleFunctionOrganizationMapper {
    int deleteByPrimaryKey(Long id);
    
    Boolean deleteByRoleId(Map<String, Object> map);

    int insert(NsSystemRoleFunctionOrganization record);

    int insertSelective(NsSystemRoleFunctionOrganization record);

    NsSystemRoleFunctionOrganization selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsSystemRoleFunctionOrganization record);

    int updateByPrimaryKey(NsSystemRoleFunctionOrganization record);
    
    List<NsSystemRoleFunctionOrganization> selectBySelective(NsSystemRoleFunctionOrganizationVo roleFuncOrgVo);
}