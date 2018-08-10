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
import com.newsee.charge.entity.ChargeChargePaymentMethod;
import com.newsee.charge.service.IPaymethodService;
import com.newsee.charge.service.remote.ISystemRemoteService;
import com.newsee.charge.vo.PaymethodVo;
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
@RequestMapping("/paymethod")
@Api(tags = {"com.newsee.charge.controller.PaymethodController"}, description = "支付方式列表页面操作 REST API，包含支付方式页面的所有操作方法。")
public class PaymethodController {

    @Autowired
    private IPaymethodService paymethodService;

    @Autowired
    private ISystemRemoteService systemRemoteService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm() {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.PAY_METHOD_LIST);
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

    @ApiOperation(value = "支付方式列表获取")
    @RequestMapping(value = "/list-paymethod", method = RequestMethod.POST)
    public RestResult<PageInfo<ChargeChargePaymentMethod>> listPage(@ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) {
        BizException.isNull(searchVo, "查询条件");
        Long organizationId = searchVo.getOrganizationId();
        BizException.isNull2(organizationId,"请选择左侧组织树");
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<ChargeChargePaymentMethod> pageInfo = paymethodService.listPage(searchVo);
        return new RestResult<>(pageInfo);
    }

    @ApiOperation(value = "支付方式详情获取")
    @RequestMapping(value = "/detail-paymethod", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailPaymethod(@ApiParam(value = "支付方式ID") @RequestParam(value = "id") Long id)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IntrospectionException {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.PAY_METHOD_LIST);
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
        // 获取支付方式详情信息
        PaymethodVo vo = paymethodService.detail(id);
        resultData.put(FormConstants.FORM_MODEL_DATA, vo);
        result = new RestResult<>(resultData);
        return result;
    }

    @ApiOperation(value = "新增支付方式")
    @RequestMapping(value = "/add-paymethod", method = RequestMethod.POST)
    public RestResult<Boolean> addPaymethod(@ApiParam(value = "支付方式新增") @RequestBody PaymethodVo vo) {
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String userName = LoginDataHelper.getUserName();
        Long userId = LoginDataHelper.getUserId();
        // 检查支付方式code是否存在
        boolean isExists = paymethodService.checkCodeIsExists(enterpriseId, null, vo.getMethodCode());
        if (isExists) {
            return new RestResult<Boolean>(ResultCodeEnum.DATA_NOT_EXIST.CODE, "该支付方式编码已存在。");
        }
        Date now = new Date();
        vo.setEnterpriseId(enterpriseId);
        vo.setCreateUserId(userId);
        vo.setCreateUserName(userName);
        vo.setCreateTime(now);
        vo.setUpdateUserId(userId);
        vo.setUpdateUserName(userName);
        vo.setUpdateTime(now);
        // 编辑支付方式详情信息
        boolean result = paymethodService.add(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "编辑支付方式")
    @RequestMapping(value = "/edit-paymethod", method = RequestMethod.POST)
    public RestResult<Boolean> editPaymethod(@ApiParam(value = "支付方式详情") @RequestBody PaymethodVo vo) {
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String userName = LoginDataHelper.getUserName();
        Long userId = LoginDataHelper.getUserId();
        // 检查支付方式code是否存在
        boolean isExists = paymethodService.checkCodeIsExists(enterpriseId, vo.getId(), vo.getMethodCode());
        if (isExists) {
            return new RestResult<Boolean>(ResultCodeEnum.DATA_NOT_EXIST.CODE, "该支付方式编码已存在。");
        }
        Date now = new Date();
        vo.setUpdateUserId(userId);
        vo.setUpdateUserName(userName);
        vo.setUpdateTime(now);
        // 编辑支付方式详情信息
        boolean result = paymethodService.edit(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "删除支付方式")
    @RequestMapping(value = "/delete-paymethod", method = RequestMethod.POST)
    public RestResult<Boolean> deletePaymethod(@ApiParam(value = "支付方式ID") @RequestParam("id") Long id) {
        // 删除支付方式详情信息
        boolean result = paymethodService.delete(id);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "批量删除支付方式")
    @RequestMapping(value = "/delete-paymethod-batch", method = RequestMethod.POST)
    public RestResult<Boolean> deletePaymethodBatch(@ApiParam(value = "支付方式ID") @RequestBody List<Long> ids) {
        // 删除支付方式详情信息
        boolean result = paymethodService.deleteBatch(ids);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "删除全部支付方式")
    @RequestMapping(value = "/delete-paymethod-all", method = RequestMethod.POST)
    public RestResult<Boolean> deletePaymethodBatch( @ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) {
        // 删除支付方式详情信息
        BizException.isNull(searchVo, "查询条件");
        Long organizationId = searchVo.getOrganizationId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        boolean result = paymethodService.deleteAll(searchVo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "启用/停用")
    @RequestMapping(value = "/enable-paymethod", method = RequestMethod.POST)
    public RestResult<Boolean> enablePaymethod(@ApiParam(value = "支付方式ID") @RequestParam("id") Long id) {
        BizException.isNull(id, "id");
        boolean result = paymethodService.enablePaymethod(id);
        return new RestResult<>(result);
    }
}
