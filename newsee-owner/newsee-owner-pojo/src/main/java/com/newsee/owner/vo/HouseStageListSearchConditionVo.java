package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 房态操作列表搜索vo
 * Created by niyang on 2017/9/29.
 */
public class HouseStageListSearchConditionVo implements Serializable {

    @ApiModelProperty(value = "页数")
    private Integer pageSize;
    @ApiModelProperty(value = "当前页")
    private Integer pageIndex;


    /**
     * 列表类型 1.售楼 2.收房 3.装修 4.入住 5.出租
     */
    @ApiModelProperty(value = "列表类型 1.售楼 2.收房 3.装修 4.入住 5.出租")
    private Integer pageType;

    /**
     * 房产节点ID
     */
    @ApiModelProperty(value = "房产节点ID")
    private Long houseNodeId;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getHouseNodeId() {
        return houseNodeId;
    }

    public void setHouseNodeId(Long houseNodeId) {
        this.houseNodeId = houseNodeId;
    }

    public Integer getPageType() {
        return pageType;
    }

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }
}
