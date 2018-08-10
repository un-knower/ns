/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NspaymentChargePayment;
import com.newsee.common.vo.SearchVo;

public interface NspaymentChargePaymentMapper {
  
    NspaymentChargePayment selectById(Long id);
    
    int insert(NspaymentChargePayment nspaymentChargePayment);
    
    int insertBatch(List<NspaymentChargePayment> nspaymentChargePaymentList);
    
    int updateById(NspaymentChargePayment nspaymentChargePayment);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NspaymentChargePayment> listPage(SearchVo vo);
    
}