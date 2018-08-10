package com.newsee.owner.dao;

import java.util.Map;

import com.newsee.owner.entity.OwnerHouseUnitInfo;

public interface OwnerHouseUnitInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHouseUnitInfo record);

    int insertSelective(OwnerHouseUnitInfo record);

    OwnerHouseUnitInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHouseUnitInfo record);

    int updateByPrimaryKey(OwnerHouseUnitInfo record);
    
    OwnerHouseUnitInfo loadHouseByShortName(Map<String, Object> map);
    
    OwnerHouseUnitInfo loadHouseByNo(Map<String, Object> map);
}