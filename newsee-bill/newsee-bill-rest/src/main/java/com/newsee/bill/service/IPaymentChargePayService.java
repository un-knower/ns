/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.newsee.bill.entity.NspaymentChargePayment;
import com.newsee.common.vo.SearchVo;
import com.newsee.bill.vo.PaymentChargePayVo;

public interface IPaymentChargePayService {

	/**
	 * 获取客户缴款明细表列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<NspaymentChargePayment> listPage(SearchVo searchVo);
	
	/**
	 * 获取客户缴款明细表详情
	 * @param id 主键id
	 * @return
	 */
	PaymentChargePayVo detail(Long id);
	
	/**
	 * 编辑客户缴款明细表详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(PaymentChargePayVo vo);
	
	/**
	 * 新增客户缴款明细表
	 * @return boolean 新增成功与否
	 */
	boolean add(PaymentChargePayVo vo);
	
	/**
	 * 根据主键删除客户缴款明细表
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除客户缴款明细表
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);

}
