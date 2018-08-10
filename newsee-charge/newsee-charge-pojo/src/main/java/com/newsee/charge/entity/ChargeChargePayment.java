/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 已缴款表
 * @version 1.0
 * @author
 */
public class ChargeChargePayment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** id */
	@ApiModelProperty(value = "id")
	private Long id;
	
	/** chargeDetailId */
	@ApiModelProperty(value = "chargeDetailId")
	private Long chargeDetailId;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	/** 房产id */
	@ApiModelProperty(value = "房产id")
	private Long houseId;
	
	/** 房产名称 */
	@ApiModelProperty(value = "房产名称")
	private String houseName;
	
	/** 是否被冲正 */
	@ApiModelProperty(value = "是否被冲正")
	private Integer isCanceled;
	
	/** 收费标准id */
	@ApiModelProperty(value = "收费标准id")
	private Long chargeId;
	
	/** 上次余额 */
	@ApiModelProperty(value = "上次余额")
	private Long preCharge;
	
	/** 本次缴款费用 */
	@ApiModelProperty(value = "本次缴款费用")
	private Long chargePaid;
	
	/** 本次应收款缴款额不包括滞纳金 */
	@ApiModelProperty(value = "本次应收款缴款额不包括滞纳金")
	private Long shouldPaid;
	
	/** 本次折扣 */
	@ApiModelProperty(value = "本次折扣")
	private Long disCount;
	
	/** 本次该缴滞纳金 */
	@ApiModelProperty(value = "本次该缴滞纳金")
	private Long delaySum;
	
	/** 本次滞纳金减免 */
	@ApiModelProperty(value = "本次滞纳金减免")
	private Long delayDisCount;
	
	/** 本次算滞纳金日期 */
	@ApiModelProperty(value = "本次算滞纳金日期")
	private Date delayDate;
	
	/** 本次缴款日期 */
	@ApiModelProperty(value = "本次缴款日期")
	private Date operatorDate;
	
	/** 审核状态 */
	@ApiModelProperty(value = "审核状态")
	private Integer isCheck;
	
	/** 审核人用户id */
	@ApiModelProperty(value = "审核人用户id")
	private Long checkUserId;
	
	/** checkUserName */
	@ApiModelProperty(value = "checkUserName")
	private String checkUserName;
	
	/** 审核时间 */
	@ApiModelProperty(value = "审核时间")
	private Date checkDate;
	
	/** 创建人用户id */
	@ApiModelProperty(value = "创建人用户id")
	private Long createUserId;
	
	/** 创建人姓名 */
	@ApiModelProperty(value = "创建人姓名")
	private String createUserName;
	
	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	
	/** 插入或者更新时间 */
	@ApiModelProperty(value = "插入或者更新时间")
	private Date sysTime;
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setChargeDetailId(Long chargeDetailId) {
		this.chargeDetailId = chargeDetailId;
	}
	
	public Long getChargeDetailId() {
		return chargeDetailId;
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
	
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	
	public Long getHouseId() {
		return houseId;
	}
	
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	
	public String getHouseName() {
		return houseName;
	}
	
	public void setIsCanceled(Integer isCanceled) {
		this.isCanceled = isCanceled;
	}
	
	public Integer getIsCanceled() {
		return isCanceled;
	}
	
	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}
	
	public Long getChargeId() {
		return chargeId;
	}
	
	public void setPreCharge(Long preCharge) {
		this.preCharge = preCharge;
	}
	
	public Long getPreCharge() {
		return preCharge;
	}
	
	public void setChargePaid(Long chargePaid) {
		this.chargePaid = chargePaid;
	}
	
	public Long getChargePaid() {
		return chargePaid;
	}
	
	public void setShouldPaid(Long shouldPaid) {
		this.shouldPaid = shouldPaid;
	}
	
	public Long getShouldPaid() {
		return shouldPaid;
	}
	
	public void setDisCount(Long disCount) {
		this.disCount = disCount;
	}
	
	public Long getDisCount() {
		return disCount;
	}
	
	public void setDelaySum(Long delaySum) {
		this.delaySum = delaySum;
	}
	
	public Long getDelaySum() {
		return delaySum;
	}
	
	public void setDelayDisCount(Long delayDisCount) {
		this.delayDisCount = delayDisCount;
	}
	
	public Long getDelayDisCount() {
		return delayDisCount;
	}
	
	public void setDelayDate(Date delayDate) {
		this.delayDate = delayDate;
	}
	
	public Date getDelayDate() {
		return delayDate;
	}
	
	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}
	
	public Date getOperatorDate() {
		return operatorDate;
	}
	
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	
	public Integer getIsCheck() {
		return isCheck;
	}
	
	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}
	
	public Long getCheckUserId() {
		return checkUserId;
	}
	
	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	
	public String getCheckUserName() {
		return checkUserName;
	}
	
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	public Date getCheckDate() {
		return checkDate;
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
	
	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}
	
	public Date getSysTime() {
		return sysTime;
	}
	
}
