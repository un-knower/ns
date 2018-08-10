/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.newsee.soss.entity.NsSossProductOrderPrecinct;
import com.newsee.soss.entity.NsSossProductOrderProduct;

import io.swagger.annotations.ApiModelProperty;

/**
 * ProductOrderVo
 * @version 1.0
 * @author
 */
public class ProductOrderVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "订单编号")
	private String orderCode;

	/** 企业名称,1|text|0|200|1,1|text|0||1|0 */
	@ApiModelProperty(value = "企业名称,1|text|0|200|1,1|text|0||1|0")
	private String enterpriseName;
	
	/** 付款金额,1|text|0|150|10,1|numbertext|0||10|0 */
	@ApiModelProperty(value = "付款金额,1|text|0|150|10,1|numbertext|0||10|0")
	private BigDecimal payMoney;
	
	/** 付款时间,1|date|0|200|11,1|timepicker|0||11|0 */
	@ApiModelProperty(value = "付款时间,1|date|0|200|11,1|timepicker|0||11|0")
	private Date payTime;
	
	/** 服务订单状态,1|select|0|150|12,1|select|0||12|0,0:申请购买|1:待支付|2:支付成功|3:支付失败 */
	@ApiModelProperty(value = "服务订单状态,1|select|0|150|12,1|select|0||12|0,0:申请购买|1:待支付|2:支付成功|3:支付失败")
	private String payStatus;
	
	/** 订单产品ID,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "订单产品ID,1|number|0|0|1,1|numbertext|0||1|1")
	private Long id;
	
	/** 订单id,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "订单id,1|number|0|0|1,1|numbertext|0||1|1")
	private Long productOrderId;
	
	/** 产品名,1|text|0|200|3,1|text|0||3|0 */
	@ApiModelProperty(value = "产品名,1|text|0|200|3,1|text|0||3|0")
	private String productName;
	
	/** 产品费用,0,1|numbertext|0||4|1 */
	@ApiModelProperty(value = "产品费用,0,1|numbertext|0||4|1")
	private Long price;
	
	/** 服务项目数量,1|number|0|150|4,1|numbertext|0||4|0 */
	@ApiModelProperty(value = "服务项目数量,1|number|0|150|4,1|numbertext|0||4|0")
	private Integer serviceCount;
	
	/** 服务面积,1|number|0|150|5,1|numbertext|0||5|0 */
	@ApiModelProperty(value = "服务面积,1|number|0|150|5,1|numbertext|0||5|0")
	private Long serviceArea;
	
	/** 产品是否试用,1|select|0|150|16,1|radio|0||16|0,0:否|1:是 */
	@ApiModelProperty(value = "产品是否试用,1|select|0|150|16,1|radio|0||16|0,0:否|1:是")
	private String isTrial;
	//试用时间（月）
	private Integer trialCycle;
	
	/** 服务是否开通,1|select|0|150|15,1|radio|0||15|0,0:待开通|1:已开通|2:关闭 */
	@ApiModelProperty(value = "服务是否开通,1|select|0|150|15,1|radio|0||15|0,0:待开通|1:已开通|2:关闭")
	private String serviceWork;
	
	/** 服务开始时间,1|date|0|200|13,1|datepicker|1||13|0 */
	@ApiModelProperty(value = "服务开始时间,1|date|0|200|13,1|datepicker|1||13|0")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startTime;
	
	/** 服务截止时间,1|date|0|200|14,1|datepicker|1||14|0 */
	@ApiModelProperty(value = "服务截止时间,1|date|0|200|14,1|datepicker|1||14|0")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date endTime;
	
	/** 服务项目id,0,1|numbertext|0||7|1 */
	@ApiModelProperty(value = "服务项目id,0,1|numbertext|0||7|1")
	private Long precinctId;
	@ApiModelProperty(value = "续费时长,,")
	private Date renewEndTime;
	
	public Date getRenewEndTime() {
		return renewEndTime;
	}

	public void setRenewEndTime(Date renewEndTime) {
		this.renewEndTime = renewEndTime;
	}

	/** 服务项目名称,0,1|text|0||8|0 */
	@ApiModelProperty(value = "服务项目名称,0,1|text|0||8|0")
	private String precinctName;
	
	/** 服务项目面积,0,1|numbertext|0||9|0 */
	@ApiModelProperty(value = "服务项目面积,0,1|numbertext|0||9|0")
	private Long precinctArea;
	/**服务项目*/
	private List<NsSossProductOrderPrecinct> precinctList;
	/**购买产品*/
	private NsSossProductOrderProduct orderProduct;
	private Long enterpriseId;
	private Long createUserId;
	private Date createTime;
	private String createUserName;
	private Integer buyCycle; //购买周期，已（月）为单位
	private Date updateTime;
	private Long updateUserId;
	private String updateUserName;
	//产品类型
	private String productType;	
	private String productTypeName;	
	//产品服务状态
	private String serviceStatus;
	//订单服务总项目数
	private Integer serviceTotalCount;
	//订单服务总面积
	private Long serviceTotalArea;
	//备注
	private String remark;
	//产品简介
	private String introduce;
	
	
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public Integer getServiceTotalCount() {
		return serviceTotalCount;
	}

	public void setServiceTotalCount(Integer serviceTotalCount) {
		this.serviceTotalCount = serviceTotalCount;
	}

	public Long getServiceTotalArea() {
		return serviceTotalArea;
	}

	public void setServiceTotalArea(Long serviceTotalArea) {
		this.serviceTotalArea = serviceTotalArea;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTrialCycle() {
		return trialCycle;
	}

	public void setTrialCycle(Integer trialCycle) {
		this.trialCycle = trialCycle;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public NsSossProductOrderProduct getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(NsSossProductOrderProduct orderProduct) {
		this.orderProduct = orderProduct;
	}

	public Integer getBuyCycle() {
		return buyCycle;
	}

	public void setBuyCycle(Integer buyCycle) {
		this.buyCycle = buyCycle;
	}

	public List<NsSossProductOrderPrecinct> getPrecinctList() {
		return precinctList;
	}

	public void setPrecinctList(List<NsSossProductOrderPrecinct> precinctList) {
		this.precinctList = precinctList;
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

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setProductOrderId(Long productOrderId) {
		this.productOrderId = productOrderId;
	}
	
	public Long getProductOrderId() {
		return productOrderId;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public Long getPrice() {
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
	
	public void setIsTrial(String isTrial) {
		this.isTrial = isTrial;
	}
	
	public String getIsTrial() {
		return isTrial;
	}
	
	public void setServiceWork(String serviceWork) {
		this.serviceWork = serviceWork;
	}
	
	public String getServiceWork() {
		return serviceWork;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setPrecinctId(Long precinctId) {
		this.precinctId = precinctId;
	}
	
	public Long getPrecinctId() {
		return precinctId;
	}
	
	public void setPrecinctName(String precinctName) {
		this.precinctName = precinctName;
	}
	
	public String getPrecinctName() {
		return precinctName;
	}
	
	public void setPrecinctArea(Long precinctArea) {
		this.precinctArea = precinctArea;
	}
	
	public Long getPrecinctArea() {
		return precinctArea;
	}
	
}
