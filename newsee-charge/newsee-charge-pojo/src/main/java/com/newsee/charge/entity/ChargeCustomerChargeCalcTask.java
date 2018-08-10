/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * 应收款计算任务
 * @version 1.0
 * @author
 */
public class ChargeCustomerChargeCalcTask implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|1,1|numbertext|0||1|1")
	private Long id;
	
	private   String planName;
	
	public String getPlanName() {
		
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	/** 计划id */
	@ApiModelProperty(value = "计划id")
	private Long planId;
	
	/** 项目id */
	@ApiModelProperty(value = "项目id")
	private Long precinctId;
	
	/** 项目,1|text|0|250|2,1|label|0||1|0 */
	@ApiModelProperty(value = "项目,1|text|0|250|2,1|label|0||1|0")
	private String precinctName;
	
	/** 任务名称,1|text|0|250|2,1|text|1||2|0 */
	@ApiModelProperty(value = "任务名称,1|text|0|250|2,1|text|1||2|0")
	private String taskName;
	
	/** 类型,1|text|0|200|3,,1:自动计划|2:手动算费|3:直接录入|4:批量导入 */
	@ApiModelProperty(value = "类型,1|text|0|200|3,,1:自动计划|2:手动算费|3:直接录入|4:批量导入")
	private String taskType;
	
	/** 费用重复时,,,1:重新生成|2:不生成 */
	@ApiModelProperty(value = "费用重复时,,,1:重新生成|2:不生成")
	private String chargeRepeatHandleType;
	
	/** 生效时间,,1|timepicker|0||5|0 */
	@ApiModelProperty(value = "生效时间,,1|timepicker|0||5|0")
	private Date workDate;
	
	/** 应收账期开始,1|date|0|150|5 */
	@ApiModelProperty(value = "应收账期开始,1|date|0|150|5")
	private Date shouldChargeAccountStart;
	
	/** 应收账期结束,1|date|0|150|6 */
	@ApiModelProperty(value = "应收账期结束,1|date|0|150|6")
	private Date shouldChargeAccountEnd;
	
	/** 计费周期,,,1:延续,2:指定周期 */
	@ApiModelProperty(value = "计费周期,,,1:延续,2:指定周期")
	private String chargeCycleType;
	
	/** 本次计算生将生成,,1|datepicker|0||7|0 */
	@ApiModelProperty(value = "本次计算生将生成,,1|datepicker|0||7|0")
	private Date chargeCycleStartDate;
	
	/** 本次计算生将生成,,1|datepicker|0||8|0 */
	@ApiModelProperty(value = "本次计算生将生成,,1|datepicker|0||8|0")
	private Date chargeCycleEndDate;
	
	/** 状态,1|select|0|150|4,,1:新建|2:费用已生成|3:费用已审核|4:费用审核不通过 */
	@ApiModelProperty(value = "状态,1|select|0|150|4,,1:新建|2:费用已生成|3:费用已审核|4:费用审核不通过")
	private String taskStatus;
	
	/** 审核状态,1|select|0|150|7,,1:已审核|2:审核通过|3:审核不通过 */
	@ApiModelProperty(value = "审核状态,1|select|0|150|7,,1:已审核|2:审核通过|3:审核不通过")
	private String isCheck;
	
	/** 审核时间 */
	@ApiModelProperty(value = "审核时间")
	private Date checkDate;
	
	/** 审核人用户id */
	@ApiModelProperty(value = "审核人用户id")
	private Long checkUserId;
	
	/** 审核人姓名 */
	@ApiModelProperty(value = "审核人姓名")
	private String checkUserName;
	
	/** 备注,,1|textarea|0||10|0 */
	@ApiModelProperty(value = "备注,,1|textarea|0||10|0")
	private String remark;
	
	/** 执行时间,1|date|0|200|8 */
	@ApiModelProperty(value = "执行时间,1|date|0|200|8")
	private Date executeTime;
	
	/** 执行结果,1|text|0|250|9 */
	@ApiModelProperty(value = "执行结果,1|text|0|250|9")
	private String executeResult;
	
	/** 户数,1|number|0|200|6 */
	@ApiModelProperty(value = "户数,1|number|0|200|6")
	private Integer housesSum;
	
	/** 生成金额,1|number|1|200|7 */
	@ApiModelProperty(value = "生成金额,1|number|1|200|7")
	private Long chargeSum;
	
	/** 创建人id */
	@ApiModelProperty(value = "创建人id")
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
	
	private Integer sequence;
	/** 插入或更新时间 */
	@ApiModelProperty(value = "插入或更新时间")
	private Date sysTime;
	
	private String is_work;
	
	private String is_exist;
	
	private String chargeStartMonth;
	
	/** 执行频率*/
	private String interval;
	
	
	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getChargeStartMonth() {
		return chargeStartMonth;
	}

	public void setChargeStartMonth(String chargeStartMonth) {
		this.chargeStartMonth = chargeStartMonth;
	}

	public String getIs_exist() {
		return is_exist;
	}

	
	
	public Integer getSequence() {
		return sequence;
	}



	public void setSequence(Integer sequence) {
		sequence = sequence;
	}



	public void setIs_exist(String is_exist) {
		this.is_exist = is_exist;
	}

	public String getIs_work() {
		return is_work;
	}

	public void setIs_work(String is_work) {
		this.is_work = is_work;
	}

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
	
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	
	public Long getPlanId() {
		return planId;
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
	
	public void setTaskName(String taskName) {
		this.setPlanName(taskName);
		this.taskName = taskName;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	
	
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getChargeRepeatHandleType() {
		return chargeRepeatHandleType;
	}

	public void setChargeRepeatHandleType(String chargeRepeatHandleType) {
		this.chargeRepeatHandleType = chargeRepeatHandleType;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	
	public Date getWorkDate() {
		return workDate;
	}
	
	public void setShouldChargeAccountStart(Date shouldChargeAccountStart) {
		this.shouldChargeAccountStart = shouldChargeAccountStart;
	}
	
	public Date getShouldChargeAccountStart() {
		return shouldChargeAccountStart;
	}
	
	public void setShouldChargeAccountEnd(Date shouldChargeAccountEnd) {
		this.shouldChargeAccountEnd = shouldChargeAccountEnd;
	}
	
	public Date getShouldChargeAccountEnd() {
		return shouldChargeAccountEnd;
	}
	
	
	
	public String getChargeCycleType() {
		return chargeCycleType;
	}

	public void setChargeCycleType(String chargeCycleType) {
		this.chargeCycleType = chargeCycleType;
	}

	public void setChargeCycleStartDate(Date chargeCycleStartDate) {
		this.chargeCycleStartDate = chargeCycleStartDate;
	}
	
	public Date getChargeCycleStartDate() {
		return chargeCycleStartDate;
	}
	
	public void setChargeCycleEndDate(Date chargeCycleEndDate) {
		this.chargeCycleEndDate = chargeCycleEndDate;
	}
	
	public Date getChargeCycleEndDate() {
		return chargeCycleEndDate;
	}
	
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	public String getTaskStatus() {
		return taskStatus;
	}
	
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	
	public String getIsCheck() {
		return isCheck;
	}
	
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	public Date getCheckDate() {
		return checkDate;
	}
	
	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}
	
	public Long getCheckUserId() {
		return checkUserId;
	}
	
	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	
	public String getCheckUserName() {
		return checkUserName;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}
	
	public Date getExecuteTime() {
		return executeTime;
	}
	
	public void setExecuteResult(String executeResult) {
		this.executeResult = executeResult;
	}
	
	public String getExecuteResult() {
		return executeResult;
	}
	
	public void setHousesSum(Integer housesSum) {
		this.housesSum = housesSum;
	}
	
	public Integer getHousesSum() {
		return housesSum;
	}
	
	public void setChargeSum(Long chargeSum) {
		this.chargeSum = chargeSum;
	}
	
	public Long getChargeSum() {
		return chargeSum;
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
