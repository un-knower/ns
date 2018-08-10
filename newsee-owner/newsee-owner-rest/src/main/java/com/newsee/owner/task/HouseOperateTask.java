package com.newsee.owner.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.newsee.database.util.DataSourceContextHolder;
import com.newsee.database.util.DataSourceTypeContextHolder;
import com.newsee.owner.service.IHouseOperateService;

@Component
public class HouseOperateTask {
	
	/** logger-logback */
	private static final Logger logger = LoggerFactory.getLogger(HouseOperateTask.class);
	
	@Autowired
	private IHouseOperateService houseOperateService;
	/**
	 * 每30秒扫描一次数据库，执行数据库中的定时任务
	 * 有分库，需要每个分库执行一次
	 * 
	 * @throws Exception
	 */
	@Scheduled(cron = "0 0 0 * * ?")
    public void chargePaymentCacl() throws Exception {
		logger.info("===↓↓↓↓↓===正在刷新定时任务===↓↓↓↓↓===");
		int index = 0;
		Long start = System.currentTimeMillis();
		DataSourceTypeContextHolder.setWrite();
		DataSourceContextHolder.setDB00();
		index = houseOperateService.editDecorateStageTask();
		logger.info("===========DB00更新："+index+"条===========");
		DataSourceContextHolder.setDB01();
		index = houseOperateService.editDecorateStageTask();
        logger.info("===========DB01更新："+index+"条===========");
		DataSourceContextHolder.setDB02();
		index = houseOperateService.editDecorateStageTask();
        logger.info("===========DB02更新："+index+"条===========");
		Long cost = System.currentTimeMillis() - start;
		logger.info("===↑↑↑↑↑===结束刷新定时任务,耗时："+cost+"ms===↑↑↑↑↑===");
    }

	
}
