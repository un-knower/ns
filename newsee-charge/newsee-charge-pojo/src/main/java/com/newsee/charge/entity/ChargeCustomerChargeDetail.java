/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 应收费用表
 * @version 1.0
 * @author
 */
public class ChargeCustomerChargeDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键,1|number|0|0|0 */
	@ApiModelProperty(value = "主键,1|number|0|0|0")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	 private Integer sequence;
	/** 任务id */
	@ApiModelProperty(value = "任务id")
	private Long taskId;
	
	/** 项目id */
	@ApiModelProperty(value = "项目id")
	private Long preinctId;
	
	/** 项目,1|text|0|200|1 */
	@ApiModelProperty(value = "项目,1|text|0|200|1")
	private String preinctName;
	
	/** 房间id */
	@ApiModelProperty(value = "房间id")
	private Long houseId;
	
	/** 房产简称,1|text|0|200|2,1|label|0||1|1 */
	@ApiModelProperty(value = "房产简称,1|text|0|200|2,1|label|0||1|1")
	private String houseName;
	
	/** 收费对象id */
	@ApiModelProperty(value = "收费对象id")
	private Long ownerId;
	
	/** 收费对象,1|text|0|150|3,1|label|0||2|0 */
	@ApiModelProperty(value = "收费对象,1|text|0|150|3,1|label|0||2|0")
	private String ownerName;
	
	/** 缴费对象类型 */
	@ApiModelProperty(value = "缴费对象类型")
	private Integer paidOwnerType;
	
	/** 收费科目id */
	@ApiModelProperty(value = "收费科目id")
	private Long chargeItemId;
	
	/** 收费科目,1|text|0|200|2,1|label|0||6|0 */
	@ApiModelProperty(value = "收费科目,1|text|0|200|2,1|label|0||6|0")
	private String chargeItemName;
	
	/** 收费标准id */
	@ApiModelProperty(value = "收费标准id")
	private Long chargeId;
	
	/** 收费标准,1|text|0|150|5,1|label|0||6|0 */
	@ApiModelProperty(value = "收费标准,1|text|0|150|5,1|label|0||6|0")
	private String chargeName;
	
	/** 单价，不含单位 */
	@ApiModelProperty(value = "单价，不含单位")
	private Double price;
	
	/** 单价,1|text|0|150|6,1|lable|0||8|0 */
	@ApiModelProperty(value = "单价,1|text|0|150|6,1|lable|0||8|0")
	private String chargeItemPrice;
	
	/** 面积/用量,1|number|0|150|7,1|label|0||9|0 */
	@ApiModelProperty(value = "面积/用量,1|number|0|150|7,1|label|0||9|0")
	private Double amount;
	
	/** 计费周期开始,1|date|0|150|8,1|label|0||4|0 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@ApiModelProperty(value = "计费周期开始,1|date|0|150|8,1|label|0||4|0")
	private Date calcStartDate;
	
	/** 计费周期结束,1|date|0|150|9,1|label|0||5|0 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@ApiModelProperty(value = "计费周期结束,1|date|0|150|9,1|label|0||5|0")
	private Date calcEndDate;
	
	/** 应收日期,1|date|0|150|9 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@ApiModelProperty(value = "应收日期,1|date|0|150|9")
	private Date shouldChargeDate;
	
	/** 本期应收,1|number|1|150|13,1|label|0||10|0 */
	@ApiModelProperty(value = "本期应收,1|number|1|150|13,1|label|0||10|0")
	private Double actualChargeSum;
	
	/** 应收金额 */
	@ApiModelProperty(value = "应收金额")
	private Double chargeSum;
	
	/** 实缴金额,1|number|1|150|11,1|label|0||11|0 */
	@ApiModelProperty(value = "实缴金额,1|number|1|150|11,1|label|0||11|0")
	private Double paidChargeSum;
	
	/** 本期欠费,1|number|1|150|13,1|label|0||13|0 */
	@ApiModelProperty(value = "本期欠费,1|number|1|150|13,1|label|0||13|0")
	private Long arrears;
	
	/** 减免金额,1|number|1|150|12,1|label|0||14|0 */
	@ApiModelProperty(value = "减免金额,1|number|1|150|12,1|label|0||14|0")
	private Double discount;
	
	/** 违约金额 */
	@ApiModelProperty(value = "违约金额")
	private Double delaySum;
	
	/** 减免滞纳金额 */
	@ApiModelProperty(value = "减免滞纳金额")
	private Double delayDiscount;
	
	/** 滞纳天数 */
	@ApiModelProperty(value = "滞纳天数")
	private Integer delayDays;
	
	/** 减免原因 */
	@ApiModelProperty(value = "减免原因")
	private String discountReason;
	
	@ApiModelProperty(value = "减免时间")
	private Date discountDate;

	@ApiModelProperty(value = "调整原因")
	private String description;
	
	/** 税额,1|number|1|150|14,1|label|0||15|0 */
	@ApiModelProperty(value = "税额,1|number|1|150|14,1|label|0||15|0")
	private Double tax;
	
	/** 审核状态,1|select|0|150|15,1|label|0||16|0,1:未审核|2:审核通过|3:审核不通过 */
	@ApiModelProperty(value = "审核状态,1|select|0|150|15,1|label|0||16|0,1:未审核|2:审核通过|3:审核不通过")
	private String isCheck;
	
	/** 不通过原因 */
	@ApiModelProperty(value = "不通过原因")
	private String notCheckReason;
	
	/** 是否已缴清 */
	@ApiModelProperty(value = "是否已缴清")
	private Integer isPaid;
	
	/** 缴费日期,,1|label|0||15|0 */
	@ApiModelProperty(value = "缴费日期,,1|label|0||15|0")
	private Date paidDate;
	
	/** 是否已月结 */
	@ApiModelProperty(value = "是否已月结")
	private Integer isClosing;
	
	/** 是否已坏账 */
	@ApiModelProperty(value = "是否已坏账")
	private Integer isBadDebt;
	
	/** 坏账时间 */
	@ApiModelProperty(value = "坏账时间")
	private Date badDebtDate;
	
	/** 费用产生方式 */
	@ApiModelProperty(value = "费用产生方式")
	private Integer chargeSource;
	
	/** 创建人id */
	@ApiModelProperty(value = "创建人id")
	private Long createUserId;
	
	/** 创建人姓名 */
	@ApiModelProperty(value = "创建人姓名")
	private String createUserName;
	
	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	
	/** 更新人id */
	@ApiModelProperty(value = "更新人id")
	private Long updateUserId;
	
	/** 更新人姓名 */
	@ApiModelProperty(value = "更新人姓名")
	private String updateUserName;
	
	/** 更新时间 */
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	
	/** 插入或者更新时间 */
	@ApiModelProperty(value = "插入或者更新时间")
	private Date sysTime;

	/** 计费周期 */
	@ApiModelProperty(value = "计费周期")
	private String chargeCycle;
 
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	
	
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Date getDiscountDate() {
		return discountDate;
	}

	public void setDiscountDate(Date discountDate) {
		this.discountDate = discountDate;
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
	
	public void setPreinctId(Long preinctId) {
		this.preinctId = preinctId;
	}
	
	public Long getPreinctId() {
		return preinctId;
	}
	
	public void setPreinctName(String preinctName) {
		this.preinctName = preinctName;
	}
	
	public String getPreinctName() {
		return preinctName;
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
	
	public void setPaidOwnerType(Integer paidOwnerType) {
		this.paidOwnerType = paidOwnerType;
	}
	
	public Integer getPaidOwnerType() {
		return paidOwnerType;
	}
	
	public void setChargeItemId(Long chargeItemId) {
		this.chargeItemId = chargeItemId;
	}
	
	public Long getChargeItemId() {
		return chargeItemId;
	}
	
	public void setChargeItemName(String chargeItemName) {
		this.chargeItemName = chargeItemName;
	}
	
	public String getChargeItemName() {
		return chargeItemName;
	}
	
	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}
	
	public Long getChargeId() {
		return chargeId;
	}
	
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	
	public String getChargeName() {
		return chargeName;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setChargeItemPrice(String chargeItemPrice) {
		this.chargeItemPrice = chargeItemPrice;
	}
	
	public String getChargeItemPrice() {
		return chargeItemPrice;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setCalcStartDate(Date calcStartDate) {
		this.calcStartDate = calcStartDate;
	}

	public Date getCalcStartDate() {
		return calcStartDate;
	}

	public void setCalcEndDate(Date calcEndDate) {
		this.calcEndDate = calcEndDate;
	}

	public Date getCalcEndDate() {
		return calcEndDate;
	}

	public void setShouldChargeDate(Date shouldChargeDate) {
		this.shouldChargeDate = shouldChargeDate;
	}
	
	public Date getShouldChargeDate() {
		return shouldChargeDate;
	}

	public Double getActualChargeSum() {
		return actualChargeSum;
	}

	public void setActualChargeSum(Double actualChargeSum) {
		this.actualChargeSum = actualChargeSum;
	}

	public Double getChargeSum() {
		return chargeSum;
	}

	public void setChargeSum(Double chargeSum) {
		this.chargeSum = chargeSum;
	}

	public Double getPaidChargeSum() {
		return paidChargeSum;
	}

	public void setPaidChargeSum(Double paidChargeSum) {
		this.paidChargeSum = paidChargeSum;
	}

	public void setArrears(Long arrears) {
		this.arrears = arrears;
	}
	
	public Long getArrears() {
		return arrears;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getDelaySum() {
		return delaySum;
	}

	public void setDelaySum(Double delaySum) {
		this.delaySum = delaySum;
	}

	public Double getDelayDiscount() {
		return delayDiscount;
	}

	public void setDelayDiscount(Double delayDiscount) {
		this.delayDiscount = delayDiscount;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public void setDelayDays(Integer delayDays) {
		this.delayDays = delayDays;
	}
	
	public Integer getDelayDays() {
		return delayDays;
	}
	
	public void setDiscountReason(String discountReason) {
		this.discountReason = discountReason;
	}
	
	public String getDiscountReason() {
		return discountReason;
	}
	
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	
	public String getIsCheck() {
		return isCheck;
	}
	
	public void setNotCheckReason(String notCheckReason) {
		this.notCheckReason = notCheckReason;
	}
	
	public String getNotCheckReason() {
		return notCheckReason;
	}
	
	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}
	
	public Integer getIsPaid() {
		return isPaid;
	}
	
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	
	public Date getPaidDate() {
		return paidDate;
	}
	
	public void setIsClosing(Integer isClosing) {
		this.isClosing = isClosing;
	}
	
	public Integer getIsClosing() {
		return isClosing;
	}
	
	public void setIsBadDebt(Integer isBadDebt) {
		this.isBadDebt = isBadDebt;
	}
	
	public Integer getIsBadDebt() {
		return isBadDebt;
	}
	
	public void setBadDebtDate(Date badDebtDate) {
		this.badDebtDate = badDebtDate;
	}
	
	public Date getBadDebtDate() {
		return badDebtDate;
	}
	
	public void setChargeSource(Integer chargeSource) {
		this.chargeSource = chargeSource;
	}
	
	public Integer getChargeSource() {
		return chargeSource;
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

	public String getChargeCycle() {
		return chargeCycle;
	}

	public void setChargeCycle(String chargeCycle) {
		this.chargeCycle = chargeCycle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
