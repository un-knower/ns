/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NsbillAutoBillNumSet;
import com.newsee.common.vo.SearchVo;

public interface NsbillAutoBillNumSetMapper {
  
    NsbillAutoBillNumSet selectById(Long id);
    
    int insert(NsbillAutoBillNumSet nsbillAutoBillNumSet);
    
    int insertBatch(List<NsbillAutoBillNumSet> nsbillAutoBillNumSetList);
    
    int updateById(NsbillAutoBillNumSet nsbillAutoBillNumSet);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsbillAutoBillNumSet> listPage(SearchVo vo);
    
}