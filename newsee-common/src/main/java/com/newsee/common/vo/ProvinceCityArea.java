package com.newsee.common.vo;

import java.io.Serializable;

public class ProvinceCityArea implements Serializable {

    private static final long serialVersionUID = -3544479886497643170L;
    
    private String province;
    private String city;
    private String district;
    private String street;
    
    public ProvinceCityArea() {
    	
    }
    
    public ProvinceCityArea(String province, String city, String district, String street) {
		this.province = province == null ? "" : province;
		this.city = city == null ? "" : city;
		this.district = district == null ? "" : district;
		this.street = street == null ? "" : street;
	}
    
	public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    
}
