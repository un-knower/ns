/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsCoreRolePerm;

public interface NsCoreRolePermMapper {
  
    NsCoreRolePerm selectById(Long id);
    
    List<NsCoreRolePerm> selectByRoleIds(List<String> list);
    
    List<NsCoreRolePerm> selectByRoleId(String roleid);
    
    int insert(NsCoreRolePerm nsCoreRolePerm);
    
    int insertBatch(List<NsCoreRolePerm> nsCoreRolePermList);
    
    int updateById(NsCoreRolePerm nsCoreRolePerm);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    int deleteByRoleId(String id);
}