package com.newsee.owner.dao;

import java.util.List;
import java.util.Map;

import com.newsee.owner.entity.OwnerCustomerBaseInfo;
import com.newsee.owner.vo.CustomerVo;

public interface OwnerCustomerBaseInfoMapper {
    int deleteByPrimaryKey(Long ownerId);

    int insert(OwnerCustomerBaseInfo record);

    CustomerVo loadCustomer(Long ownerId);

//    int update(OwnerCustomerBaseInfo record);
    int updateByPrimaryKeySelective(OwnerCustomerBaseInfo record);
    
    int updateByPrimary(OwnerCustomerBaseInfo record);
    
    List<CustomerVo> listCustomerById(List<Long> list);
    
    int deleteBatch(Map<String, Object> map);
    
    CustomerVo loadCustomerByName(Map<String, Object> map);

    List<OwnerCustomerBaseInfo> searchCustomer(Map<String,Object> map);
}