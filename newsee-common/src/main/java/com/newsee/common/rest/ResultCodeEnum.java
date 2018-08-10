package com.newsee.common.rest;

/**
 * 返回结果枚举
 * @author 肖斯斯  ADD ON 2017/08/11
 *
 */
public enum ResultCodeEnum {
	//成功
	SUCCESS("200", "操作成功。"),
	ILLEGAL_REQUEST("201", "非法请求。"),
	
	//未登录
	NOT_LOGIN("401", "您还未登录，请登录。"),
	//无权限
	UNAUTHORIZED("402", "您无权限执行该操作，请联系管理员开通相关权限。"), 
	
	//系统内部错误
	SERVER_ERROR("500", "服务未启动！请稍后再试！"), 
	DB_ERROR("501", "数据库操作异常，请联系管理员及时处理。"),
	HYSTRIX_TIMEOUT("510","服务连接超时"),
	HYSTRIX_IO_ERROR("511","服务还未启动成功"),
	HYSTRIX_ERROR("512","没有可调用服务"),

	//远程服务错误
	RIBBON_TIMEOUT("513","服务连接超时"),
	RIBBON_IO_ERROR("514","服务还未启动成功"),
	RIBBON_ERROR("515","没有可调用服务"),

	//数据错误
	PASSWORD_ERROR("601", "您输入的用户名或密码错误。"),
	PARAMS_ERROR("602", "参数错误。"),
	PARAMS_MISSING("603", "参数缺失。"),
	DATA_NOT_EXIST("604", "数据不存在。"),
	CONCURRENT_DATA("605", "其他用户正在操作此数据，请刷新后重试。"),
	  DATA_EXIST("606", "数据已存在。"),
	DATA_LIMIT("607","该账号注册企业已达到三家"),
	DATA_ENABLE_ACTIVE("608","账号被停用"),
	DATA_IS_DELETED("609","账号已经被删除"),
	
    REGISTER_SUCCESS("700", "注册成功!"),
	VERIFICATE_CODE_EXPIRATION("701", "验证码已过期"),
	VERIFICATE_CODE_ERROR("702", "验证码错误"),
	REGISTER_PASSWORD("703", "密码格式或长度不正确"),
	REGISTER_CONFIRM_PASSWORD("704", "确认密码与密码不符"),
	ENTERPRISE_NAME_NULL("705", "企业名称为空"),
	ENTERPRISE_NAME_EXISTS("706", "您已是该企业成员"),
	FAILURE("-4000", "操作失败");
	
	//返回结果code
	public String CODE;

	//返回结果备注
	public String DESC;

	ResultCodeEnum(String code, String desc) {
		this.CODE = code;
		this.DESC = desc;
	}

}
