package com.newsee.owner.entity;

import java.util.Date;

public class OwnerHouseClusterInfo {
    /**
     * 
     *
     * @mbggenerated
     */
    private Long houseId;

    /**
     * 组团编号
     *
     * @mbggenerated
     */
    private String clusterNo;

    /**
     * 组团简称
     *
     * @mbggenerated
     */
    private String clusterShortName;

    /**
     * 开发商
     */
    private Long developer;

    /**
     * 承建商
     */
    private String builder;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

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

    private String createUserName;
    
    private String updateUserName;
    
    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getClusterNo() {
        return clusterNo;
    }

    public void setClusterNo(String clusterNo) {
        this.clusterNo = clusterNo;
    }

    public String getClusterShortName() {
        return clusterShortName;
    }

    public void setClusterShortName(String clusterShortName) {
        this.clusterShortName = clusterShortName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getDeveloper() {
        return developer;
    }

    public void setDeveloper(Long developer) {
        this.developer = developer;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
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