package com.newsee.database.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;
import com.newsee.database.constant.DataSourceEnum;
import com.newsee.database.constant.DataSourceTypeEnum;
import com.newsee.database.util.DataSourceContextHolder;
import com.newsee.database.util.DataSourceTypeContextHolder;

@Configuration
@AutoConfigureAfter(DataSourceConfiguration.class)
@Component
@RefreshScope
public class MybatisConfiguration implements TransactionManagementConfigurer{
	
	private final static Logger log = LoggerFactory.getLogger(MybatisConfiguration.class);
	
	@Value("${mysql.datasource.readSize}")  
    private String readDataSourceSize; 
    
    @Value("${mysql.datasource.mapperLocations}")  
    private String mapperLocations;
    
    @Value("${mysql.datasource.configLocation}")  
    private String configLocation;
    
    /**DB00写库*/
    @Autowired  
    @Qualifier("DB00writeDataSource")  
    private DataSource DB00writeDataSource;
    
    /**DB00读库*/
    @Autowired
    @Qualifier("DB00readDataSource01")  
    private DataSource DB00readDataSource01;
    
    /**DB01写库*/
    @Autowired  
    @Qualifier("DB01writeDataSource")  
    private DataSource DB01writeDataSource;
    
    /**DB01读库*/
    @Autowired
    @Qualifier("DB01readDataSource01")  
    private DataSource DB01readDataSource01;
    
    /**DB02写库*/
    @Autowired  
    @Qualifier("DB02writeDataSource")  
    private DataSource DB02writeDataSource;
    
    /**DB02读库*/
    @Autowired
    @Qualifier("DB02readDataSource01")  
    private DataSource DB02readDataSource01;
    
	
	@Bean(name="sqlSessionFactory")
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {		
		log.info("--------------------  sqlSessionFactory init ---------------------");
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy());
		//sqlSessionFactoryBean.setTypeAliasesPackage("com.newsee.owner.entity");
		//设置mapper.xml文件所在位置   
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
		//设置mybatis-config.xml配置文件位置  
		sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
		//分页插件  
        PageHelper pageHelper = new PageHelper();  
        Properties props = new Properties();  
        props.setProperty("reasonable", "true");  
        props.setProperty("supportMethodsArguments", "false"); 
        props.setProperty("returnPageInfo", "check");  
        props.setProperty("params", "count=countSql");  
        pageHelper.setProperties(props);  
        //添加插件  
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});  
		return sqlSessionFactoryBean.getObject();
	}
	
	/**
	 * @Description: 把所有数据源都放在路由中
	 * @author: 胡乾亮
	 * @date: 2017年8月15日下午2:44:33
	 */
	@Bean(name="roundRobinDataSouceProxy")  
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {  
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();  
        //把所有数据库都放在targetDataSources中,注意key值要和determineCurrentLookupKey()中代码写的一至，  
        //否则切换数据源时找不到正确的数据源  
        targetDataSources.put(DataSourceEnum.DB00.getValue()+DataSourceTypeEnum.write.getType(), DB00writeDataSource);  
        targetDataSources.put(DataSourceEnum.DB00.getValue()+DataSourceTypeEnum.read.getType()+"1", DB00readDataSource01); 
        targetDataSources.put(DataSourceEnum.DB01.getValue()+DataSourceTypeEnum.write.getType(), DB01writeDataSource);
        targetDataSources.put(DataSourceEnum.DB01.getValue()+DataSourceTypeEnum.read.getType()+"1", DB01readDataSource01);
        targetDataSources.put(DataSourceEnum.DB02.getValue()+DataSourceTypeEnum.write.getType(), DB02writeDataSource);
        targetDataSources.put(DataSourceEnum.DB02.getValue()+DataSourceTypeEnum.read.getType()+"1", DB02readDataSource01);
        //路由类，寻找对应的数据源  
        AbstractRoutingDataSource proxy = new AbstractRoutingDataSource(){  
             private AtomicInteger count = new AtomicInteger(0);  
            /** 
             * 这是AbstractRoutingDataSource类中的一个抽象方法， 
             * 而它的返回值是你所要用的数据源dataSource的key值，有了这个key值， 
             * targetDataSources就从中取出对应的DataSource，如果找不到，就用配置默认的数据源。 
             */  
            @Override  
            protected Object determineCurrentLookupKey() {
            	Object result = null;
                String typeKey = DataSourceTypeContextHolder.getReadOrWrite();  
                String dataSourceKey = DataSourceContextHolder.getDataSource();
                  
                if(dataSourceKey == null || typeKey == null){  
                    throw new NullPointerException("数据库路由时，决定使用哪个数据源和哪个数据库源类型都不能为空...");  
                }else{
                	switch(dataSourceKey){
                		case "DB00": 
						result = changeDataSource(typeKey, dataSourceKey);
                			break;
                		case "DB01":
						result = changeDataSource(typeKey, dataSourceKey);
                			break;
                		case "DB02": 
						result = changeDataSource(typeKey, dataSourceKey);
                			break;
                		default:
						result = changeDataSource(typeKey, dataSourceKey);
                			break;
                	}
                }
                return result;
            }
            
			private Object changeDataSource(String typeKey, String dataSourceKey) {
				Object result;
				final int readSize00 = Integer.parseInt(readDataSourceSize);
				if (typeKey.equals(DataSourceTypeEnum.write.getType())){  
				    System.out.println("使用数据库"+dataSourceKey+"write.............");  
				    result = dataSourceKey+DataSourceTypeEnum.write.getType();  
				}else{
					 //读库， 简单负载均衡  
				    int number = count.getAndAdd(1); 
				    int lookupKey = number % readSize00;  
				    System.out.println("使用数据库"+dataSourceKey+"read-"+(lookupKey+1));  
				    result = dataSourceKey+DataSourceTypeEnum.read.getType()+(lookupKey+1);  
				}
				return result;
			}  
        };  
          
        proxy.setDefaultTargetDataSource(DB00writeDataSource);//默认库  
        proxy.setTargetDataSources(targetDataSources);  
        return proxy;  
    }  
	
	@Bean  
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {  
        return new SqlSessionTemplate(sqlSessionFactory);  
    }  
	
	/**
	 * 数据库监控
	 */
	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");
		Map<String, String> initParameters = new HashMap<String, String>();
		// initParameters.put("loginUsername", "druid");// 用户名
		// initParameters.put("loginPassword", "druid");// 密码
		initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
		// initParameters.put("allow", "127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)
		// initParameters.put("deny", "192.168.20.38");// IP黑名单
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
      
    //事务管理  
    @Bean 
//    @ConditionalOnMissingBean  
    public DataSourceTransactionManager transactionManager() {  
        System.out.println("DB00transactionManager事务管理");
    	return new DataSourceTransactionManager(roundRobinDataSouceProxy()); 
    	//return new DataSourceTransactionManager((DataSource)springContextUtil.getBean("roundRobinDataSouceProxy"));  
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        // TODO Auto-generated method stub
        return transactionManager();
    }
    
//    @Bean 
//    @ConditionalOnMissingBean  
//    public DataSourceTransactionManager DB01transactionManager() {  
//        System.out.println("DB01transactionManager事务管理");
//    	return new DataSourceTransactionManager(DB01writeDataSource); 
//    }
//    
//    @Bean 
//    @ConditionalOnMissingBean  
//    public DataSourceTransactionManager DB02transactionManager() {  
//        System.out.println("DB02transactionManager事务管理");
//    	return new DataSourceTransactionManager(DB02writeDataSource); 
//    }
}
