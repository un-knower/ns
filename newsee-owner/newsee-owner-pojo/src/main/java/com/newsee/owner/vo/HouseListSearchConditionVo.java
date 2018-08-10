package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by niyang on 2017/9/13.
 */
public class HouseListSearchConditionVo implements Serializable{

    @ApiModelProperty(value = "页数")
    private Integer pageSize;
    @ApiModelProperty(value = "当前页")
    private Integer pageIndex;

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
}
