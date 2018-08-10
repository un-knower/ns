/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import java.util.Map;

import com.newsee.charge.entity.ChargeCustomerChargeDetail;
import com.newsee.common.vo.SearchVo;

public interface ChargeCustomerChargeDetailMapper {
  
    ChargeCustomerChargeDetail selectById(Long id);
    
    int insert(ChargeCustomerChargeDetail chargeCustomerChargeDetail);
    
    int insertBatch(List<ChargeCustomerChargeDetail> chargeCustomerChargeDetailList);
    
    int updateById(ChargeCustomerChargeDetail chargeCustomerChargeDetail);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeCustomerChargeDetail> listPage(SearchVo vo);

	ChargeCustomerChargeDetail selectByHouseAndItem(ChargeCustomerChargeDetail detail);

    /**
     * 应收款审核、反审核
     * @param map
     * @return
     */
    int checkChargeDetail(Map<String,Object> map);
    
    Integer findPlanExceuteNumbers(Long planId);

    /**
     * 减免应收款
     * @param detail
     * @return
     */
    int discountChargeDetail(ChargeCustomerChargeDetail detail);

	List<ChargeCustomerChargeDetail> selectByTaskId(Long id);

	Integer checkPayment(Map<String, Object> map);
}