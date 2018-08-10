package com.newsee.database.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: 数据源配置
 * @author: 胡乾亮
 * @date: 2017年8月15日下午1:40:17
 */
@Configuration
@EnableTransactionManagement
@RefreshScope
@Component
public class DataSourceConfiguration {

	 private final static Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);
	 
	 @Value("${mysql.datasource.type}")  
	 private Class<? extends DataSource> dataSourceType;
	 
	 /**
	  * @Description: 写库 数据源配置
	  * @author: 胡乾亮
	  * @date: 2017年8月15日下午1:46:08
	  */
	 @Bean(name = "DB00writeDataSource")  
     @Primary  
     @ConfigurationProperties(prefix = "mysql.datasource.DB00write", exceptionIfInvalid=false)  
     public DataSource writeDataSource() {  
         log.info("-------------------- DB00writeDataSource init ---------------------");  
         return DataSourceBuilder.create().type(dataSourceType).build();  
     }  
	 
	 /**
	  * @Description: 读库 数据源配置
	  * @author: 胡乾亮
	  * @date: 2017年8月15日下午1:48:24
	  */
     @Bean(name = "DB00readDataSource01")  
     @ConfigurationProperties(prefix = "mysql.datasource.DB00read01", exceptionIfInvalid=false)  
     public DataSource readDataSourceOne() {  
         log.info("-------------------- DB00read01DataSourceOne init ---------------------");  
         return DataSourceBuilder.create().type(dataSourceType).build();  
     }  
     
     /**
	  * @Description: 写库 数据源配置
	  * @author: 胡乾亮
	  * @date: 2017年8月15日下午1:46:08
	  */
	 @Bean(name = "DB01writeDataSource")  
     //@Primary  
     @ConfigurationProperties(prefix = "mysql.datasource.DB01write", exceptionIfInvalid=false)  
     public DataSource DB01writeDataSource() {  
         log.info("-------------------- DB01writeDataSource init ---------------------");  
         return DataSourceBuilder.create().type(dataSourceType).build();  
     }  
	 
	 /**
	  * @Description: 读库 数据源配置
	  * @author: 胡乾亮
	  * @date: 2017年8月15日下午1:48:24
	  */
     @Bean(name = "DB01readDataSource01")  
     @ConfigurationProperties(prefix = "mysql.datasource.DB01read01", exceptionIfInvalid=false)  
     public DataSource DB01readDataSourceOne() {  
         log.info("-------------------- DB01read01DataSourceOne init ---------------------");  
         return DataSourceBuilder.create().type(dataSourceType).build();  
     }  
     
     /**
	  * @Description: 写库 数据源配置
	  * @author: 胡乾亮
	  * @date: 2017年8月15日下午1:46:08
	  */
	 @Bean(name = "DB02writeDataSource")  
     //@Primary  
     @ConfigurationProperties(prefix = "mysql.datasource.DB02write", exceptionIfInvalid=false)  
     public DataSource DB02writeDataSource() {  
         log.info("-------------------- DB02writeDataSource init ---------------------");  
         return DataSourceBuilder.create().type(dataSourceType).build();  
     }  
	 
	 /**
	  * @Description: 读库 数据源配置
	  * @author: 胡乾亮
	  * @date: 2017年8月15日下午1:48:24
	  */
     @Bean(name = "DB02readDataSource01")  
     @ConfigurationProperties(prefix = "mysql.datasource.DB02read01", exceptionIfInvalid=false)  
     public DataSource DB02readDataSourceOne() {  
         log.info("-------------------- DB02read01DataSourceOne init ---------------------");  
         return DataSourceBuilder.create().type(dataSourceType).build();  
     }  
	 
	 
}
