/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.newsee.charge.entity.ChargeChargeStandard;
import com.newsee.common.vo.SearchVo;

public interface ChargeChargeStandardMapper {
  
    ChargeChargeStandard selectById(Long id);
    
    int insert(ChargeChargeStandard chargeChargeStandard);
    
    int insertBatch(List<ChargeChargeStandard> chargeChargeStandardList);
    
    int updateById(ChargeChargeStandard chargeChargeStandard);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeChargeStandard> listPage(SearchVo vo);
    List<ChargeChargeStandard> selectByPrecintId(Long id);
    List<ChargeChargeStandard> listPageAll(SearchVo vo);
    
    List<ChargeChargeStandard> findChargeItemNameForPericint(Map<String, Object> map);
    
    List<ChargeChargeStandard> findChargestandardNameForPericint(Map<String, Object> map);
    
    Integer checkName(ChargeChargeStandard chargeChargeStandard);
    
    /**
     * 根据organizationId,enterpriseId获取相应的收费标准
     * @param map
     * @return
     */
    List<ChargeChargeStandard> selectByOrganizationId(Map<String, Object> map);

}