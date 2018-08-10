package com.newsee.apigateway.service.remote;

import com.newsee.common.exception.BizException;
import com.newsee.common.rest.RestResult;
import com.newsee.oauth.vo.AppUserVo;
import com.newsee.common.entity.AppUser;

import io.swagger.annotations.ApiParam;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by niyang on 2017/8/11.
 */
@FeignClient("oauth-server")
@RequestMapping(value = "/oauth")
public interface IOauthRemoteService {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RestResult<Map<String,Object>> login(@RequestParam("username")String username, 
    		@RequestParam("password") String password,
    		@RequestHeader("appId")String appName,
    		@RequestHeader("appClientType")String appClientType)throws BizException;
    
    @RequestMapping(value = "/mutil-enterprise-login", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> mutilEnterpriseLogin(
    		@RequestParam("userAccount") String userAccount, 
    		@RequestParam("password") String password, 
    		@RequestParam("enterpriseId") Long enterpriseId, 
    		@RequestHeader("appId") String appId,
    		@RequestHeader("appClientType") String appClientType
    		) throws BizException;
    
    @RequestMapping(value = "/query-user-enterprise", method = RequestMethod.POST)
    public RestResult<List<AppUserVo>> queryUserEnterprise(
    		@RequestParam("useraccount") String userAccount, 
    		@RequestHeader("appId") String appId
    		) throws BizException;

    @RequestMapping(value = "/validateToken",method = RequestMethod.GET)
    public RestResult<AppUser> validateToken(@RequestHeader("token")String token, 
    		@RequestHeader("appId")String appId,
    		@RequestHeader("appClientType")String appClientType)throws BizException;

    @RequestMapping(value ="/logout",method = RequestMethod.GET)
    public RestResult<Boolean> logout(@RequestHeader("token")String token,@RequestHeader("appId")String appId)throws BizException;
    
    @RequestMapping(value ="/validateAppId",method = RequestMethod.GET)
    public RestResult<Boolean> validateAppId(@ApiParam(name="appId",value="应用Id")@RequestHeader("appId") String appId)throws BizException;;
}
