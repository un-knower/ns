/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 收费科目表
 * @version 1.0
 * @author
 */
public class ChargeChargeItemGoodsTax implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "主键")
	private Long id;
	
	/** 收费科目id */
	@ApiModelProperty(value = "收费科目id")
	private Long itemId;
	
	/** 税目编码id */
	@ApiModelProperty(value = "税目编码id")
	private Long taxId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getTaxId() {
		return taxId;
	}

	public void setTaxId(Long taxId) {
		this.taxId = taxId;
	}
	
}
