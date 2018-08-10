package com.newsee.owner.vo;

import java.io.Serializable;
import java.util.Date;

public class OwnerHouseRelationshipVo implements Serializable{

    private static final long serialVersionUID = -1829944369302846359L;

    /**
     * 
     */
    private Long houseOwnerRelationshipId;

    /**
     * 项目ID
     */
    private Long precinctId;

    private String precinctName;
    /**
     * 房产ID
     */
    private Long houseId;

    private String houseName;
    
    private String houseFullName;
    /**
     * 业主ID
     */
    private Long ownerId;

    private String ownerName;
    /**
     * 租户ID
     */
    private Long rentOwnerId;

    private String rentOwnerName;
    /**
     * 收费对象ID
     */
    private Long chargeOwnerId;

    private String chargeOwnerName;
    /**
     * 相关联操作ID
     */
    private Long detailId;
    
    private String ownerProperty;
    
    private String ownerPropertyName;
    
    /**
     * 是否为主房产
     */    
    private Byte isMainHouse;
    
    private Byte isDeleted;
    /**
     * 申请日期 yyyy-MM-dd
     *
     * @mbggenerated
     */
    private Date applyDate;

    /**
     * 开始时间
     *
     * @mbggenerated
     */
    private Date startDate;

    /**
     * 结束时间
     *
     * @mbggenerated
     */
    private Date endDate;
    
    private Byte ownerCategory;
    
    private Byte isCurrentRecord;
    
    private Long handlerId;

    private String carNumbers;
    
    public Long getHouseOwnerRelationshipId() {
        return houseOwnerRelationshipId;
    }

    public void setHouseOwnerRelationshipId(Long houseOwnerRelationshipId) {
        this.houseOwnerRelationshipId = houseOwnerRelationshipId;
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

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getRentOwnerId() {
        return rentOwnerId;
    }

    public void setRentOwnerId(Long rentOwnerId) {
        this.rentOwnerId = rentOwnerId;
    }

    public Long getChargeOwnerId() {
        return chargeOwnerId;
    }

    public void setChargeOwnerId(Long chargeOwnerId) {
        this.chargeOwnerId = chargeOwnerId;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getRentOwnerName() {
        return rentOwnerName;
    }

    public void setRentOwnerName(String rentOwnerName) {
        this.rentOwnerName = rentOwnerName;
    }

    public String getChargeOwnerName() {
        return chargeOwnerName;
    }

    public void setChargeOwnerName(String chargeOwnerName) {
        this.chargeOwnerName = chargeOwnerName;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Byte getIsMainHouse() {
        return isMainHouse;
    }

    public void setIsMainHouse(Byte isMainHouse) {
        this.isMainHouse = isMainHouse;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Byte getOwnerCategory() {
        return ownerCategory;
    }

    public void setOwnerCategory(Byte ownerCategory) {
        this.ownerCategory = ownerCategory;
    }

    public Byte getIsCurrentRecord() {
        return isCurrentRecord;
    }

    public void setIsCurrentRecord(Byte isCurrentRecord) {
        this.isCurrentRecord = isCurrentRecord;
    }

    public String getOwnerProperty() {
        return ownerProperty;
    }

    public void setOwnerProperty(String ownerProperty) {
        this.ownerProperty = ownerProperty;
    }

    public String getOwnerPropertyName() {
        return ownerPropertyName;
    }

    public void setOwnerPropertyName(String ownerPropertyName) {
        this.ownerPropertyName = ownerPropertyName;
    }

    public String getCarNumbers() {
        return carNumbers;
    }

    public void setCarNumbers(String carNumbers) {
        this.carNumbers = carNumbers;
    }
    
}
