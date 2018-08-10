/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import java.util.Map;

import com.newsee.charge.entity.ChargeChargeStandard;
import com.newsee.charge.entity.ChargeHouseChargeStandard;
import com.newsee.charge.vo.HouseStandardAddVo;
import com.newsee.common.vo.SearchVo;

public interface ChargeHouseChargeStandardMapper {
  
    ChargeHouseChargeStandard selectById(Long id);
    
    ChargeHouseChargeStandard findHouseStandard(HouseStandardAddVo map);
    List<ChargeHouseChargeStandard> findHouseStandardByPrecientId(HouseStandardAddVo map);
    int insert(ChargeHouseChargeStandard chargeHouseChargeStandard);
    
    int insertBatch(List<ChargeHouseChargeStandard> chargeHouseChargeStandardList);
    
    int updateById(ChargeHouseChargeStandard chargeHouseChargeStandard);
    
    int deleteById(Long id);
   
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeHouseChargeStandard> listPage(SearchVo vo);
    
    int updateAuditStatus(Map<String, Object> map);
    
    int auditAllHouseStandrd(SearchVo vo);
    
    int auditBackAllHouseStandrd(SearchVo vo);

    /**
     * 查询APP端的收费标准
     * @param map
     * @return
     */
    List<ChargeHouseChargeStandard> listAppHouseStandard(Map<String,Object> map);
}