/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 收费业务标志表
 * @version 1.0
 * @author
 */
public class NspaymentSysSubject implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 自增id */
	@ApiModelProperty(value = "自增id")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "组织id,1|number|0|0|0,1|text|0||1|1")
	private Long organizationId;
	
	/** 借贷标志,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "借贷标志,1|number|0|0|0,1|text|0||1|1")
	private Long isDebit;
	
	/** 是否是根目录,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "是否是根目录,1|number|0|0|0,1|text|0||1|1")
	private Long isRoot;
	
	/** 是否是叶子,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "是否是叶子,1|number|0|0|0,1|text|0||1|1")
	private Long isLeafage;
	
	/** 层级编号,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "层级编号,1|number|0|0|0,1|text|0||1|1")
	private Long subjectLevel;
	
	/** 根目录编号,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "根目录编号,1|text|0|0|0,1|text|0||1|1")
	private String rootId;
	
	/** 科目编号,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "科目编号,1|text|0|0|0,1|text|0||1|1")
	private String subjectCode;
	
	/** 科目名称,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "科目名称,1|text|0|0|0,1|text|0||1|1")
	private String subjectName;
	
	/** 代号,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "代号,1|text|0|0|0,1|text|0||1|1")
	private String shortCode;
	
	/** 父编号,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "父编号,1|text|0|0|0,1|text|0||1|1")
	private String parentId;
	
	/** 祖先节点编号,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "祖先节点编号,1|text|0|0|0,1|text|0||1|1")
	private String ancestorId;
	
	/** 备注,1|text|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "备注,1|text|0|0|0,1|text|0||1|1")
	private String summary;
	
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
	
	public void setIsDebit(Long isDebit) {
		this.isDebit = isDebit;
	}
	
	public Long getIsDebit() {
		return isDebit;
	}
	
	public void setIsRoot(Long isRoot) {
		this.isRoot = isRoot;
	}
	
	public Long getIsRoot() {
		return isRoot;
	}
	
	public void setIsLeafage(Long isLeafage) {
		this.isLeafage = isLeafage;
	}
	
	public Long getIsLeafage() {
		return isLeafage;
	}
	
	public void setSubjectLevel(Long subjectLevel) {
		this.subjectLevel = subjectLevel;
	}
	
	public Long getSubjectLevel() {
		return subjectLevel;
	}
	
	public void setRootId(String rootId) {
		this.rootId = rootId;
	}
	
	public String getRootId() {
		return rootId;
	}
	
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}
	
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	
	public String getShortCode() {
		return shortCode;
	}
	
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	public void setAncestorId(String ancestorId) {
		this.ancestorId = ancestorId;
	}
	
	public String getAncestorId() {
		return ancestorId;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getSummary() {
		return summary;
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
