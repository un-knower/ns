/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import com.newsee.bill.entity.NsbillBillUsedDetail;
import com.newsee.common.vo.SearchVo;

import java.util.List;

public interface NsbillBillUsedDetailMapper {
  
    NsbillBillUsedDetail selectById(Long id);
    
    int insert(NsbillBillUsedDetail nsbillBillUsedDetail);
    
    int insertBatch(List<NsbillBillUsedDetail> nsbillBillUsedDetailList);
    
    int updateById(NsbillBillUsedDetail nsbillBillUsedDetail);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsbillBillUsedDetail> listPage(SearchVo vo);
    
}