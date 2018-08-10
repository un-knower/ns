package com.newsee.owner.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.newsee.owner.entity.OwnerCarsInfo;

@Repository
public interface OwnerCarsInfoDao {

   /* int deleteByOwnerId(Long ownerId);*/

    /**
     * 批量插入车辆信息
     * @param cars
     * @return
     */
    int insertBatch(List<OwnerCarsInfo> cars);

    List<OwnerCarsInfo> selectByOwnerId(Long ownerId);

    int updateDelStatusOwnerCarByOwnerId(Long ownerId);

    int updateBatdchDelStatusOwnerCarByOwnerId(List<Long> ownerIds);

}
