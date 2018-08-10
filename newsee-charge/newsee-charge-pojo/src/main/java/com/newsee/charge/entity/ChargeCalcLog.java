/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 计算费用日志表
 * @version 1.0
 * @author
 */
public class ChargeCalcLog implements Serializable {
	
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
	
	/** 任务Id */
	@ApiModelProperty(value = "任务Id")
	private Long taskId;
	
	/** 描述 */
	@ApiModelProperty(value = "描述")
	private String description;
	
	/** 金额 */
	@ApiModelProperty(value = "金额")
	private Double amount;
	
	/** 是否成功，0:成功，1:失败 */
	@ApiModelProperty(value = "是否成功，0:成功，1:失败")
	private Integer isSuccess;
	
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
	
	/** 是否逻辑删除，0:未删除，1:删除 */
	@ApiModelProperty(value = "是否逻辑删除，0:未删除，1:删除")
	private Integer isDelete;
	
	
	private Integer sequence;
	  
	private Long houseId;
	
	private  Long precintId;
	
	private String houseName;
	
	private String preintName;
	
	private Long chargeItemId;
	  
	private Date chargeStartTime;
	
	private Date chargeEndTime;
	
	private Long chargeCustomId;
	
	private String chargeCustomName;

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public Long getPrecintId() {
		return precintId;
	}

	public void setPrecintId(Long precintId) {
		this.precintId = precintId;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getPreintName() {
		return preintName;
	}

	public void setPreintName(String preintName) {
		this.preintName = preintName;
	}

	public Long getChargeItemId() {
		return chargeItemId;
	}

	public void setChargeItemId(Long chargeItemId) {
		this.chargeItemId = chargeItemId;
	}

	public Date getChargeStartTime() {
		return chargeStartTime;
	}

	public void setChargeStartTime(Date chargeStartTime) {
		this.chargeStartTime = chargeStartTime;
	}

	public Date getChargeEndTime() {
		return chargeEndTime;
	}

	public void setChargeEndTime(Date chargeEndTime) {
		this.chargeEndTime = chargeEndTime;
	}

	public Long getChargeCustomId() {
		return chargeCustomId;
	}

	public void setChargeCustomId(Long chargeCustomId) {
		this.chargeCustomId = chargeCustomId;
	}

	public String getChargeCustomName() {
		return chargeCustomName;
	}

	public void setChargeCustomName(String chargeCustomName) {
		this.chargeCustomName = chargeCustomName;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

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
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public Integer getIsSuccess() {
		return isSuccess;
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
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}
	
}
