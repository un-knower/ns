package com.newsee.log.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 业务日志entity
 * @author xiaosisi add on 2017/12/21
 *
 */
public class BizLogEntity implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 企业id */
	private Long enterpriseId;
	
	/** 公司id */
	private Long companyId;
	
	/** 组织id */
	private Long organizationId;

	/** 操作人员id */
	private Long userId;
	
	/** 操作人员姓名 */
	private String userName;
	
	/** 操作类型 10：新增，20：修改，30，：删除*/
	private Integer operateType;
	
	/** 操作类型名称 10：新增，20：修改，30，：删除 */
	private String operateName;
	
	/**日志类型：10：功能日志，20：数据日志*/
	private Integer logType;
	
	/** 数据id */
	private Long dataId;
	
	/** 数据id List 用于批量操作记录用*/
	private List<Long> dataIds;
	
	/** 功能菜单id */
	private String funcId;
	
	/** 操作时间 */
	private Date operateDate;
	
	/** 备注，即具体的操作日志 */
	private String remark;
	
	/** 修改前的json */
	private String beforeJson;
	
	/** 修改后的json */
	private String afterJson;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public List<Long> getDataIds() {
		return dataIds;
	}

	public void setDataIds(List<Long> dataIds) {
		this.dataIds = dataIds;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBeforeJson() {
		return beforeJson;
	}

	public void setBeforeJson(String beforeJson) {
		this.beforeJson = beforeJson;
	}

	public String getAfterJson() {
		return afterJson;
	}

	public void setAfterJson(String afterJson) {
		this.afterJson = afterJson;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
}
