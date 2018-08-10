package com.newsee.owner.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class OwnerCarportInfoVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 5979742681413742940L;

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

    private String carportTypeName;

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
    private BigDecimal chargingArea;

    /**
     * 建筑面积
     *
     * @mbggenerated
     */
    private BigDecimal buildingArea;

    /**
     * 辅助计费面积
     *
     * @mbggenerated
     */
    private BigDecimal assistChargingArea;

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

    public String getCarportTypeName() {
        return carportTypeName;
    }

    public void setCarportTypeName(String carportTypeName) {
        this.carportTypeName = carportTypeName;
    }
    
}