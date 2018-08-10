package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class OwnerProjectPropertyInfoVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 8684027428279989507L;

    @ApiModelProperty(value = "房产ID")
    private Long houseId;

    /**
     * 项目负责人
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "项目负责人")
    private String proManager;

    /**
     * 项目负责人电话
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "项目负责人电话")
    private String proManagerPhone;

    /**
     * 管理处电话
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "管理处电话")
    private String managementPhone;

    /**
     * 客服电话
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "客服电话")
    private String servicePhone;

    /**
     * 物业管理用房面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "物业管理用房面积")
    private BigDecimal propertyManageArea;

    /**
     * 物业经营用房面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "物业经营用房面积")
    private BigDecimal propertyOperateArea;

    /**
     * 社区用房面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "社区用房面积")
    private BigDecimal communityArea;

    /**
     * 会馆面积
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "会馆面积")
    private BigDecimal hallArea;

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getManagementPhone() {
        return managementPhone;
    }

    public void setManagementPhone(String managementPhone) {
        this.managementPhone = managementPhone;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getProManager() {
        return proManager;
    }

    public void setProManager(String proManager) {
        this.proManager = proManager;
    }

    public String getProManagerPhone() {
        return proManagerPhone;
    }

    public void setProManagerPhone(String proManagerPhone) {
        this.proManagerPhone = proManagerPhone;
    }

    public BigDecimal getPropertyManageArea() {
        return propertyManageArea;
    }

    public void setPropertyManageArea(BigDecimal propertyManageArea) {
        this.propertyManageArea = propertyManageArea;
    }

    public BigDecimal getPropertyOperateArea() {
        return propertyOperateArea;
    }

    public void setPropertyOperateArea(BigDecimal propertyOperateArea) {
        this.propertyOperateArea = propertyOperateArea;
    }

    public BigDecimal getCommunityArea() {
        return communityArea;
    }

    public void setCommunityArea(BigDecimal communityArea) {
        this.communityArea = communityArea;
    }

    public BigDecimal getHallArea() {
        return hallArea;
    }

    public void setHallArea(BigDecimal hallArea) {
        this.hallArea = hallArea;
    }

}