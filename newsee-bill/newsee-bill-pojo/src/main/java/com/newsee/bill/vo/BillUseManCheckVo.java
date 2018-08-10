package com.newsee.bill.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author mu.jie
 * @Date 2018/7/31 11:36
 */
public class BillUseManCheckVo {
    @ApiModelProperty(value = "票据使用自增Id")
    private Long id;

    @ApiModelProperty(value = "票据本编号Id")
    private Long billDetailId;

    @ApiModelProperty(value = "票据号")
    private String billNum;

    @ApiModelProperty(value = "票据类型")
    private String billType;

    @ApiModelProperty(value = "已被领用数量")
    private Long drawNum;

    @ApiModelProperty(value = "已被使用数量")
    private Long usedNum;

    @ApiModelProperty(value = "已被废止数量")
    private Long destroyNum;

    @ApiModelProperty(value = "已被销号数量")
    private Long checkedNum;

    @ApiModelProperty(value = "票据金额")
    private Long billFund;

    /**
     * 写死当前时间，给前端，
     */
    @ApiModelProperty(value = "核销日期")
    private Date checkDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Long billDetailId) {
        this.billDetailId = billDetailId;
    }

    public String getBillNum() {
        return billNum;
    }

    public void setBillNum(String billNum) {
        this.billNum = billNum;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public Long getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(Long drawNum) {
        this.drawNum = drawNum;
    }

    public Long getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Long usedNum) {
        this.usedNum = usedNum;
    }

    public Long getDestroyNum() {
        return destroyNum;
    }

    public void setDestroyNum(Long destroyNum) {
        this.destroyNum = destroyNum;
    }

    public Long getCheckedNum() {
        return checkedNum;
    }

    public void setCheckedNum(Long checkedNum) {
        this.checkedNum = checkedNum;
    }

    public Long getBillFund() {
        return billFund;
    }

    public void setBillFund(Long billFund) {
        this.billFund = billFund;
    }

    public Date getCheckDate() {
        return new Date();
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}
