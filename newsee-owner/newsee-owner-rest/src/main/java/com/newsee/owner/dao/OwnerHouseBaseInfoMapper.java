package com.newsee.owner.dao;

import com.newsee.common.vo.SearchProjectVo;
import com.newsee.owner.entity.OwnerHouseBaseInfo;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OwnerHouseBaseInfoMapper {
    int deleteByPrimaryKey(Long houseId);

    int insert(OwnerHouseBaseInfo record);

    int insertSelective(OwnerHouseBaseInfo record);

    OwnerHouseBaseInfo selectByPrimaryKey(Long houseId);

    int updateByPrimaryKeySelective(OwnerHouseBaseInfo record);

    int updateByPrimaryKey(OwnerHouseBaseInfo record);

    List<OwnerHouseBaseInfo> listOwnerHouseBaseInfoByHouseIdList(@Param("houseIdList") List<Long> houseIdList);

    int batchUpdate(List<OwnerHouseBaseInfo> list);

    int batchUpdateByHouseId(Map<String, Object> params);

    List<OwnerHouseBaseInfo> listOwnerHouseBaseInfoByPath(@Param("path") String path);

    List<OwnerHouseBaseInfo> listHouseBaseInfoByParam(Map<String, Object> params);

    List<OwnerHouseBaseInfo> listHouseBaseInfo(Map<String, Object> params);

    int deleteByPath(@Param("path") String path, @Param("updateUserId") Long updateUserId, @Param("updateUserName") String updateUserName);

    int deleteById(@Param("houseId") Long houseId, @Param("updateUserId") Long updateUserId, @Param("updateUserName") String updateUserName);

    /**
     * 批量插入房产基础信息
     *
     * @param list
     */
    void batchInsertBaseInfoList(List<OwnerHouseBaseInfo> list);

    /**
     * @param @param  map
     * @param @return 设定文件
     * @return int    返回类型
     * @throws
     * @Title: updateSort
     * @Description: 修改房产节点顺序
     * @author wangrenjie
     */
    int updateSort(Map<String, Object> map);

    /**
     * @param @param  houseId
     * @param @return 设定文件
     * @return OwnerHouseBaseInfo    返回类型
     * @throws
     * @Title: checkParentByHouseId
     * @Description: 判断节点是否为父节点
     * @author wangrenjie
     */
    OwnerHouseBaseInfo checkParentByHouseId(Long houseId);

    /**
     * @param map
     * @return List<OwnerHouseBaseInfo>    返回类型
     * @Title: listHouseTreeBySearch
     * @Description: 房产树模糊查询
     * @date 2017年11月27日 下午2:06:45
     * @author wangrenjie
     */
    List<OwnerHouseBaseInfo> listHouseTreeBySearch(Map<String, Object> map);

    /**
     * @param map
     * @return int    返回类型
     * @Title: batchDelete
     * @Description: 批量删除
     * @date 2017年11月27日 下午2:07:11
     * @author wangrenjie
     */
    int batchDelete(Map<String, Object> map);


    OwnerHouseBaseInfo loadHouseByName(Map<String, Object> map);

    //============================================

    /**
     * 获取基础房产信息
     *
     * @param houseId            房产id
     * @param houseIdList
     * @param houseName          房产名
     * @param houseNameList
     * @param housePath          树节点路径，例：/1/2/3
     * @param houseTypeList      房产类型 0.初始值 1.区域 2.项目 3.组团 4.楼栋 5.单元 6.房产 7.车库 8.车位 9.公共区域
     * @param houseStage         房态当前状态：10空置  20未领  30空关  40入住
     * @param houseStageLt       小于，房态当前状态：10空置  20未领  30空关  40入住
     * @param houseStageGt       大于，房态当前状态：10空置  20未领  30空关  40入住
     * @param houseRentStage     出租状态 0未出租 1已出租
     * @param houseDecorateStage 装修状态  0未装修 1装修中 2已装修
     * @param isBlockUp          是否停用 0.否 1是
     * @return
     */
    List<OwnerHouseBaseInfo> selectHouseBaseInfoList(Map<String, Object> map);

//    List<OwnerHouseBaseInfo> listHouseForReport(Map<String, Object> map);

    //============================================
    Integer getSortByParentId(Long parentId);

    List<OwnerHouseBaseInfo> listForSearchCustomer(Map<String, Object> map);

    List<OwnerHouseBaseInfo> listHouseBase(Map<String, Object> map);

    OwnerHouseBaseInfo loadHouseByNo(Map<String, Object> map);

    int batchUpdateDecorateStageByHouseId(Map<String, Object> map);

    List<OwnerHouseBaseInfo> selectByPrecinctNameAndHouseName(Map<String,Object> map);

	List<OwnerHouseBaseInfo> searchPrecinctApp(SearchProjectVo searchVo);

	OwnerHouseBaseInfo searchParentName(Long parentId);

    List<OwnerHouseBaseInfo> searchHouseInfoByFullName(Map<String, Object> map);
}