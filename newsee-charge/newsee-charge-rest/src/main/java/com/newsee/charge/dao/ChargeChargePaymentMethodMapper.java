/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import java.util.Map;

import com.newsee.charge.entity.ChargeChargePaymentMethod;
import com.newsee.common.vo.SearchVo;

public interface ChargeChargePaymentMethodMapper {
  
    ChargeChargePaymentMethod selectById(Long id);
    
    int insert(ChargeChargePaymentMethod chargeChargePaymentMethod);
    
    int insertBatch(List<ChargeChargePaymentMethod> chargeChargePaymentMethodList);
    
    int updateById(ChargeChargePaymentMethod chargeChargePaymentMethod);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeChargePaymentMethod> listPage(SearchVo vo);
    
    List<ChargeChargePaymentMethod> selectByCode(Map<String, Object> map);
}