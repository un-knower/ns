/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.vo.SearchVo;
import com.newsee.soss.entity.NsSossProductOrder;

public interface NsSossProductOrderMapper {
	 
	List<NsSossProductOrder> listResultBySearch(SearchVo searchVo);
  
    NsSossProductOrder selectById(Long id);
    
    /**
     * 获取服务订单数据
     * @param orderCode
     * @param enterpriseId
     * @param payStatus
     * @return
     */
    List<NsSossProductOrder> selectProductOrders(Map<String, Object> map);
    
    int insert(NsSossProductOrder nsSossProductOrder);
    
    int insertBatch(List<NsSossProductOrder> nsSossProductOrderList);
    
    int updateById(NsSossProductOrder nsSossProductOrder);
    
    int deleteById(Long id);
    /**
     * 修改删除状态
     * @param isDelete
     * @param ids
     * @param enterpriseId
     */
    int updateDeleteBatch(Map<String, Object> map);
    
    int deleteBatch(List<Long> ids);
    
}