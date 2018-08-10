package com.newsee.owner.dao;

import com.newsee.owner.entity.OwnerHouseHouseExtendInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OwnerHouseHouseExtendInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHouseHouseExtendInfo record);

    int insertSelective(OwnerHouseHouseExtendInfo record);

    OwnerHouseHouseExtendInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHouseHouseExtendInfo record);

    int updateByPrimaryKey(OwnerHouseHouseExtendInfo record);

    List<OwnerHouseHouseExtendInfo> listHouseExtendInfoListByHouseId(@Param("houseIdList") List<Long> houseIdList);

    /**
     * 批量插入房产扩展信息
     */
    void batchInsertHouseExtendInfoList(List<OwnerHouseHouseExtendInfo> list);

}