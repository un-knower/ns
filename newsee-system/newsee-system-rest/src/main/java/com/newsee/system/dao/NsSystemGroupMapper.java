/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsSystemGroup;

public interface NsSystemGroupMapper {
  
    NsSystemGroup selectById(Long id);
    
    int insert(NsSystemGroup nsSystemGroup);
    
    int insertBatch(List<NsSystemGroup> nsSystemGroupList);
    
    int updateById(NsSystemGroup nsSystemGroup);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
}