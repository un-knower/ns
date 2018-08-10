/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

/**
 * BillUseManVo
 * @version 1.0
 * @author
 */
public class BillUseManVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 自增id */
	@ApiModelProperty(value = "自增id")
	private Long id;

	/** 票据号 */
	@ApiModelProperty(value = "票据号")
	private String billNum;

	/** 票据类型 */
	@ApiModelProperty(value = "票据类型")
	private String billType;

	/** 客户ID */
	@ApiModelProperty(value = "客户ID")
	private Long customerId;

	/** 客户ID */
	@ApiModelProperty(value = "客户名称")
	private Long customerName;

	/** 房产 */
	@ApiModelProperty(value = "房产")
	private String houseList;

	/** 票据金额 */
	@ApiModelProperty(value = "票据金额")
	private BigDecimal billFund;

	@ApiModelProperty(value = "票据状态，未使用，已启用，已缴费，已核销，已废弃，已冲销，已交账，已挂账，已更换")
	private String billStatus;

	/** 收费科目id */
	@ApiModelProperty(value = "收费科目Id")
	private Long itemId;
	/** 收费科目 */
	@ApiModelProperty(value = "收费科目")
	private String itemName;

	/** 票据开具日期/开票人 */
	@ApiModelProperty(value = "票据开具日期")
	private String usedInfo;
	/** 领用日期/领用人 */
	@ApiModelProperty(value = "领用日期/领用人")
	private String drawInfo;

	/** 处理日期/处理人:updateTime/updateUserName */
	@ApiModelProperty(value = "处理日期/处理人")
	private String handleInfo;

	/** 更新人姓名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "更新人姓名,1|text|0|300|2,1|text|1||2|0")
	private String updateUserName;
	
	/** 对应表名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "对应表名,1|text|0|300|2,1|text|1||2|0")
	private String tableName;
	
	/** 创建时间,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "创建时间,1|text|0|300|2,1|text|1||2|0")
	private Date createTime;
	
	/** 更新时间,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "更新时间,1|text|0|300|2,1|text|1||2|0")
	private Date updateTime;
	
	/** 创建人姓名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "创建人姓名,1|text|0|300|2,1|text|1||2|0")
	private String createUserName;
	
	/** 备注说明,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "备注说明,1|text|0|300|2,1|text|1||2|0")
	private String remark;
	
		
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	
	public String getUpdateUserName() {
		return updateUserName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillNum() {
		return billNum;
	}

	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerName() {
		return customerName;
	}

	public void setCustomerName(Long customerName) {
		this.customerName = customerName;
	}

	public String getHouseList() {
		return houseList;
	}

	public void setHouseList(String houseList) {
		this.houseList = houseList;
	}

	public BigDecimal getBillFund() {
		return billFund;
	}

	public void setBillFund(BigDecimal billFund) {
		this.billFund = billFund;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUsedInfo() {
		return usedInfo;
	}

	public void setUsedInfo(String usedInfo) {
		this.usedInfo = usedInfo;
	}

	public String getDrawInfo() {
		return drawInfo;
	}

	public void setDrawInfo(String drawInfo) {
		this.drawInfo = drawInfo;
	}

	public String getHandleInfo() {
		return handleInfo;
	}

	public void setHandleInfo(String handleInfo) {
		this.handleInfo = handleInfo;
	}
}
