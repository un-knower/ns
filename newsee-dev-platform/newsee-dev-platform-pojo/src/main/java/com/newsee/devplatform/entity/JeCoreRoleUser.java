/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色_人员
 * @version 1.0
 * @author
 */
public class JeCoreRoleUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 角色ID */
	@ApiModelProperty(value = "角色ID")
	private String roleid;
	
	/** 人员ID */
	@ApiModelProperty(value = "人员ID")
	private String userid;
	
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	public String getRoleid() {
		return roleid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUserid() {
		return userid;
	}
	
}
