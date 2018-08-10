package com.newsee.charge.service;

import com.newsee.charge.entity.ChargeGoodsTaxNumber;

/**
 * 税号设置Service
 * 
 * @author: mu.jie
 * @date: 2018年5月2日 上午9:25:58
 */
public interface IChargeGoodsTaxNumberService {

	/**
	 * 税号详情
	 * @param organizationId 组织id
	 * @param enterpriseId 租户id
	 * @return
	 */
	ChargeGoodsTaxNumber detail(final Long organizationId, Long enterpriseId);

	/**
	 * 新增编辑税号
	 * @param chargeGoodsTaxNumber 租户id
	 * @return
	 */
	Boolean addGoodsTaxNumber(ChargeGoodsTaxNumber chargeGoodsTaxNumber);

	/**
	 * @param id
	 * @return
	 */
	Boolean deleteGoodsTaxNumber(final Long id);
}
