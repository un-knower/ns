package com.newsee.oauth.entity;

import java.io.Serializable;

public class AppClient implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2060680026037705674L;

	/**id */
    private Long id;

    /** 给app分批的id */
    private String appId;
    
    /** 客户端类型*/
    private String appClientType;

    /** 给APP客户端分配的token加密秘钥  */
    private String appSecret;

    /** 是否有效，1：有效，0：无效 */
    private Integer clientStatus;

    /** token有效时长 */
    private Long timeMillis;

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

	public String getAppClientType() {
		return appClientType;
	}

	public void setAppClientType(String appClientType) {
		this.appClientType = appClientType;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}


	public Long getTimeMillis() {
		return timeMillis;
	}

	public void setTimeMillis(Long timeMillis) {
		this.timeMillis = timeMillis;
	}

	public Integer getClientStatus() {
		return clientStatus;
	}

	public void setClientStatus(Integer clientStatus) {
		this.clientStatus = clientStatus;
	}
    
}