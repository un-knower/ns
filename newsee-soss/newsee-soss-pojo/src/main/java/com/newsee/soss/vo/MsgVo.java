/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

/**
 * MsgVo
 * @version 1.0
 * @author
 */
public class MsgVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 消息ID,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "消息ID,1|number|0|0|1,1|numbertext|0||1|1")
	private Long id;
	
	/** 消息类型,1|select|0|200|2,1|select|1||2|0,0:其他|1:服务消息|2:工单消息 */
	@ApiModelProperty(value = "消息类型,1|select|0|200|2,1|select|1||2|0,0:其他|1:服务消息|2:工单消息")
	private String msgType;
	
	/** 消息备注说明,1|text|0|300|3,1|textarea|0||3|0 */
	@ApiModelProperty(value = "消息备注说明,1|text|0|300|3,1|textarea|0||3|0")
	private String remark;
	
	/** 消息开通类型111...[0]站内消息[1]短信[2]邮箱,1|text|0|300|4,1|text|0||4|0 */
	@ApiModelProperty(value = "消息开通类型111...[0]站内消息[1]短信[2]邮箱,1|text|0|300|4,1|text|0||4|0")
	private String sendType;
	//站内
	private String sendWeb;
	//短信
	private String sendSMS;
	//邮件
	private String sendEmail;		
	
	public String getSendWeb() {
		return sendWeb;
	}

	public void setSendWeb(String sendWeb) {
		this.sendWeb = sendWeb;
	}

	public String getSendSMS() {
		return sendSMS;
	}

	public void setSendSMS(String sendSMS) {
		this.sendSMS = sendSMS;
	}

	public String getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
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
		if (sendType != null && sendType.trim().length() > 2) {
			this.sendWeb = String.valueOf(sendType.charAt(0));
			this.sendSMS = String.valueOf(sendType.charAt(1));
			this.sendEmail = String.valueOf(sendType.charAt(2));
		}
	}
	
	public String getSendType() {
		return sendType;
	}
	
}
