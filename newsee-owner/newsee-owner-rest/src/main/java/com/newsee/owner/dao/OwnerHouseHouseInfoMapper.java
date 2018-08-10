package com.newsee.owner.dao;

import com.newsee.owner.entity.OwnerHouseHouseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OwnerHouseHouseInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHouseHouseInfo record);

    int insertSelective(OwnerHouseHouseInfo record);

    OwnerHouseHouseInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHouseHouseInfo record);

    int updateByPrimaryKey(OwnerHouseHouseInfo record);

    List<OwnerHouseHouseInfo> listHouseInfoListByHouseId(@Param("houseIdList")List<Long> houseIdList);

    /**
     * 批量插入房产信息
     */
    void batchInsertHouseInfoList(List<OwnerHouseHouseInfo> list);
    
    OwnerHouseHouseInfo loadHouseByShortName(Map<String, Object> map);
    
    OwnerHouseHouseInfo loadHouseByNo(Map<String, Object> map);
}