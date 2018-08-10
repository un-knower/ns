package com.newsee.common.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class SearchProjectVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6275918208434503722L;

	/** 企业ID */
	@ApiModelProperty(value = "企业ID; 注：检索时，不用传此参数,自动传入登录人所在企业ID")
    private Long enterpriseId;
    
    /** 组织ID */
	@ApiModelProperty(value = "组织ID")
    private Long organizationId;
	
	/** 城市编号 */
	@ApiModelProperty(value = "城市编号")
	private Integer cityId;
	
	/** 项目名称 */
	@ApiModelProperty(value = "项目名称")
	private String projectName;
	
	/** 项目ID */
	@ApiModelProperty(value = "项目ID")
	private Integer projectId;
	
	/** 区域 */
	@ApiModelProperty(value = "区域")
	private String areaName;
	
	/** 分子公司 */
	@ApiModelProperty(value = "分子公司")
	private String branchCompany;

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getBranchCompany() {
		return branchCompany;
	}

	public void setBranchCompany(String branchCompany) {
		this.branchCompany = branchCompany;
	}
	
	
}
