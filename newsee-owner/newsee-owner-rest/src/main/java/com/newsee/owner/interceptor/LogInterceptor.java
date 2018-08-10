package com.newsee.owner.interceptor;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.newsee.common.constant.NewseeMethodRuleEnum;
import com.newsee.common.constant.RequestConstants;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.APPRestResult;
import com.newsee.common.rest.RestLog;
import com.newsee.common.rest.RestResult;
import com.newsee.log.entity.BizLogEntity;
import com.newsee.log.entity.RequestLogEntity;
import com.newsee.owner.service.remote.ILogRemoteService;

/**
 * controller切面，
 * 拦截每个请求，并组成请求日志，
 * 拦截add请求，组成新增日志
 * 拦截del请求，组成删除日志
 * 拦截edit请求，组成修改日志
 * 将日志消息发送至kafka消息队列中，
 * kafka监听到该消息后，
 * 调用log远程服务，将日志插入到mangodb中
 * @author xiaosisi add on 2017/08/17
 *
 */
@Aspect
@Component
public class LogInterceptor {
	
	/** logger-logback */
	private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
	
	/** 记录contrller执行开始时间，保证线程安全 */
	private ThreadLocal<Long> controllerStartTime = new ThreadLocal<Long>();
	
	/** 记录Service执行开始时间，保证线程安全 */
	private ThreadLocal<Long> serviceStartTime = new ThreadLocal<Long>();
	
	@Autowired
	private ILogRemoteService logRemoteService;
	
	@Pointcut("execution(public * com.newsee.*.controller..*.*(..))")
	private void controllerAspect(){}
	
	@Pointcut("execution(public * com.newsee.*.service.impl.*.*(..))")
	private void serviceAspect(){}
	
	/**
	 * 方法执行前拦截
	 * 组成请求日志，发送到kafka中，kafka监听到该消息后插入到mangodb中
	 * @param joinPoint
	 */
	@Before(value="controllerAspect()")
	public void controllerMethodBefore(JoinPoint joinPoint) throws Exception{
		controllerStartTime.set(System.currentTimeMillis());
		logger.info("=====开始执行请求=====↓↓↓↓↓" + joinPoint.getTarget().getClass().getName() +"."+joinPoint.getSignature().getName() + "↓↓↓↓=====开始执行请求=====");
		//处理请求日志，将消息发送到kafka中，kafka监听到之后记录到mongodb中
		requestLogHandler(joinPoint);
	}
	
	/**
	 * 处理请求日志
	 * @param joinPoint 切点
	 * @author xiaosisi add on 2017/08/23
	 */
	private void requestLogHandler(JoinPoint joinPoint) throws Exception{
		HttpServletRequest request = LoginDataHelper.getRequest();
		RequestLogEntity log = new RequestLogEntity();
		log.setUserId(LoginDataHelper.getUserId());
		log.setUserName(LoginDataHelper.getUserName());
		log.setRequestDate(new Date());
		log.setAppName(RequestConstants.CAESAR_APP_NAME);
		log.setIpAddress(LoginDataHelper.getIpAddr());
		log.setAgentName(request.getHeader(RequestConstants.HEADER_USER_AGENT));
		log.setRequestPath(request.getRequestURI());
//		log.setRequestParams(JSON.toJSONString(joinPoint.getArgs()));
		logRemoteService.sendRequestLog(log);
	}
	
	/**
	 * 方法执行后拦截
	 * @param result
	 */
	@AfterReturning(returning= "result",value="controllerAspect()")
	public void controllerMethodAfterReturning(JoinPoint joinPoint, Object result) throws Exception{
		//记录花费时间
		Long cost = System.currentTimeMillis() - controllerStartTime.get();
		//获取方法名
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
		logger.info("=====结束执行请求=====↑↑↑↑↑" + className +"."+ methodName + "↑↑↑↑=====结束执行请求=====共花费："+cost +"ms");
		
		RestLog restLog= null;
		try {
			RestResult restResult = (RestResult)result;
			restLog = restResult.getRestLog();
		} catch (Exception e) {
			APPRestResult restResult =(APPRestResult)result;
			restLog = restResult.getRestLog();
		}
		
		if(!Objects.isNull(restLog)){
			BizLogEntity log = new BizLogEntity();
			log.setDataId(restLog.getDataId());
			log.setEnterpriseId(LoginDataHelper.getEnterpriseId());
			log.setCompanyId(LoginDataHelper.getCompanyId());
			log.setOrganizationId(LoginDataHelper.getOrgId());
			log.setFuncId(LoginDataHelper.getFuncId());
			log.setOperateDate(new Date());
			log.setUserId(LoginDataHelper.getUserId());
			log.setUserName(LoginDataHelper.getUserName());
			log.setRemark(handlerReamrk(restLog, methodName, log));
			//发送请求日志
			logRemoteService.sendBizLog(log);

		}
	}
	
	@Before(value="serviceAspect()")
	public void serviceMethodBefore(JoinPoint joinPoint) throws Exception{
		serviceStartTime.set(System.currentTimeMillis());
		logger.info("=====开始执行服务=====↓↓↓↓↓" + joinPoint.getTarget().getClass().getName() +"."+joinPoint.getSignature().getName() + "↓↓↓↓=====开始执行服务=====");
	}
	
	@AfterReturning(value="serviceAspect()")
	public void serviceMethodAfter(JoinPoint joinPoint) throws Exception{
		//记录花费时间
		Long cost = System.currentTimeMillis() - serviceStartTime.get();
		//获取方法名
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getName();
		logger.info("=====结束执行服务=====↑↑↑↑↑" + className +"."+ methodName + "↑↑↑↑=====结束执行服务=====共花费："+cost +"ms");
	}
		
//	/**
//	 * 处理新增的业务日志
//	 * @param joinPoint
//	 */
//	private void bussinessLogHandlerAdd(Object result){
//		RestResult resultData = (RestResult)result;
//		BussinessLogEntity bussinessLog = new BussinessLogEntity();
//		String userName = LoginDataHelper.getUserName();
//		bussinessLog.setDataId((Long)resultData.getResultData());
//		bussinessLog.setMenuId(1);
//		bussinessLog.setMenuName("MENU_OWNER_REGISTER");
//		bussinessLog.setOperateDate(new Date());
//		bussinessLog.setOperateName(NewseeMethodRuleEnum.add.getName());
//		bussinessLog.setOperateType(NewseeMethodRuleEnum.add.getType());
//		bussinessLog.setUserId(LoginDataHelper.getUserId());
//		bussinessLog.setUserName(userName);
//		bussinessLog.setRemark(userName + NewseeMethodRuleEnum.add.getName()+"了该条数据。");
//		//kafkaMessageSendRemoteService.sendBussinessLogMsg(bussinessLog);
//	}
	
//	/**
//	 * 处理新增的业务日志
//	 * @param joinPoint
//	 */
//	private void bussinessLogHandlerDelete(Object result){
//		RestResult resultData = (RestResult)result;
//		BussinessLogEntity bussinessLog = new BussinessLogEntity();
//		String userName = LoginDataHelper.getUserName();
//		bussinessLog.setDataId((Long)resultData.getResultData());
//		bussinessLog.setMenuId(1);
//		bussinessLog.setMenuName("MENU_OWNER_REGISTER");
//		bussinessLog.setOperateDate(new Date());
//		bussinessLog.setOperateName(NewseeMethodRuleEnum.delete.getName());
//		bussinessLog.setOperateType(NewseeMethodRuleEnum.delete.getType());
//		bussinessLog.setUserId(LoginDataHelper.getUserId());
//		bussinessLog.setUserName(userName);
//		bussinessLog.setRemark(userName + NewseeMethodRuleEnum.delete.getName()+"了该条数据。");
//		//kafkaMessageSendRemoteService.sendBussinessLogMsg(bussinessLog);
//	}
//	
//	/**
//	 * 处理新增的业务日志
//	 * @param joinPoint
//	 */
//	private void bussinessLogHandlerDeleteBatch(Object result){
//		RestResult resultData = (RestResult)result;
//		BussinessLogEntity bussinessLog = new BussinessLogEntity();
//		String userName = LoginDataHelper.getUserName();
//		bussinessLog.setDataId((Long)resultData.getResultData());
//		bussinessLog.setMenuId(1);
//		bussinessLog.setMenuName("MENU_OWNER_REGISTER");
//		bussinessLog.setOperateDate(new Date());
//		bussinessLog.setOperateName(NewseeMethodRuleEnum.deleteBatch.getName());
//		bussinessLog.setOperateType(NewseeMethodRuleEnum.deleteBatch.getType());
//		bussinessLog.setUserId(LoginDataHelper.getUserId());
//		bussinessLog.setUserName(userName);
//		bussinessLog.setRemark(userName + NewseeMethodRuleEnum.deleteBatch.getName()+"了该条数据。");
//		//kafkaMessageSendRemoteService.sendBussinessLogMsg(bussinessLog);
//	}
//	
//	/**
//	 * 处理业务详情日志，将获取详情的数据存入redis中，作为初始数据
//	 * @param result controller返回数据
//	 */
//	private void bussinessLogHandlerDetail(Object result){
//		RestResult resultData = (RestResult)result;
//		String detial = JSON.toJSONString(resultData.getResultData());
//		Long id = Long.valueOf(JSON.parseObject(detial, Map.class).get("id").toString());
//		redisUtil.setStringValue("MENU_OWNER_REGISTER" + NewseeMethodRuleEnum.detail.getType() + id, detial);
//	}
//	
//	/**
//	 * 处理业务详情日志，将获取详情的数据存入redis中，作为初始数据
//	 * @param result controller返回数据
//	 */
//	private void bussinessLogHandlerEdit(JoinPoint joinPoint, Object result){
//		RestResult resultData = (RestResult)result;
//		String afterEditJson = JSON.toJSONString(joinPoint.getArgs()[0]);
//		BussinessEditLogEntity bussinessLog = new BussinessEditLogEntity();
//		bussinessLog.setMenuId(1);
//		bussinessLog.setMenuName("MENU_OWNER_REGISTER");
//		bussinessLog.setEditAfterJson(afterEditJson);
//		bussinessLog.setUserId(LoginDataHelper.getUserId());
//		bussinessLog.setUserName(LoginDataHelper.getUserName());
//		bussinessLog.setOperateDate(new Date());
//		//kafkaMessageSendRemoteService.sendBussinessEditLogMsg(bussinessLog);
//	}
	
	private String handlerReamrk(RestLog restLog, String methodName, BizLogEntity log){
		String remark = "";
		String handlerName = "";
		//新增的场合
		if(methodName.indexOf(NewseeMethodRuleEnum.add.getType()) >= 0){
			handlerName = NewseeMethodRuleEnum.add.getName();
		}
		//新增的场合
		if(methodName.indexOf(NewseeMethodRuleEnum.delete.getType()) >= 0){
			handlerName = NewseeMethodRuleEnum.delete.getName();	
		}
		//详情的时候
		if(methodName.indexOf(NewseeMethodRuleEnum.detail.getType()) >= 0){
		}
		//编辑的时候
		if(methodName.indexOf(NewseeMethodRuleEnum.edit.getType()) >= 0){
			handlerName = NewseeMethodRuleEnum.edit.getName();
		}
		remark = handlerName +  "了" + restLog.getModuleName() + ":"+ restLog.getSubjectName()+"。";
		return remark;
	}
}
