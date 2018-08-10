package com.newsee.soss.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.common.entity.FilterEntity;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.dao.NsSossMsgMapper;
import com.newsee.soss.entity.NsSossMsg;
import com.newsee.soss.service.IMsgService;
import com.newsee.soss.vo.MsgVo;

@Service
public class MsgServiceImpl implements IMsgService {
	
    @Autowired
    private NsSossMsgMapper nsSossMsgMapper;
    
	/**
	 * 获取消息设置列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	public PageInfo<MsgVo> listPage(SearchVo searchVo){
		if (Objects.isNull(searchVo)) {
			return null;
		}
		List<MsgVo> voList = new ArrayList<>();
		MsgVo msgVo = null;
		String temp = "sendWeb sendSMS sendEmail";
		if (temp.contains(searchVo.getOrderFieldName())) {
			searchVo.setOrderFieldName(null);
		}
		List<FilterEntity> filterItems = searchVo.getFilterList();
		if (!CollectionUtils.isEmpty(filterItems)) {
			for (Iterator<FilterEntity> iterator = filterItems.iterator(); iterator.hasNext();) {
				FilterEntity filter = iterator.next();
				if (temp.contains(filter.getFieldName())) {					
					iterator.remove();
				}
			}
		}
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
         List<NsSossMsg> list = nsSossMsgMapper.listPage(searchVo);
         for (NsSossMsg msg : list) {
        	 msgVo = new MsgVo();
        	 BeanUtils.copyProperties(msg, msgVo);
        	 voList.add(msgVo);
		}
        PageInfo<MsgVo> pageInfo = new PageInfo<>(voList);
        
        return pageInfo;
	}
	
	/**
	 * 获取消息设置详情
	 * @param id 主键id
	 * @return
	 */
	public MsgVo detail(Long id){
		if(Objects.isNull(id)) {
			return null;
		}
		MsgVo vo = new MsgVo();
		NsSossMsg nsSossMsg = nsSossMsgMapper.selectById(id);
		//如果查询出了数据，将数据拷贝到vo中
		if(!Objects.isNull(nsSossMsg)){
			BeanUtils.copyProperties(nsSossMsg, vo);
		}
	    return vo;
	}
	
	/**
	 * 编辑消息设置详情
	 * @return boolean 编辑成功与否
	 */
	public boolean edit(MsgVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsSossMsg nsSossMsg = new NsSossMsg();
		BeanUtils.copyProperties(vo, nsSossMsg);
		nsSossMsg.setSendType(vo.getSendWeb()+vo.getSendSMS()+vo.getSendEmail());		
		int countnsSossMsg = nsSossMsgMapper.updateById(nsSossMsg);
		if(countnsSossMsg == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 新增消息设置
	 * @return boolean 新增成功与否
	 */
	public boolean add(MsgVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsSossMsg nsSossMsg = new NsSossMsg();
		BeanUtils.copyProperties(vo, nsSossMsg);
		int countnsSossMsg = nsSossMsgMapper.insert(nsSossMsg);
		if(countnsSossMsg == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键删除消息设置
	 * @param id 主键id
	 * @return
	 */
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		int countnsSossMsg = nsSossMsgMapper.deleteById(id);
		if(countnsSossMsg == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键批量删除消息设置
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		int countnsSossMsg = nsSossMsgMapper.deleteBatch(ids);
		if(countnsSossMsg == 0 ){
			return false;
		}
	    return true;
	}
}
