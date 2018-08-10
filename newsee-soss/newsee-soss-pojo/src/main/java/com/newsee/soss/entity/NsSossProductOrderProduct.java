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
 * 服务订单产品表
 * @version 1.0
 * @author
 */
public class NsSossProductOrderProduct implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 订单产品ID,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "订单产品ID,1|number|0|0|1,1|numbertext|0||1|1")
	private Long id;
	
	/** 订单id,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "订单id,1|number|0|0|1,1|numbertext|0||1|1")
	private Long productOrderId;
	
	/** 产品id */
	@ApiModelProperty(value = "产品id")
	private Long productId;
	
	/** 产品名,1|text|0|200|3,1|text|0||3|0 */
	@ApiModelProperty(value = "产品名,1|text|0|200|3,1|text|0||3|0")
	private String productName;
	
	/** 产品类型 */
	@ApiModelProperty(value = "产品类型")
	private String productType;
	
	/** 产品编码 */
	@ApiModelProperty(value = "产品编码")
	private String productCode;
	
	/** 产品简介 */
	@ApiModelProperty(value = "产品简介")
	private String introduce;
	
	/** 产品图片 */
	@ApiModelProperty(value = "产品图片")
	private String imageCode;
	
	/** 产品icon */
	@ApiModelProperty(value = "产品icon")
	private String iconCode;
	
	/** 产品描述 */
	@ApiModelProperty(value = "产品描述")
	private String content;
	
	/** 产品费用,0,1|numbertext|0||4|1 */
	@ApiModelProperty(value = "产品费用,0,1|numbertext|0||4|1")
	private BigDecimal price;
	
	/** 费用单位，默认人民币 */
	@ApiModelProperty(value = "费用单位，默认人民币")
	private String priceUnit;
	
	/** 服务项目数量,1|number|0|150|4,1|numbertext|0||4|0 */
	@ApiModelProperty(value = "服务项目数量,1|number|0|150|4,1|numbertext|0||4|0")
	private Integer serviceCount;
	
	/** 服务面积,1|number|0|150|5,1|numbertext|0||5|0 */
	@ApiModelProperty(value = "服务面积,1|number|0|150|5,1|numbertext|0||5|0")
	private Long serviceArea;
	
	/** 产品的服务地址 */
	@ApiModelProperty(value = "产品的服务地址")
	private String serviceUrl;
	
	/** 产品折扣 */
	@ApiModelProperty(value = "产品折扣")
	private Integer discount;
	
	/** 产品是否试用,1|select|0|150|16,1|radio|0||16|0,0:否|1:是 */
	@ApiModelProperty(value = "产品是否试用,1|select|0|150|16,1|radio|0||16|0,0:否|1:是")
	private String isTrial;
	
	/** 产品试用周期，以天为单位 */
	@ApiModelProperty(value = "产品试用周期，以天为单位")
	private Integer trialCycle;
	
	/** 服务状态 */
	@ApiModelProperty(value = "服务状态")
	private String serviceStatus;
	
	/** 服务是否开通,1|select|0|150|15,1|radio|0||15|0,0:待开通|1:已开通|2:关闭 */
	@ApiModelProperty(value = "服务是否开通,1|select|0|150|15,1|radio|0||15|0,0:待开通|1:已开通|2:关闭")
	private String serviceWork;
	
	/** 服务开始时间,1|date|0|200|13,1|datepicker|1||13|0 */
	@ApiModelProperty(value = "服务开始时间,1|date|0|200|13,1|datepicker|1||13|0")
	private Date startTime;
	
	/** 服务截止时间,1|date|0|200|14,1|datepicker|1||14|0 */
	@ApiModelProperty(value = "服务截止时间,1|date|0|200|14,1|datepicker|1||14|0")
	private Date endTime;
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setProductOrderId(Long productOrderId) {
		this.productOrderId = productOrderId;
	}
	
	public Long getProductOrderId() {
		return productOrderId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public Long getProductId() {
		return productId;
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
	
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	
	public String getServiceStatus() {
		return serviceStatus;
	}
	
	public void setServiceWork(String serviceWork) {
		this.serviceWork = serviceWork;
	}
	
	public String getServiceWork() {
		return serviceWork;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
}
