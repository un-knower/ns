/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import java.util.Map;

import com.newsee.charge.entity.ChargeCalcPlanQuartz;

public interface ChargeCalcPlanQuartzMapper {
  
	ChargeCalcPlanQuartz selectById(Long id);
	
	ChargeCalcPlanQuartz selectByPlanId(Long id);
    
    List<ChargeCalcPlanQuartz> selectAllWorkedPlan();
    
    List<ChargeCalcPlanQuartz> selectAllPlan();
    
    int deleteById(Long id);
    
    int insert(ChargeCalcPlanQuartz chargeCalcPlanQuartz);
    
    int insertBatch(List<ChargeCalcPlanQuartz> list);
    
    int deleteBatch(List<Long> ids);
    
    int updateById(ChargeCalcPlanQuartz chargeCalcPlanQuartz);

	Integer planManager(Map<String, Object> map);
    
}