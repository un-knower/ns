package com.newsee.charge.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 应收款变更日志表
 * @author mu.jie
 * @Date 2018/6/12 10:07
 */
public class ChargeCustomerChargeDetailLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键,1|number|0|0|0 */
    @ApiModelProperty(value = "主键,1|number|0|0|0")
    private Long id;

    /** 企业id */
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;

    /** 组织id */
    @ApiModelProperty(value = "组织id")
    private Long organizationId;

    /** 应收款费用id */
    @ApiModelProperty(value = "应收款费用id")
    private Long chargeDetailId;

    /** 项目id */
    @ApiModelProperty(value = "项目id")
    private Long preinctId;

    /** 项目,1|text|0|200|1 */
    @ApiModelProperty(value = "项目,1|text|0|200|1")
    private String preinctName;

    /** 组团id */
    @ApiModelProperty(value = "组团id")
    private Long groupId;

    /** 组团名称，没有组团就没有 */
    @ApiModelProperty(value = "组团名称")
    private String groupName;

    /** 收费对象id */
    @ApiModelProperty(value = "收费对象id")
    private Long ownerId;

    /** 收费对象,1|text|0|150|3,1|label|0||2|0 */
    @ApiModelProperty(value = "收费对象,1|text|0|150|3,1|label|0||2|0")
    private String ownerName;

    /** 缴费对象类型 */
    @ApiModelProperty(value = "缴费对象类型")
    private Integer paidOwnerType;

    /** 面积/用量,1|number|0|150|7,1|label|0||9|0 */
    @ApiModelProperty(value = "面积/用量,1|number|0|150|7,1|label|0||9|0")
    private Double amount;

    /** 收费标准id */
    @ApiModelProperty(value = "收费标准id")
    private Long chargeId;

    /** 收费标准,1|text|0|150|5,1|label|0||6|0 */
    @ApiModelProperty(value = "收费标准,1|text|0|150|5,1|label|0||6|0")
    private String chargeName;

    /** 应收日期,1|date|0|150|9 */
    @ApiModelProperty(value = "应收日期,1|date|0|150|9")
    private Date shouldChargeDate;

    /** 房间id */
    @ApiModelProperty(value = "房间id")
    private Long houseId;

    /** 房产简称,1|text|0|200|2,1|label|0||1|1 */
    @ApiModelProperty(value = "房产简称,1|text|0|200|2,1|label|0||1|1")
    private String houseName;

    /** 收费科目id */
    @ApiModelProperty(value = "收费科目id")
    private Long chargeItemId;

    /** 收费科目,1|text|0|200|2,1|label|0||6|0 */
    @ApiModelProperty(value = "收费科目,1|text|0|200|2,1|label|0||6|0")
    private String chargeItemName;

    /** 计费周期开始,1|date|0|150|8,1|label|0||4|0 */
    @ApiModelProperty(value = "计费周期开始,1|date|0|150|8,1|label|0||4|0")
    private Date oldCalcStartDate;

    /** 计费周期结束,1|date|0|150|9,1|label|0||5|0 */
    @ApiModelProperty(value = "计费周期结束,1|date|0|150|9,1|label|0||5|0")
    private Date oldCalcEndDate;

    /** 计费周期开始,1|date|0|150|8,1|label|0||4|0 */
    @ApiModelProperty(value = "计费周期开始,1|date|0|150|8,1|label|0||4|0")
    private Date calcStartDate;

    /** 计费周期结束,1|date|0|150|9,1|label|0||5|0 */
    @ApiModelProperty(value = "计费周期结束,1|date|0|150|9,1|label|0||5|0")
    private Date calcEndDate;

    /** 调整前-应收金额,1|number|1|150|13,1|label|0||10|0 */
    @ApiModelProperty(value = "调整前-应收金额,1|number|1|150|13,1|label|0||10|0")
    private Double beforeActualChargeSum;

    /** 调整-应收金额,1|number|1|150|13,1|label|0||10|0 */
    @ApiModelProperty(value = "调整-应收金额,1|number|1|150|13,1|label|0||10|0")
    private Double changeChargeSum;

    /** 调整后-应收金额,1|number|1|150|13,1|label|0||10|0 */
    @ApiModelProperty(value = "调整后-应收金额,1|number|1|150|13,1|label|0||10|0")
    private Double afterActualChargeSum;

    /** 减免前-应收金额,1|number|1|150|13,1|label|0||10|0 */
    @ApiModelProperty(value = "减免前-应收金额,1|number|1|150|13,1|label|0||10|0")
    private Double beforeDiscountSum;

    /** 减免-应收金额,1|number|1|150|13,1|label|0||10|0 */
    @ApiModelProperty(value = "减免-应收金额,1|number|1|150|13,1|label|0||10|0")
    private Double discountSum;

    /** 减免后-应收金额,1|number|1|150|13,1|label|0||10|0 */
    @ApiModelProperty(value = "减免后-应收金额,1|number|1|150|13,1|label|0||10|0")
    private Double afterDiscountSum;

    /** 审核状态,1|select|0|150|15,1|label|0||16|0,1:未审核|2:审核通过|3:审核不通过 */
    @ApiModelProperty(value = "审核状态,1|select|0|150|15,1|label|0||16|0,1:未审核|2:审核通过|3:审核不通过")
    private String isCheck;

    /** 变更明细 */
    @ApiModelProperty(value = "变更明细")
    private String description;

    /** 创建人id */
    @ApiModelProperty(value = "创建人id")
    private Long createUserId;

    /** 创建人姓名 */
    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /** 更新人id */
    @ApiModelProperty(value = "更新人id")
    private Long updateUserId;

    /** 更新人姓名 */
    @ApiModelProperty(value = "更新人姓名")
    private String updateUserName;

    /** 更新时间 */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /** 插入或者更新时间 */
    @ApiModelProperty(value = "插入或者更新时间")
    private Date sysTime;

    /** 是否删除,0:未删除，1:已删除 */
    @ApiModelProperty(value = "是否删除,0:未删除，1:已删除")
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getChargeDetailId() {
        return chargeDetailId;
    }

    public void setChargeDetailId(Long chargeDetailId) {
        this.chargeDetailId = chargeDetailId;
    }

    public Long getPreinctId() {
        return preinctId;
    }

    public void setPreinctId(Long preinctId) {
        this.preinctId = preinctId;
    }

    public String getPreinctName() {
        return preinctName;
    }

    public void setPreinctName(String preinctName) {
        this.preinctName = preinctName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Long getChargeItemId() {
        return chargeItemId;
    }

    public void setChargeItemId(Long chargeItemId) {
        this.chargeItemId = chargeItemId;
    }

    public String getChargeItemName() {
        return chargeItemName;
    }

    public void setChargeItemName(String chargeItemName) {
        this.chargeItemName = chargeItemName;
    }

    public Date getCalcStartDate() {
        return calcStartDate;
    }

    public void setCalcStartDate(Date calcStartDate) {
        this.calcStartDate = calcStartDate;
    }

    public Date getCalcEndDate() {
        return calcEndDate;
    }

    public void setCalcEndDate(Date calcEndDate) {
        this.calcEndDate = calcEndDate;
    }

    public Double getBeforeActualChargeSum() {
        return beforeActualChargeSum;
    }

    public void setBeforeActualChargeSum(Double beforeActualChargeSum) {
        this.beforeActualChargeSum = beforeActualChargeSum;
    }

    public Double getChangeChargeSum() {
        return changeChargeSum;
    }

    public void setChangeChargeSum(Double changeChargeSum) {
        this.changeChargeSum = changeChargeSum;
    }

    public Double getAfterActualChargeSum() {
        return afterActualChargeSum;
    }

    public void setAfterActualChargeSum(Double afterActualChargeSum) {
        this.afterActualChargeSum = afterActualChargeSum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBeforeDiscountSum() {
        return beforeDiscountSum;
    }

    public void setBeforeDiscountSum(Double beforeDiscountSum) {
        this.beforeDiscountSum = beforeDiscountSum;
    }

    public Double getDiscountSum() {
        return discountSum;
    }

    public void setDiscountSum(Double discountSum) {
        this.discountSum = discountSum;
    }

    public Double getAfterDiscountSum() {
        return afterDiscountSum;
    }

    public void setAfterDiscountSum(Double afterDiscountSum) {
        this.afterDiscountSum = afterDiscountSum;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
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

    public Date getSysTime() {
        return sysTime;
    }

    public void setSysTime(Date sysTime) {
        this.sysTime = sysTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getPaidOwnerType() {
        return paidOwnerType;
    }

    public void setPaidOwnerType(Integer paidOwnerType) {
        this.paidOwnerType = paidOwnerType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public Date getShouldChargeDate() {
        return shouldChargeDate;
    }

    public void setShouldChargeDate(Date shouldChargeDate) {
        this.shouldChargeDate = shouldChargeDate;
    }

    public Date getOldCalcStartDate() {
        return oldCalcStartDate;
    }

    public void setOldCalcStartDate(Date oldCalcStartDate) {
        this.oldCalcStartDate = oldCalcStartDate;
    }

    public Date getOldCalcEndDate() {
        return oldCalcEndDate;
    }

    public void setOldCalcEndDate(Date oldCalcEndDate) {
        this.oldCalcEndDate = oldCalcEndDate;
    }
}
