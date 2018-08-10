package com.newsee.owner.entity;

import java.util.Date;

public class OwnerHouseEntranceGuardCard {
    /**
     * 
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long houseId;

    /**
     * 属主业户ID
     *
     * @mbggenerated
     */
    private Long ownerId;

    /**
     * 门禁卡号
     *
     * @mbggenerated
     */
    private String cardNo;

    /**
     * 门禁卡状态 0启用 
     *
     * @mbggenerated
     */
    private Byte cardStatus;

    /**
     * 
     *
     * @mbggenerated
     */
    private Byte isDeleted;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * 	
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long updateUserId;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date sysTime;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long houseBaseInfoHouseId;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long ownerBaseInfoOwnerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Byte getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Byte cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
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

    public Date getSysTime() {
        return sysTime;
    }

    public void setSysTime(Date sysTime) {
        this.sysTime = sysTime;
    }

    public Long getHouseBaseInfoHouseId() {
        return houseBaseInfoHouseId;
    }

    public void setHouseBaseInfoHouseId(Long houseBaseInfoHouseId) {
        this.houseBaseInfoHouseId = houseBaseInfoHouseId;
    }

    public Long getOwnerBaseInfoOwnerId() {
        return ownerBaseInfoOwnerId;
    }

    public void setOwnerBaseInfoOwnerId(Long ownerBaseInfoOwnerId) {
        this.ownerBaseInfoOwnerId = ownerBaseInfoOwnerId;
    }
}