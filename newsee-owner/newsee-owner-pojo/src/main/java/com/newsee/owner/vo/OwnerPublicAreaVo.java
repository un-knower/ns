package com.newsee.owner.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OwnerPublicAreaVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -5103466827016543783L;

    private Long houseId;

    private String publicAreaNo;

    private String publicAreaShortName;

    private Long developer;

    private String builder;

    private Date startWorkTime;

    private Date completeTime;

    private Date deliveryTime;

    private BigDecimal buildingArea;

    private String remark;
    
    private Long handlerId;

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getPublicAreaNo() {
        return publicAreaNo;
    }

    public void setPublicAreaNo(String publicAreaNo) {
        this.publicAreaNo = publicAreaNo;
    }

    public String getPublicAreaShortName() {
        return publicAreaShortName;
    }

    public void setPublicAreaShortName(String publicAreaShortName) {
        this.publicAreaShortName = publicAreaShortName;
    }

    public Long getDeveloper() {
        return developer;
    }

    public void setDeveloper(Long developer) {
        this.developer = developer;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public Date getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(Date startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public BigDecimal getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BigDecimal buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }
    
}
