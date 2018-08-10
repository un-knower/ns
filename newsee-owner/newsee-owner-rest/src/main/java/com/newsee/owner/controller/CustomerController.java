package com.newsee.owner.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.newsee.owner.entity.OwnerCustomerBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestLog;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.ProvinceCityArea;
import com.newsee.common.vo.SearchVo;
import com.newsee.owner.entity.OwnerCustomerResult;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import com.newsee.owner.service.ICarService;
import com.newsee.owner.service.ICustomerService;
import com.newsee.owner.service.IHouseService;
import com.newsee.owner.service.remote.ISystemRemoteService;
import com.newsee.owner.utils.OwnerUtils;
import com.newsee.owner.vo.CarVo;
import com.newsee.owner.vo.CustomerFamilyVo;
import com.newsee.owner.vo.CustomerVo;
import com.newsee.owner.vo.FamilyInfoVo;
import com.newsee.owner.vo.MainHouseVo;
import com.newsee.owner.vo.OwnerBankAccountVo;
import com.newsee.owner.vo.OwnerHouseRelationshipVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.entity.NsSystemArea;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsSystemAreaVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@ResponseBody
@RequestMapping(value = "/customer")
@Api(tags = {"com.newsee.owner.controller.CustomerController"}, description = "菜单按钮  REST API，包含所有菜单，页面按钮的操作方法。")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IHouseService houseService;

    @Autowired
    private ICarService carService;

    @Autowired
    private ISystemRemoteService systemRemoteService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 模块名称，日志记录用
     */
    private final String customer = "客户";
    private final String famliy = "家庭成员";
    private final String bank = "银行账户";

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm() {
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
        // String funcId = LoginDataHelper.getFuncId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.CUSTOMER_MANAGEMENT);
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
        // 检查表单中是否有表格项目，并且做相应处理
        List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
        // 获取表格中选项为table的field，并拼接表头
        /*
         * SystemResourceFieldVo simpleGridField = FormUtils.getBaseTableField(formFields); if(!Objects.isNull(simpleGridField)){
         * simpleGridField.setSimpleGridHeaders(baseTableHandler()); }
         */

        if (!Objects.isNull(resultData.get("modelData"))) {
            Map<String, Object> customerVomodelMap = (Map<String, Object>) resultData.get("modelData");
            if (Objects.isNull(customerVomodelMap.get("pictures")) || StringUtils.isBlank((String) customerVomodelMap.get("pictures"))) {
                customerVomodelMap.put("pictures", new ArrayList<>());
            }
            if (Objects.isNull(customerVomodelMap.get("mainHouseList")) || StringUtils.isBlank((String) customerVomodelMap.get("mainHouseList"))
                    || CollectionUtils.isEmpty(((List<MainHouseVo>) customerVomodelMap.get("mainHouseList")))) {
                customerVomodelMap.put("mainHouseList", new ArrayList<>());
                Iterator<NsCoreResourcefieldVo> it = formFields.iterator();
                while (it.hasNext()) {
                    NsCoreResourcefieldVo fieldVo = (NsCoreResourcefieldVo) it.next();
                    if ("mainHouseList".equals(fieldVo.getResourcefieldCode())) {
                        it.remove();
                    }

                }

            }
        }
        resultData.put("fields", formFields);
        // redisUtil.setObjectValue(RedisKeysConstants.REDIS_FUNCTION_FIELDS_PREFIX
        // + "_" + enterpriseId.toString() + "_" + organizationId.toString() +
        // "_" + funcId + "_" + interpreter + "_" + formOperateType,
        // formFields);
        return result;
    }

    @ApiOperation(value = "初始化省市区控件")
    @RequestMapping(value = "/initProvinceCityArea", method = RequestMethod.GET)
    public RestResult<Map<String, List<NsSystemAreaVo>>> initProvinceCityArea(@ApiParam("区域级别Level 1省 2市 3区 4街道") @RequestParam(name = "areaLevel", required = false) String areaLevel,
                                                                              @ApiParam("区域编码，根据区域编码，获取其下的子区域") @RequestParam(name = "areaCode", required = false) String areaCode,
                                                                              @ApiParam("客户ID") @RequestParam(name = "queryParam", required = false) String queryParam) {
        Map<String, List<NsSystemAreaVo>> itemListMap = null;

        // 点击请求
        if ((!StringUtils.isBlank(areaLevel) || !StringUtils.isBlank(areaCode)) && (StringUtils.isBlank(queryParam))) {
            if (("1".equals(areaLevel) && StringUtils.isBlank(areaCode)) || ("2".equals(areaLevel) && !StringUtils.isBlank(areaCode)) || ("3".equals(areaLevel) && !StringUtils.isBlank(areaCode))
                    || ("4".equals(areaLevel) && !StringUtils.isBlank(areaCode))) {
                itemListMap = new HashMap<>();
                List<NsSystemAreaVo> areaList = new ArrayList<>();
                RestResult<List<NsSystemAreaVo>> resultAreas = systemRemoteService.areaFuncinfo(areaLevel, areaCode);
                if (!Objects.isNull(resultAreas)) {
                    areaList = resultAreas.getResultData();
                }
                if ("1".equals(areaLevel)) {
                    itemListMap.put("provinces", areaList);
                } else if ("2".equals(areaLevel)) {
                    itemListMap.put("cities", areaList);
                } else if ("3".equals(areaLevel)) {
                    itemListMap.put("districts", areaList);
                } else if ("4".equals(areaLevel)) {
                    itemListMap.put("streets", areaList);
                }
            }
        }

        // 编辑页面初始化请求
        if ((StringUtils.isBlank(areaLevel) && StringUtils.isBlank(areaCode)) && (!StringUtils.isBlank(queryParam))) {
            itemListMap = new HashMap<>();
            Long ownerId = Long.parseLong(queryParam);
            CustomerVo customerVo = customerService.detail(ownerId);
            ProvinceCityArea provincialandcity = customerVo.getProvinceCityArea();
            // 省
            List<NsSystemAreaVo> provinceitemList = new ArrayList<>();
            NsSystemAreaVo provinceEntity = new NsSystemAreaVo();
            NsSystemArea proArea = systemRemoteService.getArea(provincialandcity.getProvince());
            if (!Objects.isNull(proArea)) {
                provinceEntity.setLabel(proArea.getAreaName());
                provinceEntity.setValue(provincialandcity.getProvince());
                provinceitemList.add(provinceEntity);
                itemListMap.put("provinces", provinceitemList);
            } else {
                itemListMap.put("provinces", new ArrayList<>());
            }
            // 市
            List<NsSystemAreaVo> cityitemList = new ArrayList<>();
            NsSystemAreaVo cityEntity = new NsSystemAreaVo();
            NsSystemArea cityArea = systemRemoteService.getArea(provincialandcity.getCity());
            if (!Objects.isNull(cityArea)) {
                cityEntity.setLabel(cityArea.getAreaName());
                cityEntity.setValue(provincialandcity.getCity());
                cityitemList.add(cityEntity);
                itemListMap.put("cities", cityitemList);
            } else {
                itemListMap.put("cities", new ArrayList<>());
            }
            // 区
            List<NsSystemAreaVo> areaitemList = new ArrayList<>();
            NsSystemAreaVo areaEntity = new NsSystemAreaVo();
            NsSystemArea areaArea = systemRemoteService.getArea(provincialandcity.getDistrict());
            if (!Objects.isNull(areaArea)) {
                areaEntity.setLabel(areaArea.getAreaName());
                areaEntity.setValue(provincialandcity.getDistrict());
                areaitemList.add(areaEntity);
                itemListMap.put("districts", areaitemList);
            } else {
                itemListMap.put("districts", new ArrayList<>());
            }
            // 街道
            List<NsSystemAreaVo> streetitemList = new ArrayList<>();
            NsSystemAreaVo streetEntity = new NsSystemAreaVo();
            NsSystemArea streetArea = systemRemoteService.getArea(provincialandcity.getStreet());
            if (!Objects.isNull(streetArea)) {
                streetEntity.setLabel(streetArea.getAreaName());
                streetEntity.setValue(provincialandcity.getStreet());
                streetitemList.add(streetEntity);
                itemListMap.put("streets", streetitemList);
            } else {
                itemListMap.put("streets", new ArrayList<>());
            }
        }
        return new RestResult<>(itemListMap);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "查询客户列表")
    @RequestMapping(value = "/list-customer", method = RequestMethod.POST)
    public RestResult<PageInfo<OwnerCustomerResult>> listCustomer(@RequestBody SearchVo searchVo) throws Exception {
        BizException.isNull(searchVo, "查询条件");
        RestResult<PageInfo<OwnerCustomerResult>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = LoginDataHelper.getFuncId();

        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);

        NsCoreResourcecolumnVo nsCoreResourcecolumnVo = new NsCoreResourcecolumnVo();
        nsCoreResourcecolumnVo.setEnterpriseId(enterpriseId);
        nsCoreResourcecolumnVo.setOrganizationId(organizationId);
        nsCoreResourcecolumnVo.setResourcecolumnFuncinfoId(funcId);

        // 从redis中获取是否有缓存，如有，从缓存中获取，如无，从数据库中重新获取表单项目
        String fieldRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX + "_" + enterpriseId.toString() + "_" + organizationId.toString() + "_" + funcId;
        Object filedRedisObject = redisUtil.getObjectValue(fieldRedisKey);
        if (filedRedisObject == null) {
            RestResult<Map<String, Object>> columnResult = systemRemoteService.listColumnForRemote(nsCoreResourcecolumnVo);
            filedRedisObject = columnResult.getResultData();
        }

        PageInfo<OwnerCustomerResult> pageInfo = customerService.listPage(searchVo, (Map<String, Object>) filedRedisObject, true);

        restResult = new RestResult<>(pageInfo);
        return restResult;
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取列表合计")
    @RequestMapping(value = "/get-total", method = RequestMethod.POST)
    public RestResult<OwnerCustomerResult> getTotal(@RequestBody SearchVo searchConditionVo) throws Exception {
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
        String fieldRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX + "_" + enterpriseId.toString() + "_" + organizationId.toString() + "_" + funcId;
        Object filedRedisObject = redisUtil.getObjectValue(fieldRedisKey);
        if (filedRedisObject == null) {
            RestResult<Map<String, Object>> columnResult = systemRemoteService.listColumnForRemote(nsCoreResourcecolumnVo);
            filedRedisObject = columnResult.getResultData();
        }
        OwnerCustomerResult total = customerService.getTotal(searchConditionVo, (Map<String, Object>) filedRedisObject);
        return new RestResult<>(total);
    }

    @ApiOperation(value = "获取客户信息")
    @RequestMapping(value = "/detail-customer-for-search", method = RequestMethod.GET)
    public RestResult<List<CustomerVo>> detailCustomerForSearch(Long ownerId) {
        CustomerVo customerVo = customerService.detail(ownerId);
        List<CustomerVo> list = new ArrayList<>(1);
        list.add(customerVo);
        return new RestResult<>(list);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "客户表单详情")
    @RequestMapping(value = "/detail-customer", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailCustomer(Long ownerId) {
        RestResult<Map<String, Object>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
        // String funcId = LoginDataHelper.getFuncId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.CUSTOMER_MANAGEMENT);
        String interpreter = LoginDataHelper.getFieldInterpreter();
        String formOperateType = LoginDataHelper.getFormOperateType();
        CustomerVo customerVo = customerService.detail(ownerId);
        if (!Objects.isNull(customerVo)) {
            NsCoreDictionaryVo ownerPropertyDic = getDictionary("ownerProperty", organizationId);
            Map<String, NsCoreDictionaryVo> dicMap = new HashMap<>();
            dicMap.put("ownerPropertyDic", ownerPropertyDic);
            Map<String, Object> precinctHouseMap = houseService.listPrecinctHouse(ownerId, dicMap);
            customerVo.setMainHouseList((List<MainHouseVo>) precinctHouseMap.get("ownerMainHouseList"));
            /*
             * @SuppressWarnings("unchecked") Map<String, Object> functionInfo = (Map<String, Object>)redisUtil.getObjectValue(RedisKeysConstants.
             * REDIS_FUNCTION_INFO_PREFIX + "_" +enterpriseId.toString()+"_"+ organizationId.toString()+"_"+funcId);
             */
            LoginCommonDataVo commonVo = new LoginCommonDataVo();
            commonVo.setOrganizationId(organizationId);
            commonVo.setEnterpriseId(enterpriseId);
            commonVo.setGroupLevelOrgId(groupLevelOrgId);
            commonVo.setFuncId(funcId);
            commonVo.setInterpreter(interpreter);
            commonVo.setFormOperateType(formOperateType);
            RestResult<Map<String, Object>> result = systemRemoteService.listField(commonVo);
            Map<String, Object> formInfo = result.getResultData();
            formInfo.put("tableItems", precinctHouseMap.get("ownerHouseList"));
            List<NsCoreResourcefieldVo> fields = null;
            // List<NsCoreResourcefieldVo> fields =
            // (List<NsCoreResourcefieldVo>)redisUtil.getObjectValue(RedisKeysConstants.REDIS_FUNCTION_FIELDS_PREFIX
            // + "_" + enterpriseId.toString() + "_" + organizationId.toString()
            // + "_" + funcId + "_" + interpreter + "_" + formOperateType);
            if (CollectionUtils.isEmpty(fields)) {
                // 检查表单中是否有表格项目，并且做相应处理
                List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(formInfo);
                fields = formFields;
            }
            // ------------modeldata处理
            // 去空
            customerVo = CommonUtils.clearNull(customerVo);
            formInfo.put("modelData", customerVo);
            // ------------filed处理
            if (!Objects.isNull(formInfo.get("modelData"))) {
                CustomerVo customerVoTemp = (CustomerVo) formInfo.get("modelData");
                if (Objects.isNull(customerVoTemp.getMainHouseList()) || CollectionUtils.isEmpty(customerVoTemp.getMainHouseList())) {
                    Iterator<NsCoreResourcefieldVo> it = fields.iterator();
                    while (it.hasNext()) {
                        NsCoreResourcefieldVo fieldVo = (NsCoreResourcefieldVo) it.next();
                        if ("mainHouseList".equals(fieldVo.getResourcefieldCode())) {
                            it.remove();
                        }

                    }

                }
            }
            formInfo.put("fields", fields);
            restResult = new RestResult<>(formInfo);
        }
        return restResult;
    }

    @ApiOperation(value = "新增客户")
    @RequestMapping(value = "/add-customer", method = RequestMethod.POST)
    public RestResult<Long> addCustomer(@RequestBody CustomerVo customerVo) {
        RestResult<Long> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();

        customerVo.setOrganizationId(organizationId);
        customerVo.setHandlerId(userId);
        customerVo.setCreateUserName(userName);
        customerVo.setUpdateUserName(userName);
        customerVo.setEnterpriseId(enterpriseId);
        NsCoreDictionaryVo ownerTypeDic = getDictionary("ownerType", organizationId);
        NsCoreDictionaryVo ownerPropertyDic = getDictionary("ownerProperty", organizationId);
        NsCoreDictionaryVo ownerLevelDic = getDictionary("ownerLevel", organizationId);
        NsCoreDictionaryVo certificateTypeDic = getDictionary("certificateType", organizationId);
        NsCoreDictionaryVo maritalStatusDic = getDictionary("maritalStatus", organizationId);
        NsCoreDictionaryVo educationDic = getDictionary("education", organizationId);
        NsCoreDictionaryVo regionDic = getDictionary("region", organizationId);
        NsCoreDictionaryVo nationDic = getDictionary("nation", organizationId);
        NsCoreDictionaryVo interestDic = getDictionary("interestsIdList", organizationId);
        NsCoreDictionaryVo genderDic = getDictionary("gender", organizationId);
        NsCoreDictionaryVo tradeDic = getDictionary("tradeId", organizationId);
        Map<String, NsCoreDictionaryVo> dicMap = new HashMap<>();
        dicMap.put("certificateTypeDic", certificateTypeDic);
        dicMap.put("maritalStatusDic", maritalStatusDic);
        dicMap.put("educationDic", educationDic);
        dicMap.put("regionDic", regionDic);
        dicMap.put("nationDic", nationDic);
        dicMap.put("ownerTypeDic", ownerTypeDic);
        dicMap.put("ownerPropertyDic", ownerPropertyDic);
        dicMap.put("ownerLevelDic", ownerLevelDic);
        dicMap.put("interestDic", interestDic);
        dicMap.put("genderDic", genderDic);
        dicMap.put("tradeDic", tradeDic);
        Long ownerId = customerService.add(customerVo, dicMap);
        restResult = new RestResult<>(ownerId);
        // 获取表单id,组合业务日志记录
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.CUSTOMER_MANAGEMENT);
        RestLog log = new RestLog(ownerId, customer, customerVo.getOwnerName(), funcId);
        restResult = new RestResult<>(ownerId);
        restResult.setRestLog(log);
        return restResult;
    }

    @ApiOperation(value = "更新客户")
    @RequestMapping(value = "/edit-customer", method = RequestMethod.POST)
    public RestResult<Integer> editCustomer(@RequestBody CustomerVo customerVo) throws Exception {
        RestResult<Integer> restResult = null;
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Long organizationId = LoginDataHelper.getOrgId();

        customerVo.setHandlerId(userId);
        customerVo.setUpdateUserName(userName);
        NsCoreDictionaryVo ownerTypeDic = getDictionary("ownerType", organizationId);
        NsCoreDictionaryVo ownerPropertyDic = getDictionary("ownerProperty", organizationId);
        NsCoreDictionaryVo ownerLevelDic = getDictionary("ownerLevel", organizationId);
        NsCoreDictionaryVo certificateTypeDic = getDictionary("certificateType", organizationId);
        NsCoreDictionaryVo maritalStatusDic = getDictionary("maritalStatus", organizationId);
        NsCoreDictionaryVo educationDic = getDictionary("education", organizationId);
        NsCoreDictionaryVo regionDic = getDictionary("region", organizationId);
        NsCoreDictionaryVo nationDic = getDictionary("nation", organizationId);
        NsCoreDictionaryVo interestDic = getDictionary("interestsIdList", organizationId);
        NsCoreDictionaryVo genderDic = getDictionary("gender", organizationId);
        NsCoreDictionaryVo tradeDic = getDictionary("tradeId", organizationId);
        Map<String, NsCoreDictionaryVo> dicMap = new HashMap<>();
        dicMap.put("certificateTypeDic", certificateTypeDic);
        dicMap.put("maritalStatusDic", maritalStatusDic);
        dicMap.put("educationDic", educationDic);
        dicMap.put("regionDic", regionDic);
        dicMap.put("nationDic", nationDic);
        dicMap.put("ownerTypeDic", ownerTypeDic);
        dicMap.put("ownerPropertyDic", ownerPropertyDic);
        dicMap.put("ownerLevelDic", ownerLevelDic);
        dicMap.put("interestDic", interestDic);
        dicMap.put("genderDic", genderDic);
        dicMap.put("tradeDic", tradeDic);
        int index = customerService.edit(customerVo, dicMap);
        restResult = new RestResult<>(index);
        return restResult;
    }

    @ApiOperation(value = "客户视图")
    @RequestMapping(value = "/customer-view", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> customerView(Long ownerId) {
        RestResult<Map<String, Object>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Map<String, Object> map = new HashMap<>();
        CustomerVo customerVo = customerService.detail(ownerId);
        if (customerVo != null) {
            NsCoreDictionaryVo ownerTypeDic = getDictionary("ownerType", organizationId);
            NsCoreDictionaryVo ownerPropertyDic = getDictionary("ownerProperty", organizationId);
            NsCoreDictionaryVo ownerLevelDic = getDictionary("ownerLevel", organizationId);
            NsCoreDictionaryVo certificateTypeDic = getDictionary("certificateType", organizationId);
            NsCoreDictionaryVo maritalStatusDic = getDictionary("maritalStatus", organizationId);
            NsCoreDictionaryVo educationDic = getDictionary("education", organizationId);
            NsCoreDictionaryVo regionDic = getDictionary("region", organizationId);
            NsCoreDictionaryVo nationDic = getDictionary("nation", organizationId);
            NsCoreDictionaryVo interestDic = getDictionary("interestsIdList", organizationId);
            NsCoreDictionaryVo tradeDic = getDictionary("tradeId", organizationId);

            String ownerTypeName = OwnerUtils.getDicName(ownerTypeDic, customerVo.getOwnerType());
            customerVo.setOwnerTypeName(ownerTypeName);
            String ownerPropertyName = OwnerUtils.getDicName(ownerPropertyDic, customerVo.getOwnerProperty());
            customerVo.setOwnerPropertyName(ownerPropertyName);
            String ownerLevelName = OwnerUtils.getDicName(ownerLevelDic, customerVo.getOwnerLevel());
            customerVo.setOwnerLevelName(ownerLevelName);
            String certificateTypeName = OwnerUtils.getDicName(certificateTypeDic, customerVo.getCertificateType());
            customerVo.setCertificateTypeName(certificateTypeName);
            String maritalStatusName = OwnerUtils.getDicName(maritalStatusDic, customerVo.getMaritalStatus());
            customerVo.setMaritalStatusName(maritalStatusName);
            String educationName = OwnerUtils.getDicName(educationDic, customerVo.getEducation());
            customerVo.setEducationName(educationName);
            String regionName = OwnerUtils.getDicName(regionDic, customerVo.getRegion());
            customerVo.setRegionName(regionName);
            String nationName = OwnerUtils.getDicName(nationDic, customerVo.getNation());
            customerVo.setNationName(nationName);
            String interestsNames = OwnerUtils.getDicArrayName(interestDic, customerVo.getInterestsIds());
            customerVo.setInterestsNames(interestsNames);
            String tradeName = OwnerUtils.getDicName(tradeDic, customerVo.getTradeId());
            customerVo.setTradeName(tradeName);
            if (StringUtils.hasLength(customerVo.getProvinceId())) {
                NsSystemArea province = systemRemoteService.getArea(customerVo.getProvinceId());
                if (province != null) {
                    customerVo.setProvinceName(province.getAreaName());
                }
            }
            if (StringUtils.hasLength(customerVo.getCityId())) {
                NsSystemArea city = systemRemoteService.getArea(customerVo.getCityId());
                if (city != null) {
                    customerVo.setCityName(city.getAreaName());
                }
            }
            if (StringUtils.hasLength(customerVo.getAreaId())) {
                NsSystemArea area = systemRemoteService.getArea(customerVo.getAreaId());
                if (area != null) {
                    customerVo.setAreaName(area.getAreaName());
                }
            }
            if (StringUtils.hasLength(customerVo.getStreetId())) {
                NsSystemArea street = systemRemoteService.getArea(customerVo.getStreetId());
                if (street != null) {
                    customerVo.setStreetName(street.getAreaName());
                }
            }
            // 客户信息
            map.put("customerVo", customerVo);
            // 客户性质
            // 项目信息
            List<OwnerHouseBaseInfo> precinctList = houseService.listOwnerPrecinct(customerVo);
            map.put("precinctList", precinctList);
            // 车辆信息
            List<CarVo> ownerCarList = carService.listOwnerCar(ownerId);
            NsCoreDictionaryVo vehicleBrandDic = getDictionary("vehicleBrandId", organizationId);
            NsCoreDictionaryVo carColorDic = getDictionary("carColorId", organizationId);
            for (CarVo carVo : ownerCarList) {
                if (StringUtils.hasLength(carVo.getVehicleBrandId())) {
                    String vehicleBrandName = OwnerUtils.getDicName(vehicleBrandDic, carVo.getVehicleBrandId());
                    carVo.setVehicleBrandName(vehicleBrandName);
                }
                if (StringUtils.hasLength(carVo.getCarColorId())) {
                    String carColorName = OwnerUtils.getDicName(carColorDic, carVo.getCarColorId());
                    carVo.setCarColorName(carColorName);
                }
            }
            map.put("ownerCarList", ownerCarList);
            // TODO 客户缴费信息
            restResult = new RestResult<>(map);
        } else {
            restResult = new RestResult<>(ResultCodeEnum.DATA_NOT_EXIST.CODE, ResultCodeEnum.DATA_NOT_EXIST.DESC);
        }
        return restResult;
    }

    @ApiOperation(value = "根据客户名称模糊查询")
    @RequestMapping(value = "/list-customer-search", method = RequestMethod.GET)
    public RestResult<List<CustomerVo>> listCustomerForSearch(@RequestParam("customerName") String customerName, @ApiParam("0非开发商客户 1开发商 2所有客户") @RequestParam("isDeveloper") Integer isDeveloper,
                                                              @ApiParam("需要屏蔽的客户ID") @RequestParam(name = "ownerId", required = false) Long ownerId) {
        RestResult<List<CustomerVo>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        List<CustomerVo> list = customerService.listCustomerForSearch(customerName, organizationId, isDeveloper, ownerId);
        restResult = new RestResult<>(list);
        return restResult;
    }

    @ApiOperation(value = "获取客户房产--选择房产项目")
    @RequestMapping(value = "/list-owner-precinct", method = RequestMethod.POST)
    public RestResult<List<OwnerHouseRelationshipVo>> listOwnerPrecinct(Long ownerId) {
        RestResult<List<OwnerHouseRelationshipVo>> restResult = null;
        // 项目信息
        List<OwnerHouseRelationshipVo> precinctList = houseService.listOwnerPrecinctRelation(ownerId);
        restResult = new RestResult<>(precinctList);
        return restResult;
    }

    @ApiOperation(value = "获取客户房产、车位")
    @RequestMapping(value = "/list-owner-house", method = RequestMethod.POST)
    public RestResult<List<OwnerHouseRelationshipVo>> listOwnerHouse(Long ownerId, Long precinctId, Byte houseType) {
        // RestResult<List<OwnerHouseRelationshipVo>> restResult = null;
        // 房产信息
        Long organizationId = LoginDataHelper.getOrgId();
        List<OwnerHouseRelationshipVo> houseList = houseService.listOwnerHouseRelation(ownerId, houseType, precinctId);
        if (!CollectionUtils.isEmpty(houseList)) {
            NsCoreDictionaryVo ownerPropertyDic = getDictionary("ownerProperty", organizationId);
            for (OwnerHouseRelationshipVo ownerHouseRelationshipVo : houseList) {
                String ownerPropertyName = OwnerUtils.getDicName(ownerPropertyDic, ownerHouseRelationshipVo.getOwnerProperty());
                ownerHouseRelationshipVo.setOwnerPropertyName(ownerPropertyName);
            }
            // restResult = new RestResult<>(houseList);
        } else {
            houseList = new ArrayList<>(0);
            // restResult = new RestResult<>(ResultCodeEnum.DATA_NOT_EXIST.CODE,
            // ResultCodeEnum.DATA_NOT_EXIST.DESC);
        }
        return new RestResult<>(houseList);
    }

    @ApiOperation(value = "删除全部客户")
    @RequestMapping(value = "/all-delete-customer", method = RequestMethod.POST)
    public RestResult<String> deleteCustomerBatch(@ApiParam(value = "客户列表查询条件") @RequestBody SearchVo searchVo) throws Exception {
        BizException.isNull(searchVo, "客户列表查询条件");
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();

        Map<String, Object> map = customerService.deleteAllCustomerBySearch(searchVo, userId, userName);
        if (!(boolean) map.get("result")) {
            return new RestResult<>(ResultCodeEnum.FAILURE.CODE, (String) map.get("message"));
        }
        return new RestResult<>(ResultCodeEnum.SUCCESS.CODE, (String) map.get("message"));
    }

    @ApiOperation(value = "批量删除客户")
    @RequestMapping(value = "/batch-delete-customer", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> deleteCustomerBatch(@ApiParam(value = "客户IDList") @RequestBody List<Long> customerIdList) {
        BizException.isNull(customerIdList, "客户ID");
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Map<String, Object> map = customerService.deleteCustomerBatch(customerIdList, userId, userName);
        if (!(boolean) map.get("result")) {
            return new RestResult<>(ResultCodeEnum.FAILURE.CODE, (String) map.get("message"));
        }
        return new RestResult<>(ResultCodeEnum.SUCCESS.CODE, (String) map.get("message"));
    }

    @ApiOperation(value = "新增客户家庭关系")
    @RequestMapping(value = "/add-family", method = RequestMethod.POST)
    public RestResult<Integer> addFamily(@RequestBody CustomerFamilyVo customerFamilyVo) throws Exception {
        // BizException.isNull(customerFamilyVo.getCustomerVo(), "家庭成员信息");
        BizException.isNull(customerFamilyVo.getOwnerId(), "家庭成员不能为空");
        BizException.isNull(customerFamilyVo.getRelationOwnerId(), "业主ID不能为空");
        RestResult<Integer> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long userId = LoginDataHelper.getUserId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String userName = LoginDataHelper.getUserName();
        customerFamilyVo.setHandlerId(userId);
        if (customerFamilyVo.getCustomerVo() != null) {
            customerFamilyVo.getCustomerVo().setOrganizationId(organizationId);
            customerFamilyVo.getCustomerVo().setEnterpriseId(enterpriseId);
            customerFamilyVo.getCustomerVo().setHandlerId(userId);
            customerFamilyVo.getCustomerVo().setCreateUserName(userName);
            customerFamilyVo.getCustomerVo().setUpdateUserName(userName);
        }
        NsCoreDictionaryVo ownerTypeDic = getDictionary("ownerType", organizationId);
        NsCoreDictionaryVo ownerPropertyDic = getDictionary("ownerProperty", organizationId);
        NsCoreDictionaryVo ownerLevelDic = getDictionary("ownerLevel", organizationId);
        NsCoreDictionaryVo certificateTypeDic = getDictionary("certificateType", organizationId);
        NsCoreDictionaryVo maritalStatusDic = getDictionary("maritalStatus", organizationId);
        NsCoreDictionaryVo educationDic = getDictionary("education", organizationId);
        NsCoreDictionaryVo regionDic = getDictionary("region", organizationId);
        NsCoreDictionaryVo nationDic = getDictionary("nation", organizationId);
        NsCoreDictionaryVo interestDic = getDictionary("interestsIdList", organizationId);
        NsCoreDictionaryVo genderDic = getDictionary("gender", organizationId);
        Map<String, NsCoreDictionaryVo> dicMap = new HashMap<>();
        dicMap.put("certificateTypeDic", certificateTypeDic);
        dicMap.put("maritalStatusDic", maritalStatusDic);
        dicMap.put("educationDic", educationDic);
        dicMap.put("regionDic", regionDic);
        dicMap.put("nationDic", nationDic);
        dicMap.put("ownerTypeDic", ownerTypeDic);
        dicMap.put("ownerPropertyDic", ownerPropertyDic);
        dicMap.put("ownerLevelDic", ownerLevelDic);
        dicMap.put("interestDic", interestDic);
        dicMap.put("genderDic", genderDic);
        int index = customerService.addFamily(customerFamilyVo, dicMap);
        // 业务日志记录用
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.CUSTOMER_MANAGEMENT);
        RestLog log = new RestLog(customerFamilyVo.getOwnerId(), famliy, customerFamilyVo.getOwnerName(), funcId);
        restResult = new RestResult<>(index);
        restResult.setRestLog(log);
        return restResult;
    }

    @ApiOperation(value = "删除客户家庭关系")
    @RequestMapping(value = "/delete-family", method = RequestMethod.DELETE)
    public RestResult<Integer> deleteFamily(Long ownerId, Long relationOwnerId) throws Exception {
        RestResult<Integer> restResult = null;
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        int index = customerService.deleteFamily(ownerId, relationOwnerId, userId, userName);
        restResult = new RestResult<>(index);
        return restResult;
    }

    @ApiOperation(value = "查看客户家庭成员")
    @RequestMapping(value = "/detail-family", method = RequestMethod.GET)
    public RestResult<List<FamilyInfoVo>> detailFamily(Long ownerId) {
        RestResult<List<FamilyInfoVo>> restResult = null;
        List<FamilyInfoVo> list = customerService.listFamilyByOwnerId(ownerId);
        restResult = new RestResult<>(list);
        return restResult;
    }

    @ApiOperation(value = "查看客户银行账户")
    @RequestMapping(value = "/detail-bank", method = RequestMethod.GET)
    public RestResult<List<OwnerBankAccountVo>> detailBank(Long ownerId) {
        RestResult<List<OwnerBankAccountVo>> restResult = null;
        List<OwnerBankAccountVo> list = customerService.listBankByOwnerId(ownerId);
        restResult = new RestResult<>(list);
        return restResult;
    }

    @ApiOperation(value = "查看客户单个银行账户")
    @RequestMapping(value = "/detail-single-bank", method = RequestMethod.GET)
    public RestResult<OwnerBankAccountVo> detailSingleBank(Long ownerBankAccountId) {
        RestResult<OwnerBankAccountVo> restResult = null;
        OwnerBankAccountVo ownerBankAccountVo = customerService.detailSingleBank(ownerBankAccountId);
        restResult = new RestResult<>(ownerBankAccountVo);
        return restResult;
    }

    @ApiOperation(value = "新增客户银行账户")
    @RequestMapping(value = "/add-bank", method = RequestMethod.POST)
    public RestResult<Integer> addBank(@RequestBody OwnerBankAccountVo ownerBankAccountVo) {
        RestResult<Integer> restResult = null;
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        int index = customerService.addBank(ownerBankAccountVo, userId, userName);
        // 业务日志记录用
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.CUSTOMER_MANAGEMENT);
        RestLog log = new RestLog(ownerBankAccountVo.getOwnerId(), bank, ownerBankAccountVo.getAccountName(), funcId);
        restResult = new RestResult<>(index);
        restResult.setRestLog(log);
        return restResult;
    }

    @ApiOperation(value = "删除客户银行账户")
    @RequestMapping(value = "/delete-bank", method = RequestMethod.DELETE)
    public RestResult<Integer> deleteBank(Long ownerId, Long ownerBankAccountId) {
        RestResult<Integer> restResult = null;
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        int index = customerService.deleteBank(ownerId, ownerBankAccountId, userId, userName);
        restResult = new RestResult<>(index);
        return restResult;
    }

    private NsCoreDictionaryVo getDictionary(String code, Long organizationId) {
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

    @ApiOperation(value = "查询指定节点下的客户")
    @RequestMapping(value = "/list-all-customer", method = RequestMethod.GET)
    public RestResult<List<CustomerVo>> listAllCustomer(@ApiParam(value = "房产ID") @RequestParam("houseId") Long houseId) {
        BizException.isNull(houseId, "房产ID");
        List<CustomerVo> customerVoList = customerService.listAllCustomer(houseId);
        return new RestResult<>(customerVoList);
    }

    @ApiOperation(value = "查询指定节点下，指定类型的一个客户")
    @RequestMapping(value = "/get-customer", method = RequestMethod.GET)
    public RestResult<CustomerVo> getCustomer(
            @ApiParam(value = "房产ID") @RequestParam("houseId") Long houseId,
            @ApiParam(value = "客户类型:0业主 1租户 2住户 3开发商") @RequestParam("ownerProperty") String ownerProperty,
            @ApiParam(value = "查询类型,0:租户->业主->开发商,1:业主->租户") @RequestParam(value = "type", required = false) String type) {
        BizException.isNull(houseId, "房产ID");
        BizException.isNull(ownerProperty, "类型");
        CustomerVo customerVo = customerService.getCustomerByHouseIdAndProperty(houseId, ownerProperty,type);
        return new RestResult<>(customerVo);
    }

    @ApiOperation(value = "根据条件搜索客户信息")
    @RequestMapping(value = "/search-customer", method = RequestMethod.GET)
    public RestResult<List<OwnerCustomerBaseInfo>> searchCustomer(@ApiParam(value = "房产ID") @RequestParam("houseId") Long houseId,
                                                       @ApiParam(value = "客户类型:0业主 1租户 2住户 3开发商") @RequestParam("ownerProperty") String ownerProperty,
                                                       @ApiParam(value = "客户名称") @RequestParam("name") String name,
                                                       @ApiParam(value = "客户类型:0个人 1企业") @RequestParam("ownerType") String ownerType) {
        List<OwnerCustomerBaseInfo> list = customerService.searchCustomer(houseId, ownerProperty, name, ownerType);
        return new RestResult<>(list);
    }


}
