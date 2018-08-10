/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 应收款计算计划-收费科目
 * @version 1.0
 * @author
 */
public class ChargeCustomerChargeCalcTaskChargeItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@ApiModelProperty(value = "主键")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	/** 任务id */
	@ApiModelProperty(value = "任务id")
	private Long taskId;
	
	/** 收费项目id */
	@ApiModelProperty(value = "收费项目id")
	private Long chargeItem;

	/** 收费项目名称 */
	@ApiModelProperty(value = "收费项目名称")
	private String chargeItemName;
	
		
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
	
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	public Long getTaskId() {
		return taskId;
	}
	
	public void setChargeItem(Long chargeItem) {
		this.chargeItem = chargeItem;
	}
	
	public Long getChargeItem() {
		return chargeItem;
	}

	public String getChargeItemName() {
		return chargeItemName;
	}

	public void setChargeItemName(String chargeItemName) {
		this.chargeItemName = chargeItemName;
	}
}
