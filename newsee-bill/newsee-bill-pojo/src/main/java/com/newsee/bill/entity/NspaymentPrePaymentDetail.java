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
public class NspaymentPrePaymentDetail implements Serializable {
	
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
	
	/** 业务标志,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "业务标志,1|text|0|0|0,1|text|0||1|1")
	private String subjectCode;
	
	/** 结算方式,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "结算方式,1|number|0|0|0,1|text|0||1|1")
	private String squareTypeId;
	
	/** 预收款前余额,1|decimal|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "预收款前余额,1|decimal|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal beforeBalance;
	
	/** 当前发生金额,1|decimal|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "当前发生金额,1|decimal|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal occurBalance;
	
	/** 后余额,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "后余额,1|number|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal afterBalance;
	
	/** 对应的缴款明细编号,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "对应的缴款明细编号,1|number|0|0|0,1|text|0||1|1")
	private Long chargePaymentId;
	
	/** 操作时间,1|date|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "操作时间,1|date|0|0|0,1|text|0||1|1")
	private Date operatorDate;
	
	/** 凭证前缀,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "凭证前缀,1|text|0|0|0,1|text|0||1|1")
	private String prefix;
	
	/** 编号,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "编号,1|text|0|0|0,1|text|0||1|1")
	private String number;
	
	/** 凭证后缀,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "凭证后缀,1|text|0|0|0,1|text|0||1|1")
	private String postfix;
	
	/** 操作人员编号,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "操作人员编号,1|number|0|0|0,1|text|0||1|1")
	private Long userId;
	
	/** 备注,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "备注,1|text|0|0|0,1|text|0||1|1")
	private String remark;
	
	/** 是否取消,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "是否取消,1|number|0|0|0,1|text|0||1|1")
	private Long isCanceled;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long linkId;
	
	/** 账期,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "账期,1|number|0|0|0,1|text|0||1|1")
	private Long accountBook;
	
	/** ,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|text|0|0|0,1|text|0||1|1")
	private String billNum;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private String billType;
	
	/** ,1|date|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|date|0|0|0,1|text|0||1|1")
	private Date checkDate;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long checkUserId;
	
	/** 日结日,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "日结日,1|text|0|0|0,1|text|0||1|1")
	private String closingDay;
	
	/** ,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|text|0|0|0,1|text|0||1|1")
	private String dailyClosingDay;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long dayClosingUserId;
	
	/** 房产id,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "房产id,1|number|0|0|0,1|text|0||1|1")
	private Long houseId;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long isCheck;
	
	/** 是否月结,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "是否月结,1|number|0|0|0,1|text|0||1|1")
	private Long isClosing;
	
	/** 是否日结,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "是否日结,1|number|0|0|0,1|text|0||1|1")
	private Long isDayClosing;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long isEnterAccount;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long linkChargeDetailId;
	
	/** 原属账期,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "原属账期,1|number|0|0|0,1|text|0||1|1")
	private Long oldAccountBook;
	
	/** 管理处,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "管理处,1|number|0|0|0,1|text|0||1|1")
	private Long precinctId;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long refPrePaymentId;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long transId;
	
	/** ,1|decimal|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|decimal|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal virtualBalance;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long voucherBatchId;
	
	/** ,1|date|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = ",1|date|0|300|2,1|text|1||2|0")
	private Date realDoDate;
	
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
	
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}
	
	public void setSquareTypeId(String squareTypeId) {
		this.squareTypeId = squareTypeId;
	}
	
	public String getSquareTypeId() {
		return squareTypeId;
	}
	
	public void setBeforeBalance(java.math.BigDecimal beforeBalance) {
		this.beforeBalance = beforeBalance;
	}
	
	public java.math.BigDecimal getBeforeBalance() {
		return beforeBalance;
	}
	
	public void setOccurBalance(java.math.BigDecimal occurBalance) {
		this.occurBalance = occurBalance;
	}
	
	public java.math.BigDecimal getOccurBalance() {
		return occurBalance;
	}
	
	public void setAfterBalance(java.math.BigDecimal afterBalance) {
		this.afterBalance = afterBalance;
	}
	
	public java.math.BigDecimal getAfterBalance() {
		return afterBalance;
	}
	
	public void setChargePaymentId(Long chargePaymentId) {
		this.chargePaymentId = chargePaymentId;
	}
	
	public Long getChargePaymentId() {
		return chargePaymentId;
	}
	
	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}
	
	public Date getOperatorDate() {
		return operatorDate;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}
	
	public String getPostfix() {
		return postfix;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setIsCanceled(Long isCanceled) {
		this.isCanceled = isCanceled;
	}
	
	public Long getIsCanceled() {
		return isCanceled;
	}
	
	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}
	
	public Long getLinkId() {
		return linkId;
	}
	
	public void setAccountBook(Long accountBook) {
		this.accountBook = accountBook;
	}
	
	public Long getAccountBook() {
		return accountBook;
	}
	
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	
	public String getBillNum() {
		return billNum;
	}
	
	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	public String getBillType() {
		return billType;
	}
	
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	public Date getCheckDate() {
		return checkDate;
	}
	
	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}
	
	public Long getCheckUserId() {
		return checkUserId;
	}
	
	public void setClosingDay(String closingDay) {
		this.closingDay = closingDay;
	}
	
	public String getClosingDay() {
		return closingDay;
	}
	
	public void setDailyClosingDay(String dailyClosingDay) {
		this.dailyClosingDay = dailyClosingDay;
	}
	
	public String getDailyClosingDay() {
		return dailyClosingDay;
	}
	
	public void setDayClosingUserId(Long dayClosingUserId) {
		this.dayClosingUserId = dayClosingUserId;
	}
	
	public Long getDayClosingUserId() {
		return dayClosingUserId;
	}
	
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	
	public Long getHouseId() {
		return houseId;
	}
	
	public void setIsCheck(Long isCheck) {
		this.isCheck = isCheck;
	}
	
	public Long getIsCheck() {
		return isCheck;
	}
	
	public void setIsClosing(Long isClosing) {
		this.isClosing = isClosing;
	}
	
	public Long getIsClosing() {
		return isClosing;
	}
	
	public void setIsDayClosing(Long isDayClosing) {
		this.isDayClosing = isDayClosing;
	}
	
	public Long getIsDayClosing() {
		return isDayClosing;
	}
	
	public void setIsEnterAccount(Long isEnterAccount) {
		this.isEnterAccount = isEnterAccount;
	}
	
	public Long getIsEnterAccount() {
		return isEnterAccount;
	}
	
	public void setLinkChargeDetailId(Long linkChargeDetailId) {
		this.linkChargeDetailId = linkChargeDetailId;
	}
	
	public Long getLinkChargeDetailId() {
		return linkChargeDetailId;
	}
	
	public void setOldAccountBook(Long oldAccountBook) {
		this.oldAccountBook = oldAccountBook;
	}
	
	public Long getOldAccountBook() {
		return oldAccountBook;
	}
	
	public void setPrecinctId(Long precinctId) {
		this.precinctId = precinctId;
	}
	
	public Long getPrecinctId() {
		return precinctId;
	}
	
	public void setRefPrePaymentId(Long refPrePaymentId) {
		this.refPrePaymentId = refPrePaymentId;
	}
	
	public Long getRefPrePaymentId() {
		return refPrePaymentId;
	}
	
	public void setTransId(Long transId) {
		this.transId = transId;
	}
	
	public Long getTransId() {
		return transId;
	}
	
	public void setVirtualBalance(java.math.BigDecimal virtualBalance) {
		this.virtualBalance = virtualBalance;
	}
	
	public java.math.BigDecimal getVirtualBalance() {
		return virtualBalance;
	}
	
	public void setVoucherBatchId(Long voucherBatchId) {
		this.voucherBatchId = voucherBatchId;
	}
	
	public Long getVoucherBatchId() {
		return voucherBatchId;
	}
	
	public void setRealDoDate(Date realDoDate) {
		this.realDoDate = realDoDate;
	}
	
	public Date getRealDoDate() {
		return realDoDate;
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
