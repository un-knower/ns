/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.newsee.charge.vo.AppStandardVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.charge.entity.ChargeChargeStandard;
import com.newsee.charge.vo.StandardVo;

public interface IStandardService {

	/**
	 * 获取收费标准列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<ChargeChargeStandard> listPage(SearchVo searchVo);
	
	/**
	 * 获取收费标准详情
	 * @param id 主键id
	 * @return
	 */
	StandardVo detail(Long id);
	public Boolean checkName(StandardVo vo);
	 List<ChargeChargeStandard> listPageALL(SearchVo searchVo);
	/**
	 * 编辑收费标准详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(StandardVo vo);
	
	/**
	 * 新增收费标准
	 * @return boolean 新增成功与否
	 */
	boolean add(StandardVo vo);
	
	/**
	 * 根据主键删除收费标准
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除收费标准
	 * @param ids
	 * @return
	 */
	int deleteBatch(List<Long> ids);

	/**
	 * 收费标准下拉框
	 * @param searchVo
	 * @return
	 */
	List<ChargeChargeStandard> listStandardForm(SearchVo searchVo);
}
