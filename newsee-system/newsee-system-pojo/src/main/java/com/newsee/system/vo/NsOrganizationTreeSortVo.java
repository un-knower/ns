package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class NsOrganizationTreeSortVo implements Serializable {

    private static final long serialVersionUID = -6193842567278870313L;

    /** 被拖拽的节点ID */
    @ApiModelProperty(value = "被拖拽的节点ID")
    private Long organizationId;
    
    /**目的位置的父节点Id */
    @ApiModelProperty(value = "目的位置的父节点Id")
    private Long organizationParentId;
    
    /**目的位置排序号*/
    @ApiModelProperty(value = "目的位置排序号")
    private Integer sort;
    
    /**目的位置以下的本层的所有组织ID */
    @ApiModelProperty(value = "目的位置以下的本层的所有组织ID ")
    private List<Long> sortOrganizationIds;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getOrganizationParentId() {
        return organizationParentId;
    }

    public void setOrganizationParentId(Long organizationParentId) {
        this.organizationParentId = organizationParentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<Long> getSortOrganizationIds() {
        return sortOrganizationIds;
    }

    public void setSortOrganizationIds(List<Long> sortOrganizationIds) {
        this.sortOrganizationIds = sortOrganizationIds;
    }
    
    
}
