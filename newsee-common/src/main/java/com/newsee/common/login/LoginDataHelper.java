package com.newsee.common.login;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.constant.RequestConstants;
import com.newsee.common.entity.AppUser;
import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.entity.NsSystemCompany;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.utils.SpringBeanUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.redis.util.RedisUtil;

/**
 * 获取登录数据帮助类，
 * 注意：只能在controller层使用
 *
 * @author xiaosisi add on 2017/08/25
 */
public class LoginDataHelper {

    /**
     * 获取用户当前所属组织ID，有可能是集团，分公司或者部门的组织id
     *
     * @return
     */
    public static Long getOrgId() {
        String orgId = getRequest().getHeader(RequestConstants.HEADER_USER_CURRENT_ORGANIZATIONID);
        if (!StringUtils.isBlank(orgId)) {
            return Long.valueOf(orgId);
        }
        return null;
    }

    /**
     * 获取当前用户公司级别的组织id
     *
     * @return
     */
    public static Long getCompanyLevelOrgId() {
        String orgId = getRequest().getHeader(RequestConstants.HEADER_USER_COMPANYLEVEL_ORGANIZATIONID);
        if (!StringUtils.isBlank(orgId)) {
            return Long.valueOf(orgId);
        }
        return null;
    }

    /**
     * 获取当前用户集团级别的组织id
     *
     * @return
     */
    public static Long getGroupLevelOrgId() {
        String orgId = getRequest().getHeader(RequestConstants.HEADER_USER_GROUPLEVEL_ORGANIZATIONID);
        if (!StringUtils.isBlank(orgId)) {
            return Long.valueOf(orgId);
        }
        return null;
    }

    /**
     * 获取用户id
     *
     * @return
     */
    public static Long getUserId() {
        String userId = getRequest().getHeader(RequestConstants.HEADER_USER_ID);
        if (!StringUtils.isBlank(userId)) {
            return Long.valueOf(userId);
        }
        return null;
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public static String getUserName() {
        AppUser appUser = getAppUser();
        if (appUser == null) {
            return null;
        }
        return appUser.getUserName();
    }

    /**
     * 获取用户所属租户id
     *
     * @return
     */
    public static Long getEnterpriseId() {
        String enterpriseId = getRequest().getHeader(RequestConstants.HEADER_USER_ENTERPRISEID);
        if (!StringUtils.isBlank(enterpriseId)) {
            return Long.valueOf(enterpriseId);
        }
        return null;
    }

    public static Long getCompanyId() {
        String enterpriseId = getRequest().getHeader(RequestConstants.HEADER_USER_COMPANYID);
        if (!StringUtils.isBlank(enterpriseId)) {
            return Long.valueOf(enterpriseId);
        }
        return null;
    }

    /**
     * 获取页面功能code
     *
     * @return
     */
    public static String getFuncId() {
        String functionId = getRequest().getHeader(RequestConstants.HEADER_FUNC_ID);
        return functionId;
    }

    /**
     * 获取同一个功能下不同表单的标志
     *
     * @return
     */
    public static String getFieldInterpreter() {
        String interpreter = getRequest().getHeader(RequestConstants.HEADER_INTERPRETER);
        return interpreter;
    }

    /**
     * 获取表单的操作类型
     *
     * @return
     */
    public static String getFormOperateType() {
        String formOperateType = getRequest().getHeader(RequestConstants.HEADER_FORM_OPERATE_TYPE);
        return formOperateType;
    }

    /**
     * 获取公司名称
     *
     * @return
     */
    public static String getCompanyName() {
        return getNsSystemCompany().getCompanyName();
    }

    /**
     * 获取用户所属角色id
     *
     * @return
     */
    public static Long getRoleId() {
//        String roleId = getRequest().getHeader(RequestConstants.HEADER_USER_ROLEID);
//        if(!StringUtils.isBlank(roleId)){
//            return Long.valueOf(roleId);
//        }
//        return null;
        return 13L;
    }

    /**
     * 获取appid
     *
     * @return
     */
    public static String getAppId() {
        String appId = getRequest().getHeader(RequestConstants.APP_ID);
        return appId;
    }

    /**
     * 获取登录客户端类型
     *
     * @return
     */
    public static String getAppClientType() {
        String appClientType = getRequest().getHeader(RequestConstants.APP_CLIENT_TYPE);
        return appClientType;
    }

    /**
     * 获取登录信息中的用户信息
     *
     * @return
     */
    public static NsSystemUser getNsSystemUser() {
        RedisUtil redisUtil = SpringBeanUtils.getBean(RedisUtil.class);
        String redisKey = RedisKeysConstants.REDIS_LOGININFO_USER_PREFIX + getAppId() + "_" + getUserId();
        if (Objects.isNull(redisUtil.getObjectValue(redisKey))) {
            return null;
        }
        return (NsSystemUser) redisUtil.getObjectValue(redisKey);
    }

    /**
     * 获取登录人的Appuser信息
     *
     * @return
     */
    public static AppUser getAppUser() {
        RedisUtil redisUtil = SpringBeanUtils.getBean(RedisUtil.class);
        String redisKey = RedisKeysConstants.REDIS_LOGININFO_APP_USER_PREFIX + getAppId() + "_" + getUserId();
        if (Objects.isNull(redisUtil.getObjectValue(redisKey))) {
            return null;
        }
        return (AppUser) redisUtil.getObjectValue(redisKey);
    }

    /**
     * 获取登录信息中公司信息
     *
     * @return
     */
    public static NsSystemCompany getNsSystemCompany() {
        RedisUtil redisUtil = SpringBeanUtils.getBean(RedisUtil.class);
        String redisKey = RedisKeysConstants.REDIS_LOGININFO_COMPANY_PREFIX + getAppId() + "_" + getCompanyId();
        if (Objects.isNull(redisUtil.getObjectValue(redisKey))) {
            return null;
        }
        return (NsSystemCompany) redisUtil.getObjectValue(redisKey);
    }

    /**
     * 获取登录信息中的企业信息
     *
     * @return
     */
    public static NsSossEnterprise getNsPlatformEnterprise() {
        RedisUtil redisUtil = SpringBeanUtils.getBean(RedisUtil.class);
        String redisKey = RedisKeysConstants.REDIS_LOGININFO_ENTERPRISE_PREFIX + getAppId() + "_" + getEnterpriseId();
        if (Objects.isNull(redisUtil.getObjectValue(redisKey))) {
            return null;
        }
        return (NsSossEnterprise) redisUtil.getObjectValue(redisKey);
    }

    /**
     * 获取请求端ip地址
     *
     * @return ip地址
     */
    public static String getIpAddr() {
        HttpServletRequest request = getRequest();
        String xip = request.getHeader("X-Real-IP");
        String xfor = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(xfor) && !"unKnown".equalsIgnoreCase(xfor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xfor.indexOf(",");
            if (index != -1) {
                return xfor.substring(0, index);
            } else {
                return xfor;
            }
        }
        xfor = xip;
        if (!StringUtils.isBlank(xfor) && !"unKnown".equalsIgnoreCase(xfor)) {
            return xfor;
        }
        if (StringUtils.isBlank(xfor) || "unknown".equalsIgnoreCase(xfor)) {
            xfor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xfor) || "unknown".equalsIgnoreCase(xfor)) {
            xfor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xfor) || "unknown".equalsIgnoreCase(xfor)) {
            xfor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(xfor) || "unknown".equalsIgnoreCase(xfor)) {
            xfor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(xfor) || "unknown".equalsIgnoreCase(xfor)) {
            xfor = request.getRemoteAddr();
        }
        return xfor;
    }

    /**
     * 获取当前请求的request
     *
     * @return request
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }

    /**
     * initLoginCommonDataVo
     *
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author 胡乾亮
     * @date 2017年12月27日下午2:12:33
     */
    public static LoginCommonDataVo initLoginCommonDataVo() {
//        Long organizationId= LoginDataHelper.getCompanyLevelOrgId();
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
        String funcId = LoginDataHelper.getFuncId();
        String interpreter = LoginDataHelper.getFieldInterpreter();
        String formOperateType = LoginDataHelper.getFormOperateType();
        LoginCommonDataVo commonVo = new LoginCommonDataVo();
        commonVo.setOrganizationId(organizationId);
        commonVo.setEnterpriseId(enterpriseId);
        commonVo.setGroupLevelOrgId(groupLevelOrgId);
        commonVo.setFuncId(funcId);
        commonVo.setInterpreter(interpreter);
        commonVo.setFormOperateType(formOperateType);
        return commonVo;
    }

}
