package com.newsee.database.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: 获取spring容器，以访问容器中定义的其他bean 
 * @author: 胡乾亮
 * @date: 2017年8月15日下午2:58:41
 */
@Component 
public class SpringContextUtil implements ApplicationContextAware {

	@Autowired  
    private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 this.applicationContext = applicationContext;  
	}

	public ApplicationContext getApplicationContext() {  
        return this.applicationContext;  
    } 
	
	public Object getBean(String name) throws BeansException {  
        return applicationContext.getBean(name);  
    }
	
	public Object getBean(Class<?> obj) throws BeansException {  
        return applicationContext.getBean(obj);
    } 
}
