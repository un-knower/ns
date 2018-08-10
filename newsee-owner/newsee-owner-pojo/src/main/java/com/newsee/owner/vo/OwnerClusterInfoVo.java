package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class OwnerClusterInfoVo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 4928258752428693359L;

    /**
     * 
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "房产ID")
    private Long houseId;

    /**
     * 组团编号
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "组团编号")
    private String clusterNo;
    
    /**
     * 组团简称
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "组团简称")
    private String clusterShortName;

    /**
     * 开发商
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "开发商")
    private Long developer;

    /**
     * 承建商
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "承建商")
    private String builder;

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

    public String getClusterNo() {
        return clusterNo;
    }

    public void setClusterNo(String clusterNo) {
        this.clusterNo = clusterNo;
    }

    public String getClusterShortName() {
        return clusterShortName;
    }

    public void setClusterShortName(String clusterShortName) {
        this.clusterShortName = clusterShortName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getDeveloper() {
        return developer;
    }

    public void setDeveloper(Long developer) {
        this.developer = developer;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

}