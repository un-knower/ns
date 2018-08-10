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
 * 产品服务订单表
 * @version 1.0
 * @author
 */
public class NsSossProductOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 订单ID */
	@ApiModelProperty(value = "订单ID")
	private Long id;
	
	@ApiModelProperty(value = "订单编号")
	private String orderCode;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 企业名称,1|text|0|200|1,1|text|0||1|0 */
	@ApiModelProperty(value = "企业名称,1|text|0|200|1,1|text|0||1|0")
	private String enterpriseName;
	
	/** 付款金额,1|text|0|150|10,1|numbertext|0||10|0 */
	@ApiModelProperty(value = "付款金额,1|text|0|150|10,1|numbertext|0||10|0")
	private BigDecimal payMoney;
	
	/** 付款时间,1|date|0|200|11,1|timepicker|0||11|0 */
	@ApiModelProperty(value = "付款时间,1|date|0|200|11,1|timepicker|0||11|0")
	private Date payTime;
	
	/** 服务订单状态,1|select|0|150|12,1|select|0||12|0,0:暂存|1:待支付|2:支付成功|3:支付失败 */
	@ApiModelProperty(value = "服务订单状态,1|select|0|150|12,1|select|0||12|0,0:暂存|1:待支付|2:支付成功|3:支付失败")
	private String payStatus;
	
	/** 服务订单是否删除0 否 1 是 */
	@ApiModelProperty(value = "服务订单是否删除0 否 1 是")
	private Integer isDelete;
	
	/** 服务开始时间 */
	@ApiModelProperty(value = "服务开始时间")
	private Date serviceStartTime;
	
	/** 服务截止时间 */
	@ApiModelProperty(value = "服务截止时间")
	private Date serviceEndTime;
	
	/** 服务项目数 */
	@ApiModelProperty(value = "服务项目数")
	private Integer serviceTotalCount;
	
	/** 服务面积 */
	@ApiModelProperty(value = "服务面积")
	private Long serviceTotalArea;
	
	/** 备注 */
	@ApiModelProperty(value = "备注")
	private String remark;
	
	/** createTime */
	@ApiModelProperty(value = "createTime")
	private Date createTime;
	
	/** createUserId */
	@ApiModelProperty(value = "createUserId")
	private Long createUserId;
	
	/** createUserName */
	@ApiModelProperty(value = "createUserName")
	private String createUserName;
	
	/** updateTime */
	@ApiModelProperty(value = "updateTime")
	private Date updateTime;
	
	/** updateUserId */
	@ApiModelProperty(value = "updateUserId")
	private Long updateUserId;
	
	/** updateUserName */
	@ApiModelProperty(value = "updateUserName")
	private String updateUserName;
	

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
	public String getEnterpriseName() {
		return enterpriseName;
	}
	
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	public Date getPayTime() {
		return payTime;
	}
	
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getPayStatus() {
		return payStatus;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}
	
	public void setServiceStartTime(Date serviceStartTime) {
		this.serviceStartTime = serviceStartTime;
	}
	
	public Date getServiceStartTime() {
		return serviceStartTime;
	}
	
	public void setServiceEndTime(Date serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}
	
	public Date getServiceEndTime() {
		return serviceEndTime;
	}
	
	public void setServiceTotalCount(Integer serviceTotalCount) {
		this.serviceTotalCount = serviceTotalCount;
	}
	
	public Integer getServiceTotalCount() {
		return serviceTotalCount;
	}
	
	public void setServiceTotalArea(Long serviceTotalArea) {
		this.serviceTotalArea = serviceTotalArea;
	}
	
	public Long getServiceTotalArea() {
		return serviceTotalArea;
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
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
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
	
}
