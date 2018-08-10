package com.newsee.owner.entity;

public class BillBillDetailBusiness {
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
    private String tablename;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long billdetailid;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long businessid;

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
    private String isused;

    /**
     * 
     *
     * @mbggenerated
     */
    private Integer iscanceled;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long importbatchid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public Long getBilldetailid() {
        return billdetailid;
    }

    public void setBilldetailid(Long billdetailid) {
        this.billdetailid = billdetailid;
    }

    public Long getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Long businessid) {
        this.businessid = businessid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsused() {
        return isused;
    }

    public void setIsused(String isused) {
        this.isused = isused;
    }

    public Integer getIscanceled() {
        return iscanceled;
    }

    public void setIscanceled(Integer iscanceled) {
        this.iscanceled = iscanceled;
    }

    public Long getImportbatchid() {
        return importbatchid;
    }

    public void setImportbatchid(Long importbatchid) {
        this.importbatchid = importbatchid;
    }
}