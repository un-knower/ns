package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OwnerRoomExtendInfoVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 2857813780655800309L;

    /**
     * 
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "房产ID")
    private Long houseId;

    /**
     * 套内面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "套内面积")
    private BigDecimal insideArea;

    /**
     * 公摊面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "公摊面积")
    private BigDecimal poolArea;

    /**
     * 花园面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "花园面积")
    private BigDecimal gardenArea;

    /**
     * 地下室面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "地下室面积")
    private BigDecimal basementArea;

    /**
     * 赠送面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "赠送面积")
    private BigDecimal giftArea;

    /**
     * 房产性质ID
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "房产性质ID")
    private String roomPropertyId;

    /**
     * 房产户型ID
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "房产户型ID")
    private String roomHouseType;

    @ApiModelProperty(value = "移交日期")
    private Date deliveryTime;
    
    @ApiModelProperty(value = "收房日期")
    private Date takeOverTime;
    
    /**
     * 维保开始日期
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "维保开始日期")
    private Date maintenanceStartDate;

    /**
     * 维保结束日期
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "维保结束日期")
    private Date maintenanceEndDate;

    @ApiModelProperty(value = "维保日期")
    private List<Date> maintenanceDate;
    /**
     * 备注
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public BigDecimal getInsideArea() {
        return insideArea;
    }

    public void setInsideArea(BigDecimal insideArea) {
        this.insideArea = insideArea;
    }

    public BigDecimal getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(BigDecimal poolArea) {
        this.poolArea = poolArea;
    }

    public BigDecimal getGardenArea() {
        return gardenArea;
    }

    public void setGardenArea(BigDecimal gardenArea) {
        this.gardenArea = gardenArea;
    }

    public BigDecimal getBasementArea() {
        return basementArea;
    }

    public void setBasementArea(BigDecimal basementArea) {
        this.basementArea = basementArea;
    }

    public BigDecimal getGiftArea() {
        return giftArea;
    }

    public void setGiftArea(BigDecimal giftArea) {
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

    public List<Date> getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(List<Date> maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

}