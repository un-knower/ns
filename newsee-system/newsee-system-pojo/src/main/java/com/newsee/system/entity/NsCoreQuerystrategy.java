/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 查询策略
 * @version 1.0
 * @author
 */
public class NsCoreQuerystrategy implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键ID */
	@ApiModelProperty(value = "主键ID")
	private Long id;
	
	/** 所属企业ID */
	@ApiModelProperty(value = "所属企业ID")
	private Long enterpriseId;
	
	/** 所属组织ID */
	@ApiModelProperty(value = "所属组织ID")
	private Long organizationId;
	
	/** 查询策略ID */
	@ApiModelProperty(value = "查询策略ID")
	private String jeCoreQuerystrategyId;
	
	/** 功能ID */
	@ApiModelProperty(value = "功能ID")
	private String querystrategyFuncinfoId;
	
	/** 默认 */
	@ApiModelProperty(value = "默认")
	private String querystrategyDef;
	
	/** 覆盖功能sql */
	@ApiModelProperty(value = "覆盖功能sql")
	private String querystrategyFggnsql;
	
	/** 方法 */
	@ApiModelProperty(value = "方法")
	private String querystrategyFn;
	
	/** 功能编码 */
	@ApiModelProperty(value = "功能编码")
	private String querystrategyFunccode;
	
	/** 名称 */
	@ApiModelProperty(value = "名称")
	private String querystrategyName;
	
	/** 英文名 */
	@ApiModelProperty(value = "英文名")
	private String querystrategyNameEn;
	
	/** SQL */
	@ApiModelProperty(value = "SQL")
	private String querystrategySql;
	
	/** 用户ID */
	@ApiModelProperty(value = "用户ID")
	private String querystrategyUserid;
	
	/** 审核标记 */
	@ApiModelProperty(value = "审核标记")
	private String syAudflag;
	
	/** 登记者所在部门编码 */
	@ApiModelProperty(value = "登记者所在部门编码")
	private String syCreateorg;
	
	/** 登记者所在部门 */
	@ApiModelProperty(value = "登记者所在部门")
	private String syCreateorgname;
	
	/** 登记时间 */
	@ApiModelProperty(value = "登记时间")
	private String syCreatetime;
	
	/** 登记人编码 */
	@ApiModelProperty(value = "登记人编码")
	private String syCreateuser;
	
	/** 登记人 */
	@ApiModelProperty(value = "登记人")
	private String syCreateusername;
	
	/** 排序字段 */
	@ApiModelProperty(value = "排序字段")
	private Integer syOrderindex;
	
	/** 流程定义ID */
	@ApiModelProperty(value = "流程定义ID")
	private String syPdid;
	
	/** 流程实例ID */
	@ApiModelProperty(value = "流程实例ID")
	private String syPiid;
	
	/** 数据状态 */
	@ApiModelProperty(value = "数据状态")
	private String syStatus;
	
		
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
	

	
	public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public void setJeCoreQuerystrategyId(String jeCoreQuerystrategyId) {
		this.jeCoreQuerystrategyId = jeCoreQuerystrategyId;
	}
	
	public String getJeCoreQuerystrategyId() {
		return jeCoreQuerystrategyId;
	}
	
	public void setQuerystrategyFuncinfoId(String querystrategyFuncinfoId) {
		this.querystrategyFuncinfoId = querystrategyFuncinfoId;
	}
	
	public String getQuerystrategyFuncinfoId() {
		return querystrategyFuncinfoId;
	}
	
	public void setQuerystrategyDef(String querystrategyDef) {
		this.querystrategyDef = querystrategyDef;
	}
	
	public String getQuerystrategyDef() {
		return querystrategyDef;
	}
	
	public void setQuerystrategyFggnsql(String querystrategyFggnsql) {
		this.querystrategyFggnsql = querystrategyFggnsql;
	}
	
	public String getQuerystrategyFggnsql() {
		return querystrategyFggnsql;
	}
	
	public void setQuerystrategyFn(String querystrategyFn) {
		this.querystrategyFn = querystrategyFn;
	}
	
	public String getQuerystrategyFn() {
		return querystrategyFn;
	}
	
	public void setQuerystrategyFunccode(String querystrategyFunccode) {
		this.querystrategyFunccode = querystrategyFunccode;
	}
	
	public String getQuerystrategyFunccode() {
		return querystrategyFunccode;
	}
	
	public void setQuerystrategyName(String querystrategyName) {
		this.querystrategyName = querystrategyName;
	}
	
	public String getQuerystrategyName() {
		return querystrategyName;
	}
	
	public void setQuerystrategyNameEn(String querystrategyNameEn) {
		this.querystrategyNameEn = querystrategyNameEn;
	}
	
	public String getQuerystrategyNameEn() {
		return querystrategyNameEn;
	}
	
	public void setQuerystrategySql(String querystrategySql) {
		this.querystrategySql = querystrategySql;
	}
	
	public String getQuerystrategySql() {
		return querystrategySql;
	}
	
	public void setQuerystrategyUserid(String querystrategyUserid) {
		this.querystrategyUserid = querystrategyUserid;
	}
	
	public String getQuerystrategyUserid() {
		return querystrategyUserid;
	}
	
	public void setSyAudflag(String syAudflag) {
		this.syAudflag = syAudflag;
	}
	
	public String getSyAudflag() {
		return syAudflag;
	}
	
	public void setSyCreateorg(String syCreateorg) {
		this.syCreateorg = syCreateorg;
	}
	
	public String getSyCreateorg() {
		return syCreateorg;
	}
	
	public void setSyCreateorgname(String syCreateorgname) {
		this.syCreateorgname = syCreateorgname;
	}
	
	public String getSyCreateorgname() {
		return syCreateorgname;
	}
	
	public void setSyCreatetime(String syCreatetime) {
		this.syCreatetime = syCreatetime;
	}
	
	public String getSyCreatetime() {
		return syCreatetime;
	}
	
	public void setSyCreateuser(String syCreateuser) {
		this.syCreateuser = syCreateuser;
	}
	
	public String getSyCreateuser() {
		return syCreateuser;
	}
	
	public void setSyCreateusername(String syCreateusername) {
		this.syCreateusername = syCreateusername;
	}
	
	public String getSyCreateusername() {
		return syCreateusername;
	}
	
	public void setSyOrderindex(Integer syOrderindex) {
		this.syOrderindex = syOrderindex;
	}
	
	public Integer getSyOrderindex() {
		return syOrderindex;
	}
	
	public void setSyPdid(String syPdid) {
		this.syPdid = syPdid;
	}
	
	public String getSyPdid() {
		return syPdid;
	}
	
	public void setSyPiid(String syPiid) {
		this.syPiid = syPiid;
	}
	
	public String getSyPiid() {
		return syPiid;
	}
	
	public void setSyStatus(String syStatus) {
		this.syStatus = syStatus;
	}
	
	public String getSyStatus() {
		return syStatus;
	}
	
}
