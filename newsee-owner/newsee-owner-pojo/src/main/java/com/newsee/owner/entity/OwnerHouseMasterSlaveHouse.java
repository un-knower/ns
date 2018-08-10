package com.newsee.owner.entity;

import java.util.Date;

public class OwnerHouseMasterSlaveHouse {
    /**
     * 
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 主房产ID
     *
     * @mbggenerated
     */
    private Long masterHouseId;

    /**
     * 从房产ID
     *
     * @mbggenerated
     */
    private Long slaveHouseId;

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

    private String createUserName;
    
    private String updateUserName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMasterHouseId() {
        return masterHouseId;
    }

    public void setMasterHouseId(Long masterHouseId) {
        this.masterHouseId = masterHouseId;
    }

    public Long getSlaveHouseId() {
        return slaveHouseId;
    }

    public void setSlaveHouseId(Long slaveHouseId) {
        this.slaveHouseId = slaveHouseId;
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
    
}