package com.newsee.oauth.service.remote.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newsee.log.entity.BizLogEntity;
import com.newsee.log.entity.LoginLogEntity;
import com.newsee.log.entity.RequestLogEntity;
import com.newsee.oauth.service.remote.ILogRemoteService;

@Component
public class LogRemoteServiceHystrix implements ILogRemoteService {

	private final static Logger logger = LoggerFactory.getLogger(LogRemoteServiceHystrix.class);
	/*@RequestMapping(value="/kafka/send-login-log", method=RequestMethod.POST)
	public void sendLoginLog(LoginLogEntity log){
		logger.error("△△△△△调用kafka消息发送登录登出日志服务失败△△△△△");
	}
	    
	 @RequestMapping(value="/kafka/send-request-log", method=RequestMethod.POST)
	 public void sendRequestLog(RequestLogEntity log){
		 logger.error("△△△△△调用kafka消息发送请求日志服务失败△△△△△");
	 }
	    
	 @RequestMapping(value="/kafka/send-biz-log", method=RequestMethod.POST)
	 public void sendBizLog(BizLogEntity log){
		 logger.error("△△△△△调用kafka消息发送业务日志服务失败△△△△△");
	 }*/

    @Override
    public void sendLoginLog(LoginLogEntity log) {
        logger.error("△△△△△调用kafka消息发送登录登出日志服务失败△△△△△");
    }

    @Override
    public void sendRequestLog(RequestLogEntity log) {
        logger.error("△△△△△调用kafka消息发送请求日志服务失败△△△△△");
        
    }

    @Override
    public void sendBizLog(BizLogEntity log) {
        logger.error("△△△△△调用kafka消息发送业务日志服务失败△△△△△");
        
    }

}
