package com.newsee.bill.vo;

import java.util.Date;
import java.util.List;

/**
 * @author mu.jie
 * @Date 2018/7/24 9:52
 */
public class BillUseManSearchVo {

    private Integer isCheck;

    /** 企业id */
    private Long enterpriseId;

    /** 组织id,1|number|0|0|0,1|text|0||1|1 */
    private Long organizationId;
    /**
     * 房产id
     */
    private Long houseId;
    /**
     * 票据类型
     */
    private List<String> billType;
    /**
     * 票据起始号码
     */
    private String billStartNum;
    /**
     * 票据结束号码
     */
    private String billEndNum;
    /**
     * 交账开始日期
     */
    private Date checkStartDate;
    /**
     * 交账结束日期
     */
    private Date checkEndDate;
    /**
     * 开票人
     */
    private Long usedUserId;
    /**
     * 票据状态
     */
    private List<String> billStatus;

    /**
     * 开票日期，起始
     */
    private Date usedStartDate;

    /**
     * 开票日期，结束
     */
    private Date usedEndDate;
    /**
     * 账期
     */
    private String accountBook;
    /**
     * 结算方式
     */
    private List<String> payMethod;
    /**
     * 票据代码
     */
    private String billCode;

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public List<String> getBillType() {
		return billType;
	}

	public void setBillType(List<String> billType) {
		this.billType = billType;
	}

	public List<String> getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(List<String> billStatus) {
		this.billStatus = billStatus;
	}

	public List<String> getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(List<String> payMethod) {
		this.payMethod = payMethod;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
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

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getBillStartNum() {
        return billStartNum;
    }

    public void setBillStartNum(String billStartNum) {
        this.billStartNum = billStartNum;
    }

    public String getBillEndNum() {
        return billEndNum;
    }

    public void setBillEndNum(String billEndNum) {
        this.billEndNum = billEndNum;
    }

    public Date getCheckStartDate() {
        return checkStartDate;
    }

    public void setCheckStartDate(Date checkStartDate) {
        this.checkStartDate = checkStartDate;
    }

    public Date getCheckEndDate() {
        return checkEndDate;
    }

    public void setCheckEndDate(Date checkEndDate) {
        this.checkEndDate = checkEndDate;
    }

    public Long getUsedUserId() {
        return usedUserId;
    }

    public void setUsedUserId(Long usedUserId) {
        this.usedUserId = usedUserId;
    }

    public Date getUsedStartDate() {
        return usedStartDate;
    }

    public void setUsedStartDate(Date usedStartDate) {
        this.usedStartDate = usedStartDate;
    }

    public Date getUsedEndDate() {
        return usedEndDate;
    }

    public void setUsedEndDate(Date usedEndDate) {
        this.usedEndDate = usedEndDate;
    }

    public String getAccountBook() {
        return accountBook;
    }

    public void setAccountBook(String accountBook) {
        this.accountBook = accountBook;
    }
}
