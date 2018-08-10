package com.newsee.system.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class RoleResultVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -7753806298514621593L;

    @ApiModelProperty(value = "结果表ID")
    private String id;
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;
    @ApiModelProperty(value = "公司ID")
    private Long companyId;
    @ApiModelProperty(value = "角色组ID")
    private Long roleGroupId;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "删除标识")
    private Byte isDeleted;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "角色功能权限")
    private String menuButtonNames;
    @ApiModelProperty(value = "授权人名称")
    private String userNames;
    @ApiModelProperty(value = "角色项目权限")
    private String precinctNames;
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
    public Long getRoleGroupId() {
        return roleGroupId;
    }
    public void setRoleGroupId(Long roleGroupId) {
        this.roleGroupId = roleGroupId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public Byte getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }    
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getMenuButtonNames() {
        return menuButtonNames;
    }
    public void setMenuButtonNames(String menuButtonNames) {
        this.menuButtonNames = menuButtonNames;
    }
    public String getUserNames() {
        return userNames;
    }
    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }
    public String getPrecinctNames() {
        return precinctNames;
    }
    public void setPrecinctNames(String precinctNames) {
        this.precinctNames = precinctNames;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
}
