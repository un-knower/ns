/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.system.entity.NsSystemOrganization;

public interface NsSystemOrganizationMapper {
  
    NsSystemOrganization selectById(Long id);
    
    int insert(NsSystemOrganization nsSystemOrganization);
    
    int insertBatch(List<NsSystemOrganization> nsSystemOrganizationList);
    
    int updateById(NsSystemOrganization nsSystemOrganization);
    
    int updateByIdSelective(NsSystemOrganization nsSystemOrganization);

    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    List<NsSystemOrganization> selectAllCompanyLevelOrg(Map<String, String> map);
    
    NsSystemOrganization selectByGroupId(Long id);
    
    NsSystemOrganization selectByOrganizationId(Long organizationId);

    NsSystemOrganization selectByCompanyId(Long id);
    
    NsSystemOrganization selectByDepartmentId(Long id);
    
    List<NsSystemOrganization> selectByPath(Map<String, Object> map);
    
    List<NsSystemOrganization> selectChildsByParentId(Map<String, Object> map);
    
    Boolean updateOrderAddOneByIds(List<Long> list);
    
    Boolean updateLevelByIds(Map<String, Object> map);
    
    Boolean batchUpdatePath(List<NsSystemOrganization> list);
    
    List<NsSystemOrganization> selectByIds(List<Long> list);
    
    int selectChildCount(Map<String, Object> map);
    
    List<NsSystemOrganization> listOrganizationTreeBySearch(Map<String, Object> map);
    
    List<NsSystemOrganization> checkOnlyCode(NsSystemOrganization nsSystemOrganization);
    
    List<NsSystemOrganization> checkOnlyShortName(NsSystemOrganization nsSystemOrganization);
    
    int selectCountByOrgCode(NsSystemOrganization organization);
    
    
    
}