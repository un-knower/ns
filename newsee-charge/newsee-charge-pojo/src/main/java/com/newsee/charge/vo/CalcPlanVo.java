/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

/**
 * CalcPlanVo
 * @version 1.0
 * @author
 */
public class CalcPlanVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 户数,1|number|0|200|6 */
	@ApiModelProperty(value = "户数,1|number|0|200|6")
	private Integer housesSum;
	
	/** 执行时间,1|date|0|200|8 */
	@ApiModelProperty(value = "执行时间,1|date|0|200|8")
	private Date executeTime;
	
	/** 本次计算生将生成,,1|datepicker|0||8|0 */
	@ApiModelProperty(value = "本次计算生将生成,,1|datepicker|0||8|0")
	private Date chargeCycleEndDate;
	
	/** 状态,1|select|0|150|4,,1:新建|2:费用已生成|3:费用已审核|4:费用审核不通过 */
	@ApiModelProperty(value = "状态,1|select|0|150|4,,1:新建|2:费用已生成|3:费用已审核|4:费用审核不通过")
	private Integer taskStatus;
	
	/** 项目,1|text|0|250|2,1|label|0||1|0 */
	@ApiModelProperty(value = "项目,1|text|0|250|2,1|label|0||1|0")
	private String precinctName;
	
	/** 备注,,1|textarea|0||10|0 */
	@ApiModelProperty(value = "备注,,1|textarea|0||10|0")
	private String remark;
	
	/** 应收账期结束,1|date|0|150|6 */
	@ApiModelProperty(value = "应收账期结束,1|date|0|150|6")
	private Date shouldChargeAccountEnd;
	
	/** 应收账期开始,1|date|0|150|5 */
	@ApiModelProperty(value = "应收账期开始,1|date|0|150|5")
	private Date shouldChargeAccountStart;
	
	/** 任务名称,1|text|0|250|2,1|text|1||2|0 */
	@ApiModelProperty(value = "任务名称,1|text|0|250|2,1|text|1||2|0")
	private String taskName;
	
	/** 生成金额,1|number|1|200|7 */
	@ApiModelProperty(value = "生成金额,1|number|1|200|7")
	private Long chargeSum;
	
	/** 类型,1|text|0|200|3,,1:自动计划|2:手动算费|3:直接录入|4:批量导入 */
	@ApiModelProperty(value = "类型,1|text|0|200|3,,1:自动计划|2:手动算费|3:直接录入|4:批量导入")
	private Integer taskType;
	
	/** 生效时间,,1|timepicker|0||5|0 */
	@ApiModelProperty(value = "生效时间,,1|timepicker|0||5|0")
	private Date workDate;
	
	/** 主键,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|1,1|numbertext|0||1|1")
	private Long id;
	
	/** 审核状态,1|select|0|150|7,,1:已审核|2:审核通过|3:审核不通过 */
	@ApiModelProperty(value = "审核状态,1|select|0|150|7,,1:已审核|2:审核通过|3:审核不通过")
	private Integer isCheck;
	
	/** 执行结果,1|text|0|250|9 */
	@ApiModelProperty(value = "执行结果,1|text|0|250|9")
	private String executeResult;
	
	/** 本次计算生将生成,,1|datepicker|0||7|0 */
	@ApiModelProperty(value = "本次计算生将生成,,1|datepicker|0||7|0")
	private Date chargeCycleStartDate;
	
		
	public void setHousesSum(Integer housesSum) {
		this.housesSum = housesSum;
	}
	
	public Integer getHousesSum() {
		return housesSum;
	}
	
	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}
	
	public Date getExecuteTime() {
		return executeTime;
	}
	
	public void setChargeCycleEndDate(Date chargeCycleEndDate) {
		this.chargeCycleEndDate = chargeCycleEndDate;
	}
	
	public Date getChargeCycleEndDate() {
		return chargeCycleEndDate;
	}
	
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	public Integer getTaskStatus() {
		return taskStatus;
	}
	
	public void setPrecinctName(String precinctName) {
		this.precinctName = precinctName;
	}
	
	public String getPrecinctName() {
		return precinctName;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setShouldChargeAccountEnd(Date shouldChargeAccountEnd) {
		this.shouldChargeAccountEnd = shouldChargeAccountEnd;
	}
	
	public Date getShouldChargeAccountEnd() {
		return shouldChargeAccountEnd;
	}
	
	public void setShouldChargeAccountStart(Date shouldChargeAccountStart) {
		this.shouldChargeAccountStart = shouldChargeAccountStart;
	}
	
	public Date getShouldChargeAccountStart() {
		return shouldChargeAccountStart;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getTaskName() {
		return taskName;
	}
	
	public void setChargeSum(Long chargeSum) {
		this.chargeSum = chargeSum;
	}
	
	public Long getChargeSum() {
		return chargeSum;
	}
	
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	
	public Integer getTaskType() {
		return taskType;
	}
	
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	
	public Date getWorkDate() {
		return workDate;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	
	public Integer getIsCheck() {
		return isCheck;
	}
	
	public void setExecuteResult(String executeResult) {
		this.executeResult = executeResult;
	}
	
	public String getExecuteResult() {
		return executeResult;
	}
	
	public void setChargeCycleStartDate(Date chargeCycleStartDate) {
		this.chargeCycleStartDate = chargeCycleStartDate;
	}
	
	public Date getChargeCycleStartDate() {
		return chargeCycleStartDate;
	}
	
}
