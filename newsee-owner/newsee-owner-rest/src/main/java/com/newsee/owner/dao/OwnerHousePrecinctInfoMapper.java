package com.newsee.owner.dao;

import java.util.Map;

import com.newsee.owner.entity.OwnerHousePrecinctInfo;

public interface OwnerHousePrecinctInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHousePrecinctInfo record);

    int insertSelective(OwnerHousePrecinctInfo record);

    OwnerHousePrecinctInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHousePrecinctInfo record);

    int updateByPrimaryKey(OwnerHousePrecinctInfo record);
    
//    OwnerHousePrecinctInfo checkUniqueByName();
//    OwnerHousePrecinctInfo checkUniqueByNo();
//    OwnerHousePrecinctInfo checkUniqueByShortName();
    OwnerHousePrecinctInfo loadHouseByShortName(Map<String, Object> map);
    OwnerHousePrecinctInfo loadHouseByNo(Map<String, Object> map);
}