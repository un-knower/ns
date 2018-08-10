/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 权限表
 * @version 1.0
 * @author
 */
public class NsCorePermission implements Serializable {
	
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
	
	/** 权限ID */
	@ApiModelProperty(value = "权限ID")
	private String perid;
	
	/** 追加类型 */
	@ApiModelProperty(value = "追加类型")
	private String appendtype;
	
	/** 功能主键 */
	@ApiModelProperty(value = "功能主键")
	private String funcid;
	
	/** 所属模块 */
	@ApiModelProperty(value = "所属模块")
	private String module;
	
	/** 操作ID */
	@ApiModelProperty(value = "操作ID")
	private String operid;
	
	/** 权限编码 */
	@ApiModelProperty(value = "权限编码")
	private String permcode;
	
	/** 权限路径 */
	@ApiModelProperty(value = "权限路径")
	private String permpath;
	
	/** 权限类型 */
	@ApiModelProperty(value = "权限类型")
	private String permtype;
	
	/** 资源ID */
	@ApiModelProperty(value = "资源ID")
	private String resid;
	
		
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

    public void setPerid(String perid) {
		this.perid = perid;
	}
	
	public String getPerid() {
		return perid;
	}
	
	public void setAppendtype(String appendtype) {
		this.appendtype = appendtype;
	}
	
	public String getAppendtype() {
		return appendtype;
	}
	
	public void setFuncid(String funcid) {
		this.funcid = funcid;
	}
	
	public String getFuncid() {
		return funcid;
	}
	
	public void setModule(String module) {
		this.module = module;
	}
	
	public String getModule() {
		return module;
	}
	
	public void setOperid(String operid) {
		this.operid = operid;
	}
	
	public String getOperid() {
		return operid;
	}
	
	public void setPermcode(String permcode) {
		this.permcode = permcode;
	}
	
	public String getPermcode() {
		return permcode;
	}
	
	public void setPermpath(String permpath) {
		this.permpath = permpath;
	}
	
	public String getPermpath() {
		return permpath;
	}
	
	public void setPermtype(String permtype) {
		this.permtype = permtype;
	}
	
	public String getPermtype() {
		return permtype;
	}
	
	public void setResid(String resid) {
		this.resid = resid;
	}
	
	public String getResid() {
		return resid;
	}
	
}
