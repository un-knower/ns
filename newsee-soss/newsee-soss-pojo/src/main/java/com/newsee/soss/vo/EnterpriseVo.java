/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.vo;

import java.io.Serializable;
import java.util.Date;

import com.newsee.common.vo.ProvinceCityArea;

import io.swagger.annotations.ApiModelProperty;

/**
 * EnterpriseVo
 * @version 1.0
 * @author
 */
public class EnterpriseVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

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
	
	/** 备注,1|text|0|300|19,1|textarea|0||19|0 */
	@ApiModelProperty(value = "备注,1|text|0|300|19,1|textarea|0||19|0")
	private String remark;
	
	/** 申请人,1|text|0|200|13,1|text|0||13|0 */
	@ApiModelProperty(value = "申请人,1|text|0|200|13,1|text|0||13|0")
	private String createUserName;
	
	/** 申请时间,1|date|0|200|14,1|timepicker|0||14|0 */
	@ApiModelProperty(value = "申请时间,1|date|0|200|14,1|timepicker|0||14|0")
	private Date createTime;
	/**创建人ID*/
	private Long createUserId;
	/**申请人邮箱*/
	@ApiModelProperty(value = "申请人邮箱")
	private String createUserEmail;
	
	@ApiModelProperty(value = "省市区")
	private ProvinceCityArea provinceCityArea; 
	
	/** updateUserId */
	private Long updateUserId;	
	/** updateUserName */
	private String updateUserName;	
	/** updateTime */
	private Date updateTime;
	
	@ApiModelProperty(value = "企业员工数")
	private Integer enterpriseUserCount;
	
	@ApiModelProperty(value="注册步骤1 手机号验证，2 注册提交")
	private Integer registerStep;
	
	@ApiModelProperty(value="注册人手机")
	private String registerUserPhone;
	
	@ApiModelProperty(value="操作人名称")
	private String handleUserName;
	
	@ApiModelProperty(value="注册密码")
	private String password;
	//确认密码
	private String confirmPWD;
	
	@ApiModelProperty(value="手机验证码")
	private String verificateCode;
	
	
	public String getConfirmPWD() {
		return confirmPWD;
	}

	public void setConfirmPWD(String confirmPWD) {
		this.confirmPWD = confirmPWD;
	}

	public ProvinceCityArea getProvinceCityArea() {
		return provinceCityArea;
	}

	public void setProvinceCityArea(ProvinceCityArea provinceCityArea) {
		this.provinceCityArea = provinceCityArea;
	}

	public String getCreateUserEmail() {
		return createUserEmail;
	}

	public void setCreateUserEmail(String createUserEmail) {
		this.createUserEmail = createUserEmail;
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

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getEnterpriseUserCount() {
		return enterpriseUserCount;
	}

	public void setEnterpriseUserCount(Integer enterpriseUserCount) {
		this.enterpriseUserCount = enterpriseUserCount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRegisterStep() {
		return registerStep;
	}

	public void setRegisterStep(Integer registerStep) {
		this.registerStep = registerStep;
	}

	public String getRegisterUserPhone() {
		return registerUserPhone;
	}

	public void setRegisterUserPhone(String registerUserPhone) {
		this.registerUserPhone = registerUserPhone;
	}

	public String getHandleUserName() {
		return handleUserName;
	}

	public void setHandleUserName(String handleUserName) {
		this.handleUserName = handleUserName;
	}

	public String getVerificateCode() {
		return verificateCode;
	}

	public void setVerificateCode(String verificateCode) {
		this.verificateCode = verificateCode;
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
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
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
	
}
