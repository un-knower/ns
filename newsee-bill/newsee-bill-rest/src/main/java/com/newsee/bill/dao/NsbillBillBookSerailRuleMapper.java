/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NsbillBillBookSerailRule;
import com.newsee.common.vo.SearchVo;

public interface NsbillBillBookSerailRuleMapper {
  
    NsbillBillBookSerailRule selectById(Long id);
    
    int insert(NsbillBillBookSerailRule nsbillBillBookSerailRule);
    
    int insertBatch(List<NsbillBillBookSerailRule> nsbillBillBookSerailRuleList);
    
    int updateById(NsbillBillBookSerailRule nsbillBillBookSerailRule);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsbillBillBookSerailRule> listPage(SearchVo vo);
    
    List<NsbillBillBookSerailRule> ListBillRuleInfo(NsbillBillBookSerailRule nsbillBillBookSerailRule);
    
}