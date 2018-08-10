package com.newsee.charge.service;

import com.newsee.charge.entity.ChargeCalcPlanQuartz;

public interface ScheduleTriggerService {
	
	/**
	 * 刷新quartz所有的定时任务信息
	 * @return
	 */
	public void refreshTrigger();
	
	/**
	 * 刷新quartz中的具体的任务信息
	 * @return
	 */
	public void refreshTrigger(ChargeCalcPlanQuartz plan);
	
}
