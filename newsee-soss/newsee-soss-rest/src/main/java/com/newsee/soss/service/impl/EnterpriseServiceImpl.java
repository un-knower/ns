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

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.vo.ProvinceCityArea;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.soss.dao.NsSossEnterpriseMapper;
import com.newsee.soss.service.IEnterpriseService;
import com.newsee.soss.service.remote.ISystemRemoteService;
import com.newsee.soss.vo.EnterpriseVo;
import com.newsee.system.entity.NsSystemArea;
import com.newsee.system.vo.NsSystemUserVo;

@Service
public class EnterpriseServiceImpl implements IEnterpriseService {
	
    @Autowired
    private NsSossEnterpriseMapper nsSossEnterpriseMapper;
    
    @Autowired
    private ISystemRemoteService systemRemoteService;
    
    @Autowired
    private RedisUtil redisUtil;
    
	@Override
	public NsSossEnterprise getEnterpriseInfo(Long enterpriseId) throws Exception {
		NsSossEnterprise enterprise = nsSossEnterpriseMapper.selectById(enterpriseId);
		return enterprise;
	}

	/**
	 * 获取企业列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	public PageInfo<EnterpriseVo> listPage(SearchVo searchVo){
		if (Objects.isNull(searchVo)) {
			return null;
		}
		List<EnterpriseVo> list = null;
		//查询数据
		PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
		List<NsSossEnterprise> entityList = nsSossEnterpriseMapper.listResultBySearch(searchVo);
		if (!CollectionUtils.isEmpty(entityList)) {
			//提取省市区code
			List<String> areaCodeList = new ArrayList<>();
			for (NsSossEnterprise entity : entityList) {
				areaCodeList.add(entity.getProvince());
				areaCodeList.add(entity.getCity());
				areaCodeList.add(entity.getDistrict());
				areaCodeList.add(entity.getStreet());
			}
			//拼接省市区
			List<NsSystemArea> areaList = systemRemoteService.getAreaList(String.join(",", areaCodeList));
			if (!CollectionUtils.isEmpty(areaList)) {
				for (NsSossEnterprise entity : entityList) {
					for (NsSystemArea area : areaList) {
						if(area.getAreaCode().equals(entity.getProvince()))
							entity.param_address = entity.param_address.replaceAll("P#", area.getAreaName());
						if(area.getAreaCode().equals(entity.getCity()))
							entity.param_address = entity.param_address.replaceAll("C#", area.getAreaName());
						if(area.getAreaCode().equals(entity.getDistrict()))
							entity.param_address = entity.param_address.replaceAll("D#", area.getAreaName());
						if(area.getAreaCode().equals(entity.getStreet()))
							entity.param_address = entity.param_address.replaceAll("S#", area.getAreaName());												
					}	
				}
			}
			
			//提取企业ID
			List<NsSystemUser> userList = new ArrayList<>(entityList.size());
			list = new ArrayList<>(entityList.size());
			NsSystemUser user = null;
			EnterpriseVo enterpriseVo = null;
			for (NsSossEnterprise entity : entityList) {
				enterpriseVo = new EnterpriseVo();
				BeanUtils.copyProperties(entity, enterpriseVo);
				enterpriseVo.setAddress(entity.param_address.replaceAll("S#", " ") + entity.getAddress());
				list.add(enterpriseVo);
				user = new NsSystemUser();
				user.setUserId(entity.getCreateUserId());
				user.setUserName(entity.getCreateUserName());
				user.setEnterpriseId(entity.getEnterpriseId());
				userList.add(user);
			}
			//获取员工数
			RestResult<List<NsSystemUser>> tempRes = systemRemoteService.getEnterpriseUserCount(JSON.toJSONString(userList));
			if (ResultCodeEnum.SUCCESS.CODE.equals(tempRes.getResultCode())) {
				//合并数据
				List<NsSystemUser> users = tempRes.getResultData();
				for (EnterpriseVo vo : list) {
					for (NsSystemUser us : users) {
						if (vo.getEnterpriseId().equals(us.getEnterpriseId())) {
							vo.setEnterpriseUserCount(us.getUserCount());
							vo.setRegisterUserPhone(us.getUserAccount());
							break;
						}
					}					
				}
				tempRes = null;
			}
		}
		
        PageInfo<EnterpriseVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
	}
	
	/**
	 * 获取企业详情
	 * @param id 主键id
	 * @return
	 */
	public EnterpriseVo detail(Long id){
		if(Objects.isNull(id)) {
			return null;
		}
		EnterpriseVo vo = new EnterpriseVo();
		NsSossEnterprise nsSossEnterprise = nsSossEnterpriseMapper.selectById(id);
		//如果查询出了数据，将数据拷贝到vo中
		if(!Objects.isNull(nsSossEnterprise)){
			BeanUtils.copyProperties(nsSossEnterprise, vo);
			NsSystemUser user = systemRemoteService.detailUserRemote(nsSossEnterprise.getCreateUserId());
			if (!Objects.isNull(user)) {
				vo.setRegisterUserPhone(user.getUserAccount());				 
			}			 
		}
		ProvinceCityArea pca = new ProvinceCityArea(nsSossEnterprise.getProvince(), nsSossEnterprise.getCity(), nsSossEnterprise.getDistrict(), nsSossEnterprise.getStreet());
		vo.setProvinceCityArea(pca);
	    return vo;
	}
	
	/**
	 * 编辑企业详情
	 * @return boolean 编辑成功与否
	 */
	public boolean edit(EnterpriseVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsSossEnterprise nsSossEnterprise = new NsSossEnterprise();
		BeanUtils.copyProperties(vo, nsSossEnterprise);
		ProvinceCityArea pca = vo.getProvinceCityArea();
		if (!Objects.isNull(pca)) {
			nsSossEnterprise.setProvince(pca.getProvince());
			nsSossEnterprise.setCity(pca.getCity());
			nsSossEnterprise.setDistrict(pca.getDistrict());
			nsSossEnterprise.setStreet(pca.getStreet());
			nsSossEnterprise.setEnterpriseId(vo.getEnterpriseId());
		}
		int countnsSossEnterprise = nsSossEnterpriseMapper.updateById(nsSossEnterprise);
		if(countnsSossEnterprise == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 新增企业
	 * @return boolean 新增成功与否
	 */
	public boolean add(EnterpriseVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsSossEnterprise nsSossEnterprise = new NsSossEnterprise();
		BeanUtils.copyProperties(vo, nsSossEnterprise);
		ProvinceCityArea pca = vo.getProvinceCityArea();
		if (!Objects.isNull(pca)) {
			nsSossEnterprise.setProvince(pca.getProvince());
			nsSossEnterprise.setCity(pca.getCity());
			nsSossEnterprise.setDistrict(pca.getDistrict());
			nsSossEnterprise.setStreet(pca.getStreet());
		}
		int countnsSossEnterprise = nsSossEnterpriseMapper.insert(nsSossEnterprise);
		if(countnsSossEnterprise == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键删除企业
	 * @param id 主键id
	 * @return
	 */
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		int countnsSossEnterprise = nsSossEnterpriseMapper.deleteById(id);
		if(countnsSossEnterprise == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键批量删除企业
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		int countnsSossEnterprise = nsSossEnterpriseMapper.deleteBatch(ids);
		if(countnsSossEnterprise == 0 ){
			return false;
		}
	    return true;
	}

	@Override
	public Long registerEnterpriseInfo(EnterpriseVo vo) throws Exception { 
		//保存企业表
		NsSossEnterprise enterprise = new NsSossEnterprise();
		BeanUtils.copyProperties(vo, enterprise);
		enterprise.setCreateUserName(vo.getCreateUserName());
		enterprise.setUpdateUserName(vo.getCreateUserName());
		nsSossEnterpriseMapper.insert(enterprise);
		return enterprise.getEnterpriseId();
	}

	@Override
	public List<NsSossEnterprise> findEnterpriseInfoList(List<Long> enterpriseIds) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("enterpriseIdList", enterpriseIds);
		List<NsSossEnterprise> enterpriseList = nsSossEnterpriseMapper.selectEnterpriseList(map);
		return enterpriseList;
	}

	@Override
	public List<NsSossEnterprise> findEnterpriseInfoListByUserIds(List<Long> userIds) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("createUserIdList", userIds);
		List<NsSossEnterprise> enterpriseList = nsSossEnterpriseMapper.selectEnterpriseList(map);
		return enterpriseList;
	}
	
	
	
}
