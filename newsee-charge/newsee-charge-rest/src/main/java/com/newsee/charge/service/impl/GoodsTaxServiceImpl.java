package com.newsee.charge.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.newsee.charge.dao.ChargeChargeItemMapper;
import com.newsee.charge.dao.ChargeGoodsTaxMapper;
import com.newsee.charge.entity.ChargeChargeItem;
import com.newsee.charge.entity.ChargeGoodsTax;
import com.newsee.charge.service.IGoodsTaxService;
import com.newsee.charge.vo.GoodsTaxVo;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;

import scala.languageFeature.reflectiveCalls;

@Service
public class GoodsTaxServiceImpl implements IGoodsTaxService {

	@Autowired
	private ChargeGoodsTaxMapper chargeGoodstaxMapper;
	@Autowired
	private ChargeChargeItemMapper chargeChargeItemMapper;

	/**
	 * 获取税目列表信息
	 * 
	 * @param searchVo 检索条件
	 * @return
	 */
	@ReadDataSource
	public PageInfo<ChargeGoodsTax> listPage(SearchVo searchVo) {
		if (Objects.isNull(searchVo)) {
			return null;
		}
		PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
		List<ChargeGoodsTax> list = chargeGoodstaxMapper.listPage(searchVo);
		PageInfo<ChargeGoodsTax> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 获取税目详情
	 * 
	 * @param id 主键id
	 * @return
	 */
	@ReadDataSource
	public GoodsTaxVo detail(Long id) {
		if (Objects.isNull(id)) {
			return null;
		}
		GoodsTaxVo vo = new GoodsTaxVo();
		ChargeGoodsTax chargeGoodstax = chargeGoodstaxMapper.selectById(id);
		// 如果查询出了数据，将数据拷贝到vo中
		if (!Objects.isNull(chargeGoodstax)) {
			BeanUtils.copyProperties(chargeGoodstax, vo);
		}
		return vo;
	}

	/**
	 * 编辑税目详情
	 * 
	 * @return boolean 编辑成功与否
	 */
	@WriteDataSource
	public boolean edit(GoodsTaxVo vo) {
		if (Objects.isNull(vo)) {
			return false;
		}
		ChargeGoodsTax chargeGoodstax = new ChargeGoodsTax();
		BeanUtils.copyProperties(vo, chargeGoodstax);
		int countchargeGoodstax = chargeGoodstaxMapper.updateById(chargeGoodstax);
		if (countchargeGoodstax == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 新增税目
	 * 
	 * @return boolean 新增成功与否
	 */
	@WriteDataSource
	public boolean add(GoodsTaxVo vo) {
		if (Objects.isNull(vo)) {
			return false;
		}
		ChargeGoodsTax chargeGoodstax = new ChargeGoodsTax();
		BeanUtils.copyProperties(vo, chargeGoodstax);
		int countchargeGoodstax = chargeGoodstaxMapper.insert(chargeGoodstax);
		if (countchargeGoodstax == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 根据主键删除税目,逻辑删除
	 * 
	 * @param id 主键id
	 * @return
	 */
	@WriteDataSource
	public boolean delete(Long id) {
		if (Objects.isNull(id)) {
			return false;
		}
		ChargeGoodsTax chargeGoodsTax = chargeGoodstaxMapper.selectById(id);
		// 判断收费科目设置中是否有用到这个税目，如果用到则不能删除，否则可以删除
		List<ChargeChargeItem> chargeItemList = chargeChargeItemMapper.selectByGoodsTaxCode(chargeGoodsTax.getGoodsTaxNo());
		if (!CollectionUtils.isEmpty(chargeItemList)) {
			BizException.fail(ResultCodeEnum.SERVER_ERROR, "收费科目中有使用该税目，不能删除！");
		}
		int num = chargeGoodstaxMapper.deleteById(id);
		if (num == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 根据主键批量删除税目
	 * 
	 * @param ids
	 * @return
	 */
	@WriteDataSource
	public boolean deleteBatch(List<Long> ids) {
		if (Objects.isNull(ids)) {
			return false;
		}
		// 判断收费科目设置中是否有用到这个税目，如果用到则不能删除，否则可以删除
		List<ChargeGoodsTax> chargeGoodsTaxList = chargeGoodstaxMapper.selectByIds(ids);
		chargeGoodsTaxList.forEach(chargeGoodsTax -> {
			List<ChargeChargeItem> chargeItemList = chargeChargeItemMapper.selectByGoodsTaxCode(chargeGoodsTax.getGoodsTaxNo());
			if (!CollectionUtils.isEmpty(chargeItemList)) {
				BizException.fail(ResultCodeEnum.SERVER_ERROR, "收费科目中有使用该税目，不能删除！，编号：" + chargeGoodsTax.getGoodsTaxNo());
			}
		});
		int countchargeGoodstax = chargeGoodstaxMapper.deleteBatch(ids);
		if (countchargeGoodstax == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 判断税目编码是否存在
	 * 
	 * @param taxNo 税目编码
	 * @param id 主键id
	 * @return
	 */
	@ReadDataSource
	public boolean checkTaxNoIsExist(String taxNo, Long id, Long enterpriseId) {
		if (id != null) {
			ChargeGoodsTax dbTax = chargeGoodstaxMapper.selectById(id);
			if (taxNo.equals(dbTax.getGoodsTaxNo())) {
				return false;
			}
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("enterpriseId", enterpriseId);
		map.put("goodsTaxNo", taxNo);
		List<ChargeGoodsTax> tax = chargeGoodstaxMapper.selectByCode(map);
		if (CollectionUtils.isEmpty(tax)) {
			return false;
		}
		return true;
	}

	/*
	 * 根据税目编码搜索
	 */
	@ReadDataSource
	@Override
	public List<ChargeGoodsTax> searchByGoodsTaxNo(String goodsTaxNo, Long enterpriseId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("goodsTaxNo", goodsTaxNo);
		map.put("enterpriseId", enterpriseId);
		List<ChargeGoodsTax> list = chargeGoodstaxMapper.searchByCode(map);
		return list;
	}
}
