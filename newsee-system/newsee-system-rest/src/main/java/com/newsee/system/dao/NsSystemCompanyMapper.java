/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;

import com.newsee.common.entity.NsSystemCompany;

public interface NsSystemCompanyMapper {
  
    NsSystemCompany selectById(Long id);
    
    int insert(NsSystemCompany nsSystemCompany);
    
    int insertBatch(List<NsSystemCompany> nsSystemCompanyList);
    
    int updateByIdSelective(NsSystemCompany nsSystemCompany);
    
    int updateById(NsSystemCompany nsSystemCompany);

    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    List<NsSystemCompany> listById(List<Long> list);
    
    NsSystemCompany checkOnlyShortName(NsSystemCompany nsSystemCompany);
    
    NsSystemCompany checkOnlyCode(NsSystemCompany nsSystemCompany);
    
    NsSystemCompany checkOnlyName(NsSystemCompany nsSystemCompany);
}