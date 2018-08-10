/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NspaymentDayClosing;
import com.newsee.common.vo.SearchVo;

public interface NspaymentDayClosingMapper {
  
    NspaymentDayClosing selectById(Long id);
    
    int insert(NspaymentDayClosing nspaymentDayClosing);
    
    int insertBatch(List<NspaymentDayClosing> nspaymentDayClosingList);
    
    int updateById(NspaymentDayClosing nspaymentDayClosing);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NspaymentDayClosing> listPage(SearchVo vo);
    
}