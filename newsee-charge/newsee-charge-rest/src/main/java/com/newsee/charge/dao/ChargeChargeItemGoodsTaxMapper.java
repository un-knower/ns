/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import com.newsee.charge.entity.ChargeChargeItemGoodsTax;

public interface ChargeChargeItemGoodsTaxMapper {
  
    ChargeChargeItemGoodsTax selectById(Long id);
    
    List<ChargeChargeItemGoodsTax> selectByItemId(Long id);
    
    int updateById(ChargeChargeItemGoodsTax chargeChargeItemGoodsTax);
    
    int insert(ChargeChargeItemGoodsTax chargeChargeItemGoodsTax);
    
    int insertBatch(List<ChargeChargeItemGoodsTax> chargeChargeItemGoodsTaxList);
    
    int deleteById(Long id);
    
    int deleteByItemId(Long id);
    
    int deleteByItemIdBatch(List<Long> ids);
    
    int deleteBatch(List<Long> ids);
    
}