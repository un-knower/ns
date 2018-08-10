package com.newsee.owner.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.newsee.owner.vo.HouseOwnerVo;
import com.newsee.owner.vo.OwnerSerchConditionVo;

public interface IOwnerService {
	
    /**
     * 
    * @Title: listPage 
    * @Description: 业主列表查询
    * @param @param vo
    * @param @return
    * @param @throws Exception    设定文件 
    * @return PageInfo<HouseOwnerVo>    返回类型 
    * @throws 
    * @author wangrenjie
     */
    PageInfo<HouseOwnerVo> listPage(OwnerSerchConditionVo vo) throws Exception;
    
    /**
     * 
    * @Title: detail 
    * @Description: 业主详情查询
    * @param @param ownerId
    * @param @return
    * @param @throws Exception    设定文件 
    * @return HouseOwner    返回类型 
    * @throws 
    * @author wangrenjie
     */
    HouseOwnerVo detail(Long ownerId) throws Exception;
    
	/**
	 * @Description: 新增业主
	 * @author: 胡乾亮
	 * @date: 2017年8月1日上午10:30:15
	 */
	Long addOwner(HouseOwnerVo houseOwnerVo) throws Exception;
	
	/**
	 * @Description: 删除业主
	 * @author: 胡乾亮
	 * @date: 2017年8月1日上午10:32:08
	 */
	int deleteOwnerById(Long ownerId) throws Exception;
	
	/**
	 * @Description: 批量删除业主
	 * @author: 胡乾亮
	 * @date: 2017年8月1日上午10:38:02
	 */
	int deleteBatchOwnerByIds(List<Long> ownerIds) throws Exception;
	
	/**
	 * @Description: 编辑业主信息
	 * @author: 胡乾亮
	 * @date: 2017年8月1日上午10:44:09
	 */
	int editOwnerById(HouseOwnerVo houseOwnerVo) throws Exception;

	/**
	 * 批量查询
	 * @param ownerIds
	 * @return
	 */
	List<HouseOwnerVo> selectHouseOwnerVoList(List<Long> ownerIds);
	
}
