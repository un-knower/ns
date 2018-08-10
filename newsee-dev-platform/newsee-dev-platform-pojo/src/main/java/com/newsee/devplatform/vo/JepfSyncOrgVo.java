package com.newsee.devplatform.vo;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

public class JepfSyncOrgVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "企业ID")
	private Long enterpriseId;
	
	@ApiModelProperty(value = "组织ID")
	private Long organizationId;

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}
