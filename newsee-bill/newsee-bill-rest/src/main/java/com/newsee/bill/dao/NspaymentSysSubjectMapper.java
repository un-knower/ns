/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NspaymentSysSubject;
import com.newsee.common.vo.SearchVo;

public interface NspaymentSysSubjectMapper {
  
    NspaymentSysSubject selectById(Long id);
    
    int insert(NspaymentSysSubject nspaymentSysSubject);
    
    int insertBatch(List<NspaymentSysSubject> nspaymentSysSubjectList);
    
    int updateById(NspaymentSysSubject nspaymentSysSubject);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NspaymentSysSubject> listPage(SearchVo vo);
    
}