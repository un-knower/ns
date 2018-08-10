package com.newsee.common.constant;


/**
 * Created by wangrenjie on 2017/9/27.
 */
public interface Constants {

    public static final String SEPARATOR_PATH = "/";
    
    public static final Integer DECIMAL_TRANS_LONG = 10000;

    /**
     * 结果表
     */
    public static final String COLLECTIONS_SYSTEM_MENU_RESULT = "caesar-system-menu-result";
    
    public static final String COLLECTIONS_SYSTEM_ROLE_RESULT = "caesar-system-role-result";

    public static final String COLLECTIONS_SYSTEM_DICTIONARY_RESULT = "caesar-system-dictionary-result";
    
    public static final String COLLECTIONS_SYSTEM_RESULT = "caesar-system-result";
    
    public static final String COLLECTIONS_SYSTEM_USER_LIST = "result_system_user_list";
    
    public static final String COLLECTIONS_ENTERPRISE_LIST = "enterprise_list"; 
    
    public static final String COLLECTIONS_CUSTOM_FORM_CLOUNM = "caesar-custom-form-clounm";
    
    public static final String COLLECTIONS_CUSTOMER_RESULT = "caesar-customer-result";
    /**
     * 排序
     */
    public static final String COLLECTIONS_ORDER_BY_DESC = "DESC";
    
    public static final String COLLECTIONS_ORDER_BY_ASC = "ASC";
    /**
     * 表头字段类型
     */
    public static final String FIELD_TYPE_TEXT = "text";
    public static final String FIELD_TYPE_NUMBER = "number";
    public static final String FIELD_TYPE_DATE = "date";
    public static final String FIELD_TYPE_SELECT = "select";
    /**
     * 菜单
     */
    public static final String PLATFORM_MENU = "platform_menu";
    /**
     * 角色
     */ 
    public static final String SYSTEM_ROLE = "system_role";
    /**不为空*/
    public static final String COMPARISON_NOT_NULL = "NOT_NULL";
    /**为空*/
    public static final String COMPARISON_NULL = "NULL";
    /**NOT_LIKE*/
    public static final String COMPARISON_NOT_LIKE = "NOT_LIKE";
    /**LIKE*/
    public static final String COMPARISON_LIKE = "LIKE";
    /**大于*/
    public static final String COMPARISON_GREATER_THAN = "GREATER_THAN";
    /**小于*/
    public static final String COMPARISON_LESS_THAN = "LESS_THAN";
    /**等于*/
    public static final String COMPARISON_EQUAL = "EQUAL";
    /**不等于*/
    public static final String COMPARISON_NOT_EQUAL = "NOT_EQUAL";
    /**大于等于*/
    public static final String COMPARISON_GREATER_EQUAL_THAN = "GREATER_EQUAL_THAN";
    /**大于等于*/
    public static final String COMPARISON_LESS_EQUAL_THAN = "LESS_EQUAL_THAN";
    public static final String RESULT_SUCCESS_RETURN = "success";
    public static final String RESULT_FAIL_RETURN = "fail";
    public static final String RESULT_TOTAL_RETURN = "total";
    
    
    //----------是否删除-----------
    /**删除*/
    public static final byte DELETE_YES = 1;
    /**不删除*/
    public static final byte DELETE_NO = 0;
    
    
    //----------前缀-----------
    /**公司前缀*/
    public static final String PRE_COMPANY = "company_";
    /**部门前缀*/
    public static final String PRE_DEPARTMENT = "department_";
    
    //----------是否在职-----------
    /**在职*/
    public static final String JOB_YES = "1";
    /**离职*/
    public static final String JOB_NO = "0";
    
    //----------是否激活-----------
    /**激活*/
    public static final String ACTIVE_YES = "1";
    /**未激活*/
    public static final String  ACTIVE_NO = "0";
    
    //----------操作类型-----------
    /**停用*/
    public static final String OPERATE_TYPE_STOP = "stop";
    /**启用*/
    public static final String OPERATE_TYPE_ENABLE= "enable";
    /**启用*/
    public static final String OPERATE_TYPE_LEAVE= "leave";
    
    //----------企业状态-----------
    /**正常*/
    public static final byte ENTERPRISE_NORMAL = 0;
    /**冻结*/
    public static final byte ENTERPRISE_FREEZE = 1;
    /**过期*/
    public static final byte ENTERPRISE_EXPIRE = 2;
    
   //----------产品服务状态-----------
    /**正常生效 1*/
    public static final String ORDER_PRODUCT_NORMAL = "1";
    /**即将过期 2*/
    public static final String ORDER_PRODUCT_WILL_EXPR = "2";
    /**已过期 3*/
    public static final String ORDER_PRODUCT_EXPRED = "3";
    
    //----------产品服务开通情况-----------
    /**待开通 0*/
    public static final String ORDER_PRODUCT_WAIT_OPEN = "0"; 
    /**开通 1*/
    public static final String ORDER_PRODUCT_OPEN = "1";
    /**关闭 2*/
    public static final String ORDER_PRODUCT_CLOSE = "2";
    
    /**
     * 角色缓存前缀
     */
//    public static String AUTHORITY_ROLE_PREFIX = "authority_role_";
    
    //----------TRUE/FALSE-----------
    public static final Byte TRUE = 1;
    public static final Byte FALSE = 0;
  //----------房产类型0.初始值 1.区域 2.项目 3.组团 4.楼栋 5.单元 6.房产 7.车库 8.车位 9.公共区域-----------
    public static final String HOUSE_TYPE_DEFAULT = "0";
    public static final String HOUSE_TYPE_AREA = "1";
    public static final String HOUSE_TYPE_PRECINCT = "2";
    public static final String HOUSE_TYPE_CLUSTER = "3";
    public static final String HOUSE_TYPE_BUILD = "4";
    public static final String HOUSE_TYPE_UNIT = "5";
    public static final String HOUSE_TYPE_HOUSE = "6";
    public static final String HOUSE_TYPE_GARAGE = "7";
    public static final String HOUSE_TYPE_CARPORT = "8";
    public static final String HOUSE_TYPE_PUBLICAREA = "9";
    
    //客户类型0个人 1企业
    public static final String OWNER_TYPE_PERSON = "0";
    public static final String OWNER_TYPE_ENTERPRISE = "1";
    
    //客户性质0业主 1租户 2住户 3开发商 4无房客户
    public static final String OWNER_PROPERTY_OWNER = "0";
    public static final String OWNER_PROPERTY_RENT = "1";
    public static final String OWNER_PROPERTY_HOUSEHOLD = "2";
    public static final String OWNER_PROPERTY_DEVOLOPER = "3";
    public static final String OWNER_PROPERTY_NONE = "4";
    
    //------单据类型---------
    /**工单*/
    public static final String GD = "GD";
    /**购买服务订单*/
    public static final String BP = "BP";
    
    //------组织类型---------
    /**集团*/
    public static final int ORG_TYPE_GROUP = 0;
    /**公司*/
    public static final int ORG_TYPE_COMPANY = 1;
    /**部门*/
    public static final int ORG_TYPE_DEPARTMENT = 2;
    
  //------层级---------
    /**高 0*/
    public static final int LEVEL_HIGH = 0;
    /**中高 1*/
    public static final int LEVEL_MEDIUM_HIGH = 1;
    /**中 2*/
    public static final int LEVEL_MEDIUM = 2;
    /**中低 3*/
    public static final int LEVEL_HIGH_LOW = 3;
    /**低 4*/
    public static final int LEVEL_LOW = 4;
    
    //------0：平台管理员，1：系统管理员，2：普通员工-------- 
    /**0：平台管理员*/
    public static final int USER_TYPE_PLAT = 0;
    /**1：系统管理员*/
    public static final int USER_TYPE_SYSTEM = 1;
    /**2：普通员工*/
    public static final int USER_TYPE_STAFF = 2;
    
    //------启用状态---------
    /**未启用*/
    public static final int ENABLE_NO = 1;
    /**已启用*/
    public static final int ENABLE_YES = 2;
    /**已停用*/
    public static final int ENABLE_STOP = 3;
    
    //------可见范围类型---------
    /**本人可见*/
    public static final int SEE_USER = 1;
    /**本部门可见*/
    public static final int SEE_DEPARTMENT = 2;
    /**本公司可见*/
    public static final int SEE_COMPANY= 3;
    /**本集团可见*/
    public static final int SEE_GROUP = 4;
    /**分管组织可见*/
    public static final int SEE_OTHER_ORGANIZATION = 5;
    
    //房态
//    public static final String STAGE_KONGZHI = "10";
//    public static final String STAGE_WEILING = "20";
//    public static final String STAGE_KONGGUAN = "30";
//    public static final String STAGE_RUZHU = "40";
    //装修状态
//    public static final String DECORATE_STAGE_NONE = "0";
//    public static final String DECORATE_STAGE_IN = "1";
//    public static final String DECORATE_STAGE_DONE = "2";

    //出租状态
//    public static final String RENT_STAGE_NONE = "0";
//    public static final String RENT_STAGE_IN = "1";
    
    //导入日志返回类型
    public static final Byte RESULT_SUCCESS = 1;
    public static final Byte RESULT_ERROR = -1;
    public static final Byte RESULT_WARN = 0;

    //------统计类型---------0不统计 1统计本页 2统计当前所有
    /**0不统计*/
    public static final int TOTAL_TYPE_NONE = 0;
    /**1统计本页*/
    public static final int TOTAL_TYPE_PAGE = 1;
    /**2统计当前所有*/
    public static final int TOTAL_TYPE_ALL = 2;
}
