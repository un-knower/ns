package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class OwnerUnitInfoVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -3500254541261962031L;

    /**
     * 
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "房产ID")
    private Long houseId;
    
    /**
     * 单元编号
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "单元编号")
    private String unitNo;

    /**
     * 单元简称
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "单元简称")
    private String unitShortName;

    /**
     * 备注
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getUnitShortName() {
        return unitShortName;
    }

    public void setUnitShortName(String unitShortName) {
        this.unitShortName = unitShortName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}