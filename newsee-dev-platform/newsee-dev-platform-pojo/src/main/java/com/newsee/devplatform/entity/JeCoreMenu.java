/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 菜单
 * @version 1.0
 * @author
 */
public class JeCoreMenu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 菜单ID */
	@ApiModelProperty(value = "菜单ID")
	private String jeCoreMenuId;
	
	/** 大按钮菜单 */
	@ApiModelProperty(value = "大按钮菜单")
	private String menuBigbutton;
	
	/** 大图标 */
	@ApiModelProperty(value = "大图标")
	private String menuBigicon;
	
	/** 大图标CLS */
	@ApiModelProperty(value = "大图标CLS")
	private String menuBigiconcls;
	
	/** 节点编码 */
	@ApiModelProperty(value = "节点编码")
	private String menuCode;
	
	/** 是否启用 */
	@ApiModelProperty(value = "是否启用")
	private String menuEnabled;
	
	/** 功能类型 */
	@ApiModelProperty(value = "功能类型")
	private String menuFunctype;
	
	/** 功能说明 */
	@ApiModelProperty(value = "功能说明")
	private String menuHelp;
	
	/** 图标 */
	@ApiModelProperty(value = "图标")
	private String menuIcon;
	
	/** 图标CLS */
	@ApiModelProperty(value = "图标CLS")
	private String menuIconcls;
	
	/** 是否桌面图标 */
	@ApiModelProperty(value = "是否桌面图标")
	private String menuIsdesktop;
	
	/** 节点名称 */
	@ApiModelProperty(value = "节点名称")
	private String menuMenuname;
	
	/** 英文标题 */
	@ApiModelProperty(value = "英文标题")
	private String menuMenusubname;
	
	/** 一级模块 */
	@ApiModelProperty(value = "一级模块")
	private String menuModulecode;
	
	/** 节点信息 */
	@ApiModelProperty(value = "节点信息")
	private String menuNodeinfo;
	
	/** 节点信息类型 */
	@ApiModelProperty(value = "节点信息类型")
	private String menuNodeinfotype;
	
	/** 是否快速启动菜单 */
	@ApiModelProperty(value = "是否快速启动菜单")
	private String menuQuickstart;
	
	/** 树形路径 */
	@ApiModelProperty(value = "树形路径")
	private String menuTreepath;
	
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
	
	/** 表单上传虚字段 */
	@ApiModelProperty(value = "表单上传虚字段")
	private String syFormuploadfiles;
	
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
	
	/** 拼音简写 */
	@ApiModelProperty(value = "拼音简写")
	private String syPyjz;
	
	/** 拼音全称 */
	@ApiModelProperty(value = "拼音全称")
	private String syPyqc;
	
	/** 数据状态 */
	@ApiModelProperty(value = "数据状态")
	private String syStatus;
	
	/** 树形排序字段 */
	@ApiModelProperty(value = "树形排序字段")
	private String syTreeorderindex;
	
	public void setJeCoreMenuId(String jeCoreMenuId) {
		this.jeCoreMenuId = jeCoreMenuId;
	}
	
	public String getJeCoreMenuId() {
		return jeCoreMenuId;
	}
	
	public void setMenuBigbutton(String menuBigbutton) {
		this.menuBigbutton = menuBigbutton;
	}
	
	public String getMenuBigbutton() {
		return menuBigbutton;
	}
	
	public void setMenuBigicon(String menuBigicon) {
		this.menuBigicon = menuBigicon;
	}
	
	public String getMenuBigicon() {
		return menuBigicon;
	}
	
	public void setMenuBigiconcls(String menuBigiconcls) {
		this.menuBigiconcls = menuBigiconcls;
	}
	
	public String getMenuBigiconcls() {
		return menuBigiconcls;
	}
	
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	public String getMenuCode() {
		return menuCode;
	}
	
	public void setMenuEnabled(String menuEnabled) {
		this.menuEnabled = menuEnabled;
	}
	
	public String getMenuEnabled() {
		return menuEnabled;
	}
	
	public void setMenuFunctype(String menuFunctype) {
		this.menuFunctype = menuFunctype;
	}
	
	public String getMenuFunctype() {
		return menuFunctype;
	}
	
	public void setMenuHelp(String menuHelp) {
		this.menuHelp = menuHelp;
	}
	
	public String getMenuHelp() {
		return menuHelp;
	}
	
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	
	public String getMenuIcon() {
		return menuIcon;
	}
	
	public void setMenuIconcls(String menuIconcls) {
		this.menuIconcls = menuIconcls;
	}
	
	public String getMenuIconcls() {
		return menuIconcls;
	}
	
	public void setMenuIsdesktop(String menuIsdesktop) {
		this.menuIsdesktop = menuIsdesktop;
	}
	
	public String getMenuIsdesktop() {
		return menuIsdesktop;
	}
	
	public void setMenuMenuname(String menuMenuname) {
		this.menuMenuname = menuMenuname;
	}
	
	public String getMenuMenuname() {
		return menuMenuname;
	}
	
	public void setMenuMenusubname(String menuMenusubname) {
		this.menuMenusubname = menuMenusubname;
	}
	
	public String getMenuMenusubname() {
		return menuMenusubname;
	}
	
	public void setMenuModulecode(String menuModulecode) {
		this.menuModulecode = menuModulecode;
	}
	
	public String getMenuModulecode() {
		return menuModulecode;
	}
	
	public void setMenuNodeinfo(String menuNodeinfo) {
		this.menuNodeinfo = menuNodeinfo;
	}
	
	public String getMenuNodeinfo() {
		return menuNodeinfo;
	}
	
	public void setMenuNodeinfotype(String menuNodeinfotype) {
		this.menuNodeinfotype = menuNodeinfotype;
	}
	
	public String getMenuNodeinfotype() {
		return menuNodeinfotype;
	}
	
	public void setMenuQuickstart(String menuQuickstart) {
		this.menuQuickstart = menuQuickstart;
	}
	
	public String getMenuQuickstart() {
		return menuQuickstart;
	}
	
	public void setMenuTreepath(String menuTreepath) {
		this.menuTreepath = menuTreepath;
	}
	
	public String getMenuTreepath() {
		return menuTreepath;
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
	
	public void setSyFormuploadfiles(String syFormuploadfiles) {
		this.syFormuploadfiles = syFormuploadfiles;
	}
	
	public String getSyFormuploadfiles() {
		return syFormuploadfiles;
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
	
	public void setSyPyjz(String syPyjz) {
		this.syPyjz = syPyjz;
	}
	
	public String getSyPyjz() {
		return syPyjz;
	}
	
	public void setSyPyqc(String syPyqc) {
		this.syPyqc = syPyqc;
	}
	
	public String getSyPyqc() {
		return syPyqc;
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
