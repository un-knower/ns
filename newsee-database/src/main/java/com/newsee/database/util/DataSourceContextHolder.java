package com.newsee.database.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newsee.database.constant.DataSourceEnum;

/**
 * @Description: 数据库切换
 * @author: 胡乾亮
 * @date: 2017年8月21日下午3:56:00
 */
public class DataSourceContextHolder {

	
	private final static Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

	//线程本地环境
	private static final ThreadLocal<String> localDataSource = new ThreadLocal<String>();  
	  
    public static ThreadLocal<String> getLocal() {  
        return localDataSource;  
    }
    
    public static void setDB00() {  
    	localDataSource.set(DataSourceEnum.DB00.getValue());
        log.info("数据库切换到DB00...");
    }  
    
    public static void setDB01() {  
    	localDataSource.set(DataSourceEnum.DB01.getValue());
        log.info("数据库切换到DB01...");
    }  
    
    public static void setDB02() {  
    	localDataSource.set(DataSourceEnum.DB02.getValue());
        log.info("数据库切换到DB02...");
    }  
    
    public static String getDataSource() {  
        return localDataSource.get();  
    }  
    
    public static void clear(){  
    	localDataSource.remove();  
    }  
}
