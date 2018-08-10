/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.Date;
import java.util.List;
import com.newsee.charge.entity.ChargeCustomerChargeCalcPlan;
import com.newsee.common.vo.SearchVo;

public interface ChargeCustomerChargeCalcPlanMapper {
  
    ChargeCustomerChargeCalcPlan selectById(Long id);
    
    int insert(ChargeCustomerChargeCalcPlan chargeCustomerChargeCalcPlan);
    
    int insertBatch(List<ChargeCustomerChargeCalcPlan> chargeCustomerChargeCalcPlanList);
    
    int updateById(ChargeCustomerChargeCalcPlan chargeCustomerChargeCalcPlan);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeCustomerChargeCalcPlan> listPage(SearchVo vo);
    
    List<ChargeCustomerChargeCalcPlan> selectAllPlan(Date executeTime);
}