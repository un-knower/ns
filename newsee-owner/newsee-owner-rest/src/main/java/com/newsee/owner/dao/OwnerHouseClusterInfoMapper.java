package com.newsee.owner.dao;

import java.util.Map;

import com.newsee.owner.entity.OwnerHouseClusterInfo;

public interface OwnerHouseClusterInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHouseClusterInfo record);

    int insertSelective(OwnerHouseClusterInfo record);

    OwnerHouseClusterInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHouseClusterInfo record);

    int updateByPrimaryKey(OwnerHouseClusterInfo record);
    
    OwnerHouseClusterInfo loadHouseByShortName(Map<String, Object> map);
    
    OwnerHouseClusterInfo loadHouseByNo(Map<String, Object> map);
}