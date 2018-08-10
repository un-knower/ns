/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.entity.NsSossMsgRecord;
import com.newsee.soss.vo.MsgRecordVo;

public interface IMsgRecordService {

	/**
	 * 获取消息记录列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<MsgRecordVo> listPage(SearchVo searchVo);
	
	/**
	 * 获取消息记录详情
	 * @param id 主键id
	 * @return
	 */
	MsgRecordVo detail(Long id);
	
	/**
	 * 编辑消息记录详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(MsgRecordVo vo);
	
	/**
	 * 新增消息记录
	 * @return boolean 新增成功与否
	 */
	boolean add(MsgRecordVo vo);
	
	/**
	 * 根据主键删除消息记录
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除消息记录
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);
	
	/**
	 * 查询消息记录
	 * @param vo
	 * @return
	 */
	List<NsSossMsgRecord> findMsgRecordList(MsgRecordVo vo);

}
