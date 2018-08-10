package com.newsee.system.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SystemUserBaseVo implements Serializable {

    private static final long serialVersionUID = 5904967583605516834L;
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "0：女，1：男")
    private String userSex;
    @ApiModelProperty(value = "用户年龄")
    private Integer userAge;
    @ApiModelProperty(value = "用户账号")
    private String userAccount;
    @ApiModelProperty(value = "用户密码")
    private String userPassword;
    @ApiModelProperty(value = "手机号")
    private String userTelephone;
    @ApiModelProperty(value = "用户生日")
    private Date userBirthday;
    @ApiModelProperty(value = "用户入职日期")
    private Date userHiredate;
    @ApiModelProperty(value = "用户职位")
    private String userPosition;
    @ApiModelProperty(value = "0：平台管理员，1：系统管理员，2：普通员工")
    private String userType;
    @ApiModelProperty(value = "所属公司ID")
    private Long companyId;
    @ApiModelProperty(value = "所属部门ID")
    private Long departmentId;
    @ApiModelProperty(value = "员工拥有的角色组ID")
    private List<Long> systemRoleGroupIds;
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getUserAge() {
        return userAge;
    }
    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }
    public String getUserAccount() {
        return userAccount;
    }
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    public String getUserTelephone() {
        return userTelephone;
    }
    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }
    public Date getUserBirthday() {
        return userBirthday;
    }
    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }
    public Date getUserHiredate() {
        return userHiredate;
    }
    public void setUserHiredate(Date userHiredate) {
        this.userHiredate = userHiredate;
    }
    public String getUserPosition() {
        return userPosition;
    }
    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
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
    
    public List<Long> getSystemRoleGroupIds() {
        return systemRoleGroupIds;
    }
    public void setSystemRoleGroupIds(List<Long> systemRoleGroupIds) {
        this.systemRoleGroupIds = systemRoleGroupIds;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserSex() {
        return userSex;
    }
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
}
