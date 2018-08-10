/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsCoreRolegroupPerm;

public interface NsCoreRolegroupPermMapper {
  
    NsCoreRolegroupPerm selectById(Long id);
    
    int insert(NsCoreRolegroupPerm nsCoreRolegroupPerm);
    
    int insertBatch(List<NsCoreRolegroupPerm> nsCoreRolegroupPermList);
    
    int updateById(NsCoreRolegroupPerm nsCoreRolegroupPerm);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    List<NsCoreRolegroupPerm> selectByRolegroupId(String id);
}