package com.newsee.owner.entity;

import java.util.Date;

public class OwnerHouseStageExtendInfoRent {
    private Long detailId;

    private Long rentOwnerId;

    private Date rentStartDate;

    private Date rentEndDate;

    private String ownerProperty;
    
    private Date sysTime;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getRentOwnerId() {
        return rentOwnerId;
    }

    public void setRentOwnerId(Long rentOwnerId) {
        this.rentOwnerId = rentOwnerId;
    }

    public Date getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(Date rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public Date getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(Date rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public Date getSysTime() {
        return sysTime;
    }

    public void setSysTime(Date sysTime) {
        this.sysTime = sysTime;
    }

    public String getOwnerProperty() {
        return ownerProperty;
    }

    public void setOwnerProperty(String ownerProperty) {
        this.ownerProperty = ownerProperty;
    }
    
}