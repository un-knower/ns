package com.newsee.bill.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.bill.dao.NsbillBillBookSerailRuleMapper;
import com.newsee.bill.dao.NsbillBillInfoDetailMapper;
import com.newsee.bill.dao.NsbillBillInfoMapper;
import com.newsee.bill.entity.NsbillBillBookSerailRule;
import com.newsee.bill.entity.NsbillBillInfo;
import com.newsee.bill.entity.NsbillBillInfoDetail;
import com.newsee.bill.service.IBillPurcService;
import com.newsee.bill.vo.BillPurcVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;

@Service
public class BillPurcServiceImpl implements IBillPurcService {
	
    @Autowired
    private NsbillBillInfoMapper nsBillBillinfoMapper;
    @Autowired
    private NsbillBillInfoDetailMapper nsBillBillinfodetailMapper;
    
    @Autowired
    private NsbillBillBookSerailRuleMapper nsbillBillBookSerailRuleMapper;
    
	/**
	 * 获取票据购入列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	public PageInfo<BillPurcVo> listPage(SearchVo searchVo){
		if (Objects.isNull(searchVo)) {
			return null;
		}
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<BillPurcVo> list = nsBillBillinfoMapper.listPage(searchVo);
        PageInfo<BillPurcVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
	}
	
	/**
	 * 获取票据购入详情
	 * @param id 主键id
	 * @return
	 */
	public BillPurcVo detail(Long id){
		if(Objects.isNull(id)) {
			return null;
		}
		BillPurcVo vo = new BillPurcVo();
		BillPurcVo nsBillBillinfo = nsBillBillinfoMapper.selectById(id);
		//如果查询出了数据，将数据拷贝到vo中
		if(!Objects.isNull(nsBillBillinfo)){
			BeanUtils.copyProperties(nsBillBillinfo, vo);
		}
		/*BillPurcVo nsBillBillinfodetail = nsBillBillinfodetailMapper.selectById(id);
		//如果查询出了数据，将数据拷贝到vo中
		if(!Objects.isNull(nsBillBillinfodetail)){
			BeanUtils.copyProperties(nsBillBillinfodetail, vo);
		}*/
	    return vo;
	}
	
	/**
	 * 编辑票据购入详情
	 * @return boolean 编辑成功与否
	 */
	public boolean edit(BillPurcVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
	/*	nsbillbillinfo nsBillBillinfo = new BillPurcVo();
		BeanUtils.copyProperties(vo, nsBillBillinfo);
		int countnsBillBillinfo = nsBillBillinfoMapper.updateById(nsBillBillinfo);
		if(countnsBillBillinfo == 0 ){
			return false;
		}*/
		BillPurcVo nsBillBillinfodetail = new BillPurcVo();
		BeanUtils.copyProperties(vo, nsBillBillinfodetail);
		/*int countnsBillBillinfodetail = nsBillBillinfodetailMapper.updateById(nsBillBillinfodetail);
		if(countnsBillBillinfodetail == 0 ){
			return false;
		}*/
	    return true;
	}
	
	/**
	 * 新增票据购入
	 * @return boolean 新增成功与否
	 */
	@WriteDataSource
	public boolean add(NsbillBillInfoDetail vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		
	/*	int countnsBillBillinfo = nsBillBillinfoMapper.insert(vo);
		if(countnsBillBillinfo == 0 ){
			return false;
		}*/
		//查询同类型发票的数据  计算发票编号
		Integer num = nsBillBillinfodetailMapper.selectBillTypeNum(vo);
		if(Objects.isNull(num)){
			num = 0;
		}
		vo.setFromNum((++num).longValue());
		vo.setToNum((--num).longValue());
	    int detail = nsBillBillinfodetailMapper.insert(vo);
		if(detail == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键删除票据购入
	 * @param id 主键id
	 * @return
	 */
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		int countnsBillBillinfo = nsBillBillinfoMapper.deleteById(id);
		if(countnsBillBillinfo == 0 ){
			return false;
		}
		int countnsBillBillinfodetail = nsBillBillinfodetailMapper.deleteById(id);
		if(countnsBillBillinfodetail == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键批量删除票据购入
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		int countnsBillBillinfo = nsBillBillinfoMapper.deleteBatch(ids);
		if(countnsBillBillinfo == 0 ){
			return false;
		}
		int countnsBillBillinfodetail = nsBillBillinfodetailMapper.deleteBatch(ids);
		if(countnsBillBillinfodetail == 0 ){
			return false;
		}
	    return true;
	}

	@Override
	@ReadDataSource
	public List<NsbillBillBookSerailRule> ListBillRuleInfo(NsbillBillBookSerailRule nsbillBillBookSerailRule) {
		List<NsbillBillBookSerailRule> nsbillBillBookSerailRules = nsbillBillBookSerailRuleMapper.ListBillRuleInfo(nsbillBillBookSerailRule);
		return nsbillBillBookSerailRules;
	}

	@Override
	@WriteDataSource
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public Integer updateBillRuleInfo(List<NsbillBillBookSerailRule> nsbillBillBookSerailRules) {
		nsbillBillBookSerailRules.forEach(item->{
			nsbillBillBookSerailRuleMapper.updateById(item);
		});
		return 1;
	}

	@Override
	public boolean edit(NsbillBillInfo vo) {
		// TODO Auto-generated method stub
		return false;
	}
}
