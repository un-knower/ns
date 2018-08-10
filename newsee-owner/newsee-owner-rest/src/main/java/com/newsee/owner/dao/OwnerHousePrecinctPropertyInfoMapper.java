package com.newsee.owner.dao;

import com.newsee.owner.entity.OwnerHousePrecinctPropertyInfo;

public interface OwnerHousePrecinctPropertyInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHousePrecinctPropertyInfo record);

    int insertSelective(OwnerHousePrecinctPropertyInfo record);

    OwnerHousePrecinctPropertyInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHousePrecinctPropertyInfo record);

    int updateByPrimaryKey(OwnerHousePrecinctPropertyInfo record);
}