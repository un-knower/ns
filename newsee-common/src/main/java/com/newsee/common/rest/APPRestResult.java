package com.newsee.common.rest;

import java.io.Serializable;

/**
 * rest返回结果类
 * @author wangjun  ADD ON 2018/08/11
 *
 */
public class APPRestResult<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	private String NWRespCode;  //返回代码
	
	private String NWErrMsg;//错误说明
	
	private Integer RecordCount;//记录数
	
	private RestLog  restLog;
	private  T record ;
	
	public APPRestResult(T resultData){
		this.NWRespCode = ResultCodeEnum.SUCCESS.CODE;
		this.NWErrMsg = ResultCodeEnum.SUCCESS.DESC;
		this.record = resultData;
	}

	public String getNWRespCode() {
		return NWRespCode;
	}

	public void setNWRespCode(String nWRespCode) {
		NWRespCode = nWRespCode;
	}

	public String getNWErrMsg() {
		return NWErrMsg;
	}

	public void setNWErrMsg(String nWErrMsg) {
		NWErrMsg = nWErrMsg;
	}

	public Integer getRecordCount() {
		return RecordCount;
	}

	public void setRecordCount(Integer recordCount) {
		RecordCount = recordCount;
	}

	public RestLog getRestLog() {
		return restLog;
	}

	public void setRestLog(RestLog restLog) {
		this.restLog = restLog;
	}

	public T getRecord() {
		return record;
	}

	public void setRecord(T record) {
		this.record = record;
	}
	
	
	
}
