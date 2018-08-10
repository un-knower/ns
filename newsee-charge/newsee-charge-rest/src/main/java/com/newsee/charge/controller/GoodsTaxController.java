/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
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
import com.newsee.charge.entity.ChargeGoodsTax;
import com.newsee.charge.service.IGoodsTaxService;
import com.newsee.charge.service.remote.ISystemRemoteService;
import com.newsee.charge.vo.GoodsTaxVo;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/goodsTax")
@Api(tags = { "com.newsee.charge.controller.GoodsTaxController" }, description = "税目列表页面操作 REST API，包含税目页面的所有操作方法。")
public class GoodsTaxController {

	@Autowired
	private IGoodsTaxService goodsTaxService;

	@Autowired
	private ISystemRemoteService systemRemoteService;

	@Autowired
	RedisUtil redisUtil;

	@ApiOperation(value = "初始化表单项目")
	@RequestMapping(value = "/init-form", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> initForm() {
		Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.GOODS_TAX_LIST);
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
		// 检查表单中是否有表格项目，并且做相应处理
		List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
		resultData.put(FormConstants.FORM_FIELDS, formFields);
		return result;
	}

	@ApiOperation(value = "税目列表获取")
	@RequestMapping(value = "/list-goodsTax", method = RequestMethod.POST)
	public RestResult<PageInfo<ChargeGoodsTax>> listPage(@ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) {
		BizException.isNull(searchVo, "查询条件");
		RestResult<PageInfo<ChargeGoodsTax>> restResult = null;
		Long organizationId = LoginDataHelper.getOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		searchVo.setEnterpriseId(enterpriseId);
		searchVo.setOrganizationId(organizationId);
		PageInfo<ChargeGoodsTax> pageInfo = goodsTaxService.listPage(searchVo);
		restResult = new RestResult<>(pageInfo);
		return restResult;
	}

	@ApiOperation(value = "税目详情获取")
	@RequestMapping(value = "/detail-goodsTax", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailGoodsTax(@ApiParam(value = "税目ID") @RequestParam(value = "id") Long id)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IntrospectionException {
		Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.GOODS_TAX_LIST);
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
		// 获取税目详情信息
		GoodsTaxVo vo = goodsTaxService.detail(id);
		resultData.put(FormConstants.FORM_MODEL_DATA, vo);
		result = new RestResult<>(resultData);
		return result;
	}

	@ApiOperation(value = "新增税目")
	@RequestMapping(value = "/add-goodsTax", method = RequestMethod.POST)
	public RestResult<Boolean> addGoodsTax(@ApiParam(value = "新增税目") @RequestBody GoodsTaxVo vo) {
		// 编辑税目详情信息
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		// 检查编号是否存在
		boolean isExists = goodsTaxService.checkTaxNoIsExist(vo.getGoodsTaxNo(), null, enterpriseId);
		if (isExists) {
			return new RestResult<Boolean>(ResultCodeEnum.DATA_NOT_EXIST.CODE, "该税目编码已存在。");
		} 
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		Long organizationId = LoginDataHelper.getGroupLevelOrgId();
		vo.setOrganizationId(organizationId);
		// 检查同一集团下code是否唯一  
		vo.setEnterpriseId(enterpriseId);
		Date now = new Date();
		vo.setCreateUserId(userId);
		vo.setCreateUserName(userName);
		vo.setCreateTime(now);
		vo.setUpdateUserId(userId);
		vo.setUpdateTime(now);
		vo.setUpdateUserName(userName);
		boolean result = goodsTaxService.add(vo);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "编辑税目")
	@RequestMapping(value = "/edit-goodsTax", method = RequestMethod.POST)
	public RestResult<Boolean> editGoodsTax(@ApiParam(value = "税目详情") @RequestBody GoodsTaxVo vo) {
		// 编辑税目详情信息
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		// 检查编号是否存在
		boolean isExists = goodsTaxService.checkTaxNoIsExist(vo.getGoodsTaxNo(), vo.getId(), enterpriseId);
		if (isExists) {
			return new RestResult<Boolean>(ResultCodeEnum.DATA_NOT_EXIST.CODE, "该税目编码已存在。");
		}
		Long organizationId = LoginDataHelper.getGroupLevelOrgId();
		vo.setOrganizationId(organizationId);
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		Date now = new Date();
		vo.setUpdateUserId(userId);
		vo.setUpdateUserName(userName);
		vo.setUpdateTime(now);
		boolean result = goodsTaxService.edit(vo);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "删除税目")
	@RequestMapping(value = "/delete-goodsTax", method = RequestMethod.POST)
	public RestResult<Boolean> deleteGoodsTax(@ApiParam(value = "税目ID") @RequestParam("id") Long id) {
		// 删除税目详情信息
		boolean result = goodsTaxService.delete(id);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "批量删除税目")
	@RequestMapping(value = "/delete-goodsTax-batch", method = RequestMethod.POST)
	public RestResult<Boolean> deleteGoodsTaxBatch(@ApiParam(value = "税目ID") @RequestBody List<Long> ids) {
		// 删除税目详情信息
		boolean result = goodsTaxService.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "根据税目编码搜索")
	@RequestMapping(value = "/search-goodsTax", method = RequestMethod.GET)
	public RestResult<List<ChargeGoodsTax>> searchGoodsTax(@ApiParam(value = "税目编码") @RequestParam(value = "goodsTaxNo") String goodsTaxNo) {
		BizException.isNull(goodsTaxNo, "编码不能为空");
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		List<ChargeGoodsTax> list = goodsTaxService.searchByGoodsTaxNo(goodsTaxNo, enterpriseId);
		return new RestResult<>(list);
	}
}
