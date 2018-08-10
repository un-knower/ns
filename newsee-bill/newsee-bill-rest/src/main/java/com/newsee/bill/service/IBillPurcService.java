/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.newsee.bill.entity.NsbillBillBookSerailRule;
import com.newsee.bill.entity.NsbillBillInfo;
import com.newsee.bill.entity.NsbillBillInfoDetail;
import com.newsee.bill.vo.BillPurcVo;
import com.newsee.common.vo.SearchVo;

public interface IBillPurcService {

	/**
	 * 获取票据购入列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<BillPurcVo> listPage(SearchVo searchVo);
	
    List<NsbillBillBookSerailRule> ListBillRuleInfo(NsbillBillBookSerailRule nsbillBillBookSerailRule);
    Integer updateBillRuleInfo(List<NsbillBillBookSerailRule> nsbillBillBookSerailRules);
	
	/**
	 * 获取票据购入详情
	 * @param id 主键id
	 * @return
	 */
	BillPurcVo detail(Long id);
	
	/**
	 * 编辑票据购入详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(NsbillBillInfo vo);
	
	/**
	 * 新增票据购入
	 * @return boolean 新增成功与否
	 */
	boolean add(NsbillBillInfoDetail vo);
	
	/**
	 * 根据主键删除票据购入
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除票据购入
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);

}
