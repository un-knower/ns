/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NspaymentDayClosingHelp;
import com.newsee.common.vo.SearchVo;

public interface NspaymentDayClosingHelpMapper {
  
    NspaymentDayClosingHelp selectById(Long id);
    
    int insert(NspaymentDayClosingHelp nspaymentDayClosingHelp);
    
    int insertBatch(List<NspaymentDayClosingHelp> nspaymentDayClosingHelpList);
    
    int updateById(NspaymentDayClosingHelp nspaymentDayClosingHelp);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NspaymentDayClosingHelp> listPage(SearchVo vo);
    
}