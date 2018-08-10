/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * ChargeCustomerChargeCalcPlanChargeItem
 * @version 1.0
 * @author
 */
public class ChargeCustomerChargeCalcPlanChargeItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** id */
	@ApiModelProperty(value = "id")
	private Long id;
	
	/** 计划id */
	@ApiModelProperty(value = "计划id")
	private Long planId;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	/** 收费科目id */
	@ApiModelProperty(value = "收费科目id")
	private Long chargeItem;
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	
	public Long getPlanId() {
		return planId;
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
	
	public void setChargeItem(Long chargeItem) {
		this.chargeItem = chargeItem;
	}
	
	public Long getChargeItem() {
		return chargeItem;
	}
	
}
