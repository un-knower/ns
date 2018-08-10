package com.newsee.common.constant;

public class RequestConstants {

	/** 客户端类型 */
    public final static String IOS = "ios";
    public final static String ANDROID = "android";
    public final static String WEIXIN = "weixin";
    public final static String PC = "pc";

    /** token */
    public final static String TOKEN = "token";
    public final static String TIME_MILLIS = "timeMillis";

    /** login */
    public final static String LOGIN_METHOD = "/login";
    public final static String LOGOUT_METHOD = "/logout";
    
    public final static String MUTIL_LOGIN_METHOD = "/mutil-enterprise-login";
    public final static String QUERY_USER_ENTERPRISE="/query-user-enterprise";

    /** 登录相关共通参数名称 */
    public final static String USERNAME = "username";
    public final static String USERACCOUNT = "userAccount";
    public final static String ENTERPRISEID = "enterpriseId";
    public final static String PASSWORD = "password";
    public final static String APP_ID = "appId";
    public final static String APP_CLIENT_TYPE = "appClientType";
    
    /** header中的共通参数 */
    public static final String HEADER_FUNC_ID = "funcId";
	public static final String HEADER_USER_ID = "userId";
	public static final String HEADER_USER_ROLEID = "roleId";
	public static final String HEADER_USER_COMPANYID = "companyId";
	/**区分一个funcId下面有多个不同的表单*/
	public static final String HEADER_INTERPRETER = "interpreter";
	/**0:新增 1：编辑*/
	public static final String HEADER_FORM_OPERATE_TYPE = "formOperateType";
	
	//用户所属集团或分公司级别的组织id
	public static final String HEADER_USER_COMPANYLEVEL_ORGANIZATIONID = "companyLevelOrganizationId";
	//用户所属集团或分公司级别的组织id
    public static final String HEADER_USER_GROUPLEVEL_ORGANIZATIONID = "groupLevelOrganizationId";
	//用户当前的组织id
	public static final String HEADER_USER_CURRENT_ORGANIZATIONID = "organizationId";
	public static final String HEADER_USER_NAME = "userName";
	public static final String HEADER_USER_COMPANYNAME = "companyName";
	public static final String HEADER_USER_ENTERPRISEID = "enterpriseId";
	
	/** 其他 */
	public static final String HEADER_USER_AGENT = "User-Agent";
	public static final String CAESAR_APP_NAME = "caesar";
}
