package com.newsee.oauth.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.newsee.common.constant.RequestConstants;
import com.newsee.common.entity.AppUser;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.log.entity.LoginLogEntity;
import com.newsee.oauth.service.IAppUserService;
import com.newsee.oauth.service.remote.ILogRemoteService;
import com.newsee.oauth.util.CookieUtil;
import com.newsee.oauth.vo.AppUserVo;
import com.newsee.oauth.vo.MobilUserInfo;
import com.newsee.redis.util.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Created by niyang on 2017/8/8.
 */
@RestController
@RequestMapping(value = "/oauth")
@Api(tags = {"com.newsee.oauth.controller.LoginController"}, description = "用户登录 REST API，包含登录，注销，token验证方法。")
public class LoginController {

    @Autowired
    private IAppUserService appUserService;
    
    @Autowired
    private ILogRemoteService logRemoteService;
    
    @Autowired
    private RedisUtil redisUtil;

    
    
    
    /**
     * 
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/bash-edit-userinfo",method = RequestMethod.POST)
    public RestResult<Map<String, Boolean>> bashEditUserInfo(List<AppUserVo> appUserList, @RequestHeader(name="appId") String appId){
    	@SuppressWarnings("rawtypes")
		RestResult result =RestResult.SUCCESS;
    	Boolean res = appUserService.updatePasswordByPhoneNumber(appUserList);
    	if(!res) result=RestResult.FAILURE;
    	return result;
    }
    /**
     * 登录
     * @param uesrname
     * @param password
     * @param appName
     * @return RestResult
     * @throws BizException
     */
    @ApiOperation(
    		value = "登录", 
    		notes = "登录接口，检查用户名和密码是否正确，返回响应的状态码",
    		response = RestResult.class)
    @ApiResponses({@ApiResponse(code = 200, message="登录成功。"),
    	 		   @ApiResponse(code = 500, message="系统内部错误，请联系管理员及时处理。"),
    	 		   @ApiResponse(code = 501, message="数据库异常，请联系管理员及时处理。"),
    	           @ApiResponse(code = 604, message="用户不存在。"),
    	           @ApiResponse(code = 601, message="密码错误。")})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> login(
    		@ApiParam(name="username",value="用户名")@RequestParam("username") String username, 
    		@ApiParam(name="password",value="密码")@RequestParam("password") String password, 
    		@ApiParam(name="appId",value="应用Id")@RequestHeader("appId") String appId,
    		@ApiParam(name="appClientType",value="应用客户端类型")@RequestHeader("appClientType") String appClientType
    		) throws BizException, Exception {
        Map<String, Object> result = appUserService.login(username, password, appId, appClientType);
        String token = "";
        if(!Objects.isNull(result.get("token"))){
        	token = (String)result.get("token");
        }
        AppUser user = null;
        if(!Objects.isNull(result.get("appUser"))){
        	user = (AppUser)result.get("appUser");
        }
        sendLoginLog(appId, appClientType, RequestConstants.LOGIN_METHOD, token, user, username, ResultCodeEnum.SUCCESS.CODE);
        return new RestResult<>(result);
    }
    
    
    /**
     * 登录
     * @param uesrname
     * @param password
     * @param appName
     * @return RestResult
     * @throws BizException
     */
    @ApiOperation(
    		value = "登录", 
    		notes = "移动端登录",
    		response = RestResult.class)
    @ApiResponses({@ApiResponse(code = 200, message="登录成功。"),
    	 		   @ApiResponse(code = 500, message="系统内部错误，请联系管理员及时处理。"),
    	 		   @ApiResponse(code = 501, message="数据库异常，请联系管理员及时处理。"),
    	           @ApiResponse(code = 604, message="用户不存在。"),
    	           @ApiResponse(code = 601, message="密码错误。")})
    @RequestMapping(value = "/login_android", method = RequestMethod.POST)
    public RestResult<MobilUserInfo> login_android(
    		@RequestParam("account") String account,
    		@RequestParam("passWord") String passWord,
    		@RequestHeader("pushClientID") String pushClientID,
    		@RequestHeader("deviceInfo") String deviceInfo
    		) throws BizException, Exception {
    	MobilUserInfo mobilUserInfo = null;
        Map<String, Object> result = appUserService.login(account, passWord, pushClientID, deviceInfo);
        String token = "";
        if(!Objects.isNull(result.get("token"))){
        	token = (String)result.get("token");
        }
        AppUser user = null;
        if(!Objects.isNull(result.get("appUser"))){
        	user = (AppUser)result.get("appUser");
        }
        NsSystemUser sysUser = null;
        if(!Objects.isNull(result.get("sysUser"))){
        	sysUser =(NsSystemUser) result.get("sysUser");
        }
        mobilUserInfo.setUserName(sysUser.getUserAccount());
        mobilUserInfo.setUserToken(token);
        mobilUserInfo.setMobilePhone(sysUser.getUserTelephone());
        mobilUserInfo.setDepartmentID(sysUser.getOrganizationId()+"");
        mobilUserInfo.setDepartmentName(sysUser.getOrganizationShortName());
        mobilUserInfo.setCompanyID(sysUser.getEnterpriseId()+"");
        sendLoginLog(pushClientID, deviceInfo, RequestConstants.LOGIN_METHOD, token, user, account, ResultCodeEnum.SUCCESS.CODE);
        return new RestResult<>(mobilUserInfo);
    }
    
    /**
     * 登录
     * @param uesrname
     * @param password
     * @param appName
     * @return RestResult
     * @throws BizException
     */
    @ApiOperation(
    		value = "登录", 
    		notes = "登录接口，检查用户名和密码是否正确，返回响应的状态码",
    		response = RestResult.class)
    @ApiResponses({@ApiResponse(code = 200, message="登录成功。"),
    	 		   @ApiResponse(code = 500, message="系统内部错误，请联系管理员及时处理。"),
    	 		   @ApiResponse(code = 501, message="数据库异常，请联系管理员及时处理。"),
    	           @ApiResponse(code = 604, message="用户不存在。"),
    	           @ApiResponse(code = 601, message="密码错误。")})
    @RequestMapping(value = "/mutil-enterprise-login", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> mutilEnterpriseLogin(
    		@ApiParam(name="userAccount",value="用户名")@RequestParam("userAccount") String userAccount, 
    		@ApiParam(name="password",value="密码")@RequestParam("password") String password, 
    		@ApiParam(name="enterpriseId",value="企业id")@RequestParam("enterpriseId") Long enterpriseId, 
    		@ApiParam(name="appId",value="应用Id")@RequestHeader("appId") String appId,
    		@ApiParam(name="appClientType",value="应用客户端类型")@RequestHeader("appClientType") String appClientType
    		) throws BizException, Exception {
        Map<String, Object> result = appUserService.login(userAccount, enterpriseId, password, appId, appClientType);
        String token = "";
        if(!Objects.isNull(result.get("token"))){
        	token = (String)result.get("token");
        }
        AppUser user = null;
        if(!Objects.isNull(result.get("appUser"))){
        	user = (AppUser)result.get("appUser");
        }
        sendLoginLog(appId, appClientType, RequestConstants.LOGIN_METHOD, token, user, userAccount, ResultCodeEnum.SUCCESS.CODE);
        return new RestResult<>(result);
    }
    
    /**
     * 登录
     * @param uesrname
     * @param password
     * @param appName
     * @return RestResult
     * @throws BizException
     */
    @ApiOperation(
    		value = "获取用户是否有多家公司", 
    		notes = "登录接口，检查用户名和密码是否正确，返回响应的状态码",
    		response = RestResult.class)
    @RequestMapping(value = "/query-user-enterprise", method = RequestMethod.GET)
    public RestResult<List<AppUserVo>> queryUserEnterprise(
    		@ApiParam(name="userAccount",value="用户名")@RequestParam("userAccount") String userAccount, 
    		@ApiParam(name="appId",value="应用Id")@RequestHeader("appId") String appId
    		) throws BizException, Exception {
        List<AppUser> list= appUserService.queryAppUserByAccountName(userAccount, appId);
        List<AppUserVo> listVo = new ArrayList<AppUserVo>();
        RestResult<List<AppUserVo>> result = new RestResult<List<AppUserVo>>(listVo);
        if(!CollectionUtils.isEmpty(list)){
        	list.forEach(appUser ->{
        		AppUserVo vo = new AppUserVo();
        		BeanUtils.copyProperties(appUser, vo);
        		listVo.add(vo);
        	});
        	result.setResultData(listVo);
        }else{
        	result = RestResult.DATA_NOT_EXIST;
        }
        return result;
    }

    @ApiOperation(
    		value = "用户注册", 
    		notes = "注册接口",
    		response = RestResult.class)
    @ApiResponses({@ApiResponse(code = 200, message="注册成功。"),
    			   @ApiResponse(code = 500, message="系统内部错误，请联系管理员及时处理。"),
    			   @ApiResponse(code = 501, message="数据库异常，请联系管理员及时处理。")})
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RestResult<Boolean> register(@ApiParam(name="user",value="用户对象")@RequestBody AppUser user)throws BizException{
        Boolean result = appUserService.addAppUser(user);
        return new RestResult<>(result);
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResult<Boolean> edit(@ApiParam(name="user",value="用户对象")@RequestBody AppUser user)throws BizException{
        Boolean result = appUserService.editAppUser(user);
        return new RestResult<>(result);
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public RestResult<Boolean> delete(@RequestParam(value="userId") Long userId)throws BizException{
        Boolean result = appUserService.deleteAppUser(userId);
        return new RestResult<>(result);
    }
    
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public AppUser detail(@RequestParam(value="userId") Long userId)throws BizException{
        AppUser result = appUserService.detailAppUser(userId);
        return result;
    }

    @ApiOperation(
    		value = "验证token", 
    		notes = "token验证接口",
    		response = RestResult.class)
    @ApiResponses({@ApiResponse(code = 200, message="token有效。"),
    			   @ApiResponse(code = 401, message="非法token。"),
    			   @ApiResponse(code = 500, message="系统内部错误，请联系管理员及时处理。"),
    			   @ApiResponse(code = 501, message="数据库异常，请联系管理员及时处理。"),
    			   @ApiResponse(code = 604, message="非法客户端。"),})
    @RequestMapping(value = "/validateToken", method = RequestMethod.GET)
    public RestResult<AppUser> validateToken(
    		@ApiParam(name="token",value="token") @RequestHeader("token") String token, 
    		@ApiParam(name="appId",value="应用Id")@RequestHeader("appId") String appId,
    		@ApiParam(name="appClientType",value="应用客户端类型")@RequestHeader("appClientType") String appClientType
    		) throws BizException {
        AppUser user = appUserService.validateToken(token, appId, appClientType);
        return new RestResult<>(user);
    }
    
    @ApiOperation(
    		value = "注销", 
    		notes = "注销接口",
    		response = RestResult.class)
    @ApiResponses({@ApiResponse(code = 200, message="注销成功。"),
    	           @ApiResponse(code = 500, message="系统内部错误，请联系管理员及时处理。"),
    	           @ApiResponse(code = 501, message="数据库异常，请联系管理员及时处理。")})
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public RestResult<Boolean> logout(
    		@ApiParam(name="token",value="token") @RequestHeader("token") String token, 
    		@ApiParam(name="appId", value="应用英文名称")@RequestHeader("appId") String appId, 
    		HttpServletResponse response)  throws BizException,Exception{
        CookieUtil.clear(response, "token");
        String userJson= redisUtil.getStringValue(appId + "_" + token);
        AppUser user = JSON.parseObject(userJson, AppUser.class);
        String appClientType = LoginDataHelper.getAppClientType();
        String userName = user.getUserName();
        sendLoginLog(appId, appClientType, RequestConstants.LOGOUT_METHOD, token, user, userName, ResultCodeEnum.SUCCESS.CODE);
        Boolean result = appUserService.logout(token, appId, user);
        return new RestResult<>(result);
    }
    
    @ApiOperation(
    		value = "验证appId合法性", 
    		notes = "验证appId合法性接口",
    		response = RestResult.class)
    @ApiResponses({@ApiResponse(code = 200, message="验证接口调用成功。")})
    @RequestMapping(value = "/validateAppId", method = RequestMethod.GET)
    public RestResult<Boolean> validateAppId(@ApiParam(name="appId",value="应用Id")@RequestHeader("appId") String appId){
    	 Boolean result = appUserService.validateAppId(appId);
    	 return new RestResult<>(result);
    }
    
    /**
     * 发送登录或者登出消息至kafka，kafka监听到之后插入到mongodb中
     * @param appId
     * @param appClientType
     * @param loginType
     * @param token
     * @param user
     * @param userName
     * @throws Exception
     */
    private void sendLoginLog(String appId, 
    		String appClientType, 
    		String loginType, 
    		String token,  
    		AppUser user, 
    		String userName, 
    		String errorCode) throws Exception{
    	LoginLogEntity log = new LoginLogEntity();
    	log.setAppId(appId);
    	log.setAppClientType(appClientType);
    	log.setIp(LoginDataHelper.getIpAddr());
    	log.setLoginDate(new Date());
    	log.setLoginType(loginType);
    	log.setUserName(userName);
    	log.setErrorCode(errorCode);
    	if(!Objects.isNull(user)){
    		log.setToken(token);
        	log.setUserId(user.getUserId());
        	log.setEnterpriseId(user.getEnterpriseId());
        	log.setCompanyId(user.getCompanyId());
        	log.setOrganizationId(user.getOrganizationId());
    	}
    	logRemoteService.sendLoginLog(log);
    }
}
