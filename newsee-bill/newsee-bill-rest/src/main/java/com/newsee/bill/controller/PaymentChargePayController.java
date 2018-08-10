/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.controller;

import com.github.pagehelper.PageInfo;
import com.newsee.bill.entity.NspaymentChargePayment;
import com.newsee.bill.service.IPaymentChargePayService;
import com.newsee.bill.service.remote.ISystemRemoteService;
import com.newsee.bill.vo.PaymentChargePayVo;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/paymentChargePay")
@Api(tags = {"com.newsee.bill.controller.PaymentChargePayController"}, description = "客户缴款明细表列表页面操作 REST API，包含客户缴款明细表页面的所有操作方法。")
public class PaymentChargePayController{
    
    @Autowired
    private IPaymentChargePayService paymentChargePayService;
    
    @Autowired
    private ISystemRemoteService systemRemoteService;
    
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm(){
    	 Long organizationId= LoginDataHelper.getCompanyLevelOrgId();
         Long enterpriseId = LoginDataHelper.getEnterpriseId();
         String funcId = MenuHelper.getFuncIdByMenuEnName("paymentChargePayment");
         String interpreter = LoginDataHelper.getFieldInterpreter();
         String formOperateType = LoginDataHelper.getFormOperateType();
         LoginCommonDataVo commonVo = new LoginCommonDataVo();
         commonVo.setOrganizationId(organizationId);
         commonVo.setEnterpriseId(enterpriseId);
         commonVo.setFuncId(funcId);
         commonVo.setInterpreter(interpreter);
         commonVo.setFormOperateType(formOperateType);
         RestResult<Map<String, Object>> result = systemRemoteService.listField(commonVo);
         Map<String, Object> resultData = result.getResultData();
         //检查表单中是否有表格项目，并且做相应处理
         List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
         resultData.put(FormConstants.FORM_FIELDS, formFields);
         return result;
    }
    
    @ApiOperation(value = "客户缴款明细表列表获取")
	@RequestMapping(value = "/list-paymentChargePay", method = RequestMethod.POST)
	public RestResult<PageInfo<NspaymentChargePayment>> listPage(@ApiParam(value = "查询条件")@RequestBody SearchVo searchVo) {
    	BizException.isNull(searchVo, "查询条件");
    	RestResult<PageInfo<NspaymentChargePayment>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = LoginDataHelper.getFuncId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<NspaymentChargePayment> pageInfo =  paymentChargePayService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);            
        return restResult;
	}

    @ApiOperation(value = "客户缴款明细表详情获取")
	@RequestMapping(value = "/detail-paymentChargePay", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailPaymentChargePay(@ApiParam(value = "客户缴款明细表ID") @RequestParam(value = "id")Long id){
		Long organizationId= LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.ITEM_LIST);
        String interpreter = LoginDataHelper.getFieldInterpreter();
        String formOperateType = LoginDataHelper.getFormOperateType();
        Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
        LoginCommonDataVo commonVo = new LoginCommonDataVo();
        commonVo.setOrganizationId(organizationId);
        commonVo.setGroupLevelOrgId(groupLevelOrgId);
        commonVo.setEnterpriseId(enterpriseId);
        commonVo.setFuncId(funcId);
        commonVo.setInterpreter(interpreter);
        commonVo.setFormOperateType(formOperateType);
        RestResult<Map<String, Object>> result = systemRemoteService.listField(commonVo);
        Map<String, Object> resultData = result.getResultData();
        List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
        resultData.put(FormConstants.FORM_FIELDS, formFields);
		//获取客户缴款明细表详情信息
		PaymentChargePayVo vo = paymentChargePayService.detail(id);
		resultData.put(FormConstants.FORM_MODEL_DATA, vo);
	    result = new RestResult<>(resultData);
		return result;
	}

	@ApiOperation(value = "编辑客户缴款明细表")
	@RequestMapping(value = "/add-paymentChargePay", method = RequestMethod.POST)
	public RestResult<Boolean> aaPaymentChargePay(@ApiParam(value = "客户缴款明细表详情")@RequestBody PaymentChargePayVo vo) {
		//编辑客户缴款明细表详情信息
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
		Long userId = LoginDataHelper.getUserId();
		vo.setEnterpriseId(enterpriseId);
		vo.setOrganizationId(organizationId);
		boolean result = paymentChargePayService.add(vo);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "编辑客户缴款明细表")
	@RequestMapping(value = "/edit-paymentChargePay", method = RequestMethod.POST)
	public RestResult<Boolean> editPaymentChargePay(@ApiParam(value = "客户缴款明细表详情")@RequestBody PaymentChargePayVo vo) {
		//编辑客户缴款明细表详情信息
		Long userId = LoginDataHelper.getUserId();
		boolean result = paymentChargePayService.edit(vo);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "删除客户缴款明细表")
	@RequestMapping(value = "/delete-paymentChargePay")
	public RestResult<Boolean> deletePaymentChargePay(@ApiParam(value = "客户缴款明细表ID") @RequestParam("id") Long id) {
		//删除客户缴款明细表详情信息
		boolean result = paymentChargePayService.delete(id);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "批量删除客户缴款明细表")
	@RequestMapping(value = "/delete-paymentChargePay-batch")
	public RestResult<Boolean> deletePaymentChargePayBatch(@ApiParam(value = "客户缴款明细表ID") @RequestBody List<Long> ids) {
		//删除客户缴款明细表详情信息
		boolean result = paymentChargePayService.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}

    /**
     * 1.红冲金额不能大于应收款金额
     * 2.冲销后的才能进行红冲
     * 3.已经红冲的不能再次红冲
     * @param paymentChargePayVo
     * @return
     */
	@ApiOperation(value = "红冲")
    @RequestMapping(value = "/redBill",method = RequestMethod.POST)
    public RestResult<Boolean> redBill(@RequestBody PaymentChargePayVo paymentChargePayVo){
        return null;
    }

    /**
     * 1.已经缴费，未交账前才能进行冲销
     * @param map
     * @return
     */
    @ApiOperation(value = "冲销")
    @RequestMapping(value = "/cancellation" ,method = RequestMethod.POST)
    public RestResult<Boolean> cancellation(@RequestBody Map<String,Object> map){
        return null;
    }
}
