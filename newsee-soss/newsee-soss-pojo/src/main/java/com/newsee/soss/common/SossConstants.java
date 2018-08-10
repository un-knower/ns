package com.newsee.soss.common;

public abstract class SossConstants {

	public static final String PRODUCT_TYPE = "productType";
	/**工单状态*/
	public static final String SERVICE_STATUS = "status";
	
	/**待处理*/
	public static final String SERVICE_STATUS_VALUE_WAIT = "0";
	/**处理中*/
	public static final String SERVICE_STATUS_VALUE_DOING = "1";
	/**已完成*/
	public static final String SERVICE_STATUS_VALUE_OVER = "2";
	/**待确认*/
	public static final String SERVICE_STATUS_VALUE_CONFIRM = "3";
	/**待评价*/
	public static final String SERVICE_STATUS_VALUE_EVLUING = "4";
	/**已评价*/
	public static final String SERVICE_STATUS_VALUE_EVLUED = "5";
	/**注册用户上线*/
	public static final Integer REGISTER_USER_LIMIT =3;
	
}
