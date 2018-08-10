/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.newsee.charge.entity.ChargeChargeStandard;
import io.swagger.annotations.ApiModelProperty;

/**
 * PaymentVo
 * @version 1.0
 * @author
 */
public class PaymentVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 单价,1|text|0|150|6,1|lable|0||8|0 */
	@ApiModelProperty(value = "单价,1|text|0|150|6,1|lable|0||8|0")
	private String chargeItemPrice;

	/** 收费对象id */
	@ApiModelProperty(value = "收费对象id")
	private Long ownerId;

	/** 收费对象,1|text|0|150|3,1|label|0||2|0 */
	@ApiModelProperty(value = "收费对象,1|text|0|150|3,1|label|0||2|0")
	private String ownerName;

	/** 缴费对象类型 */
	@ApiModelProperty(value = "缴费对象类型")
	private Integer paidOwnerType;
	
	/** 减免金额,1|number|1|150|12,1|label|0||14|0 */
	@ApiModelProperty(value = "减免金额,1|number|1|150|12,1|label|0||14|0")
	private Double discount;
	
	/** 房产简称,1|text|0|200|2,1|label|0||1|1 */
	@ApiModelProperty(value = "房产简称,1|text|0|200|2,1|label|0||1|1")
	private String houseName;
	
	/** 面积/用量,1|number|0|150|7,1|label|0||9|0 */
	@ApiModelProperty(value = "面积/用量,1|number|0|150|7,1|label|0||9|0")
	private Double amount;
	
	/** 计费周期开始,1|date|0|150|8,1|label|0||4|0 */
	@ApiModelProperty(value = "计费周期开始,1|date|0|150|8,1|label|0||4|0")
	private Date calcStartDate;
	
	/** 应收日期,1|date|0|150|9 */
	@ApiModelProperty(value = "应收日期,1|date|0|150|9")
	private Date shouldChargeDate;
	
	/** 项目,1|text|0|200|1 */
	@ApiModelProperty(value = "项目,1|text|0|200|1")
	private String preinctName;
	
	/** 实缴金额,1|number|1|150|11,1|label|0||11|0 */
	@ApiModelProperty(value = "实缴金额,1|number|1|150|11,1|label|0||11|0")
	private Long paidChargeSum;

	/** 收费标准id */
	@ApiModelProperty(value = "收费标准id")
	private Long chargeId;

	/** 收费标准,1|text|0|150|5,1|label|0||6|0 */
	@ApiModelProperty(value = "收费标准,1|text|0|150|5,1|label|0||6|0")
	private String chargeName;
	
	/** 税额,1|number|1|150|14,1|label|0||15|0 */
	@ApiModelProperty(value = "税额,1|number|1|150|14,1|label|0||15|0")
	private Long tax;
	
	/** 本期欠费,1|number|1|150|13,1|label|0||13|0 */
	@ApiModelProperty(value = "本期欠费,1|number|1|150|13,1|label|0||13|0")
	private Long arrears;
	
	/** 计费周期结束,1|date|0|150|9,1|label|0||5|0 */
	@ApiModelProperty(value = "计费周期结束,1|date|0|150|9,1|label|0||5|0")
	private Date calcEndDate;
	
	/** 缴费日期,,1|label|0||15|0 */
	@ApiModelProperty(value = "缴费日期,,1|label|0||15|0")
	private Date paidDate;
	
	/** 收费科目,1|text|0|200|2,1|label|0||6|0 */
	@ApiModelProperty(value = "收费科目,1|text|0|200|2,1|label|0||6|0")
	private String chargeItemName;
	
	/** 应收金额,1|number|1|150|13,1|label|0||10|0 */
	@ApiModelProperty(value = "应收金额,1|number|1|150|13,1|label|0||10|0")
	private Double ChargeSum;
	
	/** 本期应收,1|number|1|150|13,1|label|0||10|0 */
	@ApiModelProperty(value = "本期应收,1|number|1|150|13,1|label|0||10|0")
	private Double actualChargeSum;
	
	/** 主键,1|number|0|0|0 */
	@ApiModelProperty(value = "主键,1|number|0|0|0")
	private Long id;
	
	/** 审核状态,1|select|0|150|15,1|label|0||16|0,1:未审核|2:审核通过|3:审核不通过 */
	@ApiModelProperty(value = "审核状态,1|select|0|150|15,1|label|0||16|0,1:未审核|2:审核通过|3:审核不通过")
	private String isCheck;

	/** 备注 */
	@ApiModelProperty(value = "调整原因")
	private String description;

	private List<ChargeChargeStandard> standardList;
	
	
	public Double getChargeSum() {
		return ChargeSum;
	}

	public void setChargeSum(Double chargeSum) {
		ChargeSum = chargeSum;
	}

	public void setChargeItemPrice(String chargeItemPrice) {
		this.chargeItemPrice = chargeItemPrice;
	}
	
	public String getChargeItemPrice() {
		return chargeItemPrice;
	}
	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	public Double getDiscount() {
		return discount;
	}
	
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	
	public String getHouseName() {
		return houseName;
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
	
	public void setShouldChargeDate(Date shouldChargeDate) {
		this.shouldChargeDate = shouldChargeDate;
	}
	
	public Date getShouldChargeDate() {
		return shouldChargeDate;
	}
	
	public void setPreinctName(String preinctName) {
		this.preinctName = preinctName;
	}
	
	public String getPreinctName() {
		return preinctName;
	}
	
	public void setPaidChargeSum(Long paidChargeSum) {
		this.paidChargeSum = paidChargeSum;
	}
	
	public Long getPaidChargeSum() {
		return paidChargeSum;
	}
	
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	
	public String getChargeName() {
		return chargeName;
	}
	
	public void setTax(Long tax) {
		this.tax = tax;
	}
	
	public Long getTax() {
		return tax;
	}
	
	public void setArrears(Long arrears) {
		this.arrears = arrears;
	}
	
	public Long getArrears() {
		return arrears;
	}
	
	public void setCalcEndDate(Date calcEndDate) {
		this.calcEndDate = calcEndDate;
	}
	
	public Date getCalcEndDate() {
		return calcEndDate;
	}
	
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	
	public Date getPaidDate() {
		return paidDate;
	}
	
	public void setChargeItemName(String chargeItemName) {
		this.chargeItemName = chargeItemName;
	}
	
	public String getChargeItemName() {
		return chargeItemName;
	}
	
	public void setActualChargeSum(Double actualChargeSum) {
		this.actualChargeSum = actualChargeSum;
	}
	
	public Double getActualChargeSum() {
		return actualChargeSum;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	
	public String getIsCheck() {
		return isCheck;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getPaidOwnerType() {
		return paidOwnerType;
	}

	public void setPaidOwnerType(Integer paidOwnerType) {
		this.paidOwnerType = paidOwnerType;
	}

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ChargeChargeStandard> getStandardList() {
		return standardList;
	}

	public void setStandardList(List<ChargeChargeStandard> standardList) {
		this.standardList = standardList;
	}
}
