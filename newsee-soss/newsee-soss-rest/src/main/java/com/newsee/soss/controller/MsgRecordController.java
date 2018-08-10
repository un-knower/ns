/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.soss.service.IMsgRecordService;
import com.newsee.soss.service.remote.ISystemRemoteService;
import com.newsee.soss.vo.MsgRecordVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/msgRecord")
@Api(tags = {"com.newsee.soss.controller.MsgRecordController"}, description = "消息记录列表页面操作 REST API，包含消息记录页面的所有操作方法。")
public class MsgRecordController{
    
    @Autowired
    private IMsgRecordService msgRecordService;
    
    @Autowired
    private ISystemRemoteService systemRemoteService;
    
    @Autowired
    RedisUtil redisUtil;
    
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm(){
    	 Long organizationId= LoginDataHelper.getOrgId();
         Long enterpriseId = LoginDataHelper.getEnterpriseId();
         Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
         String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.SOSS_MSGRECORDLIST);
         String interpreter = LoginDataHelper.getFieldInterpreter();
         String formOperateType = LoginDataHelper.getFormOperateType();
         LoginCommonDataVo commonVo = new LoginCommonDataVo();
         commonVo.setOrganizationId(organizationId);
         commonVo.setEnterpriseId(enterpriseId);
         commonVo.setGroupLevelOrgId(groupLevelOrgId);
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
    
    @ApiOperation(value = "消息记录列表获取")
	@RequestMapping(value = "/list-msgRecord", method = RequestMethod.POST)
	public RestResult<PageInfo<MsgRecordVo>> listPage(@ApiParam(value = "查询条件")@RequestBody SearchVo searchVo) {
    	RestResult<PageInfo<MsgRecordVo >> restResult = null;
        PageInfo<MsgRecordVo> pageInfo = msgRecordService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);            
        return restResult;
	}

    @ApiOperation(value = "消息记录详情获取")
	@RequestMapping(value = "/detail-msgRecord", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailMsgRecord(@ApiParam(value = "消息记录ID") @RequestParam(value = "id")Long id) 
			throws IllegalArgumentException,
			IllegalAccessException,
			InvocationTargetException,
			IntrospectionException{
		RestResult<Map<String, Object>> result = null;
		//获取消息记录详情信息
		MsgRecordVo vo = msgRecordService.detail(id);
		Map<String, Object> functionInfo = null;
		
	    result = new RestResult<>(functionInfo);
		return result;
	}

	@ApiOperation(value = "编辑消息记录")
	@RequestMapping(value = "/add-msgRecord", method = RequestMethod.POST)
	public RestResult<Boolean> aaMsgRecord(@ApiParam(value = "消息记录详情")@RequestBody MsgRecordVo vo) {
		//编辑消息记录详情信息
		boolean result = msgRecordService.add(vo);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "编辑消息记录")
	@RequestMapping(value = "/edit-msgRecord", method = RequestMethod.POST)
	public RestResult<Boolean> editMsgRecord(@ApiParam(value = "消息记录详情")@RequestBody MsgRecordVo vo) {
		//编辑消息记录详情信息
		boolean result = msgRecordService.edit(vo);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "删除消息记录")
	@RequestMapping(value = "/delete-msgRecord")
	public RestResult<Boolean> deleteMsgRecord(@ApiParam(value = "消息记录ID") @RequestParam("id") Long id) {
		//删除消息记录详情信息
		boolean result = msgRecordService.delete(id);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "批量删除消息记录")
	@RequestMapping(value = "/delete-msgRecord-batch")
	public RestResult<Boolean> deleteMsgRecordBatch(@ApiParam(value = "消息记录ID")@RequestBody List<Long> ids) {
		//删除消息记录详情信息
		boolean result = msgRecordService.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}
}
