package com.newsee.system.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class NsSystemGroupVo implements Serializable {

    private static final long serialVersionUID = 2746988054159500820L;
    
    /** 集团ID */
    @ApiModelProperty(value = "集团ID")
    private Long groupId;
    
    /** enterpriseId */
    @ApiModelProperty(value = "enterpriseId")
    private Long enterpriseId;
    
    /** 集团名称 */
    @ApiModelProperty(value = "集团名称")
    private String groupName;
    

    /** 集团简称 */
    @ApiModelProperty(value = "集团简称")
    private String groupShortName;
    
    /** 集团编码*/
    @ApiModelProperty(value = "集团编码")
    private String groupCode;
    
    /** 备注 */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /** 对应的组织ID */
    @ApiModelProperty(value = "对应的组织ID")
    private Long organizationId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getGroupShortName() {
        return groupShortName;
    }

    public void setGroupShortName(String groupShortName) {
        this.groupShortName = groupShortName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    
}
