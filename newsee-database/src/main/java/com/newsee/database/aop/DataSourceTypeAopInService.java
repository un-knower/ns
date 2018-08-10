package com.newsee.database.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.newsee.database.constant.DataSourceTypeEnum;
import com.newsee.database.util.DataSourceTypeContextHolder;

/**
 * @Description: service 层 AOP拦截设置切换数据源
 * 必须在事务AOP之前执行，所以实现Ordered,order的值越小，越先执行
 * 如果一旦开始切换到写库，则之后的读都会走写库 
 * @author: 胡乾亮
 * @date: 2017年8月14日上午10:12:36
 */

@Aspect
@Component
@Order(0)
public class DataSourceTypeAopInService {

     @Before("execution(* com.newsee.*.service.impl..*.*(..)) and @annotation(com.newsee.database.annotation.ReadDataSource) ")  
     public void setReadDataSourceType() {  
         //如果已经开启写事务了，那之后的所有读都从写库读 ,所以在这里切换回读库 
         if(!DataSourceTypeEnum.write.getType().equals(DataSourceTypeContextHolder.getReadOrWrite())){  
             DataSourceTypeContextHolder.setRead();  
         }  
     }  
       
     @Before("execution(* com.newsee.*.service.impl..*.*(..)) and @annotation(com.newsee.database.annotation.WriteDataSource) ")  
     public void setWriteDataSourceType() {  
         DataSourceTypeContextHolder.setWrite();  
     }  
}
