package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by niyang on 2017/10/13.
 */
public class HouseViewVo implements Serializable {


    private static final long serialVersionUID = 3986819231959218163L;

    @ApiModelProperty(value = "房产ID")
    private Long houseId;

    @ApiModelProperty(value = "房号")
    private String houseName;
    
    @ApiModelProperty(value = "房产名称")
    private String houseFullName;
    
    private String houseType;
    
    @ApiModelProperty(value = "房产类型ID")
    private String roomTypeId;

    @ApiModelProperty(value = "房产类型名称")
    private String roomTypeName;

    @ApiModelProperty(value = "计费面积")
    private BigDecimal chargingArea;

    @ApiModelProperty(value = "查询年份")
    private String year;
    
    @ApiModelProperty(value = "当前房态")
    private String stageName;
    
    @ApiModelProperty(value = "欠费情况")
    private BigDecimal arrearage;

    @ApiModelProperty(value = "售楼变更历史")
    private List<HouseStageDetailVo> salesStageVoList;

    @ApiModelProperty(value = "收房变更历史")
    private List<HouseStageDetailVo> takeStageVoList;

    @ApiModelProperty(value = "入住变更历史")
    private List<HouseStageDetailVo> checkInStageVoList;

    @ApiModelProperty(value = "装修变更历史")
    private List<HouseStageDetailVo> decorateStageVoList;

    @ApiModelProperty(value = "出租变更历史")
    private List<HouseStageDetailVo> rentStageVoList;

    @ApiModelProperty(value = "业主信息")
    private CustomerVo customerVo;

    @ApiModelProperty(value = "家庭信息")
    private List<FamilyInfoVo> familyInfoList;
    
    @ApiModelProperty(value = "备注")
    private String remark;
    
    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public BigDecimal getChargingArea() {
        return chargingArea;
    }

    public void setChargingArea(BigDecimal chargingArea) {
        this.chargingArea = chargingArea;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public BigDecimal getArrearage() {
        return arrearage;
    }

    public void setArrearage(BigDecimal arrearage) {
        this.arrearage = arrearage;
    }

    public CustomerVo getCustomerVo() {
        return customerVo;
    }

    public void setCustomerVo(CustomerVo customerVo) {
        this.customerVo = customerVo;
    }

    public List<FamilyInfoVo> getFamilyInfoList() {
        return familyInfoList;
    }

    public void setFamilyInfoList(List<FamilyInfoVo> familyInfoList) {
        this.familyInfoList = familyInfoList;
    }

    public String getHouseFullName() {
        return houseFullName;
    }

    public void setHouseFullName(String houseFullName) {
        this.houseFullName = houseFullName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public List<HouseStageDetailVo> getSalesStageVoList() {
        return salesStageVoList;
    }

    public void setSalesStageVoList(List<HouseStageDetailVo> salesStageVoList) {
        this.salesStageVoList = salesStageVoList;
    }

    public List<HouseStageDetailVo> getTakeStageVoList() {
        return takeStageVoList;
    }

    public void setTakeStageVoList(List<HouseStageDetailVo> takeStageVoList) {
        this.takeStageVoList = takeStageVoList;
    }

    public List<HouseStageDetailVo> getCheckInStageVoList() {
        return checkInStageVoList;
    }

    public void setCheckInStageVoList(List<HouseStageDetailVo> checkInStageVoList) {
        this.checkInStageVoList = checkInStageVoList;
    }

    public List<HouseStageDetailVo> getDecorateStageVoList() {
        return decorateStageVoList;
    }

    public void setDecorateStageVoList(List<HouseStageDetailVo> decorateStageVoList) {
        this.decorateStageVoList = decorateStageVoList;
    }

    public List<HouseStageDetailVo> getRentStageVoList() {
        return rentStageVoList;
    }

    public void setRentStageVoList(List<HouseStageDetailVo> rentStageVoList) {
        this.rentStageVoList = rentStageVoList;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }
    
}
