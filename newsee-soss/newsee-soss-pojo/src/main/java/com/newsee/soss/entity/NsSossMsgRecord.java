/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 消息记录表
 * @version 1.0
 * @author
 */
public class NsSossMsgRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 消息记录ID,1|number|0|0|1 */
	@ApiModelProperty(value = "消息记录ID,1|number|0|0|1")
	private Long id;
	
	/** 消息ID */
	@ApiModelProperty(value = "消息ID")
	private Long msgId;
	
	/** 企业id */
	@ApiModelProperty(value = "企业id")
	private Long enterpriseId;
	
	/** 消息类型,1|select|0|200|2 */
	@ApiModelProperty(value = "消息类型,1|select|0|200|2")
	private String msgType;
	
	/** 消息 0 未读 1 已读 */
	@ApiModelProperty(value = "消息 0 未读 1 已读")
	private String isRead;
	
	/** 消息0 未删除 1 已删除 */
	@ApiModelProperty(value = "消息0 未删除 1 已删除")
	private Integer isDelete;
	
	/** 消息发送方式111...[0]站内[1]短信[2]邮箱 */
	@ApiModelProperty(value = "消息发送方式111...[0]站内[1]短信[2]邮箱")
	private String sendType;
	
	/** icon图标 */
	@ApiModelProperty(value = "icon图标")
	private String iconCode;
	
	/** 图片 */
	@ApiModelProperty(value = "图片")
	private String imageCode;
	
	/** 消息内容,1|text|0|300|3 */
	@ApiModelProperty(value = "消息内容,1|text|0|300|3")
	private String content;
	
	/** 创建时间,1|date|0|200|4 */
	@ApiModelProperty(value = "创建时间,1|date|0|200|4")
	private Date createTime;
	
		
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
	
	public Long getMsgId() {
		return msgId;
	}
	
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	public String getMsgType() {
		return msgType;
	}
	
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	
	public String getIsRead() {
		return isRead;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}
	
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	
	public String getSendType() {
		return sendType;
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
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
}
