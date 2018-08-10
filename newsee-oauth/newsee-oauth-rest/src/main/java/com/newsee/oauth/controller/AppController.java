package com.newsee.oauth.controller;

import com.newsee.common.exception.BizException;
import com.newsee.common.rest.RestResult;
import com.newsee.oauth.entity.AppClient;
import com.newsee.oauth.service.IAppService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by niyang on 2017/8/11.
 */
@RestController
@RequestMapping(value = "/oauth")
@Api(tags = {"com.newsee.oauth.controller.ApplicationController"}, description = "客户端操作REST API，包含增加增加caesar系统访问客户端的方法。")
public class AppController {

    @Autowired
    private IAppService appService;
    
    @ApiOperation(
    		value = "增加caesar系统访问客户端", 
    		notes = "按照appId，clientType，秘钥，及秘钥有效期增加秘钥，在系统初始化时该秘钥会加载只redis，用于加密返回给客户端的token。",
    		response = RestResult.class)
    @ApiResponses({@ApiResponse(code = 200, message="操作成功。"),
    	           @ApiResponse(code = 500, message="系统内部错误，请联系管理员及时处理。"),
    	           @ApiResponse(code = 501, message="数据库异常，请联系管理员及时处理。")})
    @RequestMapping(value = "/addAppClient",method = RequestMethod.POST)
    public Boolean addApplicationClient(@ApiParam(name="appClient",value="客户端对象")@RequestBody AppClient appClient)throws BizException{
        Boolean result=appService.addAppClient(appClient);
        return result;
    }
}
