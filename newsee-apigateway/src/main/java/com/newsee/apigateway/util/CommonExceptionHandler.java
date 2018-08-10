package com.newsee.apigateway.util;

import com.alibaba.fastjson.JSONObject;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Created by niyang on 2017/8/15.
 */
public class CommonExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    public static String handlerExcetpion(String errorMessage,String logMessage) {
        if (StringUtils.isEmpty(errorMessage)) {
            errorMessage = ResultCodeEnum.SERVER_ERROR.DESC;
        }
        RestResult restResult = new RestResult(ResultCodeEnum.SERVER_ERROR.CODE, errorMessage);
        String responseBody = JSONObject.toJSONString(restResult);
        logger.error("agigateway error,{}",logMessage);
        return responseBody;
    }
}
