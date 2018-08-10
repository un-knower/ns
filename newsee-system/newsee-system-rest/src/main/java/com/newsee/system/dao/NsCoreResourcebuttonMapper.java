/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.system.entity.NsCoreResourcebutton;

public interface NsCoreResourcebuttonMapper {
  
    NsCoreResourcebutton selectById(Long id);
    
    /**
     * 根据orgId和buttonid获取button信息
     * @param nsCoreResourcebutton
     * @return
     */
    NsCoreResourcebutton selectByOrgIdAndButtonId(NsCoreResourcebutton nsCoreResourcebutton);
    
    List<NsCoreResourcebutton> selectByFunctionId(String id);
    
    List<NsCoreResourcebutton> selectByButtonIds(Map<String, Object> map);
    
    List<NsCoreResourcebutton> selectByOrgId(Map<String, Object> map);
    
    int insert(NsCoreResourcebutton nsCoreResourcebutton);
    
    int insertBatch(List<NsCoreResourcebutton> nsCoreResourcebuttonList);
    
    int updateById(NsCoreResourcebutton nsCoreResourcebutton);
    
    /**
     * 根据orgId和buttonid更新button信息
     * @param nsCoreResourcebutton
     * @return
     */
    int updateByOrgIdAndButtonId(NsCoreResourcebutton nsCoreResourcebutton);
    
    /**
     * 根据企业id和组织id删除button
     * @param nsCoreResourcebutton
     * @return
     */
    int deleteByOrgId(NsCoreResourcebutton nsCoreResourcebutton);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
}