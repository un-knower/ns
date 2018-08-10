package com.newsee.owner.entity;

import java.util.Date;

public class OwnerCustomerBankAccount {
    /**
     * 
     *
     * @mbggenerated
     */
    private Long ownerBankAccountId;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long ownerId;

    /**
     * 开户银行
     *
     * @mbggenerated
     */
    private String bankName;

    /**
     * 银行地址
     *
     * @mbggenerated
     */
    private String bankAddress;

    /**
     * 是否启用
     *
     * @mbggenerated
     */
    private Byte isEnable;

    /**
     * 开户名称
     *
     * @mbggenerated
     */
    private String accountName;

    /**
     * 银行账号
     *
     * @mbggenerated
     */
    private String account;

    /**
     * 银行协议号
     *
     * @mbggenerated
     */
    private String protocolNumber;

    /**
     * 托收编号
     *
     * @mbggenerated
     */
    private String collectionNumber;

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
    /**
     * 
     *
     * @mbggenerated
     */
    private Long ownerBaseInfoOwnerId;

    public Long getOwnerBankAccountId() {
        return ownerBankAccountId;
    }

    public void setOwnerBankAccountId(Long ownerBankAccountId) {
        this.ownerBankAccountId = ownerBankAccountId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public Byte getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Byte isEnable) {
        this.isEnable = isEnable;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getCollectionNumber() {
        return collectionNumber;
    }

    public void setCollectionNumber(String collectionNumber) {
        this.collectionNumber = collectionNumber;
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

    public Long getOwnerBaseInfoOwnerId() {
        return ownerBaseInfoOwnerId;
    }

    public void setOwnerBaseInfoOwnerId(Long ownerBaseInfoOwnerId) {
        this.ownerBaseInfoOwnerId = ownerBaseInfoOwnerId;
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