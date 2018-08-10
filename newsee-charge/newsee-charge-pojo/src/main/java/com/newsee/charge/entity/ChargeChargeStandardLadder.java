/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 收费标准分段价位表
 * @version 1.0
 * @author
 */
public class ChargeChargeStandardLadder implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** id */
	@ApiModelProperty(value = "id")
	private Long id;
	
	/** 收费标准id */
	@ApiModelProperty(value = "收费标准id")
	private Long standardId;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	/** 分段开始数量 */
	@ApiModelProperty(value = "分段开始数量")
	private String ladderStartNum;
	
	/** 分段结束数量 */
	@ApiModelProperty(value = "分段结束数量")
	private String ladderEndNum;
	
	/** 分段单价 */
	@ApiModelProperty(value = "分段单价")
	private String ladderPrice;
	
	/** 分段单价单位 */
	@ApiModelProperty(value = "分段单价单位")
	private String ladderUnit;
	
	@ApiModelProperty(value = "分段类型")
	private String type;
		
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}
	
	public Long getStandardId() {
		return standardId;
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
	
	
	


	public String getLadderPrice() {
		return ladderPrice;
	}

	public void setLadderPrice(String ladderPrice) {
		this.ladderPrice = ladderPrice;
	}

	public String getLadderStartNum() {
		return ladderStartNum;
	}

	public void setLadderStartNum(String ladderStartNum) {
		this.ladderStartNum = ladderStartNum;
	}

	public String getLadderEndNum() {
		return ladderEndNum;
	}

	public void setLadderEndNum(String ladderEndNum) {
		this.ladderEndNum = ladderEndNum;
	}

	public String getLadderUnit() {
		return ladderUnit;
	}

	public void setLadderUnit(String ladderUnit) {
		this.ladderUnit = ladderUnit;
	}
	
	
	
}
