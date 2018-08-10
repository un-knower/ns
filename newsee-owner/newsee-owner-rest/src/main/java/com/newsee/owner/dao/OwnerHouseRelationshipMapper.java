package com.newsee.owner.dao;

import java.util.List;
import java.util.Map;

import com.newsee.owner.entity.OwnerHouseRelationship;
import com.newsee.owner.entity.OwnerHouseStageDetail;
import com.newsee.owner.vo.OwnerHouseRelationshipVo;

public interface OwnerHouseRelationshipMapper {
    int deleteByPrimaryKey(Long houseOwnerRelationshipId);

    int insertBatch(List<OwnerHouseRelationship> list);

    int insertSelective(OwnerHouseRelationship record);

    OwnerHouseRelationship selectByPrimaryKey(Long houseOwnerRelationshipId);

    int updateByPrimaryKeySelective(OwnerHouseRelationship record);

    int updateByPrimaryKey(OwnerHouseRelationship record);
    
    /**
     * 
    * @Title: listOwnerPrecinctRelation 
    * @Description: 获取业主房产项目关系 
    * @param ownerId
    * @return List<OwnerHouseRelationshipVo>    返回类型 
    * @date  2017年11月15日 下午1:53:54
    * @author wangrenjie
     */
    List<OwnerHouseRelationshipVo> listOwnerPrecinctRelation(Long ownerId);

    /**
     * 
    * @Title: listRentPrecinctRelation 
    * @Description: 获取租户房产项目关系
    * @param ownerId
    * @return List<OwnerHouseRelationshipVo>    返回类型 
    * @date  2017年11月15日 下午1:54:12
    * @author wangrenjie
     */
    List<OwnerHouseRelationshipVo> listRentPrecinctRelation(Long ownerId);
    
    List<OwnerHouseRelationshipVo> listOwnerAllHouseRelation(Long ownerId);
    
    List<OwnerHouseRelationshipVo> listOwnerAllHouseRelationByList(Map<String, Object> map);
    
    List<OwnerHouseRelationshipVo> listRentAllHouseRelation(Long ownerId);

    List<OwnerHouseRelationshipVo> listRentAllHouseRelationByList(Map<String, Object> map);
    /**
     * 
    * @Title: updateMainHouse 
    * @Description: 更新主房产
    * @param @param record
    * @param @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author wangrenjie
     */
//    int updateMainHouse(Map<String, Object> map);
    
    /**
     * 
    * @Title: listRelationByHouseId 
    * @Description: 获取业主房产关联关系
    * @param houseId
    * @return OwnerHouseRelationshipVo   返回类型 
    * @date  2017年11月15日 下午2:05:16
    * @author wangrenjie
     */
    OwnerHouseRelationship loadOwnerRelationByHouseId(Long houseId);
    
    /**
     * 
    * @Title: listOwnerRelationHistoryByHouseId 
    * @Description: 获取业主历史房产关联关系
    * @param houseId
    * @return List<OwnerHouseRelationship>    返回类型 
    * @date  2017年11月15日 下午2:27:36
    * @author wangrenjie
     */
    List<OwnerHouseRelationship> listOwnerRelationHistoryByHouseId(Long houseId);
    
    /**
     * 
    * @Title: listDecorateRelationByHouseId 
    * @Description: 获取房产装修记录
    * @param List<OwnerHouseStageDetail> list
    * @return List<OwnerHouseRelationshipVo>    返回类型 
    * @date  2017年11月15日 下午2:53:37
    * @author wangrenjie
     */
    List<OwnerHouseRelationshipVo> listDecorateRelationByDetailId(List<OwnerHouseStageDetail> list);
    
    /**
     * 
    * @Title: listRentRelationByDetailId 
    * @Description: 获取房产出租记录
    * @param list
    * @return List<OwnerHouseRelationshipVo>    返回类型 
    * @date  2017年11月15日 下午3:02:27
    * @author wangrenjie
     */
    List<OwnerHouseRelationshipVo> listRentRelationByDetailId(List<OwnerHouseStageDetail> list);
    /**
     * 
    * @Title: loadOwnerIdByHouseId 
    * @Description: 获取房产的业主ID
    * @param houseId
    * @return Long    返回类型 
    * @date  2017年11月15日 下午4:10:21
    * @author wangrenjie
     */
    Long loadOwnerIdByHouseId(Long houseId);
    
    /**
     * 
    * @Title: listOwnerHouseRelation 
    * @Description: 获取业主项目下房产关系
    * @param map
    * @return List<OwnerHouseRelationshipVo>    返回类型 
    * @date  2017年11月15日 下午4:56:40
    * @author wangrenjie
     */
    List<OwnerHouseRelationshipVo> listOwnerHouseRelation(Map<String, Object> map);
    
    /**
     * 
    * @Title: listRentHouseRelation 
    * @Description: 获取租户项目下房产关系
    * @param map
    * @return List<OwnerHouseRelationshipVo>    返回类型 
    * @date  2017年11月15日 下午4:57:25
    * @author wangrenjie
     */
    List<OwnerHouseRelationshipVo> listRentHouseRelation(Map<String, Object> map);
    
    /**
     * 
    * @Title: loadOwnerHouseByPrecinctId 
    * @Description: 根据项目ID获取客户房产关系
    * @param ownerId
    * @param precinctId
    * @return OwnerHouseRelationshipVo    返回类型 
    * @date  2017年11月16日 下午1:28:21
    * @author wangrenjie
     */
    List<OwnerHouseRelationship> listOwnerHouseByPrecinctId(Map<String, Object> map);
    
    /**
     * 
    * @Title: deleteRelation 
    * @Description: 删除客户房产关系
    * @param map
    * @return int    返回类型 
    * @date  2017年11月16日 下午3:01:14
    * @author wangrenjie
     */
    int deleteRelation(Map<String, Object> map);
    
    /**
     * 
    * @Title: listOwnerIdByHouseId 
    * @Description: 获取房产关联的客户ID 
    * @param houseId
    * @return List<Long>    返回类型 
    * @date  2017年11月16日 下午3:01:28
    * @author wangrenjie
     */
    List<Long> listOwnerIdByHouseId(Long houseId);
    
    /**
     * 
    * @Title: listOwnerByHouseId 
    * @Description: 获取当前记录客户房产关联关系
    * @param houseId
    * @param detailId
    * @return List<OwnerHouseRelationship>    返回类型 
    * @date  2017年11月17日 上午11:25:24
    * @author wangrenjie
     */
    List<OwnerHouseRelationship> listOwnerByHouseId(Map<String, Object> map);
    
    /**
     * 
    * @Title: listAllOwnerIdByHouseId 
    * @Description: 获取房产对应的业主
    * @param list
    * @return List<Long>    返回类型 
    * @date  2017年11月27日 下午2:56:24
    * @author wangrenjie
     */
    List<Long> listAllOwnerIdByHouseId(List<Long> list);
    /**
     * 
    * @Title: editCurrentRecordFalse 
    * @Description: 撤销房产当前记录状态
    * @param map
    * @return int    返回类型 
    * @date  2017年11月17日 上午9:49:30
    * @author wangrenjie
     */
    int editCurrentRecordFalse(Map<String, Object> map);
    
    /**
     * 
    * @Title: editCurrentRecordTrue 
    * @Description: 根据detailId更新房产关系为当前记录
    * @param map
    * @return int    返回类型 
    * @date  2017年11月17日 上午9:50:17
    * @author wangrenjie
     */
    int editCurrentRecordTrue(Map<String, Object> map);
    /**
     * 
    * @Title: insertBatchForCurrent 
    * @Description: 新增当前记录 
    * @param map
    * @return int    返回类型 
    * @date  2017年11月21日 上午9:49:32
    * @author wangrenjie
     */
    int insertBatchForCurrent(Map<String, Object> map);
    
    /**
     * 
    * @Title: deleteRelationByDetail 
    * @Description: 根据detailId删除客户房产关系
    * @param map
    * @return int    返回类型 
    * @date  2017年11月21日 上午9:49:59
    * @author wangrenjie
     */
    int deleteRelationByDetail(Map<String, Object> map);
    
    /**
     * 
    * @Title: listAllDetailId
    * @Description: 获取房产业主/产权人对应所有房态操作
    * @param houseId
    * @return List<Long>    返回类型 
    * @date  2017年11月22日 下午2:00:50
    * @author wangrenjie
     */
    List<Long> listAllDetailId(Map<String, Object> map);
    
    List<OwnerHouseRelationship > listAllRelationHistory(Map<String, Object> map);
    
    List<OwnerHouseRelationshipVo> listPrecinctHouseByOwner(Long ownerId);
    
    List<OwnerHouseRelationshipVo> listPrecinctHouseByRent(Long ownerId);
    
    List<Long> listOwnerForSearchCustomer(List<Long> precinctList);
    List<Long> listRentForSearchCustomer(List<Long> precinctList);
    List<Long> listOwnerForSearchCustomerBySearchVo(Map<String, Object> map);
    List<Long> listRentForSearchCustomerBySearchVo(Map<String, Object> map);
    List<Long> listPrecinctForSearchCustomer(List<Long> ownerIdList);
    List<Long> listRentPrecinctForSearchCustomer(List<Long> ownerIdList);
    
    /**
     * 根据房产Id和类型查询
     * @param map houserId:房产Id,ownerProperty:类型
     * @return
     */
    List<Long> listByHouseIdAndProperty(Map<String, Object> map);
}