package com.newsee.log.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.newsee.log.entity.ImportLogEntity;

public class ImportLogVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -2407458076511135982L;
    /** 导入用户id */
    private Long userId;
    /** 导入用户名 */
    private String userName;
    /** 导入时间 */
    private Date importDate;
    
    private String importName;
    
    private List<ImportLogEntity> logList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public List<ImportLogEntity> getLogList() {
        return logList;
    }

    public void setLogList(List<ImportLogEntity> logList) {
        this.logList = logList;
    }

    public String getImportName() {
        return importName;
    }

    public void setImportName(String importName) {
        this.importName = importName;
    }
    
}
