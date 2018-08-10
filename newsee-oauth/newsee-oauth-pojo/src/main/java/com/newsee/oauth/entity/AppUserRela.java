package com.newsee.oauth.entity;

import java.io.Serializable;

public class AppUserRela implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1639584248420056054L;

	/** id */
    private Long id;

    /** 用户id */
    private Long userId;
    
    /** 给用户分配的appId */
    private String appId;
    
    /** 是否有效，1：有效，0：无效 */
    private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}