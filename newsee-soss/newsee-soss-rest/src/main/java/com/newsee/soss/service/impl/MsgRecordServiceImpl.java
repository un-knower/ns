package com.newsee.soss.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.dao.NsSossMsgRecordMapper;
import com.newsee.soss.entity.NsSossMsgRecord;
import com.newsee.soss.service.IMsgRecordService;
import com.newsee.soss.vo.MsgRecordVo;


@Service
public class MsgRecordServiceImpl implements IMsgRecordService {
	
    @Autowired
    private NsSossMsgRecordMapper nsSossMsgrecordMapper;
    
	/**
	 * 获取消息记录列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	public PageInfo<MsgRecordVo> listPage(SearchVo searchVo){
		if (Objects.isNull(searchVo)) {
			return null;
		}
		List<MsgRecordVo> voList = new ArrayList<>();
		MsgRecordVo msgRecordVo = null;
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
         List<NsSossMsgRecord> list = nsSossMsgrecordMapper.listPage(searchVo);
         for (NsSossMsgRecord msgRecord : list) {
        	 msgRecordVo = new MsgRecordVo();
        	 BeanUtils.copyProperties(msgRecord, msgRecordVo);
        	 voList.add(msgRecordVo);        	 
		}
         
        PageInfo<MsgRecordVo> pageInfo = new PageInfo<>(voList);
        return pageInfo;
	}
	
	/**
	 * 获取消息记录详情
	 * @param id 主键id
	 * @return
	 */
	public MsgRecordVo detail(Long id){
		if(Objects.isNull(id)) {
			return null;
		}
		MsgRecordVo vo = new MsgRecordVo();
		NsSossMsgRecord nsSossMsgrecord = nsSossMsgrecordMapper.selectById(id);
		//如果查询出了数据，将数据拷贝到vo中
		if(!Objects.isNull(nsSossMsgrecord)){
			BeanUtils.copyProperties(nsSossMsgrecord, vo);
		}
	    return vo;
	}
	
	/**
	 * 编辑消息记录详情
	 * @return boolean 编辑成功与否
	 */
	public boolean edit(MsgRecordVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsSossMsgRecord nsSossMsgrecord = new NsSossMsgRecord();
		BeanUtils.copyProperties(vo, nsSossMsgrecord);
		int countnsSossMsgrecord = nsSossMsgrecordMapper.updateById(nsSossMsgrecord);
		if(countnsSossMsgrecord == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 新增消息记录
	 * @return boolean 新增成功与否
	 */
	public boolean add(MsgRecordVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsSossMsgRecord nsSossMsgrecord = new NsSossMsgRecord();
		BeanUtils.copyProperties(vo, nsSossMsgrecord);
		int countnsSossMsgrecord = nsSossMsgrecordMapper.insert(nsSossMsgrecord);
		if(countnsSossMsgrecord == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键删除消息记录
	 * @param id 主键id
	 * @return
	 */
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		int countnsSossMsgrecord = nsSossMsgrecordMapper.deleteById(id);
		if(countnsSossMsgrecord == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键批量删除消息记录
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		int countnsSossMsgrecord = nsSossMsgrecordMapper.deleteBatch(ids);
		if(countnsSossMsgrecord == 0 ){
			return false;
		}
	    return true;
	}

	@Override
	public List<NsSossMsgRecord> findMsgRecordList(MsgRecordVo vo) {
		Map<String, Object> map = new HashMap<>();
		map.put("enterpriseId", vo.getEnterpriseId());
		map.put("isRead", vo.getIsRead());
		map.put("msgType", vo.getMsgType());
		List<NsSossMsgRecord> list = nsSossMsgrecordMapper.selectMsgRecordList(map);
		return list;
	}
	
}
