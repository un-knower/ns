/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import java.util.Map;
import com.newsee.charge.entity.ChargeGoodsTaxRate;
import com.newsee.common.vo.SearchVo;

public interface ChargeGoodsTaxRateMapper {
  
    ChargeGoodsTaxRate selectById(Long id);
    
    int insert(ChargeGoodsTaxRate chargeGoodsTaxRate);
    
    int insertBatch(List<ChargeGoodsTaxRate> chargeGoodsTaxRateList);
    
    int updateById(ChargeGoodsTaxRate chargeGoodsTaxRate);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeGoodsTaxRate> listPage(SearchVo vo);
    
    ChargeGoodsTaxRate selectByOrganizationIdAndItemId(Map<String, Object> map);

    int deleteByParam(Map<String,Object> map);

    int updatePrecinctName(Map<String,Object> map);
}