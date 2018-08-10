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
public class NsbillPrintContent implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 自增id */
	@ApiModelProperty(value = "自增id")
	private Long id;
	
	/** 设置ID */
	@ApiModelProperty(value = "设置ID")
	private Long settingId;
	
	/** 打印字段 */
	@ApiModelProperty(value = "打印字段")
	private String printField;
	
	/** 横坐标 */
	@ApiModelProperty(value = "横坐标")
	private Integer pointX;
	
	/** 纵坐标 */
	@ApiModelProperty(value = "纵坐标")
	private Integer pointY;
	
	/** 对齐 */
	@ApiModelProperty(value = "对齐")
	private Integer align;
	
	/** valign */
	@ApiModelProperty(value = "valign")
	private Integer valign;
	
	/** 下边框宽度 */
	@ApiModelProperty(value = "下边框宽度")
	private Integer borderBottom;
	
	/** 左边框宽度 */
	@ApiModelProperty(value = "左边框宽度")
	private Integer borderLeft;
	
	/** 右边框宽度 */
	@ApiModelProperty(value = "右边框宽度")
	private Integer borderRight;
	
	/** 上边框宽度 */
	@ApiModelProperty(value = "上边框宽度")
	private Integer borderTop;
	
	/** 高度 */
	@ApiModelProperty(value = "高度")
	private Integer height;
	
	/** 宽度 */
	@ApiModelProperty(value = "宽度")
	private Integer width;
	
	/** 是否大的 */
	@ApiModelProperty(value = "是否大的")
	private Integer isBigSum;
	
	/** 是否是小的，1:是，0:否 */
	@ApiModelProperty(value = "是否是小的，1:是，0:否")
	private Integer isSmallSum;
	
	/** 是否备注 */
	@ApiModelProperty(value = "是否备注")
	private Integer isRemark;
	
	/** 是否 */
	@ApiModelProperty(value = "是否")
	private Integer isYzxm;
	
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
	
	public void setPrintField(String printField) {
		this.printField = printField;
	}
	
	public String getPrintField() {
		return printField;
	}
	
	public void setPointX(Integer pointX) {
		this.pointX = pointX;
	}
	
	public Integer getPointX() {
		return pointX;
	}
	
	public void setPointY(Integer pointY) {
		this.pointY = pointY;
	}
	
	public Integer getPointY() {
		return pointY;
	}
	
	public void setAlign(Integer align) {
		this.align = align;
	}
	
	public Integer getAlign() {
		return align;
	}
	
	public void setValign(Integer valign) {
		this.valign = valign;
	}
	
	public Integer getValign() {
		return valign;
	}
	
	public void setBorderBottom(Integer borderBottom) {
		this.borderBottom = borderBottom;
	}
	
	public Integer getBorderBottom() {
		return borderBottom;
	}
	
	public void setBorderLeft(Integer borderLeft) {
		this.borderLeft = borderLeft;
	}
	
	public Integer getBorderLeft() {
		return borderLeft;
	}
	
	public void setBorderRight(Integer borderRight) {
		this.borderRight = borderRight;
	}
	
	public Integer getBorderRight() {
		return borderRight;
	}
	
	public void setBorderTop(Integer borderTop) {
		this.borderTop = borderTop;
	}
	
	public Integer getBorderTop() {
		return borderTop;
	}
	
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public Integer getHeight() {
		return height;
	}
	
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	public Integer getWidth() {
		return width;
	}
	
	public void setIsBigSum(Integer isBigSum) {
		this.isBigSum = isBigSum;
	}
	
	public Integer getIsBigSum() {
		return isBigSum;
	}
	
	public void setIsSmallSum(Integer isSmallSum) {
		this.isSmallSum = isSmallSum;
	}
	
	public Integer getIsSmallSum() {
		return isSmallSum;
	}
	
	public void setIsRemark(Integer isRemark) {
		this.isRemark = isRemark;
	}
	
	public Integer getIsRemark() {
		return isRemark;
	}
	
	public void setIsYzxm(Integer isYzxm) {
		this.isYzxm = isYzxm;
	}
	
	public Integer getIsYzxm() {
		return isYzxm;
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
