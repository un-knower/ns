/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.vo;

import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import com.newsee.charge.entity.ChargeChargeStandardLadder;

import io.swagger.annotations.ApiModelProperty;

/**
 * StandardVo
 * @version 1.0
 * @author
 */
public class StandardVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
   private Long enterpriseId;
   
   private Long organizationId;
	
	/** 项目名称,1|text|0|250|1,1|label|0||2|0 */

	
	@ApiModelProperty(value = "项目名称,1|text|0|250|1,1|label|0||2|0")
	private String preinctName;
	
	private String unit;
	
	@ApiModelProperty(value = "项目ID")
	private Long preinctId;
	
	/** 收费科目id,,1|select|1||2|0 */
	@ApiModelProperty(value = "收费科目id,,1|select|1||2|0")
	private Long chargeItemId;
	

	@ApiModelProperty(value = "收费名称")
	private String  chargeItemName;
	
	/** 编码,1|text|0|0|250|2,1|text|1|numEn|2|0 */
	@ApiModelProperty(value = "编码,1|text|0|0|250|2,1|text|1|numEn|2|0")
	private String standardCode;
	
	/** 名称,1|text|0|250|3,1|text|1||3|0 */
	@ApiModelProperty(value = "名称,1|text|0|250|3,1|text|1||3|0")
	private String standardName;
	
	/** 分段单价,,1|radio|0||5|0 */
	@ApiModelProperty(value = "是否分段,,1|radio|0||5|0")
	private String isLadder;
	
	/** 分段单价list */
	@ApiModelProperty(value = "分段单价list")
	private List<ChargeChargeStandardLadder> standardLadderList;
	
	@ApiModelProperty(value = "计费公式")
	private String  expressions ;
	
	/** 实收精确到,,1|radio|1||7|0,1:分|2:角|3:元 */
	@ApiModelProperty(value = "实收精确到,,1|radio|1||7|0,1:分|2:角|3:元")
	private String chargeRoundType;
	
	/** 实收尾数,,1|radio|1||10|0,1:四舍五入|2:只舍不入|3:只入不舍|4:不做处理 */
	@ApiModelProperty(value = "实收尾数,,1|radio|1||10|0,1:四舍五入|2:只舍不入|3:只入不舍|4:不做处理")
	private String mantissa;
	
	/** 是否生效,1|select|0|100|8,1|radio|1||8|0,0:否|1:是 */
	@ApiModelProperty(value = "是否生效,1|select|0|100|8,1|radio|1||8|0,0:否|1:是")
	private String isExecuting;
	
	/** 生效时间,1|date|0|200|8,1|datepicker|1||8|0 */
	@ApiModelProperty(value = "生效开始时间,1|date|0|200|8,1|datepicker|1||8|0")
	private Date executeDate;
	
	/** 失效日期,1|date|0|200|9,1|datepicker|0||9|0 */
	@ApiModelProperty(value = "失效日期,1|date|0|200|9,1|datepicker|0||9|0")
	private Date cancelDate;
	
	
	/** 备注,,1|textarea|0||10|0 */
	@ApiModelProperty(value = "备注,,1|textarea|0||10|0")
	private String remark;
	
	/** 计费日期,1|select|0|200|11,1|select|0||11|0,1:按自然月|2:按计费周期 */
	@ApiModelProperty(value = "收费周期,1|select|0|200|11,1|select|0||11|0,1:按自然月|2:按计费周期")
	private String chargeType;
	
	private String   unitName;
	
	/** 计费周期拆分方式 */
	@ApiModelProperty(value = "计费周期拆分方式")
	private String chargeSplitType;
	
	
	/** 计算开始日期,1|select|0|200|15,1|radio|0||15|0,1:应收日期|1:计费结束日期|2:计费开始日期 */
	@ApiModelProperty(value = "计算开始日期,1|select|0|200|15,1|radio|0||15|0,1:应收日期|1:计费结束日期|2:计费开始日期")
	private String delayType;
	
	/** 应收月份,,1|radio|1||12|0,1:计费日期所在月|2:计费结束日期所在月 */
	@ApiModelProperty(value = "应收月份,,1|radio|1||12|0,1:计费日期所在月|2:计费结束日期所在月")
	private String chargeMonthType;
	
	
	
	/** 应收月份,,1|nubmertext|0||13|0 */
	@ApiModelProperty(value = "应收月份,,1|nubmertext|0||13|0")
	private String chargeMonth;
	
	
	/** 应收日,,1|radio|1||14|0,1:月末|2:当天|3:指定日期|4:计费开始日期后 */
	@ApiModelProperty(value = "应收日,,1|radio|1||14|0,1:月末|2:当天|3:指定日期|4:计费开始日期后")
	private String chargeDayType;
	
	/** 应收日,,1|numbertext|0||13|0 */
	@ApiModelProperty(value = "应收日,,1|numbertext|0||13|0")
	private String chargeDay;
	
	
	/** 财务账期,1|select|0|250|14,1|select|0||14|0,1:计费开始日期所在月|2:应收日期所在月 */
	@ApiModelProperty(value = "财务账期,1|select|0|250|14,1|select|0||14|0,1:计费开始日期所在月|2:应收日期所在月")
	private String accountType;
	
	/** 应收账期*/
	@ApiModelProperty(value = "应收账期")
	private String shouldType;
	
	
	/** 收取违约金,1|select|0|100|13,1|radio|0||13|0,0:否|1:是 */
	@ApiModelProperty(value = "收取违约金,1|select|0|100|13,1|radio|0||13|0,0:否|1:是")
	private String isDelay;
	
	@ApiModelProperty(value = "是否分段")
	private String  isDelayArea ;
	
	@ApiModelProperty(value = "违约分段")
	private List<ChargeChargeStandardLadder> delayList;
	
	@ApiModelProperty(value = "违约类型")
	private String delayTypeSelect;
	
	@ApiModelProperty(value = "违约天数")
	private String delayMount;
	
	@ApiModelProperty(value = "违约单位")
	private String delayUnit;
	
	
	
	/** 固定月数,,1|text|0||10|0 */
	@ApiModelProperty(value = "固定月数,,1|text|0||10|0")
	private String periodCount;
	
	/** 固定月数,,1|checkbox|1||11|0,1:是 */
	@ApiModelProperty(value = "固定月数,,1|checkbox|1||11|0,1:是")
	private String isFixInterval;
	
	
	/** 违约金比例,1|number|0|150|14,1|numbertext|0||14|0 */
	@ApiModelProperty(value = "违约金比例,1|number|0|150|14,1|numbertext|0||14|0")
	private String rate;
	
	/** 单价单位,,1|select|0||6|0,1:元|2:元/度|3:元/平分米.月|4:元/个.月|5:元/吨 */
	@ApiModelProperty(value = "单价单位,,1|select|0||6|0,1:元|2:元/度|3:元/平分米.月|4:元/个.月|5:元/吨")
	private String priceUnit;
	
	
	
	/** 单价,1|number|1|100|5,1|numbertext|0||5|0 */
	@ApiModelProperty(value = "单价,1|number|1|100|5,1|numbertext|0||5|0")
	private String price;
	
	/** 固定月份,,1|select|0||10|0,1:1月|2:2月|3:3月|4:4月|5:5月|6:6月|7:7月|8:8月|9:9月|10:10月|11:11月|12:12月 */
	@ApiModelProperty(value = "固定月份,,1|select|0||10|0,1:1月|2:2月|3:3月|4:4月|5:5月|6:6月|7:7月|8:8月|9:9月|10:10月|11:11月|12:12月")
	private String allMonth;
	
	/** 主键,1|text|0|0|1,1|text|0||1|1 */
	@ApiModelProperty(value = "主键,1|text|0|0|1,1|text|0||1|1")
	private Long id;
	
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
	
	
	private List<String> effectiveDate ;
	
	
    
	
	
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<String> getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(List<String> effectiveDate) {
		this.effectiveDate = effectiveDate;
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

	
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	
	public Long getPreinctId() {
		return preinctId;
	}

	public void setPreinctId(Long preinctId) {
		this.preinctId = preinctId;
	}

	public String getChargeItemName() {
		return chargeItemName;
	}

	public void setChargeItemName(String chargeItemName) {
		this.chargeItemName = chargeItemName;
	}


	public String getExpressions() {
		return expressions;
	}

	public void setExpressions(String expressions) {
		this.expressions = expressions;
	}

	public List<ChargeChargeStandardLadder> getDelayList() {
		return delayList;
	}

	public void setDelayList(List<ChargeChargeStandardLadder> delayList) {
		this.delayList = delayList;
	}


	


	public String getIsDelayArea() {
		return isDelayArea;
	}

	public void setIsDelayArea(String isDelayArea) {
		this.isDelayArea = isDelayArea;
	}

	public String getDelayTypeSelect() {
		return delayTypeSelect;
	}

	public void setDelayTypeSelect(String delayTypeSelect) {
		this.delayTypeSelect = delayTypeSelect;
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

	public String getIsLadder() {
		return isLadder;
	}

	public void setIsLadder(String isLadder) {
		this.isLadder = isLadder;
	}

	public List<ChargeChargeStandardLadder> getStandardLadderList() {
		return standardLadderList;
	}

	public void setStandardLadderList(List<ChargeChargeStandardLadder> standardLadderList) {
		this.standardLadderList = standardLadderList;
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

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getDelayType() {
		return delayType;
	}

	public void setDelayType(String delayType) {
		this.delayType = delayType;
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

	public String getDelayMount() {
		return delayMount;
	}

	public void setDelayMount(String delayMount) {
		this.delayMount = delayMount;
	}

	public String getDelayUnit() {
		return delayUnit;
	}

	public void setDelayUnit(String delayUnit) {
		this.delayUnit = delayUnit;
	}

	public String getPeriodCount() {
		return periodCount;
	}

	public void setPeriodCount(String periodCount) {
		this.periodCount = periodCount;
	}

	public String getIsFixInterval() {
		return isFixInterval;
	}

	public void setIsFixInterval(String isFixInterval) {
		this.isFixInterval = isFixInterval;
	}

	

	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}



	public String getAllMonth() {
		return allMonth;
	}

	public void setAllMonth(String allMonth) {
		this.allMonth = allMonth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
