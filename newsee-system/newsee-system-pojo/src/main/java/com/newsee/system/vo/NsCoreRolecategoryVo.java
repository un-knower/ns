package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

public class NsCoreRolecategoryVo implements Serializable {

    private static final long serialVersionUID = 2016015854920304682L;
    
    private Long rolecategoryId;

    private Long enterpriseId;

    private Long organizationId;

    private String rolecategoryName;

    private Byte isDeleted;

    private String remark;
    
    private Boolean disabled;
    
    private Long handerId;
    
    private List<NsCoreRoleVo> roleVos;

    public Long getRolecategoryId() {
        return rolecategoryId;
    }

    public void setRolecategoryId(Long rolecategoryId) {
        this.rolecategoryId = rolecategoryId;
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

    public String getRolecategoryName() {
        return rolecategoryName;
    }

    public void setRolecategoryName(String rolecategoryName) {
        this.rolecategoryName = rolecategoryName;
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

    public Long getHanderId() {
        return handerId;
    }

    public void setHanderId(Long handerId) {
        this.handerId = handerId;
    }


    public List<NsCoreRoleVo> getRoleVos() {
        return roleVos;
    }

    public void setRoleVos(List<NsCoreRoleVo> roleVos) {
        this.roleVos = roleVos;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

}
