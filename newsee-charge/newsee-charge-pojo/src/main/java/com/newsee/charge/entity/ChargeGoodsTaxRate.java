/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 税率设置表
 *
 * @author
 * @version 1.0
 */
public class ChargeGoodsTaxRate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键,1|number|0|0|1
     */
    @ApiModelProperty(value = "主键,1|number|0|0|1")
    private Long id;

    /**
     * 税目id
     */
    @ApiModelProperty(value = "税目id")
    private Long goodsTaxId;

    /**
     * 税目编码,1|text|0|200|4
     */
    @ApiModelProperty(value = "税目编码,1|text|0|200|4")
    private String goodsTaxCode;

    @ApiModelProperty(value = "税目名称,1|text|0|200|5")
    private String goodsTaxName;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;

    /**
     * 组织id
     */
    @ApiModelProperty(value = "组织id")
    private Long organizationId;

    /**
     * 项目id
     */
    @ApiModelProperty(value = "项目id")
    private Long precinctId;

    /**
     * 项目名称,1|text|0|200|1
     */
    @ApiModelProperty(value = "项目名称,1|text|0|200|1")
    private String precinctName;

    /**
     * 收费科目id
     */
    @ApiModelProperty(value = "收费科目id")
    private Long chargeItemId;

    /**
     * 收费科目,1|text|0|200|2
     */
    @ApiModelProperty(value = "收费科目,1|text|0|200|2")
    private String chargeItemName;

    /**
     * 税率,1|number|0|130|3
     */
    @ApiModelProperty(value = "税率,1|number|0|130|3")
    private Double taxRate;

    /**
     * 备注,1|text|0|250|5
     */
    @ApiModelProperty(value = "备注,1|text|0|250|5")
    private String remark;

    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;

    /**
     * 创建人,1|text|0|150|6
     */
    @ApiModelProperty(value = "创建人,1|text|0|150|6")
    private String createUserName;

    /**
     * 创建时间,1|date|0|200|6
     */
    @ApiModelProperty(value = "创建时间,1|date|0|200|6")
    private Date createTime;

    /**
     * 更新人id
     */
    @ApiModelProperty(value = "更新人id")
    private Long updateUserId;

    /**
     * 修改人,1|text|0|150|7
     */
    @ApiModelProperty(value = "修改人,1|text|0|150|7")
    private String updateUserName;

    /**
     * 修改时间,1|date|0|200|9
     */
    @ApiModelProperty(value = "修改时间,1|date|0|200|9")
    private Date updateTime;

    /**
     * 插入更新时间
     */
    @ApiModelProperty(value = "插入更新时间")
    private Date sysTime;

    @ApiModelProperty(value = "逻辑删除，0:未删除 1:删除")
    private Integer isDelete;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setGoodsTaxId(Long goodsTaxId) {
        this.goodsTaxId = goodsTaxId;
    }

    public Long getGoodsTaxId() {
        return goodsTaxId;
    }

    public void setGoodsTaxCode(String goodsTaxCode) {
        this.goodsTaxCode = goodsTaxCode;
    }

    public String getGoodsTaxCode() {
        return goodsTaxCode;
    }

    public String getGoodsTaxName() {
        return goodsTaxName;
    }

    public void setGoodsTaxName(String goodsTaxName) {
        this.goodsTaxName = goodsTaxName;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setPrecinctId(Long precinctId) {
        this.precinctId = precinctId;
    }

    public Long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    public String getPrecinctName() {
        return precinctName;
    }

    public void setChargeItemId(Long chargeItemId) {
        this.chargeItemId = chargeItemId;
    }

    public Long getChargeItemId() {
        return chargeItemId;
    }

    public void setChargeItemName(String chargeItemName) {
        this.chargeItemName = chargeItemName;
    }

    public String getChargeItemName() {
        return chargeItemName;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setSysTime(Date sysTime) {
        this.sysTime = sysTime;
    }

    public Date getSysTime() {
        return sysTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
