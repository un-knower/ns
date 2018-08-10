/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.vo.ServiceVo;

public interface IServiceService {

	/**
	 * 获取工单列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<ServiceVo> listPage(SearchVo searchVo);
	
	/**
	 * 获取工单详情
	 * @param id 主键id
	 * @return
	 */
	ServiceVo detail(Long id);
	
	/**
	 * 编辑工单详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(ServiceVo vo);
	
	/**
	 * 新增工单
	 * @return boolean 新增成功与否
	 */
	boolean add(ServiceVo vo);
	
	/**
	 * 根据主键删除工单
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除工单
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);
	
	/**
	 * 统计工单
	 * @param enterpriseId
	 * @return
	 */
	Map<String, Integer> getServiceCountInfo(Long enterpriseId, Long userId);
}
