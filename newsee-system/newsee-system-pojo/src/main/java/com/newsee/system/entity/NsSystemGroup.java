/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 组织_集团
 * @version 1.0
 * @author
 */
public class NsSystemGroup implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 集团ID */
	@ApiModelProperty(value = "集团ID")
	private Long groupId;
	
	/** enterpriseId */
	@ApiModelProperty(value = "enterpriseId")
	private Long enterpriseId;
	
	/** 集团名称 */
	@ApiModelProperty(value = "集团名称")
	private String groupName;
	
	/** 集团简称 */
    @ApiModelProperty(value = "集团简称")
	private String groupShortName;
	
    /** 集团编码*/
    @ApiModelProperty(value = "集团编码")
	private String groupCode;
	
	/** 启用状态 ：1=未启用，2=已启用，3=已停用 */
	@ApiModelProperty(value = "启用状态 ：1=未启用，2=已启用，3=已停用")
	private Integer groupEnablestate;
	
	/** 是否删除：0=已删，1=未删 */
	@ApiModelProperty(value = "是否删除：0=已删，1=未删")
	private Integer isDeleted;
	
	/** 备注 */
	@ApiModelProperty(value = "备注")
	private String remark;
	
	/** createUserId */
	@ApiModelProperty(value = "createUserId")
	private Long createUserId;
	
	/** createUserName */
	@ApiModelProperty(value = "createUserName")
	private String createUserName;
	
	/** createTime */
	@ApiModelProperty(value = "createTime")
	private Date createTime;
	
	/** updateUserId */
	@ApiModelProperty(value = "updateUserId")
	private Long updateUserId;
	
	/** updateUserName */
	@ApiModelProperty(value = "updateUserName")
	private String updateUserName;
	
	/** updateTime */
	@ApiModelProperty(value = "updateTime")
	private Date updateTime;
	
		
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	public Long getGroupId() {
		return groupId;
	}
	
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupEnablestate(Integer groupEnablestate) {
		this.groupEnablestate = groupEnablestate;
	}
	
	public Integer getGroupEnablestate() {
		return groupEnablestate;
	}
	
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Integer getIsDeleted() {
		return isDeleted;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getRemark() {
		return remark;
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
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	public Long getUpdateUserId() {
		return updateUserId;
	}
	
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	
	public String getUpdateUserName() {
		return updateUserName;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

    public String getGroupShortName() {
        return groupShortName;
    }

    public void setGroupShortName(String groupShortName) {
        this.groupShortName = groupShortName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
	
}
