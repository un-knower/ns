/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.newsee.charge.vo.AppStandardVo;
import com.newsee.common.rest.APPRestResult;
import com.newsee.common.rest.ResultCodeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.newsee.charge.entity.ChargeHouseChargeStandard;
import com.newsee.charge.entity.ChargeHouseChargeStandardCustomer;
import com.newsee.charge.service.IHouseStandardService;
import com.newsee.charge.vo.ChargeStaticData;
import com.newsee.charge.vo.HouseStandardAddVo;
import com.newsee.charge.vo.HouseStandardVo;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.vo.SearchVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/houseStandard")
@Api(tags = {"com.newsee.charge.controller.HouseStandardController"}, description = "房产收费标准列表页面操作 REST API，包含房产收费标准页面的所有操作方法。")
public class HouseStandardController {

    @Autowired
    private IHouseStandardService houseStandardService;

    @ApiOperation(value = "房产收费标准列表获取")
    @RequestMapping(value = "/list-houseStandard", method = RequestMethod.POST)
    public RestResult<PageInfo<ChargeHouseChargeStandard>> listPage(@ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) {
        BizException.isNull(searchVo, "查询条件");
        RestResult<PageInfo<ChargeHouseChargeStandard>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<ChargeHouseChargeStandard> pageInfo = houseStandardService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);
        return restResult;
    }

    @ApiOperation(value = "初始化新增房产收费标准数据")
    @RequestMapping(value = "/init-house-standard-form", method = RequestMethod.POST)
    public RestResult<List<HouseStandardAddVo>> initHouseStandardForm(@ApiParam(value = "选中的房产节点信息") @RequestBody HouseStandardAddVo vo) {
        //参数检查
        if (Objects.isNull(vo) ||
                Objects.isNull(vo.getHouseId()) ||
                Objects.isNull(vo.getOrganizationId())) {
            BizException.isNull2(null, "参数错误");
        }
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        vo.setEnterpriseId(enterpriseId);
        List<HouseStandardAddVo> resultVo = houseStandardService.initHouseStandardData(vo);
        return new RestResult<List<HouseStandardAddVo>>(resultVo);
    }

    @ApiOperation(value = "初始化新增房产收费标准数据")
    @RequestMapping(value = "/check-house-standard-form", method = RequestMethod.POST)
    public RestResult<Integer> checkHouseStandardForm(@ApiParam(value = "选中的房产节点信息") @RequestBody HouseStandardAddVo vo) {
        //参数检查
        if (Objects.isNull(vo) ||
                Objects.isNull(vo.getHouseId()) ||
                Objects.isNull(vo.getOrganizationId())) {
            BizException.isNull2(null, "参数错误");
        }
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        vo.setEnterpriseId(enterpriseId);
        Integer resultVo = houseStandardService.checkHouseStandardData(vo);
        return new RestResult<Integer>(resultVo);
    }

    @ApiOperation(value = "新增房产收费标准")
    @RequestMapping(value = "/add-house-standard", method = RequestMethod.POST)
    public RestResult<Boolean> aaHouseStandard(@RequestParam(value = "developer", required = false, defaultValue = "") String developer, @RequestParam("isCover") Boolean isCover, @ApiParam(value = "房产收费标准详情") @RequestBody List<HouseStandardAddVo> vo) {
        //编辑房产收费标准详情信息
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getOrgId();
        Date now = new Date();
        Map<String, Object> param = Maps.newHashMap();
        param.put(ChargeStaticData.IS_COVER_TYPE, isCover);
        param.put(ChargeStaticData.DEVELOPER_TYPE, developer);
        vo.forEach(Item -> {
            Item.setCreateUserId(userId);
            Item.setCreateUserName(userName);
            Item.setCreateTime(now);
            Item.setUpdateUserId(userId);
            Item.setUpdateUserName(userName);
            Item.setUpdateTime(now);
            Item.setEnterpriseId(enterpriseId);
            Item.setOrganizationId(organizationId);
        });

        boolean result = houseStandardService.add(vo, param);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "编辑房产收费标准客户")
    @RequestMapping(value = "/edit-house-standard-customer", method = RequestMethod.POST)
    public RestResult<Boolean> editHouseStandardCustomer(@ApiParam(value = "房产收费标准详情") @RequestBody List<ChargeHouseChargeStandardCustomer> vos) {
        Date now = new Date();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long orgId = LoginDataHelper.getOrgId();
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        for (ChargeHouseChargeStandardCustomer vo : vos) {
            vo.setEnterpriseId(enterpriseId);
            vo.setOrganizationId(orgId);
            vo.setCreateUserName(userName);
            vo.setCreateUserId(userId);
            vo.setCreateTime(now);
            vo.setUpdateUserName(userName);
            vo.setUpdateUserId(userId);
            vo.setUpdateTime(now);
        }
        //编辑房产收费标准详情信息
        boolean result = houseStandardService.edit(vos);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "编辑房产收费标准")
    @RequestMapping(value = "/edit-house-standard", method = RequestMethod.POST)
    public RestResult<Boolean> editHouseStandard(@ApiParam(value = "房产收费标准详情") @RequestBody List<ChargeHouseChargeStandard> vos) {
        //编辑房产收费标准详情信息
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        for (ChargeHouseChargeStandard chargeHouseChargeStandard : vos) {
            chargeHouseChargeStandard.setEnterpriseId(enterpriseId);
        }
        boolean result = houseStandardService.editHouse(vos);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "房产收费标准客户详情")
    @RequestMapping(value = "/detail-house-standard", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> detailHouseStandard(@ApiParam(value = "收费标准详情") @RequestParam("id") Long id, @RequestParam("houseId") Long houseId) {
        //编辑房产收费标准详情信息
        Map<String, Object> map = Maps.newHashMap();
        map.put("houseChargeId", id);
        map.put("houseId", houseId);
        Map<String, Object> result = houseStandardService.detail(map);
        return new RestResult<Map<String, Object>>(result);
    }

    @ApiOperation(value = "删除房产收费标准")
    @RequestMapping(value = "/delete-house-standard", method = RequestMethod.GET)
    public RestResult<Boolean> deleteHouseStandard(@ApiParam(value = "房产收费标准ID") @RequestParam("id") Long id) {
        BizException.isNull(id, "参数错误");
        //删除房产收费标准详情信息
        boolean result = houseStandardService.delete(id);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "批量删除房产收费标准")
    @RequestMapping(value = "/delete-house-standard-batch", method = RequestMethod.POST)
    public RestResult<String> deleteHouseStandardBatch(@ApiParam(value = "房产收费标准ID") @RequestBody List<Long> ids) {
        //删除房产收费标准详情信息
        String result = houseStandardService.deleteBatch(ids);
        return new RestResult<>(ResultCodeEnum.SUCCESS.CODE, result);
    }

    @ApiOperation(value = "批量删除房产收费标准")
    @RequestMapping(value = "/delete-house-standard-all", method = RequestMethod.POST)
    public RestResult<String> deleteHouseStandardALL(@ApiParam(value = "房产收费标准ID") @RequestBody SearchVo searchVo) {
        //删除房产收费标准详情信息
        BizException.isNull(searchVo, "查询条件");
        RestResult<PageInfo<ChargeHouseChargeStandard>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        List<ChargeHouseChargeStandard> pageInfo = houseStandardService.listPageALL(searchVo);
        if (!CollectionUtils.isEmpty(pageInfo)) {
            List<Long> ids = pageInfo.stream().map(item -> item.getId()).collect(Collectors.toList());
            String result = houseStandardService.deleteBatch(ids);
            return new RestResult<>(ResultCodeEnum.SUCCESS.CODE, result);
        }
        return new RestResult<>(ResultCodeEnum.SUCCESS.CODE, "选择0条");
    }

    @ApiOperation(value = "审核房产收费标准")
    @RequestMapping(value = "/audit-house-standard", method = RequestMethod.POST)
    public RestResult<Boolean> auditHouseStandard(@ApiParam(value = "房产收费标准ID") @RequestBody HouseStandardVo vo) {
        BizException.isNull(vo, "参数错误");
        //删除房产收费标准详情信息
        Date now = new Date();
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        vo.setUpdateUserId(userId);
        vo.setUpdateUserName(userName);
        vo.setUpdateTime(now);
        boolean result = houseStandardService.auditHouseStandard(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "审核全部房产收费标准")
    @RequestMapping(value = "/audit-all-house-standard", method = RequestMethod.POST)
    public RestResult<Boolean> auditAllHouseStandard(@ApiParam(value = "房产收费标准ID") @RequestBody SearchVo vo) {
        BizException.isNull(vo, "查询条件");
        BizException.isNull(vo.getOtherConditions(), "查询条件");
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        vo.setEnterpriseId(enterpriseId);
        Map<String, Object> map = vo.getOtherConditions();
        map.put("updateUserId", LoginDataHelper.getUserId());
        map.put("updateUserName", LoginDataHelper.getUserName());
        map.put("updateTime", new Date());
        boolean result = houseStandardService.auditAllHouseStandrd(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "反审核房产收费标准")
    @RequestMapping(value = "/audit-back-house-standard", method = RequestMethod.POST)
    public RestResult<Boolean> auditBackHouseStandard(@ApiParam(value = "房产收费标准ID") @RequestBody HouseStandardVo vo) {
        BizException.isNull(vo, "参数错误");
        //删除房产收费标准详情信息
        Date now = new Date();
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        vo.setUpdateUserId(userId);
        vo.setUpdateUserName(userName);
        vo.setUpdateTime(now);
        boolean result = houseStandardService.auditBackHouseStandard(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "反审核全部房产收费标准")
    @RequestMapping(value = "/audit-back-all-house-standard", method = RequestMethod.POST)
    public RestResult<Boolean> auditBackAllHouseStandard(@ApiParam(value = "房产收费标准ID") @RequestBody SearchVo vo) {
        BizException.isNull(vo, "查询条件");
        BizException.isNull(vo.getOtherConditions(), "查询条件");
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        vo.setEnterpriseId(enterpriseId);
        Map<String, Object> map = vo.getOtherConditions();
        map.put("updateUserId", LoginDataHelper.getUserId());
        map.put("updateUserName", LoginDataHelper.getUserName());
        map.put("updateTime", new Date());
        boolean result = houseStandardService.auditBackAllHouseStandrd(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "对外APP接口，查询收费标准")
    @RequestMapping(value = "/app-detail-standard", method = RequestMethod.GET)
    public APPRestResult<List<AppStandardVo>> detailAppStandard(@ApiParam(value = "房产名称") @RequestParam(value = "HouseName", required = false) String houseName,
                                                                @ApiParam(value = "房产Id") @RequestParam(value = "HouseID", required = false) Long houseId,
                                                                @ApiParam(value = "查询类型") @RequestParam(value = "TypeID", required = false) Integer typeId) {
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long orgId = LoginDataHelper.getOrgId();
        List<AppStandardVo> appStandardVos = houseStandardService.listAppStandard(enterpriseId, orgId, houseId, houseName, typeId);
        return new APPRestResult<>(appStandardVos);
    }
}
