/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import com.newsee.charge.entity.ChargeChargePayment;
import com.newsee.common.vo.SearchVo;

public interface ChargeChargePaymentMapper {
  
    ChargeChargePayment selectById(Long id);
    
    int insert(ChargeChargePayment chargeChargePayment);
    
    int insertBatch(List<ChargeChargePayment> chargeChargePaymentList);
    
    int updateById(ChargeChargePayment chargeChargePayment);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeChargePayment> listPage(SearchVo vo);
    
}