/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.charge.entity.ChargeChargeItem;
import com.newsee.charge.entity.ChargeChargeItemType;
import com.newsee.charge.vo.ItemTypeVo;
import com.newsee.charge.vo.ItemVo;

public interface IItemService {

	/**
	 * 获取收费科目列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<ChargeChargeItem> listPage(SearchVo searchVo);
	
	
	 List<ChargeChargeItem> listPageALL(SearchVo searchVo);
	 
	 List<ChargeChargeItem> findChildSubject(Long id );
	/**
	 * 获取收费科目详情
	 * @param id 主键id
	 * @return
	 */
	ItemVo detail(Long id);
	
	ChargeChargeItem findById(Long Id);
	
	
	
	/**
	 * 编辑收费科目详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(ItemVo vo);
	
	/**
	 * 新增收费科目
	 * @return boolean 新增成功与否
	 */
	boolean add(ItemVo vo);
	
	/**
	 * 根据主键删除收费科目
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除收费科目
	 * @param ids
	 * @return
	 */
	Integer deleteBatch(List<Long> ids);
	
	Integer clearTaxRate(List<Long> ids);
	
	List<Long> findChildALL(Long id);
	/**
	 * 新增收费科目分类
	 * @return boolean 新增成功与否
	 */
	boolean addItemType(ItemTypeVo vo);
	
	/**
	 * 新增收费科目分类
	 * @return boolean 新增成功与否
	 */
	boolean editItemType(ItemTypeVo vo);
	
	/**
	 * 根据主键删除收费科目
	 * @param id 主键id
	 * @return
	 */
	boolean deleteItemType(Long id);
	
	/**
	 * 根据主键删除收费科目
	 * @param id 主键id
	 * @return
	 */
	List<ChargeChargeItemType> listItemTypeTree(Long parentId);
	
	/**
	 * 检查itemcode是否存在
	 * @param chargeItemCode itemCode
	 * @param id 主键id
	 * @param enterpriseId 企业id
	 * @return
	 */
	boolean checkItemCodeIsExist(String chargeItemCode, Long id, Long enterpriseId);
	
	/**
	 * 检查itemtypecode是否存在
	 * @param chargeItemTypeCode
	 * @param id
	 * @param enterpriseId
	 * @return
	 */
	boolean checkItemTypeCodeIsExist(String chargeItemTypeCode, Long id, Long enterpriseId);
	
	/**
	 * 根据收费科目类型id获取收费科目列表
	 * @param id
	 * @return
	 */
	public List<ChargeChargeItem> listItemTree(Long id,Long enterPriseId);

	public List<ChargeChargeItem> selectByCodeOrName(Map<String, Object> map);

	/**
	 * 科目树-下拉框树
	 * @param enterpriseId
	 * @param id
	 * @return
	 */
	Map<String, Object> listItemTreeForm(Long enterpriseId, Long id);
}
