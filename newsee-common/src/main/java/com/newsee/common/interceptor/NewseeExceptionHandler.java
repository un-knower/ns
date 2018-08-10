package com.newsee.common.interceptor;

import com.netflix.client.ClientException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import feign.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

/**
 * Created by niyang on 2017/8/11.
 */
@ControllerAdvice
@ResponseBody
public class NewseeExceptionHandler {

    private static final Logger logger= LoggerFactory.getLogger(NewseeExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    public Object BizExceptionHandler(HttpServletRequest request, Exception e) {
        @SuppressWarnings("rawtypes")
		RestResult restResult = new RestResult<>();
        if (e instanceof BizException) {
            e.printStackTrace();
            BizException bizException = (BizException) e;
            restResult.setResultCode(bizException.getCode());
            restResult.setResultMsg(bizException.getMsg());
            logger.error(e.toString());
        }else if (e instanceof SQLException) {
            e.printStackTrace();
            restResult.setResultCode(ResultCodeEnum.DB_ERROR.CODE);
            restResult.setResultMsg(e.getMessage());
            logger.error(e.toString());
        }else if(e instanceof HystrixRuntimeException){
            e.printStackTrace();
            ResultCodeEnum resultCode=null;
            //hystrix超时熔断
            if (e.getCause() instanceof TimeoutException) {
                resultCode=ResultCodeEnum.HYSTRIX_TIMEOUT;
            }
            //feign依赖服务关闭，但eureka服务清单还未刷新
            if (e.getCause() instanceof RetryableException) {
                resultCode=ResultCodeEnum.HYSTRIX_IO_ERROR;
            }
            //没有任何feign依赖服务启动
            if(e.getCause().getCause() instanceof ClientException){
                resultCode=ResultCodeEnum.HYSTRIX_ERROR;
            }
            restResult.setResultCode(resultCode.CODE);
            restResult.setResultMsg(resultCode.DESC);
            logger.error(e.toString());
        }else if(e instanceof RetryableException){
            e.printStackTrace();
            ResultCodeEnum resultCode=null;
            //ribbon服务调用超时
            if (e.getCause() instanceof SocketTimeoutException) {
                resultCode=ResultCodeEnum.RIBBON_TIMEOUT;
            }
            //feign依赖服务关闭，但eureka服务清单还未刷新
            else if (e.getCause() instanceof ConnectException) {
                resultCode=ResultCodeEnum.RIBBON_IO_ERROR;
            }
            restResult.setResultCode(resultCode.CODE);
            restResult.setResultMsg(resultCode.DESC);
            logger.error(e.toString());
        }else if(e instanceof ClientException){
            e.printStackTrace();
            restResult.setResultCode(ResultCodeEnum.RIBBON_ERROR.CODE);
            restResult.setResultMsg(e.getMessage());
            logger.error(e.toString());
        }
        else {
            e.printStackTrace();
            restResult.setResultCode(ResultCodeEnum.SERVER_ERROR.CODE);
            restResult.setResultMsg(e.getMessage());
            logger.error(e.toString());
        }
        return restResult;
    }
}
