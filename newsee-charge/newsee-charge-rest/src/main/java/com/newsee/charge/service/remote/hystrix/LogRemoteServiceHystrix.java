package com.newsee.charge.service.remote.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.newsee.log.entity.BizLogEntity;
import com.newsee.log.entity.ImportLogEntity;
import com.newsee.log.entity.LoginLogEntity;
import com.newsee.log.entity.RequestLogEntity;
import com.newsee.charge.service.remote.ILogRemoteService;

/**
 * 
 * @Description: kafka消息发送短路器
 * @author: wangjun add on 2017/12/22
 * @date: 2017年8月16日上午10:40:26
 */
@Component
public class LogRemoteServiceHystrix implements ILogRemoteService{
	
	private static final Logger logger = LoggerFactory.getLogger(LogRemoteServiceHystrix.class);
    
	public void sendLoginLog(LoginLogEntity log){
		logger.error("△△△△△调用kafka消息发送服务失败△△△△△");
	}
	    
	 public void sendRequestLog(RequestLogEntity log){
		 logger.error("△△△△△调用kafka消息发送服务失败△△△△△");
	 }
	    
	 public void sendBizLog(BizLogEntity log){
		 logger.error("△△△△△调用kafka消息发送服务失败△△△△△");
	 }

    @Override
    public void sendImportLog(ImportLogEntity log) {
        logger.error("△△△△△调用kafka消息发送服务失败△△△△△");        
    }
}
