package com.newsee.database.aop;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.newsee.database.util.DataSourceContextHolder;

/**
 * @Description: AOP 拦截controller层通过租户的company_id对数据库的个数取模来给该租户分配对应的数据库
 * @author: 胡乾亮
 * @date: 2017年8月21日下午2:39:51
 */

@Aspect
@Component
@Order(0)
public class DataSourceAopInController {
	
	private static final int DB_COUNT = 3;

	@Before("execution(* com.newsee.*.controller..*.*(..)) ")  
	public void setDataSource() { 
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		int num = 0;
		if (!Objects.isNull(request.getHeader("enterpriseId"))) {
		    int enterpriseId = Integer.parseInt(request.getHeader("enterpriseId"));
		    num = enterpriseId % DB_COUNT;
        }
		switch(num){
			case 0: 
				DataSourceContextHolder.setDB00();
				break;
			case 1: 
				DataSourceContextHolder.setDB01();
				break;
			case 2: 
				DataSourceContextHolder.setDB02();
				break;
			default:
				DataSourceContextHolder.setDB00();
				break;
		}
		
    }  
}
