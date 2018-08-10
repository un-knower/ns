package com.newsee.owner.dao;

import java.util.Map;

import com.newsee.owner.entity.OwnerHouseBuildingInfo;

public interface OwnerHouseBuildingInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHouseBuildingInfo record);

    int insertSelective(OwnerHouseBuildingInfo record);

    OwnerHouseBuildingInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHouseBuildingInfo record);

    int updateByPrimaryKey(OwnerHouseBuildingInfo record);
    
    OwnerHouseBuildingInfo loadHouseByShortName(Map<String, Object> map);
    
    OwnerHouseBuildingInfo loadHouseByNo(Map<String, Object> map);
}