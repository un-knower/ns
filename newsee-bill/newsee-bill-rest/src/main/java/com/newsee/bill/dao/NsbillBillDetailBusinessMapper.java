/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NsbillBillDetailBusiness;
import com.newsee.common.vo.SearchVo;

public interface NsbillBillDetailBusinessMapper {
  
    NsbillBillDetailBusiness selectById(Long id);
    
    int insert(NsbillBillDetailBusiness nsbillBillDetailBusiness);
    
    int insertBatch(List<NsbillBillDetailBusiness> nsbillBillDetailBusinessList);
    
    int updateById(NsbillBillDetailBusiness nsbillBillDetailBusiness);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsbillBillDetailBusiness> listPage(SearchVo vo);
    
}