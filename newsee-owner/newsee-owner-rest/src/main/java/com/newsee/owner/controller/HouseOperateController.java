package com.newsee.owner.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import com.newsee.common.enums.HouseOperateTypeEnum;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.ExcelHelper;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.common.vo.SelectItemVo;
import com.newsee.owner.service.ICustomerService;
import com.newsee.owner.service.IHouseOperateService;
import com.newsee.owner.service.remote.ISystemRemoteService;
import com.newsee.owner.vo.CustomerVo;
import com.newsee.owner.vo.HouseListVo;
import com.newsee.owner.vo.HouseOperateSalesVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsCoreDictionaryitemVo;
import com.newsee.system.vo.NsSystemUserVo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@ResponseBody
@RequestMapping(value = "/houseOperate")
public class HouseOperateController {

	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IHouseOperateService houseOperateService;
	@Autowired
	private ISystemRemoteService systemRemoteService;
	@Autowired
	RedisUtil redisUtil;

	@ApiOperation(value = "初始化表单项目")
	@RequestMapping(value = "/init-form", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> initForm(@RequestParam(value = "houseId") Long houseId,
			@RequestParam(value = "detailId") Long detailId,
			@ApiParam("是否修改 0 否 1 是") @RequestParam("isEdit") Byte isEdit,
			@ApiParam("房态操作动作：1售楼 2收房 3入住 4搬出 5出租 6转租 7退租 8空关 9转让 10装修") @RequestParam("houseOperateType") String houseOperateType)
			throws Exception {
		Long organizationId = LoginDataHelper.getOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
		// String funcId = LoginDataHelper.getFuncId();
		String menuName = "";
		HouseOperateTypeEnum houseOperateTypeEnum = HouseOperateTypeEnum.getInstance(houseOperateType);
		if (houseOperateTypeEnum == null) {
			BizException.fail(ResultCodeEnum.PARAMS_ERROR, "房态操作类型错误");
		}
		switch (houseOperateTypeEnum) {
		case SHOU_LOU:
		case ZHUAN_RANG:
			menuName = MenuEnNameConstants.SALES_HOUSE_REGISTER;
			break;
		case SHOU_FANG:
			menuName = MenuEnNameConstants.RECEIVE_HOUSE_MANAGEMENT;
			break;
		case RU_ZHU:
		case KONG_GUAN:
		case BAN_CHU:
			menuName = MenuEnNameConstants.LIVE_MANAGEMENT;
			break;
		case CHU_ZU:
		case TUI_ZU:
		case ZHUAN_ZU:
			menuName = MenuEnNameConstants.LEASE_MANAGEMENT;
			break;
		case ZHUANG_XIU:
			menuName = MenuEnNameConstants.DECORATIO_NMANAGEMENT;
			break;
		default:
			break;
		}
		String funcId = MenuHelper.getFuncIdByMenuEnName(menuName);
		if (!StringUtils.hasLength(funcId)) {
			BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "表单不存在");
		}
		String interpreter = LoginDataHelper.getFieldInterpreter();
		LoginCommonDataVo commonVo = new LoginCommonDataVo();
		commonVo.setOrganizationId(organizationId);
		commonVo.setEnterpriseId(enterpriseId);
		commonVo.setGroupLevelOrgId(groupLevelOrgId);
		commonVo.setFuncId(funcId);
		commonVo.setInterpreter(interpreter);
		RestResult<Map<String, Object>> result = systemRemoteService.listField(commonVo);
		Map<String, Object> resultData = result.getResultData();
		// 替换modedata
		HouseOperateSalesVo houseOperateSalesVo = houseOperateService.detailHouseStage(houseId, detailId,
				houseOperateType, isEdit);
		if (!Objects.isNull(houseOperateSalesVo)) {
			if (!CommonUtils.isObjectEmpty(houseOperateSalesVo.getCreateUserId())) {
				// 获取操作人
				RestResult<NsSystemUserVo> createUser = systemRemoteService
						.detailUser(houseOperateSalesVo.getCreateUserId());
				RestResult<NsSystemUserVo> updateUser = systemRemoteService
						.detailUser(houseOperateSalesVo.getUpdateUserId());
				if (createUser != null && createUser.getResultData() != null) {
					houseOperateSalesVo.setCreateUserName(createUser.getResultData().getUserName());
				}
				if (updateUser != null && updateUser.getResultData() != null) {
					houseOperateSalesVo.setUpdateUserName(updateUser.getResultData().getUserName());
				}
			}
			// 获取开发商
			Long ownerId = houseOperateService.getDeveloperByHousePath(houseId);
			CustomerVo developer = customerService.detail(ownerId);
			// if (!Objects.isNull(houseOperateSalesVo.getNewOwner())){
			// houseOperateSalesVo.setOwnerName(Objects.isNull(houseOperateSalesVo.getNewOwner().getOwnerName())?"":houseOperateSalesVo.getNewOwner().getOwnerName());
			// houseOperateSalesVo.setOwnerCertificate(Objects.isNull(houseOperateSalesVo.getNewOwner().getCertificate())?"":houseOperateSalesVo.getNewOwner().getCertificate());
			// houseOperateSalesVo.setOwnerMobile(Objects.isNull(houseOperateSalesVo.getNewOwner().getMobile())?"":houseOperateSalesVo.getNewOwner().getMobile());
			// }
			// if (!Objects.isNull(houseOperateSalesVo.getNewRentOwner())){
			// houseOperateSalesVo.setLesseeName(Objects.isNull(houseOperateSalesVo.getNewRentOwner().getOwnerName())?"":houseOperateSalesVo.getNewRentOwner().getOwnerName());
			// houseOperateSalesVo.setLesseeCertificate(Objects.isNull(houseOperateSalesVo.getNewRentOwner().getCertificate())?"":houseOperateSalesVo.getNewRentOwner().getCertificate());
			// houseOperateSalesVo.setLesseeMobile(Objects.isNull(houseOperateSalesVo.getNewRentOwner().getMobile())?"":houseOperateSalesVo.getNewRentOwner().getMobile());
			// }
			houseOperateSalesVo = CommonUtils.clearNull(houseOperateSalesVo);
			Map<String, Object> houseOpeVoMap = FormUtils.beanToMap(houseOperateSalesVo);
			if (developer != null) {
				houseOpeVoMap.put("developer", CommonUtils.clearNull(developer));
			}
			// Iterator<Map.Entry<String, Object>> it =
			// houseOpeVoMap.entrySet().iterator();
			// while (it.hasNext()) {
			// Map.Entry<String, Object> entry = it.next();
			// String key = entry.getKey();
			// Object param = entry.getValue();
			// if (param instanceof Integer) {
			// if (Objects.isNull(param)) {
			// houseOpeVoMap.put(key, 0);
			// }
			// } else if (param instanceof String) {
			// if (Objects.isNull(param)) {
			// houseOpeVoMap.put(key, "");
			// }
			// } else if (param instanceof Double) {
			// if (Objects.isNull(param)) {
			// houseOpeVoMap.put(key, 0.00);
			// }
			// } else if (param instanceof Float) {
			// if (Objects.isNull(param)) {
			// houseOpeVoMap.put(key, 0.0f);
			// }
			// } else if (param instanceof Long) {
			// if (Objects.isNull(param)) {
			// houseOpeVoMap.put(key, 0L);
			// }
			// } else if (param instanceof Boolean) {
			// if (Objects.isNull(param)) {
			// houseOpeVoMap.put(key, false);
			// }
			// } else if (param instanceof Date) {
			// if (Objects.isNull(param)) {
			// houseOpeVoMap.put(key, new Date());
			// }
			// } else if (param instanceof Byte) {
			// if (Objects.isNull(param)) {
			// houseOpeVoMap.put(key, 0);
			// }
			// } else if (param instanceof List) {
			// if (Objects.isNull(param)) {
			// houseOpeVoMap.put(key, new ArrayList<>());
			// }
			// }else if (param instanceof CustomerVo) {
			// if (Objects.isNull(param)) {
			// houseOpeVoMap.put(key, new CustomerVo());
			// }
			// }
			// }
			resultData.put("modelData", houseOpeVoMap);
		}
		// 检查表单中是否有表格项目，并且做相应处理
		List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
		// 获取表格中选项为table的field，并拼接表头
		/*
		 * SystemResourceFieldVo simpleGridField =
		 * FormUtils.getBaseTableField(formFields);
		 * if(!Objects.isNull(simpleGridField)){
		 * simpleGridField.setSimpleGridHeaders(baseTableHandler()); }
		 */
		NsCoreDictionaryVo ownerRelationshipDic = getDictionary("ownerRelationship", organizationId);
		if (ownerRelationshipDic != null) {
			List<SelectItemVo> selectItem = new ArrayList<>();
			for (NsCoreDictionaryitemVo dictionaryitemVo : ownerRelationshipDic.getDictionaryitemVos()) {
				SelectItemVo selectItemVo = new SelectItemVo();
				selectItemVo.setLabel(dictionaryitemVo.getDictionaryitemItemname());
				selectItemVo.setValue(dictionaryitemVo.getDictionaryitemItemcode());
				selectItem.add(selectItemVo);
			}
			resultData.put("selectItem", selectItem);
		}
		resultData.put("fields", formFields);
		redisUtil.setObjectValue(RedisKeysConstants.REDIS_FUNCTION_FIELDS_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString() + "_" + funcId + "_" + interpreter, formFields);
		return result;
	}

	@ApiOperation(value = "新增售楼/转让")
	@RequestMapping(value = "/house-operate-add-sales", method = RequestMethod.POST)
	public RestResult<Long> addSales(@RequestBody HouseOperateSalesVo houseOperateSalesVo) throws Exception {
		BizException.isNull(houseOperateSalesVo.getNewOwner(), "新/当前业主不能为空");
		BizException.isNull(houseOperateSalesVo.getNewOwner().getOwnerId(), "新/当前业主不能为空");
		Map<String, Object> resultMap = houseOperateService.checkStageOperate(houseOperateSalesVo.getDetailId(),
				houseOperateSalesVo.getHouseId(), HouseOperateTypeEnum.SHOU_LOU.getValue());
		if (!(boolean) resultMap.get("result")) {
			BizException.fail(ResultCodeEnum.ILLEGAL_REQUEST, resultMap.get("message").toString());
		}
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		Long organizationId = LoginDataHelper.getOrgId();

		NsCoreDictionaryVo ownerPropertyDic = getDictionary("ownerProperty", organizationId);
		Map<String, NsCoreDictionaryVo> dicMap = new HashMap<>();
		dicMap.put("ownerPropertyDic", ownerPropertyDic);
		long detailId = houseOperateService.addSalse(houseOperateSalesVo, userId, userName, dicMap, false);

		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		// 从redis中删除房产树
		String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString();
		redisUtil.delete(fieldRedisKey);
		return new RestResult<>(detailId);
	}

	@ApiOperation(value = "新增收房")
	@RequestMapping(value = "/house-operate-add-take", method = RequestMethod.POST)
	public RestResult<Long> addTake(@RequestBody HouseOperateSalesVo houseOperateSalesVo) throws Exception {

		Map<String, Object> resultMap = houseOperateService.checkStageOperate(houseOperateSalesVo.getDetailId(),
				houseOperateSalesVo.getHouseId(), HouseOperateTypeEnum.SHOU_FANG.getValue());
		if (!(boolean) resultMap.get("result")) {
			BizException.fail(ResultCodeEnum.ILLEGAL_REQUEST, resultMap.get("message").toString());
		}
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		Long organizationId = LoginDataHelper.getOrgId();

		NsCoreDictionaryVo ownerPropertyDic = getDictionary("ownerProperty", organizationId);
		Map<String, NsCoreDictionaryVo> dicMap = new HashMap<>();
		dicMap.put("ownerPropertyDic", ownerPropertyDic);
		long detailId = houseOperateService.addTake(houseOperateSalesVo, userId, userName, dicMap, false);

		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		// 从redis中删除房产树
		String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString();
		redisUtil.delete(fieldRedisKey);
		return new RestResult<>(detailId);
	}

	@ApiOperation(value = "新增入住")
	@RequestMapping(value = "/house-operate-add-check-in", method = RequestMethod.POST)
	public RestResult<Long> addCheckIn(@RequestBody HouseOperateSalesVo houseOperateSalesVo) throws Exception {
		Map<String, Object> resultMap = houseOperateService.checkStageOperate(houseOperateSalesVo.getDetailId(),
				houseOperateSalesVo.getHouseId(), HouseOperateTypeEnum.RU_ZHU.getValue());
		if (!(boolean) resultMap.get("result")) {
			BizException.fail(ResultCodeEnum.ILLEGAL_REQUEST, resultMap.get("message").toString());
		}
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		Long organizationId = LoginDataHelper.getOrgId();

		long detailId = houseOperateService.addCheckIn(houseOperateSalesVo, userId, userName);

		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		// 从redis中删除房产树
		String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString();
		redisUtil.delete(fieldRedisKey);
		return new RestResult<>(detailId);
	}

	@ApiOperation(value = "新增搬出")
	@RequestMapping(value = "/house-operate-add-move-out", method = RequestMethod.POST)
	public RestResult<Long> addMoveOut(@RequestBody HouseOperateSalesVo houseOperateSalesVo) throws Exception {
		Map<String, Object> resultMap = houseOperateService.checkStageOperate(houseOperateSalesVo.getDetailId(),
				houseOperateSalesVo.getHouseId(), HouseOperateTypeEnum.BAN_CHU.getValue());
		if (!(boolean) resultMap.get("result")) {
			BizException.fail(ResultCodeEnum.ILLEGAL_REQUEST, resultMap.get("message").toString());
		}
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		long detailId = houseOperateService.addMoveOut(houseOperateSalesVo, userId, userName);

		Long organizationId = LoginDataHelper.getOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		// 从redis中删除房产树
		String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString();
		redisUtil.delete(fieldRedisKey);
		return new RestResult<>(detailId);
	}

	@ApiOperation(value = "新增出租")
	@RequestMapping(value = "/house-operate-add-rent", method = RequestMethod.POST)
	public RestResult<Long> addRent(@RequestBody HouseOperateSalesVo houseOperateSalesVo) throws Exception {
		Map<String, Object> resultMap = houseOperateService.checkStageOperate(houseOperateSalesVo.getDetailId(),
				houseOperateSalesVo.getHouseId(), HouseOperateTypeEnum.CHU_ZU.getValue());
		if (!(boolean) resultMap.get("result")) {
			BizException.fail(ResultCodeEnum.ILLEGAL_REQUEST, resultMap.get("message").toString());
		}
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		Long organizationId = LoginDataHelper.getOrgId();

		NsCoreDictionaryVo ownerPropertyDic = getDictionary("ownerProperty", organizationId);
		Map<String, NsCoreDictionaryVo> dicMap = new HashMap<>();
		dicMap.put("ownerPropertyDic", ownerPropertyDic);
		long detailId = houseOperateService.addRent(houseOperateSalesVo, userId, userName, dicMap);

		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		// 从redis中删除房产树
		String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString();
		redisUtil.delete(fieldRedisKey);
		return new RestResult<>(detailId);
	}

	@ApiOperation(value = "新增转租")
	@RequestMapping(value = "/house-operate-add-sublet", method = RequestMethod.POST)
	public RestResult<Long> addSublet(@RequestBody HouseOperateSalesVo houseOperateSalesVo) throws Exception {
		Map<String, Object> resultMap = houseOperateService.checkStageOperate(houseOperateSalesVo.getDetailId(),
				houseOperateSalesVo.getHouseId(), HouseOperateTypeEnum.ZHUAN_ZU.getValue());
		if (!(boolean) resultMap.get("result")) {
			BizException.fail(ResultCodeEnum.ILLEGAL_REQUEST, resultMap.get("message").toString());
		}
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		Long organizationId = LoginDataHelper.getOrgId();

		NsCoreDictionaryVo ownerPropertyDic = getDictionary("ownerProperty", organizationId);
		Map<String, NsCoreDictionaryVo> dicMap = new HashMap<>();
		dicMap.put("ownerPropertyDic", ownerPropertyDic);
		long detailId = houseOperateService.addSublet(houseOperateSalesVo, userId, userName, dicMap);

		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		// 从redis中删除房产树
		String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString();
		redisUtil.delete(fieldRedisKey);
		return new RestResult<>(detailId);
	}

	@ApiOperation(value = "新增退租")
	@RequestMapping(value = "/house-operate-add-rent-out", method = RequestMethod.POST)
	public RestResult<Long> addRentOut(@RequestBody HouseOperateSalesVo houseOperateSalesVo) throws Exception {
		Map<String, Object> resultMap = houseOperateService.checkStageOperate(houseOperateSalesVo.getDetailId(),
				houseOperateSalesVo.getHouseId(), HouseOperateTypeEnum.TUI_ZU.getValue());
		if (!(boolean) resultMap.get("result")) {
			BizException.fail(ResultCodeEnum.ILLEGAL_REQUEST, resultMap.get("message").toString());
		}
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		Long organizationId = LoginDataHelper.getOrgId();

		NsCoreDictionaryVo ownerPropertyDic = getDictionary("ownerProperty", organizationId);
		Map<String, NsCoreDictionaryVo> dicMap = new HashMap<>();
		dicMap.put("ownerPropertyDic", ownerPropertyDic);
		long detailId = houseOperateService.addRentOut(houseOperateSalesVo, userId, userName, dicMap);

		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		// 从redis中删除房产树
		String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString();
		redisUtil.delete(fieldRedisKey);
		return new RestResult<>(detailId);
	}

	@ApiOperation(value = "新增装修")
	@RequestMapping(value = "/house-operate-add-decorate", method = RequestMethod.POST)
	public RestResult<Long> addDecorate(@RequestBody HouseOperateSalesVo houseOperateSalesVo) throws Exception {
		Map<String, Object> resultMap = houseOperateService.checkStageOperate(houseOperateSalesVo.getDetailId(),
				houseOperateSalesVo.getHouseId(), HouseOperateTypeEnum.ZHUANG_XIU.getValue());
		if (!(boolean) resultMap.get("result")) {
			BizException.fail(ResultCodeEnum.ILLEGAL_REQUEST, resultMap.get("message").toString());
		}
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		long detailId = houseOperateService.addDecorate(houseOperateSalesVo, userId, userName);

		Long organizationId = LoginDataHelper.getOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		// 从redis中删除房产树
		String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString();
		redisUtil.delete(fieldRedisKey);
		return new RestResult<>(detailId);
	}

	@ApiOperation(value = "新增空关")
	@RequestMapping(value = "/house-operate-add-empty", method = RequestMethod.POST)
	public RestResult<Long> addEmpty(@RequestBody HouseOperateSalesVo houseOperateSalesVo) throws Exception {
		Map<String, Object> resultMap = houseOperateService.checkStageOperate(houseOperateSalesVo.getDetailId(),
				houseOperateSalesVo.getHouseId(), HouseOperateTypeEnum.KONG_GUAN.getValue());
		if (!(boolean) resultMap.get("result")) {
			BizException.fail(ResultCodeEnum.ILLEGAL_REQUEST, resultMap.get("message").toString());
		}
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		long detailId = houseOperateService.addEmpty(houseOperateSalesVo, userId, userName);

		Long organizationId = LoginDataHelper.getOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		// 从redis中删除房产树
		String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString();
		redisUtil.delete(fieldRedisKey);
		return new RestResult<>(detailId);
	}

	@ApiOperation(value = "修改房态")
	@RequestMapping(value = "/house-operate-edit-house-stage", method = RequestMethod.POST)
	public RestResult<Long> editHouseStage(@RequestBody HouseOperateSalesVo houseOperateSalesVo) throws Exception {
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		// 判断是否为当前
		long detailId = houseOperateService.editHouseStage(houseOperateSalesVo, userId, userName);
		return new RestResult<>(detailId);
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询房态操作列表")
	@RequestMapping(value = "/list-stage-operate", method = RequestMethod.POST)
	public RestResult<PageInfo<HouseListVo>> listStageOperate(@RequestBody SearchVo searchVo,
			@ApiParam("房态操作动作：1售楼 2收房 3入住  5出租  10装修") @RequestParam("houseOperateType") String houseOperateType)
			throws Exception {
		RestResult<PageInfo<HouseListVo>> restResult = null;
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
			RestResult<Map<String, Object>> columnResult = systemRemoteService.listColumnForRemote(nsCoreResourcecolumnVo);
			filedRedisObject = columnResult.getResultData();
		}
		PageInfo<HouseListVo> pageInfo = houseOperateService.listPage(searchVo, houseOperateType,
				(Map<String, Object>) filedRedisObject, true);
		restResult = new RestResult<>(pageInfo);
		return restResult;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "获取列表合计")
	@RequestMapping(value = "/get-total", method = RequestMethod.POST)
	public RestResult<HouseListVo> getTotal(@RequestBody SearchVo searchConditionVo,
			@ApiParam("房态操作动作：1售楼 2收房 3入住  5出租  10装修") @RequestParam("houseOperateType") String houseOperateType)
			throws Exception {
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
		HouseListVo total = houseOperateService.getTotal(searchConditionVo, houseOperateType,
				(Map<String, Object>) filedRedisObject);
		return new RestResult<>(total);
	}

	@ApiOperation(value = "判断房产当前状态下是否可以进行当前操作")
	@RequestMapping(value = "/check-stage-operate", method = RequestMethod.POST)
	public RestResult<Map<String, Object>> checkStageOperate(@ApiParam("房产ID") @RequestParam("houseId") Long houseId,
			@ApiParam("房态操作动作：1售楼 2收房 3入住 4搬出 5出租 6转租 7退租 8空关 9转让 10装修") @RequestParam("houseOperateType") String houseOperateType)
			throws Exception {
		RestResult<Map<String, Object>> restResult = null;
		Map<String, Object> resultMap = houseOperateService.checkStageOperate(null, houseId, houseOperateType);
		restResult = new RestResult<>(resultMap);
		return restResult;
	}

	@ApiOperation(value = "撤销房态操作")
	@RequestMapping(value = "/revoke-stage-detail", method = RequestMethod.POST)
	public RestResult<String> revokeStageDetail(@RequestBody List<Map<String, Long>> list) throws Exception {
		RestResult<String> restResult = null;
		int falseNum = 0;
		int successNum = 0;
		for (Map<String, Long> map : list) {
			Long houseId = map.get("houseId");
			Long detailId = map.get("detailId");
			Long userId = LoginDataHelper.getUserId();
			String userName = LoginDataHelper.getUserName();
			boolean flag = houseOperateService.revokeStageDetail(houseId, detailId, userId, userName);
			if (flag) {
				successNum += 1;
			} else {
				falseNum += 1;
			}
		}
		String revokeResult = "选择" + list.size() + "条，撤销成功" + successNum + "条，失败" + falseNum + "条。";

		Long organizationId = LoginDataHelper.getOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		// 从redis中删除房产树
		String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
				+ organizationId.toString();
		redisUtil.delete(fieldRedisKey);
		restResult = new RestResult<>(revokeResult);
		return restResult;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "导出房产")
	@RequestMapping(value = "/export-house", method = RequestMethod.POST)
	public RestResult<Object> exportHouse(@RequestBody SearchVo searchConditionVo,
			@ApiParam("房态操作动作：1售楼 2收房 3入住  5出租  10装修") @RequestParam("houseOperateType") String houseOperateType,
			HttpServletResponse response) throws Exception {
		Long start = new Date().getTime();
		String funcId = LoginDataHelper.getFuncId();
		Long organizationId = LoginDataHelper.getOrgId();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		searchConditionVo.setEnterpriseId(enterpriseId);
		searchConditionVo.setOrganizationId(organizationId);
		PageInfo<HouseListVo> result = houseOperateService.listPage(searchConditionVo, houseOperateType, null, false);
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
				HouseOperateTypeEnum houseOperateTypeEnum = HouseOperateTypeEnum.getInstance(houseOperateType);
				String sheetName = houseOperateTypeEnum.getTitle() + "信息";
				ExcelHelper.exportExcel(result.getList(), columnList, sheetName, response);
			}
		}

		Long end = new Date().getTime();
		Long time = end - start;
		System.out.println("exportHouse========" + time);
		return new RestResult<>();
	}

	private NsCoreDictionaryVo getDictionary(String code, Long organizationId) throws Exception {
		NsCoreDictionaryVo dictionaryVo = null;
		NsCoreDictionary dictionary = new NsCoreDictionary();
		dictionary.setDictionaryDdcode(code);
		dictionary.setOrganizationId(organizationId);
		RestResult<NsCoreDictionaryVo> dictionaryResult = systemRemoteService.getDictionary(dictionary);
		if (dictionaryResult != null) {
			dictionaryVo = dictionaryResult.getResultData();
		}
		return dictionaryVo;
	}

}
