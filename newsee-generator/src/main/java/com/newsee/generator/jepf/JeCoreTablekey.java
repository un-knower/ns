/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.generator.jepf;

import java.io.Serializable;

/**
 * 资源表-->键管理
 * @version 1.0
 * @author
 */
public class JeCoreTablekey implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 是否检测 */
	private String tablekeyChecked;
	
	/** 分类 */
	private String tablekeyClassify;
	
	/** 键编码 */
	private String tablekeyCode;
	
	/** 字段编码 */
	private String tablekeyColumncode;
	
	/** 是否已创建 */
	private String tablekeyIscreate;
	
	/** 是否强制性约束 */
	private String tablekeyIsrestraint;
	
	/** 关联字段 */
	private String tablekeyLinecolumncode;
	
	/** 关联类型 */
	private String tablekeyLinetyle;
	
	/** 关联表 */
	private String tablekeyLinktable;
	
	/** 资源表外键 */
	private String tablekeyResourcetableId;
	
	/** 表编码 */
	private String tablekeyTablecode;
	
	/** 类型 */
	private String tablekeyType;
	
	/** 主键ID */
	private String jeCoreTablekeyId;
	
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
	
	/** 数据状态 */
	private String syStatus;
	
		
	public void setTablekeyChecked(String tablekeyChecked) {
		this.tablekeyChecked = tablekeyChecked;
	}
	
	public String getTablekeyChecked() {
		return tablekeyChecked;
	}
	
	public void setTablekeyClassify(String tablekeyClassify) {
		this.tablekeyClassify = tablekeyClassify;
	}
	
	public String getTablekeyClassify() {
		return tablekeyClassify;
	}
	
	public void setTablekeyCode(String tablekeyCode) {
		this.tablekeyCode = tablekeyCode;
	}
	
	public String getTablekeyCode() {
		return tablekeyCode;
	}
	
	public void setTablekeyColumncode(String tablekeyColumncode) {
		this.tablekeyColumncode = tablekeyColumncode;
	}
	
	public String getTablekeyColumncode() {
		return tablekeyColumncode;
	}
	
	public void setTablekeyIscreate(String tablekeyIscreate) {
		this.tablekeyIscreate = tablekeyIscreate;
	}
	
	public String getTablekeyIscreate() {
		return tablekeyIscreate;
	}
	
	public void setTablekeyIsrestraint(String tablekeyIsrestraint) {
		this.tablekeyIsrestraint = tablekeyIsrestraint;
	}
	
	public String getTablekeyIsrestraint() {
		return tablekeyIsrestraint;
	}
	
	public void setTablekeyLinecolumncode(String tablekeyLinecolumncode) {
		this.tablekeyLinecolumncode = tablekeyLinecolumncode;
	}
	
	public String getTablekeyLinecolumncode() {
		return tablekeyLinecolumncode;
	}
	
	public void setTablekeyLinetyle(String tablekeyLinetyle) {
		this.tablekeyLinetyle = tablekeyLinetyle;
	}
	
	public String getTablekeyLinetyle() {
		return tablekeyLinetyle;
	}
	
	public void setTablekeyLinktable(String tablekeyLinktable) {
		this.tablekeyLinktable = tablekeyLinktable;
	}
	
	public String getTablekeyLinktable() {
		return tablekeyLinktable;
	}
	
	public void setTablekeyResourcetableId(String tablekeyResourcetableId) {
		this.tablekeyResourcetableId = tablekeyResourcetableId;
	}
	
	public String getTablekeyResourcetableId() {
		return tablekeyResourcetableId;
	}
	
	public void setTablekeyTablecode(String tablekeyTablecode) {
		this.tablekeyTablecode = tablekeyTablecode;
	}
	
	public String getTablekeyTablecode() {
		return tablekeyTablecode;
	}
	
	public void setTablekeyType(String tablekeyType) {
		this.tablekeyType = tablekeyType;
	}
	
	public String getTablekeyType() {
		return tablekeyType;
	}
	
	public void setJeCoreTablekeyId(String jeCoreTablekeyId) {
		this.jeCoreTablekeyId = jeCoreTablekeyId;
	}
	
	public String getJeCoreTablekeyId() {
		return jeCoreTablekeyId;
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
	
	public void setSyStatus(String syStatus) {
		this.syStatus = syStatus;
	}
	
	public String getSyStatus() {
		return syStatus;
	}
	
}
