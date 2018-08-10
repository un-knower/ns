package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class NsSystemSuperAdmin implements Serializable {

    private static final long serialVersionUID = -1554950304639555970L;
    
    /** 组织ID */
    @ApiModelProperty(value = "组织ID")
    private Long organizationId;
    
    /** enterpriseId */
    @ApiModelProperty(value = "租户ID")
    private Long enterpriseId;
    
    /** 所属集团 */
    @ApiModelProperty(value = "所属集团")
    private Long groupId;
    
    /** 默认负责人的userid */
    @ApiModelProperty(value = "默认负责人的userid")
    private Long userid;
    
    @ApiModelProperty(value = "判断创建什么角色")
    private String roleType;
    /**角色拥有的房产*/
    private List<Long> houseIds;

    
    public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public List<Long> getHouseIds() {
        return houseIds;
    }

    public void setHouseIds(List<Long> houseIds) {
        this.houseIds = houseIds;
    }
}
