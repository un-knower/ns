package com.newsee.owner.dao;

import java.util.List;

import com.newsee.owner.entity.OwnerHouseMasterSlaveHouse;

public interface OwnerHouseMasterSlaveHouseMapper {
    int deleteByMasterHouseId(Long masterHouseId);

    int insert(OwnerHouseMasterSlaveHouse record);

    int insertSelective(OwnerHouseMasterSlaveHouse record);

    OwnerHouseMasterSlaveHouse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OwnerHouseMasterSlaveHouse record);

    int updateByPrimaryKey(OwnerHouseMasterSlaveHouse record);
    
    int insertBatch(List<OwnerHouseMasterSlaveHouse> list);

    List<OwnerHouseMasterSlaveHouse> listSlaveHouse(Long houseId);
}