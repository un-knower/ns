/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

/**
 * BillReVo
 * @version 1.0
 * @author
 */
public class BillReVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 票据类型,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据类型,1|text|0|300|2,1|text|1||2|0")
	private String billType;
	
	/** 更新人姓名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "更新人姓名,1|text|0|300|2,1|text|1||2|0")
	private String updateUserName;
	
	/** 票号,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票号,1|text|0|300|2,1|text|1||2|0")
	private String billNum;
	
	/** 创建时间,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "创建时间,1|text|0|300|2,1|text|1||2|0")
	private Date createTime;
	
	/** 更新时间,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "更新时间,1|text|0|300|2,1|text|1||2|0")
	private Date updateTime;
	
	/** 票据编码,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据编码,1|text|0|300|2,1|text|1||2|0")
	private String billCode;
	
	/** 创建人姓名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "创建人姓名,1|text|0|300|2,1|text|1||2|0")
	private String createUserName;
	
		
	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	public String getBillType() {
		return billType;
	}
	
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	
	public String getUpdateUserName() {
		return updateUserName;
	}
	
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	
	public String getBillNum() {
		return billNum;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	
	public String getBillCode() {
		return billCode;
	}
	
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	public String getCreateUserName() {
		return createUserName;
	}
	
}
