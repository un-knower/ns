/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.newsee.charge.entity.ChargeCustomerChargeDetail;
import com.newsee.charge.entity.ChargeCustomerChargeDetailLog;
import com.newsee.charge.service.IPaymentService;
import com.newsee.charge.service.impl.PaymentServiceImpl;
import com.newsee.charge.service.remote.ISystemRemoteService;
import com.newsee.charge.vo.PaymentVo;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/payment")
@Api(tags = {"com.newsee.charge.controller.PaymentController"}, description = "应收款列表页面操作 REST API，包含应收款页面的所有操作方法。")
public class PaymentController {
    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private ISystemRemoteService systemRemoteService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm() {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.PAYMENT_LIST);
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
        //检查表单中是否有表格项目，并且做相应处理
        List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
        resultData.put(FormConstants.FORM_FIELDS, formFields);
        return result;
    }

    @ApiOperation(value = "应收款列表获取")
    @RequestMapping(value = "/list-payment", method = RequestMethod.POST)
    public RestResult<PageInfo<ChargeCustomerChargeDetail>> listPage(@ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) throws Exception {
        BizException.isNull(searchVo, "查询条件");
        BizException.isNull2(searchVo.getHouseId(), "请选择左边房产树");
        RestResult<PageInfo<ChargeCustomerChargeDetail>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<ChargeCustomerChargeDetail> pageInfo = paymentService.listPage(searchVo);
        //合计行
        ChargeCustomerChargeDetail total = getTotal(searchVo);
        if (total!=null) {
            List<ChargeCustomerChargeDetail> list = pageInfo.getList();
            list.add(list.size(), total);
        }
        restResult = new RestResult<>(pageInfo);
        return restResult;
    }

    @ApiOperation(value = "应收款详情获取")
    @RequestMapping(value = "/detail-payment", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailPayment(@ApiParam(value = "应收款ID") @RequestParam(value = "id") Long id)
            throws IllegalArgumentException {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.PAYMENT_LIST);
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
        PaymentVo vo = paymentService.detail(id);
        resultData.put(FormConstants.FORM_MODEL_DATA, vo);
        result = new RestResult<>(resultData);
        return result;
    }

    @ApiOperation(value = "新增应收款")
    @RequestMapping(value = "/add-payment", method = RequestMethod.POST)
    public RestResult<Boolean> aaPayment(@ApiParam(value = "应收款详情") @RequestBody PaymentVo vo) {
        //编辑应收款详情信息
        boolean result = paymentService.add(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "编辑应收款")
    @RequestMapping(value = "/edit-payment", method = RequestMethod.POST)
    public RestResult<Boolean> editPayment(@ApiParam(value = "应收款详情") @RequestBody PaymentVo vo) {
        //编辑应收款详情信息
        boolean result = paymentService.edit(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "批量调整应收款")
    @RequestMapping(value = "/edit-payment-batch", method = RequestMethod.POST)
    public RestResult<Boolean> editPaymentBatch(@ApiParam(value = "应收款详情") @RequestBody List<PaymentVo> list) {
        BizException.isNull(list, "数据");
        boolean result = paymentService.editBatch(list);
        return new RestResult<>(result);
    }

    @ApiOperation(value = "删除应收款")
    @RequestMapping(value = "/delete-payment")
    public RestResult<Boolean> deletePayment(@ApiParam(value = "应收款ID") @RequestParam("id") Long id) {
        //删除应收款详情信息
        boolean result = paymentService.delete(id);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "批量删除应收款")
    @RequestMapping(value = "/delete-payment-batch")
    public RestResult<Boolean> deletePaymentBatch(@ApiParam(value = "应收款ID") @RequestBody List<Long> ids) {
        //删除应收款详情信息
        boolean result = paymentService.deleteBatch(ids);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "应收款审核/反审核")
    @RequestMapping(value = "/check-payment", method = RequestMethod.POST)
    public RestResult<String> checkChargeDetail(@ApiParam(value = "应收款管理id") @RequestBody Map<String, Object> map) {
        getCurrentPageId(map);
        BizException.isNull(map.get("ids"), "请选择一条数据审核");
        String check = (String) map.get("isCheck");
        BizException.isNull(check, "审核状态");
        if (!"审核通过".equals(check) && !"审核不通过".equals(check) && !"反审核".equals(check)) {
            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "审核状态不正确");
        }
        JSONArray jsonArray = JSONArray.parseArray(map.get("ids").toString());
        List ids = JSONArray.toJavaObject(jsonArray, List.class);
        int size = ids.size();
        if (size == 0) {
            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "请选择一条数据审核");
        }
        Integer num = paymentService.checkChargeDetail(map);
        RestResult<String> restResult = new RestResult<>(ResultCodeEnum.SUCCESS.CODE, "选择" + size + "条," + "成功" + num + "条," + "失败" + (size - num) + "条");
        return restResult;
    }

    @ApiOperation(value = "应收款减免")
    @RequestMapping(value = "/discount-payment", method = RequestMethod.POST)
    public RestResult<String> discountChargeDetail(@ApiParam(value = "减免金额") @RequestBody Map<String, Object> map) {

        BizException.isNull(map.get("chargeDetailList"), "数据");
        List<ChargeCustomerChargeDetail> chargeDetailList = JSONArray.parseArray(JSON.toJSONString(map.get("chargeDetailList")), ChargeCustomerChargeDetail.class);
        String type = (String) map.get("type");
        int size = chargeDetailList.size();
        Integer num = paymentService.discountChargeDetail(type, chargeDetailList);
        RestResult<String> restResult = new RestResult<>(ResultCodeEnum.SUCCESS.CODE, "选择" + size + "条," + "成功" + num + "条," + "失败" + (size - num) + "条");
        return restResult;
    }

    /**
     * 合计行
     * @param searchVo
     * @return
     * @throws Exception
     */
    public ChargeCustomerChargeDetail getTotal(SearchVo searchVo) throws Exception {
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getOrgId();
        String funcId = LoginDataHelper.getFuncId();
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
        ChargeCustomerChargeDetail total = paymentService.getTotal(searchVo, (Map<String, Object>) filedRedisObject);
        return total;
    }

    @ApiOperation(value = "应收款日志")
    @RequestMapping(value = "/list-payment-log", method = RequestMethod.POST)
    public RestResult<PageInfo<ChargeCustomerChargeDetailLog>> listPaymentLog(@ApiParam(value = "应收款管理id") @RequestBody Map<String, Object> map) {
        getCurrentPageId(map);
        BizException.isNull(map.get("ids"), "请选择一条数据查看");
        BizException.isNull(map.get("searchVo1"), "分页查询条件");
        PageInfo<ChargeCustomerChargeDetailLog> pageInfo = paymentService.listPaymentLog(map);
        return new RestResult<>(pageInfo);
    }

    /**
     * 根据查询条件获取当前页的所有id
     */
    private void getCurrentPageId(@ApiParam(value = "应收款管理id") @RequestBody Map<String, Object> map) {
        if (!isNull(map.get("searchVo"))) {
            JSONObject voObject = JSONObject.parseObject(JSON.toJSONString(map.get("searchVo")));
            SearchVo searchVo = JSONObject.toJavaObject(voObject, SearchVo.class);
            Long organizationId = LoginDataHelper.getOrgId();
            Long enterpriseId = LoginDataHelper.getEnterpriseId();
            searchVo.setEnterpriseId(enterpriseId);
            searchVo.setOrganizationId(organizationId);
            PageInfo<ChargeCustomerChargeDetail> pageInfo = paymentService.listPage(searchVo);
            if (!isNull(pageInfo)) {
                List<Long> ids = pageInfo.getList().stream().map(e -> e.getId()).collect(Collectors.toList());
                map.put("ids", ids);
            }
        }
    }
}
