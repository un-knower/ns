package com.newsee.log.thread;

import java.util.Objects;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * 处理日志请求,
 * 将日志请求插入到mangodb指定的collection中
 * @author xiaosisi add on 2017/08/17
 *  modify by xiaosisi on 2017/12/20 修改成使用泛型支持所有的实体插入
 *
 */
public class LogHandlerThread<T> implements Runnable{
	
	/** 请求日志实体 */
	private T log;
	
	/**mongo collection 的名称 */
	private String colName;
	
	/** mongodb 操作模板 */
	private MongoTemplate mongoTemplate;

	/** 县城构造类 */
	public LogHandlerThread(T log,
			String colName,
			MongoTemplate mongoTemplate) {
		this.log = log;
		this.colName = colName;
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * run,执行保存
	 */
	@Override
	public void run() {
		if(!Objects.isNull(log)){
			mongoTemplate.save(log, colName);
		}
	}
}
