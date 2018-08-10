/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 票据使用详细信息表
 * @version 1.0
 * @author
 */
public class NsbillBillUsedDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 自增id */
	@ApiModelProperty(value = "自增id")
	private Long id;
	
	/** 使用记录编号 */
	@ApiModelProperty(value = "使用记录编号")
	private Long billUsedId;
	
	/** 科目编号 */
	@ApiModelProperty(value = "科目编号")
	private String subjectCode;
	
	/** 内容 */
	@ApiModelProperty(value = "内容")
	private String content;
	
	/** 费用类型ID */
	@ApiModelProperty(value = "费用类型ID")
	private Long chargeItemId;
	
	/** 业务单号Id */
	@ApiModelProperty(value = "业务单号Id")
	private Long businessId;
	
	/** 数量 */
	@ApiModelProperty(value = "数量")
	private BigDecimal amount;
	
	/** 单价 */
	@ApiModelProperty(value = "单价")
	private BigDecimal price;
	
	/** 该科目票据金额 */
	@ApiModelProperty(value = "该科目票据金额")
	private BigDecimal billFund;
	
	/** 备注说明 */
	@ApiModelProperty(value = "备注说明")
	private String remark;
	
	/** 更换票据时使用 */
	@ApiModelProperty(value = "更换票据时使用")
	private Long oldId;
	
	/** 开始时间 */
	@ApiModelProperty(value = "开始时间")
	private Date beginDate;
	
	/** 结束时间 */
	@ApiModelProperty(value = "结束时间")
	private Date endDate;
	
	/** chargeId */
	@ApiModelProperty(value = "chargeId")
	private Long chargeId;
	
	/** 客户id */
	@ApiModelProperty(value = "客户id")
	private Long customerId;
	
	/** 减免金额 */
	@ApiModelProperty(value = "减免金额")
	private BigDecimal discount;
	
	/** 房产id */
	@ApiModelProperty(value = "房产id")
	private Long houseId;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Long manualAutoId;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private String manualCustomerName;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private String manualHouseName;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Long manualSatelliteCardId;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Long meterId;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Date usedDate;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Long userId;
	
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
	
	/** 是否删除，0:未删除，1:删除 */
	@ApiModelProperty(value = "是否删除，0:未删除，1:删除")
	private Integer isDelete;
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setBillUsedId(Long billUsedId) {
		this.billUsedId = billUsedId;
	}
	
	public Long getBillUsedId() {
		return billUsedId;
	}
	
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setChargeItemId(Long chargeItemId) {
		this.chargeItemId = chargeItemId;
	}
	
	public Long getChargeItemId() {
		return chargeItemId;
	}
	
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	
	public Long getBusinessId() {
		return businessId;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setBillFund(BigDecimal billFund) {
		this.billFund = billFund;
	}
	
	public BigDecimal getBillFund() {
		return billFund;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setOldId(Long oldId) {
		this.oldId = oldId;
	}
	
	public Long getOldId() {
		return oldId;
	}
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public Date getBeginDate() {
		return beginDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}
	
	public Long getChargeId() {
		return chargeId;
	}
	
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	public BigDecimal getDiscount() {
		return discount;
	}
	
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	
	public Long getHouseId() {
		return houseId;
	}
	
	public void setManualAutoId(Long manualAutoId) {
		this.manualAutoId = manualAutoId;
	}
	
	public Long getManualAutoId() {
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
	
	public void setManualSatelliteCardId(Long manualSatelliteCardId) {
		this.manualSatelliteCardId = manualSatelliteCardId;
	}
	
	public Long getManualSatelliteCardId() {
		return manualSatelliteCardId;
	}
	
	public void setMeterId(Long meterId) {
		this.meterId = meterId;
	}
	
	public Long getMeterId() {
		return meterId;
	}
	
	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}
	
	public Date getUsedDate() {
		return usedDate;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getUserId() {
		return userId;
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
