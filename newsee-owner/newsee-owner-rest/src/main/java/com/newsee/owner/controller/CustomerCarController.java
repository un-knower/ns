package com.newsee.owner.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.Constants;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.ExcelHelper;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.owner.service.ICarService;
import com.newsee.owner.service.remote.ISystemRemoteService;
import com.newsee.owner.vo.CarVo;
import com.newsee.redis.util.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@ResponseBody
@RequestMapping(value = "/car")
@Api(tags = { "com.newsee.owner.controller.CustomerCarController" }, description = "车辆管理  REST API，包含所有菜单，页面按钮的操作方法。")
public class CustomerCarController {

	@Autowired
	private ICarService carService;
	@Autowired
	private ISystemRemoteService systemRemoteService;
	@Autowired
	private RedisUtil redisUtil;

	@ApiOperation(value = "初始化表单项目")
	@RequestMapping(value = "/init-form", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> initForm(@RequestParam("ownerCarId") Long ownerCarId) {
		Long organizationId = LoginDataHelper.getOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
		// String funcId = LoginDataHelper.getFuncId();
		String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.CAR_MANAGEMENT);
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
		if (!CommonUtils.isObjectEmpty(ownerCarId)) {
			CarVo carVo = carService.detailOwnerCar(ownerCarId);
			if (carVo != null) {
				carVo = CommonUtils.clearNull(carVo);
				Map<String, Object> carVoMap = FormUtils.beanToMap(carVo);
				resultData.put("modelData", carVoMap);
			}
		}
		// 检查表单中是否有表格项目，并且做相应处理
		List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
		resultData.put("fields", formFields);
		return result;
	}

	@ApiOperation(value = "新增车辆")
	@RequestMapping(value = "/add-car", method = RequestMethod.POST)
	public RestResult<Long> addCar(@RequestBody CarVo carVo) {
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		carVo.setEnterpriseId(LoginDataHelper.getEnterpriseId());
		carVo.setOrganizationId(LoginDataHelper.getOrgId());
		carVo.setCreateUserId(userId);
		carVo.setCreateUserName(userName);
		carVo.setUpdateUserId(userId);
		carVo.setUpdateUserName(userName);
		// 校验车牌唯一性
		boolean flag = carService.checkCarNumberOnly(carVo);
		if (!flag) {
			BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "车牌号不能重复");
		}
		Long index = carService.addOwnerCar(carVo);
		return new RestResult<>(index);
	}

	@ApiOperation(value = "获取车辆详情")
	@RequestMapping(value = "/detail-car", method = RequestMethod.GET)
	public RestResult<CarVo> detailCar(@RequestParam("ownerCarId") Long ownerCarId) {
		CarVo carVo = carService.detailOwnerCar(ownerCarId);
		return new RestResult<>(carVo);
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "车辆管理列表")
	@RequestMapping(value = "/list-car", method = RequestMethod.POST)
	public RestResult<PageInfo<CarVo>> listCar(@RequestBody SearchVo searchVo) throws Exception {
		String funcId = LoginDataHelper.getFuncId();
		Long organizationId = LoginDataHelper.getOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		searchVo.setEnterpriseId(enterpriseId);
		searchVo.setOrganizationId(organizationId);

		NsCoreResourcecolumnVo nsCoreResourcecolumnVo = new NsCoreResourcecolumnVo();
		nsCoreResourcecolumnVo.setEnterpriseId(enterpriseId);
		nsCoreResourcecolumnVo.setOrganizationId(organizationId);
		nsCoreResourcecolumnVo.setResourcecolumnFuncinfoId(funcId);

		// 从redis中获取是否有缓存，如有，从缓存中获取，如无，从数据库中重新获取表单项目
		String fieldRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString() + "_" + funcId;
		Object filedRedisObject = redisUtil.getObjectValue(fieldRedisKey);
		if (filedRedisObject == null) {
			RestResult<Map<String, Object>> columnResult = systemRemoteService
					.listColumnForRemote(nsCoreResourcecolumnVo);
			filedRedisObject = columnResult.getResultData();
		}
		PageInfo<CarVo> pageInfo = carService.listOwnerCarForSearch(searchVo, (Map<String, Object>) filedRedisObject,
				true);
		return new RestResult<>(pageInfo);
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "获取列表合计")
	@RequestMapping(value = "/get-total", method = RequestMethod.POST)
	public RestResult<CarVo> getTotal(@RequestBody SearchVo searchConditionVo) throws Exception {
		String funcId = LoginDataHelper.getFuncId();
		Long organizationId = LoginDataHelper.getOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		searchConditionVo.setEnterpriseId(enterpriseId);
		searchConditionVo.setOrganizationId(organizationId);

		NsCoreResourcecolumnVo nsCoreResourcecolumnVo = new NsCoreResourcecolumnVo();
		nsCoreResourcecolumnVo.setEnterpriseId(enterpriseId);
		nsCoreResourcecolumnVo.setOrganizationId(organizationId);
		nsCoreResourcecolumnVo.setResourcecolumnFuncinfoId(funcId);

		// 从redis中获取是否有缓存，如有，从缓存中获取，如无，从数据库中重新获取表单项目
		String fieldRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString() + "_" + funcId;
		Object filedRedisObject = redisUtil.getObjectValue(fieldRedisKey);
		if (filedRedisObject == null) {
			RestResult<Map<String, Object>> columnResult = systemRemoteService
					.listColumnForRemote(nsCoreResourcecolumnVo);
			filedRedisObject = columnResult.getResultData();
		}
		CarVo total = carService.getTotal(searchConditionVo, (Map<String, Object>) filedRedisObject);
		return new RestResult<>(total);
	}

	@ApiOperation(value = "删除车辆")
	@RequestMapping(value = "/delete-car", method = RequestMethod.DELETE)
	public RestResult<Integer> deleteCar(@RequestBody List<Long> ownerCarIdList) {
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		if (CollectionUtils.isEmpty(ownerCarIdList)) {
			BizException.fail(ResultCodeEnum.PARAMS_MISSING, "请选择要删除的车辆");
		}
		int index = carService.deleteOwnerCar(ownerCarIdList, userId, userName);
		return new RestResult<>(index);
	}

	@ApiOperation(value = "删除所有车辆")
	@RequestMapping(value = "/delete-all-car", method = RequestMethod.DELETE)
	public RestResult<Integer> deleteAllCar(@RequestBody SearchVo searchVo) throws Exception {
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		int index = carService.deleteAllOwnerCar(searchVo, userId, userName);
		return new RestResult<>(index);
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "导出车辆")
	@RequestMapping(value = "/export-car", method = RequestMethod.POST)
	public RestResult<Object> exportCar(@RequestBody SearchVo searchConditionVo, HttpServletResponse response)
			throws Exception {
		Long start = new Date().getTime();
		String funcId = LoginDataHelper.getFuncId();
		Long organizationId = LoginDataHelper.getOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		searchConditionVo.setEnterpriseId(enterpriseId);
		searchConditionVo.setOrganizationId(organizationId);
		PageInfo<CarVo> result = carService.listOwnerCarForSearch(searchConditionVo, null, false);
		NsCoreResourcecolumnVo nsCoreResourcecolumnVo = new NsCoreResourcecolumnVo();
		nsCoreResourcecolumnVo.setEnterpriseId(enterpriseId);
		nsCoreResourcecolumnVo.setOrganizationId(organizationId);
		nsCoreResourcecolumnVo.setResourcecolumnFuncinfoId(funcId);
		// 从redis中获取是否有缓存，如有，从缓存中获取，如无，从数据库中重新获取表单项目
		String fieldRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString() + "_" + funcId;
		Object filedRedisObject = redisUtil.getObjectValue(fieldRedisKey);
		RestResult<Map<String, Object>> columnResult = null;
		Map<String, Object> columnMap = null;
		if (filedRedisObject == null) {
			columnResult = systemRemoteService.listColumnForRemote(nsCoreResourcecolumnVo);
			columnMap = columnResult.getResultData();
		} else {
			columnMap = (Map<String, Object>) filedRedisObject;
		}
		if (columnMap != null) {
			String json = JSONObject.toJSONString(columnMap.get("columns"));
			List<NsCoreResourcecolumnVo> columnList = JSONArray.parseArray(json, NsCoreResourcecolumnVo.class);
			if (!CollectionUtils.isEmpty(columnList)) {
				columnList = columnList.stream()
						.filter(column -> Constants.FALSE.toString().equals(column.getResourcecolumnHidden()))
						.collect(Collectors.toList());
				ExcelHelper.exportExcel(result.getList(), columnList, "车辆信息", response);
			}
		}

		Long end = new Date().getTime();
		Long time = end - start;
		System.out.println("exportHouse========" + time);
		return new RestResult<>();
	}
}
