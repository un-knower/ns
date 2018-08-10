package com.newsee.owner.entity;

import java.util.Date;

import com.newsee.common.entity.BaseEntity;

public class OwnerHouseStageDetail extends BaseEntity{
    private Long detailId;

    private Long houseId;

    private Long oldOwnerId;

    private String houseOperateType;

    private Byte isSublet;
    
    private String isNowDetail;
    
    private Date handleTime;

    private Long previousDetailId;

    private String houseStage;
    
    private String rentStage;
    
    private String decorateStage;
    
    private String remark;

    private Byte isDeleted;

    private Long createUserId;

    private Date createTime;

    private Long updateUserId;

    private Date updateTime;

    private Date sysTime;

    private String createUserName;
    
    private String updateUserName;
    
    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getOldOwnerId() {
        return oldOwnerId;
    }

    public void setOldOwnerId(Long oldOwnerId) {
        this.oldOwnerId = oldOwnerId;
    }

    public String getHouseOperateType() {
        return houseOperateType;
    }

    public void setHouseOperateType(String houseOperateType) {
        this.houseOperateType = houseOperateType;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Long getPreviousDetailId() {
        return previousDetailId;
    }

    public void setPreviousDetailId(Long previousDetailId) {
        this.previousDetailId = previousDetailId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Byte getIsSublet() {
        return isSublet;
    }

    public void setIsSublet(Byte isSublet) {
        this.isSublet = isSublet;
    }

    public String getHouseStage() {
        return houseStage;
    }

    public void setHouseStage(String houseStage) {
        this.houseStage = houseStage;
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

    public String getIsNowDetail() {
        return isNowDetail;
    }

    public void setIsNowDetail(String isNowDetail) {
        this.isNowDetail = isNowDetail;
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