package com.newsee.owner.service;

import java.util.List;
import java.util.Map;

import com.newsee.owner.entity.HouseListEntity;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import com.newsee.owner.vo.ReportFormVo;

public interface IReportFormService {
	
	/**
	 * 获取房产基本信息
	 * @throws Exception
	 */
	Map<String, Object> findBaseHouseInfo(ReportFormVo vo) throws Exception;
	
	/**
	 * 房产信息合并
	 * @param baseList
	 * @return
	 * @throws Exception
	 */
	List<ReportFormVo> getHouseInfoMerge(List<OwnerHouseBaseInfo> houseBaseList, ReportFormVo vo) throws Exception;
	/**
	 * 合并公共区域信息
	 * @param houseBaseList
	 * @return
	 * @throws Exception
	 */
	List<ReportFormVo> getPublicAreaInfoMerge(List<OwnerHouseBaseInfo> houseBaseList, ReportFormVo vo) throws Exception;
	/**
	 * 合并车位信息
	 * @param houseBaseList
	 * @return
	 * @throws Exception
	 */
	List<ReportFormVo> getCarportInfoMerge(List<OwnerHouseBaseInfo> houseBaseList, ReportFormVo vo) throws Exception;
	
	/**
	 * 
	 * @param houseIdList 房产ID
	 * @param orgId  组织ID
	 * @throws Exception
	 */
	List<HouseListEntity> findHouseResultInfo(List<Long> houseIdList, ReportFormVo vo) throws Exception;
	
}
