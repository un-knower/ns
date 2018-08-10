/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 资源表
 * @version 1.0
 * @author
 */
public class NsCoreResourcetable implements Serializable {
	
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
	
	/** 资源表ID */
	@ApiModelProperty(value = "资源表ID")
	private String jeCoreResourcetableId;
	
	/** 拥有子表 */
	@ApiModelProperty(value = "拥有子表")
	private String resourcetableChildtablecodes;
	
	/** 图标样式 */
	@ApiModelProperty(value = "图标样式")
	private String resourcetableIconcls;
	
	/** 初始化流程信息 */
	@ApiModelProperty(value = "初始化流程信息")
	private String resourcetableImplwf;
	
	/** 是否已创建 */
	@ApiModelProperty(value = "是否已创建")
	private String resourcetableIscreate;
	
	/** 是否存在外键 */
	@ApiModelProperty(value = "是否存在外键")
	private String resourcetableIsuseforeignkey;
	
	/** 树形多根 */
	@ApiModelProperty(value = "树形多根")
	private String resourcetableMoreroot;
	
	/** 节点信息 */
	@ApiModelProperty(value = "节点信息")
	private String resourcetableNodeinfo;
	
	/** 节点信息类型 */
	@ApiModelProperty(value = "节点信息类型")
	private String resourcetableNodeinfotype;
	
	/** 原表名 */
	@ApiModelProperty(value = "原表名")
	private String resourcetableOldtablecode;
	
	/** 所属父表 */
	@ApiModelProperty(value = "所属父表")
	private String resourcetableParenttablecodes;
	
	/** 主键编码 */
	@ApiModelProperty(value = "主键编码")
	private String resourcetablePkcode;
	
	/** 备注 */
	@ApiModelProperty(value = "备注")
	private String resourcetableRemark;
	
	/** 视图SQL */
	@ApiModelProperty(value = "视图SQL")
	private String resourcetableSql;
	
	/** 表编码 */
	@ApiModelProperty(value = "表编码")
	private String resourcetableTablecode;
	
	/** 表名称 */
	@ApiModelProperty(value = "表名称")
	private String resourcetableTablename;
	
	/** 注解 */
	@ApiModelProperty(value = "注解")
	private String resourcetableTablenote;
	
	/** 表类型 */
	@ApiModelProperty(value = "表类型")
	private String resourcetableType;
	
	/** 使用初始化功能 */
	@ApiModelProperty(value = "使用初始化功能")
	private String resourcetableUsefunc;
	
	/** 是否禁用 */
	@ApiModelProperty(value = "是否禁用")
	private String syDisabled;
	
	/** JE核心 */
	@ApiModelProperty(value = "JE核心")
	private String syJecore;
	
	/** JE系统 */
	@ApiModelProperty(value = "JE系统")
	private String syJesys;
	
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
	
	/** 层次 */
	@ApiModelProperty(value = "层次")
	private Integer syLayer;
	
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
	
	/** 节点类型 */
	@ApiModelProperty(value = "节点类型")
	private String syNodetype;
	
	/** 排序字段 */
	@ApiModelProperty(value = "排序字段")
	private Integer syOrderindex;
	
	/** 父节点ID */
	@ApiModelProperty(value = "父节点ID")
	private String syParent;
	
	/** 父节点路径 */
	@ApiModelProperty(value = "父节点路径")
	private String syParentpath;
	
	/** 树形路径 */
	@ApiModelProperty(value = "树形路径")
	private String syPath;
	
	/** 流程定义ID */
	@ApiModelProperty(value = "流程定义ID")
	private String syPdid;
	
	/** 流程实例ID */
	@ApiModelProperty(value = "流程实例ID")
	private String syPiid;
	
	/** 数据状态 */
	@ApiModelProperty(value = "数据状态")
	private String syStatus;
	
	/** 树形排序字段 */
	@ApiModelProperty(value = "树形排序字段")
	private String syTreeorderindex;
	
		
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

    public void setJeCoreResourcetableId(String jeCoreResourcetableId) {
		this.jeCoreResourcetableId = jeCoreResourcetableId;
	}
	
	public String getJeCoreResourcetableId() {
		return jeCoreResourcetableId;
	}
	
	public void setResourcetableChildtablecodes(String resourcetableChildtablecodes) {
		this.resourcetableChildtablecodes = resourcetableChildtablecodes;
	}
	
	public String getResourcetableChildtablecodes() {
		return resourcetableChildtablecodes;
	}
	
	public void setResourcetableIconcls(String resourcetableIconcls) {
		this.resourcetableIconcls = resourcetableIconcls;
	}
	
	public String getResourcetableIconcls() {
		return resourcetableIconcls;
	}
	
	public void setResourcetableImplwf(String resourcetableImplwf) {
		this.resourcetableImplwf = resourcetableImplwf;
	}
	
	public String getResourcetableImplwf() {
		return resourcetableImplwf;
	}
	
	public void setResourcetableIscreate(String resourcetableIscreate) {
		this.resourcetableIscreate = resourcetableIscreate;
	}
	
	public String getResourcetableIscreate() {
		return resourcetableIscreate;
	}
	
	public void setResourcetableIsuseforeignkey(String resourcetableIsuseforeignkey) {
		this.resourcetableIsuseforeignkey = resourcetableIsuseforeignkey;
	}
	
	public String getResourcetableIsuseforeignkey() {
		return resourcetableIsuseforeignkey;
	}
	
	public void setResourcetableMoreroot(String resourcetableMoreroot) {
		this.resourcetableMoreroot = resourcetableMoreroot;
	}
	
	public String getResourcetableMoreroot() {
		return resourcetableMoreroot;
	}
	
	public void setResourcetableNodeinfo(String resourcetableNodeinfo) {
		this.resourcetableNodeinfo = resourcetableNodeinfo;
	}
	
	public String getResourcetableNodeinfo() {
		return resourcetableNodeinfo;
	}
	
	public void setResourcetableNodeinfotype(String resourcetableNodeinfotype) {
		this.resourcetableNodeinfotype = resourcetableNodeinfotype;
	}
	
	public String getResourcetableNodeinfotype() {
		return resourcetableNodeinfotype;
	}
	
	public void setResourcetableOldtablecode(String resourcetableOldtablecode) {
		this.resourcetableOldtablecode = resourcetableOldtablecode;
	}
	
	public String getResourcetableOldtablecode() {
		return resourcetableOldtablecode;
	}
	
	public void setResourcetableParenttablecodes(String resourcetableParenttablecodes) {
		this.resourcetableParenttablecodes = resourcetableParenttablecodes;
	}
	
	public String getResourcetableParenttablecodes() {
		return resourcetableParenttablecodes;
	}
	
	public void setResourcetablePkcode(String resourcetablePkcode) {
		this.resourcetablePkcode = resourcetablePkcode;
	}
	
	public String getResourcetablePkcode() {
		return resourcetablePkcode;
	}
	
	public void setResourcetableRemark(String resourcetableRemark) {
		this.resourcetableRemark = resourcetableRemark;
	}
	
	public String getResourcetableRemark() {
		return resourcetableRemark;
	}
	
	public void setResourcetableSql(String resourcetableSql) {
		this.resourcetableSql = resourcetableSql;
	}
	
	public String getResourcetableSql() {
		return resourcetableSql;
	}
	
	public void setResourcetableTablecode(String resourcetableTablecode) {
		this.resourcetableTablecode = resourcetableTablecode;
	}
	
	public String getResourcetableTablecode() {
		return resourcetableTablecode;
	}
	
	public void setResourcetableTablename(String resourcetableTablename) {
		this.resourcetableTablename = resourcetableTablename;
	}
	
	public String getResourcetableTablename() {
		return resourcetableTablename;
	}
	
	public void setResourcetableTablenote(String resourcetableTablenote) {
		this.resourcetableTablenote = resourcetableTablenote;
	}
	
	public String getResourcetableTablenote() {
		return resourcetableTablenote;
	}
	
	public void setResourcetableType(String resourcetableType) {
		this.resourcetableType = resourcetableType;
	}
	
	public String getResourcetableType() {
		return resourcetableType;
	}
	
	public void setResourcetableUsefunc(String resourcetableUsefunc) {
		this.resourcetableUsefunc = resourcetableUsefunc;
	}
	
	public String getResourcetableUsefunc() {
		return resourcetableUsefunc;
	}
	
	public void setSyDisabled(String syDisabled) {
		this.syDisabled = syDisabled;
	}
	
	public String getSyDisabled() {
		return syDisabled;
	}
	
	public void setSyJecore(String syJecore) {
		this.syJecore = syJecore;
	}
	
	public String getSyJecore() {
		return syJecore;
	}
	
	public void setSyJesys(String syJesys) {
		this.syJesys = syJesys;
	}
	
	public String getSyJesys() {
		return syJesys;
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
	
	public void setSyLayer(Integer syLayer) {
		this.syLayer = syLayer;
	}
	
	public Integer getSyLayer() {
		return syLayer;
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
	
	public void setSyNodetype(String syNodetype) {
		this.syNodetype = syNodetype;
	}
	
	public String getSyNodetype() {
		return syNodetype;
	}
	
	public void setSyOrderindex(Integer syOrderindex) {
		this.syOrderindex = syOrderindex;
	}
	
	public Integer getSyOrderindex() {
		return syOrderindex;
	}
	
	public void setSyParent(String syParent) {
		this.syParent = syParent;
	}
	
	public String getSyParent() {
		return syParent;
	}
	
	public void setSyParentpath(String syParentpath) {
		this.syParentpath = syParentpath;
	}
	
	public String getSyParentpath() {
		return syParentpath;
	}
	
	public void setSyPath(String syPath) {
		this.syPath = syPath;
	}
	
	public String getSyPath() {
		return syPath;
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
	
	public void setSyTreeorderindex(String syTreeorderindex) {
		this.syTreeorderindex = syTreeorderindex;
	}
	
	public String getSyTreeorderindex() {
		return syTreeorderindex;
	}
	
}
