package com.newsee.owner.dao;

import java.util.List;
import java.util.Map;

import com.newsee.owner.entity.OwnerHouseStageDetail;

public interface OwnerHouseStageDetailMapper {

    int insert(OwnerHouseStageDetail record);

    int insertSelective(OwnerHouseStageDetail record);

    OwnerHouseStageDetail selectByPrimaryKey(Long detailId);

    int updateByPrimaryKeySelective(OwnerHouseStageDetail record);

    int updateByPrimaryKey(OwnerHouseStageDetail record);
    
    /**
     * 
    * @Title: listStageByHouseId 
    * @Description: 获取房产操作信息
    * @param houseId
    * @return List<OwnerHouseStageDetail>    返回类型 
    * @date  2017年11月14日 上午10:12:26
    * @author wangrenjie
     */
    List<OwnerHouseStageDetail> listStageByHouseId(Long houseId);
    /**
     * 
    * @Title: listStage 
    * @Description: 获取房产操作信息
    * @param map
    * @return List<OwnerHouseStageDetail>    返回类型 
    * @date  2017年11月14日 上午10:15:13
    * @author wangrenjie
     */
    List<OwnerHouseStageDetail> listStage(Map<String, Object> map);
    
    /**
     * 
    * @Title: loadRentForSublet 
    * @Description: 获取转租操作的出租记录
    * @param map
    * @return OwnerHouseStageDetail    返回类型 
    * @date  2017年11月23日 下午4:29:14
    * @author wangrenjie
     */
    OwnerHouseStageDetail loadRentForSublet(Map<String, Object> map);
    
    /**
     * 
    * @Title: deleteStageDetail 
    * @Description: 删除房态操作
    * @param detailId
    * @return int    返回类型 
    * @date  2017年11月24日 下午4:47:35
    * @author wangrenjie
     */
    int deleteStageDetail(Map<String, Object> map);
    
    OwnerHouseStageDetail loadDetail(Map<String, Object> map);
    
    int updateNowDetail(Map<String, Object> map);
}