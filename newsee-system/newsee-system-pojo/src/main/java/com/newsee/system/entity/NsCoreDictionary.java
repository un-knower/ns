/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据字典表
 * @version 1.0
 * @author
 */
public class NsCoreDictionary implements Serializable {
	
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
	
	/** 所属字典组ID */
    @ApiModelProperty(value = "所属字典组ID")
    private Long dictionaryGroupId;
	
	/** 字典ID */
	@ApiModelProperty(value = "字典ID")
	private String jeCoreDictionaryId;
	
	/** 适应范围: 0=全局，1=集团，2=公司，3=部门，4=集团+公司 */
    @ApiModelProperty(value = "适应范围: 0=全局，1=集团，2=公司，3=部门，4=集团+公司")
    private String dictionaryUseScope;
	
	/** 所属子系统 */
	@ApiModelProperty(value = "所属子系统")
	private String dictionaryBelongsto;
	
	/** 所属子系统 */
	@ApiModelProperty(value = "所属子系统")
	private String dictionaryBelongstoname;
	
	/** 类名 */
	@ApiModelProperty(value = "类名")
	private String dictionaryClass;
	
	/** 外部自定实体名称 */
	@ApiModelProperty(value = "外部自定实体名称")
	private String dictionaryClassname;
	
	/** 字典编码 */
	@ApiModelProperty(value = "字典编码")
	private String dictionaryDdcode;
	
	/** 字典名称 */
	@ApiModelProperty(value = "字典名称")
	private String dictionaryDdname;
	
	/** 字典类型 */
	@ApiModelProperty(value = "字典类型")
	private String dictionaryDdtype;
	
	/** 类型 */
	@ApiModelProperty(value = "类型")
	private String dictionaryDictype;
	
	/** 树形扩展字段 */
	@ApiModelProperty(value = "树形扩展字段")
	private String dictionaryFieldconfigs;
	
	/** 字典项根节点ID */
	@ApiModelProperty(value = "字典项根节点ID")
	private String dictionaryItemrootId;
	
	/** 方法名 */
	@ApiModelProperty(value = "方法名")
	private String dictionaryMethod;
	
	/** 排序条件 */
	@ApiModelProperty(value = "排序条件")
	private String dictionaryOrdersql;
	
	/** SQL */
	@ApiModelProperty(value = "SQL")
	private String dictionarySql;
	
	/** SQL配置信息 */
	@ApiModelProperty(value = "SQL配置信息")
	private String dictionarySqlconfig;
	
	/** SQL列表说明 */
	@ApiModelProperty(value = "SQL列表说明")
	private String dictionarySqllbsm;
	
	/** SQL配置信息列表 */
	@ApiModelProperty(value = "SQL配置信息列表")
	private String dictionarySqlpzxxlb;
	
	/** SQL树形说明 */
	@ApiModelProperty(value = "SQL树形说明")
	private String dictionarySqlsxsm;
	
	/** 查询条件 */
	@ApiModelProperty(value = "查询条件")
	private String dictionaryWheresql;
	
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

    public void setJeCoreDictionaryId(String jeCoreDictionaryId) {
		this.jeCoreDictionaryId = jeCoreDictionaryId;
	}
	
	public String getJeCoreDictionaryId() {
		return jeCoreDictionaryId;
	}
	
	public void setDictionaryBelongsto(String dictionaryBelongsto) {
		this.dictionaryBelongsto = dictionaryBelongsto;
	}
	
	public String getDictionaryBelongsto() {
		return dictionaryBelongsto;
	}
	
	public void setDictionaryBelongstoname(String dictionaryBelongstoname) {
		this.dictionaryBelongstoname = dictionaryBelongstoname;
	}
	
	public String getDictionaryBelongstoname() {
		return dictionaryBelongstoname;
	}
	
	public void setDictionaryClass(String dictionaryClass) {
		this.dictionaryClass = dictionaryClass;
	}
	
	public String getDictionaryClass() {
		return dictionaryClass;
	}
	
	public void setDictionaryClassname(String dictionaryClassname) {
		this.dictionaryClassname = dictionaryClassname;
	}
	
	public String getDictionaryClassname() {
		return dictionaryClassname;
	}
	
	public void setDictionaryDdcode(String dictionaryDdcode) {
		this.dictionaryDdcode = dictionaryDdcode;
	}
	
	public String getDictionaryDdcode() {
		return dictionaryDdcode;
	}
	
	public void setDictionaryDdname(String dictionaryDdname) {
		this.dictionaryDdname = dictionaryDdname;
	}
	
	public String getDictionaryDdname() {
		return dictionaryDdname;
	}
	
	public void setDictionaryDdtype(String dictionaryDdtype) {
		this.dictionaryDdtype = dictionaryDdtype;
	}
	
	public String getDictionaryDdtype() {
		return dictionaryDdtype;
	}
	
	public void setDictionaryDictype(String dictionaryDictype) {
		this.dictionaryDictype = dictionaryDictype;
	}
	
	public String getDictionaryDictype() {
		return dictionaryDictype;
	}
	
	public void setDictionaryFieldconfigs(String dictionaryFieldconfigs) {
		this.dictionaryFieldconfigs = dictionaryFieldconfigs;
	}
	
	public String getDictionaryFieldconfigs() {
		return dictionaryFieldconfigs;
	}
	
	public void setDictionaryItemrootId(String dictionaryItemrootId) {
		this.dictionaryItemrootId = dictionaryItemrootId;
	}
	
	public String getDictionaryItemrootId() {
		return dictionaryItemrootId;
	}
	
	public void setDictionaryMethod(String dictionaryMethod) {
		this.dictionaryMethod = dictionaryMethod;
	}
	
	public String getDictionaryMethod() {
		return dictionaryMethod;
	}
	
	public void setDictionaryOrdersql(String dictionaryOrdersql) {
		this.dictionaryOrdersql = dictionaryOrdersql;
	}
	
	public String getDictionaryOrdersql() {
		return dictionaryOrdersql;
	}
	
	public void setDictionarySql(String dictionarySql) {
		this.dictionarySql = dictionarySql;
	}
	
	public String getDictionarySql() {
		return dictionarySql;
	}
	
	public void setDictionarySqlconfig(String dictionarySqlconfig) {
		this.dictionarySqlconfig = dictionarySqlconfig;
	}
	
	public String getDictionarySqlconfig() {
		return dictionarySqlconfig;
	}
	
	public void setDictionarySqllbsm(String dictionarySqllbsm) {
		this.dictionarySqllbsm = dictionarySqllbsm;
	}
	
	public String getDictionarySqllbsm() {
		return dictionarySqllbsm;
	}
	
	public void setDictionarySqlpzxxlb(String dictionarySqlpzxxlb) {
		this.dictionarySqlpzxxlb = dictionarySqlpzxxlb;
	}
	
	public String getDictionarySqlpzxxlb() {
		return dictionarySqlpzxxlb;
	}
	
	public void setDictionarySqlsxsm(String dictionarySqlsxsm) {
		this.dictionarySqlsxsm = dictionarySqlsxsm;
	}
	
	public String getDictionarySqlsxsm() {
		return dictionarySqlsxsm;
	}
	
	public void setDictionaryWheresql(String dictionaryWheresql) {
		this.dictionaryWheresql = dictionaryWheresql;
	}
	
	public String getDictionaryWheresql() {
		return dictionaryWheresql;
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

    public Long getDictionaryGroupId() {
        return dictionaryGroupId;
    }

    public void setDictionaryGroupId(Long dictionaryGroupId) {
        this.dictionaryGroupId = dictionaryGroupId;
    }

    public String getDictionaryUseScope() {
        return dictionaryUseScope;
    }

    public void setDictionaryUseScope(String dictionaryUseScope) {
        this.dictionaryUseScope = dictionaryUseScope;
    }
	
}
