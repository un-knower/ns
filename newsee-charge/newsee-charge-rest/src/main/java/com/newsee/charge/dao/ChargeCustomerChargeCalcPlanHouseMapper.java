/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import com.newsee.charge.entity.ChargeCustomerChargeCalcPlanHouse;
import com.newsee.common.vo.SearchVo;

public interface ChargeCustomerChargeCalcPlanHouseMapper {
  
    ChargeCustomerChargeCalcPlanHouse selectById(Long id);
    
    int insert(ChargeCustomerChargeCalcPlanHouse chargeCustomerChargeCalcPlanHouse);
    
    int insertBatch(List<ChargeCustomerChargeCalcPlanHouse> chargeCustomerChargeCalcPlanHouseList);
    
    int updateById(ChargeCustomerChargeCalcPlanHouse chargeCustomerChargeCalcPlanHouse);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeCustomerChargeCalcPlanHouse> listPage(SearchVo vo);
    
}