/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 客户预收款余额表
 * @version 1.0
 * @author
 */
public class NspaymentPrePayment implements Serializable {
	
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
	
	/** 客户编号,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "客户编号,1|number|0|0|0,1|text|0||1|1")
	private Long customerId;
	
	/** 费用项目编号,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "费用项目编号,1|number|0|0|0,1|text|0||1|1")
	private Long chargeItemId;
	
	/** 累计存入,1|decimal|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "累计存入,1|decimal|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal addSum;
	
	/** 当前余额,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "当前余额,1|number|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal balance;
	
	/** 累计支出,1|decimal|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "累计支出,1|decimal|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal subtractSum;
	
	/** ,1|decimal|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|decimal|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal frozenHangSum;
	
	/** 房产id,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "房产id,1|number|0|0|0,1|text|0||1|1")
	private Long houseId;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long isPublic;
	
	/** ,1|decimal|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|decimal|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal virtualSum;
	
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
	
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	
	public void setChargeItemId(Long chargeItemId) {
		this.chargeItemId = chargeItemId;
	}
	
	public Long getChargeItemId() {
		return chargeItemId;
	}
	
	public void setAddSum(java.math.BigDecimal addSum) {
		this.addSum = addSum;
	}
	
	public java.math.BigDecimal getAddSum() {
		return addSum;
	}
	
	public void setBalance(java.math.BigDecimal balance) {
		this.balance = balance;
	}
	
	public java.math.BigDecimal getBalance() {
		return balance;
	}
	
	public void setSubtractSum(java.math.BigDecimal subtractSum) {
		this.subtractSum = subtractSum;
	}
	
	public java.math.BigDecimal getSubtractSum() {
		return subtractSum;
	}
	
	public void setFrozenHangSum(java.math.BigDecimal frozenHangSum) {
		this.frozenHangSum = frozenHangSum;
	}
	
	public java.math.BigDecimal getFrozenHangSum() {
		return frozenHangSum;
	}
	
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	
	public Long getHouseId() {
		return houseId;
	}
	
	public void setIsPublic(Long isPublic) {
		this.isPublic = isPublic;
	}
	
	public Long getIsPublic() {
		return isPublic;
	}
	
	public void setVirtualSum(java.math.BigDecimal virtualSum) {
		this.virtualSum = virtualSum;
	}
	
	public java.math.BigDecimal getVirtualSum() {
		return virtualSum;
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
