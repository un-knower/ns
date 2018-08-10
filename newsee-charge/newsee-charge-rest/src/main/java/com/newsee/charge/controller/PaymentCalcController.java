/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.controller;

import static java.util.Objects.isNull;

import java.beans.IntrospectionException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.newsee.charge.service.remote.IOwnerRemoteService;
import com.newsee.charge.vo.PaymentCalcExcelVo;
import com.newsee.charge.vo.PaymentCalcVoImportExcel;
import com.newsee.common.utils.DateUtils;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.newsee.charge.enmus.HouseStandrdAduitStatus;
import com.newsee.charge.entity.ChargeCalcLog;
import com.newsee.charge.entity.ChargeChargeItem;
import com.newsee.charge.entity.ChargeCustomerChargeCalcTask;
import com.newsee.charge.service.IPaymentCalcService;
import com.newsee.charge.service.remote.ISystemRemoteService;
import com.newsee.charge.vo.PaymentCalcVo;
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
@RequestMapping("/paymentCalc")
@Api(tags = {
        "com.newsee.charge.controller.PaymentCalcController"}, description = "应收款计算列表页面操作 REST API，包含应收款计算页面的所有操作方法。")
public class PaymentCalcController {

    @Autowired
    private IPaymentCalcService paymentCalcService;

    @Autowired
    private ISystemRemoteService systemRemoteService;

    @Autowired
    private IOwnerRemoteService ownerRemoteService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm() {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.PAYMENT_CALC_LIST);
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

    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form-item", method = RequestMethod.GET)
    public RestResult<List<ChargeChargeItem>> initFormItem(@RequestParam("houseId") Long houseId) {
        List<ChargeChargeItem> items = paymentCalcService.getChargeItemList(houseId);
        if (Objects.isNull(items)) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该项目未设置收费标准！");
        }
        return new RestResult<>(items);
    }

    @ApiOperation(value = "任务列表获取")
    @RequestMapping(value = "/list-paymentCalc", method = RequestMethod.POST)
    public RestResult<PageInfo<ChargeCustomerChargeCalcTask>> listPage(
            @ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) {
        BizException.isNull(searchVo, "查询条件");
        RestResult<PageInfo<ChargeCustomerChargeCalcTask>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<ChargeCustomerChargeCalcTask> pageInfo = paymentCalcService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);
        return restResult;
    }

    @ApiOperation(value = "任务详情")
    @RequestMapping(value = "/detail-task", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailChargeCalcTask(@ApiParam(value = "任务id") @RequestParam(value = "id") Long id)
            throws IllegalArgumentException,
            IllegalAccessException,
            InvocationTargetException,
            IntrospectionException {
        BizException.isNull(id, "任务id");
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.PAYMENT_CALC_LIST);
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
        ChargeCustomerChargeCalcTask task = paymentCalcService.detailChargeCalcTask(id);
        resultData.put(FormConstants.FORM_MODEL_DATA, task);
        result = new RestResult<>(resultData);
        return result;

//        return new RestResult<>(task);
    }

    @ApiOperation(value = "自动计划管理列表获取")
    @RequestMapping(value = "/list-planCalc", method = RequestMethod.POST)
    public RestResult<PageInfo<ChargeCustomerChargeCalcTask>> listPlanPage(@ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) {
        BizException.isNull(searchVo, "查询条件");
        RestResult<PageInfo<ChargeCustomerChargeCalcTask>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<ChargeCustomerChargeCalcTask> pageInfo = paymentCalcService.listPlanPage(searchVo);
        restResult = new RestResult<>(pageInfo);
        return restResult;
    }

    @ApiOperation(value = "应收款计算详情获取")
    @RequestMapping(value = "/detail-calc-task", method = RequestMethod.GET)
    public RestResult<PaymentCalcVo> detailPaymentCalc(
            @ApiParam(value = "应收款计算ID") @RequestParam(value = "id") Long id)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IntrospectionException {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.PAYMENT_CALC_LIST);
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
        // 获取应收款计算详情信息
        PaymentCalcVo vo = paymentCalcService.detail(id);
        return new RestResult<>(vo);
    }

    @ApiOperation(value = "新增自动算费或者手动算费计划")
    @RequestMapping(value = "/add-calc-plan", method = RequestMethod.POST)
    public RestResult<Boolean> aaPaymentCalc(@ApiParam(value = "应收款计算详情") @RequestBody PaymentCalcVo vo) {
        // 编辑应收款计算详情信息
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Date now = new Date();
        vo.setEnterpriseId(enterpriseId);
        vo.setOrganizationId(organizationId);
        vo.setCreateUserId(userId);
        vo.setCreateUserName(userName);
        vo.setCreateTime(now);
        boolean result = paymentCalcService.add(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "算费")
    @RequestMapping(value = "/cost", method = RequestMethod.GET)
    public RestResult<Boolean> cost(@ApiParam(value = "应收款计算详情") @RequestParam("planId") Long planId) {
        // 编辑应收款计算详情信息
        paymentCalcService.calculateCost(planId);
        return new RestResult<Boolean>(true);
    }

    @ApiOperation(value = "审核,反审核")
    @RequestMapping(value = "/check-paymentCalc")
    public RestResult<Object> examinePaymentCalcALL(@ApiParam(value = "应收款计算ID") @RequestBody Map<String, Object> map) {
        // {"ids":"[12,34,34]","isCheck":0,"searchVo":SearchVo}
        if (!isNull(map.get("searchVo"))) {
            // 全部删除
            JSONObject voObject = JSONObject.parseObject(JSON.toJSONString(map.get("searchVo")));
            SearchVo searchVo = JSONObject.toJavaObject(voObject, SearchVo.class);
            Long organizationId = LoginDataHelper.getOrgId();
            Long enterpriseId = LoginDataHelper.getEnterpriseId();
            searchVo.setEnterpriseId(enterpriseId);
            searchVo.setOrganizationId(organizationId);
            List<ChargeCustomerChargeCalcTask> tasks = paymentCalcService.listPageALL(searchVo);
            if (!isNull(tasks)) {
                List<Long> ids = tasks.stream().map(e -> e.getId()).collect(Collectors.toList());
                map.put("ids", ids);
            }
        }

        if (isNull(map.get("ids"))) {
            BizException.isNull("请选中一条数据审核");
        }
        Comparator<Integer> comparator = (param1, param2) -> param1.compareTo(param2);
        JSONArray jsonArray = JSONArray.parseArray(map.get("ids").toString());
        List ids = JSONArray.toJavaObject(jsonArray, List.class);
        int size = ids.size();
        Integer result = paymentCalcService.checkPaymentCalc(map);
        paymentCalcService.checkPayment(map);
        if (comparator.compare(size, result) != 0) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR,
                    "选择" + size + "条," + "成功" + (result) + "条," + "失败" + (size - result) + "条");
        }
        return new RestResult<Object>(Boolean.TRUE);
    }

    @ApiOperation(value = "编辑应收款计算")
    @RequestMapping(value = "/edit-paymentCalc", method = RequestMethod.POST)
    public RestResult<Boolean> editPaymentCalc(@ApiParam(value = "应收款计算详情") @RequestBody PaymentCalcVo vo) {
        // 编辑应收款计算详情信息
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Date now = new Date();
        vo.setChargeCycleStartDate(now);
        vo.setChargeCycleEndDate(now);
        vo.setEnterpriseId(enterpriseId);
        vo.setOrganizationId(organizationId);
        vo.setUpdateUserName(userName);
        vo.setUpdateTime(now);
        vo.setUpdateUserId(userId);
        boolean result = paymentCalcService.edit(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "删除应收款计算")
    @RequestMapping(value = "/delete-paymentCalc")
    public RestResult<Boolean> deletePaymentCalc(@ApiParam(value = "应收款计算ID") @RequestParam("id") Long id) {
        // 删除应收款计算详情信息
        boolean result = paymentCalcService.delete(id);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "批量删除应收款计算")
    @RequestMapping(value = "/delete-paymentCalc-batch")
    public RestResult<Boolean> deletePaymentCalcBatch(@ApiParam(value = "应收款计算ID") @RequestBody SearchVo searchVo) {
        // 删除应收款计算详情信息
        boolean result = true;
        List<ChargeCustomerChargeCalcTask> tasks = paymentCalcService.listPageAll(searchVo);
        if (!isNull(tasks)) {
            List<Long> ids = tasks.stream().map(e -> e.getId()).collect(Collectors.toList());
            result = paymentCalcService.deleteBatch(ids);
        }
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "删除所有应收款计算")
    @RequestMapping(value = "/delete-paymentCalc-all")
    public RestResult<Boolean> deletePaymentCalcALL(@ApiParam(value = "应收款计算ID") @RequestBody List<Long> ids) {
        // 删除应收款计算详情信息
        boolean result = paymentCalcService.deleteBatch(ids);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "应收款计算任务清除")
    @RequestMapping(value = "/delete-taskDetails")
    public RestResult<Boolean> deleteTaskDetails(@ApiParam(value = "应收款计算ID") @RequestParam("id") Long id) {
        boolean result = paymentCalcService.deleteTaskDetails(id);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "计划启用，停用")
    @RequestMapping(value = "/plan-paymentCalc")
    public RestResult<Object> planManager(@ApiParam(value = "") @RequestBody Map<String, Object> map) {
        // {"ids":"[12,34,34]","isWork":"启用","searchVo":SearchVo}
        if (!isNull(map.get("searchVo"))) {
            // 全部删除
            JSONObject voObject = JSONObject.parseObject(JSON.toJSONString(map.get("searchVo")));
            SearchVo searchVo = JSONObject.toJavaObject(voObject, SearchVo.class);
            Long organizationId = LoginDataHelper.getOrgId();
            Long enterpriseId = LoginDataHelper.getEnterpriseId();
            searchVo.setEnterpriseId(enterpriseId);
            searchVo.setOrganizationId(organizationId);
            List<ChargeCustomerChargeCalcTask> tasks = paymentCalcService.listPageALL(searchVo);
            if (!isNull(tasks)) {
                List<Long> ids = tasks.stream().map(e -> e.getId()).collect(Collectors.toList());
                map.put("ids", ids);
            }
        }

        if (isNull(map.get("ids"))) {
            BizException.isNull("请选中一条数据启用/禁用");
        }
        Comparator<Integer> comparator = (param1, param2) -> param1.compareTo(param2);
        JSONArray jsonArray = JSONArray.parseArray(map.get("ids").toString());
        List ids = JSONArray.toJavaObject(jsonArray, List.class);
        int size = ids.size();
        Integer result = paymentCalcService.planManager(map);
        if (comparator.compare(size, result) != 0) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR,
                    "选择" + size + "条," + "成功" + (result) + "条," + "失败" + (size - result) + "条");
        }
        return new RestResult<Object>(Boolean.TRUE);
    }

    @ApiOperation(value = "应收款计算批量导入")
    @RequestMapping(value = "/import-paymentCalc", method = RequestMethod.POST)
    public RestResult<String> importPaymentCalc(MultipartFile file, String paymentInfo) {
        BizException.isNull(paymentInfo, "任务信息");
        PaymentCalcVoImportExcel paymentCalcVo = JSONObject.parseObject(paymentInfo, PaymentCalcVoImportExcel.class);
        BizException.isNull(paymentCalcVo.getPrecinctId(), "项目id");
        Date now = new Date();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getOrgId();
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        paymentCalcVo.setEnterpriseId(enterpriseId);
        paymentCalcVo.setOrganizationId(organizationId);
        paymentCalcVo.setCreateUserId(userId);
        paymentCalcVo.setCreateUserName(userName);
        paymentCalcVo.setCreateTime(now);
        paymentCalcVo.setUpdateTime(now);
        paymentCalcVo.setUpdateUserId(userId);
        paymentCalcVo.setUpdateUserName(userName);
//        if (isNull(file)) {
//            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "请上传模板");
//        }
        String result = paymentCalcService.importPaymentCalc(file, paymentCalcVo);
        return new RestResult<>(result);
    }

    @ApiOperation(value = "应收款计算导入模板下载")
    @RequestMapping(value = "/download-excel", method = RequestMethod.GET)
    public RestResult<Boolean> downloadExcelTemplate(@ApiParam(value = "项目id") @RequestParam(value = "precinctId") Long precinctId,
                                                     @ApiParam(value = "科目id") @RequestParam(value = "itemId", required = false) Long itemId,
                                                     @ApiParam(value = "收费标准id") @RequestParam(value = "standardId", required = false) Long standardId,
                                                     @ApiParam(value = "应收日期") @RequestParam(value = "shouldChargeDate", required = false) String shouldChargeDate,
                                                     @ApiParam(value = "计费开始时间") @RequestParam(value = "startDate", required = false) String startDate,
                                                     @ApiParam(value = "计费结束时间") @RequestParam(value = "endDate", required = false) String endDate,
                                                     HttpServletResponse response) throws UnsupportedEncodingException {
        response.reset();
        String precinctName = "";
        if (precinctId != null) {
            OwnerHouseBaseInfo houseBaseInfo = ownerRemoteService.getHouseInfo(precinctId).getResultData();
            precinctName = houseBaseInfo.getHouseName();
        }
        String dateStr = DateUtils.dateToString(new Date(), DateUtils.YYYYMMDDHHMMSS_NOSYBOL);
        String fileName = "应收款批量导入-" + precinctName + "-" + dateStr + ".xls";
        response.setHeader("downloadFileName", URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Access-Control-Expose-Headers", "downloadFileName, Content-Disposition");
        response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        Date shouldChargeDate1 = shouldChargeDate == null ? null : DateUtils.strToDate(shouldChargeDate, DateUtils.YYYYMMDD_CROSS_HHMMSS);
        Date startDate1 = startDate == null ? null : DateUtils.strToDate(startDate, DateUtils.YYYYMMDD_CROSS_HHMMSS);
        Date endDate1 = endDate == null ? null : DateUtils.strToDate(endDate, DateUtils.YYYYMMDD_CROSS_HHMMSS);
        paymentCalcService.downloadExcelTemplate(precinctId, itemId, standardId, shouldChargeDate1, startDate1, endDate1, response);
        return new RestResult<>();
    }

    @ApiOperation(value = "日志查询")
    @RequestMapping(value = "/logs", method = RequestMethod.POST)
    public RestResult<PageInfo<ChargeCalcLog>> listPageChargeCalcLog(@ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) {
        BizException.isNull(searchVo.getId(), "任务id");
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<ChargeCalcLog> pageInfo = paymentCalcService.listPageChargeCalcLog(searchVo);
        return new RestResult<>(pageInfo);
    }

}
