package com.newsee.owner.entity;

import java.util.Date;

public class OwnerHouseHouseInfo {
    /**
     * 
     *
     * @mbggenerated
     */
    private Long houseId;

    /**
     * 房产编号
     *
     * @mbggenerated
     */
    private String roomNo;

    /**
     * 房产简称
     *
     * @mbggenerated
     */
    private String roomShortName;

    /**
     * 主房产相关标识位 0无 1主房产 2次房产
     *
     * @mbggenerated
     */
    private Integer mainHouseFlag;

    /**
     * 楼层
     *
     * @mbggenerated
     */
    private Integer floor;

    /**
     * 楼层号
     *
     * @mbggenerated
     */
    private String floorNum;

    /**
     * 房产类型，记录最小ID
     *
     * @mbggenerated
     */
    private String roomTypeId;

    /**
     * 助记符
     *
     * @mbggenerated
     */
    private String mnemonic;

    /**
     * 计费面积
     *
     * @mbggenerated
     */
    private Long chargingArea;

    /**
     * 建筑面积
     *
     * @mbggenerated
     */
    private Long buildingArea;

    /**
     * 辅助计费面积
     *
     * @mbggenerated
     */
    private Long assistChargingArea;

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

    private String remark;

    private String createUserName;
    
    private String updateUserName;
    
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public Long getChargingArea() {
        return chargingArea;
    }

    public void setChargingArea(Long chargingArea) {
        this.chargingArea = chargingArea;
    }

    public Long getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(Long buildingArea) {
        this.buildingArea = buildingArea;
    }

    public Long getAssistChargingArea() {
        return assistChargingArea;
    }

    public void setAssistChargingArea(Long assistChargingArea) {
        this.assistChargingArea = assistChargingArea;
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