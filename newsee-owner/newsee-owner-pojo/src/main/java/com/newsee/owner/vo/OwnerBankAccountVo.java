package com.newsee.owner.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class OwnerBankAccountVo implements Serializable{

    private static final long serialVersionUID = -5152690479449738925L;

    @ApiModelProperty("")
    private Long ownerBankAccountId;
    
    @ApiModelProperty("客户ID")
    private Long ownerId;

    @ApiModelProperty("开户银行")
    private String bankName;

    @ApiModelProperty("银行地址")
    private String bankAddress;

    @ApiModelProperty("是否启用")
    private Byte isEnable;

    @ApiModelProperty("开户名称")
    private String accountName;

    @ApiModelProperty("银行账号")
    private String account;

    @ApiModelProperty("银行协议号")
    private String protocolNumber;

    @ApiModelProperty("托收编号")
    private String collectionNumber;

    @ApiModelProperty("备注")
    private String remark;
    
    @ApiModelProperty("操作人")
    private Long handlerId;

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

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }
    
}
