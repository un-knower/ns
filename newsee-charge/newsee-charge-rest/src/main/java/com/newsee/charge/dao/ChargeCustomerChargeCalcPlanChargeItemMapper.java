/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import com.newsee.charge.entity.ChargeCustomerChargeCalcPlanChargeItem;
import com.newsee.common.vo.SearchVo;

public interface ChargeCustomerChargeCalcPlanChargeItemMapper {
  
    ChargeCustomerChargeCalcPlanChargeItem selectById(Long id);
    
    int insert(ChargeCustomerChargeCalcPlanChargeItem chargeCustomerChargeCalcPlanChargeItem);
    
    int insertBatch(List<ChargeCustomerChargeCalcPlanChargeItem> chargeCustomerChargeCalcPlanChargeItemList);
    
    int updateById(ChargeCustomerChargeCalcPlanChargeItem chargeCustomerChargeCalcPlanChargeItem);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeCustomerChargeCalcPlanChargeItem> listPage(SearchVo vo);
    
}