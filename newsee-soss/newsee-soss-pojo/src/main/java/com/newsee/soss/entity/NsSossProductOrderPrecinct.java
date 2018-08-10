/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 服务项目表
 * @version 1.0
 * @author
 */
public class NsSossProductOrderPrecinct implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** id */
	@ApiModelProperty(value = "id")
	private Long id;
	
	/** 订单产品id */
	@ApiModelProperty(value = "订单产品id")
	private Long orderProductId;
	
	/** 服务项目id,0,1|numbertext|0||7|1 */
	@ApiModelProperty(value = "服务项目id,0,1|numbertext|0||7|1")
	private Long precinctId;
	
	/** 服务项目名称,0,1|text|0||8|0 */
	@ApiModelProperty(value = "服务项目名称,0,1|text|0||8|0")
	private String precinctName;
	
	/** 服务项目面积,0,1|numbertext|0||9|0 */
	@ApiModelProperty(value = "服务项目面积,0,1|numbertext|0||9|0")
	private Long precinctArea;
	/**项目数*/
	private Integer precinctNum;
	
	/** 服务项目状态 */
	@ApiModelProperty(value = "服务项目状态")
	private String precinctStatus;
	
	/** 省 */
	@ApiModelProperty(value = "省")
	private String provinceId;
	
	/** 市 */
	@ApiModelProperty(value = "市")
	private String cityId;
	
	/** 区 */
	@ApiModelProperty(value = "区")
	private String areaId;
	
	/** 街道 */
	@ApiModelProperty(value = "街道")
	private String streetId;
	
	
	public Integer getPrecinctNum() {
		return precinctNum;
	}

	public void setPrecinctNum(Integer precinctNum) {
		this.precinctNum = precinctNum;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setOrderProductId(Long orderProductId) {
		this.orderProductId = orderProductId;
	}
	
	public Long getOrderProductId() {
		return orderProductId;
	}
	
	public void setPrecinctId(Long precinctId) {
		this.precinctId = precinctId;
	}
	
	public Long getPrecinctId() {
		return precinctId;
	}
	
	public void setPrecinctName(String precinctName) {
		this.precinctName = precinctName;
	}
	
	public String getPrecinctName() {
		return precinctName;
	}
	
	public void setPrecinctArea(Long precinctArea) {
		this.precinctArea = precinctArea;
	}
	
	public Long getPrecinctArea() {
		return precinctArea;
	}
	
	public void setPrecinctStatus(String precinctStatus) {
		this.precinctStatus = precinctStatus;
	}
	
	public String getPrecinctStatus() {
		return precinctStatus;
	}
	
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	public String getProvinceId() {
		return provinceId;
	}
	
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	public String getCityId() {
		return cityId;
	}
	
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public String getAreaId() {
		return areaId;
	}
	
	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}
	
	public String getStreetId() {
		return streetId;
	}
	
}
