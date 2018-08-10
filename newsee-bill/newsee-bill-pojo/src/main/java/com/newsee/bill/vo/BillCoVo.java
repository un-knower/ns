/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

/**
 * BillCoVo
 * @version 1.0
 * @author
 */
public class BillCoVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 经手人名称,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "经手人名称,1|text|0|300|2,1|text|1||2|0")
	private String dealUserName;
	
	/** 更新人姓名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "更新人姓名,1|text|0|300|2,1|text|1||2|0")
	private String updateUserName;
	
	/** 创建时间,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "创建时间,1|text|0|300|2,1|text|1||2|0")
	private Date createTime;
	
	/** 更新时间,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "更新时间,1|text|0|300|2,1|text|1||2|0")
	private Date updateTime;
	
	/** 领用部门编号,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "领用部门编号,1|text|0|300|2,1|text|1||2|0")
	private String drawDepartmentId;
	
	/** 领用人名称,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "领用人名称,1|text|0|300|2,1|text|1||2|0")
	private String drawUserName;
	
	/** 主键,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|0,1|text|0||1|1")
	private Long id;
	
	/** 组织id,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "组织id,1|number|0|0|0,1|text|0||1|1")
	private Long organizationId;
	
	/** 创建人姓名,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "创建人姓名,1|text|0|300|2,1|text|1||2|0")
	private String createUserName;
	
		
	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}
	
	public String getDealUserName() {
		return dealUserName;
	}
	
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	
	public String getUpdateUserName() {
		return updateUserName;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setDrawDepartmentId(String drawDepartmentId) {
		this.drawDepartmentId = drawDepartmentId;
	}
	
	public String getDrawDepartmentId() {
		return drawDepartmentId;
	}
	
	public void setDrawUserName(String drawUserName) {
		this.drawUserName = drawUserName;
	}
	
	public String getDrawUserName() {
		return drawUserName;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	public Long getOrganizationId() {
		return organizationId;
	}
	
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	public String getCreateUserName() {
		return createUserName;
	}
	
}
