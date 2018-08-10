/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.vo.EnterpriseVo;

public interface IEnterpriseService {
	
	NsSossEnterprise getEnterpriseInfo(Long enterpriseId) throws Exception;
	
	/**
	 * 添加新企业
	 * @param vo
	 * @throws Exception
	 */
	Long registerEnterpriseInfo(EnterpriseVo vo) throws Exception;
	
	/**
	 * 根据企业ID，查询企业信息
	 * @param enterpriseIds
	 * @return
	 * @throws Exception
	 */
	List<NsSossEnterprise> findEnterpriseInfoList(List<Long> enterpriseIds) throws Exception;
	
	/**
	 * 根据用户ID，查询企业信息
	 * @param userIds
	 * @return
	 * @throws Exception
	 */
	List<NsSossEnterprise> findEnterpriseInfoListByUserIds(List<Long> userIds) throws Exception;

	/**
	 * 获取企业列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<EnterpriseVo> listPage(SearchVo searchVo);
	
	/**
	 * 获取企业详情
	 * @param id 主键id
	 * @return
	 */
	EnterpriseVo detail(Long id);
	
	/**
	 * 编辑企业详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(EnterpriseVo vo);
	
	/**
	 * 新增企业
	 * @return boolean 新增成功与否
	 */
	boolean add(EnterpriseVo vo);
	
	/**
	 * 根据主键删除企业
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除企业
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);

}
