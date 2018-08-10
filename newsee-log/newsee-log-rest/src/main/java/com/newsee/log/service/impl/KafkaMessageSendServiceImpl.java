package com.newsee.log.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.newsee.common.utils.StringUtils;
import com.newsee.log.entity.BizLogEntity;
import com.newsee.log.entity.ImportLogEntity;
import com.newsee.log.entity.LoginLogEntity;
import com.newsee.log.entity.RequestLogEntity;
import com.newsee.log.listener.KafkaTopicListener;
import com.newsee.log.service.IKafkaMessageSendService;

@Service
public class KafkaMessageSendServiceImpl implements IKafkaMessageSendService{

    /**
     * 请求日志消息发送至kafka消息中心指定的topic
     * @author xiaosisi add on 2017/08/15
     * @param requestLog 请求日志对象
     * @param topic 指定的topic，即消息队列
     */
	@Override
    public void sendLoginLog(LoginLogEntity log, String topic){
    	if(Objects.isNull(log) || StringUtils.isBlank(topic)){
    		return;
    	}
        KafkaTopicListener.template.send(topic, JSONObject.toJSONString(log));           
    }
	
	/**
     * 请求日志消息发送至kafka消息中心指定的topic
     * @author xiaosisi add on 2017/08/15
     * @param requestLog 请求日志对象
     * @param topic 指定的topic，即消息队列
     */
	@Override
    public void sendRequestLog(RequestLogEntity log, String topic){
    	if(Objects.isNull(log) || StringUtils.isBlank(topic)){
    		return;
    	}
        KafkaTopicListener.template.send(topic, JSONObject.toJSONString(log));           
    }
    
	
	/**
     * 请求日志消息发送至kafka消息中心指定的topic
     * @author xiaosisi add on 2017/08/15
     * @param requestLog 请求日志对象
     * @param topic 指定的topic，即消息队列
     */
	@Override
    public void sendBizLog(BizLogEntity log, String topic){
    	if(Objects.isNull(log) || StringUtils.isBlank(topic)){
    		return;
    	}
    	System.out.println("=========="+JSONObject.toJSONString(log));
        KafkaTopicListener.template.send(topic, JSONObject.toJSONString(log));           
    }

	/**
     * 请求日志消息发送至kafka消息中心指定的topic
     * @author wangrenjie add on 2018/01/29
     * @param requestLog 请求日志对象
     * @param topic 指定的topic，即消息队列
     */
    @Override
    public void sendImportLog(ImportLogEntity log, String topic) {
        if(Objects.isNull(log) || StringUtils.isBlank(topic)){
            return;
        }
        KafkaTopicListener.template.send(topic, JSONObject.toJSONString(log));      
    }
    
    
  /*  *//**
     * 业务日志消息发送至kafka消息中心指定的topic，用于新增，删除的简单日志处理
     * @param requestLog 请求日志对象
     * @param topic 指定的topic，即消息队列
     * @author xiaosisi add on 2017/08/23
     *//*
    public void sendBussinessLogMsg(BussinessLogEntity bussinessLog, String topic)throws Exception{
    	if (bussinessLog != null) {
            String logJson = JSONObject.toJSONString(bussinessLog);
            KafkaTopicListener.template.send(topic, logJson);           
        }
    }
    
    *//**
     * 业务日志消息发送至kafka消息中心指定的topic,用于处理编辑的复杂日志处理
     * @param requestLog 请求日志对象
     * @param topic 指定的topic，即消息队列
     * @author xiaosisi add on 2017/08/23
     *//*
    public void sendBussinessEditLogMsg(BussinessEditLogEntity bussinessLog, String topic)throws Exception{
    	if (bussinessLog != null) {
            String logJson = JSONObject.toJSONString(bussinessLog);
            KafkaTopicListener.template.send(topic, logJson);           
        }
    }*/
}