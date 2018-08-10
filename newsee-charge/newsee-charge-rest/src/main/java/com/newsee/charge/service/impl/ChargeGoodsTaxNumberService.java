package com.newsee.charge.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.newsee.charge.dao.ChargeGoodsTaxNumberMapper;
import com.newsee.charge.entity.ChargeGoodsTaxNumber;
import com.newsee.charge.service.IChargeGoodsTaxNumberService;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;

/**
 * 税号设置
 * 
 * @author: mu.jie
 * @date: 2018年5月2日 上午9:29:54
 */
@Service
public class ChargeGoodsTaxNumberService implements IChargeGoodsTaxNumberService {

	@Autowired
	private ChargeGoodsTaxNumberMapper chargeGoodsTaxNumberMapper;

	/**
	 * 税号设置详情
	 */
	@ReadDataSource
	@Override
	public ChargeGoodsTaxNumber detail(final Long organizationId, final Long enterpriseId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("enterpriseId", enterpriseId);
		map.put("organizationId", organizationId);
		ChargeGoodsTaxNumber chargeGoodsTaxNumber = chargeGoodsTaxNumberMapper.selectByOrganizationId(map);
		return chargeGoodsTaxNumber;
	}

	/**
	 * 新增编辑税号设置
	 * 
	 * @return 是否成功
	 */
	@WriteDataSource
	@Override
	public Boolean addGoodsTaxNumber(ChargeGoodsTaxNumber chargeGoodsTaxNumber) {
		if (chargeGoodsTaxNumber == null) {
			return false;
		}
		int num;
		// 如果查询出来已经有，则更新改数据
		ChargeGoodsTaxNumber detail = detail(chargeGoodsTaxNumber.getOrganizationId(), chargeGoodsTaxNumber.getEnterpriseId());
		if (detail != null) {
			chargeGoodsTaxNumber.setId(detail.getId());
		}
		if (chargeGoodsTaxNumber.getId() != null) {
			num = chargeGoodsTaxNumberMapper.updateById(chargeGoodsTaxNumber);
		} else {
			num = chargeGoodsTaxNumberMapper.insert(chargeGoodsTaxNumber);
		}
		return num != 0;
	}

	/**
	 * 删除税号设置
	 */
	@WriteDataSource
	@Override
	public Boolean deleteGoodsTaxNumber(final Long id) {
		int num = chargeGoodsTaxNumberMapper.deleteById(id);
		return num != 0;
	}

}
