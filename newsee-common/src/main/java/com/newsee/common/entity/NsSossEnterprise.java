/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.common.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 企业租户
 * @version 1.0
 * @author
 */
public class NsSossEnterprise implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String param_address = "P#C#D#S#";

	/** 企业ID,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "企业ID,1|number|0|0|1,1|numbertext|0||1|1")
	private Long enterpriseId;
	
	/** 企业名称,1|text|0|200|2,1|text|0||2|0 */
	@ApiModelProperty(value = "企业名称,1|text|0|200|2,1|text|0||2|0")
	private String name;
	
	/** 企业营业执照号码,1|text|0|200|3,1|text|0||3|0 */
	@ApiModelProperty(value = "企业营业执照号码,1|text|0|200|3,1|text|0||3|0")
	private String businessLicense;
	
	/** 企业编号,1|text|0|200|4,1|text|0||4|0 */
	@ApiModelProperty(value = "企业编号,1|text|0|200|4,1|text|0||4|0")
	private String code;
	
	/** 开通项目数,1|number|0|150|5,1|label|0||5|0 */
	@ApiModelProperty(value = "开通项目数,1|number|0|150|5,1|label|0||5|0")
	private String precinctAmount;
	
	/** 省ID */
	@ApiModelProperty(value = "省code")
	private String province;
	
	/** 市ID */
	@ApiModelProperty(value = "市code")
	private String city;
	
	/** 区ID */
	@ApiModelProperty(value = "区code")
	private String district;
	
	/** 街道ID */
	@ApiModelProperty(value = "街道code")
	private String street;
	
	/** 企业详细地址,1|text|0|300|15,1|text|0||15|0 */
	@ApiModelProperty(value = "企业详细地址,1|text|0|300|15,1|text|0||15|0")
	private String address;
	
	/** 有效期开始时间,1|date|0|200|6,1|datepicker|0||6|0 */
	@ApiModelProperty(value = "有效期开始时间,1|date|0|200|6,1|datepicker|0||6|0")
	private Date validdateStartTime;
	
	/** 有效期结束时间,1|date|0|200|7,1|datepicker|0||7|0 */
	@ApiModelProperty(value = "有效期结束时间,1|date|0|200|7,1|datepicker|0||7|0")
	private Date validdateEndTime;
	
	/** 企业状态,1|select|0|150|20,1|radio|0||20|0,0:正常|1:冻结|2:已过期 */
	@ApiModelProperty(value = "企业状态,1|select|0|150|20,1|radio|0||20|0,0:正常|1:冻结|2:已过期")
	private String enterpriseState;
	
	/** 企业税号,1|text|0|200|10,1|text|0||10|0 */
	@ApiModelProperty(value = "企业税号,1|text|0|200|10,1|text|0||10|0")
	private String einCode;
	
	/** 企业服务电话,1|text|0|200|11,1|text|0|tel|11|0 */
	@ApiModelProperty(value = "企业服务电话,1|text|0|200|11,1|text|0|tel|11|0")
	private String serviceTel;
	
	/** 0：未删除，1：已删除 */
	@ApiModelProperty(value = "0：未删除，1：已删除")
	private Integer isDelete;
	
	/** 备注,1|text|0|300|19,1|textarea|0||19|0 */
	@ApiModelProperty(value = "备注,1|text|0|300|19,1|textarea|0||19|0")
	private String remark;
	
	/**是否运营企业*/
	private Integer isOperation;
	
	/** createUserId */
	@ApiModelProperty(value = "createUserId")
	private Long createUserId;
	
	/** 申请人,1|text|0|200|13,1|text|0||13|0 */
	@ApiModelProperty(value = "申请人,1|text|0|200|13,1|text|0||13|0")
	private String createUserName;
	
	/** 申请时间,1|date|0|200|14,1|timepicker|0||14|0 */
	@ApiModelProperty(value = "申请时间,1|date|0|200|14,1|timepicker|0||14|0")
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
	
	
	public Integer getIsOperation() {
		return isOperation;
	}

	public void setIsOperation(Integer isOperation) {
		this.isOperation = isOperation;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	
	public String getBusinessLicense() {
		return businessLicense;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setPrecinctAmount(String precinctAmount) {
		this.precinctAmount = precinctAmount;
	}
	
	public String getPrecinctAmount() {
		return precinctAmount;
	}
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setValiddateStartTime(Date validdateStartTime) {
		this.validdateStartTime = validdateStartTime;
	}
	
	public Date getValiddateStartTime() {
		return validdateStartTime;
	}
	
	public void setValiddateEndTime(Date validdateEndTime) {
		this.validdateEndTime = validdateEndTime;
	}
	
	public Date getValiddateEndTime() {
		return validdateEndTime;
	}
	
	public void setEnterpriseState(String enterpriseState) {
		this.enterpriseState = enterpriseState;
	}
	
	public String getEnterpriseState() {
		return enterpriseState;
	}
	
	public void setEinCode(String einCode) {
		this.einCode = einCode;
	}
	
	public String getEinCode() {
		return einCode;
	}
	
	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}
	
	public String getServiceTel() {
		return serviceTel;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getIsDelete() {
		return isDelete;
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
	
}
