/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import com.newsee.charge.entity.ChargeCalcLog;
import com.newsee.common.vo.SearchVo;

public interface ChargeCalcLogMapper {
  
    ChargeCalcLog selectById(Long id);
    
    int insert(ChargeCalcLog chargeCalcLog);
    
    int insertBatch(List<ChargeCalcLog> chargeCalcLogList);
    
    int updateById(ChargeCalcLog chargeCalcLog);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeCalcLog> listPage(SearchVo vo);
    
}