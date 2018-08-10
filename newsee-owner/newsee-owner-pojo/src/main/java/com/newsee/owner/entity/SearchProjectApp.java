package com.newsee.owner.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class SearchProjectApp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "项目编号")
	private Long ProjectId;
	
	@ApiModelProperty(value = "项目名称")
	private String ProjectName;
	
	@ApiModelProperty(value = "项目Logo图片地址")
	private String LogoImg;
	
	@ApiModelProperty(value = "销售热线")
	private String SalesTelePhone;
	
	@ApiModelProperty(value = "销售均价")
	private String SalesPrice;
	
	@ApiModelProperty(value = "物业地址")
	private String PropertyAddress;
	
	@ApiModelProperty(value = "产品品类")
	private String ProjectCatagory;
	
	@ApiModelProperty(value = "所在城市名称")
	private String CityName;
	
	@ApiModelProperty(value = "所在城市ID")
	private Long CityID;
	
	@ApiModelProperty(value = "经度")
	private Float Longitude;
	
	@ApiModelProperty(value = "纬度")
	private Float Latitude;
	
	@ApiModelProperty(value = "子公司名称")
	private String BranchCompany;
	
	@ApiModelProperty(value = "所在区域")
	private String AreaName;
	
	@ApiModelProperty(value = "项目简称")
	private String ProjectShortName;

	
	public Long getProjectId() {
		return ProjectId;
	}

	public void setProjectId(Long projectId) {
		ProjectId = projectId;
	}

	public Long getCityID() {
		return CityID;
	}

	public void setCityID(Long cityID) {
		CityID = cityID;
	}

	public Float getLongitude() {
		return Longitude;
	}

	public void setLongitude(Float longitude) {
		Longitude = longitude;
	}

	public Float getLatitude() {
		return Latitude;
	}

	public void setLatitude(Float latitude) {
		Latitude = latitude;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public String getLogoImg() {
		return LogoImg;
	}

	public void setLogoImg(String logoImg) {
		LogoImg = logoImg;
	}

	public String getSalesTelePhone() {
		return SalesTelePhone;
	}

	public void setSalesTelePhone(String salesTelePhone) {
		SalesTelePhone = salesTelePhone;
	}

	public String getSalesPrice() {
		return SalesPrice;
	}

	public void setSalesPrice(String salesPrice) {
		SalesPrice = salesPrice;
	}

	public String getPropertyAddress() {
		return PropertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		PropertyAddress = propertyAddress;
	}

	public String getProjectCatagory() {
		return ProjectCatagory;
	}

	public void setProjectCatagory(String projectCatagory) {
		ProjectCatagory = projectCatagory;
	}

	public String getCityName() {
		return CityName;
	}

	public void setCityName(String cityName) {
		CityName = cityName;
	}

	public String getBranchCompany() {
		return BranchCompany;
	}

	public void setBranchCompany(String branchCompany) {
		BranchCompany = branchCompany;
	}

	public String getAreaName() {
		return AreaName;
	}

	public void setAreaName(String areaName) {
		AreaName = areaName;
	}

	public String getProjectShortName() {
		return ProjectShortName;
	}

	public void setProjectShortName(String projectShortName) {
		ProjectShortName = projectShortName;
	}
	
	
}
