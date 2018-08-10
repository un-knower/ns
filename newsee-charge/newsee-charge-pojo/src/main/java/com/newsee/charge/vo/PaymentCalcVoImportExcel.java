/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.vo;

import com.newsee.charge.entity.ChargeCustomerChargeCalcTaskChargeItem;
import com.newsee.charge.entity.ChargeCustomerChargeCalcTaskHouse;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * PaymentCalcVo
 * @version 1.0
 * @author
 */
public class PaymentCalcVoImportExcel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 户数,1|number|0|200|6 */
	@ApiModelProperty(value = "户数,1|number|0|200|6")
	private Integer housesSum;
	
	/** 执行时间,1|date|0|200|8 */
	@ApiModelProperty(value = "执行时间,1|date|0|200|8")
	private Date executeTime;
	
	/** 日 周  月  年  0 1 2 3  */
	private String dateType;
	
	/** 多少号 */
	private String monthDay;
	
	/** 几点几分 */
	private String mSenconds;
	
	/** 几月几号*/
	private String mdays;
	
	/** 周几*/
	private String weeks;
	/** 本次计算生将生成,,1|datepicker|0||8|0 */
	@ApiModelProperty(value = "本次计算生将生成,,1|datepicker|0||8|0")
	private Date chargeCycleEndDate;
	
	/** 状态,1|select|0|150|4,,1:新建|2:费用已生成|3:费用已审核|4:费用审核不通过 */
	@ApiModelProperty(value = "状态,1|select|0|150|4,,1:新建|2:费用已生成|3:费用已审核|4:费用审核不通过")
	private String taskStatus;
	
	/** 项目,1|text|0|250|2,1|label|0||1|0 */
	@ApiModelProperty(value = "项目,1|text|0|250|2,1|label|0||1|0")
	private String precinctName;
	
	@ApiModelProperty(value = "状态,启用，不启用")
	private String status;
	@ApiModelProperty(value = "项目Id,1|text|0|250|2,1|label|0||1|0")
	private Long precinctId;
	
	@ApiModelProperty(value = "计费开始月份，本月，次月")
	private String chargeStartMonth;
	/** 备注,,1|textarea|0||10|0 */
	@ApiModelProperty(value = "备注,,1|textarea|0||10|0")
	private String remark;
	
	@ApiModelProperty(value = "费用重复时，重新生成，不生成")
	private String chargeRepeatHandleType;
	
	/** 应收账期结束,1|date|0|150|6 */
	@ApiModelProperty(value = "应收账期结束,1|date|0|150|6")
	private String shouldChargeAccountEnd;
	
	/** 应收账期开始,1|date|0|150|5 */
	@ApiModelProperty(value = "应收账期开始,1|date|0|150|5")
	private String shouldChargeAccountStart;
	
	/** 任务名称,1|text|0|250|2,1|text|1||2|0 */
	@ApiModelProperty(value = "任务名称,1|text|0|250|2,1|text|1||2|0")
	private String taskName;
	
	/** 生成金额,1|number|1|200|7 */
	@ApiModelProperty(value = "生成金额,1|number|1|200|7")
	private Long chargeSum;
	
	/** 类型,1|text|0|200|3,,1:自动计划|2:手动算费|3:直接录入|4:批量导入 */
	@ApiModelProperty(value = "类型,1|text|0|200|3,,1:自动计划|2:手动算费|3:直接录入|4:批量导入")
	private String taskType;
	
	/** 生效时间,,1|timepicker|0||5|0 */
	@ApiModelProperty(value = "生效时间,,1|timepicker|0||5|0")
	private String workDate;

	/** 计费周期,,,1:延续,2:指定周期 */
	@ApiModelProperty(value = "计费周期,,,1:延续,2:指定周期")
	private String chargeCycleType;
	
	/** 主键,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|1,1|numbertext|0||1|1")
	private Long id;
	
	/** 审核状态,1|select|0|150|7,,1:已审核|2:审核通过|3:审核不通过 */
	@ApiModelProperty(value = "审核状态,1|select|0|150|7,,1:已审核|2:审核通过|3:审核不通过")
	private String isCheck;
	
	/** 执行结果,1|text|0|250|9 */
	@ApiModelProperty(value = "执行结果,1|text|0|250|9")
	private String executeResult;
	
	/** 本次计算生将生成,,1|datepicker|0||7|0 */
	@ApiModelProperty(value = "本次计算生将生成,,1|datepicker|0||7|0")
	private Date chargeCycleStartDate;
	
	
    private Long enterpriseId;
	
	
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	private Long createUserId;
	
	private String createUserName;
	
	private Date CreateTime;
	
    private Long updateUserId;
    
    private String updateUserName;
    
    private Date updateTime;
	
    
    
	public String getChargeCycleType() {
		return chargeCycleType;
	}

	public void setChargeCycleType(String chargeCycleType) {
		this.chargeCycleType = chargeCycleType;
	}

	private List<ChargeCustomerChargeCalcTaskHouse> houses;
    
	private List<ChargeCustomerChargeCalcTaskChargeItem> items;
	
	public List<ChargeCustomerChargeCalcTaskHouse> getHouses() {
		return houses;
	}

	public void setHouses(List<ChargeCustomerChargeCalcTaskHouse> houses) {
		this.houses = houses;
	}

	public List<ChargeCustomerChargeCalcTaskChargeItem> getItems() {
		return items;
	}

	public void setItems(List<ChargeCustomerChargeCalcTaskChargeItem> items) {
		this.items = items;
	}

	public String getShouldChargeAccountEnd() {
		return shouldChargeAccountEnd;
	}

	public void setShouldChargeAccountEnd(String shouldChargeAccountEnd) {
		this.shouldChargeAccountEnd = shouldChargeAccountEnd;
	}

	public String getShouldChargeAccountStart() {
		return shouldChargeAccountStart;
	}

	public void setShouldChargeAccountStart(String shouldChargeAccountStart) {
		this.shouldChargeAccountStart = shouldChargeAccountStart;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getMonthDay() {
		return monthDay;
	}

	public void setMonthDay(String monthDay) {
		this.monthDay = monthDay;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getPrecinctId() {
		return precinctId;
	}

	public void setPrecinctId(Long precinctId) {
		this.precinctId = precinctId;
	}

	public String getChargeStartMonth() {
		return chargeStartMonth;
	}

	public void setChargeStartMonth(String chargeStartMonth) {
		this.chargeStartMonth = chargeStartMonth;
	}

	public String getChargeRepeatHandleType() {
		return chargeRepeatHandleType;
	}

	public void setChargeRepeatHandleType(String chargeRepeatHandleType) {
		this.chargeRepeatHandleType = chargeRepeatHandleType;
	}

	public String getmSenconds() {
		return mSenconds;
	}

	public void setmSenconds(String mSenconds) {
		this.mSenconds = mSenconds;
	}

	public String getMdays() {
		return mdays;
	}

	public void setMdays(String mdays) {
		this.mdays = mdays;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
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
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
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
	
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	public String getTaskStatus() {
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

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	
	public String getIsCheck() {
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
