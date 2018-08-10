/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.vo.ProductVo;

public interface IProductService {

	/**
	 * 获取产品列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<ProductVo> listPage(SearchVo searchVo);
	
	/**
	 * 获取产品详情
	 * @param id 主键id
	 * @return
	 */
	ProductVo detail(Long id);
	
	/**
	 * 编辑产品详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(ProductVo vo);
	
	/**
	 * 新增产品
	 * @return String 新增
	 */
	boolean add(ProductVo vo);
	
	/**
	 * 根据主键删除产品
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除产品
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);

	/**
	 * 获取所有产品
	 * @return
	 * @throws Exception
	 */
	Map<String, List<ProductVo>> findProductList() throws Exception;
	
	
}
