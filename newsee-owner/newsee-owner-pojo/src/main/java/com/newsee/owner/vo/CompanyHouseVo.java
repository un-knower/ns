package com.newsee.owner.vo;

import java.io.Serializable;
import java.util.List;

public class CompanyHouseVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 4607943978977888754L;

    private Long organizationId;
    
    private String companyName;
    
    private Long houseId;
    
    private List<HouseBaseInfoTreeNode> childOwnerHouseBaseInfoTreeNodeList;

    private Boolean isHasChild;
    

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<HouseBaseInfoTreeNode> getChildOwnerHouseBaseInfoTreeNodeList() {
        return childOwnerHouseBaseInfoTreeNodeList;
    }

    public void setChildOwnerHouseBaseInfoTreeNodeList(List<HouseBaseInfoTreeNode> childOwnerHouseBaseInfoTreeNodeList) {
        this.childOwnerHouseBaseInfoTreeNodeList = childOwnerHouseBaseInfoTreeNodeList;
    }

    public Boolean getIsHasChild() {
        return isHasChild;
    }

    public void setIsHasChild(Boolean isHasChild) {
        this.isHasChild = isHasChild;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

}
