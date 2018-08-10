/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NsbillBillInfoDetail;
import com.newsee.bill.vo.BillUseManSearchVo;
import com.newsee.common.vo.SearchVo;

public interface NsbillBillInfoDetailMapper {
  
    NsbillBillInfoDetail selectById(Long id);
    
    int insert(NsbillBillInfoDetail nsbillBillInfoDetail);
    
    Integer selectBillTypeNum(NsbillBillInfoDetail nsbillBillInfoDetail);
    
    int insertBatch(List<NsbillBillInfoDetail> nsbillBillInfoDetailList);
    
    int updateById(NsbillBillInfoDetail nsbillBillInfoDetail);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsbillBillInfoDetail> listPage(SearchVo vo);

}