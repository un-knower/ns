package com.newsee.owner.dao;

import java.util.List;
import java.util.Map;

import com.newsee.owner.entity.OwnerHousePublicAreaInfo;

public interface OwnerHousePublicAreaInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHousePublicAreaInfo record);

    int insertSelective(OwnerHousePublicAreaInfo record);

    OwnerHousePublicAreaInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHousePublicAreaInfo record);

    int updateByPrimaryKey(OwnerHousePublicAreaInfo record);
    
    OwnerHousePublicAreaInfo loadHouseByShortName(Map<String, Object> map);
    
    OwnerHousePublicAreaInfo loadHouseByNo(Map<String, Object> map);
    
    /**
     * 获取公共区域信息
     * @param list
     * @return
     */
    List<OwnerHousePublicAreaInfo> selectPublicAreaInfoList(List<Long> list);
}