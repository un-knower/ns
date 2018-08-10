package com.newsee.owner.entity;

import java.util.Date;


public class OwnerHouseCarportExtendInfo {
    /**
     * 
     *
     * @mbggenerated
     */
    private Long houseId;

    /**
     * 套内面积
     *
     * @mbggenerated
     */
    private Long insideArea;

    /**
     * 公摊面积
     *
     * @mbggenerated
     */
    private Long poolArea;
    
    /**
     * 移交日期
     *
     * @mbggenerated
     */
    private Date deliveryTime;
    
    /**
     * 收房日期
     *
     * @mbggenerated
     */
    private Date takeOverTime;

    /**
     * 维保开始日期
     *
     * @mbggenerated
     */
    private Date maintenanceStartDate;

    /**
     * 维保结束日期
     *
     * @mbggenerated
     */
    private Date maintenanceEndDate;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;


    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getInsideArea() {
        return insideArea;
    }

    public void setInsideArea(Long insideArea) {
        this.insideArea = insideArea;
    }

    public Long getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(Long poolArea) {
        this.poolArea = poolArea;
    }

    public Date getMaintenanceStartDate() {
        return maintenanceStartDate;
    }

    public void setMaintenanceStartDate(Date maintenanceStartDate) {
        this.maintenanceStartDate = maintenanceStartDate;
    }

    public Date getMaintenanceEndDate() {
        return maintenanceEndDate;
    }

    public void setMaintenanceEndDate(Date maintenanceEndDate) {
        this.maintenanceEndDate = maintenanceEndDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getTakeOverTime() {
        return takeOverTime;
    }

    public void setTakeOverTime(Date takeOverTime) {
        this.takeOverTime = takeOverTime;
    }

}