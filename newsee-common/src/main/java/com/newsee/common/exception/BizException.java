package com.newsee.common.exception;

import com.newsee.common.rest.ResultCodeEnum;

/**
 * Created by niyang on 2017/8/15.
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code = null;
    private String msg = null;

    public BizException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }


    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static void ParaException(String msg) {
        throw new BizException(ResultCodeEnum.PARAMS_ERROR.CODE, msg);
    }

    public static void ParaOutRangeException(String msg) {
        throw new BizException(ResultCodeEnum.PARAMS_ERROR.CODE, msg);
    }

    public static void fail(ResultCodeEnum code, String msg) {
        String message = msg == null?code.DESC:msg;
        throw new BizException(code.CODE, message);
    }

    public static void isNull(Object o) {
        if(o == null || o.equals("")) {
            throw new BizException(ResultCodeEnum.PARAMS_MISSING.CODE, "主键不能为空");
        }
    }

    public static void isNull(Object obj, String msg) {
        if(obj == null || obj.equals("")) {
            throw new BizException(ResultCodeEnum.PARAMS_MISSING.CODE, msg + "不能为空");
        }
    }

    public static void isNull2(Object obj, String msg) {
        if(obj == null || obj.equals("")) {
            throw new BizException(ResultCodeEnum.PARAMS_MISSING.CODE, msg);
        }
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}


