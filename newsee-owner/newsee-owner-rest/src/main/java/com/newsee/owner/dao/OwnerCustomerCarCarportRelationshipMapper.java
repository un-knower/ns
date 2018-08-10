package com.newsee.owner.dao;

import java.util.List;

import com.newsee.owner.entity.OwnerCustomerCarCarportRelationship;
import com.newsee.owner.entity.OwnerHouseBaseInfo;

public interface OwnerCustomerCarCarportRelationshipMapper {
    int deleteByPrimaryKey(Long relationshipId);

    int insert(OwnerCustomerCarCarportRelationship record);

    int insertSelective(OwnerCustomerCarCarportRelationship record);

    OwnerCustomerCarCarportRelationship selectByPrimaryKey(Long relationshipId);

    int updateByPrimaryKeySelective(OwnerCustomerCarCarportRelationship record);

    int updateByPrimaryKey(OwnerCustomerCarCarportRelationship record);
    
    int insertBatch(List<OwnerCustomerCarCarportRelationship> list);
    
    int deleteByCarId(Long carId);
    
    List<OwnerHouseBaseInfo> listOwnerCarport(Long carId);
    
    List<OwnerCustomerCarCarportRelationship> listOwnerCarportByCarIdList(List<Long> list);
    
    List<Long> listForSearchCar(List<Long> list);
}