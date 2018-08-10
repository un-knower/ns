/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.vo.SearchVo;
import com.newsee.system.entity.NsCoreRole;

public interface NsCoreRoleMapper {
  
    NsCoreRole selectById(Long id);
    
    List<NsCoreRole> selectByRoleIds(List<String> list);
    
    int insert(NsCoreRole nsCoreRole);
    
    int insertBatch(List<NsCoreRole> nsCoreRoleList);
    
    int updateById(NsCoreRole nsCoreRole);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    int updateByRoleId(NsCoreRole nsCoreRole);
    
    int deleteByRoleId(String id);
    
    NsCoreRole selectByRoleId(String id);
    
    List<NsCoreRole> selectByOrganizationId(Long id);
    
    List<NsCoreRole> listResultBySearch(SearchVo searchVo);
    
    List<NsCoreRole> selectByOrgIdAndCategoryId(Map<String, Object> map);
    
    NsCoreRole selectByOrgIdAndName(Map<String, Object> map);
    
}