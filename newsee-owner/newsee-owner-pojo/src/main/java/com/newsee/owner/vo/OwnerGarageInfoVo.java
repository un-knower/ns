package com.newsee.owner.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OwnerGarageInfoVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -4426063893566424717L;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long houseId;
    
    /**
     * 车库编号
     *
     * @mbggenerated
     */
    private String garageNo;

    /**
     * 车库简称
     *
     * @mbggenerated
     */
    private String garageShortName;

    /**
     * 总车位数
     *
     * @mbggenerated
     */
    private Integer carportCount;

    /**
     * 建筑面积
     *
     * @mbggenerated
     */
    private BigDecimal buildingArea;
    
    private Long developer;
    
    private String builder;
    
    private Date deliveryTime;
    
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

    public String getGarageNo() {
        return garageNo;
    }

    public void setGarageNo(String garageNo) {
        this.garageNo = garageNo;
    }

    public String getGarageShortName() {
        return garageShortName;
    }

    public void setGarageShortName(String garageShortName) {
        this.garageShortName = garageShortName;
    }

    public Integer getCarportCount() {
        return carportCount;
    }

    public void setCarportCount(Integer carportCount) {
        this.carportCount = carportCount;
    }

    public String getRemark() {
        return remark;
    }

    public BigDecimal getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

}