package com.newsee.owner.dao;

import java.util.List;

import com.newsee.owner.entity.OwnerHouseStageExtendInfoDecorate;

public interface OwnerHouseStageExtendInfoDecorateMapper {
    int deleteByPrimaryKey(Long detailId);

    int insert(OwnerHouseStageExtendInfoDecorate record);

    int insertSelective(OwnerHouseStageExtendInfoDecorate record);

    OwnerHouseStageExtendInfoDecorate selectByPrimaryKey(Long detailId);

    int updateByPrimaryKeySelective(OwnerHouseStageExtendInfoDecorate record);

    int updateByPrimaryKey(OwnerHouseStageExtendInfoDecorate record);
    
    List<OwnerHouseStageExtendInfoDecorate> listDecorateByHouseId(Long houseId);
    
    List<Long> listDecorateForTask();
}