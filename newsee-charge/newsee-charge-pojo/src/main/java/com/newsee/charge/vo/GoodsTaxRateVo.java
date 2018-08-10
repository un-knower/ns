/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

/**
 * GoodsTaxRateVo
 * @version 1.0
 * @author
 */
public class GoodsTaxRateVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键,1|number|0|0|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|1")
	private Long id;
	
	/** 收费科目,1|text|0|200|2 */
	@ApiModelProperty(value = "收费科目,1|text|0|200|2")
	private String chargeItemName;
	
	/** 税目编码,1|text|0|200|4 */
	@ApiModelProperty(value = "税目编码,1|text|0|200|4")
	private String goodsTaxCode;
	
	/** 税率,1|number|0|130|3 */
	@ApiModelProperty(value = "税率,1|number|0|130|3")
	private Double taxRate;
	
	/** 项目名称,1|text|0|200|1 */
	@ApiModelProperty(value = "项目名称,1|text|0|200|1")
	private String precinctName;
	
	/** 备注,1|text|0|250|5 */
	@ApiModelProperty(value = "备注,1|text|0|250|5")
	private String remark;
	
	/** 创建人id */
	@ApiModelProperty(value = "创建人id")
	private Long createUserId;
	
	/** 创建人,1|text|0|150|6 */
	@ApiModelProperty(value = "创建人,1|text|0|150|6")
	private String createUserName;
	
	/** 创建时间,1|date|0|200|6 */
	@ApiModelProperty(value = "创建时间,1|date|0|200|6")
	private Date createTime;
	
	/** 更新人id */
	@ApiModelProperty(value = "更新人id")
	private Long updateUserId;

	/** 修改人,1|text|0|150|7 */
	@ApiModelProperty(value = "修改人,1|text|0|150|7")
	private String updateUserName;
	
	/** 修改时间,1|date|0|200|9 */
	@ApiModelProperty(value = "修改时间,1|date|0|200|9")
	private Date updateTime;
	
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	
	public String getUpdateUserName() {
		return updateUserName;
	}
	
	public void setChargeItemName(String chargeItemName) {
		this.chargeItemName = chargeItemName;
	}
	
	public String getChargeItemName() {
		return chargeItemName;
	}
	
	public void setGoodsTaxCode(String goodsTaxCode) {
		this.goodsTaxCode = goodsTaxCode;
	}
	
	public String getGoodsTaxCode() {
		return goodsTaxCode;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	
	public Double getTaxRate() {
		return taxRate;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setPrecinctName(String precinctName) {
		this.precinctName = precinctName;
	}
	
	public String getPrecinctName() {
		return precinctName;
	}
	
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	public String getCreateUserName() {
		return createUserName;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	
}
