package com.newsee.owner.vo;

import java.io.Serializable;
import java.util.List;

public class HouseBaseVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 5955603864369423707L;
    /**
     * 
     *
     * @mbggenerated
     */
    private Long houseId;
    /**
     * 租户ID
     *
     * @mbggenerated
     */
    private Long enterpriseId;

    /**
     * 公司ID
     *
     * @mbggenerated
     */
    private Long organizationId;

    /**
     * 项目ID
     *
     * @mbggenerated
     */
    private Long precinctId;

    /**
     * 楼栋ID
     *
     * @mbggenerated
     */
    private Long buildingId;

    /**
     * 房号
     *
     */
    private String houseName;

    private String houseNo;
    /**
     * 房产名称
     */
    private String houseFullName;
    /**
     * 房产类型 0.初始值 1.区域 2.项目 3.组团 4.楼栋 5.单元 6.房产 7.车库 8.车位 9.公共区域
     *
     * @mbggenerated
     */
    private String houseType;

    private String houseTypeName;
    /**
     * 父Id
     *
     * @mbggenerated
     */
    private Long parentId;

    /**
     * 树节点路径，例：/1/2/3/
     *
     * @mbggenerated
     */
    private String path;

    /**
     * 层级
     *
     * @mbggenerated
     */
    private Integer level;

    /**
     * 排序
     *
     * @mbggenerated
     */
    private Integer sort;

    /**
     * 房态当前状态：10空置  20未领  30空关  40入住  50出租
     *
     * @mbggenerated
     */
    private String stage;

    private String rentStage;
    /**
     * 装修状态  0未装修 1装修中 2已装修
     *
     * @mbggenerated
     */
    private String decorateStage;

    private String stageName;
    /**
     * 是否停用 0.否 1是
     *
     * @mbggenerated
     */
    private Integer isBlockUp;

    /**
     * 是否虚拟，0否 1是
     *
     * @mbggenerated
     */
    private Integer isVirtual;

    /**
     * 是否锁定 0否 1是
     *
     * @mbggenerated
     */
    private Integer isLock;

    /**
     * 房屋操作状态：0.未被操作 1.被合并 2.被拆封 
     *
     * @mbggenerated
     */
    private Integer editStatus;

    /**
     * 继承房间，拆分，存储父房产号；合并，存储父房产号，以逗号隔开
     *
     * @mbggenerated
     */
    private String parentHouseIds;

    List<HouseStageDetailVo> houseStageDetailVoList;
    
    public List<HouseStageDetailVo> getHouseStageDetailVoList() {
        return houseStageDetailVoList;
    }

    public void setHouseStageDetailVoList(List<HouseStageDetailVo> houseStageDetailVoList) {
        this.houseStageDetailVoList = houseStageDetailVoList;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(Long precinctId) {
        this.precinctId = precinctId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseFullName() {
        return houseFullName;
    }

    public void setHouseFullName(String houseFullName) {
        this.houseFullName = houseFullName;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
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

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
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

    public String getHouseTypeName() {
        return houseTypeName;
    }

    public void setHouseTypeName(String houseTypeName) {
        this.houseTypeName = houseTypeName;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

}
