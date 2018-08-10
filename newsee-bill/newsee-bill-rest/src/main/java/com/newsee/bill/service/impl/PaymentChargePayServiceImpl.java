package com.newsee.bill.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.bill.dao.NspaymentChargePaymentMapper;
import com.newsee.bill.entity.NspaymentChargePayment;
import com.newsee.bill.service.IPaymentChargePayService;
import com.newsee.bill.vo.PaymentChargePayVo;
import com.newsee.common.vo.SearchVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PaymentChargePayServiceImpl implements IPaymentChargePayService {
	
    @Autowired
    private NspaymentChargePaymentMapper nsPaymentChargepaymentMapper;
    
	/**
	 * 获取客户缴款明细表列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	public PageInfo<NspaymentChargePayment> listPage(SearchVo searchVo){
		if (Objects.isNull(searchVo)) {
			return null;
		}
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<NspaymentChargePayment> list = nsPaymentChargepaymentMapper.listPage(searchVo);
        PageInfo<NspaymentChargePayment> pageInfo = new PageInfo<>(list);
        return pageInfo;
	}
	
	/**
	 * 获取客户缴款明细表详情
	 * @param id 主键id
	 * @return
	 */
	public PaymentChargePayVo detail(Long id){
		if(Objects.isNull(id)) {
			return null;
		}
		PaymentChargePayVo vo = new PaymentChargePayVo();
		NspaymentChargePayment nsPaymentChargepayment = nsPaymentChargepaymentMapper.selectById(id);
		//如果查询出了数据，将数据拷贝到vo中
		if(!Objects.isNull(nsPaymentChargepayment)){
			BeanUtils.copyProperties(nsPaymentChargepayment, vo);
		}
	    return vo;
	}
	
	/**
	 * 编辑客户缴款明细表详情
	 * @return boolean 编辑成功与否
	 */
	public boolean edit(PaymentChargePayVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NspaymentChargePayment nsPaymentChargepayment = new NspaymentChargePayment();
		BeanUtils.copyProperties(vo, nsPaymentChargepayment);
		int countnsPaymentChargepayment = nsPaymentChargepaymentMapper.updateById(nsPaymentChargepayment);
		if(countnsPaymentChargepayment == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 新增客户缴款明细表
	 * @return boolean 新增成功与否
	 */
	public boolean add(PaymentChargePayVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NspaymentChargePayment nsPaymentChargepayment = new NspaymentChargePayment();
		BeanUtils.copyProperties(vo, nsPaymentChargepayment);
		int countnsPaymentChargepayment = nsPaymentChargepaymentMapper.insert(nsPaymentChargepayment);
		if(countnsPaymentChargepayment == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键删除客户缴款明细表
	 * @param id 主键id
	 * @return
	 */
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		int countnsPaymentChargepayment = nsPaymentChargepaymentMapper.deleteById(id);
		if(countnsPaymentChargepayment == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键批量删除客户缴款明细表
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		int countnsPaymentChargepayment = nsPaymentChargepaymentMapper.deleteBatch(ids);
		if(countnsPaymentChargepayment == 0 ){
			return false;
		}
	    return true;
	}
}
