/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import com.newsee.charge.entity.ChargeCustomerChargeCalcTask;
import com.newsee.common.vo.SearchVo;

import java.util.List;
import java.util.Map;

public interface ChargeCustomerChargeCalcTaskMapper {
  
    ChargeCustomerChargeCalcTask selectById(Long id);
    
    int insert(ChargeCustomerChargeCalcTask chargeCustomerChargeCalcTask);
    List<ChargeCustomerChargeCalcTask> selectByIdAndName(ChargeCustomerChargeCalcTask chargeCustomerChargeCalcTask);
    
    int insertBatch(List<ChargeCustomerChargeCalcTask> chargeCustomerChargeCalcTaskList);
    
    int updateById(ChargeCustomerChargeCalcTask chargeCustomerChargeCalcTask);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    int checkPayment(Map<String, Object> map);
    
    List<ChargeCustomerChargeCalcTask> listPage(SearchVo vo);
    
    List<ChargeCustomerChargeCalcTask> selectAllPlan(Long id);
    
    List<ChargeCustomerChargeCalcTask> listAllPlan(SearchVo searchVo);

	Integer checkPaymentCalc(Map<String, Object> map);
    
}