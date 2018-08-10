package com.newsee.owner.dao;

import java.util.List;
import java.util.Map;

import com.newsee.owner.entity.OwnerCustomerCar;
import com.newsee.owner.vo.CarVo;

public interface OwnerCustomerCarMapper {
    int deleteByPrimaryKey(Long ownerCarId);

    int insert(OwnerCustomerCar record);

    int insertSelective(OwnerCustomerCar record);

    OwnerCustomerCar selectByPrimaryKey(Long ownerCarId);

    int updateByPrimaryKeySelective(OwnerCustomerCar record);

    int updateByPrimaryKey(OwnerCustomerCar record);
    
    List<CarVo> listOwnerCar(Long ownerId);
    
    int deleteOwnerCar(Map<String, Object> map);
    
    int deleteOwnerCarByOwnerId(Map<String, Object> map);
    
    List<CarVo> listResultBySearch(Map<String, Object> map);
    
    OwnerCustomerCar loadByCarNumber(Map<String, Object> map);
    
    List<CarVo> listCarByHouseId(Long houseId);
}