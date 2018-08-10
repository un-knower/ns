package com.newsee.owner.vo;

import java.util.Date;
import java.util.List;

import com.newsee.common.vo.BaseVo;
import com.newsee.owner.entity.OwnerHouseBaseInfo;

import io.swagger.annotations.ApiModelProperty;

public class CarVo extends BaseVo{

    private static final long serialVersionUID = -554878253798755489L;

    /**
     * 
     */
    private Long ownerCarId;
    private Long enterpriseId;
    private Long organizationId;
    /**
     * 业户ID
     */
    @ApiModelProperty("业户ID")
    private Long ownerId;

    private String ownerName;
    
    private String certificate;
    
    private String mobile;
    private CustomerVo customerVo;
    /**
     * 车牌号码
     */
    @ApiModelProperty("车牌号码")
    private String carNumber;

    /**
     * 停车证
     */
    @ApiModelProperty("停车证")
    private String parkingPermit;

    /**
     * 车辆品牌ID
     */
    @ApiModelProperty("车辆品牌ID")
    private String vehicleBrandId;

    private String vehicleBrandName;
    /**
     * 车辆颜色ID
     */
    @ApiModelProperty("车辆颜色ID")
    private String carColorId;

    private String carColorName;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 是否当前使用车辆 0否 1是
     */
    @ApiModelProperty("是否当前使用车辆 0否 1是")
    private String isCurrentUse;

    /**
     * 是否固定车位 0否 1是
     */
    @ApiModelProperty("是否固定车位 0否 1是")
    private String isFixedParking;
    
    @ApiModelProperty("固定车位")
    private List<OwnerHouseBaseInfo> carportList;
    
    @ApiModelProperty("登记人ID")
    private Long createUserId;

    @ApiModelProperty("登记人名称")
    private String createUserName;
    
    @ApiModelProperty("登记时间")
    private Date createTime;

    @ApiModelProperty("更新人ID")
    private Long updateUserId;

    @ApiModelProperty("更新人名称")
    private String updateUserName;
    
    @ApiModelProperty("更新时间")
    private Date updateTime;
    
    public Long getOwnerCarId() {
        return ownerCarId;
    }

    public void setOwnerCarId(Long ownerCarId) {
        this.ownerCarId = ownerCarId;
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

    public List<OwnerHouseBaseInfo> getCarportList() {
        return carportList;
    }

    public void setCarportList(List<OwnerHouseBaseInfo> carportList) {
        this.carportList = carportList;
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

    public CustomerVo getCustomerVo() {
        return customerVo;
    }

    public void setCustomerVo(CustomerVo customerVo) {
        this.customerVo = customerVo;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVehicleBrandName() {
        return vehicleBrandName;
    }

    public void setVehicleBrandName(String vehicleBrandName) {
        this.vehicleBrandName = vehicleBrandName;
    }

    public String getCarColorName() {
        return carColorName;
    }

    public void setCarColorName(String carColorName) {
        this.carColorName = carColorName;
    }

}
