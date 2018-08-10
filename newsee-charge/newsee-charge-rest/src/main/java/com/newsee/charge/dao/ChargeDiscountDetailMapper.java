/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import com.newsee.charge.entity.ChargeDiscountDetail;
import com.newsee.common.vo.SearchVo;

public interface ChargeDiscountDetailMapper {
  
    ChargeDiscountDetail selectById(Long id);
    
    int insert(ChargeDiscountDetail chargeDiscountDetail);
    
    int insertBatch(List<ChargeDiscountDetail> chargeDiscountDetailList);
    
    int updateById(ChargeDiscountDetail chargeDiscountDetail);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeDiscountDetail> listPage(SearchVo vo);
    
}