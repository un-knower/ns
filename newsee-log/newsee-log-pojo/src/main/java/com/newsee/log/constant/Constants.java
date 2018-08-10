package com.newsee.log.constant;

/**
 * Created by niyang on 2017/8/18.
 */
public interface Constants {
    /**
     * collections
     */
    public static final String COLLECTIONS_SYSTEM = "caesar-system-log";
    public static final String COLLECTIONS_REQUEST = "caesar-request-log";
    public static final String COLLECTIONS_BUSSINESS = "caesar-bussiness-log";

    /**
     * system_log column
     */
    public static final String SYSTEM_COLUMN_SYSTEM_LOG = "systemLog";
    public static final String SYSTEM_COLUMN_APP_NAME = "appName";
    public static final String SYSTEM_COLUMN_DATE = "date";
    public static final String SYSTEM_COLUMN_LOG_LEVEL = "logLevel";

    /**
     * request_log column
     */
    public static final String REQUEST_COLUMN_USER_ID = "userId";
    public static final String REQUEST_COLUMN_USER_NAME = "userName";
    public static final String REQUEST_COLUMN_APP_NAME = "appName";
    public static final String REQUEST_COLUMN_IP_ADDRESS = "ipAddress";
    public static final String REQUEST_COLUMN_REQUEST_PATH = "requestPath";
    public static final String REQUEST_COLUMN_REQUEST_PARAMS = "requestParams";
    public static final String REQUEST_COLUMN_REQUEST_DATE = "requestDate";
    /**
     * bussiness_log column
     */
    public static final String BUSSINESS_COLUMN_USER_ID = "userId";
    public static final String BUSSINESS_COLUMN_USER_NAME = "userName";
    public static final String BUSSINESS_COLUMN_OPERATE_TYPE = "operateType";
    public static final String BUSSINESS_COLUMN_OPERATE_NAME = "operateName";
    public static final String BUSSINESS_COLUMN_MEMU_ID = "memuId";
    public static final String BUSSINESS_COLUMN_MEMU_NAME = "menuName";
    public static final String BUSSINESS_COLUMN_OPERATE_DATE = "operateDate";
    public static final String BUSSINESS_COLUMN_REMARK = "remark";
    
}
