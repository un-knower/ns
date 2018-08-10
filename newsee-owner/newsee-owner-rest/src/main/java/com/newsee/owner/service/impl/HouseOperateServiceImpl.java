package com.newsee.owner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.Constants;
import com.newsee.common.entity.FilterEntity;
import com.newsee.common.enums.HouseDecorateStageEnum;
import com.newsee.common.enums.HouseOperateTypeEnum;
import com.newsee.common.enums.HouseRentStageEnum;
import com.newsee.common.enums.HouseStageEnum;
import com.newsee.common.enums.HouseTypeEnum;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;
import com.newsee.owner.constant.OwnerConstants;
import com.newsee.owner.dao.OwnerCustomerBaseInfoMapper;
import com.newsee.owner.dao.OwnerCustomerFamilyInfoMapper;
import com.newsee.owner.dao.OwnerCustomerInfoMapper;
import com.newsee.owner.dao.OwnerCustomerMainHouseMapper;
import com.newsee.owner.dao.OwnerCustomerResultMapper;
import com.newsee.owner.dao.OwnerHouseBaseInfoMapper;
import com.newsee.owner.dao.OwnerHouseBuildingExtendInfoMapper;
import com.newsee.owner.dao.OwnerHouseClusterInfoMapper;
import com.newsee.owner.dao.OwnerHouseGarageInfoMapper;
import com.newsee.owner.dao.OwnerHousePrecinctExtendInfoMapper;
import com.newsee.owner.dao.OwnerHousePropertyOwnerMapper;
import com.newsee.owner.dao.OwnerHousePublicAreaInfoMapper;
import com.newsee.owner.dao.OwnerHouseRelationshipMapper;
import com.newsee.owner.dao.OwnerHouseResultMapper;
import com.newsee.owner.dao.OwnerHouseStageDetailMapper;
import com.newsee.owner.dao.OwnerHouseStageExtendInfoDecorateMapper;
import com.newsee.owner.dao.OwnerHouseStageExtendInfoEmptyMapper;
import com.newsee.owner.dao.OwnerHouseStageExtendInfoRentMapper;
import com.newsee.owner.entity.HouseListEntity;
import com.newsee.owner.entity.OwnerCustomerBaseInfo;
import com.newsee.owner.entity.OwnerCustomerFamilyInfo;
import com.newsee.owner.entity.OwnerCustomerInfo;
import com.newsee.owner.entity.OwnerCustomerMainHouse;
import com.newsee.owner.entity.OwnerCustomerResult;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import com.newsee.owner.entity.OwnerHouseBuildingExtendInfo;
import com.newsee.owner.entity.OwnerHouseClusterInfo;
import com.newsee.owner.entity.OwnerHouseGarageInfo;
import com.newsee.owner.entity.OwnerHousePrecinctExtendInfo;
import com.newsee.owner.entity.OwnerHousePropertyOwner;
import com.newsee.owner.entity.OwnerHousePublicAreaInfo;
import com.newsee.owner.entity.OwnerHouseRelationship;
import com.newsee.owner.entity.OwnerHouseStageDetail;
import com.newsee.owner.entity.OwnerHouseStageExtendInfoDecorate;
import com.newsee.owner.entity.OwnerHouseStageExtendInfoEmpty;
import com.newsee.owner.entity.OwnerHouseStageExtendInfoRent;
import com.newsee.owner.enums.CustomerCallEnum;
import com.newsee.owner.enums.CustomerRelationEnum;
import com.newsee.owner.service.IHouseOperateService;
import com.newsee.owner.utils.OwnerUtils;
import com.newsee.owner.vo.CustomerFamilyVo;
import com.newsee.owner.vo.CustomerVo;
import com.newsee.owner.vo.HouseListVo;
import com.newsee.owner.vo.HouseOperateSalesVo;
import com.newsee.owner.vo.OwnerHouseRelationshipVo;
import com.newsee.system.vo.NsCoreDictionaryVo;

@Service
public class HouseOperateServiceImpl implements IHouseOperateService {

    @Autowired
    private OwnerHouseStageDetailMapper houseStageDetailMapper;

    @Autowired
    private OwnerHouseRelationshipMapper houseRelationshipMapper;

    @Autowired
    private OwnerHousePropertyOwnerMapper housePropertyOwnerMapper;

    @Autowired
    private OwnerHouseBaseInfoMapper houseBaseInfoMapper;

    @Autowired
    private OwnerHousePublicAreaInfoMapper ownerHousePublicAreaInfoMapper;

    @Autowired
    private OwnerHousePrecinctExtendInfoMapper ownerHousePrecinctExtendInfoMapper;

    @Autowired
    private OwnerHouseClusterInfoMapper ownerHouseClusterInfoMapper;

    @Autowired
    private OwnerHouseBuildingExtendInfoMapper ownerHouseBuildingExtendInfoMapper;

    @Autowired
    private OwnerHouseGarageInfoMapper ownerHouseGarageInfoMapper;

    @Autowired
    private OwnerCustomerBaseInfoMapper customerBaseInfoMapper;

    @Autowired
    private OwnerCustomerInfoMapper customerInfoMapper;

    @Autowired
    private OwnerHouseStageExtendInfoDecorateMapper decorateMapper;

    @Autowired
    private OwnerHouseStageExtendInfoRentMapper rentMapper;

    @Autowired
    private OwnerHouseStageExtendInfoEmptyMapper emptyMapper;

    @Autowired
    private OwnerCustomerFamilyInfoMapper familyMapper;

    @Autowired
    private OwnerHouseResultMapper houseResultMapper;

    @Autowired
    private OwnerCustomerBaseInfoMapper ownerCustomerBaseInfoMapper;

    @Autowired
    private OwnerCustomerResultMapper customerResultMapper;

    @Autowired
    private OwnerCustomerMainHouseMapper mainHouseMapper;
//        1、更新原业主信息(非必填)
//        7、判断原业主是否还有关联房产，如果没有更新原业主客户性质为无房客户
//        5、保存房产操作记录---原业主搬出(非必填)
//        6、删除原业主的客户房产关系
//        8、保存房产操作记录---转让
//        9、保存新业主的客户房产关系
//        4、更新新业主当前项目是否为主房产
//        9、保存共有产权人的客户房产关系
//        10、保存房产操作记录---收房(非必填)
//        11、保存房产操作记录---入住(非必填)

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long addSalse(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName, Map<String, NsCoreDictionaryVo> dicMap, boolean doTake) throws Exception {

        BizException.isNull(houseOperateSalesVo.getNewOwner(), "新业主不能为空");
        BizException.isNull(houseOperateSalesVo.getNewOwner().getOwnerId(), "新业主不能为空");
        BizException.isNull(houseOperateSalesVo.getSalesDate(), "售楼时间不能为空");

        OwnerHouseBaseInfo houseBaseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
        houseOperateSalesVo.setPrecinctId(houseBaseInfo.getPrecinctId());
        //验证房产当前是否有过房态操作
        OwnerHouseRelationship relationship = houseRelationshipMapper.loadOwnerRelationByHouseId(houseOperateSalesVo.getHouseId());
        if (relationship != null) {
            houseOperateSalesVo.setPreviousDetailId(relationship.getDetailId());
        }
        //新业主房产操作
        OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
        BeanUtils.copyProperties(houseOperateSalesVo, ownerHouseStageDetail);
        ownerHouseStageDetail.setHouseId(houseOperateSalesVo.getHouseId());
        ownerHouseStageDetail.setPreviousDetailId(houseOperateSalesVo.getPreviousDetailId());
        ownerHouseStageDetail.setHandleTime(houseOperateSalesVo.getSalesDate());
        ownerHouseStageDetail.setRemark(houseOperateSalesVo.getRemark());
        //保存操作前房态
        ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
        ownerHouseStageDetail.setRentStage(houseBaseInfo.getRentStage());
        ownerHouseStageDetail.setDecorateStage(houseBaseInfo.getDecorateStage());
        ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
        ownerHouseStageDetail.setCreateUserId(userId);
        ownerHouseStageDetail.setCreateUserName(userName);
        ownerHouseStageDetail.setUpdateUserId(userId);
        ownerHouseStageDetail.setUpdateUserName(userName);
        //新业主房产关系
        OwnerHouseRelationship houseRelationship = new OwnerHouseRelationship();
        BeanUtils.copyProperties(houseOperateSalesVo, houseRelationship);
        houseRelationship.setOwnerId(houseOperateSalesVo.getNewOwner().getOwnerId());
        houseRelationship.setIsCurrentRecord(Constants.TRUE);
        houseRelationship.setOwnerCategory(OwnerConstants.OWNER_CATEGORY_OWNER);
        houseRelationship.setIsDeleted(Constants.FALSE);
        houseRelationship.setCreateUserId(userId);
        houseRelationship.setCreateUserName(userName);
        houseRelationship.setUpdateUserId(userId);
        houseRelationship.setUpdateUserName(userName);

//        Long ownerId = houseRelationshipMapper.loadOwnerIdByHouseId(houseOperateSalesVo.getHouseId());
        String stageName = "";
        //判断是否为转让
        if (!HouseStageEnum.KONG_ZHI.getValue().equals(houseBaseInfo.getStage())) {
            doTake = true;
            //获取原业主
            Map<String, Object> oldRelationMap = new HashMap<>();
            oldRelationMap.put("houseId", houseOperateSalesVo.getHouseId());
            oldRelationMap.put("isCurrentRecord", Boolean.TRUE);
            oldRelationMap.put("isDeleted", Constants.FALSE);
            List<OwnerHouseRelationship> oldRelationships = houseRelationshipMapper.listOwnerByHouseId(oldRelationMap);
            if (!CollectionUtils.isEmpty(oldRelationships)) {
                List<Long> oldOwnerIdList = new ArrayList<>();
                Map<Byte, List<OwnerHouseRelationship>> oldMap = oldRelationships.stream().collect(Collectors.groupingBy(OwnerHouseRelationship::getOwnerCategory));
                //业主
                List<OwnerHouseRelationship> oldOwnerRelationList = oldMap.get(OwnerConstants.OWNER_CATEGORY_OWNER);
                if (oldOwnerRelationList != null) {
                    oldOwnerRelationList.forEach(relation -> {
                        oldOwnerIdList.add(relation.getOwnerId());
                    });
                    if (!CollectionUtils.isEmpty(oldOwnerIdList)) {
                        List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(oldOwnerIdList);

                        if (!CollectionUtils.isEmpty(ownerList)) {
                            houseOperateSalesVo.setOldOwner(ownerList.get(0));
                        }
                    }
                }
            }
            Long oldOwnerId = 0L;
            if (houseOperateSalesVo.getOldOwner() != null) {
                oldOwnerId = houseOperateSalesVo.getOldOwner().getOwnerId();
                ownerHouseStageDetail.setOldOwnerId(oldOwnerId);
            }
            //保存房产操作记录---转让
            //修改原业主信息
            OwnerCustomerInfo customerInfo = new OwnerCustomerInfo();
            BeanUtils.copyProperties(houseOperateSalesVo.getOldOwner(), customerInfo);
            customerInfo.setUpdateUserId(userId);
            customerInfo.setUpdateUserName(userName);
            customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
            // 同步客户结果表
            OwnerCustomerResult ownerCustomerResult = new OwnerCustomerResult();
            BeanUtils.copyProperties(houseOperateSalesVo.getOldOwner(), ownerCustomerResult);
            ownerCustomerResult.setUpdateUserId(userId);
            ownerCustomerResult.setUpdateUserName(userName);
            ownerCustomerResult.setUpdateTime(new Date());
            customerResultMapper.updateByPrimaryKeySelective(ownerCustomerResult);
            stageName = HouseOperateTypeEnum.ZHUAN_RANG.getTitle();
            ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
            ownerHouseStageDetail.setPreviousDetailId(houseOperateSalesVo.getDetailId());
            ownerHouseStageDetail.setOldOwnerId(oldOwnerId);
            //获取房产业主与共有产权人
            List<Long> ownerIdList = houseRelationshipMapper.listOwnerIdByHouseId(houseOperateSalesVo.getHouseId());

            //获取当前项目下的相关房产
            Map<String, Object> map = new HashMap<>();
            map.put("list", ownerIdList);
            map.put("precinctId", houseOperateSalesVo.getPrecinctId());
            List<OwnerHouseRelationshipVo> relationshipList = houseRelationshipMapper.listOwnerAllHouseRelationByList(map);
            List<OwnerHouseRelationshipVo> rentRelationshipList = houseRelationshipMapper.listRentAllHouseRelationByList(map);
            relationshipList.addAll(rentRelationshipList);
            if (!CollectionUtils.isEmpty(relationshipList)) {
                Map<Long, List<OwnerHouseRelationshipVo>> ownerMap = relationshipList.stream().collect(Collectors.groupingBy(OwnerHouseRelationshipVo::getOwnerId));

                //获取原业主及产权人在当前项目下的主房产
                List<OwnerCustomerMainHouse> mainHouseList = mainHouseMapper.listByOwnerHouse(map);

                Map<Long, List<OwnerCustomerMainHouse>> mainHouseMap = mainHouseList.stream().collect(Collectors.groupingBy(OwnerCustomerMainHouse::getOwnerId));
                for (Long ownerId : ownerIdList) {
                    //删除客户房产关系
//                    Map<String, Object> relationMap = new HashMap<>();
//                    relationMap.put("updateUserId", userId);
//                    relationMap.put("updateUserName", userName);
//                    relationMap.put("ownerId", ownerId);
//                    relationMap.put("houseId", houseOperateSalesVo.getHouseId());
//                    houseRelationshipMapper.deleteRelation(relationMap);

                    //判断原业主与产权人在拥有的房产中的客户性质，如果全不为业主或租户则改为住户
                    OwnerCustomerBaseInfo baseInfo = new OwnerCustomerBaseInfo();
                    BeanUtils.copyProperties(houseOperateSalesVo.getOldOwner(), baseInfo);
                    baseInfo.setUpdateUserId(userId);
                    baseInfo.setUpdateUserName(userName);
                    ownerCustomerResult = new OwnerCustomerResult();
                    ownerCustomerResult.setOwnerId(baseInfo.getOwnerId());
                    ownerCustomerResult.setUpdateUserId(userId);
                    ownerCustomerResult.setUpdateUserName(userName);
                    ownerCustomerResult.setUpdateTime(new Date());

                    List<OwnerHouseRelationshipVo> ownerHouseRelationshipVos = ownerMap.get(ownerId);
                    //去除当前的房产
                    ownerHouseRelationshipVos.removeIf(relation -> relation.getHouseId().equals(houseOperateSalesVo.getHouseId()));
                    if (CollectionUtils.isEmpty(ownerHouseRelationshipVos)) {
                        //是否为租户
                        List<Long> ownerIds = new ArrayList<>();
                        ownerIds.add(ownerId);
                        List<OwnerHouseStageExtendInfoRent> detailList = rentMapper.listAllCurrentRent(ownerIds);
                        if (!CollectionUtils.isEmpty(detailList)) {
                            baseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_RENT);
                        } else {
                            //是否为住户
                            List<Long> familyOwnerIdList = familyMapper.listAllOwnerByFamily(ownerId);
                            List<Long> familyRentIdList = familyMapper.listAllRentByFamily(ownerId);
                            if (!CollectionUtils.isEmpty(familyRentIdList)) {
                                detailList = rentMapper.listAllCurrentRent(familyRentIdList);
                            }
                            if (!CollectionUtils.isEmpty(familyOwnerIdList) ||
                                    !CollectionUtils.isEmpty(detailList)) {
                                baseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_HOUSEHOLD);
                            } else {
                                //如果原业主没有房产关系，则改变原业主性质为无房客户
                                baseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_NONE);
                            }
                        }
                    } else {
                        baseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_OWNER);
                    }
                    ownerCustomerBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
                    //同步客户结果表
//                    String ownerPropertyName = OwnerUtils.getDicName(dicMap.get("ownerPropertyDic"), baseInfo.getOwnerProperty());
//                    ownerCustomerResult.setOwnerPropertyName(ownerPropertyName);
                    ownerCustomerResult.setOwnerProperty(baseInfo.getOwnerProperty());
                    customerResultMapper.updateByPrimaryKeySelective(ownerCustomerResult);


                    List<OwnerCustomerMainHouse> ownerMainHouseList = mainHouseMap.get(ownerId);
                    //删除原业主与共有产权人的主房产
                    if (!CollectionUtils.isEmpty(ownerMainHouseList)) {
                        for (OwnerCustomerMainHouse ownerCustomerMainHouse : ownerMainHouseList) {
                            if (houseOperateSalesVo.getHouseId().equals(ownerCustomerMainHouse.getHouseId())) {
                                Map<String, Object> deteleMap = new HashMap<>();
                                List<Long> deteleList = new ArrayList<>();
                                deteleList.add(ownerId);
                                deteleMap.put("ownerIdList", deteleList);
                                deteleMap.put("houseId", houseOperateSalesVo.getHouseId());
                                mainHouseMapper.deleteMainHouse(deteleMap);
                            }
                        }
                    }

                    //去除当前的房产
//                    ownerMainHouseList.removeIf(relation -> relation.getHouseId().equals(houseOperateSalesVo.getHouseId()));
                    if (!CollectionUtils.isEmpty(ownerHouseRelationshipVos)) {
                        //保存原业主与共有产权人当前项目下剩余房产的第一个为主房产
                        OwnerCustomerMainHouse mainHouse = new OwnerCustomerMainHouse();
                        mainHouse.setOwnerId(ownerId);
                        mainHouse.setHouseId(ownerHouseRelationshipVos.get(0).getHouseId());
                        mainHouse.setPrecinctId(ownerHouseRelationshipVos.get(0).getPrecinctId());
                        mainHouseMapper.insertSelective(mainHouse);
                    }
                }
                //原业主进行搬出操作
                Map<String, Object> detailMap = new HashMap<>();
                detailMap.put("houseId", houseOperateSalesVo.getHouseId());
                List<String> list = new ArrayList<>();
                list.add(HouseOperateTypeEnum.RU_ZHU.getValue());
                detailMap.put("list", list);
                detailMap.put("isNowDetail", Constants.TRUE.toString());
                List<OwnerHouseStageDetail> detailList = houseStageDetailMapper.listStage(detailMap);
                if (!CollectionUtils.isEmpty(detailList) && houseOperateSalesVo.getApplyDate() != null) {
                    houseOperateSalesVo.setDetailId(houseOperateSalesVo.getPreviousDetailId());
                    Long detailId = addMoveOut(houseOperateSalesVo, userId, userName);
                    houseOperateSalesVo.setPreviousDetailId(detailId);
                }
            } else {
                BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "原业主已无房产可售");
            }
            //更新上一条售楼列表的当前记录为历史记录
            Map<String, Object> updateNowDetailMap = new HashMap<>();
            List<String> houseOperateTypeList = new ArrayList<>();
            houseOperateTypeList.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
            houseOperateTypeList.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
            houseOperateTypeList.add(HouseOperateTypeEnum.SHOU_FANG.getValue());
            updateNowDetailMap.put("houseId", houseOperateSalesVo.getHouseId());
            updateNowDetailMap.put("isNowDetail", Constants.FALSE.toString());
            updateNowDetailMap.put("updateUserId", userId);
            updateNowDetailMap.put("updateUserName", userName);
            updateNowDetailMap.put("houseOperateTypeList", houseOperateTypeList);
            houseStageDetailMapper.updateNowDetail(updateNowDetailMap);
            ownerHouseStageDetail.setIsNowDetail(Constants.TRUE.toString());
            houseOperateSalesVo.setHouseOperateType(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
        } else {
            stageName = HouseOperateTypeEnum.SHOU_LOU.getTitle();
            houseOperateSalesVo.setHouseOperateType(HouseOperateTypeEnum.SHOU_LOU.getValue());
            ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.SHOU_LOU.getValue());
            ownerHouseStageDetail.setIsNowDetail(Constants.TRUE.toString());
        }
        //保存新业主相关信息
        long index = houseStageDetailMapper.insertSelective(ownerHouseStageDetail);
        index = ownerHouseStageDetail.getDetailId();
        houseOperateSalesVo.setDetailId(ownerHouseStageDetail.getDetailId());
        if (index > 0) {
            //保存新业主的客户房产关系
            houseRelationship.setDetailId(ownerHouseStageDetail.getDetailId());
            houseRelationship.setOwnerProperty(Constants.OWNER_PROPERTY_OWNER);

            CustomerVo customerGetPrecinct = ownerCustomerBaseInfoMapper.loadCustomer(houseOperateSalesVo.getNewOwner().getOwnerId());
            OwnerCustomerBaseInfo ownerCustomerBaseInfo = new OwnerCustomerBaseInfo();
            BeanUtils.copyProperties(houseOperateSalesVo.getNewOwner(), ownerCustomerBaseInfo);
            ownerCustomerBaseInfo.setUpdateUserId(userId);
            ownerCustomerBaseInfo.setUpdateUserName(userName);
            ownerCustomerBaseInfo.setUpdateTime(new Date());
            if (!customerGetPrecinct.getPrecinctId().contains(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH)) {
                ownerCustomerBaseInfo.setPrecinctId(houseOperateSalesVo.getNewOwner().getPrecinctId() + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH);
            }
//            String ownerPropertyName = OwnerUtils.getDicName(dicMap.get("ownerPropertyDic"), houseRelationship.getOwnerProperty());
//            ownerCustomerResult.setOwnerPropertyName(ownerPropertyName);
            ownerCustomerBaseInfo.setOwnerProperty(houseRelationship.getOwnerProperty());
            ownerCustomerBaseInfoMapper.updateByPrimaryKeySelective(ownerCustomerBaseInfo);
            //同步客户结果表
            OwnerCustomerResult ownerCustomerResult = new OwnerCustomerResult();
            BeanUtils.copyProperties(houseOperateSalesVo.getNewOwner(), ownerCustomerResult);
            ownerCustomerResult.setUpdateUserId(userId);
            ownerCustomerResult.setUpdateUserName(userName);
            ownerCustomerResult.setUpdateTime(new Date());
            if (!customerGetPrecinct.getPrecinctId().contains(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH)) {
                ownerCustomerResult.setPrecinctId(houseOperateSalesVo.getNewOwner().getPrecinctId() + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH);
            }
//            String ownerPropertyName = OwnerUtils.getDicName(dicMap.get("ownerPropertyDic"), houseRelationship.getOwnerProperty());
//            ownerCustomerResult.setOwnerPropertyName(ownerPropertyName);
            ownerCustomerResult.setOwnerProperty(houseRelationship.getOwnerProperty());
            customerResultMapper.updateByPrimaryKeySelective(ownerCustomerResult);

            if (Constants.TRUE.equals(houseOperateSalesVo.getIsMainHouse())) {
                //保存主房产
                Map<String, Object> map = new HashMap<>();
                List<Long> ownerIdList = new ArrayList<>();
                ownerIdList.add(houseRelationship.getOwnerId());
                map.put("ownerIdList", ownerIdList);
                map.put("precinctId", houseOperateSalesVo.getPrecinctId());
                mainHouseMapper.deleteMainHouse(map);
                OwnerCustomerMainHouse mainHouse = new OwnerCustomerMainHouse();
                BeanUtils.copyProperties(houseRelationship, mainHouse);
                mainHouseMapper.insertSelective(mainHouse);
            }
            //修改当前记录为历史记录
            Map<String, Object> editRelationMap = new HashMap<>();
            editRelationMap.put("userId", userId);
            editRelationMap.put("preDetailId", houseOperateSalesVo.getPreviousDetailId());
            editRelationMap.put("houseId", houseOperateSalesVo.getHouseId());
            houseRelationshipMapper.editCurrentRecordFalse(editRelationMap);

            houseRelationshipMapper.insertSelective(houseRelationship);
            //保存产权人信息
            String propertyOwnerNames = "";
            if (!CollectionUtils.isEmpty(houseOperateSalesVo.getNewPropertyOwnerList())) {
                List<CustomerVo> propertyOwnerList = houseOperateSalesVo.getNewPropertyOwnerList();
                List<OwnerHousePropertyOwner> propertyOwners = new ArrayList<>();
                List<Long> propertyOwnerIdList = new ArrayList<>();
                List<OwnerHouseRelationship> propertyOwnerHouseRelationshipList = new ArrayList<>();
                List<OwnerCustomerMainHouse> mainHouseList = new ArrayList<>();
                Map<String, Object> map = new HashMap<>();
                List<Long> ownerIdList = new ArrayList<>();
                for (CustomerVo customerVo : propertyOwnerList) {
                    if (StringUtils.hasLength(propertyOwnerNames)) {
                        propertyOwnerNames = propertyOwnerNames + "," + customerVo.getOwnerName();
                    } else {
                        propertyOwnerNames = propertyOwnerNames + customerVo.getOwnerName();
                    }
                    OwnerHousePropertyOwner propertyOwner = new OwnerHousePropertyOwner();
                    propertyOwner.setHouseId(houseOperateSalesVo.getHouseId());
                    propertyOwner.setHouseName(houseOperateSalesVo.getHouseName());
                    propertyOwner.setOwnerId(customerVo.getOwnerId());
                    propertyOwner.setOwnerName(customerVo.getOwnerName());
                    propertyOwner.setCreateUserId(userId);
                    propertyOwner.setCreateUserName(userName);
                    propertyOwner.setUpdateUserId(userId);
                    propertyOwner.setUpdateUserName(userName);
                    propertyOwner.setIsDeleted(Constants.FALSE);
                    propertyOwners.add(propertyOwner);

                    propertyOwnerIdList.add(customerVo.getOwnerId());

                    OwnerHouseRelationship propertyOwnerHouseRelationship = new OwnerHouseRelationship();
                    propertyOwnerHouseRelationship.setOwnerId(customerVo.getOwnerId());
                    propertyOwnerHouseRelationship.setDetailId(ownerHouseStageDetail.getDetailId());
                    propertyOwnerHouseRelationship.setOwnerProperty(Constants.OWNER_PROPERTY_OWNER);
                    propertyOwnerHouseRelationship.setIsMainHouse(houseOperateSalesVo.getIsMainHouse());
                    propertyOwnerHouseRelationship.setPrecinctId(houseOperateSalesVo.getPrecinctId());
                    propertyOwnerHouseRelationship.setHouseId(houseOperateSalesVo.getHouseId());
                    propertyOwnerHouseRelationship.setOwnerCategory(OwnerConstants.OWNER_CATEGORY_PROPERTY);
                    propertyOwnerHouseRelationship.setIsCurrentRecord(Constants.TRUE);
                    propertyOwnerHouseRelationship.setIsDeleted(Constants.FALSE);
                    propertyOwnerHouseRelationship.setCreateUserId(userId);
                    propertyOwnerHouseRelationship.setCreateUserName(userName);
                    propertyOwnerHouseRelationship.setUpdateUserId(userId);
                    propertyOwnerHouseRelationship.setUpdateUserName(userName);

                    //判断每个产权人在该项目中的主房产 
                    if (Constants.TRUE.equals(houseOperateSalesVo.getIsMainHouse())) {
                        ownerIdList.add(houseRelationship.getOwnerId());
                        map.put("ownerIdList", ownerIdList);
                        map.put("precinctId", houseOperateSalesVo.getPrecinctId());
                        OwnerCustomerMainHouse mainHouse = new OwnerCustomerMainHouse();
                        BeanUtils.copyProperties(houseRelationship, mainHouse);
                        mainHouseList.add(mainHouse);
                    }
                    propertyOwnerHouseRelationshipList.add(propertyOwnerHouseRelationship);

                    ownerCustomerBaseInfo = new OwnerCustomerBaseInfo();
                    BeanUtils.copyProperties(customerVo, ownerCustomerBaseInfo);
                    ownerCustomerBaseInfo.setUpdateUserId(userId);
                    ownerCustomerBaseInfo.setUpdateUserName(userName);
                    ownerCustomerBaseInfo.setUpdateTime(new Date());
                    if (!customerVo.getPrecinctId().contains(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH)) {
                        ownerCustomerBaseInfo.setPrecinctId(customerVo.getPrecinctId() + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH);
                    }
//                    String ownerPropertyName = OwnerUtils.getDicName(dicMap.get("ownerPropertyDic"), houseRelationship.getOwnerProperty());
//                    ownerCustomerResult.setOwnerPropertyName(ownerPropertyName);
                    ownerCustomerBaseInfo.setOwnerProperty(houseRelationship.getOwnerProperty());
                    ownerCustomerBaseInfoMapper.updateByPrimaryKeySelective(ownerCustomerBaseInfo);

                    //同步客户结果表
                    ownerCustomerResult = new OwnerCustomerResult();
                    BeanUtils.copyProperties(customerVo, ownerCustomerResult);
//                    ownerCustomerResult.setOwnerId(houseOperateSalesVo.getNewOwner().getOwnerId());
                    ownerCustomerResult.setUpdateUserId(userId);
                    ownerCustomerResult.setUpdateUserName(userName);
                    ownerCustomerResult.setUpdateTime(new Date());
//                    ownerPropertyName = OwnerUtils.getDicName(dicMap.get("ownerPropertyDic"), Constants.OWNER_PROPERTY_OWNER);
//                    ownerCustomerResult.setOwnerPropertyName(ownerPropertyName);
                    ownerCustomerResult.setOwnerProperty(Constants.OWNER_PROPERTY_OWNER);
                    if (!customerVo.getPrecinctId().contains(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH)) {
                        ownerCustomerResult.setPrecinctId(houseOperateSalesVo.getNewOwner().getPrecinctId() + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH);
                    }
                    customerResultMapper.updateByPrimaryKeySelective(ownerCustomerResult);
                }
                //保存主房产
                if (!CollectionUtils.isEmpty(ownerIdList)) {
                    mainHouseMapper.deleteMainHouse(map);
                    if (!CollectionUtils.isEmpty(mainHouseList)) {
                        mainHouseMapper.insertBatch(mainHouseList);
                    }
                }
                //保存成员关系
                List<OwnerCustomerFamilyInfo> addFamilyInfoList = new ArrayList<>();
                List<OwnerCustomerFamilyInfo> familyInfos = familyMapper.listAllFamily(propertyOwnerIdList);
                if (!CollectionUtils.isEmpty(familyInfos)) {
                    for (CustomerVo customerVo : propertyOwnerList) {
                        boolean flag = false;
                        for (OwnerCustomerFamilyInfo ownerCustomerFamilyInfo : familyInfos) {
                            if (ownerCustomerFamilyInfo.getOwnerId().equals(customerVo.getOwnerId())) {
                                if (ownerCustomerFamilyInfo.getRelationOwnerId().equals(houseOperateSalesVo.getNewOwner().getOwnerId())) {
                                    //关系存在，更新
                                    ownerCustomerFamilyInfo.setOwnerRelationship(customerVo.getOwnerRelationship());
                                    ownerCustomerFamilyInfo.setUpdateUserId(userId);
                                    ownerCustomerFamilyInfo.setUpdateUserName(userName);
                                    CustomerFamilyVo customerFamilyVo = new CustomerFamilyVo();
                                    BeanUtils.copyProperties(ownerCustomerFamilyInfo, customerFamilyVo);
                                    CustomerCallEnum customerCallEnum = OwnerUtils.getCallForFamily(customerFamilyVo);
                                    if (customerCallEnum != null) {
                                        ownerCustomerFamilyInfo.setRelationOwnerCall(customerCallEnum.getValue());
                                    }
                                    familyMapper.updateByPrimaryKeySelective(ownerCustomerFamilyInfo);
                                    //反向更新
                                    ownerCustomerFamilyInfo.setOwnerRelationship(customerVo.getOwnerRelationship());
                                    ownerCustomerFamilyInfo.setUpdateUserId(userId);
                                    ownerCustomerFamilyInfo.setUpdateUserName(userName);
                                    customerFamilyVo = new CustomerFamilyVo();
                                    BeanUtils.copyProperties(ownerCustomerFamilyInfo, customerFamilyVo);
                                    customerFamilyVo.setOwnerId(customerFamilyVo.getRelationOwnerId());
                                    customerFamilyVo.setRelationOwnerId(customerFamilyVo.getOwnerId());
                                    CustomerRelationEnum contraryRelationEnum = OwnerUtils.getContraryForFamily(customerFamilyVo);
                                    ownerCustomerFamilyInfo.setOwnerRelationship(contraryRelationEnum.getValue());
                                    customerFamilyVo.setOwnerRelationship(contraryRelationEnum.getValue());
                                    customerCallEnum = OwnerUtils.getCallForFamily(customerFamilyVo);
                                    if (customerCallEnum != null) {
                                        ownerCustomerFamilyInfo.setRelationOwnerCall(customerCallEnum.getValue());
                                    }
                                    familyMapper.updateByPrimaryKeySelective(ownerCustomerFamilyInfo);
                                    flag = false;
                                    break;
                                } else {
                                    flag = true;
                                }
                            }
                        }
                        if (flag) {
                            //关系不存在，新增
                            CustomerFamilyVo customerFamilyVo = new CustomerFamilyVo();
                            customerFamilyVo.setOwnerId(customerVo.getOwnerId());
                            customerFamilyVo.setOwnerRelationship(customerVo.getOwnerRelationship());
                            customerFamilyVo.setCustomerVo(customerVo);
                            customerFamilyVo.setRelationOwnerId(houseOperateSalesVo.getNewOwner().getOwnerId());
                            OwnerCustomerFamilyInfo familyInfo = new OwnerCustomerFamilyInfo();
                            BeanUtils.copyProperties(customerFamilyVo, familyInfo);
                            familyInfo.setCreateUserId(userId);
                            familyInfo.setCreateUserName(userName);
                            familyInfo.setUpdateUserId(userId);
                            familyInfo.setUpdateUserName(userName);
                            CustomerCallEnum customerCallEnum = OwnerUtils.getCallForFamily(customerFamilyVo);
                            if (customerCallEnum != null) {
                                familyInfo.setRelationOwnerCall(customerCallEnum.getValue());
                            }
                            addFamilyInfoList.add(familyInfo);
                            //新增反向关系
                            customerFamilyVo.setOwnerId(houseOperateSalesVo.getNewOwner().getOwnerId());
                            customerFamilyVo.setRelationOwnerId(customerVo.getOwnerId());
                            familyInfo = new OwnerCustomerFamilyInfo();
                            BeanUtils.copyProperties(customerFamilyVo, familyInfo);
                            familyInfo.setCreateUserId(userId);
                            familyInfo.setCreateUserName(userName);
                            familyInfo.setUpdateUserId(userId);
                            familyInfo.setUpdateUserName(userName);
                            CustomerRelationEnum contraryRelationEnum = OwnerUtils.getContraryForFamily(customerFamilyVo);
                            customerFamilyVo.setOwnerRelationship(contraryRelationEnum.getValue());
                            familyInfo.setOwnerRelationship(contraryRelationEnum.getValue());
                            customerCallEnum = OwnerUtils.getCallForFamily(customerFamilyVo);
                            if (customerCallEnum != null) {
                                familyInfo.setRelationOwnerCall(customerCallEnum.getValue());
                            }
                            addFamilyInfoList.add(familyInfo);
                        }
                    }
                }
                if (!CollectionUtils.isEmpty(addFamilyInfoList)) {
                    familyMapper.insertBatch(addFamilyInfoList);
                }
                //保存产权人信息
                housePropertyOwnerMapper.insertBatch(propertyOwners);
                //保存新产权人的客户房产关系
                houseRelationshipMapper.insertBatch(propertyOwnerHouseRelationshipList);
                //更新新业主是否有家庭成员
                OwnerCustomerResult customerResult = new OwnerCustomerResult();
                customerResult.setOwnerId(houseOperateSalesVo.getNewOwner().getOwnerId());
                customerResult.setHasFamily(Constants.TRUE.toString());
                customerResult.setUpdateUserId(userId);
                customerResult.setUpdateUserName(userName);
                customerResultMapper.updateByPrimaryKeySelective(customerResult);
            }
            //更新房态
//            OwnerHouseBaseInfo baseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
            OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
            baseInfo.setHouseId(houseOperateSalesVo.getHouseId());
            baseInfo.setUpdateUserId(userId);
            baseInfo.setUpdateUserName(userName);
            baseInfo.setStage(HouseStageEnum.WEI_LING.getValue());
            houseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
            //同步MongoDB
            SearchVo searchVo = new SearchVo();
            List<FilterEntity> filterList = new ArrayList<>();
            FilterEntity currentRecordEntity = new FilterEntity();
            currentRecordEntity.setFieldName("is_current_record");
            currentRecordEntity.setFieldValue(Constants.TRUE.toString());
            currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(currentRecordEntity);
            FilterEntity houseIdEntity = new FilterEntity();
            houseIdEntity.setFieldName("result.house_id");
            houseIdEntity.setFieldValue(houseOperateSalesVo.getHouseId().toString());
            houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(houseIdEntity);
            searchVo.setFilterList(filterList);
            List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
            //更新上一条为历史数据
            HouseListEntity houseEntity = new HouseListEntity();
            houseEntity.setIsCurrentRecord(Constants.FALSE);
//            houseEntity.setIsCurrentRecordName("历史");
            houseEntity.setHouseId(houseOperateSalesVo.getHouseId());
//            houseEntity.setIsNowDetail(Constants.FALSE.toString());
            houseResultMapper.updateCurrentRecord(houseEntity);

            if (!CollectionUtils.isEmpty(houseListEntities)) {
                baseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
                //新增当前售楼信息
                HouseListEntity houseListEntity = houseListEntities.get(0);
                houseListEntity.setHouseStage(HouseStageEnum.WEI_LING.getValue());
                houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                houseListEntity.setIsCurrentRecord(Constants.TRUE);
                houseListEntity.setOwnerId(houseOperateSalesVo.getNewOwner().getOwnerId());
                houseListEntity.setOwnerName(houseOperateSalesVo.getNewOwner().getOwnerName());
                houseListEntity.setOwnerPhone(houseOperateSalesVo.getNewOwner().getMobile());
                houseListEntity.setCertificate(houseOperateSalesVo.getNewOwner().getCertificate());
                houseListEntity.setColumn0(houseOperateSalesVo.getColumn0());
                houseListEntity.setColumn1(houseOperateSalesVo.getColumn1());
                houseListEntity.setColumn2(houseOperateSalesVo.getColumn2());
                houseListEntity.setColumn3(houseOperateSalesVo.getColumn3());
                houseListEntity.setColumn4(houseOperateSalesVo.getColumn4());
                houseListEntity.setColumn5(houseOperateSalesVo.getColumn5());
                houseListEntity.setColumn6(houseOperateSalesVo.getColumn6());
                houseListEntity.setColumn7(houseOperateSalesVo.getColumn7());
                houseListEntity.setColumn8(houseOperateSalesVo.getColumn8());
                houseListEntity.setColumn9(houseOperateSalesVo.getColumn9());
                houseListEntity.setSalesStageName(stageName);
                houseListEntity.setTakeStageName("未收房");
                houseListEntity.setCheckInStageName("未入住");
                houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                houseListEntity.setHouseOperateType(houseOperateSalesVo.getHouseOperateType());
                houseListEntity.setRentStage(HouseRentStageEnum.RENT_STAGE_NONE.getValue());
                houseListEntity.setRentStageName(HouseRentStageEnum.RENT_STAGE_NONE.getTitle());
                houseListEntity.setDecorateStage(HouseDecorateStageEnum.DECORATE_STAGE_NONE.getValue());
                houseListEntity.setDecorateStageName(HouseDecorateStageEnum.DECORATE_STAGE_NONE.getTitle());
                houseListEntity.setHandleTime(houseOperateSalesVo.getSalesDate());
                if (StringUtils.hasLength(propertyOwnerNames)) {
                    houseListEntity.setPropertyOwnerNames(propertyOwnerNames.substring(0, propertyOwnerNames.length() - 1));
                }
//                houseListEntity.setIsNowDetail(Constants.TRUE.toString());
                houseResultMapper.insertSelective(houseListEntity);
            }
        }
        //收房
        if (doTake) {
            long takeDetail = 0;
            if (houseOperateSalesVo.getTakeDate() != null) {
                houseOperateSalesVo.setDetailId(ownerHouseStageDetail.getDetailId());
                takeDetail = addTake(houseOperateSalesVo, userId, userName, dicMap, true);
            }
            //入住
            if (houseOperateSalesVo.getCheckInDate() != null) {
                houseOperateSalesVo.setDetailId(takeDetail);
                addCheckIn(houseOperateSalesVo, userId, userName);
            }
        }
        return houseOperateSalesVo.getDetailId();
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long addTake(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName, Map<String, NsCoreDictionaryVo> dicMap, boolean hasSales) throws Exception {
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("houseId", houseOperateSalesVo.getHouseId());
        List<String> list = new ArrayList<>();
        list.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
        list.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
        detailMap.put("list", list);
        OwnerHouseStageDetail salesDetail = houseStageDetailMapper.loadDetail(detailMap);
        Long previousDetailId = houseOperateSalesVo.getDetailId();
        long detailId = 0;
        boolean hasTake = true;
        if (salesDetail == null) {
            //房产未售,进行售楼登记
            if (!hasSales) {
                previousDetailId = addSalse(houseOperateSalesVo, userId, userName, dicMap, true);
                hasTake = false;
            }
        } else {
            if (houseOperateSalesVo.getSalesDate() != null) {
                //房产已售，更新售楼记录
                OwnerHouseStageDetail record = new OwnerHouseStageDetail();
                record.setDetailId(salesDetail.getDetailId());
                record.setUpdateUserId(userId);
                record.setUpdateUserName(userName);
                record.setHandleTime(houseOperateSalesVo.getSalesDate());
                houseStageDetailMapper.updateByPrimaryKeySelective(record);
                //同步MongoDB
                HouseListEntity houseListEntity = new HouseListEntity();
                houseListEntity.setHandleTime(houseOperateSalesVo.getSalesDate());
                houseListEntity.setHouseId(houseOperateSalesVo.getHouseId());
                houseListEntity.setDetailId(salesDetail.getDetailId());
                houseResultMapper.updateByDetailId(houseListEntity);
            }
        }
        if (hasTake) {
            OwnerHouseBaseInfo houseBaseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
            houseOperateSalesVo.setPrecinctId(houseBaseInfo.getPrecinctId());
            //业主房产操作---收房
            OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
            BeanUtils.copyProperties(houseOperateSalesVo, ownerHouseStageDetail);
            ownerHouseStageDetail.setHouseId(houseOperateSalesVo.getHouseId());
            ownerHouseStageDetail.setHandleTime(houseOperateSalesVo.getTakeDate());
            ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.SHOU_FANG.getValue());
            ownerHouseStageDetail.setPreviousDetailId(previousDetailId);
            ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
            ownerHouseStageDetail.setRentStage(houseBaseInfo.getRentStage());
            ownerHouseStageDetail.setDecorateStage(houseBaseInfo.getDecorateStage());
            ownerHouseStageDetail.setRemark(houseOperateSalesVo.getRemark());
            ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
            ownerHouseStageDetail.setCreateUserId(userId);
            ownerHouseStageDetail.setCreateUserName(userName);
            ownerHouseStageDetail.setUpdateUserId(userId);
            ownerHouseStageDetail.setUpdateUserName(userName);

            //更新上一条收房列表的当前记录为历史记录
            Map<String, Object> updateNowDetailMap = new HashMap<>();
            List<String> houseOperateTypeList = new ArrayList<>();
            houseOperateTypeList.add(HouseOperateTypeEnum.SHOU_FANG.getValue());
            updateNowDetailMap.put("houseId", houseOperateSalesVo.getHouseId());
            updateNowDetailMap.put("isNowDetail", Constants.FALSE.toString());
            updateNowDetailMap.put("updateUserId", userId);
            updateNowDetailMap.put("updateUserName", userName);
            updateNowDetailMap.put("houseOperateTypeList", houseOperateTypeList);
            houseStageDetailMapper.updateNowDetail(updateNowDetailMap);
            ownerHouseStageDetail.setIsNowDetail(Constants.TRUE.toString());
            //保存新业主相关信息
            int index = houseStageDetailMapper.insertSelective(ownerHouseStageDetail);
            detailId = ownerHouseStageDetail.getDetailId();
            if (index > 0) {
                //修改当前记录为历史记录
                Map<String, Object> editRelationMap = new HashMap<>();
                editRelationMap.put("userId", userId);
                editRelationMap.put("preDetailId", previousDetailId);
                editRelationMap.put("houseId", houseOperateSalesVo.getHouseId());
                houseRelationshipMapper.editCurrentRecordFalse(editRelationMap);
                //新增收房记录为当前记录
                editRelationMap.put("detailId", ownerHouseStageDetail.getDetailId());
                houseRelationshipMapper.insertBatchForCurrent(editRelationMap);
                //更新房态
                OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                baseInfo.setHouseId(houseOperateSalesVo.getHouseId());
                baseInfo.setUpdateUserId(userId);
                baseInfo.setUpdateUserName(userName);
                baseInfo.setStage(HouseStageEnum.KONG_GUAN.getValue());
                houseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
                //同步MongoDB
                SearchVo searchVo = new SearchVo();
                List<FilterEntity> filterList = new ArrayList<>();
                FilterEntity currentRecordEntity = new FilterEntity();
                currentRecordEntity.setFieldName("is_current_record");
                currentRecordEntity.setFieldValue(Constants.TRUE.toString());
                currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
                filterList.add(currentRecordEntity);
                FilterEntity houseIdEntity = new FilterEntity();
                houseIdEntity.setFieldName("result.house_id");
                houseIdEntity.setFieldValue(houseOperateSalesVo.getHouseId().toString());
                houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
                filterList.add(houseIdEntity);
                searchVo.setFilterList(filterList);
                List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
                //更新上一条为历史数据
                HouseListEntity houseEntity = new HouseListEntity();
                houseEntity.setIsCurrentRecord(Constants.FALSE);
//                houseEntity.setIsCurrentRecordName("历史");
                houseEntity.setHouseId(houseOperateSalesVo.getHouseId());
//                houseEntity.setIsNowDetail(Constants.FALSE.toString());
                houseResultMapper.updateCurrentRecord(houseEntity);

                if (!CollectionUtils.isEmpty(houseListEntities)) {
                    baseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
                    //新增当前收房信息
                    HouseListEntity houseListEntity = houseListEntities.get(0);
                    houseListEntity.setHouseStage(HouseStageEnum.KONG_GUAN.getValue());
                    houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                    houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                    houseListEntity.setIsCurrentRecord(Constants.TRUE);
                    houseListEntity.setTakeStageName("已收房");
                    houseListEntity.setHouseOperateType(HouseOperateTypeEnum.SHOU_FANG.getValue());
                    houseListEntity.setHandleTime(houseOperateSalesVo.getTakeDate());
                    houseListEntity.setColumn0(houseOperateSalesVo.getColumn0());
                    houseListEntity.setColumn1(houseOperateSalesVo.getColumn1());
                    houseListEntity.setColumn2(houseOperateSalesVo.getColumn2());
                    houseListEntity.setColumn3(houseOperateSalesVo.getColumn3());
                    houseListEntity.setColumn4(houseOperateSalesVo.getColumn4());
                    houseListEntity.setColumn5(houseOperateSalesVo.getColumn5());
                    houseListEntity.setColumn6(houseOperateSalesVo.getColumn6());
                    houseListEntity.setColumn7(houseOperateSalesVo.getColumn7());
                    houseListEntity.setColumn8(houseOperateSalesVo.getColumn8());
                    houseListEntity.setColumn9(houseOperateSalesVo.getColumn9());
//                    houseEntity.setIsNowDetail(Constants.TRUE.toString());
                    houseResultMapper.insertSelective(houseListEntity);
                }

            }
            detailId = ownerHouseStageDetail.getDetailId();
        }
        return detailId;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long addCheckIn(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName) throws Exception {
      /*  HouseOperateSalesVo houseOperateSalesVo = new HouseOperateSalesVo();
        houseOperateSalesVo = FormUtils.mapToBean(map, houseOperateSalesVo);*/
        Long previousDetailId = houseOperateSalesVo.getDetailId();
        //验证房产当前是否有过房态操作
        OwnerHouseRelationship relationship = houseRelationshipMapper.loadOwnerRelationByHouseId(houseOperateSalesVo.getHouseId());
        if (relationship != null) {
            previousDetailId = relationship.getDetailId();
        }
        //业主房产操作---入住
        OwnerHouseBaseInfo houseBaseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
        houseOperateSalesVo.setPrecinctId(houseBaseInfo.getPrecinctId());
        OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
        BeanUtils.copyProperties(houseOperateSalesVo, ownerHouseStageDetail);
        ownerHouseStageDetail.setHouseId(houseOperateSalesVo.getHouseId());
        ownerHouseStageDetail.setHandleTime(houseOperateSalesVo.getCheckInDate());
        ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.RU_ZHU.getValue());
        ownerHouseStageDetail.setPreviousDetailId(previousDetailId);
        ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
        ownerHouseStageDetail.setRentStage(houseBaseInfo.getRentStage());
        ownerHouseStageDetail.setDecorateStage(houseBaseInfo.getDecorateStage());
        ownerHouseStageDetail.setRemark(houseOperateSalesVo.getRemark());
        ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
        ownerHouseStageDetail.setCreateUserId(userId);
        ownerHouseStageDetail.setCreateUserName(userName);
        ownerHouseStageDetail.setUpdateUserId(userId);
        ownerHouseStageDetail.setUpdateUserName(userName);

        //更新上一条入住列表的当前记录为历史记录
        Map<String, Object> updateNowDetailMap = new HashMap<>();
        List<String> houseOperateTypeList = new ArrayList<>();
        houseOperateTypeList.add(HouseOperateTypeEnum.RU_ZHU.getValue());
        houseOperateTypeList.add(HouseOperateTypeEnum.BAN_CHU.getValue());
        houseOperateTypeList.add(HouseOperateTypeEnum.KONG_GUAN.getValue());
        updateNowDetailMap.put("houseId", houseOperateSalesVo.getHouseId());
        updateNowDetailMap.put("isNowDetail", Constants.FALSE.toString());
        updateNowDetailMap.put("updateUserId", userId);
        updateNowDetailMap.put("updateUserName", userName);
        updateNowDetailMap.put("houseOperateTypeList", houseOperateTypeList);
        houseStageDetailMapper.updateNowDetail(updateNowDetailMap);
        ownerHouseStageDetail.setIsNowDetail(Constants.TRUE.toString());
        //保存新业主相关信息
        int index = houseStageDetailMapper.insertSelective(ownerHouseStageDetail);
        long detailId = ownerHouseStageDetail.getDetailId();
        if (index > 0) {
            //修改当前记录为历史记录
            Map<String, Object> editRelationMap = new HashMap<>();
            editRelationMap.put("userId", userId);
            editRelationMap.put("preDetailId", previousDetailId);
            editRelationMap.put("houseId", houseOperateSalesVo.getHouseId());
            houseRelationshipMapper.editCurrentRecordFalse(editRelationMap);
            //新增入住记录为当前记录
            editRelationMap.put("detailId", ownerHouseStageDetail.getDetailId());
            houseRelationshipMapper.insertBatchForCurrent(editRelationMap);
            //更新房态
            OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
            baseInfo.setHouseId(houseOperateSalesVo.getHouseId());
            baseInfo.setUpdateUserId(userId);
            baseInfo.setUpdateUserName(userName);
            baseInfo.setStage(HouseStageEnum.RU_ZHU.getValue());
            houseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
            //同步MongoDB
            SearchVo searchVo = new SearchVo();
            List<FilterEntity> filterList = new ArrayList<>();
            FilterEntity currentRecordEntity = new FilterEntity();
            currentRecordEntity.setFieldName("is_current_record");
            currentRecordEntity.setFieldValue(Constants.TRUE.toString());
            currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(currentRecordEntity);
            FilterEntity houseIdEntity = new FilterEntity();
            houseIdEntity.setFieldName("result.house_id");
            houseIdEntity.setFieldValue(houseOperateSalesVo.getHouseId().toString());
            houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(houseIdEntity);
            searchVo.setFilterList(filterList);
            List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
            //更新上一条为历史数据
            HouseListEntity houseEntity = new HouseListEntity();
            houseEntity.setIsCurrentRecord(Constants.FALSE);
//            houseEntity.setIsCurrentRecordName("历史");
            houseEntity.setHouseId(houseOperateSalesVo.getHouseId());
//            houseEntity.setIsNowDetail(Constants.FALSE.toString());
            houseResultMapper.updateCurrentRecord(houseEntity);

            if (!CollectionUtils.isEmpty(houseListEntities)) {
                baseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
                //新增当前入住信息
                HouseListEntity houseListEntity = houseListEntities.get(0);
                houseListEntity.setHouseStage(HouseStageEnum.RU_ZHU.getValue());
                houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                houseListEntity.setIsCurrentRecord(Constants.TRUE);
                houseListEntity.setCheckInStageName("已入住");
                houseListEntity.setHouseOperateType(HouseOperateTypeEnum.RU_ZHU.getValue());
                houseListEntity.setHandleTime(houseOperateSalesVo.getCheckInDate());
                houseListEntity.setColumn0(houseOperateSalesVo.getColumn0());
                houseListEntity.setColumn1(houseOperateSalesVo.getColumn1());
                houseListEntity.setColumn2(houseOperateSalesVo.getColumn2());
                houseListEntity.setColumn3(houseOperateSalesVo.getColumn3());
                houseListEntity.setColumn4(houseOperateSalesVo.getColumn4());
                houseListEntity.setColumn5(houseOperateSalesVo.getColumn5());
                houseListEntity.setColumn6(houseOperateSalesVo.getColumn6());
                houseListEntity.setColumn7(houseOperateSalesVo.getColumn7());
                houseListEntity.setColumn8(houseOperateSalesVo.getColumn8());
                houseListEntity.setColumn9(houseOperateSalesVo.getColumn9());
//                houseEntity.setIsNowDetail(Constants.TRUE.toString());
                houseResultMapper.insertSelective(houseListEntity);
            }

        }
        return detailId;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long addMoveOut(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName) throws Exception {
//        HouseOperateSalesVo houseOperateSalesVo = new HouseOperateSalesVo();
//        houseOperateSalesVo = FormUtils.mapToBean(map, houseOperateSalesVo);
        Long previousDetailId = houseOperateSalesVo.getDetailId();
        //验证房产当前是否有过房态操作
        OwnerHouseRelationship relationship = houseRelationshipMapper.loadOwnerRelationByHouseId(houseOperateSalesVo.getHouseId());
        if (relationship != null) {
            previousDetailId = relationship.getDetailId();
        }
        //业主房产操作---搬出
        OwnerHouseBaseInfo houseBaseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
        houseOperateSalesVo.setPrecinctId(houseBaseInfo.getPrecinctId());
        OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
        BeanUtils.copyProperties(houseOperateSalesVo, ownerHouseStageDetail);
        ownerHouseStageDetail.setHouseId(houseOperateSalesVo.getHouseId());
        ownerHouseStageDetail.setHandleTime(houseOperateSalesVo.getCheckInDate());
        ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.BAN_CHU.getValue());
        ownerHouseStageDetail.setPreviousDetailId(previousDetailId);
        ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
        ownerHouseStageDetail.setRentStage(houseBaseInfo.getRentStage());
        ownerHouseStageDetail.setDecorateStage(houseBaseInfo.getDecorateStage());
        ownerHouseStageDetail.setRemark(houseOperateSalesVo.getRemark());
        ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
        ownerHouseStageDetail.setCreateUserId(userId);
        ownerHouseStageDetail.setCreateUserName(userName);
        ownerHouseStageDetail.setUpdateUserId(userId);
        ownerHouseStageDetail.setUpdateUserName(userName);

        //更新上一条入住列表的当前记录为历史记录
        Map<String, Object> updateNowDetailMap = new HashMap<>();
        List<String> houseOperateTypeList = new ArrayList<>();
        houseOperateTypeList.add(HouseOperateTypeEnum.RU_ZHU.getValue());
        houseOperateTypeList.add(HouseOperateTypeEnum.BAN_CHU.getValue());
        houseOperateTypeList.add(HouseOperateTypeEnum.KONG_GUAN.getValue());
        updateNowDetailMap.put("houseId", houseOperateSalesVo.getHouseId());
        updateNowDetailMap.put("isNowDetail", Constants.FALSE.toString());
        updateNowDetailMap.put("updateUserId", userId);
        updateNowDetailMap.put("updateUserName", userName);
        updateNowDetailMap.put("houseOperateTypeList", houseOperateTypeList);
        houseStageDetailMapper.updateNowDetail(updateNowDetailMap);
        ownerHouseStageDetail.setIsNowDetail(Constants.TRUE.toString());
        //保存房态操作
        int index = houseStageDetailMapper.insertSelective(ownerHouseStageDetail);
        long detailId = ownerHouseStageDetail.getDetailId();
        if (index > 0) {
            //修改当前记录为历史记录
            Map<String, Object> editRelationMap = new HashMap<>();
            editRelationMap.put("userId", userId);
            editRelationMap.put("preDetailId", previousDetailId);
            editRelationMap.put("houseId", houseOperateSalesVo.getHouseId());
            houseRelationshipMapper.editCurrentRecordFalse(editRelationMap);
            //新增搬出记录为当前记录
            editRelationMap.put("detailId", ownerHouseStageDetail.getDetailId());
            houseRelationshipMapper.insertBatchForCurrent(editRelationMap);
            //更新房态
            OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
            baseInfo.setHouseId(houseOperateSalesVo.getHouseId());
            baseInfo.setUpdateUserId(userId);
            baseInfo.setUpdateUserName(userName);
            baseInfo.setStage(HouseStageEnum.KONG_GUAN.getValue());
            houseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
            //同步MongoDB
            SearchVo searchVo = new SearchVo();
            List<FilterEntity> filterList = new ArrayList<>();
            FilterEntity currentRecordEntity = new FilterEntity();
            currentRecordEntity.setFieldName("is_current_record");
            currentRecordEntity.setFieldValue(Constants.TRUE.toString());
            currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(currentRecordEntity);
            FilterEntity houseIdEntity = new FilterEntity();
            houseIdEntity.setFieldName("result.house_id");
            houseIdEntity.setFieldValue(houseOperateSalesVo.getHouseId().toString());
            houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(houseIdEntity);
            searchVo.setFilterList(filterList);
            List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
            //更新上一条为历史数据
            HouseListEntity houseEntity = new HouseListEntity();
            houseEntity.setIsCurrentRecord(Constants.FALSE);
//            houseEntity.setIsCurrentRecordName("历史");
            houseEntity.setHouseId(houseOperateSalesVo.getHouseId());
//            houseEntity.setIsNowDetail(Constants.FALSE.toString());
            houseResultMapper.updateCurrentRecord(houseEntity);

            if (!CollectionUtils.isEmpty(houseListEntities)) {
                baseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
                //新增当前搬出信息
                HouseListEntity houseListEntity = houseListEntities.get(0);
                houseListEntity.setHouseStage(HouseStageEnum.KONG_GUAN.getValue());
                houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                houseListEntity.setIsCurrentRecord(Constants.TRUE);
                houseListEntity.setCheckInStageName("空关");
                houseListEntity.setHouseOperateType(HouseOperateTypeEnum.BAN_CHU.getValue());
                houseListEntity.setHandleTime(houseOperateSalesVo.getApplyDate());
                houseListEntity.setColumn0(houseOperateSalesVo.getColumn0());
                houseListEntity.setColumn1(houseOperateSalesVo.getColumn1());
                houseListEntity.setColumn2(houseOperateSalesVo.getColumn2());
                houseListEntity.setColumn3(houseOperateSalesVo.getColumn3());
                houseListEntity.setColumn4(houseOperateSalesVo.getColumn4());
                houseListEntity.setColumn5(houseOperateSalesVo.getColumn5());
                houseListEntity.setColumn6(houseOperateSalesVo.getColumn6());
                houseListEntity.setColumn7(houseOperateSalesVo.getColumn7());
                houseListEntity.setColumn8(houseOperateSalesVo.getColumn8());
                houseListEntity.setColumn9(houseOperateSalesVo.getColumn9());
//                houseEntity.setIsNowDetail(Constants.TRUE.toString());
                houseResultMapper.insertSelective(houseListEntity);
            }
        }
        return detailId;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long addRent(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName, Map<String, NsCoreDictionaryVo> dicMap) throws Exception {
        Long previousDetailId = houseOperateSalesVo.getDetailId();
        //验证房产当前是否有过房态操作
        OwnerHouseRelationship relationship = houseRelationshipMapper.loadOwnerRelationByHouseId(houseOperateSalesVo.getHouseId());
        if (relationship != null) {
            previousDetailId = relationship.getDetailId();
        }
        //业主房产操作---出租
        OwnerHouseBaseInfo houseBaseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
        houseOperateSalesVo.setPrecinctId(houseBaseInfo.getPrecinctId());
        OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
        BeanUtils.copyProperties(houseOperateSalesVo, ownerHouseStageDetail);
        ownerHouseStageDetail.setHouseId(houseOperateSalesVo.getHouseId());
        ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.CHU_ZU.getValue());
        if (houseOperateSalesVo.getNewOwner() != null) {
            ownerHouseStageDetail.setOldOwnerId(houseOperateSalesVo.getNewOwner().getOwnerId());
        }
        ownerHouseStageDetail.setPreviousDetailId(previousDetailId);
        ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
        ownerHouseStageDetail.setRentStage(houseBaseInfo.getRentStage());
        ownerHouseStageDetail.setDecorateStage(houseBaseInfo.getDecorateStage());
        ownerHouseStageDetail.setRemark(houseOperateSalesVo.getRemark());
        ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
        ownerHouseStageDetail.setCreateUserId(userId);
        ownerHouseStageDetail.setCreateUserName(userName);
        ownerHouseStageDetail.setUpdateUserId(userId);
        ownerHouseStageDetail.setUpdateUserName(userName);

        //更新上一条出租列表的当前记录为历史记录
        Map<String, Object> updateNowDetailMap = new HashMap<>();
        List<String> houseOperateTypeList = new ArrayList<>();
        houseOperateTypeList.add(HouseOperateTypeEnum.CHU_ZU.getValue());
        houseOperateTypeList.add(HouseOperateTypeEnum.TUI_ZU.getValue());
        houseOperateTypeList.add(HouseOperateTypeEnum.ZHUAN_ZU.getValue());
        updateNowDetailMap.put("houseId", houseOperateSalesVo.getHouseId());
        updateNowDetailMap.put("isNowDetail", Constants.FALSE.toString());
        updateNowDetailMap.put("updateUserId", userId);
        updateNowDetailMap.put("updateUserName", userName);
        updateNowDetailMap.put("houseOperateTypeList", houseOperateTypeList);
        houseStageDetailMapper.updateNowDetail(updateNowDetailMap);
        ownerHouseStageDetail.setIsNowDetail(Constants.TRUE.toString());
        //保存操作信息
        int index = houseStageDetailMapper.insertSelective(ownerHouseStageDetail);
        long detailId = ownerHouseStageDetail.getDetailId();
        if (index > 0) {
            //保存出租信息
            OwnerHouseStageExtendInfoRent extendInfoRent = new OwnerHouseStageExtendInfoRent();
            extendInfoRent.setDetailId(ownerHouseStageDetail.getDetailId());
            if (!CollectionUtils.isEmpty(houseOperateSalesVo.getTimeList())) {
                extendInfoRent.setRentStartDate(houseOperateSalesVo.getTimeList().get(0));
                extendInfoRent.setRentEndDate(houseOperateSalesVo.getTimeList().get(1));
            }
            extendInfoRent.setRentOwnerId(houseOperateSalesVo.getNewRentOwner().getOwnerId());
            extendInfoRent.setOwnerProperty(Constants.OWNER_PROPERTY_RENT);
            rentMapper.insertSelective(extendInfoRent);
            //修改客户性质
            CustomerVo customerVo = customerBaseInfoMapper.loadCustomer(houseOperateSalesVo.getNewRentOwner().getOwnerId());
            OwnerCustomerBaseInfo customerBaseInfo = null;
            customerBaseInfo = new OwnerCustomerBaseInfo();
            BeanUtils.copyProperties(houseOperateSalesVo.getNewRentOwner(), customerBaseInfo);
            if (!customerVo.getPrecinctId().contains(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH)) {
                customerBaseInfo.setPrecinctId(customerBaseInfo.getPrecinctId() + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH);
            }
            if (!Constants.OWNER_PROPERTY_OWNER.equals(customerVo.getOwnerProperty()) &&
                    !Constants.OWNER_PROPERTY_DEVOLOPER.equals(customerVo.getOwnerProperty())) {

                customerBaseInfo.setUpdateUserId(userId);
                customerBaseInfo.setUpdateUserName(userName);
                customerBaseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_RENT);
                ownerCustomerBaseInfoMapper.updateByPrimaryKeySelective(customerBaseInfo);
            }
            OwnerCustomerInfo customerInfo = new OwnerCustomerInfo();
            BeanUtils.copyProperties(houseOperateSalesVo.getNewRentOwner(), customerInfo);
            customerInfo.setUpdateUserId(userId);
            customerInfo.setUpdateUserName(userName);
            customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
            //同步客户结果表
            OwnerCustomerResult ownerCustomerResult = new OwnerCustomerResult();
            if (customerBaseInfo != null) {
                BeanUtils.copyProperties(customerBaseInfo, ownerCustomerResult);
            }
            if (!customerVo.getPrecinctId().contains(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH)) {
                ownerCustomerResult.setPrecinctId(ownerCustomerResult.getPrecinctId() + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH);
            }
            BeanUtils.copyProperties(customerInfo, ownerCustomerResult);
            ownerCustomerResult.setUpdateTime(new Date());
//            String ownerPropertyName = OwnerUtils.getDicName(dicMap.get("ownerPropertyDic"), customerBaseInfo.getOwnerProperty());
//            ownerCustomerResult.setOwnerPropertyName(ownerPropertyName);
            customerResultMapper.updateByPrimaryKeySelective(ownerCustomerResult);
            //修改当前记录为历史记录
            Map<String, Object> editRelationMap = new HashMap<>();
            editRelationMap.put("userId", userId);
            editRelationMap.put("preDetailId", previousDetailId);
            editRelationMap.put("houseId", houseOperateSalesVo.getHouseId());
            houseRelationshipMapper.editCurrentRecordFalse(editRelationMap);
            //新增出租记录为当前记录
            HouseStageEnum houseStageEnum = HouseStageEnum.getInstance(houseBaseInfo.getStage());
            if (houseStageEnum.equals(HouseStageEnum.KONG_ZHI)) {
                OwnerHouseRelationship houseRelationship = new OwnerHouseRelationship();
                BeanUtils.copyProperties(houseOperateSalesVo, houseRelationship);
//                long developerId = getDeveloperByHousePath(houseOperateSalesVo.getHouseId());
//                houseRelationship.setOwnerId(developerId);
                houseRelationship.setOwnerId(houseOperateSalesVo.getNewRentOwner().getOwnerId());
                houseRelationship.setIsCurrentRecord(Constants.TRUE);
                houseRelationship.setOwnerCategory(OwnerConstants.OWNER_CATEGORY_OWNER);
                houseRelationship.setIsDeleted(Constants.FALSE);
                houseRelationship.setCreateUserId(userId);
                houseRelationship.setCreateUserName(userName);
                houseRelationship.setUpdateUserId(userId);
                houseRelationship.setUpdateUserName(userName);
                houseRelationship.setDetailId(detailId);
                houseRelationshipMapper.insertSelective(houseRelationship);
            } else {
                editRelationMap.put("detailId", ownerHouseStageDetail.getDetailId());
                houseRelationshipMapper.insertBatchForCurrent(editRelationMap);
            }

            //更新房态
            OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
            baseInfo.setHouseId(houseOperateSalesVo.getHouseId());
            baseInfo.setUpdateUserId(userId);
            baseInfo.setUpdateUserName(userName);
            baseInfo.setRentStage(HouseRentStageEnum.RENT_STAGE_IN.getValue());
            houseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
            //同步MongoDB
            SearchVo searchVo = new SearchVo();
            List<FilterEntity> filterList = new ArrayList<>();
            FilterEntity currentRecordEntity = new FilterEntity();
            currentRecordEntity.setFieldName("is_current_record");
            currentRecordEntity.setFieldValue(Constants.TRUE.toString());
            currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(currentRecordEntity);
            FilterEntity houseIdEntity = new FilterEntity();
            houseIdEntity.setFieldName("result.house_id");
            houseIdEntity.setFieldValue(houseOperateSalesVo.getHouseId().toString());
            houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(houseIdEntity);
            searchVo.setFilterList(filterList);
            List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
            //更新上一条为历史数据
            HouseListEntity houseEntity = new HouseListEntity();
            houseEntity.setIsCurrentRecord(Constants.FALSE);
//            houseEntity.setIsCurrentRecordName("历史");
            houseEntity.setHouseId(houseOperateSalesVo.getHouseId());
            houseResultMapper.updateCurrentRecord(houseEntity);

            if (!CollectionUtils.isEmpty(houseListEntities)) {
                baseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
                //新增当前出租信息
                HouseListEntity houseListEntity = houseListEntities.get(0);
                houseListEntity.setRentStage(HouseRentStageEnum.RENT_STAGE_IN.getValue());
                houseListEntity.setRentStageName(HouseRentStageEnum.RENT_STAGE_IN.getTitle());
                houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                houseListEntity.setIsCurrentRecord(Constants.TRUE);
                houseListEntity.setHouseOperateType(HouseOperateTypeEnum.CHU_ZU.getValue());
                houseListEntity.setHandleTime(houseOperateSalesVo.getApplyDate());
                if (!CollectionUtils.isEmpty(houseOperateSalesVo.getTimeList())) {
                    houseListEntity.setStartTime(houseOperateSalesVo.getTimeList().get(0));
                    houseListEntity.setEndTime(houseOperateSalesVo.getTimeList().get(1));
                }
//                houseListEntity.setStartTime(houseOperateSalesVo.getStartTime());
//                houseListEntity.setEndTime(houseOperateSalesVo.getEndTime());
                houseListEntity.setLesseeId(houseOperateSalesVo.getNewRentOwner().getOwnerId());
                houseListEntity.setLesseeName(houseOperateSalesVo.getNewRentOwner().getOwnerName());
                houseListEntity.setLesseeCertificate(houseOperateSalesVo.getNewRentOwner().getCertificate());
                houseListEntity.setLesseeMobile(houseOperateSalesVo.getNewRentOwner().getMobile());
                houseListEntity.setColumn0(houseOperateSalesVo.getColumn0());
                houseListEntity.setColumn1(houseOperateSalesVo.getColumn1());
                houseListEntity.setColumn2(houseOperateSalesVo.getColumn2());
                houseListEntity.setColumn3(houseOperateSalesVo.getColumn3());
                houseListEntity.setColumn4(houseOperateSalesVo.getColumn4());
                houseListEntity.setColumn5(houseOperateSalesVo.getColumn5());
                houseListEntity.setColumn6(houseOperateSalesVo.getColumn6());
                houseListEntity.setColumn7(houseOperateSalesVo.getColumn7());
                houseListEntity.setColumn8(houseOperateSalesVo.getColumn8());
                houseListEntity.setColumn9(houseOperateSalesVo.getColumn9());
                houseResultMapper.insertSelective(houseListEntity);
            }

        }
        return detailId;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long addRentOut(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName, Map<String, NsCoreDictionaryVo> dicMap) throws Exception {
        Long previousDetailId = houseOperateSalesVo.getDetailId();
        //验证房产当前是否有过房态操作
        OwnerHouseRelationship relationship = houseRelationshipMapper.loadOwnerRelationByHouseId(houseOperateSalesVo.getHouseId());
        if (relationship != null) {
            previousDetailId = relationship.getDetailId();
        }
        //业主房产操作---退租
        OwnerHouseBaseInfo houseBaseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
        houseOperateSalesVo.setPrecinctId(houseBaseInfo.getPrecinctId());
        OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
        BeanUtils.copyProperties(houseOperateSalesVo, ownerHouseStageDetail);
        ownerHouseStageDetail.setHouseId(houseOperateSalesVo.getHouseId());
        ownerHouseStageDetail.setHandleTime(houseOperateSalesVo.getApplyDate());
        ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.TUI_ZU.getValue());
        ownerHouseStageDetail.setPreviousDetailId(previousDetailId);
        if (houseOperateSalesVo.getNewOwner() != null) {
            ownerHouseStageDetail.setOldOwnerId(houseOperateSalesVo.getNewOwner().getOwnerId());
        }
        ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
        ownerHouseStageDetail.setRentStage(houseBaseInfo.getRentStage());
        ownerHouseStageDetail.setDecorateStage(houseBaseInfo.getDecorateStage());
        ownerHouseStageDetail.setRemark(houseOperateSalesVo.getRemark());
        ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
        ownerHouseStageDetail.setCreateUserId(userId);
        ownerHouseStageDetail.setCreateUserName(userName);
        ownerHouseStageDetail.setUpdateUserId(userId);
        ownerHouseStageDetail.setUpdateUserName(userName);

        //更新上一条出租列表的当前记录为历史记录
        Map<String, Object> updateNowDetailMap = new HashMap<>();
        List<String> houseOperateTypeList = new ArrayList<>();
        houseOperateTypeList.add(HouseOperateTypeEnum.CHU_ZU.getValue());
        houseOperateTypeList.add(HouseOperateTypeEnum.TUI_ZU.getValue());
        houseOperateTypeList.add(HouseOperateTypeEnum.ZHUAN_ZU.getValue());
        updateNowDetailMap.put("houseId", houseOperateSalesVo.getHouseId());
        updateNowDetailMap.put("isNowDetail", Constants.FALSE.toString());
        updateNowDetailMap.put("updateUserId", userId);
        updateNowDetailMap.put("updateUserName", userName);
        updateNowDetailMap.put("houseOperateTypeList", houseOperateTypeList);
        houseStageDetailMapper.updateNowDetail(updateNowDetailMap);
        ownerHouseStageDetail.setIsNowDetail(Constants.TRUE.toString());
        //保存新业主相关信息
        int index = houseStageDetailMapper.insertSelective(ownerHouseStageDetail);
        long detailId = ownerHouseStageDetail.getDetailId();
        if (index > 0) {
            //修改租户信息
            OwnerCustomerInfo customerInfo = new OwnerCustomerInfo();
            BeanUtils.copyProperties(houseOperateSalesVo.getOldRentOwner(), customerInfo);
            customerInfo.setUpdateUserId(userId);
            customerInfo.setUpdateUserName(userName);
            customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
            //修改客户性质
//            CustomerVo customerVo = customerBaseInfoMapper.loadCustomer(houseOperateSalesVo.getNewRentOwner().getOwnerId());
//            if (Constants.OWNER_PROPERTY_RENT.equals(customerVo.getOwnerProperty())) {
//                OwnerCustomerBaseInfo customerBaseInfo = new OwnerCustomerBaseInfo();
//                BeanUtils.copyProperties(houseOperateSalesVo.getOldOwner(), customerBaseInfo);
//                customerBaseInfo.setUpdateUserId(userId);
//                customerBaseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_NONE);
//                ownerCustomerBaseInfoMapper.update(customerBaseInfo);
//            }

            //修改当前记录为历史记录
            Map<String, Object> editRelationMap = new HashMap<>();
            editRelationMap.put("userId", userId);
            editRelationMap.put("preDetailId", previousDetailId);
            editRelationMap.put("houseId", houseOperateSalesVo.getHouseId());
            houseRelationshipMapper.editCurrentRecordFalse(editRelationMap);
            //新增退租记录为当前记录
            editRelationMap.put("detailId", ownerHouseStageDetail.getDetailId());
            houseRelationshipMapper.insertBatchForCurrent(editRelationMap);
            //更新房态
            OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
            baseInfo.setHouseId(houseOperateSalesVo.getHouseId());
            baseInfo.setUpdateUserId(userId);
            baseInfo.setUpdateUserName(userName);
            baseInfo.setRentStage(HouseRentStageEnum.RENT_STAGE_NONE.getValue());
            houseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
            //同步MongoDB
            SearchVo searchVo = new SearchVo();
            List<FilterEntity> filterList = new ArrayList<>();
            FilterEntity currentRecordEntity = new FilterEntity();
            currentRecordEntity.setFieldName("is_current_record");
            currentRecordEntity.setFieldValue(Constants.TRUE.toString());
            currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(currentRecordEntity);
            FilterEntity houseIdEntity = new FilterEntity();
            houseIdEntity.setFieldName("result.house_id");
            houseIdEntity.setFieldValue(houseOperateSalesVo.getHouseId().toString());
            houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(houseIdEntity);
            searchVo.setFilterList(filterList);
            List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
            //更新上一条为历史数据
            HouseListEntity houseEntity = new HouseListEntity();
            houseEntity.setIsCurrentRecord(Constants.FALSE);
//            houseEntity.setIsCurrentRecordName("历史");
            houseEntity.setHouseId(houseOperateSalesVo.getHouseId());
            houseResultMapper.updateCurrentRecord(houseEntity);

            if (!CollectionUtils.isEmpty(houseListEntities)) {
                baseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
                //新增当前退租信息
                HouseListEntity houseListEntity = houseListEntities.get(0);
                houseListEntity.setRentStage(HouseRentStageEnum.RENT_STAGE_NONE.getValue());
                houseListEntity.setRentStageName(HouseRentStageEnum.RENT_STAGE_NONE.getTitle());
                houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                houseListEntity.setIsCurrentRecord(Constants.TRUE);
                houseListEntity.setHouseOperateType(HouseOperateTypeEnum.TUI_ZU.getValue());
                houseListEntity.setHandleTime(houseOperateSalesVo.getApplyDate());
                houseListEntity.setLesseeId(0L);
                houseListEntity.setLesseeName("");
                houseListEntity.setLesseeCertificate("");
                houseListEntity.setLesseeMobile("");
                houseListEntity.setColumn0(houseOperateSalesVo.getColumn0());
                houseListEntity.setColumn1(houseOperateSalesVo.getColumn1());
                houseListEntity.setColumn2(houseOperateSalesVo.getColumn2());
                houseListEntity.setColumn3(houseOperateSalesVo.getColumn3());
                houseListEntity.setColumn4(houseOperateSalesVo.getColumn4());
                houseListEntity.setColumn5(houseOperateSalesVo.getColumn5());
                houseListEntity.setColumn6(houseOperateSalesVo.getColumn6());
                houseListEntity.setColumn7(houseOperateSalesVo.getColumn7());
                houseListEntity.setColumn8(houseOperateSalesVo.getColumn8());
                houseListEntity.setColumn9(houseOperateSalesVo.getColumn9());
                houseResultMapper.insertSelective(houseListEntity);
            }

            long ownerId = houseOperateSalesVo.getOldRentOwner().getOwnerId();
            //获取租户房产
            List<OwnerHouseRelationshipVo> relationshipList = houseRelationshipMapper.listOwnerAllHouseRelation(ownerId);
            if (!CollectionUtils.isEmpty(relationshipList)) {
                relationshipList.removeIf(relation -> relation.getHouseId().equals(houseOperateSalesVo.getHouseId()));
                List<OwnerHouseRelationshipVo> rentRelationshipList = houseRelationshipMapper.listRentAllHouseRelation(ownerId);
                relationshipList.addAll(rentRelationshipList);
                Map<Long, List<OwnerHouseRelationshipVo>> ownerHouseMap = relationshipList.stream().collect(Collectors.groupingBy(OwnerHouseRelationshipVo::getPrecinctId));
                //获取当前项目下的相关房产
                List<OwnerHouseRelationshipVo> houseList = ownerHouseMap.get(houseOperateSalesVo.getPrecinctId());
//                houseList.removeIf(relation -> relation.getHouseId().equals(houseOperateSalesVo.getHouseId()));
                Map<String, Object> map = new HashMap<>();
                List<Long> ownerIdList = new ArrayList<>();
                ownerIdList.add(ownerId);
                map.put("list", ownerIdList);
                map.put("precinctId", houseOperateSalesVo.getPrecinctId());
                List<OwnerCustomerMainHouse> mainHouseList = mainHouseMapper.listByOwnerHouse(map);
                if (!CollectionUtils.isEmpty(houseList)) {
                    //租户在当前项目中无主房产
                    if (CollectionUtils.isEmpty(mainHouseList)) {
                        //更新租户主房产为项目中的第一个房产
                        OwnerCustomerMainHouse mainHouse = new OwnerCustomerMainHouse();
                        mainHouse.setOwnerId(ownerId);
                        mainHouse.setHouseId(houseList.get(0).getHouseId());
                        mainHouse.setPrecinctId(houseList.get(0).getPrecinctId());
                        mainHouseMapper.insertSelective(mainHouse);
                    }
                }
                //判断租户在拥有的房产中的客户性质，如果全不为业主或租户则改为住户
                OwnerCustomerBaseInfo customerBaseInfo = new OwnerCustomerBaseInfo();
                BeanUtils.copyProperties(houseOperateSalesVo.getNewRentOwner(), baseInfo);
                customerBaseInfo.setUpdateUserId(userId);
                customerBaseInfo.setUpdateUserName(userName);
                if (CollectionUtils.isEmpty(relationshipList)) {
                    //是否为租户
                    List<Long> ownerIds = new ArrayList<>();
                    ownerIds.add(ownerId);
                    List<OwnerHouseStageExtendInfoRent> detailList = rentMapper.listAllCurrentRent(ownerIds);
                    if (!CollectionUtils.isEmpty(detailList)) {
                        customerBaseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_RENT);
                    } else {
                        //是否为住户
                        List<Long> familyOwnerIdList = familyMapper.listAllOwnerByFamily(ownerId);
                        List<Long> familyRentIdList = familyMapper.listAllRentByFamily(ownerId);
                        detailList = rentMapper.listAllCurrentRent(familyRentIdList);
                        if (!CollectionUtils.isEmpty(familyOwnerIdList) ||
                                !CollectionUtils.isEmpty(detailList)) {
                            customerBaseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_HOUSEHOLD);
                        } else {
                            //如果租户没有房产关系，则改变租户性质为无房客户
                            customerBaseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_NONE);
                        }
                    }
                } else {
                    customerBaseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_OWNER);
                }
                ownerCustomerBaseInfoMapper.updateByPrimaryKeySelective(customerBaseInfo);
                //同步客户结果表
                OwnerCustomerResult ownerCustomerResult = new OwnerCustomerResult();
                BeanUtils.copyProperties(houseOperateSalesVo.getOldRentOwner(), ownerCustomerResult);
                ownerCustomerResult.setOwnerProperty(customerBaseInfo.getOwnerProperty());
//                String ownerPropertyName = OwnerUtils.getDicName(dicMap.get("ownerPropertyDic"), customerBaseInfo.getOwnerProperty());
//                ownerCustomerResult.setOwnerPropertyName(ownerPropertyName);
                ownerCustomerResult = new OwnerCustomerResult();
                ownerCustomerResult.setOwnerId(customerBaseInfo.getOwnerId());
                ownerCustomerResult.setUpdateTime(new Date());
                ownerCustomerResult.setUpdateUserId(userId);
                ownerCustomerResult.setUpdateUserName(userName);
                customerResultMapper.updateByPrimaryKeySelective(ownerCustomerResult);
            }
        }
        return detailId;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long addSublet(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName, Map<String, NsCoreDictionaryVo> dicMap) throws Exception {
        houseOperateSalesVo.setIsSublet(Constants.TRUE);
        //退租
        long rentOutDetail = addRentOut(houseOperateSalesVo, userId, userName, dicMap);
        houseOperateSalesVo.setDetailId(rentOutDetail);
        //出租
        long rentDetail = addRent(houseOperateSalesVo, userId, userName, dicMap);
        return rentDetail;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long addDecorate(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName) throws Exception {
        Long previousDetailId = houseOperateSalesVo.getDetailId();
        //验证房产当前是否有过房态操作
        OwnerHouseRelationship relationship = houseRelationshipMapper.loadOwnerRelationByHouseId(houseOperateSalesVo.getHouseId());
        if (relationship != null) {
            previousDetailId = relationship.getDetailId();
        }
        //业主房产操作---装修
        OwnerHouseBaseInfo houseBaseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
        OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
        BeanUtils.copyProperties(houseOperateSalesVo, ownerHouseStageDetail);
        ownerHouseStageDetail.setHouseId(houseOperateSalesVo.getHouseId());
        ownerHouseStageDetail.setHandleTime(houseOperateSalesVo.getApplyDate());
        ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
        ownerHouseStageDetail.setPreviousDetailId(previousDetailId);
        ownerHouseStageDetail.setOldOwnerId(Objects.isNull(houseOperateSalesVo.getOldOwner()) ? 0L : houseOperateSalesVo.getOldOwner().getOwnerId());
        ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
        ownerHouseStageDetail.setRentStage(houseBaseInfo.getRentStage());
        ownerHouseStageDetail.setDecorateStage(houseBaseInfo.getDecorateStage());
        ownerHouseStageDetail.setRemark(houseOperateSalesVo.getRemark());
        ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
        ownerHouseStageDetail.setCreateUserId(userId);
        ownerHouseStageDetail.setCreateUserName(userName);
        ownerHouseStageDetail.setUpdateUserId(userId);
        ownerHouseStageDetail.setUpdateUserName(userName);

        //更新上一条装修列表的当前记录为历史记录
        Map<String, Object> updateNowDetailMap = new HashMap<>();
        List<String> houseOperateTypeList = new ArrayList<>();
        houseOperateTypeList.add(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
        updateNowDetailMap.put("houseId", houseOperateSalesVo.getHouseId());
        updateNowDetailMap.put("isNowDetail", Constants.FALSE.toString());
        updateNowDetailMap.put("updateUserId", userId);
        updateNowDetailMap.put("updateUserName", userName);
        updateNowDetailMap.put("houseOperateTypeList", houseOperateTypeList);
        houseStageDetailMapper.updateNowDetail(updateNowDetailMap);
        ownerHouseStageDetail.setIsNowDetail(Constants.TRUE.toString());
        //保存新业主相关信息
        int index = houseStageDetailMapper.insertSelective(ownerHouseStageDetail);
        long detailId = ownerHouseStageDetail.getDetailId();
        if (index > 0) {
            //保存装修信息
            OwnerHouseStageExtendInfoDecorate extendInfoDecorate = new OwnerHouseStageExtendInfoDecorate();
            extendInfoDecorate.setDetailId(detailId);
            extendInfoDecorate.setApplyDate(houseOperateSalesVo.getApplyDate());
            if (!CollectionUtils.isEmpty(houseOperateSalesVo.getTimeList())) {
                extendInfoDecorate.setDecorateStartDate(houseOperateSalesVo.getTimeList().get(0));
                extendInfoDecorate.setDecorateEndDate(houseOperateSalesVo.getTimeList().get(1));
            }
//            extendInfoDecorate.setDecorateStartDate(houseOperateSalesVo.getStartTime());
//            extendInfoDecorate.setDecorateEndDate(houseOperateSalesVo.getEndTime());
            decorateMapper.insertSelective(extendInfoDecorate);
            //修改当前记录为历史记录
            Map<String, Object> editRelationMap = new HashMap<>();
            editRelationMap.put("userId", userId);
            editRelationMap.put("preDetailId", previousDetailId);
            editRelationMap.put("houseId", houseOperateSalesVo.getHouseId());
            houseRelationshipMapper.editCurrentRecordFalse(editRelationMap);
            //新增装修记录为当前记录
            HouseStageEnum houseStageEnum = HouseStageEnum.getInstance(houseBaseInfo.getStage());
            if (houseStageEnum.equals(HouseStageEnum.KONG_ZHI)) {
                OwnerHouseRelationship houseRelationship = new OwnerHouseRelationship();
                BeanUtils.copyProperties(houseOperateSalesVo, houseRelationship);
//                long developerId = getDeveloperByHousePath(houseOperateSalesVo.getHouseId());
//                houseRelationship.setOwnerId(developerId);
                houseRelationship.setOwnerId(houseOperateSalesVo.getNewRentOwner().getOwnerId());
                houseRelationship.setIsCurrentRecord(Constants.TRUE);
                houseRelationship.setOwnerCategory(OwnerConstants.OWNER_CATEGORY_OWNER);
                houseRelationship.setIsDeleted(Constants.FALSE);
                houseRelationship.setCreateUserId(userId);
                houseRelationship.setCreateUserName(userName);
                houseRelationship.setUpdateUserId(userId);
                houseRelationship.setUpdateUserName(userName);
                houseRelationship.setDetailId(detailId);
                houseRelationshipMapper.insertSelective(houseRelationship);
            } else {
                editRelationMap.put("detailId", ownerHouseStageDetail.getDetailId());
                houseRelationshipMapper.insertBatchForCurrent(editRelationMap);
            }
            //更新房态
            OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
            baseInfo.setHouseId(houseOperateSalesVo.getHouseId());
            baseInfo.setUpdateUserId(userId);
            baseInfo.setUpdateUserName(userName);
            baseInfo.setDecorateStage(HouseDecorateStageEnum.DECORATE_STAGE_IN.getValue());
            houseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
            //同步MongoDB
            SearchVo searchVo = new SearchVo();
            List<FilterEntity> filterList = new ArrayList<>();
            FilterEntity currentRecordEntity = new FilterEntity();
            currentRecordEntity.setFieldName("is_current_record");
            currentRecordEntity.setFieldValue(Constants.TRUE.toString());
            currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(currentRecordEntity);
            FilterEntity houseIdEntity = new FilterEntity();
            houseIdEntity.setFieldName("result.house_id");
            houseIdEntity.setFieldValue(houseOperateSalesVo.getHouseId().toString());
            houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(houseIdEntity);
            searchVo.setFilterList(filterList);
            List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
            //更新上一条为历史数据
            HouseListEntity houseEntity = new HouseListEntity();
            houseEntity.setIsCurrentRecord(Constants.FALSE);
//            houseEntity.setIsCurrentRecordName("历史");
            houseEntity.setHouseId(houseOperateSalesVo.getHouseId());
//            houseEntity.setIsNowDetail(Constants.FALSE.toString());
            houseResultMapper.updateCurrentRecord(houseEntity);

            if (!CollectionUtils.isEmpty(houseListEntities)) {
                baseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
                //新增当前装修信息
                HouseListEntity houseListEntity = houseListEntities.get(0);
                houseListEntity.setDecorateStage(HouseDecorateStageEnum.DECORATE_STAGE_IN.getValue());
                houseListEntity.setDecorateStageName(HouseDecorateStageEnum.DECORATE_STAGE_IN.getTitle());
                houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                houseListEntity.setIsCurrentRecord(Constants.TRUE);
                houseListEntity.setHouseOperateType(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
                houseListEntity.setHandleTime(houseOperateSalesVo.getApplyDate());
                if (!CollectionUtils.isEmpty(houseOperateSalesVo.getTimeList())) {
                    houseListEntity.setStartTime(houseOperateSalesVo.getTimeList().get(0));
                    houseListEntity.setEndTime(houseOperateSalesVo.getTimeList().get(1));
                }
//                houseListEntity.setStartTime(houseOperateSalesVo.getStartTime());
//                houseListEntity.setEndTime(houseOperateSalesVo.getEndTime());
                houseListEntity.setColumn0(houseOperateSalesVo.getColumn0());
                houseListEntity.setColumn1(houseOperateSalesVo.getColumn1());
                houseListEntity.setColumn2(houseOperateSalesVo.getColumn2());
                houseListEntity.setColumn3(houseOperateSalesVo.getColumn3());
                houseListEntity.setColumn4(houseOperateSalesVo.getColumn4());
                houseListEntity.setColumn5(houseOperateSalesVo.getColumn5());
                houseListEntity.setColumn6(houseOperateSalesVo.getColumn6());
                houseListEntity.setColumn7(houseOperateSalesVo.getColumn7());
                houseListEntity.setColumn8(houseOperateSalesVo.getColumn8());
                houseListEntity.setColumn9(houseOperateSalesVo.getColumn9());
//                houseEntity.setIsNowDetail(Constants.TRUE.toString());
                houseResultMapper.insertSelective(houseListEntity);
            }
        }
        return detailId;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public long addEmpty(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName) throws Exception {
//        HouseOperateSalesVo houseOperateSalesVo = new HouseOperateSalesVo();
//        try {
//            houseOperateSalesVo = (HouseOperateSalesVo) FormUtils.mapToObject(map, HouseOperateSalesVo.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Long previousDetailId = houseOperateSalesVo.getDetailId();
        //验证房产当前是否有过房态操作
        OwnerHouseRelationship relationship = houseRelationshipMapper.loadOwnerRelationByHouseId(houseOperateSalesVo.getHouseId());
        if (relationship != null) {
            previousDetailId = relationship.getDetailId();
        }
        //业主房产操作---空关
        OwnerHouseBaseInfo houseBaseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
        houseOperateSalesVo.setPrecinctId(houseBaseInfo.getPrecinctId());
        OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
        BeanUtils.copyProperties(houseOperateSalesVo, ownerHouseStageDetail);
        ownerHouseStageDetail.setHouseId(houseOperateSalesVo.getHouseId());
        ownerHouseStageDetail.setHandleTime(houseOperateSalesVo.getApplyDate());
        ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.KONG_GUAN.getValue());
        ownerHouseStageDetail.setPreviousDetailId(previousDetailId);
        ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
        ownerHouseStageDetail.setRentStage(houseBaseInfo.getRentStage());
        ownerHouseStageDetail.setDecorateStage(houseBaseInfo.getDecorateStage());
        ownerHouseStageDetail.setRemark(houseOperateSalesVo.getRemark());
        ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
        ownerHouseStageDetail.setCreateUserId(userId);
        ownerHouseStageDetail.setCreateUserName(userName);
        ownerHouseStageDetail.setUpdateUserId(userId);
        ownerHouseStageDetail.setUpdateUserName(userName);

        //更新上一条入住列表的当前记录为历史记录
        Map<String, Object> updateNowDetailMap = new HashMap<>();
        List<String> houseOperateTypeList = new ArrayList<>();
        houseOperateTypeList.add(HouseOperateTypeEnum.RU_ZHU.getValue());
        houseOperateTypeList.add(HouseOperateTypeEnum.BAN_CHU.getValue());
        houseOperateTypeList.add(HouseOperateTypeEnum.KONG_GUAN.getValue());
        updateNowDetailMap.put("houseId", houseOperateSalesVo.getHouseId());
        updateNowDetailMap.put("isNowDetail", Constants.FALSE.toString());
        updateNowDetailMap.put("updateUserId", userId);
        updateNowDetailMap.put("updateUserName", userName);
        updateNowDetailMap.put("houseOperateTypeList", houseOperateTypeList);
        houseStageDetailMapper.updateNowDetail(updateNowDetailMap);
        ownerHouseStageDetail.setIsNowDetail(Constants.TRUE.toString());
        //保存新业主相关信息
        int index = houseStageDetailMapper.insertSelective(ownerHouseStageDetail);
        long detailId = ownerHouseStageDetail.getDetailId();
        if (index > 0) {
            //保存空关信息
            OwnerHouseStageExtendInfoEmpty extendInfoEmpty = new OwnerHouseStageExtendInfoEmpty();
            extendInfoEmpty.setDetailId(detailId);
            if (!CollectionUtils.isEmpty(houseOperateSalesVo.getTimeList())) {
                extendInfoEmpty.setEmtpyStartDate(houseOperateSalesVo.getTimeList().get(0));
                extendInfoEmpty.setEmptyEndDate(houseOperateSalesVo.getTimeList().get(1));
            }
//            extendInfoEmpty.setEmtpyStartDate(houseOperateSalesVo.getStartTime());
//            extendInfoEmpty.setEmptyEndDate(houseOperateSalesVo.getEndTime());
            emptyMapper.insertSelective(extendInfoEmpty);
            //修改当前记录为历史记录
            Map<String, Object> editRelationMap = new HashMap<>();
            editRelationMap.put("userId", userId);
            editRelationMap.put("preDetailId", previousDetailId);
            editRelationMap.put("houseId", houseOperateSalesVo.getHouseId());
            houseRelationshipMapper.editCurrentRecordFalse(editRelationMap);
            //新增空关记录为当前记录
            editRelationMap.put("detailId", ownerHouseStageDetail.getDetailId());
            houseRelationshipMapper.insertBatchForCurrent(editRelationMap);
            //更新房态
            OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
            baseInfo.setHouseId(houseOperateSalesVo.getHouseId());
            baseInfo.setUpdateUserId(userId);
            baseInfo.setUpdateUserName(userName);
            baseInfo.setStage(HouseStageEnum.KONG_GUAN.getValue());
            houseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
            //同步MongoDB
            SearchVo searchVo = new SearchVo();
            List<FilterEntity> filterList = new ArrayList<>();
            FilterEntity currentRecordEntity = new FilterEntity();
            currentRecordEntity.setFieldName("is_current_record");
            currentRecordEntity.setFieldValue(Constants.TRUE.toString());
            currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(currentRecordEntity);
            FilterEntity houseIdEntity = new FilterEntity();
            houseIdEntity.setFieldName("result.house_id");
            houseIdEntity.setFieldValue(houseOperateSalesVo.getHouseId().toString());
            houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
            filterList.add(houseIdEntity);
            searchVo.setFilterList(filterList);
            List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
            //更新上一条为历史数据
            HouseListEntity houseEntity = new HouseListEntity();
            houseEntity.setIsCurrentRecord(Constants.FALSE);
//            houseEntity.setIsCurrentRecordName("历史");
            houseEntity.setHouseId(houseOperateSalesVo.getHouseId());
//            houseEntity.setIsNowDetail(Constants.FALSE.toString());
            houseResultMapper.updateCurrentRecord(houseEntity);

            if (!CollectionUtils.isEmpty(houseListEntities)) {
                baseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseOperateSalesVo.getHouseId());
                //新增当前空关信息
                HouseListEntity houseListEntity = new HouseListEntity();
                BeanUtils.copyProperties(houseListEntities.get(0), houseListEntity);
                houseListEntity.setHouseStage(HouseStageEnum.KONG_GUAN.getValue());
                houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                houseListEntity.setIsCurrentRecord(Constants.TRUE);
                houseListEntity.setHouseOperateType(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
                houseListEntity.setHandleTime(houseOperateSalesVo.getApplyDate());
                if (!CollectionUtils.isEmpty(houseOperateSalesVo.getTimeList())) {
                    houseListEntity.setStartTime(houseOperateSalesVo.getTimeList().get(0));
                    houseListEntity.setEndTime(houseOperateSalesVo.getTimeList().get(1));
                }
//                houseListEntity.setStartTime(houseOperateSalesVo.getStartTime());
//                houseListEntity.setEndTime(houseOperateSalesVo.getEndTime());
                houseListEntity.setColumn0(houseOperateSalesVo.getColumn0());
                houseListEntity.setColumn1(houseOperateSalesVo.getColumn1());
                houseListEntity.setColumn2(houseOperateSalesVo.getColumn2());
                houseListEntity.setColumn3(houseOperateSalesVo.getColumn3());
                houseListEntity.setColumn4(houseOperateSalesVo.getColumn4());
                houseListEntity.setColumn5(houseOperateSalesVo.getColumn5());
                houseListEntity.setColumn6(houseOperateSalesVo.getColumn6());
                houseListEntity.setColumn7(houseOperateSalesVo.getColumn7());
                houseListEntity.setColumn8(houseOperateSalesVo.getColumn8());
                houseListEntity.setColumn9(houseOperateSalesVo.getColumn9());
//                houseEntity.setIsNowDetail(Constants.TRUE.toString());
                houseResultMapper.insertSelective(houseListEntity);
            }

        }
        return detailId;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int editHouseStage(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName) throws Exception {
        //非当前数据无法修改
        int index = 0;
        Map<String, Object> newRelationMap = new HashMap<>();
        newRelationMap.put("houseId", houseOperateSalesVo.getHouseId());
        newRelationMap.put("isCurrentRecord", true);
        newRelationMap.put("detailId", houseOperateSalesVo.getDetailId());
        newRelationMap.put("isDeleted", Constants.FALSE);
        List<OwnerHouseRelationship> newRelationships = houseRelationshipMapper.listOwnerByHouseId(newRelationMap);
        if (CollectionUtils.isEmpty(newRelationships)) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "历史数据无法修改");
        }
        OwnerHouseStageDetail detail = new OwnerHouseStageDetail();
        detail.setDetailId(houseOperateSalesVo.getDetailId());
        detail.setUpdateUserId(userId);
        detail.setUpdateUserName(userName);
        detail.setRemark(houseOperateSalesVo.getRemark());
        if (Constants.TRUE.equals(houseOperateSalesVo.getIsSublet())) {
            houseOperateSalesVo.setHouseOperateType(HouseOperateTypeEnum.ZHUAN_ZU.getValue());
        }
        String propertyOwnerNames = "";
        HouseOperateTypeEnum houseOperateTypeEnum = HouseOperateTypeEnum.getInstance(houseOperateSalesVo.getHouseOperateType());
        switch (houseOperateTypeEnum) {
            case SHOU_LOU:
            case ZHUAN_RANG:
                detail.setHandleTime(houseOperateSalesVo.getSalesDate());
                index = houseStageDetailMapper.updateByPrimaryKeySelective(detail);
                if (index > 0) {
                    //删除当前记录
                    Map<String, Object> editRelationMap = new HashMap<>();
                    editRelationMap.put("userId", userId);
                    editRelationMap.put("detailId", houseOperateSalesVo.getDetailId());
                    editRelationMap.put("isCurrentRecord", true);
                    houseRelationshipMapper.deleteRelationByDetail(editRelationMap);
                    //新业主房产关系
                    OwnerHouseRelationship houseRelationship = new OwnerHouseRelationship();
                    BeanUtils.copyProperties(houseOperateSalesVo, houseRelationship);
                    houseRelationship.setOwnerId(houseOperateSalesVo.getNewOwner().getOwnerId());
                    houseRelationship.setIsCurrentRecord(Constants.TRUE);
                    houseRelationship.setOwnerCategory(OwnerConstants.OWNER_CATEGORY_OWNER);
                    houseRelationship.setIsDeleted(Constants.FALSE);
                    houseRelationship.setCreateUserId(userId);
                    houseRelationship.setCreateUserName(userName);
                    houseRelationship.setUpdateUserId(userId);
                    houseRelationship.setUpdateUserName(userName);
                    //保存新业主的客户房产关系
                    houseRelationship.setDetailId(houseOperateSalesVo.getDetailId());
                    if (Constants.TRUE.equals(houseOperateSalesVo.getIsMainHouse())) {
                        //保存主房产
                        Map<String, Object> map = new HashMap<>();
                        List<Long> ownerIdList = new ArrayList<>();
                        ownerIdList.add(houseRelationship.getOwnerId());
                        map.put("ownerIdList", ownerIdList);
                        map.put("precinctId", houseOperateSalesVo.getPrecinctId());
                        mainHouseMapper.deleteMainHouse(map);
                        OwnerCustomerMainHouse mainHouse = new OwnerCustomerMainHouse();
                        BeanUtils.copyProperties(houseRelationship, mainHouse);
                        mainHouseMapper.insertSelective(mainHouse);
                    } else {
                        Map<String, Object> map = new HashMap<>();
                        List<Long> ownerIdList = new ArrayList<>();
                        ownerIdList.add(houseRelationship.getOwnerId());
                        map.put("ownerIdList", ownerIdList);
                        map.put("precinctId", houseOperateSalesVo.getPrecinctId());
                        mainHouseMapper.deleteMainHouse(map);
                    }
                    houseRelationshipMapper.insertSelective(houseRelationship);
                    //保存产权人信息
                    List<Long> propertyOwnerIdList = new ArrayList<>();
                    if (!CollectionUtils.isEmpty(houseOperateSalesVo.getNewPropertyOwnerList())) {
                        List<CustomerVo> propertyOwnerList = houseOperateSalesVo.getNewPropertyOwnerList();
                        List<OwnerHousePropertyOwner> propertyOwners = new ArrayList<>();
                        List<OwnerHouseRelationship> propertyOwnerHouseRelationshipList = new ArrayList<>();
                        Map<String, Object> map = new HashMap<>();
                        List<Long> ownerIdList = new ArrayList<>();
                        List<OwnerCustomerMainHouse> mainHouseList = new ArrayList<>();
                        for (CustomerVo customerVo : propertyOwnerList) {
                            propertyOwnerIdList.add(customerVo.getOwnerId());
                            if (StringUtils.hasLength(propertyOwnerNames)) {
                                propertyOwnerNames = propertyOwnerNames + "," + customerVo.getOwnerName();
                            } else {
                                propertyOwnerNames = propertyOwnerNames + customerVo.getOwnerName();
                            }
                            OwnerHousePropertyOwner propertyOwner = new OwnerHousePropertyOwner();
                            propertyOwner.setHouseId(houseOperateSalesVo.getHouseId());
                            propertyOwner.setHouseName(houseOperateSalesVo.getHouseName());
                            propertyOwner.setOwnerId(customerVo.getOwnerId());
                            propertyOwner.setOwnerName(customerVo.getOwnerName());
                            propertyOwner.setCreateUserId(userId);
                            propertyOwner.setCreateUserName(userName);
                            propertyOwner.setUpdateUserId(userId);
                            propertyOwner.setUpdateUserName(userName);
                            propertyOwner.setIsDeleted(Constants.FALSE);
                            propertyOwners.add(propertyOwner);

                            OwnerHouseRelationship propertyOwnerHouseRelationship = new OwnerHouseRelationship();
                            propertyOwnerHouseRelationship.setOwnerId(customerVo.getOwnerId());
                            propertyOwnerHouseRelationship.setDetailId(houseOperateSalesVo.getDetailId());
                            propertyOwnerHouseRelationship.setIsMainHouse(houseOperateSalesVo.getIsMainHouse());
                            propertyOwnerHouseRelationship.setPrecinctId(houseOperateSalesVo.getPrecinctId());
                            propertyOwnerHouseRelationship.setHouseId(houseOperateSalesVo.getHouseId());
                            propertyOwnerHouseRelationship.setOwnerCategory(OwnerConstants.OWNER_CATEGORY_PROPERTY);
                            propertyOwnerHouseRelationship.setIsCurrentRecord(Constants.TRUE);
                            propertyOwnerHouseRelationship.setIsDeleted(Constants.FALSE);
                            propertyOwnerHouseRelationship.setCreateUserId(userId);
                            propertyOwnerHouseRelationship.setCreateUserName(userName);
                            propertyOwnerHouseRelationship.setUpdateUserId(userId);
                            propertyOwnerHouseRelationship.setUpdateUserName(userName);

                            //判断每个产权人在该项目中的主房产
                            if (Constants.TRUE.equals(houseOperateSalesVo.getIsMainHouse())) {
                                ownerIdList.add(houseRelationship.getOwnerId());
                                map.put("ownerIdList", ownerIdList);
                                map.put("precinctId", houseOperateSalesVo.getPrecinctId());
                                OwnerCustomerMainHouse mainHouse = new OwnerCustomerMainHouse();
                                BeanUtils.copyProperties(houseRelationship, mainHouse);
                                mainHouseList.add(mainHouse);
                            }
                            propertyOwnerHouseRelationshipList.add(propertyOwnerHouseRelationship);
                        }
                        //保存主房产
                        if (!CollectionUtils.isEmpty(ownerIdList)) {
                            mainHouseMapper.deleteMainHouse(map);
                            if (!CollectionUtils.isEmpty(mainHouseList)) {
                                mainHouseMapper.insertBatch(mainHouseList);
                            }
                        }
                        //保存成员关系
                        List<OwnerCustomerFamilyInfo> addFamilyInfoList = new ArrayList<>();
                        List<OwnerCustomerFamilyInfo> familyInfos = familyMapper.listAllFamily(propertyOwnerIdList);
                        if (!CollectionUtils.isEmpty(familyInfos)) {
                            for (CustomerVo customerVo : propertyOwnerList) {
                                boolean flag = false;
                                for (OwnerCustomerFamilyInfo ownerCustomerFamilyInfo : familyInfos) {
                                    if (ownerCustomerFamilyInfo.getOwnerId().equals(customerVo.getOwnerId())) {
                                        if (ownerCustomerFamilyInfo.getRelationOwnerId().equals(houseOperateSalesVo.getNewOwner().getOwnerId())) {
                                            //关系存在，更新
                                            ownerCustomerFamilyInfo.setOwnerRelationship(customerVo.getOwnerRelationship());
                                            ownerCustomerFamilyInfo.setUpdateUserId(userId);
                                            ownerCustomerFamilyInfo.setUpdateUserName(userName);
                                            CustomerFamilyVo customerFamilyVo = new CustomerFamilyVo();
                                            BeanUtils.copyProperties(ownerCustomerFamilyInfo, customerFamilyVo);
                                            CustomerCallEnum customerCallEnum = OwnerUtils.getCallForFamily(customerFamilyVo);
                                            if (customerCallEnum != null) {
                                                ownerCustomerFamilyInfo.setRelationOwnerCall(customerCallEnum.getValue());
                                            }
                                            familyMapper.updateByPrimaryKeySelective(ownerCustomerFamilyInfo);
                                            //反向更新
                                            ownerCustomerFamilyInfo.setOwnerRelationship(customerVo.getOwnerRelationship());
                                            ownerCustomerFamilyInfo.setUpdateUserId(userId);
                                            ownerCustomerFamilyInfo.setUpdateUserName(userName);
                                            customerFamilyVo = new CustomerFamilyVo();
                                            BeanUtils.copyProperties(ownerCustomerFamilyInfo, customerFamilyVo);
                                            customerFamilyVo.setOwnerId(customerFamilyVo.getRelationOwnerId());
                                            customerFamilyVo.setRelationOwnerId(customerFamilyVo.getOwnerId());
                                            CustomerRelationEnum contraryRelationEnum = OwnerUtils.getContraryForFamily(customerFamilyVo);
                                            ownerCustomerFamilyInfo.setOwnerRelationship(contraryRelationEnum.getValue());
                                            customerFamilyVo.setOwnerRelationship(contraryRelationEnum.getValue());
                                            customerCallEnum = OwnerUtils.getCallForFamily(customerFamilyVo);
                                            if (customerCallEnum != null) {
                                                ownerCustomerFamilyInfo.setRelationOwnerCall(customerCallEnum.getValue());
                                            }
                                            familyMapper.updateByPrimaryKeySelective(ownerCustomerFamilyInfo);
                                            flag = false;
                                            break;
                                        } else {
                                            flag = true;
                                        }
                                    }
                                }
                                if (flag) {
                                    //关系不存在，新增
                                    CustomerFamilyVo customerFamilyVo = new CustomerFamilyVo();
                                    customerFamilyVo.setOwnerId(customerVo.getOwnerId());
                                    customerFamilyVo.setOwnerRelationship(customerVo.getOwnerRelationship());
                                    customerFamilyVo.setCustomerVo(customerVo);
                                    customerFamilyVo.setRelationOwnerId(houseOperateSalesVo.getNewOwner().getOwnerId());
                                    OwnerCustomerFamilyInfo familyInfo = new OwnerCustomerFamilyInfo();
                                    BeanUtils.copyProperties(customerFamilyVo, familyInfo);
                                    familyInfo.setCreateUserId(userId);
                                    familyInfo.setCreateUserName(userName);
                                    familyInfo.setUpdateUserId(userId);
                                    familyInfo.setUpdateUserName(userName);
                                    CustomerCallEnum customerCallEnum = OwnerUtils.getCallForFamily(customerFamilyVo);
                                    if (customerCallEnum != null) {
                                        familyInfo.setRelationOwnerCall(customerCallEnum.getValue());
                                    }
                                    addFamilyInfoList.add(familyInfo);
                                    //新增反向关系
                                    customerFamilyVo.setOwnerId(houseOperateSalesVo.getNewOwner().getOwnerId());
                                    customerFamilyVo.setRelationOwnerId(customerVo.getOwnerId());
                                    familyInfo = new OwnerCustomerFamilyInfo();
                                    BeanUtils.copyProperties(customerFamilyVo, familyInfo);
                                    familyInfo.setCreateUserId(userId);
                                    familyInfo.setCreateUserName(userName);
                                    familyInfo.setUpdateUserId(userId);
                                    familyInfo.setUpdateUserName(userName);
                                    CustomerRelationEnum contraryRelationEnum = OwnerUtils.getContraryForFamily(customerFamilyVo);
                                    customerFamilyVo.setOwnerRelationship(contraryRelationEnum.getValue());
                                    familyInfo.setOwnerRelationship(contraryRelationEnum.getValue());
                                    customerCallEnum = OwnerUtils.getCallForFamily(customerFamilyVo);
                                    if (customerCallEnum != null) {
                                        familyInfo.setRelationOwnerCall(customerCallEnum.getValue());
                                    }
                                    addFamilyInfoList.add(familyInfo);
                                }
                            }
                        }
                        if (!CollectionUtils.isEmpty(addFamilyInfoList)) {
                            familyMapper.insertBatch(addFamilyInfoList);
                        }
                        //更新新业主是否有家庭成员
                        OwnerCustomerResult customerResult = new OwnerCustomerResult();
                        customerResult.setOwnerId(houseOperateSalesVo.getNewOwner().getOwnerId());
                        customerResult.setHasFamily(Constants.TRUE.toString());
                        customerResult.setUpdateUserId(userId);
                        customerResult.setUpdateUserName(userName);
                        customerResultMapper.updateByPrimaryKeySelective(customerResult);
                        //保存产权人信息
                        housePropertyOwnerMapper.insertBatch(propertyOwners);
                        //保存新产权人的客户房产关系
                        houseRelationshipMapper.insertBatch(propertyOwnerHouseRelationshipList);
                    }
                }
                break;
            case SHOU_FANG:
                detail.setHandleTime(houseOperateSalesVo.getTakeDate());
                index = houseStageDetailMapper.updateByPrimaryKeySelective(detail);
                break;
            case ZHUANG_XIU:
                detail.setHandleTime(houseOperateSalesVo.getApplyDate());
                index = houseStageDetailMapper.updateByPrimaryKeySelective(detail);
                OwnerHouseStageExtendInfoDecorate decorate = decorateMapper.selectByPrimaryKey(houseOperateSalesVo.getDetailId());
                decorate.setApplyDate(houseOperateSalesVo.getApplyDate());
                if (!CollectionUtils.isEmpty(houseOperateSalesVo.getTimeList())) {
                    decorate.setDecorateStartDate(houseOperateSalesVo.getTimeList().get(0));
                    decorate.setDecorateEndDate(houseOperateSalesVo.getTimeList().get(1));
                }
//            decorate.setDecorateStartDate(houseOperateSalesVo.getStartTime());
//            decorate.setDecorateEndDate(houseOperateSalesVo.getEndTime());
                decorate.setUpdateUserId(userId);
                decorate.setUpdateUserName(userName);
                decorateMapper.updateByPrimaryKeySelective(decorate);
                break;
            case CHU_ZU:
                detail.setHandleTime(houseOperateSalesVo.getApplyDate());
                index = houseStageDetailMapper.updateByPrimaryKeySelective(detail);
                OwnerHouseStageExtendInfoRent rent = rentMapper.selectByPrimaryKey(houseOperateSalesVo.getDetailId());
                if (!CollectionUtils.isEmpty(houseOperateSalesVo.getTimeList())) {
                    rent.setRentStartDate(houseOperateSalesVo.getTimeList().get(0));
                    rent.setRentEndDate(houseOperateSalesVo.getTimeList().get(1));
                }
//            rent.setRentStartDate(houseOperateSalesVo.getStartTime());
//            rent.setRentEndDate(houseOperateSalesVo.getEndTime());
                rent.setRentOwnerId(houseOperateSalesVo.getNewRentOwner().getOwnerId());
                rentMapper.updateByPrimaryKeySelective(rent);
                break;
            case ZHUAN_ZU:
                //退租
                detail.setDetailId(houseOperateSalesVo.getOtherDetailId());
                detail.setHandleTime(houseOperateSalesVo.getApplyDate());
                index = houseStageDetailMapper.updateByPrimaryKeySelective(detail);

                //出租
                detail.setDetailId(houseOperateSalesVo.getDetailId());
                detail.setHandleTime(houseOperateSalesVo.getApplyDate());
                index = houseStageDetailMapper.updateByPrimaryKeySelective(detail);
                OwnerHouseStageExtendInfoRent newRent = rentMapper.selectByPrimaryKey(houseOperateSalesVo.getDetailId());
                if (!CollectionUtils.isEmpty(houseOperateSalesVo.getTimeList())) {
                    newRent.setRentStartDate(houseOperateSalesVo.getTimeList().get(0));
                    newRent.setRentEndDate(houseOperateSalesVo.getTimeList().get(1));
                }
//            newRent.setRentStartDate(houseOperateSalesVo.getStartTime());
//            newRent.setRentEndDate(houseOperateSalesVo.getEndTime());
                newRent.setRentOwnerId(houseOperateSalesVo.getNewRentOwner().getOwnerId());
                rentMapper.updateByPrimaryKeySelective(newRent);
                break;
            case TUI_ZU:
                detail.setHandleTime(houseOperateSalesVo.getApplyDate());
                index = houseStageDetailMapper.updateByPrimaryKeySelective(detail);
                break;
            case RU_ZHU:
                detail.setHandleTime(houseOperateSalesVo.getCheckInDate());
                index = houseStageDetailMapper.updateByPrimaryKeySelective(detail);
                break;
            case BAN_CHU:
                detail.setHandleTime(houseOperateSalesVo.getApplyDate());
                index = houseStageDetailMapper.updateByPrimaryKeySelective(detail);
                break;
            default:
                break;
        }
        //同步结果表
        HouseListEntity houseListEntity = new HouseListEntity();
        BeanUtils.copyProperties(detail, houseListEntity);
        houseListEntity.setHouseId(houseOperateSalesVo.getHouseId());
        if (StringUtils.hasLength(propertyOwnerNames)) {
            houseListEntity.setPropertyOwnerNames(propertyOwnerNames.substring(0, propertyOwnerNames.length() - 1));
        }
        if (!CollectionUtils.isEmpty(houseOperateSalesVo.getTimeList())) {
            houseListEntity.setStartTime(houseOperateSalesVo.getTimeList().get(0));
            houseListEntity.setEndTime(houseOperateSalesVo.getTimeList().get(1));
        }
        houseResultMapper.updateByDetailId(houseListEntity);
        return index;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public HouseOperateSalesVo detailHouseStage(Long houseId, Long detailId, String houseOperateType, Byte isEdit) throws Exception {
        OwnerHouseBaseInfo houseBaseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseId);
        if (houseBaseInfo == null) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "该房产不存在");
        }
        HouseOperateSalesVo houseOperateSalesVo = new HouseOperateSalesVo();
        houseOperateSalesVo.setPrecinctId(houseBaseInfo.getPrecinctId());
        //获取当前业主与产权人
        Map<String, Object> newRelationMap = new HashMap<>();
        newRelationMap.put("houseId", houseId);
        newRelationMap.put("isCurrentRecord", true);
        newRelationMap.put("isDeleted", Constants.FALSE);
        List<OwnerHouseRelationship> newRelationships = houseRelationshipMapper.listOwnerByHouseId(newRelationMap);
        List<Long> newOwnerIdList = new ArrayList<>();
        List<OwnerCustomerFamilyInfo> familyList = new ArrayList<>();
        Map<Byte, List<OwnerHouseRelationship>> newMap = newRelationships.stream().collect(Collectors.groupingBy(OwnerHouseRelationship::getOwnerCategory));
        //业主
        List<OwnerHouseRelationship> ownerRelationList = newMap.get(OwnerConstants.OWNER_CATEGORY_OWNER);
        if (ownerRelationList != null) {
            houseOperateSalesVo.setDetailId(ownerRelationList.get(0).getDetailId());
            houseOperateSalesVo.setIsMainHouse(ownerRelationList.get(0).getIsMainHouse());
            ownerRelationList.forEach(relation -> {
                newOwnerIdList.add(relation.getOwnerId());
            });
            if (!CollectionUtils.isEmpty(newOwnerIdList)) {
                Map<String, Object> mainHouseMap = new HashMap<>();
                mainHouseMap.put("list", newOwnerIdList);
                mainHouseMap.put("houseId", houseId);
                List<OwnerCustomerMainHouse> mainHouses = mainHouseMapper.listByOwnerHouse(mainHouseMap);
                if (!CollectionUtils.isEmpty(mainHouses)) {
                    houseOperateSalesVo.setIsMainHouse(Constants.TRUE);
                } else {
                    houseOperateSalesVo.setIsMainHouse(Constants.FALSE);
                }
                List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(newOwnerIdList);
//                houseOperateSalesVo.setNewOwner(ownerList.get(0));
                if (!CollectionUtils.isEmpty(ownerList)) {
                    if (HouseOperateTypeEnum.SHOU_LOU.getValue().equals(houseOperateType)
                            || HouseOperateTypeEnum.ZHUAN_RANG.getValue().equals(houseOperateType)) {
                        if (Constants.TRUE.equals(isEdit)) {
                            houseOperateSalesVo.setNewOwner(ownerList.get(0));
                        } else {
                            houseOperateSalesVo.setOldOwner(ownerList.get(0));
                        }
                    } else {
                        houseOperateSalesVo.setNewOwner(ownerList.get(0));
                    }
                }
            }
            familyList = familyMapper.listFamilyByOwnerId(ownerRelationList.get(0).getOwnerId());
        }
        //产权人
        List<OwnerHouseRelationship> propertyOwnerList = newMap.get(OwnerConstants.OWNER_CATEGORY_PROPERTY);
        if (propertyOwnerList != null) {
            newOwnerIdList.clear();
            propertyOwnerList.forEach(relation -> {
                newOwnerIdList.add(relation.getOwnerId());
            });
            if (!CollectionUtils.isEmpty(newOwnerIdList)) {
                List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(newOwnerIdList);
                for (CustomerVo customerVo : ownerList) {
                    for (OwnerCustomerFamilyInfo family : familyList) {
                        if (family.getOwnerId().equals(customerVo.getOwnerId())) {
                            customerVo.setOwnerRelationship(family.getOwnerRelationship());
                        }
                    }
                }
//                houseOperateSalesVo.setNewPropertyOwnerList(ownerList);
                if (HouseOperateTypeEnum.SHOU_LOU.getValue().equals(houseOperateType)
                        || HouseOperateTypeEnum.ZHUAN_RANG.getValue().equals(houseOperateType)) {
                    if (Constants.TRUE.equals(isEdit)) {
                        houseOperateSalesVo.setNewPropertyOwnerList(ownerList);
                    } else {
                        houseOperateSalesVo.setOldPropertyOwnerList(ownerList);
                    }
                } else {
                    houseOperateSalesVo.setNewPropertyOwnerList(ownerList);
                }
            }
        }
        //获取当前出租人
        List<OwnerHouseRelationship> relationships = houseRelationshipMapper.listOwnerRelationHistoryByHouseId(houseId);
        List<OwnerHouseStageDetail> rentDetailList = new ArrayList<>();
        relationships.forEach(relation -> {
            OwnerHouseStageDetail detail = new OwnerHouseStageDetail();
            detail.setDetailId(relation.getDetailId());
            rentDetailList.add(detail);
        });
        if (!CollectionUtils.isEmpty(rentDetailList)) {
            List<OwnerHouseStageExtendInfoRent> rentList = rentMapper.listAllRentByDetail(rentDetailList);
            if (!CollectionUtils.isEmpty(rentList)) {
                OwnerHouseStageExtendInfoRent rent = rentList.get(rentList.size() - 1);
                CustomerVo rentOwner = customerBaseInfoMapper.loadCustomer(rent.getRentOwnerId());
                if (HouseOperateTypeEnum.ZHUAN_ZU.getValue().equals(houseOperateType)) {
                    if (Constants.TRUE.equals(isEdit)) {
                        houseOperateSalesVo.setNewRentOwner(rentOwner);
                    } else {
                        houseOperateSalesVo.setOldRentOwner(rentOwner);
                    }
                } else if (HouseOperateTypeEnum.TUI_ZU.getValue().equals(houseOperateType)) {
                    houseOperateSalesVo.setOldRentOwner(rentOwner);
                } else {
                    houseOperateSalesVo.setNewRentOwner(rentOwner);
                }
            }
        }
        //新增收房时获取房产售楼记录
        if (HouseStageEnum.KONG_ZHI.getValue().equals(houseBaseInfo.getStage())
                && HouseOperateTypeEnum.SHOU_FANG.getValue().equals(houseOperateType)) {
            Map<String, Object> detailMap = new HashMap<>();
            detailMap.put("houseId", houseId);
            List<String> list = new ArrayList<>();
            list.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
            list.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
            detailMap.put("list", list);
            OwnerHouseStageDetail detail = houseStageDetailMapper.loadDetail(detailMap);
//            OwnerHouseRelationship nowRelationship =  houseRelationshipMapper.loadOwnerRelationByHouseId(houseId);
//            if (nowRelationship != null) {
//                OwnerHouseStageDetail detail = houseStageDetailMapper.selectByPrimaryKey(nowRelationship.getDetailId()); 
            if (detail != null) {
                BeanUtils.copyProperties(detail, houseOperateSalesVo);
                houseOperateSalesVo.setSalesDate(detail.getHandleTime());
                houseOperateSalesVo.setPreviousDetailId(detail.getDetailId());
            }
//            }
        }
        //转让时获取原业主入住时间
        if (HouseOperateTypeEnum.ZHUAN_RANG.getValue().equals(houseOperateType)) {
            Map<String, Object> detailMap = new HashMap<>();
            detailMap.put("houseId", houseId);
            List<String> list = new ArrayList<>();
            list.add(HouseOperateTypeEnum.RU_ZHU.getValue());
            detailMap.put("list", list);
            OwnerHouseStageDetail detail = houseStageDetailMapper.loadDetail(detailMap);
//            OwnerHouseRelationship nowRelationship =  houseRelationshipMapper.loadOwnerRelationByHouseId(houseId);
//            if (nowRelationship != null) {
//                OwnerHouseStageDetail detail = houseStageDetailMapper.selectByPrimaryKey(nowRelationship.getDetailId()); 
            if (detail != null) {
                BeanUtils.copyProperties(detail, houseOperateSalesVo);
                houseOperateSalesVo.setOldCheckInDate(detail.getHandleTime());
            }
//            }
        }
        if (CommonUtils.isObjectEmpty(detailId)) {
            //获取房产当前状态
            HouseListEntity houseListEntity = houseResultMapper.selectByPrimaryKey(houseId);
            if (houseListEntity != null) {
                if (houseListEntity.getHouseOperateType().equals(houseOperateType)) {
                    detailId = houseListEntity.getDetailId();
                }
            }
        }
        if (!CommonUtils.isObjectEmpty(detailId) && Constants.TRUE.equals(isEdit)) {
            OwnerHouseStageDetail ownerHouseStageDetail = houseStageDetailMapper.selectByPrimaryKey(detailId);
            if (ownerHouseStageDetail == null) {
                BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "操作数据不存在");
            }
            BeanUtils.copyProperties(ownerHouseStageDetail, houseOperateSalesVo);
            List<Long> rentOwnerIdList = new ArrayList<>();

            HouseOperateTypeEnum houseOperateTypeEnum = HouseOperateTypeEnum.getInstance(ownerHouseStageDetail.getHouseOperateType());
            if (!HouseOperateTypeEnum.SHOU_LOU.equals(houseOperateTypeEnum)) {
                //获取操作当时的当前业主及产权人
                newRelationMap = new HashMap<>();
                newRelationMap.put("houseId", houseId);
                newRelationMap.put("detailId", detailId);
                newRelationMap.put("isDeleted", Constants.FALSE);
                newRelationships = houseRelationshipMapper.listOwnerByHouseId(newRelationMap);
                if (!CollectionUtils.isEmpty(newRelationships)) {
                    newOwnerIdList.clear();
                    newMap = newRelationships.stream().collect(Collectors.groupingBy(OwnerHouseRelationship::getOwnerCategory));
                    //业主
                    List<OwnerHouseRelationship> newOwnerRelationList = newMap.get(OwnerConstants.OWNER_CATEGORY_OWNER);
                    if (newOwnerRelationList != null) {
                        newOwnerRelationList.forEach(relation -> {
                            newOwnerIdList.add(relation.getOwnerId());
                        });
                        if (!CollectionUtils.isEmpty(newOwnerIdList)) {
                            List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(newOwnerIdList);

                            if (!CollectionUtils.isEmpty(ownerList)) {
                                houseOperateSalesVo.setNewOwner(ownerList.get(0));
                            }
                        }
                        familyList = familyMapper.listFamilyByOwnerId(newOwnerRelationList.get(0).getOwnerId());
                    }
                    //产权人
                    List<OwnerHouseRelationship> newPropertyOwnerList = newMap.get(OwnerConstants.OWNER_CATEGORY_PROPERTY);
                    if (newPropertyOwnerList != null) {
                        newOwnerIdList.clear();
                        newPropertyOwnerList.forEach(relation -> {
                            newOwnerIdList.add(relation.getOwnerId());
                        });
                        if (!CollectionUtils.isEmpty(newOwnerIdList)) {
                            List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(newOwnerIdList);
                            for (CustomerVo customerVo : ownerList) {
                                for (OwnerCustomerFamilyInfo family : familyList) {
                                    if (family.getOwnerId().equals(customerVo.getOwnerId())) {
                                        customerVo.setOwnerRelationship(family.getOwnerRelationship());
                                    }
                                }
                            }
                            houseOperateSalesVo.setNewPropertyOwnerList(ownerList);
                        }
                    }
                }
            }
            switch (houseOperateTypeEnum) {
                case SHOU_LOU:
                    //获取操作时的业主及产权人
                    Map<String, Object> oldRelationMap = new HashMap<>();
                    oldRelationMap.put("houseId", houseId);
                    oldRelationMap.put("detailId", detailId);
                    oldRelationMap.put("isDeleted", Constants.FALSE);
                    List<OwnerHouseRelationship> oldRelationships = houseRelationshipMapper.listOwnerByHouseId(oldRelationMap);
                    if (!CollectionUtils.isEmpty(oldRelationships)) {
                        List<Long> oldOwnerIdList = new ArrayList<>();
                        Map<Byte, List<OwnerHouseRelationship>> oldMap = oldRelationships.stream().collect(Collectors.groupingBy(OwnerHouseRelationship::getOwnerCategory));
                        //业主
                        List<OwnerHouseRelationship> oldOwnerRelationList = oldMap.get(OwnerConstants.OWNER_CATEGORY_OWNER);
                        if (oldOwnerRelationList != null) {
                            oldOwnerRelationList.forEach(relation -> {
                                oldOwnerIdList.add(relation.getOwnerId());
                            });
                            if (!CollectionUtils.isEmpty(oldOwnerIdList)) {
                                List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(oldOwnerIdList);

                                if (!CollectionUtils.isEmpty(ownerList)) {
                                    houseOperateSalesVo.setNewOwner(ownerList.get(0));
                                }
                            }
                            familyList = familyMapper.listFamilyByOwnerId(oldOwnerRelationList.get(0).getOwnerId());
                        }
                        //产权人
                        List<OwnerHouseRelationship> oldPropertyOwnerList = oldMap.get(OwnerConstants.OWNER_CATEGORY_PROPERTY);
                        if (oldPropertyOwnerList != null) {
                            oldOwnerIdList.clear();
                            oldPropertyOwnerList.forEach(relation -> {
                                oldOwnerIdList.add(relation.getOwnerId());
                            });
                            if (!CollectionUtils.isEmpty(oldOwnerIdList)) {
                                List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(oldOwnerIdList);
                                for (CustomerVo customerVo : ownerList) {
                                    for (OwnerCustomerFamilyInfo family : familyList) {
                                        if (family.getOwnerId().equals(customerVo.getOwnerId())) {
                                            customerVo.setOwnerRelationship(family.getOwnerRelationship());
                                        }
                                    }
                                }
                                houseOperateSalesVo.setNewPropertyOwnerList(ownerList);
                            }
                        }
                    }
                    houseOperateSalesVo.setSalesDate(ownerHouseStageDetail.getHandleTime());
                    break;
                case ZHUAN_RANG:
                    //转让显示原业主与产权人
                    Map<String, Object> detailMap = new HashMap<>();
                    detailMap.put("houseId", houseId);
                    List<String> list = new ArrayList<>();
                    list.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
                    list.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
                    detailMap.put("list", list);
                    detailMap.put("isNowDetail", Constants.FALSE.toString());
                    List<OwnerHouseStageDetail> detailList = houseStageDetailMapper.listStage(detailMap);
                    if (!CollectionUtils.isEmpty(detailList)) {
                        if (detailList.size() > 1) {
                            Long tempDetailId = detailId;
                            detailList.removeIf(detail -> detail.getDetailId().compareTo(tempDetailId) >= 0);
                        }
                        if (!CollectionUtils.isEmpty(detailList)) {
                            //获取操作当时原业主及产权人
                            oldRelationMap = new HashMap<>();
                            oldRelationMap.put("houseId", houseId);
                            oldRelationMap.put("detailId", detailList.get(detailList.size() - 1).getDetailId());
                            oldRelationMap.put("isDeleted", Constants.FALSE);
                            oldRelationships = houseRelationshipMapper.listOwnerByHouseId(oldRelationMap);
                            if (!CollectionUtils.isEmpty(oldRelationships)) {
                                List<Long> oldOwnerIdList = new ArrayList<>();
                                Map<Byte, List<OwnerHouseRelationship>> oldMap = oldRelationships.stream().collect(Collectors.groupingBy(OwnerHouseRelationship::getOwnerCategory));
                                //业主
                                List<OwnerHouseRelationship> oldOwnerRelationList = oldMap.get(OwnerConstants.OWNER_CATEGORY_OWNER);
                                if (oldOwnerRelationList != null) {
                                    oldOwnerRelationList.forEach(relation -> {
                                        oldOwnerIdList.add(relation.getOwnerId());
                                    });
                                    if (!CollectionUtils.isEmpty(oldOwnerIdList)) {
                                        List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(oldOwnerIdList);

                                        if (!CollectionUtils.isEmpty(ownerList)) {
                                            houseOperateSalesVo.setOldOwner(ownerList.get(0));
                                        }
                                    }
                                    familyList = familyMapper.listFamilyByOwnerId(oldOwnerRelationList.get(0).getOwnerId());
                                }
                                //产权人
                                List<OwnerHouseRelationship> oldPropertyOwnerList = oldMap.get(OwnerConstants.OWNER_CATEGORY_PROPERTY);
                                if (oldPropertyOwnerList != null) {
                                    oldOwnerIdList.clear();
                                    oldPropertyOwnerList.forEach(relation -> {
                                        oldOwnerIdList.add(relation.getOwnerId());
                                    });
                                    if (!CollectionUtils.isEmpty(oldOwnerIdList)) {
                                        List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(oldOwnerIdList);
                                        for (CustomerVo customerVo : ownerList) {
                                            for (OwnerCustomerFamilyInfo family : familyList) {
                                                if (family.getOwnerId().equals(customerVo.getOwnerId())) {
                                                    customerVo.setOwnerRelationship(family.getOwnerRelationship());
                                                }
                                            }
                                        }
                                        houseOperateSalesVo.setOldPropertyOwnerList(ownerList);
                                    }
                                }
                                houseOperateSalesVo.setSalesDate(ownerHouseStageDetail.getHandleTime());
                            }
                        }
                    }

                    break;
                case SHOU_FANG:
                    houseOperateSalesVo.setTakeDate(ownerHouseStageDetail.getHandleTime());
                    break;
                case ZHUANG_XIU:
                    OwnerHouseStageExtendInfoDecorate decorate = decorateMapper.selectByPrimaryKey(detailId);
                    if (decorate != null) {
                        houseOperateSalesVo.setApplyDate(decorate.getApplyDate());
                        List<Date> timeList = new ArrayList<>(2);
                        timeList.add(decorate.getDecorateStartDate());
                        timeList.add(decorate.getDecorateEndDate());
                        houseOperateSalesVo.setTimeList(timeList);
//                    houseOperateSalesVo.setStartTime(decorate.getDecorateStartDate());
//                    houseOperateSalesVo.setEndTime(decorate.getDecorateEndDate());
                    }
                    break;
                case CHU_ZU:
                    OwnerHouseStageExtendInfoRent rent = rentMapper.selectByPrimaryKey(detailId);
                    if (rent != null) {
                        List<Date> timeList = new ArrayList<>(2);
                        timeList.add(rent.getRentStartDate());
                        timeList.add(rent.getRentEndDate());
                        houseOperateSalesVo.setTimeList(timeList);
//                    houseOperateSalesVo.setStartTime(rent.getRentStartDate());
//                    houseOperateSalesVo.setEndTime(rent.getRentEndDate());
                        rentOwnerIdList.add(rent.getRentOwnerId());
                        if (!CollectionUtils.isEmpty(rentOwnerIdList)) {
                            List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(rentOwnerIdList);
                            if (!CollectionUtils.isEmpty(ownerList)) {
                                houseOperateSalesVo.setNewRentOwner(ownerList.get(0));
                            }
                        }
                        if (Constants.TRUE.equals(ownerHouseStageDetail.getIsSublet())) {
                            OwnerHouseStageExtendInfoRent preRent = rentMapper.selectByPrimaryKey(ownerHouseStageDetail.getPreviousDetailId());
                            if (preRent != null) {
                                List<Long> preOwnerIdList = new ArrayList<>();
                                houseOperateSalesVo.setOtherDetailId(preRent.getDetailId());
                                List<Date> rentTimeList = new ArrayList<>(2);
                                rentTimeList.add(preRent.getRentStartDate());
                                rentTimeList.add(preRent.getRentEndDate());
                                houseOperateSalesVo.setTimeList(rentTimeList);
//                            houseOperateSalesVo.setStartTime(preRent.getRentStartDate());
//                            houseOperateSalesVo.setEndTime(preRent.getRentEndDate());
                                preOwnerIdList.add(preRent.getRentOwnerId());
                                if (!CollectionUtils.isEmpty(preOwnerIdList)) {
                                    List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(preOwnerIdList);
                                    if (!CollectionUtils.isEmpty(ownerList)) {
                                        houseOperateSalesVo.setOldRentOwner(ownerList.get(0));
                                    }
                                }
                            }
                        }
                    }
                    break;
                case TUI_ZU:
                    houseOperateSalesVo.setApplyDate(ownerHouseStageDetail.getHandleTime());
                    OwnerHouseStageExtendInfoRent rentOut = rentMapper.selectByPrimaryKey(detailId);
                    if (rentOut != null) {
                        rentOwnerIdList.add(rentOut.getRentOwnerId());
                        if (!CollectionUtils.isEmpty(rentOwnerIdList)) {
                            List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(rentOwnerIdList);
                            if (!CollectionUtils.isEmpty(ownerList)) {
                                houseOperateSalesVo.setOldRentOwner(ownerList.get(0));
                            }
                        }
                        if (Constants.TRUE.equals(ownerHouseStageDetail.getIsSublet())) {
                            Map<String, Object> subletMap = new HashMap<>();
                            subletMap.put("houseId", houseId);
                            subletMap.put("detailId", detailId);
                            OwnerHouseStageDetail rentDetail = houseStageDetailMapper.loadRentForSublet(subletMap);
                            OwnerHouseStageExtendInfoRent preRent = rentMapper.selectByPrimaryKey(rentDetail.getDetailId());
                            houseOperateSalesVo.setOtherDetailId(detailId);
                            houseOperateSalesVo.setDetailId(preRent.getDetailId());
                            List<Date> rentTimeList = new ArrayList<>(2);
                            rentTimeList.add(preRent.getRentStartDate());
                            rentTimeList.add(preRent.getRentEndDate());
                            houseOperateSalesVo.setTimeList(rentTimeList);
//                        houseOperateSalesVo.setStartTime(preRent.getRentStartDate());
//                        houseOperateSalesVo.setEndTime(preRent.getRentEndDate());
                            rentOwnerIdList.clear();
                            rentOwnerIdList.add(preRent.getRentOwnerId());
                            if (!CollectionUtils.isEmpty(rentOwnerIdList)) {
                                List<CustomerVo> ownerList = customerBaseInfoMapper.listCustomerById(rentOwnerIdList);
                                if (!CollectionUtils.isEmpty(ownerList)) {
                                    houseOperateSalesVo.setNewRentOwner(ownerList.get(0));
                                }
                            }
                        }
                    }
                    break;
                case RU_ZHU:
                    houseOperateSalesVo.setCheckInDate(ownerHouseStageDetail.getHandleTime());
                    break;
                case BAN_CHU:
                    houseOperateSalesVo.setApplyDate(ownerHouseStageDetail.getHandleTime());
                    break;
                default:
                    break;
            }
        }

        return houseOperateSalesVo;
    }

    @SuppressWarnings("unchecked")
    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public PageInfo<HouseListVo> listPage(SearchVo searchVo, String houseOperateType, Map<String, Object> columnMap, boolean pageFlag) throws Exception {
        PageInfo<HouseListVo> pageInfo = new PageInfo<>();
        Map<String, Object> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(searchVo.getTreeConditions())) {
            for (FilterEntity treeFilterEntity : searchVo.getTreeConditions()) {
                FilterEntity searchFilter = new FilterEntity();
                if ("0".equals(treeFilterEntity.getFieldValue())) {
                    searchFilter.setComparison(Constants.COMPARISON_LIKE);
                    searchFilter.setFieldName("path");
                    searchFilter.setFieldValue(Constants.SEPARATOR_PATH);
                } else {
                    OwnerHouseBaseInfo searchBase = houseBaseInfoMapper.selectByPrimaryKey(Long.valueOf(treeFilterEntity.getFieldValue()));
                    if (HouseTypeEnum.ROOM.getValue().equals(searchBase.getHouseType())
                            || HouseTypeEnum.CARPORT.getValue().equals(searchBase.getHouseType())
                            || HouseTypeEnum.PUBLICAREA.getValue().equals(searchBase.getHouseType())) {
                        searchFilter.setComparison(Constants.COMPARISON_EQUAL);
                        searchFilter.setFieldName("result.houseId");
                        searchFilter.setFieldValue(searchBase.getHouseId().toString());
                    } else {
                        searchFilter.setComparison(Constants.COMPARISON_LIKE);
                        searchFilter.setFieldName("path");
                        searchFilter.setFieldValue(searchBase.getPath() + searchBase.getHouseId() + Constants.SEPARATOR_PATH);
                    }
                }
                searchVo.getFilterList().add(searchFilter);
            }
        }
        //result表创建时间字段与detail表重复，需要特殊处理增加表别名
        if (!CollectionUtils.isEmpty(searchVo.getFilterList())) {
            for (FilterEntity filterEntity : searchVo.getFilterList()) {
                if (filterEntity.getFieldName().equals("create_time")) {
                    filterEntity.setFieldName("detail.create_time");
                }
            }
        }
        if (StringUtils.hasLength(searchVo.getOrderFieldName()) && searchVo.getOrderFieldName().equals("create_time")) {
            searchVo.setOrderFieldName("detail.create_time");
        }
        map.put("searchVo", searchVo);
        map.put("houseOperateType", houseOperateType);
        List<HouseListEntity> resultList = new ArrayList<>();
        if (pageFlag) {
            PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
            resultList = houseResultMapper.listResultBySearchForOperate(map);
        } else {
            resultList = houseResultMapper.listResultBySearchForOperate(map);
        }

        PageInfo<HouseListEntity> pageEntity = new PageInfo<>(resultList);
        List<HouseListVo> voList = new ArrayList<>();
        for (HouseListEntity houseListEntity : resultList) {
            HouseListVo houseListVo = new HouseListVo();
            BeanUtils.copyProperties(houseListEntity, houseListVo);
            houseListVo.setAssistChargingArea(CommonUtils.long2Decimal(houseListEntity.getAssistChargingArea()));
            houseListVo.setBasementArea(CommonUtils.long2Decimal(houseListEntity.getBasementArea()));
            houseListVo.setBuildingArea(CommonUtils.long2Decimal(houseListEntity.getBuildingArea()));
            houseListVo.setChargingArea(CommonUtils.long2Decimal(houseListEntity.getChargingArea()));
            houseListVo.setGardenArea(CommonUtils.long2Decimal(houseListEntity.getGardenArea()));
            houseListVo.setGiftArea(CommonUtils.long2Decimal(houseListEntity.getGiftArea()));
            houseListVo.setInsideArea(CommonUtils.long2Decimal(houseListEntity.getInsideArea()));
            houseListVo.setPoolArea(CommonUtils.long2Decimal(houseListEntity.getPoolArea()));
            if (HouseStageEnum.KONG_ZHI.getValue().equals(houseListVo.getHouseStage())) {
                houseListVo.setIsNowDetail(Constants.TRUE.toString());
            }
            if (!houseOperateType.equals(HouseOperateTypeEnum.SHOU_LOU.getValue())
                    && !houseOperateType.equals(houseListVo.getHouseOperateType())) {
                houseListVo.setStartTime(null);
                houseListVo.setEndTime(null);
                houseListVo.setHandleTime(null);
            }
            voList.add(houseListVo);
        }
        pageInfo = new PageInfo<>(voList);
        BeanUtils.copyProperties(pageEntity, pageInfo);
        if (!CollectionUtils.isEmpty(voList)) {
            //统计
            //获取需要统计的字段
            if (columnMap != null) {
                List<NsCoreResourcecolumnVo> columnList = (List<NsCoreResourcecolumnVo>) columnMap.get("columns");
                columnList = CommonUtils.getTotalColumn(columnList);
                if (!CollectionUtils.isEmpty(columnList)) {
                    String pageJson = "";
                    //统计本页
                    pageJson = JSONObject.toJSONString(voList);
                    String pageTotalJson = CommonUtils.totalList(pageJson, columnList, HouseListVo.class);
                    if (StringUtils.hasLength(pageTotalJson)) {
                        HouseListVo total = JSONObject.parseObject(pageTotalJson, HouseListVo.class);
                        voList.add(total);
                    }
                }
            }
        }
        pageInfo.setList(voList);
        return pageInfo;
    }

    @SuppressWarnings("unchecked")
    @Override
    @ReadDataSource
    public HouseListVo getTotal(SearchVo searchConditionVo, String houseOperateType, Map<String, Object> columnMap) throws Exception {
        HouseListVo total = null;
        //统计
        //获取需要统计的字段
        if (columnMap != null) {
            List<NsCoreResourcecolumnVo> columnList = (List<NsCoreResourcecolumnVo>) columnMap.get("columns");
            columnList = CommonUtils.getTotalColumn(columnList);
            if (!CollectionUtils.isEmpty(columnList)) {
                String AllJson = "";
                //统计当前所有
                Map<String, Object> map = new HashMap<>();
                if (!CollectionUtils.isEmpty(searchConditionVo.getTreeConditions())) {
                    searchConditionVo.getFilterList().addAll(searchConditionVo.getTreeConditions());
                }
                map.put("searchVo", searchConditionVo);
                map.put("houseOperateType", houseOperateType);
                List<HouseListEntity> resultListForTotal = houseResultMapper.listResultBySearchForOperate(map);
                if (!CollectionUtils.isEmpty(resultListForTotal)) {
                    List<HouseListVo> resultVoListForTotal = new ArrayList<>();
                    for (HouseListEntity houseListEntity : resultListForTotal) {
                        HouseListVo houseListVo = new HouseListVo();
                        BeanUtils.copyProperties(houseListEntity, houseListVo);
                        houseListVo.setAssistChargingArea(CommonUtils.long2Decimal(houseListEntity.getAssistChargingArea()));
                        houseListVo.setBasementArea(CommonUtils.long2Decimal(houseListEntity.getBasementArea()));
                        houseListVo.setBuildingArea(CommonUtils.long2Decimal(houseListEntity.getBuildingArea()));
                        houseListVo.setChargingArea(CommonUtils.long2Decimal(houseListEntity.getChargingArea()));
                        houseListVo.setGardenArea(CommonUtils.long2Decimal(houseListEntity.getGardenArea()));
                        houseListVo.setGiftArea(CommonUtils.long2Decimal(houseListEntity.getGiftArea()));
                        houseListVo.setInsideArea(CommonUtils.long2Decimal(houseListEntity.getInsideArea()));
                        houseListVo.setPoolArea(CommonUtils.long2Decimal(houseListEntity.getPoolArea()));
                        resultVoListForTotal.add(houseListVo);
                    }
                    AllJson = JSONObject.toJSONString(resultVoListForTotal);
                    String allTotalJson = CommonUtils.totalList(AllJson, columnList, HouseListVo.class);
                    if (StringUtils.hasLength(allTotalJson)) {
                        total = JSONObject.parseObject(allTotalJson, HouseListVo.class);
                    }
                }
            }
        }
        return total;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public Long getDeveloperByHousePath(Long houseId) throws Exception {
        Long developerId = 0L;
        OwnerHouseBaseInfo baseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseId);
        if (baseInfo != null) {
            if (HouseTypeEnum.PUBLICAREA.getValue().equals(baseInfo.getHouseType())) {
                OwnerHousePublicAreaInfo publicAreaInfo = ownerHousePublicAreaInfoMapper.selectByPrimaryKey(houseId);
                developerId = publicAreaInfo.getDeveloper();
            } else {
                List<Long> parentIdList = StringUtils.handlerPath2List(baseInfo.getPath());
                List<OwnerHouseBaseInfo> houseBaseInfoList = houseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(parentIdList);
                for (OwnerHouseBaseInfo ownerHouseBaseInfo : houseBaseInfoList) {
                    HouseTypeEnum houseTypeEnum = HouseTypeEnum.getInstance(ownerHouseBaseInfo.getHouseType());
                    switch (houseTypeEnum) {
                        case BUILDING:
                            OwnerHouseBuildingExtendInfo buildingExtendInfo = ownerHouseBuildingExtendInfoMapper.selectByPrimaryKey(ownerHouseBaseInfo.getHouseId());
                            if (buildingExtendInfo != null) {
                                developerId = buildingExtendInfo.getDeveloper();
                            }
                            return developerId;
                        case CLUSTER:
                            OwnerHouseClusterInfo clusterInfo = ownerHouseClusterInfoMapper.selectByPrimaryKey(ownerHouseBaseInfo.getHouseId());
                            if (clusterInfo != null) {
                                developerId = clusterInfo.getDeveloper();
                            }
                            return developerId;
                        case GARAGE:
                            OwnerHouseGarageInfo garageInfo = ownerHouseGarageInfoMapper.selectByPrimaryKey(ownerHouseBaseInfo.getHouseId());
                            if (garageInfo != null) {
                                developerId = garageInfo.getDeveloper();
                            }
                            return developerId;
                        case PRECINCT:
                            OwnerHousePrecinctExtendInfo projectExtendInfo = ownerHousePrecinctExtendInfoMapper.selectByPrimaryKey(ownerHouseBaseInfo.getHouseId());
                            if (projectExtendInfo != null) {
                                developerId = projectExtendInfo.getDeveloper();
                            }
                            return developerId;
                        default:
                            break;
                    }
                }
            }
        }
        return developerId;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public Map<String, Object> checkStageOperate(Long detailId, Long houseId, String houseOperateType) throws Exception {
        HouseOperateTypeEnum houseOperateTypeEnum = HouseOperateTypeEnum.getInstance(houseOperateType);
        if (houseOperateTypeEnum == null) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "操作类型不存在");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result", false);
        map.put("message", houseOperateTypeEnum.getTitle() + "操作失败");
        OwnerHouseBaseInfo houseBaseInfo = houseBaseInfoMapper.selectByPrimaryKey(houseId);
        if (houseBaseInfo != null) {
            //房产类型为6、8、9才能进行房态操作
            if (!Constants.HOUSE_TYPE_HOUSE.equals(houseBaseInfo.getHouseType()) &&
                    !Constants.HOUSE_TYPE_CARPORT.equals(houseBaseInfo.getHouseType()) &&
                    !Constants.HOUSE_TYPE_PUBLICAREA.equals(houseBaseInfo.getHouseType())) {
                map.put("result", false);
                map.put("message", "只有房间、车位、公共区域才能进行房态操作");
                return map;
            }
            if (houseBaseInfo.getIsDeleted().intValue() == Constants.TRUE.intValue()) {
                BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "当前房产已被删除");
            }
            if (CommonUtils.isObjectEmpty(detailId)) {
                HouseListEntity houseListEntity = houseResultMapper.selectByPrimaryKey(houseId);
//                detailId = houseListEntity.getDetailId();
                if (Constants.FALSE.equals(houseListEntity.getIsCurrentRecord())) {
                    BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "历史数据不可修改");
                }
            }
            //房产停用，不可操作
            if (Constants.TRUE.intValue() == houseBaseInfo.getIsBlockUp()) {
                map.put("result", false);
                map.put("message", "房产停用，不可操作");
                return map;
            }
//            OwnerHouseStageDetail detail = houseStageDetailMapper.selectByPrimaryKey(detailId);
//            if (detail != null) {
//                if (Constants.FALSE.toString().equals(detail.getIsNowDetail())) {
//                    BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "历史数据不可修改");
//                }
//            }

            //空置状态可进行的房态操作
            List<String> kongzhiList = new ArrayList<>();
            kongzhiList.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
            kongzhiList.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
            kongzhiList.add(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
            kongzhiList.add(HouseOperateTypeEnum.SHOU_FANG.getValue());
            kongzhiList.add(HouseOperateTypeEnum.CHU_ZU.getValue());
            //未领状态可进行的房态操作
            List<String> weilingList = new ArrayList<>();
//            weilingList.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
            weilingList.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
            weilingList.add(HouseOperateTypeEnum.SHOU_FANG.getValue());
            weilingList.add(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
            //空关状态可进行的房态操作
            List<String> kongguanList = new ArrayList<>();
//            kongguanList.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
            kongguanList.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
            kongguanList.add(HouseOperateTypeEnum.RU_ZHU.getValue());
            kongguanList.add(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
            kongguanList.add(HouseOperateTypeEnum.CHU_ZU.getValue());
            kongguanList.add(HouseOperateTypeEnum.TUI_ZU.getValue());
            kongguanList.add(HouseOperateTypeEnum.ZHUAN_ZU.getValue());
            //装修中状态可进行的房态操作
            List<String> zhuangxiuzhongList = new ArrayList<>();
            zhuangxiuzhongList.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
            zhuangxiuzhongList.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
            zhuangxiuzhongList.add(HouseOperateTypeEnum.RU_ZHU.getValue());
            zhuangxiuzhongList.add(HouseOperateTypeEnum.CHU_ZU.getValue());
            //已装修状态可进行的房态操作
            List<String> yizhuangxiuList = new ArrayList<>();
            yizhuangxiuList.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
            yizhuangxiuList.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
            yizhuangxiuList.add(HouseOperateTypeEnum.RU_ZHU.getValue());
            yizhuangxiuList.add(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
            yizhuangxiuList.add(HouseOperateTypeEnum.CHU_ZU.getValue());
            //未装修状态可进行的房态操作
            List<String> weizhuangxiuList = new ArrayList<>();
            weizhuangxiuList.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
            weizhuangxiuList.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
            weizhuangxiuList.add(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
            weizhuangxiuList.add(HouseOperateTypeEnum.SHOU_FANG.getValue());
            weizhuangxiuList.add(HouseOperateTypeEnum.CHU_ZU.getValue());
            weizhuangxiuList.add(HouseOperateTypeEnum.RU_ZHU.getValue());
            weizhuangxiuList.add(HouseOperateTypeEnum.KONG_GUAN.getValue());
            weizhuangxiuList.add(HouseOperateTypeEnum.BAN_CHU.getValue());
            weizhuangxiuList.add(HouseOperateTypeEnum.ZHUAN_ZU.getValue());
            weizhuangxiuList.add(HouseOperateTypeEnum.TUI_ZU.getValue());
            //入住状态可进行的房态操作
            List<String> ruzhuList = new ArrayList<>();
//            ruzhuList.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
            ruzhuList.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
            ruzhuList.add(HouseOperateTypeEnum.BAN_CHU.getValue());
            ruzhuList.add(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
            ruzhuList.add(HouseOperateTypeEnum.CHU_ZU.getValue());
            ruzhuList.add(HouseOperateTypeEnum.KONG_GUAN.getValue());
            //出租状态可进行的房态操作
            List<String> chuzuList = new ArrayList<>();
            chuzuList.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
            chuzuList.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
            chuzuList.add(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
            chuzuList.add(HouseOperateTypeEnum.ZHUAN_ZU.getValue());
            chuzuList.add(HouseOperateTypeEnum.TUI_ZU.getValue());
            //未租状态可进行的房态操作
            List<String> weizuList = new ArrayList<>();
            weizuList.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
            weizuList.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
            weizuList.add(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
            weizuList.add(HouseOperateTypeEnum.SHOU_FANG.getValue());
            weizuList.add(HouseOperateTypeEnum.CHU_ZU.getValue());
            weizuList.add(HouseOperateTypeEnum.RU_ZHU.getValue());
            weizuList.add(HouseOperateTypeEnum.KONG_GUAN.getValue());
            weizuList.add(HouseOperateTypeEnum.BAN_CHU.getValue());
            weizuList.add(HouseOperateTypeEnum.ZHUAN_ZU.getValue());

            if (!HouseStageEnum.KONG_ZHI.getValue().equals(houseBaseInfo.getStage()) && HouseOperateTypeEnum.SHOU_LOU.equals(houseOperateTypeEnum)) {
                houseOperateTypeEnum = HouseOperateTypeEnum.ZHUAN_RANG;
                houseOperateType = houseOperateTypeEnum.getValue();
            }
            //判断房产当前状态下是否可以进行当前操作
            boolean houseStageFlag = true;
            if (HouseStageEnum.KONG_ZHI.getValue().equals(houseBaseInfo.getStage())) {
                if (!kongzhiList.contains(houseOperateType)) {
                    houseStageFlag = false;
                    map.put("message", HouseStageEnum.KONG_ZHI.getTitle() + "状态不可进行" + houseOperateTypeEnum.getTitle() + "操作");
                }
            } else if (HouseStageEnum.WEI_LING.getValue().equals(houseBaseInfo.getStage())) {
                if (!weilingList.contains(houseOperateType)) {
                    houseStageFlag = false;
                    map.put("message", HouseStageEnum.WEI_LING.getTitle() + "状态不可进行" + houseOperateTypeEnum.getTitle() + "操作");
                }
            } else if (HouseStageEnum.KONG_GUAN.getValue().equals(houseBaseInfo.getStage())) {
                if (!kongguanList.contains(houseOperateType)) {
                    houseStageFlag = false;
                    map.put("message", HouseStageEnum.KONG_GUAN.getTitle() + "状态不可进行" + houseOperateTypeEnum.getTitle() + "操作");
                }
            } else if (HouseStageEnum.RU_ZHU.getValue().equals(houseBaseInfo.getStage())) {
                if (!ruzhuList.contains(houseOperateType)) {
                    houseStageFlag = false;
                    map.put("message", HouseStageEnum.RU_ZHU.getTitle() + "状态不可进行" + houseOperateTypeEnum.getTitle() + "操作");
                }
            }
            boolean decorateStageFlage = true;
            if (HouseDecorateStageEnum.DECORATE_STAGE_IN.getValue().equals(houseBaseInfo.getDecorateStage())) {
                if (!zhuangxiuzhongList.contains(houseOperateType)) {
                    decorateStageFlage = false;
                    map.put("message", HouseDecorateStageEnum.DECORATE_STAGE_IN.getTitle() + "状态不可进行" + houseOperateTypeEnum.getTitle() + "操作");
                }
            } else if (HouseDecorateStageEnum.DECORATE_STAGE_DONE.getValue().equals(houseBaseInfo.getDecorateStage())) {
                if (!yizhuangxiuList.contains(houseOperateType)) {
                    decorateStageFlage = false;
                    map.put("message", HouseDecorateStageEnum.DECORATE_STAGE_DONE.getTitle() + "状态不可进行" + houseOperateTypeEnum.getTitle() + "操作");
                }
            } else {
                if (!weizhuangxiuList.contains(houseOperateType)) {
                    decorateStageFlage = false;
                    map.put("message", HouseDecorateStageEnum.DECORATE_STAGE_NONE.getTitle() + "状态不可进行" + houseOperateTypeEnum.getTitle() + "操作");
                }
            }
            boolean rentStageFlag = true;
            if (HouseRentStageEnum.RENT_STAGE_IN.getValue().equals(houseBaseInfo.getRentStage())) {
                if (!chuzuList.contains(houseOperateType)) {
                    rentStageFlag = false;
                    map.put("message", HouseRentStageEnum.RENT_STAGE_IN.getTitle() + "状态不可进行" + houseOperateTypeEnum.getTitle() + "操作");
                }
            } else if (HouseRentStageEnum.RENT_STAGE_NONE.getValue().equals(houseBaseInfo.getRentStage())) {
                if (!weizuList.contains(houseOperateType)) {
                    rentStageFlag = false;
                    map.put("message", HouseRentStageEnum.RENT_STAGE_NONE.getTitle() + "状态不可进行" + houseOperateTypeEnum.getTitle() + "操作");
                }
            }
            if (houseStageFlag && decorateStageFlage && rentStageFlag) {
                map.put("result", true);
                map.put("message", "操作成功");
            } else {
                map.put("result", false);
            }
        } else {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "房产不存在");
        }
        return map;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean revokeStageDetail(Long houseId, Long detailId, Long userId, String userName) throws Exception {
        boolean flag = false;
        OwnerHouseStageDetail stageDetail = houseStageDetailMapper.selectByPrimaryKey(detailId);
        Map<String, Object> currentRecordMap = new HashMap<>();
        currentRecordMap.put("isCurrentRecord", true);
        currentRecordMap.put("houseId", houseId);
        currentRecordMap.put("detailId", detailId);
        currentRecordMap.put("isDeleted", Constants.FALSE);
        List<OwnerHouseRelationship> relationshipList = houseRelationshipMapper.listOwnerByHouseId(currentRecordMap);
        if (CollectionUtils.isEmpty(relationshipList)) {
            BizException.fail(ResultCodeEnum.ILLEGAL_REQUEST, "历史数据不可撤销");
        }
        if (!CommonUtils.isObjectEmpty(stageDetail.getPreviousDetailId())) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userId);
            map.put("updateUserName", userName);
            map.put("detailId", stageDetail.getPreviousDetailId());
            map.put("houseId", houseId);

            houseRelationshipMapper.editCurrentRecordTrue(map);
        }
        Map<String, Object> deteleMap = new HashMap<>();
        deteleMap.put("userId", userId);
        deteleMap.put("updateUserName", userName);
        deteleMap.put("detailId", detailId);
        houseStageDetailMapper.deleteStageDetail(deteleMap);
        houseRelationshipMapper.deleteRelationByDetail(deteleMap);
        //删除撤销的历史记录
        houseResultMapper.deleteCurrentRecord(houseId);

        OwnerHouseStageDetail previousDetail = houseStageDetailMapper.selectByPrimaryKey(stageDetail.getPreviousDetailId());
        Map<String, Object> map = new HashMap<>();
        //更新房态
        OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
        baseInfo.setHouseId(houseId);
        baseInfo.setUpdateUserId(userId);
        baseInfo.setUpdateUserName(userName);
        Long previousDetailId = 0L;
        if (previousDetail != null) {
            previousDetailId = previousDetail.getDetailId();
            baseInfo.setStage(stageDetail.getHouseStage());
            baseInfo.setRentStage(stageDetail.getRentStage());
            baseInfo.setDecorateStage(stageDetail.getDecorateStage());
            map.put("detailId", previousDetail.getDetailId());
        } else {
            baseInfo.setStage(HouseStageEnum.KONG_ZHI.getValue());
            baseInfo.setRentStage(HouseRentStageEnum.RENT_STAGE_NONE.getValue());
            baseInfo.setDecorateStage(HouseDecorateStageEnum.DECORATE_STAGE_NONE.getValue());
            map.put("detailId", 0L);
        }
        houseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);

        //更新房态列表当前状态
        boolean updateFlag = false;
        List<String> list = new ArrayList<>();
        HouseOperateTypeEnum houseOperateTypeEnum = HouseOperateTypeEnum.getInstance(stageDetail.getHouseOperateType());
        switch (houseOperateTypeEnum) {
            case SHOU_LOU:
                updateFlag = true;
                break;
            case ZHUAN_RANG:
                if (stageDetail.getHouseOperateType().equals(previousDetail.getHouseOperateType())) {
                    updateFlag = true;
                } else {
                    list.add(HouseOperateTypeEnum.SHOU_LOU.getValue());
                    list.add(HouseOperateTypeEnum.ZHUAN_RANG.getValue());
                }
                break;
            case SHOU_FANG:
                if (stageDetail.getHouseOperateType().equals(previousDetail.getHouseOperateType())) {
                    updateFlag = true;
                } else {
                    list.add(HouseOperateTypeEnum.SHOU_FANG.getValue());
                }
                break;
            case RU_ZHU:
            case KONG_GUAN:
            case BAN_CHU:
                if (stageDetail.getHouseOperateType().equals(previousDetail.getHouseOperateType())) {
                    updateFlag = true;
                } else {
                    list.add(HouseOperateTypeEnum.RU_ZHU.getValue());
                    list.add(HouseOperateTypeEnum.KONG_GUAN.getValue());
                    list.add(HouseOperateTypeEnum.BAN_CHU.getValue());
                }
                break;
            case CHU_ZU:
            case TUI_ZU:
                if (stageDetail.getHouseOperateType().equals(previousDetail.getHouseOperateType())) {
                    updateFlag = true;
                } else {
                    list.add(HouseOperateTypeEnum.CHU_ZU.getValue());
                    list.add(HouseOperateTypeEnum.TUI_ZU.getValue());
                }
                break;
            case ZHUANG_XIU:
                if (stageDetail.getHouseOperateType().equals(previousDetail.getHouseOperateType())) {
                    updateFlag = true;
                } else {
                    list.add(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
                }
                break;
            default:
                break;
        }
        if (updateFlag && !CommonUtils.isObjectEmpty(previousDetailId)) {
            OwnerHouseStageDetail updateDetail = new OwnerHouseStageDetail();
            updateDetail.setDetailId(previousDetailId);
            updateDetail.setIsNowDetail(Constants.TRUE.toString());
            updateDetail.setUpdateUserId(userId);
            updateDetail.setUpdateUserName(userName);
            houseStageDetailMapper.updateByPrimaryKeySelective(updateDetail);
        } else {
            if (!CollectionUtils.isEmpty(list)) {
                Map<String, Object> detailMap = new HashMap<>();
                detailMap.put("houseId", houseId);
                detailMap.put("list", list);
//                detailMap.put("isNowDetail", Constants.FALSE.toString());
                List<OwnerHouseStageDetail> details = houseStageDetailMapper.listStage(detailMap);
                if (!CollectionUtils.isEmpty(details)) {
                    OwnerHouseStageDetail updateDetail = details.get(details.size() - 1);
//                    updateDetail.setDetailId(previousDetailId);
                    updateDetail.setIsNowDetail(Constants.TRUE.toString());
                    updateDetail.setUpdateUserId(userId);
                    updateDetail.setUpdateUserName(userName);
                    houseStageDetailMapper.updateByPrimaryKeySelective(updateDetail);
                }
            }
        }

        map.put("houseId", houseId);
        HouseListEntity houseListEntity = houseResultMapper.selectByDetailId(map);
        if (houseListEntity != null) {
            HouseListEntity houseEntity = new HouseListEntity();
            houseEntity.setIsCurrentRecord(Constants.TRUE);
            houseEntity.setHouseResultId(houseListEntity.getHouseResultId());
            //还原上一条记录为当前记录
            houseResultMapper.updateByPrimaryKeySelective(houseEntity);
        }
        flag = true;
        return flag;
    }

    //    private void turnNowDetail(Long houseId, String houseOperateType){
//        //售楼(售楼、转让)管理、收房管理、入住(入住、空关、搬出)管理、装修管理、出租(出租、转租、退租)管理
//        HouseOperateTypeEnum houseOperateTypeEnum = HouseOperateTypeEnum.getInstance(houseOperateType);
//        switch (houseOperateTypeEnum) {
//        case SHOU_LOU:
//        case ZHUAN_RANG:
//            //
//            break;
//        case SHOU_FANG:
//            break;
//        case RU_ZHU:
//        case BAN_CHU:
//        case KONG_GUAN:
//            break;
//        case CHU_ZU:
//        case TUI_ZU:
//        case ZHUAN_ZU:
//            break;
//        case ZHUANG_XIU:
//            break;
//        default:
//            break;
//
//        }
//    }
    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int editDecorateStageTask() {
        int index = 0;
        List<Long> houseIdList = decorateMapper.listDecorateForTask();
        if (!CollectionUtils.isEmpty(houseIdList)) {
            Map<String, Object> map = new HashMap<>();
            map.put("decorateStage", 2);
            map.put("houseIdList", houseIdList);
            index = houseBaseInfoMapper.batchUpdateDecorateStageByHouseId(map);
            if (!CommonUtils.isObjectEmpty(index)) {
                houseResultMapper.batchUpdateDecorateStageByHouseId(map);
            }
        }
        return index;
    }
}
