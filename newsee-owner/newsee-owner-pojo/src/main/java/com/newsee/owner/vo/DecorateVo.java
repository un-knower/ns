package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by niyang on 2017/9/18.
 */
public class DecorateVo implements Serializable {

    @ApiModelProperty(value = "房产ID")
    private Long houseId;
    @ApiModelProperty(value = "申请日期 yyyy-MM-dd")
    private String handleTime;
    @ApiModelProperty(value = "装修开始日期 yyyy-MM-dd")
    private String startDecorateTime;
    @ApiModelProperty(value = "装修结束日期 yyyy-MM-dd")
    private String endDecorateTime;
    @ApiModelProperty(value = "装修状态 0未装修 1装修中 2已装修")
    private String decorateStage;
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    public String getStartDecorateTime() {
        return startDecorateTime;
    }

    public void setStartDecorateTime(String startDecorateTime) {
        this.startDecorateTime = startDecorateTime;
    }

    public String getEndDecorateTime() {
        return endDecorateTime;
    }

    public void setEndDecorateTime(String endDecorateTime) {
        this.endDecorateTime = endDecorateTime;
    }

    public String getDecorateStage() {
        return decorateStage;
    }

    public void setDecorateStage(String decorateStage) {
        this.decorateStage = decorateStage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
