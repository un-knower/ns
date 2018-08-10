/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.controller;

import com.github.pagehelper.PageInfo;
import com.newsee.charge.entity.ChargeGoodsTaxRate;
import com.newsee.charge.service.IGoodsTaxRateService;
import com.newsee.charge.service.remote.IOwnerRemoteService;
import com.newsee.charge.service.remote.ISystemRemoteService;
import com.newsee.charge.vo.GoodsTaxRateVo;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import com.newsee.redis.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Objects.isNull;

@RestController
@RequestMapping("/goodsTaxRate")
@Api(tags = {"com.newsee.charge.controller.GoodsTaxRateController"}, description = "税率列表页面操作 REST API，包含税率页面的所有操作方法。")
public class GoodsTaxRateController {

    @Autowired
    private IGoodsTaxRateService goodsTaxRateService;
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
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.GOODS_TAX_RATE_LIST);
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

    @ApiOperation(value = "税率列表获取")
    @RequestMapping(value = "/list-goodsTaxRate", method = RequestMethod.POST)
    public RestResult<PageInfo<ChargeGoodsTaxRate>> listPage(@ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) {
        BizException.isNull(searchVo, "查询条件");
        //获取页面房产树点击的houseId，根据houseid获取该项目所属的organizaitonid
        Long companyLevelOrgsnzationId = LoginDataHelper.getCompanyLevelOrgId();
        Long precinctId = Long.valueOf(searchVo.getOtherConditions().get("houseId").toString());

        if (isNull(precinctId)) {
            BizException.isNull2(precinctId, "请选择左侧房产树中的项目。");
        }
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        //获取选中的树节点中的公司级别的organizationid
        searchVo.setOrganizationId(companyLevelOrgsnzationId);
        searchVo.setEnterpriseId(enterpriseId);
        //初始化税率
        List<OwnerHouseBaseInfo> houseBaseInfoList = ownerRemoteService.listAllChildNode(precinctId, newArrayList("2")).getResultData();
        //查询税率列表
        PageInfo<ChargeGoodsTaxRate> pageInfo = goodsTaxRateService.listPage(houseBaseInfoList, searchVo);
        return new RestResult<>(pageInfo);
    }

    @ApiOperation(value = "税率详情获取")
    @RequestMapping(value = "/detail-goodsTaxRate", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailGoodsTaxRate(@ApiParam(value = "税率ID") @RequestParam(value = "id") Long id)
            throws IllegalArgumentException,
            IllegalAccessException,
            InvocationTargetException,
            IntrospectionException {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.ITEM_LIST);
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
        //获取税率详情信息
        GoodsTaxRateVo vo = goodsTaxRateService.detail(id);
        resultData.put(FormConstants.FORM_MODEL_DATA, vo);
        return new RestResult<>(resultData);
    }

    @ApiOperation(value = "新增税率")
    @RequestMapping(value = "/add-goodsTaxRate", method = RequestMethod.POST)
    public RestResult<Boolean> aaGoodsTaxRate(@ApiParam(value = "税率详情") @RequestBody GoodsTaxRateVo vo) {
        boolean result = goodsTaxRateService.add(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "编辑税率")
    @RequestMapping(value = "/edit-goodsTaxRate", method = RequestMethod.POST)
    public RestResult<Boolean> editGoodsTaxRate(@ApiParam(value = "税率编辑") @RequestBody List<GoodsTaxRateVo> vos) {
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Date now = new Date();
        for (GoodsTaxRateVo vo : vos) {
            vo.setUpdateTime(now);
            vo.setUpdateUserId(userId);
            vo.setUpdateUserName(userName);
        }
        //编辑税率详情信息
        boolean result = goodsTaxRateService.edit(vos);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "删除税率")
    @RequestMapping(value = "/delete-goodsTaxRate", method = RequestMethod.POST)
    public RestResult<Boolean> deleteGoodsTaxRate(@ApiParam(value = "收费科目ID") @RequestParam(value = "chargeItemId", required = false) Long chargeItemId,
                                                  @ApiParam(value = "项目ID") @RequestParam(value = "houseId", required = false) Long houseId) {
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        if (isNull(chargeItemId) && isNull(houseId)) {
            throw new BizException(ResultCodeEnum.PARAMS_ERROR.CODE, "收费科目id和房产id不能都为空");
        }
        boolean result = goodsTaxRateService.delete(enterpriseId, chargeItemId, houseId);
        return new RestResult<>(result);
    }

    @ApiOperation(value = "批量删除税率")
    @RequestMapping(value = "/delete-goodsTaxRate-batch")
    public RestResult<Boolean> deleteGoodsTaxRateBatch(@ApiParam(value = "税率ID") @RequestBody List<Long> ids) {
        //删除税率详情信息
        boolean result = goodsTaxRateService.deleteBatch(ids);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "修改税率项目名称")
    @RequestMapping(value = "/update-precinctName", method = RequestMethod.POST)
    public RestResult<Boolean> updatePrecinctName(@ApiParam(value = "项目ID") @RequestParam(value = "houseId") Long houseId,
                                                  @ApiParam(value = "项目名称") @RequestParam(value = "houseName") String houseName) {
        BizException.isNull(houseId, "项目Id");
        BizException.isNull(houseName, "项目名称");
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        boolean result = goodsTaxRateService.updatePrecinctName(enterpriseId, houseId, houseName);
        return new RestResult<>(result);
    }

}
