package com.newsee.owner.dao;

import java.util.List;

import com.newsee.owner.entity.OwnerHouseStageDetail;
import com.newsee.owner.entity.OwnerHouseStageExtendInfoRent;

public interface OwnerHouseStageExtendInfoRentMapper {
    int deleteByPrimaryKey(Long detailId);

    int insert(OwnerHouseStageExtendInfoRent record);

    int insertSelective(OwnerHouseStageExtendInfoRent record);

    OwnerHouseStageExtendInfoRent selectByPrimaryKey(Long detailId);

    int updateByPrimaryKeySelective(OwnerHouseStageExtendInfoRent record);

    int updateByPrimaryKey(OwnerHouseStageExtendInfoRent record);
    
    
    OwnerHouseStageExtendInfoRent loadLastRent(Long houseId);
    
    /**
     * 
    * @Title: listAllRentByOwnerId 
    * @Description: 获取租户全部出租记录ID
    * @param ownerId
    * @return List<Long>    返回类型 
    * @date  2017年11月23日 上午9:45:36
    * @author wangrenjie
     */
    List<Long> listAllRentByOwnerId(Long ownerId);
    
    /**
     * 
    * @Title: listAllCurrentRent 
    * @Description: 获取租户当前出租记录
    * @param ownerId
    * @return List<OwnerHouseStageExtendInfoRent>    返回类型 
    * @date  2017年11月23日 上午9:49:57
    * @author wangrenjie
     */
    List<OwnerHouseStageExtendInfoRent> listAllCurrentRent(List<Long> list);
    
    /**
     * 
    * @Title: listAllRentByDetail 
    * @Description: 获取全部出租记录
    * @param list
    * @return List<OwnerHouseStageExtendInfoRent>    返回类型 
    * @date  2017年11月24日 下午1:44:43
    * @author wangrenjie
     */
    List<OwnerHouseStageExtendInfoRent> listAllRentByDetail(List<OwnerHouseStageDetail> list);
    
    /**
     * 
    * @Title: listAllRentIdByHouseId 
    * @Description: 获取房产对应的租户
    * @param list
    * @return List<Long>    返回类型 
    * @date  2017年11月27日 下午2:56:46
    * @author wangrenjie
     */
    List<Long> listAllRentIdByHouseId(List<Long> list);
    
    List<OwnerHouseStageExtendInfoRent> listRentByHouseId(Long houseId);
}