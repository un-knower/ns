package com.newsee.common.constant;

/**
 * kafka相关常量
 * 如topic
 * @author xiaosisi add on 2017/08/17
 *
 */
public class KafkaTopicConstants {

	/** kafka 请求日志topic 名称*/
	public static final String NEWSEE_REQUEST_LOG_TOPIC = "newsee-request-log-topic";
	
	/** kafka 登录登出日志topic 名称*/
	public static final String NEWSEE_LOGIN_LOG_TOPIC = "newsee-login-log-topic";
	
	/** kafka 业务日志topic 名称,处理新增，删除简单日志 */
	public static final String NEWSEE_BIZ_LOG_TOPIC = "newsee-biz-log-topic";
	
	/** kafka 业务日志topic 名称,处理编辑的复杂日志 */
	public static final String NEWSEE_BIZ_LOG_TOPIC_EDIT = "newsee-biz-log-edit-topic";
	
    /** kafka 业务日志topic 名称,处理导入日志 */
    public static final String NEWSEE_IMPORT_LOG_TOPIC = "newsee-import-log-topic";
	
}
