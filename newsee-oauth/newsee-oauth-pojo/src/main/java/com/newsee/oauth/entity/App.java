package com.newsee.oauth.entity;

import java.io.Serializable;

public class App implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6263625340305132673L;

	/** id */
    private Long id;

    /** 给应用分配的appId */
    private String appId;
    
    /** 未加密的appId */
    private String appIdSource;
    
    /** 应用名称*/
    private String appName;

    /** 是否有效，1：有效，0：无效*/
    private Integer appStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(Integer appStatus) {
		this.appStatus = appStatus;
	}

	public String getAppIdSource() {
		return appIdSource;
	}

	public void setAppIdSource(String appIdSource) {
		this.appIdSource = appIdSource;
	}

}