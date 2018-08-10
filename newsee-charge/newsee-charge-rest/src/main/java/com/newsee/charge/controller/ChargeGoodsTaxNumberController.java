package com.newsee.charge.controller;

import com.newsee.charge.entity.ChargeGoodsTaxNumber;
import com.newsee.charge.service.IChargeGoodsTaxNumberService;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 税号设置
 *
 * @author: mu.jie
 * @date: 2018年5月2日 上午9:00:36
 */
@RestController
@RequestMapping("/goodsTaxNumber")
@Api(tags = {"com.newsee.charge.controller.ChargeGoodsTaxNumberController"}, description = "税号设置页面操作 REST API，包含税号页面的所有操作方法。")
public class ChargeGoodsTaxNumberController {
    @Autowired
    private IChargeGoodsTaxNumberService chargeGoodsTaxNumberService;

    @ApiOperation(value = "税号设置详情")
    @RequestMapping(value = "/detail-goodsTaxNumber", method = RequestMethod.GET)
    public RestResult<ChargeGoodsTaxNumber> detailGoodsTaxNumber(@ApiParam(value = "组织ID") @RequestParam(value = "organizationId") Long organizationId) {
        BizException.isNull(organizationId, "组织id");
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        ChargeGoodsTaxNumber chargeGoodsTaxNumber = chargeGoodsTaxNumberService.detail(organizationId, enterpriseId);
        if (chargeGoodsTaxNumber == null) {
            chargeGoodsTaxNumber = new ChargeGoodsTaxNumber();
        }
        return new RestResult<>(chargeGoodsTaxNumber);
    }

    @ApiOperation(value = "税号设置新增,编辑")
    @RequestMapping(value = "/add-goodsTaxNumber", method = RequestMethod.POST)
    public RestResult<Boolean> addGoodsTaxNumber(@ApiParam(value = "新增/编辑税号") @RequestBody ChargeGoodsTaxNumber chargeGoodsTaxNumber) {
        Date now = new Date();
        BizException.isNull(chargeGoodsTaxNumber.getOrganizationId(), "组织id");
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        chargeGoodsTaxNumber.setEnterpriseId(enterpriseId);
        if (chargeGoodsTaxNumber.getId() == null) {
            chargeGoodsTaxNumber.setCreateTime(now);
            chargeGoodsTaxNumber.setCreateUserId(userId);
            chargeGoodsTaxNumber.setCreateUserName(userName);
        }
        chargeGoodsTaxNumber.setUpdateTime(now);
        chargeGoodsTaxNumber.setUpdateUserId(userId);
        chargeGoodsTaxNumber.setUpdateUserName(userName);
        chargeGoodsTaxNumber.setSysTime(now);


        Boolean flag = chargeGoodsTaxNumberService.addGoodsTaxNumber(chargeGoodsTaxNumber);
        return new RestResult<>(flag);
    }

    @ApiOperation(value = "删除税号设置")
    @RequestMapping(value = "/delete-goodsTaxNumber", method = RequestMethod.POST)
    public RestResult<Boolean> deleteGoodsTaxNumber(@ApiParam(value = "税号Id") @RequestParam Long id) {
        BizException.isNull(id, "id");
        Boolean flag = chargeGoodsTaxNumberService.deleteGoodsTaxNumber(id);
        return new RestResult<>(flag);
    }

}
