package com.newsee.system.entity;


import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 省/市/区域表
 * @author sw
 *
 */
public class NsSystemArea implements Serializable{
	 
    private static final long serialVersionUID = 4318703622430269992L;
    /** 主键ID */
	@ApiModelProperty(value = "主键ID")
    private Long id;
	/** 区域编码 */
	@ApiModelProperty(value = "区域编码")
    private String areaCode;
	/** 区域名 */
	@ApiModelProperty(value = "区域名")
    private String areaName;
	/** 父级编码 */
	@ApiModelProperty(value = "父级编码")
    private String parentAreaCode;
	/** 区域级别 0 国，1 省，2 市，3 区/县，4 街道/镇 */
	@ApiModelProperty(value = "区域级别 0 国，1 省，2 市，3 区/县，4 街道/镇")
    private String areaLevel;
	/** 区域拼音首字母大写简称 */
	@ApiModelProperty(value = "区域拼音首字母大写简称")
    private String areaPy;
	/** 区域邮编 */
	@ApiModelProperty(value = "区域邮编")
    private String areaPostCode;
	/** 序列 */
	@ApiModelProperty(value = "序列")
    private Integer orderNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParentAreaCode() {
        return parentAreaCode;
    }

    public void setParentAreaCode(String parentAreaCode) {
        this.parentAreaCode = parentAreaCode;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getAreaPy() {
        return areaPy;
    }

    public void setAreaPy(String areaPy) {
        this.areaPy = areaPy;
    }

    public String getAreaPostCode() {
        return areaPostCode;
    }

    public void setAreaPostCode(String areaPostCode) {
        this.areaPostCode = areaPostCode;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}