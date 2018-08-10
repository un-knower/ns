/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.newsee.charge.vo.AppStandardVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.charge.entity.ChargeHouseChargeStandard;
import com.newsee.charge.entity.ChargeHouseChargeStandardCustomer;
import com.newsee.charge.vo.HouseStandardAddVo;
import com.newsee.charge.vo.HouseStandardVo;

public interface IHouseStandardService {

	/**
	 * 获取房产收费标准列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<ChargeHouseChargeStandard> listPage(SearchVo searchVo);
	
	public Integer checkHouseStandardData(HouseStandardAddVo vo);
	
	public boolean editHouse(List<ChargeHouseChargeStandard> vos);
	
	/**
	 * 获取房产收费标准详情
	 * @param id 主键id
	 * @return
	 */
	Map<String, Object> detail(Map<String, Object> param);
	
	
	public List<ChargeHouseChargeStandard> listPageALL(SearchVo searchVo);
	/**
	 * 编辑房产收费标准详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(List<ChargeHouseChargeStandardCustomer> vos);
	
	/**
	 * 审核房产收费标准（审核通过或者不通过调用此接口）
	 * @param vo
	 * @return
	 */
	boolean auditHouseStandard(HouseStandardVo vo);
	
	/**
	 * 反审核房产收费标准  
	 * @param vo
	 * @return
	 */
	boolean auditBackHouseStandard(HouseStandardVo vo);
	
	/**
	 * 新增房产收费标准
	 * @return boolean 新增成功与否
	 */
	boolean add(List<HouseStandardAddVo> vo,Map<String, Object> map);
	
	/**
	 * 初始化新增房产收费标准页面数据
	 * @return boolean 新增成功与否
	 */
	List<HouseStandardAddVo> initHouseStandardData(HouseStandardAddVo vo);
	
	/**
	 * 根据主键删除房产收费标准
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除房产收费标准
	 * @param ids
	 * @return
	 */
	String deleteBatch(List<Long> ids);
	
	/**
	 * 审核通过或者不通过所有的房产收费标准
	 * @param searchVo 检索条件
	 * @return
	 */
	boolean auditAllHouseStandrd(SearchVo searchVo);
	
	/**
	 * 反审核过所有的房产收费标准
	 * @param searchVo 检索条件
	 * @return
	 */
	boolean auditBackAllHouseStandrd(SearchVo searchVo);

	/**
	 * 给APP端查询收费标准
	 * @param enterpriseId
	 * @param orgId
	 * @param houseId
	 * @param houseName
	 * @param typeId
	 * @return
	 */
	List<AppStandardVo> listAppStandard(Long enterpriseId, Long orgId, Long houseId, String houseName, Integer typeId);

}
