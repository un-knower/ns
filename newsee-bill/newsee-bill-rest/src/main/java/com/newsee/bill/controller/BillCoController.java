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
import com.newsee.bill.entity.NsbillBillDraw;
import com.newsee.bill.service.IBillCoService;
import com.newsee.bill.service.remote.ISystemRemoteService;
import com.newsee.bill.vo.BillCoVo;
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
@RequestMapping("/billCo")
@Api(tags = {"com.newsee.bill.controller.BillCoController"}, description = "票据领用列表页面操作 REST API，包含票据领用页面的所有操作方法。")
public class BillCoController{
    
    @Autowired
    private IBillCoService billCoService;
    
    @Autowired
    private ISystemRemoteService systemRemoteService;
    
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm(){
    	 Long organizationId= LoginDataHelper.getCompanyLevelOrgId();
         Long enterpriseId = LoginDataHelper.getEnterpriseId();
         String funcId = MenuHelper.getFuncIdByMenuEnName("billCollar");
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
    
    @ApiOperation(value = "票据领用列表获取")
	@RequestMapping(value = "/list-billCo", method = RequestMethod.POST)
	public RestResult<PageInfo<BillCoVo>> listPage(@ApiParam(value = "查询条件")@RequestBody SearchVo searchVo) {
    	BizException.isNull(searchVo, "查询条件");
    	RestResult<PageInfo<BillCoVo>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = LoginDataHelper.getFuncId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<BillCoVo> pageInfo =  billCoService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);            
        return restResult;
	}

    @ApiOperation(value = "票据领用详情获取")
	@RequestMapping(value = "/detail-billCo", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailBillCo(@ApiParam(value = "票据领用ID") @RequestParam(value = "id")Long id){
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
		//获取票据领用详情信息
		BillCoVo vo = billCoService.detail(id);
		resultData.put(FormConstants.FORM_MODEL_DATA, vo);
	    result = new RestResult<>(resultData);
		return result;
	}

	@ApiOperation(value = "新增票据领用")
	@RequestMapping(value = "/add-billCo", method = RequestMethod.POST)
	public RestResult<Boolean> aaBillCo(@ApiParam(value = "票据领用详情")@RequestBody NsbillBillDraw vo) {
		//编辑票据领用详情信息
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
		Long userId = LoginDataHelper.getUserId();
		vo.setOrganizationId(organizationId);
		boolean result = billCoService.add(vo);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "编辑票据领用")
	@RequestMapping(value = "/edit-billCo", method = RequestMethod.POST)
	public RestResult<Boolean> editBillCo(@ApiParam(value = "票据领用详情")@RequestBody BillCoVo vo) {
		//编辑票据领用详情信息
		Long userId = LoginDataHelper.getUserId();
		boolean result = billCoService.edit(vo);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "删除票据领用")
	@RequestMapping(value = "/delete-billCo")
	public RestResult<Boolean> deleteBillCo(@ApiParam(value = "票据领用ID") @RequestParam("id") Long id) {
		//删除票据领用详情信息
		boolean result = billCoService.delete(id);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "批量删除票据领用")
	@RequestMapping(value = "/delete-billCo-batch")
	public RestResult<Boolean> deleteBillCoBatch(@ApiParam(value = "票据领用ID") @RequestBody List<Long> ids) {
		//删除票据领用详情信息
		boolean result = billCoService.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}
}
