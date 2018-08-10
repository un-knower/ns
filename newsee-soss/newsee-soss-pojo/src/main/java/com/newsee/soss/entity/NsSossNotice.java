/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 公告新闻表
 * @version 1.0
 * @author
 */
public class NsSossNotice implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 公告id,1|number|0|0|1 */
	@ApiModelProperty(value = "公告id,1|number|0|0|1")
	private Long id;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 企业名称,1|text|0|200|2 */
	@ApiModelProperty(value = "企业名称,1|text|0|200|2")
	private String enterpriseName;
	
	/** 标题,1|text|0|200|3 */
	@ApiModelProperty(value = "标题,1|text|0|200|3")
	private String title;
	
	/** 栏目,1|select|0|200|4,0,0:常见问题|10:新手学堂|20:SOOS系统|30:SAAS系统 */
	@ApiModelProperty(value = "栏目,1|select|0|200|4,0,0:常见问题|10:新手学堂|20:SOOS系统|30:SAAS系统")
	private String noticeType;
	
	/** 状态,1|select|0|200|10,0,0:保存|1:发布|2:审核 */
	@ApiModelProperty(value = "状态,1|select|0|200|10,0,0:保存|1:发布|2:审核")
	private String noticeStatus;
	
	/** icon */
	@ApiModelProperty(value = "icon")
	private String iconCode;
	
	/** 图片 */
	@ApiModelProperty(value = "图片")
	private String imageCode;
	
	/** 内容 */
	@ApiModelProperty(value = "内容")
	private String content;
	
	/** 操作时间,1|date|0|200|6 */
	@ApiModelProperty(value = "操作时间,1|date|0|200|6")
	private Date handleTime;
	
	/** 操作人 */
	@ApiModelProperty(value = "操作人")
	private Long handleUserId;
	
	/** 操作人名,1|text|0|200|7 */
	@ApiModelProperty(value = "操作人名,1|text|0|200|7")
	private String handleUserName;
	
	/** url连接 */
	@ApiModelProperty(value = "url连接")
	private String url;
	
	/** 发布时间,1|date|0|200|5 */
	@ApiModelProperty(value = "发布时间,1|date|0|200|5")
	private Date publishTime;
	
	/** 是否删除 0 否，1 是 */
	@ApiModelProperty(value = "是否删除 0 否，1 是")
	private Integer isDelete;
	
	/** 备注 */
	@ApiModelProperty(value = "备注")
	private String remark;
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
	public String getEnterpriseName() {
		return enterpriseName;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	
	public String getNoticeType() {
		return noticeType;
	}
	
	
	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public void setIconCode(String iconCode) {
		this.iconCode = iconCode;
	}
	
	public String getIconCode() {
		return iconCode;
	}
	
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	
	public String getImageCode() {
		return imageCode;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	
	public Date getHandleTime() {
		return handleTime;
	}
	
	public void setHandleUserId(Long handleUserId) {
		this.handleUserId = handleUserId;
	}
	
	public Long getHandleUserId() {
		return handleUserId;
	}
	
	public void setHandleUserName(String handleUserName) {
		this.handleUserName = handleUserName;
	}
	
	public String getHandleUserName() {
		return handleUserName;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	
	public Date getPublishTime() {
		return publishTime;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
}
