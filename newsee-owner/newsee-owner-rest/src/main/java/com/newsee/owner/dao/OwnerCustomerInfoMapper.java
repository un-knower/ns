package com.newsee.owner.dao;

import com.newsee.owner.entity.OwnerCustomerInfo;

public interface OwnerCustomerInfoMapper {
    int deleteByPrimaryKey(Long ownerId);

    int insert(OwnerCustomerInfo record);

    int insertSelective(OwnerCustomerInfo record);

    OwnerCustomerInfo selectByPrimaryKey(Long ownerId);

//    int update(OwnerCustomerInfo record);

    int updateByPrimaryKeySelective(OwnerCustomerInfo record);
    
    int updateByPrimary(OwnerCustomerInfo record);
}