package com.newsee.owner.entity;

import java.util.Date;

import com.newsee.common.entity.BaseEntity;

public class OwnerCustomerBaseInfo extends BaseEntity{
    /**
     * 客户编号
     *
     * @mbggenerated
     */
    private Long ownerId;

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
     * 所属项目ID，即house_id
     *
     * @mbggenerated
     */
    private String precinctId;

    private String precinctName;

    /**
     * 联系电话
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * 客户名称
     *
     * @mbggenerated
     */
    private String ownerName;

    /**
     * 客户类型：0个人 1企业
     *
     * @mbggenerated
     */
    private String ownerType;

    /**
     * 客户性质：0业主 1租户 2住户 3开发商
     *
     * @mbggenerated
     */
    private String ownerProperty;

    /**
     * 客户等级：0VIP 1A 2B 3C
     *
     * @mbggenerated
     */
    private String ownerLevel;

    /**
     * 证件类型 10身份证  11护照                20营业执照
     *
     * @mbggenerated
     */
    private String certificateType;

    /**
     * 
     *
     * @mbggenerated
     */
    private String certificate;

    /**
     * 是否删除 0否 1是
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

    private String createUserName;
    
    private String updateUserName;
    
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

    public String getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(String precinctId) {
        this.precinctId = precinctId;
    }

    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerProperty() {
        return ownerProperty;
    }

    public void setOwnerProperty(String ownerProperty) {
        this.ownerProperty = ownerProperty;
    }

    public String getOwnerLevel() {
        return ownerLevel;
    }

    public void setOwnerLevel(String ownerLevel) {
        this.ownerLevel = ownerLevel;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
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