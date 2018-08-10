package com.newsee.bill.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.bill.dao.NsbillBillInfoDetailMapper;
import com.newsee.bill.dao.NsbillBillUsedMapper;
import com.newsee.bill.entity.NsbillBillDraw;
import com.newsee.bill.entity.NsbillBillInfoDetail;
import com.newsee.bill.entity.NsbillBillUsed;
import com.newsee.bill.service.IBillCoService;
import com.newsee.bill.service.IBillPurcService;
import com.newsee.bill.service.IBillUseManService;
import com.newsee.bill.vo.BillUseManCheckVo;
import com.newsee.bill.vo.BillUseManSearchVo;
import com.newsee.bill.vo.BillUseManThird;
import com.newsee.bill.vo.BillUseManVo;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.service.ApiListing;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class BillUseManServiceImpl implements IBillUseManService {

    @Autowired
    private NsbillBillUsedMapper nsbillBillUsedMapper;
    @Autowired
    private NsbillBillInfoDetailMapper nsbillBillInfoDetailMapper;
    @Autowired
    private IBillCoService billCoService;
    @Autowired
    private IBillPurcService billPurcService;

    /**
     * 获取票据使用管理列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    @Override
    @ReadDataSource
    public PageInfo<NsbillBillUsed> listPage(SearchVo searchVo) {
        if (searchVo == null) {
            return null;
        }
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<NsbillBillUsed> nsbillBillUseds = nsbillBillUsedMapper.listPage(searchVo);
        PageInfo pageInfo = new PageInfo(nsbillBillUseds);
        return pageInfo;
    }

    @Override
    @ReadDataSource
    public NsbillBillUsed detail(Long id) {
        // TODO Auto-generated method stub
        NsbillBillUsed nsbillused = nsbillBillUsedMapper.selectById(id);
        return nsbillused;
    }

    @Override
    @WriteDataSource
    public boolean edit(BillUseManVo vo) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @WriteDataSource
    public boolean add(BillUseManVo vo) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    @ReadDataSource
    public List<NsbillBillUsed> listPageAll(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        List<NsbillBillUsed> list = nsbillBillUsedMapper.listPage(searchVo);
        return list;
    }

    @Override
    @WriteDataSource
    public Integer enableBillUseMan(Map<String, Object> map) {
        Integer result = nsbillBillUsedMapper.enableBill(map);
        return result;
    }

    @Override
    @WriteDataSource
    public boolean delete(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    @WriteDataSource
    public boolean deleteBatch(List<Long> ids) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 使用票据：
     * 1.生成票据使用记录
     * 2.生成票据购入记录
     * 3.生成票据领用记录
     */
    @Override
    @WriteDataSource
    public boolean usedBillUseMan(BillUseManThird billUseManThird) {
        //生成票据购入记录
        NsbillBillInfoDetail billInfoDetail = createBillInfoDetail(billUseManThird);
        //生成票据领用记录
        createBillBillDraw(billInfoDetail, billUseManThird);
        //生成票据使用记录
        NsbillBillUsed billBillUsed = new NsbillBillUsed();
        billBillUsed.setBillDetailId(billInfoDetail.getId());
        BeanUtils.copyProperties(billUseManThird, billBillUsed);
        nsbillBillUsedMapper.insert(billBillUsed);
        return true;
    }

    /**
     * 创建票据领用记录
     */
    private void createBillBillDraw(NsbillBillInfoDetail billInfoDetail, BillUseManThird billUseManThird) {
        NsbillBillDraw billDraw = new NsbillBillDraw();
        billDraw.setOrganizationId(billUseManThird.getOrganizationId());
        billDraw.setEnterpriseId(billUseManThird.getEnterpriseId());
        billDraw.setBillDetailId(billInfoDetail.getId());
        billDraw.setDrawDate(billUseManThird.getCreateTime());
        billDraw.setDealUserId(billUseManThird.getCreateUserId());
        billDraw.setDealUserName(billUseManThird.getCreateUserName());
        //TODO:领用部门名称需要吗，不确定
        billDraw.setDrawUserId(billUseManThird.getCreateUserId());
        billDraw.setDrawUserName(billUseManThird.getCreateUserName());
        billDraw.setDrawNum(1L);
        billDraw.setRemark(billUseManThird.getRemark());
        billDraw.setCreateTime(billUseManThird.getCreateTime());
        billDraw.setCreateUserId(billUseManThird.getCreateUserId());
        billDraw.setCreateUserName(billUseManThird.getCreateUserName());
        billDraw.setUpdateTime(billUseManThird.getUpdateTime());
        billDraw.setUpdateUserId(billUseManThird.getUpdateUserId());
        billDraw.setUpdateUserName(billUseManThird.getUpdateUserName());
        billCoService.add(billDraw);
    }

    /**
     * 创建票据购入记录
     */
    private NsbillBillInfoDetail createBillInfoDetail(BillUseManThird billUseManThird) {
        NsbillBillInfoDetail billInfoDetail = new NsbillBillInfoDetail();
        billInfoDetail.setEnterpriseId(billUseManThird.getEnterpriseId());
        billInfoDetail.setOrganizationId(billUseManThird.getOrganizationId());
        billInfoDetail.setBillType(billUseManThird.getBillType());
        billInfoDetail.setCreateTime(billUseManThird.getCreateTime());
        billInfoDetail.setCreateUserId(billUseManThird.getCreateUserId());
        billInfoDetail.setCreateUserName(billUseManThird.getCreateUserName());
        billInfoDetail.setUpdateTime(billUseManThird.getUpdateTime());
        billInfoDetail.setUpdateUserId(billUseManThird.getUpdateUserId());
        billInfoDetail.setUpdateUserName(billUseManThird.getUpdateUserName());
        billInfoDetail.setBillDetailName(billUseManThird.getBillDetailName());
        billInfoDetail.setPageBalance(billUseManThird.getPageBalance());
        billInfoDetail.setCatalogId(billUseManThird.getCatalogId());
        billInfoDetail.setIsDraw("1");//已经被领用
        billInfoDetail.setPreNum(1L);//起初数量为1
        billInfoDetail.setDrawNum(1L);//被领用数量为1
        billInfoDetail.setUsedNum(1L);//被使用数量为1
        billInfoDetail.setDestroyNum(0L);//被废止数量0
        billInfoDetail.setCheckedNum(0L);//已被销号数量0
        billInfoDetail.setRemark(billUseManThird.getRemark());
        billPurcService.add(billInfoDetail);
        return billInfoDetail;
    }

    /**
     * 票据销号/反销号（核销）
     *
     * @param map
     * @return
     */
    @Override
    @WriteDataSource
    public Integer checkBillUseManBatch(Map<String, Object> map) {
        BillUseManSearchVo billUseManSearchVo = JSONObject.parseObject(JSON.toJSONString(map.get("searchVo")), BillUseManSearchVo.class);
        List<NsbillBillUsed> billUsedList = nsbillBillUsedMapper.listCheckBillUseManBatch(billUseManSearchVo);
        List<BillUseManCheckVo> checkVoList = JSONObject.parseArray(JSON.toJSONString(map.get("data")), BillUseManCheckVo.class);
        int sum = 0;
        for (NsbillBillUsed nsbillBillUsed : billUsedList) {
            for (BillUseManCheckVo checkVo : checkVoList) {
                if (checkVo.getBillType().equals(nsbillBillUsed.getBillType())) {
                    nsbillBillUsed.setCheckDate(checkVo.getCheckDate());
                    if (billUseManSearchVo.getIsCheck() == 0) {
                        nsbillBillUsed.setBillStatus("已核销");
                        nsbillBillUsed.setIsCheck(1);
                    } else {
                        nsbillBillUsed.setBillStatus("未核销");
                        nsbillBillUsed.setIsCheck(0);
                    }
                    int num = nsbillBillUsedMapper.checkBillUseMan(nsbillBillUsed);
                    sum += num;
                }
            }
        }
        return sum;
    }

    @Override
    @ReadDataSource
    public List<BillUseManCheckVo> listCheckBillUseManBatch(BillUseManSearchVo billUseManSearchVo) {
        List<BillUseManCheckVo> billUseManSearchVos = nsbillBillUsedMapper.listCheckBillUseManBatchGroupBy(billUseManSearchVo);
        return billUseManSearchVos;
    }

    @Override
    @ReadDataSource
    public List<NsbillBillUsed> selectByIds(List<Long> ids) {
        List<NsbillBillUsed> list = nsbillBillUsedMapper.selectByIds(ids);
        return list;
    }

	@Override
	@ReadDataSource
	public List<NsbillBillUsed> listbillCode(String billCode,Long enterpriseId,Long organizationId) {
		BillUseManSearchVo vo = new BillUseManSearchVo();
		vo.setEnterpriseId(enterpriseId);
		vo.setOrganizationId(organizationId);
		vo.setBillCode(billCode);
		List<NsbillBillUsed> list = nsbillBillUsedMapper.selectByCode(vo);
		return list;
	}


    /**
     * 编辑票据使用管理详情
     * @return boolean 编辑成功与否
     *//*
	public boolean edit(BillUseManVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsBillBillused nsBillBillused = new NsBillBillused();
		BeanUtils.copyProperties(vo, nsBillBillused);
		int countnsBillBillused = nsBillBillusedMapper.updateById(nsBillBillused);
		if(countnsBillBillused == 0 ){
			return false;
		}
		NsBillBilluseddetail nsBillBilluseddetail = new NsBillBilluseddetail();
		BeanUtils.copyProperties(vo, nsBillBilluseddetail);
		int countnsBillBilluseddetail = nsBillBilluseddetailMapper.updateById(nsBillBilluseddetail);
		if(countnsBillBilluseddetail == 0 ){
			return false;
		}
		NsBillBilldetailbusiness nsBillBilldetailbusiness = new NsBillBilldetailbusiness();
		BeanUtils.copyProperties(vo, nsBillBilldetailbusiness);
		int countnsBillBilldetailbusiness = nsBillBilldetailbusinessMapper.updateById(nsBillBilldetailbusiness);
		if(countnsBillBilldetailbusiness == 0 ){
			return false;
		}
	    return true;
	}
	
	*//**
     * 新增票据使用管理
     * @return boolean 新增成功与否
     *//*
	public boolean add(BillUseManVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsBillBillused nsBillBillused = new NsBillBillused();
		BeanUtils.copyProperties(vo, nsBillBillused);
		int countnsBillBillused = nsBillBillusedMapper.insert(nsBillBillused);
		if(countnsBillBillused == 0 ){
			return false;
		}
		NsBillBilluseddetail nsBillBilluseddetail = new NsBillBilluseddetail();
		BeanUtils.copyProperties(vo, nsBillBilluseddetail);
		int countnsBillBilluseddetail = nsBillBilluseddetailMapper.insert(nsBillBilluseddetail);
		if(countnsBillBilluseddetail == 0 ){
			return false;
		}
		NsBillBilldetailbusiness nsBillBilldetailbusiness = new NsBillBilldetailbusiness();
		BeanUtils.copyProperties(vo, nsBillBilldetailbusiness);
		int countnsBillBilldetailbusiness = nsBillBilldetailbusinessMapper.insert(nsBillBilldetailbusiness);
		if(countnsBillBilldetailbusiness == 0 ){
			return false;
		}
	    return true;
	}
	
	*//**
     * 根据主键删除票据使用管理
     * @param id 主键id
     * @return
     *//*
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		int countnsBillBillused = nsBillBillusedMapper.deleteById(id);
		if(countnsBillBillused == 0 ){
			return false;
		}
		int countnsBillBilluseddetail = nsBillBilluseddetailMapper.deleteById(id);
		if(countnsBillBilluseddetail == 0 ){
			return false;
		}
		int countnsBillBilldetailbusiness = nsBillBilldetailbusinessMapper.deleteById(id);
		if(countnsBillBilldetailbusiness == 0 ){
			return false;
		}
	    return true;
	}
	
	*//**
     * 根据主键批量删除票据使用管理
     * @param ids
     * @return
     *//*
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		int countnsBillBillused = nsBillBillusedMapper.deleteBatch(ids);
		if(countnsBillBillused == 0 ){
			return false;
		}
		int countnsBillBilluseddetail = nsBillBilluseddetailMapper.deleteBatch(ids);
		if(countnsBillBilluseddetail == 0 ){
			return false;
		}
		int countnsBillBilldetailbusiness = nsBillBilldetailbusinessMapper.deleteBatch(ids);
		if(countnsBillBilldetailbusiness == 0 ){
			return false;
		}
	    return true;
	}*/
}
