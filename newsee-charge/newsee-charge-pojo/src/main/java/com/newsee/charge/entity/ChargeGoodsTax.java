/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 税目设置表
 * @version 1.0
 * @author
 */
public class ChargeGoodsTax implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键,1|number|0|0|1,1|text|0||1|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|1,1|text|0||1|1")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	/** 税目分类编码,1|text|0|200|1,1|text|1|numEn|1|0 */
	@ApiModelProperty(value = "税目分类编码,1|text|0|200|1,1|text|1|numEn|1|0")
	private String goodsTaxNo;
	
	/** 税目分类名称,1|text|0|200|2,1|text|1||2|0 */
	@ApiModelProperty(value = "税目分类名称,1|text|0|200|2,1|text|1||2|0")
	private String goodsTaxName;
	
	/** 创建人id */
	@ApiModelProperty(value = "创建人id")
	private Long createUserId;
	
	/** 创建人,1|text|0|150|3 */
	@ApiModelProperty(value = "创建人,1|text|0|150|3")
	private String createUserName;
	
	/** 创建时间,1|date|0|200|4 */
	@ApiModelProperty(value = "创建时间,1|date|0|200|4")
	private Date createTime;
	
	/** 更新人id */
	@ApiModelProperty(value = "更新人id")
	private Long updateUserId;
	
	/** 修改人,1|text|0|150|5 */
	@ApiModelProperty(value = "修改人,1|text|0|150|5")
	private String updateUserName;
	
	/** 修改时间,1|date|0|200|6 */
	@ApiModelProperty(value = "修改时间,1|date|0|200|6")
	private Date updateTime;
	
	/** sysTime */
	@ApiModelProperty(value = "sysTime")
	private Date sysTime;

	/**  逻辑删除 */
	@ApiModelProperty(value = "isDeleted")
	private Integer isDeleted;
	
		
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
	
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	public Long getOrganizationId() {
		return organizationId;
	}
	
	public void setGoodsTaxNo(String goodsTaxNo) {
		this.goodsTaxNo = goodsTaxNo;
	}
	
	public String getGoodsTaxNo() {
		return goodsTaxNo;
	}
	
	public void setGoodsTaxName(String goodsTaxName) {
		this.goodsTaxName = goodsTaxName;
	}
	
	public String getGoodsTaxName() {
		return goodsTaxName;
	}
	
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	
	public Long getCreateUserId() {
		return createUserId;
	}
	
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	public String getCreateUserName() {
		return createUserName;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	public Long getUpdateUserId() {
		return updateUserId;
	}
	
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	
	public String getUpdateUserName() {
		return updateUserName;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}
	
	public Date getSysTime() {
		return sysTime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
}
