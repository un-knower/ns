/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 组织_部门
 * @version 1.0
 * @author
 */
public class NsSystemDepartment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 部门ID */
	@ApiModelProperty(value = "部门ID")
	private Long departmentId;
	
	/** enterpriseId */
	@ApiModelProperty(value = "enterpriseId")
	private Long enterpriseId;
	
	/** 所属集团 */
	@ApiModelProperty(value = "所属集团")
	private Long groupId;
	
	/** 所属公司 */
	@ApiModelProperty(value = "所属公司")
	private Long companyId;
	
	/** 上级部门ID */
	@ApiModelProperty(value = "上级部门ID")
	private Long departmentParentId;
	
	/** 部门编号 */
    @ApiModelProperty(value = "部门编号")
    private String departmentCode;
	
	/** 部门名称 */
	@ApiModelProperty(value = "部门名称")
	private String departmentName;
	
	/** 部门简称 */
	@ApiModelProperty(value = "部门简称")
	private String departmentShortName;
	
	/** 法定名称 */
	@ApiModelProperty(value = "法定名称")
	private String departmentLegalName;
	
	/** 部门类型：0=普通部门，1=虚拟部门，2=职能中心，3=服务中心 */
	@ApiModelProperty(value = "部门类型：0=普通部门，1=虚拟部门，2=职能中心，3=服务中心")
	private Integer departmentType;
	
	/** 所有上级部门（ID拼接） */
	@ApiModelProperty(value = "所有上级部门（ID拼接）")
	private String departmentPath;
	
	/** 启用状态 ：1=未启用，2=已启用，3=已停用 */
	@ApiModelProperty(value = "启用状态 ：1=未启用，2=已启用，3=已停用")
	private Integer departmentEnablestate;
	
	/** 部门负责人 */
	@ApiModelProperty(value = "部门负责人")
	private Long departmentManagerId;
	
	/** 成立日期 */
	@ApiModelProperty(value = "成立日期")
	private Date departmentBuildDate;
	
	/** 部门性质 */
	@ApiModelProperty(value = "部门性质")
	private Integer departmentNature;
	
	/** 排序编号 */
	@ApiModelProperty(value = "排序编号")
	private Integer orderNo;
	
	/** 是否删除：0=已删，1=未删 */
	@ApiModelProperty(value = "是否删除：0=已删，1=未删")
	private Integer isDeleted;
	
	/** 备注 */
	@ApiModelProperty(value = "备注")
	private String remark;
	
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
	
		
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	public Long getDepartmentId() {
		return departmentId;
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
	
	public void setDepartmentParentId(Long departmentParentId) {
		this.departmentParentId = departmentParentId;
	}
	
	public Long getDepartmentParentId() {
		return departmentParentId;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	
	public void setDepartmentShortName(String departmentShortName) {
		this.departmentShortName = departmentShortName;
	}
	
	public String getDepartmentShortName() {
		return departmentShortName;
	}
	
	public void setDepartmentLegalName(String departmentLegalName) {
		this.departmentLegalName = departmentLegalName;
	}
	
	public String getDepartmentLegalName() {
		return departmentLegalName;
	}
	
	public void setDepartmentType(Integer departmentType) {
		this.departmentType = departmentType;
	}
	
	public Integer getDepartmentType() {
		return departmentType;
	}
	
	public void setDepartmentPath(String departmentPath) {
		this.departmentPath = departmentPath;
	}
	
	public String getDepartmentPath() {
		return departmentPath;
	}
	
	public void setDepartmentEnablestate(Integer departmentEnablestate) {
		this.departmentEnablestate = departmentEnablestate;
	}
	
	public Integer getDepartmentEnablestate() {
		return departmentEnablestate;
	}
	
	public void setDepartmentManagerId(Long departmentManagerId) {
		this.departmentManagerId = departmentManagerId;
	}
	
	public Long getDepartmentManagerId() {
		return departmentManagerId;
	}
	
	public void setDepartmentBuildDate(Date departmentBuildDate) {
		this.departmentBuildDate = departmentBuildDate;
	}
	
	public Date getDepartmentBuildDate() {
		return departmentBuildDate;
	}
	
	public void setDepartmentNature(Integer departmentNature) {
		this.departmentNature = departmentNature;
	}
	
	public Integer getDepartmentNature() {
		return departmentNature;
	}
	
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	public Integer getOrderNo() {
		return orderNo;
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

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
