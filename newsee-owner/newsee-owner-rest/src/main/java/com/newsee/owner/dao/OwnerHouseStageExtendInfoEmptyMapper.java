package com.newsee.owner.dao;

import com.newsee.owner.entity.OwnerHouseStageExtendInfoEmpty;

public interface OwnerHouseStageExtendInfoEmptyMapper {
    int deleteByPrimaryKey(Long detailId);

    int insert(OwnerHouseStageExtendInfoEmpty record);

    int insertSelective(OwnerHouseStageExtendInfoEmpty record);

    OwnerHouseStageExtendInfoEmpty selectByPrimaryKey(Long detailId);

    int updateByPrimaryKeySelective(OwnerHouseStageExtendInfoEmpty record);

    int updateByPrimaryKey(OwnerHouseStageExtendInfoEmpty record);
}