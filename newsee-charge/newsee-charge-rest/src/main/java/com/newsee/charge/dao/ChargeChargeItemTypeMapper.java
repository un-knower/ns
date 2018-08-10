/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import java.util.Map;

import com.newsee.charge.entity.ChargeChargeItemType;
import com.newsee.common.vo.SearchVo;

public interface ChargeChargeItemTypeMapper {
  
    ChargeChargeItemType selectById(Long id);
    
    int insert(ChargeChargeItemType chargeChargeItemType);
    
    int insertBatch(List<ChargeChargeItemType> chargeChargeItemTypeList);
    
    int updateById(ChargeChargeItemType chargeChargeItemType);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeChargeItemType> listPage(SearchVo vo);
    
    List<ChargeChargeItemType> listItemTypeTree(Long parentId);
    
    List<ChargeChargeItemType> selectByCode(Map<String, Object> map);
    
}