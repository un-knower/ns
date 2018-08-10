/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;

import com.newsee.bill.entity.NsbillBillInfo;
import com.newsee.bill.vo.BillPurcVo;
import com.newsee.common.vo.SearchVo;

public interface NsbillBillInfoMapper {
  
	BillPurcVo selectById(Long id);
    
    int insert(NsbillBillInfo nsbillBillInfo);
    
    int insertBatch(List<NsbillBillInfo> nsbillBillInfoList);
    
    int updateById(NsbillBillInfo nsbillBillInfo);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<BillPurcVo> listPage(SearchVo vo);
    
}