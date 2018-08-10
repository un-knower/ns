package com.newsee.owner.entity;

import java.util.Date;

public class OwnerHouseCarportInfo {
    /**
     * 
     *
     * @mbggenerated
     */
    private Long houseId;

    /**
     * 车位编号
     *
     * @mbggenerated
     */
    private String carportNo;

    /**
     * 车位简称
     *
     * @mbggenerated
     */
    private String carportShortName;

    /**
     * 主房产相关标识位 0无 1主房产 2次房产
     *
     * @mbggenerated
     */
    private Byte mainHouseFlag;

    /**
     * 车位类型，记录最小ID
     *
     * @mbggenerated
     */
    private String carportTypeId;

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

    private String createUserName;
    
    private String updateUserName;
    
    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getCarportNo() {
        return carportNo;
    }

    public void setCarportNo(String carportNo) {
        this.carportNo = carportNo;
    }

    public String getCarportShortName() {
        return carportShortName;
    }

    public void setCarportShortName(String carportShortName) {
        this.carportShortName = carportShortName;
    }

    public Byte getMainHouseFlag() {
        return mainHouseFlag;
    }

    public void setMainHouseFlag(Byte mainHouseFlag) {
        this.mainHouseFlag = mainHouseFlag;
    }

    public String getCarportTypeId() {
        return carportTypeId;
    }

    public void setCarportTypeId(String carportTypeId) {
        this.carportTypeId = carportTypeId;
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