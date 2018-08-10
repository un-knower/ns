package com.newsee.soss.config;

import com.github.pagehelper.PageHelper;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@MapperScan("com.newsee.soss.dao")
public class MybatisConfig implements TransactionManagementConfigurer{
	//mybaits mapper xml搜索路径
	private final static String mapperLocations="classpath:mapper/*.xml"; 
	//别名实体
	private final static String typeAliasPackage = "com.newsee.soss.entity";
	 
	@Autowired 
	private DataSource dataSource;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		/** 设置mybatis configuration 扫描路径 */
		//sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		/** 设置datasource */
		sqlSessionFactoryBean.setDataSource(dataSource);	
		/** 设置mapper的xml扫描 */
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
		/** 设置typeAlias 包扫描路径 */
		sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
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

	// 创建事务管理器
    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager(dataSource);
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		sqlSessionFactory = sqlSessionFactory();
		return new SqlSessionTemplate(sqlSessionFactory);
	}
 
}
