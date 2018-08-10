/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.newsee.common.vo.FileVo;
import com.newsee.soss.entity.NsSossServiceRecord;

import io.swagger.annotations.ApiModelProperty;

/**
 * ServiceVo
 * @version 1.0
 * @author
 */
public class ServiceVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** ID,1|number|0|0|1,1|numbertext|0||1|1 */
	@ApiModelProperty(value = "ID,1|number|0|0|1,1|numbertext|0||1|1")
	private Long id;
	
	/** 工单类型,1|select|0|200|3,1|select|1||3|0,0:未知|1:售前|2:功能|3:财务 */
	@ApiModelProperty(value = "工单类型,1|select|0|200|3,1|select|1||3|0,0:未知|1:售前|2:功能|3:财务")
	private String serviceType;
	
	/** 工单状态,1|select|0|200|10,1|select|1||10|1,0:待处理|1:处理中|2:已完成|3:待确认|4:待评价|5:已评价 */
	@ApiModelProperty(value = "工单状态,1|select|0|200|10,1|select|1||10|1,0:待处理|1:处理中|2:已完成|3:待确认|4:待评价|5:已评价")
	private String status;
	
	/** 工单内容,1|text|0|400|4,1|text|1||4|0 */
	@ApiModelProperty(value = "工单内容,1|text|0|400|4,1|text|1||4|0")
	private String content;
	
	/** 企业名称,1|text|0|200|5,1|text|0||5|0 */
	@ApiModelProperty(value = "企业名称,1|text|0|200|5,1|text|0||5|0")
	private String enterpriseName;
	private Long enterpriseId;
	private String code;
	/** 工单图片编码 */
	@ApiModelProperty(value = "工单图片编码")
	private String imageCode;
	private String imageUrl;
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
	
	private String handleUserPhone;
	
	private List<NsSossServiceRecord> serviceRecordList;
	
	private String statusName;
	private Integer userType;
	private String servicePriority;
	private Date expectDate;
	private Long expectHandleUserID;
	private String expectHandleUserName;
	
	/**之前状态*/
	private String oldStatus;
	
	@ApiModelProperty(value = "工单联系人邮箱")
	private String userEmail;
	
	@ApiModelProperty(value = "工单联系人手机")
	private String userPhone;
	
	@ApiModelProperty(value = "评价等级")
	private String ratingScale;
	//全部
	private Integer serviceTotal = 0;
//	待受理
	private Integer serviceWait = 0;
//	待确认
	private Integer serviceConfirm = 0;
//	待评价
	private Integer serviceEvalu = 0;


	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getServiceTotal() {
		return serviceTotal;
	}

	public void setServiceTotal(Integer serviceTotal) {
		this.serviceTotal = serviceTotal;
	}

	public Integer getServiceWait() {
		return serviceWait;
	}

	public void setServiceWait(Integer serviceWait) {
		this.serviceWait = serviceWait;
	}

	public Integer getServiceConfirm() {
		return serviceConfirm;
	}

	public void setServiceConfirm(Integer serviceConfirm) {
		this.serviceConfirm = serviceConfirm;
	}

	public Integer getServiceEvalu() {
		return serviceEvalu;
	}

	public void setServiceEvalu(Integer serviceEvalu) {
		this.serviceEvalu = serviceEvalu;
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

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getHandleUserPhone() {
		return handleUserPhone;
	}

	public void setHandleUserPhone(String handleUserPhone) {
		this.handleUserPhone = handleUserPhone;
	}

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public List<NsSossServiceRecord> getServiceRecordList() {
		return serviceRecordList;
	}

	public void setServiceRecordList(List<NsSossServiceRecord> serviceRecordList) {
		this.serviceRecordList = serviceRecordList;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
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
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
	public String getEnterpriseName() {
		return enterpriseName;
	}
	
}
