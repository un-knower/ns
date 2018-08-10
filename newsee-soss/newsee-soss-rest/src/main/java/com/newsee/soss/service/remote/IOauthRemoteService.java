package com.newsee.soss.service.remote;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newsee.common.rest.RestResult;
import com.newsee.soss.vo.AppUserVo;


@FeignClient(value="oauth-server")
public interface IOauthRemoteService {
	 /**单企业登录授权*/
//    @RequestMapping(value = "/oauth/login", method = RequestMethod.POST)
//    public RestResult<Map<String, Object>> loginOauth(@RequestParam(name="username",required= true) String username,@RequestParam(name="password",required=true) String password,
//    		@RequestHeader(name="appId",required=true) String appId,@RequestHeader(name="appClientType",required=true) String appClientType	);

    /**查询多企业信息*/
    @RequestMapping(value="/oauth/query-user-enterprise", method = RequestMethod.GET)
    public RestResult<List<AppUserVo>> getUserInfo(@RequestParam(name="userAccount") String useraccount, @RequestHeader(name="appId") String appId);
    /**批量修改密码企业信息*/
    @RequestMapping(value="/oauth/bash-edit-userinfo", method = RequestMethod.POST)
	public RestResult<Map<String, Object>> bashEditUserInfo(List<AppUserVo> appUserVoList, @RequestHeader(name="appId") String appId);

    /**多企业选择账号登录*/
//    @RequestMapping(value="/oauth/mutil-enterprise-login", method = RequestMethod.POST)
//    public RestResult<Map<String, Object>> mutilEnterpriseLogin(@RequestParam(name="userAccount") String userAccount, @RequestParam("password") String password, @RequestParam(name = "enterpriseId") Long enterpriseId, @RequestHeader(name ="appId") String appId,  @RequestHeader(name = "appClientType") String appClientType);
}
