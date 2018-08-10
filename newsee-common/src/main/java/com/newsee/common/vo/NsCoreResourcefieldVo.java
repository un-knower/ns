/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.newsee.common.entity.SelectEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能的表单资源
 * @version 1.0
 * @author
 */
public class NsCoreResourcefieldVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 主键ID */
	@ApiModelProperty(value = "主键ID")
	private String _id;
	
	/** 所属企业ID */
	@ApiModelProperty(value = "所属企业ID")
	private Long enterpriseId;
	
	/** 所属公司ID */
	@ApiModelProperty(value = "所属组织ID")
	private Long organizationId;
	
	/** 表单字段ID */
	@ApiModelProperty(value = "表单字段ID")
	private String jeCoreResourcefieldId;
	
	/** 功能ID */
	@ApiModelProperty(value = "功能ID")
	private String resourcefieldFuncinfoId;
	
	/** 是否为空 */
	@ApiModelProperty(value = "是否为空")
	private Boolean isfieldRequired;
	
	/** 必填表达式 */
	@ApiModelProperty(value = "必填表达式")
	private String resourcefieldAllowblankexp;
	
	/** 必填控制表达式方法 */
	@ApiModelProperty(value = "必填控制表达式方法")
	private String resourcefieldAllowblankexpfn;
	
	/** 背景色 */
	@ApiModelProperty(value = "背景色")
	private String resourcefieldBgcolor;
	
	/** 绑定信息 */
	@ApiModelProperty(value = "绑定信息，必输提示")
	private String resourcefieldBinding;
	
	/** 绑定表达式方法 */
	@ApiModelProperty(value = "绑定表达式方法")
	private List<String> resourcefieldBindingfnList;
	
	/** 编码 */
	@ApiModelProperty(value = "编码")
	private String resourcefieldCode;
	
	/** 列数(分组框用) */
	@ApiModelProperty(value = "列数(分组框用)")
	private Integer resourcefieldCols;
	
	/** 所占列数 */
	@ApiModelProperty(value = "所占列数")
	private Integer resourcefieldColspan;
	
	/** 配置信息 */
	@ApiModelProperty(value = "配置信息")
	private String resourcefieldConfiginfo;
	
	/** 描述 */
	@ApiModelProperty(value = "描述")
	private String resourcefieldDescription;
	
	/** 是否可用 */
	@ApiModelProperty(value = "是否可用")
	private Boolean isResourcefieldDisabled;
	
	/** 可选可编辑 */
	@ApiModelProperty(value = "可选可编辑")
	private Boolean isResourcefieldEditable;
	
	/** 输入提示 */
	@ApiModelProperty(value = "输入提示")
	private String resourcefieldEmptytext;
	
	/** 可选表达式 */
	@ApiModelProperty(value = "可选表达式")
	private String resourcefieldEnableexp;
	
	/** 树形字典项可选表达式 */
	@ApiModelProperty(value = "树形字典项可选表达式")
	private String resourcefieldEnableexpfn;
	
	/** 可选表达式提示语 */
	@ApiModelProperty(value = "可选表达式提示语")
	private String resourcefieldEnabletip;
	
	/** 字段样式表 */
	@ApiModelProperty(value = "字段样式表,icon")
	private String resourcefieldFieldcls;
	
	/** 字段颜色 */
	@ApiModelProperty(value = "字段颜色")
	private String resourcefieldFieldcolor;
	
	/** 字段懒加载 */
	@ApiModelProperty(value = "字段懒加载")
	private String resourcefieldFieldlazy;
	
	/** 比例 */
	@ApiModelProperty(value = "比例")
	private String resourcefieldFlex;
	
	/** 分组框 */
	@ApiModelProperty(value = "分组框")
	private String resourcefieldGroupname;
	
	/** 高度 */
	@ApiModelProperty(value = "高度")
	private Integer fieldHeight;
	
	/** 是否隐藏 */
	@ApiModelProperty(value = "是否隐藏")
	private Boolean isResourcefieldHidden;
	
	/** 历史留痕 */
	@ApiModelProperty(value = "历史留痕")
	private String resourcefieldHistory;
	
	/** 是否导入 */
	@ApiModelProperty(value = "是否导入")
	private String resourcefieldIfimpl;
	
	/** 显隐控制表达式 */
	@ApiModelProperty(value = "显隐控制表达式")
	private String resourcefieldInterpreter;
	
	/** 显隐控制表达式方法 */
	@ApiModelProperty(value = "显隐控制表达式方法")
	private String resourcefieldInterpreterfn;
	
	/** 是否主键 */
	@ApiModelProperty(value = "是否主键")
	private String resourcefieldIspk;
	
	/** 监听事件 */
	@ApiModelProperty(value = "监听事件")
	private String resourcefieldJslistener;
	
	/** 标签颜色 */
	@ApiModelProperty(value = "标签颜色")
	private String resourcefieldLabelcolor;
	
	/** 定位 */
	@ApiModelProperty(value = "定位")
	private String resourcefieldLocate;
	
	/** 最大值 */
	@ApiModelProperty(value = "最大值")
	private Integer resourcefieldMaxvalue;
	
	/** 最小值 */
	@ApiModelProperty(value = "最小值")
	private Integer resourcefieldMinvalue;
	
	/** 名称 */
	@ApiModelProperty(value = "名称")
	private String resourcefieldName;
	
	/** 英文名 */
	@ApiModelProperty(value = "英文名")
	private String resourcefieldNameEn;
	
	/** 产品扩展功能ID */
	@ApiModelProperty(value = "产品扩展功能ID")
	private String resourcefieldNewfuncid;
	
	/** 辅助配置信息 */
	@ApiModelProperty(value = "辅助配置信息")
	private String resourcefieldOtherconfig;
	
	/** 辅助配置信息 */
    @ApiModelProperty(value = "辅助配置信息json对象")
    private Map<String,Object> resourcefieldOtherconfigObject;
	
	/** 是否只读 */
	@ApiModelProperty(value = "是否只读")
	private Boolean isResourcefieldReadonly;
	
	/** 只读表达式 */
	@ApiModelProperty(value = "只读表达式")
	private String resourcefieldReadonlyexp;
	
	/** 只读控制表达式方法 */
	@ApiModelProperty(value = "只读控制表达式方法")
	private String resourcefieldReadonlyexpfn;
	
	/** 正则表达式 */
	@ApiModelProperty(value = "正则表达式")
	private String resourcefieldRegex;
	
	/** 正则提示信息 */
	@ApiModelProperty(value = "正则提示信息")
	private String resourcefieldRegextext;
	
	/** 禁用 */
	@ApiModelProperty(value = "禁用")
	private String resourcefieldRemoved;
	
	@ApiModelProperty(value = "是否禁用")
    private Boolean isResourcefieldRemoved;
	
	/** 依附字段 */
	@ApiModelProperty(value = "依附字段")
	private String resourcefieldRowfield;
	
	/** 所占行数 */
	@ApiModelProperty(value = "所占行数")
	private Integer resourcefieldRowspan;
	
	/** 步长 */
	@ApiModelProperty(value = "步长")
	private Integer resourcefieldStep;
	
	/** 系统模式 */
	@ApiModelProperty(value = "系统模式")
	private String resourcefieldSysmode;
	
	/** 高级后缀 */
	@ApiModelProperty(value = "高级后缀")
	private String resourcefieldUnitlisttpl;
	
	/** 字段后缀 */
	@ApiModelProperty(value = "字段后缀")
	private String resourcefieldUnittpl;
	
	/** 默认值 */
	@ApiModelProperty(value = "默认值")
	private String resourcefieldValue;
	
	/** 默认值函数 */
	@ApiModelProperty(value = "默认值函数")
	private String resourcefieldValuefn;
	
	/** 校验信息 */
	@ApiModelProperty(value = "校验信息")
	private String resourcefieldVtype;
	
	/** 验证配置 */
	@ApiModelProperty(value = "验证配置")
	private String resourcefieldVtypeconfig;
	
	
	/** 宽度 */
	@ApiModelProperty(value = "宽度")
	private Integer fieldWidth;
	
	/** 类型 */
	@ApiModelProperty(value = "类型")
	private String resourcefieldXtype;
	
	/** 排序字段 */
	@ApiModelProperty(value = "排序字段")
	private Integer syOrderindex;
	
	/** 数据字典选项*/
	@ApiModelProperty(value = "数据字典选项")
	private List<SelectEntity> items;
	
	/** 数据字典省市区*/
    @ApiModelProperty(value = "数据字典选项(省市区控件用)")
    private Map<String, List<SelectEntity>> itemsObj;
    
	/** 数据字典code*/
	@ApiModelProperty(value = "数据字典code")
	private String ddcode;
	
	/** 表单值*/
	@ApiModelProperty(value = "表单值")
	private String value;
	
	/** 分组框所属控件*/
	@ApiModelProperty(value = "分组框所属控件")
	private List<NsCoreResourcefieldVo> children = new ArrayList<NsCoreResourcefieldVo>();
	
/*	*//** label宽度*//*
	@ApiModelProperty(value = "表单值")
	private Integer funcinfoFormlabelwidth;*/
	
	/** form表单中表格的头部信息 */
	@ApiModelProperty(value = "form表单中表格的头部信息")
	private String tableHeader;
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	
	public Long getOrganizationId() {
		return organizationId;
	}
	
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
	
	public void setResourcefieldBindingfnList(List<String> resourcefieldBindingfnList) {
		this.resourcefieldBindingfnList = resourcefieldBindingfnList;
	}
	
	public List<String> getResourcefieldBindingfnList() {
		return resourcefieldBindingfnList;
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
	
	public void setResourcefieldXtype(String resourcefieldXtype) {
		this.resourcefieldXtype = resourcefieldXtype;
	}
	
	public String getResourcefieldXtype() {
		return resourcefieldXtype;
	}
	
	public Integer getFieldHeight() {
		return fieldHeight;
	}

	public void setFieldHeight(Integer fieldHeight) {
		this.fieldHeight = fieldHeight;
	}

	public Integer getFieldWidth() {
		return fieldWidth;
	}

	public void setFieldWidth(Integer fieldWidth) {
		this.fieldWidth = fieldWidth;
	}

	public Integer getSyOrderindex() {
		return syOrderindex;
	}

	public void setSyOrderindex(Integer syOrderindex) {
		this.syOrderindex = syOrderindex;
	}

	public List<SelectEntity> getItems() {
		return items;
	}

	public void setItems(List<SelectEntity> items) {
		this.items = items;
	}
	
	public String getDdcode() {
		return ddcode;
	}

	public void setDdcode(String ddcode) {
		this.ddcode = ddcode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	public Boolean getIsResourcefieldDisabled() {
		return isResourcefieldDisabled;
	}

	public void setIsResourcefieldDisabled(Boolean isResourcefieldDisabled) {
		this.isResourcefieldDisabled = isResourcefieldDisabled;
	}

	public Boolean getIsResourcefieldEditable() {
		return isResourcefieldEditable;
	}

	public void setIsResourcefieldEditable(Boolean isResourcefieldEditable) {
		this.isResourcefieldEditable = isResourcefieldEditable;
	}

	public Boolean getIsResourcefieldReadonly() {
		return isResourcefieldReadonly;
	}

	public void setIsResourcefieldReadonly(Boolean isResourcefieldReadonly) {
		this.isResourcefieldReadonly = isResourcefieldReadonly;
	}
	
	public Boolean getIsResourcefieldHidden() {
		return isResourcefieldHidden;
	}

	public void setIsResourcefieldHidden(Boolean isResourcefieldHidden) {
		this.isResourcefieldHidden = isResourcefieldHidden;
	}
	
	public Boolean getIsfieldRequired() {
		return isfieldRequired;
	}

	public void setIsfieldRequired(Boolean isfieldRequired) {
		this.isfieldRequired = isfieldRequired;
	}
	
	public String getTableHeader() {
		return tableHeader;
	}

	public void setTableHeader(String tableHeader) {
		this.tableHeader = tableHeader;
	}

    public Boolean getIsResourcefieldRemoved() {
        return isResourcefieldRemoved;
    }

    public void setIsResourcefieldRemoved(Boolean isResourcefieldRemoved) {
        this.isResourcefieldRemoved = isResourcefieldRemoved;
    }

    public Map<String, Object> getResourcefieldOtherconfigObject() {
        return resourcefieldOtherconfigObject;
    }

    public void setResourcefieldOtherconfigObject(Map<String, Object> resourcefieldOtherconfigObject) {
        this.resourcefieldOtherconfigObject = resourcefieldOtherconfigObject;
    }

    public List<NsCoreResourcefieldVo> getChildren() {
		return children;
	}

	public void setChildren(List<NsCoreResourcefieldVo> children) {
		this.children = children;
	}

    public Map<String, List<SelectEntity>> getItemsObj() {
        return itemsObj;
    }

    public void setItemsObj(Map<String, List<SelectEntity>> itemsObj) {
        this.itemsObj = itemsObj;
    }
	
}
