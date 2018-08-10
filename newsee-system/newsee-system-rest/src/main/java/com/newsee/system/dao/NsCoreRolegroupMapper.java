/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsCoreRolegroup;

public interface NsCoreRolegroupMapper {
  
    NsCoreRolegroup selectById(Long id);
    
    int insert(NsCoreRolegroup nsCoreRolegroup);
    
    int insertBatch(List<NsCoreRolegroup> nsCoreRolegroupList);
    
    int updateById(NsCoreRolegroup nsCoreRolegroup);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    
    List<NsCoreRolegroup> selectByOrganizationId(Long id);
}