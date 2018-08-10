/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.newsee.charge.entity.ChargeChargeItem;
import com.newsee.common.vo.SearchVo;

public interface ChargeChargeItemMapper {
  
    ChargeChargeItem selectById(Long id);
    
    int insert(ChargeChargeItem chargeChargeItem);
    
    int insertBatch(List<ChargeChargeItem> chargeChargeItemList);
    
    int updateById(ChargeChargeItem chargeChargeItem);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeChargeItem> findPath(Map<String, Object> map);
    
    List<ChargeChargeItem> listPage(SearchVo vo);
    
    List<ChargeChargeItem> selectByCode(Map<String, Object> map);
    
    List<ChargeChargeItem> selectByTypeId(Map<String, Object> map);

    List<ChargeChargeItem> selectItemTreeForm(Map<String, Object> map);

    List<ChargeChargeItem> selectByCodeOrName(Map<String, Object> map);
    /**
     * 根据organizationId和enterpriseId获取收费科目信息
     * map中需要传入  organizationId和enterpriseId
     * @param map organizationId enterpriseId
     * @return
     */
    List<ChargeChargeItem> selectByOrganizationId(Map<String, Object> map);
    
    /**
     * 根据税目科目查询收费科目信息
     * @param goodsTaxCode 税目科目编码
     * @return
     */
    List<ChargeChargeItem> selectByGoodsTaxCode(String goodsTaxCode);

	int clearTaxRate(List<Long> ids);
}