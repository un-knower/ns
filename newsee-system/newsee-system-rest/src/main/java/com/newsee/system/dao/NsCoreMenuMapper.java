/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.system.entity.NsCoreMenu;

public interface NsCoreMenuMapper {
  
    NsCoreMenu selectById(Long id);
    
    NsCoreMenu selectByNodeInfo(Map<String, Object> map);
    
    /**
     * 根据企业id，organizationid，jeCoreMenuId,
     * 获取menu信息
     * @param menu
     * @return
     * @author xiaosisi add on 2017/11/28
     */
    NsCoreMenu selectByMenuIdAndOrgId(NsCoreMenu menu);
    
    /**
     * 根据企业id，organizationid
     * 删除menu信息，用于jepf数据同步
     * @param menu
     * @return
     * @author xiaosisi add on 2018/01/19
     */
    int deleteByOrgId(NsCoreMenu menu);
    
    /**
     * 根据企业id，organizationid，jeCoreMenuId,
     * 更新menu信息
     * @param menu
     * @return
     * @author xiaosisi add on 2017/11/28
     */
    int updateByMenuIdAndOrgId(NsCoreMenu menu);

    List<NsCoreMenu> selectByMenuIds(Map<String, Object> map);
    
    int insert(NsCoreMenu nsCoreMenu);
    
    int insertBatch(List<NsCoreMenu> nsCoreMenuList);
    
    int updateById(NsCoreMenu nsCoreMenu);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
}