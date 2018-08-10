package com.newsee.owner.dao;

import java.util.List;

import com.newsee.owner.entity.OwnerHouseCarportExtendInfo;

public interface OwnerHouseCarportExtendInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHouseCarportExtendInfo record);

    int insertSelective(OwnerHouseCarportExtendInfo record);

    OwnerHouseCarportExtendInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHouseCarportExtendInfo record);

    int updateByPrimaryKey(OwnerHouseCarportExtendInfo record);
    
    /**
     * 根据房产ID，获取车位信息
     * @param list
     * @return
     */
    List<OwnerHouseCarportExtendInfo> selectCarportExtendInfoList(List<Long> list);
}