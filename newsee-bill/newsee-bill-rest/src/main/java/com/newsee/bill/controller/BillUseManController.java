/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.controller;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.newsee.bill.vo.BillUseManCheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.newsee.bill.entity.NsbillBillUsed;
import com.newsee.bill.service.IBillUseManService;
import com.newsee.bill.service.remote.ISystemRemoteService;
import com.newsee.bill.vo.BillUseManSearchVo;
import com.newsee.bill.vo.BillUseManThird;
import com.newsee.bill.vo.BillUseManVo;
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
import com.newsee.owner.vo.CustomerVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//import com.newsee.charge.entity.ChargeCustomerChargeCalcTask;

@RestController
@RequestMapping("/billUseMan")
@Api(tags = {"com.newsee.bill.controller.BillUseManController"}, description = "票据使用管理列表页面操作 REST API，包含票据使用管理页面的所有操作方法。")
public class BillUseManController {

    @Autowired
    private IBillUseManService billUseManService;

    @Autowired
    private ISystemRemoteService systemRemoteService;

    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm() {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName("billUseManager");
        String interpreter = LoginDataHelper.getFieldInterpreter();
        String formOperateType = LoginDataHelper.getFormOperateType();
        LoginCommonDataVo commonVo = new LoginCommonDataVo();
        commonVo.setOrganizationId(organizationId);
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

    @ApiOperation(value = "票据使用管理列表获取")
    @RequestMapping(value = "/list-billUseMan", method = RequestMethod.POST)
    public RestResult<PageInfo<NsbillBillUsed>> listPage(@ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) {
        BizException.isNull(searchVo, "查询条件");
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<NsbillBillUsed> pageInfo = billUseManService.listPage(searchVo);
        return new RestResult<>(pageInfo);
    }

    @ApiOperation(value = "票据使用管理详情获取")
    @RequestMapping(value = "/detail-billUseMan", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailBillUseMan(@ApiParam(value = "票据使用管理ID") @RequestParam(value = "id") Long id) {
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
        //获取票据使用管理详情信息
        NsbillBillUsed vo = billUseManService.detail(id);
        resultData.put(FormConstants.FORM_MODEL_DATA, vo);
        result = new RestResult<>(resultData);
        return result;
    }

    @ApiOperation(value = "编辑票据使用管理")
    @RequestMapping(value = "/add-billUseMan", method = RequestMethod.POST)
    public RestResult<Boolean> aaBillUseMan(@ApiParam(value = "票据使用管理详情") @RequestBody BillUseManVo vo) {
        //编辑票据使用管理详情信息
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long userId = LoginDataHelper.getUserId();
		/*vo.setEnterpriseId(enterpriseId);
		vo.setOrganizationId(organizationId);
		vo.setHandlerId(userId);*/
        boolean result = billUseManService.add(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "编辑票据使用管理")
    @RequestMapping(value = "/edit-billUseMan", method = RequestMethod.POST)
    public RestResult<Boolean> editBillUseMan(@ApiParam(value = "票据使用管理详情") @RequestBody BillUseManVo vo) {
        //编辑票据使用管理详情信息
        Long userId = LoginDataHelper.getUserId();
//		vo.setHandlerId(userId);
        boolean result = billUseManService.edit(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "删除票据使用管理")
    @RequestMapping(value = "/delete-billUseMan")
    public RestResult<Boolean> deleteBillUseMan(@ApiParam(value = "票据使用管理ID") @RequestParam("id") Long id) {
        //删除票据使用管理详情信息
        boolean result = billUseManService.delete(id);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "批量删除票据使用管理")
    @RequestMapping(value = "/delete-billUseMan-batch")
    public RestResult<Boolean> deleteBillUseManBatch(@ApiParam(value = "票据使用管理ID") @RequestBody List<Long> ids) {
        //删除票据使用管理详情信息
        boolean result = billUseManService.deleteBatch(ids);
        return new RestResult<Boolean>(result);
    }
       
    /*@ApiOperation(value = "票据启用/废弃")
    @RequestMapping(value = "/enable-billUseMan")
    public RestResult<Object> enableBillUseMan(@ApiParam(value = "票据使用管理ID") @RequestBody Map<String, Object> map) {
//		 {"ids":"[12,34,34]","billStatus":"已启用","searchVo":SearchVo}
        if (!isNull(map.get("searchVo"))) {
            JSONObject voObject = JSONObject.parseObject(JSON.toJSONString(map.get("searchVo")));
            SearchVo searchVo = JSONObject.toJavaObject(voObject, SearchVo.class);
            Long organizationId = LoginDataHelper.getOrgId();
            Long enterpriseId = LoginDataHelper.getEnterpriseId();
            searchVo.setEnterpriseId(enterpriseId);
            searchVo.setOrganizationId(organizationId);
            List<NsbillBillUsed> bills = billUseManService.listPageAll(searchVo);
            if (!isNull(bills)) {
                List<Long> ids = bills.stream().map(e -> e.getId()).collect(Collectors.toList());
                List<String> status = bills.stream().map(e -> e.getBillStatus()).collect(Collectors.toList());
                map.put("ids", ids);
                if(map.get("billStatus").toString().equals("已启用")){
                	if(!status.contains("已更换") | !status.contains("已废弃")){
                		BizException.fail(ResultCodeEnum.SERVER_ERROR,"票据状态为已更换或已废弃才能进行启用操作！");
                	}
                }
                if(map.get("billStatus").toString().equals("已废弃")){
                	if(status.contains("已销账") | status.contains("已废弃")){
                		BizException.fail(ResultCodeEnum.SERVER_ERROR,"存在已废弃或已销账的票据,操作失败！");
                	}
                }
            }
        }
        if (isNull(map.get("ids"))) {
            BizException.isNull("未选择数据!");
        }
        Comparator<Integer> comparator = (param1, param2) -> param1.compareTo(param2);
        JSONArray jsonArray = JSONArray.parseArray(map.get("ids").toString());
        List ids = JSONArray.toJavaObject(jsonArray, List.class);
        int size = ids.size();
        Integer result = billUseManService.enableBillUseMan(map);
        if (comparator.compare(size, result) != 0) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR,
                    "选择" + size + "条," + "成功" + (result) + "条," + "失败" + (size - result) + "条");
        }
        return new RestResult<Object>(Boolean.TRUE);
	}*/

    @ApiOperation(value = "票据启用/废弃")
    @RequestMapping(value = "/enable-billUseMan")
    public RestResult<Object> enableBillUseMan(@ApiParam(value = "票据使用管理ID") @RequestBody Map<String, Object> map) {
//		 {"ids":"[12,34,34]","billStatus":"已启用"}
        if (isNull(map.get("ids"))) {
            BizException.isNull("未选择数据!");
        }
        Comparator<Integer> comparator = (param1, param2) -> param1.compareTo(param2);
        JSONArray jsonArray = JSONArray.parseArray(map.get("ids").toString());
        List<Long> ids = JSONArray.toJavaObject(jsonArray, List.class);
        int size = ids.size();
        System.out.println(ids);
        List<NsbillBillUsed> bills = billUseManService.selectByIds(ids);
        if (!isNull(bills)) {
            List<String> status1 = bills.stream().map(e -> e.getBillStatus()).collect(Collectors.toList());
            List<NsbillBillUsed> status = bills.stream().filter(e -> !(e.getBillStatus().equals("已更换") || e.getBillStatus().equals("已废弃"))).collect(Collectors.toList());
            //Set<String> s = new HashSet(status);

            if (map.get("billStatus").toString().equals("已启用")) {
				/*if(!status.contains("已更换") && !status.contains("已废弃")){
					BizException.fail(ResultCodeEnum.SERVER_ERROR,"存在不是已更换或已废弃的票据，操作失败！");
				}*/
                if (status.size() > 0) {
                    BizException.fail(ResultCodeEnum.SERVER_ERROR, "存在不是已更换或已废弃的票据，操作失败！");
                }
            }
            if (map.get("billStatus").toString().equals("已废弃")) {
                if (status1.contains("已销账") || status1.contains("已废弃")) {
                    BizException.fail(ResultCodeEnum.SERVER_ERROR, "存在已废弃或已销账的票据,操作失败！");
                }
            }
        }
        Integer result = billUseManService.enableBillUseMan(map);
        if (comparator.compare(size, result) != 0) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR,
                    "选择" + size + "条," + "成功" + (result) + "条," + "失败" + (size - result) + "条");
        }
        return new RestResult<Object>(Boolean.TRUE);
    }


    @ApiOperation(value = "对外接口,使用票据")
    @RequestMapping(value = "/used-billUseMan", method = RequestMethod.POST)
    public RestResult<Boolean> usedBillUseMan(@ApiParam(value = "票据使用数据") @RequestBody BillUseManThird billUseManThird) {
        BizException.isNull(billUseManThird, "数据");
        Date now = new Date();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long orgId = LoginDataHelper.getOrgId();
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        billUseManThird.setEnterpriseId(enterpriseId);
        billUseManThird.setOrganizationId(orgId);
        billUseManThird.setCreateUserName(userName);
        billUseManThird.setCreateUserId(userId);
        billUseManThird.setCreateTime(now);
        billUseManThird.setUpdateUserId(userId);
        billUseManThird.setUpdateUserName(userName);
        billUseManThird.setUpdateTime(now);
        boolean result = billUseManService.usedBillUseMan(billUseManThird);
        return new RestResult<>(result);
    }

    @ApiOperation(value = "票据批量销号/反销号")
    @RequestMapping(value = "/check-billUseMan-batch", method = RequestMethod.POST)
    public RestResult<String> checkBillUseManBatch(@ApiParam(value = "票据使用管理") @RequestBody Map<String, Object> map) {
        //{"searchVo":BillUseManSearchVo,"data":[{"billType":"定额发票(佰元)","checkDate":"2018-07-31 11:11:11"},{"billType":"限额发票(仟)","checkDate":"2018-07-31 11:11:11"}]}}
        BizException.isNull(map.get("searchVo"), "查询条件");
        BizException.isNull(map.get("data"), "数据");
        Integer sum = billUseManService.checkBillUseManBatch(map);
        String result = "成功销号" + sum + "条";
        return new RestResult<>(result);
    }

    @ApiOperation(value = "批量销号查询搜索")
    @RequestMapping(value = "/list-check-billUseMan-batch", method = RequestMethod.POST)
    public RestResult<List<BillUseManCheckVo>> listCheckBillUseManBatch(@ApiParam(value = "搜索参数") @RequestBody BillUseManSearchVo billUseManSearchVo) {
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long orgId = LoginDataHelper.getOrgId();
        billUseManSearchVo.setEnterpriseId(enterpriseId);
        billUseManSearchVo.setOrganizationId(orgId);
        List<BillUseManCheckVo> nsbillBillUseds = billUseManService.listCheckBillUseManBatch(billUseManSearchVo);
        return new RestResult<>(nsbillBillUseds);
    }
    
    @ApiOperation(value = "根据票据代码模糊查询")
    @RequestMapping(value = "/list-billcode-search", method = RequestMethod.GET)
    public RestResult<List<String>> listbillCode(@RequestParam("billCode") String billCode) {
        RestResult<List<String>> restResult = null;
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getOrgId();
        List<NsbillBillUsed> listcode = billUseManService.listbillCode(billCode,enterpriseId,organizationId);
        List<String> list = new ArrayList<>();
        for (NsbillBillUsed nsbillBillUsed : listcode) {
			list.add(nsbillBillUsed.getBillCode());
		}
        restResult = new RestResult<>(list);
        return restResult;
    }
}
