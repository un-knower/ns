package com.newsee.owner.entity;

import java.util.Date;

public class OwnerHousePrecinctExtendInfo {
    /**
     * house_base_info表的ID
     *
     * @mbggenerated
     */
    private Long houseId;

    /**
     * 开发商
     *
     * @mbggenerated
     */
    private Long developer;

    /**
     * 承建商
     *
     * @mbggenerated
     */
    private String builder;

    /**
     * 开工日期
     *
     * @mbggenerated
     */
    private Date startWorkTime;

    /**
     * 竣工日期
     *
     * @mbggenerated
     */
    private Date completeTime;

    /**
     * 交付日期
     *
     * @mbggenerated
     */
    private Date deliveryTime;

    /**
     * 接管日期
     *
     * @mbggenerated
     */
    private Date takeOverTime;

    /**
     * 退出日期
     *
     * @mbggenerated
     */
    private Date quitTime;

    /**
     * 建筑面积
     *
     * @mbggenerated
     */
    private Long buildingArea;

    /**
     * 占地面积
     *
     * @mbggenerated
     */
    private Long floorArea;

    /**
     * 地上面积
     *
     * @mbggenerated
     */
    private Long groundArea;

    /**
     * 地下面积
     *
     * @mbggenerated
     */
    private Long undergroundArea;

    /**
     * 总收费面积
     *
     * @mbggenerated
     */
    private Long chargeArea;

    /**
     * 已交付面积
     *
     * @mbggenerated
     */
    private Long deliveryArea;

    /**
     * 绿化面积
     *
     * @mbggenerated
     */
    private Long greenArea;

    /**
     * 辅助面积
     *
     * @mbggenerated
     */
    private Long assistArea;

    /**
     * 绿化率
     *
     * @mbggenerated
     */
    private Long greeningRate;

    /**
     * 容积率
     *
     * @mbggenerated
     */
    private Long plotRatio;

    /**
     * 总户数
     *
     * @mbggenerated
     */
    private Integer households;

    /**
     * 入住户数
     *
     * @mbggenerated
     */
    private Integer checkInHouseholds;

    /**
     * 地面车位数
     *
     * @mbggenerated
     */
    private Integer groundParkingSpace;

    /**
     * 地面车位收费标准
     *
     * @mbggenerated
     */
    private String groundParkingChargeStandard;

    /**
     * 临停车位
     *
     * @mbggenerated
     */
    private Integer tempParkingSpace;

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

    public Date getTakeOverTime() {
        return takeOverTime;
    }

    public void setTakeOverTime(Date takeOverTime) {
        this.takeOverTime = takeOverTime;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    public Long getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(Long buildingArea) {
        this.buildingArea = buildingArea;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public Long getGroundArea() {
        return groundArea;
    }

    public void setGroundArea(Long groundArea) {
        this.groundArea = groundArea;
    }

    public Long getUndergroundArea() {
        return undergroundArea;
    }

    public void setUndergroundArea(Long undergroundArea) {
        this.undergroundArea = undergroundArea;
    }

    public Long getChargeArea() {
        return chargeArea;
    }

    public void setChargeArea(Long chargeArea) {
        this.chargeArea = chargeArea;
    }

    public Long getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(Long deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public Long getGreenArea() {
        return greenArea;
    }

    public void setGreenArea(Long greenArea) {
        this.greenArea = greenArea;
    }

    public Long getAssistArea() {
        return assistArea;
    }

    public void setAssistArea(Long assistArea) {
        this.assistArea = assistArea;
    }

    public Long getGreeningRate() {
        return greeningRate;
    }

    public void setGreeningRate(Long greeningRate) {
        this.greeningRate = greeningRate;
    }

    public Long getPlotRatio() {
        return plotRatio;
    }

    public void setPlotRatio(Long plotRatio) {
        this.plotRatio = plotRatio;
    }

    public Integer getHouseholds() {
        return households;
    }

    public void setHouseholds(Integer households) {
        this.households = households;
    }

    public Integer getCheckInHouseholds() {
        return checkInHouseholds;
    }

    public void setCheckInHouseholds(Integer checkInHouseholds) {
        this.checkInHouseholds = checkInHouseholds;
    }

    public Integer getGroundParkingSpace() {
        return groundParkingSpace;
    }

    public void setGroundParkingSpace(Integer groundParkingSpace) {
        this.groundParkingSpace = groundParkingSpace;
    }

    public String getGroundParkingChargeStandard() {
        return groundParkingChargeStandard;
    }

    public void setGroundParkingChargeStandard(String groundParkingChargeStandard) {
        this.groundParkingChargeStandard = groundParkingChargeStandard;
    }

    public Integer getTempParkingSpace() {
        return tempParkingSpace;
    }

    public void setTempParkingSpace(Integer tempParkingSpace) {
        this.tempParkingSpace = tempParkingSpace;
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