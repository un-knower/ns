package com.newsee.charge.task;

import com.newsee.charge.service.impl.PaymentCalcServiceImpl;
import com.newsee.common.constant.DataSourceEnum;
import com.newsee.common.utils.SpringBeanUtils;
import com.newsee.database.util.DataSourceContextHolder;
import com.newsee.database.util.DataSourceTypeContextHolder;
import com.newsee.common.utils.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义的quartz任务，用来计算应收款
 * @author wangjun add on 2018/04/09
 *
 */
public class ChargeCalcJob implements Job{
	
	/** logger-logback */
	private static final Logger logger = LoggerFactory.getLogger(ChargeCalcJob.class);
	
	public void execute(JobExecutionContext context) throws JobExecutionException{
		CronTrigger trigger = (CronTrigger)context.getTrigger();
		String corn = trigger.getCronExpression();
		String jobName = trigger.getKey().getName();
		if(StringUtils.isBlank(jobName)){
			logger.error("没有获取到计划id，请检查计划id是否设置正确");
			return;
		}
		Long planId = Long.valueOf(jobName.split("\\$")[0]);
		Long startTime = System.currentTimeMillis();
		logger.info("========↓↓↓↓↓↓========开始执行自动算费计划，planid为"+planId+",执行频率为:"+corn+"===========↓↓↓↓↓==========");
		//PaymentCalcTaskServiceImpl calc = SpringBeanUtils.getBean(PaymentCalcTaskServiceImpl.class);
		PaymentCalcServiceImpl calc = SpringBeanUtils.getBean(PaymentCalcServiceImpl.class);
		DataSourceTypeContextHolder.setWrite();
		DataSourceContextHolder.getLocal().set(jobName.split("\\$")[1]);
		//calc.executeAutoPlan(planId);
		calc.calculateCost(planId);
		Long cost = System.currentTimeMillis() - startTime;
		logger.info("========↑↑↑↑↑↑========开始执行自动算费计划，planid为"+planId+",执行频率为:"+corn+",耗时："+cost+"ms===========↑↑↑↑↑==========");
	}

}
