package com.newsee.charge.task;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;  
import org.quartz.JobBuilder;  
import org.quartz.JobDetail;  
import org.quartz.JobKey;  
import org.quartz.Scheduler;  
import org.quartz.TriggerBuilder;  
import org.quartz.TriggerKey;  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.newsee.charge.dao.ChargeCalcPlanQuartzMapper;
import com.newsee.charge.enmus.HouseStandrdAduitStatus;
import com.newsee.charge.entity.ChargeCalcPlanQuartz;
import com.newsee.charge.service.ScheduleTriggerService;

@Service 
public class ScheduleTriggerServiceImpl implements ScheduleTriggerService {

	/** logger-logback */
	private static final Logger logger = LoggerFactory.getLogger(ScheduleTriggerServiceImpl.class);

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private ChargeCalcPlanQuartzMapper chargeCalcPlanQuartzMapper;

	@Override
	public void refreshTrigger() {
		try {
			// 查询出数据库中所有的定时任务
			List<ChargeCalcPlanQuartz> planList = chargeCalcPlanQuartzMapper.selectAllPlan();
			if (!CollectionUtils.isEmpty(planList)) {
				for (ChargeCalcPlanQuartz plan : planList) {
					refreshTrigger(plan);
				}
			}
		} catch (Exception e) {
			logger.error("定时任务每日刷新触发器任务异常，在ScheduleTriggerServiceImpl的方法refreshTrigger中，异常信息：", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refreshTrigger(ChargeCalcPlanQuartz plan) {
		try {
			// 查询出数据库中所有的定时任务
			// 查看该任务是否为启用状态
			String status = plan.getIsWork();
			String name = plan.getPlanId().toString();
			String group = plan.getJobName();
			String dataSource =  plan.getDbDataSource();
			TriggerKey triggerKey = TriggerKey.triggerKey(name+"$"+dataSource, group);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			// 说明本条任务还没有添加到quartz中
			if (null == trigger) {
				// 停用状态下不创建触发器
				if (HouseStandrdAduitStatus.DISABLED.getTitle().equals(status)) {
					return;
				}
				JobDetail jobDetail = null;
				try {
					// 创建JobDetail（数据库中job_name存的任务全路径，这里就可以动态的把任务注入到JobDetail中）
					jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(group)).withIdentity(name+"$"+dataSource, group).build();
					// 表达式调度构建器
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(plan.getPlanCorn());
					// 按新的cronExpression表达式构建一个新的trigger
					trigger = TriggerBuilder.newTrigger().withIdentity(name+"$"+dataSource, group).withSchedule(scheduleBuilder).build();
					// 把trigger和jobDetail注入到调度器
					scheduler.scheduleJob(jobDetail, trigger);
				} catch (ClassNotFoundException e) {
					logger.error("请检查jobName是否正确,jobName" + group + ":", e);
				}

			} else {
				// 说明查出来的这条任务，已经设置到quartz中了
				// Trigger已存在，先判断是否需要删除，如果不需要，再判定是否时间有变化
				if (HouseStandrdAduitStatus.DISABLED.getTitle().equals(status)) { // 如果是禁用，从quartz中删除这条任务
					JobKey jobKey = JobKey.jobKey(name+"$"+dataSource, group);
					scheduler.deleteJob(jobKey);
					return;
				}
				String searchCron = plan.getPlanCorn(); // 获取数据库的
				String currentCron = trigger.getCronExpression();
				if (!searchCron.equals(currentCron)) { // 说明该任务有变化，需要更新quartz中的对应的记录
					// 表达式调度构建器
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);
					// 按新的cronExpression表达式重新构建trigger
					trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
					// 按新的trigger重新设置job执行
					scheduler.rescheduleJob(triggerKey, trigger);
				}
			}
		} catch (Exception e) {
			logger.error("定时任务每日刷新触发器任务异常，在ScheduleTriggerServiceImpl的方法refreshTrigger中，异常信息：", e);
		}
	}
}
