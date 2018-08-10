package com.newsee.system.vo;

import java.io.Serializable;

public class SystemUserResult implements Serializable{
    
    private static final long serialVersionUID = 7790667856409487449L;

    /**员工ID*/
    private Long userId;
    
    /**所属企业ID*/
    private Long enterpriseId;
    
    /**所属公司ID*/
    private Long companyId;
    
    /**所属部门ID*/
    private Long departmentId;
    
    /**员工名称*/
    private String userName;

    /**员工账号*/
    private String userAccount;

    /**员工职务*/
    private String userPosition;

    /**员工手机*/
    private String userTelephone;

    /**在职状态*/
    private Byte userState;

    /**员工类型*/
    private Byte userType;
    
    /**删除状态*/
    private Byte isDeleted;
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public Byte getUserState() {
        return userState;
    }

    public void setUserState(Byte userState) {
        this.userState = userState;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

}
