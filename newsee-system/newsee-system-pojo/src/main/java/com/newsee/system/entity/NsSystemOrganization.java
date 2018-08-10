/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 组织
 * @version 1.0
 * @author
 */
public class NsSystemOrganization implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 组织ID */
	@ApiModelProperty(value = "组织ID")
	private Long organizationId;
	
	/** enterpriseId */
	@ApiModelProperty(value = "enterpriseId")
	private Long enterpriseId;
	
	/** 所属集团 */
	@ApiModelProperty(value = "所属集团")
	private Long groupId;
	
	/** 所属公司 */
	@ApiModelProperty(value = "所属公司")
	private Long companyId;
	
	/** 所属部门 */
	@ApiModelProperty(value = "所属部门")
	private Long departmentId;
	
	/** 父级组织ID */
	@ApiModelProperty(value = "父级组织ID")
	private Long organizationParentId;
	
	/** 组织名称 */
	@ApiModelProperty(value = "组织名称")
	private String organizationName;
	
	/** 组织简称 */
    @ApiModelProperty(value = "组织简称")
    private String organizationShortName;
	
	/** 组织编码 */
    @ApiModelProperty(value = "组织名称")
    private String organizationCode;
	
	/** 组织类型：0 集团、1 公司、2 职能中心、3服务中心 */
	@ApiModelProperty(value = "组织类型：0 集团、1 公司、2 职能中心、3服务中心")
	private Integer organizationType;
	
	/** 启用状态 ：1 未启用、2 已启用、3 已停用 */
	@ApiModelProperty(value = "启用状态 ：1 未启用、2 已启用、3 已停用")
	private Integer organizationEnablestate;
	
	/** 路径（父级组织ID拼接） */
	@ApiModelProperty(value = "路径（父级组织ID拼接）")
	private String organizationPath;
	
	/** 层级 */
	@ApiModelProperty(value = "层级")
	private Integer organizationLevel;
	
	/** 排序 */
	@ApiModelProperty(value = "排序")
	private Integer organizationOrdercolumn;
	
	/** 备注 */
	@ApiModelProperty(value = "备注")
	private String remark;
	
	/** 0：未删除，1：已删除 */
    @ApiModelProperty(value = "0：未删除，1：已删除")
    private Integer isDeleted;
	
	/** 版本主键 (预留字段) */
	@ApiModelProperty(value = "版本主键 (预留字段)")
	private Long versionId;
	
	/** createUserId */
	@ApiModelProperty(value = "createUserId")
	private Long createUserId;
	
	/** createUserName */
	@ApiModelProperty(value = "createUserName")
	private String createUserName;
	
	/** createTime */
	@ApiModelProperty(value = "createTime")
	private Date createTime;
	
	/** updateUserId */
	@ApiModelProperty(value = "updateUserId")
	private Long updateUserId;
	
	/** updateUserName */
	@ApiModelProperty(value = "updateUserName")
	private String updateUserName;
	
	/** updateTime */
	@ApiModelProperty(value = "updateTime")
	private Date updateTime;
	
		
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	public Long getOrganizationId() {
		return organizationId;
	}
	
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	public Long getGroupId() {
		return groupId;
	}
	
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	public Long getCompanyId() {
		return companyId;
	}
	
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	public Long getDepartmentId() {
		return departmentId;
	}
	
	public void setOrganizationParentId(Long organizationParentId) {
		this.organizationParentId = organizationParentId;
	}
	
	public Long getOrganizationParentId() {
		return organizationParentId;
	}
	
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	public String getOrganizationName() {
		return organizationName;
	}
	
	public void setOrganizationType(Integer organizationType) {
		this.organizationType = organizationType;
	}
	
	public Integer getOrganizationType() {
		return organizationType;
	}
	
	public void setOrganizationEnablestate(Integer organizationEnablestate) {
		this.organizationEnablestate = organizationEnablestate;
	}
	
	public Integer getOrganizationEnablestate() {
		return organizationEnablestate;
	}
	
	public void setOrganizationPath(String organizationPath) {
		this.organizationPath = organizationPath;
	}
	
	public String getOrganizationPath() {
		return organizationPath;
	}
	
	public void setOrganizationLevel(Integer organizationLevel) {
		this.organizationLevel = organizationLevel;
	}
	
	public Integer getOrganizationLevel() {
		return organizationLevel;
	}
	
	public void setOrganizationOrdercolumn(Integer organizationOrdercolumn) {
		this.organizationOrdercolumn = organizationOrdercolumn;
	}
	
	public Integer getOrganizationOrdercolumn() {
		return organizationOrdercolumn;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}
	
	public Long getVersionId() {
		return versionId;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getOrganizationShortName() {
        return organizationShortName;
    }

    public void setOrganizationShortName(String organizationShortName) {
        this.organizationShortName = organizationShortName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }
	
}
