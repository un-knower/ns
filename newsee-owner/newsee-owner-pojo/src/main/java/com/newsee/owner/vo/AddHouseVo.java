package com.newsee.owner.vo;

import java.io.Serializable;
import java.util.List;

import com.newsee.common.enums.HouseTypeEnum;

import io.swagger.annotations.ApiModelProperty;

public class AddHouseVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -5187839434056156476L;
    
    private Long enterpriseId; 
    private Long organizationId; 
    private Long parentId; 
    private Long houseId; 
    private String houseType;
    private HouseTypeEnum houseTypeEnum; 
    private String houseName; 
    private Long userId; 
    private String userName;
    private String houseInfo;
    @ApiModelProperty("关联的房产IDList")
    private List<Long> houseRelationList;
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
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public Long getHouseId() {
        return houseId;
    }
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
    public HouseTypeEnum getHouseTypeEnum() {
        return houseTypeEnum;
    }
    public void setHouseTypeEnum(HouseTypeEnum houseTypeEnum) {
        this.houseTypeEnum = houseTypeEnum;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getHouseInfo() {
        return houseInfo;
    }
    public void setHouseInfo(String houseInfo) {
        this.houseInfo = houseInfo;
    }
    public String getHouseType() {
        return houseType;
    }
    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
    public String getHouseName() {
        return houseName;
    }
    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
    public List<Long> getHouseRelationList() {
        return houseRelationList;
    }
    public void setHouseRelationList(List<Long> houseRelationList) {
        this.houseRelationList = houseRelationList;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
