package com.newsee.owner.dao;

import java.util.List;
import java.util.Map;

import com.newsee.owner.entity.OwnerHouseCarportInfo;

public interface OwnerHouseCarportInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHouseCarportInfo record);

    int insertSelective(OwnerHouseCarportInfo record);

    OwnerHouseCarportInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHouseCarportInfo record);

    int updateByPrimaryKey(OwnerHouseCarportInfo record);
    
    OwnerHouseCarportInfo loadHouseByShortName(Map<String, Object> map);
    
    OwnerHouseCarportInfo loadHouseByNo(Map<String, Object> map);
    
    /**
     * 根据房产ID，获取车位信息
     * @param list
     * @return
     */
    List<OwnerHouseCarportInfo> selectCarportInfoList(List<Long> list);
}