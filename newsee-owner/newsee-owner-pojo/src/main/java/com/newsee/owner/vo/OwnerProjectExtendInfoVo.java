package com.newsee.owner.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class OwnerProjectExtendInfoVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 2344753837757145622L;

    /**
     * house_base_info表的ID
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "房产ID")
    private Long houseId;

    /**
     * 开发商
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "开发商")
    private Long developer;

    /**
     * 承建商
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "承建商")
    private String builder;

    /**
     * 开工日期
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "开工日期")
    private Date startWorkTime;

    /**
     * 竣工日期
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "竣工日期")
    private Date completeTime;

    /**
     * 交付日期
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "交付日期")
    private Date deliveryTime;

    /**
     * 接管日期
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "接管日期")
    private Date takeOverTime;

    /**
     * 退出日期
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "退出日期")
    private Date quitTime;

    /**
     * 建筑面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "建筑面积")
    private BigDecimal buildingArea;

    /**
     * 占地面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "占地面积")
    private BigDecimal floorArea;

    /**
     * 地上面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "地上面积")
    private BigDecimal groundArea;

    /**
     * 地下面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "地下面积")
    private BigDecimal undergroundArea;

    /**
     * 总收费面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "总收费面积")
    private BigDecimal chargeArea;

    /**
     * 已交付面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "已交付面积")
    private BigDecimal deliveryArea;

    /**
     * 绿化面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "绿化面积")
    private BigDecimal greenArea;

    /**
     * 辅助面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "辅助面积")
    private BigDecimal assistArea;

    /**
     * 绿化率
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "绿化率")
    private BigDecimal greeningRate;

    /**
     * 容积率
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "容积率")
    private BigDecimal plotRatio;

    /**
     * 总户数
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "总户数")
    private Integer households;

    /**
     * 入住户数
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "入住户数")
    private Integer checkInHouseholds;

    /**
     * 地面车位数
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "地面车位数")
    private Integer groundParkingSpace;

    /**
     * 地面车位收费标准
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "地面车位收费标准")
    private String groundParkingChargeStandard;

    /**
     * 临停车位
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "临停车位")
    private Integer tempParkingSpace;

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

    public BigDecimal getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    public BigDecimal getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    public BigDecimal getGroundArea() {
        return groundArea;
    }

    public void setGroundArea(BigDecimal groundArea) {
        this.groundArea = groundArea;
    }

    public BigDecimal getUndergroundArea() {
        return undergroundArea;
    }

    public void setUndergroundArea(BigDecimal undergroundArea) {
        this.undergroundArea = undergroundArea;
    }

    public BigDecimal getChargeArea() {
        return chargeArea;
    }

    public void setChargeArea(BigDecimal chargeArea) {
        this.chargeArea = chargeArea;
    }

    public BigDecimal getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(BigDecimal deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public BigDecimal getGreenArea() {
        return greenArea;
    }

    public void setGreenArea(BigDecimal greenArea) {
        this.greenArea = greenArea;
    }

    public BigDecimal getAssistArea() {
        return assistArea;
    }

    public void setAssistArea(BigDecimal assistArea) {
        this.assistArea = assistArea;
    }

    public BigDecimal getGreeningRate() {
        return greeningRate;
    }

    public void setGreeningRate(BigDecimal greeningRate) {
        this.greeningRate = greeningRate;
    }

    public BigDecimal getPlotRatio() {
        return plotRatio;
    }

    public void setPlotRatio(BigDecimal plotRatio) {
        this.plotRatio = plotRatio;
    }

}