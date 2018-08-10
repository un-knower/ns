/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能的列表资源
 * @version 1.0
 * @author
 */
public class JeCoreResourcecolumn implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 列ID */
	@ApiModelProperty(value = "列ID")
	private String jeCoreResourcecolumnId;
	
	/** 功能ID */
	@ApiModelProperty(value = "功能ID")
	private String resourcecolumnFuncinfoId;
	
	/** 列居中 */
	@ApiModelProperty(value = "列居中")
	private String resourcecolumnAlign;
	
	/** 是否为空 */
	@ApiModelProperty(value = "是否为空")
	private String resourcecolumnAllowblank;
	
	/** 是否编辑 */
	@ApiModelProperty(value = "是否编辑")
	private String resourcecolumnAllowedit;
	
	/** 选中样式 */
	@ApiModelProperty(value = "选中样式")
	private String resourcecolumnCheckedcls;
	
	/** 编码 */
	@ApiModelProperty(value = "编码")
	private String resourcecolumnCode;
	
	/** 高亮策略中的color */
	@ApiModelProperty(value = "高亮策略中的color")
	private String resourcecolumnColor1;
	
	/** 字典策略中的color */
	@ApiModelProperty(value = "字典策略中的color")
	private String resourcecolumnColor2;
	
	/** 列查询类型 */
	@ApiModelProperty(value = "列查询类型")
	private String resourcecolumnColumnquerytype;
	
	/** 列提示 */
	@ApiModelProperty(value = "列提示")
	private String resourcecolumnColumntip;
	
	/** 配置信息 */
	@ApiModelProperty(value = "配置信息")
	private String resourcecolumnConfiginfo;
	
	/** 是否拥转换器conversion */
	@ApiModelProperty(value = "是否拥转换器conversion")
	private String resourcecolumnConversion;
	
	/** 时间格式 */
	@ApiModelProperty(value = "时间格式")
	private String resourcecolumnDateformat;
	
	/** 字典编码 */
	@ApiModelProperty(value = "字典编码")
	private String resourcecolumnDdcode;
	
	/** 描述 */
	@ApiModelProperty(value = "描述")
	private String resourcecolumnDescription;
	
	/** 启用图标 */
	@ApiModelProperty(value = "启用图标")
	private String resourcecolumnEnableicon;
	
	/** 启用批量更新 */
	@ApiModelProperty(value = "启用批量更新")
	private String resourcecolumnEnableupdate;
	
	/** 列表排序字段 */
	@ApiModelProperty(value = "列表排序字段")
	private java.math.BigDecimal resourcecolumnFieldorderindex;
	
	/** 编码 */
	@ApiModelProperty(value = "编码")
	private String resourcecolumnFkcode;
	
	/** 比例 */
	@ApiModelProperty(value = "比例")
	private String resourcecolumnFlex;
	
	/** 字典策略中的color */
	@ApiModelProperty(value = "字典策略中的color")
	private String resourcecolumnFontcolor;
	
	/** 分组 */
	@ApiModelProperty(value = "分组")
	private String resourcecolumnGroup;
	
	/** 是否隐藏 */
	@ApiModelProperty(value = "是否隐藏")
	private String resourcecolumnHidden;
	
	/** 隐藏编辑提示 */
	@ApiModelProperty(value = "隐藏编辑提示")
	private String resourcecolumnHidetitlecls;
	
	/** 是否拥转换器highlighting */
	@ApiModelProperty(value = "是否拥转换器highlighting")
	private String resourcecolumnHighlighting;
	
	/** 是否超链接 */
	@ApiModelProperty(value = "是否超链接")
	private String resourcecolumnHyperlink;
	
	/** 是否导入 */
	@ApiModelProperty(value = "是否导入")
	private String resourcecolumnIfimpl;
	
	/** 是否索引 */
	@ApiModelProperty(value = "是否索引")
	private String resourcecolumnIndex;
	
	/** 是否在字典中取 */
	@ApiModelProperty(value = "是否在字典中取")
	private String resourcecolumnIsdd;
	
	/** 是否主键 */
	@ApiModelProperty(value = "是否主键")
	private String resourcecolumnIspk;
	
	/** 监听事件 */
	@ApiModelProperty(value = "监听事件")
	private String resourcecolumnJslistener;
	
	/** 关键字查询组建名称 */
	@ApiModelProperty(value = "关键字查询组建名称")
	private String resourcecolumnKeyword;
	
	/** 分步加载 */
	@ApiModelProperty(value = "分步加载")
	private String resourcecolumnLazyload;
	
	/** 链接方法 */
	@ApiModelProperty(value = "链接方法")
	private String resourcecolumnLinkmethod;
	
	/** 锁定列 */
	@ApiModelProperty(value = "锁定列")
	private String resourcecolumnLocked;
	
	/** 最大值 */
	@ApiModelProperty(value = "最大值")
	private Integer resourcecolumnMaxvalue;
	
	/** 合并单元格 */
	@ApiModelProperty(value = "合并单元格")
	private String resourcecolumnMerge;
	
	/** 最小值 */
	@ApiModelProperty(value = "最小值")
	private Integer resourcecolumnMinvalue;
	
	/** 多表头 */
	@ApiModelProperty(value = "多表头")
	private String resourcecolumnMorecolumn;
	
	/** 多表头名称 */
	@ApiModelProperty(value = "多表头名称")
	private String resourcecolumnMorecolumnname;
	
	/** 多行显示 */
	@ApiModelProperty(value = "多行显示")
	private String resourcecolumnMultirows;
	
	/** 名称 */
	@ApiModelProperty(value = "名称")
	private String resourcecolumnName;
	
	/** 英文名 */
	@ApiModelProperty(value = "英文名")
	private String resourcecolumnNameEn;
	
	/** 产品扩展功能ID */
	@ApiModelProperty(value = "产品扩展功能ID")
	private String resourcecolumnNewfuncid;
	
	/** 是否排序 */
	@ApiModelProperty(value = "是否排序")
	private String resourcecolumnOrder;
	
	/** 查询排序 */
	@ApiModelProperty(value = "查询排序")
	private Integer resourcecolumnQueryindex;
	
	/** 查询信息 */
	@ApiModelProperty(value = "查询信息")
	private String resourcecolumnQueryinfo;
	
	/** 查询类型 */
	@ApiModelProperty(value = "查询类型")
	private String resourcecolumnQuerytype;
	
	/** 是否快速查询 */
	@ApiModelProperty(value = "是否快速查询")
	private String resourcecolumnQuickquery;
	
	/** 快速查询时查询类型 */
	@ApiModelProperty(value = "快速查询时查询类型")
	private String resourcecolumnQuickquerytype;
	
	/** 右边框色 */
	@ApiModelProperty(value = "右边框色")
	private String resourcecolumnRbordercolor;
	
	/** 编辑时全选 */
	@ApiModelProperty(value = "编辑时全选")
	private String resourcecolumnSelectonfocus;
	
	/** 总统计描述 */
	@ApiModelProperty(value = "总统计描述")
	private String resourcecolumnStatistallmsg;
	
	/** 统计描述 */
	@ApiModelProperty(value = "统计描述")
	private String resourcecolumnStatisticsmsg;
	
	/** 统计类型 */
	@ApiModelProperty(value = "统计类型")
	private String resourcecolumnStatisticstype;
	
	/** 步长 */
	@ApiModelProperty(value = "步长")
	private Integer resourcecolumnStep;
	
	/** 统计数字格式 */
	@ApiModelProperty(value = "统计数字格式")
	private String resourcecolumnSummaryformat;
	
	/** 系统模式 */
	@ApiModelProperty(value = "系统模式")
	private String resourcecolumnSysmode;
	
	/** 策略 */
	@ApiModelProperty(value = "策略")
	private String resourcecolumnTactics;
	
	/** 颜色 */
	@ApiModelProperty(value = "颜色")
	private String resourcecolumnTitlecolor;
	
	/** 补选中样式 */
	@ApiModelProperty(value = "补选中样式")
	private String resourcecolumnUncheckedcls;
	
	/** 默认值 */
	@ApiModelProperty(value = "默认值")
	private String resourcecolumnValue;
	
	/** 校验信息 */
	@ApiModelProperty(value = "校验信息")
	private String resourcecolumnVtype;
	
	/** 宽度 */
	@ApiModelProperty(value = "宽度")
	private String resourcecolumnWidth;
	
	/** 英文宽度 */
	@ApiModelProperty(value = "英文宽度")
	private String resourcecolumnWidthEn;
	
	/** 类型 */
	@ApiModelProperty(value = "类型")
	private String resourcecolumnXtype;
	
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
	
	/** 拼音简写 */
	@ApiModelProperty(value = "拼音简写")
	private String syPyjz;
	
	/** 拼音全称 */
	@ApiModelProperty(value = "拼音全称")
	private String syPyqc;
	
	/** 数据状态 */
	@ApiModelProperty(value = "数据状态")
	private String syStatus;
	
	public void setJeCoreResourcecolumnId(String jeCoreResourcecolumnId) {
		this.jeCoreResourcecolumnId = jeCoreResourcecolumnId;
	}
	
	public String getJeCoreResourcecolumnId() {
		return jeCoreResourcecolumnId;
	}
	
	public void setResourcecolumnFuncinfoId(String resourcecolumnFuncinfoId) {
		this.resourcecolumnFuncinfoId = resourcecolumnFuncinfoId;
	}
	
	public String getResourcecolumnFuncinfoId() {
		return resourcecolumnFuncinfoId;
	}
	
	public void setResourcecolumnAlign(String resourcecolumnAlign) {
		this.resourcecolumnAlign = resourcecolumnAlign;
	}
	
	public String getResourcecolumnAlign() {
		return resourcecolumnAlign;
	}
	
	public void setResourcecolumnAllowblank(String resourcecolumnAllowblank) {
		this.resourcecolumnAllowblank = resourcecolumnAllowblank;
	}
	
	public String getResourcecolumnAllowblank() {
		return resourcecolumnAllowblank;
	}
	
	public void setResourcecolumnAllowedit(String resourcecolumnAllowedit) {
		this.resourcecolumnAllowedit = resourcecolumnAllowedit;
	}
	
	public String getResourcecolumnAllowedit() {
		return resourcecolumnAllowedit;
	}
	
	public void setResourcecolumnCheckedcls(String resourcecolumnCheckedcls) {
		this.resourcecolumnCheckedcls = resourcecolumnCheckedcls;
	}
	
	public String getResourcecolumnCheckedcls() {
		return resourcecolumnCheckedcls;
	}
	
	public void setResourcecolumnCode(String resourcecolumnCode) {
		this.resourcecolumnCode = resourcecolumnCode;
	}
	
	public String getResourcecolumnCode() {
		return resourcecolumnCode;
	}
	
	public void setResourcecolumnColor1(String resourcecolumnColor1) {
		this.resourcecolumnColor1 = resourcecolumnColor1;
	}
	
	public String getResourcecolumnColor1() {
		return resourcecolumnColor1;
	}
	
	public void setResourcecolumnColor2(String resourcecolumnColor2) {
		this.resourcecolumnColor2 = resourcecolumnColor2;
	}
	
	public String getResourcecolumnColor2() {
		return resourcecolumnColor2;
	}
	
	public void setResourcecolumnColumnquerytype(String resourcecolumnColumnquerytype) {
		this.resourcecolumnColumnquerytype = resourcecolumnColumnquerytype;
	}
	
	public String getResourcecolumnColumnquerytype() {
		return resourcecolumnColumnquerytype;
	}
	
	public void setResourcecolumnColumntip(String resourcecolumnColumntip) {
		this.resourcecolumnColumntip = resourcecolumnColumntip;
	}
	
	public String getResourcecolumnColumntip() {
		return resourcecolumnColumntip;
	}
	
	public void setResourcecolumnConfiginfo(String resourcecolumnConfiginfo) {
		this.resourcecolumnConfiginfo = resourcecolumnConfiginfo;
	}
	
	public String getResourcecolumnConfiginfo() {
		return resourcecolumnConfiginfo;
	}
	
	public void setResourcecolumnConversion(String resourcecolumnConversion) {
		this.resourcecolumnConversion = resourcecolumnConversion;
	}
	
	public String getResourcecolumnConversion() {
		return resourcecolumnConversion;
	}
	
	public void setResourcecolumnDateformat(String resourcecolumnDateformat) {
		this.resourcecolumnDateformat = resourcecolumnDateformat;
	}
	
	public String getResourcecolumnDateformat() {
		return resourcecolumnDateformat;
	}
	
	public void setResourcecolumnDdcode(String resourcecolumnDdcode) {
		this.resourcecolumnDdcode = resourcecolumnDdcode;
	}
	
	public String getResourcecolumnDdcode() {
		return resourcecolumnDdcode;
	}
	
	public void setResourcecolumnDescription(String resourcecolumnDescription) {
		this.resourcecolumnDescription = resourcecolumnDescription;
	}
	
	public String getResourcecolumnDescription() {
		return resourcecolumnDescription;
	}
	
	public void setResourcecolumnEnableicon(String resourcecolumnEnableicon) {
		this.resourcecolumnEnableicon = resourcecolumnEnableicon;
	}
	
	public String getResourcecolumnEnableicon() {
		return resourcecolumnEnableicon;
	}
	
	public void setResourcecolumnEnableupdate(String resourcecolumnEnableupdate) {
		this.resourcecolumnEnableupdate = resourcecolumnEnableupdate;
	}
	
	public String getResourcecolumnEnableupdate() {
		return resourcecolumnEnableupdate;
	}
	
	public void setResourcecolumnFieldorderindex(java.math.BigDecimal resourcecolumnFieldorderindex) {
		this.resourcecolumnFieldorderindex = resourcecolumnFieldorderindex;
	}
	
	public java.math.BigDecimal getResourcecolumnFieldorderindex() {
		return resourcecolumnFieldorderindex;
	}
	
	public void setResourcecolumnFkcode(String resourcecolumnFkcode) {
		this.resourcecolumnFkcode = resourcecolumnFkcode;
	}
	
	public String getResourcecolumnFkcode() {
		return resourcecolumnFkcode;
	}
	
	public void setResourcecolumnFlex(String resourcecolumnFlex) {
		this.resourcecolumnFlex = resourcecolumnFlex;
	}
	
	public String getResourcecolumnFlex() {
		return resourcecolumnFlex;
	}
	
	public void setResourcecolumnFontcolor(String resourcecolumnFontcolor) {
		this.resourcecolumnFontcolor = resourcecolumnFontcolor;
	}
	
	public String getResourcecolumnFontcolor() {
		return resourcecolumnFontcolor;
	}
	
	public void setResourcecolumnGroup(String resourcecolumnGroup) {
		this.resourcecolumnGroup = resourcecolumnGroup;
	}
	
	public String getResourcecolumnGroup() {
		return resourcecolumnGroup;
	}
	
	public void setResourcecolumnHidden(String resourcecolumnHidden) {
		this.resourcecolumnHidden = resourcecolumnHidden;
	}
	
	public String getResourcecolumnHidden() {
		return resourcecolumnHidden;
	}
	
	public void setResourcecolumnHidetitlecls(String resourcecolumnHidetitlecls) {
		this.resourcecolumnHidetitlecls = resourcecolumnHidetitlecls;
	}
	
	public String getResourcecolumnHidetitlecls() {
		return resourcecolumnHidetitlecls;
	}
	
	public void setResourcecolumnHighlighting(String resourcecolumnHighlighting) {
		this.resourcecolumnHighlighting = resourcecolumnHighlighting;
	}
	
	public String getResourcecolumnHighlighting() {
		return resourcecolumnHighlighting;
	}
	
	public void setResourcecolumnHyperlink(String resourcecolumnHyperlink) {
		this.resourcecolumnHyperlink = resourcecolumnHyperlink;
	}
	
	public String getResourcecolumnHyperlink() {
		return resourcecolumnHyperlink;
	}
	
	public void setResourcecolumnIfimpl(String resourcecolumnIfimpl) {
		this.resourcecolumnIfimpl = resourcecolumnIfimpl;
	}
	
	public String getResourcecolumnIfimpl() {
		return resourcecolumnIfimpl;
	}
	
	public void setResourcecolumnIndex(String resourcecolumnIndex) {
		this.resourcecolumnIndex = resourcecolumnIndex;
	}
	
	public String getResourcecolumnIndex() {
		return resourcecolumnIndex;
	}
	
	public void setResourcecolumnIsdd(String resourcecolumnIsdd) {
		this.resourcecolumnIsdd = resourcecolumnIsdd;
	}
	
	public String getResourcecolumnIsdd() {
		return resourcecolumnIsdd;
	}
	
	public void setResourcecolumnIspk(String resourcecolumnIspk) {
		this.resourcecolumnIspk = resourcecolumnIspk;
	}
	
	public String getResourcecolumnIspk() {
		return resourcecolumnIspk;
	}
	
	public void setResourcecolumnJslistener(String resourcecolumnJslistener) {
		this.resourcecolumnJslistener = resourcecolumnJslistener;
	}
	
	public String getResourcecolumnJslistener() {
		return resourcecolumnJslistener;
	}
	
	public void setResourcecolumnKeyword(String resourcecolumnKeyword) {
		this.resourcecolumnKeyword = resourcecolumnKeyword;
	}
	
	public String getResourcecolumnKeyword() {
		return resourcecolumnKeyword;
	}
	
	public void setResourcecolumnLazyload(String resourcecolumnLazyload) {
		this.resourcecolumnLazyload = resourcecolumnLazyload;
	}
	
	public String getResourcecolumnLazyload() {
		return resourcecolumnLazyload;
	}
	
	public void setResourcecolumnLinkmethod(String resourcecolumnLinkmethod) {
		this.resourcecolumnLinkmethod = resourcecolumnLinkmethod;
	}
	
	public String getResourcecolumnLinkmethod() {
		return resourcecolumnLinkmethod;
	}
	
	public void setResourcecolumnLocked(String resourcecolumnLocked) {
		this.resourcecolumnLocked = resourcecolumnLocked;
	}
	
	public String getResourcecolumnLocked() {
		return resourcecolumnLocked;
	}
	
	public void setResourcecolumnMaxvalue(Integer resourcecolumnMaxvalue) {
		this.resourcecolumnMaxvalue = resourcecolumnMaxvalue;
	}
	
	public Integer getResourcecolumnMaxvalue() {
		return resourcecolumnMaxvalue;
	}
	
	public void setResourcecolumnMerge(String resourcecolumnMerge) {
		this.resourcecolumnMerge = resourcecolumnMerge;
	}
	
	public String getResourcecolumnMerge() {
		return resourcecolumnMerge;
	}
	
	public void setResourcecolumnMinvalue(Integer resourcecolumnMinvalue) {
		this.resourcecolumnMinvalue = resourcecolumnMinvalue;
	}
	
	public Integer getResourcecolumnMinvalue() {
		return resourcecolumnMinvalue;
	}
	
	public void setResourcecolumnMorecolumn(String resourcecolumnMorecolumn) {
		this.resourcecolumnMorecolumn = resourcecolumnMorecolumn;
	}
	
	public String getResourcecolumnMorecolumn() {
		return resourcecolumnMorecolumn;
	}
	
	public void setResourcecolumnMorecolumnname(String resourcecolumnMorecolumnname) {
		this.resourcecolumnMorecolumnname = resourcecolumnMorecolumnname;
	}
	
	public String getResourcecolumnMorecolumnname() {
		return resourcecolumnMorecolumnname;
	}
	
	public void setResourcecolumnMultirows(String resourcecolumnMultirows) {
		this.resourcecolumnMultirows = resourcecolumnMultirows;
	}
	
	public String getResourcecolumnMultirows() {
		return resourcecolumnMultirows;
	}
	
	public void setResourcecolumnName(String resourcecolumnName) {
		this.resourcecolumnName = resourcecolumnName;
	}
	
	public String getResourcecolumnName() {
		return resourcecolumnName;
	}
	
	public void setResourcecolumnNameEn(String resourcecolumnNameEn) {
		this.resourcecolumnNameEn = resourcecolumnNameEn;
	}
	
	public String getResourcecolumnNameEn() {
		return resourcecolumnNameEn;
	}
	
	public void setResourcecolumnNewfuncid(String resourcecolumnNewfuncid) {
		this.resourcecolumnNewfuncid = resourcecolumnNewfuncid;
	}
	
	public String getResourcecolumnNewfuncid() {
		return resourcecolumnNewfuncid;
	}
	
	public void setResourcecolumnOrder(String resourcecolumnOrder) {
		this.resourcecolumnOrder = resourcecolumnOrder;
	}
	
	public String getResourcecolumnOrder() {
		return resourcecolumnOrder;
	}
	
	public void setResourcecolumnQueryindex(Integer resourcecolumnQueryindex) {
		this.resourcecolumnQueryindex = resourcecolumnQueryindex;
	}
	
	public Integer getResourcecolumnQueryindex() {
		return resourcecolumnQueryindex;
	}
	
	public void setResourcecolumnQueryinfo(String resourcecolumnQueryinfo) {
		this.resourcecolumnQueryinfo = resourcecolumnQueryinfo;
	}
	
	public String getResourcecolumnQueryinfo() {
		return resourcecolumnQueryinfo;
	}
	
	public void setResourcecolumnQuerytype(String resourcecolumnQuerytype) {
		this.resourcecolumnQuerytype = resourcecolumnQuerytype;
	}
	
	public String getResourcecolumnQuerytype() {
		return resourcecolumnQuerytype;
	}
	
	public void setResourcecolumnQuickquery(String resourcecolumnQuickquery) {
		this.resourcecolumnQuickquery = resourcecolumnQuickquery;
	}
	
	public String getResourcecolumnQuickquery() {
		return resourcecolumnQuickquery;
	}
	
	public void setResourcecolumnQuickquerytype(String resourcecolumnQuickquerytype) {
		this.resourcecolumnQuickquerytype = resourcecolumnQuickquerytype;
	}
	
	public String getResourcecolumnQuickquerytype() {
		return resourcecolumnQuickquerytype;
	}
	
	public void setResourcecolumnRbordercolor(String resourcecolumnRbordercolor) {
		this.resourcecolumnRbordercolor = resourcecolumnRbordercolor;
	}
	
	public String getResourcecolumnRbordercolor() {
		return resourcecolumnRbordercolor;
	}
	
	public void setResourcecolumnSelectonfocus(String resourcecolumnSelectonfocus) {
		this.resourcecolumnSelectonfocus = resourcecolumnSelectonfocus;
	}
	
	public String getResourcecolumnSelectonfocus() {
		return resourcecolumnSelectonfocus;
	}
	
	public void setResourcecolumnStatistallmsg(String resourcecolumnStatistallmsg) {
		this.resourcecolumnStatistallmsg = resourcecolumnStatistallmsg;
	}
	
	public String getResourcecolumnStatistallmsg() {
		return resourcecolumnStatistallmsg;
	}
	
	public void setResourcecolumnStatisticsmsg(String resourcecolumnStatisticsmsg) {
		this.resourcecolumnStatisticsmsg = resourcecolumnStatisticsmsg;
	}
	
	public String getResourcecolumnStatisticsmsg() {
		return resourcecolumnStatisticsmsg;
	}
	
	public void setResourcecolumnStatisticstype(String resourcecolumnStatisticstype) {
		this.resourcecolumnStatisticstype = resourcecolumnStatisticstype;
	}
	
	public String getResourcecolumnStatisticstype() {
		return resourcecolumnStatisticstype;
	}
	
	public void setResourcecolumnStep(Integer resourcecolumnStep) {
		this.resourcecolumnStep = resourcecolumnStep;
	}
	
	public Integer getResourcecolumnStep() {
		return resourcecolumnStep;
	}
	
	public void setResourcecolumnSummaryformat(String resourcecolumnSummaryformat) {
		this.resourcecolumnSummaryformat = resourcecolumnSummaryformat;
	}
	
	public String getResourcecolumnSummaryformat() {
		return resourcecolumnSummaryformat;
	}
	
	public void setResourcecolumnSysmode(String resourcecolumnSysmode) {
		this.resourcecolumnSysmode = resourcecolumnSysmode;
	}
	
	public String getResourcecolumnSysmode() {
		return resourcecolumnSysmode;
	}
	
	public void setResourcecolumnTactics(String resourcecolumnTactics) {
		this.resourcecolumnTactics = resourcecolumnTactics;
	}
	
	public String getResourcecolumnTactics() {
		return resourcecolumnTactics;
	}
	
	public void setResourcecolumnTitlecolor(String resourcecolumnTitlecolor) {
		this.resourcecolumnTitlecolor = resourcecolumnTitlecolor;
	}
	
	public String getResourcecolumnTitlecolor() {
		return resourcecolumnTitlecolor;
	}
	
	public void setResourcecolumnUncheckedcls(String resourcecolumnUncheckedcls) {
		this.resourcecolumnUncheckedcls = resourcecolumnUncheckedcls;
	}
	
	public String getResourcecolumnUncheckedcls() {
		return resourcecolumnUncheckedcls;
	}
	
	public void setResourcecolumnValue(String resourcecolumnValue) {
		this.resourcecolumnValue = resourcecolumnValue;
	}
	
	public String getResourcecolumnValue() {
		return resourcecolumnValue;
	}
	
	public void setResourcecolumnVtype(String resourcecolumnVtype) {
		this.resourcecolumnVtype = resourcecolumnVtype;
	}
	
	public String getResourcecolumnVtype() {
		return resourcecolumnVtype;
	}
	
	public void setResourcecolumnWidth(String resourcecolumnWidth) {
		this.resourcecolumnWidth = resourcecolumnWidth;
	}
	
	public String getResourcecolumnWidth() {
		return resourcecolumnWidth;
	}
	
	public void setResourcecolumnWidthEn(String resourcecolumnWidthEn) {
		this.resourcecolumnWidthEn = resourcecolumnWidthEn;
	}
	
	public String getResourcecolumnWidthEn() {
		return resourcecolumnWidthEn;
	}
	
	public void setResourcecolumnXtype(String resourcecolumnXtype) {
		this.resourcecolumnXtype = resourcecolumnXtype;
	}
	
	public String getResourcecolumnXtype() {
		return resourcecolumnXtype;
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
	
}
