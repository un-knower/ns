/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.controller;


import com.github.pagehelper.PageInfo;
import com.newsee.charge.entity.ChargeChargeStandard;
import com.newsee.charge.service.IStandardService;
import com.newsee.charge.service.remote.ISystemRemoteService;
import com.newsee.charge.vo.StandardVo;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestController
@RequestMapping("/standard")
@Api(tags = {"com.newsee.charge.controller.StandardController"}, description = "收费标准列表页面操作 REST API，包含收费标准页面的所有操作方法。")
public class StandardController {

    @Autowired
    private IStandardService standardService;

    @Autowired
    private ISystemRemoteService systemRemoteService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm() {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.STANDARD_LIST);
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

    @ApiOperation(value = "收费标准列表获取")
    @RequestMapping(value = "/list-standard", method = RequestMethod.POST)
    public RestResult<PageInfo<ChargeChargeStandard>> listPage(
            @ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) {
        BizException.isNull(searchVo, "查询条件");
        RestResult<PageInfo<ChargeChargeStandard>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<ChargeChargeStandard> pageInfo = standardService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);
        return restResult;
    }

    @ApiOperation(value = "收费标准详情获取")
    @RequestMapping(value = "/detail-standard", method = RequestMethod.GET)
    public RestResult<StandardVo> detailStandard(
            @ApiParam(value = "收费标准ID") @RequestParam(value = "id") Long id)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IntrospectionException {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.STANDARD_LIST);
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
		/*RestResult<Map<String, Object>> result = systemRemoteService.listField(commonVo);
		Map<String, Object> resultData = result.getResultData();
		List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
		resultData.put(FormConstants.FORM_FIELDS, formFields);*/
        // 获取收费标准详情信息
        StandardVo vo = standardService.detail(id);
        return new RestResult<>(vo);
    }

    @ApiOperation(value = "增加收费标准")
    @RequestMapping(value = "/add-standard", method = RequestMethod.POST)
    public RestResult<Object> aaStandard(@ApiParam(value = "收费标准详情") @RequestBody StandardVo vo) {
        // 编辑收费标准详情信息
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        vo.setEnterpriseId(enterpriseId);
        vo.setOrganizationId(organizationId);
        vo.setCreateUserId(LoginDataHelper.getUserId());
        vo.setCreateTime(new Date());
        vo.setCreateUserName(LoginDataHelper.getUserName());
        if (!standardService.checkName(vo)) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该编码已存在");
        }
        boolean result = standardService.add(vo);
        return new RestResult<Object>(result);
    }

    @ApiOperation(value = "编辑收费标准")
    @RequestMapping(value = "/edit-standard", method = RequestMethod.POST)
    public RestResult<Boolean> editStandard(@ApiParam(value = "收费标准详情") @RequestBody StandardVo vo) {
        // 编辑收费标准详情信息
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        vo.setEnterpriseId(enterpriseId);
        vo.setOrganizationId(organizationId);
        vo.setUpdateUserId(LoginDataHelper.getUserId());
        vo.setUpdateTime(new Date());
        vo.setUpdateUserName(LoginDataHelper.getUserName());
        //检验该名称是否已经存在
        if (!standardService.checkName(vo)) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该编码已存在");
        }
        boolean result = standardService.edit(vo);

        return new RestResult<Boolean>(result);

    }

    @ApiOperation(value = "删除收费标准")
    @RequestMapping(value = "/delete-standard", method = RequestMethod.GET)
    public RestResult<Boolean> deleteStandard(@ApiParam(value = "收费标准ID") @RequestParam("id") Long id) {
        // 删除收费标准详情信息
        boolean result = standardService.delete(id);
        if (!result) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该收费标准已经设置了房间收费标准，不允许删除！");
        }
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "批量删除收费标准")
    @RequestMapping(value = "/delete-standard-batch", method = RequestMethod.POST)
    public RestResult<Object> deleteStandardBatch(@ApiParam(value = "收费标准ID组") @RequestBody List<Long> ids) {
        // 删除收费标准详情信息
        Integer total = ids.size();
        int result = standardService.deleteBatch(ids);
        if (total != result) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "选择" + total + "条," + "成功" + result + "条," + "失败" + (total - result) + "条");
        }
        return new RestResult<Object>(true);
    }

    @ApiOperation(value = "删除所有")
    @RequestMapping(value = "/delete-standard-all", method = RequestMethod.POST)
    public RestResult<Object> deleteStandardALL(@ApiParam(value = "查询VO") @RequestBody SearchVo searchVo) {
        // 删除收费标准详情信息
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        List<ChargeChargeStandard> list = standardService.listPageALL(searchVo);
        List<Long> ids = new ArrayList<Long>();
        for (ChargeChargeStandard chargeChargeStandard : list) {
            ids.add(chargeChargeStandard.getId());
        }
        if (CollectionUtils.isEmpty(ids)) {
            return new RestResult<Object>(true);
        }
        int total = ids.size();
        int result = standardService.deleteBatch(ids);
        if (total != result) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "选择" + total + "条," + "成功" + result + "条," + "失败" + (total - result) + "条");
        }

        return new RestResult<Object>(result);
    }

    @ApiOperation(value = "收费标准下拉查询")
    @RequestMapping(value = "/list-standard-form", method = RequestMethod.GET)
    public RestResult<List<Map<String,Object>>> listStandardForm(@ApiParam(value = "houseId") @RequestParam(value = "houseId") Long houseId,
                                                         @ApiParam(value = "itemId") @RequestParam(value = "itemId") Long itemId) {

        SearchVo searchVo = new SearchVo();
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        searchVo.setHouseId(houseId);
        searchVo.setId(itemId);
        List<ChargeChargeStandard> list = standardService.listStandardForm(searchVo);
        List<Map<String,Object>> resultList = new ArrayList<>();
        for (ChargeChargeStandard standard : list) {
            Map<String,Object> map = new HashMap<>();
            map.put("label",standard.getStandardName());
            map.put("value",standard.getId());
            resultList.add(map);
        }
        return new RestResult<>(resultList);
    }

}
