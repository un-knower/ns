package com.newsee.system.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.entity.NsSystemCompany;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.rest.RestResult;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.service.ILoginInfoService;
import com.newsee.system.service.remote.ISossRemoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@ResponseBody
@RequestMapping("/lcinfo")
@Api(tags = {"com.newsee.system.controller.LoginInfoController"}, description = "获取登录信息  REST API，包含获取企业信息，公司信息，用户信息的方法")
public class LoginInfoController {

	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private ILoginInfoService loginInfoService;
	@Autowired
	private ISossRemoteService sossRemoteService;
	
	@Value("${spring.websocket.url}")
	private String websocketURL ;
	
	@ApiOperation(value="获取当前环境的websocketurl")
	@RequestMapping(value="/websocket-url",method=RequestMethod.GET)
	public RestResult<String> getWebsocketURL(){
		RestResult<String> result = null;
		System.out.println("------------------------"+websocketURL+"=------------");
		result = new RestResult<String>(websocketURL);
		return result;
	}
	@ApiOperation(value = "根据userId获取user信息")
	@RequestMapping(value="/user-info",method=RequestMethod.GET)
	public RestResult<NsSystemUser> userInfo(@ApiParam(name="appId",value="appId")@RequestParam("appId") String appId,
			@ApiParam(name="userId",value="用户id")@RequestParam("userId") Long userId){
		RestResult<NsSystemUser> result = null;
		NsSystemUser user = loginInfoService.getNsSystemUser(userId);
		if(!Objects.isNull(user)){
			redisUtil.setObjectValue(RedisKeysConstants.REDIS_LOGININFO_USER_PREFIX+appId+"_"+ userId.toString(), user);
		}
		result = new RestResult<>(user);
		return result;
	}
	
	@ApiOperation(value = "根据companyId获取公司信息")
	@RequestMapping(value="/company-info",method=RequestMethod.GET)
	public RestResult<NsSystemCompany> companyInfo(@ApiParam(name="appId",value="appId")@RequestParam("appId") String appId,
			@ApiParam(name="companyId",value="公司id")@RequestParam("companyId") Long companyId){
		RestResult<NsSystemCompany> result = null;
		NsSystemCompany company = loginInfoService.getNsSystemCompany(companyId);
		if(!Objects.isNull(company)){
			redisUtil.setObjectValue(RedisKeysConstants.REDIS_LOGININFO_COMPANY_PREFIX+appId+"_"+ companyId.toString(), company);
		}
		result = new RestResult<>(company);
		return result;
	}
	
	@ApiOperation(value = "根据enterpriseId获取企业信息")
	@RequestMapping(value="/enterprise-info",method=RequestMethod.GET)
	public RestResult<NsSossEnterprise> enterpriseInfo(@ApiParam(name="appId",value="appId")@RequestParam("appId") String appId,
			@ApiParam(name="enterpriseId",value="企业id")@RequestParam("enterpriseId") Long enterpriseId){
		RestResult<NsSossEnterprise> result = null;
//		NsSossEnterprise enterprise = loginInfoService.getNsPlatformEnterprise(enterpriseId);
		NsSossEnterprise enterprise = sossRemoteService.getEnterpriseInfo(enterpriseId);
		if(!Objects.isNull(enterprise)){
			redisUtil.setObjectValue(RedisKeysConstants.REDIS_LOGININFO_ENTERPRISE_PREFIX+appId+"_"+ enterpriseId.toString(), enterprise);
		}
		result = new RestResult<>(enterprise);
		return result;
	}
	
}
