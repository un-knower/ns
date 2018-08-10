/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NsbillPrintPage;
import com.newsee.common.vo.SearchVo;

public interface NsbillPrintPageMapper {
  
    NsbillPrintPage selectById(Long id);
    
    int insert(NsbillPrintPage nsbillPrintPage);
    
    int insertBatch(List<NsbillPrintPage> nsbillPrintPageList);
    
    int updateById(NsbillPrintPage nsbillPrintPage);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsbillPrintPage> listPage(SearchVo vo);
    
}