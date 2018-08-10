package com.newsee.common.vo;

import java.io.Serializable;

public class LoginCommonDataVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long enterpriseId;
	
	private Long organizationId;
	
	private Long groupLevelOrgId;
	
	private Long companyLevelOrgId;
	
	private String funcId;
	
	private Long userId;
	
	/** 获取同一个功能下不同表单的标志*/
	private String interpreter;
	
	/** 表单操作类型：0新增 1编辑 */
	private String formOperateType;

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

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

    public String getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(String interpreter) {
        this.interpreter = interpreter;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFormOperateType() {
        return formOperateType;
    }

    public void setFormOperateType(String formOperateType) {
        this.formOperateType = formOperateType;
    }

    public Long getGroupLevelOrgId() {
        return groupLevelOrgId;
    }

    public void setGroupLevelOrgId(Long groupLevelOrgId) {
        this.groupLevelOrgId = groupLevelOrgId;
    }

    public Long getCompanyLevelOrgId() {
        return companyLevelOrgId;
    }

    public void setCompanyLevelOrgId(Long companyLevelOrgId) {
        this.companyLevelOrgId = companyLevelOrgId;
    }
    
	
}
