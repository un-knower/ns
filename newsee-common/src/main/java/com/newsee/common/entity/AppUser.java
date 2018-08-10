package com.newsee.common.entity;

import java.io.Serializable;
import java.util.Date;

public class AppUser implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1336897433931771349L;

	/** id */
    private Long id;
    
    /** appId */
    private String appId;
    
    /** 企业id */
    private Long enterpriseId;
    
    /** 公司id */
    private Long companyId;
    
    /** 组织id */
    private Long organizationId;
    
	/** 用户id */
    private Long userId;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String password;
    
    /** 用户类型，0:内部运营账户，1:物业公司员工账户，2:业主（客户）账户 */
    private Integer userType;
    
    /** 上次登录时间  */
    private Date lastLoginTime;
    
    /** 是否已激活  */
    private Integer isActived;
    
    /** 是否已删除  */
    private Integer isDeleted;

    /** 激活验证码 */
    private String activationKey;

    /** 重置密码的验证码 */
    private String resetPasswordKey;
    
    /**企业名*/
    private String enterpriseName;
    
    /**登录名*/
    private String userAccount;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getIsActived() {
		return isActived;
	}

	public void setIsActived(Integer isActived) {
		this.isActived = isActived;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getResetPasswordKey() {
		return resetPasswordKey;
	}

	public void setResetPasswordKey(String resetPasswordKey) {
		this.resetPasswordKey = resetPasswordKey;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}