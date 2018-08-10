/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 富文本表
 * @version 1.0
 * @author
 */
public class NsSossRichtext implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** id */
	@ApiModelProperty(value = "id")
	private Long id;
	
	/** 目标业务ID */
	@ApiModelProperty(value = "目标业务ID")
	private Long targetId;
	
	/** 目标业务编码 */
	@ApiModelProperty(value = "目标业务编码")
	private String targetCode;
	
	/** 富文本内容 */
	@ApiModelProperty(value = "富文本内容")
	private String targetContent;
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	
	public Long getTargetId() {
		return targetId;
	}
	
	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}
	
	public String getTargetCode() {
		return targetCode;
	}
	
	public void setTargetContent(String targetContent) {
		this.targetContent = targetContent;
	}
	
	public String getTargetContent() {
		return targetContent;
	}
	
}
