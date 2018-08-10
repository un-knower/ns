/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.vo.MsgVo;

public interface IMsgService {

	/**
	 * 获取消息设置列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<MsgVo> listPage(SearchVo searchVo);
	
	/**
	 * 获取消息设置详情
	 * @param id 主键id
	 * @return
	 */
	MsgVo detail(Long id);
	
	/**
	 * 编辑消息设置详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(MsgVo vo);
	
	/**
	 * 新增消息设置
	 * @return boolean 新增成功与否
	 */
	boolean add(MsgVo vo);
	
	/**
	 * 根据主键删除消息设置
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除消息设置
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);

}
