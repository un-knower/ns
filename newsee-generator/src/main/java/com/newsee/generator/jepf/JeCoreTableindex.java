/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.generator.jepf;

import java.io.Serializable;

/**
 * 资源表-->索引管理
 * @version 1.0
 * @author
 */
public class JeCoreTableindex implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 分类 */
	private String tableindexClassify;
	
	/** 字段编码 */
	private String tableindexFieldcode;
	
	/** 字段名称 */
	private String tableindexFieldname;
	
	/** 创建 */
	private String tableindexIscreate;
	
	/** 名称 */
	private String tableindexName;
	
	/** 备注 */
	private String tableindexRemark;
	
	/** 外键 */
	private String tableindexResourcetableId;
	
	/** 表名 */
	private String tableindexTablecode;
	
	/** 唯一 */
	private String tableindexUnique;
	
	/** 主键ID */
	private String jeCoreTableindexId;
	
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
	
	/** 排序字段 */
	private Integer syOrderindex;
	
	/** 流程定义ID */
	private String syPdid;
	
	/** 流程实例ID */
	private String syPiid;
	
	/** 数据状态 */
	private String syStatus;
	
		
	public void setTableindexClassify(String tableindexClassify) {
		this.tableindexClassify = tableindexClassify;
	}
	
	public String getTableindexClassify() {
		return tableindexClassify;
	}
	
	public void setTableindexFieldcode(String tableindexFieldcode) {
		this.tableindexFieldcode = tableindexFieldcode;
	}
	
	public String getTableindexFieldcode() {
		return tableindexFieldcode;
	}
	
	public void setTableindexFieldname(String tableindexFieldname) {
		this.tableindexFieldname = tableindexFieldname;
	}
	
	public String getTableindexFieldname() {
		return tableindexFieldname;
	}
	
	public void setTableindexIscreate(String tableindexIscreate) {
		this.tableindexIscreate = tableindexIscreate;
	}
	
	public String getTableindexIscreate() {
		return tableindexIscreate;
	}
	
	public void setTableindexName(String tableindexName) {
		this.tableindexName = tableindexName;
	}
	
	public String getTableindexName() {
		return tableindexName;
	}
	
	public void setTableindexRemark(String tableindexRemark) {
		this.tableindexRemark = tableindexRemark;
	}
	
	public String getTableindexRemark() {
		return tableindexRemark;
	}
	
	public void setTableindexResourcetableId(String tableindexResourcetableId) {
		this.tableindexResourcetableId = tableindexResourcetableId;
	}
	
	public String getTableindexResourcetableId() {
		return tableindexResourcetableId;
	}
	
	public void setTableindexTablecode(String tableindexTablecode) {
		this.tableindexTablecode = tableindexTablecode;
	}
	
	public String getTableindexTablecode() {
		return tableindexTablecode;
	}
	
	public void setTableindexUnique(String tableindexUnique) {
		this.tableindexUnique = tableindexUnique;
	}
	
	public String getTableindexUnique() {
		return tableindexUnique;
	}
	
	public void setJeCoreTableindexId(String jeCoreTableindexId) {
		this.jeCoreTableindexId = jeCoreTableindexId;
	}
	
	public String getJeCoreTableindexId() {
		return jeCoreTableindexId;
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
