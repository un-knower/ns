package com.newsee.log.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SearchConditionVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 3394412658208565167L;
    
    @ApiModelProperty("用户ID")
    private Long userId;
    /**用户名*/
    @ApiModelProperty("用户名")
    private String userName;
    /**开始时间*/
    @ApiModelProperty("开始时间")
    private Date startTime;
    /**结束时间*/
    @ApiModelProperty("结束时间")
    private Date endTime;
    /**菜单名称*/
    @ApiModelProperty("菜单名称")
    private String menuName;
    /**菜单ID*/
    @ApiModelProperty("菜单ID")
    private Integer menuId;
    /**日志级别*/
    @ApiModelProperty("日志级别")
    private String logLevel;
    /**操作类型*/
    @ApiModelProperty("操作类型")
    private String operateType;
    /**应用名*/
    @ApiModelProperty("应用名")
    private String appName;
    /**ip地址*/
    @ApiModelProperty("ip地址")
    private String ipAddress;
    /**请求路径*/
    @ApiModelProperty("请求路径")
    private String requestPath;
    /**方法名*/
    @ApiModelProperty("方法名")
    private String methodName;
    private List<String> keywords;
    /** 项目名称 */
    @ApiModelProperty("项目名称")
    private String precinctName;
    /** 房产名称 */
    @ApiModelProperty("房产名称")
    private String houseName;
    @ApiModelProperty("导入日志名称")
    private String importName;
    private Integer pageNum;
    private Integer pageSize;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getLogLevel() {
        return logLevel;
    }
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }
    public String getOperateType() {
        return operateType;
    }
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public String getRequestPath() {
        return requestPath;
    }
    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public Integer getMenuId() {
        return menuId;
    }
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    public List<String> getKeywords() {
        return keywords;
    }
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public Integer getPageNum() {
        return pageNum;
    }
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getPrecinctName() {
        return precinctName;
    }
    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }
    public String getHouseName() {
        return houseName;
    }
    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
    public String getImportName() {
        return importName;
    }
    public void setImportName(String importName) {
        this.importName = importName;
    }
    
}
