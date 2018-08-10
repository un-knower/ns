/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.dao;

import java.util.List;
import java.util.Map;
import com.newsee.soss.entity.NsSossProductOrderPrecinct;

public interface NsSossProductOrderPrecinctMapper {
	/**
	 * 获取项目信息
	 * @param orderProductId
	 * @param orderProductIdList
	 * @param precinctId
	 * @param precinctIdList
	 * @return
	 */
	List<NsSossProductOrderPrecinct> selectPrecinctInfos(Map<String, Object> map);
  
    NsSossProductOrderPrecinct selectById(Long id);
    
    int insert(NsSossProductOrderPrecinct nsSossProductOrderPrecinct);
    
    int insertBatch(List<NsSossProductOrderPrecinct> nsSossProductOrderPrecinctList);
    
    int updateById(NsSossProductOrderPrecinct nsSossProductOrderPrecinct);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
}