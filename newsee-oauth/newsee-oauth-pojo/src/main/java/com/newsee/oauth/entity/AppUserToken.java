package com.newsee.oauth.entity;

import java.io.Serializable;
import java.util.Date;

public class AppUserToken implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1178266156888886743L;

	/** id */
    private Long id;

    /** token */
    private String token;

    /** userId */
    private Long userId;

    /** ��Ӧ�÷����appId */
    private String appId;
    
    /** Ӧ�ÿͻ������� */
    private String appClientType;

    /** �Ƿ���Ч1:��Ч��0:��Ч*/
    private Integer tokenStatus;

    /** ����ʱ��  */
    private Date buildTime;

    /** ��ֹ��Чʱ�� */
    private Date expireTime;

    /** ��ip */
    private String bindIp;

    /**tokenˢ��key */
    private String refreshTokenKey;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getAppClientType() {
		return appClientType;
	}

	public void setAppClientType(String appClientType) {
		this.appClientType = appClientType;
	}


	public Date getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getBindIp() {
		return bindIp;
	}

	public void setBindIp(String bindIp) {
		this.bindIp = bindIp;
	}

	public String getRefreshTokenKey() {
		return refreshTokenKey;
	}

	public void setRefreshTokenKey(String refreshTokenKey) {
		this.refreshTokenKey = refreshTokenKey;
	}

	public Integer getTokenStatus() {
		return tokenStatus;
	}

	public void setTokenStatus(Integer tokenStatus) {
		this.tokenStatus = tokenStatus;
	}
    
}