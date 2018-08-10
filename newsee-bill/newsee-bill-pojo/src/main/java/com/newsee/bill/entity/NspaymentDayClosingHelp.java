/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 交账明细表
 * @version 1.0
 * @author
 */
public class NspaymentDayClosingHelp implements Serializable {
	
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
	
	/** ,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|text|0|0|0,1|text|0||1|1")
	private String sessionId;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long businessFlag;
	
	/** ,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|text|0|0|0,1|text|0||1|1")
	private String subjectCode;
	
	/** 客户ID,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "客户ID,1|number|0|0|0,1|text|0||1|1")
	private Long customerId;
	
	/** 房产ID,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "房产ID,1|number|0|0|0,1|text|0||1|1")
	private Long houseId;
	
	/** 项目ID,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "项目ID,1|number|0|0|0,1|text|0||1|1")
	private Long chargeItemId;
	
	/** ,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|text|0|0|0,1|text|0||1|1")
	private String squareTypeId;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long isBill;
	
	/** 票据类型,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "票据类型,1|text|0|0|0,1|text|0||1|1")
	private String billType;
	
	/** 票据号码,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "票据号码,1|text|0|0|0,1|text|0||1|1")
	private String billNum;
	
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
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setBusinessFlag(Long businessFlag) {
		this.businessFlag = businessFlag;
	}
	
	public Long getBusinessFlag() {
		return businessFlag;
	}
	
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}
	
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	
	public Long getHouseId() {
		return houseId;
	}
	
	public void setChargeItemId(Long chargeItemId) {
		this.chargeItemId = chargeItemId;
	}
	
	public Long getChargeItemId() {
		return chargeItemId;
	}
	
	public void setSquareTypeId(String squareTypeId) {
		this.squareTypeId = squareTypeId;
	}
	
	public String getSquareTypeId() {
		return squareTypeId;
	}
	
	public void setIsBill(Long isBill) {
		this.isBill = isBill;
	}
	
	public Long getIsBill() {
		return isBill;
	}
	
	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	public String getBillType() {
		return billType;
	}
	
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	
	public String getBillNum() {
		return billNum;
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
