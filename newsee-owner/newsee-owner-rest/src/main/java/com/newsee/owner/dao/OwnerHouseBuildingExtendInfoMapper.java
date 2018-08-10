package com.newsee.owner.dao;

import com.newsee.owner.entity.OwnerHouseBuildingExtendInfo;

public interface OwnerHouseBuildingExtendInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHouseBuildingExtendInfo record);

    int insertSelective(OwnerHouseBuildingExtendInfo record);

    OwnerHouseBuildingExtendInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHouseBuildingExtendInfo record);

    int updateByPrimaryKey(OwnerHouseBuildingExtendInfo record);
}