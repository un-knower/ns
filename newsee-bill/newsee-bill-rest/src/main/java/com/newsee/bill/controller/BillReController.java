/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newsee.bill.service.IBillReService;
import com.newsee.bill.service.remote.ISystemRemoteService;
import com.newsee.bill.vo.BillReVo;
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
@RequestMapping("/billRe")
@Api(tags = {"com.newsee.bill.controller.BillReController"}, description = "票据补录列表页面操作 REST API，包含票据补录页面的所有操作方法。")
public class BillReController{
    
    @Autowired
    private IBillReService billReService;
    
    @Autowired
    private ISystemRemoteService systemRemoteService;
    
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm(){
    	 Long organizationId= LoginDataHelper.getCompanyLevelOrgId();
         Long enterpriseId = LoginDataHelper.getEnterpriseId();
         String funcId = MenuHelper.getFuncIdByMenuEnName("billRecord");
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
    
    @ApiOperation(value = "票据补录列表获取")
	@RequestMapping(value = "/list-billRe", method = RequestMethod.POST)
	public RestResult<PageInfo<BillReVo>> listPage(@ApiParam(value = "查询条件")@RequestBody SearchVo searchVo) {
    	BizException.isNull(searchVo, "查询条件");
    	RestResult<PageInfo<BillReVo>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = LoginDataHelper.getFuncId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<BillReVo> pageInfo =  billReService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);            
        return restResult;
	}

    @ApiOperation(value = "票据补录详情获取")
	@RequestMapping(value = "/detail-billRe", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailBillRe(@ApiParam(value = "票据补录ID") @RequestParam(value = "id")Long id){
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
		//获取票据补录详情信息
		BillReVo vo = billReService.detail(id);
		resultData.put(FormConstants.FORM_MODEL_DATA, vo);
	    result = new RestResult<>(resultData);
		return result;
	}

	@ApiOperation(value = "编辑票据补录")
	@RequestMapping(value = "/add-billRe", method = RequestMethod.POST)
	public RestResult<Boolean> aaBillRe(@ApiParam(value = "票据补录详情")@RequestBody BillReVo vo) {
		//编辑票据补录详情信息
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
		Long userId = LoginDataHelper.getUserId();
		/*vo.setEnterpriseId(enterpriseId);
		vo.setOrganizationId(organizationId);
		vo.setHandlerId(userId);*/
		boolean result = billReService.add(vo);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "编辑票据补录")
	@RequestMapping(value = "/edit-billRe", method = RequestMethod.POST)
	public RestResult<Boolean> editBillRe(@ApiParam(value = "票据补录详情")@RequestBody BillReVo vo) {
		//编辑票据补录详情信息
		Long userId = LoginDataHelper.getUserId();
		/*vo.setHandlerId(userId);*/
		boolean result = billReService.edit(vo);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "删除票据补录")
	@RequestMapping(value = "/delete-billRe")
	public RestResult<Boolean> deleteBillRe(@ApiParam(value = "票据补录ID") @RequestParam("id") Long id) {
		//删除票据补录详情信息
		boolean result = billReService.delete(id);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "批量删除票据补录")
	@RequestMapping(value = "/delete-billRe-batch")
	public RestResult<Boolean> deleteBillReBatch(@ApiParam(value = "票据补录ID") @RequestBody List<Long> ids) {
		//删除票据补录详情信息
		boolean result = billReService.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}
}
