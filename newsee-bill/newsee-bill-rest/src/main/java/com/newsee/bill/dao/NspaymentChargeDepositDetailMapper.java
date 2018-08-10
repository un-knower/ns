/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import com.newsee.bill.entity.NspaymentChargeDepositDetail;
import com.newsee.common.vo.SearchVo;

import java.util.List;

public interface NspaymentChargeDepositDetailMapper {
  
    NspaymentChargeDepositDetail selectById(Long id);
    
    int insert(NspaymentChargeDepositDetail nspaymentChargeDepositDetail);
    
    int insertBatch(List<NspaymentChargeDepositDetail> nspaymentChargeDepositDetailList);
    
    int updateById(NspaymentChargeDepositDetail nspaymentChargeDepositDetail);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NspaymentChargeDepositDetail> listPage(SearchVo vo);
    
}