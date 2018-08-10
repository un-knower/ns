/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.HashMap;
import java.util.List;
import com.newsee.charge.entity.ChargeChargeStandardLadder;
import com.newsee.common.vo.SearchVo;

public interface ChargeChargeStandardLadderMapper {
  
	 List<ChargeChargeStandardLadder>   selectById(HashMap<String, Object> param);
    
    int insert(ChargeChargeStandardLadder chargeChargeStandardLadder);
    
    int insertBatch(List<ChargeChargeStandardLadder> chargeChargeStandardLadderList);
    
    int updateById(ChargeChargeStandardLadder chargeChargeStandardLadder);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeChargeStandardLadder> listPage(SearchVo vo);
    
    List<ChargeChargeStandardLadder> selectByStandardId(Long id);
    
    int deleteByStandardId(Long id);
    
    int deleteByStandardIdBatch(List<Long> ids);
    
}