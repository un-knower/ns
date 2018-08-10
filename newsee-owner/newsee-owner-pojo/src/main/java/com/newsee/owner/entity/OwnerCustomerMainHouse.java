package com.newsee.owner.entity;

public class OwnerCustomerMainHouse {

    private Long mainHouseId;

    private Long ownerId;

    private Long precinctId;
    
    private Long houseId;

    private String ownerProperty;

    public Long getMainHouseId() {
        return mainHouseId;
    }

    public void setMainHouseId(Long mainHouseId) {
        this.mainHouseId = mainHouseId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getOwnerProperty() {
        return ownerProperty;
    }

    public void setOwnerProperty(String ownerProperty) {
        this.ownerProperty = ownerProperty;
    }

    public Long getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(Long precinctId) {
        this.precinctId = precinctId;
    }
    
}