/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NsbillBillUsedChangeDetail;
import com.newsee.common.vo.SearchVo;

public interface NsbillBillUsedChangeDetailMapper {
  
    NsbillBillUsedChangeDetail selectById(Long id);
    
    int insert(NsbillBillUsedChangeDetail nsbillBillUsedChangeDetail);
    
    int insertBatch(List<NsbillBillUsedChangeDetail> nsbillBillUsedChangeDetailList);
    
    int updateById(NsbillBillUsedChangeDetail nsbillBillUsedChangeDetail);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsbillBillUsedChangeDetail> listPage(SearchVo vo);
    
}