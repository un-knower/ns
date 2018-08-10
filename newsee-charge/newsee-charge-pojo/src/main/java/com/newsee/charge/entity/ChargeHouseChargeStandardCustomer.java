/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;

/**
 * 房产收费标准客户表
 * @version 1.0
 * @author
 */
public class ChargeHouseChargeStandardCustomer implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** id */
	@ApiModelProperty(value = "id")
	private Long id;
	
	/** enterpriseId */
	@ApiModelProperty(value = "enterpriseId")
	private Long enterpriseId;
	
	/** organizationId */
	@ApiModelProperty(value = "organizationId")
	private Long organizationId;
	
	/** houseChargeId */
	@ApiModelProperty(value = "houseChargeId")
	private Long houseChargeId;
	
	/** houseId */
	@ApiModelProperty(value = "houseId")
	private Long houseId;
	
	/** ownerId */
	@ApiModelProperty(value = "ownerId")
	private Long ownerId;
	
	/** ownerName */
	@ApiModelProperty(value = "ownerName")
	private String ownerName;
	
	/** ownerType */
	@ApiModelProperty(value = "ownerType")
	private Integer ownerType;
	
	/** bearRatio */
	@ApiModelProperty(value = "bearRatio")
	private String bearRatio;
	
	/** remark */
	@ApiModelProperty(value = "remark")
	private String remark;
	
	/** createUserId */
	@ApiModelProperty(value = "createUserId")
	private Long createUserId;
	
	/** 创建人姓名 */
	@ApiModelProperty(value = "创建人姓名")
	private String createUserName;
	
	/** createTime */
	@ApiModelProperty(value = "createTime")
	private Date createTime;
	
	/** updateUserId */
	@ApiModelProperty(value = "updateUserId")
	private Long updateUserId;
	
	/** updateUserName */
	@ApiModelProperty(value = "updateUserName")
	private String updateUserName;
	
	/** updateTime */
	@ApiModelProperty(value = "updateTime")
	private Date updateTime;
	
	/** sysTime */
	@ApiModelProperty(value = "sysTime")
	private Date sysTime;

	@ApiModelProperty(value = "逻辑删除，0:未删除 1:删除")
	private Integer isDelete;

	private Map<String, Object> relativeCustomerMap;
	
		
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
	
	public void setHouseChargeId(Long houseChargeId) {
		this.houseChargeId = houseChargeId;
	}
	
	public Long getHouseChargeId() {
		return houseChargeId;
	}
	
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	
	public Long getHouseId() {
		return houseId;
	}
	
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	
	public Long getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}
	
	public Integer getOwnerType() {
		return ownerType;
	}
	
	
	public String getBearRatio() {
		return bearRatio;
	}

	public void setBearRatio(String bearRatio) {
		this.bearRatio = bearRatio;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Map<String, Object> getRelativeCustomerMap() {
		return relativeCustomerMap;
	}

	public void setRelativeCustomerMap(Map<String, Object> relativeCustomerMap) {
		this.relativeCustomerMap = relativeCustomerMap;
	}
}
