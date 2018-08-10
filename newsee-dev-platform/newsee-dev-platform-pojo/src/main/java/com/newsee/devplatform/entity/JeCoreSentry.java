/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 岗位表
 * @version 1.0
 * @author
 */
public class JeCoreSentry implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 岗位ID */
	@ApiModelProperty(value = "岗位ID")
	private String sentryid;
	
	/** 集团公司代码 */
	@ApiModelProperty(value = "集团公司代码")
	private String jtgsdm;
	
	/** 集团公司主键 */
	@ApiModelProperty(value = "集团公司主键")
	private String jtgsid;
	
	/** 集团公司名称 */
	@ApiModelProperty(value = "集团公司名称")
	private String jtgsmc;
	
	/** 集团监管公司编码 */
	@ApiModelProperty(value = "集团监管公司编码")
	private String jtjggscode;
	
	/** 集团监管公司主键 */
	@ApiModelProperty(value = "集团监管公司主键")
	private String jtjggsid;
	
	/** 集团监管公司名称 */
	@ApiModelProperty(value = "集团监管公司名称")
	private String jtjggsname;
	
	/** 主要职责 */
	@ApiModelProperty(value = "主要职责")
	private String mainduty;
	
	/** 功能信息 */
	@ApiModelProperty(value = "功能信息")
	private String nodeinfo;
	
	/** 功能信息类型 */
	@ApiModelProperty(value = "功能信息类型")
	private String nodeinfotype;
	
	/** 排序字段 */
	@ApiModelProperty(value = "排序字段")
	private Integer orderindex;
	
	/** 岗位编码 */
	@ApiModelProperty(value = "岗位编码")
	private String sentrycode;
	
	/** 岗位名称 */
	@ApiModelProperty(value = "岗位名称")
	private String sentryname;
	
	/** 岗位参数值表名 */
	@ApiModelProperty(value = "岗位参数值表名")
	private String tablecode;
	
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
	
	/** 修改人姓名 */
	@ApiModelProperty(value = "修改人姓名")
	private String modifyusername;
	
	/** 节点类型 */
	@ApiModelProperty(value = "节点类型")
	private String nodetype;
	
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
	
	public void setSentryid(String sentryid) {
		this.sentryid = sentryid;
	}
	
	public String getSentryid() {
		return sentryid;
	}
	
	public void setJtgsdm(String jtgsdm) {
		this.jtgsdm = jtgsdm;
	}
	
	public String getJtgsdm() {
		return jtgsdm;
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
	
	public void setJtjggscode(String jtjggscode) {
		this.jtjggscode = jtjggscode;
	}
	
	public String getJtjggscode() {
		return jtjggscode;
	}
	
	public void setJtjggsid(String jtjggsid) {
		this.jtjggsid = jtjggsid;
	}
	
	public String getJtjggsid() {
		return jtjggsid;
	}
	
	public void setJtjggsname(String jtjggsname) {
		this.jtjggsname = jtjggsname;
	}
	
	public String getJtjggsname() {
		return jtjggsname;
	}
	
	public void setMainduty(String mainduty) {
		this.mainduty = mainduty;
	}
	
	public String getMainduty() {
		return mainduty;
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
	
	public void setOrderindex(Integer orderindex) {
		this.orderindex = orderindex;
	}
	
	public Integer getOrderindex() {
		return orderindex;
	}
	
	public void setSentrycode(String sentrycode) {
		this.sentrycode = sentrycode;
	}
	
	public String getSentrycode() {
		return sentrycode;
	}
	
	public void setSentryname(String sentryname) {
		this.sentryname = sentryname;
	}
	
	public String getSentryname() {
		return sentryname;
	}
	
	public void setTablecode(String tablecode) {
		this.tablecode = tablecode;
	}
	
	public String getTablecode() {
		return tablecode;
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
	
	public void setModifyusername(String modifyusername) {
		this.modifyusername = modifyusername;
	}
	
	public String getModifyusername() {
		return modifyusername;
	}
	
	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}
	
	public String getNodetype() {
		return nodetype;
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
	
}
