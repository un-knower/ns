package com.newsee.oauth.service;


import java.util.List;
import java.util.Map;

import com.newsee.common.entity.AppUser;
import com.newsee.oauth.vo.AppUserVo;

/**
 * Created by niyang on 2017/8/8.
 */
public interface IAppUserService {
	
	
	/**
	 * update password by user phoneNumber
	 */

	public Boolean updatePasswordByPhoneNumber(List<AppUserVo> appUsers);
    /**
     * 新增用户
     * @param user
     * @return
     */
    public Boolean addAppUser(AppUser user);
    
    /**
     * 编辑用户
     * @param user
     * @return
     */
    public Boolean editAppUser(AppUser user);
    
    /**
     * 删除用户
     * @param user
     * @return
     */
    public Boolean deleteAppUser(Long userId);
    
    
    /**
     * 用户详情
     * @param user
     * @return
     */
    public AppUser detailAppUser(Long userId);
    
    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public Map<String,Object> login(String userName, String password, String appId, String appClientType);
    
    /**
     * 登录，选择公司后登录
     * @param userAccount
     * @param enterpriseId
     * @param password
     * @param appId
     * @param appClientType
     * @return
     */
    public Map<String,Object> login(String userAccount, Long enterpriseId, String password, String appId, String appClientType);
    
    /**
     * 根据账户名和appId获取AppUser信息
     * @param accountName
     * @param AppId
     * @return
     * @history add by xiaosisi on 2018/02/01
     */
    public List<AppUser> queryAppUserByAccountName(String userAccount, String appId);

    /**
     * 验证token
     * @param token
     * @param applicationClientNickName
     * @return
     */
    public AppUser validateToken(String token, String appId, String appClientType);
    
    /**
     * 验证AppId
     * @param appId
     * @return
     */
    public Boolean validateAppId(String appId);

    /**
     * 登出
     * @param token
     * @return
     */
    public Boolean logout(String token, String appClientType, AppUser user);
}
