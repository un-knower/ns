/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 工单表
 * @version 1.0
 * @author
 */
public class NsSossService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** ID,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "ID,1|number|0|0|1,1|numbertext|0||1|1")
	private Long id;
	
	/** 工单编号,1|text|0|200|2, 1|text|0||2|0 */
	@ApiModelProperty(value = "工单编号,1|text|0|200|2, 1|text|0||2|0")
	private String code;
	
	/** 工单类型,1|select|0|200|3,1|select|1||3|0,0:未知|1:售前|2:功能|3:财务 */
	@ApiModelProperty(value = "工单类型,1|select|0|200|3,1|select|1||3|0,0:未知|1:售前|2:功能|3:财务")
	private String serviceType;
	
	/** 工单状态,1|select|0|200|10,1|select|1||10|1,0:待处理|1:处理中|2:已完成|3:待确认|4:待评价|5:已评价 */
	@ApiModelProperty(value = "工单状态,1|select|0|200|10,1|select|1||10|1,0:待处理|1:处理中|2:已完成|3:待确认|4:待评价|5:已评价")
	private String status;
	
	/** 工单是否删除0 否 1 是 */
	@ApiModelProperty(value = "工单是否删除0 否 1 是")
	private Integer isDelete;
	
	/** 工单内容,1|text|0|400|4,1|text|1||4|0 */
	@ApiModelProperty(value = "工单内容,1|text|0|400|4,1|text|1||4|0")
	private String content;
	
	/** 工单图片编码 */
	@ApiModelProperty(value = "工单图片编码")
	private String imageCode;
	
	/** 提交工单的企业 */
	@ApiModelProperty(value = "提交工单的企业")
	private Long enterpriseId;
	
	/** 企业名称,1|text|0|200|5,1|text|0||5|0 */
	@ApiModelProperty(value = "企业名称,1|text|0|200|5,1|text|0||5|0")
	private String enterpriseName;
	
	/** 备注 */
	@ApiModelProperty(value = "备注")
	private String remark;
	
	/** 工单提交时间,1|date|0|200|12 */
	@ApiModelProperty(value = "工单提交时间,1|date|0|200|12")
	private Date createTime;
	
	/** 工单提交人 */
	@ApiModelProperty(value = "工单提交人")
	private Long createUserId;
	
	/** 工单提交人,1|text|0|200|13 */
	@ApiModelProperty(value = "工单提交人,1|text|0|200|13")
	private String createUserName;
	
	@ApiModelProperty(value = "工单联系人邮箱")
	private String userEmail;
	
	@ApiModelProperty(value = "工单联系人手机")
	private String userPhone;
	
	@ApiModelProperty(value = "评价等级")
	private String ratingScale;
	
	private String servicePriority;
	private Date expectDate;
	private Long expectHandleUserID;
	private String expectHandleUserName;
	
	public Date getExpectDate() {
		return expectDate;
	}

	public void setExpectDate(Date expectDate) {
		this.expectDate = expectDate;
	}

	public Long getExpectHandleUserID() {
		return expectHandleUserID;
	}

	public void setExpectHandleUserID(Long expectHandleUserID) {
		this.expectHandleUserID = expectHandleUserID;
	}

	public String getExpectHandleUserName() {
		return expectHandleUserName;
	}

	public void setExpectHandleUserName(String expectHandleUserName) {
		this.expectHandleUserName = expectHandleUserName;
	}

	public String getServicePriority() {
		return servicePriority;
	}

	public void setServicePriority(String servicePriority) {
		this.servicePriority = servicePriority;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getRatingScale() {
		return ratingScale;
	}

	public void setRatingScale(String ratingScale) {
		this.ratingScale = ratingScale;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	
	public String getImageCode() {
		return imageCode;
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
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	
	public Long getCreateUserId() {
		return createUserId;
	}
	
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	public String getCreateUserName() {
		return createUserName;
	}
	
}
