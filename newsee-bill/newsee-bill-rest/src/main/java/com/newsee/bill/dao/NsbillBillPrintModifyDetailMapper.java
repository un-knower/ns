/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NsbillBillPrintModifyDetail;
import com.newsee.common.vo.SearchVo;

public interface NsbillBillPrintModifyDetailMapper {
  
    NsbillBillPrintModifyDetail selectById(Long id);
    
    int insert(NsbillBillPrintModifyDetail nsbillBillPrintModifyDetail);
    
    int insertBatch(List<NsbillBillPrintModifyDetail> nsbillBillPrintModifyDetailList);
    
    int updateById(NsbillBillPrintModifyDetail nsbillBillPrintModifyDetail);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsbillBillPrintModifyDetail> listPage(SearchVo vo);
    
}