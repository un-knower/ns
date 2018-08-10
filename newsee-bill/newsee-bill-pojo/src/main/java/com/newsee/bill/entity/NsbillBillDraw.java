/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 票据领用表
 * @version 1.0
 * @author
 */
public class NsbillBillDraw implements Serializable {
	
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
	
	/** 票据本编号 */
	@ApiModelProperty(value = "票据本编号")
	private Long billDetailId;
	
	/** 领用日期 */
	@ApiModelProperty(value = "领用日期")
	private Date drawDate;
	
	/** 经手人ID */
	@ApiModelProperty(value = "经手人ID")
	private Long dealUserId;
	
	/** 经手人名称,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "经手人名称,1|text|0|300|2,1|text|1||2|0")
	private String dealUserName;
	
	/** 领用部门编号,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "领用部门编号,1|text|0|300|2,1|text|1||2|0")
	private String drawDepartmentId;
	
	/** 领用部门名称 */
	@ApiModelProperty(value = "领用部门名称")
	private Long drawDepartmentName;
	
	/** 领用人ID */
	@ApiModelProperty(value = "领用人ID")
	private Long drawUserId;
	
	/** 领用人名称,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "领用人名称,1|text|0|300|2,1|text|1||2|0")
	private String drawUserName;
	
	/** 领用数量 */
	@ApiModelProperty(value = "领用数量")
	private Long drawNum;
	
	/** 备注说明 */
	@ApiModelProperty(value = "备注说明")
	private String remark;
	
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
	
	/** subjectCode */
	@ApiModelProperty(value = "subjectCode")
	private String subjectCode;
	
	/** precinctId */
	@ApiModelProperty(value = "precinctId")
	private Long precinctId;
	
	/** useUserId */
	@ApiModelProperty(value = "useUserId")
	private Long useUserId;
	
		
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
	
	public void setBillDetailId(Long billDetailId) {
		this.billDetailId = billDetailId;
	}
	
	public Long getBillDetailId() {
		return billDetailId;
	}
	
	public void setDrawDate(Date drawDate) {
		this.drawDate = drawDate;
	}
	
	public Date getDrawDate() {
		return drawDate;
	}
	
	public void setDealUserId(Long dealUserId) {
		this.dealUserId = dealUserId;
	}
	
	public Long getDealUserId() {
		return dealUserId;
	}
	
	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}
	
	public String getDealUserName() {
		return dealUserName;
	}
	
	public void setDrawDepartmentId(String drawDepartmentId) {
		this.drawDepartmentId = drawDepartmentId;
	}
	
	public String getDrawDepartmentId() {
		return drawDepartmentId;
	}
	
	public void setDrawDepartmentName(Long drawDepartmentName) {
		this.drawDepartmentName = drawDepartmentName;
	}
	
	public Long getDrawDepartmentName() {
		return drawDepartmentName;
	}
	
	public void setDrawUserId(Long drawUserId) {
		this.drawUserId = drawUserId;
	}
	
	public Long getDrawUserId() {
		return drawUserId;
	}
	
	public void setDrawUserName(String drawUserName) {
		this.drawUserName = drawUserName;
	}
	
	public String getDrawUserName() {
		return drawUserName;
	}
	
	public void setDrawNum(Long drawNum) {
		this.drawNum = drawNum;
	}
	
	public Long getDrawNum() {
		return drawNum;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
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
	
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}
	
	public void setPrecinctId(Long precinctId) {
		this.precinctId = precinctId;
	}
	
	public Long getPrecinctId() {
		return precinctId;
	}
	
	public void setUseUserId(Long useUserId) {
		this.useUserId = useUserId;
	}
	
	public Long getUseUserId() {
		return useUserId;
	}
	
}
