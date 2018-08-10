/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 消息表
 * @version 1.0
 * @author
 */
public class NsSossMsg implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 消息ID,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "消息ID,1|number|0|0|1,1|numbertext|0||1|1")
	private Long id;
	
	/** 企业ID */
	@ApiModelProperty(value = "企业ID")
	private Long enterpriseId;
	
	/** 消息类型,1|select|0|200|2,1|select|1||2|0,0:其他|1:服务消息|2:工单消息 */
	@ApiModelProperty(value = "消息类型,1|select|0|200|2,1|select|1||2|0,0:其他|1:服务消息|2:工单消息")
	private String msgType;
	
	/**消息编码*/
	private String msgCode;
	
	/** 消息备注说明,1|text|0|300|3,1|textarea|0||3|0 */
	@ApiModelProperty(value = "消息备注说明,1|text|0|300|3,1|textarea|0||3|0")
	private String remark;
	
	/** 消息开通类型111...[0]站内消息[1]短信[2]邮箱,1|text|0|300|4,1|text|0||4|0 */
	@ApiModelProperty(value = "消息开通类型111...[0]站内消息[1]短信[2]邮箱,1|text|0|300|4,1|text|0||4|0")
	private String sendType;
	
	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	
	/** 修改时间 */
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	
	
	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

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
	
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	public String getMsgType() {
		return msgType;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	
	public String getSendType() {
		return sendType;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
}
