/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newsee.bill.entity.NsbillBillBookSerailRule;
import com.newsee.bill.entity.NsbillBillInfo;
import com.newsee.bill.entity.NsbillBillInfoDetail;
import com.newsee.bill.service.IBillPurcService;
import com.newsee.bill.service.remote.ISystemRemoteService;
import com.newsee.bill.vo.BillPurcVo;
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

@RestController
@RequestMapping("/billPurc")
@Api(tags = {"com.newsee.bill.controller.BillPurcController"}, description = "票据购入列表页面操作 REST API，包含票据购入页面的所有操作方法。")
public class BillPurcController{
    
    @Autowired
    private IBillPurcService billPurcService;
    
    @Autowired
    private ISystemRemoteService systemRemoteService;
    
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm(){
    	 Long organizationId= LoginDataHelper.getCompanyLevelOrgId();
         Long enterpriseId = LoginDataHelper.getEnterpriseId();
         String funcId = MenuHelper.getFuncIdByMenuEnName("billPurchase");
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
    
    
    @ApiOperation(value = "票本规则查询")
   	@RequestMapping(value = "/list-bill-ruleInfo", method = RequestMethod.GET)
   	public RestResult<List<NsbillBillBookSerailRule>> ListBillRuleInfo() {
       	RestResult<List<NsbillBillBookSerailRule>> restResult = null;
           Long organizationId = LoginDataHelper.getOrgId();
           Long enterpriseId = LoginDataHelper.getEnterpriseId();
           String funcId = LoginDataHelper.getFuncId();
           NsbillBillBookSerailRule nsbillBillBookSerailRule =  new  NsbillBillBookSerailRule();
           nsbillBillBookSerailRule.setEnterpriseId(enterpriseId);
           nsbillBillBookSerailRule.setOrganizationId(organizationId);
           List<NsbillBillBookSerailRule> nsbillBillBookSerailRules =  billPurcService.ListBillRuleInfo(nsbillBillBookSerailRule);
           restResult = new RestResult<>(nsbillBillBookSerailRules);            
           return restResult;
   	}
    
    @ApiOperation(value = "更新票本规则")
   	@RequestMapping(value = "/update-bill-ruleInfo", method = RequestMethod.GET)
   	public RestResult<Boolean> updateBillRuleInfo(@RequestBody List<NsbillBillBookSerailRule> nsbillBillBookSerailRules) {
       	    RestResult<Boolean> restResult = null;
           int result = billPurcService.updateBillRuleInfo(nsbillBillBookSerailRules);
           restResult = new RestResult<>(true);            
           return restResult;
   	}
    
    @ApiOperation(value = "票据购入列表获取")
	@RequestMapping(value = "/list-billPurc", method = RequestMethod.POST)
	public RestResult<PageInfo<BillPurcVo>> listPage(@ApiParam(value = "查询条件")@RequestBody SearchVo searchVo) {
    	BizException.isNull(searchVo, "查询条件");
    	RestResult<PageInfo<BillPurcVo>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = LoginDataHelper.getFuncId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<BillPurcVo> pageInfo =  billPurcService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);            
        return restResult;
	}

    @ApiOperation(value = "票据购入详情获取")
	@RequestMapping(value = "/detail-billPurc", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailBillPurc(@ApiParam(value = "票据购入ID") @RequestParam(value = "id")Long id){
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
		//获取票据购入详情信息
		BillPurcVo vo = billPurcService.detail(id);
		resultData.put(FormConstants.FORM_MODEL_DATA, vo);
	    result = new RestResult<>(resultData);
		return result;
	}

	@ApiOperation(value = "新增票据购入")
	@RequestMapping(value = "/add-billPurc", method = RequestMethod.POST)
	public RestResult<Boolean> aaBillPurc(@ApiParam(value = "票据购入详情")@RequestBody NsbillBillInfoDetail vo) {
		//编辑票据购入详情信息
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
		Long userId = LoginDataHelper.getUserId();
		vo.setEnterpriseId(enterpriseId);
		vo.setOrganizationId(organizationId);
		vo.setCreateUserId(userId);
		vo.setCreateTime(new Date());
		boolean result = billPurcService.add(vo);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "编辑票据购入")
	@RequestMapping(value = "/edit-billPurc", method = RequestMethod.POST)
	public RestResult<Boolean> editBillPurc(@ApiParam(value = "票据购入详情")@RequestBody BillPurcVo vo) {
		//编辑票据购入详情信息
		Long userId = LoginDataHelper.getUserId();
//		vo.setHandlerId(userId);
		boolean result = true/*billPurcService.edit(vo)*/;
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "删除票据购入")
	@RequestMapping(value = "/delete-billPurc")
	public RestResult<Boolean> deleteBillPurc(@ApiParam(value = "票据购入ID") @RequestParam("id") Long id) {
		//删除票据购入详情信息
		boolean result = billPurcService.delete(id);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "批量删除票据购入")
	@RequestMapping(value = "/delete-billPurc-batch")
	public RestResult<Boolean> deleteBillPurcBatch(@ApiParam(value = "票据购入ID") @RequestBody List<Long> ids) {
		//删除票据购入详情信息
		boolean result = billPurcService.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}
}
