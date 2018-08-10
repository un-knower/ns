/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 票据打印内容设置表
 * @version 1.0
 * @author
 */
public class NsbillPrintHtml implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 自增id */
	@ApiModelProperty(value = "自增id")
	private Long id;
	
	/** 设置ID */
	@ApiModelProperty(value = "设置ID")
	private Long settingId;
	
	/** 打印类型 */
	@ApiModelProperty(value = "打印类型")
	private Integer printType;
	
	/** 打印的HTML */
	@ApiModelProperty(value = "打印的HTML")
	private String printHtml;
	
	/** 打印的XML */
	@ApiModelProperty(value = "打印的XML")
	private String printXml;
	
	/** 创建人id */
	@ApiModelProperty(value = "创建人id")
	private Long createUserId;
	
	/** 创建人姓名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "创建人姓名,1|text|0|300|2,1|text|1||2|0")
	private String createUserName;
	
	/** 创建时间,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "创建时间,1|text|0|300|2,1|text|1||2|0")
	private Date createTime;
	
	/** 更新人id */
	@ApiModelProperty(value = "更新人id")
	private Long updateUserId;
	
	/** 更新人姓名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "更新人姓名,1|text|0|300|2,1|text|1||2|0")
	private String updateUserName;
	
	/** 更新时间,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "更新时间,1|text|0|300|2,1|text|1||2|0")
	private Date updateTime;
	
	/** 是否删除 */
	@ApiModelProperty(value = "是否删除")
	private Integer isDelete;
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setSettingId(Long settingId) {
		this.settingId = settingId;
	}
	
	public Long getSettingId() {
		return settingId;
	}
	
	public void setPrintType(Integer printType) {
		this.printType = printType;
	}
	
	public Integer getPrintType() {
		return printType;
	}
	
	public void setPrintHtml(String printHtml) {
		this.printHtml = printHtml;
	}
	
	public String getPrintHtml() {
		return printHtml;
	}
	
	public void setPrintXml(String printXml) {
		this.printXml = printXml;
	}
	
	public String getPrintXml() {
		return printXml;
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
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}
	
}
