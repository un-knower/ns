package com.newsee.owner.dao;

import java.util.List;
import java.util.Map;

import com.newsee.owner.entity.OwnerCustomerFamilyInfo;
import com.newsee.owner.entity.OwnerCustomerFamilyInfoKey;

public interface OwnerCustomerFamilyInfoMapper {
    int deleteFamily(Map<String, Object> map);
    int deleteFamilyByOwnerId(Map<String, Object> map);
    int insert(OwnerCustomerFamilyInfo record);

    int insertSelective(OwnerCustomerFamilyInfo record);

    OwnerCustomerFamilyInfo selectByPrimaryKey(OwnerCustomerFamilyInfoKey key);

    int updateByPrimaryKeySelective(OwnerCustomerFamilyInfo record);
    int updateByFamilyId(OwnerCustomerFamilyInfo record);
    
    /**
     * 
    * @Title: listFamilyByOwnerId 
    * @Description: 获取业主家庭成员
    * @param ownerId
    * @return List<OwnerCustomerFamilyInfo>    返回类型 
    * @date  2017年11月22日 下午2:04:17
    * @author wangrenjie
     */
    List<OwnerCustomerFamilyInfo> listFamilyByOwnerId(Long ownerId);

    /**
     * 
    * @Title: listAllFamilyByOwnerId 
    * @Description: 获取各业主的家庭成员
    * @param ownerId
    * @return List<Long>    返回类型 
    * @date  2017年11月22日 下午2:03:32
    * @author wangrenjie
     */
    List<Long> listAllFamilyByOwnerId(Long ownerId);
    
    /**
     * 
    * @Title: listAllFamily 
    * @Description: 根据客户IDList获取其所以家庭成员ID
    * @param list
    * @return List<Long>    返回类型 
    * @date  2017年11月27日 下午2:59:00
    * @author wangrenjie
     */
    List<Long> listAllFamilyId(List<Long> list);
    
    List<OwnerCustomerFamilyInfo> listAllFamily(List<Long> list);
    /**
     * 
    * @Title: listAllOwnerByFamily 
    * @Description: 获取家庭成员对应的业主
    * @param ownerId
    * @return List<Long>    返回类型 
    * @date  2017年11月23日 上午10:27:57
    * @author wangrenjie
     */
    List<Long> listAllOwnerByFamily(Long ownerId);
    
    List<Long> listAllRentByFamily(Long ownerId);
    
    int insertBatch(List<OwnerCustomerFamilyInfo> list);

}