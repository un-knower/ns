package com.newsee.owner.entity;

import java.util.Date;


public class OwnerHouseBaseInfo {
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

//    private Long departmentId;
    
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
     * 房产名称
     *
     * @mbggenerated
     */
    private String houseName;

    /**
     * 房产全称
     */
    private String houseFullName;
    
    private String houseNo;
    /**
     * 房产类型 0.初始值 1.区域 2.项目 3.组团 4.楼栋 5.单元 6.房产 7.车库 8.车位 9.公共区域
     *
     * @mbggenerated
     */
    private String houseType;

    /**
     * 父Id
     *
     * @mbggenerated
     */
    private Long parentId;
    
    /**
     * 父Name
     *
     * @mbggenerated
     */
    private String parentName;

    /**
     * 树节点路径，例：/1/2/3
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
     * 房态当前状态：10空置  20未领  30空关  40入住 
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

    /**
     * 删除标记位 0正常 1删除
     *
     * @mbggenerated
     */
    private Integer isDeleted;

    /**
     * 创建者ID
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 更新者ID
     *
     * @mbggenerated
     */
    private Long updateUserId;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    private String uuid;

    private Boolean isHasChild;
    
    private String createUserName;
    
    private String updateUserName;
    
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}