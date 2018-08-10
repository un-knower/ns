/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NspaymentChargeDeposit;
import com.newsee.common.vo.SearchVo;

public interface NspaymentChargeDepositMapper {
  
    NspaymentChargeDeposit selectById(Long id);
    
    int insert(NspaymentChargeDeposit nspaymentChargeDeposit);
    
    int insertBatch(List<NspaymentChargeDeposit> nspaymentChargeDepositList);
    
    int updateById(NspaymentChargeDeposit nspaymentChargeDeposit);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NspaymentChargeDeposit> listPage(SearchVo vo);
    
}