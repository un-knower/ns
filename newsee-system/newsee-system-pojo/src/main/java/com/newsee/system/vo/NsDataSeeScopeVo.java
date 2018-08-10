package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

public class NsDataSeeScopeVo implements Serializable {

    private static final long serialVersionUID = 6637797344503739701L;

    private Long enterpriseId;

    private String roleid;

    private String funcId;
    
    //本集团、本公司、本部门
    private Long seeOrganizationId;
    
    //本人
    private Long seeUserId;
    
    //选择的可见类型
    private Integer seeScopeType;
    
    //经管部门
    private List<Long> seeOtherOrgIdList;

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

    public Long getSeeUserId() {
        return seeUserId;
    }

    public void setSeeUserId(Long seeUserId) {
        this.seeUserId = seeUserId;
    }

    public List<Long> getSeeOtherOrgIdList() {
        return seeOtherOrgIdList;
    }

    public void setSeeOtherOrgIdList(List<Long> seeOtherOrgIdList) {
        this.seeOtherOrgIdList = seeOtherOrgIdList;
    }

    public Integer getSeeScopeType() {
        return seeScopeType;
    }

    public void setSeeScopeType(Integer seeScopeType) {
        this.seeScopeType = seeScopeType;
    }
}
