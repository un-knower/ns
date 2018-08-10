package com.newsee.system.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.system.entity.NsCoreFuncinfo;
import com.newsee.system.service.INsFuncinfoService;
import com.newsee.system.vo.NsCoreFuncinfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@ResponseBody
@RequestMapping("/menu")
@Api(tags = {"com.newsee.system.controller.NsMenuController"}, description = "菜单按钮  REST API，包含所有菜单，页面按钮的操作方法。")
public class NsMenuController {

    @Autowired
    private INsFuncinfoService systemFuncinfoService;
    
   
    /**
     * 
     * @Title: detailMenu 
     * @Description: 查看菜单详情
     * @param menuId
     * @return RestResult    返回类型 
     * @throws 
     * @author 肖斯斯
      */
     @ApiOperation(value = "获取菜单详情(平台管理)")
     @RequestMapping(value = "/detail-funcinfo", method = RequestMethod.GET)
     public RestResult<NsCoreFuncinfoVo> detailFuncinfo(@ApiParam(value = "菜单ID") @RequestHeader("functionId") String functionId){
         RestResult<NsCoreFuncinfoVo> restResult = null;
         Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
         Long enterpriseId = LoginDataHelper.getEnterpriseId();
         Map<String, Object> map = new HashMap<>();
         map.put("enterpriseId", enterpriseId);
         map.put("organizationId", organizationId);
         map.put("funcId", functionId);
         NsCoreFuncinfo info =  systemFuncinfoService.getFuncInfo(map);
         NsCoreFuncinfoVo vo = new NsCoreFuncinfoVo();
         BeanUtils.copyProperties(info, vo);
         restResult = new  RestResult<NsCoreFuncinfoVo>(vo);
         return restResult;
     }
}
