package com.newsee.owner.dao;

import com.newsee.owner.entity.OwnerHousePrecinctExtendInfo;

public interface OwnerHousePrecinctExtendInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHousePrecinctExtendInfo record);

    int insertSelective(OwnerHousePrecinctExtendInfo record);

    OwnerHousePrecinctExtendInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHousePrecinctExtendInfo record);

    int updateByPrimaryKey(OwnerHousePrecinctExtendInfo record);
}