package com.newsee.bill.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mu.jie
 * @Date 2018/7/16 14:27
 */
public class BillUseManThird {
    /** 企业id */
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;

    /** 组织id,1|number|0|0|0,1|text|0||1|1 */
    @ApiModelProperty(value = "组织id,1|number|0|0|0,1|text|0||1|1")
    private Long organizationId;

    /** 票据号 */
    @ApiModelProperty(value = "票据号")
    private String billNum;

    /** 客户ID */
    @ApiModelProperty(value = "客户ID")
    private Long customerId;
    /** 客户名称 */
    @ApiModelProperty(value = "客户名称")
    private String customerName;

    /** 项目 */
    @ApiModelProperty(value = "项目")
    private String houseList;

    /** 票据开具日期 */
    @ApiModelProperty(value = "票据开具日期")
    private Date usedDate;

    /** 票据开具人id */
    @ApiModelProperty(value = "票据开具人id")
    private Long usedUserId;

    /** 票据开具人 */
    @ApiModelProperty(value = "票据开具人")
    private String usedUserName;

    /** 票据金额 */
    @ApiModelProperty(value = "票据金额")
    private BigDecimal billFund;

    /** 创建人id */
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;

    /** 创建人姓名,1|text|0|300|2,1|text|1||2|0 */
    @ApiModelProperty(value = "创建人姓名,1|text|0|300|2,1|text|1||2|0")
    private String createUserName;

    /** 创建时间,1|text|0|300|2,1|text|1||2|0 */
    @ApiModelProperty(value = "创建时间,1|text|0|300|2,1|text|1||2|0")
    private Date createTime;

    /** 更新人id */
    @ApiModelProperty(value = "更新人id")
    private Long updateUserId;

    /** 更新人姓名,1|text|0|300|2,1|text|1||2|0 */
    @ApiModelProperty(value = "更新人姓名,1|text|0|300|2,1|text|1||2|0")
    private String updateUserName;

    /** 更新时间,1|text|0|300|2,1|text|1||2|0 */
    @ApiModelProperty(value = "更新时间,1|text|0|300|2,1|text|1||2|0")
    private Date updateTime;

    /** 票据类型 */
    @ApiModelProperty(value = "票据类型")
    private String billType;

    /** 票据本名称,1|text|0|300|2,1|text|1||2|0 */
    @ApiModelProperty(value = "票据本名称,1|text|0|300|2,1|text|1||2|0")
    private String billDetailName;

    /** 票据面额,1|text|0|300|2,1|text|1||2|0 */
    @ApiModelProperty(value = "票据面额,1|text|0|300|2,1|text|1||2|0")
    private String pageBalance;

    /** 分类目录编号,1|text|0|300|2,1|text|1||2|0 */
    @ApiModelProperty(value = "分类目录编号,1|text|0|300|2,1|text|1||2|0")
    private String catalogId;

    /** 备注说明,1|text|0|300|2,1|text|1||2|0 */
    @ApiModelProperty(value = "备注说明,1|text|0|300|2,1|text|1||2|0")
    private String remark;

    public String getPageBalance() {
        return pageBalance;
    }

    public void setPageBalance(String pageBalance) {
        this.pageBalance = pageBalance;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getBillDetailName() {
        return billDetailName;
    }

    public void setBillDetailName(String billDetailName) {
        this.billDetailName = billDetailName;
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

    public String getBillNum() {
        return billNum;
    }

    public void setBillNum(String billNum) {
        this.billNum = billNum;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getHouseList() {
        return houseList;
    }

    public void setHouseList(String houseList) {
        this.houseList = houseList;
    }

    public Date getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(Date usedDate) {
        this.usedDate = usedDate;
    }

    public Long getUsedUserId() {
        return usedUserId;
    }

    public void setUsedUserId(Long usedUserId) {
        this.usedUserId = usedUserId;
    }

    public String getUsedUserName() {
        return usedUserName;
    }

    public void setUsedUserName(String usedUserName) {
        this.usedUserName = usedUserName;
    }

    public BigDecimal getBillFund() {
        return billFund;
    }

    public void setBillFund(BigDecimal billFund) {
        this.billFund = billFund;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
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

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
