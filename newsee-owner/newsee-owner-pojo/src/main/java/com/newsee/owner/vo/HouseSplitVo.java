package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by niyang on 2017/9/28.
 */
public class HouseSplitVo implements Serializable {
    @ApiModelProperty(value = "拆分房间ID")
    private Long houseId;
    @ApiModelProperty(value = "拆分信息")
    private List<HouseSplitInfoVo> houseSplitInfoVoList;

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public List<HouseSplitInfoVo> getHouseSplitInfoVoList() {
        return houseSplitInfoVoList;
    }

    public void setHouseSplitInfoVoList(List<HouseSplitInfoVo> houseSplitInfoVoList) {
        this.houseSplitInfoVoList = houseSplitInfoVoList;
    }
}
