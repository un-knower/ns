/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 票据编号规则表
 * @version 1.0
 * @author
 */
public class NsbillBillBookSerailRule implements Serializable {
	
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
	
	/** 票本类型,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票本类型,1|text|0|300|2,1|text|1||2|0")
	private String billType;
	
	/** 编号规则,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "编号规则,1|text|0|300|2,1|text|1||2|0")
	private String serialRule;
	
	/** 简称 */
	@ApiModelProperty(value = "简称")
	private String simpleName;
	
	/** 数量 */
	@ApiModelProperty(value = "数量")
	private String pageAmount;
	
	/** 是否限额发票 */
	@ApiModelProperty(value = "是否限额发票")
	private String limit;
	
	/** 是否定额发票 */
	@ApiModelProperty(value = "是否定额发票")
	private String quantum;
	
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
	
	/** 是否卷式发票 */
	@ApiModelProperty(value = "是否卷式发票")
	private String rollType;
	
	/** 是否增值税发票 */
	@ApiModelProperty(value = "是否增值税发票")
	private String taxType;
	
	/** 是否增值税普通发票 */
	@ApiModelProperty(value = "是否增值税普通发票")
	private String dztaxType;
	
		
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
	
	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	public String getBillType() {
		return billType;
	}
	
	public void setSerialRule(String serialRule) {
		this.serialRule = serialRule;
	}
	
	public String getSerialRule() {
		return serialRule;
	}
	
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	
	public String getSimpleName() {
		return simpleName;
	}
	
	public void setPageAmount(String pageAmount) {
		this.pageAmount = pageAmount;
	}
	
	public String getPageAmount() {
		return pageAmount;
	}
	
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	public String getLimit() {
		return limit;
	}
	
	public void setQuantum(String quantum) {
		this.quantum = quantum;
	}
	
	public String getQuantum() {
		return quantum;
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
	
	public void setRollType(String rollType) {
		this.rollType = rollType;
	}
	
	public String getRollType() {
		return rollType;
	}
	
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	
	public String getTaxType() {
		return taxType;
	}
	
	public void setDztaxType(String dztaxType) {
		this.dztaxType = dztaxType;
	}
	
	public String getDztaxType() {
		return dztaxType;
	}
	
}
