package com.newsee.owner.entity;

public class OwnerCustomerFamilyInfoKey {
    /**
     * 业户ID
     *
     * @mbggenerated
     */
    private Long ownerId;

    /**
     * 被关联业户ID
     *
     * @mbggenerated
     */
    private Long relationOwnerId;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getRelationOwnerId() {
        return relationOwnerId;
    }

    public void setRelationOwnerId(Long relationOwnerId) {
        this.relationOwnerId = relationOwnerId;
    }
}