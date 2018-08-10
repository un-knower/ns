package com.newsee.common.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class SelectEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1585433587079506104L;
	
	/** 下拉选项label */
	@ApiModelProperty(value = "下拉选项label")
	private String label;
	
	/** 下拉选项value */
	@ApiModelProperty(value = "下拉选项value")
	private String value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
