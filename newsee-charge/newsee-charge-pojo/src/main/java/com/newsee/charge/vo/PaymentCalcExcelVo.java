package com.newsee.charge.vo;

import java.util.Date;

/**
 * 导出Excel
 */
public class PaymentCalcExcelVo {
    /** 项目名称 */
    private String precinctName;
    /** 房产简称 */
    private String houesName;
    /** 收费对象类型 */
    private String chargeType;
    /** 收费对象姓名 */
    private String chargeName;
    /** 证件类型 */
    private String idCardType;
    /** 证件号码 */
    private String idCard;
    /** 收费科目 */
    private String chargeItemName;
    /** 单价 */
    private Double price;
    /** 计费开始日期 */
    private Date startDate;
    /** 计费结束时间 */
    private Date endDate;
    /** 应收款日期 */
    private Date shouldChargeDate;
    /** 应收金额 */
    private Double shouldChargePrice;

    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    public String getHouesName() {
        return houesName;
    }

    public void setHouesName(String houesName) {
        this.houesName = houesName;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getChargeItemName() {
        return chargeItemName;
    }

    public void setChargeItemName(String chargeItemName) {
        this.chargeItemName = chargeItemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getShouldChargeDate() {
        return shouldChargeDate;
    }

    public void setShouldChargeDate(Date shouldChargeDate) {
        this.shouldChargeDate = shouldChargeDate;
    }

    public Double getShouldChargePrice() {
        return shouldChargePrice;
    }

    public void setShouldChargePrice(Double shouldChargePrice) {
        this.shouldChargePrice = shouldChargePrice;
    }
}
