/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 参数配置表
 * @version 1.0
 * @author
 */
public class NsSossNoticeType implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** id */
	@ApiModelProperty(value = "id")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 参数类型编码 */
	@ApiModelProperty(value = "参数类型编码")
	private String typeCode;
	
	/** 参数编码 */
	@ApiModelProperty(value = "参数编码")
	private String paramCode;
	
	/** 参数名称 */
	@ApiModelProperty(value = "参数名称")
	private String paramName;
	
	/** 参数值 */
	@ApiModelProperty(value = "参数值")
	private String paramValue;
	
	/** 参数父参数编码 */
	@ApiModelProperty(value = "参数父参数编码")
	private String fatherCode;
	
	/** 参数下级参数编码 */
	@ApiModelProperty(value = "参数下级参数编码")
	private String subCode;
	
	/** 备注说明 */
	@ApiModelProperty(value = "备注说明")
	private String remark;
	
	/** 参数状态，0 开启，1 停用 */
	@ApiModelProperty(value = "参数状态，0 开启，1 停用")
	private Integer status;
	
	/** 参数是否显示，0 显示， 1 不显示 */
	@ApiModelProperty(value = "参数是否显示，0 显示， 1 不显示")
	private Integer flag;
	
	/** 序列 */
	@ApiModelProperty(value = "序列")
	private Integer orderBy;
	
	/** 修改时间 */
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	
	/** 修改人 */
	@ApiModelProperty(value = "修改人")
	private Long updateUserId;
	
		
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
	
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	public String getTypeCode() {
		return typeCode;
	}
	
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	
	public String getParamCode() {
		return paramCode;
	}
	
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	
	public String getParamName() {
		return paramName;
	}
	
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	public String getParamValue() {
		return paramValue;
	}
	
	public void setFatherCode(String fatherCode) {
		this.fatherCode = fatherCode;
	}
	
	public String getFatherCode() {
		return fatherCode;
	}
	
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	
	public String getSubCode() {
		return subCode;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public Integer getFlag() {
		return flag;
	}
	
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	
	public Integer getOrderBy() {
		return orderBy;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	public Long getUpdateUserId() {
		return updateUserId;
	}
	
}
