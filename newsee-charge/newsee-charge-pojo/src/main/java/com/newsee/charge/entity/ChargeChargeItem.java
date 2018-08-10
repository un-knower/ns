/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 收费科目表
 * @version 1.0
 * @author
 */
public class ChargeChargeItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<ChargeChargeItem> childList;
	private String unitName;
	private Boolean isHasChild;
	/** 主键,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "主键,1|number|0|0|0,1|text|0||1|1")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 组织id */
	@ApiModelProperty(value = "组织id")
	private Long organizationId;
	
	/** 科目类型,1|select|0|200|3,1|select|0||2|0,1:住宅物业服务费|2:商铺、商场物业服务费|3:写字楼物业服务费|4:学校物业服务费|5:花园物业服务费|6:车场（库）服务费|7:生活垃圾清运服务费|8:电梯维护服务费 */
	@ApiModelProperty(value = "科目类型,1|select|0|200|3,1|select|0||2|0,1:住宅物业服务费|2:商铺、商场物业服务费|3:写字楼物业服务费|4:学校物业服务费|5:花园物业服务费|6:车场（库）服务费|7:生活垃圾清运服务费|8:电梯维护服务费")
	private Long chargeItemTypeId;
	
	/** 科目类型 */
	@ApiModelProperty(value = "科目类型")
	private String chargeItemTypeName;
	
	/** 父节点id */
	@ApiModelProperty(value = "父节点id")
	private Long parentId;
	
	/** 收费科目编码,1|text|0|200|1,1|text|1|numEn|1|0 */
	@ApiModelProperty(value = "收费科目编码,1|text|0|200|1,1|text|1|numEn|1|0")
	private String chargeItemCode;
	
	/** 名称,1|text|0|300|2,1|text|1||2|0 */
	@ApiModelProperty(value = "名称,1|text|0|300|2,1|text|1||2|0")
	private String chargeItemName;
	
	/** 简称,1|text|0|250|4,1|text|1||4|0 */
	@ApiModelProperty(value = "简称,1|text|0|250|4,1|text|1||4|0")
	private String chargeItemShortName;
	
	/** 助记码,1|text|0|200|5,1|text|0||5|0 */
	@ApiModelProperty(value = "助记码,1|text|0|200|5,1|text|0||5|0")
	private String chargeItemHelpCode;
	
	/** 费用类型,1|select|0|250|6,1|select|0||6|0,1:常规收费项目|2:抄表收费项目|3:临时收费项目|4:租赁收费项目|5:分摊收费项目|6:押金收费项目|7:出入证收费项目 */
	@ApiModelProperty(value = "费用类型,1|select|0|250|6,1|select|0||6|0,1:常规收费项目|2:抄表收费项目|3:临时收费项目|4:租赁收费项目|5:分摊收费项目|6:押金收费项目|7:出入证收费项目")
	private String chargeItemInType;
	
	/** 费用类别,1|select|0|200|7,1|select|0||7|0,1:物业费|2:多种经营收入|3:公共收益 */
	@ApiModelProperty(value = "费用类别,1|select|0|200|7,1|select|0||7|0,1:物业费|2:多种经营收入|3:公共收益")
	private String chargeItemClass;
	
	/** 税率,1|number|0|50|7,1|numbertext|0||7|0 */
	@ApiModelProperty(value = "税率,1|number|0|50|7,1|numbertext|0||7|0")
	private Double taxRate;
	
	/** 税目编码id,1|text|0|200|8,1|text|0||8|0 */
	@ApiModelProperty(value = "税目编码id,1|text|0|200|8,1|text|0||8|0")
	private Long goodsTaxId;
	
	/** 税目编码,1|select|0|200|8,1|select|0||8|0 */
	@ApiModelProperty(value= "税目编码,1|text|0|200|8,1|text|0||8|0")
	private String goodsTaxCode;
	
	/** 科目类型,1|select|0|200|3,1|select|0||2|0,1:住宅物业服务费|2:商铺、商场物业服务费|3:写字楼物业服务费|4:学校物业服务费|5:花园物业服务费|6:车场（库）服务费|7:生活垃圾清运服务费|8:电梯维护服务费 */
	@ApiModelProperty(value = "科目类型,1|select|0|200|3,1|select|0||2|0,1:住宅物业服务费|2:商铺、商场物业服务费|3:写字楼物业服务费|4:学校物业服务费|5:花园物业服务费|6:车场（库）服务费|7:生活垃圾清运服务费|8:电梯维护服务费")
	private String chargeItemType;
	
	/** 票据打印类型,1|select|0|200|9,1|radio|0||9|0,1:按计费面积|2:按数量|3:其他 */
	@ApiModelProperty(value = "票据打印类型,1|select|0|200|9,1|radio|0||9|0,1:按计费面积|2:按数量|3:其他")
	private String billPrintType;
	
	/** 财务接口编码,1|text|0|200|10,1|text|0||11|0 */
	@ApiModelProperty(value = "财务接口编码,1|text|0|200|10,1|text|0||11|0")
	private String chargeInterfaceCode;
	
	/** 财务接口名称,1|text|0|250|11,1|text|0||12|0 */
	@ApiModelProperty(value = "财务接口名称,1|text|0|250|11,1|text|0||12|0")
	private String chargeInterfaceName;
	
	/** 是否有效,1|select|0|150|12,1|radio|0||13|0,1:是|0:否 */
	@ApiModelProperty(value = "是否有效,1|select|0|150|12,1|radio|0||13|0,1:是|0:否")
	private String isWork;
	
	/** 备注,0,1|textarea|0||13|0 */
	@ApiModelProperty(value = "备注,0,1|textarea|0||13|0")
	private String remark;
	
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
	
	/** sysTime */
	@ApiModelProperty(value = "sysTime")
	private Date sysTime;
	
	private Long orderIndex;
	
	private String path;
	
	private String unit;
	
	private String highLevelSubjectName;
	
	private String subjectType;
	
	private String chargeType;
	
	private String chargeLeib;
	
	
	
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public Long getGoodsTaxId() {
		return goodsTaxId;
	}

	public void setGoodsTaxId(Long goodsTaxId) {
		this.goodsTaxId = goodsTaxId;
	}

	public String getChargeLeib() {
		return chargeLeib;
	}

	public void setChargeLeib(String chargeLeib) {
		this.chargeLeib = chargeLeib;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getHighLevelSubjectName() {
		return highLevelSubjectName;
	}

	public void setHighLevelSubjectName(String highLevelSubjectName) {
		this.highLevelSubjectName = highLevelSubjectName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Long getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Long orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getIsHasChild() {
		return isHasChild;
	}

	public void setIsHasChild(Boolean isHasChild) {
		this.isHasChild = isHasChild;
	}

	public List<ChargeChargeItem> getChildList() {
		return childList;
	}

	public void setChildList(List<ChargeChargeItem> childList) {
		this.childList = childList;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
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
	
	public void setChargeItemTypeId(Long chargeItemTypeId) {
		this.chargeItemTypeId = chargeItemTypeId;
	}
	
	public Long getChargeItemTypeId() {
		return chargeItemTypeId;
	}
	
	public void setChargeItemTypeName(String chargeItemTypeName) {
		this.chargeItemTypeName = chargeItemTypeName;
	}
	
	public String getChargeItemTypeName() {
		return chargeItemTypeName;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public Long getParentId() {
		return parentId;
	}
	
	public void setChargeItemCode(String chargeItemCode) {
		this.chargeItemCode = chargeItemCode;
	}
	
	public String getChargeItemCode() {
		return chargeItemCode;
	}
	
	public void setChargeItemName(String chargeItemName) {
		this.chargeItemName = chargeItemName;
	}
	
	public String getChargeItemName() {
		return chargeItemName;
	}
	
	public void setChargeItemShortName(String chargeItemShortName) {
		this.chargeItemShortName = chargeItemShortName;
	}
	
	public String getChargeItemShortName() {
		return chargeItemShortName;
	}
	
	public void setChargeItemHelpCode(String chargeItemHelpCode) {
		this.chargeItemHelpCode = chargeItemHelpCode;
	}
	
	public String getChargeItemHelpCode() {
		return chargeItemHelpCode;
	}
	
	public void setChargeItemInType(String chargeItemInType) {
		this.chargeItemInType = chargeItemInType;
	}
	
	public String getChargeItemInType() {
		return chargeItemInType;
	}
	
	public void setChargeItemClass(String chargeItemClass) {
		this.chargeItemClass = chargeItemClass;
	}
	
	public String getChargeItemClass() {
		chargeItemClass = (chargeItemClass == null || "".equals(chargeItemClass)  ?"0" : chargeItemClass);
		return chargeItemClass;
	}
	
	
	
	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public String getGoodsTaxCode() {
		return goodsTaxCode;
	}

	public void setGoodsTaxCode(String goodsTaxCode) {
		this.goodsTaxCode = goodsTaxCode;
	}

	public void setBillPrintType(String billPrintType) {
		this.billPrintType = billPrintType;
	}
	
	public String getBillPrintType() {
		return billPrintType;
	}
	
	public void setChargeInterfaceCode(String chargeInterfaceCode) {
		this.chargeInterfaceCode = chargeInterfaceCode;
	}
	
	public String getChargeInterfaceCode() {
		return chargeInterfaceCode;
	}
	
	public void setChargeInterfaceName(String chargeInterfaceName) {
		this.chargeInterfaceName = chargeInterfaceName;
	}
	
	public String getChargeInterfaceName() {
		return chargeInterfaceName;
	}
	
	public void setIsWork(String isWork) {
		this.isWork = isWork;
	}
	
	public String getIsWork() {
		return isWork;
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
	
	public String getChargeItemType() {
		chargeItemType = (chargeItemType == null || "".equals(chargeItemType)  ?"0" : chargeItemType);
		return chargeItemType;
	}

	public void setChargeItemType(String chargeItemType) {
		this.chargeItemType = chargeItemType;
	}

}
