/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.generator.jepf;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典类型组
 * @version 1.0
 * @author
 */
public class NsCoreDictionarygroup implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 数据字典类型组ID */
	private Long dictionaryGroupId;
	
	/** 所属企业ID */
	private Long enterpriseId;
	
	/** 所属组织ID */
	private Long organizationId;
	
	/** 数据字典类型组名称 */
	private String dictionaryGroupName;
	
	/** 0：未删除，1：已删除 */
	private Integer isDeleted;
	
	/** 备注 */
	private String remark;
	
	/** createUserId */
	private Long createUserId;
	
	/** createTime */
	private Date createTime;
	
	/** updateUserId */
	private Long updateUserId;
	
	/** updateTime */
	private Date updateTime;
	
		
	public void setDictionaryGroupId(Long dictionaryGroupId) {
		this.dictionaryGroupId = dictionaryGroupId;
	}
	
	public Long getDictionaryGroupId() {
		return dictionaryGroupId;
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
	
	public void setDictionaryGroupName(String dictionaryGroupName) {
		this.dictionaryGroupName = dictionaryGroupName;
	}
	
	public String getDictionaryGroupName() {
		return dictionaryGroupName;
	}
	
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Integer getIsDeleted() {
		return isDeleted;
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
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
}
