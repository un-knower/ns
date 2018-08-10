/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据字典项表
 * @version 1.0
 * @author
 */
public class NsCoreDictionaryitem implements Serializable {
	
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
	
	/** 字典项ID */
	@ApiModelProperty(value = "字典项ID")
	private String jeCoreDictionaryitemId;
	
	/** 字典ID */
	@ApiModelProperty(value = "字典ID")
	private String dictionaryitemDictionaryId;
	
	/** 单元格颜色 */
	@ApiModelProperty(value = "单元格颜色")
	private String dictionaryitemBackgroundcolor;
	
	/** 分类 */
	@ApiModelProperty(value = "分类")
	private String dictionaryitemClassify;
	
	/** 字体颜色 */
	@ApiModelProperty(value = "字体颜色")
	private String dictionaryitemFontcolor;
	
	/** 图标样式 */
	@ApiModelProperty(value = "图标样式")
	private String dictionaryitemIconcls;
	
	/** 节点编码 */
	@ApiModelProperty(value = "节点编码")
	private String dictionaryitemItemcode;
	
	/** 节点名称 */
	@ApiModelProperty(value = "节点名称")
	private String dictionaryitemItemname;
	
	/** 英文 */
	@ApiModelProperty(value = "英文")
	private String dictionaryitemItemnameEn;
	
	/** 节点信息 */
	@ApiModelProperty(value = "节点信息")
	private String dictionaryitemNodeinfo;
	
	/** 节点信息类型 */
	@ApiModelProperty(value = "节点信息类型")
	private String dictionaryitemNodeinfotype;
	
	/** 引用图片 */
	@ApiModelProperty(value = "引用图片")
	private String dictionaryitemRefphoto;
	
	/** 树形图标 */
	@ApiModelProperty(value = "树形图标")
	private String dictionaryitemTreeiconcls;
	
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

    public void setJeCoreDictionaryitemId(String jeCoreDictionaryitemId) {
		this.jeCoreDictionaryitemId = jeCoreDictionaryitemId;
	}
	
	public String getJeCoreDictionaryitemId() {
		return jeCoreDictionaryitemId;
	}
	
	public void setDictionaryitemDictionaryId(String dictionaryitemDictionaryId) {
		this.dictionaryitemDictionaryId = dictionaryitemDictionaryId;
	}
	
	public String getDictionaryitemDictionaryId() {
		return dictionaryitemDictionaryId;
	}
	
	public void setDictionaryitemBackgroundcolor(String dictionaryitemBackgroundcolor) {
		this.dictionaryitemBackgroundcolor = dictionaryitemBackgroundcolor;
	}
	
	public String getDictionaryitemBackgroundcolor() {
		return dictionaryitemBackgroundcolor;
	}
	
	public void setDictionaryitemClassify(String dictionaryitemClassify) {
		this.dictionaryitemClassify = dictionaryitemClassify;
	}
	
	public String getDictionaryitemClassify() {
		return dictionaryitemClassify;
	}
	
	public void setDictionaryitemFontcolor(String dictionaryitemFontcolor) {
		this.dictionaryitemFontcolor = dictionaryitemFontcolor;
	}
	
	public String getDictionaryitemFontcolor() {
		return dictionaryitemFontcolor;
	}
	
	public void setDictionaryitemIconcls(String dictionaryitemIconcls) {
		this.dictionaryitemIconcls = dictionaryitemIconcls;
	}
	
	public String getDictionaryitemIconcls() {
		return dictionaryitemIconcls;
	}
	
	public void setDictionaryitemItemcode(String dictionaryitemItemcode) {
		this.dictionaryitemItemcode = dictionaryitemItemcode;
	}
	
	public String getDictionaryitemItemcode() {
		return dictionaryitemItemcode;
	}
	
	public void setDictionaryitemItemname(String dictionaryitemItemname) {
		this.dictionaryitemItemname = dictionaryitemItemname;
	}
	
	public String getDictionaryitemItemname() {
		return dictionaryitemItemname;
	}
	
	public void setDictionaryitemItemnameEn(String dictionaryitemItemnameEn) {
		this.dictionaryitemItemnameEn = dictionaryitemItemnameEn;
	}
	
	public String getDictionaryitemItemnameEn() {
		return dictionaryitemItemnameEn;
	}
	
	public void setDictionaryitemNodeinfo(String dictionaryitemNodeinfo) {
		this.dictionaryitemNodeinfo = dictionaryitemNodeinfo;
	}
	
	public String getDictionaryitemNodeinfo() {
		return dictionaryitemNodeinfo;
	}
	
	public void setDictionaryitemNodeinfotype(String dictionaryitemNodeinfotype) {
		this.dictionaryitemNodeinfotype = dictionaryitemNodeinfotype;
	}
	
	public String getDictionaryitemNodeinfotype() {
		return dictionaryitemNodeinfotype;
	}
	
	public void setDictionaryitemRefphoto(String dictionaryitemRefphoto) {
		this.dictionaryitemRefphoto = dictionaryitemRefphoto;
	}
	
	public String getDictionaryitemRefphoto() {
		return dictionaryitemRefphoto;
	}
	
	public void setDictionaryitemTreeiconcls(String dictionaryitemTreeiconcls) {
		this.dictionaryitemTreeiconcls = dictionaryitemTreeiconcls;
	}
	
	public String getDictionaryitemTreeiconcls() {
		return dictionaryitemTreeiconcls;
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
