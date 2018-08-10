package com.newsee.owner.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.newsee.owner.dao.*;
import com.newsee.owner.entity.*;
import com.newsee.owner.service.IHouseOperateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.newsee.common.constant.Constants;
import com.newsee.common.entity.FilterEntity;
import com.newsee.common.enums.HouseTypeEnum;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.FileVo;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.common.vo.ProvinceCityArea;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;
import com.newsee.owner.enums.CustomerCallEnum;
import com.newsee.owner.enums.CustomerRelationEnum;
import com.newsee.owner.service.ICustomerService;
import com.newsee.owner.utils.OwnerUtils;
import com.newsee.owner.vo.CustomerFamilyVo;
import com.newsee.owner.vo.CustomerVo;
import com.newsee.owner.vo.FamilyInfoVo;
import com.newsee.owner.vo.MainHouseVo;
import com.newsee.owner.vo.OwnerBankAccountVo;
import com.newsee.owner.vo.OwnerHouseRelationshipVo;
import com.newsee.system.vo.NsCoreDictionaryVo;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private OwnerCustomerBaseInfoMapper ownerCustomerBaseInfoMapper;

    @Autowired
    private OwnerCustomerInfoMapper ownerCustomerInfoMapper;

    @Autowired
    private OwnerHouseRelationshipMapper ownerHouseRelationshipMapper;

    @Autowired
    private OwnerCustomerFamilyInfoMapper ownerCustomerFamilyInfoMapper;

    @Autowired
    private OwnerCustomerBankAccountMapper ownerCustomerBankAccountMapper;

    @Autowired
    private OwnerCustomerResultMapper customerResultMapper;

    @Autowired
    private OwnerCustomerMainHouseMapper mainHouseMapper;

    @Autowired
    private OwnerHouseBaseInfoMapper houseBaseMapper;

    @Autowired
    private OwnerCustomerCarMapper carMapper;

    @Autowired
    private OwnerHouseResultMapper ownerHouseResultMapper;

    @Autowired
    private IHouseOperateService iHouseOperateService;


    @SuppressWarnings("unchecked")
    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public PageInfo<OwnerCustomerResult> listPage(SearchVo searchVo, Map<String, Object> columnMap, boolean pageFlag) throws Exception {

        Map<String, Object> map = getSearchCondition(searchVo);
        // List<Long> searchOwnerIdList = (List<Long>) map.get("ownerIdList");
        // if (CollectionUtils.isEmpty(searchOwnerIdList)) {
        // return new PageInfo<>(new ArrayList<>(0));
        // }
        // if (!CollectionUtils.isEmpty(precinctListForNoHouse)) {
        // FilterEntity filterEntity = new FilterEntity();
        // filterEntity.setFieldName("precinct_id");
        // filterEntity.setComparison(Constants.COMPARISON_LIKE);
        // filterEntity.setFieldValue(Constants.SEPARATOR_PATH+String.join(Constants.SEPARATOR_PATH,
        // precinctListForNoHouse)+Constants.SEPARATOR_PATH);
        // searchVo.getFilterList().add(filterEntity);
        // }
        List<OwnerCustomerResult> list = new ArrayList<>();
        PageInfo<OwnerCustomerResult> pageInfo = null;
        if (map == null) {
            return new PageInfo<>(list);
        }
        if (pageFlag) {
            PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
            list = customerResultMapper.listResultBySearch(map);
            pageInfo = new PageInfo<>(list);
        } else {
            list = customerResultMapper.listResultBySearch(map);
            pageInfo = new PageInfo<>(list);
        }
        if (!CollectionUtils.isEmpty(list)) {
            List<Long> precinctList = new ArrayList<>();
            List<Long> ownerIdList = new ArrayList<>();
            for (OwnerCustomerResult item : list) {
                String precinct = item.getPrecinctId();
                if (StringUtils.hasLength(precinct)) {
                    precinctList = Stream.of(precinctList, StringUtils.handlerPath2List(precinct)).flatMap(data -> data.stream()).collect(Collectors.toList());
                    ownerIdList.add(item.getOwnerId());
                }
            }
            CommonUtils.removeDuplicate(precinctList);
            List<OwnerHouseBaseInfo> houseBaseInfoList = null;
            if (!CollectionUtils.isEmpty(precinctList)) {
                houseBaseInfoList = houseBaseMapper.listOwnerHouseBaseInfoByHouseIdList(precinctList);
            }
            for (OwnerCustomerResult item : list) {
                List<OwnerHouseBaseInfo> ownerPrecinctList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(houseBaseInfoList)) {
                    for (OwnerHouseBaseInfo ownerHouseBaseInfo : houseBaseInfoList) {
                        List<Long> temp = StringUtils.handlerPath2List(item.getPrecinctId());
                        if (temp.contains(ownerHouseBaseInfo.getHouseId())) {
                            ownerPrecinctList.add(ownerHouseBaseInfo);
                        }
                    }
                }
                item.setPrecinctList(ownerPrecinctList);
            }
            // 获取客户家庭成员
            // List<OwnerCustomerFamilyInfo> familyList = ownerCustomerFamilyInfoMapper.listAllFamily(ownerIdList);
            // if (!CollectionUtils.isEmpty(familyList)) {
            // List<Long> familyIdList = new ArrayList<>();
            // familyList.forEach(family->{
            // familyIdList.add(family.getOwnerId());
            // });
            // if (!CollectionUtils.isEmpty(familyIdList)) {
            // map.clear();
            // map.put("searchVo", searchVo);
            // map.put("ownerIdList", familyIdList);
            // List<OwnerCustomerResult> familyResultList = customerResultMapper.listResultBySearch(map);
            // if (!CollectionUtils.isEmpty(familyResultList)) {
            // list.addAll(familyResultList);
            // }
            // }
            // }
            if (!CollectionUtils.isEmpty(ownerIdList)) {
                map.clear();
                map.put("list", ownerIdList);
                // 获取客户查询结果中的客户的主房产
                List<Long> ownerPrecinctList = ownerHouseRelationshipMapper.listPrecinctForSearchCustomer(ownerIdList);
                List<Long> rentPrecinctList = ownerHouseRelationshipMapper.listRentPrecinctForSearchCustomer(ownerIdList);
                List<OwnerCustomerMainHouse> mainHouses = mainHouseMapper.listByOwnerHouse(map);
                List<Long> houseIdList = new ArrayList<>();

                if (!CollectionUtils.isEmpty(mainHouses)) {
                    mainHouses.forEach(mainHouse -> {
                        houseIdList.add(mainHouse.getHouseId());
                    });
                    List<OwnerHouseBaseInfo> houseList = new ArrayList<>();
                    if (CollectionUtils.isEmpty(houseIdList)) {
                        houseList = houseBaseMapper.listOwnerHouseBaseInfoByHouseIdList(houseIdList);
                    }
                    for (OwnerCustomerResult ownerCustomerResult : list) {
                        List<OwnerHouseBaseInfo> precinctBaseList = new ArrayList<>();
                        List<OwnerHouseBaseInfo> mainHouseList = new ArrayList<>();
                        for (Long houseId : ownerPrecinctList) {
                            OwnerHouseBaseInfo house = houseBaseMapper.selectByPrimaryKey(houseId);
                            precinctBaseList.add(house);
                        }
                        for (Long houseId : rentPrecinctList) {
                            OwnerHouseBaseInfo house = houseBaseMapper.selectByPrimaryKey(houseId);
                            precinctBaseList.add(house);
                        }
                        ownerCustomerResult.setPrecinctList(precinctBaseList);
                        for (OwnerCustomerMainHouse ownerCustomerMainHouse : mainHouses) {
                            if (ownerCustomerMainHouse.getOwnerId().equals(ownerCustomerResult.getOwnerId()) && !CollectionUtils.isEmpty(houseList)) {
                                for (OwnerHouseBaseInfo house : houseList) {
                                    if (ownerCustomerMainHouse.getHouseId().equals(house.getHouseId())) {
                                        mainHouseList.add(house);
                                    }
                                }
                            }
                        }
                        ownerCustomerResult.setMainHouseList(mainHouseList);
                    }
                }
            }

            // 统计
            // 获取需要统计的字段
            if (columnMap != null) {
                List<NsCoreResourcecolumnVo> columnList = (List<NsCoreResourcecolumnVo>) columnMap.get("columns");
                columnList = CommonUtils.getTotalColumn(columnList);
                if (!CollectionUtils.isEmpty(columnList)) {
                    String pageJson = "";
                    // 统计本页
                    pageJson = JSONObject.toJSONString(list);
                    String pageTotalJson = CommonUtils.totalList(pageJson, columnList, OwnerCustomerResult.class);
                    if (StringUtils.hasLength(pageTotalJson)) {
                        OwnerCustomerResult total = JSONObject.parseObject(pageTotalJson, OwnerCustomerResult.class);
                        list.add(total);
                    }
                }
            }
        }

        return pageInfo;
    }

    private Map<String, Object> getSearchCondition(SearchVo searchVo) {
        Map<String, Object> map = new HashMap<>();
        SearchVo searchHouseVo = new SearchVo();
        searchHouseVo.setFilterList(new ArrayList<FilterEntity>());
        // List<Long> precinctIdList = new ArrayList<>();
        List<String> precinctListForNoHouse = new ArrayList<>();
        FilterEntity searchFilter = new FilterEntity();
        FilterEntity filterEntity = null;
        OwnerHouseBaseInfo searchBase = null;
        // 房产树查询条件处理
        if (!CollectionUtils.isEmpty(searchVo.getTreeConditions())) {
            filterEntity = searchVo.getTreeConditions().get(0);
            searchFilter = new FilterEntity();
            if ("0".equals(filterEntity.getFieldValue())) {
                searchFilter.setComparison(Constants.COMPARISON_LIKE);
                searchFilter.setFieldName("path");
                searchFilter.setFieldValue(Constants.SEPARATOR_PATH);
                // TODO 数据权限
            } else {
                searchBase = houseBaseMapper.selectByPrimaryKey(Long.valueOf(filterEntity.getFieldValue()));
                if (searchBase == null) {
                    BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "该节点不存在");
                }
                if (HouseTypeEnum.ROOM.getValue().equals(searchBase.getHouseType()) || HouseTypeEnum.CARPORT.getValue().equals(searchBase.getHouseType())
                        || HouseTypeEnum.PUBLICAREA.getValue().equals(searchBase.getHouseType())) {
                    searchFilter.setComparison(Constants.COMPARISON_EQUAL);
                    searchFilter.setFieldName("houseId");
                    searchFilter.setFieldValue(searchBase.getHouseId().toString());
                    precinctListForNoHouse.add(searchBase.getPrecinctId().toString());
                } else {
                    if (HouseTypeEnum.PRECINCT.getValue().equals(searchBase.getHouseType())) {
                        // precinctIdList.add(searchBase.getHouseId());
                        precinctListForNoHouse.add(searchBase.getHouseId().toString());

                    } else if (HouseTypeEnum.AREA.getValue().equals(searchBase.getHouseType())) {
                        List<OwnerHouseBaseInfo> baseInfos = houseBaseMapper.listOwnerHouseBaseInfoByPath(searchBase.getPath() + searchBase.getHouseId() + Constants.SEPARATOR_PATH);
                        if (CollectionUtils.isEmpty(baseInfos)) {
                            return null;
                        } else {
                            baseInfos.forEach(base -> {
                                if (HouseTypeEnum.PRECINCT.getValue().equals(base.getHouseType())) {
                                    precinctListForNoHouse.add(base.getHouseId().toString());
                                }
                            });
                        }
                    }
                    searchFilter.setComparison(Constants.COMPARISON_LIKE);
                    searchFilter.setFieldName("path");
                    searchFilter.setFieldValue(searchBase.getPath() + searchBase.getHouseId() + Constants.SEPARATOR_PATH);
                }
            }

        }
        searchHouseVo.getFilterList().add(searchFilter);
        List<FilterEntity> precinctFilterList = new ArrayList<>();
        List<FilterEntity> mainHouseFilterList = new ArrayList<>();

        List<Long> searchOwnerIdList = new ArrayList<>();
        List<OwnerHouseBaseInfo> houseResultList = new ArrayList<>();
        map.put("searchVo", searchHouseVo);
        houseResultList = houseBaseMapper.listForSearchCustomer(map);
        // 1、房产树选择根节点、区域时，如果没有子节点，列表为空
        // 2、选择项目时，列表显示子节点关联的业主、租户、住户
        // 3、选择其他节点时，列表显示子节点关联的业主、租户、住户、以及节点所属项目的无房客户
        // if (CollectionUtils.isEmpty(houseResultList)) {
        // BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "无房产数据");
        // }
        // 筛选器-根据项目名称，主房产名称进行查询处理
        if (!CollectionUtils.isEmpty(searchVo.getFilterList()) && filterEntity != null) {
            for (FilterEntity searchFilterEntity : searchVo.getFilterList()) {
                if ("precinctName".equals(filterEntity.getFieldName())) {
                    // 按项目查询
                    FilterEntity precinctFilter = new FilterEntity();
                    precinctFilter.setFieldName("houseName");
                    precinctFilter.setComparison(searchFilterEntity.getComparison());
                    precinctFilter.setFieldValue(searchFilterEntity.getFieldValue());
                    precinctFilterList.add(precinctFilter);
                }
                if ("mainHouseList".equals(searchFilterEntity.getFieldName())) {
                    // 按主房产查询
                    FilterEntity mainHouseFilter = new FilterEntity();
                    mainHouseFilter.setFieldName("houseFullName");
                    mainHouseFilter.setComparison(searchFilterEntity.getComparison());
                    mainHouseFilter.setFieldValue(searchFilterEntity.getFieldValue());
                    mainHouseFilterList.add(mainHouseFilter);
                }
            }
            searchVo.getFilterList().removeIf(searchFilterEntity -> "precinctName".equals(searchFilterEntity.getFieldName()));
            searchVo.getFilterList().removeIf(searchFilterEntity -> "mainHouseName".equals(searchFilterEntity.getFieldName()));
        }

        List<Long> houseIdList = new ArrayList<>();
        List<Long> precinctIdList = new ArrayList<>();
        if (searchBase != null && !HouseTypeEnum.AREA.getValue().equals(searchBase.getHouseType()) && !HouseTypeEnum.PRECINCT.getValue().equals(searchBase.getHouseType())) {
            houseResultList.forEach(house -> {
                precinctIdList.add(house.getPrecinctId());
                houseIdList.add(house.getHouseId());
            });
            if (!CollectionUtils.isEmpty(precinctIdList)) {
                CommonUtils.removeDuplicate(precinctIdList);
                List<OwnerHouseBaseInfo> precinctList = houseBaseMapper.listOwnerHouseBaseInfoByHouseIdList(precinctIdList);
                if (!CollectionUtils.isEmpty(precinctFilterList)) {
                    for (FilterEntity precinctFilter : precinctFilterList) {
                        precinctList.removeIf(precinct -> !precinct.getHouseName().contains(precinctFilter.getFieldValue()));
                    }
                }
                if (!CollectionUtils.isEmpty(precinctList)) {
                    precinctIdList.clear();
                    precinctList.forEach(precinct -> {
                        precinctIdList.add(precinct.getHouseId());
                    });
                }
                Map<String, Object> searchOwnerMap = new HashMap<>();
                if (!CollectionUtils.isEmpty(precinctIdList)) {
                    searchOwnerMap.put("precinctIdList", precinctIdList);
                }
                if (!CollectionUtils.isEmpty(houseIdList)) {
                    searchOwnerMap.put("houseIdList", houseIdList);
                    List<Long> ownerIdList = ownerHouseRelationshipMapper.listOwnerForSearchCustomerBySearchVo(searchOwnerMap);
                    searchOwnerIdList.addAll(ownerIdList);
                    List<Long> rentIdList = ownerHouseRelationshipMapper.listRentForSearchCustomerBySearchVo(searchOwnerMap);
                    searchOwnerIdList.addAll(rentIdList);
                }
            }
        }

        if (!CollectionUtils.isEmpty(mainHouseFilterList)) {
            SearchVo searchMainHouse = new SearchVo();
            searchMainHouse.setEnterpriseId(searchVo.getEnterpriseId());
            searchMainHouse.setOrganizationId(searchVo.getOrganizationId());
            searchMainHouse.setFilterList(mainHouseFilterList);
            searchMainHouse.getFilterList().addAll(searchVo.getTreeConditions());
            List<Long> ownerIdList = mainHouseMapper.listForSearchCustomer(searchMainHouse);
            searchOwnerIdList.addAll(ownerIdList);
        }
        map.clear();
        map.put("searchVo", searchVo);
        if (!CollectionUtils.isEmpty(precinctListForNoHouse)) {
            CommonUtils.removeDuplicate(precinctListForNoHouse);
            map.put("precinctListForNoHouse", precinctListForNoHouse);
        }
        if (!CollectionUtils.isEmpty(searchOwnerIdList)) {
            map.put("ownerIdList", searchOwnerIdList);
        } else {
            if (searchBase != null) {
                if (HouseTypeEnum.ROOM.getValue().equals(searchBase.getHouseType()) || HouseTypeEnum.CARPORT.getValue().equals(searchBase.getHouseType())
                        || HouseTypeEnum.PUBLICAREA.getValue().equals(searchBase.getHouseType())) {
                    return null;
                }
            }
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    @ReadDataSource
    public OwnerCustomerResult getTotal(SearchVo searchVo, Map<String, Object> columnMap) throws Exception {
        OwnerCustomerResult total = null;
        // 统计
        // 获取需要统计的字段
        if (columnMap != null) {
            List<NsCoreResourcecolumnVo> columnList = (List<NsCoreResourcecolumnVo>) columnMap.get("columns");
            columnList = CommonUtils.getTotalColumn(columnList);
            if (!CollectionUtils.isEmpty(columnList)) {
                String AllJson = "";
                Map<String, Object> map = getSearchCondition(searchVo);
                List<OwnerCustomerResult> resultVoListForTotal = null;
                resultVoListForTotal = customerResultMapper.listResultBySearch(map);
                if (!CollectionUtils.isEmpty(resultVoListForTotal)) {
                    AllJson = JSONObject.toJSONString(resultVoListForTotal);
                    String allTotalJson = CommonUtils.totalList(AllJson, columnList, OwnerCustomerResult.class);
                    if (StringUtils.hasLength(allTotalJson)) {
                        total = JSONObject.parseObject(allTotalJson, OwnerCustomerResult.class);
                    }
                }
            }
        }
        return total;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Long add(CustomerVo customerVo, Map<String, NsCoreDictionaryVo> dicMap) {

        // 客户所属项目
        if (CommonUtils.isObjectEmpty(customerVo.getPrecinctId())) {
            BizException.fail(ResultCodeEnum.PARAMS_MISSING, "请选择客户所属项目");
        }
        OwnerHouseBaseInfo houseBaseInfo = houseBaseMapper.selectByPrimaryKey(Long.valueOf(customerVo.getPrecinctId()));

        HouseTypeEnum houseTypeEnum = HouseTypeEnum.getInstance(houseBaseInfo.getHouseType());
        switch (houseTypeEnum) {
            case PRECINCT:
                customerVo.setPrecinctId(Constants.SEPARATOR_PATH + customerVo.getPrecinctId() + Constants.SEPARATOR_PATH);
                break;
            case CLUSTER:
            case UNIT:
            case BUILDING:
            case GARAGE:
            case PUBLICAREA:
            case ROOM:
            case CARPORT:
                customerVo.setPrecinctId(Constants.SEPARATOR_PATH + houseBaseInfo.getPrecinctId().toString() + Constants.SEPARATOR_PATH);
                break;
            default:
                BizException.fail(ResultCodeEnum.PARAMS_MISSING, "请选择客户所属项目");
                break;
        }
        if (customerVo.getProvinceCityArea() != null) {
            customerVo.setProvinceId(customerVo.getProvinceCityArea().getProvince());
            customerVo.setCityId(customerVo.getProvinceCityArea().getCity());
            customerVo.setAreaId(customerVo.getProvinceCityArea().getDistrict());
            customerVo.setStreetId(customerVo.getProvinceCityArea().getStreet());
        }
        OwnerCustomerBaseInfo baseInfo = new OwnerCustomerBaseInfo();
        BeanUtils.copyProperties(customerVo, baseInfo);
        baseInfo.setCreateUserId(customerVo.getHandlerId());
        baseInfo.setCreateUserName(customerVo.getCreateUserName());
        baseInfo.setUpdateUserId(customerVo.getHandlerId());
        baseInfo.setIsDeleted(Constants.DELETE_NO);
        if (CommonUtils.isObjectEmpty(baseInfo.getOwnerProperty())) {
            baseInfo.setOwnerProperty(Constants.OWNER_PROPERTY_NONE);
        }
        // 验证客户唯一性
        Map<String, Object> checkOnlyMap = new HashMap<>();
        checkOnlyMap.put("ownerName", customerVo.getOwnerName());
        checkOnlyMap.put("organizationId", customerVo.getOrganizationId());
        checkOnlyMap.put("certificate", customerVo.getCertificate());
        checkOnlyMap.put("mobile", customerVo.getMobile());
        CustomerVo checkOnlyVo = ownerCustomerBaseInfoMapper.loadCustomerByName(checkOnlyMap);
        if (checkOnlyVo != null) {
            BizException.fail(ResultCodeEnum.DATA_EXIST, "客户已存在");
        }
        // 新增客户基础信息
        int index = ownerCustomerBaseInfoMapper.insert(baseInfo);
        customerVo.setOwnerId(baseInfo.getOwnerId());
        if (index > 0) {
            // 新增客户扩展信息
            if (customerVo.getBirthday() != null) {
                int age = CommonUtils.getAgeByBirth(customerVo.getBirthday());
                customerVo.setAge(age);
            }
            if (!CollectionUtils.isEmpty(customerVo.getInterestsIdList())) {
                String interestsIds = String.join(",", customerVo.getInterestsIdList());
                customerVo.setInterestsIds(interestsIds);
                // for (String interest : customerVo.getInterestsIdList()) {
                // interestsIds = interestsIds + interest + ",";
                // }
                // if(!CommonUtils.isNullOrBlank(interestsIds)){
                // customerVo.setInterestsIds(interestsIds.substring(0,interestsIds.length()-1));
                // }
            } else {
                customerVo.setInterestsIds("");
            }
            if (!CollectionUtils.isEmpty(customerVo.getPictures())) {
                customerVo.setPicUrl(customerVo.getPictures().get(0).getFileUrl());
            }
            OwnerCustomerInfo customerInfo = new OwnerCustomerInfo();
            BeanUtils.copyProperties(customerVo, customerInfo);
            customerInfo.setCreateUserId(customerVo.getHandlerId());
            customerInfo.setUpdateUserId(customerVo.getHandlerId());
            customerInfo.setIsDeleted(Constants.DELETE_NO);

            ownerCustomerInfoMapper.insert(customerInfo);
            // String mainHouseName = "";
            // String precinctName = "";
            // TODO 同步客户结果表
            // String ownerTypeName = OwnerUtils.getDicName(dicMap.get("ownerTypeDic"), customerVo.getOwnerType());
            // customerVo.setOwnerTypeName(ownerTypeName);
            // String ownerPropertyName = OwnerUtils.getDicName(dicMap.get("ownerPropertyDic"), customerVo.getOwnerProperty());
            // customerVo.setOwnerPropertyName(ownerPropertyName);
            // String ownerLevelName = OwnerUtils.getDicName(dicMap.get("ownerLevelDic"), customerVo.getOwnerLevel());
            // customerVo.setOwnerLevelName(ownerLevelName);
            // String certificateTypeName = OwnerUtils.getDicName(dicMap.get("certificateTypeDic"), customerVo.getCertificateType());
            // customerVo.setCertificateTypeName(certificateTypeName);
            // String maritalStatusName = OwnerUtils.getDicName(dicMap.get("maritalStatusDic"), customerVo.getMaritalStatus());
            // customerVo.setMaritalStatusName(maritalStatusName);
            // String educationName = OwnerUtils.getDicName(dicMap.get("educationDic"), customerVo.getEducation());
            // customerVo.setEducationName(educationName);
            // String regionName = OwnerUtils.getDicName(dicMap.get("regionDic"), customerVo.getRegion());
            // customerVo.setRegionName(regionName);
            // String nationName = OwnerUtils.getDicName(dicMap.get("nationDic"), customerVo.getNation());
            // customerVo.setNationName(nationName);
            // String tradeName = OwnerUtils.getDicName(dicMap.get("tradeDic"), customerVo.getNation());
            // customerVo.setTradeName(tradeName);
            // String interestsNames = OwnerUtils.getDicArrayName(dicMap.get("interestDic"), customerVo.getInterestsIds());
            // customerVo.setInterestsNames(interestsNames);
            // String genderName = OwnerUtils.getDicName(dicMap.get("genderDic"), customerVo.getGender());
            // customerVo.setGenderName(genderName);
            OwnerCustomerResult ownerCustomerResult = new OwnerCustomerResult();
            BeanUtils.copyProperties(customerVo, ownerCustomerResult);
            if (Constants.OWNER_TYPE_ENTERPRISE.equals(customerVo.getOwnerType())) {
                ownerCustomerResult.setMobile(customerVo.getLinkmanPhone());
            }
            ownerCustomerResult.setUpdateUserId(customerVo.getHandlerId());
            ownerCustomerResult.setUpdateTime(new Date());
            // ownerCustomerResult.setMainHouseName(mainHouseName);
            // ownerCustomerResult.setPrecinctName(precinctName);
            customerResultMapper.insertSelective(ownerCustomerResult);
        }
        return baseInfo.getOwnerId();
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int edit(CustomerVo customerVo, Map<String, NsCoreDictionaryVo> dicMap) {
        if (customerVo.getProvinceCityArea() != null) {
            customerVo.setProvinceId(customerVo.getProvinceCityArea().getProvince());
            customerVo.setCityId(customerVo.getProvinceCityArea().getCity());
            customerVo.setAreaId(customerVo.getProvinceCityArea().getDistrict());
            customerVo.setStreetId(customerVo.getProvinceCityArea().getStreet());
        }
        if (StringUtils.hasLength(customerVo.getPrecinctId())) {
            List<Long> precinctIdList = StringUtils.handlerPath2List(customerVo.getPrecinctId());
            if (!CollectionUtils.isEmpty(precinctIdList)) {
                CommonUtils.removeDuplicate(precinctIdList);
                String precinctIdStr = "";
                for (Long precinctId : precinctIdList) {
                    precinctIdStr = precinctId + Constants.SEPARATOR_PATH;
                }
                customerVo.setPrecinctId(Constants.SEPARATOR_PATH + precinctIdStr);
            }
        }
        OwnerCustomerBaseInfo baseInfo = new OwnerCustomerBaseInfo();
        BeanUtils.copyProperties(customerVo, baseInfo);

        baseInfo.setCreateUserId(customerVo.getHandlerId());
        baseInfo.setUpdateUserId(customerVo.getHandlerId());
        baseInfo.setIsDeleted(Constants.DELETE_NO);
        // 验证客户唯一性
        Map<String, Object> checkOnlyMap = new HashMap<>();
        checkOnlyMap.put("ownerName", customerVo.getOwnerName());
        checkOnlyMap.put("organizationId", customerVo.getOrganizationId());
        checkOnlyMap.put("certificate", customerVo.getCertificate());
        checkOnlyMap.put("mobile", customerVo.getMobile());
        checkOnlyMap.put("ownerId", customerVo.getOwnerId());
        CustomerVo checkOnlyVo = ownerCustomerBaseInfoMapper.loadCustomerByName(checkOnlyMap);
        if (checkOnlyVo != null) {
            BizException.fail(ResultCodeEnum.DATA_EXIST, "客户已存在");
        }
        // 更新客户基础信息
        int index = ownerCustomerBaseInfoMapper.updateByPrimary(baseInfo);
        if (index > 0) {
            // 更新客户扩展信息
            if (customerVo.getBirthday() != null) {
                int age = CommonUtils.getAgeByBirth(customerVo.getBirthday());
                customerVo.setAge(age);
            }
            if (!CollectionUtils.isEmpty(customerVo.getInterestsIdList())) {
                String interestsIds = String.join(",", customerVo.getInterestsIdList());
                customerVo.setInterestsIds(interestsIds);
                // for (String interest : customerVo.getInterestsIdList()) {
                // interestsIds = interestsIds + interest + ",";
                // }
                // if(!CommonUtils.isNullOrBlank(interestsIds)){
                // customerVo.setInterestsIds(interestsIds.substring(0,interestsIds.length()-1));
                // }
            } else {
                customerVo.setInterestsIds("");
            }
            if (!CollectionUtils.isEmpty(customerVo.getPictures())) {
                customerVo.setPicUrl(customerVo.getPictures().get(0).getFileUrl());
            }
            OwnerCustomerInfo customerInfo = new OwnerCustomerInfo();
            BeanUtils.copyProperties(customerVo, customerInfo);
            customerInfo.setCreateUserId(customerVo.getHandlerId());
            customerInfo.setUpdateUserId(customerVo.getHandlerId());
            customerInfo.setIsDeleted(Constants.DELETE_NO);

            ownerCustomerInfoMapper.updateByPrimary(customerInfo);
            // String mainHouseName = "";
            // String precinctName = "";
            // 保存客户主房产
            if (!Constants.OWNER_PROPERTY_HOUSEHOLD.equals(customerVo.getOwnerProperty())) {
                List<MainHouseVo> relationshipVos = customerVo.getMainHouseList();
                List<OwnerHouseRelationshipVo> relationshipList = new ArrayList<>();
                List<OwnerHouseRelationshipVo> ownerHouseList = ownerHouseRelationshipMapper.listOwnerAllHouseRelation(customerVo.getOwnerId());
                List<OwnerHouseRelationshipVo> rentHouseList = ownerHouseRelationshipMapper.listRentAllHouseRelation(customerVo.getOwnerId());
                relationshipList.addAll(ownerHouseList);
                relationshipList.addAll(rentHouseList);
                List<OwnerCustomerMainHouse> mainHouseList = new ArrayList<>();
                Map<String, Object> map = new HashMap<>();
                List<Long> ownerIdList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(relationshipList)) {
                    if (!CollectionUtils.isEmpty(relationshipVos)) {
                        // 判断客户在该项目中的主房产
                        ownerIdList.add(customerVo.getOwnerId());
                        map.put("ownerIdList", ownerIdList);
                        for (OwnerHouseRelationshipVo ownerHouseRelationshipVo : relationshipList) {
                            for (MainHouseVo mainHouseVo : customerVo.getMainHouseList()) {
                                if (!CommonUtils.isObjectEmpty(mainHouseVo.getOwnerHouse()) && ownerHouseRelationshipVo.getHouseId().equals(Long.valueOf(mainHouseVo.getOwnerHouse()))) {
                                    map.put("precinctId", ownerHouseRelationshipVo.getPrecinctId());
                                    OwnerCustomerMainHouse mainHouse = new OwnerCustomerMainHouse();
                                    BeanUtils.copyProperties(ownerHouseRelationshipVo, mainHouse);
                                    mainHouseList.add(mainHouse);
                                }
                            }
                        }
                        // 拼接客户主房产名称,项目名称
                        // StringBuffer firstHouseBuffer = new StringBuffer();
                        // StringBuffer precinctBuffer = new StringBuffer();
                        // mainHouseList.forEach(relationshipVo -> {
                        // firstHouseBuffer.append(relationshipVo.getHouseFullName()+",");
                        // precinctBuffer.append(relationshipVo.getPrecinctName()+",");
                        // });
                        // mainHouseName = firstHouseBuffer.substring(0, firstHouseBuffer.length()-1);
                        // precinctName = precinctBuffer.substring(0, precinctBuffer.length()-1);
                        // 保存主房产
                        if (!CollectionUtils.isEmpty(mainHouseList) && !CollectionUtils.isEmpty(ownerIdList)) {
                            mainHouseMapper.deleteMainHouse(map);
                            mainHouseMapper.insertBatch(mainHouseList);
                        }
                    }
                }
            }

            // 同步客户结果表
            // String ownerTypeName = OwnerUtils.getDicName(dicMap.get("ownerTypeDic"), customerVo.getOwnerType());
            // customerVo.setOwnerTypeName(ownerTypeName);
            // String ownerPropertyName = OwnerUtils.getDicName(dicMap.get("ownerPropertyDic"), customerVo.getOwnerProperty());
            // customerVo.setOwnerPropertyName(ownerPropertyName);
            // String ownerLevelName = OwnerUtils.getDicName(dicMap.get("ownerLevelDic"), customerVo.getOwnerLevel());
            // customerVo.setOwnerLevelName(ownerLevelName);
            // String certificateTypeName = OwnerUtils.getDicName(dicMap.get("certificateTypeDic"), customerVo.getCertificateType());
            // customerVo.setCertificateTypeName(certificateTypeName);
            // String maritalStatusName = OwnerUtils.getDicName(dicMap.get("maritalStatusDic"), customerVo.getMaritalStatus());
            // customerVo.setMaritalStatusName(maritalStatusName);
            // String educationName = OwnerUtils.getDicName(dicMap.get("educationDic"), customerVo.getEducation());
            // customerVo.setEducationName(educationName);
            // String regionName = OwnerUtils.getDicName(dicMap.get("regionDic"), customerVo.getRegion());
            // customerVo.setRegionName(regionName);
            // String nationName = OwnerUtils.getDicName(dicMap.get("nationDic"), customerVo.getNation());
            // customerVo.setNationName(nationName);
            // String tradeName = OwnerUtils.getDicName(dicMap.get("tradeDic"), customerVo.getNation());
            // customerVo.setTradeName(tradeName);
            // String interestsNames = OwnerUtils.getDicArrayName(dicMap.get("interestDic"), customerVo.getInterestsIds());
            // customerVo.setInterestsNames(interestsNames);
            // String genderName = OwnerUtils.getDicName(dicMap.get("genderDic"), customerVo.getGender());
            // customerVo.setGenderName(genderName);
            OwnerCustomerResult ownerCustomerResult = new OwnerCustomerResult();
            BeanUtils.copyProperties(customerVo, ownerCustomerResult);
            if (Constants.OWNER_TYPE_ENTERPRISE.equals(customerVo.getOwnerType())) {
                ownerCustomerResult.setMobile(customerVo.getLinkmanPhone());
            }
            ownerCustomerResult.setUpdateUserId(customerVo.getHandlerId());
            ownerCustomerResult.setUpdateTime(new Date());
            // ownerCustomerResult.setMainHouseName(mainHouseName);
            // ownerCustomerResult.setPrecinctName(precinctName);
            customerResultMapper.updateByPrimaryKey(ownerCustomerResult);
        }
        return index;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int delete(List<CustomerVo> customerVoList) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public CustomerVo detail(Long ownerId) {
        CustomerVo customerVo = ownerCustomerBaseInfoMapper.loadCustomer(ownerId);
        if (customerVo != null) {
            ProvinceCityArea provinceCityArea = new ProvinceCityArea();
            provinceCityArea.setProvince(customerVo.getProvinceId());
            provinceCityArea.setCity(customerVo.getCityId());
            provinceCityArea.setDistrict(customerVo.getAreaId());
            provinceCityArea.setStreet(customerVo.getStreetId());
            customerVo.setProvinceCityArea(provinceCityArea);

            String insterestIds = customerVo.getInterestsIds();
            if (!CommonUtils.isNullOrBlank(insterestIds)) {
                String[] valueArray = StringUtils.delimitedListToStringArray(insterestIds, ",");
                List<String> valueStrList = Arrays.asList(valueArray);
                customerVo.setInterestsIdList(valueStrList);
            }

            if (StringUtils.hasLength(customerVo.getPicUrl())) {
                List<FileVo> fileVos = new ArrayList<>();
                FileVo fileVo = new FileVo();
                fileVo.setFileUrl(customerVo.getPicUrl());
                fileVos.add(fileVo);
                customerVo.setPictures(fileVos);
            }

            if (customerVo.getBirthday() != null) {
                int age = CommonUtils.getAgeByBirth(customerVo.getBirthday());
                customerVo.setAge(age);
            }
        }
        return customerVo;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int addFamily(CustomerFamilyVo customerFamilyVo, Map<String, NsCoreDictionaryVo> dicMap) throws Exception {
        // 保存客户信息
        // CustomerVo customerVo = null;
        int index = 0;
        // if (!CommonUtils.isObjectEmpty(customerFamilyVo.getRelationOwnerId())) {
        // customerVo = detail(customerFamilyVo.getRelationOwnerId());
        // }
        // if (customerVo != null) {
        // if (Constants.OWNER_TYPE_PERSON.equals(customerVo.getOwnerType())) {
        // customerFamilyVo.getCustomerVo().setOwnerType(Constants.OWNER_TYPE_PERSON);
        // }else {
        // if (!CustomerRelationEnum.ZIGONGSI.getValue().equals(customerFamilyVo.getOwnerRelationship())) {
        // customerFamilyVo.getCustomerVo().setOwnerType(Constants.OWNER_TYPE_PERSON);
        // }else {
        // customerFamilyVo.getCustomerVo().setOwnerType(Constants.OWNER_TYPE_ENTERPRISE);
        // }
        // }
        // if (owner == null) {
        // add(customerFamilyVo.getCustomerVo(), dicMap);
        // }else {
        // if (StringUtils.hasLength(owner.getOwnerProperty())) {
        // if (!Constants.OWNER_PROPERTY_OWNER.equals(owner.getOwnerProperty()) &&
        // !Constants.OWNER_PROPERTY_RENT.equals(owner.getOwnerProperty()) &&
        // !Constants.OWNER_PROPERTY_DEVOLOPER.equals(owner.getOwnerProperty())) {
        // customerFamilyVo.getCustomerVo().setOwnerProperty(Constants.OWNER_PROPERTY_HOUSEHOLD);
        // }
        // }
        // customerFamilyVo.getCustomerVo().setOwnerId(customerFamilyVo.getOwnerId());
        // edit(customerFamilyVo.getCustomerVo(), dicMap);
        // }
        // 保存成员关系
        CustomerVo family = detail(customerFamilyVo.getOwnerId());
        CustomerVo owner = detail(customerFamilyVo.getRelationOwnerId());

        OwnerCustomerFamilyInfoKey key = new OwnerCustomerFamilyInfoKey();
        key.setOwnerId(customerFamilyVo.getOwnerId());
        key.setRelationOwnerId(customerFamilyVo.getRelationOwnerId());
        OwnerCustomerFamilyInfo familyInfo = ownerCustomerFamilyInfoMapper.selectByPrimaryKey(key);
        if (CommonUtils.isObjectEmpty(customerFamilyVo.getFamilyId()) && familyInfo != null) {
            BizException.fail(ResultCodeEnum.DATA_EXIST, "家庭成员已存在");
        } else {
            familyInfo = new OwnerCustomerFamilyInfo();
        }
        BeanUtils.copyProperties(customerFamilyVo, familyInfo);
        familyInfo.setUpdateUserId(customerFamilyVo.getHandlerId());
        customerFamilyVo.setCustomerVo(family);
        CustomerCallEnum customerCallEnum = OwnerUtils.getCallForFamily(customerFamilyVo);
        if (customerCallEnum != null) {
            familyInfo.setCreateUserId(customerFamilyVo.getHandlerId());
            familyInfo.setRelationOwnerCall(customerCallEnum.getValue());
        }
        if (CommonUtils.isObjectEmpty(customerFamilyVo.getFamilyId())) {
            index = ownerCustomerFamilyInfoMapper.insertSelective(familyInfo);
        } else {
            index = ownerCustomerFamilyInfoMapper.updateByFamilyId(familyInfo);
        }
        if (index > 0) {
            OwnerCustomerResult customerResult = new OwnerCustomerResult();
            customerResult.setOwnerId(customerFamilyVo.getRelationOwnerId());
            customerResult.setHasFamily(Constants.TRUE.toString());
            customerResult.setUpdateUserId(customerFamilyVo.getHandlerId());
            customerResultMapper.updateByPrimaryKeySelective(customerResult);

            // 增加家庭成员与业主的关系
            OwnerCustomerFamilyInfoKey contraryKey = new OwnerCustomerFamilyInfoKey();
            contraryKey.setOwnerId(customerFamilyVo.getRelationOwnerId());
            contraryKey.setRelationOwnerId(customerFamilyVo.getOwnerId());
            OwnerCustomerFamilyInfo contraryInfo = ownerCustomerFamilyInfoMapper.selectByPrimaryKey(contraryKey);
            int contraryIndex = 0;
            if (contraryInfo == null) {
                customerFamilyVo.setOwnerId(contraryKey.getOwnerId());
                customerFamilyVo.setRelationOwnerId(contraryKey.getRelationOwnerId());
                contraryInfo = new OwnerCustomerFamilyInfo();
                BeanUtils.copyProperties(customerFamilyVo, contraryInfo);
                contraryInfo.setUpdateUserId(customerFamilyVo.getHandlerId());
                CustomerRelationEnum contraryRelationEnum = OwnerUtils.getContraryForFamily(customerFamilyVo);
                customerFamilyVo.setOwnerRelationship(contraryRelationEnum.getValue());
                contraryInfo.setOwnerRelationship(contraryRelationEnum.getValue());
                customerFamilyVo.setCustomerVo(owner);
                CustomerCallEnum contraryCallEnum = OwnerUtils.getCallForFamily(customerFamilyVo);
                if (contraryCallEnum != null) {
                    contraryInfo.setCreateUserId(customerFamilyVo.getHandlerId());
                    contraryInfo.setRelationOwnerCall(contraryCallEnum.getValue());
                }
                contraryIndex = ownerCustomerFamilyInfoMapper.insertSelective(contraryInfo);
            } else {
                contraryInfo.setUpdateUserId(customerFamilyVo.getHandlerId());
                CustomerRelationEnum contraryRelationEnum = OwnerUtils.getContraryForFamily(customerFamilyVo);
                customerFamilyVo.setOwnerRelationship(contraryRelationEnum.getValue());
                contraryInfo.setOwnerRelationship(contraryRelationEnum.getValue());
                customerFamilyVo.setCustomerVo(owner);
                CustomerCallEnum contraryCallEnum = OwnerUtils.getCallForFamily(customerFamilyVo);
                if (contraryCallEnum != null) {
                    contraryInfo.setCreateUserId(customerFamilyVo.getHandlerId());
                    contraryInfo.setRelationOwnerCall(contraryCallEnum.getValue());
                }
                contraryIndex = ownerCustomerFamilyInfoMapper.updateByFamilyId(contraryInfo);
            }
            if (contraryIndex > 0) {
                OwnerCustomerResult contraryCustomerResult = new OwnerCustomerResult();
                contraryCustomerResult.setOwnerId(customerFamilyVo.getOwnerId());
                contraryCustomerResult.setHasFamily(Constants.TRUE.toString());
                contraryCustomerResult.setUpdateUserId(customerFamilyVo.getHandlerId());
                customerResultMapper.updateByPrimaryKeySelective(contraryCustomerResult);
            }
        }
        // }
        return index;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, Object> deleteAllCustomerBySearch(SearchVo searchVo, Long userId, String userName) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<OwnerCustomerResult> customerList = listPage(searchVo, null, false).getList();
        List<Long> customerIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(customerList)) {
            for (OwnerCustomerResult customerResult : customerList) {
                customerIdList.add(customerResult.getOwnerId());
            }
        } else {
            map.put("result", false);
            map.put("message", "请选择要删除的客户");
            return map;
        }
        int index = 0;
        List<Long> deleteFailList = new ArrayList<>();
        List<Long> deleteList = new ArrayList<>();
        Map<String, Object> relationMap = new HashMap<>();
        relationMap.put("list", customerIdList);
        List<OwnerHouseRelationshipVo> ownerList = ownerHouseRelationshipMapper.listOwnerAllHouseRelationByList(relationMap);
        List<OwnerHouseRelationshipVo> rentList = ownerHouseRelationshipMapper.listRentAllHouseRelationByList(relationMap);
        if (!CollectionUtils.isEmpty(ownerList)) {
            for (OwnerHouseRelationshipVo ownerHouseRelationshipVo : ownerList) {
                if (customerIdList.contains(ownerHouseRelationshipVo.getOwnerId())) {
                    deleteFailList.add(ownerHouseRelationshipVo.getOwnerId());
                    customerIdList.removeIf(id -> id.equals(ownerHouseRelationshipVo.getOwnerId()));
                }
            }
        }
        if (!CollectionUtils.isEmpty(rentList)) {
            for (OwnerHouseRelationshipVo ownerHouseRelationshipVo : rentList) {
                if (customerIdList.contains(ownerHouseRelationshipVo.getRentOwnerId())) {
                    deleteFailList.add(ownerHouseRelationshipVo.getOwnerId());
                    customerIdList.removeIf(id -> id.equals(ownerHouseRelationshipVo.getRentOwnerId()));
                }
            }
        }
        deleteList = Stream.of(deleteList, customerIdList).flatMap(data -> data.stream()).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteList)) {
            Map<String, Object> deleteMap = new HashMap<>();
            deleteMap.put("list", deleteList);
            deleteMap.put("updateUserId", userId);
            deleteMap.put("updateUserName", userName);
            index = ownerCustomerBaseInfoMapper.deleteBatch(deleteMap);
            if (index > 0) {
                // 删除客户车辆信息
                carMapper.deleteOwnerCarByOwnerId(deleteMap);
                // 删除客户银行账户
                ownerCustomerBankAccountMapper.deleteBankByOwnerId(deleteMap);
                // 删除客户成员关系
                ownerCustomerFamilyInfoMapper.deleteFamilyByOwnerId(deleteMap);
                // 删除结果表
                customerResultMapper.batchDeleteByOwnerId(deleteList);
            }
        }
        if (index > 0) {
            map.put("result", true);
            if (customerIdList.size() == 1) {
                map.put("message", "删除成功");
            } else {
                map.put("message", "选择" + customerIdList.size() + "条，删除成功" + index + "条，失败" + deleteFailList.size() + "条。");
            }
        } else {
            map.put("result", false);
            map.put("message", "删除失败");
        }
        return map;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map<String, Object> deleteCustomerBatch(List<Long> customerIdList, Long userId, String userName) {
        int index = 0;
        Map<String, Object> map = new HashMap<>();
        List<Long> deleteFailList = new ArrayList<>();
        List<Long> deleteList = new ArrayList<>();
        Map<String, Object> relationMap = new HashMap<>();
        relationMap.put("list", customerIdList);
        List<OwnerHouseRelationshipVo> ownerList = ownerHouseRelationshipMapper.listOwnerAllHouseRelationByList(relationMap);
        List<OwnerHouseRelationshipVo> rentList = ownerHouseRelationshipMapper.listRentAllHouseRelationByList(relationMap);
        if (!CollectionUtils.isEmpty(ownerList)) {
            for (OwnerHouseRelationshipVo ownerHouseRelationshipVo : ownerList) {
                if (customerIdList.contains(ownerHouseRelationshipVo.getOwnerId())) {
                    deleteFailList.add(ownerHouseRelationshipVo.getOwnerId());
                    customerIdList.removeIf(id -> id.equals(ownerHouseRelationshipVo.getOwnerId()));
                }
            }
        }
        if (!CollectionUtils.isEmpty(rentList)) {
            for (OwnerHouseRelationshipVo ownerHouseRelationshipVo : rentList) {
                if (customerIdList.contains(ownerHouseRelationshipVo.getRentOwnerId())) {
                    deleteFailList.add(ownerHouseRelationshipVo.getOwnerId());
                    customerIdList.removeIf(id -> id.equals(ownerHouseRelationshipVo.getRentOwnerId()));
                }
            }
        }
        deleteList = Stream.of(deleteList, customerIdList).flatMap(data -> data.stream()).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteList)) {
            Map<String, Object> deleteMap = new HashMap<>();
            deleteMap.put("list", deleteList);
            deleteMap.put("updateUserId", userId);
            deleteMap.put("updateUserName", userName);
            index = ownerCustomerBaseInfoMapper.deleteBatch(deleteMap);
            if (index > 0) {
                // 删除客户车辆信息
                carMapper.deleteOwnerCarByOwnerId(deleteMap);
                // 删除客户银行账户
                ownerCustomerBankAccountMapper.deleteBankByOwnerId(deleteMap);
                // 删除客户成员关系
                ownerCustomerFamilyInfoMapper.deleteFamilyByOwnerId(deleteMap);
                // 删除结果表
                customerResultMapper.batchDeleteByOwnerId(deleteList);
            }
        }
        if (index > 0) {
            map.put("result", true);
            if (customerIdList.size() == 1) {
                map.put("message", "删除成功");
            } else {
                map.put("message", "选择" + customerIdList.size() + "条，删除成功" + index + "条，失败" + deleteFailList.size() + "条。");
            }
        } else {
            map.put("result", false);
            map.put("message", "删除失败");
        }
        return map;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteFamily(Long ownerId, Long relationOwnerId, Long userId, String userName) {
        int index = 0;
        Map<String, Object> map = new HashMap<>();
        map.put("ownerId", ownerId);
        map.put("relationOwnerId", relationOwnerId);
        map.put("userId", userId);
        index = ownerCustomerFamilyInfoMapper.deleteFamily(map);
        if (index > 0) {
            List<Long> list = ownerCustomerFamilyInfoMapper.listAllFamilyByOwnerId(relationOwnerId);
            if (CollectionUtils.isEmpty(list)) {
                OwnerCustomerResult customerResult = new OwnerCustomerResult();
                customerResult.setOwnerId(relationOwnerId);
                customerResult.setHasFamily(Constants.FALSE.toString());
                customerResult.setUpdateUserId(userId);
                customerResult.setUpdateUserName(userName);
                customerResultMapper.updateByPrimaryKeySelective(customerResult);
            }
            // 反向删除成员关系
            map.clear();
            map.put("ownerId", relationOwnerId);
            map.put("relationOwnerId", ownerId);
            map.put("userId", userId);
            index = ownerCustomerFamilyInfoMapper.deleteFamily(map);
            list = ownerCustomerFamilyInfoMapper.listAllFamilyByOwnerId(ownerId);
            if (CollectionUtils.isEmpty(list)) {
                OwnerCustomerResult customerResult = new OwnerCustomerResult();
                customerResult.setOwnerId(ownerId);
                customerResult.setHasFamily(Constants.FALSE.toString());
                customerResult.setUpdateUserId(userId);
                customerResult.setUpdateUserName(userName);
                customerResultMapper.updateByPrimaryKeySelective(customerResult);
            }
        }
        return index;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public List<CustomerVo> listCustomerForSearch(String ownerName, Long organizationId, Integer isDeveloper, Long ownerId) {
        SearchVo searchVo = new SearchVo();
        searchVo.setOrganizationId(organizationId);
        searchVo.setMainSearch(ownerName);
        List<FilterEntity> list = new ArrayList<>();
        FilterEntity filterEntity = new FilterEntity();
        switch (isDeveloper) {
            case 0:
                filterEntity.setFieldName("owner_property");
                filterEntity.setFieldValue(Constants.OWNER_PROPERTY_DEVOLOPER.toString());
                filterEntity.setComparison(Constants.COMPARISON_NOT_EQUAL);
                list.add(filterEntity);
                break;
            case 1:
                filterEntity.setFieldName("owner_property");
                filterEntity.setFieldValue(Constants.OWNER_PROPERTY_DEVOLOPER.toString());
                filterEntity.setComparison(Constants.COMPARISON_EQUAL);
                list.add(filterEntity);
                break;
            case 2:
                break;
            default:
                break;
        }
        if (!CommonUtils.isObjectEmpty(ownerId)) {
            FilterEntity filterEntity1 = new FilterEntity();
            filterEntity1.setFieldName("owner_Id");
            filterEntity1.setFieldValue(ownerId.toString());
            filterEntity1.setComparison(Constants.COMPARISON_NOT_EQUAL);
            list.add(filterEntity1);
        }
        if (!CollectionUtils.isEmpty(list)) {
            searchVo.setFilterList(list);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("searchVo", searchVo);
        List<OwnerCustomerResult> customerResultList = customerResultMapper.listResultBySearch(map);
        List<CustomerVo> customerVoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(customerResultList)) {
            for (OwnerCustomerResult ownerCustomerResult : customerResultList) {
                CustomerVo customerVo = new CustomerVo();
                BeanUtils.copyProperties(ownerCustomerResult, customerVo);

                // 获取客户家庭成员
                List<OwnerCustomerFamilyInfo> familyInfos = ownerCustomerFamilyInfoMapper.listFamilyByOwnerId(ownerCustomerResult.getOwnerId());
                customerVo.setFamilyInfoList(familyInfos);
                customerVoList.add(customerVo);
            }
        }
        return customerVoList;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public List<FamilyInfoVo> listFamilyByOwnerId(Long ownerId) {
        List<FamilyInfoVo> familyInfoVoList = new ArrayList<>();
        List<OwnerCustomerFamilyInfo> familyInfoList = ownerCustomerFamilyInfoMapper.listFamilyByOwnerId(ownerId);
        if (!CollectionUtils.isEmpty(familyInfoList)) {
            List<Long> ownerIdList = new ArrayList<>();
            for (OwnerCustomerFamilyInfo familyInfo : familyInfoList) {
                ownerIdList.add(familyInfo.getOwnerId());
            }
            List<CustomerVo> customerVoList = ownerCustomerBaseInfoMapper.listCustomerById(ownerIdList);
            if (!CollectionUtils.isEmpty(customerVoList)) {
                for (OwnerCustomerFamilyInfo familyInfo : familyInfoList) {
                    FamilyInfoVo familyInfoVo = new FamilyInfoVo();
                    BeanUtils.copyProperties(familyInfo, familyInfoVo);
                    CustomerRelationEnum relationEnum = CustomerRelationEnum.getInstance(familyInfoVo.getOwnerRelationship());
                    if (relationEnum != null) {
                        familyInfoVo.setOwnerRelationshipName(relationEnum.getTitle());
                    }
                    CustomerCallEnum callEnum = CustomerCallEnum.getInstance(familyInfoVo.getRelationOwnerCall());
                    if (callEnum != null) {
                        familyInfoVo.setRelationOwnerCallName(callEnum.getTitle());
                    }
                    for (CustomerVo customerVo : customerVoList) {
                        if (familyInfo.getOwnerId().equals(customerVo.getOwnerId())) {
                            familyInfoVo.setCustomerVo(customerVo);
                            break;
                        }
                    }
                    familyInfoVoList.add(familyInfoVo);
                }
            }
        }
        return familyInfoVoList;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int addBank(OwnerBankAccountVo ownerBankAccountVo, Long userId, String userName) {
        int index = 0;
        OwnerCustomerBankAccount bankAccount = new OwnerCustomerBankAccount();
        BeanUtils.copyProperties(ownerBankAccountVo, bankAccount);
        bankAccount.setUpdateUserId(userId);
        bankAccount.setUpdateUserName(userName);
        bankAccount.setIsDeleted(Constants.FALSE);
        if (CommonUtils.isObjectEmpty(ownerBankAccountVo.getOwnerBankAccountId())) {
            bankAccount.setCreateUserId(userId);
            bankAccount.setCreateUserName(userName);
            index = ownerCustomerBankAccountMapper.insertSelective(bankAccount);
        } else {
            index = ownerCustomerBankAccountMapper.updateByPrimaryKey(bankAccount);
        }
        return index;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public List<OwnerBankAccountVo> listBankByOwnerId(Long ownerId) {
        List<OwnerBankAccountVo> bankAccountVoList = new ArrayList<>();
        List<OwnerCustomerBankAccount> list = ownerCustomerBankAccountMapper.listBankByOwnerId(ownerId);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(bankAccount -> {
                OwnerBankAccountVo ownerBankAccountVo = new OwnerBankAccountVo();
                BeanUtils.copyProperties(bankAccount, ownerBankAccountVo);
                bankAccountVoList.add(ownerBankAccountVo);
            });
        }
        return bankAccountVoList;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteBank(Long ownerId, Long ownerBankAccountId, Long userId, String userName) {
        Map<String, Object> map = new HashMap<>();
        map.put("ownerId", ownerId);
        map.put("ownerBankAccountId", ownerBankAccountId);
        map.put("userId", userId);
        map.put("userName", userName);
        int index = ownerCustomerBankAccountMapper.deleteBank(map);
        return index;
    }

    @Override
    @ReadDataSource
    @Transactional(readOnly = true)
    public OwnerBankAccountVo detailSingleBank(Long ownerBankAccountId) {
        OwnerCustomerBankAccount bankAccount = ownerCustomerBankAccountMapper.selectByPrimaryKey(ownerBankAccountId);
        OwnerBankAccountVo ownerBankAccountVo = new OwnerBankAccountVo();
        BeanUtils.copyProperties(bankAccount, ownerBankAccountVo);
        return ownerBankAccountVo;
    }

    @Override
    @ReadDataSource
    public List<CustomerVo> listAllCustomer(Long houseId) {
        List<CustomerVo> customerVoList = null;
        Map<String, Object> map = new HashMap<>();
        List<Long> houseIdList = new ArrayList<>(1);
        houseIdList.add(houseId);
        map.put("houseIdList", houseIdList);
        List<Long> rentIdList = ownerHouseRelationshipMapper.listRentForSearchCustomerBySearchVo(map);
        if (!CollectionUtils.isEmpty(rentIdList)) {
            customerVoList = ownerCustomerBaseInfoMapper.listCustomerById(rentIdList);
        } else {
            List<Long> ownerIdList = ownerHouseRelationshipMapper.listOwnerForSearchCustomerBySearchVo(map);
            if (!CollectionUtils.isEmpty(ownerIdList)) {
                customerVoList = ownerCustomerBaseInfoMapper.listCustomerById(ownerIdList);
            }
        }
        return customerVoList;
    }

    /**
     * 根据项目Id和类型查询用户对象
     *
     * @param houseId
     * @param ownerProperty 要查询的客户类型：0业主 1租户 2住户 3开发商
     * @param type 查询类型,0:租户->业主->开发商,1:业主->租户
     * @return 如果没有租户则查询业主，如果没有业主则查询开发商，没有开发商返回空
     */
    @Override
    @ReadDataSource
    public CustomerVo getCustomerByHouseIdAndProperty(Long houseId, String ownerProperty,String type) {
        List<Long> customerIdList = null;
        HouseListEntity houseListEntity = ownerHouseResultMapper.selectByPrimaryKey(houseId);
        if ("0".equals(type)) {
            if ("1".equals(ownerProperty) && houseListEntity.getLesseeId() != null) {
                customerIdList = Arrays.asList(houseListEntity.getLesseeId());
            }
            if (("0".equals(ownerProperty) || ("1".equals(ownerProperty) && CollectionUtils.isEmpty(customerIdList))) && houseListEntity.getOwnerId() != null) {
                customerIdList = Arrays.asList(houseListEntity.getOwnerId());
            }
            if ("3".equals(ownerProperty) || CollectionUtils.isEmpty(customerIdList)) {
                try {
                    Long ownerId = iHouseOperateService.getDeveloperByHousePath(houseId);
                    customerIdList = Arrays.asList(ownerId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else {
            if ("0".equals(ownerProperty)){
                customerIdList = Arrays.asList(houseListEntity.getOwnerId());
            }
            if ("1".equals(ownerProperty) || CollectionUtils.isEmpty(customerIdList)){
                customerIdList = Arrays.asList(houseListEntity.getLesseeId());
            }
        }
        //查询用户
        List<CustomerVo> customerVoList = null;
        if (!CollectionUtils.isEmpty(customerIdList)) {
            customerVoList = ownerCustomerBaseInfoMapper.listCustomerById(customerIdList);
            if (!CollectionUtils.isEmpty(customerVoList)) {
                return customerVoList.get(0);
            }
        }
        return null;
    }

    /**
     * 搜索房产下面开发商信息
     *
     * @param houseId
     * @param name
     * @param enterpriseId
     * @param orgId
     * @return
     */
    @ReadDataSource
    @Override
    public List<CustomerVo> searchDevelopersCustomer(Long houseId, String name, Long enterpriseId, Long orgId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("houseId", houseId);
        map.put("ownerProperty", "3");
        map.put("name", name);
        map.put("enterpriseId", enterpriseId);
        map.put("orgId", orgId);

        List<Long> customerIdList = ownerHouseRelationshipMapper.listByHouseIdAndProperty(map);
        if (!CollectionUtils.isEmpty(customerIdList)) {
            List<CustomerVo> customerVoList = ownerCustomerBaseInfoMapper.listCustomerById(customerIdList);
            return customerVoList;
        }
        return null;
    }

    @ReadDataSource
    @Override
    public List<OwnerCustomerBaseInfo> searchCustomer(Long houseId, String ownerProperty, String name, String ownerType) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("houseId", houseId);
        map.put("ownerProperty", ownerProperty);
        map.put("name", name);
        map.put("ownerType", ownerType);
        List<OwnerCustomerBaseInfo> customerVoList = ownerCustomerBaseInfoMapper.searchCustomer(map);
        return customerVoList;
    }


}
