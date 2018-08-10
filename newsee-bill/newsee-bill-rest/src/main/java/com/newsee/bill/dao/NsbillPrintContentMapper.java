/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NsbillPrintContent;
import com.newsee.common.vo.SearchVo;

public interface NsbillPrintContentMapper {
  
    NsbillPrintContent selectById(Long id);
    
    int insert(NsbillPrintContent nsbillPrintContent);
    
    int insertBatch(List<NsbillPrintContent> nsbillPrintContentList);
    
    int updateById(NsbillPrintContent nsbillPrintContent);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsbillPrintContent> listPage(SearchVo vo);
    
}