/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 票据信息头表
 * @version 1.0
 * @author
 */
public class NsbillBillInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|0,1|text|0||1|1")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "组织id,1|number|0|0|0,1|text|0||1|1")
	private Long organizationId;
	
	/** 票据批号,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据批号,1|text|0|300|2,1|text|1||2|0")
	private String batchCode;
	
	/** 批号名称,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "批号名称,1|text|0|300|2,1|text|1||2|0")
	private String batchName;
	
	/** 购买日期 */
	@ApiModelProperty(value = "购买日期")
	private Date buyDate;
	
	/** 购买来源单位,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "购买来源单位,1|text|0|300|2,1|text|1||2|0")
	private String fromCompany;
	
	/** 票据类型,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据类型,1|text|0|300|2,1|text|1||2|0")
	private String billType;
	
	/** 票据类型Id */
	@ApiModelProperty(value = "票据类型Id")
	private Long billTypeId;
	
	/** 备注说明,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "备注说明,1|text|0|300|2,1|text|1||2|0")
	private String remark;
	
	/** 票据本名称,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据本名称,1|text|0|300|2,1|text|1||2|0")
	private String billDetailName;
	
	/** 票据面额,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据面额,1|text|0|300|2,1|text|1||2|0")
	private String pageBalance;
	
	/** 分类目录编号,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "分类目录编号,1|text|0|300|2,1|text|1||2|0")
	private String catalogId;
	
	/** 票据号从 */
	@ApiModelProperty(value = "票据号从")
	private Long fromNum;
	
	/** 票据号到 */
	@ApiModelProperty(value = "票据号到")
	private Long toNum;
	
	/** 是否被领用,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "是否被领用,1|text|0|300|2,1|text|1||2|0")
	private String isDraw;
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
	
	/** isDelete */
	@ApiModelProperty(value = "isDelete")
	private Long isDelete;
	
		
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
	
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	
	public String getBatchCode() {
		return batchCode;
	}
	
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	
	public String getBatchName() {
		return batchName;
	}
	
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	
	public Date getBuyDate() {
		return buyDate;
	}
	
	public void setFromCompany(String fromCompany) {
		this.fromCompany = fromCompany;
	}
	
	public String getFromCompany() {
		return fromCompany;
	}
	
	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	public String getBillType() {
		return billType;
	}
	
	public void setBillTypeId(Long billTypeId) {
		this.billTypeId = billTypeId;
	}
	
	public Long getBillTypeId() {
		return billTypeId;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
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
	
	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}
	
	public Long getIsDelete() {
		return isDelete;
	}
	
}
