package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class OwnerBuildingInfoVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -9057967442045873804L;

    /**
     * 
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "房产ID")
    private Long houseId;
    
    /**
     * 楼栋编号
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "楼栋编号")
    private String buildingNo;

    /**
     * 楼栋简称
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "楼栋简称")
    private String buildingShortName;

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

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getBuildingShortName() {
        return buildingShortName;
    }

    public void setBuildingShortName(String buildingShortName) {
        this.buildingShortName = buildingShortName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}