package com.newsee.owner.dao;

import java.util.List;
import java.util.Map;

import com.newsee.owner.entity.OwnerCustomerResult;

public interface OwnerCustomerResultMapper {
    int deleteByPrimaryKey(Long ownerId);

    int batchDeleteByOwnerId(List<Long> list);
    int insert(OwnerCustomerResult record);

    int insertSelective(OwnerCustomerResult record);

    OwnerCustomerResult selectByPrimaryKey(Long ownerId);

    int updateByPrimaryKeySelective(OwnerCustomerResult record);

    int updateByPrimaryKey(OwnerCustomerResult record);
    
    List<OwnerCustomerResult> listResultBySearch(Map<String, Object> map);
    
    int updateBatch(Map<String, Object> map);
}