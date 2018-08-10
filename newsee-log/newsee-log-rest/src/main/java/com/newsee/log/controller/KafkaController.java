package com.newsee.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.newsee.common.constant.KafkaTopicConstants;
import com.newsee.log.entity.BizLogEntity;
import com.newsee.log.entity.ImportLogEntity;
import com.newsee.log.entity.LoginLogEntity;
import com.newsee.log.entity.RequestLogEntity;
import com.newsee.log.service.IKafkaMessageSendService;

@RestController
@ResponseBody
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private IKafkaMessageSendService kafkaMessageSendService;
    
    /**
     * 发送请求日志消息到kafka消息队列
     * @param log 登录登出日志对象
     * @throws Exception
     * @author xiaosisi add on 2017/08/15
     */
    @RequestMapping(value="/send-login-log", method=RequestMethod.POST)
    public void sendLoginLog(@RequestBody LoginLogEntity log){
    	kafkaMessageSendService.sendLoginLog(log, KafkaTopicConstants.NEWSEE_LOGIN_LOG_TOPIC);
    }
    
    /**
     * 发送请求日志消息到kafka消息队列
     * @param requestLog 请求日志对象
     * @throws Exception
     * @author xiaosisi add on 2017/08/15
     */
    @RequestMapping(value="/send-request-log", method=RequestMethod.POST)
    public void sendRequestLog(@RequestBody RequestLogEntity log){
    	kafkaMessageSendService.sendRequestLog(log, KafkaTopicConstants.NEWSEE_REQUEST_LOG_TOPIC);
    }
    
    /**
     * 发送请求日志消息到kafka消息队列
     * @param log 业务日志对象
     * @author xiaosisi add on 2017/08/15
     */
    @RequestMapping(value="/send-biz-log", method=RequestMethod.POST)
    public void sendBizLog(@RequestBody BizLogEntity log){
    	kafkaMessageSendService.sendBizLog(log, KafkaTopicConstants.NEWSEE_BIZ_LOG_TOPIC);
    }
    
    /**
     * 发送请求日志消息到kafka消息队列
     * @param log 业务日志对象
     * @author xiaosisi add on 2017/08/15
     */
    @RequestMapping(value="/send-import-log", method=RequestMethod.POST)
    public void sendImportLog(@RequestBody ImportLogEntity log){
        kafkaMessageSendService.sendImportLog(log, KafkaTopicConstants.NEWSEE_IMPORT_LOG_TOPIC);
    }
}
