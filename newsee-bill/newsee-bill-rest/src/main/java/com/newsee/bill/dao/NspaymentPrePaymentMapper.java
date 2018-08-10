/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NspaymentPrePayment;
import com.newsee.common.vo.SearchVo;

public interface NspaymentPrePaymentMapper {
  
    NspaymentPrePayment selectById(Long id);
    
    int insert(NspaymentPrePayment nspaymentPrePayment);
    
    int insertBatch(List<NspaymentPrePayment> nspaymentPrePaymentList);
    
    int updateById(NspaymentPrePayment nspaymentPrePayment);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NspaymentPrePayment> listPage(SearchVo vo);
    
}