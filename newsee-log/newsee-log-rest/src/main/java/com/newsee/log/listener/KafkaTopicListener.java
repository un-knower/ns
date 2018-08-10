package com.newsee.log.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.newsee.common.constant.KafkaTopicConstants;
import com.newsee.common.constant.MongoCollectionsConstants;
import com.newsee.log.entity.BizLogEntity;
import com.newsee.log.entity.ImportLogEntity;
import com.newsee.log.entity.LoginLogEntity;
import com.newsee.log.entity.RequestLogEntity;
import com.newsee.log.thread.LogHandlerThread;

/**
 * kafka消息队列监听类
 * @author xiaosisi add on 2017/08/17
 *
 */
@Component
public class KafkaTopicListener implements CommandLineRunner{
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Value("${spring.data.mongodb.uri}")
	private String mongodbUrl;

	@Autowired
	private ThreadPoolTaskExecutor executor;

	public static KafkaTemplate<String, String> template;
	/**
     * 监听请求消息日志队列【newsee-login-log-topic】
     * @param cr
     * @throws Exception
     */
    @KafkaListener(topics={KafkaTopicConstants.NEWSEE_LOGIN_LOG_TOPIC})
    public void LoginLogListener(ConsumerRecord<String, String> cr) throws Exception{
    	LoginLogEntity log = JSON.parseObject(cr.value(),LoginLogEntity.class);
    	LogHandlerThread<LoginLogEntity> logHandler = 
				new LogHandlerThread<LoginLogEntity>(log, MongoCollectionsConstants.COL_LOGIN_LOG_NAME , mongoTemplate);
		executor.execute(logHandler);
    }
    
    /**
     * 监听请求消息日志队列【newsee-request-log-topic】
     * @param cr
     * @throws Exception
     */
    @KafkaListener(topics={KafkaTopicConstants.NEWSEE_REQUEST_LOG_TOPIC})
    public void requestLogListener(ConsumerRecord<String, String> cr) throws Exception{
    	RequestLogEntity log = JSON.parseObject(cr.value(), RequestLogEntity.class);
    	LogHandlerThread<RequestLogEntity> logHandler =
				new LogHandlerThread<RequestLogEntity>(log, MongoCollectionsConstants.COL_REQUEST_LOG_NAME, mongoTemplate);
		executor.execute(logHandler);
    }
    
    /**
     * 监听业务请求消息日志队列【newsee-biz-log-topic】
     * @param cr
     * @throws Exception
     */
    @KafkaListener(topics={KafkaTopicConstants.NEWSEE_BIZ_LOG_TOPIC})
    public void bussinessLogListener(ConsumerRecord<String,  String> cr) throws Exception{
    	BizLogEntity log = JSON.parseObject(cr.value(), BizLogEntity.class);
    	LogHandlerThread<BizLogEntity> logHandler = 
				new LogHandlerThread<BizLogEntity>(log, MongoCollectionsConstants.COL_BUSSINESS_LOG_NAME, mongoTemplate);
		executor.execute(logHandler);
    }
    
    /**
     * 监听业务请求消息日志队列【newsee-import-log-topic】
     * @param cr
     * @throws Exception
     */
    @KafkaListener(topics={KafkaTopicConstants.NEWSEE_IMPORT_LOG_TOPIC})
    public void importLogListener(ConsumerRecord<String,  String> cr) throws Exception{
        ImportLogEntity log = JSON.parseObject(cr.value(), ImportLogEntity.class);
        LogHandlerThread<ImportLogEntity> logHandler = 
                new LogHandlerThread<ImportLogEntity>(log, MongoCollectionsConstants.COL_IMPORT_LOG_NAME, mongoTemplate);
        executor.execute(logHandler);
    }
    /**
     * 现成主方法，将template赋值给线程
     */
    @Override
    public void run(String... arg0) throws Exception {
        template = kafkaTemplate;
    }
}
