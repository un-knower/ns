/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.system.entity.NsCoreResourcecolumn;

public interface NsCoreResourcecolumnMapper {
  
    NsCoreResourcecolumn selectById(Long id);
    
    /**
     * 根据orgId和columnId获取column信息
     * @param nsCoreResourcecolumn
     * @return
     * @author xiaosisi add on 2017/11/29
     */
    NsCoreResourcecolumn selectByOrgIdAndColumnId(NsCoreResourcecolumn nsCoreResourcecolumn);
    
    int insert(NsCoreResourcecolumn nsCoreResourcecolumn);
    
    int insertBatch(List<NsCoreResourcecolumn> nsCoreResourcecolumnList);
    
    int updateById(NsCoreResourcecolumn nsCoreResourcecolumn);
    
    /**
     * 根据orgid和columnid更新column信息
     * @param nsCoreResourcecolumn
     * @return
     * @author xiaosisi add on 2017/11/29
     */
    int updateByOrgIdAndColumnId(NsCoreResourcecolumn nsCoreResourcecolumn);
    
    int deleteById(Long id);
    
    /**
     * 根据企业id和组织id删除column
     * @param nsCoreResourcecolumn
     * @return
     */
    int deleteByOrgId(NsCoreResourcecolumn nsCoreResourcecolumn);
    
    int deleteSoftById(Long id);
    
    List<NsCoreResourcecolumn> selectByMap(Map<String, Object> map);
    
    List<NsCoreResourcecolumn> selectByVo(NsCoreResourcecolumnVo nsCoreResourcecolumnVo);

}