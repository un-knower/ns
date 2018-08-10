/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 应收款调整记录
 * @version 1.0
 * @author
 */
public class ChargeDiscountDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@ApiModelProperty(value = "主键")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	/** 应收款id */
	@ApiModelProperty(value = "应收款id")
	private Long chargeDetailId;
	
	/** 调整前的面积或者用量 */
	@ApiModelProperty(value = "调整前的面积或者用量")
	private Double oldAmount;
	
	/** 调整后的面积或用量 */
	@ApiModelProperty(value = "调整后的面积或用量")
	private Double newAmount;
	
	/** 调整前应收款 */
	@ApiModelProperty(value = "调整前应收款")
	private Double oldShouldChargeSum;
	
	/** 调整后应收款 */
	@ApiModelProperty(value = "调整后应收款")
	private Double newShouldChargeSum;
	
	/** 调整前应收日期 */
	@ApiModelProperty(value = "调整前应收日期")
	private Date oldShouldChargeDate;
	
	/** 调整后应收日期 */
	@ApiModelProperty(value = "调整后应收日期")
	private Date newShouldChargeDay;
	
	/** 创建人id */
	@ApiModelProperty(value = "创建人id")
	private Long createUserId;
	
	/** 创建人姓名 */
	@ApiModelProperty(value = "创建人姓名")
	private String createUserName;
	
	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	
		
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
	
	public void setChargeDetailId(Long chargeDetailId) {
		this.chargeDetailId = chargeDetailId;
	}
	
	public Long getChargeDetailId() {
		return chargeDetailId;
	}

	public Double getOldAmount() {
		return oldAmount;
	}

	public void setOldAmount(Double oldAmount) {
		this.oldAmount = oldAmount;
	}

	public Double getNewAmount() {
		return newAmount;
	}

	public void setNewAmount(Double newAmount) {
		this.newAmount = newAmount;
	}

	public Double getOldShouldChargeSum() {
		return oldShouldChargeSum;
	}

	public void setOldShouldChargeSum(Double oldShouldChargeSum) {
		this.oldShouldChargeSum = oldShouldChargeSum;
	}

	public Double getNewShouldChargeSum() {
		return newShouldChargeSum;
	}

	public void setNewShouldChargeSum(Double newShouldChargeSum) {
		this.newShouldChargeSum = newShouldChargeSum;
	}

	public void setOldShouldChargeDate(Date oldShouldChargeDate) {
		this.oldShouldChargeDate = oldShouldChargeDate;
	}
	
	public Date getOldShouldChargeDate() {
		return oldShouldChargeDate;
	}
	
	public void setNewShouldChargeDay(Date newShouldChargeDay) {
		this.newShouldChargeDay = newShouldChargeDay;
	}
	
	public Date getNewShouldChargeDay() {
		return newShouldChargeDay;
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
	
}
