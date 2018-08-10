/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.system.entity.NsCoreFuncinfo;

public interface NsCoreFuncinfoMapper {
  
    NsCoreFuncinfo selectById(Long id);
    
    NsCoreFuncinfo selectByFuncId(Map<String, Object> map);
    
    List<NsCoreFuncinfo> selectByFunctionIds(List<String> list);
    
    NsCoreFuncinfo selectByFunctionCode(String funcCode);
    
    /**
     * 根据organzationid和funcid获取funcinfo
     * @param funcInfo
     * @return
     * @author xiaosisi add on 2017/11/29
     */
    NsCoreFuncinfo selectByOrgIdAndFuncId(Map<String, Object> map);
    
    List<NsCoreFuncinfo> selectByOrgId(Map<String, Object> map);
    
    int insert(NsCoreFuncinfo nsCoreFuncinfo);
    
    int insertBatch(List<NsCoreFuncinfo> nsCoreFuncinfoList);
    
    /**
     * 根据organzationid和funcId更新funcinfo
     * @param funcInfo
     * @return
     * @author xiaosisi add on 2017/11/29
     */
    int updateByOrgIdAndFuncId(NsCoreFuncinfo funcInfo);
    
    /**
     * 根据organzationid和企业id删除func信息
     * @param funcInfo
     * @return
     * @author xiaosisi add on 2018/01/19
     */
    int deleteByOrgId(NsCoreFuncinfo funcInfo);
    
    int updateById(NsCoreFuncinfo nsCoreFuncinfo);
    
    int deleteById(Long id);
    
    int deleteSoftById(Long id);
}