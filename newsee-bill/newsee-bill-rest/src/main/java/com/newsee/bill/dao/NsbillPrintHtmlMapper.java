/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NsbillPrintHtml;
import com.newsee.common.vo.SearchVo;

public interface NsbillPrintHtmlMapper {
  
    NsbillPrintHtml selectById(Long id);
    
    int insert(NsbillPrintHtml nsbillPrintHtml);
    
    int insertBatch(List<NsbillPrintHtml> nsbillPrintHtmlList);
    
    int updateById(NsbillPrintHtml nsbillPrintHtml);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsbillPrintHtml> listPage(SearchVo vo);
    
}