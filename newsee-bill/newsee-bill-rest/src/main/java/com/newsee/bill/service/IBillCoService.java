/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.newsee.bill.entity.NsbillBillDraw;
import com.newsee.bill.vo.BillCoVo;
import com.newsee.common.vo.SearchVo;

public interface IBillCoService {

	/**
	 * 获取票据领用列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<BillCoVo> listPage(SearchVo searchVo);
	
	/**
	 * 获取票据领用详情
	 * @param id 主键id
	 * @return
	 */
	BillCoVo detail(Long id);
	
	/**
	 * 编辑票据领用详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(BillCoVo vo);
	
	/**
	 * 新增票据领用
	 * @return boolean 新增成功与否
	 */
	boolean add(NsbillBillDraw vo);
	
	/**
	 * 根据主键删除票据领用
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除票据领用
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);

}
