package com.newsee.owner.entity;

import java.io.Serializable;
import java.util.Date;

import com.newsee.common.entity.BaseEntity;

import io.swagger.annotations.ApiModelProperty;

public class HouseListEntity extends BaseEntity implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -8148225807187298821L;

    private Long houseResultId;

    private Long enterpriseId;

    private Long houseId;

    private Long organizationId;
    /**
     * 房产类型 0.初始值 1.区域 2.项目 3.组团 4.楼栋 5.单元 6.房产 7.车库 8.车位 9.公共区域
     *
     * @mbggenerated
     */
    private String houseType;
    
    private String path;

    private Long precinctId;
    
    private String precinctName;
    
    private String projectName;

    private String groupName;

    private String buildingName;

    private String unitName;

    private String houseFullName;

    private String houseName;

    private String houseShortName;

    private String houseNo;

    private String houseStage;

    private String stageName;

    private Long chargingArea;

    private Long buildingArea;

    private Long insideArea;

    private Long poolArea;

    private Long assistChargingArea;

    private Long gardenArea;

    private Long basementArea;

    private Long giftArea;

    private Integer floor;

    private String floorNum;

    private Date maintenanceStartDate;

    private Date maintenanceEndDate;

    private Integer hasRelevance;

//    private String hasRelevanceName;
    
    private String roomTypeId;
    
//    private String roomTypeName;
    
    private String roomPropertyId;
    
//    private String roomPropertyName;
    
    private Long ownerId;

    private String ownerName;

    private String ownerPhone;

    private String certificate;
    
    private Integer isLock;

//    private String isLockName;

    private Integer isVirtual;

//    private String isVirtualName;

    private String rentStage;

    private String decorateStage;

    private Integer isBlockUp;

//    private String isBlockUpName;

    private Date blockUpTime;
    
    @ApiModelProperty("是否当前记录   0历史 1当前")
    private Byte isCurrentRecord;

    @ApiModelProperty("各房态管理列表是否当前 0历史 1当前")
    private String isNowDetail;

//    private String isCurrentRecordName;

    private Long detailId;

    private String salesStageName;

    private String takeStageName;

    private String checkInStageName;

    private String rentStageName;

    private String decorateStageName;

    private Date handleTime;

    private Date startTime;

    private Date endTime;

    private Date rentCheckinTime;

    @ApiModelProperty("房态操作动作：1售楼 2收房 3入住 4搬出 5出租 6转租 7退租 8空关 9转让 10装修")
    private String houseOperateType;

    private Long lesseeId;
    
    private String lesseeName;
    
    private String lesseeCertificate;

    private String lesseeMobile;

    private Long createHouseUserId;

    private Date createTime;
    
    private Long createUserId;

    private String createUserName;

    private Long updateUserId;

    private String updateUserName;

    private Date updateTime;
    
    private String propertyOwnerNames;
    
    public Long getHouseResultId() {
        return houseResultId;
    }

    public void setHouseResultId(Long houseResultId) {
        this.houseResultId = houseResultId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getHouseFullName() {
        return houseFullName;
    }

    public void setHouseFullName(String houseFullName) {
        this.houseFullName = houseFullName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseShortName() {
        return houseShortName;
    }

    public void setHouseShortName(String houseShortName) {
        this.houseShortName = houseShortName;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getHouseStage() {
        return houseStage;
    }

    public void setHouseStage(String houseStage) {
        this.houseStage = houseStage;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Long getChargingArea() {
        return chargingArea;
    }

    public void setChargingArea(Long chargingArea) {
        this.chargingArea = chargingArea;
    }

    public Long getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(Long buildingArea) {
        this.buildingArea = buildingArea;
    }

    public Long getInsideArea() {
        return insideArea;
    }

    public void setInsideArea(Long insideArea) {
        this.insideArea = insideArea;
    }

    public Long getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(Long poolArea) {
        this.poolArea = poolArea;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Integer getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Integer isVirtual) {
        this.isVirtual = isVirtual;
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

    public Integer getIsBlockUp() {
        return isBlockUp;
    }

    public void setIsBlockUp(Integer isBlockUp) {
        this.isBlockUp = isBlockUp;
    }

    public Byte getIsCurrentRecord() {
        return isCurrentRecord;
    }

    public void setIsCurrentRecord(Byte isCurrentRecord) {
        this.isCurrentRecord = isCurrentRecord;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getSalesStageName() {
        return salesStageName;
    }

    public void setSalesStageName(String salesStageName) {
        this.salesStageName = salesStageName;
    }

    public String getTakeStageName() {
        return takeStageName;
    }

    public void setTakeStageName(String takeStageName) {
        this.takeStageName = takeStageName;
    }

    public String getCheckInStageName() {
        return checkInStageName;
    }

    public void setCheckInStageName(String checkInStageName) {
        this.checkInStageName = checkInStageName;
    }

    public String getRentStageName() {
        return rentStageName;
    }

    public void setRentStageName(String rentStageName) {
        this.rentStageName = rentStageName;
    }

    public String getDecorateStageName() {
        return decorateStageName;
    }

    public void setDecorateStageName(String decorateStageName) {
        this.decorateStageName = decorateStageName;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getRentCheckinTime() {
        return rentCheckinTime;
    }

    public void setRentCheckinTime(Date rentCheckinTime) {
        this.rentCheckinTime = rentCheckinTime;
    }

    public String getHouseOperateType() {
        return houseOperateType;
    }

    public void setHouseOperateType(String houseOperateType) {
        this.houseOperateType = houseOperateType;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomPropertyId() {
        return roomPropertyId;
    }

    public void setRoomPropertyId(String roomPropertyId) {
        this.roomPropertyId = roomPropertyId;
    }

    public Long getAssistChargingArea() {
        return assistChargingArea;
    }

    public void setAssistChargingArea(Long assistChargingArea) {
        this.assistChargingArea = assistChargingArea;
    }

    public Long getGardenArea() {
        return gardenArea;
    }

    public void setGardenArea(Long gardenArea) {
        this.gardenArea = gardenArea;
    }

    public Long getBasementArea() {
        return basementArea;
    }

    public void setBasementArea(Long basementArea) {
        this.basementArea = basementArea;
    }

    public Long getGiftArea() {
        return giftArea;
    }

    public void setGiftArea(Long giftArea) {
        this.giftArea = giftArea;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public Date getMaintenanceStartDate() {
        return maintenanceStartDate;
    }

    public void setMaintenanceStartDate(Date maintenanceStartDate) {
        this.maintenanceStartDate = maintenanceStartDate;
    }

    public Date getMaintenanceEndDate() {
        return maintenanceEndDate;
    }

    public void setMaintenanceEndDate(Date maintenanceEndDate) {
        this.maintenanceEndDate = maintenanceEndDate;
    }

    public Integer getHasRelevance() {
        return hasRelevance;
    }

    public void setHasRelevance(Integer hasRelevance) {
        this.hasRelevance = hasRelevance;
    }

    public Date getBlockUpTime() {
        return blockUpTime;
    }

    public void setBlockUpTime(Date blockUpTime) {
        this.blockUpTime = blockUpTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(Long precinctId) {
        this.precinctId = precinctId;
    }

    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    public String getIsNowDetail() {
        return isNowDetail;
    }

    public void setIsNowDetail(String isNowDetail) {
        this.isNowDetail = isNowDetail;
    }

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName;
    }

    public String getLesseeCertificate() {
        return lesseeCertificate;
    }

    public void setLesseeCertificate(String lesseeCertificate) {
        this.lesseeCertificate = lesseeCertificate;
    }

    public String getLesseeMobile() {
        return lesseeMobile;
    }

    public void setLesseeMobile(String lesseeMobile) {
        this.lesseeMobile = lesseeMobile;
    }

    public String getPropertyOwnerNames() {
        return propertyOwnerNames;
    }

    public void setPropertyOwnerNames(String propertyOwnerNames) {
        this.propertyOwnerNames = propertyOwnerNames;
    }

    public Long getLesseeId() {
        return lesseeId;
    }

    public void setLesseeId(Long lesseeId) {
        this.lesseeId = lesseeId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateHouseUserId() {
        return createHouseUserId;
    }

    public void setCreateHouseUserId(Long createHouseUserId) {
        this.createHouseUserId = createHouseUserId;
    }
    
}