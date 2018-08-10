/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 交账信息表
 * @version 1.0
 * @author
 */
public class NspaymentDayClosing implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 自增id */
	@ApiModelProperty(value = "自增id")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "组织id,1|number|0|0|0,1|text|0||1|1")
	private Long organizationId;
	
	/** 日结类型,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "日结类型,1|text|0|0|0,1|text|0||1|1")
	private String closingType;
	
	/** 项目,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "项目,1|number|0|0|0,1|text|0||1|1")
	private Long precinctId;
	
	/** 结账日,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "结账日,1|text|0|0|0,1|text|0||1|1")
	private String closingDay;
	
	/** 是否结账,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "是否结账,1|number|0|0|0,1|text|0||1|1")
	private Long isClosing;
	
	/** 创建人id */
	@ApiModelProperty(value = "创建人id")
	private Long createUserId;
	
	/** 创建人姓名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "创建人姓名,1|text|0|300|2,1|text|1||2|0")
	private String createUserName;
	
	/** 创建时间,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "创建时间,1|text|0|300|2,1|text|1||2|0")
	private Date createTime;
	
	/** 更新人id */
	@ApiModelProperty(value = "更新人id")
	private Long updateUserId;
	
	/** 更新人姓名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "更新人姓名,1|text|0|300|2,1|text|1||2|0")
	private String updateUserName;
	
	/** 更新时间,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "更新时间,1|text|0|300|2,1|text|1||2|0")
	private Date updateTime;
	
	/** 是否删除 */
	@ApiModelProperty(value = "是否删除")
	private Integer isDelete;
	
		
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
	
	public void setClosingType(String closingType) {
		this.closingType = closingType;
	}
	
	public String getClosingType() {
		return closingType;
	}
	
	public void setPrecinctId(Long precinctId) {
		this.precinctId = precinctId;
	}
	
	public Long getPrecinctId() {
		return precinctId;
	}
	
	public void setClosingDay(String closingDay) {
		this.closingDay = closingDay;
	}
	
	public String getClosingDay() {
		return closingDay;
	}
	
	public void setIsClosing(Long isClosing) {
		this.isClosing = isClosing;
	}
	
	public Long getIsClosing() {
		return isClosing;
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
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}
	
}
