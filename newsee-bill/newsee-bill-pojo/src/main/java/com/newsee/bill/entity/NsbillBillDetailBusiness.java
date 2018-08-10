/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 票据使用业务代码对应表
 * @version 1.0
 * @author
 */
public class NsbillBillDetailBusiness implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 自增id */
	@ApiModelProperty(value = "自增id")
	private Long id;
	
	/** 对应表名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "对应表名,1|text|0|300|2,1|text|1||2|0")
	private String tableName;
	
	/** 记录子表编号 */
	@ApiModelProperty(value = "记录子表编号")
	private Long billDetailId;
	
	/** 业务单号id */
	@ApiModelProperty(value = "业务单号id")
	private Long businessId;
	
	/** 备注说明,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "备注说明,1|text|0|300|2,1|text|1||2|0")
	private String remark;
	
	/** 标示票据业务是否存在 */
	@ApiModelProperty(value = "标示票据业务是否存在")
	private Integer isUsed;
	
	/** 是否取消 */
	@ApiModelProperty(value = "是否取消")
	private Integer isCanceled;
	
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
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setBillDetailId(Long billDetailId) {
		this.billDetailId = billDetailId;
	}
	
	public Long getBillDetailId() {
		return billDetailId;
	}
	
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	
	public Long getBusinessId() {
		return businessId;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
	
	public Integer getIsUsed() {
		return isUsed;
	}
	
	public void setIsCanceled(Integer isCanceled) {
		this.isCanceled = isCanceled;
	}
	
	public Integer getIsCanceled() {
		return isCanceled;
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
