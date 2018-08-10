package com.newsee.common.rest;

import java.io.Serializable;

/**
 * 用于切面记录日志，半侵入业务代码
 * 切面获取到该实体的值之后，发送发kafka，
 * kakfa中的服务经过处理后，将日志写入到mongodb中
 * 供业务页面查询使用。
 * 
 * @author xiaoss add on 2017/12/22
 *
 */
public class RestLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 数据id */
	private Long dataId;
	
	/** 模块名称，如客户，家庭关系， 银行账号  */
	private String moduleName;
	
	/** 功能名称 */
	private String funcId;
	
	/** 新增，删除，修改的主体名称 */
	private String subjectName;
	
	/** 修改前的对象json字符串，业务中不需要传值，aop中处理 */
	private String beforeJson;
	
	/** 修改后的对象json字符串，业务中不需要传值，aop中处理 */
	private String afterJson;
	
	public RestLog(Long dataId, String moduleName, String subjectName, String funcId){
		this.dataId = dataId;
		this.moduleName = moduleName;
		this.subjectName = subjectName;
		this.funcId = funcId;
	}

	@SuppressWarnings("unused")
	private RestLog(){}
	
	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getBeforeJson() {
		return beforeJson;
	}

	public void setBeforeJson(String beforeJson) {
		this.beforeJson = beforeJson;
	}

	public String getAfterJson() {
		return afterJson;
	}

	public void setAfterJson(String afterJson) {
		this.afterJson = afterJson;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
}
