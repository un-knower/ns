/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.common.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 组织_公司
 * @version 1.0
 * @author
 */
public class NsSystemCompany implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 公司ID */
	@ApiModelProperty(value = "公司ID")
	private Long companyId;
	
	/** 所属企业ID */
	@ApiModelProperty(value = "所属企业ID")
	private Long enterpriseId;
	
	/** 所属集团 */
    @ApiModelProperty(value = "所属集团")
    private Long groupId;
	
	/** 上级公司ID */
	@ApiModelProperty(value = "上级公司ID")
	private Long companyParentId;
	
	/** 公司编号 */
	@ApiModelProperty(value = "公司编号")
	private String companyCode;
	
	/** 公司名称 */
	@ApiModelProperty(value = "公司名称")
	private String companyName;
	
	/** 法定名称 */
	@ApiModelProperty(value = "法定名称")
	private String companyLegalName;
	
	/** 公司简称 */
	@ApiModelProperty(value = "公司简称")
	private String companyShortName;
	
	/** 公司负责人 */
	@ApiModelProperty(value = "公司负责人")
	private Long companyManagerId;
	
	/** 公司成立日期 */
	@ApiModelProperty(value = "公司成立日期")
	private Date companyBuildDate;
	
	/** 公司路径 */
	@ApiModelProperty(value = "公司路径")
	private String companyPath;
	
	/** 公司性质 */
	@ApiModelProperty(value = "公司性质")
	private Integer companyNature;
	
	/** 省ID */
	@ApiModelProperty(value = "省ID")
	private Integer provinceId;
	
	/** 市ID */
	@ApiModelProperty(value = "市ID")
	private Integer cityId;
	
	/** 详细地址 */
	@ApiModelProperty(value = "详细地址")
	private String address;
	
	/** 排序编号 */
	@ApiModelProperty(value = "排序编号")
	private Integer orderNo;
	
	/** 是否删除 */
	@ApiModelProperty(value = "是否删除")
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
	
		
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	public Long getCompanyId() {
		return companyId;
	}
	
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	public void setCompanyParentId(Long companyParentId) {
		this.companyParentId = companyParentId;
	}
	
	public Long getCompanyParentId() {
		return companyParentId;
	}
	
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	public String getCompanyCode() {
		return companyCode;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyLegalName(String companyLegalName) {
		this.companyLegalName = companyLegalName;
	}
	
	public String getCompanyLegalName() {
		return companyLegalName;
	}
	
	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}
	
	public String getCompanyShortName() {
		return companyShortName;
	}
	
	public void setCompanyManagerId(Long companyManagerId) {
		this.companyManagerId = companyManagerId;
	}
	
	public Long getCompanyManagerId() {
		return companyManagerId;
	}
	
	public void setCompanyBuildDate(Date companyBuildDate) {
		this.companyBuildDate = companyBuildDate;
	}
	
	public Date getCompanyBuildDate() {
		return companyBuildDate;
	}
	
	public void setCompanyPath(String companyPath) {
		this.companyPath = companyPath;
	}
	
	public String getCompanyPath() {
		return companyPath;
	}
	
	public void setCompanyNature(Integer companyNature) {
		this.companyNature = companyNature;
	}
	
	public Integer getCompanyNature() {
		return companyNature;
	}
	
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	
	public Integer getProvinceId() {
		return provinceId;
	}
	
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public Integer getCityId() {
		return cityId;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
	
}
