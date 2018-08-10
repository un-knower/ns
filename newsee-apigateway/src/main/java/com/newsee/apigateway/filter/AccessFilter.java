package com.newsee.apigateway.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.newsee.apigateway.service.remote.IOauthRemoteService;
import com.newsee.apigateway.service.remote.ISystemRemoteService;
import com.newsee.apigateway.util.CommonExceptionHandler;
import com.newsee.apigateway.util.CookieUtil;
import com.newsee.common.constant.RequestConstants;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.oauth.vo.AppUserVo;
import com.newsee.common.entity.AppUser;
import com.newsee.common.entity.NsSystemUser;

public class AccessFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);
    
    @Autowired
    private IOauthRemoteService oauthRemoteService;
    
    @Autowired
    private ISystemRemoteService systemRemoteService;

	/** 
	 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * pre：可以在请求被路由之前调用
     * routing：在路由请求时候被调用
     * post：在routing和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     * filterOrder：通过int值来定义过滤器的执行顺序
     * shouldFilter：返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效。
     * run：过滤器的具体逻辑。需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，
     * 然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。
     * 
     **/
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public Object run() {
        Long inTime = System.nanoTime();
        logger.info("zuul AccessFilter start");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        HttpServletResponse response = ctx.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        //获取客户端类型
        String appClientType = request.getHeader(RequestConstants.APP_CLIENT_TYPE);
        if (StringUtils.isEmpty(appClientType)) {
        	appClientType = RequestConstants.PC;
        }
        //获取请求中的token
        String token;
        if (RequestConstants.PC.equals(appClientType)) {
            token = CookieUtil.getValue(ctx.getRequest(), RequestConstants.TOKEN);
            if(StringUtils.isEmpty(token)) {
            	  token = request.getHeader(RequestConstants.TOKEN);
            }
        } else {
            token = request.getHeader(RequestConstants.TOKEN);
        }
        
        //获取请求参数中的appId
        String appId = request.getHeader(RequestConstants.APP_ID);
        RestResult<Boolean> result = oauthRemoteService.validateAppId(appId);
        if(ResultCodeEnum.SUCCESS.CODE.equals(result.getResultCode())){
        	Boolean appIdResult = result.getResultData();
        	if(!appIdResult){
        		ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(200);
                ctx.setResponseBody(CommonExceptionHandler.handlerExcetpion("非法appId", "非法appId"));
                return null;
        	}
        }
        
        //登录操作
        String requestUri = request.getRequestURI();
        if(requestUri.indexOf(RequestConstants.QUERY_USER_ENTERPRISE) > 0){
        	 String userAccount = request.getParameter(RequestConstants.USERACCOUNT);
        	 try{
        		 RestResult<List<AppUserVo>> userResult = oauthRemoteService.queryUserEnterprise(userAccount, appId);
            	 if (userResult.getResultCode().equals(ResultCodeEnum.SUCCESS.CODE)) {
            		 ctx.setResponseBody(JSONObject.toJSONString(userResult.getResultData()));
                     ctx.setResponse(response);
            	 }else{
            		 ctx.setResponseBody(JSONObject.toJSONString(userResult));
            	 }
            	 ctx.setSendZuulResponse(false);
                 return ctx;
        	 }catch(Exception e){
        		 ctx.setSendZuulResponse(false);
                 ctx.setResponseStatusCode(200);
                 ctx.setResponseBody(CommonExceptionHandler.handlerExcetpion(null,e.getMessage()));
                 return null;
        	 }
        	
        }
		if (requestUri.indexOf(RequestConstants.MUTIL_LOGIN_METHOD) > 0) {
			String userAccount = request.getParameter(RequestConstants.USERACCOUNT);
			String password = request.getParameter(RequestConstants.PASSWORD);
			String enterpriseId = request.getParameter(RequestConstants.ENTERPRISEID);
			if (StringUtils.isEmpty(enterpriseId)) {
				ctx.setSendZuulResponse(false);
				ctx.setResponseStatusCode(200);
				ctx.setResponseBody(CommonExceptionHandler.handlerExcetpion("企业id不能为空", "企业id不能为空"));
				return null;
			}
			try {
				RestResult<Map<String, Object>> loginRestResult = oauthRemoteService.mutilEnterpriseLogin(
						userAccount,
						password, 
						Long.parseLong(enterpriseId),
						appId,
						appClientType);
				Long elapsedTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - inTime);
				logger.info("zuul AccessFilter Login response:{},{}", JSONObject.toJSONString(loginRestResult),
						elapsedTime);

				if (loginRestResult.getResultCode().equals(ResultCodeEnum.SUCCESS.CODE)) {
					// 获取用户信息，公司信息，企业信息，并存入到redis中
					AppUser appUser = JSON.parseObject(
							JSON.toJSONString(loginRestResult.getResultData().get("appUser")), AppUser.class);
					NsSystemUser sysUser = JSON.parseObject(
							JSON.toJSONString(loginRestResult.getResultData().get("sysUser")), NsSystemUser.class);
					if (!Objects.isNull(appUser)) {
						systemRemoteService.userInfo(appId, appUser.getUserId());
						systemRemoteService.companyInfo(appId, appUser.getCompanyId());
						systemRemoteService.enterpriseInfo(appId, appUser.getEnterpriseId());
					}
					Map<String, Object> loginResult = loginRestResult.getResultData();
					String resultToken = (String) loginResult.get(RequestConstants.TOKEN);
					Integer timeMillis = (Integer) loginResult.get(RequestConstants.TIME_MILLIS);
					CookieUtil.create(ctx.getResponse(), RequestConstants.TOKEN, resultToken, false, timeMillis);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("userId", appUser.getUserId());
					map.put("userName", appUser.getUserName());
					map.put("maxage", timeMillis);
					map.put("loginTime", (new Date()).getTime());
					map.put("avatar", sysUser.getUserPicurl());
					map.put("token", resultToken);
					RestResult<Map<String, Object>> restResult = new RestResult<>(map);
					ctx.setResponseBody(JSONObject.toJSONString(restResult));
					ctx.setResponse(response);
				} else {
					ctx.setResponseBody(JSONObject.toJSONString(loginRestResult));
				}
				ctx.setSendZuulResponse(false);
				return ctx;
			} catch (Exception e) {
				ctx.setSendZuulResponse(false);
				ctx.setResponseStatusCode(200);
				ctx.setResponseBody(CommonExceptionHandler.handlerExcetpion(null, e.getMessage()));
				return null;
			}

		}
        else if (requestUri.indexOf(RequestConstants.LOGIN_METHOD) > 0) {
            String userName = request.getParameter(RequestConstants.USERNAME);
            String password = request.getParameter(RequestConstants.PASSWORD);
            try {
                RestResult<Map<String, Object>> loginRestResult = oauthRemoteService.login(userName, password, appId, appClientType);
                Long elapsedTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - inTime);
                logger.info("zuul AccessFilter Login response:{},{}", JSONObject.toJSONString(loginRestResult), elapsedTime);

                if (loginRestResult.getResultCode().equals(ResultCodeEnum.SUCCESS.CODE)) {
                	//获取用户信息，公司信息，企业信息，并存入到redis中
                	AppUser appUser = JSON.parseObject(JSON.toJSONString(loginRestResult.getResultData().get("appUser")), AppUser.class);
                	NsSystemUser sysUser = JSON.parseObject(JSON.toJSONString(loginRestResult.getResultData().get("sysUser")), NsSystemUser.class);
                	if(!Objects.isNull(appUser)){
                		systemRemoteService.userInfo(appId, appUser.getUserId());
                		systemRemoteService.companyInfo(appId, appUser.getCompanyId());
                		systemRemoteService.enterpriseInfo(appId, appUser.getEnterpriseId());
                	}
                    Map<String, Object> loginResult = loginRestResult.getResultData();
                    String resultToken = (String) loginResult.get(RequestConstants.TOKEN);
                    Integer timeMillis = (Integer) loginResult.get(RequestConstants.TIME_MILLIS);
                    CookieUtil.create(ctx.getResponse(), RequestConstants.TOKEN, resultToken, false, timeMillis);
                    Map<String, Object> map= new HashMap<String, Object>();
                    map.put("userId", appUser.getUserId());
                    map.put("userName", appUser.getUserName());
                    map.put("maxage", timeMillis);
                    map.put("loginTime", (new Date()).getTime());
                    map.put("avatar", sysUser.getUserPicurl());
                    map.put("token", resultToken);
                    RestResult<Map<String, Object>> restResult = new RestResult<>(map);
                    ctx.setResponseBody(JSONObject.toJSONString(restResult));
                    ctx.setResponse(response);
                } else {
                    ctx.setResponseBody(JSONObject.toJSONString(loginRestResult));
                }
                ctx.setSendZuulResponse(false);
                return ctx;
            } catch (Exception e) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(200);
                ctx.setResponseBody(CommonExceptionHandler.handlerExcetpion(null,e.getMessage()));
                return null;
            }

        //注销操作
        } else if (requestUri.indexOf(RequestConstants.LOGOUT_METHOD) > 0) {
            try {
                RestResult<Boolean> logoutRestResult = oauthRemoteService.logout(token, appId);

                Long elapsedTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - inTime);
                logger.info("zuul AccessFilter Logout response:{},{}", JSONObject.toJSONString(logoutRestResult), elapsedTime);

                if (logoutRestResult.getResultCode().equals(ResultCodeEnum.SUCCESS.CODE)) {
                    Boolean logoutResult = (Boolean) logoutRestResult.getResultData();
                    ctx.setResponseBody(logoutResult.toString());
                } else {
                    ctx.setResponseBody(logoutRestResult.getResultMsg());
                }
                return ctx;
            } catch (Exception e) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseBody(CommonExceptionHandler.handlerExcetpion(null,e.getMessage()));
                return null;
            }
            
        //其他业务操作
        } else {
            if (StringUtils.isEmpty(token)) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseBody(CommonExceptionHandler.handlerExcetpion("没有token","没有token"));
                return ctx;
            }
            try {
                RestResult<AppUser> validateResult = oauthRemoteService.validateToken(token, appId, appClientType);

                Long elapsedTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - inTime);
                logger.info("zuul AccessFilter validateToken token:{},applicationClient:{},response:{},{}",
                        token, appClientType, JSONObject.toJSONString(validateResult), elapsedTime);

                if (validateResult.getResultCode().equals(ResultCodeEnum.SUCCESS.CODE)) {
                    AppUser user = validateResult.getResultData();
                    if (!Objects.isNull(user)) {
                    	Long currentOrgId = user.getOrganizationId();
                    	//获取公司级别的organizaitonId
                    	RestResult<Long> orgIdResult = systemRemoteService.getOrgIdCompanyLevel(currentOrgId);
                    	//获取集团级别的organizaitonId
                        RestResult<Long> groupOrgIdResult = systemRemoteService.getGroupOrgIdCompanyLevel(currentOrgId);
                    	Long companyLevelOrgId = orgIdResult.getResultData();
                    	Long groupLevelOrgId = groupOrgIdResult.getResultData();
                        ctx.addZuulRequestHeader(RequestConstants.HEADER_USER_ENTERPRISEID, user.getEnterpriseId().toString());
                        ctx.addZuulRequestHeader(RequestConstants.HEADER_USER_COMPANYID, user.getCompanyId().toString());
                        ctx.addZuulRequestHeader(RequestConstants.HEADER_USER_COMPANYLEVEL_ORGANIZATIONID, companyLevelOrgId.toString()); 
                        ctx.addZuulRequestHeader(RequestConstants.HEADER_USER_GROUPLEVEL_ORGANIZATIONID, groupLevelOrgId.toString()); 
                        ctx.addZuulRequestHeader(RequestConstants.HEADER_USER_CURRENT_ORGANIZATIONID, currentOrgId.toString());
                        ctx.addZuulRequestHeader(RequestConstants.HEADER_USER_ID, user.getUserId().toString());
                        return ctx;
                    } else {
                        ctx.setSendZuulResponse(false);
                        ctx.setResponseBody(CommonExceptionHandler.handlerExcetpion("token错误","token错误"));
                        return ctx;
                    }
                } else {
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseBody(validateResult.getResultMsg());
                    return ctx;
                }
            } catch (Exception e) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseBody(CommonExceptionHandler.handlerExcetpion(null,e.getMessage()));
                return null;
            }
        }
    }

    @Override
    public boolean shouldFilter() {
    	RequestContext ctx = RequestContext.getCurrentContext();
    	HttpServletRequest request = ctx.getRequest();
    	String uri = request.getRequestURI();
    	if (uri.contains("/enterprise/msg-code")  //获取验证码
    			|| uri.contains("/enterprise/register") //注册企业
    			|| uri.contains("/oauth/query-user-enterprise")//验证多企业
    			||uri.contains("/oauth/register-enterprise")//登录    			
    			) 
    	{
    		return false;
    	} else {
    		return true;
    	}
    }

}