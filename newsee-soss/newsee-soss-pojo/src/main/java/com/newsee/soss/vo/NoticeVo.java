/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

/**
 * NoticeVo
 * @version 1.0
 * @author
 */
public class NoticeVo implements Serializable {
	
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
	private String iconCode;
	private String iconUrl;
	
	/** 图片 */
	private String imageCode;
	private String imageUrl;
	
	/** 内容 */
	@ApiModelProperty(value = "内容")
	private String content;
	@ApiModelProperty(value = "简介")
	private String description;
	
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
	
	/** 备注 */
	@ApiModelProperty(value = "备注")
	private String remark;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}


	public String getIconCode() {
		return iconCode;
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

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public Long getHandleUserId() {
		return handleUserId;
	}

	public void setHandleUserId(Long handleUserId) {
		this.handleUserId = handleUserId;
	}

	public String getHandleUserName() {
		return handleUserName;
	}

	public void setHandleUserName(String handleUserName) {
		this.handleUserName = handleUserName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
