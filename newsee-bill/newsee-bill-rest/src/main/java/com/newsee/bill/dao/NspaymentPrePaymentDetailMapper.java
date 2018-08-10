/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NspaymentPrePaymentDetail;
import com.newsee.common.vo.SearchVo;

public interface NspaymentPrePaymentDetailMapper {
  
    NspaymentPrePaymentDetail selectById(Long id);
    
    int insert(NspaymentPrePaymentDetail nspaymentPrePaymentDetail);
    
    int insertBatch(List<NspaymentPrePaymentDetail> nspaymentPrePaymentDetailList);
    
    int updateById(NspaymentPrePaymentDetail nspaymentPrePaymentDetail);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NspaymentPrePaymentDetail> listPage(SearchVo vo);
    
}