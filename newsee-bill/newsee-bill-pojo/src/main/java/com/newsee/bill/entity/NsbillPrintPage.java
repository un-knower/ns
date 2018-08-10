/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 票据页面设置表
 * @version 1.0
 * @author
 */
public class NsbillPrintPage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 自增id */
	@ApiModelProperty(value = "自增id")
	private Long id;
	
	/** 设置ID */
	@ApiModelProperty(value = "设置ID")
	private Long settingId;
	
	/** 打印机名称 */
	@ApiModelProperty(value = "打印机名称")
	private String printerName;
	
	/** 纸张名称 */
	@ApiModelProperty(value = "纸张名称")
	private String paperName;
	
	/** 打印方向 */
	@ApiModelProperty(value = "打印方向")
	private String orientation;
	
	/** 打印底图 */
	@ApiModelProperty(value = "打印底图")
	private String imgSrc;
	
	/** 底图高 */
	@ApiModelProperty(value = "底图高")
	private Integer imgHeight;
	
	/** 底图宽 */
	@ApiModelProperty(value = "底图宽")
	private Integer imgWidth;
	
	/** 打印左边距 */
	@ApiModelProperty(value = "打印左边距")
	private Integer leftPadding;
	
	/** 打印上边距 */
	@ApiModelProperty(value = "打印上边距")
	private Integer topPadding;
	
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
	
	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}
	
	public String getPrinterName() {
		return printerName;
	}
	
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	
	public String getPaperName() {
		return paperName;
	}
	
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	public String getOrientation() {
		return orientation;
	}
	
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	public String getImgSrc() {
		return imgSrc;
	}
	
	public void setImgHeight(Integer imgHeight) {
		this.imgHeight = imgHeight;
	}
	
	public Integer getImgHeight() {
		return imgHeight;
	}
	
	public void setImgWidth(Integer imgWidth) {
		this.imgWidth = imgWidth;
	}
	
	public Integer getImgWidth() {
		return imgWidth;
	}
	
	public void setLeftPadding(Integer leftPadding) {
		this.leftPadding = leftPadding;
	}
	
	public Integer getLeftPadding() {
		return leftPadding;
	}
	
	public void setTopPadding(Integer topPadding) {
		this.topPadding = topPadding;
	}
	
	public Integer getTopPadding() {
		return topPadding;
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
