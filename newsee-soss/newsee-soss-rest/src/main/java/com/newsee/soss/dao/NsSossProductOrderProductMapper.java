/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.vo.SearchVo;
import com.newsee.soss.entity.NsSossProductOrderProduct;
import com.newsee.soss.vo.ProductOrderVo;

public interface NsSossProductOrderProductMapper {
	
	List<ProductOrderVo> listResultBySearch(SearchVo searchVo);
  
    NsSossProductOrderProduct selectById(Long id);
    
    /**
     * 获取订单产品数据
     * @param productOrderId  订单id
     * @param productOrderIdList
     * @param productId  原产品id
     * @param productName
     * @param productType 
     * @param serviceStatus
     * @param serviceStatusIn
     * @param handleTime
     * @param startTime
     * @param endTime
     * @return
     */
    List<NsSossProductOrderProduct> selectOrderProducts(Map<String, Object> map);
    
    int insert(NsSossProductOrderProduct nsSossProductOrderProduct);
    
    int insertBatch(List<NsSossProductOrderProduct> nsSossProductOrderProductList);
    
    int updateById(NsSossProductOrderProduct nsSossProductOrderProduct);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    /**
     * 更新过期订单产品状态
     * @param serviceStatus
     * @param serviceWork
     * @return
     */
    int updateExprireProduct(Map<String, Object> map);
}