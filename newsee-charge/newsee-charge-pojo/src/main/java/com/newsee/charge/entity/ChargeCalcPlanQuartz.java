package com.newsee.charge.entity;

import java.io.Serializable;

/**
 * 数据库newsee-quartz中的所有定时任务存储entity
 * @author wangjun
 *
 */
public class ChargeCalcPlanQuartz implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**自增id*/
	private Long id;
	
	/**企业id*/
	private Long enterpriseId;

	/**组织id*/
	private Long organizationId;
	
	/**计划id,表Charge_CustomerChargeCalcPlan中的id*/
	private Long planId;
	
	/**是否启用,和表Charge_CustomerChargeCalcPlan中的isWork同步*/
	private String isWork;
	
	/**quartz框架执行频率表达式*/
	private String planCorn;
	
	/**执行计划名称，为类的全称*/
	private String jobName;
	
	/**因为计费有分库，记录执行计划所属的数据库*/
	private String dbDataSource;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getPlanCorn() {
		return planCorn;
	}

	public void setPlanCorn(String planCorn) {
		this.planCorn = planCorn;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDbDataSource() {
		return dbDataSource;
	}

	public void setDbDataSource(String dbDataSource) {
		this.dbDataSource = dbDataSource;
	}

	public String getIsWork() {
		return isWork;
	}

	public void setIsWork(String isWork) {
		this.isWork = isWork;
	}

	
	
}
