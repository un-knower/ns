package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by niyang on 2017/9/28.
 */
public class HouseCombineVo implements Serializable {

    @ApiModelProperty(value = "合并房间ID")
    private List<Long> houseId;
    @ApiModelProperty(value = "计费面积")
    private BigDecimal chargingArea;

    public List<Long> getHouseId() {
        return houseId;
    }

    public void setHouseId(List<Long> houseId) {
        this.houseId = houseId;
    }

    public BigDecimal getChargingArea() {
        return chargingArea;
    }

    public void setChargingArea(BigDecimal chargingArea) {
        this.chargingArea = chargingArea;
    }
}
