/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;

import com.newsee.bill.entity.NsbillBillDraw;
import com.newsee.bill.vo.BillCoVo;
import com.newsee.common.vo.SearchVo;

public interface NsbillBillDrawMapper {
  
	BillCoVo selectById(Long id);
    
    int insert(NsbillBillDraw nsbillBillDraw);
    
    int insertBatch(List<BillCoVo> nsbillBillDrawList);
    
    int updateById(BillCoVo nsbillBillDraw);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<BillCoVo> listPage(SearchVo vo);
    
}