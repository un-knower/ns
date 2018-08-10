/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 产品表
 * @version 1.0
 * @author
 */
public class NsSossProduct implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 产品ID,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "产品ID,1|number|0|0|1,1|numbertext|0||1|1")
	private Long id;
	
	/** 产品名,1|text|0|200|2,1|text|1||2|0 */
	@ApiModelProperty(value = "产品名,1|text|0|200|2,1|text|1||2|0")
	private String productName;
	
	/** 产品类型 */
	@ApiModelProperty(value = "产品类型")
	private String productType;
	
	/** 产品编码 */
	@ApiModelProperty(value = "产品编码")
	private String productCode;
	
	/** 产品简介,1|text|0|300|8,1|textarea|0||8|0 */
	@ApiModelProperty(value = "产品简介,1|text|0|300|8,1|textarea|0||8|0")
	private String introduce;
	
	/** 产品图片 */
	@ApiModelProperty(value = "产品图片")
	private String imageCode;
	
	/** 产品icon */
	@ApiModelProperty(value = "产品icon")
	private String iconCode;
	
	/** 产品描述,1|text|0|300|9,1|textarea|0||9|0 */
	@ApiModelProperty(value = "产品描述,1|text|0|300|9,1|textarea|0||9|0")
	private String content;
	
	/** 产品费用,1|number|0|150|5,1|numbertext|1||5|0 */
	@ApiModelProperty(value = "产品费用,1|number|0|150|5,1|numbertext|1||5|0")
	private BigDecimal price;
	
	/** 费用单位，默认￥人民币 */
	@ApiModelProperty(value = "费用单位，默认￥人民币")
	private String priceUnit;
	
	/** 服务项目数量,1|number|0|150|3,1|numbertext|1||3|0 */
	@ApiModelProperty(value = "服务项目数量,1|number|0|150|3,1|numbertext|1||3|0")
	private Integer serviceCount;
	
	/** 服务面积,1|number|0|150|4,1|numbertext|1||4|0 */
	@ApiModelProperty(value = "服务面积,1|number|0|150|4,1|numbertext|1||4|0")
	private Long serviceArea;
	
	/** 产品的服务地址 */
	@ApiModelProperty(value = "产品的服务地址")
	private String serviceUrl;
	
	/** 是否删除产品 0 否，1 是 */
	@ApiModelProperty(value = "是否删除产品 0 否，1 是")
	private Integer isDelete;
	
	/** 产品是否下架,1|select|0|150|14,1|select|1||14|0,0:否|1:是 */
	@ApiModelProperty(value = "产品是否下架,1|select|0|150|14,1|select|1||14|0,0:否|1:是")
	private String shelves;
	
	/** 产品折扣,1|number|0|150|7,1|numbertext|0||7|0 */
	@ApiModelProperty(value = "产品折扣,1|number|0|150|7,1|numbertext|0||7|0")
	private Integer discount;
	
	/** 产品是否试用,1|select|0|150|15,1|select|1||15|0,0:否|1:是 */
	@ApiModelProperty(value = "产品是否试用,1|select|0|150|15,1|select|1||15|0,0:否|1:是")
	private String isTrial;
	
	/** 产品试用周期(以天为单位),1|number|0|150|6,1|numbertext|0||6|0 */
	@ApiModelProperty(value = "产品试用周期(以天为单位),1|number|0|150|6,1|numbertext|0||6|0")
	private Integer trialCycle;
	
	/** createTime */
	@ApiModelProperty(value = "createTime")
	private Date createTime;
	
	/** createUserId */
	@ApiModelProperty(value = "createUserId")
	private Long createUserId;
	
	/** createUserName */
	@ApiModelProperty(value = "createUserName")
	private String createUserName;
	
	/** 产品变更时间,1|date|0|200|10,1|timepicker|0||10|0 */
	@ApiModelProperty(value = "产品变更时间,1|date|0|200|10,1|timepicker|0||10|0")
	private Date updateTime;
	
	/** updateUserId */
	@ApiModelProperty(value = "updateUserId")
	private Long updateUserId;
	
	/** updateUserName */
	@ApiModelProperty(value = "updateUserName")
	private String updateUserName;
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public String getProductType() {
		return productType;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductCode() {
		return productCode;
	}
	
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getIntroduce() {
		return introduce;
	}
	
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	
	public String getImageCode() {
		return imageCode;
	}
	
	public void setIconCode(String iconCode) {
		this.iconCode = iconCode;
	}
	
	public String getIconCode() {
		return iconCode;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	
	public String getPriceUnit() {
		return priceUnit;
	}
	
	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}
	
	public Integer getServiceCount() {
		return serviceCount;
	}
	
	public void setServiceArea(Long serviceArea) {
		this.serviceArea = serviceArea;
	}
	
	public Long getServiceArea() {
		return serviceArea;
	}
	
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	
	public String getServiceUrl() {
		return serviceUrl;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}
	
	public void setShelves(String shelves) {
		this.shelves = shelves;
	}
	
	public String getShelves() {
		return shelves;
	}
	
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	public Integer getDiscount() {
		return discount;
	}
	
	public void setIsTrial(String isTrial) {
		this.isTrial = isTrial;
	}
	
	public String getIsTrial() {
		return isTrial;
	}
	
	public void setTrialCycle(Integer trialCycle) {
		this.trialCycle = trialCycle;
	}
	
	public Integer getTrialCycle() {
		return trialCycle;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return createTime;
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
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
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
	
}
