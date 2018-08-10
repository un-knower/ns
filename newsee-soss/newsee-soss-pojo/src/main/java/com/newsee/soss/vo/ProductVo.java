/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.newsee.soss.entity.NsSossProductOrderPrecinct;

import io.swagger.annotations.ApiModelProperty;

/**
 * ProductVo
 * @version 1.0
 * @author
 */
public class ProductVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 产品ID,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "产品ID,1|number|0|0|1,1|numbertext|0||1|1")
	private Long id;
	
	/** 产品名,1|text|0|200|2,1|text|1||2|0 */
	@ApiModelProperty(value = "产品名,1|text|0|200|2,1|text|1||2|0")
	private String productName;
	
	/** 产品编码 */
	@ApiModelProperty(value = "产品编码")
	private String productCode;
	
	/** 产品简介,1|text|0|300|8,1|textarea|0||8|0 */
	@ApiModelProperty(value = "产品简介,1|text|0|300|8,1|textarea|0||8|0")
	private String introduce;
	
	/** 产品描述,1|text|0|300|9,1|textarea|0||9|0 */
	@ApiModelProperty(value = "产品描述,1|text|0|300|9,1|textarea|0||9|0")
	private String content;
	
	/** 产品费用,1|number|0|150|5,1|numbertext|1||5|0 */
	@ApiModelProperty(value = "产品费用,1|number|0|150|5,1|numbertext|1||5|0")
	private BigDecimal price;
	
	/** 服务项目数量,1|number|0|150|3,1|numbertext|1||3|0 */
	@ApiModelProperty(value = "服务项目数量,1|number|0|150|3,1|numbertext|1||3|0")
	private Integer serviceCount;
	
	/** 服务面积,1|number|0|150|4,1|numbertext|1||4|0 */
	@ApiModelProperty(value = "服务面积,1|number|0|150|4,1|numbertext|1||4|0")
	private Long serviceArea;
	
	/** 产品是否下架,1|select|0|150|14,1|select|1||14|0,0:否|1:是 */
	@ApiModelProperty(value = "产品是否下架,1|select|0|150|14,1|select|1||14|0,0:否|1:是")
	private String shelves;
	
	/** 产品折扣,1|number|0|150|7,1|numbertext|0||7|0 */
	@ApiModelProperty(value = "产品折扣,1|number|0|150|7,1|numbertext|0||7|0")
	private Integer discount;
	
	/** 产品是否试用,1|select|0|150|15,1|select|1||15|0,0:否|1:是 */
	@ApiModelProperty(value = "产品是否试用,1|select|0|150|15,1|select|1||15|0,0:否|1:是")
	private String isTrial;
	
	/** 产品试用周期(以月为单位),1|number|0|150|6,1|numbertext|0||6|0 */
	@ApiModelProperty(value = "产品试用周期(以月为单位),1|number|0|150|6,1|numbertext|0||6|0")
	private Integer trialCycle;
	
	/** 产品变更时间,1|date|0|200|10,1|timepicker|0||10|0 */
	@ApiModelProperty(value = "产品变更时间,1|date|0|200|10,1|timepicker|0||10|0")
	private Date updateTime;
	
	/** 产品类型 */
	@ApiModelProperty(value = "产品类型")
	private String productType;
	/***/
	private String productTypeName;
	
	/** 产品图片 */
	@ApiModelProperty(value = "产品图片")
	private String imageUrl;
	
	/** 产品icon */
	@ApiModelProperty(value = "产品icon")
	private String iconUrl;
	
	/** 产品图片 */
	@ApiModelProperty(value = "产品图片")
	private String imageCode;
	
	/** 产品icon */
	@ApiModelProperty(value = "产品icon")
	private String iconCode;
	
	/** createUserId */
	@ApiModelProperty(value = "createUserId")
	private Long createUserId;
	
	/** createUserName */
	@ApiModelProperty(value = "createUserName")
	private String createUserName;
	
	/** updateUserId */
	@ApiModelProperty(value = "updateUserId")
	private Long updateUserId;
	
	/** updateUserName */
	@ApiModelProperty(value = "updateUserName")
	private String updateUserName;
	
	/**产品*/
	private List<ProductVo> productList; 
	/**项目*/
	private List<NsSossProductOrderPrecinct> precinctList;

	public List<NsSossProductOrderPrecinct> getPrecinctList() {
		return precinctList;
	}

	public void setPrecinctList(List<NsSossProductOrderPrecinct> precinctList) {
		this.precinctList = precinctList;
	}

	public List<ProductVo> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductVo> productList) {
		this.productList = productList;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getIconCode() {
		return iconCode;
	}

	public void setIconCode(String iconCode) {
		this.iconCode = iconCode;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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
	
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getIntroduce() {
		return introduce;
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
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
}
