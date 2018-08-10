package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

public class NsSystemAuthorizer implements Serializable {

    private static final long serialVersionUID = -6808688912737294993L;
    
    private Long enterpriseId;
    private Long organizationId;
    private String roleid;
    private List<String> userIds;
    
    public Long getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
    public String getRoleid() {
        return roleid;
    }
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
    public List<String> getUserIds() {
        return userIds;
    }
    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
    public Long getEnterpriseId() {
        return enterpriseId;
    }
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
