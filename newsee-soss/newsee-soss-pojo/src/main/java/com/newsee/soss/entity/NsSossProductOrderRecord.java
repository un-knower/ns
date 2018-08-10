package com.newsee.soss.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 产品订单记录表
 * @author admin
 */
public class NsSossProductOrderRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	  private Long recordID;
	  private Long productOrderID;// '订单表ID'
	  private BigDecimal payMoney;//'订单价格',
	  private String productName;// '产品名称',
	  private String productCode;//'产品编码',
	  private Date createTime;
	  private Long createUserId;
	  private String createUserName;
	  private Date serviceStartTime;
	  private Date serviceEndTime;
	  private Integer payStatus;//'服务状态|0未缴费|1已缴费'
	  private Integer isLast;//'是否是对应订单的最新订单|0否|1是',
	  private Long serviceArea;//服务面积
	  private Integer serviceCount;//服务项目数
	  
	public Long getServiceArea() {
		return serviceArea;
	}
	public void setServiceArea(Long serviceArea) {
		this.serviceArea = serviceArea;
	}
	public Integer getServiceCount() {
		return serviceCount;
	}
	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}
	public Long getRecordID() {
		return recordID;
	}
	public void setRecordID(Long recordID) {
		this.recordID = recordID;
	}
	public Long getProductOrderID() {
		return productOrderID;
	}
	public void setProductOrderID(Long productOrderID) {
		this.productOrderID = productOrderID;
	}
	
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public Date getServiceStartTime() {
		return serviceStartTime;
	}
	public void setServiceStartTime(Date serviceStartTime) {
		this.serviceStartTime = serviceStartTime;
	}
	public Date getServiceEndTime() {
		return serviceEndTime;
	}
	public void setServiceEndTime(Date serviceEndTime) {
		this.serviceEndTime = serviceEndTime;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public Integer getIsLast() {
		return isLast;
	}
	public void setIsLast(Integer isLast) {
		this.isLast = isLast;
	}
	  

}
