package com.newsee.system.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;

@Configuration
public class MybatisDataSource {
	
    @Value("${spring.datasource.type}")  
    private Class<? extends DataSource> dataSourceType; 
    
	//mybaits mapper xml搜索路径
	private final static String mapperLocations="classpath:mapper/*.xml"; 

	private org.apache.tomcat.jdbc.pool.DataSource pool;
	
    @Bean(name = "newsee_system")  
	@ConfigurationProperties(prefix = "spring.datasource")  
    public DataSource dataSource() {  
        return DataSourceBuilder.create().type(dataSourceType).build();  
    }
	
//	@Bean(destroyMethod = "close")
//	public DataSource dataSource() {		
//		DataSourceProperties config = dataSourceProperties;		
//		this.pool = new org.apache.tomcat.jdbc.pool.DataSource();		
//		this.pool.setDriverClassName(config.getDriverClassName());
//		this.pool.setUrl(config.getUrl());
//		if (config.getUsername() != null) {
//			this.pool.setUsername(config.getUsername());
//		}
//		if (config.getPassword() != null) {
//			this.pool.setPassword(config.getPassword());
//		}
//		this.pool.setInitialSize(config.getInitialSize());
//		this.pool.setMaxActive(config.getMaxActive());
//		this.pool.setMaxIdle(config.getMaxIdle());
//		this.pool.setMinIdle(config.getMinIdle());
//		this.pool.setTestOnBorrow(config.isTestOnBorrow());
//		this.pool.setTestOnReturn(config.isTestOnReturn());
//		this.pool.setValidationQuery(config.getValidationQuery());
//		return this.pool;
//	}

	@PreDestroy
	public void close() {
		if (this.pool != null) {
			this.pool.close();
		}
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
		 //分页插件  
        PageHelper pageHelper = new PageHelper();  
        Properties props = new Properties();  
        props.setProperty("reasonable", "true");  
        props.setProperty("supportMethodsArguments", "true");  
        props.setProperty("returnPageInfo", "check");  
        props.setProperty("params", "count=countSql");  
        pageHelper.setProperties(props);  
        //添加插件  
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");
		Map<String, String> initParameters = new HashMap<String, String>();
		// initParameters.put("loginUsername", "druid");// 用户�?
		// initParameters.put("loginPassword", "druid");// 密码
		initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功�?
		// initParameters.put("allow", "127.0.0.1"); // IP白名�? (没有配置或�?�为空，则允许所有访�?)
		// initParameters.put("deny", "192.168.20.38");// IP黑名�?
		// (存在共同时，deny优先于allow)
		servletRegistrationBean.setInitParameters(initParameters);
		return servletRegistrationBean;
	}
	

	/**
     * 注册FilterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        bean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean;
    }

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
