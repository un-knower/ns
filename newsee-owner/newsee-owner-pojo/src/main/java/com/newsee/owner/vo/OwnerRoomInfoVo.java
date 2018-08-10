package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class OwnerRoomInfoVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -1139933175437541586L;

    /**
     * 
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "房产ID")
    private Long houseId;
    
    /**
     * 房产编号
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "房产编号")
    private String roomNo;

    /**
     * 房产简称
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "房产简称")
    private String roomShortName;

    /**
     * 主房产相关标识位 0无 1主房产 2次房产
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "主房产相关标识位 0无 1主房产 2次房产")
    private Integer mainHouseFlag;

    /**
     * 楼层
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "楼层")
    private Integer floor;

    /**
     * 楼层号
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "楼层号")
    private String floorNum;

    /**
     * 房产类型，记录最小ID
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "房产类型，记录最小ID")
    private String roomTypeId;

    private String roomTypeName;
    /**
     * 助记符
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "助记符")
    private String mnemonic;

    /**
     * 计费面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "计费面积")
    private BigDecimal chargingArea;

    /**
     * 建筑面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "建筑面积")
    private BigDecimal buildingArea;

    /**
     * 辅助计费面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "辅助计费面积")
    private BigDecimal assistChargingArea;
    
    @ApiModelProperty(value = "备注")
    private String remark;
    
    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomShortName() {
        return roomShortName;
    }

    public void setRoomShortName(String roomShortName) {
        this.roomShortName = roomShortName;
    }

    public Integer getMainHouseFlag() {
        return mainHouseFlag;
    }

    public void setMainHouseFlag(Integer mainHouseFlag) {
        this.mainHouseFlag = mainHouseFlag;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public BigDecimal getChargingArea() {
        return chargingArea;
    }

    public void setChargingArea(BigDecimal chargingArea) {
        this.chargingArea = chargingArea;
    }

    public BigDecimal getAssistChargingArea() {
        return assistChargingArea;
    }

    public void setAssistChargingArea(BigDecimal assistChargingArea) {
        this.assistChargingArea = assistChargingArea;
    }

    public BigDecimal getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }
    
}