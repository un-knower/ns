/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.generator.jepf;

import java.io.Serializable;

/**
 * 功能的表单资源
 * @version 1.0
 * @author
 */
public class JeCoreResourcefield implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 表单字段ID */
	private String jeCoreResourcefieldId;
	
	/** 功能ID */
	private String resourcefieldFuncinfoId;
	
	/** 是否为空 */
	private String resourcefieldAllowblank;
	
	/** 必填表达式 */
	private String resourcefieldAllowblankexp;
	
	/** 必填控制表达式方法 */
	private String resourcefieldAllowblankexpfn;
	
	/** 背景色 */
	private String resourcefieldBgcolor;
	
	/** 绑定信息 */
	private String resourcefieldBinding;
	
	/** 绑定表达式方法 */
	private String resourcefieldBindingfn;
	
	/** 编码 */
	private String resourcefieldCode;
	
	/** 列数(分组框用) */
	private Integer resourcefieldCols;
	
	/** 所占列数 */
	private Integer resourcefieldColspan;
	
	/** 配置信息 */
	private String resourcefieldConfiginfo;
	
	/** 描述 */
	private String resourcefieldDescription;
	
	/** 是否可用 */
	private String resourcefieldDisabled;
	
	/** 可选可编辑 */
	private String resourcefieldEditable;
	
	/** 输入提示 */
	private String resourcefieldEmptytext;
	
	/** 可选表达式 */
	private String resourcefieldEnableexp;
	
	/** 树形字典项可选表达式 */
	private String resourcefieldEnableexpfn;
	
	/** 可选表达式提示语 */
	private String resourcefieldEnabletip;
	
	/** 字段样式表 */
	private String resourcefieldFieldcls;
	
	/** 字段颜色 */
	private String resourcefieldFieldcolor;
	
	/** 字段懒加载 */
	private String resourcefieldFieldlazy;
	
	/** 比例 */
	private String resourcefieldFlex;
	
	/** 分组框 */
	private String resourcefieldGroupname;
	
	/** 高度 */
	private String resourcefieldHeight;
	
	/** 是否隐藏 */
	private String resourcefieldHidden;
	
	/** 历史留痕 */
	private String resourcefieldHistory;
	
	/** 是否导入 */
	private String resourcefieldIfimpl;
	
	/** 显隐控制表达式 */
	private String resourcefieldInterpreter;
	
	/** 显隐控制表达式方法 */
	private String resourcefieldInterpreterfn;
	
	/** 是否主键 */
	private String resourcefieldIspk;
	
	/** 监听事件 */
	private String resourcefieldJslistener;
	
	/** 标签颜色 */
	private String resourcefieldLabelcolor;
	
	/** 定位 */
	private String resourcefieldLocate;
	
	/** 最大值 */
	private Integer resourcefieldMaxvalue;
	
	/** 最小值 */
	private Integer resourcefieldMinvalue;
	
	/** 名称 */
	private String resourcefieldName;
	
	/** 英文名 */
	private String resourcefieldNameEn;
	
	/** 产品扩展功能ID */
	private String resourcefieldNewfuncid;
	
	/** 辅助配置信息 */
	private String resourcefieldOtherconfig;
	
	/** 是否只读 */
	private String resourcefieldReadonly;
	
	/** 只读表达式 */
	private String resourcefieldReadonlyexp;
	
	/** 只读控制表达式方法 */
	private String resourcefieldReadonlyexpfn;
	
	/** 正则表达式 */
	private String resourcefieldRegex;
	
	/** 正则提示信息 */
	private String resourcefieldRegextext;
	
	/** 禁用 */
	private String resourcefieldRemoved;
	
	/** 依附字段 */
	private String resourcefieldRowfield;
	
	/** 所占行数 */
	private Integer resourcefieldRowspan;
	
	/** 步长 */
	private Integer resourcefieldStep;
	
	/** 系统模式 */
	private String resourcefieldSysmode;
	
	/** 高级后缀 */
	private String resourcefieldUnitlisttpl;
	
	/** 字段后缀 */
	private String resourcefieldUnittpl;
	
	/** 默认值 */
	private String resourcefieldValue;
	
	/** 默认值函数 */
	private String resourcefieldValuefn;
	
	/** 校验信息 */
	private String resourcefieldVtype;
	
	/** 验证配置 */
	private String resourcefieldVtypeconfig;
	
	/** whereSql */
	private String resourcefieldWheresql;
	
	/** 宽度 */
	private String resourcefieldWidth;
	
	/** 类型 */
	private String resourcefieldXtype;
	
	/** 审核标记 */
	private String syAudflag;
	
	/** 登记者所在部门编码 */
	private String syCreateorg;
	
	/** 登记者所在部门 */
	private String syCreateorgname;
	
	/** 登记时间 */
	private String syCreatetime;
	
	/** 登记人编码 */
	private String syCreateuser;
	
	/** 登记人 */
	private String syCreateusername;
	
	/** 是否启用本条数据 */
	private String syFlag;
	
	/** 表单上传虚字段 */
	private String syFormuploadfiles;
	
	/** 修改人部门编码 */
	private String syModifyorg;
	
	/** 修改人部门 */
	private String syModifyorgname;
	
	/** 修改时间 */
	private String syModifytime;
	
	/** 修改人编码 */
	private String syModifyuser;
	
	/** 修改人 */
	private String syModifyusername;
	
	/** 排序字段 */
	private Integer syOrderindex;
	
	/** 流程定义ID */
	private String syPdid;
	
	/** 流程实例ID */
	private String syPiid;
	
	/** 拼音简写 */
	private String syPyjz;
	
	/** 拼音全称 */
	private String syPyqc;
	
	/** 数据状态 */
	private String syStatus;
	
	/** 自定义验证方法 */
	private String resourcefieldVtypefn;
	
	/** 标签位置 */
	private String resourcefieldLabelalign;
	
	public void setJeCoreResourcefieldId(String jeCoreResourcefieldId) {
		this.jeCoreResourcefieldId = jeCoreResourcefieldId;
	}
	
	public String getJeCoreResourcefieldId() {
		return jeCoreResourcefieldId;
	}
	
	public void setResourcefieldFuncinfoId(String resourcefieldFuncinfoId) {
		this.resourcefieldFuncinfoId = resourcefieldFuncinfoId;
	}
	
	public String getResourcefieldFuncinfoId() {
		return resourcefieldFuncinfoId;
	}
	
	public void setResourcefieldAllowblank(String resourcefieldAllowblank) {
		this.resourcefieldAllowblank = resourcefieldAllowblank;
	}
	
	public String getResourcefieldAllowblank() {
		return resourcefieldAllowblank;
	}
	
	public void setResourcefieldAllowblankexp(String resourcefieldAllowblankexp) {
		this.resourcefieldAllowblankexp = resourcefieldAllowblankexp;
	}
	
	public String getResourcefieldAllowblankexp() {
		return resourcefieldAllowblankexp;
	}
	
	public void setResourcefieldAllowblankexpfn(String resourcefieldAllowblankexpfn) {
		this.resourcefieldAllowblankexpfn = resourcefieldAllowblankexpfn;
	}
	
	public String getResourcefieldAllowblankexpfn() {
		return resourcefieldAllowblankexpfn;
	}
	
	public void setResourcefieldBgcolor(String resourcefieldBgcolor) {
		this.resourcefieldBgcolor = resourcefieldBgcolor;
	}
	
	public String getResourcefieldBgcolor() {
		return resourcefieldBgcolor;
	}
	
	public void setResourcefieldBinding(String resourcefieldBinding) {
		this.resourcefieldBinding = resourcefieldBinding;
	}
	
	public String getResourcefieldBinding() {
		return resourcefieldBinding;
	}
	
	public void setResourcefieldBindingfn(String resourcefieldBindingfn) {
		this.resourcefieldBindingfn = resourcefieldBindingfn;
	}
	
	public String getResourcefieldBindingfn() {
		return resourcefieldBindingfn;
	}
	
	public void setResourcefieldCode(String resourcefieldCode) {
		this.resourcefieldCode = resourcefieldCode;
	}
	
	public String getResourcefieldCode() {
		return resourcefieldCode;
	}
	
	public void setResourcefieldCols(Integer resourcefieldCols) {
		this.resourcefieldCols = resourcefieldCols;
	}
	
	public Integer getResourcefieldCols() {
		return resourcefieldCols;
	}
	
	public void setResourcefieldColspan(Integer resourcefieldColspan) {
		this.resourcefieldColspan = resourcefieldColspan;
	}
	
	public Integer getResourcefieldColspan() {
		return resourcefieldColspan;
	}
	
	public void setResourcefieldConfiginfo(String resourcefieldConfiginfo) {
		this.resourcefieldConfiginfo = resourcefieldConfiginfo;
	}
	
	public String getResourcefieldConfiginfo() {
		return resourcefieldConfiginfo;
	}
	
	public void setResourcefieldDescription(String resourcefieldDescription) {
		this.resourcefieldDescription = resourcefieldDescription;
	}
	
	public String getResourcefieldDescription() {
		return resourcefieldDescription;
	}
	
	public void setResourcefieldDisabled(String resourcefieldDisabled) {
		this.resourcefieldDisabled = resourcefieldDisabled;
	}
	
	public String getResourcefieldDisabled() {
		return resourcefieldDisabled;
	}
	
	public void setResourcefieldEditable(String resourcefieldEditable) {
		this.resourcefieldEditable = resourcefieldEditable;
	}
	
	public String getResourcefieldEditable() {
		return resourcefieldEditable;
	}
	
	public void setResourcefieldEmptytext(String resourcefieldEmptytext) {
		this.resourcefieldEmptytext = resourcefieldEmptytext;
	}
	
	public String getResourcefieldEmptytext() {
		return resourcefieldEmptytext;
	}
	
	public void setResourcefieldEnableexp(String resourcefieldEnableexp) {
		this.resourcefieldEnableexp = resourcefieldEnableexp;
	}
	
	public String getResourcefieldEnableexp() {
		return resourcefieldEnableexp;
	}
	
	public void setResourcefieldEnableexpfn(String resourcefieldEnableexpfn) {
		this.resourcefieldEnableexpfn = resourcefieldEnableexpfn;
	}
	
	public String getResourcefieldEnableexpfn() {
		return resourcefieldEnableexpfn;
	}
	
	public void setResourcefieldEnabletip(String resourcefieldEnabletip) {
		this.resourcefieldEnabletip = resourcefieldEnabletip;
	}
	
	public String getResourcefieldEnabletip() {
		return resourcefieldEnabletip;
	}
	
	public void setResourcefieldFieldcls(String resourcefieldFieldcls) {
		this.resourcefieldFieldcls = resourcefieldFieldcls;
	}
	
	public String getResourcefieldFieldcls() {
		return resourcefieldFieldcls;
	}
	
	public void setResourcefieldFieldcolor(String resourcefieldFieldcolor) {
		this.resourcefieldFieldcolor = resourcefieldFieldcolor;
	}
	
	public String getResourcefieldFieldcolor() {
		return resourcefieldFieldcolor;
	}
	
	public void setResourcefieldFieldlazy(String resourcefieldFieldlazy) {
		this.resourcefieldFieldlazy = resourcefieldFieldlazy;
	}
	
	public String getResourcefieldFieldlazy() {
		return resourcefieldFieldlazy;
	}
	
	public void setResourcefieldFlex(String resourcefieldFlex) {
		this.resourcefieldFlex = resourcefieldFlex;
	}
	
	public String getResourcefieldFlex() {
		return resourcefieldFlex;
	}
	
	public void setResourcefieldGroupname(String resourcefieldGroupname) {
		this.resourcefieldGroupname = resourcefieldGroupname;
	}
	
	public String getResourcefieldGroupname() {
		return resourcefieldGroupname;
	}
	
	public void setResourcefieldHeight(String resourcefieldHeight) {
		this.resourcefieldHeight = resourcefieldHeight;
	}
	
	public String getResourcefieldHeight() {
		return resourcefieldHeight;
	}
	
	public void setResourcefieldHidden(String resourcefieldHidden) {
		this.resourcefieldHidden = resourcefieldHidden;
	}
	
	public String getResourcefieldHidden() {
		return resourcefieldHidden;
	}
	
	public void setResourcefieldHistory(String resourcefieldHistory) {
		this.resourcefieldHistory = resourcefieldHistory;
	}
	
	public String getResourcefieldHistory() {
		return resourcefieldHistory;
	}
	
	public void setResourcefieldIfimpl(String resourcefieldIfimpl) {
		this.resourcefieldIfimpl = resourcefieldIfimpl;
	}
	
	public String getResourcefieldIfimpl() {
		return resourcefieldIfimpl;
	}
	
	public void setResourcefieldInterpreter(String resourcefieldInterpreter) {
		this.resourcefieldInterpreter = resourcefieldInterpreter;
	}
	
	public String getResourcefieldInterpreter() {
		return resourcefieldInterpreter;
	}
	
	public void setResourcefieldInterpreterfn(String resourcefieldInterpreterfn) {
		this.resourcefieldInterpreterfn = resourcefieldInterpreterfn;
	}
	
	public String getResourcefieldInterpreterfn() {
		return resourcefieldInterpreterfn;
	}
	
	public void setResourcefieldIspk(String resourcefieldIspk) {
		this.resourcefieldIspk = resourcefieldIspk;
	}
	
	public String getResourcefieldIspk() {
		return resourcefieldIspk;
	}
	
	public void setResourcefieldJslistener(String resourcefieldJslistener) {
		this.resourcefieldJslistener = resourcefieldJslistener;
	}
	
	public String getResourcefieldJslistener() {
		return resourcefieldJslistener;
	}
	
	public void setResourcefieldLabelcolor(String resourcefieldLabelcolor) {
		this.resourcefieldLabelcolor = resourcefieldLabelcolor;
	}
	
	public String getResourcefieldLabelcolor() {
		return resourcefieldLabelcolor;
	}
	
	public void setResourcefieldLocate(String resourcefieldLocate) {
		this.resourcefieldLocate = resourcefieldLocate;
	}
	
	public String getResourcefieldLocate() {
		return resourcefieldLocate;
	}
	
	public void setResourcefieldMaxvalue(Integer resourcefieldMaxvalue) {
		this.resourcefieldMaxvalue = resourcefieldMaxvalue;
	}
	
	public Integer getResourcefieldMaxvalue() {
		return resourcefieldMaxvalue;
	}
	
	public void setResourcefieldMinvalue(Integer resourcefieldMinvalue) {
		this.resourcefieldMinvalue = resourcefieldMinvalue;
	}
	
	public Integer getResourcefieldMinvalue() {
		return resourcefieldMinvalue;
	}
	
	public void setResourcefieldName(String resourcefieldName) {
		this.resourcefieldName = resourcefieldName;
	}
	
	public String getResourcefieldName() {
		return resourcefieldName;
	}
	
	public void setResourcefieldNameEn(String resourcefieldNameEn) {
		this.resourcefieldNameEn = resourcefieldNameEn;
	}
	
	public String getResourcefieldNameEn() {
		return resourcefieldNameEn;
	}
	
	public void setResourcefieldNewfuncid(String resourcefieldNewfuncid) {
		this.resourcefieldNewfuncid = resourcefieldNewfuncid;
	}
	
	public String getResourcefieldNewfuncid() {
		return resourcefieldNewfuncid;
	}
	
	public void setResourcefieldOtherconfig(String resourcefieldOtherconfig) {
		this.resourcefieldOtherconfig = resourcefieldOtherconfig;
	}
	
	public String getResourcefieldOtherconfig() {
		return resourcefieldOtherconfig;
	}
	
	public void setResourcefieldReadonly(String resourcefieldReadonly) {
		this.resourcefieldReadonly = resourcefieldReadonly;
	}
	
	public String getResourcefieldReadonly() {
		return resourcefieldReadonly;
	}
	
	public void setResourcefieldReadonlyexp(String resourcefieldReadonlyexp) {
		this.resourcefieldReadonlyexp = resourcefieldReadonlyexp;
	}
	
	public String getResourcefieldReadonlyexp() {
		return resourcefieldReadonlyexp;
	}
	
	public void setResourcefieldReadonlyexpfn(String resourcefieldReadonlyexpfn) {
		this.resourcefieldReadonlyexpfn = resourcefieldReadonlyexpfn;
	}
	
	public String getResourcefieldReadonlyexpfn() {
		return resourcefieldReadonlyexpfn;
	}
	
	public void setResourcefieldRegex(String resourcefieldRegex) {
		this.resourcefieldRegex = resourcefieldRegex;
	}
	
	public String getResourcefieldRegex() {
		return resourcefieldRegex;
	}
	
	public void setResourcefieldRegextext(String resourcefieldRegextext) {
		this.resourcefieldRegextext = resourcefieldRegextext;
	}
	
	public String getResourcefieldRegextext() {
		return resourcefieldRegextext;
	}
	
	public void setResourcefieldRemoved(String resourcefieldRemoved) {
		this.resourcefieldRemoved = resourcefieldRemoved;
	}
	
	public String getResourcefieldRemoved() {
		return resourcefieldRemoved;
	}
	
	public void setResourcefieldRowfield(String resourcefieldRowfield) {
		this.resourcefieldRowfield = resourcefieldRowfield;
	}
	
	public String getResourcefieldRowfield() {
		return resourcefieldRowfield;
	}
	
	public void setResourcefieldRowspan(Integer resourcefieldRowspan) {
		this.resourcefieldRowspan = resourcefieldRowspan;
	}
	
	public Integer getResourcefieldRowspan() {
		return resourcefieldRowspan;
	}
	
	public void setResourcefieldStep(Integer resourcefieldStep) {
		this.resourcefieldStep = resourcefieldStep;
	}
	
	public Integer getResourcefieldStep() {
		return resourcefieldStep;
	}
	
	public void setResourcefieldSysmode(String resourcefieldSysmode) {
		this.resourcefieldSysmode = resourcefieldSysmode;
	}
	
	public String getResourcefieldSysmode() {
		return resourcefieldSysmode;
	}
	
	public void setResourcefieldUnitlisttpl(String resourcefieldUnitlisttpl) {
		this.resourcefieldUnitlisttpl = resourcefieldUnitlisttpl;
	}
	
	public String getResourcefieldUnitlisttpl() {
		return resourcefieldUnitlisttpl;
	}
	
	public void setResourcefieldUnittpl(String resourcefieldUnittpl) {
		this.resourcefieldUnittpl = resourcefieldUnittpl;
	}
	
	public String getResourcefieldUnittpl() {
		return resourcefieldUnittpl;
	}
	
	public void setResourcefieldValue(String resourcefieldValue) {
		this.resourcefieldValue = resourcefieldValue;
	}
	
	public String getResourcefieldValue() {
		return resourcefieldValue;
	}
	
	public void setResourcefieldValuefn(String resourcefieldValuefn) {
		this.resourcefieldValuefn = resourcefieldValuefn;
	}
	
	public String getResourcefieldValuefn() {
		return resourcefieldValuefn;
	}
	
	public void setResourcefieldVtype(String resourcefieldVtype) {
		this.resourcefieldVtype = resourcefieldVtype;
	}
	
	public String getResourcefieldVtype() {
		return resourcefieldVtype;
	}
	
	public void setResourcefieldVtypeconfig(String resourcefieldVtypeconfig) {
		this.resourcefieldVtypeconfig = resourcefieldVtypeconfig;
	}
	
	public String getResourcefieldVtypeconfig() {
		return resourcefieldVtypeconfig;
	}
	
	public void setResourcefieldWheresql(String resourcefieldWheresql) {
		this.resourcefieldWheresql = resourcefieldWheresql;
	}
	
	public String getResourcefieldWheresql() {
		return resourcefieldWheresql;
	}
	
	public void setResourcefieldWidth(String resourcefieldWidth) {
		this.resourcefieldWidth = resourcefieldWidth;
	}
	
	public String getResourcefieldWidth() {
		return resourcefieldWidth;
	}
	
	public void setResourcefieldXtype(String resourcefieldXtype) {
		this.resourcefieldXtype = resourcefieldXtype;
	}
	
	public String getResourcefieldXtype() {
		return resourcefieldXtype;
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
	
	public void setResourcefieldVtypefn(String resourcefieldVtypefn) {
		this.resourcefieldVtypefn = resourcefieldVtypefn;
	}
	
	public String getResourcefieldVtypefn() {
		return resourcefieldVtypefn;
	}
	
	public void setResourcefieldLabelalign(String resourcefieldLabelalign) {
		this.resourcefieldLabelalign = resourcefieldLabelalign;
	}
	
	public String getResourcefieldLabelalign() {
		return resourcefieldLabelalign;
	}
	
}
