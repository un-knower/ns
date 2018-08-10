package com.newsee.owner.entity;

import java.util.Date;

public class OwnerCustomerFamilyInfo extends OwnerCustomerFamilyInfoKey {
    
    private Long familyId;

    /**
     * 与被关联客户关系 1夫妻 2父母 3子女 4兄弟 5兄妹 6姐弟 7姐妹 8祖父母 9孙子女 10董事长 11总经理 12财务 13人事 14行政 15子公司 16其他"
     *
     * @mbggenerated
     */
    private String ownerRelationship;

    /**
     * 被关联客户身份 1丈夫 2妻子 3父亲 4母亲 5儿子 6女儿 7姐妹 8兄弟 9兄妹 10姐弟 11祖父 12祖母 13孙子 14孙女 15董事长 16总经理 17财务 18人事 19行政 20子公司 21其他
     *
     * @mbggenerated
     */
    private String relationOwnerCall;

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
    private Long ownerBaseInfoOwnerId;

    private String createUserName;
    
    private String updateUserName;
    
    public String getOwnerRelationship() {
        return ownerRelationship;
    }

    public void setOwnerRelationship(String ownerRelationship) {
        this.ownerRelationship = ownerRelationship;
    }

    public String getRelationOwnerCall() {
        return relationOwnerCall;
    }

    public void setRelationOwnerCall(String relationOwnerCall) {
        this.relationOwnerCall = relationOwnerCall;
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

    public Long getOwnerBaseInfoOwnerId() {
        return ownerBaseInfoOwnerId;
    }

    public void setOwnerBaseInfoOwnerId(Long ownerBaseInfoOwnerId) {
        this.ownerBaseInfoOwnerId = ownerBaseInfoOwnerId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
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