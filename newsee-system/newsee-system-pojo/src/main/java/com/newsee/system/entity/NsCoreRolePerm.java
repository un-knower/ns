/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色_权限
 * @version 1.0
 * @author
 */
public class NsCoreRolePerm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键ID */
	@ApiModelProperty(value = "主键ID")
	private Long id;
	
	/** 所属企业ID */
	@ApiModelProperty(value = "所属企业ID")
	private Long enterpriseId;
	
	/** 所属组织ID */
	@ApiModelProperty(value = "所属组织ID")
	private Long organizationId;
	
	/** 启用 */
	@ApiModelProperty(value = "启用")
	private String enabled;
	
	/** 权限ID */
	@ApiModelProperty(value = "权限ID")
	private String perid;
	
	/** 角色ID */
	@ApiModelProperty(value = "角色ID")
	private String roleid;
	
	/** 类型 */
	@ApiModelProperty(value = "类型")
	private String type;
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	public String getEnabled() {
		return enabled;
	}
	
	public void setPerid(String perid) {
		this.perid = perid;
	}
	
	public String getPerid() {
		return perid;
	}
	
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	public String getRoleid() {
		return roleid;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
}
