package com.newsee.owner.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OwnerBuildingExtendInfoVo implements Serializable{

    private static final long serialVersionUID = 4085764230448588892L;

    private Long houseId;

    private Long developer;

    private String builder;

    private Date startWorkTime;

    private Date completeTime;

    private Date deliveryTime;

    private BigDecimal buildingArea;

    private Integer totalFloorNumber;

    private Integer totalRoomNumber;

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

    public BigDecimal getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
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
}
