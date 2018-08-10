/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.soss.service.IMsgService;
import com.newsee.soss.service.remote.ISystemRemoteService;
import com.newsee.soss.vo.MsgVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/msg")
@Api(tags = {"com.newsee.soss.controller.MsgController"}, description = "消息设置列表页面操作 REST API，包含消息设置页面的所有操作方法。")
public class MsgController{
    
    @Autowired
    private IMsgService msgService;
    
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
         String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.SOSS_MSGLIST);
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
    
    @ApiOperation(value = "消息设置列表获取")
	@RequestMapping(value = "/list-msg", method = RequestMethod.POST)
	public RestResult<PageInfo<MsgVo>> listPage(@ApiParam(value = "查询条件")@RequestBody SearchVo searchVo) {
    	RestResult<PageInfo<MsgVo >> restResult = null;
        PageInfo<MsgVo> pageInfo = msgService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);            
        return restResult;
	}

    @ApiOperation(value = "消息设置详情获取")
	@RequestMapping(value = "/detail-msg", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailMsg(@ApiParam(value = "消息设置ID") @RequestParam(value = "id")Long id) 
			throws IllegalArgumentException,
			IllegalAccessException,
			InvocationTargetException,
			IntrospectionException{
		RestResult<Map<String, Object>> result = null;
		//获取消息设置详情信息
		MsgVo vo = msgService.detail(id);
		//获取表单
        LoginCommonDataVo commonVo = LoginDataHelper.initLoginCommonDataVo();
        RestResult<Map<String, Object>> results = systemRemoteService.listField(commonVo);
        Map<String, Object> resultData = results.getResultData();
        if (!Objects.isNull(vo)) {
            vo = CommonUtils.clearNull(vo);
            //详情覆盖modelData
            resultData.put(FormConstants.FORM_MODEL_DATA, vo);
            //将json字符串形式的form表单装换成相应的对象
            List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
            resultData.put(FormConstants.FORM_FIELDS, formFields);
        }
	    result = new RestResult<>(resultData);
		return result;
	}

	@ApiOperation(value = "编辑消息设置")
	@RequestMapping(value = "/add-msg", method = RequestMethod.POST)
	public RestResult<Boolean> aaMsg(@ApiParam(value = "消息设置详情")@RequestBody MsgVo vo) {
		//编辑消息设置详情信息
		boolean result = msgService.add(vo);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "编辑消息设置")
	@RequestMapping(value = "/edit-msg", method = RequestMethod.POST)
	public RestResult<Boolean> editMsg(@ApiParam(value = "消息设置详情")@RequestBody MsgVo vo) {
		//编辑消息设置详情信息
		boolean result = msgService.edit(vo);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "删除消息设置")
	@RequestMapping(value = "/delete-msg")
	public RestResult<Boolean> deleteMsg(@ApiParam(value = "消息设置ID") @RequestParam("id") Long id) {
		//删除消息设置详情信息
		boolean result = msgService.delete(id);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "批量删除消息设置")
	@RequestMapping(value = "/delete-msg-batch")
	public RestResult<Boolean> deleteMsgBatch(@ApiParam(value = "消息设置ID")@RequestBody List<Long> ids) {
		//删除消息设置详情信息
		boolean result = msgService.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}
}
