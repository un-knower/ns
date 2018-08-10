package com.newsee.owner.dao;

import java.util.List;

import com.newsee.owner.entity.OwnerHousePropertyOwner;

public interface OwnerHousePropertyOwnerMapper {
    int deleteByPrimaryKey(Long propertyOwnerId);

    int insert(OwnerHousePropertyOwner record);

    int insertSelective(OwnerHousePropertyOwner record);

    OwnerHousePropertyOwner selectByPrimaryKey(Long propertyOwnerId);

    int updateByPrimaryKeySelective(OwnerHousePropertyOwner record);

    int updateByPrimaryKey(OwnerHousePropertyOwner record);
    
    int insertBatch(List<OwnerHousePropertyOwner> list);
}