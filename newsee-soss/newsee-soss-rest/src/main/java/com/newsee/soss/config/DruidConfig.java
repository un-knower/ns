package com.newsee.soss.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {
	private final static Logger logger = LoggerFactory.getLogger(DruidConfig.class);
	
	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.initialSize}")
	private int initialSize;

	@Value("${spring.datasource.minIdle}")
	private int minIdle;

	@Value("${spring.datasource.maxActive}")
	private int maxActive;

	@Value("${spring.datasource.maxWait}")
	private int maxWait;

	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.validationQuery}")
	private String validationQuery;

	@Value("${spring.datasource.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.testOnReturn}")
	private boolean testOnReturn;

	@Value("${spring.datasource.poolPreparedStatements}")
	private boolean poolPreparedStatements;

	@Value("${spring.datasource.filters}")
	private String filters;
	
	@Bean
    @Primary
    public DataSource druidDataSource(){
    	DruidDataSource datasource = new DruidDataSource(); 
	    datasource.setUrl(dbUrl); 
	    datasource.setUsername(username); 
	    datasource.setPassword(password); 
	    datasource.setDriverClassName(driverClassName); 
	    datasource.setInitialSize(initialSize); 
	    datasource.setMinIdle(minIdle); 
	    datasource.setMaxActive(maxActive); 
	    datasource.setMaxWait(maxWait); 
	    datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis); 
	    datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis); 
	    datasource.setValidationQuery(validationQuery); 
	    datasource.setTestWhileIdle(testWhileIdle); 
	    datasource.setTestOnBorrow(testOnBorrow); 
	    datasource.setTestOnReturn(testOnReturn); 
	    datasource.setPoolPreparedStatements(poolPreparedStatements); 
	    try { 
	    	datasource.setFilters(filters); 
	    } catch (SQLException e) { 
	    	logger.error("druid configuration initialization filter", e);
	    } 
    	return datasource; 
    }
	 
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");
		Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
		// (存在共同时，deny优先于allow)
		servletRegistrationBean.setInitParameters(initParameters);
		return servletRegistrationBean;
	}

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        bean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        bean.addInitParameter("exclusions","*.html,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean;
    }
}
