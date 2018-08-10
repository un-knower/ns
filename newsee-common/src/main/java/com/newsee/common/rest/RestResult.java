package com.newsee.common.rest;

import java.io.Serializable;

/**
 * rest返回结果类
 * @author 肖斯斯  ADD ON 2017/08/11
 *
 */
public class RestResult<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**操作成功*/
	public static final RestResult SUCCESS = new RestResult(
			ResultCodeEnum.SUCCESS.CODE, ResultCodeEnum.SUCCESS.DESC);
	
	/**非法请求*/
	public static final RestResult ILLEGAL_REQUEST = new RestResult(
			ResultCodeEnum.ILLEGAL_REQUEST.CODE, ResultCodeEnum.ILLEGAL_REQUEST.DESC);
	
	/**未登录*/
	public static final RestResult NOT_LOGIN = new RestResult(
			ResultCodeEnum.NOT_LOGIN.CODE, ResultCodeEnum.NOT_LOGIN.DESC);
	
	/**无权限*/
	public static final RestResult UNAUTHORIZED = new RestResult(
			ResultCodeEnum.UNAUTHORIZED.CODE, ResultCodeEnum.UNAUTHORIZED.DESC);
	
	/**系统内部错误*/
	public static final RestResult SERVER_ERROR = new RestResult(
			ResultCodeEnum.SERVER_ERROR.CODE, ResultCodeEnum.SERVER_ERROR.DESC);
	
	/**用户名或密码错误*/
	public static final RestResult PASSWORD_ERROR = new RestResult(
			ResultCodeEnum.PASSWORD_ERROR.CODE,ResultCodeEnum.PASSWORD_ERROR.DESC);
	
	/**参数错误*/
	public static final RestResult PARAMS_ERROR = new RestResult(
			ResultCodeEnum.PARAMS_ERROR.CODE,ResultCodeEnum.PARAMS_ERROR.DESC);
	
	/**参数缺失*/
	public static final RestResult PARAMS_MISSING = new RestResult(
			ResultCodeEnum.PARAMS_MISSING.CODE,ResultCodeEnum.PARAMS_MISSING.DESC);
	
	/**并发数据操作*/
	public static final RestResult CONCURRENT_DATA = new RestResult(
			ResultCodeEnum.CONCURRENT_DATA.CODE,ResultCodeEnum.CONCURRENT_DATA.DESC);
	
	/**操作失败*/
	public static final RestResult FAILURE = new RestResult(
			ResultCodeEnum.FAILURE.CODE,ResultCodeEnum.FAILURE.DESC);
	
	/**数据不存在*/
	public static final RestResult DATA_NOT_EXIST = new RestResult(
			ResultCodeEnum.DATA_NOT_EXIST.CODE, ResultCodeEnum.DATA_NOT_EXIST.DESC);
	
	/** 错误code*/
	private String resultCode;
	
	/** 错误说明 */
	private String resultMsg = "";
	
	/**业务日志记录使用*/
	private RestLog restLog;
	
	/** 数据实体 */
	private T resultData;

	private Integer pageNum;
	private Integer pageSize;
	private Long total;
	public RestResult(T resultData) {
		this.resultCode = ResultCodeEnum.SUCCESS.CODE;
		this.resultMsg = ResultCodeEnum.SUCCESS.DESC;
		this.resultData = resultData;
	}

	public RestResult() {
	}

	public RestResult(String code, String resultMsg) {
		this.resultCode = code;
		this.resultMsg = resultMsg;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

	public RestLog getRestLog() {
		return restLog;
	}

	public void setRestLog(RestLog restLog) {
		this.restLog = restLog;
	}
}
