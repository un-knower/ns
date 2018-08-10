/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.charge.entity.ChargeGoodsTax;
import com.newsee.charge.vo.GoodsTaxVo;

public interface IGoodsTaxService {

	/**
	 * 获取税目列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<ChargeGoodsTax> listPage(SearchVo searchVo);
	
	/**
	 * 获取税目详情
	 * @param id 主键id
	 * @return
	 */
	GoodsTaxVo detail(Long id);
	
	/**
	 * 编辑税目详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(GoodsTaxVo vo);
	
	/**
	 * 新增税目
	 * @return boolean 新增成功与否
	 */
	boolean add(GoodsTaxVo vo);
	
	/**
	 * 根据主键删除税目
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除税目
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);
	
	/**
	 * 判断税目编码是否存在
	 * @param taxNo 税目编码
	 * @param id 主键id
	 * @param enterpriseId 企业id
	 * @return
	 */
	boolean checkTaxNoIsExist(String taxNo, Long id, Long enterpriseId);

	/**
	 * 根据税目编码搜索
	 * @param goodsTaxNo 税目编码
	 * @param enterpriseId 企业id
	 */
	List<ChargeGoodsTax> searchByGoodsTaxNo(String goodsTaxNo,Long enterpriseId);

}
