package com.newsee.owner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.newsee.common.enums.HouseTypeEnum;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;
import com.newsee.owner.dao.OwnerCustomerBaseInfoMapper;
import com.newsee.owner.dao.OwnerCustomerCarCarportRelationshipMapper;
import com.newsee.owner.dao.OwnerCustomerCarMapper;
import com.newsee.owner.dao.OwnerCustomerResultMapper;
import com.newsee.owner.dao.OwnerHouseBaseInfoMapper;
import com.newsee.owner.dao.OwnerHouseRelationshipMapper;
import com.newsee.owner.entity.OwnerCustomerBaseInfo;
import com.newsee.owner.entity.OwnerCustomerCar;
import com.newsee.owner.entity.OwnerCustomerCarCarportRelationship;
import com.newsee.owner.entity.OwnerCustomerResult;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import com.newsee.owner.service.ICarService;
import com.newsee.owner.vo.CarVo;
import com.newsee.owner.vo.CustomerVo;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private OwnerCustomerCarMapper ownerCustomerCarMapper;
    @Autowired
    private OwnerCustomerCarCarportRelationshipMapper carportRelationshipMapper; 
//    @Autowired
//    private OwnerHouseResultMapper houseResultMapper;
    @Autowired
    private OwnerHouseBaseInfoMapper houseBaseMapper;
    @Autowired
    private OwnerCustomerResultMapper customerResultMapper;
    @Autowired
    private OwnerHouseRelationshipMapper ownerHouseRelationshipMapper;
    @Autowired
    private OwnerCustomerBaseInfoMapper ownerCustomerBaseInfoMapper;
    @Override
    public List<CarVo> listOwnerCar(Long ownerId) {
        return ownerCustomerCarMapper.listOwnerCar(ownerId);
    }
    @Override
    @WriteDataSource
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public Long addOwnerCar(CarVo carVo) {
        OwnerCustomerCar car = new OwnerCustomerCar();
        BeanUtils.copyProperties(carVo, car);
        car.setOwnerId(carVo.getCustomerVo().getOwnerId());
        int index = 0;
        if (CommonUtils.isObjectEmpty(carVo.getOwnerCarId())) {
            index = ownerCustomerCarMapper.insertSelective(car);
        }else {
            index = ownerCustomerCarMapper.updateByPrimaryKeySelective(car);
        }
        if (index > 0) {
            OwnerCustomerResult ownerCustomerResult = customerResultMapper.selectByPrimaryKey(carVo.getCustomerVo().getOwnerId());
            //修改车主所属项目
            String precinctId = Constants.SEPARATOR_PATH+carVo.getCustomerVo().getPrecinctId()+Constants.SEPARATOR_PATH;
            if (!ownerCustomerResult.getPrecinctId().contains(precinctId)) {
                OwnerCustomerBaseInfo ownerCustomerBaseInfo = new OwnerCustomerBaseInfo();
                ownerCustomerBaseInfo.setOwnerId(carVo.getCustomerVo().getOwnerId());
                ownerCustomerBaseInfo.setUpdateUserId(carVo.getUpdateUserId());
                ownerCustomerBaseInfo.setUpdateUserName(carVo.getUpdateUserName());
                ownerCustomerBaseInfo.setUpdateTime(new Date());
                ownerCustomerBaseInfo.setPrecinctId(ownerCustomerResult.getPrecinctId()+carVo.getCustomerVo().getPrecinctId()+Constants.SEPARATOR_PATH);
                ownerCustomerBaseInfoMapper.updateByPrimaryKeySelective(ownerCustomerBaseInfo);
                //同步客户结果表
                OwnerCustomerResult customerResult = new OwnerCustomerResult();
                customerResult.setOwnerId(carVo.getCustomerVo().getOwnerId());
                customerResult.setUpdateUserId(carVo.getUpdateUserId());
                customerResult.setUpdateUserName(carVo.getUpdateUserName());
                customerResult.setUpdateTime(new Date());
                customerResult.setPrecinctId(ownerCustomerResult.getPrecinctId()+carVo.getCustomerVo().getPrecinctId()+Constants.SEPARATOR_PATH);
                customerResultMapper.updateByPrimaryKeySelective(customerResult);
            }
            
            if (!CommonUtils.isObjectEmpty(carVo.getOwnerCarId())) {
                carportRelationshipMapper.deleteByCarId(carVo.getOwnerCarId());
            }
            if (!CollectionUtils.isEmpty(carVo.getCarportList())) {
                //绑定车位
                List<OwnerCustomerCarCarportRelationship> carCarportRelationshipList = new ArrayList<>();
                for (OwnerHouseBaseInfo houseBaseVo : carVo.getCarportList()) {
                    OwnerCustomerCarCarportRelationship carCarportRelationship = new OwnerCustomerCarCarportRelationship();
//                        BeanUtils.copyProperties(houseBaseVo, carCarportRelationship);
                    carCarportRelationship.setPrecinctId(houseBaseVo.getPrecinctId());
                    carCarportRelationship.setHouseId(houseBaseVo.getHouseId());
                    carCarportRelationshipList.add(carCarportRelationship);
                }
                carportRelationshipMapper.insertBatch(carCarportRelationshipList);
            }
        }
        return car.getOwnerCarId();
    }

    @Override
    @ReadDataSource
    public CarVo detailOwnerCar(Long ownerCarId) {
        OwnerCustomerCar car = ownerCustomerCarMapper.selectByPrimaryKey(ownerCarId);
        CarVo carVo = new CarVo();
        if (car != null) {
            BeanUtils.copyProperties(car, carVo);
            OwnerCustomerResult ownerCustomerResult = customerResultMapper.selectByPrimaryKey(car.getOwnerId());
            if (ownerCustomerResult != null) {
                CustomerVo customerVo = new CustomerVo();
                BeanUtils.copyProperties(ownerCustomerResult, customerVo);
                carVo.setCustomerVo(customerVo);
            }
            List<OwnerHouseBaseInfo> carportList = carportRelationshipMapper.listOwnerCarport(car.getOwnerCarId());
            carVo.setCarportList(carportList);
        }else {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "车辆不存在");
        }
        return carVo;
    }
    @SuppressWarnings("unchecked")
    @Override
    @ReadDataSource
    public PageInfo<CarVo> listOwnerCarForSearch(SearchVo searchVo, Map<String, Object> columnMap, boolean pageFlag) throws Exception {

        Map<String, Object> map = getSearchCondition(searchVo);
        List<Long> ownerIdList = (List<Long>) map.get("ownerIdList"); 
        if (CollectionUtils.isEmpty(ownerIdList)) {
            return new PageInfo<>(new ArrayList<>(0));
        }
        List<CarVo> list = new ArrayList<>();
        if (pageFlag) {
            PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
            list = ownerCustomerCarMapper.listResultBySearch(map);
        }else {
            list = ownerCustomerCarMapper.listResultBySearch(map);
        }

        PageInfo<CarVo> pageInfo = new PageInfo<>(list);
        if (!CollectionUtils.isEmpty(list)) {
            List<Long> carIdList = new ArrayList<>();
            list.forEach(car->{
                carIdList.add(car.getOwnerCarId());
            });
            List<OwnerCustomerCarCarportRelationship> houseList = carportRelationshipMapper.listOwnerCarportByCarIdList(carIdList);
            if (!CollectionUtils.isEmpty(houseList)) {
                List<Long> houseIdList = new ArrayList<>();
                houseList.forEach(relation->{
                    houseIdList.add(relation.getHouseId());
                });
                List<OwnerHouseBaseInfo> houseBaseInfos = houseBaseMapper.listOwnerHouseBaseInfoByHouseIdList(houseIdList);
                for (CarVo carVo : list) {
                    if (!CollectionUtils.isEmpty(houseBaseInfos)) {
                        List<OwnerHouseBaseInfo> carportList = new ArrayList<>();
                        for (OwnerCustomerCarCarportRelationship carCarportRelationship : houseList) {
                            if (carVo.getOwnerCarId().equals(carCarportRelationship.getOwnerCarId()) && !CollectionUtils.isEmpty(houseBaseInfos)) {
                                for (OwnerHouseBaseInfo house : houseBaseInfos) {
                                    if (house.getHouseId().equals(carCarportRelationship.getHouseId())) {
                                        carportList.add(house);
                                    }
                                }
                            }
                        }
                        carVo.setCarportList(carportList);
                    }
                }
            }

        }

        if (!CollectionUtils.isEmpty(pageInfo.getList())) {
            //统计
            //获取需要统计的字段
            if (columnMap != null) {
                List<NsCoreResourcecolumnVo> columnList = (List<NsCoreResourcecolumnVo>) columnMap.get("columns");
                columnList = CommonUtils.getTotalColumn(columnList);
                if (!CollectionUtils.isEmpty(columnList)) {
                    String pageJson = "";
                    //统计本页
                    pageJson = JSONObject.toJSONString(list);
                    String pageTotalJson = CommonUtils.totalList(pageJson, columnList, CarVo.class);
                    if (StringUtils.hasLength(pageTotalJson)) {
                        CarVo total = JSONObject.parseObject(pageTotalJson, CarVo.class);
                        list.add(total);
                    }                  
                }
            }
        }
        return pageInfo;
    }
    private Map<String, Object> getSearchCondition(SearchVo searchVo){
        SearchVo searchHouseVo = new SearchVo();
        searchHouseVo.setFilterList(new ArrayList<FilterEntity>());

        if (!CollectionUtils.isEmpty(searchVo.getTreeConditions())) {
            for (FilterEntity treeFilterEntity : searchVo.getTreeConditions()) {
                FilterEntity searchFilter = new FilterEntity();
                if ("0".equals(treeFilterEntity.getFieldValue())) {
                    searchFilter.setComparison(Constants.COMPARISON_LIKE);
                    searchFilter.setFieldName("path");
                    searchFilter.setFieldValue(Constants.SEPARATOR_PATH);
                }else {
                    OwnerHouseBaseInfo searchBase = houseBaseMapper.selectByPrimaryKey(Long.valueOf(treeFilterEntity.getFieldValue()));
                    if (HouseTypeEnum.ROOM.getValue().equals(searchBase.getHouseType())
                            || HouseTypeEnum.CARPORT.getValue().equals(searchBase.getHouseType())
                            || HouseTypeEnum.PUBLICAREA.getValue().equals(searchBase.getHouseType())) {
                        searchFilter.setComparison(Constants.COMPARISON_EQUAL);
                        searchFilter.setFieldName("houseId");
                        searchFilter.setFieldValue(searchBase.getHouseId().toString());
                    }else {
                        searchFilter.setComparison(Constants.COMPARISON_LIKE);
                        searchFilter.setFieldName("path");
                        searchFilter.setFieldValue(searchBase.getPath() + searchBase.getHouseId() + Constants.SEPARATOR_PATH);
                    }                    
                }
                searchHouseVo.getFilterList().add(searchFilter);
            }
        }
        List<FilterEntity> houseFilterList = new ArrayList<>();
        List<FilterEntity> customerFilterList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(searchVo.getFilterList())) {
            for (FilterEntity filterEntity : searchVo.getFilterList()) {
                if ("houseFullName".equals(filterEntity.getFieldName())) {
                    //按车位名称查询
                    houseFilterList.add(filterEntity);
                }
                if ("ownerName".equals(filterEntity.getFieldName())) {
                    //按车主名称查询
                    customerFilterList.add(filterEntity);                    
                }
            }
            searchVo.getFilterList().removeIf(filterEntity -> "houseFullName".equals(filterEntity.getFieldName()));
            searchVo.getFilterList().removeIf(filterEntity -> "ownerName".equals(filterEntity.getFieldName()));
        }
        Map<String, Object> map = new HashMap<>();
        List<Long> searchCarIdList = new ArrayList<>();
        List<OwnerHouseBaseInfo> houseResultList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(houseFilterList)) {
            searchHouseVo.getFilterList().addAll(houseFilterList);
        }
        map.put("searchVo", searchHouseVo);
//        houseResultList = houseBaseMapper.listForSearchCustomer(map);
        //根据固定车位查询
//        if (!CollectionUtils.isEmpty(houseResultList)) {
//            List<Long> houseIdList = new ArrayList<>();
//            houseResultList.forEach(house->{
//                houseIdList.add(house.getHouseId());
//            });
//            searchCarIdList = carportRelationshipMapper.listForSearchCar(houseIdList);
//        }
//        
        List<Long> ownerIdList = new ArrayList<>();
        //根据筛选器查询车主
        List<OwnerCustomerResult> customerResultList = new ArrayList<>();
        Map<String, Object> searchOwnerMap = getSearchOwnerCondition(searchVo);
        if (searchOwnerMap != null) {
            searchHouseVo.setFilterList(customerFilterList);
            searchOwnerMap.put("searchVo", searchHouseVo);
            customerResultList = customerResultMapper.listResultBySearch(searchOwnerMap);
            if (!CollectionUtils.isEmpty(customerResultList)) {
                customerResultList.forEach(customer->{
                    ownerIdList.add(customer.getOwnerId());
                });
            }
        }
        map.clear();
//        map.put("customerResultList", customerResultList);
//        map.put("houseResultList", houseResultList);
        map.put("searchVo", searchVo);
        if (!CollectionUtils.isEmpty(searchCarIdList)) {
            map.put("searchCarIdList", searchCarIdList);
        }
        map.put("ownerIdList", ownerIdList);
        return map;
    }
    private Map<String, Object> getSearchOwnerCondition(SearchVo searchVo){
        Map<String, Object> map = new HashMap<>();
        SearchVo searchHouseVo = new SearchVo();
        searchHouseVo.setFilterList(new ArrayList<FilterEntity>());
//        List<Long> precinctIdList = new ArrayList<>();
        List<String> precinctListForNoHouse = new ArrayList<>();
        //房产树查询条件处理
        if (!CollectionUtils.isEmpty(searchVo.getTreeConditions())) {
            FilterEntity filterEntity = searchVo.getTreeConditions().get(0);
            OwnerHouseBaseInfo searchBase = houseBaseMapper.selectByPrimaryKey(Long.valueOf(filterEntity.getFieldValue()));
            FilterEntity searchFilter = new FilterEntity();
            if ("0".equals(filterEntity.getFieldValue())) {
                searchFilter.setComparison(Constants.COMPARISON_LIKE);
                searchFilter.setFieldName("path");
                searchFilter.setFieldValue(Constants.SEPARATOR_PATH);
                //TODO 数据权限
            }else {
                if (HouseTypeEnum.ROOM.getValue().equals(searchBase.getHouseType())
                        || HouseTypeEnum.CARPORT.getValue().equals(searchBase.getHouseType())
                        || HouseTypeEnum.PUBLICAREA.getValue().equals(searchBase.getHouseType())) {
                    searchFilter.setComparison(Constants.COMPARISON_EQUAL);
                    searchFilter.setFieldName("houseId");
                    searchFilter.setFieldValue(searchBase.getHouseId().toString());
                    precinctListForNoHouse.add(searchBase.getPrecinctId().toString());
                }else {
                    if (HouseTypeEnum.PRECINCT.getValue().equals(searchBase.getHouseType())) {
//                            precinctIdList.add(searchBase.getHouseId());
                        precinctListForNoHouse.add(searchBase.getHouseId().toString());

                    }else if (HouseTypeEnum.AREA.getValue().equals(searchBase.getHouseType())) {
                        List<OwnerHouseBaseInfo> baseInfos = houseBaseMapper.listOwnerHouseBaseInfoByPath(searchBase.getPath() + searchBase.getHouseId() + Constants.SEPARATOR_PATH);
                        if (CollectionUtils.isEmpty(baseInfos)) {
                            return null;
                        }else {
                            baseInfos.forEach(base->{
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
            searchHouseVo.getFilterList().add(searchFilter);
            List<FilterEntity> precinctFilterList = new ArrayList<>();
            
            List<Long> searchOwnerIdList = new ArrayList<>();
            List<OwnerHouseBaseInfo> houseResultList = new ArrayList<>();
            map.put("searchVo", searchHouseVo);
            houseResultList = houseBaseMapper.listForSearchCustomer(map);
            //1、房产树选择根节点、区域时，如果没有子节点，列表为空
            //2、选择项目时，列表显示子节点关联的业主、租户、住户
            //3、选择其他节点时，列表显示子节点关联的业主、租户、住户、以及节点所属项目的无房客户
//            if (CollectionUtils.isEmpty(houseResultList)) {
//                BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "无房产数据");
//            }
            //筛选器-根据项目名称，主房产名称进行查询处理
            if (!CollectionUtils.isEmpty(searchVo.getFilterList())) {
                for (FilterEntity searchFilterEntity : searchVo.getFilterList()) {
                    if ("precinctName".equals(filterEntity.getFieldName())) {
                        //按项目查询
                        FilterEntity precinctFilter = new FilterEntity();
                        precinctFilter.setFieldName("houseName");
                        precinctFilter.setComparison(searchFilterEntity.getComparison());
                        precinctFilter.setFieldValue(searchFilterEntity.getFieldValue());
                        precinctFilterList.add(precinctFilter);
                    }
                }
                searchVo.getFilterList().removeIf(searchFilterEntity -> "precinctName".equals(searchFilterEntity.getFieldName()));
            }
            
            List<Long> houseIdList = new ArrayList<>();
            List<Long> precinctIdList = new ArrayList<>();
            if (searchBase != null && !HouseTypeEnum.AREA.getValue().equals(searchBase.getHouseType())
                    && !HouseTypeEnum.PRECINCT.getValue().equals(searchBase.getHouseType())) {
                houseResultList.forEach(house->{
                    if (HouseTypeEnum.PRECINCT.getValue().equals(house.getHouseType())) {
                        precinctIdList.add(house.getHouseId());
                    }else {
                        precinctIdList.add(house.getPrecinctId());
                    }
                    if (HouseTypeEnum.ROOM.getValue().equals(house.getHouseType())
                                    || HouseTypeEnum.CARPORT.getValue().equals(house.getHouseType())
                                    || HouseTypeEnum.PUBLICAREA.getValue().equals(house.getHouseType())) {
                        houseIdList.add(house.getHouseId());
                    }
                });
                if (!CollectionUtils.isEmpty(precinctIdList)) {
                    CommonUtils.removeDuplicate(precinctIdList);
                    List<OwnerHouseBaseInfo> precinctList = houseBaseMapper.listOwnerHouseBaseInfoByHouseIdList(precinctIdList);
                    if (!CollectionUtils.isEmpty(precinctFilterList)) {
                        for (FilterEntity precinctFilter : precinctFilterList) {
                            precinctList.removeIf(precinct->!precinct.getHouseName().contains(precinctFilter.getFieldValue()));
                        }
                    }
                    if (!CollectionUtils.isEmpty(precinctList)) {
                        precinctIdList.clear();
                        precinctList.forEach(precinct->{
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
            map.clear();
            map.put("searchVo", searchVo);
            if (!CollectionUtils.isEmpty(precinctListForNoHouse)) {
                CommonUtils.removeDuplicate(precinctListForNoHouse);
                map.put("precinctListForNoHouse", precinctListForNoHouse);
            }
            if (!CollectionUtils.isEmpty(searchOwnerIdList)) {
                map.put("ownerIdList", searchOwnerIdList);
            }else {
                if (searchBase != null) {
                    if (HouseTypeEnum.ROOM.getValue().equals(searchBase.getHouseType())
                            || HouseTypeEnum.CARPORT.getValue().equals(searchBase.getHouseType())
                            || HouseTypeEnum.PUBLICAREA.getValue().equals(searchBase.getHouseType())) {
                        return null;
                    }                
                }
            }
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    @ReadDataSource
    @Override
    public CarVo getTotal(SearchVo searchConditionVo, Map<String, Object> columnMap) throws Exception {
        CarVo total = new CarVo();
        //统计
        //获取需要统计的字段
        if (columnMap != null) {
            List<NsCoreResourcecolumnVo> columnList = (List<NsCoreResourcecolumnVo>) columnMap.get("columns");
            columnList = CommonUtils.getTotalColumn(columnList);
            if (!CollectionUtils.isEmpty(columnList)) {
                String AllJson = "";
                //统计当前所有
                Map<String, Object> map = getSearchCondition(searchConditionVo);
                List<CarVo> resultListForTotal = ownerCustomerCarMapper.listResultBySearch(map);
                if (!CollectionUtils.isEmpty(resultListForTotal)) {
                    AllJson = JSONObject.toJSONString(resultListForTotal);
                    String allTotalJson = CommonUtils.totalList(AllJson, columnList, CarVo.class);
                    if (StringUtils.hasLength(allTotalJson)) {
                        total = JSONObject.parseObject(allTotalJson, CarVo.class);
                    }
                }
            }
        }
        return total;
    }
    
    @Override
    @WriteDataSource
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public int deleteOwnerCar(List<Long> ownerCarIdList, Long userId, String userName) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", ownerCarIdList);
        map.put("updateUserId", userId);
        map.put("updateUserName", userName);
        int index = ownerCustomerCarMapper.deleteOwnerCar(map);
        return index;
    }
    
    @Override
    @WriteDataSource
    @Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public int deleteAllOwnerCar(SearchVo searchVo, Long userId, String userName) throws Exception {
        PageInfo<CarVo> pageInfo = listOwnerCarForSearch(searchVo, null, false);
        if (!CollectionUtils.isEmpty(pageInfo.getList())) {
            Map<String, Object> map = new HashMap<>();
            List<Long> list = new ArrayList<>();
            for (CarVo carVo : pageInfo.getList()) {
                list.add(carVo.getOwnerCarId());
            }
            map.put("list", list);
            map.put("updateUserId", userId);
            map.put("updateUserName", userName);
            int index = ownerCustomerCarMapper.deleteOwnerCar(map);
            return index;
        }
        return 0;
    }
    @Override
    @ReadDataSource
    @Transactional(readOnly=true)
    public boolean checkCarNumberOnly(CarVo carVo) {
        boolean flag = true;
        Map<String, Object> map = new HashMap<>();
        map.put("organizationId", carVo.getOrganizationId());
        map.put("carNumber", carVo.getCarNumber());
        if (!CommonUtils.isObjectEmpty(carVo.getOwnerCarId())) {
            map.put("ownerCarId", carVo.getOwnerCarId());
        }
        OwnerCustomerCar ownerCustomerCar = ownerCustomerCarMapper.loadByCarNumber(map);
        if (ownerCustomerCar != null) {
            flag = false;
        }
        return flag;
    }

}
