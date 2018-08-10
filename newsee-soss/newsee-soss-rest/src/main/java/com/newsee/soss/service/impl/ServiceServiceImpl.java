package com.newsee.soss.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.Constants;
import com.newsee.common.utils.DateUtils;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.dao.NsSossServiceMapper;
import com.newsee.soss.dao.NsSossServiceRecordMapper;
import com.newsee.soss.entity.NsSossService;
import com.newsee.soss.entity.NsSossServiceRecord;
import com.newsee.soss.service.IServiceService;
import com.newsee.soss.vo.ServiceVo;

@Service
public class ServiceServiceImpl implements IServiceService {
	
    @Autowired
    private NsSossServiceMapper nsSossServiceMapper;
    @Autowired
    private NsSossServiceRecordMapper nsSossServicerecordMapper;
    
	/**
	 * 获取工单列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	public PageInfo<ServiceVo> listPage(SearchVo searchVo){
		if (Objects.isNull(searchVo)) {
			return null;
		}
		//1. 我需要处理的工单		
		//2. 我处理过的工单
		/*Map<String, Object> map = new HashMap<>();
		map.put("handleUserID", 0);
		nsSossServicerecordMapper.selectServiceRecordList(map);*/		
		//3. 企业工单数据
		PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
		List<NsSossService> list = nsSossServiceMapper.listResultBySearch(searchVo);
		List<ServiceVo> voList = new ArrayList<>();
		ServiceVo serviceVo = null;
		for (NsSossService service : list) {
			serviceVo = new ServiceVo();
			BeanUtils.copyProperties(service, serviceVo);
			voList.add(serviceVo);
		}
		
        PageInfo<ServiceVo> pageInfo = new PageInfo<>(voList);
        return pageInfo;
	}
	
	/**
	 * 获取工单详情
	 * @param id 主键id
	 * @return
	 */
	public ServiceVo detail(Long id){
		if(Objects.isNull(id)) {
			return null;
		}
		ServiceVo vo = new ServiceVo();
		NsSossService nsSossService = nsSossServiceMapper.selectById(id);
		//如果查询出了数据，将数据拷贝到vo中
		if(!Objects.isNull(nsSossService)){
			BeanUtils.copyProperties(nsSossService, vo);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("serviceId", id);
		List<NsSossServiceRecord> servicerecordList = nsSossServicerecordMapper.selectServiceRecordList(map);
		vo.setServiceRecordList(servicerecordList);
		 
	    return vo;
	}
	
	/**
	 * 编辑工单详情
	 * @return boolean 编辑成功与否
	 */
	public boolean edit(ServiceVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsSossService nsSossService = new NsSossService();
		nsSossService.setId(vo.getId());
		nsSossService.setServiceType(vo.getServiceType());
		nsSossService.setStatus(vo.getStatus());
		if (!StringUtils.isEmpty(vo.getServicePriority())) {
			nsSossService.setServicePriority(vo.getServicePriority());
			nsSossService.setRemark(vo.getRemark());
			nsSossService.setExpectDate(vo.getExpectDate());
			nsSossService.setExpectHandleUserID(vo.getExpectHandleUserID());
			nsSossService.setExpectHandleUserName(vo.getExpectHandleUserName());
		}
		nsSossService.setImageCode(null);		 
		int countnsSossService = nsSossServiceMapper.updateById(nsSossService);		
		if(countnsSossService == 0 ) {
			return false;
		}
		//插入操作记录数据
		NsSossServiceRecord servicerecord = new NsSossServiceRecord();
		servicerecord.setServiceId(vo.getId());
		servicerecord.setOldStatus(vo.getOldStatus());
		servicerecord.setStatus(vo.getStatus());
		servicerecord.setHandleContent(vo.getContent());
		servicerecord.setHandlePhone(vo.getHandleUserPhone());
		servicerecord.setHandleUsername(vo.getCreateUserName());
		servicerecord.setImgCode(vo.getImageCode()); //业主反馈时，可能会补充图片
		servicerecord.setRemark(vo.getRemark());
		servicerecord.setUserType(vo.getUserType());
		int countnsSossServicerecord = nsSossServicerecordMapper.insert(servicerecord);
		if(countnsSossServicerecord == 0 ) {
			return false;
		}
	    return true;
	}
	
	/**
	 * 新增工单
	 * @return boolean 新增成功与否
	 */
	public boolean add(ServiceVo vo) {
		if(Objects.isNull(vo)) {
			return false;
		}
		NsSossService nsSossService = new NsSossService();
		BeanUtils.copyProperties(vo, nsSossService);
		nsSossService.setCode(Constants.GD+DateUtils.getNo(2));
		int countnsSossService = nsSossServiceMapper.insert(nsSossService);
		
	    return countnsSossService > 0;
	}
	
	/**
	 * 根据主键删除工单
	 * @param id 主键id
	 * @return
	 */
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		Map<String, Object> map = new HashMap<>(); 
		map.put("id", id);
		map.put("isDelete", 1);
		int res = nsSossServiceMapper.updateDelete(map);
	    return res > 0;
	}
	
	/**
	 * 根据主键批量删除工单
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		Map<String, Object> map = new HashMap<>(); 
		map.put("idList", ids);
		map.put("isDelete", 1);
		int res = nsSossServiceMapper.updateDelete(map);
		 
	    return res > 0;
	}

	@Override
	public Map<String, Integer> getServiceCountInfo(Long enterpriseId, Long userId) {
		if (enterpriseId == null || enterpriseId <= 0) {
			throw new NullPointerException("enterpriseId is null");
		}
		Map<String, Integer> tempMap = null;
		Map<String, Object> paraMap = new HashMap<>(4);
		paraMap.put("enterpriseId", enterpriseId);
		paraMap.put("createUserId", userId);
		List<Map<String, Object>> mapList = nsSossServiceMapper.selectStatisticalCount(paraMap);
		if (!CollectionUtils.isEmpty(mapList)) {
			tempMap = new HashMap<>();
			for (Map<String, Object> map : mapList) {
				tempMap.put(String.valueOf(map.get("status")), ((Long) map.get("count")).intValue());
			}			 
		}
		
		return tempMap;
	}
}
