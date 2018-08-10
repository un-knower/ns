/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 工单记录表
 * @version 1.0
 * @author
 */
public class NsSossServiceRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** id */
	@ApiModelProperty(value = "id")
	private Long id;
	
	/** 工单id */
	@ApiModelProperty(value = "工单id")
	private Long serviceId;
	
	/** 工单编号 */
	@ApiModelProperty(value = "工单编号")
	private String code;
	
	/** 工单处理前状态 */
	@ApiModelProperty(value = "工单处理前状态")
	private String oldStatus;
	
	/** 工单状态 */
	@ApiModelProperty(value = "工单状态")
	private String status;
	/**工单处理人ID*/
	@ApiModelProperty(value="工单处理人id")
	private Long handleUserID;
	/** 工单处理人 */
	@ApiModelProperty(value = "工单处理人")
	private String handleUsername;
	
	/** 处理人手机 */
	@ApiModelProperty(value = "处理人手机")
	private String handlePhone;
	
	/** 处理结果 */
	@ApiModelProperty(value = "处理结果")
	private String handleContent;
	
	/** 处理时间 */
	@ApiModelProperty(value = "处理时间")
	private Date handleTime;
	
	/** 备注 */
	@ApiModelProperty(value = "备注")
	private String remark;
	
	/** createTime */
	@ApiModelProperty(value = "createTime")
	private Date createTime;
	
	private String imgCode;
	
	private Integer userType;
	
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getImgCode() {
		return imgCode;
	}

	public Long getHandleUserID() {
		return handleUserID;
	}

	public void setHandleUserID(Long handleUserID) {
		this.handleUserID = handleUserID;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	
	public Long getServiceId() {
		return serviceId;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}
	
	public String getOldStatus() {
		return oldStatus;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setHandleUsername(String handleUsername) {
		this.handleUsername = handleUsername;
	}
	
	public String getHandleUsername() {
		return handleUsername;
	}
	
	public void setHandlePhone(String handlePhone) {
		this.handlePhone = handlePhone;
	}
	
	public String getHandlePhone() {
		return handlePhone;
	}
	
	public void setHandleContent(String handleContent) {
		this.handleContent = handleContent;
	}
	
	public String getHandleContent() {
		return handleContent;
	}
	
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	
	public Date getHandleTime() {
		return handleTime;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
}
