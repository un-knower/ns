package com.newsee.owner.entity;

import java.util.Date;

public class OwnerHouseHouseExtendInfo {
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
     * 花园面积
     *
     * @mbggenerated
     */
    private Long gardenArea;

    /**
     * 地下室面积
     *
     * @mbggenerated
     */
    private Long basementArea;

    /**
     * 赠送面积
     *
     * @mbggenerated
     */
    private Long giftArea;

    /**
     * 房产性质ID
     *
     * @mbggenerated
     */
    private String roomPropertyId;

    /**
     * 房产户型ID
     *
     * @mbggenerated
     */
    private String roomHouseType;

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

    public Long getGardenArea() {
        return gardenArea;
    }

    public void setGardenArea(Long gardenArea) {
        this.gardenArea = gardenArea;
    }

    public Long getBasementArea() {
        return basementArea;
    }

    public void setBasementArea(Long basementArea) {
        this.basementArea = basementArea;
    }

    public Long getGiftArea() {
        return giftArea;
    }

    public void setGiftArea(Long giftArea) {
        this.giftArea = giftArea;
    }

    public String getRoomPropertyId() {
        return roomPropertyId;
    }

    public void setRoomPropertyId(String roomPropertyId) {
        this.roomPropertyId = roomPropertyId;
    }

    public String getRoomHouseType() {
        return roomHouseType;
    }

    public void setRoomHouseType(String roomHouseType) {
        this.roomHouseType = roomHouseType;
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