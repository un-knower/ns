package com.newsee.owner.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WyglChargeChargePayment {
    /**
     * 
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long customerid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long houseid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long meterid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long chargeid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long chargedetailid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Byte iscanceled;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long refpaymentid;

    /**
     * 
     *
     * @mbggenerated
     */
    private String billno;

    /**
     * 
     *
     * @mbggenerated
     */
    private String subjectcode;

    /**
     * 
     *
     * @mbggenerated
     */
    private BigDecimal precharge;

    /**
     * 
     *
     * @mbggenerated
     */
    private BigDecimal chargepaid;

    /**
     * 
     *
     * @mbggenerated
     */
    private BigDecimal shouldpaid;

    /**
     * 
     *
     * @mbggenerated
     */
    private BigDecimal discount;

    /**
     * 
     *
     * @mbggenerated
     */
    private BigDecimal delaysum;

    /**
     * 
     *
     * @mbggenerated
     */
    private BigDecimal delaydiscount;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date delaydate;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date operatordate;

    /**
     * 
     *
     * @mbggenerated
     */
    private String squaretypeid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Integer userid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date realdodate;

    /**
     * 
     *
     * @mbggenerated
     */
    private String prefix;

    /**
     * 
     *
     * @mbggenerated
     */
    private String number;

    /**
     * 
     *
     * @mbggenerated
     */
    private String postfix;

    /**
     * 
     *
     * @mbggenerated
     */
    private Byte ischeck;

    /**
     * 
     *
     * @mbggenerated
     */
    private Integer checkuserid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date checkdate;

    /**
     * 
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 
     *
     * @mbggenerated
     */
    private String billtype;

    /**
     * 
     *
     * @mbggenerated
     */
    private String discounttype;

    /**
     * 
     *
     * @mbggenerated
     */
    private Short amount;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long precinctid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long chargeitemid;

    /**
     * 
     *
     * @mbggenerated
     */
    private String cheque;

    /**
     * 
     *
     * @mbggenerated
     */
    private Integer accountbook;

    /**
     * 
     *
     * @mbggenerated
     */
    private Integer isclosing;

    /**
     * 
     *
     * @mbggenerated
     */
    private Integer isdayclosing;

    /**
     * 
     *
     * @mbggenerated
     */
    private String closingday;

    /**
     * 
     *
     * @mbggenerated
     */
    private Integer oldaccountbook;

    /**
     * 
     *
     * @mbggenerated
     */
    private Integer isclosestat;

    /**
     * 
     *
     * @mbggenerated
     */
    private BigDecimal validcharge;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long discountid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Integer isenteraccount;

    /**
     * 
     *
     * @mbggenerated
     */
    private String manualhousename;

    /**
     * 
     *
     * @mbggenerated
     */
    private String manualcustomername;

    /**
     * 
     *
     * @mbggenerated
     */
    private String manualautoid;

    /**
     * 
     *
     * @mbggenerated
     */
    private String manualsatellitecardid;

    /**
     * 
     *
     * @mbggenerated
     */
    private String dailyclosingday;

    /**
     * 
     *
     * @mbggenerated
     */
    private BigDecimal fromvirtualprepaid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long enteraccountuserid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date enteraccountdate;

    /**
     * 
     *
     * @mbggenerated
     */
    private BigDecimal enteraccountbalance;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long delaydetailid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long voucherbatchid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long dayclosinguserid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long importbatchid;

    /**
     * 
     *
     * @mbggenerated
     */
    private String billcode;

    /**
     * 
     *
     * @mbggenerated
     */
    private Integer isvoucher;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long refcollectionpaymentid;

    /**
     * 
     *
     * @mbggenerated
     */
    private BigDecimal taxamount;

    /**
     * 
     *
     * @mbggenerated
     */
    private BigDecimal taxrate;

    /**
     * 
     *
     * @mbggenerated
     */
    private BigDecimal nottaxamount;

    /**
     * 
     *
     * @mbggenerated
     */
    private String paybankname;

    /**
     * 
     *
     * @mbggenerated
     */
    private String paybankno;

    /**
     * 
     *
     * @mbggenerated
     */
    private Integer isalipaycharge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public Long getHouseid() {
        return houseid;
    }

    public void setHouseid(Long houseid) {
        this.houseid = houseid;
    }

    public Long getMeterid() {
        return meterid;
    }

    public void setMeterid(Long meterid) {
        this.meterid = meterid;
    }

    public Long getChargeid() {
        return chargeid;
    }

    public void setChargeid(Long chargeid) {
        this.chargeid = chargeid;
    }

    public Long getChargedetailid() {
        return chargedetailid;
    }

    public void setChargedetailid(Long chargedetailid) {
        this.chargedetailid = chargedetailid;
    }

    public Byte getIscanceled() {
        return iscanceled;
    }

    public void setIscanceled(Byte iscanceled) {
        this.iscanceled = iscanceled;
    }

    public Long getRefpaymentid() {
        return refpaymentid;
    }

    public void setRefpaymentid(Long refpaymentid) {
        this.refpaymentid = refpaymentid;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
    }

    public BigDecimal getPrecharge() {
        return precharge;
    }

    public void setPrecharge(BigDecimal precharge) {
        this.precharge = precharge;
    }

    public BigDecimal getChargepaid() {
        return chargepaid;
    }

    public void setChargepaid(BigDecimal chargepaid) {
        this.chargepaid = chargepaid;
    }

    public BigDecimal getShouldpaid() {
        return shouldpaid;
    }

    public void setShouldpaid(BigDecimal shouldpaid) {
        this.shouldpaid = shouldpaid;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDelaysum() {
        return delaysum;
    }

    public void setDelaysum(BigDecimal delaysum) {
        this.delaysum = delaysum;
    }

    public BigDecimal getDelaydiscount() {
        return delaydiscount;
    }

    public void setDelaydiscount(BigDecimal delaydiscount) {
        this.delaydiscount = delaydiscount;
    }

    public Date getDelaydate() {
        return delaydate;
    }

    public void setDelaydate(Date delaydate) {
        this.delaydate = delaydate;
    }

    public Date getOperatordate() {
        return operatordate;
    }

    public void setOperatordate(Date operatordate) {
        this.operatordate = operatordate;
    }

    public String getSquaretypeid() {
        return squaretypeid;
    }

    public void setSquaretypeid(String squaretypeid) {
        this.squaretypeid = squaretypeid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getRealdodate() {
        return realdodate;
    }

    public void setRealdodate(Date realdodate) {
        this.realdodate = realdodate;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public Byte getIscheck() {
        return ischeck;
    }

    public void setIscheck(Byte ischeck) {
        this.ischeck = ischeck;
    }

    public Integer getCheckuserid() {
        return checkuserid;
    }

    public void setCheckuserid(Integer checkuserid) {
        this.checkuserid = checkuserid;
    }

    public Date getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public String getDiscounttype() {
        return discounttype;
    }

    public void setDiscounttype(String discounttype) {
        this.discounttype = discounttype;
    }

    public Short getAmount() {
        return amount;
    }

    public void setAmount(Short amount) {
        this.amount = amount;
    }

    public Long getPrecinctid() {
        return precinctid;
    }

    public void setPrecinctid(Long precinctid) {
        this.precinctid = precinctid;
    }

    public Long getChargeitemid() {
        return chargeitemid;
    }

    public void setChargeitemid(Long chargeitemid) {
        this.chargeitemid = chargeitemid;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public Integer getAccountbook() {
        return accountbook;
    }

    public void setAccountbook(Integer accountbook) {
        this.accountbook = accountbook;
    }

    public Integer getIsclosing() {
        return isclosing;
    }

    public void setIsclosing(Integer isclosing) {
        this.isclosing = isclosing;
    }

    public Integer getIsdayclosing() {
        return isdayclosing;
    }

    public void setIsdayclosing(Integer isdayclosing) {
        this.isdayclosing = isdayclosing;
    }

    public String getClosingday() {
        return closingday;
    }

    public void setClosingday(String closingday) {
        this.closingday = closingday;
    }

    public Integer getOldaccountbook() {
        return oldaccountbook;
    }

    public void setOldaccountbook(Integer oldaccountbook) {
        this.oldaccountbook = oldaccountbook;
    }

    public Integer getIsclosestat() {
        return isclosestat;
    }

    public void setIsclosestat(Integer isclosestat) {
        this.isclosestat = isclosestat;
    }

    public BigDecimal getValidcharge() {
        return validcharge;
    }

    public void setValidcharge(BigDecimal validcharge) {
        this.validcharge = validcharge;
    }

    public Long getDiscountid() {
        return discountid;
    }

    public void setDiscountid(Long discountid) {
        this.discountid = discountid;
    }

    public Integer getIsenteraccount() {
        return isenteraccount;
    }

    public void setIsenteraccount(Integer isenteraccount) {
        this.isenteraccount = isenteraccount;
    }

    public String getManualhousename() {
        return manualhousename;
    }

    public void setManualhousename(String manualhousename) {
        this.manualhousename = manualhousename;
    }

    public String getManualcustomername() {
        return manualcustomername;
    }

    public void setManualcustomername(String manualcustomername) {
        this.manualcustomername = manualcustomername;
    }

    public String getManualautoid() {
        return manualautoid;
    }

    public void setManualautoid(String manualautoid) {
        this.manualautoid = manualautoid;
    }

    public String getManualsatellitecardid() {
        return manualsatellitecardid;
    }

    public void setManualsatellitecardid(String manualsatellitecardid) {
        this.manualsatellitecardid = manualsatellitecardid;
    }

    public String getDailyclosingday() {
        return dailyclosingday;
    }

    public void setDailyclosingday(String dailyclosingday) {
        this.dailyclosingday = dailyclosingday;
    }

    public BigDecimal getFromvirtualprepaid() {
        return fromvirtualprepaid;
    }

    public void setFromvirtualprepaid(BigDecimal fromvirtualprepaid) {
        this.fromvirtualprepaid = fromvirtualprepaid;
    }

    public Long getEnteraccountuserid() {
        return enteraccountuserid;
    }

    public void setEnteraccountuserid(Long enteraccountuserid) {
        this.enteraccountuserid = enteraccountuserid;
    }

    public Date getEnteraccountdate() {
        return enteraccountdate;
    }

    public void setEnteraccountdate(Date enteraccountdate) {
        this.enteraccountdate = enteraccountdate;
    }

    public BigDecimal getEnteraccountbalance() {
        return enteraccountbalance;
    }

    public void setEnteraccountbalance(BigDecimal enteraccountbalance) {
        this.enteraccountbalance = enteraccountbalance;
    }

    public Long getDelaydetailid() {
        return delaydetailid;
    }

    public void setDelaydetailid(Long delaydetailid) {
        this.delaydetailid = delaydetailid;
    }

    public Long getVoucherbatchid() {
        return voucherbatchid;
    }

    public void setVoucherbatchid(Long voucherbatchid) {
        this.voucherbatchid = voucherbatchid;
    }

    public Long getDayclosinguserid() {
        return dayclosinguserid;
    }

    public void setDayclosinguserid(Long dayclosinguserid) {
        this.dayclosinguserid = dayclosinguserid;
    }

    public Long getImportbatchid() {
        return importbatchid;
    }

    public void setImportbatchid(Long importbatchid) {
        this.importbatchid = importbatchid;
    }

    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }

    public Integer getIsvoucher() {
        return isvoucher;
    }

    public void setIsvoucher(Integer isvoucher) {
        this.isvoucher = isvoucher;
    }

    public Long getRefcollectionpaymentid() {
        return refcollectionpaymentid;
    }

    public void setRefcollectionpaymentid(Long refcollectionpaymentid) {
        this.refcollectionpaymentid = refcollectionpaymentid;
    }

    public BigDecimal getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(BigDecimal taxamount) {
        this.taxamount = taxamount;
    }

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public BigDecimal getNottaxamount() {
        return nottaxamount;
    }

    public void setNottaxamount(BigDecimal nottaxamount) {
        this.nottaxamount = nottaxamount;
    }

    public String getPaybankname() {
        return paybankname;
    }

    public void setPaybankname(String paybankname) {
        this.paybankname = paybankname;
    }

    public String getPaybankno() {
        return paybankno;
    }

    public void setPaybankno(String paybankno) {
        this.paybankno = paybankno;
    }

    public Integer getIsalipaycharge() {
        return isalipaycharge;
    }

    public void setIsalipaycharge(Integer isalipaycharge) {
        this.isalipaycharge = isalipaycharge;
    }
}