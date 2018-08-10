/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.newsee.charge.entity.ChargeHouseChargeStandardCustomer;

import io.swagger.annotations.ApiModelProperty;

/**
 * HouseStandardVo
 * @version 1.0
 * @author
 */
public class HouseStandardVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 主键,1|text|0|0|0 */
	@ApiModelProperty(value = "主键,1|text|0|0|0")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	/** 收费科目id */
	@ApiModelProperty(value = "收费科目id")
	private Long chargeItemId;
	
	/** 收费科目,1|text|0|250|4 */
	@ApiModelProperty(value = "收费科目,1|text|0|250|4")
	private String chargeItemName;
	
	/** 收费标准id */
	@ApiModelProperty(value = "收费标准id")
	private Long standardId;
	
	/** 收费表混 */
	@ApiModelProperty(value = "收费表混")
	private String standardName;
	
	/** 单价,1|text|0|200|5 */
	@ApiModelProperty(value = "单价,1|text|0|200|5")
	private String price;
	
	/** 收费项目id */
	@ApiModelProperty(value = "收费项目id")
	private Long chargeId;
	
	/** 项目id */
	@ApiModelProperty(value = "项目id")
	private Long preinctId;
	
	/** 项目名称,1|text|0|200|1 */
	@ApiModelProperty(value = "项目名称,1|text|0|200|1")
	private String preinctName;
	
	/** houseId */
	@ApiModelProperty(value = "houseId")
	private Long houseId;
	
	/** 房产简称,1|text|0|200|2 */
	@ApiModelProperty(value = "房产简称,1|text|0|200|2")
	private String houseName;
	
	/** 计费开始日期,1|date|0|250|6 */
	@ApiModelProperty(value = "计费开始日期,1|date|0|250|6")
	private Date chargeStartTime;
	
	/** 计费结束日期,1|date|0|250|7 */
	@ApiModelProperty(value = "计费结束日期,1|date|0|250|7")
	private Date chargeEndTime;
	
	/** 审核状态,1|select|0|250|8,,0:未审核|1:审核通过|2:审核不通过 */
	@ApiModelProperty(value = "审核状态,1|select|0|250|8,,0:未审核|1:审核通过|2:审核不通过")
	private Integer auditStatus;
	
	/** 审核是否通过*/
	@ApiModelProperty(value = "审核是否通过，true:通过，false：不通过")
	private Boolean isPass;
	
	@ApiModelProperty(value = "不通过理由")
	private String notPassReason;
	
	/** 创建人di */
	@ApiModelProperty(value = "创建人di")
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
	
	/** 选中的收费标准id */
	@ApiModelProperty(value = "更新时间")
	private List<Long> ids;

	/** sysTime */
	@ApiModelProperty(value = "sysTime")
	private Date sysTime;
	
	@ApiModelProperty(value = "收费客户列表")
	List<ChargeHouseChargeStandardCustomer> customers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getStandardId() {
		return standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
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

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public Date getChargeStartTime() {
		return chargeStartTime;
	}

	public void setChargeStartTime(Date chargeStartTime) {
		this.chargeStartTime = chargeStartTime;
	}

	public Date getChargeEndTime() {
		return chargeEndTime;
	}

	public void setChargeEndTime(Date chargeEndTime) {
		this.chargeEndTime = chargeEndTime;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
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

	public List<ChargeHouseChargeStandardCustomer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<ChargeHouseChargeStandardCustomer> customers) {
		this.customers = customers;
	}
	
	public Boolean getIsPass() {
		return isPass;
	}

	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}

	public String getNotPassReason() {
		return notPassReason;
	}

	public void setNotPassReason(String notPassReason) {
		this.notPassReason = notPassReason;
	}
	
	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	
}
