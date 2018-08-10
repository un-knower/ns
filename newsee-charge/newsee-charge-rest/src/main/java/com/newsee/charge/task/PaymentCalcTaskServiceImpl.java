package com.newsee.charge.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newsee.charge.dao.ChargeCustomerChargeCalcPlanMapper;
import com.newsee.charge.dao.ChargeCustomerChargeCalcTaskMapper;
import com.newsee.charge.entity.ChargeCustomerChargeCalcTask;
import com.newsee.charge.service.IPaymentCalcService;
import com.newsee.common.utils.DateUtils;

@Service
public class PaymentCalcTaskServiceImpl {
	
	/** logger-logback */
	private static final Logger logger = LoggerFactory.getLogger(PaymentCalcTaskServiceImpl.class);
	
    @Autowired
    private ChargeCustomerChargeCalcPlanMapper chargeCustomerChargeCalcPlanMapper;
    
    @Autowired
    private ChargeCustomerChargeCalcTaskMapper chargeCustomerChargeCalcTaskMapper;
    
    @Autowired
    private IPaymentCalcService iPaymentCalcService;
    /**
	 * 执行计算某个自动计算计划
	 * @param planid 计划id
	 * @return
	 */
	public void executeAutoPlan(Long planid){
		Date executeTime = new Date();
		Long startTime = System.currentTimeMillis();
		logger.info("===↓↓↓↓↓===开始执行数据库中所有的自动算费计划，当前时间:"+DateUtils.dateToString(executeTime, DateUtils.YYYYMMDD_CROSS_HHMMSS_SSS)+"===↓↓↓↓↓===");
		//获取所有的自动计划
//		List<ChargeCustomerChargeCalcTask> tasks = chargeCustomerChargeCalcTaskMapper.selectAllPlan(planid);
//		logger.info("======拉取到"+tasks.size()+"条符合条件的自动算费计划======");
//		//逐一计算自动算费计划
//		if(!CollectionUtils.isEmpty(tasks)){
//			for(int i = 0; i < tasks.size(); i++){
//				ChargeCustomerChargeCalcTask plan = tasks.get(i);
//				Long planStartTime = System.currentTimeMillis();
//				logger.info("===↓↓↓↓↓===开始处理自动算费计划名为"+plan.getTaskName()+"的计划===↓↓↓↓↓===");
//				//再次检查当前计划是否符合计算条件
//				
//				
//				Long planCost = System.currentTimeMillis() - planStartTime;
//				logger.info("===↑↑↑↑↑===名为"+plan.getTaskName()+"的计划处理完成,共耗时"+planCost+"毫秒===↑↑↑↑↑===");
//			}
//		}
		Long cost = System.currentTimeMillis() - startTime;
		logger.info("===↑↑↑↑↑===开始执行数据库中所有的自动算费计划，当前时间:"+DateUtils.dateToString(new Date(), DateUtils.YYYYMMDD_CROSS_HHMMSS_SSS)+",共耗费:"+ cost +"毫秒===↑↑↑↑↑====");
	}
}
