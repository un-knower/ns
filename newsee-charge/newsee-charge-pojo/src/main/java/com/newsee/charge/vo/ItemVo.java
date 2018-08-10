/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.newsee.charge.entity.ChargeChargeItemGoodsTax;

import io.swagger.annotations.ApiModelProperty;

/**
 * ItemVo
 *
 * @author
 * @version 1.0
 */
public class ItemVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;

    private String unitName;

    @ApiModelProperty(value = "组织id")
    private Long organizationId;

    /**
     * 财务接口名称,1|text|0|250|11,1|text|0||12|0
     */
    @ApiModelProperty(value = "财务接口名称,1|text|0|250|11,1|text|0||12|0")
    private String chargeInterfaceName;

    /**
     * 收费科目编码,1|text|0|200|1,1|text|1|numEn|1|0
     */
    @ApiModelProperty(value = "收费科目编码,1|text|0|200|1,1|text|1|numEn|1|0")
    private String chargeItemCode;

    /**
     * 助记码,1|text|0|200|5,1|text|0||5|0
     */
    @ApiModelProperty(value = "助记码,1|text|0|200|5,1|text|0||5|0")
    private String chargeItemHelpCode;

    /**
     * 备注,0,1|textarea|0||13|0
     */
    @ApiModelProperty(value = "备注,0,1|textarea|0||13|0")
    private String remark;

    /**
     * 科目类型,1|select|0|200|3,1|select|0||2|0,1:住宅物业服务费|2:商铺、商场物业服务费|3:写字楼物业服务费|4:学校物业服务费|5:花园物业服务费|6:车场（库）服务费|7:生活垃圾清运服务费|8:电梯维护服务费
     */
    @ApiModelProperty(value = "上级科目")
    private Long chargeItemTypeId;

    /**
     * 科目类型,1|select|0|200|3,1|select|0||2|0,1:住宅物业服务费|2:商铺、商场物业服务费|3:写字楼物业服务费|4:学校物业服务费|5:花园物业服务费|6:车场（库）服务费|7:生活垃圾清运服务费|8:电梯维护服务费
     */
    @ApiModelProperty(value = "科目类型,1|select|0|200|3,1|select|0||2|0,1:住宅物业服务费|2:商铺、商场物业服务费|3:写字楼物业服务费|4:学校物业服务费|5:花园物业服务费|6:车场（库）服务费|7:生活垃圾清运服务费|8:电梯维护服务费")
    private String chargeItemType;

    /**
     * 票据打印类型,1|select|0|200|9,1|radio|0||9|0,1:按计费面积|2:按数量|3:其他
     */
    @ApiModelProperty(value = "票据打印类型,1|select|0|200|9,1|radio|0||9|0,1:按计费面积|2:按数量|3:其他")
    private String billPrintType;

    /**
     * 名称,1|text|0|300|2,1|text|1||2|0
     */
    @ApiModelProperty(value = "名称,1|text|0|300|2,1|text|1||2|0")
    private String chargeItemName;

    /**
     * 是否有效,1|select|0|150|12,1|radio|0||13|0,1:是|0:否
     */
    @ApiModelProperty(value = "是否有效,1|select|0|150|12,1|radio|0||13|0,1:是|0:否")
    private String isWork;

    /**
     * 费用类别,1|select|0|200|7,1|select|0||7|0,1:物业费|2:多种经营收入|3:公共收益
     */
    @ApiModelProperty(value = "费用类别,1|select|0|200|7,1|select|0||7|0,1:物业费|2:多种经营收入|3:公共收益")
    private String chargeItemClass;

    /**
     * 税率,1|number|0|50|7,1|numbertext|0||7|0
     */
    @ApiModelProperty(value = "税率,1|number|0|50|7,1|numbertext|0||7|0")
    private Double taxRate;

    /**
     * 费用类型,1|select|0|250|6,1|select|0||6|0,1:常规收费项目|2:抄表收费项目|3:临时收费项目|4:租赁收费项目|5:分摊收费项目|6:押金收费项目|7:出入证收费项目
     */
    @ApiModelProperty(value = "费用类型,1|select|0|250|6,1|select|0||6|0,1:常规收费项目|2:抄表收费项目|3:临时收费项目|4:租赁收费项目|5:分摊收费项目|6:押金收费项目|7:出入证收费项目")
    private String chargeItemInType;

    /**
     * 主键,1|number|0|0|0,1|text|0||1|1
     */
    @ApiModelProperty(value = "主键,1|number|0|0|0,1|text|0||1|1")
    private Long id;

    /**
     * 财务接口编码,1|text|0|200|10,1|text|0||11|0
     */
    @ApiModelProperty(value = "财务接口编码,1|text|0|200|10,1|text|0||11|0")
    private String chargeInterfaceCode;

    /**
     * 简称,1|text|0|250|4,1|text|1||4|0
     */
    @ApiModelProperty(value = "简称,1|text|0|250|4,1|text|1||4|0")
    private String chargeItemShortName;

    @ApiModelProperty(value = "创建人id")
    private Long createUserId;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新人id")
    private Long updateUserId;

    @ApiModelProperty(value = "更新人姓名")
    private String updateUserName;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "收费科目税目编码list")
    private List<ChargeChargeItemGoodsTax> taxList;

    @ApiModelProperty(value = "上级科目Id")
    private Long parentId;

    /**
     * 税目编码id,1|text|0|200|8,1|text|0||8|0
     */
    @ApiModelProperty(value = "税目编码id,1|text|0|200|8,1|text|0||8|0")
    private Long goodsTaxId;

    /**
     * 税目编码,1|select|0|200|8,1|select|0||8|0
     */
    @ApiModelProperty(value = "税目编码,1|text|0|200|8,1|text|0||8|0")
    private String goodsTaxCode;

    /**
     * 税目编码名称
     */
    private String goodsTaxName;

    private Long orderIndex;

    private String path;

    private String unit;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Long orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public void setChargeInterfaceName(String chargeInterfaceName) {
        this.chargeInterfaceName = chargeInterfaceName;
    }

    public String getChargeInterfaceName() {
        return chargeInterfaceName;
    }

    public void setChargeItemCode(String chargeItemCode) {
        this.chargeItemCode = chargeItemCode;
    }

    public String getChargeItemCode() {
        return chargeItemCode;
    }

    public void setChargeItemHelpCode(String chargeItemHelpCode) {
        this.chargeItemHelpCode = chargeItemHelpCode;
    }

    public String getChargeItemHelpCode() {
        return chargeItemHelpCode;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setChargeItemTypeId(Long chargeItemTypeId) {
        this.chargeItemTypeId = chargeItemTypeId;
    }

    public Long getChargeItemTypeId() {
        return chargeItemTypeId;
    }

    public void setBillPrintType(String billPrintType) {
        this.billPrintType = billPrintType;
    }

    public String getBillPrintType() {
        return billPrintType;
    }

    public void setChargeItemName(String chargeItemName) {
        this.chargeItemName = chargeItemName;
    }

    public String getChargeItemName() {
        return chargeItemName;
    }

    public void setIsWork(String isWork) {
        this.isWork = isWork;
    }

    public String getIsWork() {
        return isWork;
    }

    public void setChargeItemClass(String chargeItemClass) {
        this.chargeItemClass = chargeItemClass;
    }

    public String getChargeItemClass() {
        return chargeItemClass;
    }


    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public void setChargeItemInType(String chargeItemInType) {
        this.chargeItemInType = chargeItemInType;
    }

    public String getChargeItemInType() {
        return chargeItemInType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setChargeInterfaceCode(String chargeInterfaceCode) {
        this.chargeInterfaceCode = chargeInterfaceCode;
    }

    public String getChargeInterfaceCode() {
        return chargeInterfaceCode;
    }

    public void setChargeItemShortName(String chargeItemShortName) {
        this.chargeItemShortName = chargeItemShortName;
    }

    public String getChargeItemShortName() {
        return chargeItemShortName;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
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

    public String getChargeItemType() {
        return chargeItemType;
    }

    public void setChargeItemType(String chargeItemType) {
        this.chargeItemType = chargeItemType;
    }

    public List<ChargeChargeItemGoodsTax> getTaxList() {
        return taxList;
    }

    public void setTaxList(List<ChargeChargeItemGoodsTax> taxList) {
        this.taxList = taxList;
    }

    public Long getGoodsTaxId() {
        return goodsTaxId;
    }

    public void setGoodsTaxId(Long goodsTaxId) {
        this.goodsTaxId = goodsTaxId;
    }

    public String getGoodsTaxCode() {
        return goodsTaxCode;
    }

    public void setGoodsTaxCode(String goodsTaxCode) {
        this.goodsTaxCode = goodsTaxCode;
    }

    public String getGoodsTaxName() {
        return goodsTaxName == null ? "" : goodsTaxName;
    }

    public void setGoodsTaxName(String goodsTaxName) {
        this.goodsTaxName = goodsTaxName;
    }
}
