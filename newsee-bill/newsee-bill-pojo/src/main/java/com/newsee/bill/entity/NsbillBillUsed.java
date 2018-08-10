/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 票据使用详细信息表
 * @version 1.0
 * @author
 */
public class NsbillBillUsed implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 自增id */
	@ApiModelProperty(value = "自增id")
	private Long id;

	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;

	/** 组织id,1|number|0|0|0,1|text|0||1|1 */
	@ApiModelProperty(value = "组织id,1|number|0|0|0,1|text|0||1|1")
	private Long organizationId;
	
	/** 票据本编号Id */
	@ApiModelProperty(value = "票据本编号Id")
	private Long billDetailId;
	
	/** 票据号 */
	@ApiModelProperty(value = "票据号")
	private String billNum;
	
	/** 票据去向,0-开票，1-收票 */
	@ApiModelProperty(value = "票据去向,0-开票，1-收票")
	private Integer billDirectiontiny;
	
	/** 票据类型 */
	@ApiModelProperty(value = "票据类型")
	private String billType;
	
	/** 票据币种 */
	@ApiModelProperty(value = "票据币种")
	private String billMoneyType;
	
	/** 抬头 */
	@ApiModelProperty(value = "抬头")
	private String billTitle;
	
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

	@ApiModelProperty(value = "票据状态:未使用，已启用，已缴费，已核销，已废弃，已冲销，已交账，已挂账，已更换")
	private String billStatus;

	@ApiModelProperty(value = "收费科目Id")
	private Long itemId;

	@ApiModelProperty(value = "收费科目")
	private String itemName;
	
	/** 是否被使用，1：已使用，0:未使用 */
	@ApiModelProperty(value = "是否被使用，1：已使用，0:未使用")
	private Integer isUsed;
	
	/** 是否被销号/审核过，0-未，1-已 */
	@ApiModelProperty(value = "是否被销号/审核过，0-未，1-已")
	private Integer isCheck;
	
	/** 审核人 */
	@ApiModelProperty(value = "审核人")
	private Long checkUserId;
	
	/** 审核时间 */
	@ApiModelProperty(value = "审核时间")
	private Date checkDate;
	
	/** 是否被废弃,0-未废弃，1-已废弃 */
	@ApiModelProperty(value = "是否被废弃,0-未废弃，1-已废弃")
	private Integer isDestroy;
	
	/** 票据废弃日期 */
	@ApiModelProperty(value = "票据废弃日期")
	private Date destroyDate;
	
	/** 录入时间 */
	@ApiModelProperty(value = "录入时间")
	private Date opDate;
	
	/** 经手人 */
	@ApiModelProperty(value = "经手人")
	private Long dealUserId;
	
	/** 备注说明 */
	@ApiModelProperty(value = "备注说明")
	private String remark;
	
	/** 客户ID */
	@ApiModelProperty(value = "客户ID")
	private Long customerId;

	@ApiModelProperty(value = "客户名称")
	private String customerName;

	/** 账期 */
	@ApiModelProperty(value = "账期")
	private Integer accountBook;
	
	/** 备注 */
	@ApiModelProperty(value = "备注")
	private String addRemark;
	
	/** 更改时间 */
	@ApiModelProperty(value = "更改时间")
	private Date changeDate;
	
	/** 更改人id */
	@ApiModelProperty(value = "更改人id")
	private Long changeUserId;
	
	/** 审核操作时间 */
	@ApiModelProperty(value = "审核操作时间")
	private Date checkOperateDate;
	
	/** 红冲状态 */
	@ApiModelProperty(value = "红冲状态")
	private Integer correctStatus;
	
	/** 废弃人id */
	@ApiModelProperty(value = "废弃人id")
	private Long destroyUserId;
	
	/** 领用时间 */
	@ApiModelProperty(value = "领用时间")
	private Date drawDate;
	
	/** 领用部门编号 */
	@ApiModelProperty(value = "领用部门编号")
	private String drawDepartmentId;
	
	/** 领用id */
	@ApiModelProperty(value = "领用id")
	private Long drawId;
	
	/** 领用人Id */
	@ApiModelProperty(value = "领用人Id")
	private Long drawUserId;
	/** 领用人 */
	@ApiModelProperty(value = "领用人")
	private String drawUserName;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private BigDecimal handlingCharge;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private String houseList;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Integer isChange;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Integer isDayClosing;
	
	/** 是否打印，0:未打印,1:已打印 */
	@ApiModelProperty(value = "是否打印，0:未打印,1:已打印")
	private Integer isPrinted;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Long opUserId;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private String refBillNum;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private String refBillType;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private String satelliteCards;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private String voucherNum;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Long importBatchId;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Long batchDestoryId;
	
	/** 销账人 */
	@ApiModelProperty(value = "销账人")
	private Long enterAccountUserId;
	
	/** 销账日期 */
	@ApiModelProperty(value = "销账日期")
	private Date enterAccountDate;
	
	/** 票据码 */
	@ApiModelProperty(value = "票据码")
	private String billCode;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Long billTaxId;
	
	/** 新的发票抬头 */
	@ApiModelProperty(value = "新的发票抬头")
	private String newBillTitle;
	
	/** 使用人ID */
	@ApiModelProperty(value = "使用人ID")
	private Long useUserId;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private String isRecycle;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Date recycleDate;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private Long recycleUserId;
	
	/** 税额 */
	@ApiModelProperty(value = "税额")
	private Long taxAmount;
	
	/** 不含税金额 */
	@ApiModelProperty(value = "不含税金额")
	private Long notTaxAmount;
	
	/** 发票是否上传到国税 */
	@ApiModelProperty(value = "发票是否上传到国税")
	private String isUpload;
	
	/** 发票是否产生了红字编号 */
	@ApiModelProperty(value = "发票是否产生了红字编号")
	private String resBillNo;
	
	/** 特殊备注 */
	@ApiModelProperty(value = "特殊备注")
	private String specialRemark;
	
	/** JPG地址 */
	@ApiModelProperty(value = "JPG地址")
	private String billJpgurl;
	
	/** PDF地址 */
	@ApiModelProperty(value = "PDF地址")
	private String billPdfurl;
	
	/** .. */
	@ApiModelProperty(value = "..")
	private String billJpg;
	
	/** 票据打印次数 */
	@ApiModelProperty(value = "票据打印次数")
	private Long printNum;
	
	/** 企业税号 */
	@ApiModelProperty(value = "企业税号")
	private String customerTaxCode;
	
	/** 企业开户行及账号 */
	@ApiModelProperty(value = "企业开户行及账号")
	private String customerBankAccount;
	
	/** 企业地址电话 */
	@ApiModelProperty(value = "企业地址电话")
	private String customerAddressPhone;
	
	/** 电子发票流水号 */
	@ApiModelProperty(value = "电子发票流水号")
	private String fpqqlsh;
	
	/** 电子发票请求流水号 */
	@ApiModelProperty(value = "电子发票请求流水号")
	private String billOrderNo;
	
	/** 税号 */
	@ApiModelProperty(value = "税号")
	private String billTaxNo;
	
	/** 身份认证码 */
	@ApiModelProperty(value = "身份认证码")
	private String billIdentity;
	
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
	
	/** 是否删除，0:未删除，1:删除 */
	@ApiModelProperty(value = "是否删除，0:未删除，1:删除")
	private Integer isDelete;
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setBillDetailId(Long billDetailId) {
		this.billDetailId = billDetailId;
	}
	
	public Long getBillDetailId() {
		return billDetailId;
	}
	
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	
	public String getBillNum() {
		return billNum;
	}
	
	public void setBillDirectiontiny(Integer billDirectiontiny) {
		this.billDirectiontiny = billDirectiontiny;
	}
	
	public Integer getBillDirectiontiny() {
		return billDirectiontiny;
	}
	
	public void setBillType(String billType) {
		this.billType = billType;
	}
	
	public String getBillType() {
		return billType;
	}
	
	public void setBillMoneyType(String billMoneyType) {
		this.billMoneyType = billMoneyType;
	}
	
	public String getBillMoneyType() {
		return billMoneyType;
	}
	
	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}
	
	public String getBillTitle() {
		return billTitle;
	}
	
	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}
	
	public Date getUsedDate() {
		return usedDate;
	}
	
	public void setBillFund(BigDecimal billFund) {
		this.billFund = billFund;
	}
	
	public BigDecimal getBillFund() {
		return billFund;
	}
	
	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
	
	public Integer getIsUsed() {
		return isUsed;
	}
	
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	
	public Integer getIsCheck() {
		return isCheck;
	}
	
	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}
	
	public Long getCheckUserId() {
		return checkUserId;
	}
	
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	public Date getCheckDate() {
		return checkDate;
	}
	
	public void setIsDestroy(Integer isDestroy) {
		this.isDestroy = isDestroy;
	}
	
	public Integer getIsDestroy() {
		return isDestroy;
	}
	
	public void setDestroyDate(Date destroyDate) {
		this.destroyDate = destroyDate;
	}
	
	public Date getDestroyDate() {
		return destroyDate;
	}
	
	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}
	
	public Date getOpDate() {
		return opDate;
	}
	
	public void setDealUserId(Long dealUserId) {
		this.dealUserId = dealUserId;
	}
	
	public Long getDealUserId() {
		return dealUserId;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	
	public void setAccountBook(Integer accountBook) {
		this.accountBook = accountBook;
	}
	
	public Integer getAccountBook() {
		return accountBook;
	}
	
	public void setAddRemark(String addRemark) {
		this.addRemark = addRemark;
	}
	
	public String getAddRemark() {
		return addRemark;
	}
	
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
	public Date getChangeDate() {
		return changeDate;
	}
	
	public void setChangeUserId(Long changeUserId) {
		this.changeUserId = changeUserId;
	}
	
	public Long getChangeUserId() {
		return changeUserId;
	}
	
	public void setCheckOperateDate(Date checkOperateDate) {
		this.checkOperateDate = checkOperateDate;
	}
	
	public Date getCheckOperateDate() {
		return checkOperateDate;
	}
	
	public void setCorrectStatus(Integer correctStatus) {
		this.correctStatus = correctStatus;
	}
	
	public Integer getCorrectStatus() {
		return correctStatus;
	}
	
	public void setDestroyUserId(Long destroyUserId) {
		this.destroyUserId = destroyUserId;
	}
	
	public Long getDestroyUserId() {
		return destroyUserId;
	}
	
	public void setDrawDate(Date drawDate) {
		this.drawDate = drawDate;
	}
	
	public Date getDrawDate() {
		return drawDate;
	}
	
	public void setDrawDepartmentId(String drawDepartmentId) {
		this.drawDepartmentId = drawDepartmentId;
	}
	
	public String getDrawDepartmentId() {
		return drawDepartmentId;
	}
	
	public void setDrawId(Long drawId) {
		this.drawId = drawId;
	}
	
	public Long getDrawId() {
		return drawId;
	}
	
	public void setDrawUserId(Long drawUserId) {
		this.drawUserId = drawUserId;
	}
	
	public Long getDrawUserId() {
		return drawUserId;
	}
	
	public void setHandlingCharge(BigDecimal handlingCharge) {
		this.handlingCharge = handlingCharge;
	}
	
	public BigDecimal getHandlingCharge() {
		return handlingCharge;
	}
	
	public void setHouseList(String houseList) {
		this.houseList = houseList;
	}
	
	public String getHouseList() {
		return houseList;
	}
	
	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}
	
	public Integer getIsChange() {
		return isChange;
	}
	
	public void setIsDayClosing(Integer isDayClosing) {
		this.isDayClosing = isDayClosing;
	}
	
	public Integer getIsDayClosing() {
		return isDayClosing;
	}
	
	public void setIsPrinted(Integer isPrinted) {
		this.isPrinted = isPrinted;
	}
	
	public Integer getIsPrinted() {
		return isPrinted;
	}
	
	public void setOpUserId(Long opUserId) {
		this.opUserId = opUserId;
	}
	
	public Long getOpUserId() {
		return opUserId;
	}
	
	public void setRefBillNum(String refBillNum) {
		this.refBillNum = refBillNum;
	}
	
	public String getRefBillNum() {
		return refBillNum;
	}
	
	public void setRefBillType(String refBillType) {
		this.refBillType = refBillType;
	}
	
	public String getRefBillType() {
		return refBillType;
	}
	
	public void setSatelliteCards(String satelliteCards) {
		this.satelliteCards = satelliteCards;
	}
	
	public String getSatelliteCards() {
		return satelliteCards;
	}
	
	public void setVoucherNum(String voucherNum) {
		this.voucherNum = voucherNum;
	}
	
	public String getVoucherNum() {
		return voucherNum;
	}
	
	public void setImportBatchId(Long importBatchId) {
		this.importBatchId = importBatchId;
	}
	
	public Long getImportBatchId() {
		return importBatchId;
	}
	
	public void setBatchDestoryId(Long batchDestoryId) {
		this.batchDestoryId = batchDestoryId;
	}
	
	public Long getBatchDestoryId() {
		return batchDestoryId;
	}
	
	public void setEnterAccountUserId(Long enterAccountUserId) {
		this.enterAccountUserId = enterAccountUserId;
	}
	
	public Long getEnterAccountUserId() {
		return enterAccountUserId;
	}
	
	public void setEnterAccountDate(Date enterAccountDate) {
		this.enterAccountDate = enterAccountDate;
	}
	
	public Date getEnterAccountDate() {
		return enterAccountDate;
	}
	
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	
	public String getBillCode() {
		return billCode;
	}
	
	public void setBillTaxId(Long billTaxId) {
		this.billTaxId = billTaxId;
	}
	
	public Long getBillTaxId() {
		return billTaxId;
	}
	
	public void setNewBillTitle(String newBillTitle) {
		this.newBillTitle = newBillTitle;
	}
	
	public String getNewBillTitle() {
		return newBillTitle;
	}
	
	public void setUseUserId(Long useUserId) {
		this.useUserId = useUserId;
	}
	
	public Long getUseUserId() {
		return useUserId;
	}
	
	public void setIsRecycle(String isRecycle) {
		this.isRecycle = isRecycle;
	}
	
	public String getIsRecycle() {
		return isRecycle;
	}
	
	public void setRecycleDate(Date recycleDate) {
		this.recycleDate = recycleDate;
	}
	
	public Date getRecycleDate() {
		return recycleDate;
	}
	
	public void setRecycleUserId(Long recycleUserId) {
		this.recycleUserId = recycleUserId;
	}
	
	public Long getRecycleUserId() {
		return recycleUserId;
	}
	
	public void setTaxAmount(Long taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	public Long getTaxAmount() {
		return taxAmount;
	}
	
	public void setNotTaxAmount(Long notTaxAmount) {
		this.notTaxAmount = notTaxAmount;
	}
	
	public Long getNotTaxAmount() {
		return notTaxAmount;
	}
	
	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}
	
	public String getIsUpload() {
		return isUpload;
	}
	
	public void setResBillNo(String resBillNo) {
		this.resBillNo = resBillNo;
	}
	
	public String getResBillNo() {
		return resBillNo;
	}
	
	public void setSpecialRemark(String specialRemark) {
		this.specialRemark = specialRemark;
	}
	
	public String getSpecialRemark() {
		return specialRemark;
	}
	
	public void setBillJpgurl(String billJpgurl) {
		this.billJpgurl = billJpgurl;
	}
	
	public String getBillJpgurl() {
		return billJpgurl;
	}
	
	public void setBillPdfurl(String billPdfurl) {
		this.billPdfurl = billPdfurl;
	}
	
	public String getBillPdfurl() {
		return billPdfurl;
	}
	
	public void setBillJpg(String billJpg) {
		this.billJpg = billJpg;
	}
	
	public String getBillJpg() {
		return billJpg;
	}
	
	public void setPrintNum(Long printNum) {
		this.printNum = printNum;
	}
	
	public Long getPrintNum() {
		return printNum;
	}
	
	public void setCustomerTaxCode(String customerTaxCode) {
		this.customerTaxCode = customerTaxCode;
	}
	
	public String getCustomerTaxCode() {
		return customerTaxCode;
	}
	
	public void setCustomerBankAccount(String customerBankAccount) {
		this.customerBankAccount = customerBankAccount;
	}
	
	public String getCustomerBankAccount() {
		return customerBankAccount;
	}
	
	public void setCustomerAddressPhone(String customerAddressPhone) {
		this.customerAddressPhone = customerAddressPhone;
	}
	
	public String getCustomerAddressPhone() {
		return customerAddressPhone;
	}
	
	public void setFpqqlsh(String fpqqlsh) {
		this.fpqqlsh = fpqqlsh;
	}
	
	public String getFpqqlsh() {
		return fpqqlsh;
	}
	
	public void setBillOrderNo(String billOrderNo) {
		this.billOrderNo = billOrderNo;
	}
	
	public String getBillOrderNo() {
		return billOrderNo;
	}
	
	public void setBillTaxNo(String billTaxNo) {
		this.billTaxNo = billTaxNo;
	}
	
	public String getBillTaxNo() {
		return billTaxNo;
	}
	
	public void setBillIdentity(String billIdentity) {
		this.billIdentity = billIdentity;
	}
	
	public String getBillIdentity() {
		return billIdentity;
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
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getIsDelete() {
		return isDelete;
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

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDrawUserName() {
		return drawUserName;
	}

	public void setDrawUserName(String drawUserName) {
		this.drawUserName = drawUserName;
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
}
