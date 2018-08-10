package com.newsee.owner.vo;

import java.io.Serializable;

public class MainHouseVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 6928071814685571328L;

    private String ownerPrecinct;
    
    private String ownerHouse;

    private String candidate;
    
    public String getOwnerPrecinct() {
        return ownerPrecinct;
    }

    public void setOwnerPrecinct(String ownerPrecinct) {
        this.ownerPrecinct = ownerPrecinct;
    }

    public String getOwnerHouse() {
        return ownerHouse;
    }

    public void setOwnerHouse(String ownerHouse) {
        this.ownerHouse = ownerHouse;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }
    
}
