/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.bill.vo.BillReVo;

public interface IBillReService {

	/**
	 * 获取票据补录列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<BillReVo> listPage(SearchVo searchVo);
	
	/**
	 * 获取票据补录详情
	 * @param id 主键id
	 * @return
	 */
	BillReVo detail(Long id);
	
	/**
	 * 编辑票据补录详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(BillReVo vo);
	
	/**
	 * 新增票据补录
	 * @return boolean 新增成功与否
	 */
	boolean add(BillReVo vo);
	
	/**
	 * 根据主键删除票据补录
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除票据补录
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);

}
