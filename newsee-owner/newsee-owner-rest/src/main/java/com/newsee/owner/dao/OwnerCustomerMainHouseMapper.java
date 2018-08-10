package com.newsee.owner.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.vo.SearchVo;
import com.newsee.owner.entity.OwnerCustomerMainHouse;

public interface OwnerCustomerMainHouseMapper {

    int insert(OwnerCustomerMainHouse record);

    int insertSelective(OwnerCustomerMainHouse record);

    OwnerCustomerMainHouse selectByPrimaryKey(Long mainHouseId);

    int updateByPrimaryKeySelective(OwnerCustomerMainHouse record);

    int updateByPrimaryKey(OwnerCustomerMainHouse record);
    
    int insertBatch(List<OwnerCustomerMainHouse> list);
    
    int deleteMainHouse(Map<String, Object> map);
    
    List<OwnerCustomerMainHouse> listByOwnerHouse(Map<String, Object> map);
    
    List<Long> listForSearchCustomer(SearchVo searchVo);
}