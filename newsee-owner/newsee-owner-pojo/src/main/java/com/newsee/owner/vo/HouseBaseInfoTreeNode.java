package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by niyang on 2017/9/13.
 */
public class HouseBaseInfoTreeNode implements Serializable {

    private static final long serialVersionUID = 4577406837674997647L;
    @ApiModelProperty(value = "房产ID")
    private Long houseId;
    @ApiModelProperty(value = "公司ID")
    private Long organizationId;
    @ApiModelProperty(value = "房号")
    private String houseName;
    @ApiModelProperty(value = "房产名称")
    private String houseFullName;
    @ApiModelProperty(value = "房产类型 0.初始值 1.区域 2.项目 3.组团 4.楼栋 5.单元 6.房产 7.车库 8.车位 9.公共区域")
    private String houseType;
    @ApiModelProperty(value = "父ID")
    private Long parentId;
    @ApiModelProperty(value = "树节点路径")
    private String path;
    @ApiModelProperty(value = "层级")
    private Integer level;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "房态当前状态：10空置  20未领  30空关  40入住  50出租")
    private String stage;
    @ApiModelProperty(value = "出租状态  0未出租 1已出租")
    private String rentStage;
    @ApiModelProperty(value = "装修状态  0未装修 1装修中 2已装修")
    private String decorateStage;
    @ApiModelProperty(value = "是否停用 0.否 1是")
    private Integer isBlockUp;
    @ApiModelProperty(value = "是否虚拟，0否 1是")
    private Integer isVirtual;
    @ApiModelProperty(value = "是否锁定 0否 1是")
    private Integer isLock;
    @ApiModelProperty(value = "房屋操作状态：0.未被操作 1.被合并 2.被拆封 ")
    private Integer editStatus;
    @ApiModelProperty(value = "继承房间，拆分，存储父房产号；合并，存储父房产号，以逗号隔开")
    private String parentHouseIds;

    @ApiModelProperty(value = "子房产列表")
    private List<HouseBaseInfoTreeNode> childOwnerHouseBaseInfoTreeNodeList=new ArrayList<>();
    @ApiModelProperty()
    private Boolean isHasChild;

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsBlockUp() {
        return isBlockUp;
    }

    public void setIsBlockUp(Integer isBlockUp) {
        this.isBlockUp = isBlockUp;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Integer getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(Integer editStatus) {
        this.editStatus = editStatus;
    }

    public String getParentHouseIds() {
        return parentHouseIds;
    }

    public void setParentHouseIds(String parentHouseIds) {
        this.parentHouseIds = parentHouseIds;
    }

    public List<HouseBaseInfoTreeNode> getChildOwnerHouseBaseInfoTreeNodeList() {
        return childOwnerHouseBaseInfoTreeNodeList;
    }

    public void setChildOwnerHouseBaseInfoTreeNodeList(List<HouseBaseInfoTreeNode> childOwnerHouseBaseInfoTreeNodeList) {
        this.childOwnerHouseBaseInfoTreeNodeList = childOwnerHouseBaseInfoTreeNodeList;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getIsHasChild() {
        return isHasChild;
    }

    public void setIsHasChild(Boolean isHasChild) {
        this.isHasChild = isHasChild;
    }

    public String getHouseFullName() {
        return houseFullName;
    }

    public void setHouseFullName(String houseFullName) {
        this.houseFullName = houseFullName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getRentStage() {
        return rentStage;
    }

    public void setRentStage(String rentStage) {
        this.rentStage = rentStage;
    }

    public String getDecorateStage() {
        return decorateStage;
    }

    public void setDecorateStage(String decorateStage) {
        this.decorateStage = decorateStage;
    }

}
