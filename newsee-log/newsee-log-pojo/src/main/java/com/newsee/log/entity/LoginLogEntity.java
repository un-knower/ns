package com.newsee.log.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LoginLogEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** appId */
	private String appId;
	
	/** 客户端类型 */
	private String appClientType;
	
	/** ip地址 */
	private String ip;
	
	/** 登录token */
	private String token;
	
	/** 登录时间 */
	private Date loginDate;
	
	/** 登录类型 login：登录，logout：登出*/
	private String loginType;
	
	/** 企业id */
	private Long enterpriseId;
	
	/** 公司id */
	private Long companyId;
	
	/** 组织id */
	private Long organizationId;
	
	/** 登录的用户id */
	private Long userId;
	
	/** 登录的用户名 */
	private String userName;
	
	/**登录返回的状态码*/
	private String errorCode;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppClientType() {
		return appClientType;
	}

	public void setAppClientType(String appClientType) {
		this.appClientType = appClientType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
