package com.newsee.charge.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.newsee.charge.service.ScheduleTriggerService;
import com.newsee.database.util.DataSourceContextHolder;
import com.newsee.database.util.DataSourceTypeContextHolder;

@Component
public class ChargePaymentCalcTask {
	
	/** logger-logback */
	private static final Logger logger = LoggerFactory.getLogger(ChargePaymentCalcTask.class);
	
//	@Autowired
//	private PaymentCalcTaskServiceImpl paymentCalcTaskServiceImpl;
	
	@Autowired
	private ScheduleTriggerService scheduleTriggerService;
	
	/**
	 * 每30秒扫描一次数据库，执行数据库中的定时任务
	 * 有分库，需要每个分库执行一次
	 * 
	 * @throws Exception
	 */
	@Scheduled(cron = "0/30 * * * * ?")
    public void chargePaymentCacl() throws Exception {
		logger.info("===↓↓↓↓↓===正在刷新定时任务===↓↓↓↓↓===");
		Long start = System.currentTimeMillis();
		DataSourceTypeContextHolder.setWrite();
		DataSourceContextHolder.setDB00();
		scheduleTriggerService.refreshTrigger();
		/*DataSourceContextHolder.setDB01();
		scheduleTriggerService.refreshTrigger();
		DataSourceContextHolder.setDB02();
		scheduleTriggerService.refreshTrigger();*/
		//paymentCalcTaskServiceImpl.executeAllAutoPlan();
		Long cost = System.currentTimeMillis() - start;
		logger.info("===↑↑↑↑↑===结束刷新定时任务,耗时："+cost+"ms===↑↑↑↑↑===");
    }

	
}
