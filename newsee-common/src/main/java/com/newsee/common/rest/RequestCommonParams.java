package com.newsee.common.rest;

/**
 * 
 * 请求共通参数，请求参数类的父类
 * @author 肖斯斯  ADD ON 2017/08/11
 *
 */
public class RequestCommonParams {
	
	/**客户端类型，10:PC,20:IOS,30:安卓,40:微信*/
	private Integer clientType;
	
	/** 应用名称,目前只有carse */
	private String appName;

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
