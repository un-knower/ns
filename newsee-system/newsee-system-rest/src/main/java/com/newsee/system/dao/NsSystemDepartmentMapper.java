/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsSystemDepartment;

public interface NsSystemDepartmentMapper {
  
    NsSystemDepartment selectById(Long id);
    
    int insert(NsSystemDepartment nsSystemDepartment);
    
    int insertBatch(List<NsSystemDepartment> nsSystemDepartmentList);
    
    int updateByIdSelective(NsSystemDepartment nsSystemDepartment);

    int updateById(NsSystemDepartment nsSystemDepartment);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    List<NsSystemDepartment> listById(List<Long> list);
    
    NsSystemDepartment checkOnlyShortName(NsSystemDepartment nsSystemDepartment);
    
    NsSystemDepartment checkOnlyCode(NsSystemDepartment nsSystemDepartment);
    
    NsSystemDepartment checkOnlyName(NsSystemDepartment nsSystemDepartment);
    
}