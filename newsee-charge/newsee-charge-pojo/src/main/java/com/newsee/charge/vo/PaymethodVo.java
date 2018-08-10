/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * PaymethodVo
 * @version 1.0
 * @author
 */
public class PaymethodVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 对方合作者ID,,1|text|0||9|0 */
	@ApiModelProperty(value = "对方合作者ID,,1|text|0||9|0")
	private String otherSideCooperId;
	
	/** 支付类型,1|select|0|250|4,1|select|0||4|0,1:支票|2:信用卡|3:支付宝|4:微信|5:通联 */
	@ApiModelProperty(value = "支付类型,1|select|0|250|4,1|select|0||4|0,1:支票|2:信用卡|3:支付宝|4:微信|5:通联")
	private String methodType;
	
	/** 私钥,,1|text|0||10|0 */
	@ApiModelProperty(value = "私钥,,1|text|0||10|0")
	private String privateKey;
	
	/** 公钥,,1|text|0||9|0 */
	@ApiModelProperty(value = "公钥,,1|text|0||9|0")
	private String publicKey;
	
	/** 组织名称,1|text|0|200|1 */
	@ApiModelProperty(value = "组织名称,1|text|0|200|1")
	private String organizationName;
	
	/** 组织id,,1|select|1||2|0 */
	@ApiModelProperty(value = "组织id,,1|select|1||2|0")
	private Long organizationId;
	
	/** 合作者ID,,1|text|0||8|0 */
	@ApiModelProperty(value = "合作者ID,,1|text|0||8|0")
	private String coooperId;
	
	/** 备注,,1|textarea|0||12|0 */
	@ApiModelProperty(value = "备注,,1|textarea|0||12|0")
	private String remark;
	
	/** 应用AppID,,1|text|0||7|0 */
	@ApiModelProperty(value = "应用AppID,,1|text|0||7|0")
	private String appId;
	
	/** 启用,1|select|0|150|11,1|redio|0||11|0,0:停用|1:启用 */
	@ApiModelProperty(value = "启用,1|select|0|150|11,1|redio|0||11|0,0:停用|1:启用")
	private String isWork;
	
	/** 主键,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|0,1|text|0||1|1")
	private Long id;
	
	/** 支付方式,1|text|0|250|3,1|text|1||3|0 */
	@ApiModelProperty(value = "支付方式,1|text|0|250|3,1|text|1||3|0")
	private String methodName;
	
	/** 编码,1|text|0|150|2,1|text|1|numEn|2|0 */
	@ApiModelProperty(value = "编码,1|text|0|150|2,1|text|1|numEn|2|0")
	private String methodCode;
	
	/** 账号名称,,1|text|0||6|0 */
	@ApiModelProperty(value = "账号名称,,1|text|0||6|0")
	private String accountName;
	
	@ApiModelProperty(value = "创建人id")
	private Long createUserId;

	/** 创建人,1|text|0|150|3 */
	@ApiModelProperty(value = "创建人,1|text|0|150|3")
	private String createUserName;
	
	/** 创建时间,1|date|0|200|4 */
	@ApiModelProperty(value = "创建时间,1|date|0|200|4")
	private Date createTime;
	
	@ApiModelProperty(value = "更新人id")
	private Long updateUserId;
	
	/** 修改人,1|text|0|150|5 */
	@ApiModelProperty(value = "修改人,1|text|0|150|5")
	private String updateUserName;
	
	/** 修改时间,1|date|0|200|6 */
	@ApiModelProperty(value = "修改时间,1|date|0|200|6")
	private Date updateTime;
	
	private Long enterpriseId;
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public void setOtherSideCooperId(String otherSideCooperId) {
		this.otherSideCooperId = otherSideCooperId;
	}
	
	public String getOtherSideCooperId() {
		return otherSideCooperId;
	}
	
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	
	public String getMethodType() {
		return methodType;
	}
	
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	public String getPrivateKey() {
		return privateKey;
	}
	
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	public String getPublicKey() {
		return publicKey;
	}
	
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	public String getOrganizationName() {
		return organizationName;
	}
	
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	public Long getOrganizationId() {
		return organizationId;
	}
	
	public void setCoooperId(String coooperId) {
		this.coooperId = coooperId;
	}
	
	public String getCoooperId() {
		return coooperId;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getAppId() {
		return appId;
	}
	
	public void setIsWork(String isWork) {
		this.isWork = isWork;
	}
	
	public String getIsWork() {
		return isWork;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}
	
	public String getMethodCode() {
		return methodCode;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAccountName() {
		return accountName;
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
}
