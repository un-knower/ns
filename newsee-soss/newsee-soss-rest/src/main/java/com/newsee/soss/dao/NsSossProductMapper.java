/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.vo.SearchVo;
import com.newsee.soss.entity.NsSossProduct;

public interface NsSossProductMapper {
  
	List<NsSossProduct> listResultBySearch(SearchVo searchVo);
	
    NsSossProduct selectById(Long id);
    
    int insert(NsSossProduct nsSossProduct);
    
    int insertBatch(List<NsSossProduct> nsSossProductList);
    
    int updateById(NsSossProduct nsSossProduct);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    /**
     * 获取所有产品
     * @param map
     * @return
     */
    List<NsSossProduct> selectProductList(Map<String, Object> map);
    
}