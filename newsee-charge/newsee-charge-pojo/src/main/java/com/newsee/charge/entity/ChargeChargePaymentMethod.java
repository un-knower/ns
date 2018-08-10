/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 支付方式表
 * @version 1.0
 * @author
 */
public class ChargeChargePaymentMethod implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|0,1|text|0||1|1")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id,,1|select|1||2|0 */
	@ApiModelProperty(value = "组织id,,1|select|1||2|0")
	private Long organizationId;
	
	/** 组织名称,1|text|0|200|1 */
	@ApiModelProperty(value = "组织名称,1|text|0|200|1")
	private String organizationName;
	
	/** 编码,1|text|0|150|2,1|text|1|numEn|2|0 */
	@ApiModelProperty(value = "编码,1|text|0|150|2,1|text|1|numEn|2|0")
	private String methodCode;
	
	/** 支付方式,1|text|0|250|3,1|text|1||3|0 */
	@ApiModelProperty(value = "支付方式,1|text|0|250|3,1|text|1||3|0")
	private String methodName;
	
	/** 支付类型,1|select|0|250|4,1|select|0||4|0,1:支票|2:信用卡|3:支付宝|4:微信|5:通联 */
	@ApiModelProperty(value = "支付类型,1|select|0|250|4,1|select|0||4|0,1:支票|2:信用卡|3:支付宝|4:微信|5:通联")
	private String methodType;
	
	/** 账号名称,,1|text|0||6|0 */
	@ApiModelProperty(value = "账号名称,,1|text|0||6|0")
	private String accountName;
	
	/** 应用AppID,,1|text|0||7|0 */
	@ApiModelProperty(value = "应用AppID,,1|text|0||7|0")
	private String appId;
	
	/** 合作者ID,,1|text|0||8|0 */
	@ApiModelProperty(value = "合作者ID,,1|text|0||8|0")
	private String coooperId;
	
	/** 对方合作者ID,,1|text|0||9|0 */
	@ApiModelProperty(value = "对方合作者ID,,1|text|0||9|0")
	private String otherSideCooperId;
	
	/** 公钥,,1|text|0||9|0 */
	@ApiModelProperty(value = "公钥,,1|text|0||9|0")
	private String publicKey;
	
	/** 私钥,,1|text|0||10|0 */
	@ApiModelProperty(value = "私钥,,1|text|0||10|0")
	private String privateKey;
	
	/** 启用,1|select|0|150|11,1|redio|0||11|0,0:停用|1:启用 */
	@ApiModelProperty(value = "启用,1|select|0|150|11,1|redio|0||11|0,0:停用|1:启用")
	private String isWork;
	
	/** 备注,,1|textarea|0||12|0 */
	@ApiModelProperty(value = "备注,,1|textarea|0||12|0")
	private String remark;
	
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

	@ApiModelProperty(value = "逻辑删除")
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
	
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}
	
	public String getMethodCode() {
		return methodCode;
	}
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	
	public String getMethodType() {
		return methodType;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getAppId() {
		return appId;
	}
	
	public void setCoooperId(String coooperId) {
		this.coooperId = coooperId;
	}
	
	public String getCoooperId() {
		return coooperId;
	}
	
	public void setOtherSideCooperId(String otherSideCooperId) {
		this.otherSideCooperId = otherSideCooperId;
	}
	
	public String getOtherSideCooperId() {
		return otherSideCooperId;
	}
	
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	public String getPublicKey() {
		return publicKey;
	}
	
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	public String getPrivateKey() {
		return privateKey;
	}
	
	public void setIsWork(String isWork) {
		this.isWork = isWork;
	}
	
	public String getIsWork() {
		return isWork;
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
	
	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}
	
	public Date getSysTime() {
		return sysTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
