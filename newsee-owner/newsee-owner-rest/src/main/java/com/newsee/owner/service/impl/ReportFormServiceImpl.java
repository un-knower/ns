package com.newsee.owner.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.newsee.common.constant.Constants;
import com.newsee.common.enums.HouseTypeEnum;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.database.annotation.WriteDataSource;
import com.newsee.owner.dao.OwnerHouseBaseInfoMapper;
import com.newsee.owner.dao.OwnerHouseCarportExtendInfoMapper;
import com.newsee.owner.dao.OwnerHouseCarportInfoMapper;
import com.newsee.owner.dao.OwnerHouseHouseExtendInfoMapper;
import com.newsee.owner.dao.OwnerHouseHouseInfoMapper;
import com.newsee.owner.dao.OwnerHousePublicAreaInfoMapper;
import com.newsee.owner.dao.OwnerHouseResultMapper;
import com.newsee.owner.entity.HouseListEntity;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import com.newsee.owner.entity.OwnerHouseCarportExtendInfo;
import com.newsee.owner.entity.OwnerHouseCarportInfo;
import com.newsee.owner.entity.OwnerHouseHouseExtendInfo;
import com.newsee.owner.entity.OwnerHouseHouseInfo;
import com.newsee.owner.entity.OwnerHousePublicAreaInfo;
import com.newsee.owner.service.IReportFormService;
import com.newsee.owner.vo.ReportFormVo;

@Service
public class ReportFormServiceImpl implements IReportFormService{
	
	@Autowired
	private OwnerHouseBaseInfoMapper ownerHouseBaseInfoMapper;	
	@Autowired
	private OwnerHouseHouseInfoMapper ownerHouseHouseInfoMapper;	
	@Autowired
	private OwnerHouseHouseExtendInfoMapper ownerHouseHouseExtendInfoMapper;
	@Autowired
	private OwnerHouseCarportInfoMapper ownerHouseCarportInfoMapper;
	@Autowired
	private OwnerHouseCarportExtendInfoMapper ownerHouseCarportExtendInfoMapper;
	@Autowired
	private OwnerHousePublicAreaInfoMapper ownerHousePublicAreaInfoMapper;	
	@Autowired
	private OwnerHouseResultMapper ownerHouseResultMapper;

	@WriteDataSource
	@Override
	public Map<String, Object> findBaseHouseInfo(ReportFormVo vo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enterpriseId", vo.getEnterpriseId());
	    map.put("endTime", vo.getEndTime());
        List<HouseListEntity> houseList = new ArrayList<>();
        if (CommonUtils.isObjectEmpty(vo.getHouseId())) {
            //根目录查询
            map.put("path", Constants.SEPARATOR_PATH);
            houseList = ownerHouseResultMapper.listHouseByPath(map);
            vo.setHouseType("0");
        }else {
            OwnerHouseBaseInfo info = ownerHouseBaseInfoMapper.selectByPrimaryKey(vo.getHouseId());
            if (info == null) {
                BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, ResultCodeEnum.DATA_NOT_EXIST.DESC);
            }
            vo.setHouseType(info.getHouseType());
            HouseTypeEnum houseTypeEnum = HouseTypeEnum.getInstance(info.getHouseType());
            switch (houseTypeEnum) {
            case AREA:
            case PRECINCT:
            case CLUSTER:
            case BUILDING:
            case UNIT:
            case GARAGE:
                map.put("path", info.getPath());
                houseList = ownerHouseResultMapper.listHouseByPath(map);
                break;
            case ROOM:
            case CARPORT:
            case PUBLICAREA:
                HouseListEntity baseInfo =  ownerHouseResultMapper.selectByPrimaryKey(vo.getHouseId());
                houseList.add(baseInfo);
                break;
            default:
                break;
            }
        }
//		map.put("organizationId", vo.getOrganizationId());
		map.put("houseId", vo.getHouseId());
		map.put("houseIdList", vo.getHouseIdList());
		map.put("houseType", vo.getHouseType());
		//根据项目ID，获取项目节点path，项目名
//		map.put("houseName", vo.getHouseName());
//		map.put("housePath", vo.getPath());
//		map.put("houseTypeList", vo.getHouseTypeList());
//		map.put("houseStage", vo.getStage());
//		map.put("houseStageGt", vo.getStageGt());
//		map.put("houseRentStage", vo.getRentStage());
//		map.put("houseDecorateStage", vo.getDecorateStage());
//		map.put("isBlockUp", vo.getIsBlockUp());
		List<OwnerHouseBaseInfo> list = ownerHouseBaseInfoMapper.selectHouseBaseInfoList(map);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("precinct", list);
		resultMap.put("house", houseList);
		return resultMap;
	}
	
	/**
	 * 合并房产信息
	 * @param houseBaseList
	 * @return
	 * @throws Exception
	 */
	@WriteDataSource
	@Override
	public List<ReportFormVo> getHouseInfoMerge(List<OwnerHouseBaseInfo> houseBaseList, ReportFormVo reportVo) throws Exception {
		if (CollectionUtils.isEmpty(houseBaseList))  {
			return null;
		}
		List<ReportFormVo> reportList = new ArrayList<ReportFormVo>(houseBaseList.size());
		ReportFormVo vo = null;
		for (OwnerHouseBaseInfo baseInfo : houseBaseList) { //基础房产数据
			vo = new ReportFormVo();
			BeanUtils.copyProperties(baseInfo, vo);
			reportList.add(vo);
		}
		List<Long> houseIdList = houseBaseList.stream().map(OwnerHouseBaseInfo :: getHouseId).collect(Collectors.toList());
		List<OwnerHouseHouseInfo> hhList = ownerHouseHouseInfoMapper.listHouseInfoListByHouseId(houseIdList);
		if (!CollectionUtils.isEmpty(hhList)) {
			for (OwnerHouseHouseInfo houseInfo : hhList) {
				for (ReportFormVo form : reportList) {
					if (form.getHouseId().compareTo(houseInfo.getHouseId()) == 1) {
						form.setRoomTypeId(houseInfo.getRoomTypeId()); //房产类型
						form.setChargingArea(houseInfo.getChargingArea()); //计费面积
						form.setBuildingArea(houseInfo.getBuildingArea()); //建筑面积
						form.setAssistChargingArea(houseInfo.getAssistChargingArea()); //辅助计费面积
						break;
					}
				}
			}
		}
		List<OwnerHouseHouseExtendInfo> hhExtendList = ownerHouseHouseExtendInfoMapper.listHouseExtendInfoListByHouseId(houseIdList);
		if (!CollectionUtils.isEmpty(hhExtendList)) {
			for (OwnerHouseHouseExtendInfo extend : hhExtendList) {
				for (ReportFormVo form : reportList) {
					if (form.getHouseId().compareTo(extend.getHouseId()) == 1) {
						form.setInsideArea(extend.getInsideArea()); //套内面积
						form.setPoolArea(extend.getPoolArea()); //公摊面积
						form.setGardenArea(extend.getGardenArea()); //花园面积
						form.setBasementArea(extend.getBasementArea()); //地下室面积
						form.setGiftArea(extend.getGiftArea()); //赠送面积
						form.setRoomPropertyId(extend.getRoomPropertyId()); //房产性质ID
						form.setRoomHouseType(extend.getRoomHouseType()); //房产户型ID
						form.setTakeOverTime(extend.getTakeOverTime()); //收房日期
						break;
					}
				}
			}
		}
		
		return reportList;
	}
	
	/**
	 * 合并公共区域信息
	 * @param houseBaseList
	 * @return
	 * @throws Exception
	 */
	@WriteDataSource
	@Override
	public List<ReportFormVo> getPublicAreaInfoMerge(List<OwnerHouseBaseInfo> houseBaseList, ReportFormVo reportVo) throws Exception {
		if (CollectionUtils.isEmpty(houseBaseList))  {
			return null;
		}
		List<ReportFormVo> reportList = new ArrayList<ReportFormVo>(houseBaseList.size());
		ReportFormVo vo = null;
		for (OwnerHouseBaseInfo baseInfo : houseBaseList) { //基础房产数据
			vo = new ReportFormVo();
			BeanUtils.copyProperties(baseInfo, vo);
			reportList.add(vo);
		}
		List<Long> houseIdList = houseBaseList.stream().map(OwnerHouseBaseInfo :: getHouseId).collect(Collectors.toList());
		List<OwnerHousePublicAreaInfo> publicList = ownerHousePublicAreaInfoMapper.selectPublicAreaInfoList(houseIdList);
		if (!CollectionUtils.isEmpty(publicList)) {
			for (OwnerHousePublicAreaInfo pub : publicList) {
				for (ReportFormVo form : reportList) {
					if (form.getHouseId().compareTo(pub.getHouseId()) == 1) {
						form.setBuildingArea(pub.getBuildingArea()); //建筑面积
						break;
					}
				}
			}
		}
		
		return reportList;
	}
	
	/**
	 * 合并车位信息
	 * @param houseBaseList
	 * @return
	 * @throws Exception
	 */
	@WriteDataSource
	@Override
	public List<ReportFormVo> getCarportInfoMerge(List<OwnerHouseBaseInfo> houseBaseList, ReportFormVo reportVo) throws Exception {
		if (CollectionUtils.isEmpty(houseBaseList))  {
			return null;
		}
		List<ReportFormVo> reportList = new ArrayList<ReportFormVo>(houseBaseList.size());
		ReportFormVo vo = null;
		for (OwnerHouseBaseInfo baseInfo : houseBaseList) { //基础房产数据
			vo = new ReportFormVo();
			BeanUtils.copyProperties(baseInfo, vo);
			reportList.add(vo);
		}
		List<Long> houseIdList = houseBaseList.stream().map(OwnerHouseBaseInfo :: getHouseId).collect(Collectors.toList());
		List<OwnerHouseCarportInfo> hcarList = ownerHouseCarportInfoMapper.selectCarportInfoList(houseIdList);
		if (!CollectionUtils.isEmpty(hcarList)) {
			for (OwnerHouseCarportInfo car : hcarList) {
				for (ReportFormVo form : reportList) {
					if (form.getHouseId().compareTo(car.getHouseId()) == 1) {
						form.setRoomTypeId(car.getCarportTypeId()); //车位类型
						form.setChargingArea(car.getChargingArea()); //计费面积
						form.setBuildingArea(car.getBuildingArea()); //建筑面积
						form.setAssistChargingArea(car.getAssistChargingArea()); //辅助计费面积
						break;
					}
				}
			}
		}
		List<OwnerHouseCarportExtendInfo> hcarExtendList = ownerHouseCarportExtendInfoMapper.selectCarportExtendInfoList(houseIdList);
		if (!CollectionUtils.isEmpty(hcarExtendList)) {
			for (OwnerHouseCarportExtendInfo car : hcarExtendList) {
				for (ReportFormVo form : reportList) {
					if (form.getHouseId().compareTo(car.getHouseId()) == 1) {
						form.setInsideArea(car.getInsideArea()); //套内面积
						form.setPoolArea(car.getPoolArea()); //公摊面积
						form.setTakeOverTime(car.getTakeOverTime()); //收车位日期
						break;
					}
				}
			}
		}
		
		return reportList;
	}

	@WriteDataSource
	@Override
	public List<HouseListEntity> findHouseResultInfo(List<Long> houseIdList, ReportFormVo vo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseIdList", houseIdList);
		map.put("organizationId", vo.getOrganizationId());
		List<HouseListEntity> list = null;
		if (!StringUtils.isEmpty(vo.getDecorateStage())) { //装修
			map.put("endTime", vo.getEndTime());
			map.put("isUnion", 1);
			list = ownerHouseResultMapper.selectHouseResultSegmentInfoList(map);
		} else if (!StringUtils.isEmpty(vo.getRentStage())) { //出租
			map.put("endTime", vo.getEndTime());
			list = ownerHouseResultMapper.selectHouseResultSegmentInfoList(map);
		} else {
			map.put("handleTime", vo.getHandleTime());
			map.put("houseOperateType", vo.getHouseType());
			map.put("houseStageGt", vo.getStageGt());
			map.put("blockUpTime", vo.getBlockUpTime());
			list = ownerHouseResultMapper.selectHouseResultInfoList(map);
		}
		if (!CollectionUtils.isEmpty(list)) {
			Map<Long, List<HouseListEntity>> tempMap = list.stream().collect(Collectors.groupingBy(HouseListEntity :: getHouseId));
			list.clear();
			for (Iterator<List<HouseListEntity>> iterator = tempMap.values().iterator(); iterator.hasNext();) {
				List<HouseListEntity> entry = iterator.next();
				list.add(entry.get(0));
			}
		}
		if (!CollectionUtils.isEmpty(list)) {
			if (!StringUtils.isEmpty(vo.getStage())) {
				list = list.stream().filter(house -> vo.getStage().equals(house.getHouseStage())).collect(Collectors.toList());
			} else if (!StringUtils.isEmpty(vo.getIsBlockUp())) {
				list = list.stream().filter(house -> vo.getIsBlockUp() == house.getIsBlockUp()).collect(Collectors.toList());
			}
		}
		
		return list;
	}
	
}
