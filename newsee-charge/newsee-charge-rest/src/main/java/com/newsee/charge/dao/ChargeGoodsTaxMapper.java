/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import java.util.Map;

import com.newsee.charge.entity.ChargeGoodsTax;
import com.newsee.common.vo.SearchVo;

public interface ChargeGoodsTaxMapper {
  
    ChargeGoodsTax selectById(Long id);
    
    int insert(ChargeGoodsTax chargeGoodsTax);
    
    int insertBatch(List<ChargeGoodsTax> chargeGoodsTaxList);
    
    int updateById(ChargeGoodsTax chargeGoodsTax);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeGoodsTax> listPage(SearchVo vo);
    
    List<ChargeGoodsTax> selectByCode(Map<String, Object> map);
    
    List<ChargeGoodsTax> selectByIds(List<Long> list);

    List<ChargeGoodsTax> selectByCodeOrName(Map<String,Object> map);

    List<ChargeGoodsTax> searchByCode(Map<String,Object> map);
}