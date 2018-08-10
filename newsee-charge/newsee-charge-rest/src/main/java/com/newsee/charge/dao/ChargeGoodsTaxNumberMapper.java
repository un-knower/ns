/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.dao;

import java.util.List;
import java.util.Map;
import com.newsee.charge.entity.ChargeGoodsTaxNumber;
import com.newsee.common.vo.SearchVo;

public interface ChargeGoodsTaxNumberMapper {
  
    ChargeGoodsTaxNumber selectById(Long id);
    
    int insert(ChargeGoodsTaxNumber chargeGoodsTaxNumber);
    
    int insertBatch(List<ChargeGoodsTaxNumber> chargeGoodsTaxNumberList);
    
    int updateById(ChargeGoodsTaxNumber chargeGoodsTaxNumber);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<ChargeGoodsTaxNumber> listPage(SearchVo vo);
    
    ChargeGoodsTaxNumber selectByOrganizationId(Map<String,Object> map);
    
}