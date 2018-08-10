/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newsee.charge.entity.ChargeChargeStandard;
import io.swagger.annotations.ApiModelProperty;

/**
 * HouseStandarEditdVo
 * 用于房产收费标准的新增
 * @version 1.0
 * @author
 */
public class HouseStandardAddVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "项目id或者房间id")
	private Long houseId;
	
	/** 房产简称,1|text|0|200|2 */
	@ApiModelProperty(value = "房产简称,1|text|0|200|2")
	private String houseFullName;
	
	@ApiModelProperty(value = "房间类型：项目或者房间")
	private Integer houseType;
	
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	@ApiModelProperty(value = "选中房产节点的组织id")
	private Long  organizationId;
	
	@ApiModelProperty(value = "房产收费标准")
	List<HouseStandardVo> houseStandards;
	
	@ApiModelProperty(value = "子公司或者公司设置的收费标准，用来显示在新增房产收费标准页面中的收费标准列")
	List<ChargeChargeStandard> standards;
	
	/** 创建人di */
	@ApiModelProperty(value = "创建人di")
	private Long createUserId;
	
	/** 创建人姓名 */
	@ApiModelProperty(value = "创建人姓名")
	private String createUserName;
	
	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	
	/** 更新人id */
	@ApiModelProperty(value = "更新人id")
	private Long updateUserId;
	
	/** 更新人姓名 */
	@ApiModelProperty(value = "更新人姓名")
	private String updateUserName;
	
	/** 更新时间 */
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

	private String  custorName;
	
	@ApiModelProperty(value = "计费开始日期,1|date|0|250|6")
	private Date chargeStartTime;
	
	/** 计费结束日期,1|date|0|250|7 */
	@ApiModelProperty(value = "计费结束日期,1|date|0|250|7")
	private Date chargeEndTime;
	private String meterName;
	
	private String  chargeCustor;
	
	private Long chargeItemId;
	
	private String chargeItemName;
	
	private Long preinctId;
	
	private String preinctName;
	
	private HashMap<String, Object> preinctNames;
	/** 收费标准id */
	@ApiModelProperty(value = "收费标准id")
	private Long standardId;
	
	/** 收费表混 */
	@ApiModelProperty(value = "收费表混")
	private String standardName;
	
	private String standard ;
	private  String unit ;
	
	private  Integer chargeCustomType;
	
	private Long chargeId;

	private Map<String,Object> customerTypes;


	public Map<String, Object> getCustomerTypes() {
		return customerTypes;
	}

	public void setCustomerTypes(Map<String, Object> customerTypes) {
		this.customerTypes = customerTypes;
	}

	public Date getChargeStartTime() {
		return chargeStartTime;
	}

	public void setChargeStartTime(Date chargeStartTime) {
		this.chargeStartTime = chargeStartTime;
	}

	public Date getChargeEndTime() {
		return chargeEndTime;
	}

	public void setChargeEndTime(Date chargeEndTime) {
		this.chargeEndTime = chargeEndTime;
	}

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	public Integer getChargeCustomType() {
		return chargeCustomType;
	}

	public void setChargeCustomType(Integer chargeCustomType) {
		this.chargeCustomType = chargeCustomType;
	}

	public Long getStandardId() {
		return standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}



	public HashMap<String, Object> getPreinctNames() {
		return preinctNames;
	}

	public void setPreinctNames(HashMap<String, Object> preinctNames) {
		this.preinctNames = preinctNames;
	}

	public Long getChargeItemId() {
		return chargeItemId;
	}

	public void setChargeItemId(Long chargeItemId) {
		this.chargeItemId = chargeItemId;
	}

	public String getChargeItemName() {
		return chargeItemName;
	}

	public void setChargeItemName(String chargeItemName) {
		this.chargeItemName = chargeItemName;
	}

	public Long getPreinctId() {
		return preinctId;
	}

	public void setPreinctId(Long preinctId) {
		this.preinctId = preinctId;
	}

	public String getPreinctName() {
		return preinctName;
	}

	public void setPreinctName(String preinctName) {
		this.preinctName = preinctName;
	}

	public String getCustorName() {
		return custorName;
	}

	public void setCustorName(String custorName) {
		this.custorName = custorName;
	}

	public String getMeterName() {
		return meterName;
	}

	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}

	public String getChargeCustor() {
		return chargeCustor;
	}

	public void setChargeCustor(String chargeCustor) {
		this.chargeCustor = chargeCustor;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getHouseFullName() {
		return houseFullName;
	}

	public void setHouseFullName(String houseFullName) {
		this.houseFullName = houseFullName;
	}

	public Integer getHouseType() {
		return houseType;
	}

	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}

	public List<ChargeChargeStandard> getStandards() {
		return standards;
	}

	public void setStandards(List<ChargeChargeStandard> standards) {
		this.standards = standards;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	public List<HouseStandardVo> getHouseStandards() {
		return houseStandards;
	}

	public void setHouseStandards(List<HouseStandardVo> houseStandards) {
		this.houseStandards = houseStandards;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
