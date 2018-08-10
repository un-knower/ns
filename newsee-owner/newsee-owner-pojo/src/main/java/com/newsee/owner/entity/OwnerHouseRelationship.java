package com.newsee.owner.entity;

import java.util.Date;

public class OwnerHouseRelationship {
    private Long houseOwnerRelationshipId;

    private Long precinctId;
    
    private Long houseId;

    private Long ownerId;

    private String ownerProperty;
    
    private Byte isMainHouse;

    private Long detailId;

    private Byte ownerCategory;
    
    private Byte isCurrentRecord;
    
    private Byte isDeleted;
    
    private Long createUserId;

    private Date createTime;

    private Long updateUserId;

    private Date updateTime;

    private Date sysTime;

    private String createUserName;
    
    private String updateUserName;
    
    public Long getHouseOwnerRelationshipId() {
        return houseOwnerRelationshipId;
    }

    public void setHouseOwnerRelationshipId(Long houseOwnerRelationshipId) {
        this.houseOwnerRelationshipId = houseOwnerRelationshipId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Byte getIsMainHouse() {
        return isMainHouse;
    }

    public void setIsMainHouse(Byte isMainHouse) {
        this.isMainHouse = isMainHouse;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
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

    public Date getSysTime() {
        return sysTime;
    }

    public void setSysTime(Date sysTime) {
        this.sysTime = sysTime;
    }

    public Byte getOwnerCategory() {
        return ownerCategory;
    }

    public void setOwnerCategory(Byte ownerCategory) {
        this.ownerCategory = ownerCategory;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Byte getIsCurrentRecord() {
        return isCurrentRecord;
    }

    public void setIsCurrentRecord(Byte isCurrentRecord) {
        this.isCurrentRecord = isCurrentRecord;
    }

    public Long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(Long precinctId) {
        this.precinctId = precinctId;
    }

    public String getOwnerProperty() {
        return ownerProperty;
    }

    public void setOwnerProperty(String ownerProperty) {
        this.ownerProperty = ownerProperty;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

}