package com.newsee.owner.entity;

import java.util.Date;

public class OwnerHouseBuildingExtendInfo {
    private Long houseId;

    private Long developer;

    private String builder;

    private Date startWorkTime;

    private Date completeTime;

    private Date deliveryTime;

    private Long buildingArea;

    private Integer totalFloorNumber;

    private Integer totalRoomNumber;

    private Long createUserId;

    private Date createTime;

    private Long updateUserId;

    private Date updateTime;

    private Date sysTime;

    private String createUserName;
    
    private String updateUserName;
    
    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getDeveloper() {
        return developer;
    }

    public void setDeveloper(Long developer) {
        this.developer = developer;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public Date getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(Date startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Long getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(Long buildingArea) {
        this.buildingArea = buildingArea;
    }

    public Integer getTotalFloorNumber() {
        return totalFloorNumber;
    }

    public void setTotalFloorNumber(Integer totalFloorNumber) {
        this.totalFloorNumber = totalFloorNumber;
    }

    public Integer getTotalRoomNumber() {
        return totalRoomNumber;
    }

    public void setTotalRoomNumber(Integer totalRoomNumber) {
        this.totalRoomNumber = totalRoomNumber;
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