package com.newsee.owner.service.remote;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.newsee.log.entity.BizLogEntity;
import com.newsee.log.entity.ImportLogEntity;
import com.newsee.log.entity.LoginLogEntity;
import com.newsee.log.entity.RequestLogEntity;
import com.newsee.owner.service.remote.hystrix.LogRemoteServiceHystrix;

@FeignClient(value = "log-server", fallback=LogRemoteServiceHystrix.class)
public interface ILogRemoteService {
	
	 @RequestMapping(value="/kafka/send-login-log", method=RequestMethod.POST)
	 public void sendLoginLog(@RequestBody LoginLogEntity log);
	    
	 @RequestMapping(value="/kafka/send-request-log", method=RequestMethod.POST)
	 public void sendRequestLog(@RequestBody RequestLogEntity log);
	    
	 @RequestMapping(value="/kafka/send-biz-log", method=RequestMethod.POST)
	 public void sendBizLog(@RequestBody BizLogEntity log);
	 
	 @RequestMapping(value="/kafka/send-import-log", method=RequestMethod.POST)
     public void sendImportLog(@RequestBody ImportLogEntity log);
}
