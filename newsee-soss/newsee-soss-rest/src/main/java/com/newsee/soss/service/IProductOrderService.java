/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.entity.NsSossProductOrderPrecinct;
import com.newsee.soss.entity.NsSossProductOrderProduct;
import com.newsee.soss.vo.ProductOrderVo;

public interface IProductOrderService {

	/**
	 * 获取产品订单列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<ProductOrderVo> listPage(SearchVo searchVo);
	
	/**
	 * 获取产品订单详情
	 * @param id 主键productId
	 * @return
	 */
	ProductOrderVo detail(Long productId);
	
	/**
	 * 编辑产品订单详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(ProductOrderVo vo);
	
	/**
	 * 修改订单产品服务
	 * @param product
	 * @return
	 */
	boolean updateOrderProduct(NsSossProductOrderProduct product);
	
	/**
	 * 新增产品订单
	 * @return boolean 新增成功与否
	 */
	boolean add(ProductOrderVo vo);
	
	/**
	 * 根据主键删除产品订单
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除产品订单
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);

	/**
	 * 获取企业订单信息
	 * @param enterpriseId
	 * @return
	 */
	List<ProductOrderVo> findProductOrderInfo(Long enterpriseId);
	
	/**
	 * 检查订单产品,服务状态
	 * @return
	 */
	List<NsSossProductOrderProduct> checkOrderProductStatus(int expire);
	
	/**
	 * 修改订单产品，服务状态
	 * @return
	 */
	boolean updateOrderProductStatus(List<Long> idList);
	
	/**
	 * 获取服务项目
	 * @param precinctId
	 * @return
	 */
	NsSossProductOrderPrecinct findProductPrecinct(Long precinctId);
	
	/**
	 * 修改服务项目
	 * @param precinctId
	 * @param precinctName
	 * @return
	 */
	int updateProductPrecinct(Long precinctId, String precinctName);
	/**
	 * 开通关闭服务
	 * @param vo
	 * @return
	 */
	boolean editProductOrderStatus(NsSossProductOrderProduct vo);
	
//	boolean editPayStatus(ProductOrderVo vo);
}
