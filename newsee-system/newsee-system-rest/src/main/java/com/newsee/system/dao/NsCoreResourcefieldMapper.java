/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.system.entity.NsCoreResourcefield;

public interface NsCoreResourcefieldMapper {
  
    NsCoreResourcefield selectById(Long id);
    
    /**
     * 根据orgid和fieldid获取field信息
     * @param nsCoreResourcefield
     * @return
     * @author xiaosisi add on 2017/11/29
     */
    NsCoreResourcefield selectByOrgIdAndFieldId(NsCoreResourcefield nsCoreResourcefield);
    
    int insert(NsCoreResourcefield nsCoreResourcefield);
    
    int insertBatch(List<NsCoreResourcefield> nsCoreResourcefieldList);
    
    int updateById(NsCoreResourcefield nsCoreResourcefield);
    
    /**
     * 根据orgid和fieldid更新field信息
     * @param nsCoreResourcefield
     * @return
     */
    int updateByOrgIdAndFieldId(NsCoreResourcefield nsCoreResourcefield);
    
    int deleteById(Long id);
    
    /**
     * 根据企业id和组织id删除field
     * @param nsCoreResourcefield
     * @return
     */
    int deleteByOrgId(NsCoreResourcefield nsCoreResourcefield);
    
    int deleteSoftById(Long id);
    
    List<NsCoreResourcefield> selectByMap(Map<String, Object> map);
}