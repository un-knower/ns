package com.newsee.log.service;

import com.newsee.log.entity.BizLogEntity;
import com.newsee.log.entity.ImportLogEntity;
import com.newsee.log.entity.LoginLogEntity;
import com.newsee.log.entity.RequestLogEntity;

public interface IKafkaMessageSendService {
    
    /**
     * 发送请求日志消息,将日志插入到mongodb中
     * @param <T>
     * @param requestLog
     * @param topic
     * @throws Exception
     */
     void sendLoginLog(LoginLogEntity log, String topic);
     
     /**
      * 发送请求日志消息,将日志插入到mongodb中
      * @param <T>
      * @param requestLog
      * @param topic
      * @throws Exception
      */
      void sendRequestLog(RequestLogEntity log, String topic);
      
      /**
       * 发送请求日志消息,将日志插入到mongodb中
       * @param <T>
       * @param requestLog
       * @param topic
       * @throws Exception
       */
       void sendBizLog(BizLogEntity log, String topic);
     
//    /**
//     * 发送业务日志消息,将日志插入到mongodb中
//     * @param bussinessLog 业务日志消息
//     * @param topic
//     * @throws Exception
//     */
//    void sendBussinessLogMsg(BussinessLogEntity bussinessLog, String topic) throws Exception;
//    
//    /**
//     * 业务日志消息发送至kafka消息中心指定的topic,用于处理编辑的复杂日志处理
//     * @param requestLog 请求日志对象
//     * @param topic 指定的topic，即消息队列
//     * @author xiaosisi add on 2017/08/23
//     */
//    void sendBussinessEditLogMsg(BussinessEditLogEntity bussinessLog, String topic)throws Exception;
    
       void sendImportLog(ImportLogEntity log, String topic);
}
