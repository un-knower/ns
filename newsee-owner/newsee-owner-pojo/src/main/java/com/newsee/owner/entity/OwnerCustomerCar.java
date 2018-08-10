package com.newsee.owner.entity;

import java.util.Date;

import com.newsee.common.entity.BaseEntity;

public class OwnerCustomerCar extends BaseEntity{
    /**
     * 
     *
     * @mbggenerated
     */
    private Long ownerCarId;

    private Long enterpriseId;
    private Long organizationId;
    /**
     * 业户ID
     *
     * @mbggenerated
     */
    private Long ownerId;

    /**
     * 车牌号码
     *
     * @mbggenerated
     */
    private String carNumber;

    /**
     * 停车证
     *
     * @mbggenerated
     */
    private String parkingPermit;

    /**
     * 车辆品牌ID
     *
     * @mbggenerated
     */
    private String vehicleBrandId;

    /**
     * 车辆颜色ID
     *
     * @mbggenerated
     */
    private String carColorId;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 是否当前使用车辆 0否 1是
     *
     * @mbggenerated
     */
    private String isCurrentUse;

    /**
     * 是否固定车位 0否 1是
     *
     * @mbggenerated
     */
    private String isFixedParking;

    /**
     * 
     *
     * @mbggenerated
     */
    private Byte isDeleted;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long createUserId;

    private String createUserName;
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

    private String updateUserName;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date sysTime;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long ownerBaseInfoOwnerId;

    public Long getOwnerCarId() {
        return ownerCarId;
    }

    public void setOwnerCarId(Long ownerCarId) {
        this.ownerCarId = ownerCarId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getParkingPermit() {
        return parkingPermit;
    }

    public void setParkingPermit(String parkingPermit) {
        this.parkingPermit = parkingPermit;
    }

    public String getVehicleBrandId() {
        return vehicleBrandId;
    }

    public void setVehicleBrandId(String vehicleBrandId) {
        this.vehicleBrandId = vehicleBrandId;
    }

    public String getCarColorId() {
        return carColorId;
    }

    public void setCarColorId(String carColorId) {
        this.carColorId = carColorId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsCurrentUse() {
        return isCurrentUse;
    }

    public void setIsCurrentUse(String isCurrentUse) {
        this.isCurrentUse = isCurrentUse;
    }

    public String getIsFixedParking() {
        return isFixedParking;
    }

    public void setIsFixedParking(String isFixedParking) {
        this.isFixedParking = isFixedParking;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
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

    public Date getSysTime() {
        return sysTime;
    }

    public void setSysTime(Date sysTime) {
        this.sysTime = sysTime;
    }

    public Long getOwnerBaseInfoOwnerId() {
        return ownerBaseInfoOwnerId;
    }

    public void setOwnerBaseInfoOwnerId(Long ownerBaseInfoOwnerId) {
        this.ownerBaseInfoOwnerId = ownerBaseInfoOwnerId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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