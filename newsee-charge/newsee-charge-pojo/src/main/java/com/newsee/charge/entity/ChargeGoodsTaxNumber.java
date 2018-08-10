/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 税号设置
 * @version 1.0
 * @author
 */
public class ChargeGoodsTaxNumber implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@ApiModelProperty(value = "主键")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	/** 组织名称 */
	@ApiModelProperty(value = "组织名称")
	private String organizationName;
	
	/** 公司名称,1|text|0|200|1,1|text|1||1|0 */
	@ApiModelProperty(value = "公司名称,1|text|0|200|1,1|text|1||1|0")
	private String companyName;
	
	/** 公司税号,1|text|0|200|2,1|text|1||2|0 */
	@ApiModelProperty(value = "公司税号,1|text|0|200|2,1|text|1||2|0")
	private String companyTaxNumber;
	
	/** 地址,1|text|0|200|3,1|text|1||3|0 */
	@ApiModelProperty(value = "地址,1|text|0|200|3,1|text|1||3|0")
	private String address;
	
	/** 电话,1|text|0|200|4,1|text|1||4|0 */
	@ApiModelProperty(value = "电话,1|text|0|200|4,1|text|1||4|0")
	private String phoneNumber;
	
	/** 银行,1|text|0|200|5,1|text|1||5|0 */
	@ApiModelProperty(value = "银行,1|text|0|200|5,1|text|1||5|0")
	private String bankName;
	
	/** 银行账号,1|text|0|200|6,1|text|1||6|0 */
	@ApiModelProperty(value = "银行账号,1|text|0|200|6,1|text|1||6|0")
	private String bankAccount;
	
	/** 税控电子开票服务器应用Id,1|text|0|200|7,1|text|1||7|0 */
	@ApiModelProperty(value = "税控电子开票服务器应用Id,1|text|0|200|7,1|text|1||7|0")
	private String ticketServerId;
	
	/** 税控电子开票服务器应用秘钥,1|text|0|200|8,1|text|1||8|0 */
	@ApiModelProperty(value = "税控电子开票服务器应用秘钥,1|text|0|200|8,1|text|1||8|0")
	private String ticketServerKey;

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


	@ApiModelProperty(value = "逻辑删除，0:未删除 1:删除")
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
	
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	public String getOrganizationName() {
		return organizationName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyTaxNumber(String companyTaxNumber) {
		this.companyTaxNumber = companyTaxNumber;
	}
	
	public String getCompanyTaxNumber() {
		return companyTaxNumber;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getBankName() {
		return bankName;
	}
	
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public String getBankAccount() {
		return bankAccount;
	}
	
	public void setTicketServerId(String ticketServerId) {
		this.ticketServerId = ticketServerId;
	}
	
	public String getTicketServerId() {
		return ticketServerId;
	}
	
	public void setTicketServerKey(String ticketServerKey) {
		this.ticketServerKey = ticketServerKey;
	}
	
	public String getTicketServerKey() {
		return ticketServerKey;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
