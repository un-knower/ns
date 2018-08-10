package com.newsee.owner.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchProjectVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.owner.entity.HouseListEntity;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import com.newsee.owner.entity.OwnerHouseHouseInfo;
import com.newsee.owner.vo.AddHouseVo;
import com.newsee.owner.vo.CustomerVo;
import com.newsee.owner.vo.DecorateVo;
import com.newsee.owner.vo.HouseBaseInfoTreeNode;
import com.newsee.owner.vo.HouseBuildingVo;
import com.newsee.owner.vo.HouseCarportVo;
import com.newsee.owner.vo.HouseCombineVo;
import com.newsee.owner.vo.HouseListVo;
import com.newsee.owner.vo.HouseProjectVo;
import com.newsee.owner.vo.HouseRoomVo;
import com.newsee.owner.vo.HouseSplitInfoVo;
import com.newsee.owner.vo.HouseStageDetailVo;
import com.newsee.owner.vo.OwnerClusterInfoVo;
import com.newsee.owner.vo.OwnerGarageInfoVo;
import com.newsee.owner.vo.OwnerHouseRelationshipVo;
import com.newsee.owner.vo.OwnerPublicAreaVo;
import com.newsee.owner.vo.OwnerUnitInfoVo;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsSystemOrganizationVo;

/**
 * Created by niyang on 2017/9/12.
 */
public interface IHouseService {

    /**
     * 添加房产节点
     *
     * @param organizationId
     * @param parentId
     * @param houseTypeEnum
     * @param name
     * @param userId
     * @param houseInfo
     */
    public Long addHouseNode(AddHouseVo addHouseVo, Map<String, NsCoreDictionaryVo> dicMap);
    
    /**
     * 添加房产数据
     * @param houseList
     * @return
     */
    public List<OwnerHouseBaseInfo> addHouse(List<OwnerHouseBaseInfo> houseList);

    /**
     * 编辑房产排序
     *
     * @param houseId
     * @param parentId
     * @param userId
     * @return
     */
    public Map<String, Object> editHouseSort(Long houseId, Integer sort, Long parentId, Long userId,String userName, List<Long> sortHouseIdList);

    /**
     * 获取房产树
     *
     * @param organizationId
     * @return
     */
    public List<HouseBaseInfoTreeNode> listHouseTree(Long organizationId);
    
    public List<HouseBaseInfoTreeNode> listHouseTreeForStandard(Long organizationId);
    /**
     * 
    * @Title: listHouseChildTree 
    * @Description: 获取房产树子节点
    * @param @param organizationId
    * @param @param parentId
    * @param @return    设定文件 
    * @return List<HouseBaseInfoTreeNode>    返回类型 
    * @throws 
    * @author wangrenjie
     */
    public List<HouseBaseInfoTreeNode> listHouseChildTree(Long organizationId, Long houseId);

    /**
     * 删除房产节点
     *
     * @param houseId
     * @return
     */
    public Map<String, Object> deleteHouseNode(Long houseId, Long userId,String userName);
    
    /**
     * 
    * @Title: deleteHouseBatch 
    * @Description: 批量删除房产
    * @param @param houseIdList
    * @param @param userId
    * @param @return    设定文件 
    * @return Boolean    返回类型 
    * @throws 
    * @author wangrenjie
     */
    public Map<String, Object> deleteHouseBatch(List<Long> houseIdList, Long userId,String userName);

    /**
     * 
    * @Title: deleteAllHouseBySearch 
    * @Description: 删除全部房产 
    * @param @param searchVo
    * @param @param userId
    * @param @return    设定文件 
    * @return Map<String,Object>    返回类型 
    * @throws 
    * @author wangrenjie
     */
    public Map<String, Object> deleteAllHouseBySearch(SearchVo searchVo, Long userId,String userName) throws Exception;
    /**
     * 锁定房间
     *
     * @param isLock      true锁定 false解锁
     * @param houseIdList
     * @param userId
     * @return
     */
    public Map<String, Object> editToggleLockHouse(Boolean isLock, List<Long> houseIdList, Long userId,String userName);

    /**
     * 
    * @Title: editLockAllHouseBySearch 
    * @Description: 锁定全部房产 
    * @param @param searchVo
    * @param @param userId
    * @param @return    设定文件 
    * @return Boolean    返回类型 
    * @throws 
    * @author wangrenjie
     */
    public Map<String, Object> editLockAllHouseBySearch(Boolean isLock, SearchVo searchVo, Long userId,String userName) throws Exception ;
    /**
     * 房间列表
     * @param searchConditionVo
     * @return
     */
    public PageInfo<HouseListVo> listHouse(SearchVo searchConditionVo, Map<String, Object> columnMap, boolean pageFlag) throws Exception;

    public HouseListVo getTotal(SearchVo searchConditionVo, Map<String, Object> columnMap) throws Exception;
    /**
     * 房间合并
     * @param houseCombineVo
     * @param userId
     */
    public Long editHouseCombine(HouseCombineVo houseCombineVo, Long userId, Long organizationId);

    /**
     * 房间拆封
     * @param houseSplitVoList
     * @param userId
     */
    public Boolean editHouseSplit(Long houseId,List<HouseSplitInfoVo> houseSplitVoList,Long userId,String userName);
    /**
     * @param @param  houseId 房产ID
     * @param @param  houseType 房产类型
     * @param @    设定文件
     * @return Map<String,Object>    返回类型
     * @throws
     * @Title: detailHouseByHouseId
     * @Description: 查询房产详情
     * @author wangrenjie
     */
    public Map<String, Object> detailHouseByHouseId(Long houseId, Long ownerId) ;

    /**
     * @param @param  projectInfoVo
     * @param @return
     * @param @    设定文件
     * @return int    返回类型
     * @throws
     * @Title: addProjectDetail
     * @Description: 保存项目信息
     * @author wangrenjie
     */
    public int addProjectDetail(HouseProjectVo projectInfoVo,Long userId,String userName) ;
    /**
     * 
    * @Title: addClusterDetail 
    * @Description: 保存组团信息
    * @param @param clusterInfoVo
    * @param @return
    * @param @    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author wangrenjie
     */
    public int addClusterDetail(OwnerClusterInfoVo clusterInfoVo,Long userId,String userName) ;
    /**
     * 
    * @Title: addBuildingDetail 
    * @Description: 保存楼栋信息 
    * @param @param buildingInfoVo
    * @param @return
    * @return int    返回类型
    * @throws 
    * @author wangrenjie
     */
    public int addBuildingDetail(HouseBuildingVo buildingVo,Long userId,String userName) ;

    /**
     * 
    * @Title: addUnitDetail 
    * @Description: 保存单元信息
    * @param @param unitInfoVo
    * @param @return
    * @param @    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author wangrenjie
     */
    public int addUnitDetail(OwnerUnitInfoVo unitInfoVo,Long userId,String userName);
    /**
     * @param @param  roomInfoVo
     * @param @return
     * @param @    设定文件
     * @return int    返回类型
     * @throws
     * @Title: addRoomDetail
     * @Description: 保存房间信息
     * @author wangrenjie
     */
    public int addRoomDetail(HouseRoomVo roomInfoVo,Long userId,String userName);
    /**
     * 
    * @Title: addGarageDetail 
    * @Description: 保存车库信息 
    * @param @param garageInfoVo
    * @param @return
    * @param @    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author wangrenjie
     */
    public int addGarageDetail(OwnerGarageInfoVo garageInfoVo,Long userId,String userName) ;
    /**
     * @param @param  carportInfoVo
     * @param @return
     * @param @    设定文件
     * @return int    返回类型
     * @throws
     * @Title: addCarportDetail
     * @Description: 保存车位信息
     * @author wangrenjie
     */
    public int addCarportDetail(HouseCarportVo carportInfoVo,Long userId,String userName) ;
    
    /**
     * 
    * @Title: addPublicAreaDetail 
    * @Description: 保存公共区域信息
    * @param @param publicAreaVo
    * @param @param userId
    * @param @return    设定文件 
    * @return int    返回类型 
    * @throws 
    * @author wangrenjie
     */
    public int addPublicAreaDetail(OwnerPublicAreaVo publicAreaVo, Long userId,String userName);

    /**
     * 新增装修
     *
     * @param decorateVo
     * @param userId
     * @return
     */
    public Boolean addDecorate(DecorateVo decorateVo, Long userId);

    /**
     * 
    * @Title: listHouseTreeBySearch 
    * @Description: 模糊查询房产树节点
    * @param @param houseName
    * @param @param organizationId
    * @param @return    设定文件 
    * @return List<HouseBaseInfoTreeNode>    返回类型 
    * @throws 
    * @author wangrenjie
     */
    public List<HouseBaseInfoTreeNode> listHouseTreeBySearch(String houseName, Long organizationId, Byte houseType);
    
    /**
     * 
    * @Title: detailHouseInTree 
    * @Description: 查询指定节点的房产树
    * @param houseId
    * @return HouseBaseInfoTreeNode    返回类型 
    * @date  2017年11月27日 下午4:40:04
    * @author wangrenjie
     */
    public HouseBaseInfoTreeNode detailHouseInTree(Long houseId);
    
    /**
     * 
    * @Title: listOwnerHouseRelation 
    * @Description: 获取客户在当前项目中的房产关系
    * @param ownerId
    * @param houseType
    * @param precinctId
    * @return List<OwnerHouseRelationshipVo>    返回类型 
    * @date  2017年11月27日 下午4:41:11
    * @author wangrenjie
     */
    public List<OwnerHouseRelationshipVo> listOwnerHouseRelation(Long ownerId, Byte houseType, Long precinctId);
    /**
     * 
    * @Title: listOwnerPrecinctRelation 
    * @Description: 获取客户的房产项目列表
    * @param ownerId
    * @return List<OwnerHouseRelationshipVo>    返回类型 
    * @date  2017年11月27日 下午4:41:17
    * @author wangrenjie
     */
    public List<OwnerHouseRelationshipVo> listOwnerPrecinctRelation(Long ownerId);
    
    public List<OwnerHouseBaseInfo> listOwnerPrecinct(CustomerVo customerVo);

    /**
     * 
    * @Title: loadAllRelationForOwner 
    * @Description: 获取业主当前房产关系
    * @param ownerId
    * @param houseType
    * @param houseId
    * @return List<OwnerHouseRelationshipVo>    返回类型 
    * @date  2017年11月27日 下午4:41:20
    * @author wangrenjie
     */
//    public List<OwnerHouseRelationshipVo> loadAllRelationForOwner(Long ownerId, Byte houseType, Long houseId);
    /**
     * 
    * @Title: listStageByhouseId 
    * @Description: 获取房态操作列表
    * @param houseId
    * @param ownerId
    * @return Map<String,List<HouseStageDetailVo>>    返回类型 
    * @date  2017年11月27日 下午4:41:24
    * @author wangrenjie
     */
    public Map<String, List<HouseStageDetailVo>> listStageByhouseId(Long houseId, Long ownerId);
    /**
     * 
    * @Title: getOwnerIdByhouseId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param houseId
    * @return Long    返回类型 
    * @date  2017年11月27日 下午4:41:28
    * @author wangrenjie
     */
    public Long getOwnerIdByhouseId(Long houseId);
    
    /**
     * 
    * @Title: importHouse 
    * @Description: 房产导入，进行相关保存操作
    * @return int    返回类型 
    * @date  2017年11月27日 下午4:44:25
    * @author wangrenjie
     */
    public Map<String, Object> importHouse(List<Object> list, Long userId, String userName, Byte updateFlag, Map<String, Object> excelValueMap, List<Long> orgHouseIdList, List<NsSystemOrganizationVo> organizationVoList);
    
    /**
     * 
    * @Title: importCustomer 
    * @Description: 房产导入 保存客户
    * @param list
    * @param enterpriseId
    * @param organizationId
    * @param userId
    * @param updateFlag
    * @param houseId
    * @return Long    返回类型 
    * @date  2017年12月1日 下午1:33:11
    * @author wangrenjie
     */
    public Map<String, Object> importCustomer(List<Object> list, Long enterpriseId, Long organizationId, Long userId, String userName, Byte updateFlag, Long houseId, Map<String, Object> excelValueMap);
    
    /**
     * 
    * @Title: importHouseStageOperate 
    * @Description: 房产导入 保存房态操作
    * @param list
    * @param enterpriseId
    * @param organizationId
    * @param userId
    * @param updateFlag
    * @param houseId
    * @param ownerId
    * @return Long    返回类型 
    * @date  2017年12月1日 下午1:33:08
    * @author wangrenjie
     */
    public Map<String, Object> importHouseStageOperate(List<Object> list, Long enterpriseId, Long organizationId, Long userId, String userName, Byte updateFlag, Long houseId, Long ownerId, Map<String, Object> excelValueMap);

    /**
     * 
    * @Title: listPrecinctHouse 
    * @Description: 获取客户项目房产关系
    * @param ownerId
    * @return List<PrecinctHouseVo>    返回类型 
    * @date  2017年12月1日 下午1:33:05
    * @author wangrenjie
     */
    public Map<String, Object> listPrecinctHouse(Long ownerId, Map<String, NsCoreDictionaryVo> dicMap);
    
    /**
     * 
    * @Title: checkHouseOnly 
    * @Description: 验证房产唯一性 
    * @param addHouseVo
    * @param precinctId void    返回类型 
    * @date  2017年12月20日 上午9:40:53
    * @author wangrenjie
     */
    public void checkHouseOnly(AddHouseVo addHouseVo, Long precinctId);
 
    public List<Map<String, Object>> listSlaveHouse(Long houseId);
    
    /**
     * 
    * @Title: editBlockUpHouse 
    * @Description: 房产停用 
    * @param isBlockUp
    * @param houseIdList
    * @param userId
    * @return Boolean    返回类型 
    * @date  2017年12月25日 上午9:27:53
    * @author wangrenjie
     */
    public Map<String, Object> editBlockUpHouse(Boolean isBlockUp, List<Long> houseIdList, Long userId,String userName);

    /**
     * 
    * @Title: editBlockUpAllHouseBySearch 
    * @Description: 批量房产停用
    * @param isBlockUp
    * @param searchVo
    * @param userId
    * @return Boolean    返回类型 
    * @date  2017年12月25日 上午9:28:06
    * @author wangrenjie
     */
    public Map<String, Object> editBlockUpAllHouseBySearch(Boolean isBlockUp, SearchVo searchVo, Long userId,String userName) throws Exception;
    
    public List<OwnerHouseBaseInfo> listPrecinct(Long enterpriseId, Long organizationId);
    
    public List<HouseListEntity> listAllLeafNode(Long houseId);

	/**
	 * 查询houseId节点下的所有子节点
	 * @param houseId 父id
	 * @param enterpriseId 父id
	 * @param organizationId 组织id
	 * @return 所有子节点
	 */
	public List<OwnerHouseBaseInfo> listAllChildNode(Long houseId, Long enterpriseId, Long organizationId, List<String> houseTypes);

    /**
     * 获取房产信息
     * @param houseId
     * @return
     */
    OwnerHouseBaseInfo getHouseInfo(Long houseId);

    /**
     * 根据项目名称和房产简称查询房产信息
     * @param precinctName
     * @param houseName
     * @return
     */
    List<OwnerHouseBaseInfo> searchHouseInfo(String precinctName, String houseName);

    /**
     * 查询房产扩展信息
     * @param houseId
     * @return
     */
    OwnerHouseHouseInfo getHouseHouseInfo(Long houseId);

	List<OwnerHouseBaseInfo> searchPrecinctApp(SearchProjectVo searchVo);

    /**
     * 根据房产全称查询房产信息
     * @param enterpriseId
     * @param organizationId
     * @param fullName
     * @return
     */
    List<OwnerHouseBaseInfo> searchHouseInfoByFullName(Long enterpriseId, Long organizationId, String fullName);

	List<HouseListEntity> searchHouseTreeApp(SearchProjectVo searchVo);
}
