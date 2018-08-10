/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import com.newsee.charge.entity.ChargeCustomerChargeCalcTaskHouse;
import com.newsee.common.vo.SearchVo;

public interface ChargeCustomerChargeCalcTaskHouseMapper {
  
    ChargeCustomerChargeCalcTaskHouse selectById(Long id);
    List<ChargeCustomerChargeCalcTaskHouse> selectByTaskId(Long id);
    
    int insert(ChargeCustomerChargeCalcTaskHouse chargeCustomerChargeCalcTaskHouse);
    
    int insertBatch(List<ChargeCustomerChargeCalcTaskHouse> chargeCustomerChargeCalcTaskHouseList);
    
    int updateById(ChargeCustomerChargeCalcTaskHouse chargeCustomerChargeCalcTaskHouse);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeCustomerChargeCalcTaskHouse> listPage(SearchVo vo);
    
}