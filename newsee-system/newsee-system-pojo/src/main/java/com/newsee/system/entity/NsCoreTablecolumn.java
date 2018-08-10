/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 资源表-->列管理
 * @version 1.0
 * @author
 */
public class NsCoreTablecolumn implements Serializable {
	
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
	
	/** 资源表的列ID */
	@ApiModelProperty(value = "资源表的列ID")
	private String jeCoreTablecolumnId;
	
	/** 表资源ID */
	@ApiModelProperty(value = "表资源ID")
	private String tablecolumnResourcetableId;
	
	/** 子功能配置 */
	@ApiModelProperty(value = "子功能配置")
	private String tablecolumnChildconfig;
	
	/** 分类 */
	@ApiModelProperty(value = "分类")
	private String tablecolumnClassify;
	
	/** 列编码 */
	@ApiModelProperty(value = "列编码")
	private String tablecolumnCode;
	
	/** 默认数值 */
	@ApiModelProperty(value = "默认数值")
	private String tablecolumnDefaultvalue;
	
	/** 数据字典配置 */
	@ApiModelProperty(value = "数据字典配置")
	private String tablecolumnDicconfig;
	
	/** 数据字典查询字段 */
	@ApiModelProperty(value = "数据字典查询字段")
	private String tablecolumnDicqueryfield;
	
	/** 是否已创建 */
	@ApiModelProperty(value = "是否已创建")
	private String tablecolumnIscreate;
	
	/** 是否允许是空 */
	@ApiModelProperty(value = "是否允许是空")
	private String tablecolumnIsnull;
	
	/** 长度 */
	@ApiModelProperty(value = "长度")
	private String tablecolumnLength;
	
	/** 列名称 */
	@ApiModelProperty(value = "列名称")
	private String tablecolumnName;
	
	/** 英文名称 */
	@ApiModelProperty(value = "英文名称")
	private String tablecolumnNameEn;
	
	/** 注解 */
	@ApiModelProperty(value = "注解")
	private String tablecolumnNote;
	
	/** 原编码 */
	@ApiModelProperty(value = "原编码")
	private String tablecolumnOldcode;
	
	/** 原是否唯一 */
	@ApiModelProperty(value = "原是否唯一")
	private String tablecolumnOldunique;
	
	/** 查询选择配置 */
	@ApiModelProperty(value = "查询选择配置")
	private String tablecolumnQueryconfig;
	
	/** 描述 */
	@ApiModelProperty(value = "描述")
	private String tablecolumnRemark;
	
	/** 表编码 */
	@ApiModelProperty(value = "表编码")
	private String tablecolumnTablecode;
	
	/** 树形类型 */
	@ApiModelProperty(value = "树形类型")
	private String tablecolumnTreetype;
	
	/** 类型 */
	@ApiModelProperty(value = "类型")
	private String tablecolumnType;
	
	/** 是否唯一 */
	@ApiModelProperty(value = "是否唯一")
	private String tablecolumnUnique;
	
	/** 唯一约束编码 */
	@ApiModelProperty(value = "唯一约束编码")
	private String tablecolumnUniquecode;
	
	/** 视图配置 */
	@ApiModelProperty(value = "视图配置")
	private String tablecolumnViewconfig;
	
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
	
	/** 是否启用本条数据 */
	@ApiModelProperty(value = "是否启用本条数据")
	private String syFlag;
	
	/** 修改人部门编码 */
	@ApiModelProperty(value = "修改人部门编码")
	private String syModifyorg;
	
	/** 修改人部门 */
	@ApiModelProperty(value = "修改人部门")
	private String syModifyorgname;
	
	/** 修改时间 */
	@ApiModelProperty(value = "修改时间")
	private String syModifytime;
	
	/** 修改人编码 */
	@ApiModelProperty(value = "修改人编码")
	private String syModifyuser;
	
	/** 修改人 */
	@ApiModelProperty(value = "修改人")
	private String syModifyusername;
	
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

    public void setJeCoreTablecolumnId(String jeCoreTablecolumnId) {
		this.jeCoreTablecolumnId = jeCoreTablecolumnId;
	}
	
	public String getJeCoreTablecolumnId() {
		return jeCoreTablecolumnId;
	}
	
	public void setTablecolumnResourcetableId(String tablecolumnResourcetableId) {
		this.tablecolumnResourcetableId = tablecolumnResourcetableId;
	}
	
	public String getTablecolumnResourcetableId() {
		return tablecolumnResourcetableId;
	}
	
	public void setTablecolumnChildconfig(String tablecolumnChildconfig) {
		this.tablecolumnChildconfig = tablecolumnChildconfig;
	}
	
	public String getTablecolumnChildconfig() {
		return tablecolumnChildconfig;
	}
	
	public void setTablecolumnClassify(String tablecolumnClassify) {
		this.tablecolumnClassify = tablecolumnClassify;
	}
	
	public String getTablecolumnClassify() {
		return tablecolumnClassify;
	}
	
	public void setTablecolumnCode(String tablecolumnCode) {
		this.tablecolumnCode = tablecolumnCode;
	}
	
	public String getTablecolumnCode() {
		return tablecolumnCode;
	}
	
	public void setTablecolumnDefaultvalue(String tablecolumnDefaultvalue) {
		this.tablecolumnDefaultvalue = tablecolumnDefaultvalue;
	}
	
	public String getTablecolumnDefaultvalue() {
		return tablecolumnDefaultvalue;
	}
	
	public void setTablecolumnDicconfig(String tablecolumnDicconfig) {
		this.tablecolumnDicconfig = tablecolumnDicconfig;
	}
	
	public String getTablecolumnDicconfig() {
		return tablecolumnDicconfig;
	}
	
	public void setTablecolumnDicqueryfield(String tablecolumnDicqueryfield) {
		this.tablecolumnDicqueryfield = tablecolumnDicqueryfield;
	}
	
	public String getTablecolumnDicqueryfield() {
		return tablecolumnDicqueryfield;
	}
	
	public void setTablecolumnIscreate(String tablecolumnIscreate) {
		this.tablecolumnIscreate = tablecolumnIscreate;
	}
	
	public String getTablecolumnIscreate() {
		return tablecolumnIscreate;
	}
	
	public void setTablecolumnIsnull(String tablecolumnIsnull) {
		this.tablecolumnIsnull = tablecolumnIsnull;
	}
	
	public String getTablecolumnIsnull() {
		return tablecolumnIsnull;
	}
	
	public void setTablecolumnLength(String tablecolumnLength) {
		this.tablecolumnLength = tablecolumnLength;
	}
	
	public String getTablecolumnLength() {
		return tablecolumnLength;
	}
	
	public void setTablecolumnName(String tablecolumnName) {
		this.tablecolumnName = tablecolumnName;
	}
	
	public String getTablecolumnName() {
		return tablecolumnName;
	}
	
	public void setTablecolumnNameEn(String tablecolumnNameEn) {
		this.tablecolumnNameEn = tablecolumnNameEn;
	}
	
	public String getTablecolumnNameEn() {
		return tablecolumnNameEn;
	}
	
	public void setTablecolumnNote(String tablecolumnNote) {
		this.tablecolumnNote = tablecolumnNote;
	}
	
	public String getTablecolumnNote() {
		return tablecolumnNote;
	}
	
	public void setTablecolumnOldcode(String tablecolumnOldcode) {
		this.tablecolumnOldcode = tablecolumnOldcode;
	}
	
	public String getTablecolumnOldcode() {
		return tablecolumnOldcode;
	}
	
	public void setTablecolumnOldunique(String tablecolumnOldunique) {
		this.tablecolumnOldunique = tablecolumnOldunique;
	}
	
	public String getTablecolumnOldunique() {
		return tablecolumnOldunique;
	}
	
	public void setTablecolumnQueryconfig(String tablecolumnQueryconfig) {
		this.tablecolumnQueryconfig = tablecolumnQueryconfig;
	}
	
	public String getTablecolumnQueryconfig() {
		return tablecolumnQueryconfig;
	}
	
	public void setTablecolumnRemark(String tablecolumnRemark) {
		this.tablecolumnRemark = tablecolumnRemark;
	}
	
	public String getTablecolumnRemark() {
		return tablecolumnRemark;
	}
	
	public void setTablecolumnTablecode(String tablecolumnTablecode) {
		this.tablecolumnTablecode = tablecolumnTablecode;
	}
	
	public String getTablecolumnTablecode() {
		return tablecolumnTablecode;
	}
	
	public void setTablecolumnTreetype(String tablecolumnTreetype) {
		this.tablecolumnTreetype = tablecolumnTreetype;
	}
	
	public String getTablecolumnTreetype() {
		return tablecolumnTreetype;
	}
	
	public void setTablecolumnType(String tablecolumnType) {
		this.tablecolumnType = tablecolumnType;
	}
	
	public String getTablecolumnType() {
		return tablecolumnType;
	}
	
	public void setTablecolumnUnique(String tablecolumnUnique) {
		this.tablecolumnUnique = tablecolumnUnique;
	}
	
	public String getTablecolumnUnique() {
		return tablecolumnUnique;
	}
	
	public void setTablecolumnUniquecode(String tablecolumnUniquecode) {
		this.tablecolumnUniquecode = tablecolumnUniquecode;
	}
	
	public String getTablecolumnUniquecode() {
		return tablecolumnUniquecode;
	}
	
	public void setTablecolumnViewconfig(String tablecolumnViewconfig) {
		this.tablecolumnViewconfig = tablecolumnViewconfig;
	}
	
	public String getTablecolumnViewconfig() {
		return tablecolumnViewconfig;
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
	
	public void setSyFlag(String syFlag) {
		this.syFlag = syFlag;
	}
	
	public String getSyFlag() {
		return syFlag;
	}
	
	public void setSyModifyorg(String syModifyorg) {
		this.syModifyorg = syModifyorg;
	}
	
	public String getSyModifyorg() {
		return syModifyorg;
	}
	
	public void setSyModifyorgname(String syModifyorgname) {
		this.syModifyorgname = syModifyorgname;
	}
	
	public String getSyModifyorgname() {
		return syModifyorgname;
	}
	
	public void setSyModifytime(String syModifytime) {
		this.syModifytime = syModifytime;
	}
	
	public String getSyModifytime() {
		return syModifytime;
	}
	
	public void setSyModifyuser(String syModifyuser) {
		this.syModifyuser = syModifyuser;
	}
	
	public String getSyModifyuser() {
		return syModifyuser;
	}
	
	public void setSyModifyusername(String syModifyusername) {
		this.syModifyusername = syModifyusername;
	}
	
	public String getSyModifyusername() {
		return syModifyusername;
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
