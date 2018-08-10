package com.newsee.oauth.entity;

import java.io.Serializable;

public class AppClient implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2060680026037705674L;

	/**id */
    private Long id;

    /** ��app������id */
    private String appId;
    
    /** �ͻ�������*/
    private String appClientType;

    /** ��APP�ͻ��˷����token������Կ  */
    private String appSecret;

    /** �Ƿ���Ч��1����Ч��0����Ч */
    private Integer clientStatus;

    /** token��Чʱ�� */
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