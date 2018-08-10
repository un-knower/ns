/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 客户缴款明细表
 * @version 1.0
 * @author
 */
public class NspaymentChargePayment implements Serializable {
	
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
	
	/** 项目编号,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "项目编号,1|number|0|0|0,1|text|0||1|1")
	private Long precinctId;
	
	/** 客户编号,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "客户编号,1|number|0|0|0,1|text|0||1|1")
	private Long customerId;
	
	/** 房产编号,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "房产编号,1|number|0|0|0,1|text|0||1|1")
	private Long houseId;
	
	/** 仪表编号,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "仪表编号,1|number|0|0|0,1|text|0||1|1")
	private Long meterId;
	
	/** 收费科目,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "收费科目,1|number|0|0|0,1|text|0||1|1")
	private Long chargeItemId;
	
	/** 收费标准,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "收费标准,1|number|0|0|0,1|text|0||1|1")
	private Long chargeId;
	
	/** 应收款编号,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "应收款编号,1|number|0|0|0,1|text|0||1|1")
	private Long chargeDetailId;
	
	/** 是否被冲正,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "是否被冲正,1|number|0|0|0,1|text|0||1|1")
	private Long isCanceled;
	
	/** 被冲正的凭证号,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "被冲正的凭证号,1|number|0|0|0,1|text|0||1|1")
	private Long refPaymentId;
	
	/** 票据号码,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据号码,1|text|0|300|2,1|text|1||2|0")
	private String billNo;
	
	/** 票据代码,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据代码,1|text|0|300|2,1|text|1||2|0")
	private String billCode;
	
	/** 业务标志,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "业务标志,1|text|0|300|2,1|text|1||2|0")
	private String subjectCode;
	
	/** 上次余额,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "上次余额,1|number|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal preCharge;
	
	/** 本次缴款金额,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "本次缴款金额,1|number|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal chargePaid;
	
	/** 本次应收款缴款额,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "本次应收款缴款额,1|number|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal shouldPaid;
	
	/** 本次折扣,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "本次折扣,1|number|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal disCount;
	
	/** 本次该缴滞纳金,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "本次该缴滞纳金,1|number|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal delaySum;
	
	/** 本次滞纳金减免,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "本次滞纳金减免,1|number|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal delayDisCount;
	
	/** 本次缴款日期,1|date|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "本次缴款日期,1|date|0|0|0,1|text|0||1|1")
	private Date delayDate;
	
	/** 本次缴款日期,1|date|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "本次缴款日期,1|date|0|0|0,1|text|0||1|1")
	private Date operatorDate;
	
	/** 结算方式,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "结算方式,1|text|0|300|2,1|text|1||2|0")
	private String squareTypeId;
	
	/** 实际发生日期,1|date|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "实际发生日期,1|date|0|0|0,1|text|0||1|1")
	private Date realDoDate;
	
	/** 是否已经审核过,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "是否已经审核过,1|number|0|0|0,1|text|0||1|1")
	private Long isCheck;
	
	/** 审核人编号,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "审核人编号,1|number|0|0|0,1|text|0||1|1")
	private Long checkUserId;
	
	/** 审核日期,1|date|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "审核日期,1|date|0|0|0,1|text|0||1|1")
	private Date checkDate;
	
	/** 备注,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "备注,1|text|0|0|0,1|text|0||1|1")
	private String remark;
	
	/** 账期,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "账期,1|number|0|0|0,1|text|0||1|1")
	private Long accountBook;
	
	/** 数量,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "数量,1|number|0|0|0,1|text|0||1|1")
	private Long amount;
	
	/** 票据类型,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "票据类型,1|text|0|0|0,1|text|0||1|1")
	private String billType;
	
	/** 支票号,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "支票号,1|text|0|0|0,1|text|0||1|1")
	private String cheque;
	
	/** 交账日期,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "交账日期,1|text|0|0|0,1|text|0||1|1")
	private String closingDay;
	
	/** 交账操作员,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "交账操作员,1|text|0|0|0,1|text|0||1|1")
	private Long dayClosingUserId;
	
	/** 记载应收款减免的编号,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "记载应收款减免的编号,1|text|0|0|0,1|text|0||1|1")
	private Long discountId;
	
	/** 减免类型,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "减免类型,1|text|0|0|0,1|text|0||1|1")
	private String discountType;
	
	/** 是否交账,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "是否交账,1|text|0|0|0,1|text|0||1|1")
	private Long isDayClosing;
	
	/** 是否入账,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "是否入账,1|text|0|0|0,1|text|0||1|1")
	private Long isEnterAccount;
	
	/** ,1|date|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|date|0|0|0,1|text|0||1|1")
	private Date enterAccountDate;
	
	/** ,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|number|0|0|0,1|text|0||1|1")
	private Long enterAccountUserId;
	
	/** ,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|text|0|0|0,1|text|0||1|1")
	private String manualAutoId;
	
	/** ,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|text|0|0|0,1|text|0||1|1")
	private String manualCustomerName;
	
	/** ,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|text|0|0|0,1|text|0||1|1")
	private String manualHouseName;
	
	/** ,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = ",1|text|0|0|0,1|text|0||1|1")
	private String manualSatelliteCardId;
	
	/** 原属账期,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "原属账期,1|number|0|0|0,1|text|0||1|1")
	private Long oldAccountBook;
	
	/** 当退款，红冲的时候，这里表示有效的,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "当退款，红冲的时候，这里表示有效的,1|text|0|0|0,1|text|0||1|1")
	private java.math.BigDecimal validCharge;
	
	/** 凭证批量id,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "凭证批量id,1|number|0|0|0,1|text|0||1|1")
	private Long voucherBatchId;
	
	/** 违约金明细ID,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "违约金明细ID,1|number|0|0|0,1|text|0||1|1")
	private Long delayDetailId;
	
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
	
	public void setPrecinctId(Long precinctId) {
		this.precinctId = precinctId;
	}
	
	public Long getPrecinctId() {
		return precinctId;
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
	
	public void setMeterId(Long meterId) {
		this.meterId = meterId;
	}
	
	public Long getMeterId() {
		return meterId;
	}
	
	public void setChargeItemId(Long chargeItemId) {
		this.chargeItemId = chargeItemId;
	}
	
	public Long getChargeItemId() {
		return chargeItemId;
	}
	
	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}
	
	public Long getChargeId() {
		return chargeId;
	}
	
	public void setChargeDetailId(Long chargeDetailId) {
		this.chargeDetailId = chargeDetailId;
	}
	
	public Long getChargeDetailId() {
		return chargeDetailId;
	}
	
	public void setIsCanceled(Long isCanceled) {
		this.isCanceled = isCanceled;
	}
	
	public Long getIsCanceled() {
		return isCanceled;
	}
	
	public void setRefPaymentId(Long refPaymentId) {
		this.refPaymentId = refPaymentId;
	}
	
	public Long getRefPaymentId() {
		return refPaymentId;
	}
	
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	public String getBillNo() {
		return billNo;
	}
	
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	
	public String getBillCode() {
		return billCode;
	}
	
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}
	
	public void setPreCharge(java.math.BigDecimal preCharge) {
		this.preCharge = preCharge;
	}
	
	public java.math.BigDecimal getPreCharge() {
		return preCharge;
	}
	
	public void setChargePaid(java.math.BigDecimal chargePaid) {
		this.chargePaid = chargePaid;
	}
	
	public java.math.BigDecimal getChargePaid() {
		return chargePaid;
	}
	
	public void setShouldPaid(java.math.BigDecimal shouldPaid) {
		this.shouldPaid = shouldPaid;
	}
	
	public java.math.BigDecimal getShouldPaid() {
		return shouldPaid;
	}
	
	public void setDisCount(java.math.BigDecimal disCount) {
		this.disCount = disCount;
	}
	
	public java.math.BigDecimal getDisCount() {
		return disCount;
	}
	
	public void setDelaySum(java.math.BigDecimal delaySum) {
		this.delaySum = delaySum;
	}
	
	public java.math.BigDecimal getDelaySum() {
		return delaySum;
	}
	
	public void setDelayDisCount(java.math.BigDecimal delayDisCount) {
		this.delayDisCount = delayDisCount;
	}
	
	public java.math.BigDecimal getDelayDisCount() {
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
	
	public void setSquareTypeId(String squareTypeId) {
		this.squareTypeId = squareTypeId;
	}
	
	public String getSquareTypeId() {
		return squareTypeId;
	}
	
	public void setRealDoDate(Date realDoDate) {
		this.realDoDate = realDoDate;
	}
	
	public Date getRealDoDate() {
		return realDoDate;
	}
	
	public void setIsCheck(Long isCheck) {
		this.isCheck = isCheck;
	}
	
	public Long getIsCheck() {
		return isCheck;
	}
	
	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}
	
	public Long getCheckUserId() {
		return checkUserId;
	}
	
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	public Date getCheckDate() {
		return checkDate;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setAccountBook(Long accountBook) {
		this.accountBook = accountBook;
	}
	
	public Long getAccountBook() {
		return accountBook;
	}
	
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public Long getAmount() {
		return amount;
	}
	
	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	public String getBillType() {
		return billType;
	}
	
	public void setCheque(String cheque) {
		this.cheque = cheque;
	}
	
	public String getCheque() {
		return cheque;
	}
	
	public void setClosingDay(String closingDay) {
		this.closingDay = closingDay;
	}
	
	public String getClosingDay() {
		return closingDay;
	}
	
	public void setDayClosingUserId(Long dayClosingUserId) {
		this.dayClosingUserId = dayClosingUserId;
	}
	
	public Long getDayClosingUserId() {
		return dayClosingUserId;
	}
	
	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}
	
	public Long getDiscountId() {
		return discountId;
	}
	
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
	public String getDiscountType() {
		return discountType;
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
	
	public void setEnterAccountDate(Date enterAccountDate) {
		this.enterAccountDate = enterAccountDate;
	}
	
	public Date getEnterAccountDate() {
		return enterAccountDate;
	}
	
	public void setEnterAccountUserId(Long enterAccountUserId) {
		this.enterAccountUserId = enterAccountUserId;
	}
	
	public Long getEnterAccountUserId() {
		return enterAccountUserId;
	}
	
	public void setManualAutoId(String manualAutoId) {
		this.manualAutoId = manualAutoId;
	}
	
	public String getManualAutoId() {
		return manualAutoId;
	}
	
	public void setManualCustomerName(String manualCustomerName) {
		this.manualCustomerName = manualCustomerName;
	}
	
	public String getManualCustomerName() {
		return manualCustomerName;
	}
	
	public void setManualHouseName(String manualHouseName) {
		this.manualHouseName = manualHouseName;
	}
	
	public String getManualHouseName() {
		return manualHouseName;
	}
	
	public void setManualSatelliteCardId(String manualSatelliteCardId) {
		this.manualSatelliteCardId = manualSatelliteCardId;
	}
	
	public String getManualSatelliteCardId() {
		return manualSatelliteCardId;
	}
	
	public void setOldAccountBook(Long oldAccountBook) {
		this.oldAccountBook = oldAccountBook;
	}
	
	public Long getOldAccountBook() {
		return oldAccountBook;
	}
	
	public void setValidCharge(java.math.BigDecimal validCharge) {
		this.validCharge = validCharge;
	}
	
	public java.math.BigDecimal getValidCharge() {
		return validCharge;
	}
	
	public void setVoucherBatchId(Long voucherBatchId) {
		this.voucherBatchId = voucherBatchId;
	}
	
	public Long getVoucherBatchId() {
		return voucherBatchId;
	}
	
	public void setDelayDetailId(Long delayDetailId) {
		this.delayDetailId = delayDetailId;
	}
	
	public Long getDelayDetailId() {
		return delayDetailId;
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
