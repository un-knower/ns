package com.newsee.common.constant;

/**
 * 业务列表返回Map时key定义常量类
 * @author xiaosisi add on 2017/09/04
 *
 */
public class BizListRequestConstants {

	/** 列表头部返回前端的map key*/
	public static final String LIST_HEADER_MAPKEY = "LIST_HEADER";
	
	/** 列表头部返回空白行的map key，用于列表空白行筛选*/
	public static final String LIST_BLANK_ROW_MAPKEY = "LIST_BLANK_ROW";
	
	public static final String EDIT_HEADER_TYPE = "请选择时间";
	/** 列表头部返回筛选器的map key，用于列表筛选器*/
	public static final String LIST_FILTERS_MAPKEY = "LIST_FILTERS";
	
	/** 用户菜单list行的map key */
	public static final String MENU_LIST_MAPKEY = "MENU_LIST";
	
	/** 用户所有二级菜单的map Key */
	public static final String MENU_CHILD_LIST_MAPKEY = "MENU_CHILD_LIST";
	
	/** 字段类型:text */
	public static final String HEADER_TYPE_TEXT = "text";
	
	/** 字段类型:select */
	public static final String HEADER_TYPE_SELECT = "select";
	
	/** 字段类型:cascader */
	public static final String HEADER_TYPE_CASCADER = "cascader";
	
	/** 字段类型:select */
	public static final String HEADER_TYPE_CHECKBOX = "checkbox";
	
	/** 字段类型:radio */
    public static final String HEADER_TYPE_RADIO = "radio";
	
	/** 字段类型:number */
	public static final String HEADER_TYPE_NUMBER = "number";
	
	/** 字段类型:date */
	public static final String HEADER_TYPE_DATE = "date";
	
	/** 排序类型,升序 */
	public static final String HEADER_OEDER_ASC = "asc";
	
	/** 排序类型,降序 */
	public static final String HEADER_OEDER_DESC = "desc";
	
	/** 比较条件 */
	public static final String HEADER_COMPARE_BLANK = "null";
	public static final String HEADER_COMPARE_NOT_BLANK = "not null";
	public static final String HEADER_COMPARE_CONTAIN = "like";
	public static final String HEADER_COMPARE_NOT_CONTAIN = "not like";
	public static final String HEADER_COMPARE_EQUALS = "=";
	public static final String HEADER_COMPARE_GREATER = ">";
	public static final String HEADER_COMPARE_LESS = "<";
	public static final String HEADER_COMPARE_GREATERE = ">=";
	public static final String HEADER_COMPARE_LESSE = "<=";
	
	/** 表单类型-基本表格  */
	public static final String FORM_TYPE_TABLE ="baseTable";
	
	/** 表单类型-分组框  */
	public static final String FORM_TYPE_GROUP ="group";
	
	/**自定义表单属性名字*/
	public static final String AUTO_FORM_PROPERTY_NAME = "autoForm";
}
