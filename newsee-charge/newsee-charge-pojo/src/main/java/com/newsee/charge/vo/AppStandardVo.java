package com.newsee.charge.vo;

import java.io.Serializable;

/**
 * 返回APP端
 * @author mu.jie
 * @Date 2018/8/2 16:20
 */
public class AppStandardVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long enterpriseId;

    private Long organizationId;

    private Long ChargeItemID;

    private String ChargeItemName;

    private Long ChargeID;

    private String ChargeName;

    private Long HouseID;

    private Long CustomerID;

    private Double ChargeSum;

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

    public Long getChargeItemID() {
        return ChargeItemID;
    }

    public void setChargeItemID(Long chargeItemID) {
        ChargeItemID = chargeItemID;
    }

    public String getChargeItemName() {
        return ChargeItemName;
    }

    public void setChargeItemName(String chargeItemName) {
        ChargeItemName = chargeItemName;
    }

    public Long getChargeID() {
        return ChargeID;
    }

    public void setChargeID(Long chargeID) {
        ChargeID = chargeID;
    }

    public String getChargeName() {
        return ChargeName;
    }

    public void setChargeName(String chargeName) {
        ChargeName = chargeName;
    }

    public Long getHouseID() {
        return HouseID;
    }

    public void setHouseID(Long houseID) {
        HouseID = houseID;
    }

    public Long getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(Long customerID) {
        CustomerID = customerID;
    }

    public Double getChargeSum() {
        return ChargeSum;
    }

    public void setChargeSum(Double chargeSum) {
        ChargeSum = chargeSum;
    }
}
