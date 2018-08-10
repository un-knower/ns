/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.vo.NoticeVo;

public interface INoticeService {

	/**
	 * 获取公告列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<NoticeVo> listPage(SearchVo searchVo);
	
	/**
	 * 获取公告详情
	 * @param id 主键id
	 * @return
	 */
	NoticeVo detail(Long id);
	
	/**
	 * 编辑公告详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(NoticeVo vo);
	
	/**
	 * 新增公告
	 * @return boolean 新增成功与否
	 */
	boolean add(NoticeVo vo);
	
	/**
	 * 根据主键删除公告
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除公告
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);

}
