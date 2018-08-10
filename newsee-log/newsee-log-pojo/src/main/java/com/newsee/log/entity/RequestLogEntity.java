package com.newsee.log.entity;

import java.io.Serializable;
import java.util.Date;

public class RequestLogEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3634744887808049057L;

	/** 请求用户id */
	private Long userId;
	
	/** 请求用户名 */
	private String userName;
	
	/** ip地址 */
	private String ipAddress;
	
	/** app名称，目前只有carse*/
	private String appName;
	
	/** 客户端名称，IE+版本号，Chorme+版本号等浏览器终端名称,操作系统等名称*/
	private String agentName;
	
	/** 请求路径 */
	private String requestPath;
	
	/** 请求用户参数 */
	private String requestParams;
	
	/** 请求时间 */
	private Date requestDate;
	
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
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	public String getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(String requestParams) {
		this.requestParams = requestParams;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
}
