package com.newsee.owner.entity;

import java.util.Date;

public class OwnerHouseGarageInfo {
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
    private Long buildingArea;

    
    private Long developer;
    
    private String builder;
    
    private Date deliveryTime;
    
    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long updateUserId;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date updateTime;

    private String createUserName;
    
    private String updateUserName;
    
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

    public Long getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(Long buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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