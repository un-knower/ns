/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 收费标准表
 * @version 1.0
 * @author
 */
public class ChargeChargeStandard implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键,1|text|0|0|1,1|text|0||1|1 */
	@ApiModelProperty(value = "主键,1|text|0|0|1,1|text|0||1|1")
	private Long id;
	@ApiModelProperty(value = "违约单位")
	private String delayUnit;
	
	@ApiModelProperty(value = "违约类型")
	private String delayTypeSelect;
	
	
	@ApiModelProperty(value = "是否分段")
	private String  isDelayArea ;
	
	@ApiModelProperty(value = "违约天数")
	private String delayMount;
	
	@ApiModelProperty(value = "计费周期拆分方式")
	private String chargeSplitType;
	
	@ApiModelProperty(value = "应收账期")
	private String shouldType;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	/** 编码,1|text|0|0|250|2,1|text|1|numEn|2|0 */
	@ApiModelProperty(value = "编码,1|text|0|0|250|2,1|text|1|numEn|2|0")
	private String standardCode;
	
	/** 名称,1|text|0|250|3,1|text|1||3|0 */
	@ApiModelProperty(value = "名称,1|text|0|250|3,1|text|1||3|0")
	private String standardName;
	
	/** 项目id */
	@ApiModelProperty(value = "项目id")
	private Long preinctId;
	
	/** 项目名称,1|text|0|250|1,1|label|0||2|0 */
	@ApiModelProperty(value = "项目名称,1|text|0|250|1,1|label|0||2|0")
	private String preinctName;
	
	/** 收费科目id,,1|select|1||2|0 */
	@ApiModelProperty(value = "收费科目id,,1|select|1||2|0")
	private Long chargeItemId;
	
	/** 收费科目名称 */
	@ApiModelProperty(value = "收费科目名称")
	private String chargeItemName;
	
	/** 分段单价,,1|radio|0||5|0 */
	@ApiModelProperty(value = "分段单价,,1|radio|0||5|0")
	private String isLadder;
	
	/** 计算公式 */
	@ApiModelProperty(value = "计算公式")
	private String expressions;
	
	/** 单价,1|number|1|100|5,1|numbertext|0||5|0 */
	@ApiModelProperty(value = "单价,1|number|1|100|5,1|numbertext|0||5|0")
	private String price;
	
	/** 单价单位,,1|select|0||6|0,1:元|2:元/度|3:元/平分米.月|4:元/个.月|5:元/吨 */
	@ApiModelProperty(value = "单价单位,,1|select|0||6|0,1:元|2:元/度|3:元/平分米.月|4:元/个.月|5:元/吨")
	private String priceUnit;
	
	/** 计算精度 */
	@ApiModelProperty(value = "计算精度")
	private String decimalLength;
	
	private String unit;
	/** 计算尾数 */
	@ApiModelProperty(value = "计算尾数")
	private String decimalTailLength;
	
	/** 实收精确到,,1|radio|1||7|0,1:分|2:角|3:元 */
	@ApiModelProperty(value = "实收精确到,,1|radio|1||7|0,1:分|2:角|3:元")
	private String chargeRoundType;
	
	/** 实收尾数,,1|radio|1||10|0,1:四舍五入|2:只舍不入|3:只入不舍|4:不做处理 */
	@ApiModelProperty(value = "实收尾数,,1|radio|1||10|0,1:四舍五入|2:只舍不入|3:只入不舍|4:不做处理")
	private String mantissa;
	
	/** 分成数额 */
	@ApiModelProperty(value = "分成数额")
	private Long divideNumber;
	
	/** 分成单位 */
	@ApiModelProperty(value = "分成单位")
	private String divideUnit;
	
	/** 是否生效,1|select|0|100|8,1|radio|1||8|0,0:否|1:是 */
	@ApiModelProperty(value = "是否生效,1|select|0|100|8,1|radio|1||8|0,0:否|1:是")
	private String isExecuting;
	
	/** 生效时间,1|date|0|200|8,1|datepicker|1||8|0 */
	@ApiModelProperty(value = "生效时间,1|date|0|200|8,1|datepicker|1||8|0")
	private Date executeDate;
	
	/** 失效日期,1|date|0|200|9,1|datepicker|0||9|0 */
	@ApiModelProperty(value = "失效日期,1|date|0|200|9,1|datepicker|0||9|0")
	private Date cancelDate;
	
	/** 备注,,1|textarea|0||10|0 */
	@ApiModelProperty(value = "备注,,1|textarea|0||10|0")
	private String remark;
	
	/** 固定月数,,1|checkbox|1||11|0,1:是 */
	@ApiModelProperty(value = "固定月数,,1|checkbox|1||11|0,1:是")
	private String isFixInterval;
	
	/** 固定月数,,1|text|0||10|0 */
	@ApiModelProperty(value = "固定月数,,1|text|0||10|0")
	private String periodCount;
	
	/** 固定月份,,1|select|0||10|0,1:1月|2:2月|3:3月|4:4月|5:5月|6:6月|7:7月|8:8月|9:9月|10:10月|11:11月|12:12月 */
	@ApiModelProperty(value = "固定月份,,1|select|0||10|0,1:1月|2:2月|3:3月|4:4月|5:5月|6:6月|7:7月|8:8月|9:9月|10:10月|11:11月|12:12月")
	private String allMonth;
	
	/** 计费日期,1|select|0|200|11,1|select|0||11|0,1:按自然月|2:按计费周期 */
	@ApiModelProperty(value = "计费日期,1|select|0|200|11,1|select|0||11|0,1:按自然月|2:按计费周期")
	private String chargeType;
	
	/** 应收月份,,1|radio|1||12|0,1:计费日期所在月|2:计费结束日期所在月 */
	@ApiModelProperty(value = "应收月份,,1|radio|1||12|0,1:计费日期所在月|2:计费结束日期所在月")
	private String chargeMonthType;
	
	/** 应收月份,,1|nubmertext|0||13|0 */
	@ApiModelProperty(value = "应收月份,,1|nubmertext|0||13|0")
	private String chargeMonth;
	
	/** 应收日,,1|radio|1||14|0,0:月初|1:月末|2:指定日期|3:计费开始日期后 */
	@ApiModelProperty(value = "应收日,,1|radio|1||14|0,0:月初|1:月末|2:指定日期|3:计费开始日期后")
	private String chargeDayType;
	
	/** 应收日,,1|numbertext|0||13|0 */
	@ApiModelProperty(value = "应收日,,1|numbertext|0||13|0")
	private String chargeDay;
	
	/** 财务账期,1|select|0|250|14,1|select|0||14|0,1:计费开始日期所在月|2:应收日期所在月 */
	@ApiModelProperty(value = "财务账期,1|select|0|250|14,1|select|0||14|0,1:计费开始日期所在月|2:应收日期所在月")
	private String accountType;
	
	/** 收取违约金,1|select|0|100|13,1|radio|0||13|0,0:否|1:是 */
	@ApiModelProperty(value = "收取违约金,1|select|0|100|13,1|radio|0||13|0,0:否|1:是")
	private String isDelay;
	
	/** 违约金比例,1|number|0|150|14,1|numbertext|0||14|0 */
	@ApiModelProperty(value = "违约金比例,1|number|0|150|14,1|numbertext|0||14|0")
	private String rate;
	
	/** 计算开始日期,1|select|0|200|15,1|radio|0||15|0,1:应收日期|1:计费结束日期|2:计费开始日期 */
	@ApiModelProperty(value = "计算开始日期,1|select|0|200|15,1|radio|0||15|0,1:应收日期|1:计费结束日期|2:计费开始日期")
	private String delayType;
	
	/** 违约金计算日期加的天数 */
	@ApiModelProperty(value = "违约金计算日期加的天数")
	private String isDelayDays;
	
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
	
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	private List<String> effectiveDate ;
	
	public List<String> getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(List<String> effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	
	public String getDelayUnit() {
		return delayUnit;
	}

	public void setDelayUnit(String delayUnit) {
		this.delayUnit = delayUnit;
	}

	public String getDelayTypeSelect() {
		return delayTypeSelect;
	}

	public void setDelayTypeSelect(String delayTypeSelect) {
		this.delayTypeSelect = delayTypeSelect;
	}

	public String getIsDelayArea() {
		return isDelayArea;
	}

	public void setIsDelayArea(String isDelayArea) {
		this.isDelayArea = isDelayArea;
	}

	public String getDelayMount() {
		return delayMount;
	}

	public void setDelayMount(String delayMount) {
		this.delayMount = delayMount;
	}

	public String getChargeSplitType() {
		return chargeSplitType;
	}

	public void setChargeSplitType(String chargeSplitType) {
		this.chargeSplitType = chargeSplitType;
	}

	public String getShouldType() {
		return shouldType;
	}

	public void setShouldType(String shouldType) {
		this.shouldType = shouldType;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public Long getPreinctId() {
		return preinctId;
	}

	public void setPreinctId(Long preinctId) {
		this.preinctId = preinctId;
	}

	public String getPreinctName() {
		return preinctName;
	}

	public void setPreinctName(String preinctName) {
		this.preinctName = preinctName;
	}

	public Long getChargeItemId() {
		return chargeItemId;
	}

	public void setChargeItemId(Long chargeItemId) {
		this.chargeItemId = chargeItemId;
	}

	public String getChargeItemName() {
		return chargeItemName;
	}

	public void setChargeItemName(String chargeItemName) {
		this.chargeItemName = chargeItemName;
	}

	public String getIsLadder() {
		return isLadder;
	}

	public void setIsLadder(String isLadder) {
		this.isLadder = isLadder;
	}

	public String getExpressions() {
		return expressions;
	}

	public void setExpressions(String expressions) {
		this.expressions = expressions;
	}


	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public String getDecimalLength() {
		return decimalLength;
	}

	public void setDecimalLength(String decimalLength) {
		this.decimalLength = decimalLength;
	}

	public String getDecimalTailLength() {
		return decimalTailLength;
	}

	public void setDecimalTailLength(String decimalTailLength) {
		this.decimalTailLength = decimalTailLength;
	}

	public String getChargeRoundType() {
		return chargeRoundType;
	}

	public void setChargeRoundType(String chargeRoundType) {
		this.chargeRoundType = chargeRoundType;
	}

	public String getMantissa() {
		return mantissa;
	}

	public void setMantissa(String mantissa) {
		this.mantissa = mantissa;
	}

	public Long getDivideNumber() {
		return divideNumber;
	}

	public void setDivideNumber(Long divideNumber) {
		this.divideNumber = divideNumber;
	}

	public String getDivideUnit() {
		return divideUnit;
	}

	public void setDivideUnit(String divideUnit) {
		this.divideUnit = divideUnit;
	}

	public String getIsExecuting() {
		return isExecuting;
	}

	public void setIsExecuting(String isExecuting) {
		this.isExecuting = isExecuting;
	}

	public Date getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsFixInterval() {
		return isFixInterval;
	}

	public void setIsFixInterval(String isFixInterval) {
		this.isFixInterval = isFixInterval;
	}

	public String getPeriodCount() {
		return periodCount;
	}

	public void setPeriodCount(String periodCount) {
		this.periodCount = periodCount;
	}

	public String getAllMonth() {
		return allMonth;
	}

	public void setAllMonth(String allMonth) {
		this.allMonth = allMonth;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeMonthType() {
		return chargeMonthType;
	}

	public void setChargeMonthType(String chargeMonthType) {
		this.chargeMonthType = chargeMonthType;
	}

	public String getChargeMonth() {
		return chargeMonth;
	}

	public void setChargeMonth(String chargeMonth) {
		this.chargeMonth = chargeMonth;
	}

	public String getChargeDayType() {
		return chargeDayType;
	}

	public void setChargeDayType(String chargeDayType) {
		this.chargeDayType = chargeDayType;
	}

	public String getChargeDay() {
		return chargeDay;
	}

	public void setChargeDay(String chargeDay) {
		this.chargeDay = chargeDay;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}


	public String getDelayType() {
		return delayType;
	}

	public void setDelayType(String delayType) {
		this.delayType = delayType;
	}

	public String getIsDelayDays() {
		return isDelayDays;
	}

	public void setIsDelayDays(String isDelayDays) {
		this.isDelayDays = isDelayDays;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getSysTime() {
		return sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}

	
	
}
