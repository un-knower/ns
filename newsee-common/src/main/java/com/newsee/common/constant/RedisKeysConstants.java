package com.newsee.common.constant;

/**
 * 缓存key变量共通类
 * @author xiaosisi add on 2017/11/13
 *
 */
public class RedisKeysConstants {

	/** redis缓存-缓存功能表单的key前缀，功能表单key规则:前缀+entipriseId+companyId+functionId */
	public static final String REDIS_FUNCTION_FIELDS_PREFIX = "FUNCTION_FIELDS_PREFIX";
	
	/** redis缓存-缓存功能表单的key前缀，功能表单key规则:前缀+entipriseId+companyId+functionId */
	public static final String REDIS_FUNCTION_INFO_PREFIX = "FUNCTION_INFO_PREFIX";
	
	/** redis缓存-缓存列表表头的key前缀，列表表头key规则:前缀+entipriseId+companyId+functionId */
	public static final String REDIS_COLUMN_PREFIX = "COLUMN_PREFIX";
	/** redis缓存 省/市/区数据*/
	public static final String REDIS_CACHE_PROVICE_CITY_CONTRY_PREFIX = "PROVICE_CITY_CONTRY_PREFIX";
	
	/** redis缓存-缓存登录用户的system中的user信息，key规则：前缀+appId+userid */
	public static final String REDIS_LOGININFO_USER_PREFIX ="REDIS_LOGININFO_USER_PREFIX_";
	
	/** redis缓存-缓存登录用户的oauth中的user信息，key规则：前缀+appId+userid */
	public static final String REDIS_LOGININFO_APP_USER_PREFIX ="REDIS_LOGININFO_APP_USER_PREFIX_";
	
	/** redis缓存-缓存登录用户的system中的user信息，key规则：前缀+userid */
	public static final String REDIS_LOGININFO_SYS_USER_PREFIX ="REDIS_LOGININFO_SYS_USER_PREFIX_";
	
	/** redis缓存-缓存登录用户的company信息，key规则：前缀+appId+companyid */
	public static final String REDIS_LOGININFO_COMPANY_PREFIX = "REDIS_LOGININFO_COMPANY_PREFIX_";
	
	/** redis缓存-缓存登录用户的enterprise信息，key规则：前缀+appId+enterpriseid */
	public static final String REDIS_LOGININFO_ENTERPRISE_PREFIX = "REDIS_LOGININFO_ENTERPRISE_PREFIX_";
	
	/**redis缓存-缓存登录人菜单信息，key规则：前缀+appId+enterpriseId+userId*/
	public static final String REDIS_LOGININFO_MENU_PREFIX = "REDIS_LOGININFO_MENU_PREFIX_";
	
	/**redis缓存-缓存超级管理员的权限，*/
    public static final String REDIS_LOGININFO_MENU_BUTTON_PREFIX = "REDIS_LOGININFO_MENU_BUTTON_PREFIX_";
	
	/**用于业务日志记录比较用*/
	public static final String REDIS_RESTLOG_DETAIL_PREFIX = "REDIS_RESTLOG_DETAIL_PREFIX_";
	
	/**用于业务日志记录比较用*/
	public static final String REDIS_RESTLOG_EDIT_PREFIX = "REDIS_RESTLOG_EDIT_PREFIX_";
	
    /**redis缓存-缓存房产树，key规则：前缀+enterpriseId+companyId*/
    public static final String REDIS_HOUSE_TREE_PREFIX = "REDIS_HOUSE_TREE_PREFIX_";
    
    /**redis缓存-企业注册手机验证码*/
    public static final String REDIS_REGISTER_PHONE_CODE_PREFIX = "REGISTER_PHONE_CODE_PREFIX_";
    
    /**redis缓存-企业注册验证企业*/
    public static final String REDIS_REGISTER_ENTERPRISE_PREFIX = "REGISTER_ENTERPRISE_PREFIX_";
	
}
