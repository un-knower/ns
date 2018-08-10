package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by niyang on 2017/9/28.
 */
public class HouseSplitInfoVo implements Serializable {
    @ApiModelProperty(value = "房间号")
    private String houseNo;
    @ApiModelProperty(value = "计费面积")
    private BigDecimal chargingArea;

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public BigDecimal getChargingArea() {
        return chargingArea;
    }

    public void setChargingArea(BigDecimal chargingArea) {
        this.chargingArea = chargingArea;
    }
}
