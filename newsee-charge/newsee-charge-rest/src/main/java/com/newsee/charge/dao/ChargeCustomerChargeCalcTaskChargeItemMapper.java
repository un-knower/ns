/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import com.newsee.charge.entity.ChargeCustomerChargeCalcTaskChargeItem;
import com.newsee.common.vo.SearchVo;

public interface ChargeCustomerChargeCalcTaskChargeItemMapper {
  
    ChargeCustomerChargeCalcTaskChargeItem selectById(Long id);
    List<ChargeCustomerChargeCalcTaskChargeItem> selectByTaskId(Long id);
    
    int insert(ChargeCustomerChargeCalcTaskChargeItem chargeCustomerChargeCalcTaskChargeItem);
    
    int insertBatch(List<ChargeCustomerChargeCalcTaskChargeItem> chargeCustomerChargeCalcTaskChargeItemList);
    
    int updateById(ChargeCustomerChargeCalcTaskChargeItem chargeCustomerChargeCalcTaskChargeItem);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeCustomerChargeCalcTaskChargeItem> listPage(SearchVo vo);
    
}