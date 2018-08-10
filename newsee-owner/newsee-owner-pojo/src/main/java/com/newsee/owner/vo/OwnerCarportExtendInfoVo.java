package com.newsee.owner.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class OwnerCarportExtendInfoVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -1879016682382046032L;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long houseId;


    /**
     * 套内面积
     *
     * @mbggenerated
     */
    private BigDecimal insideArea;

    /**
     * 公摊面积
     *
     * @mbggenerated
     */
    private BigDecimal poolArea;
    
    @ApiModelProperty(value = "移交日期")
    private Date deliveryTime;
    
    @ApiModelProperty(value = "收房日期")
    private Date takeOverTime;

    /**
     * 维保开始日期
     *
     * @mbggenerated
     */
    private Date maintenanceStartDate;

    /**
     * 维保结束日期
     *
     * @mbggenerated
     */
    private Date maintenanceEndDate;

    @ApiModelProperty(value = "维保日期")
    private List<Date> maintenanceDate;
    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public BigDecimal getInsideArea() {
        return insideArea;
    }

    public void setInsideArea(BigDecimal insideArea) {
        this.insideArea = insideArea;
    }

    public BigDecimal getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(BigDecimal poolArea) {
        this.poolArea = poolArea;
    }

    public Date getMaintenanceStartDate() {
        return maintenanceStartDate;
    }

    public void setMaintenanceStartDate(Date maintenanceStartDate) {
        this.maintenanceStartDate = maintenanceStartDate;
    }

    public Date getMaintenanceEndDate() {
        return maintenanceEndDate;
    }

    public void setMaintenanceEndDate(Date maintenanceEndDate) {
        this.maintenanceEndDate = maintenanceEndDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public List<Date> getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(List<Date> maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

}