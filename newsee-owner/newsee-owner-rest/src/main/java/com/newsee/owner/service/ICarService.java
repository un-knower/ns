package com.newsee.owner.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.owner.vo.CarVo;

public interface ICarService {

	/**
	 * 
	 * @Title: listOwnerCar
	 * @Description: 获取客户车辆信息
	 * @param ownerId
	 * @return List<CarVo> 返回类型
	 * @date 2018年1月5日 下午1:19:31
	 * @author wangrenjie
	 */
	public List<CarVo> listOwnerCar(Long ownerId);

	/**
	 * 
	 * @Title: addOwnerCar
	 * @Description: 新增车辆信息
	 * @param carVo
	 * @return Long 返回类型
	 * @date 2018年1月5日 下午1:19:58
	 * @author wangrenjie
	 */
	public Long addOwnerCar(CarVo carVo);

	/**
	 * 
	 * @Title: detailOwnerCar
	 * @Description: 查询车辆详情
	 * @param ownerCarId
	 * @return CarVo 返回类型
	 * @date 2018年1月5日 下午1:20:05
	 * @author wangrenjie
	 */
	public CarVo detailOwnerCar(Long ownerCarId);

	/**
	 * 
	 * @Title: listOwnerCarForSearch
	 * @Description: 获取车辆列表
	 * @param searchVo
	 * @return PageInfo<CarVo> 返回类型
	 * @date 2018年1月5日 下午1:20:09
	 * @author wangrenjie
	 * @throws Exception
	 */
	public PageInfo<CarVo> listOwnerCarForSearch(SearchVo searchVo, Map<String, Object> columnMap, boolean pageFlag)
			throws Exception;

	public CarVo getTotal(SearchVo searchConditionVo, Map<String, Object> columnMap) throws Exception;

	/**
	 * 
	 * @Title: deleteOwnerCar
	 * @Description: 批量删除车辆
	 * @param ownerCarIdList
	 * @param userId
	 * @return int 返回类型
	 * @date 2018年1月5日 下午1:20:13
	 * @author wangrenjie
	 */
	public int deleteOwnerCar(List<Long> ownerCarIdList, Long userId, String userName);

	/**
	 * 
	 * @Title: deleteAllOwnerCar
	 * @Description: 根据查询条件删除全部车辆
	 * @param searchVo
	 * @param userId
	 * @return int 返回类型
	 * @date 2018年1月5日 下午1:20:16
	 * @author wangrenjie
	 * @throws Exception
	 */
	public int deleteAllOwnerCar(SearchVo searchVo, Long userId, String userName) throws Exception;

	/**
	 * 
	 * @Title: checkCarNumberOnly
	 * @Description: 校验车辆车牌号唯一性
	 * @param organizationId
	 * @param carNumber
	 * @return boolean 返回类型
	 * @date 2018年1月5日 下午1:20:20
	 * @author wangrenjie
	 */
	public boolean checkCarNumberOnly(CarVo carVo);
}
