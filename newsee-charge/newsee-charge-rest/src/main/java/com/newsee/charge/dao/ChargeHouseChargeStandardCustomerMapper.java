/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import java.util.Map;

import com.newsee.charge.entity.ChargeHouseChargeStandardCustomer;
import com.newsee.common.vo.SearchVo;

public interface ChargeHouseChargeStandardCustomerMapper {
  
    ChargeHouseChargeStandardCustomer selectById(Long id);
    List<ChargeHouseChargeStandardCustomer> selectByHouseChargeId(Map<String, Object> map);
    
    int insert(ChargeHouseChargeStandardCustomer chargeHouseChargeStandardCustomer);
    
    int insertBatch(List<ChargeHouseChargeStandardCustomer> chargeHouseChargeStandardCustomerList);
    
    int updateById(ChargeHouseChargeStandardCustomer chargeHouseChargeStandardCustomer);
    
    int deleteById(Long id);
    void deleteByHouseChargeId(Map<String, Object> map);
    
    int deleteByHouseChargeId(Long houseChargeId);
    
    int deleteBatch(List<Long> ids);
    
    int deleteBatchByHouseId(List<Long> ids);
    
    List<ChargeHouseChargeStandardCustomer> listPage(SearchVo vo);
    
}