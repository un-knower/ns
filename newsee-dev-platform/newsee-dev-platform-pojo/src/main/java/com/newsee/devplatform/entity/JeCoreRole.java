/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色表
 * @version 1.0
 * @author
 */
public class JeCoreRole implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 角色ID */
	@ApiModelProperty(value = "角色ID")
	private String roleid;
	
	/** 岗位主键 */
	@ApiModelProperty(value = "岗位主键")
	private String sentryid;
	
	/** 部门主键 */
	@ApiModelProperty(value = "部门主键")
	private String deptid;
	
	/** 开发权 */
	@ApiModelProperty(value = "开发权")
	private String develop;
	
	/** 继承权限组 */
	@ApiModelProperty(value = "继承权限组")
	private String extendgroupcode;
	
	/** 继承权限组编码 */
	@ApiModelProperty(value = "继承权限组编码")
	private String extendgroupname;
	
	/** 聚合组编码 */
	@ApiModelProperty(value = "聚合组编码")
	private String groupcode;
	
	/** 聚合组 */
	@ApiModelProperty(value = "聚合组")
	private String groupname;
	
	/** 图标 */
	@ApiModelProperty(value = "图标")
	private String icon;
	
	/** 图标样式 */
	@ApiModelProperty(value = "图标样式")
	private String iconcls;
	
	/** 是否是超级管理员 */
	@ApiModelProperty(value = "是否是超级管理员")
	private String issuperadmin;
	
	/** 管理权 */
	@ApiModelProperty(value = "管理权")
	private String manager;
	
	/** 修改人姓名 */
	@ApiModelProperty(value = "修改人姓名")
	private String modifyusername;
	
	/** 功能信息 */
	@ApiModelProperty(value = "功能信息")
	private String nodeinfo;
	
	/** 功能信息类型 */
	@ApiModelProperty(value = "功能信息类型")
	private String nodeinfotype;
	
	/** 继承编码 */
	@ApiModelProperty(value = "继承编码")
	private String parentcode;
	
	/** 继承名称 */
	@ApiModelProperty(value = "继承名称")
	private String parentname;
	
	/** 排除权限组编码 */
	@ApiModelProperty(value = "排除权限组编码")
	private String rejectgroupcode;
	
	/** 排除权限组 */
	@ApiModelProperty(value = "排除权限组")
	private String rejectgroupname;
	
	/** 角色编码 */
	@ApiModelProperty(value = "角色编码")
	private String rolecode;
	
	/** 角色名称 */
	@ApiModelProperty(value = "角色名称")
	private String rolename;
	
	/** 角色类型 */
	@ApiModelProperty(value = "角色类型")
	private String roletype;
	
	/** 审核标记 */
	@ApiModelProperty(value = "审核标记")
	private String audflag;
	
	/** 登记者所在部门编码 */
	@ApiModelProperty(value = "登记者所在部门编码")
	private String createorg;
	
	/** 登记者所在部门名称 */
	@ApiModelProperty(value = "登记者所在部门名称")
	private String createorgname;
	
	/** 登记时间 */
	@ApiModelProperty(value = "登记时间")
	private String createtime;
	
	/** 登记人编码 */
	@ApiModelProperty(value = "登记人编码")
	private String createuser;
	
	/** 登记人姓名 */
	@ApiModelProperty(value = "登记人姓名")
	private String createusername;
	
	/** 是否启用本条数据 */
	@ApiModelProperty(value = "是否启用本条数据")
	private String flag;
	
	/** 层次 */
	@ApiModelProperty(value = "层次")
	private Integer layer;
	
	/** 修改人部门编码 */
	@ApiModelProperty(value = "修改人部门编码")
	private String modifyorg;
	
	/** 修改人部门名称 */
	@ApiModelProperty(value = "修改人部门名称")
	private String modifyorgname;
	
	/** 修改时间 */
	@ApiModelProperty(value = "修改时间")
	private String modifytime;
	
	/** 修改人编码 */
	@ApiModelProperty(value = "修改人编码")
	private String modifyuser;
	
	/** 节点类型 */
	@ApiModelProperty(value = "节点类型")
	private String nodetype;
	
	/** 排序字段 */
	@ApiModelProperty(value = "排序字段")
	private Integer orderindex;
	
	/** 父节点 */
	@ApiModelProperty(value = "父节点")
	private String parent;
	
	/** 父节点路径 */
	@ApiModelProperty(value = "父节点路径")
	private String parentpath;
	
	/** 树形路径 */
	@ApiModelProperty(value = "树形路径")
	private String path;
	
	/** 流程定义ID */
	@ApiModelProperty(value = "流程定义ID")
	private String pdid;
	
	/** 流程实例ID */
	@ApiModelProperty(value = "流程实例ID")
	private String piid;
	
	/** 数据状态 */
	@ApiModelProperty(value = "数据状态")
	private String status;
	
	/** 树形排序字段 */
	@ApiModelProperty(value = "树形排序字段")
	private String treeorderindex;
	
	/** 集团公司ID */
	@ApiModelProperty(value = "集团公司ID")
	private String jtgsid;
	
	/** 集团公司名称 */
	@ApiModelProperty(value = "集团公司名称")
	private String jtgsmc;
	
	
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	public String getRoleid() {
		return roleid;
	}
	
	public void setSentryid(String sentryid) {
		this.sentryid = sentryid;
	}
	
	public String getSentryid() {
		return sentryid;
	}
	
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	
	public String getDeptid() {
		return deptid;
	}
	
	public void setDevelop(String develop) {
		this.develop = develop;
	}
	
	public String getDevelop() {
		return develop;
	}
	
	public void setExtendgroupcode(String extendgroupcode) {
		this.extendgroupcode = extendgroupcode;
	}
	
	public String getExtendgroupcode() {
		return extendgroupcode;
	}
	
	public void setExtendgroupname(String extendgroupname) {
		this.extendgroupname = extendgroupname;
	}
	
	public String getExtendgroupname() {
		return extendgroupname;
	}
	
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	
	public String getGroupcode() {
		return groupcode;
	}
	
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	public String getGroupname() {
		return groupname;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}
	
	public String getIconcls() {
		return iconcls;
	}
	
	public void setIssuperadmin(String issuperadmin) {
		this.issuperadmin = issuperadmin;
	}
	
	public String getIssuperadmin() {
		return issuperadmin;
	}
	
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	public String getManager() {
		return manager;
	}
	
	public void setModifyusername(String modifyusername) {
		this.modifyusername = modifyusername;
	}
	
	public String getModifyusername() {
		return modifyusername;
	}
	
	public void setNodeinfo(String nodeinfo) {
		this.nodeinfo = nodeinfo;
	}
	
	public String getNodeinfo() {
		return nodeinfo;
	}
	
	public void setNodeinfotype(String nodeinfotype) {
		this.nodeinfotype = nodeinfotype;
	}
	
	public String getNodeinfotype() {
		return nodeinfotype;
	}
	
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}
	
	public String getParentcode() {
		return parentcode;
	}
	
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	
	public String getParentname() {
		return parentname;
	}
	
	public void setRejectgroupcode(String rejectgroupcode) {
		this.rejectgroupcode = rejectgroupcode;
	}
	
	public String getRejectgroupcode() {
		return rejectgroupcode;
	}
	
	public void setRejectgroupname(String rejectgroupname) {
		this.rejectgroupname = rejectgroupname;
	}
	
	public String getRejectgroupname() {
		return rejectgroupname;
	}
	
	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
	
	public String getRolecode() {
		return rolecode;
	}
	
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	public String getRolename() {
		return rolename;
	}
	
	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}
	
	public String getRoletype() {
		return roletype;
	}
	
	public void setAudflag(String audflag) {
		this.audflag = audflag;
	}
	
	public String getAudflag() {
		return audflag;
	}
	
	public void setCreateorg(String createorg) {
		this.createorg = createorg;
	}
	
	public String getCreateorg() {
		return createorg;
	}
	
	public void setCreateorgname(String createorgname) {
		this.createorgname = createorgname;
	}
	
	public String getCreateorgname() {
		return createorgname;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getCreatetime() {
		return createtime;
	}
	
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	
	public String getCreateuser() {
		return createuser;
	}
	
	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}
	
	public String getCreateusername() {
		return createusername;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	
	public Integer getLayer() {
		return layer;
	}
	
	public void setModifyorg(String modifyorg) {
		this.modifyorg = modifyorg;
	}
	
	public String getModifyorg() {
		return modifyorg;
	}
	
	public void setModifyorgname(String modifyorgname) {
		this.modifyorgname = modifyorgname;
	}
	
	public String getModifyorgname() {
		return modifyorgname;
	}
	
	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}
	
	public String getModifytime() {
		return modifytime;
	}
	
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	
	public String getModifyuser() {
		return modifyuser;
	}
	
	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}
	
	public String getNodetype() {
		return nodetype;
	}
	
	public void setOrderindex(Integer orderindex) {
		this.orderindex = orderindex;
	}
	
	public Integer getOrderindex() {
		return orderindex;
	}
	
	public void setParent(String parent) {
		this.parent = parent;
	}
	
	public String getParent() {
		return parent;
	}
	
	public void setParentpath(String parentpath) {
		this.parentpath = parentpath;
	}
	
	public String getParentpath() {
		return parentpath;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPdid(String pdid) {
		this.pdid = pdid;
	}
	
	public String getPdid() {
		return pdid;
	}
	
	public void setPiid(String piid) {
		this.piid = piid;
	}
	
	public String getPiid() {
		return piid;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setTreeorderindex(String treeorderindex) {
		this.treeorderindex = treeorderindex;
	}
	
	public String getTreeorderindex() {
		return treeorderindex;
	}
	
	public void setJtgsid(String jtgsid) {
		this.jtgsid = jtgsid;
	}
	
	public String getJtgsid() {
		return jtgsid;
	}
	
	public void setJtgsmc(String jtgsmc) {
		this.jtgsmc = jtgsmc;
	}
	
	public String getJtgsmc() {
		return jtgsmc;
	}
	
}
