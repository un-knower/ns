package com.newsee.owner.entity;

import java.util.Date;

public class OwnerHouseStageExtendInfoEmpty {
    private Long detailId;

    private Date emtpyStartDate;

    private Date emptyEndDate;

    private Date sysTime;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Date getEmtpyStartDate() {
        return emtpyStartDate;
    }

    public void setEmtpyStartDate(Date emtpyStartDate) {
        this.emtpyStartDate = emtpyStartDate;
    }

    public Date getEmptyEndDate() {
        return emptyEndDate;
    }

    public void setEmptyEndDate(Date emptyEndDate) {
        this.emptyEndDate = emptyEndDate;
    }

    public Date getSysTime() {
        return sysTime;
    }

    public void setSysTime(Date sysTime) {
        this.sysTime = sysTime;
    }
}