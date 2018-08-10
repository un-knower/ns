/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 票据详细信息表
 * @version 1.0
 * @author
 */
public class NsbillBillInfoDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|0,1|text|0||1|1")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "组织id,1|number|0|0|0,1|text|0||1|1")
	private Long organizationId;
	
	/** 票据批号ID */
	@ApiModelProperty(value = "票据批号ID")
	private Long billId;
	
	/** 票据类型,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据类型,1|text|0|300|2,1|text|1||2|0")
	private String billType;
	
	/** 票据本名称,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据本名称,1|text|0|300|2,1|text|1||2|0")
	private String billDetailName;
	
	/** 票据面额,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据面额,1|text|0|300|2,1|text|1||2|0")
	private String pageBalance;
	
	/** 分类目录编号,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "分类目录编号,1|text|0|300|2,1|text|1||2|0")
	private String catalogId;
	
	/** 票据号从 */
	@ApiModelProperty(value = "票据号从")
	private Long fromNum;
	
	/** 票据号到 */
	@ApiModelProperty(value = "票据号到")
	private Long toNum;
	
	/** 是否被领用,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "是否被领用,1|text|0|300|2,1|text|1||2|0")
	private String isDraw;
	
	/** 启初数量 */
	@ApiModelProperty(value = "启初数量")
	private Long preNum;
	
	/** 已被领用数量 */
	@ApiModelProperty(value = "已被领用数量")
	private Long drawNum;
	
	/** 已被使用数量 */
	@ApiModelProperty(value = "已被使用数量")
	private Long usedNum;
	
	/** 已被废止数量 */
	@ApiModelProperty(value = "已被废止数量")
	private Long destroyNum;
	
	/** 已被销号数量 */
	@ApiModelProperty(value = "已被销号数量")
	private Long checkedNum;
	
	/** 备注说明,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "备注说明,1|text|0|300|2,1|text|1||2|0")
	private String remark;
	
	/** 票据号码中文前缀,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "票据号码中文前缀,1|text|0|300|2,1|text|1||2|0")
	private String cnprefix;
	
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
	
	/** isDelete */
	@ApiModelProperty(value = "isDelete")
	private Long isDelete;
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	public Long getOrganizationId() {
		return organizationId;
	}
	
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	
	public Long getBillId() {
		return billId;
	}
	
	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	public String getBillType() {
		return billType;
	}
	
	public void setBillDetailName(String billDetailName) {
		this.billDetailName = billDetailName;
	}
	
	public String getBillDetailName() {
		return billDetailName;
	}
	
	public void setPageBalance(String pageBalance) {
		this.pageBalance = pageBalance;
	}
	
	public String getPageBalance() {
		return pageBalance;
	}
	
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	
	public String getCatalogId() {
		return catalogId;
	}
	
	public void setFromNum(Long fromNum) {
		this.fromNum = fromNum;
	}
	
	public Long getFromNum() {
		return fromNum;
	}
	
	public void setToNum(Long toNum) {
		this.toNum = toNum;
	}
	
	public Long getToNum() {
		return toNum;
	}
	
	public void setIsDraw(String isDraw) {
		this.isDraw = isDraw;
	}
	
	public String getIsDraw() {
		return isDraw;
	}
	
	public void setPreNum(Long preNum) {
		this.preNum = preNum;
	}
	
	public Long getPreNum() {
		return preNum;
	}
	
	public void setDrawNum(Long drawNum) {
		this.drawNum = drawNum;
	}
	
	public Long getDrawNum() {
		return drawNum;
	}
	
	public void setUsedNum(Long usedNum) {
		this.usedNum = usedNum;
	}
	
	public Long getUsedNum() {
		return usedNum;
	}
	
	public void setDestroyNum(Long destroyNum) {
		this.destroyNum = destroyNum;
	}
	
	public Long getDestroyNum() {
		return destroyNum;
	}
	
	public void setCheckedNum(Long checkedNum) {
		this.checkedNum = checkedNum;
	}
	
	public Long getCheckedNum() {
		return checkedNum;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setCnprefix(String cnprefix) {
		this.cnprefix = cnprefix;
	}
	
	public String getCnprefix() {
		return cnprefix;
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
	
	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}
	
	public Long getIsDelete() {
		return isDelete;
	}
	
}
