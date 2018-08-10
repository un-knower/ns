/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsCorePermission;

public interface NsCorePermissionMapper {
  
    NsCorePermission selectById(Long id);
    
    List<NsCorePermission> selectByPerids(List<String> list);
    
    List<NsCorePermission> selecttButtonPermByPerids(List<String> list);
    
    List<NsCorePermission> selectButtonPermByFuncId(String funcId);
    
    List<NsCorePermission> selectButtonPermByFuncIds(List<String> list);
    
    int insert(NsCorePermission nsCorePermission);
    
    int insertBatch(List<NsCorePermission> nsCorePermissionList);
    
    int updateById(NsCorePermission nsCorePermission);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    int updateByPerId(NsCorePermission nsCorePermission);
    
    int deleteByPerids(List<String> list);
}