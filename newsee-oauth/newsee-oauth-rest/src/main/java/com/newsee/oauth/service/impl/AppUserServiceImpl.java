package com.newsee.oauth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.entity.AppUser;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.Md5Utils;
import com.newsee.oauth.dao.AppUserMapper;
import com.newsee.oauth.entity.App;
import com.newsee.oauth.entity.AppClient;
import com.newsee.oauth.service.IAppService;
import com.newsee.oauth.service.IAppUserService;
import com.newsee.oauth.service.remote.ISystemRemoteService;
import com.newsee.oauth.util.JwtTokenUtil;
import com.newsee.oauth.vo.AppUserVo;
import com.newsee.redis.util.RedisUtil;

/**
 * Created by niyang on 2017/8/8.
 */
@Service
public class AppUserServiceImpl implements IAppUserService {

    private static final Logger logger= LoggerFactory.getLogger(AppUserServiceImpl.class);

    @Autowired
    private AppUserMapper appUserMapper;
    
    @Autowired
    ISystemRemoteService systemRemoteService;

    @Autowired
    private RedisUtil redisUtil;
    
    /**
     * 新增appUser
     * @author xiaosisi add on 2017/08/20
     */
    @Override
    public Boolean addAppUser(AppUser appUser) {
    	if(appUser.getPassword() == null || "".equals(appUser.getPassword())){
    		HashMap<String, Object> map  =  new HashMap<String,Object>();
    		map.put("userAccount", appUser.getUserAccount());
    		AppUser appUser2 = appUserMapper.selectPasswordByUserAccount(map);
    		appUser.setPassword(appUser2.getPassword());
    	}
        int result = appUserMapper.insert(appUser);
        return result > 0;
    }
    
    @Override
    public Boolean editAppUser(AppUser user) {
        int result = appUserMapper.updateByUserId(user);
        return result > 0;
    }
    
    public  Map<String, Object> tt(){
    	 Map<String, Object> result = new HashMap<>();
    	 result.put("errorCode", ResultCodeEnum.DATA_NOT_EXIST.CODE);
         BizException.fail(ResultCodeEnum.DATA_NOT_EXIST,"非法用户或用户不存在");
         System.out.println("exception  after!-------");
         return  result;
    }
    @Override
    public Map<String, Object> login(String userName, String password, String appId, String appClientType) {
        String token;
        Long timeMillis;
        BizException.isNull(userName,"用户名");
        BizException.isNull(password,"密码");
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("userAccount", userName);
        List<AppUser> users = appUserMapper.selectByUserAccount(map);
        List<AppUser> valilataUsers = appUserMapper.vailateUserAccount(map);
        if( valilataUsers.size() > 1){
            result.put("errorCode", ResultCodeEnum.DATA_NOT_EXIST.CODE);
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST,"非法用户！");
        } 
        if(!CollectionUtils.isEmpty(valilataUsers) && valilataUsers.get(0).getIsActived() != 1){
                result.put("errorCode", ResultCodeEnum.DATA_ENABLE_ACTIVE);
                 BizException.fail(ResultCodeEnum.DATA_ENABLE_ACTIVE,"用户被停用！");
        }
        if(!CollectionUtils.isEmpty(valilataUsers) &&valilataUsers.get(0).getIsDeleted() != 0){
            result.put("errorCode", ResultCodeEnum.DATA_IS_DELETED);
             BizException.fail(ResultCodeEnum.DATA_IS_DELETED,"用户已删除！");
        }
        if(CollectionUtils.isEmpty(valilataUsers)){
        	result.put("errorCode", ResultCodeEnum.DATA_NOT_EXIST.CODE);
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST,"用户不存在！");
        }
        AppUser user = users.get(0);
//        if(Objects.isNull(user)){
//        	result.put("errorCode", ResultCodeEnum.DATA_NOT_EXIST.CODE);
//            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST,"用户不存在");
//        }
        if (!user.getPassword().equals(password)) {
        	result.put("errorCode", ResultCodeEnum.PASSWORD_ERROR.CODE);
            BizException.fail(ResultCodeEnum.PASSWORD_ERROR,ResultCodeEnum.PASSWORD_ERROR.DESC);
        }
        NsSystemUser sysUser = systemRemoteService.detailUserRemote(user.getUserId());
        if(Objects.isNull(sysUser)){
            result.put("errorCode", ResultCodeEnum.DATA_NOT_EXIST.CODE);
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST,"登录用户详细信息获取失败");
        }
        AppClient appClient = getAppClient(appId, appClientType);

        token = JwtTokenUtil.generateToken(appClient.getAppSecret(), userName, user.getId());
        timeMillis = appClient.getTimeMillis();
        redisUtil.setStringValue(appId + "_" + token, JSON.toJSONString(user), timeMillis.intValue());
        redisUtil.setObjectValue(RedisKeysConstants.REDIS_LOGININFO_APP_USER_PREFIX + appId + "_" +user.getUserId(), user);
        redisUtil.setObjectValue(RedisKeysConstants.REDIS_LOGININFO_SYS_USER_PREFIX +sysUser.getUserId(), sysUser);
       
        result.put("token", token);
        result.put("timeMillis", timeMillis);
        result.put("userId", user.getUserId());
        result.put("appUser", user);
        result.put("errorCode", ResultCodeEnum.SUCCESS.CODE);
        result.put("sysUser", sysUser);
        //刷新最新登录时间
        AppUser updateUser = new AppUser();
        updateUser.setLastLoginTime(new Date());
        updateUser.setId(user.getId());
        Integer updateResult = appUserMapper.updateById(updateUser);
        if (updateResult <= 0) {
            logger.error("update user lastLoginTime error，{}", JSON.toJSONString(updateUser));
        }
        return result;
    }
    
    /**
     * 登录，选择公司后登录
     * @param userAccount
     * @param enterpriseId
     * @param password
     * @param appId
     * @param appClientType
     * @return
     */
    public Map<String,Object> login(String userAccount, Long enterpriseId, String password, String appId, String appClientType){
    	String token;
        Long timeMillis;
        BizException.isNull(userAccount,"用户名");
        BizException.isNull(password,"密码");
        BizException.isNull(enterpriseId,"企业id");
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> map = Maps.newHashMap();
        map.put("userAccount", userAccount);
        map.put("endterpriseId", enterpriseId);
        map.put("appId", appId);
        List<AppUser> users = appUserMapper.selectByUserAccount(map);
        if(CollectionUtils.isEmpty(users) || users.size() > 1){
        	result.put("errorCode", ResultCodeEnum.DATA_NOT_EXIST.CODE);
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST,"非法用户或用户不存在");
        }
        AppUser user = users.get(0);
        if (!user.getPassword().equals(password)) {
        	result.put("errorCode", ResultCodeEnum.PASSWORD_ERROR.CODE);
            BizException.fail(ResultCodeEnum.PASSWORD_ERROR,ResultCodeEnum.PASSWORD_ERROR.DESC);
        }
        NsSystemUser sysUser = systemRemoteService.detailUserRemote(user.getUserId());
        if(Objects.isNull(sysUser)){
            result.put("errorCode", ResultCodeEnum.DATA_NOT_EXIST.CODE);
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST,"登录用户详细信息获取失败");
        }
        AppClient appClient = getAppClient(appId, appClientType);

        token = JwtTokenUtil.generateToken(appClient.getAppSecret(), userAccount, user.getId());
        timeMillis = appClient.getTimeMillis();
        redisUtil.setStringValue(appId + "_" + token, JSON.toJSONString(user), timeMillis.intValue());
        redisUtil.setObjectValue(RedisKeysConstants.REDIS_LOGININFO_APP_USER_PREFIX + appId + "_" +user.getUserId(), user);
        redisUtil.setObjectValue(RedisKeysConstants.REDIS_LOGININFO_SYS_USER_PREFIX +sysUser.getUserId(), sysUser);
       
        result.put("token", token);
        result.put("timeMillis", timeMillis);
        result.put("userId", user.getUserId());
        result.put("appUser", user);
        result.put("errorCode", ResultCodeEnum.SUCCESS.CODE);
        result.put("sysUser", sysUser);
        //刷新最新登录时间
        AppUser updateUser = new AppUser();
        updateUser.setLastLoginTime(new Date());
        updateUser.setId(user.getId());
        Integer updateResult = appUserMapper.updateById(updateUser);
        if (updateResult <= 0) {
            logger.error("update user lastLoginTime error，{}", JSON.toJSONString(updateUser));
        }
        return result;
    }
    
    @Override
    public List<AppUser> queryAppUserByAccountName(String userAccount, String appId){
    	   BizException.isNull(userAccount,"用户名");
           BizException.isNull(appId,"AppId");
           Map<String, Object> map = Maps.newHashMap();
           map.put("userAccount", userAccount);
           map.put("appId", appId);
           List<AppUser> listUser = appUserMapper.selectByUserAccount(map);
           if (CollectionUtils.isEmpty(listUser)) {
               logger.error("can't find this user, please check you user account and appId");
           }
           return listUser;
    }
    
    @Override
    public AppUser validateToken(String token, String appId, String appClientType) {
        AppClient appClient = getAppClient(appId, appClientType);
        Boolean parserTokenResult = JwtTokenUtil.parserToken(token, appClient.getAppSecret());
        if(!parserTokenResult){
            BizException.fail(ResultCodeEnum.NOT_LOGIN, "非法Token");
        }
        AppUser user=transformToken2User(token, appId);
        logger.info("transformTokenUser Token:{},User:{}",token, JSON.toJSONString(user));
        return user;
    }
    
    @Override
    public Boolean validateAppId(String appId) {
    	if(StringUtils.isEmpty(appId)){
    		return false;
    	}
        App app = getApp(appId);
        if(Objects.isNull(app)){
        	return false;
        }
        if(Md5Utils.parseStrToMd5L16(app.getAppIdSource()).equals(appId)){
        	return true;
        }
        return false;
    }

    @Override
    public Boolean logout(String token, String appId, AppUser user) {
    	redisUtil.delete(appId + "_" + token);
    	if(!Objects.isNull(user)){
    		redisUtil.delete(RedisKeysConstants.REDIS_LOGININFO_APP_USER_PREFIX + appId + "_" + user.getUserId());
    		redisUtil.delete(RedisKeysConstants.REDIS_LOGININFO_USER_PREFIX + appId + "_" + user.getUserId());
    		redisUtil.delete(RedisKeysConstants.REDIS_LOGININFO_COMPANY_PREFIX + appId + "_" + user.getCompanyId());
    		redisUtil.delete(RedisKeysConstants.REDIS_LOGININFO_ENTERPRISE_PREFIX + appId + "_" + user.getEnterpriseId());
    	}
        return true;
    }
    
    private AppUser transformToken2User(String token, String appId){
        String userJson=redisUtil.getStringValue(appId + "_" + token);
        AppUser user=JSON.parseObject(userJson, AppUser.class);
        return user;
    }

    /**
     * 根据appId和appClientType获取AppClient信息
     * @param appId
     * @param appClientType
     * @return
     */
    private AppClient getAppClient(String appId, String appClientType) {
        String applicationConfigJson = redisUtil.getStringValue(IAppService.APP_CLIENT_SECRET_LIST);
        List<AppClient> appClientList = JSON.parseArray(applicationConfigJson, AppClient.class);
        Map<String, AppClient> map = new HashMap<String, AppClient>();
        for(AppClient appClient: appClientList){
        	map.put(appClient.getAppId() + "_" + appClient.getAppClientType(), appClient);
        }
        AppClient appClient = map.get(appId + "_" + appClientType);
        if (Objects.isNull(appClient)) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST,"不存在当前客户端");
        }
        return appClient;
    }
    
    /**
     * 根据appid获取App信息
     * @param appId
     * @return
     */
    private App getApp(String appId) {
        String appListJson = redisUtil.getStringValue(IAppService.APP_LIST);
        List<App> appList = JSON.parseArray(appListJson, App.class);
        Map<String, App> map = new HashMap<String, App>();
        for(App app: appList){
        	map.put(app.getAppId(), app);
        }
        App app = map.get(appId);
        if (Objects.isNull(app)) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST,"不存在当前应用");
        }
        return app;
    }

    @Override
    public Boolean deleteAppUser(Long userId) {
        boolean result = false;
        appUserMapper.deleteSoftByUserId(userId);
        result = true;
        return result;
    }
    
    @Override
    public AppUser detailAppUser(Long userId) {
        AppUser appUser = appUserMapper.selectByUserId(userId);
        return appUser;
    }

	@Override
	public Boolean updatePasswordByPhoneNumber(List<AppUserVo> appUsers) {
		boolean  result = true;
		result  = (appUserMapper.updatePasswordByPhoneNumber(appUsers) > 0 );
		return  result;
	}

}
