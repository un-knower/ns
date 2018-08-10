package com.newsee.owner.dao;

import java.util.Map;

import com.newsee.owner.entity.OwnerHouseGarageInfo;

public interface OwnerHouseGarageInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHouseGarageInfo record);

    int insertSelective(OwnerHouseGarageInfo record);

    OwnerHouseGarageInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHouseGarageInfo record);

    int updateByPrimaryKey(OwnerHouseGarageInfo record);
    
    OwnerHouseGarageInfo loadHouseByShortName(Map<String, Object> map);
    
    OwnerHouseGarageInfo loadHouseByNo(Map<String, Object> map);
}