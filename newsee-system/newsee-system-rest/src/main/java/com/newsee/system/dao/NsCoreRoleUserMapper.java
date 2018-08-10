/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.system.entity.NsCoreRoleUser;

public interface NsCoreRoleUserMapper {
  
    NsCoreRoleUser selectById(Long id);
    
    List<NsCoreRoleUser> selectByUserId(Map<String, Object> map);
    
    int insert(NsCoreRoleUser nsCoreRoleUser);
    
    int insertBatch(List<NsCoreRoleUser> nsCoreRoleUserList);
    
    int updateById(NsCoreRoleUser nsCoreRoleUser);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    List<NsCoreRoleUser> selectByRoleId(String id);
    
    int deleteByUserId(String id);
    
    int deleteByRoleid(String id);
}