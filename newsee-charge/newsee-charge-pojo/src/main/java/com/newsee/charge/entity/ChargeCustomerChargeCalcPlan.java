/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 应收款自动计算计划表
 * @version 1.0
 * @author
 */
public class ChargeCustomerChargeCalcPlan implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键,1|number|0|0|1,1|text|0||1|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|1,1|text|0||1|1")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	/** 项目id */
	@ApiModelProperty(value = "项目id")
	private Long precinctId;
	
	/** 项目名称,1|text|0|200|2,1|select|1||2|0 */
	@ApiModelProperty(value = "项目名称,1|text|0|200|2,1|select|1||2|0")
	private String precinctName;
	
	/** 计划名称,1|text|0|250|2,1|text|1||2|0 */
	@ApiModelProperty(value = "计划名称,1|text|0|250|2,1|text|1||2|0")
	private String planName;
	
	/** 状态,1|select|0|150|3,1|radio|1||3|0,0:停用|1:启用 */
	@ApiModelProperty(value = "状态,1|select|0|150|3,1|radio|1||3|0,0:停用|1:启用")
	private Integer isWork;
	
	/** 计费开始月份,,1|radio|0||5|0,1:本月|2:次月 */
	@ApiModelProperty(value = "计费开始月份,,1|radio|0||5|0,1:本月|2:次月")
	private Integer chargeStartMonthType;
	
	/** 费用重复时,,1|radio|0||6|0,1:重新生成|2:不生成 */
	@ApiModelProperty(value = "费用重复时,,1|radio|0||6|0,1:重新生成|2:不生成")
	private Integer chargeRepeatHandleType;
	
	/** 执行频率,,1|select|0||7|0,1:每日|2:每周|3:每月|4:每年 */
	@ApiModelProperty(value = "执行频率,,1|select|0||7|0,1:每日|2:每周|3:每月|4:每年")
	private Integer planFreqFirst;
	
	/** 执行频率之周和日,,1|select|0||8|0,1:周一|2:周二|3:周三|4:周四|5:周五|6:周六|7:周日 */
	@ApiModelProperty(value = "执行频率之周和日,,1|select|0||8|0,1:周一|2:周二|3:周三|4:周四|5:周五|6:周六|7:周日")
	private Integer planFreqSecond;
	
	/** ,,1|timepicker|0||9|0 */
	@ApiModelProperty(value = ",,1|timepicker|0||9|0")
	private Date planFreqThird;
	
	/** 执行频率,1|text|0|200|5 */
	@ApiModelProperty(value = "执行频率,1|text|0|200|5")
	private String freq;
	
	/** 备注,,1|textarea|0|9|0 */
	@ApiModelProperty(value = "备注,,1|textarea|0|9|0")
	private String remark;
	
	/** 户数,1|nubmer|0|200|6 */
	@ApiModelProperty(value = "户数,1|nubmer|0|200|6")
	private Long housesSum;
	
	/** 最后执行时间,1|date|0|250|8 */
	@ApiModelProperty(value = "最后执行时间,1|date|0|250|8")
	private Date lastExecuteTime;
	
	/** 创建人id */
	@ApiModelProperty(value = "创建人id")
	private Long createUserId;
	
	/** 创建人,1|text|0|200|7 */
	@ApiModelProperty(value = "创建人,1|text|0|200|7")
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
	
	/** 插入和更新时间 */
	@ApiModelProperty(value = "插入和更新时间")
	private Date sysTime;
	
		
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
	
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	public Long getOrganizationId() {
		return organizationId;
	}
	
	public void setPrecinctId(Long precinctId) {
		this.precinctId = precinctId;
	}
	
	public Long getPrecinctId() {
		return precinctId;
	}
	
	public void setPrecinctName(String precinctName) {
		this.precinctName = precinctName;
	}
	
	public String getPrecinctName() {
		return precinctName;
	}
	
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	public String getPlanName() {
		return planName;
	}
	
	public void setIsWork(Integer isWork) {
		this.isWork = isWork;
	}
	
	public Integer getIsWork() {
		return isWork;
	}
	
	public void setChargeStartMonthType(Integer chargeStartMonthType) {
		this.chargeStartMonthType = chargeStartMonthType;
	}
	
	public Integer getChargeStartMonthType() {
		return chargeStartMonthType;
	}
	
	public void setChargeRepeatHandleType(Integer chargeRepeatHandleType) {
		this.chargeRepeatHandleType = chargeRepeatHandleType;
	}
	
	public Integer getChargeRepeatHandleType() {
		return chargeRepeatHandleType;
	}
	
	public void setPlanFreqFirst(Integer planFreqFirst) {
		this.planFreqFirst = planFreqFirst;
	}
	
	public Integer getPlanFreqFirst() {
		return planFreqFirst;
	}
	
	public void setPlanFreqSecond(Integer planFreqSecond) {
		this.planFreqSecond = planFreqSecond;
	}
	
	public Integer getPlanFreqSecond() {
		return planFreqSecond;
	}
	
	public void setPlanFreqThird(Date planFreqThird) {
		this.planFreqThird = planFreqThird;
	}
	
	public Date getPlanFreqThird() {
		return planFreqThird;
	}
	
	public void setFreq(String freq) {
		this.freq = freq;
	}
	
	public String getFreq() {
		return freq;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setHousesSum(Long housesSum) {
		this.housesSum = housesSum;
	}
	
	public Long getHousesSum() {
		return housesSum;
	}
	
	public void setLastExecuteTime(Date lastExecuteTime) {
		this.lastExecuteTime = lastExecuteTime;
	}
	
	public Date getLastExecuteTime() {
		return lastExecuteTime;
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
	
	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}
	
	public Date getSysTime() {
		return sysTime;
	}
	
}
