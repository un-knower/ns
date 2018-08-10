/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

/**
 * BillPurcVo
 * @version 1.0
 * @author
 */
public class BillPurcVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 票据类型,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据类型,1|text|0|300|2,1|text|1||2|0")
	private String billType;
	
	/** 企业ID,1|text|0|300|2,1|text|1||2|0 */
	private  long enterpriseId;
	
	/** 更新人姓名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "更新人姓名,1|text|0|300|2,1|text|1||2|0")
	private String updateUserName;
	
	/** 票据本名称,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据本名称,1|text|0|300|2,1|text|1||2|0")
	private String billDetailName;
	
	/** 票据面额,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据面额,1|text|0|300|2,1|text|1||2|0")
	private String pageBalance;
	
	/** 创建时间,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "创建时间,1|text|0|300|2,1|text|1||2|0")
	private Date createTime;
	
	/** 是否被领用,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "是否被领用,1|text|0|300|2,1|text|1||2|0")
	private String isDraw;
	
	/** 组织id,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "组织id,1|number|0|0|0,1|text|0||1|1")
	private Long organizationId;
	
	/** 创建人姓名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "创建人姓名,1|text|0|300|2,1|text|1||2|0")
	private String createUserName;
	
	/** 分类目录编号,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "分类目录编号,1|text|0|300|2,1|text|1||2|0")
	private String catalogId;
	
	/** 备注说明,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "备注说明,1|text|0|300|2,1|text|1||2|0")
	private String remark;
	
	/** 批号名称,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "批号名称,1|text|0|300|2,1|text|1||2|0")
	private String batchName;
	
	/** 票据号码中文前缀,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据号码中文前缀,1|text|0|300|2,1|text|1||2|0")
	private String cnprefix;
	
	/** 票据批号,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据批号,1|text|0|300|2,1|text|1||2|0")
	private String batchCode;
	
	/** 更新时间,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "更新时间,1|text|0|300|2,1|text|1||2|0")
	private Date updateTime;
	
	/** 购买来源单位,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "购买来源单位,1|text|0|300|2,1|text|1||2|0")
	private String fromCompany;
	
	/** 主键,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|0,1|text|0||1|1")
	private Long id;
	
	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	public String getBillType() {
		return billType;
	}
	
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	
	public String getUpdateUserName() {
		return updateUserName;
	}
	
	public void setBillDetailName(String billDetailName) {
		this.billDetailName = billDetailName;
	}
	
	public String getBillDetailName() {
		return billDetailName;
	}
	
	public void setPageBalance(String pageBalance) {
		this.pageBalance = pageBalance;
	}
	
	public String getPageBalance() {
		return pageBalance;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setIsDraw(String isDraw) {
		this.isDraw = isDraw;
	}
	
	public String getIsDraw() {
		return isDraw;
	}
	
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	public Long getOrganizationId() {
		return organizationId;
	}
	
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	public String getCreateUserName() {
		return createUserName;
	}
	
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	
	public String getCatalogId() {
		return catalogId;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	
	public String getBatchName() {
		return batchName;
	}
	
	public void setCnprefix(String cnprefix) {
		this.cnprefix = cnprefix;
	}
	
	public String getCnprefix() {
		return cnprefix;
	}
	
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	
	public String getBatchCode() {
		return batchCode;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setFromCompany(String fromCompany) {
		this.fromCompany = fromCompany;
	}
	
	public String getFromCompany() {
		return fromCompany;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
}
