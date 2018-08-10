package com.newsee.system.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class NsSystemRoleFunctionOrganizationVo implements Serializable {

    private static final long serialVersionUID = -3392794611008256497L;

    private Long id;

    private Long enterpriseId;

    private String roleid;

    private String funcId;

    private Long seeOrganizationId;

    private Integer seeScopeType;

    private Long createUserId;

    private Date createTime;

    private Long updateUserId;

    private Date updateTime;
    
    //一个人有多个角色时
    private List<String> roleids;

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

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public Long getSeeOrganizationId() {
        return seeOrganizationId;
    }

    public void setSeeOrganizationId(Long seeOrganizationId) {
        this.seeOrganizationId = seeOrganizationId;
    }

    public Integer getSeeScopeType() {
        return seeScopeType;
    }

    public void setSeeScopeType(Integer seeScopeType) {
        this.seeScopeType = seeScopeType;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getRoleids() {
        return roleids;
    }

    public void setRoleids(List<String> roleids) {
        this.roleids = roleids;
    }
    
}
