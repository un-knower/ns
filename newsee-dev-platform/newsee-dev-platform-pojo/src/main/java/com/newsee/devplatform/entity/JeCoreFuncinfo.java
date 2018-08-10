/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能
 * @version 1.0
 * @author
 */
public class JeCoreFuncinfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 功能ID */
	@ApiModelProperty(value = "功能ID")
	private String jeCoreFuncinfoId;
	
	/** 附件保存路径 */
	@ApiModelProperty(value = "附件保存路径")
	private String funcinfoAttachmentpath;
	
	/** 是否大按钮 */
	@ApiModelProperty(value = "是否大按钮")
	private String funcinfoBigbutton;
	
	/** 书签配置信息 */
	@ApiModelProperty(value = "书签配置信息")
	private String funcinfoBookmarkconfig;
	
	/** 启用图表 */
	@ApiModelProperty(value = "启用图表")
	private String funcinfoChartsopen;
	
	/** 操作状态 */
	@ApiModelProperty(value = "操作状态")
	private String funcinfoCheckstatus;
	
	/** 操作人 */
	@ApiModelProperty(value = "操作人")
	private String funcinfoCheckuser;
	
	/** 操作人CODE */
	@ApiModelProperty(value = "操作人CODE")
	private String funcinfoCheckusercode;
	
	/** 操作人主键 */
	@ApiModelProperty(value = "操作人主键")
	private String funcinfoCheckuserid;
	
	/** 子功能多条过滤 */
	@ApiModelProperty(value = "子功能多条过滤")
	private String funcinfoChildfilter;
	
	/** 子功能分步加载数据 */
	@ApiModelProperty(value = "子功能分步加载数据")
	private String funcinfoChildrefresh;
	
	/** 子卡片横向显示 */
	@ApiModelProperty(value = "子卡片横向显示")
	private String funcinfoChildshowtype;
	
	/** 是否启用列分步加载 */
	@ApiModelProperty(value = "是否启用列分步加载")
	private String funcinfoColumnlazy;
	
	/** 功能组态 */
	@ApiModelProperty(value = "功能组态")
	private String funcinfoCombine;
	
	/** 查询策略选择模式 */
	@ApiModelProperty(value = "查询策略选择模式")
	private String funcinfoCxclselmodel;
	
	/** 数据源 */
	@ApiModelProperty(value = "数据源")
	private String funcinfoDatasource;
	
	/** 拖动排序 */
	@ApiModelProperty(value = "拖动排序")
	private String funcinfoDdorder;
	
	/** 启用表单打印按钮 */
	@ApiModelProperty(value = "启用表单打印按钮")
	private String funcinfoEnableformprint;
	
	/** 扩展实体全限定名 */
	@ApiModelProperty(value = "扩展实体全限定名")
	private String funcinfoExpandentityname;
	
	/** 扩展js文件 */
	@ApiModelProperty(value = "扩展js文件")
	private String funcinfoExpandjs;
	
	/** 扩展panel */
	@ApiModelProperty(value = "扩展panel")
	private String funcinfoExpandpanels;
	
	/** 字段边框颜色 */
	@ApiModelProperty(value = "字段边框颜色")
	private String funcinfoFieldbordercolor;
	
	/** 字段懒加载 */
	@ApiModelProperty(value = "字段懒加载")
	private String funcinfoFieldlazy;
	
	/** 表单背景色 */
	@ApiModelProperty(value = "表单背景色")
	private String funcinfoFormbgcolor;
	
	/** 表单列数 */
	@ApiModelProperty(value = "表单列数")
	private String funcinfoFormcols;
	
	/** 默认宽度 */
	@ApiModelProperty(value = "默认宽度")
	private String funcinfoFormdefwidth;
	
	/** 表单监听事件 */
	@ApiModelProperty(value = "表单监听事件")
	private String funcinfoFormjslistener;
	
	/** 表单项label宽度 */
	@ApiModelProperty(value = "表单项label宽度")
	private Integer funcinfoFormlabelwidth;
	
	/** 表单项label英文宽度 */
	@ApiModelProperty(value = "表单项label英文宽度")
	private Integer funcinfoFormlabelwidthEn;
	
	/** 表单懒加载 */
	@ApiModelProperty(value = "表单懒加载")
	private String funcinfoFormlazy;
	
	/** 表单最小宽度 */
	@ApiModelProperty(value = "表单最小宽度")
	private String funcinfoFormminwidth;
	
	/** 是否表单分页 */
	@ApiModelProperty(value = "是否表单分页")
	private String funcinfoFormpaging;
	
	/** 表单分页配置 */
	@ApiModelProperty(value = "表单分页配置")
	private String funcinfoFormpagingconfig;
	
	/** 表单标题 */
	@ApiModelProperty(value = "表单标题")
	private String funcinfoFormtitle;
	
	/** 表单标题类型 */
	@ApiModelProperty(value = "表单标题类型")
	private String funcinfoFormtitletype;
	
	/** 对应action */
	@ApiModelProperty(value = "对应action")
	private String funcinfoFuncaction;
	
	/** 功能编码 */
	@ApiModelProperty(value = "功能编码")
	private String funcinfoFunccode;
	
	/** 功能字典 */
	@ApiModelProperty(value = "功能字典")
	private String funcinfoFuncdic;
	
	/** 功能字典配置信息 */
	@ApiModelProperty(value = "功能字典配置信息")
	private String funcinfoFuncdicconfig;
	
	/** 功能名称 */
	@ApiModelProperty(value = "功能名称")
	private String funcinfoFuncname;
	
	/** 功能英文名 */
	@ApiModelProperty(value = "功能英文名")
	private String funcinfoFuncnameEn;
	
	/** 功能描述 */
	@ApiModelProperty(value = "功能描述")
	private String funcinfoFuncremark;
	
	/** 功能类型 */
	@ApiModelProperty(value = "功能类型")
	private String funcinfoFunctype;
	
	/** 列表子功能并排显示 */
	@ApiModelProperty(value = "列表子功能并排显示")
	private String funcinfoGridchildss;
	
	/** 列表监听事件 */
	@ApiModelProperty(value = "列表监听事件")
	private String funcinfoGridjslistener;
	
	/** 列表打印配置 */
	@ApiModelProperty(value = "列表打印配置")
	private String funcinfoGridprintinfo;
	
	/** 列表行模版 */
	@ApiModelProperty(value = "列表行模版")
	private String funcinfoGridrowtip;
	
	/** 列表操作说明 */
	@ApiModelProperty(value = "列表操作说明")
	private String funcinfoGridtip;
	
	/** 列表操作说明 */
	@ApiModelProperty(value = "列表操作说明")
	private String funcinfoGridtipEn;
	
	/** 分组字段 */
	@ApiModelProperty(value = "分组字段")
	private String funcinfoGroupfield;
	
	/** 分组数据模版 */
	@ApiModelProperty(value = "分组数据模版")
	private String funcinfoGroupfieldtpl;
	
	/** 高级查询展开 */
	@ApiModelProperty(value = "高级查询展开")
	private String funcinfoGroupformopen;
	
	/** 向导位置 */
	@ApiModelProperty(value = "向导位置")
	private String funcinfoGuidelocation;
	
	/** 帮助 */
	@ApiModelProperty(value = "帮助")
	private String funcinfoHelp;
	
	/** 隐藏表单工具条 */
	@ApiModelProperty(value = "隐藏表单工具条")
	private String funcinfoHideformtbar;
	
	/** 隐藏列表工具条 */
	@ApiModelProperty(value = "隐藏列表工具条")
	private String funcinfoHidegridtbar;
	
	/** 功能图标 */
	@ApiModelProperty(value = "功能图标")
	private String funcinfoIcon;
	
	/** 图标样式 */
	@ApiModelProperty(value = "图标样式")
	private String funcinfoIconcls;
	
	/** 主键编码 */
	@ApiModelProperty(value = "主键编码")
	private String funcinfoIdname;
	
	/** 数据录入方式 */
	@ApiModelProperty(value = "数据录入方式")
	private String funcinfoInserttype;
	
	/** 是否启动书签 */
	@ApiModelProperty(value = "是否启动书签")
	private String funcinfoIsbookmark;
	
	/** 功能信息是否完善 */
	@ApiModelProperty(value = "功能信息是否完善")
	private String funcinfoIscomplete;
	
	/** 是否表格树 */
	@ApiModelProperty(value = "是否表格树")
	private String funcinfoIsgridtree;
	
	/** 标签背景颜色 */
	@ApiModelProperty(value = "标签背景颜色")
	private String funcinfoLabelbgcolor;
	
	/** 列表表单 */
	@ApiModelProperty(value = "列表表单")
	private String funcinfoListform;
	
	/** LODOP打印配置 */
	@ApiModelProperty(value = "LODOP打印配置")
	private String funcinfoLodopconfig;
	
	/** LODOP打印 */
	@ApiModelProperty(value = "LODOP打印")
	private String funcinfoLodopprint;
	
	/** 全息查询是否多选 */
	@ApiModelProperty(value = "全息查询是否多选")
	private String funcinfoMultiquery;
	
	/** 列表多选 */
	@ApiModelProperty(value = "列表多选")
	private String funcinfoMultiselect;
	
	/** 产品扩展功能ID */
	@ApiModelProperty(value = "产品扩展功能ID")
	private String funcinfoNewfuncid;
	
	/** 节点信息 */
	@ApiModelProperty(value = "节点信息")
	private String funcinfoNodeinfo;
	
	/** 节点信息类型 */
	@ApiModelProperty(value = "节点信息类型")
	private String funcinfoNodeinfotype;
	
	/** 一对一卡片 */
	@ApiModelProperty(value = "一对一卡片")
	private String funcinfoOcform;
	
	/** 一对一表单 */
	@ApiModelProperty(value = "一对一表单")
	private String funcinfoOnetoform;
	
	/** 新窗口编辑卡片 */
	@ApiModelProperty(value = "新窗口编辑卡片")
	private String funcinfoOpenform;
	
	/** 排序条件 */
	@ApiModelProperty(value = "排序条件")
	private String funcinfoOrdersql;
	
	/** 排序字段描述 */
	@ApiModelProperty(value = "排序字段描述")
	private String funcinfoOrdersqlDes;
	
	/** 行数 */
	@ApiModelProperty(value = "行数")
	private Integer funcinfoPagesize;
	
	/** 权限配置内容 */
	@ApiModelProperty(value = "权限配置内容")
	private String funcinfoPermconfig;
	
	/** 主键名称 */
	@ApiModelProperty(value = "主键名称")
	private String funcinfoPkname;
	
	/** 全息查询宽度 */
	@ApiModelProperty(value = "全息查询宽度")
	private Integer funcinfoQuerywidth;
	
	/** 报表附件 */
	@ApiModelProperty(value = "报表附件")
	private String funcinfoReportfile;
	
	/** 可见部门ID */
	@ApiModelProperty(value = "可见部门ID")
	private String funcinfoSeedeptids;
	
	/** 可见部门 */
	@ApiModelProperty(value = "可见部门")
	private String funcinfoSeedeptnames;
	
	/** 可见角色ID */
	@ApiModelProperty(value = "可见角色ID")
	private String funcinfoSeeroleids;
	
	/** 可见角色 */
	@ApiModelProperty(value = "可见角色")
	private String funcinfoSeerolenames;
	
	/** 可见岗位ID */
	@ApiModelProperty(value = "可见岗位ID")
	private String funcinfoSeesentryids;
	
	/** 可见岗位 */
	@ApiModelProperty(value = "可见岗位")
	private String funcinfoSeesentrynames;
	
	/** 可见人员ID */
	@ApiModelProperty(value = "可见人员ID")
	private String funcinfoSeeuserids;
	
	/** 可见人员 */
	@ApiModelProperty(value = "可见人员")
	private String funcinfoSeeusernames;
	
	/** 子系统外键 */
	@ApiModelProperty(value = "子系统外键")
	private String funcinfoSubsystemId;
	
	/** 系统模式 */
	@ApiModelProperty(value = "系统模式")
	private String funcinfoSysmode;
	
	/** 子系统 */
	@ApiModelProperty(value = "子系统")
	private String funcinfoSystemname;
	
	/** 启用表格表单 */
	@ApiModelProperty(value = "启用表格表单")
	private String funcinfoTableform;
	
	/** 自定义表单高度 */
	@ApiModelProperty(value = "自定义表单高度")
	private String funcinfoTableformheight;
	
	/** 表单直接打印 */
	@ApiModelProperty(value = "表单直接打印")
	private String funcinfoTableformprint;
	
	/** 表格表单模版 */
	@ApiModelProperty(value = "表格表单模版")
	private String funcinfoTableformtpl;
	
	/** 自定义表单宽度 */
	@ApiModelProperty(value = "自定义表单宽度")
	private String funcinfoTableformwidth;
	
	/** 自定义表单宽度2 */
	@ApiModelProperty(value = "自定义表单宽度2")
	private String funcinfoTableformwidth2;
	
	/** 表名 */
	@ApiModelProperty(value = "表名")
	private String funcinfoTablename;
	
	/** 表格样式 */
	@ApiModelProperty(value = "表格样式")
	private String funcinfoTablestyle;
	
	/** 快速查询分步加载 */
	@ApiModelProperty(value = "快速查询分步加载")
	private String funcinfoTreerefresh;
	
	/** 快速查询操作说明 */
	@ApiModelProperty(value = "快速查询操作说明")
	private String funcinfoTreetip;
	
	/** 快速查询操作说明英文 */
	@ApiModelProperty(value = "快速查询操作说明英文")
	private String funcinfoTreetipEn;
	
	/** 启用数据留痕 */
	@ApiModelProperty(value = "启用数据留痕")
	private String funcinfoUsedatalog;
	
	/** 启用附件 */
	@ApiModelProperty(value = "启用附件")
	private String funcinfoUsefiles;
	
	/** 是否使用向导 */
	@ApiModelProperty(value = "是否使用向导")
	private String funcinfoUseguide;
	
	/** 是否挂接工作流 */
	@ApiModelProperty(value = "是否挂接工作流")
	private String funcinfoUsewf;
	
	/** 版本 */
	@ApiModelProperty(value = "版本")
	private String funcinfoVersion;
	
	/** 列表过滤条件 */
	@ApiModelProperty(value = "列表过滤条件")
	private String funcinfoWheresql;
	
	/** 过滤条件描述 */
	@ApiModelProperty(value = "过滤条件描述")
	private String funcinfoWheresqlDes;
	
	/** 弹出表单宽高 */
	@ApiModelProperty(value = "弹出表单宽高")
	private String funcinfoWinformwh;
	
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
	
	/** 列表无限滚动 */
	@ApiModelProperty(value = "列表无限滚动")
	private String funcinfoGridbuffered;
	
	/** 列表请求超时时间 */
	@ApiModelProperty(value = "列表请求超时时间")
	private Integer funcinfoGridtimeout;
	
	/** 列表行模板总是展开 */
	@ApiModelProperty(value = "列表行模板总是展开")
	private String funcinfoGridrowtipshow;
	
	/** 列表隐藏元素 */
	@ApiModelProperty(value = "列表隐藏元素")
	private String funcinfoGridhides;
	
	/** 操作表名 */
	@ApiModelProperty(value = "操作表名")
	private String funcinfoCrudtablename;
	
	/** 版本_DEV */
	@ApiModelProperty(value = "版本_DEV")
	private String funcinfoVersionDev;
	
	/** 操作人_DEV */
	@ApiModelProperty(value = "操作人_DEV")
	private String funcinfoCheckuserDev;
	
	/** 操作人主键_DEV */
	@ApiModelProperty(value = "操作人主键_DEV")
	private String funcinfoCheckuseridDev;
	
	/** 操作状态_DEV */
	@ApiModelProperty(value = "操作状态_DEV")
	private String funcinfoCheckstatusDev;
	
	/** 是否需对比 */
	@ApiModelProperty(value = "是否需对比")
	private String funcinfoSfxdb;
	
	/** 启用流程审批记录 */
	@ApiModelProperty(value = "启用流程审批记录")
	private String funcinfoUsewflog;
	
	/** 子功能高度 */
	@ApiModelProperty(value = "子功能高度")
	private String funcinfoChildheight;
	
	/** 使用关系 */
	@ApiModelProperty(value = "使用关系")
	private String funcinfoUsecjglnames;
	
	/** 启用树形列表视图 */
	@ApiModelProperty(value = "启用树形列表视图")
	private String funcinfoUsetreegrid;
	
	/** 使用关系ID */
	@ApiModelProperty(value = "使用关系ID")
	private String funcinfoUsecjglids;

	
	public void setJeCoreFuncinfoId(String jeCoreFuncinfoId) {
		this.jeCoreFuncinfoId = jeCoreFuncinfoId;
	}
	
	public String getJeCoreFuncinfoId() {
		return jeCoreFuncinfoId;
	}
	
	public void setFuncinfoAttachmentpath(String funcinfoAttachmentpath) {
		this.funcinfoAttachmentpath = funcinfoAttachmentpath;
	}
	
	public String getFuncinfoAttachmentpath() {
		return funcinfoAttachmentpath;
	}
	
	public void setFuncinfoBigbutton(String funcinfoBigbutton) {
		this.funcinfoBigbutton = funcinfoBigbutton;
	}
	
	public String getFuncinfoBigbutton() {
		return funcinfoBigbutton;
	}
	
	public void setFuncinfoBookmarkconfig(String funcinfoBookmarkconfig) {
		this.funcinfoBookmarkconfig = funcinfoBookmarkconfig;
	}
	
	public String getFuncinfoBookmarkconfig() {
		return funcinfoBookmarkconfig;
	}
	
	public void setFuncinfoChartsopen(String funcinfoChartsopen) {
		this.funcinfoChartsopen = funcinfoChartsopen;
	}
	
	public String getFuncinfoChartsopen() {
		return funcinfoChartsopen;
	}
	
	public void setFuncinfoCheckstatus(String funcinfoCheckstatus) {
		this.funcinfoCheckstatus = funcinfoCheckstatus;
	}
	
	public String getFuncinfoCheckstatus() {
		return funcinfoCheckstatus;
	}
	
	public void setFuncinfoCheckuser(String funcinfoCheckuser) {
		this.funcinfoCheckuser = funcinfoCheckuser;
	}
	
	public String getFuncinfoCheckuser() {
		return funcinfoCheckuser;
	}
	
	public void setFuncinfoCheckusercode(String funcinfoCheckusercode) {
		this.funcinfoCheckusercode = funcinfoCheckusercode;
	}
	
	public String getFuncinfoCheckusercode() {
		return funcinfoCheckusercode;
	}
	
	public void setFuncinfoCheckuserid(String funcinfoCheckuserid) {
		this.funcinfoCheckuserid = funcinfoCheckuserid;
	}
	
	public String getFuncinfoCheckuserid() {
		return funcinfoCheckuserid;
	}
	
	public void setFuncinfoChildfilter(String funcinfoChildfilter) {
		this.funcinfoChildfilter = funcinfoChildfilter;
	}
	
	public String getFuncinfoChildfilter() {
		return funcinfoChildfilter;
	}
	
	public void setFuncinfoChildrefresh(String funcinfoChildrefresh) {
		this.funcinfoChildrefresh = funcinfoChildrefresh;
	}
	
	public String getFuncinfoChildrefresh() {
		return funcinfoChildrefresh;
	}
	
	public void setFuncinfoChildshowtype(String funcinfoChildshowtype) {
		this.funcinfoChildshowtype = funcinfoChildshowtype;
	}
	
	public String getFuncinfoChildshowtype() {
		return funcinfoChildshowtype;
	}
	
	public void setFuncinfoColumnlazy(String funcinfoColumnlazy) {
		this.funcinfoColumnlazy = funcinfoColumnlazy;
	}
	
	public String getFuncinfoColumnlazy() {
		return funcinfoColumnlazy;
	}
	
	public void setFuncinfoCombine(String funcinfoCombine) {
		this.funcinfoCombine = funcinfoCombine;
	}
	
	public String getFuncinfoCombine() {
		return funcinfoCombine;
	}
	
	public void setFuncinfoCxclselmodel(String funcinfoCxclselmodel) {
		this.funcinfoCxclselmodel = funcinfoCxclselmodel;
	}
	
	public String getFuncinfoCxclselmodel() {
		return funcinfoCxclselmodel;
	}
	
	public void setFuncinfoDatasource(String funcinfoDatasource) {
		this.funcinfoDatasource = funcinfoDatasource;
	}
	
	public String getFuncinfoDatasource() {
		return funcinfoDatasource;
	}
	
	public void setFuncinfoDdorder(String funcinfoDdorder) {
		this.funcinfoDdorder = funcinfoDdorder;
	}
	
	public String getFuncinfoDdorder() {
		return funcinfoDdorder;
	}
	
	public void setFuncinfoEnableformprint(String funcinfoEnableformprint) {
		this.funcinfoEnableformprint = funcinfoEnableformprint;
	}
	
	public String getFuncinfoEnableformprint() {
		return funcinfoEnableformprint;
	}
	
	public void setFuncinfoExpandentityname(String funcinfoExpandentityname) {
		this.funcinfoExpandentityname = funcinfoExpandentityname;
	}
	
	public String getFuncinfoExpandentityname() {
		return funcinfoExpandentityname;
	}
	
	public void setFuncinfoExpandjs(String funcinfoExpandjs) {
		this.funcinfoExpandjs = funcinfoExpandjs;
	}
	
	public String getFuncinfoExpandjs() {
		return funcinfoExpandjs;
	}
	
	public void setFuncinfoExpandpanels(String funcinfoExpandpanels) {
		this.funcinfoExpandpanels = funcinfoExpandpanels;
	}
	
	public String getFuncinfoExpandpanels() {
		return funcinfoExpandpanels;
	}
	
	public void setFuncinfoFieldbordercolor(String funcinfoFieldbordercolor) {
		this.funcinfoFieldbordercolor = funcinfoFieldbordercolor;
	}
	
	public String getFuncinfoFieldbordercolor() {
		return funcinfoFieldbordercolor;
	}
	
	public void setFuncinfoFieldlazy(String funcinfoFieldlazy) {
		this.funcinfoFieldlazy = funcinfoFieldlazy;
	}
	
	public String getFuncinfoFieldlazy() {
		return funcinfoFieldlazy;
	}
	
	public void setFuncinfoFormbgcolor(String funcinfoFormbgcolor) {
		this.funcinfoFormbgcolor = funcinfoFormbgcolor;
	}
	
	public String getFuncinfoFormbgcolor() {
		return funcinfoFormbgcolor;
	}
	
	public void setFuncinfoFormcols(String funcinfoFormcols) {
		this.funcinfoFormcols = funcinfoFormcols;
	}
	
	public String getFuncinfoFormcols() {
		return funcinfoFormcols;
	}
	
	public void setFuncinfoFormdefwidth(String funcinfoFormdefwidth) {
		this.funcinfoFormdefwidth = funcinfoFormdefwidth;
	}
	
	public String getFuncinfoFormdefwidth() {
		return funcinfoFormdefwidth;
	}
	
	public void setFuncinfoFormjslistener(String funcinfoFormjslistener) {
		this.funcinfoFormjslistener = funcinfoFormjslistener;
	}
	
	public String getFuncinfoFormjslistener() {
		return funcinfoFormjslistener;
	}
	
	public void setFuncinfoFormlabelwidth(Integer funcinfoFormlabelwidth) {
		this.funcinfoFormlabelwidth = funcinfoFormlabelwidth;
	}
	
	public Integer getFuncinfoFormlabelwidth() {
		return funcinfoFormlabelwidth;
	}
	
	public void setFuncinfoFormlabelwidthEn(Integer funcinfoFormlabelwidthEn) {
		this.funcinfoFormlabelwidthEn = funcinfoFormlabelwidthEn;
	}
	
	public Integer getFuncinfoFormlabelwidthEn() {
		return funcinfoFormlabelwidthEn;
	}
	
	public void setFuncinfoFormlazy(String funcinfoFormlazy) {
		this.funcinfoFormlazy = funcinfoFormlazy;
	}
	
	public String getFuncinfoFormlazy() {
		return funcinfoFormlazy;
	}
	
	public void setFuncinfoFormminwidth(String funcinfoFormminwidth) {
		this.funcinfoFormminwidth = funcinfoFormminwidth;
	}
	
	public String getFuncinfoFormminwidth() {
		return funcinfoFormminwidth;
	}
	
	public void setFuncinfoFormpaging(String funcinfoFormpaging) {
		this.funcinfoFormpaging = funcinfoFormpaging;
	}
	
	public String getFuncinfoFormpaging() {
		return funcinfoFormpaging;
	}
	
	public void setFuncinfoFormpagingconfig(String funcinfoFormpagingconfig) {
		this.funcinfoFormpagingconfig = funcinfoFormpagingconfig;
	}
	
	public String getFuncinfoFormpagingconfig() {
		return funcinfoFormpagingconfig;
	}
	
	public void setFuncinfoFormtitle(String funcinfoFormtitle) {
		this.funcinfoFormtitle = funcinfoFormtitle;
	}
	
	public String getFuncinfoFormtitle() {
		return funcinfoFormtitle;
	}
	
	public void setFuncinfoFormtitletype(String funcinfoFormtitletype) {
		this.funcinfoFormtitletype = funcinfoFormtitletype;
	}
	
	public String getFuncinfoFormtitletype() {
		return funcinfoFormtitletype;
	}
	
	public void setFuncinfoFuncaction(String funcinfoFuncaction) {
		this.funcinfoFuncaction = funcinfoFuncaction;
	}
	
	public String getFuncinfoFuncaction() {
		return funcinfoFuncaction;
	}
	
	public void setFuncinfoFunccode(String funcinfoFunccode) {
		this.funcinfoFunccode = funcinfoFunccode;
	}
	
	public String getFuncinfoFunccode() {
		return funcinfoFunccode;
	}
	
	public void setFuncinfoFuncdic(String funcinfoFuncdic) {
		this.funcinfoFuncdic = funcinfoFuncdic;
	}
	
	public String getFuncinfoFuncdic() {
		return funcinfoFuncdic;
	}
	
	public void setFuncinfoFuncdicconfig(String funcinfoFuncdicconfig) {
		this.funcinfoFuncdicconfig = funcinfoFuncdicconfig;
	}
	
	public String getFuncinfoFuncdicconfig() {
		return funcinfoFuncdicconfig;
	}
	
	public void setFuncinfoFuncname(String funcinfoFuncname) {
		this.funcinfoFuncname = funcinfoFuncname;
	}
	
	public String getFuncinfoFuncname() {
		return funcinfoFuncname;
	}
	
	public void setFuncinfoFuncnameEn(String funcinfoFuncnameEn) {
		this.funcinfoFuncnameEn = funcinfoFuncnameEn;
	}
	
	public String getFuncinfoFuncnameEn() {
		return funcinfoFuncnameEn;
	}
	
	public void setFuncinfoFuncremark(String funcinfoFuncremark) {
		this.funcinfoFuncremark = funcinfoFuncremark;
	}
	
	public String getFuncinfoFuncremark() {
		return funcinfoFuncremark;
	}
	
	public void setFuncinfoFunctype(String funcinfoFunctype) {
		this.funcinfoFunctype = funcinfoFunctype;
	}
	
	public String getFuncinfoFunctype() {
		return funcinfoFunctype;
	}
	
	public void setFuncinfoGridchildss(String funcinfoGridchildss) {
		this.funcinfoGridchildss = funcinfoGridchildss;
	}
	
	public String getFuncinfoGridchildss() {
		return funcinfoGridchildss;
	}
	
	public void setFuncinfoGridjslistener(String funcinfoGridjslistener) {
		this.funcinfoGridjslistener = funcinfoGridjslistener;
	}
	
	public String getFuncinfoGridjslistener() {
		return funcinfoGridjslistener;
	}
	
	public void setFuncinfoGridprintinfo(String funcinfoGridprintinfo) {
		this.funcinfoGridprintinfo = funcinfoGridprintinfo;
	}
	
	public String getFuncinfoGridprintinfo() {
		return funcinfoGridprintinfo;
	}
	
	public void setFuncinfoGridrowtip(String funcinfoGridrowtip) {
		this.funcinfoGridrowtip = funcinfoGridrowtip;
	}
	
	public String getFuncinfoGridrowtip() {
		return funcinfoGridrowtip;
	}
	
	public void setFuncinfoGridtip(String funcinfoGridtip) {
		this.funcinfoGridtip = funcinfoGridtip;
	}
	
	public String getFuncinfoGridtip() {
		return funcinfoGridtip;
	}
	
	public void setFuncinfoGridtipEn(String funcinfoGridtipEn) {
		this.funcinfoGridtipEn = funcinfoGridtipEn;
	}
	
	public String getFuncinfoGridtipEn() {
		return funcinfoGridtipEn;
	}
	
	public void setFuncinfoGroupfield(String funcinfoGroupfield) {
		this.funcinfoGroupfield = funcinfoGroupfield;
	}
	
	public String getFuncinfoGroupfield() {
		return funcinfoGroupfield;
	}
	
	public void setFuncinfoGroupfieldtpl(String funcinfoGroupfieldtpl) {
		this.funcinfoGroupfieldtpl = funcinfoGroupfieldtpl;
	}
	
	public String getFuncinfoGroupfieldtpl() {
		return funcinfoGroupfieldtpl;
	}
	
	public void setFuncinfoGroupformopen(String funcinfoGroupformopen) {
		this.funcinfoGroupformopen = funcinfoGroupformopen;
	}
	
	public String getFuncinfoGroupformopen() {
		return funcinfoGroupformopen;
	}
	
	public void setFuncinfoGuidelocation(String funcinfoGuidelocation) {
		this.funcinfoGuidelocation = funcinfoGuidelocation;
	}
	
	public String getFuncinfoGuidelocation() {
		return funcinfoGuidelocation;
	}
	
	public void setFuncinfoHelp(String funcinfoHelp) {
		this.funcinfoHelp = funcinfoHelp;
	}
	
	public String getFuncinfoHelp() {
		return funcinfoHelp;
	}
	
	public void setFuncinfoHideformtbar(String funcinfoHideformtbar) {
		this.funcinfoHideformtbar = funcinfoHideformtbar;
	}
	
	public String getFuncinfoHideformtbar() {
		return funcinfoHideformtbar;
	}
	
	public void setFuncinfoHidegridtbar(String funcinfoHidegridtbar) {
		this.funcinfoHidegridtbar = funcinfoHidegridtbar;
	}
	
	public String getFuncinfoHidegridtbar() {
		return funcinfoHidegridtbar;
	}
	
	public void setFuncinfoIcon(String funcinfoIcon) {
		this.funcinfoIcon = funcinfoIcon;
	}
	
	public String getFuncinfoIcon() {
		return funcinfoIcon;
	}
	
	public void setFuncinfoIconcls(String funcinfoIconcls) {
		this.funcinfoIconcls = funcinfoIconcls;
	}
	
	public String getFuncinfoIconcls() {
		return funcinfoIconcls;
	}
	
	public void setFuncinfoIdname(String funcinfoIdname) {
		this.funcinfoIdname = funcinfoIdname;
	}
	
	public String getFuncinfoIdname() {
		return funcinfoIdname;
	}
	
	public void setFuncinfoInserttype(String funcinfoInserttype) {
		this.funcinfoInserttype = funcinfoInserttype;
	}
	
	public String getFuncinfoInserttype() {
		return funcinfoInserttype;
	}
	
	public void setFuncinfoIsbookmark(String funcinfoIsbookmark) {
		this.funcinfoIsbookmark = funcinfoIsbookmark;
	}
	
	public String getFuncinfoIsbookmark() {
		return funcinfoIsbookmark;
	}
	
	public void setFuncinfoIscomplete(String funcinfoIscomplete) {
		this.funcinfoIscomplete = funcinfoIscomplete;
	}
	
	public String getFuncinfoIscomplete() {
		return funcinfoIscomplete;
	}
	
	public void setFuncinfoIsgridtree(String funcinfoIsgridtree) {
		this.funcinfoIsgridtree = funcinfoIsgridtree;
	}
	
	public String getFuncinfoIsgridtree() {
		return funcinfoIsgridtree;
	}
	
	public void setFuncinfoLabelbgcolor(String funcinfoLabelbgcolor) {
		this.funcinfoLabelbgcolor = funcinfoLabelbgcolor;
	}
	
	public String getFuncinfoLabelbgcolor() {
		return funcinfoLabelbgcolor;
	}
	
	public void setFuncinfoListform(String funcinfoListform) {
		this.funcinfoListform = funcinfoListform;
	}
	
	public String getFuncinfoListform() {
		return funcinfoListform;
	}
	
	public void setFuncinfoLodopconfig(String funcinfoLodopconfig) {
		this.funcinfoLodopconfig = funcinfoLodopconfig;
	}
	
	public String getFuncinfoLodopconfig() {
		return funcinfoLodopconfig;
	}
	
	public void setFuncinfoLodopprint(String funcinfoLodopprint) {
		this.funcinfoLodopprint = funcinfoLodopprint;
	}
	
	public String getFuncinfoLodopprint() {
		return funcinfoLodopprint;
	}
	
	public void setFuncinfoMultiquery(String funcinfoMultiquery) {
		this.funcinfoMultiquery = funcinfoMultiquery;
	}
	
	public String getFuncinfoMultiquery() {
		return funcinfoMultiquery;
	}
	
	public void setFuncinfoMultiselect(String funcinfoMultiselect) {
		this.funcinfoMultiselect = funcinfoMultiselect;
	}
	
	public String getFuncinfoMultiselect() {
		return funcinfoMultiselect;
	}
	
	public void setFuncinfoNewfuncid(String funcinfoNewfuncid) {
		this.funcinfoNewfuncid = funcinfoNewfuncid;
	}
	
	public String getFuncinfoNewfuncid() {
		return funcinfoNewfuncid;
	}
	
	public void setFuncinfoNodeinfo(String funcinfoNodeinfo) {
		this.funcinfoNodeinfo = funcinfoNodeinfo;
	}
	
	public String getFuncinfoNodeinfo() {
		return funcinfoNodeinfo;
	}
	
	public void setFuncinfoNodeinfotype(String funcinfoNodeinfotype) {
		this.funcinfoNodeinfotype = funcinfoNodeinfotype;
	}
	
	public String getFuncinfoNodeinfotype() {
		return funcinfoNodeinfotype;
	}
	
	public void setFuncinfoOcform(String funcinfoOcform) {
		this.funcinfoOcform = funcinfoOcform;
	}
	
	public String getFuncinfoOcform() {
		return funcinfoOcform;
	}
	
	public void setFuncinfoOnetoform(String funcinfoOnetoform) {
		this.funcinfoOnetoform = funcinfoOnetoform;
	}
	
	public String getFuncinfoOnetoform() {
		return funcinfoOnetoform;
	}
	
	public void setFuncinfoOpenform(String funcinfoOpenform) {
		this.funcinfoOpenform = funcinfoOpenform;
	}
	
	public String getFuncinfoOpenform() {
		return funcinfoOpenform;
	}
	
	public void setFuncinfoOrdersql(String funcinfoOrdersql) {
		this.funcinfoOrdersql = funcinfoOrdersql;
	}
	
	public String getFuncinfoOrdersql() {
		return funcinfoOrdersql;
	}
	
	public void setFuncinfoOrdersqlDes(String funcinfoOrdersqlDes) {
		this.funcinfoOrdersqlDes = funcinfoOrdersqlDes;
	}
	
	public String getFuncinfoOrdersqlDes() {
		return funcinfoOrdersqlDes;
	}
	
	public void setFuncinfoPagesize(Integer funcinfoPagesize) {
		this.funcinfoPagesize = funcinfoPagesize;
	}
	
	public Integer getFuncinfoPagesize() {
		return funcinfoPagesize;
	}
	
	public void setFuncinfoPermconfig(String funcinfoPermconfig) {
		this.funcinfoPermconfig = funcinfoPermconfig;
	}
	
	public String getFuncinfoPermconfig() {
		return funcinfoPermconfig;
	}
	
	public void setFuncinfoPkname(String funcinfoPkname) {
		this.funcinfoPkname = funcinfoPkname;
	}
	
	public String getFuncinfoPkname() {
		return funcinfoPkname;
	}
	
	public void setFuncinfoQuerywidth(Integer funcinfoQuerywidth) {
		this.funcinfoQuerywidth = funcinfoQuerywidth;
	}
	
	public Integer getFuncinfoQuerywidth() {
		return funcinfoQuerywidth;
	}
	
	public void setFuncinfoReportfile(String funcinfoReportfile) {
		this.funcinfoReportfile = funcinfoReportfile;
	}
	
	public String getFuncinfoReportfile() {
		return funcinfoReportfile;
	}
	
	public void setFuncinfoSeedeptids(String funcinfoSeedeptids) {
		this.funcinfoSeedeptids = funcinfoSeedeptids;
	}
	
	public String getFuncinfoSeedeptids() {
		return funcinfoSeedeptids;
	}
	
	public void setFuncinfoSeedeptnames(String funcinfoSeedeptnames) {
		this.funcinfoSeedeptnames = funcinfoSeedeptnames;
	}
	
	public String getFuncinfoSeedeptnames() {
		return funcinfoSeedeptnames;
	}
	
	public void setFuncinfoSeeroleids(String funcinfoSeeroleids) {
		this.funcinfoSeeroleids = funcinfoSeeroleids;
	}
	
	public String getFuncinfoSeeroleids() {
		return funcinfoSeeroleids;
	}
	
	public void setFuncinfoSeerolenames(String funcinfoSeerolenames) {
		this.funcinfoSeerolenames = funcinfoSeerolenames;
	}
	
	public String getFuncinfoSeerolenames() {
		return funcinfoSeerolenames;
	}
	
	public void setFuncinfoSeesentryids(String funcinfoSeesentryids) {
		this.funcinfoSeesentryids = funcinfoSeesentryids;
	}
	
	public String getFuncinfoSeesentryids() {
		return funcinfoSeesentryids;
	}
	
	public void setFuncinfoSeesentrynames(String funcinfoSeesentrynames) {
		this.funcinfoSeesentrynames = funcinfoSeesentrynames;
	}
	
	public String getFuncinfoSeesentrynames() {
		return funcinfoSeesentrynames;
	}
	
	public void setFuncinfoSeeuserids(String funcinfoSeeuserids) {
		this.funcinfoSeeuserids = funcinfoSeeuserids;
	}
	
	public String getFuncinfoSeeuserids() {
		return funcinfoSeeuserids;
	}
	
	public void setFuncinfoSeeusernames(String funcinfoSeeusernames) {
		this.funcinfoSeeusernames = funcinfoSeeusernames;
	}
	
	public String getFuncinfoSeeusernames() {
		return funcinfoSeeusernames;
	}
	
	public void setFuncinfoSubsystemId(String funcinfoSubsystemId) {
		this.funcinfoSubsystemId = funcinfoSubsystemId;
	}
	
	public String getFuncinfoSubsystemId() {
		return funcinfoSubsystemId;
	}
	
	public void setFuncinfoSysmode(String funcinfoSysmode) {
		this.funcinfoSysmode = funcinfoSysmode;
	}
	
	public String getFuncinfoSysmode() {
		return funcinfoSysmode;
	}
	
	public void setFuncinfoSystemname(String funcinfoSystemname) {
		this.funcinfoSystemname = funcinfoSystemname;
	}
	
	public String getFuncinfoSystemname() {
		return funcinfoSystemname;
	}
	
	public void setFuncinfoTableform(String funcinfoTableform) {
		this.funcinfoTableform = funcinfoTableform;
	}
	
	public String getFuncinfoTableform() {
		return funcinfoTableform;
	}
	
	public void setFuncinfoTableformheight(String funcinfoTableformheight) {
		this.funcinfoTableformheight = funcinfoTableformheight;
	}
	
	public String getFuncinfoTableformheight() {
		return funcinfoTableformheight;
	}
	
	public void setFuncinfoTableformprint(String funcinfoTableformprint) {
		this.funcinfoTableformprint = funcinfoTableformprint;
	}
	
	public String getFuncinfoTableformprint() {
		return funcinfoTableformprint;
	}
	
	public void setFuncinfoTableformtpl(String funcinfoTableformtpl) {
		this.funcinfoTableformtpl = funcinfoTableformtpl;
	}
	
	public String getFuncinfoTableformtpl() {
		return funcinfoTableformtpl;
	}
	
	public void setFuncinfoTableformwidth(String funcinfoTableformwidth) {
		this.funcinfoTableformwidth = funcinfoTableformwidth;
	}
	
	public String getFuncinfoTableformwidth() {
		return funcinfoTableformwidth;
	}
	
	public void setFuncinfoTableformwidth2(String funcinfoTableformwidth2) {
		this.funcinfoTableformwidth2 = funcinfoTableformwidth2;
	}
	
	public String getFuncinfoTableformwidth2() {
		return funcinfoTableformwidth2;
	}
	
	public void setFuncinfoTablename(String funcinfoTablename) {
		this.funcinfoTablename = funcinfoTablename;
	}
	
	public String getFuncinfoTablename() {
		return funcinfoTablename;
	}
	
	public void setFuncinfoTablestyle(String funcinfoTablestyle) {
		this.funcinfoTablestyle = funcinfoTablestyle;
	}
	
	public String getFuncinfoTablestyle() {
		return funcinfoTablestyle;
	}
	
	public void setFuncinfoTreerefresh(String funcinfoTreerefresh) {
		this.funcinfoTreerefresh = funcinfoTreerefresh;
	}
	
	public String getFuncinfoTreerefresh() {
		return funcinfoTreerefresh;
	}
	
	public void setFuncinfoTreetip(String funcinfoTreetip) {
		this.funcinfoTreetip = funcinfoTreetip;
	}
	
	public String getFuncinfoTreetip() {
		return funcinfoTreetip;
	}
	
	public void setFuncinfoTreetipEn(String funcinfoTreetipEn) {
		this.funcinfoTreetipEn = funcinfoTreetipEn;
	}
	
	public String getFuncinfoTreetipEn() {
		return funcinfoTreetipEn;
	}
	
	public void setFuncinfoUsedatalog(String funcinfoUsedatalog) {
		this.funcinfoUsedatalog = funcinfoUsedatalog;
	}
	
	public String getFuncinfoUsedatalog() {
		return funcinfoUsedatalog;
	}
	
	public void setFuncinfoUsefiles(String funcinfoUsefiles) {
		this.funcinfoUsefiles = funcinfoUsefiles;
	}
	
	public String getFuncinfoUsefiles() {
		return funcinfoUsefiles;
	}
	
	public void setFuncinfoUseguide(String funcinfoUseguide) {
		this.funcinfoUseguide = funcinfoUseguide;
	}
	
	public String getFuncinfoUseguide() {
		return funcinfoUseguide;
	}
	
	public void setFuncinfoUsewf(String funcinfoUsewf) {
		this.funcinfoUsewf = funcinfoUsewf;
	}
	
	public String getFuncinfoUsewf() {
		return funcinfoUsewf;
	}
	
	public void setFuncinfoVersion(String funcinfoVersion) {
		this.funcinfoVersion = funcinfoVersion;
	}
	
	public String getFuncinfoVersion() {
		return funcinfoVersion;
	}
	
	public void setFuncinfoWheresql(String funcinfoWheresql) {
		this.funcinfoWheresql = funcinfoWheresql;
	}
	
	public String getFuncinfoWheresql() {
		return funcinfoWheresql;
	}
	
	public void setFuncinfoWheresqlDes(String funcinfoWheresqlDes) {
		this.funcinfoWheresqlDes = funcinfoWheresqlDes;
	}
	
	public String getFuncinfoWheresqlDes() {
		return funcinfoWheresqlDes;
	}
	
	public void setFuncinfoWinformwh(String funcinfoWinformwh) {
		this.funcinfoWinformwh = funcinfoWinformwh;
	}
	
	public String getFuncinfoWinformwh() {
		return funcinfoWinformwh;
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
	
	public void setFuncinfoGridbuffered(String funcinfoGridbuffered) {
		this.funcinfoGridbuffered = funcinfoGridbuffered;
	}
	
	public String getFuncinfoGridbuffered() {
		return funcinfoGridbuffered;
	}
	
	public void setFuncinfoGridtimeout(Integer funcinfoGridtimeout) {
		this.funcinfoGridtimeout = funcinfoGridtimeout;
	}
	
	public Integer getFuncinfoGridtimeout() {
		return funcinfoGridtimeout;
	}
	
	public void setFuncinfoGridrowtipshow(String funcinfoGridrowtipshow) {
		this.funcinfoGridrowtipshow = funcinfoGridrowtipshow;
	}
	
	public String getFuncinfoGridrowtipshow() {
		return funcinfoGridrowtipshow;
	}
	
	public void setFuncinfoGridhides(String funcinfoGridhides) {
		this.funcinfoGridhides = funcinfoGridhides;
	}
	
	public String getFuncinfoGridhides() {
		return funcinfoGridhides;
	}
	
	public void setFuncinfoCrudtablename(String funcinfoCrudtablename) {
		this.funcinfoCrudtablename = funcinfoCrudtablename;
	}
	
	public String getFuncinfoCrudtablename() {
		return funcinfoCrudtablename;
	}
	
	public void setFuncinfoVersionDev(String funcinfoVersionDev) {
		this.funcinfoVersionDev = funcinfoVersionDev;
	}
	
	public String getFuncinfoVersionDev() {
		return funcinfoVersionDev;
	}
	
	public void setFuncinfoCheckuserDev(String funcinfoCheckuserDev) {
		this.funcinfoCheckuserDev = funcinfoCheckuserDev;
	}
	
	public String getFuncinfoCheckuserDev() {
		return funcinfoCheckuserDev;
	}
	
	public void setFuncinfoCheckuseridDev(String funcinfoCheckuseridDev) {
		this.funcinfoCheckuseridDev = funcinfoCheckuseridDev;
	}
	
	public String getFuncinfoCheckuseridDev() {
		return funcinfoCheckuseridDev;
	}
	
	public void setFuncinfoCheckstatusDev(String funcinfoCheckstatusDev) {
		this.funcinfoCheckstatusDev = funcinfoCheckstatusDev;
	}
	
	public String getFuncinfoCheckstatusDev() {
		return funcinfoCheckstatusDev;
	}
	
	public void setFuncinfoSfxdb(String funcinfoSfxdb) {
		this.funcinfoSfxdb = funcinfoSfxdb;
	}
	
	public String getFuncinfoSfxdb() {
		return funcinfoSfxdb;
	}
	
	public void setFuncinfoUsewflog(String funcinfoUsewflog) {
		this.funcinfoUsewflog = funcinfoUsewflog;
	}
	
	public String getFuncinfoUsewflog() {
		return funcinfoUsewflog;
	}
	
	public void setFuncinfoChildheight(String funcinfoChildheight) {
		this.funcinfoChildheight = funcinfoChildheight;
	}
	
	public String getFuncinfoChildheight() {
		return funcinfoChildheight;
	}
	
	public void setFuncinfoUsecjglnames(String funcinfoUsecjglnames) {
		this.funcinfoUsecjglnames = funcinfoUsecjglnames;
	}
	
	public String getFuncinfoUsecjglnames() {
		return funcinfoUsecjglnames;
	}
	
	public void setFuncinfoUsetreegrid(String funcinfoUsetreegrid) {
		this.funcinfoUsetreegrid = funcinfoUsetreegrid;
	}
	
	public String getFuncinfoUsetreegrid() {
		return funcinfoUsetreegrid;
	}
	
	public void setFuncinfoUsecjglids(String funcinfoUsecjglids) {
		this.funcinfoUsecjglids = funcinfoUsecjglids;
	}
	
	public String getFuncinfoUsecjglids() {
		return funcinfoUsecjglids;
	}
	
}
