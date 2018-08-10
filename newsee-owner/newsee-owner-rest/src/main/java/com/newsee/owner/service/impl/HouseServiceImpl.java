package com.newsee.owner.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.newsee.common.constant.Constants;
import com.newsee.common.entity.FilterEntity;
import com.newsee.common.enums.*;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.DateUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.*;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;
import com.newsee.owner.constant.OwnerConstants;
import com.newsee.owner.dao.*;
import com.newsee.owner.entity.*;
import com.newsee.owner.enums.HouseEditStatusEnum;
import com.newsee.owner.service.IHouseService;
import com.newsee.owner.service.remote.IChargeRemoteService;
import com.newsee.owner.utils.OwnerUtils;
import com.newsee.owner.vo.*;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsSystemOrganizationVo;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by niyang on 2017/9/12.
 */
@Service
public class HouseServiceImpl implements IHouseService {

    private final static Logger logger = LoggerFactory.getLogger(HouseServiceImpl.class);

    private final static String KEY_HOUSE_FULL_NAME = "houseFullName";
    private final static String KEY_PRECINCT_NAME_LIST = "precinctNameList";
    private final static String KEY_GROUP_NAME_LIST = "groupNameList";
    private final static String KEY_BUILDING_NAME_LIST = "buildingNameList";
    private final static String KEY_UNIT_NAME_LIST = "unitNameList";

    private final static String KEY_ENTERPRISE_ID_LIST = "enterpriseIdList";
    private final static String KEY_ORGANIZATION_ID_LIST = "organizationIdList";
    private final static String KEY_PRECINCT_ID_LIST = "precinctIdList";
    private final static String KEY_GROUP_ID_LIST = "groupIdList";
    private final static String KEY_BUILDING_ID_LIST = "buildingIdList";
    private final static String KEY_UNIT_ID_LIST = "unitIdList";

    @Autowired
    private OwnerHouseBaseInfoMapper ownerHouseBaseInfoMapper;

    @Autowired
    private OwnerHouseHouseInfoMapper ownerHouseHouseInfoMapper;

    @Autowired
    private OwnerHouseHouseExtendInfoMapper ownerHouseHouseExtendInfoMapper;

    @Autowired
    private OwnerHousePrecinctInfoMapper ownerHousePrecinctInfoMapper;

    @Autowired
    private OwnerHousePrecinctExtendInfoMapper ownerHousePrecinctExtendInfoMapper;

    @Autowired
    private OwnerHousePrecinctPropertyInfoMapper ownerHousePrecinctPropertyInfoMapper;

    @Autowired
    private OwnerHouseStageDetailMapper ownerHouseStageDetailMapper;

    @Autowired
    private OwnerHouseClusterInfoMapper ownerHouseClusterInfoMapper;

    @Autowired
    private OwnerHouseBuildingInfoMapper ownerHouseBuildingInfoMapper;

    @Autowired
    private OwnerHouseBuildingExtendInfoMapper ownerHouseBuildingExtendInfoMapper;

    @Autowired
    private OwnerHouseUnitInfoMapper ownerHouseUnitInfoMapper;

    @Autowired
    private OwnerHouseGarageInfoMapper ownerHouseGarageInfoMapper;

    @Autowired
    private OwnerHouseCarportInfoMapper ownerHouseCarportInfoMapper;

    @Autowired
    private OwnerHouseCarportExtendInfoMapper ownerHouseCarportExtendInfoMapper;

    @Autowired
    private OwnerHousePublicAreaInfoMapper ownerHousePublicAreaInfoMapper;
    // @Autowired
    // private OwnerHouseEntranceGuardCardMapper ownerHouseEntranceGuardCardMapper;

    @Autowired
    private OwnerHouseRelationshipMapper ownerHouseRelationshipMapper;

    @Autowired
    private OwnerHouseStageExtendInfoRentMapper stageRentMapper;
    @Autowired
    private OwnerCustomerBaseInfoMapper ownerCustomerBaseInfoMapper;

    @Autowired
    private OwnerHouseStageExtendInfoDecorateMapper decorateMapper;

    @Autowired
    private OwnerCustomerFamilyInfoMapper familyMapper;

    @Autowired
    private OwnerCustomerInfoMapper ownerCustomerInfoMapper;

    @Autowired
    private OwnerCustomerBankAccountMapper ownerCustomerBankAccountMapper;

    @Autowired
    private OwnerHouseResultMapper houseResultMapper;

    @Autowired
    private OwnerCustomerResultMapper customerResultMapper;

    @Autowired
    private OwnerHouseMasterSlaveHouseMapper masterSlaveHouseMapper;

    @Autowired
    private OwnerCustomerMainHouseMapper mainHouseMapper;

    @Autowired
    private OwnerCustomerCarMapper ownerCustomerCarMapper;
    @Autowired
    private IChargeRemoteService iChargeRemoteService;

    @Value("${showHouseLevel}")
    private Integer showHouseLevel;

    @SuppressWarnings({"unchecked", "incomplete-switch"})
    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Long addHouseNode(AddHouseVo addHouseVo, Map<String, NsCoreDictionaryVo> dicMap) {
        Long enterpriseId = addHouseVo.getEnterpriseId();
        Long organizationId = addHouseVo.getOrganizationId();
        Long parentId = addHouseVo.getParentId();
        Long houseId = addHouseVo.getHouseId();
        HouseTypeEnum houseTypeEnum = addHouseVo.getHouseTypeEnum();
        String name = addHouseVo.getHouseName();
        Long userId = addHouseVo.getUserId();
        String userName = addHouseVo.getUserName();
        String houseInfo = addHouseVo.getHouseInfo();
        Integer level = 0;
        String path = Constants.SEPARATOR_PATH;
        Long precinctId = 0L;
        Long buildingId = 0L;
        Integer sort = 1;
        Integer addResult = 0;

        Map<String, Object> houseNameInfoMap = new HashMap<>();
        String houseFullName = "";
        List<String> projectNameList = new ArrayList<>();
        List<String> groupNameList = new ArrayList<>();
        List<String> buildingNameList = new ArrayList<>();
        List<String> unitNameList = new ArrayList<>();
        // 获取父节点信息
        if (!Objects.isNull(parentId) && parentId != 0L) {
            OwnerHouseBaseInfo parentHouseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(parentId);
            if (Objects.isNull(parentHouseBaseInfo)) {
                // logger.error("添加房产节点失败，organizationId:{}，parentId:{}", organizationId, parentId);
                BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "该父节点不存在");
            }
            Integer parentLevel = parentHouseBaseInfo.getLevel();
            String parentPath = parentHouseBaseInfo.getPath();
            // Integer parentSort = parentHouseBaseInfo.getSort();
            // if (!parentPath.equals(Constants.SEPARATOR_PATH)) {
            path = parentPath + parentHouseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH;
            // } else {
            // path = Constants.SEPARATOR_PATH + parentHouseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH;
            // }
            level = parentLevel + 1;
            // sort = parentSort + 1;
            // 验证层级关系
            HouseTypeEnum parentHouseTypeEnum = HouseTypeEnum.getInstance(parentHouseBaseInfo.getHouseType());
            validateHouseLevel(houseTypeEnum, parentHouseTypeEnum);
            // if (!(boolean) validateResultMap.get("result")) {
            // BizException.fail(ResultCodeEnum.PARAMS_ERROR, (String) validateResultMap.get("message"));
            // }
            sort = ownerHouseBaseInfoMapper.getSortByParentId(parentId);
            if (sort == null) {
                sort = 1;
            } else {
                sort += 1;
            }
            // 获取归属的项目ID和楼栋ID
            List<Long> pathList = StringUtils.handlerPath2List(path);
            if (!CollectionUtils.isEmpty(pathList)) {
                List<OwnerHouseBaseInfo> ownerHouseBaseInfoList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(pathList);
                houseNameInfoMap = getHouseInfoByPath(pathList, ownerHouseBaseInfoList, name);
                List<Long> projectIdList = (List<Long>) houseNameInfoMap.get(KEY_PRECINCT_ID_LIST);
                List<Long> buildingIdList = (List<Long>) houseNameInfoMap.get(KEY_BUILDING_ID_LIST);

                if (!CollectionUtils.isEmpty(projectIdList)) {
                    precinctId = projectIdList.get(0);
                }
                if (!CollectionUtils.isEmpty(buildingIdList)) {
                    buildingId = buildingIdList.get(0);
                }
                houseFullName = (String) houseNameInfoMap.get(KEY_HOUSE_FULL_NAME);
                projectNameList = (List<String>) houseNameInfoMap.get(KEY_PRECINCT_NAME_LIST);
                groupNameList = (List<String>) houseNameInfoMap.get(KEY_GROUP_NAME_LIST);
                buildingNameList = (List<String>) houseNameInfoMap.get(KEY_BUILDING_NAME_LIST);
                unitNameList = (List<String>) houseNameInfoMap.get(KEY_UNIT_NAME_LIST);
            }

        } else {
            houseFullName = name;
        }
        // 验证房产唯一性
        checkHouseOnly(addHouseVo, precinctId);
        // 添加新节点
        OwnerHouseBaseInfo houseBaseInfo = new OwnerHouseBaseInfo();
        OwnerHouseBaseInfo newInfo = JSONObject.parseObject(houseInfo, OwnerHouseBaseInfo.class);
        OwnerHouseBaseInfo oldHouse = null;
        if (CommonUtils.isObjectEmpty(houseId)) {
            houseBaseInfo.setPrecinctId(precinctId);
            houseBaseInfo.setBuildingId(buildingId);
            houseBaseInfo.setOrganizationId(organizationId);
            houseBaseInfo.setEnterpriseId(enterpriseId);
            houseBaseInfo.setHouseName(name);
            houseBaseInfo.setHouseNo(newInfo.getHouseNo());
            houseBaseInfo.setHouseFullName(houseFullName);
            houseBaseInfo.setHouseType(houseTypeEnum.getValue());
            houseBaseInfo.setStage(HouseStageEnum.KONG_ZHI.getValue());
            houseBaseInfo.setLevel(level);
            houseBaseInfo.setSort(sort);
            houseBaseInfo.setParentId(parentId);
            houseBaseInfo.setIsBlockUp(newInfo.getIsBlockUp());
            houseBaseInfo.setIsVirtual(newInfo.getIsVirtual());
            houseBaseInfo.setCreateUserId(userId);
            houseBaseInfo.setCreateUserName(userName);
            houseBaseInfo.setUpdateUserId(userId);
            houseBaseInfo.setUpdateUserName(userName);
            houseBaseInfo.setPath(path);
            addResult = ownerHouseBaseInfoMapper.insertSelective(houseBaseInfo);
        } else {
            oldHouse = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
            houseBaseInfo.setIsBlockUp(newInfo.getIsBlockUp());
            houseBaseInfo.setIsVirtual(newInfo.getIsVirtual());
            houseBaseInfo.setHouseId(houseId);
            houseBaseInfo.setHouseName(name);
            houseBaseInfo.setHouseNo(newInfo.getHouseNo());
            houseBaseInfo.setHouseFullName(houseFullName);
            // houseBaseInfo.setSort(sort);
            houseBaseInfo.setUpdateUserId(userId);
            houseBaseInfo.setUpdateUserName(userName);
            addResult = ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(houseBaseInfo);
            //修改对应的税率中的项目名称
            iChargeRemoteService.updatePrecinctName(houseId, name);
        }
        houseId = houseBaseInfo.getHouseId();
        String roomTypeId = "";
        // String roomTypeName = "";
        String roomPropertyId = "";
        // String roomPropertyName = "";
        if (addResult > 0) {

            Integer addInfoResult = 0;
            switch (houseTypeEnum) {
                case PRECINCT:
                    HouseProjectVo houseProjectVo = new HouseProjectVo();
                    OwnerProjectInfoVo projectInfoVo = JSONObject.parseObject(houseInfo, OwnerProjectInfoVo.class);
                    if (projectInfoVo != null) {
                        houseProjectVo.setProjectInfoVo(projectInfoVo);
                        houseProjectVo.getProjectInfoVo().setHouseId(houseId);
                    }

                    OwnerProjectExtendInfoVo projectExtendInfoVo = JSONObject.parseObject(houseInfo, OwnerProjectExtendInfoVo.class);
                    if (projectExtendInfoVo != null) {
                        houseProjectVo.setProjectExtendInfoVo(projectExtendInfoVo);
                        houseProjectVo.getProjectExtendInfoVo().setHouseId(houseId);
                    }

                    OwnerProjectPropertyInfoVo projectPropertyInfoVo = JSONObject.parseObject(houseInfo, OwnerProjectPropertyInfoVo.class);
                    if (projectPropertyInfoVo != null) {
                        houseProjectVo.setProjectPropertyInfoVo(projectPropertyInfoVo);
                        houseProjectVo.getProjectPropertyInfoVo().setHouseId(houseId);
                    }
                    addInfoResult = addProjectDetail(houseProjectVo, userId, userName);
                    break;
                case BUILDING:
                    HouseBuildingVo buildingVo = new HouseBuildingVo();
                    OwnerBuildingInfoVo buildingInfoVo = JSONObject.parseObject(houseInfo, OwnerBuildingInfoVo.class);
                    if (buildingInfoVo != null) {
                        buildingInfoVo.setHouseId(houseId);
                        buildingVo.setBuildingInfoVo(buildingInfoVo);
                    }
                    OwnerBuildingExtendInfoVo buildingExtendInfoVo = JSONObject.parseObject(houseInfo, OwnerBuildingExtendInfoVo.class);
                    if (buildingExtendInfoVo != null) {
                        buildingExtendInfoVo.setHouseId(houseId);
                        buildingVo.setBuildingExtendInfoVo(buildingExtendInfoVo);
                    }
                    addInfoResult = addBuildingDetail(buildingVo, userId, userName);
                    break;
                case UNIT:
                    OwnerUnitInfoVo unitInfoVo = JSONObject.parseObject(houseInfo, OwnerUnitInfoVo.class);
                    if (unitInfoVo != null) {
                        unitInfoVo.setHouseId(houseId);
                        addInfoResult = addUnitDetail(unitInfoVo, userId, userName);
                    }
                    break;
                case CLUSTER:
                    OwnerClusterInfoVo clusterInfoVo = JSONObject.parseObject(houseInfo, OwnerClusterInfoVo.class);
                    if (clusterInfoVo != null) {
                        clusterInfoVo.setHouseId(houseId);
                        addInfoResult = addClusterDetail(clusterInfoVo, userId, userName);
                    }
                    break;
                case ROOM:
                    HouseRoomVo roomVo = new HouseRoomVo();
                    OwnerRoomInfoVo roomInfoVo = JSONObject.parseObject(houseInfo, OwnerRoomInfoVo.class);
                    if (roomInfoVo != null) {
                        roomVo.setRoomInfoVo(roomInfoVo);
                        roomVo.getRoomInfoVo().setHouseId(houseId);
                        roomTypeId = roomInfoVo.getRoomTypeId();
                        // roomTypeName = OwnerUtils.getDicName(dicMap.get("roomTypeDic"), roomTypeId);
                    }

                    OwnerRoomExtendInfoVo roomExtendInfoVo = JSONObject.parseObject(houseInfo, OwnerRoomExtendInfoVo.class);
                    if (roomExtendInfoVo != null) {
                        roomVo.setRoomExtendInfoVo(roomExtendInfoVo);
                        roomVo.getRoomExtendInfoVo().setHouseId(houseId);
                        roomPropertyId = roomExtendInfoVo.getRoomPropertyId();
                        // roomPropertyName = OwnerUtils.getDicName(dicMap.get("roomPropertyDic"), roomPropertyId);
                    }

                    addInfoResult = addRoomDetail(roomVo, userId, userName);
                    break;
                case GARAGE:
                    OwnerGarageInfoVo garageInfoVo = JSONObject.parseObject(houseInfo, OwnerGarageInfoVo.class);
                    if (garageInfoVo != null) {
                        garageInfoVo.setHouseId(houseId);
                        addInfoResult = addGarageDetail(garageInfoVo, userId, userName);
                    }
                    break;
                case CARPORT:
                    HouseCarportVo carportVo = new HouseCarportVo();
                    OwnerCarportInfoVo carportInfoVo = JSONObject.parseObject(houseInfo, OwnerCarportInfoVo.class);
                    if (carportInfoVo != null) {
                        carportVo.setCarportInfoVo(carportInfoVo);
                        carportVo.getCarportInfoVo().setHouseId(houseId);
                        roomTypeId = carportInfoVo.getCarportTypeId();
                        // roomTypeName = OwnerUtils.getDicName(dicMap.get("carPortTypeDic"), roomTypeId);
                    }

                    OwnerCarportExtendInfoVo carportExtendInfoVo = JSONObject.parseObject(houseInfo, OwnerCarportExtendInfoVo.class);
                    if (carportExtendInfoVo != null) {
                        carportVo.setCarportExtendInfoVo(carportExtendInfoVo);
                        carportVo.getCarportExtendInfoVo().setHouseId(houseId);
                    }
                    addInfoResult = addCarportDetail(carportVo, userId, userName);
                    break;
                case PUBLICAREA:
                    OwnerPublicAreaVo publicAreaVo = JSONObject.parseObject(houseInfo, OwnerPublicAreaVo.class);
                    if (publicAreaVo != null) {
                        publicAreaVo.setHouseId(houseId);
                        addInfoResult = addPublicAreaDetail(publicAreaVo, userId, userName);
                    }
                    break;
            }
            if (addInfoResult > 0) {
                // 保存关联房产
                if (!CollectionUtils.isEmpty(addHouseVo.getHouseRelationList())) {
                    masterSlaveHouseMapper.deleteByMasterHouseId(houseId);
                    List<OwnerHouseMasterSlaveHouse> masterSlaveHouseList = new ArrayList<>();
                    for (Long slaveHouseId : addHouseVo.getHouseRelationList()) {
                        OwnerHouseMasterSlaveHouse house = new OwnerHouseMasterSlaveHouse();
                        house.setMasterHouseId(houseId);
                        house.setSlaveHouseId(slaveHouseId);
                        house.setIsDeleted(Constants.FALSE);
                        house.setCreateUserId(userId);
                        house.setCreateUserName(userName);
                        house.setUpdateUserId(userId);
                        house.setUpdateUserName(userName);
                        masterSlaveHouseList.add(house);
                    }
                    masterSlaveHouseMapper.insertBatch(masterSlaveHouseList);
                }
                // 添加节点为房间或车位时，添加mongodb
                if (houseTypeEnum.equals(HouseTypeEnum.ROOM) || houseTypeEnum.equals(HouseTypeEnum.CARPORT) || houseTypeEnum.equals(HouseTypeEnum.PUBLICAREA)) {
                    HouseListEntity houseListEntity = new HouseListEntity();

                    if (houseTypeEnum.equals(HouseTypeEnum.ROOM)) {
                        OwnerRoomInfoVo roomInfoVo = JSONObject.parseObject(houseInfo, OwnerRoomInfoVo.class);
                        OwnerRoomExtendInfoVo roomExtendInfoVo = JSONObject.parseObject(houseInfo, OwnerRoomExtendInfoVo.class);
                        BeanUtils.copyProperties(roomInfoVo, houseListEntity);
                        BeanUtils.copyProperties(roomExtendInfoVo, houseListEntity);
                        houseListEntity.setHouseNo(newInfo.getHouseNo());
                        // houseListEntity.setHouseNo(roomInfoVo.getRoomNo());
                        houseListEntity.setHouseShortName(roomInfoVo.getRoomShortName());
                        houseListEntity.setChargingArea(CommonUtils.decimal2Long(roomInfoVo.getChargingArea()));
                        houseListEntity.setBuildingArea(CommonUtils.decimal2Long(roomInfoVo.getBuildingArea()));
                        houseListEntity.setInsideArea(CommonUtils.decimal2Long(roomExtendInfoVo.getInsideArea()));
                        houseListEntity.setPoolArea(CommonUtils.decimal2Long(roomExtendInfoVo.getPoolArea()));
                        houseListEntity.setAssistChargingArea(CommonUtils.decimal2Long(roomInfoVo.getAssistChargingArea()));
                        houseListEntity.setGardenArea(CommonUtils.decimal2Long(roomExtendInfoVo.getGardenArea()));
                        houseListEntity.setBasementArea(CommonUtils.decimal2Long(roomExtendInfoVo.getBasementArea()));
                        houseListEntity.setGiftArea(CommonUtils.decimal2Long(roomExtendInfoVo.getGiftArea()));

                    } else if (houseTypeEnum.equals(HouseTypeEnum.CARPORT)) {
                        OwnerCarportInfoVo carportInfoVo = JSONObject.parseObject(houseInfo, OwnerCarportInfoVo.class);
                        OwnerCarportExtendInfoVo carportExtendInfoVo = JSONObject.parseObject(houseInfo, OwnerCarportExtendInfoVo.class);
                        BeanUtils.copyProperties(carportInfoVo, houseListEntity);
                        BeanUtils.copyProperties(carportExtendInfoVo, houseListEntity);
                        houseListEntity.setHouseNo(newInfo.getHouseNo());
                        // houseListEntity.setHouseNo(carportInfoVo.getCarportNo());
                        houseListEntity.setHouseShortName(carportInfoVo.getCarportShortName());
                        houseListEntity.setRoomTypeId(carportInfoVo.getCarportTypeId());
                        houseListEntity.setChargingArea(CommonUtils.decimal2Long(carportInfoVo.getChargingArea()));
                        houseListEntity.setBuildingArea(CommonUtils.decimal2Long(carportInfoVo.getBuildingArea()));
                        houseListEntity.setInsideArea(CommonUtils.decimal2Long(carportExtendInfoVo.getInsideArea()));
                        houseListEntity.setPoolArea(CommonUtils.decimal2Long(carportExtendInfoVo.getPoolArea()));
                        houseListEntity.setAssistChargingArea(CommonUtils.decimal2Long(carportInfoVo.getAssistChargingArea()));
                    } else if (houseTypeEnum.equals(HouseTypeEnum.PUBLICAREA)) {
                        OwnerPublicAreaVo housePublicAreaVo = JSONObject.parseObject(houseInfo, OwnerPublicAreaVo.class);
                        BeanUtils.copyProperties(housePublicAreaVo, houseListEntity);
                        houseListEntity.setHouseShortName(housePublicAreaVo.getPublicAreaShortName());
                    }
                    BeanUtils.copyProperties(houseBaseInfo, houseListEntity);
                    // 添加mongodb
                    if (CommonUtils.isObjectEmpty(addHouseVo.getHouseId())) {
                        houseListEntity.setProjectName(handlerHouseName(projectNameList));
                        houseListEntity.setGroupName(handlerHouseName(groupNameList));
                        houseListEntity.setBuildingName(handlerHouseName(buildingNameList));
                        houseListEntity.setUnitName(handlerHouseName(unitNameList));
                        houseListEntity.setHouseStage(HouseStageEnum.KONG_ZHI.getValue());
                        houseListEntity.setStageName(
                                CommonUtils.getHouseStage(HouseStageEnum.KONG_ZHI.getValue(), HouseRentStageEnum.RENT_STAGE_NONE.getValue(), HouseDecorateStageEnum.DECORATE_STAGE_NONE.getValue(), 0));
                        houseListEntity.setHasRelevance(Constants.FALSE.intValue());
                        // houseListEntity.setHasRelevanceName("否");
                        houseListEntity.setRoomTypeId(roomTypeId);
                        // houseListEntity.setRoomTypeName(roomTypeName);
                        houseListEntity.setRoomPropertyId(roomPropertyId);
                        // houseListEntity.setRoomPropertyName(roomPropertyName);
                        houseListEntity.setIsBlockUp(newInfo.getIsBlockUp());
                        // houseListEntity.setIsBlockUpName(Constants.TRUE.intValue() == newInfo.getIsBlockUp().intValue()?"是":"否");
                        // houseListEntity.setIsLock(newInfo.getIsLock());
                        houseListEntity.setIsVirtual(newInfo.getIsVirtual());
                        // houseListEntity.setIsVirtualName(Constants.TRUE.intValue() == newInfo.getIsVirtual().intValue()?"是":"否");
                        houseListEntity.setIsCurrentRecord(Constants.TRUE);
                        // houseListEntity.setIsCurrentRecordName("当前");
                        houseListEntity.setSalesStageName(HouseStageEnum.KONG_ZHI.getTitle());
                        houseListEntity.setRentStage(HouseRentStageEnum.RENT_STAGE_NONE.getValue());
                        houseListEntity.setRentStageName(HouseRentStageEnum.RENT_STAGE_NONE.getTitle());
                        houseListEntity.setDecorateStage(HouseDecorateStageEnum.DECORATE_STAGE_NONE.getValue());
                        houseListEntity.setDecorateStageName(HouseDecorateStageEnum.DECORATE_STAGE_NONE.getTitle());
                        houseListEntity.setCreateTime(new Date());
                        houseListEntity.setCreateHouseUserId(userId);
                        houseResultMapper.insertSelective(houseListEntity);
                    } else {
                        houseListEntity.setRoomTypeId(roomTypeId);
                        // houseListEntity.setRoomTypeName(roomTypeName);
                        houseListEntity.setRoomPropertyId(roomPropertyId);
                        // houseListEntity.setRoomPropertyName(roomPropertyName);
                        houseResultMapper.updateByHouseId(houseListEntity);
                    }

                }
            }
            if (!CommonUtils.isObjectEmpty(addHouseVo.getHouseId())) {
                // 同步更新子节点全称
                updateHouseName(houseId, name, oldHouse);
            }
        } else {
            BizException.fail(ResultCodeEnum.DB_ERROR, "添加房产节点失败");
        }
        return houseId;
    }

    private void updateHouseName(Long houseId, String newHouseName, OwnerHouseBaseInfo oldHouse) {

        List<OwnerHouseBaseInfo> needUpdateHouseInfoList = new ArrayList<>();
        List<OwnerHouseBaseInfo> childHouseList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByPath(oldHouse.getPath() + houseId + Constants.SEPARATOR_PATH);
        if (!CollectionUtils.isEmpty(childHouseList)) {
            // 同步更新子节点的项目名称
            for (OwnerHouseBaseInfo childHouse : childHouseList) {
                String houseFullName = childHouse.getHouseFullName().replace(oldHouse.getHouseName() + "-", newHouseName + "-");
                childHouse.setHouseFullName(houseFullName);
                ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(childHouse);
            }
        }

        // 更新结果表
        List<Long> childPathAllIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(needUpdateHouseInfoList)) {
            for (OwnerHouseBaseInfo child : needUpdateHouseInfoList) {
                List<Long> childPathIdList = StringUtils.handlerPath2List(child.getPath());
                childPathAllIdList.addAll(childPathIdList);
            }
        }

        for (OwnerHouseBaseInfo childHouse : childHouseList) {
            // 添加节点为房间或车位时，添加mongodb
            if (HouseTypeEnum.ROOM.getValue().equals(childHouse.getHouseType()) || HouseTypeEnum.CARPORT.getValue().equals(childHouse.getHouseType())
                    || HouseTypeEnum.PUBLICAREA.getValue().equals(childHouse.getHouseType())) {

                HouseListEntity houseListEntity = new HouseListEntity();
                houseListEntity.setHouseId(childHouse.getHouseId());
                houseListEntity.setHouseFullName(childHouse.getHouseFullName().replace(oldHouse.getHouseName() + "-", newHouseName + "-"));
                if (HouseTypeEnum.PRECINCT.getValue().equals(oldHouse.getHouseType())) {
                    houseListEntity.setProjectName(newHouseName);
                } else if (HouseTypeEnum.CLUSTER.getValue().equals(oldHouse.getHouseType())) {
                    houseListEntity.setGroupName(newHouseName);
                } else if (HouseTypeEnum.BUILDING.getValue().equals(oldHouse.getHouseType())) {
                    houseListEntity.setBuildingName(newHouseName);
                } else if (HouseTypeEnum.UNIT.getValue().equals(oldHouse.getHouseType())) {
                    houseListEntity.setUnitName(newHouseName);
                }
                houseResultMapper.updateByHouseId(houseListEntity);
            }
        }
    }

    /**
     * 处理房屋名称集合
     *
     * @param houseNameList
     * @return
     */
    private String handlerHouseName(List<String> houseNameList) {
        String result = "";
        if (!CollectionUtils.isEmpty(houseNameList)) {
            StringBuffer stringBuffer = new StringBuffer();
            houseNameList.stream().forEach(s -> {
                stringBuffer.append(s);
                stringBuffer.append(",");
            });
            result = stringBuffer.substring(0, stringBuffer.length() - 1);
        }
        return result;
    }

    /**
     * 将Path转换为List<Long>
     *
     * @param path
     * @return
     */
    // private List<Long> StringUtils.handlerPath2List(String path) {
    // String[] pathArray = StringUtils.delimitedListToStringArray(path, Constants.SEPARATOR_PATH);
    // List<String> pathStrList = Arrays.asList(pathArray);
    // List<Long> pathList = new ArrayList<>();
    // for (String pathStr : pathStrList) {
    // if (!StringUtils.isBlank(pathStr)) {
    // pathList.add(Long.parseLong(pathStr));
    // }
    // }
    // return pathList;
    // }

    /**
     * 根据Path获取房产路径信息
     *
     * @param pathList
     * @param ownerHouseBaseInfoList
     * @return
     */
    @SuppressWarnings("incomplete-switch")
    private Map<String, Object> getHouseInfoByPath(List<Long> pathList, List<OwnerHouseBaseInfo> ownerHouseBaseInfoList, String houseName) {
        Map<Long, OwnerHouseBaseInfo> ownerHouseBaseInfoMap = ownerHouseBaseInfoList.stream().collect(Collectors.toMap(OwnerHouseBaseInfo::getHouseId, Function.identity()));

        StringBuffer pathString = new StringBuffer();
        if (!CollectionUtils.isEmpty(pathList)) {
            pathList.stream().forEach(pathHouseId -> {
                pathString.append(ownerHouseBaseInfoMap.get(pathHouseId).getHouseName());
                pathString.append("-");
            });
        }
        String houseFullName = "";
        if (CommonUtils.isNullOrBlank(houseName)) {
            houseFullName = pathString.substring(0, pathString.length() - 1).toString();
        } else {
            houseFullName = pathString.append(houseName).toString();
        }

        List<String> projectNameList = new ArrayList<>();
        List<String> groupNameList = new ArrayList<>();
        List<String> buildingNameList = new ArrayList<>();
        List<String> unitNameList = new ArrayList<>();

        List<Long> enterpriseIdList = new ArrayList<>();
        List<Long> organizationIdList = new ArrayList<>();
        List<Long> projectIdList = new ArrayList<>();
        List<Long> groupIdList = new ArrayList<>();
        List<Long> buildingIdList = new ArrayList<>();
        List<Long> unitIdList = new ArrayList<>();
        ownerHouseBaseInfoList.stream().forEach(houseInfo -> {
            HouseTypeEnum typeEnum = HouseTypeEnum.getInstance(houseInfo.getHouseType());
            switch (typeEnum) {
                case PRECINCT:
                    enterpriseIdList.add(houseInfo.getEnterpriseId());
                    organizationIdList.add(houseInfo.getOrganizationId());
                    projectNameList.add(houseInfo.getHouseName());
                    projectIdList.add(houseInfo.getHouseId());
                    break;
                case CLUSTER:
                case GARAGE:
                    groupNameList.add(houseInfo.getHouseName());
                    groupIdList.add(houseInfo.getHouseId());
                    break;
                case BUILDING:
                    buildingNameList.add(houseInfo.getHouseName());
                    buildingIdList.add(houseInfo.getHouseId());
                    break;
                case UNIT:
                    unitNameList.add(houseInfo.getHouseName());
                    unitIdList.add(houseInfo.getHouseId());

            }
        });

        Map<String, Object> houseInfoMap = new HashMap<>();
        houseInfoMap.put(KEY_HOUSE_FULL_NAME, houseFullName);
        houseInfoMap.put(KEY_PRECINCT_NAME_LIST, projectNameList);
        houseInfoMap.put(KEY_GROUP_NAME_LIST, groupNameList);
        houseInfoMap.put(KEY_BUILDING_NAME_LIST, buildingNameList);
        houseInfoMap.put(KEY_UNIT_NAME_LIST, unitNameList);

        houseInfoMap.put(KEY_ENTERPRISE_ID_LIST, enterpriseIdList);
        houseInfoMap.put(KEY_ORGANIZATION_ID_LIST, organizationIdList);
        houseInfoMap.put(KEY_PRECINCT_ID_LIST, projectIdList);
        houseInfoMap.put(KEY_GROUP_ID_LIST, groupIdList);
        houseInfoMap.put(KEY_BUILDING_ID_LIST, buildingIdList);
        houseInfoMap.put(KEY_UNIT_ID_LIST, unitIdList);

        return houseInfoMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, Object> editHouseSort(Long houseId, Integer sort, Long parentId, Long userId, String userName, List<Long> sortHouseIdList) {
        Map<String, Object> map = new HashMap<>();
        List<Long> searchHouseIdList = new ArrayList<>();
        searchHouseIdList.add(houseId);
        if (!Objects.isNull(parentId) && parentId != 0L) {
            searchHouseIdList.add(parentId);
        }
        List<OwnerHouseBaseInfo> ownerHouseBaseInfoList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(searchHouseIdList);

        OwnerHouseBaseInfo parentHouseBaseInfo = null;
        OwnerHouseBaseInfo houseBaseInfo = null;
        // 获取当前房产节点及其父节点信息
        for (OwnerHouseBaseInfo ownerHouseBaseInfo : ownerHouseBaseInfoList) {
            if (ownerHouseBaseInfo.getHouseId().equals(houseId)) {
                houseBaseInfo = ownerHouseBaseInfo;
            }
            if (ownerHouseBaseInfo.getHouseId().equals(parentId)) {
                parentHouseBaseInfo = ownerHouseBaseInfo;
            }
        }
        // 验证层级关系
        validateEditHouseLevel(houseBaseInfo, parentHouseBaseInfo);
        String houseBaseInfoPath = houseBaseInfo.getPath();
        String oldHouseBaseFullName = houseBaseInfo.getHouseFullName();
        String oldHouseBaseInfoPath = new String(houseBaseInfo.getPath() + houseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH);
        String houseBaseFullName = houseBaseInfo.getHouseFullName();
        Integer houseBaseLevel = houseBaseInfo.getLevel();
        Integer levelBalance = 0;
        String path = Constants.SEPARATOR_PATH;
        Integer level = 0;
        if (!Objects.isNull(parentHouseBaseInfo)) {
            path = parentHouseBaseInfo.getPath() + parentHouseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH;
            houseBaseFullName = parentHouseBaseInfo.getHouseFullName() + "-" + houseBaseInfo.getHouseName();
            level = parentHouseBaseInfo.getLevel() + 1;
            levelBalance = houseBaseLevel - level;
        }
        if (parentId == 0) {
            // 父节点为根节点
            path = Constants.SEPARATOR_PATH;
            houseBaseFullName = houseBaseInfo.getHouseName();
            level = 0;
            levelBalance = houseBaseLevel - level;
        }
        houseBaseInfo.setParentId(parentId);
        houseBaseInfo.setPath(path);
        houseBaseInfo.setHouseFullName(houseBaseFullName);
        houseBaseInfo.setLevel(level);
        houseBaseInfo.setUpdateUserId(userId);
        houseBaseInfo.setUpdateUserName(userName);
        // 获取拖拽后父节点下所有子节点
        List<OwnerHouseBaseInfo> childNodeList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByPath(path);
        // 计算拖拽后所在位置
        if (sortHouseIdList.size() == 1) {
            if (!CollectionUtils.isEmpty(childNodeList)) {
                houseBaseInfo.setSort(childNodeList.get(childNodeList.size() - 1).getSort() + 1);
            } else {
                houseBaseInfo.setSort(0);
            }
        }

        boolean flag = false;
        if (sortHouseIdList.size() > 1) {
            for (Iterator<Long> iterator = sortHouseIdList.iterator(); iterator.hasNext(); ) {
                Long node = (Long) iterator.next();
                if (node.equals(houseId)) {
                    flag = true;
                }
                if (!flag) {
                    iterator.remove();
                }
            }
            if (sort == null) {
                map.put("result", false);
                map.put("messsage", "节点位置未指定");
            }
            houseBaseInfo.setSort(sort + 1);
        }
        // 获取归属的项目ID和楼栋ID
        List<Long> pathIdList = StringUtils.handlerPath2List(houseBaseInfo.getPath());
        List<OwnerHouseBaseInfo> pathHouseInfoList = null;
        if (!CollectionUtils.isEmpty(pathIdList)) {
            pathHouseInfoList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(pathIdList);
            Map<String, Object> houseIdMap = getHouseInfoByPath(pathIdList, pathHouseInfoList, null);

            List<Long> projectIdList = (List<Long>) houseIdMap.get(KEY_PRECINCT_ID_LIST);
            List<Long> buildingIdList = (List<Long>) houseIdMap.get(KEY_BUILDING_ID_LIST);

            if (!CollectionUtils.isEmpty(projectIdList)) {
                houseBaseInfo.setPrecinctId(projectIdList.get(0));
            }
            if (!CollectionUtils.isEmpty(buildingIdList)) {
                houseBaseInfo.setBuildingId(buildingIdList.get(0));
            }
        }
        // 更新当前节点信息
        Integer result = ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(houseBaseInfo);
        // 添加节点为房间或车位时，添加mongodb
        if (HouseTypeEnum.ROOM.getValue().equals(houseBaseInfo.getHouseType()) || HouseTypeEnum.CARPORT.getValue().equals(houseBaseInfo.getHouseType())
                || HouseTypeEnum.PUBLICAREA.getValue().equals(houseBaseInfo.getHouseType())) {

            // List<Long> pathIdList = StringUtils.handlerPath2List(houseBaseInfo.getPath());
            // List<OwnerHouseBaseInfo> pathHouseInfoList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(pathIdList);
            //
            Map<String, Object> houseNameInfoMap = new HashMap<>();
            if (pathHouseInfoList != null) {
                Map<Long, OwnerHouseBaseInfo> pathHouseInfoMap = pathHouseInfoList.stream().collect(Collectors.toMap(OwnerHouseBaseInfo::getHouseId, Function.identity()));

                List<OwnerHouseBaseInfo> houseBaseInfos = new ArrayList<>();
                for (Long pathId : pathIdList) {
                    houseBaseInfos.add(pathHouseInfoMap.get(pathId));
                }
                houseNameInfoMap = getHouseInfoByPath(pathIdList, houseBaseInfos, houseBaseInfo.getHouseName());
            }

            HouseListEntity houseListEntity = new HouseListEntity();
            BeanUtils.copyProperties(houseBaseInfo, houseListEntity);
            houseListEntity.setProjectName(handlerHouseName((List<String>) houseNameInfoMap.get(KEY_PRECINCT_NAME_LIST)));
            houseListEntity.setGroupName(handlerHouseName((List<String>) houseNameInfoMap.get(KEY_GROUP_NAME_LIST)));
            houseListEntity.setBuildingName(handlerHouseName((List<String>) houseNameInfoMap.get(KEY_BUILDING_NAME_LIST)));
            houseListEntity.setUnitName(handlerHouseName((List<String>) houseNameInfoMap.get(KEY_UNIT_NAME_LIST)));
            houseResultMapper.updateByHouseId(houseListEntity);
        }
        // 更新当前节点改变顺序后，排序受到影响的其他节点顺序
        if (!CollectionUtils.isEmpty(sortHouseIdList)) {
            Map<String, Object> sortMap = new HashMap<>();
            sortMap.put("updateUserId", userId);
            sortMap.put("houseIdList", sortHouseIdList);
            ownerHouseBaseInfoMapper.updateSort(sortMap);
        }
        // 获取当前节点子节点信息

        List<OwnerHouseBaseInfo> childHouseList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByPath(houseBaseInfoPath + houseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH);
        String housePath = houseBaseInfo.getPath() + houseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH;
        // 需要更改的房产或车位
        List<OwnerHouseBaseInfo> needUpdateHouseInfoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(childHouseList)) {
            for (OwnerHouseBaseInfo child : childHouseList) {
                String childPath = child.getPath();
                String childFullName = child.getHouseFullName();
                childPath = childPath.replace(oldHouseBaseInfoPath, housePath);

                child.setPath(childPath);
                childFullName = childFullName.replace(oldHouseBaseFullName, houseBaseInfo.getHouseFullName());
                child.setHouseFullName(childFullName);
                child.setUpdateUserId(userId);
                child.setUpdateUserName(userName);
                Integer childLevel = child.getLevel();
                child.setLevel(childLevel - levelBalance);

                HouseTypeEnum childHouseTypeEnum = HouseTypeEnum.getInstance(child.getHouseType());
                if (childHouseTypeEnum.equals(HouseTypeEnum.ROOM) || childHouseTypeEnum.equals(HouseTypeEnum.CARPORT) || childHouseTypeEnum.equals(HouseTypeEnum.PUBLICAREA)) {
                    needUpdateHouseInfoList.add(child);
                }
            }

            // 更新当前节点子节点信息
            // ----- 当存在性能问题时，必须使用下面方法，但需要更改datasource，并且更改WallConfig中multiStatementAllow为true
            ownerHouseBaseInfoMapper.batchUpdate(childHouseList);
            // for (OwnerHouseBaseInfo childHouse : childHouseList) {
            // ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(childHouse);
            // }
        }

        // 更新结果表
        List<Long> childPathAllIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(needUpdateHouseInfoList)) {
            for (OwnerHouseBaseInfo child : needUpdateHouseInfoList) {
                List<Long> childPathIdList = StringUtils.handlerPath2List(child.getPath());
                childPathAllIdList.addAll(childPathIdList);
            }
        }

        if (!CollectionUtils.isEmpty(childPathAllIdList)) {
            childPathAllIdList = childPathAllIdList.stream().distinct().collect(Collectors.toList());
            pathHouseInfoList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(childPathAllIdList);

            Map<Long, OwnerHouseBaseInfo> pathHouseInfoMap = pathHouseInfoList.stream().collect(Collectors.toMap(OwnerHouseBaseInfo::getHouseId, Function.identity()));

            for (OwnerHouseBaseInfo childHouse : childHouseList) {
                pathIdList = StringUtils.handlerPath2List(childHouse.getPath());
                List<OwnerHouseBaseInfo> houseBaseInfos = new ArrayList<>();
                for (Long pathId : pathIdList) {
                    houseBaseInfos.add(pathHouseInfoMap.get(pathId));
                }
                Map<String, Object> houseNameInfoMap = getHouseInfoByPath(pathIdList, houseBaseInfos, childHouse.getHouseName());

                String houseFullName = (String) houseNameInfoMap.get(KEY_HOUSE_FULL_NAME);
                List<String> projectNameList = (List<String>) houseNameInfoMap.get(KEY_PRECINCT_NAME_LIST);
                List<String> groupNameList = (List<String>) houseNameInfoMap.get(KEY_GROUP_NAME_LIST);
                List<String> buildingNameList = (List<String>) houseNameInfoMap.get(KEY_BUILDING_NAME_LIST);
                List<String> unitNameList = (List<String>) houseNameInfoMap.get(KEY_UNIT_NAME_LIST);

                // 添加节点为房间或车位时，添加mongodb
                if (HouseTypeEnum.ROOM.getValue().equals(childHouse.getHouseType()) || HouseTypeEnum.CARPORT.getValue().equals(childHouse.getHouseType())
                        || HouseTypeEnum.PUBLICAREA.getValue().equals(childHouse.getHouseType())) {

                    HouseListEntity houseListEntity = new HouseListEntity();
                    houseListEntity.setHouseId(childHouse.getHouseId());
                    houseListEntity.setPath(childHouse.getPath());
                    houseListEntity.setHouseFullName(houseFullName);
                    houseListEntity.setProjectName(handlerHouseName(projectNameList));
                    houseListEntity.setGroupName(handlerHouseName(groupNameList));
                    houseListEntity.setBuildingName(handlerHouseName(buildingNameList));
                    houseListEntity.setUnitName(handlerHouseName(unitNameList));
                    houseResultMapper.updateByHouseId(houseListEntity);
                }
            }
        }
        if (result > 0) {
            map.put("result", true);
        } else {
            map.put("result", false);
            map.put("messsage", "拖拽失败");
        }
        return map;
    }

    @Override
    @ReadDataSource
    public List<HouseBaseInfoTreeNode> listHouseTreeForStandard(Long organizationId) {
        Map<String, Object> params = new HashMap<>();
        params.put("organizationId", organizationId);

        // 配置初始展示几级树
        if (!Objects.isNull(showHouseLevel) && showHouseLevel >= 0) {
            params.put("showHouseLevel", showHouseLevel);
        }
        List<OwnerHouseBaseInfo> allHouseBaseInfoList = ownerHouseBaseInfoMapper.listHouseBaseInfoByParam(params);
        for (OwnerHouseBaseInfo ownerHouseBaseInfo : allHouseBaseInfoList) {
            if (Constants.HOUSE_TYPE_HOUSE.equals(ownerHouseBaseInfo.getHouseType()) || Constants.HOUSE_TYPE_CARPORT.equals(ownerHouseBaseInfo.getHouseType())
                    || Constants.HOUSE_TYPE_PUBLICAREA.equals(ownerHouseBaseInfo.getHouseType())) {
                ownerHouseBaseInfo.setIsHasChild(false);
            } else {
                OwnerHouseBaseInfo childInfo = ownerHouseBaseInfoMapper.checkParentByHouseId(ownerHouseBaseInfo.getHouseId());
                if (childInfo != null) {
                    ownerHouseBaseInfo.setIsHasChild(false);
                } else {
                    ownerHouseBaseInfo.setIsHasChild(false);
                }
            }

        }
        // 获取root节点
        List<OwnerHouseBaseInfo> rootHouseBaseInfoList = new ArrayList<>();
        for (OwnerHouseBaseInfo houseBaseInfo : allHouseBaseInfoList) {
            Integer level = houseBaseInfo.getLevel();
            if (level.equals(0)) {
                rootHouseBaseInfoList.add(houseBaseInfo);
            }
        }

        // 生成房产树
        List<HouseBaseInfoTreeNode> houseBaseInfoTree = new ArrayList<>();
        for (OwnerHouseBaseInfo root : rootHouseBaseInfoList) {

            HouseBaseInfoTreeNode treeNode = generateTreeNode(root.getHouseId(), allHouseBaseInfoList);

            houseBaseInfoTree.add(treeNode);
        }
        return houseBaseInfoTree;
    }

    @Override
    // TODO 变更数据源
    @ReadDataSource
    public List<HouseBaseInfoTreeNode> listHouseTree(Long organizationId) {
        Map<String, Object> params = new HashMap<>();
        params.put("organizationId", organizationId);

        // 配置初始展示几级树
        if (!Objects.isNull(showHouseLevel) && showHouseLevel >= 0) {
            params.put("showHouseLevel", showHouseLevel);
        }
        List<OwnerHouseBaseInfo> allHouseBaseInfoList = ownerHouseBaseInfoMapper.listHouseBaseInfoByParam(params);
        for (OwnerHouseBaseInfo ownerHouseBaseInfo : allHouseBaseInfoList) {
            if (Constants.HOUSE_TYPE_HOUSE.equals(ownerHouseBaseInfo.getHouseType()) || Constants.HOUSE_TYPE_CARPORT.equals(ownerHouseBaseInfo.getHouseType())
                    || Constants.HOUSE_TYPE_PUBLICAREA.equals(ownerHouseBaseInfo.getHouseType())) {
                ownerHouseBaseInfo.setIsHasChild(false);
            } else {
                OwnerHouseBaseInfo childInfo = ownerHouseBaseInfoMapper.checkParentByHouseId(ownerHouseBaseInfo.getHouseId());
                if (childInfo != null) {
                    ownerHouseBaseInfo.setIsHasChild(true);
                } else {
                    ownerHouseBaseInfo.setIsHasChild(false);
                }
            }

        }
        // 获取root节点
        List<OwnerHouseBaseInfo> rootHouseBaseInfoList = new ArrayList<>();
        for (OwnerHouseBaseInfo houseBaseInfo : allHouseBaseInfoList) {
            Integer level = houseBaseInfo.getLevel();
            if (level.equals(0)) {
                rootHouseBaseInfoList.add(houseBaseInfo);
            }
        }

        // 生成房产树
        List<HouseBaseInfoTreeNode> houseBaseInfoTree = new ArrayList<>();
        for (OwnerHouseBaseInfo root : rootHouseBaseInfoList) {

            HouseBaseInfoTreeNode treeNode = generateTreeNode(root.getHouseId(), allHouseBaseInfoList);

            houseBaseInfoTree.add(treeNode);
        }
        return houseBaseInfoTree;
    }

    @Override
    @WriteDataSource
    public Map<String, Object> deleteHouseNode(Long houseId, Long userId, String userName) {
        Map<String, Object> resultMap = new HashMap<>();
        OwnerHouseBaseInfo ownerHouseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
        // TODO 判断是否可以删除
        // TODO 是否有欠费
        // TODO 是否有子节点
        OwnerHouseBaseInfo childInfo = ownerHouseBaseInfoMapper.checkParentByHouseId(houseId);
        if (childInfo != null) {
            resultMap.put("result", false);
            resultMap.put("msg", "该节点包含子节点无法删除");
        } else {
            if (!HouseStageEnum.KONG_ZHI.getValue().equals(ownerHouseBaseInfo.getStage())) {
                resultMap.put("result", false);
                resultMap.put("msg", "该房产存在售楼记录无法删除");
                return resultMap;
            } else {
                List<OwnerHouseStageExtendInfoDecorate> decorates = decorateMapper.listDecorateByHouseId(ownerHouseBaseInfo.getHouseId());
                List<OwnerHouseStageExtendInfoRent> rents = stageRentMapper.listRentByHouseId(ownerHouseBaseInfo.getHouseId());
                if (!CollectionUtils.isEmpty(decorates)) {
                    resultMap.put("result", false);
                    resultMap.put("msg", "该房产存在装修记录无法删除");
                    return resultMap;
                } else if (!CollectionUtils.isEmpty(rents)) {
                    resultMap.put("result", false);
                    resultMap.put("msg", "该房产存在出租记录无法删除");
                    return resultMap;
                }
            }
        }
        int result = ownerHouseBaseInfoMapper.deleteById(houseId, userId, userName);
        if (result > 0) {
            // path = path + ownerHouseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH;
            // ownerHouseBaseInfoMapper.deleteByPath(path, userId);
            if (HouseTypeEnum.ROOM.getValue().equals(ownerHouseBaseInfo.getHouseType()) || HouseTypeEnum.CARPORT.getValue().equals(ownerHouseBaseInfo.getHouseType())
                    || HouseTypeEnum.PUBLICAREA.getValue().equals(ownerHouseBaseInfo.getHouseType())) {
                // 删除结果表
                List<Long> deleteList = new ArrayList<>();
                deleteList.add(houseId);
                houseResultMapper.deleteByHouseId(deleteList);
            }
            resultMap.put("result", true);
        }
        //删除税率，如果是删除的是项目，那么删除对应的税率
        if ("2".equals(ownerHouseBaseInfo.getHouseType())) {
            iChargeRemoteService.deleteGoodsTaxRate(null, houseId);
        }

        return resultMap;
    }

    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public Map<String, Object> deleteHouseBatch(List<Long> houseIdList, Long userId, String userName) {
        if (CollectionUtils.isEmpty(houseIdList)) {
            BizException.fail(ResultCodeEnum.PARAMS_MISSING, "未选择房产");
        }
        int index = 0;
        List<OwnerHouseBaseInfo> ownerHouseBaseInfoList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(houseIdList);
        List<OwnerHouseBaseInfo> deleteFailList = new ArrayList<>();
        List<Long> deleteList = new ArrayList<>();
        for (OwnerHouseBaseInfo ownerHouseBaseInfo : ownerHouseBaseInfoList) {
            // 房产、车位存在售楼、收房、入住、装修、出租、财务数据时不能删除
            // TODO 财务数据未判断 停用状态未判断
            if (!HouseStageEnum.KONG_ZHI.getValue().equals(ownerHouseBaseInfo.getStage())) {
                deleteFailList.add(ownerHouseBaseInfo);
            } else {
                List<OwnerHouseStageExtendInfoDecorate> decorates = decorateMapper.listDecorateByHouseId(ownerHouseBaseInfo.getHouseId());
                List<OwnerHouseStageExtendInfoRent> rents = stageRentMapper.listRentByHouseId(ownerHouseBaseInfo.getHouseId());
                if (!CollectionUtils.isEmpty(decorates)) {
                    deleteFailList.add(ownerHouseBaseInfo);
                } else if (!CollectionUtils.isEmpty(rents)) {
                    deleteFailList.add(ownerHouseBaseInfo);
                } else {
                    deleteList.add(ownerHouseBaseInfo.getHouseId());
                }
            }
        }
        if (!CollectionUtils.isEmpty(deleteList)) {
            Map<String, Object> deleteMap = new HashMap<>();
            deleteMap.put("list", deleteList);
            deleteMap.put("userId", userId);
            index = ownerHouseBaseInfoMapper.batchDelete(deleteMap);
            if (index > 0) {
                // 删除结果表
                houseResultMapper.deleteByHouseId(deleteList);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("deleteFailNum", deleteFailList.size());
        map.put("deleteNum", index);
        return map;
    }

    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public Map<String, Object> deleteAllHouseBySearch(SearchVo searchVo, Long userId, String userName) throws Exception {
        Map<String, Object> map = new HashMap<>();
        // if (!CollectionUtils.isEmpty(searchVo.getTreeConditions())) {
        // searchVo.getFilterList().addAll(searchVo.getTreeConditions());
        // }
        List<HouseListVo> resultList = listHouse(searchVo, null, false).getList();

        map.put("total", resultList.size());
        if (!CollectionUtils.isEmpty(resultList)) {
            List<Long> houseIdList = new ArrayList<>();
            for (HouseListVo entity : resultList) {
                houseIdList.add(entity.getHouseId());
            }
            Map<String, Object> deleteMap = deleteHouseBatch(houseIdList, userId, userName);
            map.put("deleteFailNum", deleteMap.get("deleteFailNum"));
            map.put("deleteNum", deleteMap.get("deleteNum"));
        } else {
            map.put("deleteFailNum", 0);
            map.put("deleteNum", 0);
        }
        return map;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Map<String, Object> editToggleLockHouse(Boolean isLock, List<Long> houseIdList, Long userId, String userName) {
        if (CollectionUtils.isEmpty(houseIdList)) {
            BizException.fail(ResultCodeEnum.PARAMS_MISSING, "未选择房产");
        }
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> map = new HashMap<>();

        params.put("houseIdList", houseIdList);
        params.put("isLock", isLock);
        params.put("isLockName", Boolean.TRUE.equals(isLock) ? "是" : "否");
        params.put("updateUserId", userId);
        Integer result = ownerHouseBaseInfoMapper.batchUpdateByHouseId(params);
        map.put("total", houseIdList.size());
        map.put("lockFailNum", houseIdList.size() - result);
        map.put("lockNum", result);
        if (result > 0) {
            // 更新结果表
            houseResultMapper.batchUpdateByHouseId(params);
        }
        return map;
    }

    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public Map<String, Object> editLockAllHouseBySearch(Boolean isLock, SearchVo searchVo, Long userId, String userName) throws Exception {
        Map<String, Object> map = new HashMap<>();

        // if (!CollectionUtils.isEmpty(searchVo.getTreeConditions())) {
        // searchVo.getFilterList().addAll(searchVo.getTreeConditions());
        // }
        List<HouseListVo> resultList = listHouse(searchVo, null, false).getList();

        if (!CollectionUtils.isEmpty(resultList)) {
            List<Long> houseIdList = new ArrayList<>();
            for (HouseListVo entity : resultList) {
                houseIdList.add(entity.getHouseId());
            }
            map = editToggleLockHouse(isLock, houseIdList, userId, userName);
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    // TODO 变更数据源
    @ReadDataSource
    public PageInfo<HouseListVo> listHouse(SearchVo searchVo, Map<String, Object> columnMap, boolean pageFlag) throws Exception {
        PageInfo<HouseListVo> pageInfo = new PageInfo<>();
        if (searchVo.getFilterList() == null) {
            searchVo.setFilterList(new ArrayList<>());
        }
        FilterEntity filterEntity = new FilterEntity();
        filterEntity.setComparison(Constants.COMPARISON_EQUAL);
        filterEntity.setFieldName("is_current_record");
        filterEntity.setFieldValue(Constants.TRUE.toString());
        searchVo.getFilterList().add(filterEntity);
        if (!CollectionUtils.isEmpty(searchVo.getTreeConditions())) {
            for (FilterEntity treeFilterEntity : searchVo.getTreeConditions()) {
                FilterEntity searchFilter = new FilterEntity();
                if ("0".equals(treeFilterEntity.getFieldValue())) {
                    searchFilter.setComparison(Constants.COMPARISON_LIKE);
                    searchFilter.setFieldName("path");
                    searchFilter.setFieldValue(Constants.SEPARATOR_PATH);
                } else {
                    OwnerHouseBaseInfo searchBase = ownerHouseBaseInfoMapper.selectByPrimaryKey(Long.valueOf(treeFilterEntity.getFieldValue()));
                    if (HouseTypeEnum.ROOM.getValue().equals(searchBase.getHouseType()) || HouseTypeEnum.CARPORT.getValue().equals(searchBase.getHouseType())
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
        List<HouseListEntity> resultList = new ArrayList<>();
        if (pageFlag) {
            PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
            resultList = houseResultMapper.listResultBySearch(searchVo);
        } else {
            resultList = houseResultMapper.listResultBySearch(searchVo);
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
            voList.add(houseListVo);
        }
        pageInfo = new PageInfo<>(voList);
        BeanUtils.copyProperties(pageEntity, pageInfo);
        if (!CollectionUtils.isEmpty(voList)) {
            // 统计
            // 获取需要统计的字段
            if (columnMap != null) {
                List<NsCoreResourcecolumnVo> columnList = (List<NsCoreResourcecolumnVo>) columnMap.get("columns");
                columnList = CommonUtils.getTotalColumn(columnList);
                if (!CollectionUtils.isEmpty(columnList)) {
                    String pageJson = "";
                    // 统计本页
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
    public HouseListVo getTotal(SearchVo searchVo, Map<String, Object> columnMap) throws Exception {
        HouseListVo total = new HouseListVo();
        // 统计
        // 获取需要统计的字段
        if (columnMap != null) {
            List<NsCoreResourcecolumnVo> columnList = (List<NsCoreResourcecolumnVo>) columnMap.get("columns");
            columnList = CommonUtils.getTotalColumn(columnList);
            if (!CollectionUtils.isEmpty(columnList)) {
                String AllJson = "";
                // 统计当前所有
                if (searchVo.getFilterList() == null) {
                    searchVo.setFilterList(new ArrayList<>());
                }
                FilterEntity filterEntity = new FilterEntity();
                filterEntity.setComparison(Constants.COMPARISON_EQUAL);
                filterEntity.setFieldName("is_current_record");
                filterEntity.setFieldValue(Constants.TRUE.toString());
                searchVo.getFilterList().add(filterEntity);
                if (!CollectionUtils.isEmpty(searchVo.getTreeConditions())) {
                    for (FilterEntity treeFilterEntity : searchVo.getTreeConditions()) {
                        FilterEntity searchFilter = new FilterEntity();
                        if ("0".equals(treeFilterEntity.getFieldValue())) {
                            searchFilter.setComparison(Constants.COMPARISON_LIKE);
                            searchFilter.setFieldName("path");
                            searchFilter.setFieldValue(Constants.SEPARATOR_PATH);
                        } else {
                            OwnerHouseBaseInfo searchBase = ownerHouseBaseInfoMapper.selectByPrimaryKey(Long.valueOf(treeFilterEntity.getFieldValue()));
                            if (HouseTypeEnum.ROOM.getValue().equals(searchBase.getHouseType()) || HouseTypeEnum.CARPORT.getValue().equals(searchBase.getHouseType())
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
                List<HouseListEntity> resultListForTotal = houseResultMapper.listResultBySearch(searchVo);
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
    @WriteDataSource
    public Long editHouseCombine(HouseCombineVo houseCombineVo, Long userId, Long organizationId) {
        List<Long> houseIdList = houseCombineVo.getHouseId();
        BigDecimal _chargingArea = houseCombineVo.getChargingArea();
        Long chargingArea = _chargingArea.longValue() * Constants.DECIMAL_TRANS_LONG;

        List<OwnerHouseBaseInfo> houseBaseInfoList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(houseIdList);
        if (CollectionUtils.isEmpty(houseBaseInfoList) || houseBaseInfoList.size() != houseIdList.size()) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "合并房间不存在");
        }

        // 不同业主不能合并
        Long parentId = 0L;
        for (OwnerHouseBaseInfo houseBaseInfo : houseBaseInfoList) {
            if (parentId != 0L && parentId != houseBaseInfo.getParentId()) {
                BizException.fail(ResultCodeEnum.PARAMS_ERROR, "合并房间父节点不同");
            }
            parentId = houseBaseInfo.getParentId();
        }
        // 房间租户不同不能合并
        List<OwnerHouseHouseInfo> houseHouseInfoList = ownerHouseHouseInfoMapper.listHouseInfoListByHouseId(houseIdList);

        Long combineHouseChargingArea = 0L;
        Long combineHouseBuildingArea = 0L;
        Long combineHouseAssistChargingArea = 0L;
        Integer floor = 0;
        for (OwnerHouseHouseInfo houseHouseInfo : houseHouseInfoList) {
            // 计费面积
            combineHouseChargingArea += houseHouseInfo.getChargingArea();

            // 楼层
            if (floor != 0 && floor.equals(houseHouseInfo.getFloor())) {
                BizException.fail(ResultCodeEnum.PARAMS_ERROR, "合并房间不是同一楼层");
            }
            floor = houseHouseInfo.getFloor();

            // 建筑面积
            combineHouseBuildingArea += houseHouseInfo.getBuildingArea();
            // 辅助计费面积
            combineHouseAssistChargingArea += houseHouseInfo.getAssistChargingArea();
        }

        if (!combineHouseChargingArea.equals(chargingArea)) {
            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "合并后计费面积不等于原房产计费面积总和");
        }

        List<OwnerHouseHouseExtendInfo> houseHouseExtendInfoList = ownerHouseHouseExtendInfoMapper.listHouseExtendInfoListByHouseId(houseIdList);

        Long combineInsideArea = 0L;
        Long combinePoolArea = 0L;
        Long combineGarderArea = 0L;
        Long combineBasementArea = 0L;
        Long combineGiftArea = 0L;
        for (OwnerHouseHouseExtendInfo houseExtendInfo : houseHouseExtendInfoList) {
            if (!Objects.isNull(houseExtendInfo.getInsideArea())) {
                combineInsideArea += houseExtendInfo.getInsideArea();
            }
            if (!Objects.isNull(houseExtendInfo.getPoolArea())) {
                combinePoolArea += houseExtendInfo.getPoolArea();
            }
            if (!Objects.isNull(houseExtendInfo.getGardenArea())) {
                combineGarderArea += houseExtendInfo.getPoolArea();
            }
            if (!Objects.isNull(houseExtendInfo.getBasementArea())) {
                combineBasementArea += houseExtendInfo.getPoolArea();
            }
            if (!Objects.isNull(houseExtendInfo.getGiftArea())) {
                combineGiftArea += houseExtendInfo.getGiftArea();
            }
        }

        // 新增合并后房间base信息
        OwnerHouseBaseInfo houseBaseInfo = new OwnerHouseBaseInfo();
        OwnerHouseBaseInfo initCopyHouseBaseInfo = houseBaseInfoList.get(0);
        BeanUtils.copyProperties(initCopyHouseBaseInfo, houseBaseInfo);
        String parentHouseIdStr = "";
        for (Long houseId : houseIdList) {
            parentHouseIdStr += houseId + ",";
        }
        houseBaseInfo.setParentHouseIds(parentHouseIdStr.substring(0, parentHouseIdStr.length() - 1));
        houseBaseInfo.setOrganizationId(organizationId);
        houseBaseInfo.setCreateUserId(userId);
        ownerHouseBaseInfoMapper.insertSelective(houseBaseInfo);

        // 新增房间mongodb
        Criteria criteria = new Criteria("houseId");
        criteria.in(houseIdList);
        Query query = new Query();
        query.addCriteria(criteria);

        // HouseListEntity mongodbEntity = mongoTemplate.findOne(query, HouseListEntity.class, OwnerConstants.COLLECTIONS_HOUSE_LIST);

        HouseListEntity createHouse = new HouseListEntity();
        // BeanUtils.copyProperties(mongodbEntity, createHouse);
        createHouse.setHouseFullName("");
        createHouse.setHouseName("");
        createHouse.setHouseId(houseBaseInfo.getHouseId());
        createHouse.setChargingArea(chargingArea);
        createHouse.setBuildingArea(combineHouseBuildingArea);
        createHouse.setHouseShortName("");
        createHouse.setHouseNo("");
        createHouse.setInsideArea(combineInsideArea);
        createHouse.setPoolArea(combinePoolArea);
        // mongoTemplate.save(createHouse, OwnerConstants.COLLECTIONS_HOUSE_LIST);

        // 更新原有房间base信息
        Map<String, Object> updateInitHouseBaseMap = new HashMap<>();
        updateInitHouseBaseMap.put("updateUserId", userId);
        updateInitHouseBaseMap.put("editStatus", HouseEditStatusEnum.COMBINE.getValue());
        updateInitHouseBaseMap.put("houseIdList", houseIdList);

        ownerHouseBaseInfoMapper.batchUpdateByHouseId(updateInitHouseBaseMap);

        // 删除原有房间mongodb
        // mongoTemplate.remove(query, HouseListEntity.class, OwnerConstants.COLLECTIONS_HOUSE_LIST);

        // 新增合并房间具体信息
        OwnerHouseHouseInfo combineHouse = new OwnerHouseHouseInfo();
        combineHouse.setHouseId(houseBaseInfo.getHouseId());
        combineHouse.setFloor(floor);
        combineHouse.setChargingArea(chargingArea);
        combineHouse.setBuildingArea(combineHouseBuildingArea);
        combineHouse.setAssistChargingArea(combineHouseAssistChargingArea);
        combineHouse.setCreateUserId(userId);

        ownerHouseHouseInfoMapper.insertSelective(combineHouse);

        OwnerHouseHouseExtendInfo combineExtendHouse = new OwnerHouseHouseExtendInfo();
        combineExtendHouse.setHouseId(houseBaseInfo.getHouseId());
        combineExtendHouse.setInsideArea(combineInsideArea);
        combineExtendHouse.setPoolArea(combinePoolArea);
        combineExtendHouse.setGardenArea(combineGarderArea);
        combineExtendHouse.setBasementArea(combineBasementArea);
        combineExtendHouse.setGiftArea(combineGiftArea);

        ownerHouseHouseExtendInfoMapper.insertSelective(combineExtendHouse);

        return houseBaseInfo.getHouseId();
    }

    @Override
    @WriteDataSource
    public Boolean editHouseSplit(Long houseId, List<HouseSplitInfoVo> HouseSplitInfoVoList, Long userId, String userName) {
        OwnerHouseBaseInfo initHouseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
        OwnerHouseHouseInfo initHouseInfo = ownerHouseHouseInfoMapper.selectByPrimaryKey(houseId);
        OwnerHouseHouseExtendInfo initHouseExtendInfo = ownerHouseHouseExtendInfoMapper.selectByPrimaryKey(houseId);
        Long chargeAreaCount = 0L;

        Map<String, OwnerHouseBaseInfo> houseBaseMap = new HashMap<>();
        Map<String, OwnerHouseHouseInfo> houseMap = new HashMap<>();
        Map<String, OwnerHouseHouseExtendInfo> houseExtendInfoMap = new HashMap<>();

        List<OwnerHouseBaseInfo> houseBaseInfoList = new ArrayList<>();
        List<OwnerHouseHouseInfo> houseInfoList = new ArrayList<>();
        List<OwnerHouseHouseExtendInfo> houseExtendInfoList = new ArrayList<>();
        for (int i = 0; i < HouseSplitInfoVoList.size(); i++) {
            String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            HouseSplitInfoVo houseSplit = HouseSplitInfoVoList.get(i);
            OwnerHouseBaseInfo houseBaseInfo = new OwnerHouseBaseInfo();
            Long thisChargeArea = houseSplit.getChargingArea().longValue() * Constants.DECIMAL_TRANS_LONG;
            chargeAreaCount += thisChargeArea;

            BeanUtils.copyProperties(initHouseBaseInfo, houseBaseInfo);
            houseBaseInfo.setParentHouseIds(initHouseBaseInfo.getHouseId().toString());
            houseBaseInfo.setCreateUserId(userId);
            houseBaseInfo.setCreateUserName(userName);

            OwnerHouseHouseInfo houseInfo = new OwnerHouseHouseInfo();
            OwnerHouseHouseExtendInfo houseExtendInfo = new OwnerHouseHouseExtendInfo();

            BeanUtils.copyProperties(initHouseInfo, houseInfo);
            houseInfo.setRoomNo(houseSplit.getHouseNo());
            houseInfo.setChargingArea(thisChargeArea);
            if (i == HouseSplitInfoVoList.size() - 1) {
                houseInfo.setBuildingArea(initHouseInfo.getBuildingArea() / HouseSplitInfoVoList.size() + initHouseInfo.getBuildingArea() % HouseSplitInfoVoList.size());
                houseInfo.setAssistChargingArea(initHouseInfo.getAssistChargingArea() / HouseSplitInfoVoList.size() + initHouseInfo.getAssistChargingArea() % HouseSplitInfoVoList.size());

                houseExtendInfo.setInsideArea(initHouseExtendInfo.getInsideArea() / HouseSplitInfoVoList.size() + initHouseExtendInfo.getInsideArea() % HouseSplitInfoVoList.size());
                houseExtendInfo.setPoolArea(initHouseExtendInfo.getPoolArea() / HouseSplitInfoVoList.size() + initHouseExtendInfo.getPoolArea() % HouseSplitInfoVoList.size());
                houseExtendInfo.setGardenArea(initHouseExtendInfo.getGardenArea() / HouseSplitInfoVoList.size() + initHouseExtendInfo.getGardenArea() % HouseSplitInfoVoList.size());
                houseExtendInfo.setBasementArea(initHouseExtendInfo.getBasementArea() / HouseSplitInfoVoList.size() + initHouseExtendInfo.getBasementArea() % HouseSplitInfoVoList.size());
                houseExtendInfo.setGiftArea(initHouseExtendInfo.getGiftArea() / HouseSplitInfoVoList.size() + initHouseExtendInfo.getGiftArea() % HouseSplitInfoVoList.size());
            } else {
                houseInfo.setBuildingArea(initHouseInfo.getBuildingArea() / HouseSplitInfoVoList.size());
                houseInfo.setAssistChargingArea(initHouseInfo.getAssistChargingArea() / HouseSplitInfoVoList.size());

                houseExtendInfo.setInsideArea(initHouseExtendInfo.getInsideArea() / HouseSplitInfoVoList.size());
                houseExtendInfo.setPoolArea(initHouseExtendInfo.getPoolArea() / HouseSplitInfoVoList.size());
                houseExtendInfo.setGardenArea(initHouseExtendInfo.getGardenArea() / HouseSplitInfoVoList.size());
                houseExtendInfo.setBasementArea(initHouseExtendInfo.getBasementArea() / HouseSplitInfoVoList.size());
                houseExtendInfo.setGiftArea(initHouseExtendInfo.getGiftArea() / HouseSplitInfoVoList.size());
            }
            houseInfo.setCreateUserId(userId);
            houseInfo.setCreateUserName(userName);

            houseBaseMap.put(uuid, houseBaseInfo);
            houseMap.put(uuid, houseInfo);
            houseExtendInfoMap.put(uuid, houseExtendInfo);

            houseBaseInfoList.add(houseBaseInfo);
            houseInfoList.add(houseInfo);
            houseExtendInfoList.add(houseExtendInfo);
        }
        if (!chargeAreaCount.equals(initHouseInfo.getChargingArea())) {
            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "拆分房间总收费面积与原房间不相等");
        }

        ownerHouseBaseInfoMapper.batchInsertBaseInfoList(houseBaseInfoList);
        for (OwnerHouseBaseInfo baseInfo : houseBaseInfoList) {
            String uuid = baseInfo.getUuid();
            OwnerHouseHouseInfo houseInfo = houseMap.get(uuid);
            OwnerHouseHouseExtendInfo houseExtendInfo = houseExtendInfoMap.get(uuid);

            houseInfo.setHouseId(baseInfo.getHouseId());
            houseExtendInfo.setHouseId(baseInfo.getHouseId());
        }
        ownerHouseHouseInfoMapper.batchInsertHouseInfoList(houseInfoList);
        ownerHouseHouseExtendInfoMapper.batchInsertHouseExtendInfoList(houseExtendInfoList);

        // mongodb
        Criteria criteria = new Criteria("houseId");
        criteria.is(houseId);
        Query query = new Query();
        query.addCriteria(criteria);

        // HouseListEntity initMongodbData = mongoTemplate.findOne(query, HouseListEntity.class, OwnerConstants.COLLECTIONS_HOUSE_LIST);

        List<HouseListEntity> houseListEntityList = new ArrayList<>();
        for (OwnerHouseBaseInfo baseInfo : houseBaseInfoList) {
            String uuid = baseInfo.getUuid();

            OwnerHouseHouseInfo houseInfo = houseMap.get(uuid);
            OwnerHouseHouseExtendInfo houseExtendInfo = houseExtendInfoMap.get(uuid);

            HouseListEntity createHouse = new HouseListEntity();
            // BeanUtils.copyProperties(initMongodbData, createHouse);
            createHouse.setHouseFullName("");
            createHouse.setHouseName("");
            createHouse.setHouseId(baseInfo.getHouseId());
            createHouse.setChargingArea(houseInfo.getChargingArea());
            createHouse.setBuildingArea(houseInfo.getBuildingArea());
            createHouse.setHouseShortName("");
            createHouse.setHouseNo(houseInfo.getRoomNo());
            createHouse.setInsideArea(houseExtendInfo.getInsideArea());
            createHouse.setPoolArea(houseExtendInfo.getPoolArea());
            houseListEntityList.add(createHouse);
        }

        // mongoTemplate.save(houseListEntityList, OwnerConstants.COLLECTIONS_HOUSE_LIST);

        // 删除原有房间mongodb
        // mongoTemplate.remove(query, HouseListEntity.class, OwnerConstants.COLLECTIONS_HOUSE_LIST);

        return true;
    }

    @Override
    @WriteDataSource
    public Boolean addDecorate(DecorateVo decorateVo, Long userId) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date handleTime = null;
        Date startDecorateTime = null;
        Date endDecorateTime = null;
        try {
            handleTime = sf.parse(decorateVo.getHandleTime());
            startDecorateTime = sf.parse(decorateVo.getStartDecorateTime());
            endDecorateTime = sf.parse(decorateVo.getEndDecorateTime());
        } catch (ParseException e) {
            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "日期格式不正确");
        }

        OwnerHouseBaseInfo houseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(decorateVo.getHouseId());
        if (Objects.isNull(houseBaseInfo)) {
            logger.error("新增房产装修记录失败，房产不存在,houseId:{}", decorateVo.getHouseId());
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "该房产不存在");
        }

        // 新增装修明细记录
        OwnerHouseStageExtendInfoDecorate ownerHouseDecorateStageDetail = new OwnerHouseStageExtendInfoDecorate();
        ownerHouseDecorateStageDetail.setApplyDate(handleTime);
        ownerHouseDecorateStageDetail.setDecorateStartDate(startDecorateTime);
        ownerHouseDecorateStageDetail.setDecorateEndDate(endDecorateTime);
        ownerHouseDecorateStageDetail.setCreateUserId(userId);
        ownerHouseDecorateStageDetail.setRemark(decorateVo.getRemark());
        // Integer addResult = ownerHouseDecorateStageDetailMapper.insertSelective(ownerHouseDecorateStageDetail);
        Integer addResult = 0;
        if (addResult <= 0) {
            BizException.fail(ResultCodeEnum.DB_ERROR, "新增房产装修记录失败");

        }
        // 更新房产base信息
        houseBaseInfo.setDecorateStage(decorateVo.getDecorateStage());
        houseBaseInfo.setUpdateUserId(userId);
        Integer updateResult = ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(houseBaseInfo);
        if (updateResult <= 0) {
            BizException.fail(ResultCodeEnum.DB_ERROR, "新增房产装修记录时，更新房产装修状态失败");
        }

        // 更新结果表
        Criteria criteria = new Criteria("houseId");
        criteria.is(decorateVo.getHouseId());
        Query query = new Query();
        query.addCriteria(criteria);
        Update update = new Update();
        update.set("decorateStage", decorateVo.getDecorateStage());
        // mongoTemplate.updateMulti(query, update, HouseListEntity.class, OwnerConstants.COLLECTIONS_HOUSE_LIST);
        return true;
    }

    /**
     * 递归生成Tree结构数据
     *
     * @param houseId
     * @param allHouseBaseInfoList
     * @return
     */
    private HouseBaseInfoTreeNode generateTreeNode(Long houseId, List<OwnerHouseBaseInfo> allHouseBaseInfoList) {
        HouseBaseInfoTreeNode root = getNoteByHouseId(houseId, allHouseBaseInfoList);
        List<HouseBaseInfoTreeNode> childrenTreeNode = getChildNodeByHouseId(houseId, allHouseBaseInfoList);
        for (HouseBaseInfoTreeNode treeNode : childrenTreeNode) {
            HouseBaseInfoTreeNode node = generateTreeNode(treeNode.getHouseId(), allHouseBaseInfoList);
            root.getChildOwnerHouseBaseInfoTreeNodeList().add(node);
        }
        return root;
    }

    /**
     * 获取当前节点信息
     *
     * @param houseId
     * @param allHouseBaseInfoList
     * @return
     */
    private HouseBaseInfoTreeNode getNoteByHouseId(Long houseId, List<OwnerHouseBaseInfo> allHouseBaseInfoList) {
        HouseBaseInfoTreeNode treeNode = new HouseBaseInfoTreeNode();
        for (OwnerHouseBaseInfo ownerHouseBaseInfo : allHouseBaseInfoList) {
            if (ownerHouseBaseInfo.getHouseId().equals(houseId)) {
                BeanUtils.copyProperties(ownerHouseBaseInfo, treeNode);
                break;
            }
        }
        return treeNode;
    }

    /**
     * 获取当前节点子节点信息
     *
     * @param houseId
     * @param allHouseBaseInfoList
     * @return
     */
    private List<HouseBaseInfoTreeNode> getChildNodeByHouseId(Long houseId, List<OwnerHouseBaseInfo> allHouseBaseInfoList) {
        List<HouseBaseInfoTreeNode> childrenTreeNode = new ArrayList<>();
        for (OwnerHouseBaseInfo ownerHouseBaseInfo : allHouseBaseInfoList) {
            if (ownerHouseBaseInfo.getParentId().equals(houseId)) {
                HouseBaseInfoTreeNode childTreeNode = new HouseBaseInfoTreeNode();
                BeanUtils.copyProperties(ownerHouseBaseInfo, childTreeNode);
                childrenTreeNode.add(childTreeNode);
            }
        }
        return childrenTreeNode;
    }

    /**
     * 验证层级关系
     *
     * @param validateHouseTypeEnum
     * @param parentHouseTypeEnum
     */
    private void validateHouseLevel(HouseTypeEnum validateHouseTypeEnum, HouseTypeEnum parentHouseTypeEnum) {
        // 验证层级关系
        Map<String, Object> map = new HashMap<>();
        map.put("result", true);
        switch (parentHouseTypeEnum) {
            case AREA:
                if (!validateHouseTypeEnum.equals(HouseTypeEnum.PRECINCT)) {
                    // map.put("result", false);
                    // map.put("message", parentHouseTypeEnum.getTitle() + "下不能添加" + validateHouseTypeEnum.getTitle());
                    BizException.fail(ResultCodeEnum.PARAMS_ERROR, parentHouseTypeEnum.getTitle() + "下不能添加" + validateHouseTypeEnum.getTitle());
                }
                break;
            case PRECINCT:
                if (validateHouseTypeEnum.equals(HouseTypeEnum.AREA) || validateHouseTypeEnum.equals(HouseTypeEnum.PRECINCT)) {
                    // map.put("result", false);
                    // map.put("message", parentHouseTypeEnum.getTitle() + "下不能添加" + validateHouseTypeEnum.getTitle());
                    BizException.fail(ResultCodeEnum.PARAMS_ERROR, parentHouseTypeEnum.getTitle() + "下不能添加" + validateHouseTypeEnum.getTitle());
                }
                break;
            case CLUSTER:
            case BUILDING:
            case UNIT:
            case ROOM:
            case GARAGE:
            case CARPORT:
            case PUBLICAREA:
                if (validateHouseTypeEnum.equals(HouseTypeEnum.AREA) || validateHouseTypeEnum.equals(HouseTypeEnum.PRECINCT)) {
                    // map.put("result", false);
                    // map.put("message", parentHouseTypeEnum.getTitle() + "下不能添加" + validateHouseTypeEnum.getTitle());
                    BizException.fail(ResultCodeEnum.PARAMS_ERROR, parentHouseTypeEnum.getTitle() + "下不能添加" + validateHouseTypeEnum.getTitle());
                }
                break;
            default:
                BizException.fail(ResultCodeEnum.PARAMS_ERROR, "父节点类型不存在");
                break;
        }
        // return map;
    }

    private void validateEditHouseLevel(OwnerHouseBaseInfo houseBaseInfo, OwnerHouseBaseInfo parentHouseBaseInfo) {
        // 验证层级关系
        HouseTypeEnum houseTypeEnum = HouseTypeEnum.getInstance(houseBaseInfo.getHouseType());
        if (parentHouseBaseInfo != null) {
            HouseTypeEnum parentHouseTypeEnum = HouseTypeEnum.getInstance(parentHouseBaseInfo.getHouseType());
            switch (parentHouseTypeEnum) {
                case AREA:
                    if (!houseTypeEnum.equals(HouseTypeEnum.PRECINCT)) {
                        BizException.fail(ResultCodeEnum.PARAMS_ERROR, "不可将" + houseTypeEnum.getTitle() + "节点直接拖拽到" + parentHouseTypeEnum.getTitle() + "节点下");
                    }
                    break;
                case PRECINCT:
                    if (houseTypeEnum.equals(HouseTypeEnum.AREA) || houseTypeEnum.equals(HouseTypeEnum.PRECINCT)) {
                        BizException.fail(ResultCodeEnum.PARAMS_ERROR, "不可将" + houseTypeEnum.getTitle() + "节点直接拖拽到" + parentHouseTypeEnum.getTitle() + "节点下");
                    } else {
                        if (!parentHouseBaseInfo.getHouseId().equals(houseBaseInfo.getPrecinctId())) {
                            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "不可跨项目拖拽");
                        }
                    }
                    break;
                case CLUSTER:
                case BUILDING:
                case UNIT:
                case GARAGE:
                    if (houseTypeEnum.equals(HouseTypeEnum.AREA) || houseTypeEnum.equals(HouseTypeEnum.PRECINCT)) {
                        BizException.fail(ResultCodeEnum.PARAMS_ERROR, "不可将" + houseTypeEnum.getTitle() + "节点直接拖拽到" + parentHouseTypeEnum.getTitle() + "节点下");
                    }
                    break;
                case ROOM:
                case CARPORT:
                case PUBLICAREA:
                    BizException.fail(ResultCodeEnum.PARAMS_ERROR, "不可将" + houseTypeEnum.getTitle() + "节点直接拖拽到" + parentHouseTypeEnum.getTitle() + "节点下");
                    break;
                default:
                    BizException.fail(ResultCodeEnum.PARAMS_ERROR, "父节点类型不存在");
                    break;
            }
        } else {
            if (!HouseTypeEnum.AREA.getValue().equals(houseBaseInfo.getHouseType()) && !HouseTypeEnum.PRECINCT.getValue().equals(houseBaseInfo.getHouseType())) {
                BizException.fail(ResultCodeEnum.PARAMS_ERROR, "不可将" + houseTypeEnum.getTitle() + "节点直接拖拽到根节点下");
            }
        }
    }
    // @Override
    // public OwnerRoomVo detailRoomByHouseId(Long houseId) {
    // OwnerRoomVo ownerRoomVo = null;
    // if (!Objects.isNull(houseId) && houseId != 0) {
    // ownerRoomVo = new OwnerRoomVo();
    // OwnerRoomInfo roomInfo = ownerRoomInfoMapper.selectByPrimaryKey(houseId);
    // if (roomInfo != null) {
    // ownerRoomVo.setRoomInfo(roomInfo);
    // OwnerRoomExtendInfo roomExtendInfo = ownerRoomExtendInfoMapper.selectByPrimaryKey(houseId);
    // if (roomExtendInfo != null) {
    // ownerRoomVo.setRoomExtendInfo(roomExtendInfo);
    // }
    // }
    // }
    // return ownerRoomVo;
    // }
    //
    // @Override
    // public OwnerProjectVo detailProjectByHouseId(Long houseId) {
    // // TODO Auto-generated method stub
    // return null;
    // }

    @ReadDataSource
    @Override
    public Map<String, Object> detailHouseByHouseId(Long houseId, Long ownerId) {
        Map<String, Object> map = null;
        // Map<String, Object> houseNameInfoMap = new HashMap<>();
        // String houseFullName = "";
        if (!Objects.isNull(houseId) && houseId != 0) {
            map = new HashMap<>();
            OwnerHouseBaseInfo houseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);

            if (houseBaseInfo == null) {
                BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "房产不存在");
            }
            HouseBaseVo houseBaseVo = new HouseBaseVo();
            BeanUtils.copyProperties(houseBaseInfo, houseBaseVo);
            houseBaseVo.setStageName(CommonUtils.getHouseStage(houseBaseInfo.getStage(), houseBaseInfo.getRentStage(), houseBaseInfo.getDecorateStage(), houseBaseInfo.getIsBlockUp()));
            List<HouseStageDetailVo> stageVoList = new ArrayList<>();
            // 房态变更
            Map<String, List<HouseStageDetailVo>> stageMap = listStageByhouseId(houseId, ownerId);
            map.put("stageMap", stageMap);
            HouseStageEnum houseStageEnum = HouseStageEnum.getInstance(houseBaseInfo.getStage());
            if (houseStageEnum != null) {
                // 根据houseId 获取全部房态操作记录
                List<OwnerHouseStageDetail> stageDetailList = ownerHouseStageDetailMapper.listStageByHouseId(houseId);
                // 根据houseId 获取客户房产关系
                OwnerHouseRelationship relationship = ownerHouseRelationshipMapper.loadOwnerRelationByHouseId(houseId);
                switch (houseStageEnum) {
                    case KONG_ZHI:
                        break;
                    case KONG_GUAN:
                    case WEI_LING:
                    case RU_ZHU:
                        HouseStageDetailVo houseStageDetailVo = new HouseStageDetailVo();
                        if (stageDetailList.get(stageDetailList.size() - 1).getDetailId().equals(relationship.getDetailId())) {
                            houseStageDetailVo.setOwnerId(relationship.getOwnerId());
                            CustomerVo customerVo = ownerCustomerBaseInfoMapper.loadCustomer(relationship.getOwnerId());
                            houseStageDetailVo.setOwnerName(customerVo.getOwnerName());
                        }
                        stageVoList.add(houseStageDetailVo);
                        break;
                    default:
                        break;
                }
            }

            // 出租
            List<HouseStageDetailVo> rentStageVoList = (List<HouseStageDetailVo>) stageMap.get("rentStageVoList");
            if (!CollectionUtils.isEmpty(rentStageVoList)) {
                HouseRentStageEnum houseRentStageEnum = HouseRentStageEnum.getInstance(houseBaseInfo.getRentStage());
                if (houseRentStageEnum != null) {
                    HouseStageDetailVo houseStageDetailVo = new HouseStageDetailVo();
                    switch (houseRentStageEnum) {
                        case RENT_STAGE_NONE:
                            break;
                        case RENT_STAGE_IN:
                            HouseStageDetailVo rentStage = rentStageVoList.get(rentStageVoList.size() - 1);
                            houseStageDetailVo.setOwnerId(rentStage.getOwnerId());
                            houseStageDetailVo.setOwnerName(rentStage.getOwnerName());
                            break;
                        default:
                            break;
                    }
                    houseStageDetailVo.setStage(houseRentStageEnum.getTitle());
                    stageVoList.add(houseStageDetailVo);
                }
            }
            // 装修
            HouseDecorateStageEnum houseDecorateStageEnum = HouseDecorateStageEnum.getInstance(houseBaseInfo.getDecorateStage());
            if (houseDecorateStageEnum != null) {
                HouseStageDetailVo houseStageDetailVo = new HouseStageDetailVo();
                houseStageDetailVo.setStage(houseDecorateStageEnum.getTitle());
                stageVoList.add(houseStageDetailVo);
            }
            houseBaseVo.setHouseStageDetailVoList(stageVoList);
            map.put("houseBaseVo", houseBaseVo);
            if (HouseTypeEnum.AREA.getValue().equals(houseBaseInfo.getHouseType())) {
                // 地域
            } else if (HouseTypeEnum.PRECINCT.getValue().equals(houseBaseInfo.getHouseType())) {
                // 项目
                OwnerHousePrecinctInfo projectInfo = ownerHousePrecinctInfoMapper.selectByPrimaryKey(houseId);
                OwnerProjectInfoVo projectInfoVo = new OwnerProjectInfoVo();
                if (projectInfo != null) {
                    BeanUtils.copyProperties(projectInfo, projectInfoVo);
                }
                map.put("projectInfoVo", projectInfoVo);
                OwnerHousePrecinctExtendInfo projectExtendInfo = ownerHousePrecinctExtendInfoMapper.selectByPrimaryKey(houseId);
                OwnerProjectExtendInfoVo projectExtendInfoVo = new OwnerProjectExtendInfoVo();
                if (projectExtendInfo != null) {
                    BeanUtils.copyProperties(projectExtendInfo, projectExtendInfoVo);
                    projectExtendInfoVo.setAssistArea(CommonUtils.long2DecimalHasNull(projectExtendInfo.getAssistArea()));
                    projectExtendInfoVo.setBuildingArea(CommonUtils.long2DecimalHasNull(projectExtendInfo.getBuildingArea()));
                    projectExtendInfoVo.setChargeArea(CommonUtils.long2DecimalHasNull(projectExtendInfo.getChargeArea()));
                    projectExtendInfoVo.setDeliveryArea(CommonUtils.long2DecimalHasNull(projectExtendInfo.getDeliveryArea()));
                    projectExtendInfoVo.setFloorArea(CommonUtils.long2DecimalHasNull(projectExtendInfo.getFloorArea()));
                    projectExtendInfoVo.setGreenArea(CommonUtils.long2DecimalHasNull(projectExtendInfo.getGreenArea()));
                    projectExtendInfoVo.setGreeningRate(CommonUtils.long2DecimalHasNull(projectExtendInfo.getGreeningRate()));
                    projectExtendInfoVo.setGroundArea(CommonUtils.long2DecimalHasNull(projectExtendInfo.getGroundArea()));
                    projectExtendInfoVo.setUndergroundArea(CommonUtils.long2DecimalHasNull(projectExtendInfo.getUndergroundArea()));
                    projectExtendInfoVo.setPlotRatio(CommonUtils.long2DecimalHasNull(projectExtendInfo.getPlotRatio()));
                }
                map.put("projectExtendInfoVo", projectExtendInfoVo);
                OwnerHousePrecinctPropertyInfo projectPropertyInfo = ownerHousePrecinctPropertyInfoMapper.selectByPrimaryKey(houseId);
                OwnerProjectPropertyInfoVo projectPropertyInfoVo = new OwnerProjectPropertyInfoVo();
                if (projectPropertyInfo != null) {
                    BeanUtils.copyProperties(projectPropertyInfo, projectPropertyInfoVo);
                    projectPropertyInfoVo.setCommunityArea(CommonUtils.long2DecimalHasNull(projectPropertyInfo.getCommunityArea()));
                    projectPropertyInfoVo.setHallArea(CommonUtils.long2DecimalHasNull(projectPropertyInfo.getHallArea()));
                    projectPropertyInfoVo.setPropertyManageArea(CommonUtils.long2DecimalHasNull(projectPropertyInfo.getPropertyManageArea()));
                    projectPropertyInfoVo.setPropertyOperateArea(CommonUtils.long2DecimalHasNull(projectPropertyInfo.getPropertyOperateArea()));
                }
                map.put("projectPropertyInfoVo", projectPropertyInfoVo);
            } else if (HouseTypeEnum.CLUSTER.getValue().equals(houseBaseInfo.getHouseType())) {
                // 组团
                OwnerHouseClusterInfo clusterInfo = ownerHouseClusterInfoMapper.selectByPrimaryKey(houseId);
                OwnerClusterInfoVo clusterInfoVo = new OwnerClusterInfoVo();
                if (clusterInfo != null) {
                    BeanUtils.copyProperties(clusterInfo, clusterInfoVo);
                }
                map.put("clusterInfoVo", clusterInfoVo);
            } else if (HouseTypeEnum.BUILDING.getValue().equals(houseBaseInfo.getHouseType())) {
                // 楼栋
                OwnerHouseBuildingInfo buildingInfo = ownerHouseBuildingInfoMapper.selectByPrimaryKey(houseId);
                OwnerBuildingInfoVo buildingInfoVo = new OwnerBuildingInfoVo();
                if (buildingInfo != null) {
                    BeanUtils.copyProperties(buildingInfo, buildingInfoVo);
                }
                map.put("buildingInfoVo", buildingInfoVo);
                OwnerHouseBuildingExtendInfo buildingExtendInfo = ownerHouseBuildingExtendInfoMapper.selectByPrimaryKey(houseId);
                OwnerBuildingExtendInfoVo buildingExtendInfoVo = new OwnerBuildingExtendInfoVo();
                if (buildingExtendInfo != null) {
                    BeanUtils.copyProperties(buildingExtendInfo, buildingExtendInfoVo);
                    buildingExtendInfoVo.setBuildingArea(CommonUtils.long2DecimalHasNull(buildingExtendInfo.getBuildingArea()));
                }
                map.put("buildingExtendInfoVo", buildingExtendInfoVo);
            } else if (HouseTypeEnum.UNIT.getValue().equals(houseBaseInfo.getHouseType())) {
                // 单元
                OwnerHouseUnitInfo unitInfo = ownerHouseUnitInfoMapper.selectByPrimaryKey(houseId);
                OwnerUnitInfoVo unitInfoVo = new OwnerUnitInfoVo();
                if (unitInfo != null) {
                    BeanUtils.copyProperties(unitInfo, unitInfoVo);
                }
                map.put("unitInfoVo", unitInfoVo);
            } else if (HouseTypeEnum.ROOM.getValue().equals(houseBaseInfo.getHouseType())) {
                // 房产
                OwnerHouseHouseInfo roomInfo = ownerHouseHouseInfoMapper.selectByPrimaryKey(houseId);
                OwnerRoomInfoVo roomInfoVo = new OwnerRoomInfoVo();
                if (roomInfo != null) {
                    BeanUtils.copyProperties(roomInfo, roomInfoVo);
                    roomInfoVo.setAssistChargingArea(CommonUtils.long2DecimalHasNull(roomInfo.getAssistChargingArea()));
                    roomInfoVo.setChargingArea(CommonUtils.long2DecimalHasNull(roomInfo.getChargingArea()));
                    roomInfoVo.setBuildingArea(CommonUtils.long2DecimalHasNull(roomInfo.getBuildingArea()));
                }
                map.put("roomInfoVo", roomInfoVo);
                OwnerHouseHouseExtendInfo roomExtendInfo = ownerHouseHouseExtendInfoMapper.selectByPrimaryKey(houseId);
                OwnerRoomExtendInfoVo roomExtendInfoVo = new OwnerRoomExtendInfoVo();
                if (roomExtendInfo != null) {
                    BeanUtils.copyProperties(roomExtendInfo, roomExtendInfoVo);
                    List<Date> dateList = new ArrayList<>();
                    if (roomExtendInfo.getMaintenanceStartDate() != null) {
                        dateList.add(roomExtendInfo.getMaintenanceStartDate());
                    }
                    if (roomExtendInfo.getMaintenanceEndDate() != null) {
                        dateList.add(roomExtendInfo.getMaintenanceEndDate());
                    }
                    roomExtendInfoVo.setMaintenanceDate(dateList);
                    roomExtendInfoVo.setBasementArea(CommonUtils.long2DecimalHasNull(roomExtendInfo.getBasementArea()));
                    roomExtendInfoVo.setGardenArea(CommonUtils.long2DecimalHasNull(roomExtendInfo.getGardenArea()));
                    roomExtendInfoVo.setGiftArea(CommonUtils.long2DecimalHasNull(roomExtendInfo.getGiftArea()));
                    roomExtendInfoVo.setPoolArea(CommonUtils.long2DecimalHasNull(roomExtendInfo.getPoolArea()));
                    roomExtendInfoVo.setInsideArea(CommonUtils.long2DecimalHasNull(roomExtendInfo.getInsideArea()));
                }
                map.put("roomExtendInfoVo", roomExtendInfoVo);
            } else if (HouseTypeEnum.GARAGE.getValue().equals(houseBaseInfo.getHouseType())) {
                // 车库
                OwnerHouseGarageInfo garageInfo = ownerHouseGarageInfoMapper.selectByPrimaryKey(houseId);
                OwnerGarageInfoVo garageInfoVo = new OwnerGarageInfoVo();
                if (garageInfo != null) {
                    BeanUtils.copyProperties(garageInfo, garageInfoVo);
                    garageInfoVo.setBuildingArea(CommonUtils.long2DecimalHasNull(garageInfo.getBuildingArea()));
                }
                map.put("garageInfoVo", garageInfoVo);
            } else if (HouseTypeEnum.CARPORT.getValue().equals(houseBaseInfo.getHouseType())) {
                // 车位
                OwnerHouseCarportInfo carportInfo = ownerHouseCarportInfoMapper.selectByPrimaryKey(houseId);
                OwnerCarportInfoVo carportInfoVo = new OwnerCarportInfoVo();
                if (carportInfo != null) {
                    BeanUtils.copyProperties(carportInfo, carportInfoVo);
                    carportInfoVo.setAssistChargingArea(CommonUtils.long2DecimalHasNull(carportInfo.getAssistChargingArea()));
                    carportInfoVo.setChargingArea(CommonUtils.long2DecimalHasNull(carportInfo.getChargingArea()));
                    carportInfoVo.setBuildingArea(CommonUtils.long2DecimalHasNull(carportInfo.getBuildingArea()));
                }
                map.put("carportInfoVo", carportInfoVo);
                OwnerHouseCarportExtendInfo carportExtendInfo = ownerHouseCarportExtendInfoMapper.selectByPrimaryKey(houseId);
                OwnerCarportExtendInfoVo carportExtendInfoVo = new OwnerCarportExtendInfoVo();
                if (carportExtendInfo != null) {
                    BeanUtils.copyProperties(carportExtendInfo, carportExtendInfoVo);
                    List<Date> dateList = new ArrayList<>();
                    if (carportExtendInfo.getMaintenanceStartDate() != null) {
                        dateList.add(carportExtendInfo.getMaintenanceStartDate());
                    }
                    if (carportExtendInfo.getMaintenanceEndDate() != null) {
                        dateList.add(carportExtendInfo.getMaintenanceEndDate());
                    }
                    carportExtendInfoVo.setMaintenanceDate(dateList);
                    carportExtendInfoVo.setInsideArea(CommonUtils.long2DecimalHasNull(carportExtendInfo.getInsideArea()));
                    carportExtendInfoVo.setPoolArea(CommonUtils.long2DecimalHasNull(carportExtendInfo.getPoolArea()));
                }
                map.put("carportExtendInfoVo", carportExtendInfoVo);
            } else if (HouseTypeEnum.PUBLICAREA.getValue().equals(houseBaseInfo.getHouseType())) {
                OwnerHousePublicAreaInfo publicAreaInfo = ownerHousePublicAreaInfoMapper.selectByPrimaryKey(houseId);
                OwnerPublicAreaVo publicAreaVo = new OwnerPublicAreaVo();
                if (publicAreaInfo != null) {
                    BeanUtils.copyProperties(publicAreaInfo, publicAreaVo);
                    publicAreaVo.setBuildingArea(CommonUtils.long2DecimalHasNull(publicAreaInfo.getBuildingArea()));
                }
                map.put("publicAreaVo", publicAreaVo);
            }
        }

        return map;
    }

    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int addProjectDetail(HouseProjectVo projectInfoVo, Long userId, String userName) {
        BizException.isNull(projectInfoVo, "项目信息");
        int index = 0;
        if (projectInfoVo.getProjectInfoVo() != null) {
            // 新增、修改项目基本信息
            OwnerHousePrecinctInfo newInfo = new OwnerHousePrecinctInfo();
            BeanUtils.copyProperties(projectInfoVo.getProjectInfoVo(), newInfo);
            newInfo.setUpdateUserId(userId);
            newInfo.setUpdateUserName(userName);
            // 查看数据库中是否已有数据
            OwnerHousePrecinctInfo info = ownerHousePrecinctInfoMapper.selectByPrimaryKey(projectInfoVo.getProjectInfoVo().getHouseId());
            if (info == null) {
                newInfo.setCreateUserId(userId);
                newInfo.setCreateUserName(userName);
                index = ownerHousePrecinctInfoMapper.insertSelective(newInfo);
            } else {
                index = ownerHousePrecinctInfoMapper.updateByPrimaryKey(newInfo);
            }
        }
        if (projectInfoVo.getProjectExtendInfoVo() != null) {
            // 新增、修改项目详细信息
            OwnerHousePrecinctExtendInfo newInfo = new OwnerHousePrecinctExtendInfo();
            BeanUtils.copyProperties(projectInfoVo.getProjectExtendInfoVo(), newInfo);
            newInfo.setAssistArea(CommonUtils.decimal2Long(projectInfoVo.getProjectExtendInfoVo().getAssistArea()));
            newInfo.setBuildingArea(CommonUtils.decimal2Long(projectInfoVo.getProjectExtendInfoVo().getBuildingArea()));
            newInfo.setChargeArea(CommonUtils.decimal2Long(projectInfoVo.getProjectExtendInfoVo().getChargeArea()));
            newInfo.setUpdateUserId(userId);
            newInfo.setUpdateUserName(userName);
            newInfo.setDeliveryArea(CommonUtils.decimal2Long(projectInfoVo.getProjectExtendInfoVo().getDeliveryArea()));
            newInfo.setFloorArea(CommonUtils.decimal2Long(projectInfoVo.getProjectExtendInfoVo().getFloorArea()));
            newInfo.setGreenArea(CommonUtils.decimal2Long(projectInfoVo.getProjectExtendInfoVo().getGreenArea()));
            newInfo.setGreeningRate(CommonUtils.decimal2Long(projectInfoVo.getProjectExtendInfoVo().getGreeningRate()));
            newInfo.setGroundArea(CommonUtils.decimal2Long(projectInfoVo.getProjectExtendInfoVo().getGroundArea()));
            newInfo.setPlotRatio(CommonUtils.decimal2Long(projectInfoVo.getProjectExtendInfoVo().getPlotRatio()));
            newInfo.setUndergroundArea(CommonUtils.decimal2Long(projectInfoVo.getProjectExtendInfoVo().getUndergroundArea()));
            // 查看数据库中是否已有数据
            OwnerHousePrecinctExtendInfo info = ownerHousePrecinctExtendInfoMapper.selectByPrimaryKey(projectInfoVo.getProjectExtendInfoVo().getHouseId());
            if (info == null) {
                newInfo.setCreateUserId(userId);
                newInfo.setCreateUserName(userName);
                ownerHousePrecinctExtendInfoMapper.insertSelective(newInfo);
            } else {
                ownerHousePrecinctExtendInfoMapper.updateByPrimaryKey(newInfo);
            }

        }
        if (projectInfoVo.getProjectPropertyInfoVo() != null) {
            // 新增、修改项目物业信息
            OwnerHousePrecinctPropertyInfo newInfo = new OwnerHousePrecinctPropertyInfo();
            newInfo.setCommunityArea(CommonUtils.decimal2Long(projectInfoVo.getProjectPropertyInfoVo().getCommunityArea()));
            newInfo.setHallArea(CommonUtils.decimal2Long(projectInfoVo.getProjectPropertyInfoVo().getHallArea()));
            newInfo.setHouseId(projectInfoVo.getProjectPropertyInfoVo().getHouseId());
            newInfo.setManagementPhone(projectInfoVo.getProjectPropertyInfoVo().getManagementPhone());
            newInfo.setProManager(projectInfoVo.getProjectPropertyInfoVo().getProManager());
            newInfo.setProManagerPhone(projectInfoVo.getProjectPropertyInfoVo().getProManagerPhone());
            newInfo.setPropertyManageArea(CommonUtils.decimal2Long(projectInfoVo.getProjectPropertyInfoVo().getPropertyManageArea()));
            newInfo.setPropertyOperateArea(CommonUtils.decimal2Long(projectInfoVo.getProjectPropertyInfoVo().getPropertyOperateArea()));
            newInfo.setServicePhone(projectInfoVo.getProjectPropertyInfoVo().getServicePhone());
            newInfo.setUpdateUserId(userId);
            newInfo.setUpdateUserName(userName);
            // 查看数据库中是否已有数据
            OwnerHousePrecinctPropertyInfo info = ownerHousePrecinctPropertyInfoMapper.selectByPrimaryKey(projectInfoVo.getProjectPropertyInfoVo().getHouseId());
            if (info == null) {
                newInfo.setCreateUserId(userId);
                newInfo.setCreateUserName(userName);
                ownerHousePrecinctPropertyInfoMapper.insertSelective(newInfo);
            } else {
                ownerHousePrecinctPropertyInfoMapper.updateByPrimaryKey(newInfo);
            }
        }
        return index;
    }

    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int addClusterDetail(OwnerClusterInfoVo clusterInfoVo, Long userId, String userName) {
        BizException.isNull(clusterInfoVo, "组团信息");
        int index = 0;
        // 新增、修改组团基本信息
        OwnerHouseClusterInfo newInfo = new OwnerHouseClusterInfo();
        BeanUtils.copyProperties(clusterInfoVo, newInfo);
        newInfo.setUpdateUserId(userId);
        newInfo.setUpdateUserName(userName);
        // 查看数据库中是否已有数据
        OwnerHouseClusterInfo info = ownerHouseClusterInfoMapper.selectByPrimaryKey(clusterInfoVo.getHouseId());
        if (info == null) {
            newInfo.setCreateUserId(userId);
            newInfo.setCreateUserName(userName);
            index = ownerHouseClusterInfoMapper.insertSelective(newInfo);
        } else {
            index = ownerHouseClusterInfoMapper.updateByPrimaryKey(newInfo);
        }
        return index;
    }

    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int addBuildingDetail(HouseBuildingVo buildingVo, Long userId, String userName) {
        BizException.isNull(buildingVo, "楼幢信息");
        int index = 0;
        if (buildingVo.getBuildingInfoVo() != null) {
            // 新增、修改楼栋基本信息
            OwnerHouseBuildingInfo newInfo = new OwnerHouseBuildingInfo();
            BeanUtils.copyProperties(buildingVo.getBuildingInfoVo(), newInfo);
            newInfo.setUpdateUserId(userId);
            newInfo.setUpdateUserName(userName);
            // 查看数据库中是否已有数据
            OwnerHouseBuildingInfo info = ownerHouseBuildingInfoMapper.selectByPrimaryKey(newInfo.getHouseId());
            if (info == null) {
                newInfo.setCreateUserId(userId);
                newInfo.setCreateUserName(userName);
                index = ownerHouseBuildingInfoMapper.insertSelective(newInfo);
            } else {
                index = ownerHouseBuildingInfoMapper.updateByPrimaryKey(newInfo);
            }
        }
        if (buildingVo.getBuildingExtendInfoVo() != null) {
            OwnerHouseBuildingExtendInfo newExtendInfo = new OwnerHouseBuildingExtendInfo();
            BeanUtils.copyProperties(buildingVo.getBuildingExtendInfoVo(), newExtendInfo);
            newExtendInfo.setBuildingArea(CommonUtils.decimal2Long(buildingVo.getBuildingExtendInfoVo().getBuildingArea()));
            newExtendInfo.setUpdateUserId(userId);
            newExtendInfo.setUpdateUserName(userName);
            OwnerHouseBuildingExtendInfo extendInfo = ownerHouseBuildingExtendInfoMapper.selectByPrimaryKey(newExtendInfo.getHouseId());
            if (extendInfo == null) {
                newExtendInfo.setCreateUserId(userId);
                newExtendInfo.setCreateUserName(userName);
                ownerHouseBuildingExtendInfoMapper.insertSelective(newExtendInfo);
            } else {
                ownerHouseBuildingExtendInfoMapper.updateByPrimaryKey(newExtendInfo);
            }
        }
        return index;
    }

    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int addUnitDetail(OwnerUnitInfoVo unitInfoVo, Long userId, String userName) {
        BizException.isNull(unitInfoVo, "单元信息");
        int index = 0;
        // 新增、修改单元基本信息
        OwnerHouseUnitInfo newInfo = new OwnerHouseUnitInfo();
        BeanUtils.copyProperties(unitInfoVo, newInfo);
        newInfo.setUpdateUserId(userId);
        newInfo.setUpdateUserName(userName);
        // 查看数据库中是否已有数据
        OwnerHouseUnitInfo info = ownerHouseUnitInfoMapper.selectByPrimaryKey(unitInfoVo.getHouseId());
        if (info == null) {
            newInfo.setCreateUserId(userId);
            newInfo.setCreateUserName(userName);
            index = ownerHouseUnitInfoMapper.insertSelective(newInfo);
        } else {
            index = ownerHouseUnitInfoMapper.updateByPrimaryKey(newInfo);
        }
        return index;
    }

    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int addRoomDetail(HouseRoomVo roomInfoVo, Long userId, String userName) {
        BizException.isNull(roomInfoVo, "房间信息");
        int index = 0;
        if (roomInfoVo.getRoomInfoVo() != null) {
            // 新增、修改房间基本信息
            OwnerHouseHouseInfo newInfo = new OwnerHouseHouseInfo();
            BeanUtils.copyProperties(roomInfoVo.getRoomInfoVo(), newInfo);
            newInfo.setAssistChargingArea(CommonUtils.decimal2Long(roomInfoVo.getRoomInfoVo().getAssistChargingArea()));
            newInfo.setChargingArea(CommonUtils.decimal2Long(roomInfoVo.getRoomInfoVo().getChargingArea()));
            newInfo.setBuildingArea(CommonUtils.decimal2Long(roomInfoVo.getRoomInfoVo().getBuildingArea()));
            newInfo.setUpdateUserId(userId);
            newInfo.setUpdateUserName(userName);
            // 查看数据库中是否已有数据
            OwnerHouseHouseInfo info = ownerHouseHouseInfoMapper.selectByPrimaryKey(roomInfoVo.getRoomInfoVo().getHouseId());
            if (info == null) {
                newInfo.setCreateUserId(userId);
                newInfo.setCreateUserName(userName);
                index = ownerHouseHouseInfoMapper.insertSelective(newInfo);
            } else {
                index = ownerHouseHouseInfoMapper.updateByPrimaryKey(newInfo);
            }
        }
        if (roomInfoVo.getRoomExtendInfoVo() != null) {
            // 新增、修改房间详细信息
            OwnerHouseHouseExtendInfo newInfo = new OwnerHouseHouseExtendInfo();
            BeanUtils.copyProperties(roomInfoVo.getRoomExtendInfoVo(), newInfo);
            if (!CollectionUtils.isEmpty(roomInfoVo.getRoomExtendInfoVo().getMaintenanceDate())) {
                newInfo.setMaintenanceStartDate(roomInfoVo.getRoomExtendInfoVo().getMaintenanceDate().get(0));
                newInfo.setMaintenanceEndDate(roomInfoVo.getRoomExtendInfoVo().getMaintenanceDate().get(1));
            }
            newInfo.setBasementArea(CommonUtils.decimal2Long(roomInfoVo.getRoomExtendInfoVo().getBasementArea()));
            newInfo.setGardenArea(CommonUtils.decimal2Long(roomInfoVo.getRoomExtendInfoVo().getGardenArea()));
            newInfo.setGiftArea(CommonUtils.decimal2Long(roomInfoVo.getRoomExtendInfoVo().getGiftArea()));
            newInfo.setPoolArea(CommonUtils.decimal2Long(roomInfoVo.getRoomExtendInfoVo().getPoolArea()));
            newInfo.setInsideArea(CommonUtils.decimal2Long(roomInfoVo.getRoomExtendInfoVo().getInsideArea()));
            // 查看数据库中是否已有数据
            OwnerHouseHouseExtendInfo info = ownerHouseHouseExtendInfoMapper.selectByPrimaryKey(roomInfoVo.getRoomExtendInfoVo().getHouseId());
            if (info == null) {
                ownerHouseHouseExtendInfoMapper.insertSelective(newInfo);
            } else {
                ownerHouseHouseExtendInfoMapper.updateByPrimaryKey(newInfo);
            }

        }
        return index;
    }

    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int addGarageDetail(OwnerGarageInfoVo garageInfoVo, Long userId, String userName) {
        BizException.isNull(garageInfoVo, "车库信息");
        int index = 0;
        // 新增、修改车库基本信息
        OwnerHouseGarageInfo newInfo = new OwnerHouseGarageInfo();
        BeanUtils.copyProperties(garageInfoVo, newInfo);
        newInfo.setBuildingArea(CommonUtils.decimal2Long(garageInfoVo.getBuildingArea()));
        newInfo.setUpdateUserId(userId);
        newInfo.setUpdateUserName(userName);
        // 查看数据库中是否已有数据
        OwnerHouseGarageInfo info = ownerHouseGarageInfoMapper.selectByPrimaryKey(garageInfoVo.getHouseId());
        if (info == null) {
            newInfo.setCreateUserId(userId);
            newInfo.setCreateUserName(userName);
            index = ownerHouseGarageInfoMapper.insertSelective(newInfo);
        } else {
            index = ownerHouseGarageInfoMapper.updateByPrimaryKey(newInfo);
        }
        return index;
    }

    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int addCarportDetail(HouseCarportVo carportInfoVo, Long userId, String userName) {
        BizException.isNull(carportInfoVo, "车位信息");
        int index = 0;
        if (carportInfoVo.getCarportInfoVo() != null) {
            // 新增、修改车位基本信息
            OwnerHouseCarportInfo newInfo = new OwnerHouseCarportInfo();
            BeanUtils.copyProperties(carportInfoVo.getCarportInfoVo(), newInfo);
            newInfo.setAssistChargingArea(CommonUtils.decimal2Long(carportInfoVo.getCarportInfoVo().getAssistChargingArea()));
            newInfo.setChargingArea(CommonUtils.decimal2Long(carportInfoVo.getCarportInfoVo().getChargingArea()));
            newInfo.setBuildingArea(CommonUtils.decimal2Long(carportInfoVo.getCarportInfoVo().getBuildingArea()));
            newInfo.setUpdateUserId(userId);
            newInfo.setUpdateUserName(userName);
            // 查看数据库中是否已有数据
            OwnerHouseCarportInfo info = ownerHouseCarportInfoMapper.selectByPrimaryKey(carportInfoVo.getCarportInfoVo().getHouseId());
            if (info == null) {
                newInfo.setCreateUserId(userId);
                newInfo.setCreateUserName(userName);
                index = ownerHouseCarportInfoMapper.insertSelective(newInfo);
            } else {
                index = ownerHouseCarportInfoMapper.updateByPrimaryKey(newInfo);
            }

        }
        if (carportInfoVo.getCarportExtendInfoVo() != null) {
            // 新增、修改车位详细信息
            OwnerHouseCarportExtendInfo newInfo = new OwnerHouseCarportExtendInfo();
            BeanUtils.copyProperties(carportInfoVo.getCarportExtendInfoVo(), newInfo);
            if (!CollectionUtils.isEmpty(carportInfoVo.getCarportExtendInfoVo().getMaintenanceDate())) {
                newInfo.setMaintenanceStartDate(carportInfoVo.getCarportExtendInfoVo().getMaintenanceDate().get(0));
                newInfo.setMaintenanceEndDate(carportInfoVo.getCarportExtendInfoVo().getMaintenanceDate().get(1));
            }
            newInfo.setInsideArea(CommonUtils.decimal2Long(carportInfoVo.getCarportExtendInfoVo().getInsideArea()));
            newInfo.setPoolArea(CommonUtils.decimal2Long(carportInfoVo.getCarportExtendInfoVo().getPoolArea()));
            // 查看数据库中是否已有数据
            OwnerHouseCarportExtendInfo info = ownerHouseCarportExtendInfoMapper.selectByPrimaryKey(carportInfoVo.getCarportExtendInfoVo().getHouseId());
            if (info == null) {
                ownerHouseCarportExtendInfoMapper.insertSelective(newInfo);
            } else {
                ownerHouseCarportExtendInfoMapper.updateByPrimaryKey(newInfo);
            }

        }
        return index;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int addPublicAreaDetail(OwnerPublicAreaVo publicAreaVo, Long userId, String userName) {
        BizException.isNull(publicAreaVo, "公共区域信息");
        int index = 0;
        if (publicAreaVo != null) {
            OwnerHousePublicAreaInfo newInfo = new OwnerHousePublicAreaInfo();
            BeanUtils.copyProperties(publicAreaVo, newInfo);
            newInfo.setBuildingArea(CommonUtils.decimal2Long(publicAreaVo.getBuildingArea()));
            newInfo.setUpdateUserId(userId);
            newInfo.setUpdateUserName(userName);
            OwnerHousePublicAreaInfo info = ownerHousePublicAreaInfoMapper.selectByPrimaryKey(publicAreaVo.getHouseId());
            if (info == null) {
                newInfo.setCreateUserId(userId);
                newInfo.setCreateUserName(userName);
                index = ownerHousePublicAreaInfoMapper.insertSelective(newInfo);
            } else {
                index = ownerHousePublicAreaInfoMapper.updateByPrimaryKey(newInfo);
            }
        }
        return index;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = true)
    public List<OwnerHouseRelationshipVo> listOwnerHouseRelation(Long ownerId, Byte houseType, Long precinctId) {
        List<OwnerHouseRelationshipVo> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("ownerId", ownerId);
        map.put("precinctId", precinctId);
        map.put("houseType", houseType);
        List<OwnerHouseRelationshipVo> ownerHouseRelationshipVos = ownerHouseRelationshipMapper.listOwnerHouseRelation(map);
        List<OwnerHouseRelationshipVo> rentHouseRelationshipVos = ownerHouseRelationshipMapper.listRentHouseRelation(map);

        for (Iterator<OwnerHouseRelationshipVo> iterator = ownerHouseRelationshipVos.iterator(); iterator.hasNext(); ) {
            OwnerHouseRelationshipVo ownerHouseRelationshipVo = (OwnerHouseRelationshipVo) iterator.next();
            for (Iterator<OwnerHouseRelationshipVo> iterator2 = rentHouseRelationshipVos.iterator(); iterator2.hasNext(); ) {
                OwnerHouseRelationshipVo ownerHouseRelationshipVo2 = (OwnerHouseRelationshipVo) iterator2.next();
                if (ownerHouseRelationshipVo.getHouseId().equals(ownerHouseRelationshipVo2.getHouseId())) {
                    iterator2.remove();
                }
            }
        }
        list.addAll(ownerHouseRelationshipVos);
        list.addAll(rentHouseRelationshipVos);
        if (!CollectionUtils.isEmpty(list)) {
            if (HouseTypeEnum.CARPORT.getValue().equals(houseType.toString())) {
                for (OwnerHouseRelationshipVo ownerHouseRelationshipVo : list) {
                    List<CarVo> carList = ownerCustomerCarMapper.listCarByHouseId(ownerHouseRelationshipVo.getHouseId());
                    String carNumbers = "";
                    if (!CollectionUtils.isEmpty(carList)) {
                        for (CarVo carVo : carList) {
                            if (StringUtils.hasLength(carVo.getCarNumber())) {
                                carNumbers = carNumbers + "," + carVo.getCarNumber();
                            }
                        }
                        if (StringUtils.hasLength(carNumbers)) {
                            carNumbers = carNumbers.substring(1);
                        }
                        ownerHouseRelationshipVo.setCarNumbers(carNumbers);
                    }
                }
            } else {
                Map<String, Object> mainHouseMap = new HashMap<>();
                List<Long> ownerIdList = new ArrayList<>();
                ownerIdList.add(ownerId);
                mainHouseMap.put("list", ownerIdList);
                mainHouseMap.put("precinctId", precinctId);
                List<OwnerCustomerMainHouse> mainHouseList = mainHouseMapper.listByOwnerHouse(mainHouseMap);
                for (OwnerHouseRelationshipVo ownerHouseRelationshipVo : list) {
                    Optional<OwnerCustomerMainHouse> optional = mainHouseList.stream().findAny().filter(mainHouse -> ownerHouseRelationshipVo.getHouseId().equals(mainHouse.getHouseId()));
                    if (optional != null && optional.isPresent()) {
                        ownerHouseRelationshipVo.setIsMainHouse(Constants.TRUE);
                    } else {
                        ownerHouseRelationshipVo.setIsMainHouse(Constants.FALSE);
                    }
                }
            }
        }

        return list;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = true)
    public List<OwnerHouseRelationshipVo> listOwnerPrecinctRelation(Long ownerId) {
        List<OwnerHouseRelationshipVo> list = new ArrayList<>();
        List<OwnerHouseRelationshipVo> ownerHouseRelationshipVos = ownerHouseRelationshipMapper.listOwnerPrecinctRelation(ownerId);
        List<OwnerHouseRelationshipVo> rentHouseRelationshipVos = ownerHouseRelationshipMapper.listRentPrecinctRelation(ownerId);
        for (Iterator<OwnerHouseRelationshipVo> iterator = ownerHouseRelationshipVos.iterator(); iterator.hasNext(); ) {
            OwnerHouseRelationshipVo ownerHouseRelationshipVo = (OwnerHouseRelationshipVo) iterator.next();
            for (Iterator<OwnerHouseRelationshipVo> iterator2 = rentHouseRelationshipVos.iterator(); iterator2.hasNext(); ) {
                OwnerHouseRelationshipVo ownerHouseRelationshipVo2 = (OwnerHouseRelationshipVo) iterator2.next();
                if (ownerHouseRelationshipVo.getPrecinctId().equals(ownerHouseRelationshipVo2.getPrecinctId())) {
                    iterator2.remove();
                }
            }
        }
        list.addAll(ownerHouseRelationshipVos);
        list.addAll(rentHouseRelationshipVos);
        return list;
    }

    // @Override
    // @ReadDataSource
    // public List<OwnerHouseRelationshipVo> loadAllRelationForOwner(Long ownerId, Byte houseType, Long houseId) {
    // Map<String, Object> map = new HashMap<>();
    // map.put("ownerId", ownerId);
    // map.put("houseType", houseType);
    // map.put("houseId", houseId);
    // return ownerHouseRelationshipMapper.listOwnerHouseRelation(map);
    // }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public List<HouseBaseInfoTreeNode> listHouseChildTree(Long organizationId, Long houseId) {
        Long start = new Date().getTime();
        Map<String, Object> params = new HashMap<>();
        params.put("organizationId", organizationId);
        params.put("parentId", houseId);
        List<OwnerHouseBaseInfo> allHouseBaseInfoList = ownerHouseBaseInfoMapper.listHouseBaseInfoByParam(params);
        // 生成房产树
        List<HouseBaseInfoTreeNode> houseBaseInfoTree = new ArrayList<>();
        Long end = new Date().getTime();
        Long time = end - start;
        System.out.println("listHouseChildTree===other======" + time);
        Long forstart = new Date().getTime();
        for (OwnerHouseBaseInfo ownerHouseBaseInfo : allHouseBaseInfoList) {
            if (HouseTypeEnum.ROOM.getValue().equals(ownerHouseBaseInfo.getHouseType()) || HouseTypeEnum.CARPORT.getValue().equals(ownerHouseBaseInfo.getHouseType())
                    || HouseTypeEnum.PUBLICAREA.getValue().equals(ownerHouseBaseInfo.getHouseType())) {
                ownerHouseBaseInfo.setIsHasChild(false);
            } else {
                OwnerHouseBaseInfo childInfo = ownerHouseBaseInfoMapper.checkParentByHouseId(ownerHouseBaseInfo.getHouseId());
                if (childInfo != null) {
                    ownerHouseBaseInfo.setIsHasChild(true);
                } else {
                    ownerHouseBaseInfo.setIsHasChild(false);
                }
            }

            HouseBaseInfoTreeNode houseBaseInfoTreeNode = new HouseBaseInfoTreeNode();
            BeanUtils.copyProperties(ownerHouseBaseInfo, houseBaseInfoTreeNode);
            houseBaseInfoTree.add(houseBaseInfoTreeNode);
        }
        Long forend = new Date().getTime();
        Long fortime = forend - forstart;
        System.out.println("listHouseChildTree====for========" + fortime);
        return houseBaseInfoTree;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public List<HouseBaseInfoTreeNode> listHouseTreeBySearch(String houseName, Long organizationId, Byte houseType) {
        if (CommonUtils.isNullOrBlank(houseName)) {
            return null;
        }
        List<HouseBaseInfoTreeNode> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("houseName", houseName);
        map.put("organizationId", organizationId);
        if (!CommonUtils.isObjectEmpty(houseType)) {
            map.put("houseType", houseType.toString());
        }
        List<OwnerHouseBaseInfo> houseBaseList = ownerHouseBaseInfoMapper.listHouseTreeBySearch(map);
        if (!CollectionUtils.isEmpty(houseBaseList)) {
            houseBaseList.forEach(houseBase -> {
                HouseBaseInfoTreeNode node = new HouseBaseInfoTreeNode();
                BeanUtils.copyProperties(houseBase, node);
                list.add(node);
            });
        }
        return list;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public HouseBaseInfoTreeNode detailHouseInTree(Long houseId) {
        OwnerHouseBaseInfo houseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
        if (houseBaseInfo == null) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "房产不存在");
        }
        // 如果是根节点直接返回
        if (Constants.SEPARATOR_PATH.equals(houseBaseInfo.getPath())) {
            HouseBaseInfoTreeNode treeNode = new HouseBaseInfoTreeNode();
            BeanUtils.copyProperties(houseBaseInfo, treeNode);
            OwnerHouseBaseInfo childInfo = ownerHouseBaseInfoMapper.checkParentByHouseId(houseId);
            if (childInfo != null) {
                treeNode.setIsHasChild(true);
            } else {
                treeNode.setIsHasChild(false);
            }
            return treeNode;
        }
        // 根据path查询房产节点
        List<OwnerHouseBaseInfo> houseTreeList = null;
        List<Long> pathIdList = StringUtils.handlerPath2List(houseBaseInfo.getPath());
        if (!CollectionUtils.isEmpty(pathIdList)) {
            houseTreeList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(pathIdList);
        }
        if (CollectionUtils.isEmpty(houseTreeList)) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "房产不存在");
        }
        houseTreeList.add(houseBaseInfo);
        OwnerHouseBaseInfo rootNode = new OwnerHouseBaseInfo();
        for (OwnerHouseBaseInfo ownerHouseBaseInfo : houseTreeList) {
            if (Constants.SEPARATOR_PATH.equals(ownerHouseBaseInfo.getPath())) {
                rootNode = ownerHouseBaseInfo;
            }
            OwnerHouseBaseInfo childInfo = ownerHouseBaseInfoMapper.checkParentByHouseId(ownerHouseBaseInfo.getHouseId());
            if (childInfo != null) {
                ownerHouseBaseInfo.setIsHasChild(true);
            } else {
                ownerHouseBaseInfo.setIsHasChild(false);
            }
        }
        // 递归整合房产树
        HouseBaseInfoTreeNode treeNode = generateTreeNode(rootNode.getHouseId(), houseTreeList);

        return treeNode;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public Map<String, List<HouseStageDetailVo>> listStageByhouseId(Long houseId, Long ownerId) {
        Map<String, List<HouseStageDetailVo>> resultMap = new HashMap<>();

        // 根据houseId 获取全部房态操作记录
        List<OwnerHouseStageDetail> stageDetailList = ownerHouseStageDetailMapper.listStageByHouseId(houseId);
        // 根据houseId 获取全部客户房产关系
        List<OwnerHouseRelationship> relationshipList = ownerHouseRelationshipMapper.listOwnerRelationHistoryByHouseId(houseId);
        // 按房态操作进行分组
        Map<String, List<OwnerHouseStageDetail>> map = stageDetailList.stream().collect(Collectors.groupingBy(OwnerHouseStageDetail::getHouseOperateType));
        // 获取当前用户的房产操作记录ID
        List<Long> allOwnerList = new ArrayList<>();
        List<Long> familyIdList = familyMapper.listAllFamilyByOwnerId(ownerId);
        allOwnerList.addAll(familyIdList);
        allOwnerList.add(ownerId);
        allOwnerList = allOwnerList.stream().distinct().collect(Collectors.toList());
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("houseId", houseId);
        detailMap.put("list", allOwnerList);
        List<Long> detailIdList = ownerHouseRelationshipMapper.listAllDetailId(detailMap);
        // 获取所有租户的房产操作记录ID
        List<Long> rentDetailList = stageRentMapper.listAllRentByOwnerId(ownerId);
        List<Long> allDetailList = new ArrayList<>();
        allDetailList.addAll(detailIdList);
        allDetailList.addAll(rentDetailList);
        allDetailList = allDetailList.stream().distinct().collect(Collectors.toList());
        // 售楼
        List<OwnerHouseStageDetail> salesList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(map.get(HouseOperateTypeEnum.SHOU_LOU.getValue()))) {
            salesList.addAll(map.get(HouseOperateTypeEnum.SHOU_LOU.getValue()));
        }
        if (!CollectionUtils.isEmpty(map.get(HouseOperateTypeEnum.ZHUAN_RANG.getValue()))) {
            salesList.addAll(map.get(HouseOperateTypeEnum.ZHUAN_RANG.getValue()));
        }
        if (!CollectionUtils.isEmpty(salesList)) {
            List<HouseStageDetailVo> salesStageVoList = new ArrayList<>();
            for (int i = 0; i < salesList.size(); i++) {
                OwnerHouseStageDetail ownerHouseStageDetail = salesList.get(i);
                HouseStageDetailVo houseStageDetailVo = null;
                for (Iterator<OwnerHouseRelationship> iterator = relationshipList.iterator(); iterator.hasNext(); ) {
                    OwnerHouseRelationship relationship = (OwnerHouseRelationship) iterator.next();
                    if (ownerHouseStageDetail.getDetailId().equals(relationship.getDetailId())) {
                        houseStageDetailVo = new HouseStageDetailVo();
                        houseStageDetailVo.setStageValue(HouseOperateTypeEnum.SHOU_LOU.getValue());
                        houseStageDetailVo.setDetailId(ownerHouseStageDetail.getDetailId());
                        houseStageDetailVo.setIsCurrentRecord(Constants.FALSE);
                        if (HouseOperateTypeEnum.SHOU_LOU.getValue().equals(ownerHouseStageDetail.getHouseOperateType())) {
                            houseStageDetailVo.setStage(HouseOperateTypeEnum.SHOU_LOU.getTitle());
                        } else {
                            houseStageDetailVo.setStage(HouseOperateTypeEnum.ZHUAN_RANG.getTitle());
                        }
                        if (OwnerConstants.OWNER_CATEGORY_OWNER.equals(relationship.getOwnerCategory())) {
                            houseStageDetailVo.setOwnerId(relationship.getOwnerId());
                            CustomerVo customerVo = ownerCustomerBaseInfoMapper.loadCustomer(relationship.getOwnerId());
                            if (customerVo != null) {
                                houseStageDetailVo.setOwnerName("业主: " + customerVo.getOwnerName());
                            }
                        }
                        houseStageDetailVo.setOperateDate(DateUtils.dateToString(ownerHouseStageDetail.getHandleTime(), DateUtils.YYYYMMDD_CROSS));
                        if (Constants.TRUE.equals(relationship.getIsCurrentRecord())) {
                            houseStageDetailVo.setIsNowStage(Constants.TRUE);
                        } else {
                            houseStageDetailVo.setIsNowStage(Constants.FALSE);
                        }
                        iterator.remove();
                        break;
                    }
                }
                if (houseStageDetailVo != null) {
                    for (Long detailId : allDetailList) {
                        if (ownerHouseStageDetail.getDetailId().equals(detailId)) {
                            houseStageDetailVo.setIsCurrentRecord(Constants.TRUE);
                        }
                    }
                    salesStageVoList.add(houseStageDetailVo);
                }
            }
            resultMap.put("salesStageVoList", salesStageVoList);
        }
        // 收房
        List<OwnerHouseStageDetail> takeList = map.get(HouseOperateTypeEnum.SHOU_FANG.getValue());
        if (!CollectionUtils.isEmpty(takeList)) {
            List<HouseStageDetailVo> takeStageVoList = new ArrayList<>();
            for (int i = 0; i < takeList.size(); i++) {
                OwnerHouseStageDetail ownerHouseStageDetail = takeList.get(i);
                HouseStageDetailVo houseStageDetailVo = null;
                for (Iterator<OwnerHouseRelationship> iterator = relationshipList.iterator(); iterator.hasNext(); ) {
                    OwnerHouseRelationship relationship = (OwnerHouseRelationship) iterator.next();
                    if (ownerHouseStageDetail.getDetailId().equals(relationship.getDetailId())) {
                        houseStageDetailVo = new HouseStageDetailVo();
                        houseStageDetailVo.setStageValue(HouseOperateTypeEnum.SHOU_FANG.getValue());
                        houseStageDetailVo.setDetailId(ownerHouseStageDetail.getDetailId());
                        houseStageDetailVo.setOwnerId(relationship.getOwnerId());
                        CustomerVo customerVo = ownerCustomerBaseInfoMapper.loadCustomer(relationship.getOwnerId());
                        if (customerVo != null) {
                            houseStageDetailVo.setOwnerName("收房人: " + customerVo.getOwnerName());
                        }
                        houseStageDetailVo.setIsCurrentRecord(Constants.FALSE);
                        houseStageDetailVo.setOperateDate(DateUtils.dateToString(ownerHouseStageDetail.getHandleTime(), DateUtils.YYYYMMDD_CROSS));
                        if (Constants.TRUE.equals(relationship.getIsCurrentRecord())) {
                            houseStageDetailVo.setIsNowStage(Constants.TRUE);
                        } else {
                            houseStageDetailVo.setIsNowStage(Constants.FALSE);
                        }
                        iterator.remove();
                        break;
                    }
                }
                if (houseStageDetailVo != null) {
                    for (Long detailId : allDetailList) {
                        if (ownerHouseStageDetail.getDetailId().equals(detailId)) {
                            houseStageDetailVo.setIsCurrentRecord(Constants.TRUE);
                        }
                    }
                    takeStageVoList.add(houseStageDetailVo);
                }
            }
            resultMap.put("takeStageVoList", takeStageVoList);
        }
        // 入住
        List<OwnerHouseStageDetail> checkInList = map.get(HouseOperateTypeEnum.RU_ZHU.getValue());
        if (!CollectionUtils.isEmpty(checkInList)) {
            List<HouseStageDetailVo> checkInStageVoList = new ArrayList<>();
            for (int i = 0; i < checkInList.size(); i++) {
                HouseStageDetailVo houseStageDetailVo = null;
                OwnerHouseStageDetail ownerHouseStageDetail = checkInList.get(i);
                for (Iterator<OwnerHouseRelationship> iterator = relationshipList.iterator(); iterator.hasNext(); ) {
                    OwnerHouseRelationship relationship = (OwnerHouseRelationship) iterator.next();
                    if (ownerHouseStageDetail.getDetailId().equals(relationship.getDetailId())) {
                        houseStageDetailVo = new HouseStageDetailVo();
                        houseStageDetailVo.setStageValue(HouseOperateTypeEnum.RU_ZHU.getValue());
                        houseStageDetailVo.setDetailId(ownerHouseStageDetail.getDetailId());
                        houseStageDetailVo.setOwnerId(relationship.getOwnerId());
                        CustomerVo customerVo = ownerCustomerBaseInfoMapper.loadCustomer(relationship.getOwnerId());
                        if (customerVo != null) {
                            houseStageDetailVo.setOwnerName("入住人: " + customerVo.getOwnerName());
                        }
                        houseStageDetailVo.setIsCurrentRecord(Constants.FALSE);
                        houseStageDetailVo.setOperateDate(DateUtils.dateToString(ownerHouseStageDetail.getHandleTime(), DateUtils.YYYYMMDD_CROSS));
                        if (Constants.TRUE.equals(relationship.getIsCurrentRecord())) {
                            houseStageDetailVo.setIsNowStage(Constants.TRUE);
                        } else {
                            houseStageDetailVo.setIsNowStage(Constants.FALSE);
                        }
                        iterator.remove();
                        break;
                    }
                }
                if (houseStageDetailVo != null) {
                    for (Long detailId : allDetailList) {
                        if (ownerHouseStageDetail.getDetailId().equals(detailId)) {
                            houseStageDetailVo.setIsCurrentRecord(Constants.TRUE);
                        }
                    }
                    checkInStageVoList.add(houseStageDetailVo);
                }
            }
            resultMap.put("checkInStageVoList", checkInStageVoList);
        }
        // 装修
        List<OwnerHouseStageDetail> decorateList = map.get(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
        if (!CollectionUtils.isEmpty(decorateList)) {
            OwnerHouseBaseInfo baseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
            List<OwnerHouseRelationshipVo> decorateRelationshipList = ownerHouseRelationshipMapper.listDecorateRelationByDetailId(decorateList);
            List<HouseStageDetailVo> decorateStageVoList = new ArrayList<>();
            for (int i = 0; i < decorateList.size(); i++) {
                HouseStageDetailVo houseStageDetailVo = null;
                OwnerHouseStageDetail ownerHouseStageDetail = decorateList.get(i);
                for (Iterator<OwnerHouseRelationshipVo> iterator = decorateRelationshipList.iterator(); iterator.hasNext(); ) {
                    OwnerHouseRelationshipVo relationship = (OwnerHouseRelationshipVo) iterator.next();
                    if (ownerHouseStageDetail.getDetailId().equals(relationship.getDetailId())) {
                        houseStageDetailVo = new HouseStageDetailVo();
                        houseStageDetailVo.setStageValue(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
                        houseStageDetailVo.setDetailId(ownerHouseStageDetail.getDetailId());
                        if (i == decorateList.size() - 1) {
                            HouseDecorateStageEnum decorateStageEnum = HouseDecorateStageEnum.getInstance(baseInfo.getDecorateStage());
                            switch (decorateStageEnum) {
                                case DECORATE_STAGE_NONE:
                                    break;
                                case DECORATE_STAGE_IN:
                                    houseStageDetailVo.setStage(HouseDecorateStageEnum.DECORATE_STAGE_IN.getTitle());
                                    break;
                                case DECORATE_STAGE_DONE:
                                    houseStageDetailVo.setStage(HouseDecorateStageEnum.DECORATE_STAGE_DONE.getTitle());
                                    break;
                                default:
                                    break;
                            }
                            houseStageDetailVo.setOperateDate(
                                    DateUtils.dateToString(relationship.getStartDate(), DateUtils.YYYYMMDD_CROSS) + "至" + DateUtils.dateToString(relationship.getEndDate(), DateUtils.YYYYMMDD_CROSS));
                        } else {
                            houseStageDetailVo.setStage(HouseDecorateStageEnum.DECORATE_STAGE_DONE.getTitle());
                            houseStageDetailVo.setOperateDate(
                                    DateUtils.dateToString(relationship.getStartDate(), DateUtils.YYYYMMDD_CROSS) + "至" + DateUtils.dateToString(relationship.getEndDate(), DateUtils.YYYYMMDD_CROSS));
                        }
                        houseStageDetailVo.setIsCurrentRecord(Constants.FALSE);
                        // houseStageDetailVo.setOperateDate(DateUtils.dateToString(ownerHouseStageDetail.getHandleTime(), DateUtils.YYYYMMDD_CROSS));
                        if (Constants.TRUE.equals(relationship.getIsCurrentRecord())) {
                            houseStageDetailVo.setIsNowStage(Constants.TRUE);
                        } else {
                            houseStageDetailVo.setIsNowStage(Constants.FALSE);
                        }
                        iterator.remove();
                        break;
                    }
                }
                if (houseStageDetailVo != null) {
                    for (Long detailId : allDetailList) {
                        if (ownerHouseStageDetail.getDetailId().equals(detailId)) {
                            houseStageDetailVo.setIsCurrentRecord(Constants.TRUE);
                        }
                    }
                    decorateStageVoList.add(houseStageDetailVo);
                }
            }
            resultMap.put("decorateStageVoList", decorateStageVoList);
        }
        // 出租
        List<OwnerHouseStageDetail> rentList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(map.get(HouseOperateTypeEnum.CHU_ZU.getValue()))) {
            rentList.addAll(map.get(HouseOperateTypeEnum.CHU_ZU.getValue()));
        }
        // 转租
        if (!CollectionUtils.isEmpty(map.get(HouseOperateTypeEnum.ZHUAN_ZU.getValue()))) {
            rentList.addAll(map.get(HouseOperateTypeEnum.ZHUAN_ZU.getValue()));
        }
        // 退租
        if (!CollectionUtils.isEmpty(map.get(HouseOperateTypeEnum.TUI_ZU.getValue()))) {
            rentList.addAll(map.get(HouseOperateTypeEnum.TUI_ZU.getValue()));
        }

        if (!CollectionUtils.isEmpty(rentList)) {
            List<HouseStageDetailVo> rentStageVoList = new ArrayList<>();
            // 根据houseId 获取全部客户房产关系
            List<OwnerHouseRelationshipVo> rentRelationshipList = ownerHouseRelationshipMapper.listRentRelationByDetailId(rentList);
            for (int i = 0; i < rentList.size(); i++) {
                OwnerHouseStageDetail ownerHouseStageDetail = rentList.get(i);
                HouseStageDetailVo houseStageDetailVo = null;
                for (Iterator<OwnerHouseRelationshipVo> iterator = rentRelationshipList.iterator(); iterator.hasNext(); ) {
                    OwnerHouseRelationshipVo relationshipVo = (OwnerHouseRelationshipVo) iterator.next();
                    if (ownerHouseStageDetail.getDetailId().equals(relationshipVo.getDetailId())) {
                        houseStageDetailVo = new HouseStageDetailVo();
                        houseStageDetailVo.setStageValue(HouseOperateTypeEnum.CHU_ZU.getValue());
                        houseStageDetailVo.setDetailId(ownerHouseStageDetail.getDetailId());
                        houseStageDetailVo.setOwnerId(relationshipVo.getRentOwnerId());
                        CustomerVo customerVo = ownerCustomerBaseInfoMapper.loadCustomer(relationshipVo.getRentOwnerId());
                        if (customerVo != null) {
                            houseStageDetailVo.setOwnerName("租户: " + customerVo.getOwnerName());
                        }
                        houseStageDetailVo.setIsCurrentRecord(Constants.FALSE);
                        if (HouseOperateTypeEnum.CHU_ZU.getValue().equals(ownerHouseStageDetail.getHouseOperateType())) {
                            houseStageDetailVo.setStage(HouseOperateTypeEnum.CHU_ZU.getTitle());
                            houseStageDetailVo.setOperateDate(DateUtils.dateToString(relationshipVo.getStartDate(), DateUtils.YYYYMMDD_CROSS) + "至"
                                    + DateUtils.dateToString(relationshipVo.getEndDate(), DateUtils.YYYYMMDD_CROSS));
                        } else if (HouseOperateTypeEnum.TUI_ZU.getValue().equals(ownerHouseStageDetail.getHouseOperateType())) {
                            houseStageDetailVo.setStage(HouseOperateTypeEnum.TUI_ZU.getTitle());
                            houseStageDetailVo.setOperateDate(DateUtils.dateToString(ownerHouseStageDetail.getHandleTime(), DateUtils.YYYYMMDD_CROSS));
                        }
                        if (Constants.TRUE.equals(relationshipVo.getIsCurrentRecord())) {
                            houseStageDetailVo.setIsNowStage(Constants.TRUE);
                        } else {
                            houseStageDetailVo.setIsNowStage(Constants.FALSE);
                        }
                        iterator.remove();
                        break;
                    }

                }

                if (houseStageDetailVo != null) {
                    for (Long detailId : allDetailList) {
                        if (ownerHouseStageDetail.getDetailId().equals(detailId)) {
                            houseStageDetailVo.setIsCurrentRecord(Constants.TRUE);
                        }
                    }
                    rentStageVoList.add(houseStageDetailVo);
                }

            }
            resultMap.put("rentStageVoList", rentStageVoList);
        }
        // 搬出
        // List<OwnerHouseStageDetail> moveOutList= map.get(HouseOperateTypeEnum.BAN_CHU);
        // if (!CollectionUtils.isEmpty(moveOutList)) {
        //
        // }
        // 延长空关
        // List<OwnerHouseStageDetail> prolongEmptyList= map.get(HouseOperateTypeEnum.YAN_CHANG_KONG_GUAN);
        // if (!CollectionUtils.isEmpty(prolongEmptyList)) {
        //
        // }
        return resultMap;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public Long getOwnerIdByhouseId(Long houseId) {
        return ownerHouseRelationshipMapper.loadOwnerIdByHouseId(houseId);
    }

    @SuppressWarnings("unchecked")
    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, Object> importHouse(List<Object> list, Long userId, String userName, Byte updateFlag, Map<String, Object> excelValueMap, List<Long> orgHouseIdList,
                                           List<NsSystemOrganizationVo> organizationVoList) {

        // 获取当前操作人所属公司下所有部门
        // 获取部门的项目权限
        // 如果无项目权限----该部门暂无此项目权限
        // 判断导入项目与部门权限是否匹配
        // 如果项目权限不匹配----该部门暂无此项目权限
        List<OwnerHouseBaseInfo> orgPrecinctList = new ArrayList<>();
        List<OwnerHouseBaseInfo> orgHouseList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(orgHouseIdList);
        List<Long> orgPrecinctIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(orgHouseList)) {
            for (OwnerHouseBaseInfo houseBaseInfo : orgHouseList) {
                if (!HouseTypeEnum.PRECINCT.equals(houseBaseInfo.getHouseType())) {
                    orgPrecinctIdList.add(houseBaseInfo.getPrecinctId());
                }
            }
            orgHouseList.removeIf(house -> !HouseTypeEnum.PRECINCT.equals(house.getHouseType()));
            if (!CollectionUtils.isEmpty(orgPrecinctIdList)) {
                orgPrecinctList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(orgPrecinctIdList);
            }
            orgPrecinctList.addAll(orgHouseList);
        }
        Map<String, Object> nameMap = new HashMap<>();
        String precinctName = (String) excelValueMap.get("precinctName");
        nameMap.put("houseName", precinctName);
        nameMap.put("houseType", Constants.HOUSE_TYPE_PRECINCT);
        OwnerHouseBaseInfo precinctBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
        String orgName = (String) excelValueMap.get("orgName");
        Optional<NsSystemOrganizationVo> optional = organizationVoList.stream().filter(org -> org.getOrganizationShortName().equals(orgName)).findFirst();
        NsSystemOrganizationVo organizationVo = optional.get();
        Long enterpriseId = organizationVo.getEnterpriseId();
        Long organizationId = organizationVo.getOrganizationId();
        precinctBase.setEnterpriseId(enterpriseId);
        precinctBase.setOrganizationId(organizationId);
        Map<String, Object> resultMap = new HashMap<>();
        Integer level = 0;
        String houseFullName = "";
        String path = "";
        Long parentId = 0L;
        nameMap.put("organizationId", organizationId);
        // 保存区域
        String areaName = (String) excelValueMap.get("areaName");
        OwnerHouseBaseInfo areaBase = null;
        if (!CommonUtils.isNullOrBlank(areaName)) {
            nameMap.put("houseName", areaName);
            nameMap.put("houseType", Constants.HOUSE_TYPE_AREA);
            areaBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
            // 获取区域sort
            Map<String, Object> params = new HashMap<>();
            params.put("organizationId", organizationId);
            if (areaBase != null) {
                params.put("parentId", areaBase.getParentId());
            } else {
                params.put("parentId", 0);
            }
            List<OwnerHouseBaseInfo> allHouseBaseInfoList = ownerHouseBaseInfoMapper.listHouseBaseInfoByParam(params);
            int sort = 0;
            if (!CollectionUtils.isEmpty(allHouseBaseInfoList)) {
                sort = allHouseBaseInfoList.get(allHouseBaseInfoList.size() - 1).getSort() + 1;
            }
            OwnerHouseBaseInfo houseBaseInfo = new OwnerHouseBaseInfo();
            if (areaBase == null) {
                // 新增
                houseBaseInfo.setOrganizationId(organizationId);
                houseBaseInfo.setEnterpriseId(enterpriseId);
                houseBaseInfo.setHouseName(areaName);
                houseBaseInfo.setHouseFullName(areaName);
                houseBaseInfo.setHouseType(HouseTypeEnum.AREA.getValue());
                houseBaseInfo.setStage(HouseStageEnum.KONG_ZHI.getValue());
                houseBaseInfo.setLevel(level);
                houseBaseInfo.setSort(sort);
                houseBaseInfo.setParentId(0L);
                houseBaseInfo.setIsBlockUp(Constants.FALSE.intValue());
                houseBaseInfo.setIsVirtual(Constants.FALSE.intValue());
                houseBaseInfo.setCreateUserId(userId);
                houseBaseInfo.setCreateUserName(userName);
                houseBaseInfo.setUpdateUserId(userId);
                houseBaseInfo.setUpdateUserName(userName);
                houseBaseInfo.setPath(Constants.SEPARATOR_PATH);
                ownerHouseBaseInfoMapper.insertSelective(houseBaseInfo);
                // if (index > 0) {
                // Map<String, Object> resultMap = new HashMap<>();
                // resultMap.put("resultType", Constants.RESULT_SUCCESS);
                // resultMap.put("message", areaName+",区域导入成功");
                // resultList.add(resultMap);
                // }
                path = Constants.SEPARATOR_PATH + houseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH;
                houseFullName = houseBaseInfo.getHouseFullName();
                level = houseBaseInfo.getLevel() + 1;
                parentId = houseBaseInfo.getHouseId();
            } else {
                path = areaBase.getPath() + areaBase.getHouseId() + Constants.SEPARATOR_PATH;
                houseFullName = areaBase.getHouseFullName();
                level = areaBase.getLevel() + 1;
                parentId = areaBase.getHouseId();
            }
        } else {
            path = Constants.SEPARATOR_PATH;
        }

        // 保存项目
        if (!CommonUtils.isNullOrBlank(precinctName)) {
            // 获取sort
            // Map<String, Object> params = new HashMap<>();
            // params.put("organizationId", organizationId);
            // if (precinctBase != null) {
            // params.put("parentId", precinctBase.getParentId());
            // }else {
            // params.put("parentId", parentId);
            // }
            // List<OwnerHouseBaseInfo> allHouseBaseInfoList = ownerHouseBaseInfoMapper.listHouseBaseInfoByParam(params);
            // int sort = 0;
            // if (!CollectionUtils.isEmpty(allHouseBaseInfoList)) {
            // sort = allHouseBaseInfoList.get(allHouseBaseInfoList.size()-1).getSort()+1;
            // }
            // OwnerHouseBaseInfo houseBaseInfo = new OwnerHouseBaseInfo();
            if (CommonUtils.isNullOrBlank(houseFullName)) {
                houseFullName = precinctName;
            } else {
                houseFullName = houseFullName + "-" + precinctName;
            }
            // if (precinctBase == null) {
            // houseBaseInfo.setOrganizationId(organizationId);
            // houseBaseInfo.setEnterpriseId(enterpriseId);
            // houseBaseInfo.setHouseName(precinctName);
            // houseBaseInfo.setHouseFullName(houseFullName);
            // houseBaseInfo.setHouseType(HouseTypeEnum.PRECINCT.getValue());
            // houseBaseInfo.setStage(HouseStageEnum.KONG_ZHI.getValue());
            // houseBaseInfo.setLevel(level);
            // houseBaseInfo.setSort(sort);
            // houseBaseInfo.setParentId(parentId);
            // houseBaseInfo.setIsBlockUp(Constants.FALSE.intValue());
            // houseBaseInfo.setIsVirtual(Constants.FALSE.intValue());
            // houseBaseInfo.setCreateUserId(userId);
            // houseBaseInfo.setUpdateUserId(userId);
            // houseBaseInfo.setPath(path);
            // int index = ownerHouseBaseInfoMapper.insertSelective(houseBaseInfo);
            // if (index > 0) {
            //// Map<String, Object> resultMap = new HashMap<>();
            //// resultMap.put("resultType", Constants.RESULT_SUCCESS);
            //// resultMap.put("message", houseFullName+",区域导入成功");
            //// resultList.add(resultMap);
            //
            // //新增项目编号
            // OwnerHousePrecinctInfo newInfo = new OwnerHousePrecinctInfo();
            // newInfo.setHouseId(houseBaseInfo.getHouseId());
            // String proNo = (String) excelValueMap.get("proNo");
            // if (CommonUtils.isNullOrBlank(proNo)) {
            // proNo = createHouseNoByHouseName(precinctName);
            // }
            // newInfo.setProNo(proNo);
            // String proTypeId = (String) excelValueMap.get("proTypeId");
            // //TODO 数据字典获取相应value
            // newInfo.setProTypeId(proTypeId);
            // newInfo.setUpdateUserId(userId);
            // newInfo.setCreateUserId(userId);
            // ownerHousePrecinctInfoMapper.insertSelective(newInfo);
            // }
            //
            // path = path + houseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH;
            // houseFullName = houseBaseInfo.getHouseFullName();
            // level = houseBaseInfo.getLevel() + 1;
            // parentId = houseBaseInfo.getHouseId();
            // }else {
            if (Constants.TRUE.equals(updateFlag)) {
                // 修改项目编号
                // OwnerHousePrecinctInfo oldInfo = ownerHousePrecinctInfoMapper.selectByPrimaryKey(precinctBase.getHouseId());

                String proNo = (String) excelValueMap.get("proNo");
                if (CommonUtils.isNullOrBlank(proNo)) {
                    proNo = createHouseNoByHouseName(precinctName);
                }
                OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                baseInfo.setHouseId(precinctBase.getHouseId());
                baseInfo.setHouseNo(proNo);
                ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
                OwnerHousePrecinctInfo newInfo = new OwnerHousePrecinctInfo();
                newInfo.setHouseId(precinctBase.getHouseId());
                // newInfo.setProNo(proNo);
                String proTypeId = (String) excelValueMap.get("proTypeId");
                newInfo.setProTypeId(proTypeId);
                newInfo.setUpdateUserId(userId);
                newInfo.setUpdateUserName(userName);
                newInfo.setCreateUserId(userId);
                newInfo.setCreateUserName(userName);
                ownerHousePrecinctInfoMapper.updateByPrimaryKeySelective(newInfo);
            }

            // if (index > 0) {
            // Map<String, Object> resultMap = new HashMap<>();
            // resultMap.put("resultType", Constants.RESULT_WARN);
            // String oldStr = JSONObject.toJSONString(oldInfo);
            // String newStr = JSONObject.toJSONString(newInfo);
            // CommonUtils.distinguish(oldStr, newStr);
            // resultMap.put("message", houseFullName+",项目导入成功，警告：记录有更新，项目类型"+oldInfo.getProTypeId()+"，更新为"+newInfo.getProTypeId());
            // resultList.add(resultMap);
            // }
            path = precinctBase.getPath() + precinctBase.getHouseId() + Constants.SEPARATOR_PATH;
            houseFullName = precinctBase.getHouseFullName();
            level = precinctBase.getLevel() + 1;
            parentId = precinctBase.getHouseId();
            // }

        }
        // 保存组团
        String clusterName = (String) excelValueMap.get("clusterName");
        Long precinctId = 0L;
        Long buildingId = 0L;
        if (!CommonUtils.isNullOrBlank(clusterName)) {
            // 获取归属的项目ID和楼栋ID
            List<Long> pathIdList = StringUtils.handlerPath2List(path);
            if (!CollectionUtils.isEmpty(pathIdList)) {
                List<OwnerHouseBaseInfo> houseBaseInfos = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(pathIdList);
                Map<String, Object> houseIdMap = getHouseInfoByPath(pathIdList, houseBaseInfos, null);

                List<Long> projectIdList = (List<Long>) houseIdMap.get(KEY_PRECINCT_ID_LIST);
                List<Long> buildingIdList = (List<Long>) houseIdMap.get(KEY_BUILDING_ID_LIST);

                if (!CollectionUtils.isEmpty(projectIdList)) {
                    precinctId = projectIdList.get(0);
                }
                if (!CollectionUtils.isEmpty(buildingIdList)) {
                    buildingId = buildingIdList.get(0);
                }
            }
            // 查询导入组团是否已存在
            nameMap.put("houseName", clusterName);
            nameMap.put("houseType", Constants.HOUSE_TYPE_CLUSTER);
            nameMap.put("precinctId", precinctId);
            OwnerHouseBaseInfo clusterBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
            // 获取sort
            Map<String, Object> params = new HashMap<>();
            params.put("organizationId", organizationId);
            if (clusterBase != null) {
                params.put("parentId", clusterBase.getParentId());
            } else {
                params.put("parentId", parentId);
            }
            List<OwnerHouseBaseInfo> allHouseBaseInfoList = ownerHouseBaseInfoMapper.listHouseBaseInfoByParam(params);
            int sort = 0;
            if (!CollectionUtils.isEmpty(allHouseBaseInfoList)) {
                sort = allHouseBaseInfoList.get(allHouseBaseInfoList.size() - 1).getSort() + 1;
            }
            OwnerHouseBaseInfo houseBaseInfo = new OwnerHouseBaseInfo();
            if (CommonUtils.isNullOrBlank(houseFullName)) {
                houseFullName = clusterName;
            } else {
                houseFullName = houseFullName + "-" + clusterName;
            }
            String clusterNo = (String) excelValueMap.get("clusterNo");
            if (CommonUtils.isNullOrBlank(clusterNo)) {
                clusterNo = createHouseNoByHouseName(clusterName);
            }
            if (clusterBase == null) {
                houseBaseInfo.setOrganizationId(organizationId);
                houseBaseInfo.setEnterpriseId(enterpriseId);
                houseBaseInfo.setHouseName(clusterName);
                houseBaseInfo.setHouseFullName(houseFullName);
                houseBaseInfo.setHouseType(HouseTypeEnum.CLUSTER.getValue());
                houseBaseInfo.setBuildingId(buildingId);
                houseBaseInfo.setPrecinctId(precinctId);
                houseBaseInfo.setStage(HouseStageEnum.KONG_ZHI.getValue());
                houseBaseInfo.setLevel(level);
                houseBaseInfo.setSort(sort);
                houseBaseInfo.setParentId(parentId);
                houseBaseInfo.setHouseNo(clusterNo);
                houseBaseInfo.setIsBlockUp(Constants.FALSE.intValue());
                houseBaseInfo.setIsVirtual(Constants.FALSE.intValue());
                houseBaseInfo.setCreateUserId(userId);
                houseBaseInfo.setCreateUserName(userName);
                houseBaseInfo.setUpdateUserId(userId);
                houseBaseInfo.setUpdateUserName(userName);
                houseBaseInfo.setPath(path);
                ownerHouseBaseInfoMapper.insertSelective(houseBaseInfo);

                // 新增组团编号
                OwnerHouseClusterInfo newInfo = new OwnerHouseClusterInfo();
                newInfo.setHouseId(houseBaseInfo.getHouseId());

                newInfo.setUpdateUserId(userId);
                newInfo.setCreateUserName(userName);
                newInfo.setUpdateUserName(userName);
                newInfo.setCreateUserId(userId);
                ownerHouseClusterInfoMapper.insertSelective(newInfo);

                path = path + houseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH;
                houseFullName = houseBaseInfo.getHouseFullName();
                level = houseBaseInfo.getLevel() + 1;
                parentId = houseBaseInfo.getHouseId();
            } else {
                if (Constants.TRUE.equals(updateFlag)) {
                    // 修改组团编号

                    OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                    baseInfo.setHouseId(clusterBase.getHouseId());
                    baseInfo.setHouseNo(clusterNo);
                    baseInfo.setUpdateUserId(userId);
                    baseInfo.setUpdateUserName(userName);
                    ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);

                    OwnerHouseClusterInfo newInfo = new OwnerHouseClusterInfo();
                    newInfo.setHouseId(clusterBase.getHouseId());
                    // newInfo.setClusterNo(clusterNo);
                    newInfo.setUpdateUserId(userId);
                    newInfo.setUpdateUserName(userName);
                    newInfo.setCreateUserId(userId);
                    newInfo.setCreateUserName(userName);

                    ownerHouseClusterInfoMapper.updateByPrimaryKeySelective(newInfo);
                }

                path = clusterBase.getPath() + clusterBase.getHouseId() + Constants.SEPARATOR_PATH;
                houseFullName = clusterBase.getHouseFullName();
                level = clusterBase.getLevel() + 1;
                parentId = clusterBase.getHouseId();
            }
        }
        // 保存楼栋
        String buildingName = (String) excelValueMap.get("buildingName");
        if (!CommonUtils.isNullOrBlank(buildingName)) {
            // 获取归属的项目ID和楼栋ID
            List<Long> pathIdList = StringUtils.handlerPath2List(path);
            if (!CollectionUtils.isEmpty(pathIdList)) {
                List<OwnerHouseBaseInfo> houseBaseInfos = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(pathIdList);
                Map<String, Object> houseIdMap = getHouseInfoByPath(pathIdList, houseBaseInfos, null);

                List<Long> projectIdList = (List<Long>) houseIdMap.get(KEY_PRECINCT_ID_LIST);
                List<Long> buildingIdList = (List<Long>) houseIdMap.get(KEY_BUILDING_ID_LIST);

                if (!CollectionUtils.isEmpty(projectIdList)) {
                    precinctId = projectIdList.get(0);
                }
                if (!CollectionUtils.isEmpty(buildingIdList)) {
                    buildingId = buildingIdList.get(0);
                }
            }

            // 查询导入楼幢是否已存在
            nameMap.put("houseName", buildingName);
            nameMap.put("houseType", Constants.HOUSE_TYPE_BUILD);
            nameMap.put("parentId", parentId);
            OwnerHouseBaseInfo buildingBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
            // 获取sort
            Map<String, Object> params = new HashMap<>();
            params.put("organizationId", organizationId);
            if (buildingBase != null) {
                params.put("parentId", buildingBase.getParentId());
            } else {
                params.put("parentId", parentId);
            }
            List<OwnerHouseBaseInfo> allHouseBaseInfoList = ownerHouseBaseInfoMapper.listHouseBaseInfoByParam(params);
            int sort = 0;
            if (!CollectionUtils.isEmpty(allHouseBaseInfoList)) {
                sort = allHouseBaseInfoList.get(allHouseBaseInfoList.size() - 1).getSort() + 1;
            }
            OwnerHouseBaseInfo houseBaseInfo = new OwnerHouseBaseInfo();
            if (CommonUtils.isNullOrBlank(houseFullName)) {
                houseFullName = buildingName;
            } else {
                houseFullName = houseFullName + "-" + buildingName;
            }
            String buildingNo = (String) excelValueMap.get("buildingNo");
            if (CommonUtils.isNullOrBlank(buildingNo)) {
                buildingNo = createHouseNoByHouseName(buildingName);
            }
            if (buildingBase == null) {
                houseBaseInfo.setOrganizationId(organizationId);
                houseBaseInfo.setEnterpriseId(enterpriseId);
                houseBaseInfo.setHouseName(buildingName);
                houseBaseInfo.setHouseNo(buildingNo);
                houseBaseInfo.setHouseFullName(houseFullName);
                houseBaseInfo.setHouseType(HouseTypeEnum.BUILDING.getValue());
                houseBaseInfo.setBuildingId(buildingId);
                houseBaseInfo.setPrecinctId(precinctId);
                houseBaseInfo.setStage(HouseStageEnum.KONG_ZHI.getValue());
                houseBaseInfo.setLevel(level);
                houseBaseInfo.setSort(sort);
                houseBaseInfo.setParentId(parentId);
                houseBaseInfo.setIsBlockUp(Constants.FALSE.intValue());
                houseBaseInfo.setIsVirtual(Constants.FALSE.intValue());
                houseBaseInfo.setCreateUserId(userId);
                houseBaseInfo.setCreateUserName(userName);
                houseBaseInfo.setUpdateUserId(userId);
                houseBaseInfo.setUpdateUserName(userName);
                houseBaseInfo.setPath(path);
                ownerHouseBaseInfoMapper.insertSelective(houseBaseInfo);

                // 新增楼栋编号
                OwnerHouseBuildingInfo newInfo = new OwnerHouseBuildingInfo();
                newInfo.setHouseId(houseBaseInfo.getHouseId());
                // newInfo.setBuildingNo(buildingNo);
                newInfo.setUpdateUserId(userId);
                newInfo.setUpdateUserName(userName);
                newInfo.setCreateUserId(userId);
                newInfo.setCreateUserName(userName);
                ownerHouseBuildingInfoMapper.insertSelective(newInfo);

                path = path + houseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH;
                houseFullName = houseBaseInfo.getHouseFullName();
                level = houseBaseInfo.getLevel() + 1;
                parentId = houseBaseInfo.getHouseId();
            } else {
                if (Constants.TRUE.equals(updateFlag)) {
                    // 修改楼栋编号

                    OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                    baseInfo.setHouseId(buildingBase.getHouseId());
                    baseInfo.setHouseNo(buildingNo);
                    baseInfo.setUpdateUserId(userId);
                    baseInfo.setUpdateUserName(userName);
                    ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);

                    OwnerHouseBuildingInfo newInfo = new OwnerHouseBuildingInfo();
                    newInfo.setHouseId(buildingBase.getHouseId());
                    // newInfo.setBuildingNo(buildingNo);
                    newInfo.setUpdateUserId(userId);
                    newInfo.setUpdateUserName(userName);
                    newInfo.setCreateUserId(userId);
                    newInfo.setCreateUserName(userName);
                    ownerHouseBuildingInfoMapper.updateByPrimaryKeySelective(newInfo);
                }

                path = buildingBase.getPath() + buildingBase.getHouseId() + Constants.SEPARATOR_PATH;
                houseFullName = buildingBase.getHouseFullName();
                level = buildingBase.getLevel() + 1;
                parentId = buildingBase.getHouseId();
            }
        }
        // 保存单元
        String unitName = (String) excelValueMap.get("unitName");
        if (!CommonUtils.isNullOrBlank(unitName)) {
            // 获取归属的项目ID和楼栋ID
            List<Long> pathIdList = StringUtils.handlerPath2List(path);
            if (!CollectionUtils.isEmpty(pathIdList)) {
                List<OwnerHouseBaseInfo> houseBaseInfos = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(pathIdList);
                Map<String, Object> houseIdMap = getHouseInfoByPath(pathIdList, houseBaseInfos, null);

                List<Long> projectIdList = (List<Long>) houseIdMap.get(KEY_PRECINCT_ID_LIST);
                List<Long> buildingIdList = (List<Long>) houseIdMap.get(KEY_BUILDING_ID_LIST);

                if (!CollectionUtils.isEmpty(projectIdList)) {
                    precinctId = projectIdList.get(0);
                }
                if (!CollectionUtils.isEmpty(buildingIdList)) {
                    buildingId = buildingIdList.get(0);
                }
            }

            // 查询导入单元是否已存在
            nameMap.put("houseName", unitName);
            nameMap.put("houseType", Constants.HOUSE_TYPE_UNIT);
            nameMap.put("parentId", parentId);
            OwnerHouseBaseInfo unitBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
            // 获取sort
            Map<String, Object> params = new HashMap<>();
            params.put("organizationId", organizationId);
            if (unitBase != null) {
                params.put("parentId", unitBase.getParentId());
            } else {
                params.put("parentId", parentId);
            }
            List<OwnerHouseBaseInfo> allHouseBaseInfoList = ownerHouseBaseInfoMapper.listHouseBaseInfoByParam(params);
            int sort = 0;
            if (!CollectionUtils.isEmpty(allHouseBaseInfoList)) {
                sort = allHouseBaseInfoList.get(allHouseBaseInfoList.size() - 1).getSort() + 1;
            }
            OwnerHouseBaseInfo houseBaseInfo = new OwnerHouseBaseInfo();
            if (CommonUtils.isNullOrBlank(houseFullName)) {
                houseFullName = unitName;
            } else {
                houseFullName = houseFullName + "-" + unitName;
            }
            String unitNo = (String) excelValueMap.get("unitNo");
            if (CommonUtils.isNullOrBlank(unitNo)) {
                unitNo = createHouseNoByHouseName(unitName);
            }
            if (unitBase == null) {
                houseBaseInfo.setOrganizationId(organizationId);
                houseBaseInfo.setEnterpriseId(enterpriseId);
                houseBaseInfo.setHouseName(unitName);
                houseBaseInfo.setHouseNo(unitNo);
                houseBaseInfo.setHouseFullName(houseFullName);
                houseBaseInfo.setHouseType(HouseTypeEnum.UNIT.getValue());
                houseBaseInfo.setBuildingId(buildingId);
                houseBaseInfo.setPrecinctId(precinctId);
                houseBaseInfo.setStage(HouseStageEnum.KONG_ZHI.getValue());
                houseBaseInfo.setLevel(level);
                houseBaseInfo.setSort(sort);
                houseBaseInfo.setParentId(parentId);
                houseBaseInfo.setIsBlockUp(Constants.FALSE.intValue());
                houseBaseInfo.setIsVirtual(Constants.FALSE.intValue());
                houseBaseInfo.setCreateUserId(userId);
                houseBaseInfo.setCreateUserName(userName);
                houseBaseInfo.setUpdateUserId(userId);
                houseBaseInfo.setUpdateUserName(userName);
                houseBaseInfo.setPath(path);
                ownerHouseBaseInfoMapper.insertSelective(houseBaseInfo);

                // 新增单元编号
                OwnerHouseUnitInfo newInfo = new OwnerHouseUnitInfo();
                newInfo.setHouseId(houseBaseInfo.getHouseId());

                // newInfo.setUnitNo(unitNo);
                newInfo.setUpdateUserId(userId);
                newInfo.setUpdateUserName(userName);
                newInfo.setCreateUserId(userId);
                newInfo.setCreateUserName(userName);
                ownerHouseUnitInfoMapper.insertSelective(newInfo);

                path = path + houseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH;
                houseFullName = houseBaseInfo.getHouseFullName();
                level = houseBaseInfo.getLevel() + 1;
                parentId = houseBaseInfo.getHouseId();
            } else {
                if (Constants.TRUE.equals(updateFlag)) {
                    // 修改单元编号
                    OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                    baseInfo.setHouseId(unitBase.getHouseId());
                    baseInfo.setHouseNo(unitNo);
                    baseInfo.setUpdateUserId(userId);
                    baseInfo.setUpdateUserName(userName);
                    ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);

                    OwnerHouseUnitInfo newInfo = new OwnerHouseUnitInfo();
                    newInfo.setHouseId(unitBase.getHouseId());
                    newInfo.setUnitNo(unitNo);
                    newInfo.setUpdateUserId(userId);
                    newInfo.setUpdateUserName(userName);
                    newInfo.setCreateUserId(userId);
                    newInfo.setCreateUserName(userName);
                    ownerHouseUnitInfoMapper.updateByPrimaryKeySelective(newInfo);
                }

                path = unitBase.getPath() + unitBase.getHouseId() + Constants.SEPARATOR_PATH;
                houseFullName = unitBase.getHouseFullName();
                level = unitBase.getLevel() + 1;
                parentId = unitBase.getHouseId();
            }
        }
        // 保存车库
        List<Object> garageNameList = (List<Object>) excelValueMap.get("garageNameList");
        List<Object> garageNoList = (List<Object>) excelValueMap.get("garageNoList");
        if (!CollectionUtils.isEmpty(garageNameList)) {
            for (int i = 0; i < garageNameList.size(); i++) {
                String garageName = (String) garageNameList.get(i);
                if (!CommonUtils.isNullOrBlank(garageName)) {
                    // 获取归属的项目ID和楼栋ID
                    List<Long> pathIdList = StringUtils.handlerPath2List(path);
                    if (!CollectionUtils.isEmpty(pathIdList)) {
                        List<OwnerHouseBaseInfo> houseBaseInfos = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(pathIdList);
                        Map<String, Object> houseIdMap = getHouseInfoByPath(pathIdList, houseBaseInfos, null);

                        List<Long> projectIdList = (List<Long>) houseIdMap.get(KEY_PRECINCT_ID_LIST);
                        List<Long> buildingIdList = (List<Long>) houseIdMap.get(KEY_BUILDING_ID_LIST);

                        if (!CollectionUtils.isEmpty(projectIdList)) {
                            precinctId = projectIdList.get(0);
                        }
                        if (!CollectionUtils.isEmpty(buildingIdList)) {
                            buildingId = buildingIdList.get(0);
                        }
                    }

                    // 查询导入车库是否已存在
                    nameMap.put("houseName", garageName);
                    nameMap.put("houseType", Constants.HOUSE_TYPE_GARAGE);
                    nameMap.put("parentId", parentId);
                    OwnerHouseBaseInfo garageBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
                    // 获取sort
                    Map<String, Object> params = new HashMap<>();
                    params.put("organizationId", organizationId);
                    if (garageBase != null) {
                        params.put("parentId", garageBase.getParentId());
                    } else {
                        params.put("parentId", parentId);
                    }
                    List<OwnerHouseBaseInfo> allHouseBaseInfoList = ownerHouseBaseInfoMapper.listHouseBaseInfoByParam(params);
                    int sort = 0;
                    if (!CollectionUtils.isEmpty(allHouseBaseInfoList)) {
                        sort = allHouseBaseInfoList.get(allHouseBaseInfoList.size() - 1).getSort() + 1;
                    }
                    OwnerHouseBaseInfo houseBaseInfo = new OwnerHouseBaseInfo();
                    if (CommonUtils.isNullOrBlank(houseFullName)) {
                        houseFullName = garageName;
                    } else {
                        houseFullName = houseFullName + "-" + garageName;
                    }
                    String garageNo = "";
                    if (!CollectionUtils.isEmpty(garageNoList)) {
                        garageNo = (String) garageNoList.get(i);
                    }
                    if (CommonUtils.isNullOrBlank(garageNo)) {
                        garageNo = createHouseNoByHouseName(garageName);
                    }
                    if (garageBase == null) {
                        houseBaseInfo.setOrganizationId(organizationId);
                        houseBaseInfo.setEnterpriseId(enterpriseId);
                        houseBaseInfo.setHouseName(garageName);
                        houseBaseInfo.setHouseNo(garageNo);
                        houseBaseInfo.setHouseFullName(houseFullName);
                        houseBaseInfo.setHouseType(HouseTypeEnum.GARAGE.getValue());
                        houseBaseInfo.setBuildingId(buildingId);
                        houseBaseInfo.setPrecinctId(precinctId);
                        houseBaseInfo.setStage(HouseStageEnum.KONG_ZHI.getValue());
                        houseBaseInfo.setLevel(level);
                        houseBaseInfo.setSort(sort);
                        houseBaseInfo.setParentId(parentId);
                        houseBaseInfo.setIsBlockUp(Constants.FALSE.intValue());
                        houseBaseInfo.setIsVirtual(Constants.FALSE.intValue());
                        houseBaseInfo.setCreateUserId(userId);
                        houseBaseInfo.setCreateUserName(userName);
                        houseBaseInfo.setUpdateUserId(userId);
                        houseBaseInfo.setUpdateUserName(userName);
                        houseBaseInfo.setPath(path);
                        ownerHouseBaseInfoMapper.insertSelective(houseBaseInfo);

                        // 新增车库编号
                        OwnerHouseGarageInfo newInfo = new OwnerHouseGarageInfo();
                        newInfo.setHouseId(houseBaseInfo.getHouseId());
                        // newInfo.setGarageNo(garageNo);
                        newInfo.setUpdateUserId(userId);
                        newInfo.setUpdateUserName(userName);
                        newInfo.setCreateUserId(userId);
                        newInfo.setCreateUserName(userName);
                        ownerHouseGarageInfoMapper.insertSelective(newInfo);

                        path = path + houseBaseInfo.getHouseId() + Constants.SEPARATOR_PATH;
                        houseFullName = houseBaseInfo.getHouseFullName();
                        level = houseBaseInfo.getLevel() + 1;
                        parentId = houseBaseInfo.getHouseId();
                    } else {
                        if (Constants.TRUE.equals(updateFlag)) {
                            // 修改车库编号
                            OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                            baseInfo.setHouseId(garageBase.getHouseId());
                            baseInfo.setHouseNo(garageNo);
                            baseInfo.setUpdateUserId(userId);
                            baseInfo.setUpdateUserName(userName);
                            ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);

                            OwnerHouseGarageInfo newInfo = new OwnerHouseGarageInfo();
                            newInfo.setHouseId(garageBase.getHouseId());
                            // newInfo.setGarageNo(garageNo);
                            newInfo.setUpdateUserId(userId);
                            newInfo.setUpdateUserName(userName);
                            newInfo.setCreateUserId(userId);
                            newInfo.setCreateUserName(userName);
                            ownerHouseGarageInfoMapper.updateByPrimaryKeySelective(newInfo);
                        }

                        path = garageBase.getPath() + garageBase.getHouseId() + Constants.SEPARATOR_PATH;
                        houseFullName = garageBase.getHouseFullName();
                        level = garageBase.getLevel() + 1;
                        parentId = garageBase.getHouseId();
                    }
                }
            }
        }
        long houseId = 0;
        // 保存房产
        List<String> roomTypeList = (List<String>) excelValueMap.get("roomTypeList");
        List<String> carPortTypeList = (List<String>) excelValueMap.get("carPortTypeList");
        String roomType = (String) excelValueMap.get("roomType");
        if (roomTypeList.contains(roomType)) {
            String roomName = (String) excelValueMap.get("roomName");
            String roomShortName = (String) excelValueMap.get("roomShortName");
            // 数据字典获取相应value
            String roomTypeId = (String) excelValueMap.get("roomTypeId");
            String roomNo = (String) excelValueMap.get("roomNo");
            BigDecimal chargingArea = new BigDecimal((String) excelValueMap.get("chargingArea"));
            BigDecimal assistChargingArea = new BigDecimal((String) excelValueMap.get("assistChargingArea"));
            BigDecimal buildingArea = new BigDecimal((String) excelValueMap.get("buildingArea"));
            BigDecimal insideArea = new BigDecimal((String) excelValueMap.get("insideArea"));
            BigDecimal poolArea = new BigDecimal((String) excelValueMap.get("poolArea"));
            BigDecimal gardenArea = new BigDecimal((String) excelValueMap.get("gardenArea"));
            BigDecimal basementArea = new BigDecimal((String) excelValueMap.get("basementArea"));
            BigDecimal giftArea = new BigDecimal((String) excelValueMap.get("giftArea"));

            if (!CommonUtils.isNullOrBlank(roomName)) {
                // 获取归属的项目ID和楼栋ID
                List<Long> pathIdList = StringUtils.handlerPath2List(path);
                List<String> projectNameList = new ArrayList<>();
                List<String> groupNameList = new ArrayList<>();
                List<String> buildingNameList = new ArrayList<>();
                List<String> unitNameList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(pathIdList)) {
                    List<OwnerHouseBaseInfo> houseBaseInfos = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(pathIdList);
                    Map<String, Object> houseIdMap = getHouseInfoByPath(pathIdList, houseBaseInfos, null);

                    List<Long> projectIdList = (List<Long>) houseIdMap.get(KEY_PRECINCT_ID_LIST);
                    List<Long> buildingIdList = (List<Long>) houseIdMap.get(KEY_BUILDING_ID_LIST);
                    projectNameList = (List<String>) houseIdMap.get(KEY_PRECINCT_NAME_LIST);
                    groupNameList = (List<String>) houseIdMap.get(KEY_GROUP_NAME_LIST);
                    buildingNameList = (List<String>) houseIdMap.get(KEY_BUILDING_NAME_LIST);
                    unitNameList = (List<String>) houseIdMap.get(KEY_UNIT_NAME_LIST);
                    if (!CollectionUtils.isEmpty(projectIdList)) {
                        precinctId = projectIdList.get(0);
                    }
                    if (!CollectionUtils.isEmpty(buildingIdList)) {
                        buildingId = buildingIdList.get(0);
                    }
                }
                // 查询导入房产是否已存在
                nameMap.put("houseName", roomName);
                nameMap.put("houseType", Constants.HOUSE_TYPE_HOUSE);
                nameMap.put("parentId", parentId);
                OwnerHouseBaseInfo roomBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
                // 获取sort
                Map<String, Object> params = new HashMap<>();
                params.put("organizationId", organizationId);
                if (roomBase != null) {
                    params.put("parentId", roomBase.getParentId());
                } else {
                    params.put("parentId", parentId);
                }
                List<OwnerHouseBaseInfo> allHouseBaseInfoList = ownerHouseBaseInfoMapper.listHouseBaseInfoByParam(params);
                int sort = 0;
                if (!CollectionUtils.isEmpty(allHouseBaseInfoList)) {
                    sort = allHouseBaseInfoList.get(allHouseBaseInfoList.size() - 1).getSort() + 1;
                }
                OwnerHouseBaseInfo houseBaseInfo = new OwnerHouseBaseInfo();
                if (CommonUtils.isNullOrBlank(houseFullName)) {
                    houseFullName = roomName;
                } else {
                    houseFullName = houseFullName + "-" + roomName;
                }
                if (CommonUtils.isNullOrBlank(roomNo)) {
                    roomNo = createHouseNoByHouseName(roomName);
                }
                if (roomBase == null) {
                    houseBaseInfo.setOrganizationId(organizationId);
                    houseBaseInfo.setEnterpriseId(enterpriseId);
                    houseBaseInfo.setHouseName(roomName);
                    houseBaseInfo.setHouseNo(roomNo);
                    houseBaseInfo.setHouseFullName(houseFullName);
                    houseBaseInfo.setBuildingId(buildingId);
                    houseBaseInfo.setPrecinctId(precinctId);
                    houseBaseInfo.setHouseType(HouseTypeEnum.ROOM.getValue());
                    houseBaseInfo.setStage(HouseStageEnum.KONG_ZHI.getValue());
                    houseBaseInfo.setLevel(level);
                    houseBaseInfo.setSort(sort);
                    houseBaseInfo.setParentId(parentId);
                    houseBaseInfo.setIsBlockUp(Constants.FALSE.intValue());
                    houseBaseInfo.setIsVirtual(Constants.FALSE.intValue());
                    houseBaseInfo.setCreateUserId(userId);
                    houseBaseInfo.setCreateUserName(userName);
                    houseBaseInfo.setUpdateUserId(userId);
                    houseBaseInfo.setUpdateUserName(userName);
                    houseBaseInfo.setPath(path);
                    ownerHouseBaseInfoMapper.insertSelective(houseBaseInfo);
                    houseId = houseBaseInfo.getHouseId();
                    // 新增房产编号
                    OwnerHouseHouseInfo newInfo = new OwnerHouseHouseInfo();
                    newInfo.setHouseId(houseBaseInfo.getHouseId());
                    newInfo.setRoomShortName(roomShortName);
                    // 数据字典获取相应value
                    newInfo.setRoomTypeId(roomTypeId);

                    // newInfo.setRoomNo(roomNo);
                    newInfo.setAssistChargingArea(CommonUtils.decimal2Long(assistChargingArea));
                    newInfo.setChargingArea(CommonUtils.decimal2Long(chargingArea));
                    newInfo.setBuildingArea(CommonUtils.decimal2Long(buildingArea));
                    newInfo.setUpdateUserId(userId);
                    newInfo.setUpdateUserName(userName);
                    newInfo.setCreateUserId(userId);
                    newInfo.setCreateUserName(userName);
                    ownerHouseHouseInfoMapper.insertSelective(newInfo);

                    OwnerHouseHouseExtendInfo extendInfo = new OwnerHouseHouseExtendInfo();
                    extendInfo.setHouseId(houseBaseInfo.getHouseId());
                    extendInfo.setBasementArea(CommonUtils.decimal2Long(basementArea));
                    extendInfo.setGardenArea(CommonUtils.decimal2Long(gardenArea));
                    extendInfo.setGiftArea(CommonUtils.decimal2Long(giftArea));
                    extendInfo.setPoolArea(CommonUtils.decimal2Long(poolArea));
                    extendInfo.setInsideArea(CommonUtils.decimal2Long(insideArea));
                    ownerHouseHouseExtendInfoMapper.insertSelective(extendInfo);
                    // 添加结果表
                    HouseListEntity houseListEntity = new HouseListEntity();
                    BeanUtils.copyProperties(houseBaseInfo, houseListEntity);
                    BeanUtils.copyProperties(extendInfo, houseListEntity);
                    // houseListEntity.setIsBlockUpName(Constants.TRUE.intValue() == houseListEntity.getIsBlockUp().intValue()?"是":"否");
                    // houseListEntity.setIsVirtualName(Constants.TRUE.intValue() == houseListEntity.getIsVirtual().intValue()?"是":"否");
                    houseListEntity.setIsCurrentRecord(Constants.TRUE);
                    // houseListEntity.setIsCurrentRecordName("当前");
                    houseListEntity.setProjectName(handlerHouseName(projectNameList));
                    houseListEntity.setGroupName(handlerHouseName(groupNameList));
                    houseListEntity.setBuildingName(handlerHouseName(buildingNameList));
                    houseListEntity.setUnitName(handlerHouseName(unitNameList));
                    houseListEntity.setStageName(
                            CommonUtils.getHouseStage(HouseStageEnum.KONG_ZHI.getValue(), HouseRentStageEnum.RENT_STAGE_NONE.getValue(), HouseDecorateStageEnum.DECORATE_STAGE_NONE.getValue(), 0));
                    houseListEntity.setSalesStageName(HouseStageEnum.KONG_ZHI.getTitle());
                    houseListEntity.setRentStage(HouseRentStageEnum.RENT_STAGE_NONE.getValue());
                    houseListEntity.setRentStageName(HouseRentStageEnum.RENT_STAGE_NONE.getTitle());
                    houseListEntity.setDecorateStage(HouseDecorateStageEnum.DECORATE_STAGE_NONE.getValue());
                    houseListEntity.setDecorateStageName(HouseDecorateStageEnum.DECORATE_STAGE_NONE.getTitle());
                    houseListEntity.setRoomTypeId(roomTypeId);
                    // houseListEntity.setRoomTypeName(roomType);
                    houseListEntity.setCreateTime(new Date());
                    houseListEntity.setCreateHouseUserId(userId);
                    houseResultMapper.insertSelective(houseListEntity);

                } else {
                    houseId = roomBase.getHouseId();
                    if (Constants.TRUE.equals(updateFlag)) {
                        // 修改房产编号
                        OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                        baseInfo.setHouseId(roomBase.getHouseId());
                        baseInfo.setHouseNo(roomNo);
                        baseInfo.setUpdateUserId(userId);
                        baseInfo.setUpdateUserName(userName);
                        ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);

                        OwnerHouseHouseInfo newInfo = new OwnerHouseHouseInfo();
                        newInfo.setHouseId(roomBase.getHouseId());
                        newInfo.setRoomShortName(roomShortName);
                        // 房产类型
                        newInfo.setRoomTypeId(roomTypeId);
                        // newInfo.setRoomNo(roomNo);
                        newInfo.setAssistChargingArea(CommonUtils.decimal2Long(assistChargingArea));
                        newInfo.setChargingArea(CommonUtils.decimal2Long(chargingArea));
                        newInfo.setBuildingArea(CommonUtils.decimal2Long(buildingArea));
                        newInfo.setUpdateUserId(userId);
                        newInfo.setUpdateUserName(userName);
                        newInfo.setCreateUserId(userId);
                        newInfo.setCreateUserName(userName);
                        ownerHouseHouseInfoMapper.updateByPrimaryKeySelective(newInfo);

                        OwnerHouseHouseExtendInfo extendInfo = new OwnerHouseHouseExtendInfo();
                        extendInfo.setHouseId(houseId);
                        extendInfo.setBasementArea(CommonUtils.decimal2Long(basementArea));
                        extendInfo.setGardenArea(CommonUtils.decimal2Long(gardenArea));
                        extendInfo.setGiftArea(CommonUtils.decimal2Long(giftArea));
                        extendInfo.setPoolArea(CommonUtils.decimal2Long(poolArea));
                        extendInfo.setInsideArea(CommonUtils.decimal2Long(insideArea));
                        ownerHouseHouseExtendInfoMapper.updateByPrimaryKeySelective(extendInfo);

                        HouseListEntity houseListEntity = new HouseListEntity();
                        BeanUtils.copyProperties(houseBaseInfo, houseListEntity);
                        houseListEntity.setHouseName(roomName);
                        houseListEntity.setHouseFullName(houseFullName);
                        houseListEntity.setHouseShortName(roomShortName);
                        houseListEntity.setHouseNo(roomNo);
                        houseListEntity.setChargingArea(CommonUtils.decimal2Long(chargingArea));
                        houseListEntity.setBuildingArea(CommonUtils.decimal2Long(buildingArea));
                        houseListEntity.setInsideArea(CommonUtils.decimal2Long(insideArea));
                        houseListEntity.setPoolArea(CommonUtils.decimal2Long(poolArea));
                        houseListEntity.setHouseId(houseId);
                        houseListEntity.setRoomTypeId(roomTypeId);
                        // houseListEntity.setRoomTypeName(roomType);
                        houseResultMapper.updateByHouseId(houseListEntity);
                    }
                }
                resultMap.put("precinctId", precinctId);
                resultMap.put("precinctName", projectNameList.get(0));
                resultMap.put("houseId", houseId);
                resultMap.put("houseName", roomName);
                resultMap.put("houseShortName", roomShortName);
                resultMap.put("houseNo", roomNo);
                resultMap.put("houseFullName", houseFullName);
            }
        } else if (carPortTypeList.contains(roomType)) {
            // 保存车位
            String carPortName = (String) excelValueMap.get("carPortName");
            String carPortShortName = (String) excelValueMap.get("carPortShortName");
            Date deliveryTime = null;
            if (!CommonUtils.isNullOrBlank((String) excelValueMap.get("deliveryTime"))) {
                deliveryTime = DateUtils.strToDate((String) excelValueMap.get("deliveryTime"));
            }
            // 数据字典获取相应value
            String carPortTypeId = (String) excelValueMap.get("carPortTypeId");
            String carPortNo = (String) excelValueMap.get("carPortNo");
            BigDecimal chargingArea = new BigDecimal((String) excelValueMap.get("chargingArea"));
            BigDecimal assistChargingArea = new BigDecimal((String) excelValueMap.get("assistChargingArea"));
            BigDecimal buildingArea = new BigDecimal((String) excelValueMap.get("buildingArea"));
            BigDecimal insideArea = new BigDecimal((String) excelValueMap.get("insideArea"));
            BigDecimal poolArea = new BigDecimal((String) excelValueMap.get("poolArea"));

            if (!CommonUtils.isNullOrBlank(carPortName)) {
                // 获取归属的项目ID和楼栋ID
                List<Long> pathIdList = StringUtils.handlerPath2List(path);
                List<String> projectNameList = new ArrayList<>();
                List<String> groupNameList = new ArrayList<>();
                List<String> buildingNameList = new ArrayList<>();
                List<String> unitNameList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(pathIdList)) {
                    List<OwnerHouseBaseInfo> houseBaseInfos = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(pathIdList);
                    Map<String, Object> houseIdMap = getHouseInfoByPath(pathIdList, houseBaseInfos, null);

                    List<Long> projectIdList = (List<Long>) houseIdMap.get(KEY_PRECINCT_ID_LIST);
                    List<Long> buildingIdList = (List<Long>) houseIdMap.get(KEY_BUILDING_ID_LIST);
                    projectNameList = (List<String>) houseIdMap.get(KEY_PRECINCT_NAME_LIST);
                    groupNameList = (List<String>) houseIdMap.get(KEY_GROUP_NAME_LIST);
                    buildingNameList = (List<String>) houseIdMap.get(KEY_BUILDING_NAME_LIST);
                    unitNameList = (List<String>) houseIdMap.get(KEY_UNIT_NAME_LIST);
                    if (!CollectionUtils.isEmpty(projectIdList)) {
                        precinctId = projectIdList.get(0);
                    }
                    if (!CollectionUtils.isEmpty(buildingIdList)) {
                        buildingId = buildingIdList.get(0);
                    }
                }
                // 查询导入房产是否已存在
                nameMap.put("houseName", carPortName);
                nameMap.put("houseType", Constants.HOUSE_TYPE_HOUSE);
                nameMap.put("parentId", parentId);
                OwnerHouseBaseInfo carPortBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
                // 获取sort
                Map<String, Object> params = new HashMap<>();
                params.put("organizationId", organizationId);
                if (carPortBase != null) {
                    params.put("parentId", carPortBase.getParentId());
                } else {
                    params.put("parentId", parentId);
                }
                List<OwnerHouseBaseInfo> allHouseBaseInfoList = ownerHouseBaseInfoMapper.listHouseBaseInfoByParam(params);
                int sort = 0;
                if (!CollectionUtils.isEmpty(allHouseBaseInfoList)) {
                    sort = allHouseBaseInfoList.get(allHouseBaseInfoList.size() - 1).getSort() + 1;
                }
                OwnerHouseBaseInfo houseBaseInfo = new OwnerHouseBaseInfo();
                if (CommonUtils.isNullOrBlank(houseFullName)) {
                    houseFullName = carPortName;
                } else {
                    houseFullName = houseFullName + "-" + carPortName;
                }
                if (CommonUtils.isNullOrBlank(carPortNo)) {
                    carPortNo = createHouseNoByHouseName(carPortName);
                }
                if (carPortBase == null) {
                    houseBaseInfo.setOrganizationId(organizationId);
                    houseBaseInfo.setEnterpriseId(enterpriseId);
                    houseBaseInfo.setHouseName(carPortName);
                    houseBaseInfo.setHouseNo(carPortNo);
                    houseBaseInfo.setHouseFullName(houseFullName);
                    houseBaseInfo.setBuildingId(buildingId);
                    houseBaseInfo.setPrecinctId(precinctId);
                    houseBaseInfo.setHouseType(HouseTypeEnum.CARPORT.getValue());
                    houseBaseInfo.setStage(HouseStageEnum.KONG_ZHI.getValue());
                    houseBaseInfo.setLevel(level);
                    houseBaseInfo.setSort(sort);
                    houseBaseInfo.setParentId(parentId);
                    houseBaseInfo.setIsBlockUp(Constants.FALSE.intValue());
                    houseBaseInfo.setIsVirtual(Constants.FALSE.intValue());
                    houseBaseInfo.setCreateUserId(userId);
                    houseBaseInfo.setCreateUserName(userName);
                    houseBaseInfo.setUpdateUserId(userId);
                    houseBaseInfo.setUpdateUserName(userName);
                    houseBaseInfo.setPath(path);
                    ownerHouseBaseInfoMapper.insertSelective(houseBaseInfo);
                    houseId = houseBaseInfo.getHouseId();

                    // 新增车位编号
                    OwnerHouseCarportInfo newInfo = new OwnerHouseCarportInfo();
                    newInfo.setHouseId(houseBaseInfo.getHouseId());
                    newInfo.setCarportShortName(carPortShortName);
                    // 房产类型
                    newInfo.setCarportTypeId(carPortTypeId);

                    // newInfo.setCarportNo(carPortNo);
                    newInfo.setAssistChargingArea(CommonUtils.decimal2Long(assistChargingArea));
                    newInfo.setChargingArea(CommonUtils.decimal2Long(chargingArea));
                    newInfo.setBuildingArea(CommonUtils.decimal2Long(buildingArea));
                    newInfo.setUpdateUserId(userId);
                    newInfo.setUpdateUserName(userName);
                    newInfo.setCreateUserId(userId);
                    newInfo.setCreateUserName(userName);
                    ownerHouseCarportInfoMapper.insertSelective(newInfo);

                    OwnerHouseCarportExtendInfo extendInfo = new OwnerHouseCarportExtendInfo();
                    extendInfo.setHouseId(houseBaseInfo.getHouseId());
                    extendInfo.setPoolArea(CommonUtils.decimal2Long(poolArea));
                    extendInfo.setInsideArea(CommonUtils.decimal2Long(insideArea));
                    extendInfo.setDeliveryTime(deliveryTime);
                    ownerHouseCarportExtendInfoMapper.insertSelective(extendInfo);
                    // 添加mongodb
                    HouseListEntity houseListEntity = new HouseListEntity();
                    BeanUtils.copyProperties(houseBaseInfo, houseListEntity);
                    BeanUtils.copyProperties(extendInfo, houseListEntity);
                    // houseListEntity.setIsBlockUpName(Constants.TRUE.intValue() == houseListEntity.getIsBlockUp().intValue()?"是":"否");
                    // houseListEntity.setIsVirtualName(Constants.TRUE.intValue() == houseListEntity.getIsVirtual().intValue()?"是":"否");
                    houseListEntity.setIsCurrentRecord(Constants.TRUE);
                    // houseListEntity.setIsCurrentRecordName("当前");
                    houseListEntity.setProjectName(handlerHouseName(projectNameList));
                    houseListEntity.setGroupName(handlerHouseName(groupNameList));
                    houseListEntity.setBuildingName(handlerHouseName(buildingNameList));
                    houseListEntity.setUnitName(handlerHouseName(unitNameList));
                    houseListEntity.setStageName(
                            CommonUtils.getHouseStage(HouseStageEnum.KONG_ZHI.getValue(), HouseRentStageEnum.RENT_STAGE_NONE.getValue(), HouseDecorateStageEnum.DECORATE_STAGE_NONE.getValue(), 0));
                    houseListEntity.setSalesStageName(HouseStageEnum.KONG_ZHI.getTitle());
                    houseListEntity.setRentStage(HouseRentStageEnum.RENT_STAGE_NONE.getValue());
                    houseListEntity.setRentStageName(HouseRentStageEnum.RENT_STAGE_NONE.getTitle());
                    houseListEntity.setDecorateStage(HouseDecorateStageEnum.DECORATE_STAGE_NONE.getValue());
                    houseListEntity.setDecorateStageName(HouseDecorateStageEnum.DECORATE_STAGE_NONE.getTitle());
                    houseListEntity.setRoomTypeId(carPortTypeId);
                    // houseListEntity.setRoomTypeName(roomType);
                    houseListEntity.setCreateTime(new Date());
                    houseListEntity.setCreateHouseUserId(userId);
                    houseResultMapper.insertSelective(houseListEntity);
                } else {
                    houseId = carPortBase.getHouseId();
                    if (Constants.TRUE.equals(updateFlag)) {
                        // 修改车位编号
                        OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                        baseInfo.setHouseId(carPortBase.getHouseId());
                        baseInfo.setHouseNo(carPortNo);
                        baseInfo.setUpdateUserId(userId);
                        baseInfo.setUpdateUserName(userName);
                        ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);

                        OwnerHouseCarportInfo newInfo = new OwnerHouseCarportInfo();
                        newInfo.setHouseId(carPortBase.getHouseId());
                        newInfo.setCarportShortName(carPortShortName);
                        // TODO 数据字典获取相应value
                        newInfo.setCarportTypeId(carPortTypeId);

                        // newInfo.setCarportNo(carPortNo);
                        newInfo.setAssistChargingArea(CommonUtils.decimal2Long(assistChargingArea));
                        newInfo.setChargingArea(CommonUtils.decimal2Long(chargingArea));
                        newInfo.setBuildingArea(CommonUtils.decimal2Long(buildingArea));
                        newInfo.setUpdateUserId(userId);
                        newInfo.setUpdateUserName(userName);
                        newInfo.setCreateUserId(userId);
                        newInfo.setCreateUserName(userName);
                        ownerHouseCarportInfoMapper.updateByPrimaryKeySelective(newInfo);

                        OwnerHouseCarportExtendInfo extendInfo = new OwnerHouseCarportExtendInfo();
                        extendInfo.setHouseId(houseBaseInfo.getHouseId());
                        extendInfo.setPoolArea(CommonUtils.decimal2Long(poolArea));
                        extendInfo.setInsideArea(CommonUtils.decimal2Long(insideArea));
                        ownerHouseCarportExtendInfoMapper.updateByPrimaryKeySelective(extendInfo);

                        HouseListEntity houseListEntity = new HouseListEntity();
                        houseListEntity.setHouseName(carPortName);
                        houseListEntity.setHouseFullName(houseFullName);
                        houseListEntity.setHouseShortName(carPortShortName);
                        houseListEntity.setHouseNo(carPortNo);
                        houseListEntity.setChargingArea(CommonUtils.decimal2Long(chargingArea));
                        houseListEntity.setBuildingArea(CommonUtils.decimal2Long(buildingArea));
                        houseListEntity.setInsideArea(CommonUtils.decimal2Long(insideArea));
                        houseListEntity.setPoolArea(CommonUtils.decimal2Long(poolArea));
                        houseListEntity.setHouseId(houseId);
                        houseListEntity.setRoomTypeId(carPortTypeId);
                        // houseListEntity.setRoomTypeName(roomType);
                        houseResultMapper.updateByHouseId(houseListEntity);
                    }

                }
                resultMap.put("precinctId", precinctId);
                resultMap.put("precinctName", projectNameList.get(0));
                resultMap.put("houseId", houseId);
                resultMap.put("houseName", carPortName);
                resultMap.put("houseShortName", carPortShortName);
                resultMap.put("houseNo", carPortNo);
                resultMap.put("houseFullName", houseFullName);
            }
        }

        resultMap.put("resultType", Constants.RESULT_SUCCESS);
        resultMap.put("houseId", houseId);
        return resultMap;
    }

    private String createHouseNoByHouseName(String houseName) {
        String houseNo = "";
        if (!CommonUtils.isNullOrBlank(houseName)) {
            char[] cl_chars = houseName.trim().toCharArray();
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
            defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
            try {
                for (int i = 0; i < cl_chars.length; i++) {
                    if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并截取第一个字母
                        houseNo += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0].substring(0, 1);
                    } else {// 如果字符不是中文,则不转换
                        houseNo += cl_chars[i];
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                BizException.fail(ResultCodeEnum.PARAMS_ERROR, "字符不能转成汉语拼音");
            }
        }
        return houseNo;
    }

    @Override
    @WriteDataSource
    public Map<String, Object> importCustomer(List<Object> list, Long enterpriseId, Long organizationId, Long userId, String userName, Byte updateFlag, Long houseId,
                                              Map<String, Object> excelValueMap) {
        Map<String, Object> resultMap = new HashMap<>();
        // 保存客户
        String ownerName = (String) excelValueMap.get("ownerName");
        String mobile = (String) excelValueMap.get("mobile");
        String certificateType = (String) excelValueMap.get("certificateType");
        // String certificateTypeName = (String) excelValueMap.get("certificateTypeName");
        String certificate = (String) excelValueMap.get("certificate");
        String nativePlace = (String) excelValueMap.get("nativePlace");
        String maritalStatus = (String) excelValueMap.get("maritalStatus");
        // String maritalStatusName = (String) excelValueMap.get("maritalStatusName");
        String education = (String) excelValueMap.get("education");
        // String educationName = (String) excelValueMap.get("educationName");
        String region = (String) excelValueMap.get("region");
        // String regionName = (String) excelValueMap.get("regionName");
        String nation = (String) excelValueMap.get("nation");
        // String nationName = (String) excelValueMap.get("nationName");
        String linkman = (String) excelValueMap.get("linkman");
        String linkmanPhone = (String) excelValueMap.get("linkmanPhone");
        String phone = (String) excelValueMap.get("phone");
        String ownerType = (String) excelValueMap.get("ownerType");
        // String ownerTypeName = (String) excelValueMap.get("ownerTypeName");
        String companyPhone = (String) excelValueMap.get("companyPhone");
        String fax = (String) excelValueMap.get("fax");
        String emergencyContact = (String) excelValueMap.get("emergencyContact");
        String emergencyContactPhone = (String) excelValueMap.get("emergencyContactPhone");
        Long ownerId = 0L;
        if (!CommonUtils.isNullOrBlank(ownerName)) {
            Map<String, Object> customerMap = new HashMap<>();
            customerMap.put("ownerName", ownerName);
            customerMap.put("organizationId", organizationId);
            customerMap.put("certificate", certificate);
            customerMap.put("mobile", mobile);
            CustomerVo customerVo = ownerCustomerBaseInfoMapper.loadCustomerByName(customerMap);

            // 获取房间信息
            OwnerHouseBaseInfo houseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
            if (customerVo == null) {
                // 新增业主
                OwnerCustomerBaseInfo baseInfo = new OwnerCustomerBaseInfo();
                baseInfo.setOwnerName(ownerName);
                // TODO 数据字典返回value
                baseInfo.setCertificateType(certificateType);
                baseInfo.setCertificate(certificate);
                baseInfo.setEnterpriseId(enterpriseId);
                baseInfo.setOrganizationId(organizationId);
                baseInfo.setOwnerType(ownerType);
                baseInfo.setIsDeleted(Constants.DELETE_NO);
                baseInfo.setCreateUserId(userId);
                baseInfo.setCreateUserName(userName);
                baseInfo.setUpdateUserId(userId);
                baseInfo.setUpdateUserName(userName);
                baseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_NONE);
                baseInfo.setPrecinctId(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH);
                // 新增客户基础信息
                int index = ownerCustomerBaseInfoMapper.insert(baseInfo);
                ownerId = baseInfo.getOwnerId();
                if (index > 0) {
                    // 新增客户扩展信息
                    OwnerCustomerInfo customerInfo = new OwnerCustomerInfo();
                    customerInfo.setOwnerId(baseInfo.getOwnerId());
                    customerInfo.setMobile(mobile);
                    customerInfo.setPhone(phone);
                    customerInfo.setCompanyPhone(companyPhone);
                    // TODO 数据字典返回value
                    customerInfo.setEducation(education);
                    customerInfo.setNation(nation);
                    customerInfo.setRegion(region);
                    customerInfo.setMaritalStatus(maritalStatus);
                    customerInfo.setLinkman(linkman);
                    customerInfo.setLinkmanPhone(linkmanPhone);
                    customerInfo.setFax(fax);
                    customerInfo.setNativePlace(nativePlace);
                    customerInfo.setEmergencyContact(emergencyContact);
                    customerInfo.setEmergencyContactPhone(emergencyContactPhone);
                    customerInfo.setCreateUserId(userId);
                    customerInfo.setCreateUserName(userName);
                    customerInfo.setUpdateUserId(userId);
                    customerInfo.setUpdateUserName(userName);
                    customerInfo.setIsDeleted(Constants.DELETE_NO);
                    ownerCustomerInfoMapper.insert(customerInfo);
                    // 同步客户结果表
                    OwnerCustomerResult ownerCustomerResult = new OwnerCustomerResult();
                    BeanUtils.copyProperties(baseInfo, ownerCustomerResult);
                    BeanUtils.copyProperties(customerInfo, ownerCustomerResult);
                    ownerCustomerResult.setUpdateTime(new Date());
                    // ownerCustomerResult.setOwnerTypeName(ownerTypeName);
                    // ownerCustomerResult.setCertificateTypeName(certificateTypeName);
                    // ownerCustomerResult.setEducationName(educationName);
                    // ownerCustomerResult.setRegionName(regionName);
                    // ownerCustomerResult.setNationName(nationName);
                    // ownerCustomerResult.setMaritalStatusName(maritalStatusName);
                    customerResultMapper.insertSelective(ownerCustomerResult);
                }
            } else {
                ownerId = customerVo.getOwnerId();
                if (Constants.TRUE.equals(updateFlag)) {
                    // 修改业主
                    OwnerCustomerBaseInfo baseInfo = new OwnerCustomerBaseInfo();
                    baseInfo.setOwnerId(customerVo.getOwnerId());
                    baseInfo.setIsDeleted(Constants.DELETE_NO);
                    baseInfo.setUpdateUserId(userId);
                    baseInfo.setUpdateUserName(userName);
                    baseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_NONE);
                    if (!customerVo.getPrecinctId().contains(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH)) {
                        baseInfo.setPrecinctId(customerVo.getPrecinctId() + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH);
                    }
                    // 新增客户基础信息
                    int index = ownerCustomerBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
                    if (index > 0) {
                        // 新增客户扩展信息
                        OwnerCustomerInfo customerInfo = new OwnerCustomerInfo();
                        customerInfo.setOwnerId(baseInfo.getOwnerId());
                        customerInfo.setMobile(mobile);
                        customerInfo.setPhone(phone);
                        customerInfo.setCompanyPhone(companyPhone);
                        // TODO 数据字典返回value
                        customerInfo.setEducation(education);
                        customerInfo.setNation(nation);
                        customerInfo.setRegion(region);
                        customerInfo.setMaritalStatus(maritalStatus);
                        customerInfo.setLinkman(linkman);
                        customerInfo.setLinkmanPhone(linkmanPhone);
                        customerInfo.setFax(fax);
                        customerInfo.setNativePlace(nativePlace);
                        customerInfo.setEmergencyContact(emergencyContact);
                        customerInfo.setEmergencyContactPhone(emergencyContactPhone);
                        customerInfo.setUpdateUserId(userId);
                        customerInfo.setUpdateUserName(userName);
                        customerInfo.setIsDeleted(Constants.DELETE_NO);
                        ownerCustomerInfoMapper.updateByPrimaryKeySelective(customerInfo);
                        // 同步客户结果表
                        OwnerCustomerResult ownerCustomerResult = new OwnerCustomerResult();
                        BeanUtils.copyProperties(customerVo, ownerCustomerResult);
                        if (!customerVo.getPrecinctId().contains(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH)) {
                            ownerCustomerResult.setPrecinctId(customerVo.getPrecinctId() + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH);
                        }
                        // ownerCustomerResult.setOwnerTypeName(ownerTypeName);
                        // ownerCustomerResult.setCertificateTypeName(certificateTypeName);
                        // ownerCustomerResult.setEducationName(educationName);
                        // ownerCustomerResult.setRegionName(regionName);
                        // ownerCustomerResult.setNationName(nationName);
                        // ownerCustomerResult.setMaritalStatusName(maritalStatusName);
                        ownerCustomerResult.setUpdateTime(new Date());
                        customerResultMapper.updateByPrimaryKeySelective(ownerCustomerResult);
                    }
                }
            }
            if (!CommonUtils.isObjectEmpty(ownerId)) {
                // 保存银行卡
                String bankName = (String) excelValueMap.get("bankId");
                String bankAddress = (String) excelValueMap.get("bankAddress");
                String accountName = (String) excelValueMap.get("accountName");
                String account = (String) excelValueMap.get("account");
                String isEnableStr = (String) excelValueMap.get("isEnable");
                Byte isEnable = 0;
                if ("已启用".equals(isEnableStr)) {
                    isEnable = Constants.TRUE;
                } else {
                    isEnable = Constants.FALSE;
                }
                String protocolNumber = (String) excelValueMap.get("protocolNumber");
                String collectionNumber = (String) excelValueMap.get("collectionNumber");
                String bankRemark = (String) excelValueMap.get("bankRemark");
                if (!CommonUtils.isNullOrBlank(account)) {
                    OwnerCustomerBankAccount bankAccount = new OwnerCustomerBankAccount();
                    bankAccount.setOwnerId(ownerId);
                    bankAccount.setBankName(bankName);
                    bankAccount.setBankAddress(bankAddress);
                    bankAccount.setAccountName(accountName);
                    bankAccount.setAccount(account);
                    bankAccount.setIsEnable(isEnable);
                    bankAccount.setProtocolNumber(protocolNumber);
                    bankAccount.setCollectionNumber(collectionNumber);
                    bankAccount.setRemark(bankRemark);
                    bankAccount.setUpdateUserId(userId);
                    bankAccount.setUpdateUserName(userName);
                    bankAccount.setIsDeleted(Constants.FALSE);
                    bankAccount.setCreateUserId(userId);
                    bankAccount.setCreateUserName(userName);
                    ownerCustomerBankAccountMapper.insertSelective(bankAccount);
                }
            }
        }
        resultMap.put("resultType", Constants.RESULT_SUCCESS);
        resultMap.put("ownerId", ownerId);
        return resultMap;
    }

    @Override
    @WriteDataSource
    public Map<String, Object> importHouseStageOperate(List<Object> list, Long enterpriseId, Long organizationId, Long userId, String userName, Byte updateFlag, Long houseId, Long ownerId,
                                                       Map<String, Object> excelValueMap) {
        Map<String, Object> resultMap = new HashMap<>();
        // 售楼
        // 查询房产是否存在售楼记录
        // 查询房产业主关系是否一致
        // 存在 更新最后一条售楼记录
        // 保存房态变更操作--售楼、收房、入住
        String ownerName = (String) excelValueMap.get("ownerName");
        String mobile = (String) excelValueMap.get("mobile");
        String certificate = (String) excelValueMap.get("certificate");
        Date salesDate = null;
        if (!CommonUtils.isNullOrBlank((String) excelValueMap.get("salesDate"))) {
            salesDate = DateUtils.strToDate((String) excelValueMap.get("salesDate"));
        }
        Date takeDate = null;
        if (!CommonUtils.isNullOrBlank((String) excelValueMap.get("takeDate"))) {
            takeDate = DateUtils.strToDate((String) excelValueMap.get("takeDate"));
        }
        Date decorateStartDate = null;
        if (!CommonUtils.isNullOrBlank((String) excelValueMap.get("decorateStartDate"))) {
            decorateStartDate = DateUtils.strToDate((String) excelValueMap.get("decorateStartDate"));
        }
        Date decorateEndDate = null;
        if (!CommonUtils.isNullOrBlank((String) excelValueMap.get("decorateEndDate"))) {
            decorateEndDate = DateUtils.strToDate((String) excelValueMap.get("decorateEndDate"));
        }
        Date rentStartDate = null;
        if (!CommonUtils.isNullOrBlank((String) excelValueMap.get("rentStartDate"))) {
            rentStartDate = DateUtils.strToDate((String) excelValueMap.get("rentStartDate"));
        }
        Date rentEndDate = null;
        if (!CommonUtils.isNullOrBlank((String) excelValueMap.get("rentEndDate"))) {
            rentEndDate = DateUtils.strToDate((String) excelValueMap.get("rentEndDate"));
        }
        Date checkInDate = null;
        if (!CommonUtils.isNullOrBlank((String) excelValueMap.get("checkInDate"))) {
            checkInDate = DateUtils.strToDate((String) excelValueMap.get("checkInDate"));
        }
        if (checkInDate != null) {
            if (takeDate != null) {
                if (salesDate == null) {
                    salesDate = takeDate;
                }
            } else {
                takeDate = checkInDate;
            }
        }
        if (!CommonUtils.isNullOrBlank(ownerName) && checkInDate == null && takeDate == null && salesDate == null) {
            salesDate = new Date();
        }
        Long detailId = 0L;
        boolean ownerMismatch = false;
        Map<String, Object> checkMap = new HashMap<>();
        checkMap.put("houseId", houseId);
        checkMap.put("houseOperateType", HouseOperateTypeEnum.SHOU_LOU.getValue());
        List<OwnerHouseRelationship> relationships = ownerHouseRelationshipMapper.listAllRelationHistory(checkMap);
        if (CollectionUtils.isEmpty(relationships)) {
            if (salesDate != null) {
                // 不存在 新增售楼记录
                OwnerHouseBaseInfo houseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                // 新业主房产操作
                OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
                ownerHouseStageDetail.setHouseId(houseId);
                ownerHouseStageDetail.setHandleTime(salesDate);
                // 保存操作前房态
                ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
                ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
                ownerHouseStageDetail.setCreateUserId(userId);
                ownerHouseStageDetail.setCreateUserName(userName);
                ownerHouseStageDetail.setUpdateUserId(userId);
                ownerHouseStageDetail.setUpdateUserName(userName);
                // 新业主房产关系
                OwnerHouseRelationship houseRelationship = new OwnerHouseRelationship();
                houseRelationship.setHouseId(houseId);
                houseRelationship.setPrecinctId(houseBaseInfo.getPrecinctId());
                houseRelationship.setOwnerId(ownerId);
                houseRelationship.setIsCurrentRecord(Constants.TRUE);
                houseRelationship.setOwnerCategory(OwnerConstants.OWNER_CATEGORY_OWNER);
                houseRelationship.setIsDeleted(Constants.FALSE);
                houseRelationship.setCreateUserId(userId);
                houseRelationship.setCreateUserName(userName);
                houseRelationship.setUpdateUserId(userId);
                houseRelationship.setUpdateUserName(userName);
                String stageName = HouseOperateTypeEnum.SHOU_LOU.getTitle();
                ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.SHOU_LOU.getValue());
                // 保存新业主相关信息
                long index = ownerHouseStageDetailMapper.insertSelective(ownerHouseStageDetail);
                index = ownerHouseStageDetail.getDetailId();
                if (index > 0) {
                    // 保存新业主的客户房产关系
                    houseRelationship.setDetailId(ownerHouseStageDetail.getDetailId());
                    houseRelationship.setOwnerProperty(Constants.OWNER_PROPERTY_OWNER);
                    Map<String, Object> map = new HashMap<>();
                    map.put("precinctId", houseBaseInfo.getPrecinctId());
                    // 保存主房产
                    List<Long> ownerIdList = new ArrayList<>();
                    ownerIdList.add(houseRelationship.getOwnerId());
                    map.put("ownerIdList", ownerIdList);
                    mainHouseMapper.deleteMainHouse(map);
                    OwnerCustomerMainHouse mainHouse = new OwnerCustomerMainHouse();
                    BeanUtils.copyProperties(houseRelationship, mainHouse);
                    mainHouseMapper.insertSelective(mainHouse);

                    ownerHouseRelationshipMapper.insertSelective(houseRelationship);
                    // TODO 保存产权人信息
                    // 更新房态
                    OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                    baseInfo.setHouseId(houseId);
                    baseInfo.setUpdateUserId(userId);
                    baseInfo.setUpdateUserName(userName);
                    baseInfo.setStage(HouseStageEnum.WEI_LING.getValue());
                    ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
                    // 同步结果表
                    SearchVo searchVo = new SearchVo();
                    List<FilterEntity> filterList = new ArrayList<>();
                    FilterEntity currentRecordEntity = new FilterEntity();
                    currentRecordEntity.setFieldName("is_current_record");
                    currentRecordEntity.setFieldValue(Constants.TRUE.toString());
                    currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
                    filterList.add(currentRecordEntity);
                    FilterEntity houseIdEntity = new FilterEntity();
                    houseIdEntity.setFieldName("result.house_id");
                    houseIdEntity.setFieldValue(houseId.toString());
                    houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
                    filterList.add(houseIdEntity);
                    searchVo.setFilterList(filterList);
                    List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
                    // 更新上一条为历史数据
                    HouseListEntity houseEntity = new HouseListEntity();
                    houseEntity.setIsCurrentRecord(Constants.FALSE);
                    // houseEntity.setIsCurrentRecordName("历史");
                    houseEntity.setHouseId(houseId);
                    houseResultMapper.updateByHouseId(houseEntity);
                    // mongoTemplate.updateFirst(query, update, HouseListEntity.class, OwnerConstants.COLLECTIONS_HOUSE_LIST);

                    if (!CollectionUtils.isEmpty(houseListEntities)) {
                        baseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                        // 新增当前售楼信息
                        HouseListEntity houseListEntity = houseListEntities.get(0);
                        houseListEntity.setHouseStage(HouseStageEnum.WEI_LING.getValue());
                        houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                        houseListEntity.setIsCurrentRecord(Constants.TRUE);
                        houseListEntity.setOwnerId(ownerId);
                        houseListEntity.setOwnerName(ownerName);
                        houseListEntity.setOwnerPhone(mobile);
                        houseListEntity.setCertificate(certificate);
                        houseListEntity.setSalesStageName(stageName);
                        houseListEntity.setTakeStageName("未收房");
                        houseListEntity.setCheckInStageName("未入住");
                        houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                        houseListEntity.setHouseOperateType(HouseOperateTypeEnum.SHOU_LOU.getValue());
                        houseListEntity.setRentStage(HouseRentStageEnum.RENT_STAGE_NONE.getValue());
                        houseListEntity.setRentStageName(HouseRentStageEnum.RENT_STAGE_NONE.getTitle());
                        houseListEntity.setDecorateStage(HouseDecorateStageEnum.DECORATE_STAGE_NONE.getValue());
                        houseListEntity.setDecorateStageName(HouseDecorateStageEnum.DECORATE_STAGE_NONE.getTitle());
                        houseListEntity.setHandleTime(salesDate);
                        houseResultMapper.insertSelective(houseListEntity);
                    }
                }
                detailId = index;
                ownerMismatch = true;
            }
        } else {
            OwnerHouseRelationship relationship = null;
            List<Long> ownerIdList = ownerHouseRelationshipMapper.listOwnerIdByHouseId(houseId);
            boolean update = false;
            for (OwnerHouseRelationship ownerHouseRelationship : relationships) {
                if (ownerId.equals(ownerHouseRelationship.getOwnerId())) {
                    // 房间+业主（含产权人）不变，且为当前业主时更新最后一条操作记录
                    if (ownerIdList.contains(ownerId)) {
                        ownerMismatch = true;
                        detailId = ownerHouseRelationship.getDetailId();
                    }
                    update = true;
                    relationship = ownerHouseRelationship;
                }
            }
            if (update) {
                if (Constants.TRUE.equals(updateFlag)) {
                    OwnerHouseStageDetail detail = new OwnerHouseStageDetail();
                    detail.setDetailId(relationship.getDetailId());
                    detail.setHandleTime(salesDate);
                    detail.setUpdateUserId(userId);
                    detail.setUpdateUserName(userName);
                    ownerHouseStageDetailMapper.updateByPrimaryKeySelective(detail);
                }
            }
        }
        if (ownerMismatch) {
            // 收房
            checkMap.put("houseId", houseId);
            checkMap.put("houseOperateType", HouseOperateTypeEnum.SHOU_FANG.getValue());
            relationships = ownerHouseRelationshipMapper.listAllRelationHistory(checkMap);
            if (CollectionUtils.isEmpty(relationships)) {
                if (takeDate != null) {
                    // 不存在 新增收房记录
                    long previousDetailId = detailId;
                    OwnerHouseBaseInfo houseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                    // 业主房产操作---收房
                    OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
                    ownerHouseStageDetail.setHouseId(houseId);
                    ownerHouseStageDetail.setHandleTime(takeDate);
                    ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.SHOU_FANG.getValue());
                    ownerHouseStageDetail.setPreviousDetailId(previousDetailId);
                    ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
                    ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
                    ownerHouseStageDetail.setCreateUserId(userId);
                    ownerHouseStageDetail.setCreateUserName(userName);
                    ownerHouseStageDetail.setUpdateUserId(userId);
                    ownerHouseStageDetail.setUpdateUserName(userName);

                    int index = ownerHouseStageDetailMapper.insertSelective(ownerHouseStageDetail);
                    detailId = ownerHouseStageDetail.getDetailId();
                    if (index > 0) {
                        // 修改当前记录为历史记录
                        Map<String, Object> editRelationMap = new HashMap<>();
                        editRelationMap.put("userId", userId);
                        editRelationMap.put("preDetailId", previousDetailId);
                        editRelationMap.put("houseId", houseId);
                        ownerHouseRelationshipMapper.editCurrentRecordFalse(editRelationMap);
                        // 新增收房记录为当前记录
                        editRelationMap.put("detailId", ownerHouseStageDetail.getDetailId());
                        ownerHouseRelationshipMapper.insertBatchForCurrent(editRelationMap);
                        // 更新房态
                        OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                        baseInfo.setHouseId(houseId);
                        baseInfo.setUpdateUserId(userId);
                        baseInfo.setUpdateUserName(userName);
                        baseInfo.setStage(HouseStageEnum.KONG_GUAN.getValue());
                        ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
                        // 同步结果表
                        SearchVo searchVo = new SearchVo();
                        List<FilterEntity> filterList = new ArrayList<>();
                        FilterEntity currentRecordEntity = new FilterEntity();
                        currentRecordEntity.setFieldName("is_current_record");
                        currentRecordEntity.setFieldValue(Constants.TRUE.toString());
                        currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
                        filterList.add(currentRecordEntity);
                        FilterEntity houseIdEntity = new FilterEntity();
                        houseIdEntity.setFieldName("result.house_id");
                        houseIdEntity.setFieldValue(houseId.toString());
                        houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
                        filterList.add(houseIdEntity);
                        searchVo.setFilterList(filterList);
                        List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
                        // 更新上一条为历史数据
                        HouseListEntity houseEntity = new HouseListEntity();
                        houseEntity.setIsCurrentRecord(Constants.FALSE);
                        // houseEntity.setIsCurrentRecordName("历史");
                        houseEntity.setHouseId(houseId);
                        houseResultMapper.updateByHouseId(houseEntity);

                        if (!CollectionUtils.isEmpty(houseListEntities)) {
                            baseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                            // 新增当前收房信息
                            HouseListEntity houseListEntity = houseListEntities.get(0);
                            houseListEntity.setHouseStage(HouseStageEnum.KONG_GUAN.getValue());
                            houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                            houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                            houseListEntity.setIsCurrentRecord(Constants.TRUE);
                            houseListEntity.setTakeStageName("已收房");
                            houseListEntity.setHouseOperateType(HouseOperateTypeEnum.SHOU_FANG.getValue());
                            houseListEntity.setHandleTime(takeDate);
                            houseResultMapper.insertSelective(houseListEntity);
                        }
                    }
                }
            } else {
                OwnerHouseRelationship relationship = relationships.get(relationships.size() - 1);
                List<Long> ownerIdList = ownerHouseRelationshipMapper.listOwnerIdByHouseId(houseId);
                for (OwnerHouseRelationship ownerHouseRelationship : relationships) {
                    if (ownerId.equals(ownerHouseRelationship.getOwnerId())) {
                        // 房间+业主（含产权人）不变，且为当前业主时更新最后一条操作记录
                        if (ownerIdList.contains(ownerId)) {
                            detailId = ownerHouseRelationship.getDetailId();
                        }
                    }
                }
                if (Constants.TRUE.equals(updateFlag)) {
                    OwnerHouseStageDetail detail = new OwnerHouseStageDetail();
                    detail.setDetailId(relationship.getDetailId());
                    detail.setHandleTime(takeDate);
                    detail.setUpdateUserId(userId);
                    detail.setUpdateUserName(userName);
                    ownerHouseStageDetailMapper.updateByPrimaryKeySelective(detail);
                }
            }
            // 入住
            checkMap.put("houseId", houseId);
            checkMap.put("houseOperateType", HouseOperateTypeEnum.RU_ZHU.getValue());
            relationships = ownerHouseRelationshipMapper.listAllRelationHistory(checkMap);
            if (CollectionUtils.isEmpty(relationships)) {
                if (checkInDate != null) {
                    // 不存在 新增收房记录
                    long previousDetailId = detailId;
                    // 业主房产操作---入住
                    OwnerHouseBaseInfo houseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                    OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
                    ownerHouseStageDetail.setHouseId(houseId);
                    ownerHouseStageDetail.setHandleTime(checkInDate);
                    ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.RU_ZHU.getValue());
                    ownerHouseStageDetail.setPreviousDetailId(previousDetailId);
                    ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
                    ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
                    ownerHouseStageDetail.setCreateUserId(userId);
                    ownerHouseStageDetail.setCreateUserName(userName);
                    ownerHouseStageDetail.setUpdateUserId(userId);
                    ownerHouseStageDetail.setUpdateUserName(userName);

                    // 保存新业主相关信息
                    int index = ownerHouseStageDetailMapper.insertSelective(ownerHouseStageDetail);
                    detailId = ownerHouseStageDetail.getDetailId();
                    if (index > 0) {
                        // 修改当前记录为历史记录
                        Map<String, Object> editRelationMap = new HashMap<>();
                        editRelationMap.put("userId", userId);
                        editRelationMap.put("preDetailId", previousDetailId);
                        editRelationMap.put("houseId", houseId);
                        ownerHouseRelationshipMapper.editCurrentRecordFalse(editRelationMap);
                        // 新增入住记录为当前记录
                        editRelationMap.put("detailId", ownerHouseStageDetail.getDetailId());
                        ownerHouseRelationshipMapper.insertBatchForCurrent(editRelationMap);
                        // 更新房态
                        OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                        baseInfo.setHouseId(houseId);
                        baseInfo.setUpdateUserId(userId);
                        baseInfo.setUpdateUserName(userName);
                        baseInfo.setStage(HouseStageEnum.RU_ZHU.getValue());
                        ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
                        // 同步结果表
                        SearchVo searchVo = new SearchVo();
                        List<FilterEntity> filterList = new ArrayList<>();
                        FilterEntity currentRecordEntity = new FilterEntity();
                        currentRecordEntity.setFieldName("is_current_record");
                        currentRecordEntity.setFieldValue(Constants.TRUE.toString());
                        currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
                        filterList.add(currentRecordEntity);
                        FilterEntity houseIdEntity = new FilterEntity();
                        houseIdEntity.setFieldName("result.house_id");
                        houseIdEntity.setFieldValue(houseId.toString());
                        houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
                        filterList.add(houseIdEntity);
                        searchVo.setFilterList(filterList);
                        List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
                        // 更新上一条为历史数据
                        HouseListEntity houseEntity = new HouseListEntity();
                        houseEntity.setIsCurrentRecord(Constants.FALSE);
                        // houseEntity.setIsCurrentRecordName("历史");
                        houseEntity.setHouseId(houseId);
                        houseResultMapper.updateByHouseId(houseEntity);

                        if (!CollectionUtils.isEmpty(houseListEntities)) {
                            baseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                            // 新增当前入住信息
                            HouseListEntity houseListEntity = houseListEntities.get(0);
                            houseListEntity.setHouseStage(HouseStageEnum.RU_ZHU.getValue());
                            houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                            houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                            houseListEntity.setIsCurrentRecord(Constants.TRUE);
                            houseListEntity.setCheckInStageName("已入住");
                            houseListEntity.setHouseOperateType(HouseOperateTypeEnum.RU_ZHU.getValue());
                            houseListEntity.setHandleTime(checkInDate);
                            houseResultMapper.insertSelective(houseListEntity);
                        }
                    }
                }
            } else {
                OwnerHouseRelationship relationship = relationships.get(relationships.size() - 1);
                List<Long> ownerIdList = ownerHouseRelationshipMapper.listOwnerIdByHouseId(houseId);
                for (OwnerHouseRelationship ownerHouseRelationship : relationships) {
                    if (ownerId.equals(ownerHouseRelationship.getOwnerId())) {
                        // 房间+业主（含产权人）不变，且为当前业主时更新最后一条操作记录
                        if (ownerIdList.contains(ownerId)) {
                            detailId = ownerHouseRelationship.getDetailId();
                        }
                    }
                }
                if (Constants.TRUE.equals(updateFlag)) {
                    OwnerHouseStageDetail detail = new OwnerHouseStageDetail();
                    detail.setDetailId(relationship.getDetailId());
                    detail.setHandleTime(checkInDate);
                    detail.setUpdateUserId(userId);
                    detail.setUpdateUserName(userName);
                    ownerHouseStageDetailMapper.updateByPrimaryKeySelective(detail);
                }

            }
            // 装修
            checkMap.put("houseId", houseId);
            checkMap.put("houseOperateType", HouseOperateTypeEnum.ZHUANG_XIU.getValue());
            relationships = ownerHouseRelationshipMapper.listAllRelationHistory(checkMap);
            if (CollectionUtils.isEmpty(relationships)) {
                if (decorateStartDate != null) {
                    // 不存在 新增装修记录
                    long previousDetailId = detailId;
                    // 业主房产操作---装修
                    OwnerHouseBaseInfo houseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                    OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
                    ownerHouseStageDetail.setHouseId(houseId);
                    ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
                    ownerHouseStageDetail.setPreviousDetailId(previousDetailId);
                    ownerHouseStageDetail.setOldOwnerId(ownerId);
                    ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
                    ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
                    ownerHouseStageDetail.setCreateUserId(userId);
                    ownerHouseStageDetail.setCreateUserName(userName);
                    ownerHouseStageDetail.setUpdateUserId(userId);
                    ownerHouseStageDetail.setUpdateUserName(userName);

                    // 保存新业主相关信息
                    int index = ownerHouseStageDetailMapper.insertSelective(ownerHouseStageDetail);
                    detailId = ownerHouseStageDetail.getDetailId();
                    if (index > 0) {
                        // 保存装修信息
                        OwnerHouseStageExtendInfoDecorate extendInfoDecorate = new OwnerHouseStageExtendInfoDecorate();
                        extendInfoDecorate.setDetailId(detailId);
                        extendInfoDecorate.setDecorateStartDate(decorateStartDate);
                        extendInfoDecorate.setDecorateStartDate(decorateEndDate);
                        decorateMapper.insertSelective(extendInfoDecorate);
                        // 修改当前记录为历史记录
                        Map<String, Object> editRelationMap = new HashMap<>();
                        editRelationMap.put("userId", userId);
                        editRelationMap.put("preDetailId", previousDetailId);
                        editRelationMap.put("houseId", houseId);
                        ownerHouseRelationshipMapper.editCurrentRecordFalse(editRelationMap);
                        // 新增装修记录为当前记录
                        HouseStageEnum houseStageEnum = HouseStageEnum.getInstance(houseBaseInfo.getStage());
                        if (houseStageEnum.equals(HouseStageEnum.KONG_ZHI)) {
                            OwnerHouseRelationship houseRelationship = new OwnerHouseRelationship();
                            long developerId = 0;
                            // 获取开发商
                            OwnerHouseBaseInfo baseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                            if (baseInfo != null) {
                                if (HouseTypeEnum.PUBLICAREA.getValue().equals(baseInfo.getHouseType())) {
                                    OwnerHousePublicAreaInfo publicAreaInfo = ownerHousePublicAreaInfoMapper.selectByPrimaryKey(houseId);
                                    developerId = publicAreaInfo.getDeveloper();
                                } else {
                                    List<Long> parentIdList = StringUtils.handlerPath2List(baseInfo.getPath());
                                    List<OwnerHouseBaseInfo> houseBaseInfoList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(parentIdList);
                                    for (OwnerHouseBaseInfo ownerHouseBaseInfo : houseBaseInfoList) {
                                        HouseTypeEnum houseTypeEnum = HouseTypeEnum.getInstance(ownerHouseBaseInfo.getHouseType());
                                        switch (houseTypeEnum) {
                                            case BUILDING:
                                                OwnerHouseBuildingExtendInfo buildingExtendInfo = ownerHouseBuildingExtendInfoMapper.selectByPrimaryKey(ownerHouseBaseInfo.getHouseId());
                                                if (buildingExtendInfo != null) {
                                                    developerId = buildingExtendInfo.getDeveloper();
                                                }
                                            case CLUSTER:
                                                OwnerHouseClusterInfo clusterInfo = ownerHouseClusterInfoMapper.selectByPrimaryKey(ownerHouseBaseInfo.getHouseId());
                                                if (clusterInfo != null) {
                                                    developerId = clusterInfo.getDeveloper();
                                                }
                                            case GARAGE:
                                                OwnerHouseGarageInfo garageInfo = ownerHouseGarageInfoMapper.selectByPrimaryKey(ownerHouseBaseInfo.getHouseId());
                                                if (garageInfo != null) {
                                                    developerId = garageInfo.getDeveloper();
                                                }
                                            case PRECINCT:
                                                OwnerHousePrecinctExtendInfo projectExtendInfo = ownerHousePrecinctExtendInfoMapper.selectByPrimaryKey(ownerHouseBaseInfo.getHouseId());
                                                if (projectExtendInfo != null) {
                                                    developerId = projectExtendInfo.getDeveloper();
                                                }
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                            houseRelationship.setOwnerId(developerId);
                            houseRelationship.setIsCurrentRecord(Constants.TRUE);
                            houseRelationship.setOwnerCategory(OwnerConstants.OWNER_CATEGORY_OWNER);
                            houseRelationship.setIsDeleted(Constants.FALSE);
                            houseRelationship.setCreateUserId(userId);
                            houseRelationship.setCreateUserName(userName);
                            houseRelationship.setUpdateUserId(userId);
                            houseRelationship.setUpdateUserName(userName);
                            houseRelationship.setDetailId(detailId);
                            ownerHouseRelationshipMapper.insertSelective(houseRelationship);
                        } else {
                            editRelationMap.put("detailId", ownerHouseStageDetail.getDetailId());
                            ownerHouseRelationshipMapper.insertBatchForCurrent(editRelationMap);
                        }
                        // 更新房态
                        OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                        baseInfo.setHouseId(houseId);
                        baseInfo.setUpdateUserId(userId);
                        baseInfo.setUpdateUserName(userName);
                        baseInfo.setDecorateStage(HouseDecorateStageEnum.DECORATE_STAGE_IN.getValue());
                        ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
                        // 同步结果表
                        SearchVo searchVo = new SearchVo();
                        List<FilterEntity> filterList = new ArrayList<>();
                        FilterEntity currentRecordEntity = new FilterEntity();
                        currentRecordEntity.setFieldName("is_current_record");
                        currentRecordEntity.setFieldValue(Constants.TRUE.toString());
                        currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
                        filterList.add(currentRecordEntity);
                        FilterEntity houseIdEntity = new FilterEntity();
                        houseIdEntity.setFieldName("result.house_id");
                        houseIdEntity.setFieldValue(houseId.toString());
                        houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
                        filterList.add(houseIdEntity);
                        searchVo.setFilterList(filterList);
                        List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
                        // 更新上一条为历史数据
                        HouseListEntity houseEntity = new HouseListEntity();
                        houseEntity.setIsCurrentRecord(Constants.FALSE);
                        // houseEntity.setIsCurrentRecordName("历史");
                        houseEntity.setHouseId(houseId);
                        houseResultMapper.updateByHouseId(houseEntity);
                        if (!CollectionUtils.isEmpty(houseListEntities)) {
                            baseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                            // 新增当前装修信息
                            HouseListEntity houseListEntity = houseListEntities.get(0);
                            houseListEntity.setHouseStage(HouseStageEnum.RU_ZHU.getValue());
                            houseListEntity.setDecorateStage(HouseDecorateStageEnum.DECORATE_STAGE_IN.getValue());
                            houseListEntity.setDecorateStageName(HouseDecorateStageEnum.DECORATE_STAGE_IN.getTitle());
                            houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                            houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                            houseListEntity.setIsCurrentRecord(Constants.TRUE);
                            houseListEntity.setHouseOperateType(HouseOperateTypeEnum.ZHUANG_XIU.getValue());
                            houseListEntity.setStartTime(decorateStartDate);
                            houseListEntity.setEndTime(decorateEndDate);
                            houseResultMapper.insertSelective(houseListEntity);
                        }
                    }
                }
            } else {
                OwnerHouseRelationship relationship = relationships.get(relationships.size() - 1);
                List<Long> ownerIdList = ownerHouseRelationshipMapper.listOwnerIdByHouseId(houseId);
                for (OwnerHouseRelationship ownerHouseRelationship : relationships) {
                    if (ownerId.equals(ownerHouseRelationship.getOwnerId())) {
                        // 房间+业主（含产权人）不变，且为当前业主时更新最后一条操作记录
                        if (ownerIdList.contains(ownerId)) {
                            detailId = ownerHouseRelationship.getDetailId();
                        }
                    }
                }
                if (Constants.TRUE.equals(updateFlag)) {
                    OwnerHouseStageExtendInfoDecorate decorate = new OwnerHouseStageExtendInfoDecorate();
                    decorate.setDetailId(relationship.getDetailId());
                    decorate.setDecorateStartDate(decorateStartDate);
                    decorate.setDecorateEndDate(decorateEndDate);
                    decorate.setUpdateUserId(userId);
                    decorate.setUpdateUserName(userName);
                    decorateMapper.updateByPrimaryKeySelective(decorate);
                }
            }
            // 出租
            // 保存租户
            String rentOwnerName = (String) excelValueMap.get("rentOwnerName");
            String rentMobile = (String) excelValueMap.get("rentMobile");
            String rentCertificateType = (String) excelValueMap.get("rentCertificateType");
            // String rentCertificateTypeName = (String) excelValueMap.get("rentCertificateTypeName");
            String rentCertificate = (String) excelValueMap.get("rentCertificate");
            String rentNativePlace = (String) excelValueMap.get("rentNativePlace");
            String rentMaritalStatus = (String) excelValueMap.get("rentMaritalStatus");
            // String rentMaritalStatusName = (String) excelValueMap.get("rentMaritalStatusName");
            String rentEducation = (String) excelValueMap.get("rentEducation");
            // String rentEducationName = (String) excelValueMap.get("rentEducationName");
            String rentRegion = (String) excelValueMap.get("rentRegion");
            // String rentRegionName = (String) excelValueMap.get("rentRegionName");
            String rentNation = (String) excelValueMap.get("rentNation");
            // String rentNationName = (String) excelValueMap.get("rentNationName");
            String rentLinkman = (String) excelValueMap.get("rentLinkman");
            String rentLinkmanPhone = (String) excelValueMap.get("rentLinkmanPhone");
            String rentPhone = (String) excelValueMap.get("rentPhone");
            String rentOwnerType = (String) excelValueMap.get("rentOwnerType");
            // String rentOwnerTypeName = (String) excelValueMap.get("rentOwnerTypeName");
            String rentCompanyPhone = (String) excelValueMap.get("rentCompanyPhone");
            String rentFax = (String) excelValueMap.get("rentFax");
            String rentEmergencyContact = (String) excelValueMap.get("rentEmergencyContact");
            String rentEmergencyContactPhone = (String) excelValueMap.get("rentEmergencyContactPhone");
            if (!CommonUtils.isNullOrBlank(rentOwnerName)) {
                Map<String, Object> rentCustomerMap = new HashMap<>();
                rentCustomerMap.put("ownerName", rentOwnerName);
                rentCustomerMap.put("organizationId", organizationId);
                rentCustomerMap.put("certificate", rentCertificate);
                rentCustomerMap.put("mobile", rentMobile);
                CustomerVo rentCustomerVo = ownerCustomerBaseInfoMapper.loadCustomerByName(rentCustomerMap);

                OwnerHouseBaseInfo houseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                Long rentOwnerId = 0L;
                if (rentCustomerVo == null) {
                    // 新增业主
                    OwnerCustomerBaseInfo baseInfo = new OwnerCustomerBaseInfo();
                    baseInfo.setOwnerName(rentOwnerName);
                    // TODO 数据字典查询Value
                    baseInfo.setCertificateType(rentCertificateType);
                    baseInfo.setCertificate(rentCertificate);
                    baseInfo.setEnterpriseId(enterpriseId);
                    baseInfo.setOrganizationId(organizationId);
                    baseInfo.setOwnerType(rentOwnerType);
                    baseInfo.setIsDeleted(Constants.DELETE_NO);
                    baseInfo.setCreateUserId(userId);
                    baseInfo.setCreateUserName(userName);
                    baseInfo.setUpdateUserId(userId);
                    baseInfo.setUpdateUserName(userName);
                    baseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_NONE);
                    baseInfo.setPrecinctId(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH);

                    // 新增客户基础信息
                    int index = ownerCustomerBaseInfoMapper.insert(baseInfo);
                    rentOwnerId = baseInfo.getOwnerId();
                    if (index > 0) {
                        // 新增客户扩展信息
                        OwnerCustomerInfo customerInfo = new OwnerCustomerInfo();
                        customerInfo.setOwnerId(baseInfo.getOwnerId());
                        customerInfo.setMobile(rentMobile);
                        customerInfo.setPhone(rentPhone);
                        customerInfo.setCompanyPhone(rentCompanyPhone);
                        // TODO 数据字典查询Value
                        customerInfo.setEducation(rentEducation);
                        customerInfo.setNation(rentNation);
                        customerInfo.setRegion(rentRegion);
                        customerInfo.setMaritalStatus(rentMaritalStatus);
                        customerInfo.setLinkman(rentLinkman);
                        customerInfo.setLinkmanPhone(rentLinkmanPhone);
                        customerInfo.setFax(rentFax);
                        customerInfo.setNativePlace(rentNativePlace);
                        customerInfo.setEmergencyContact(rentEmergencyContact);
                        customerInfo.setEmergencyContactPhone(rentEmergencyContactPhone);
                        customerInfo.setCreateUserId(userId);
                        customerInfo.setCreateUserName(userName);
                        customerInfo.setUpdateUserId(userId);
                        customerInfo.setUpdateUserName(userName);
                        customerInfo.setIsDeleted(Constants.DELETE_NO);
                        ownerCustomerInfoMapper.insert(customerInfo);
                        // 同步客户结果表
                        OwnerCustomerResult ownerCustomerResult = new OwnerCustomerResult();
                        BeanUtils.copyProperties(baseInfo, ownerCustomerResult);
                        BeanUtils.copyProperties(customerInfo, ownerCustomerResult);
                        ownerCustomerResult.setUpdateTime(new Date());
                        // ownerCustomerResult.setOwnerTypeName(rentOwnerTypeName);
                        // ownerCustomerResult.setCertificateTypeName(rentCertificateTypeName);
                        // ownerCustomerResult.setEducationName(rentEducationName);
                        // ownerCustomerResult.setRegionName(rentRegionName);
                        // ownerCustomerResult.setNationName(rentNationName);
                        // ownerCustomerResult.setMaritalStatusName(rentMaritalStatusName);
                        customerResultMapper.insertSelective(ownerCustomerResult);
                    }
                } else {
                    // 修改租户
                    rentOwnerId = rentCustomerVo.getOwnerId();
                    if (Constants.TRUE.equals(updateFlag)) {
                        OwnerCustomerBaseInfo baseInfo = new OwnerCustomerBaseInfo();
                        baseInfo.setOwnerId(rentCustomerVo.getOwnerId());
                        baseInfo.setIsDeleted(Constants.DELETE_NO);
                        baseInfo.setUpdateUserId(userId);
                        baseInfo.setUpdateUserName(userName);
                        baseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_NONE);
                        if (rentCustomerVo.getPrecinctId().contains(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH)) {
                            baseInfo.setPrecinctId(rentCustomerVo.getPrecinctId() + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH);
                        }

                        // 新增客户基础信息
                        int index = ownerCustomerBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
                        if (index > 0) {
                            // 新增客户扩展信息
                            OwnerCustomerInfo customerInfo = new OwnerCustomerInfo();
                            customerInfo.setOwnerId(baseInfo.getOwnerId());
                            customerInfo.setMobile(rentMobile);
                            customerInfo.setPhone(rentPhone);
                            customerInfo.setCompanyPhone(rentCompanyPhone);
                            // TODO 数据字典查询Value
                            customerInfo.setEducation(rentEducation);
                            customerInfo.setNation(rentNation);
                            customerInfo.setRegion(rentRegion);
                            customerInfo.setMaritalStatus(rentMaritalStatus);
                            customerInfo.setLinkman(rentLinkman);
                            customerInfo.setLinkmanPhone(rentLinkmanPhone);
                            customerInfo.setFax(rentFax);
                            customerInfo.setNativePlace(rentNativePlace);
                            customerInfo.setEmergencyContact(rentEmergencyContact);
                            customerInfo.setEmergencyContactPhone(rentEmergencyContactPhone);
                            customerInfo.setUpdateUserId(userId);
                            customerInfo.setUpdateUserName(userName);
                            customerInfo.setIsDeleted(Constants.DELETE_NO);
                            ownerCustomerInfoMapper.updateByPrimaryKeySelective(customerInfo);
                            // 同步客户结果表
                            OwnerCustomerResult ownerCustomerResult = new OwnerCustomerResult();
                            BeanUtils.copyProperties(rentCustomerVo, ownerCustomerResult);
                            if (rentCustomerVo.getPrecinctId().contains(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH)) {
                                ownerCustomerResult.setPrecinctId(rentCustomerVo.getPrecinctId() + houseBaseInfo.getPrecinctId() + Constants.SEPARATOR_PATH);
                            }

                            // ownerCustomerResult.setOwnerTypeName(rentOwnerTypeName);
                            // ownerCustomerResult.setCertificateTypeName(rentCertificateTypeName);
                            // ownerCustomerResult.setEducationName(rentEducationName);
                            // ownerCustomerResult.setRegionName(rentRegionName);
                            // ownerCustomerResult.setNationName(rentNationName);
                            // ownerCustomerResult.setMaritalStatusName(rentMaritalStatusName);
                            ownerCustomerResult.setUpdateTime(new Date());
                            customerResultMapper.updateByPrimaryKeySelective(ownerCustomerResult);
                        }
                    }
                }
                if (!CommonUtils.isObjectEmpty(rentOwnerId)) {
                    // 保存银行卡
                    String rentBankName = (String) excelValueMap.get("rentBankId");
                    String rentBankAddress = (String) excelValueMap.get("rentBankAddress");
                    String rentAccountName = (String) excelValueMap.get("rentAccountName");
                    String rentAccount = (String) excelValueMap.get("rentAccount");
                    String isEnableStr = (String) excelValueMap.get("rentIsEnable");
                    Byte rentIsEnable = 0;
                    if ("已启用".equals(isEnableStr)) {
                        rentIsEnable = Constants.TRUE;
                    } else {
                        rentIsEnable = Constants.FALSE;
                    }
                    String rentProtocolNumber = (String) excelValueMap.get("rentProtocolNumber");
                    String rentCollectionNumber = (String) excelValueMap.get("rentCollectionNumber");
                    String rentBankRemark = (String) excelValueMap.get("rentBankRemark");
                    if (!CommonUtils.isNullOrBlank(rentAccount)) {
                        OwnerCustomerBankAccount bankAccount = new OwnerCustomerBankAccount();
                        bankAccount.setOwnerId(ownerId);
                        bankAccount.setBankName(rentBankName);
                        bankAccount.setBankAddress(rentBankAddress);
                        bankAccount.setAccountName(rentAccountName);
                        bankAccount.setAccount(rentAccount);
                        bankAccount.setIsEnable(rentIsEnable);
                        bankAccount.setProtocolNumber(rentProtocolNumber);
                        bankAccount.setCollectionNumber(rentCollectionNumber);
                        bankAccount.setRemark(rentBankRemark);
                        bankAccount.setUpdateUserId(userId);
                        bankAccount.setUpdateUserName(userName);
                        bankAccount.setIsDeleted(Constants.FALSE);
                        bankAccount.setCreateUserId(userId);
                        bankAccount.setCreateUserName(userName);
                        ownerCustomerBankAccountMapper.insertSelective(bankAccount);
                    }
                    checkMap.put("houseId", houseId);
                    checkMap.put("houseOperateType", HouseOperateTypeEnum.CHU_ZU.getValue());
                    relationships = ownerHouseRelationshipMapper.listAllRelationHistory(checkMap);
                    if (CollectionUtils.isEmpty(relationships)) {
                        if (rentStartDate != null) {
                            // 不存在 新增收房记录
                            long previousDetailId = detailId;
                            // 业主房产操作---出租
                            houseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                            OwnerHouseStageDetail ownerHouseStageDetail = new OwnerHouseStageDetail();
                            ownerHouseStageDetail.setHouseId(houseId);
                            ownerHouseStageDetail.setHouseOperateType(HouseOperateTypeEnum.CHU_ZU.getValue());
                            ownerHouseStageDetail.setOldOwnerId(ownerId);
                            ownerHouseStageDetail.setPreviousDetailId(previousDetailId);
                            ownerHouseStageDetail.setHouseStage(houseBaseInfo.getStage());
                            ownerHouseStageDetail.setIsDeleted(Constants.FALSE);
                            ownerHouseStageDetail.setCreateUserId(userId);
                            ownerHouseStageDetail.setCreateUserName(userName);
                            ownerHouseStageDetail.setUpdateUserId(userId);
                            ownerHouseStageDetail.setUpdateUserName(userName);
                            int index = ownerHouseStageDetailMapper.insertSelective(ownerHouseStageDetail);
                            detailId = ownerHouseStageDetail.getDetailId();
                            if (index > 0) {
                                // 保存出租信息
                                OwnerHouseStageExtendInfoRent extendInfoRent = new OwnerHouseStageExtendInfoRent();
                                extendInfoRent.setDetailId(ownerHouseStageDetail.getDetailId());
                                extendInfoRent.setRentStartDate(rentStartDate);
                                extendInfoRent.setRentEndDate(rentEndDate);
                                extendInfoRent.setRentOwnerId(rentOwnerId);
                                extendInfoRent.setOwnerProperty(Constants.OWNER_PROPERTY_RENT);
                                stageRentMapper.insertSelective(extendInfoRent);
                                // 修改客户性质
                                rentCustomerVo = ownerCustomerBaseInfoMapper.loadCustomer(rentOwnerId);
                                if (!Constants.OWNER_PROPERTY_OWNER.equals(rentCustomerVo.getOwnerProperty()) && !Constants.OWNER_PROPERTY_DEVOLOPER.equals(rentCustomerVo.getOwnerProperty())) {
                                    OwnerCustomerBaseInfo customerBaseInfo = new OwnerCustomerBaseInfo();
                                    customerBaseInfo.setOwnerId(rentOwnerId);
                                    customerBaseInfo.setUpdateUserId(userId);
                                    customerBaseInfo.setUpdateUserName(userName);
                                    customerBaseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_RENT);
                                    ownerCustomerBaseInfoMapper.updateByPrimaryKeySelective(customerBaseInfo);
                                    OwnerCustomerResult ownerCustomerResult = new OwnerCustomerResult();
                                    BeanUtils.copyProperties(customerBaseInfo, ownerCustomerResult);
                                    ownerCustomerResult.setUpdateTime(new Date());
                                    customerResultMapper.updateByPrimaryKeySelective(ownerCustomerResult);
                                }

                                // 修改当前记录为历史记录
                                Map<String, Object> editRelationMap = new HashMap<>();
                                editRelationMap.put("userId", userId);
                                editRelationMap.put("preDetailId", previousDetailId);
                                editRelationMap.put("houseId", houseId);
                                ownerHouseRelationshipMapper.editCurrentRecordFalse(editRelationMap);
                                // 新增出租记录为当前记录
                                HouseStageEnum houseStageEnum = HouseStageEnum.getInstance(houseBaseInfo.getStage());
                                if (houseStageEnum.equals(HouseStageEnum.KONG_ZHI)) {
                                    OwnerHouseRelationship houseRelationship = new OwnerHouseRelationship();
                                    long developerId = 0;
                                    // 获取开发商
                                    OwnerHouseBaseInfo baseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                                    if (baseInfo != null) {
                                        if (HouseTypeEnum.PUBLICAREA.getValue().equals(baseInfo.getHouseType())) {
                                            OwnerHousePublicAreaInfo publicAreaInfo = ownerHousePublicAreaInfoMapper.selectByPrimaryKey(houseId);
                                            developerId = publicAreaInfo.getDeveloper();
                                        } else {
                                            List<Long> parentIdList = StringUtils.handlerPath2List(baseInfo.getPath());
                                            List<OwnerHouseBaseInfo> houseBaseInfoList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(parentIdList);
                                            for (OwnerHouseBaseInfo ownerHouseBaseInfo : houseBaseInfoList) {
                                                HouseTypeEnum houseTypeEnum = HouseTypeEnum.getInstance(ownerHouseBaseInfo.getHouseType());
                                                switch (houseTypeEnum) {
                                                    case BUILDING:
                                                        OwnerHouseBuildingExtendInfo buildingExtendInfo = ownerHouseBuildingExtendInfoMapper.selectByPrimaryKey(ownerHouseBaseInfo.getHouseId());
                                                        if (buildingExtendInfo != null) {
                                                            developerId = buildingExtendInfo.getDeveloper();
                                                        }
                                                    case CLUSTER:
                                                        OwnerHouseClusterInfo clusterInfo = ownerHouseClusterInfoMapper.selectByPrimaryKey(ownerHouseBaseInfo.getHouseId());
                                                        if (clusterInfo != null) {
                                                            developerId = clusterInfo.getDeveloper();
                                                        }
                                                    case GARAGE:
                                                        OwnerHouseGarageInfo garageInfo = ownerHouseGarageInfoMapper.selectByPrimaryKey(ownerHouseBaseInfo.getHouseId());
                                                        if (garageInfo != null) {
                                                            developerId = garageInfo.getDeveloper();
                                                        }
                                                    case PRECINCT:
                                                        OwnerHousePrecinctExtendInfo projectExtendInfo = ownerHousePrecinctExtendInfoMapper.selectByPrimaryKey(ownerHouseBaseInfo.getHouseId());
                                                        if (projectExtendInfo != null) {
                                                            developerId = projectExtendInfo.getDeveloper();
                                                        }
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                    }
                                    houseRelationship.setOwnerId(developerId);
                                    houseRelationship.setIsCurrentRecord(Constants.TRUE);
                                    houseRelationship.setOwnerCategory(OwnerConstants.OWNER_CATEGORY_OWNER);
                                    houseRelationship.setIsDeleted(Constants.FALSE);
                                    houseRelationship.setCreateUserId(userId);
                                    houseRelationship.setCreateUserName(userName);
                                    houseRelationship.setUpdateUserId(userId);
                                    houseRelationship.setUpdateUserName(userName);
                                    houseRelationship.setDetailId(detailId);
                                    ownerHouseRelationshipMapper.insertSelective(houseRelationship);
                                } else {
                                    editRelationMap.put("detailId", ownerHouseStageDetail.getDetailId());
                                    ownerHouseRelationshipMapper.insertBatchForCurrent(editRelationMap);
                                }

                                // 更新房态
                                OwnerHouseBaseInfo baseInfo = new OwnerHouseBaseInfo();
                                baseInfo.setHouseId(houseId);
                                baseInfo.setUpdateUserId(userId);
                                baseInfo.setUpdateUserName(userName);
                                baseInfo.setRentStage(HouseRentStageEnum.RENT_STAGE_IN.getValue());
                                ownerHouseBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
                                // 同步结果表
                                SearchVo searchVo = new SearchVo();
                                List<FilterEntity> filterList = new ArrayList<>();
                                FilterEntity currentRecordEntity = new FilterEntity();
                                currentRecordEntity.setFieldName("is_current_record");
                                currentRecordEntity.setFieldValue(Constants.TRUE.toString());
                                currentRecordEntity.setComparison(Constants.COMPARISON_EQUAL);
                                filterList.add(currentRecordEntity);
                                FilterEntity houseIdEntity = new FilterEntity();
                                houseIdEntity.setFieldName("result.house_id");
                                houseIdEntity.setFieldValue(houseId.toString());
                                houseIdEntity.setComparison(Constants.COMPARISON_EQUAL);
                                filterList.add(houseIdEntity);
                                searchVo.setFilterList(filterList);
                                List<HouseListEntity> houseListEntities = houseResultMapper.listResultBySearch(searchVo);
                                // 更新上一条为历史数据
                                HouseListEntity houseEntity = new HouseListEntity();
                                houseEntity.setIsCurrentRecord(Constants.FALSE);
                                // houseEntity.setIsCurrentRecordName("历史");
                                houseEntity.setHouseId(houseId);
                                houseResultMapper.updateCurrentRecord(houseEntity);

                                if (!CollectionUtils.isEmpty(houseListEntities)) {
                                    baseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
                                    // 新增当前出租信息
                                    HouseListEntity houseListEntity = houseListEntities.get(0);
                                    houseListEntity.setRentStage(HouseRentStageEnum.RENT_STAGE_IN.getValue());
                                    houseListEntity.setRentStageName(HouseRentStageEnum.RENT_STAGE_IN.getTitle());
                                    houseListEntity.setStageName(CommonUtils.getHouseStage(baseInfo.getStage(), baseInfo.getRentStage(), baseInfo.getDecorateStage(), baseInfo.getIsBlockUp()));
                                    houseListEntity.setDetailId(ownerHouseStageDetail.getDetailId());
                                    houseListEntity.setIsCurrentRecord(Constants.TRUE);
                                    houseListEntity.setHouseOperateType(HouseOperateTypeEnum.CHU_ZU.getValue());
                                    houseListEntity.setStartTime(rentStartDate);
                                    houseListEntity.setEndTime(rentEndDate);
                                    houseResultMapper.insertSelective(houseListEntity);
                                }
                            }
                        }
                    } else {
                        OwnerHouseRelationship relationship = relationships.get(relationships.size() - 1);
                        // List<Long> ownerIdList = ownerHouseRelationshipMapper.listOwnerIdByHouseId(houseId);
                        // for (OwnerHouseRelationship ownerHouseRelationship : relationships) {
                        // if (ownerId.equals(ownerHouseRelationship.getOwnerId())) {
                        // //房间+业主（含产权人）不变，且为当前业主时更新最后一条操作记录
                        // if (ownerIdList.contains(ownerId)) {
                        // detailId = ownerHouseRelationship.getDetailId();
                        // }
                        // }
                        // }
                        if (Constants.TRUE.equals(updateFlag)) {
                            OwnerHouseStageExtendInfoRent detail = new OwnerHouseStageExtendInfoRent();
                            detail.setDetailId(relationship.getDetailId());
                            detail.setRentOwnerId(rentOwnerId);
                            detail.setRentStartDate(rentStartDate);
                            detail.setRentEndDate(rentEndDate);
                            stageRentMapper.updateByPrimaryKeySelective(detail);
                        }
                    }
                }
            }
        } else {
            // TODO
            resultMap.put("resultType", Constants.RESULT_ERROR);
            resultMap.put("message", "导入失败,原因:导入业主非当前房产业主，");
        }
        resultMap.put("resultType", Constants.RESULT_SUCCESS);
        resultMap.put("detailId", detailId);
        return resultMap;
    }

    @Override
    @ReadDataSource
    public Map<String, Object> listPrecinctHouse(Long ownerId, Map<String, NsCoreDictionaryVo> dicMap) {
        Map<String, Object> resultMap = new HashMap<>();
        List<SelectVo> selectVoList = new ArrayList<>();
        List<MainHouseVo> mainHouseVos = new ArrayList<>();
        List<OwnerHouseRelationshipVo> precinctHouseVos = new ArrayList<>();
        // 获取客户所有关联房产
        List<OwnerHouseRelationshipVo> owner = ownerHouseRelationshipMapper.listPrecinctHouseByOwner(ownerId);
        List<OwnerHouseRelationshipVo> rent = ownerHouseRelationshipMapper.listPrecinctHouseByRent(ownerId);
        precinctHouseVos.addAll(owner);
        precinctHouseVos.addAll(rent);
        if (!CollectionUtils.isEmpty(precinctHouseVos)) {
            // 按项目对房产进行分组
            Map<Long, List<OwnerHouseRelationshipVo>> map = precinctHouseVos.stream().collect(Collectors.groupingBy(OwnerHouseRelationshipVo::getPrecinctId));
            Set<Entry<Long, List<OwnerHouseRelationshipVo>>> entry = map.entrySet();
            int index = 0;
            List<FormItemDataVo> formItemData = new ArrayList<>();
            Map<String, Object> mainHouseMap = new HashMap<>();
            List<Long> ownerIdList = new ArrayList<>();
            ownerIdList.add(ownerId);
            mainHouseMap.put("list", ownerIdList);
            List<OwnerCustomerMainHouse> mainHouseList = mainHouseMapper.listByOwnerHouse(mainHouseMap);
            for (Iterator<Entry<Long, List<OwnerHouseRelationshipVo>>> iterator = entry.iterator(); iterator.hasNext(); ) {
                Entry<Long, List<OwnerHouseRelationshipVo>> item = (Entry<Long, List<OwnerHouseRelationshipVo>>) iterator.next();
                FormItemDataVo formItemDataVo = new FormItemDataVo();
                MainHouseVo mainHouseVo = new MainHouseVo();
                precinctHouseVos.forEach(precinctHouseVo -> {
                    if (item.getKey().equals(precinctHouseVo.getPrecinctId())) {
                        mainHouseVo.setOwnerPrecinct(precinctHouseVo.getPrecinctName());
                    }
                });
                List<SelectItemVo> items = new ArrayList<>();
                for (OwnerHouseRelationshipVo house : item.getValue()) {
                    SelectItemVo selectItemVo = new SelectItemVo();
                    String ownerPropertyName = OwnerUtils.getDicName(dicMap.get("ownerPropertyDic"), house.getOwnerProperty());
                    selectItemVo.setLabel(house.getHouseFullName() + "(" + ownerPropertyName + ")");
                    selectItemVo.setValue(house.getHouseId().toString());
                    items.add(selectItemVo);
                    for (OwnerCustomerMainHouse mainHouse : mainHouseList) {
                        if (mainHouse.getHouseId().equals(house.getHouseId())) {
                            mainHouseVo.setOwnerHouse(house.getHouseId().toString());
                        }
                    }
                }
                mainHouseVos.add(mainHouseVo);
                formItemDataVo.setItems(items);
                SelectVo selectVo = new SelectVo();
                selectVo.setKey("ownerHouse");
                formItemDataVo.setIndex(index + "");
                formItemData.add(formItemDataVo);
                selectVo.setFormItemData(formItemData);
                selectVoList.add(selectVo);
                index++;
            }
        }
        resultMap.put("ownerMainHouseList", mainHouseVos);
        resultMap.put("ownerHouseList", selectVoList);
        return resultMap;
    }

    @Override
    @ReadDataSource
    public void checkHouseOnly(AddHouseVo addHouseVo, Long precinctId) {
        Map<String, Object> nameMap = new HashMap<>();
        nameMap.put("enterpriseId", addHouseVo.getEnterpriseId());
        nameMap.put("organizationId", addHouseVo.getOrganizationId());
        nameMap.put("houseName", addHouseVo.getHouseName());
        nameMap.put("houseId", addHouseVo.getHouseId());
        nameMap.put("houseType", addHouseVo.getHouseType());
        nameMap.put("parentId", addHouseVo.getParentId());
        nameMap.put("precinctId", precinctId);
        String houseInfo = addHouseVo.getHouseInfo();
        OwnerHouseBaseInfo tempHouseBase = JSONObject.parseObject(houseInfo, OwnerHouseBaseInfo.class);
        if (!HouseTypeEnum.AREA.equals(addHouseVo.getHouseTypeEnum())) {
            nameMap.put("no", tempHouseBase.getHouseNo());
            OwnerHouseBaseInfo houseNoOnly = ownerHouseBaseInfoMapper.loadHouseByNo(nameMap);
            if (houseNoOnly != null) {
                BizException.fail(ResultCodeEnum.DATA_EXIST, "节点编号已存在");
            }
        }
        switch (addHouseVo.getHouseTypeEnum()) {
            case AREA:
                OwnerHouseBaseInfo houseBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
                if (houseBase != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, HouseTypeEnum.AREA.getTitle() + "已存在");
                }
                break;
            case PRECINCT:
                houseBase = null;
                houseBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
                if (houseBase != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, HouseTypeEnum.PRECINCT.getTitle() + "已存在");
                }

                OwnerProjectInfoVo projectInfoVo = JSONObject.parseObject(houseInfo, OwnerProjectInfoVo.class);

                nameMap.put("shortName", projectInfoVo.getProShortName());
                OwnerHousePrecinctInfo checkPrecinctShortName = ownerHousePrecinctInfoMapper.loadHouseByShortName(nameMap);
                if (checkPrecinctShortName != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, "项目简称已存在");
                }

                // nameMap.put("no", projectInfoVo.getProNo());
                // OwnerHousePrecinctInfo checkPrecinctNo = ownerHousePrecinctInfoMapper.loadHouseByNo(nameMap);
                // if (checkPrecinctNo != null) {
                // BizException.fail(ResultCodeEnum.DATA_EXIST, "项目编号已存在");
                // }

                break;
            case BUILDING:
                houseBase = null;
                houseBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
                if (houseBase != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, HouseTypeEnum.BUILDING.getTitle() + "已存在");
                }
                OwnerBuildingInfoVo buildingInfoVo = JSONObject.parseObject(houseInfo, OwnerBuildingInfoVo.class);

                nameMap.put("shortName", buildingInfoVo.getBuildingShortName());
                OwnerHouseBuildingInfo checkBuildingShortName = ownerHouseBuildingInfoMapper.loadHouseByShortName(nameMap);
                if (checkBuildingShortName != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, "楼幢简称已存在");
                }

                // nameMap.put("no", buildingInfoVo.getBuildingNo());
                // OwnerHouseBuildingInfo checkBuildingNo = ownerHouseBuildingInfoMapper.loadHouseByNo(nameMap);
                // if (checkBuildingNo != null) {
                // BizException.fail(ResultCodeEnum.DATA_EXIST, "楼幢编号已存在");
                // }

                break;
            case UNIT:
                houseBase = null;
                houseBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
                if (houseBase != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, HouseTypeEnum.UNIT.getTitle() + "已存在");
                }
                OwnerUnitInfoVo unitInfoVo = JSONObject.parseObject(houseInfo, OwnerUnitInfoVo.class);

                nameMap.put("shortName", unitInfoVo.getUnitShortName());
                OwnerHouseUnitInfo checkUnitShortName = ownerHouseUnitInfoMapper.loadHouseByShortName(nameMap);
                if (checkUnitShortName != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, "单元简称已存在");
                }

                // nameMap.put("no", unitInfoVo.getUnitNo());
                // OwnerHouseUnitInfo checkUnitNo = ownerHouseUnitInfoMapper.loadHouseByNo(nameMap);
                // if (checkUnitNo != null) {
                // BizException.fail(ResultCodeEnum.DATA_EXIST, "单元编号已存在");
                // }

                break;
            case CLUSTER:
                houseBase = null;
                houseBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
                if (houseBase != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, HouseTypeEnum.CLUSTER.getTitle() + "已存在");
                }
                OwnerClusterInfoVo clusterInfoVo = JSONObject.parseObject(houseInfo, OwnerClusterInfoVo.class);

                nameMap.put("shortName", clusterInfoVo.getClusterShortName());
                OwnerHouseClusterInfo checkClusterShortName = ownerHouseClusterInfoMapper.loadHouseByShortName(nameMap);
                if (checkClusterShortName != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, "组团简称已存在");
                }

                // nameMap.put("no", clusterInfoVo.getClusterNo());
                // OwnerHouseClusterInfo checkClusterNo = ownerHouseClusterInfoMapper.loadHouseByNo(nameMap);
                // if (checkClusterNo != null) {
                // BizException.fail(ResultCodeEnum.DATA_EXIST, "组团编号已存在");
                // }

                break;
            case ROOM:
                houseBase = null;
                houseBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
                if (houseBase != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, HouseTypeEnum.ROOM.getTitle() + "已存在");
                }
                OwnerRoomInfoVo roomInfoVo = JSONObject.parseObject(houseInfo, OwnerRoomInfoVo.class);

                nameMap.put("shortName", roomInfoVo.getRoomShortName());
                OwnerHouseHouseInfo checkRoomShortName = ownerHouseHouseInfoMapper.loadHouseByShortName(nameMap);
                if (checkRoomShortName != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, "房间简称已存在");
                }

                // nameMap.put("no", roomInfoVo.getRoomNo());
                // OwnerHouseHouseInfo checkRoomNo = ownerHouseHouseInfoMapper.loadHouseByNo(nameMap);
                //
                // if (checkRoomNo != null) {
                // BizException.fail(ResultCodeEnum.DATA_EXIST, "房间编号已存在");
                // }

                break;
            case GARAGE:
                houseBase = null;
                houseBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
                if (houseBase != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, HouseTypeEnum.GARAGE.getTitle() + "已存在");
                }
                OwnerGarageInfoVo garageInfoVo = JSONObject.parseObject(houseInfo, OwnerGarageInfoVo.class);

                nameMap.put("shortName", garageInfoVo.getGarageShortName());
                OwnerHouseGarageInfo checkGarageShortName = ownerHouseGarageInfoMapper.loadHouseByShortName(nameMap);
                if (checkGarageShortName != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, "车库简称已存在");
                }

                // nameMap.put("no", garageInfoVo.getGarageNo());
                // OwnerHouseGarageInfo checkGarageNo = ownerHouseGarageInfoMapper.loadHouseByNo(nameMap);
                //
                // if (checkGarageNo != null) {
                // BizException.fail(ResultCodeEnum.DATA_EXIST, "车库编号已存在");
                // }
                break;
            case CARPORT:
                houseBase = null;
                houseBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
                if (houseBase != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, HouseTypeEnum.CARPORT.getTitle() + "已存在");
                }
                OwnerCarportInfoVo carportInfoVo = JSONObject.parseObject(houseInfo, OwnerCarportInfoVo.class);

                nameMap.put("shortName", carportInfoVo.getCarportShortName());
                OwnerHouseCarportInfo checkCarportShortName = ownerHouseCarportInfoMapper.loadHouseByShortName(nameMap);
                if (checkCarportShortName != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, "车位简称已存在");
                }

                // nameMap.put("no", carportInfoVo.getCarportNo());
                // OwnerHouseCarportInfo checkCarportNo = ownerHouseCarportInfoMapper.loadHouseByNo(nameMap);
                //
                // if (checkCarportNo != null) {
                // BizException.fail(ResultCodeEnum.DATA_EXIST, "车位编号已存在");
                // }

                break;
            case PUBLICAREA:
                houseBase = null;
                houseBase = ownerHouseBaseInfoMapper.loadHouseByName(nameMap);
                if (houseBase != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, HouseTypeEnum.PUBLICAREA.getTitle() + "已存在");
                }
                OwnerPublicAreaVo publicAreaVo = JSONObject.parseObject(houseInfo, OwnerPublicAreaVo.class);

                nameMap.put("shortName", publicAreaVo.getPublicAreaShortName());
                OwnerHousePublicAreaInfo checkPublicAreaShortName = ownerHousePublicAreaInfoMapper.loadHouseByShortName(nameMap);
                if (checkPublicAreaShortName != null) {
                    BizException.fail(ResultCodeEnum.DATA_EXIST, "公共区域简称已存在");
                }

                // nameMap.put("no", publicAreaVo.getPublicAreaNo());
                // OwnerHousePublicAreaInfo checkPublicAreaNo = ownerHousePublicAreaInfoMapper.loadHouseByNo(nameMap);
                //
                // if (checkPublicAreaNo != null) {
                // BizException.fail(ResultCodeEnum.DATA_EXIST, "公共区域编号已存在");
                // }
                break;
        }
    }

    @Override
    @ReadDataSource
    public List<Map<String, Object>> listSlaveHouse(Long houseId) {
        List<Map<String, Object>> slaveHouseList = new ArrayList<>();
        List<OwnerHouseMasterSlaveHouse> houseList = masterSlaveHouseMapper.listSlaveHouse(houseId);
        if (!CollectionUtils.isEmpty(houseList)) {
            List<Long> houseIdList = new ArrayList<>();
            houseList.forEach(slaveHouse -> {
                houseIdList.add(slaveHouse.getSlaveHouseId());

            });
            List<OwnerHouseBaseInfo> houseBaseInfos = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(houseIdList);
            if (!CollectionUtils.isEmpty(houseBaseInfos)) {
                houseBaseInfos.forEach(houseBaseInfo -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("slaveHouseId", houseBaseInfo.getHouseId());
                    map.put("slaveHouseFullName", houseBaseInfo.getHouseFullName());
                    slaveHouseList.add(map);
                });
            }

        }
        return slaveHouseList;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, Object> editBlockUpHouse(Boolean isBlockUp, List<Long> houseIdList, Long userId, String userName) {
        if (CollectionUtils.isEmpty(houseIdList)) {
            BizException.fail(ResultCodeEnum.PARAMS_MISSING, "未选择房产");
        }
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        params.put("houseIdList", houseIdList);
        params.put("isBlockUp", isBlockUp);
        params.put("isBlockUpName", Boolean.TRUE.equals(isBlockUp) ? "是" : "否");
        params.put("updateUserId", userId);
        Integer result = ownerHouseBaseInfoMapper.batchUpdateByHouseId(params);
        map.put("total", houseIdList.size());
        map.put("blockUpFailNum", houseIdList.size() - result);
        map.put("blockUpNum", result);
        if (result > 0) {
            // 更新结果表
            houseResultMapper.batchUpdateByHouseId(params);
        }
        return map;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, Object> editBlockUpAllHouseBySearch(Boolean isBlockUp, SearchVo searchVo, Long userId, String userName) throws Exception {
        Map<String, Object> map = new HashMap<>();
        // if (!CollectionUtils.isEmpty(searchVo.getTreeConditions())) {
        // searchVo.getFilterList().addAll(searchVo.getTreeConditions());
        // }
        List<HouseListVo> resultList = listHouse(searchVo, null, false).getList();
        if (!CollectionUtils.isEmpty(resultList)) {
            List<Long> houseIdList = new ArrayList<>();
            for (HouseListVo entity : resultList) {
                houseIdList.add(entity.getHouseId());
            }
            map = editBlockUpHouse(isBlockUp, houseIdList, userId, userName);
        }
        return map;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public List<OwnerHouseBaseInfo> listPrecinct(Long enterpriseId, Long organizationId) {
        Map<String, Object> map = new HashMap<>();
        map.put("houseType", HouseTypeEnum.PRECINCT.getValue());
        map.put("enterpriseId", enterpriseId);
        map.put("organizationId", organizationId);
        List<OwnerHouseBaseInfo> list = ownerHouseBaseInfoMapper.listHouseBase(map);
        return list;
    }

    @Override
    public List<OwnerHouseBaseInfo> addHouse(List<OwnerHouseBaseInfo> houseList) {
        for (OwnerHouseBaseInfo house : houseList) {
            house.setHouseFullName(house.getHouseName());
            house.setLevel(-1); // TODO SOSS初始化项目
            ownerHouseBaseInfoMapper.insertSelective(house);
        }

        return houseList;
    }

    @Override
    @ReadDataSource
    public List<OwnerHouseBaseInfo> listOwnerPrecinct(CustomerVo customerVo) {
        List<OwnerHouseBaseInfo> precinctList = new ArrayList<>(0);
        if (StringUtils.hasLength(customerVo.getPrecinctId())) {
            List<Long> precinctIdList = StringUtils.handlerPath2List(customerVo.getPrecinctId());
            CommonUtils.removeDuplicate(precinctIdList);
            if (!CollectionUtils.isEmpty(precinctIdList)) {
                precinctList = ownerHouseBaseInfoMapper.listOwnerHouseBaseInfoByHouseIdList(precinctIdList);
            }
        }
        return precinctList;
    }

    @Override
    @ReadDataSource
    public List<HouseListEntity> listAllLeafNode(Long houseId) {
        OwnerHouseBaseInfo houseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
        List<HouseListEntity> houseList = null;
        Map<String, Object> map = new HashMap<>();
        if (houseBaseInfo != null) {
            houseList = houseResultMapper.listHouseByPath(map);
            if (!CollectionUtils.isEmpty(houseList)) {
                for (Iterator<HouseListEntity> iterator = houseList.iterator(); iterator.hasNext(); ) {
                    HouseListEntity ownerHouseBaseInfo = (HouseListEntity) iterator.next();
                    if (!HouseTypeEnum.ROOM.getValue().equals(ownerHouseBaseInfo.getHouseType()) && !HouseTypeEnum.CARPORT.getValue().equals(ownerHouseBaseInfo.getHouseType())
                            && !HouseTypeEnum.PUBLICAREA.getValue().equals(ownerHouseBaseInfo.getHouseType())) {
                        iterator.remove();
                    }
                }
            }
        }
        return houseList;
    }

    @Override
    @ReadDataSource
    public List<OwnerHouseBaseInfo> listAllChildNode(Long houseId, Long enterpriseId, Long organizationId, List<String> houseTypes) {
        Map<String, Object> map = new HashMap<>();
        String path = Constants.SEPARATOR_PATH;
        if (houseId != 0L) {
            path = Constants.SEPARATOR_PATH + houseId + Constants.SEPARATOR_PATH;
        }
        map.put("path", path);
        map.put("organizationId", organizationId);
        map.put("houseTypes", houseTypes);
        map.put("enterpriseId", enterpriseId);
        List<OwnerHouseBaseInfo> houseList = ownerHouseBaseInfoMapper.listHouseBaseInfo(map);
        return houseList;
    }

    @ReadDataSource
    @Override
    public OwnerHouseBaseInfo getHouseInfo(Long houseId) {
        OwnerHouseBaseInfo houseBaseInfo = ownerHouseBaseInfoMapper.selectByPrimaryKey(houseId);
        return houseBaseInfo;
    }

    @ReadDataSource
    @Override
    public List<OwnerHouseBaseInfo> searchHouseInfo(String precinctName, String houseName) {
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long orgId = LoginDataHelper.getOrgId();
        Map<String, Object> map = Maps.newHashMap();
        map.put("enterpriseId", enterpriseId);
        map.put("organizationId", orgId);
        map.put("precinctName", precinctName);
        map.put("houseName", houseName);
        List<OwnerHouseBaseInfo> houseBaseInfoList = ownerHouseBaseInfoMapper.selectByPrecinctNameAndHouseName(map);
        return houseBaseInfoList;
    }

    @Override
    @ReadDataSource
    public OwnerHouseHouseInfo getHouseHouseInfo(Long houseId) {
        OwnerHouseHouseInfo ownerHouseHouseInfo = ownerHouseHouseInfoMapper.selectByPrimaryKey(houseId);
        return ownerHouseHouseInfo;
    }

	@Override
	@ReadDataSource
	public List<OwnerHouseBaseInfo> searchPrecinctApp(SearchProjectVo searchVo) {
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getOrgId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        List<OwnerHouseBaseInfo> houseBaseInfoList = ownerHouseBaseInfoMapper.searchPrecinctApp(searchVo);
        for (OwnerHouseBaseInfo ownerHouseBaseInfo : houseBaseInfoList) {
        	ownerHouseBaseInfo.setParentName(ownerHouseBaseInfoMapper.searchParentName(ownerHouseBaseInfo.getParentId()).getHouseName());
		}
		return houseBaseInfoList;
	}


    @Override
    public List<OwnerHouseBaseInfo> searchHouseInfoByFullName(Long enterpriseId, Long organizationId, String fullName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("enterpriseId", enterpriseId);
        map.put("organizationId", organizationId);
        map.put("fullName", fullName);
        List<OwnerHouseBaseInfo> houseBaseInfoList = ownerHouseBaseInfoMapper.searchHouseInfoByFullName(map);
        return houseBaseInfoList;
    }

	@Override
	@ReadDataSource
	public List<HouseListEntity> searchHouseTreeApp(SearchProjectVo searchVo) {
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getOrgId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        List<HouseListEntity> houseList = houseResultMapper.searchHouseTreeApp(searchVo);
		return houseList;
	}

}
