/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.vo.SearchVo;
import com.newsee.soss.entity.NsSossNotice;

public interface NsSossNoticeMapper {
  
	List<NsSossNotice> listResultBySearch(SearchVo searchVo);
	
    NsSossNotice selectById(Long id);
    
    int insert(NsSossNotice nsSossNotice);
    
    int insertBatch(List<NsSossNotice> nsSossNoticeList);
    
    int updateById(NsSossNotice nsSossNotice);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
}