package com.newsee.owner.dao;

import com.newsee.owner.entity.OwnerHouseEntranceGuardCard;

public interface OwnerHouseEntranceGuardCardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OwnerHouseEntranceGuardCard record);

    int insertSelective(OwnerHouseEntranceGuardCard record);

    OwnerHouseEntranceGuardCard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OwnerHouseEntranceGuardCard record);

    int updateByPrimaryKey(OwnerHouseEntranceGuardCard record);
}