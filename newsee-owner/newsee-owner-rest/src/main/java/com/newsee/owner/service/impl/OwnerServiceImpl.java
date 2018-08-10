package com.newsee.owner.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;
import com.newsee.owner.dao.HouseOwnerDao;
import com.newsee.owner.dao.OwnerCarsInfoDao;
import com.newsee.owner.entity.HouseOwner;
import com.newsee.owner.entity.OwnerCarsInfo;
import com.newsee.owner.service.IOwnerService;
import com.newsee.owner.vo.HouseOwnerVo;
import com.newsee.owner.vo.OwnerSerchConditionVo;


/** 
 * 如果方法有内部有数据库操作，则必须指定@WriteDataSource还是@ReadDataSource 
 * 注：AOP ，内部方法之间互相调用时，如果是this.xxx()这形式，不会触发AOP拦截，可能会 
 * 导致无法决定数据库是走写库还是读库 
 * 方法： 
 * 为了触发AOP的拦截，调用内部方法时，需要特殊处理下，看方法getService() 
 * @author 胡乾亮
 * 
 */
@Service
@Transactional
public class OwnerServiceImpl implements IOwnerService{
	
	@Autowired
	private HouseOwnerDao houseOwnerDao;
	
	@Autowired
	private OwnerCarsInfoDao ownerCarsInfoDao;
	

	@WriteDataSource
	@Override
	public Long addOwner(HouseOwnerVo houseOwnerVo) throws Exception {
		//冗余字段
		List<OwnerCarsInfo> ownerCarsInfoList = houseOwnerVo.getOwnerCarsInfoList();
		if(CollectionUtils.isNotEmpty(ownerCarsInfoList)){
			houseOwnerVo.setPlateNo(ownerCarsInfoList.get(0).getPlateNo());	
		}

		int ownerNum = houseOwnerDao.insertSelective(houseOwnerVo);
		if(ownerNum > 0){
			//新增该业主的车辆
			if(CollectionUtils.isNotEmpty(ownerCarsInfoList)){
				for(OwnerCarsInfo car : ownerCarsInfoList){
					car.setHouseOwnerId(houseOwnerVo.getId());
					car.setOwnerName(houseOwnerVo.getOwnerName());
					car.setOrganizationId(houseOwnerVo.getOrganizationId());
					car.setCreateUserId(houseOwnerVo.getCreateUserId());
					car.setCreateTime(houseOwnerVo.getCreateTime());
				}
				ownerCarsInfoDao.insertBatch(ownerCarsInfoList);
			}
		}
		return houseOwnerVo.getId();
	} 

	@WriteDataSource
	@Override
	public int deleteOwnerById(Long ownerId) throws Exception {
		//删除业主
		HouseOwner owner = new HouseOwner();
		owner.setId(ownerId);
		owner.setDelStatus((byte) 1);
		int num = houseOwnerDao.updateByPrimaryKeySelective(owner);
		if(num > 0){
			//删除该业主的车辆
			ownerCarsInfoDao.updateDelStatusOwnerCarByOwnerId(ownerId);
		}
		return num;
	}

	@WriteDataSource
	@Override
	public int deleteBatchOwnerByIds(List<Long> ownerIds) throws Exception {
		//删除业主
		HouseOwnerVo ownervo = new HouseOwnerVo();
		ownervo.setIds(ownerIds);
		ownervo.setDelStatus((byte) 1);
		int num = houseOwnerDao.updateBatchByIds(ownervo);
		if(num > 0){
			ownerCarsInfoDao.updateBatdchDelStatusOwnerCarByOwnerId(ownerIds);
		}
		return num;
	}

	@WriteDataSource
	@Override
	public int editOwnerById(HouseOwnerVo houseOwnerVo) throws Exception {
		//冗余字段
		List<OwnerCarsInfo> ownerCarsInfoList = houseOwnerVo.getOwnerCarsInfoList();
		if(CollectionUtils.isNotEmpty(ownerCarsInfoList)){
			houseOwnerVo.setPlateNo(ownerCarsInfoList.get(0).getPlateNo());	
		}
		//编辑业主
		int num = houseOwnerDao.updateByPrimaryKeySelective(houseOwnerVo);
		if(num > 0){
			ownerCarsInfoDao.updateDelStatusOwnerCarByOwnerId(houseOwnerVo.getId());
			if(CollectionUtils.isNotEmpty(ownerCarsInfoList)){
				for(OwnerCarsInfo car : ownerCarsInfoList){
					car.setHouseOwnerId(houseOwnerVo.getId());
					car.setProjectId(1L);
					car.setOrganizationId(houseOwnerVo.getOrganizationId());
					car.setCreateUserId(houseOwnerVo.getCreateUserId());
					car.setCreateTime(houseOwnerVo.getCreateTime());
				}
				ownerCarsInfoDao.insertBatch(ownerCarsInfoList);
			}
		}
		return num;
	}

	@ReadDataSource
	@Override
	public List<HouseOwnerVo> selectHouseOwnerVoList(List<Long> ownerIds) {
		return houseOwnerDao.loadByIdList(ownerIds);
	}


	@ReadDataSource
	@Override
	public PageInfo<HouseOwnerVo> listPage(OwnerSerchConditionVo vo) throws Exception {
        PageInfo<HouseOwnerVo> pageInfo = null;
        if (vo != null) {
            PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
            List<HouseOwnerVo> list = houseOwnerDao.loadList(vo);      
            pageInfo = new PageInfo<>(list);
        }
        return pageInfo;
	}

	@ReadDataSource
	@Override
	public HouseOwnerVo detail(Long ownerId) throws Exception {
	    HouseOwnerVo owner = null;
        if (ownerId != null) {
            owner = houseOwnerDao.loadById(ownerId);
            owner.setOwnerCarsInfoList(ownerCarsInfoDao.selectByOwnerId(ownerId));
        }
        return owner;
	}
}
