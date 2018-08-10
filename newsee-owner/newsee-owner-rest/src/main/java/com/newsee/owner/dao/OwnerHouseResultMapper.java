package com.newsee.owner.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.vo.SearchProjectVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.owner.entity.HouseListEntity;

public interface OwnerHouseResultMapper {
    int deleteByPrimaryKey(Long houseResultId);

    int insert(HouseListEntity record);

    int insertSelective(HouseListEntity record);

    HouseListEntity selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(HouseListEntity record);

    int updateByPrimaryKey(HouseListEntity record);
    
    int updateByHouseId(HouseListEntity record);
    
    int deleteByHouseId(List<Long> list);
    
    int deleteCurrentRecord(Long houseId);
    
    List<HouseListEntity> listResultBySearch(SearchVo searchVo);
    
    int batchUpdateByHouseId(Map<String, Object> map);
    
    int updateCurrentRecord(HouseListEntity record);

    List<HouseListEntity> listResultBySearchForOperate(Map<String, Object> map);

    HouseListEntity selectByDetailId(Map<String, Object> map);
    
    /**
     * 根据房产ID获取最新的房产数据
     * @param houseIdList
     * @param organizationId
     * @param houseStage 10空置 20未领 30空关 40入住
     * @param houseStageGt 
     * @param houseOperateType 1售楼 2收房 3入住 4搬出 5出租 6转租 7退租 8空关 9转让 10装修 11停用
     * @param isBlockUp 是否停用 0 否，1 是
     * @param blockUpTime
     * @param handleTime
     * @return 
     */
    List<HouseListEntity> selectHouseResultInfoList(Map<String, Object> map);
    
    /**
     * @param houseIdList
     * @param organizationId
     * @param endTime  截止时间
     * @param isUnion 是否查出所有
     * @return
     */
    List<HouseListEntity> selectHouseResultSegmentInfoList(Map<String, Object> map);
    
    List<HouseListEntity> listForSearchCustomer(Map<String, Object> map);
    
    int updateByDetailId(HouseListEntity record);
    
    List<HouseListEntity> listHouseByPath(Map<String, Object> map);
    
    int batchUpdateDecorateStageByHouseId(Map<String, Object> map);

	List<HouseListEntity> searchHouseTreeApp(SearchProjectVo searchVo);
}