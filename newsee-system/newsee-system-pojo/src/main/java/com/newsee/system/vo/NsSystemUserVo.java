package com.newsee.system.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class NsSystemUserVo implements Serializable {
    
    private static final long serialVersionUID = -5259935657300899504L;
    
    /** 用户ID */
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    
    /** enterpriseId */
    @ApiModelProperty(value = "enterpriseId")
    private Long enterpriseId;
    
    /** organizationId */
    @ApiModelProperty(value = "enterpriseId")
    private Long organizationId;
    
    /** 所属集团ID */
    @ApiModelProperty(value = "所属集团ID")
    private Long groupId;
    
    /** 所属公司ID */
    @ApiModelProperty(value = "所属公司ID")
    private Long companyId;
    
    /** 所属部门ID */
    @ApiModelProperty(value = "所属部门ID")
    private Long departmentId;
    
    /** 所属岗位ID */
    @ApiModelProperty(value = "所属岗位ID")
    private String sentryId;
    
    /** 所属职位ID */
    @ApiModelProperty(value = "所属职位ID")
    private Long positionId;
    
    /** 用户名称 */
    @ApiModelProperty(value = "用户名称")
    private String userName;
    
    /** 0：女，1：男 */
    @ApiModelProperty(value = "0：女，1：男")
    private String userSex;
    
    /** 用户年龄 */
    @ApiModelProperty(value = "用户年龄")
    private Integer userAge;
    
    /** 用户账号（手机号） */
    @ApiModelProperty(value = "用户账号（手机号）")
    private String userAccount;
    
    /** 手机号 */
    @ApiModelProperty(value = "手机号")
    private String userTelephone;
    
    /** 用户密码 */
    @ApiModelProperty(value = "用户密码")
    private String userPassword;
    
    /** 用户生日 */
    @ApiModelProperty(value = "用户生日")
    private Date userBirthday;
    
    /** 用户入职日期 */
    @ApiModelProperty(value = "用户入职日期")
    private Date userHiredate;
    
    /** 0：在职，1：离职 */
    @ApiModelProperty(value = "0：在职，1：离职")
    private String userState;
    
    /** 0:内部运营账户，1:物业公司员工账户，2:业主（客户）账户 */
    @ApiModelProperty(value = "0:内部运营账户，1:物业公司员工账户，2:业主（客户）账户")
    private Integer userType;
    
    /** 0：身份证，1：军官证，2：其他 */
    @ApiModelProperty(value = "0：身份证，1：军官证，2：其他")
    private String userCertificateType;
    
    /** 证件号码 */
    @ApiModelProperty(value = "证件号码")
    private String userCertificateNumber;
    
    /** 用户头像*/
    @ApiModelProperty(value = "用户头像")
    private String userPicurl;
    
    /** 0：未删除，1：已删除 */
    @ApiModelProperty(value = "0：未删除，1：已删除")
    private Integer isDeleted;
    
    /** 0：未激活，1：已激活 */
    @ApiModelProperty(value = "0：未激活，1：已激活")
    private String isActived;
    
    /** 备注 */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /** 角色*/
    @ApiModelProperty(value = "角色")
    private List<NsCoreRoleVo> roleVos;
    
    /** 组织*/
    @ApiModelProperty(value = "组织")
    private NsSystemOrganizationVo organizationVo;
    
    /**员工角色IDs*/
    @ApiModelProperty(value = "员工角色IDs")
    private List<String> roleids;
    
    /** 所属组织名称 */
    @ApiModelProperty(value = "所属组织名称")
    private String organizationName;
    
    /** 用户所属组织简称 */
    @ApiModelProperty(value = "用户所属组织简称")
    private String organizationShortName;
    
    
    /** appId */
    @ApiModelProperty(value = "appId")
    private String appId;
    
    /** 操作人ID*/
    @ApiModelProperty(value = "操作人ID")
    private Long handlerId;
    

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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    public String getUserCertificateNumber() {
        return userCertificateNumber;
    }

    public void setUserCertificateNumber(String userCertificateNumber) {
        this.userCertificateNumber = userCertificateNumber;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<NsCoreRoleVo> getRoleVos() {
        return roleVos;
    }

    public void setRoleVos(List<NsCoreRoleVo> roleVos) {
        this.roleVos = roleVos;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public NsSystemOrganizationVo getOrganizationVo() {
        return organizationVo;
    }

    public void setOrganizationVo(NsSystemOrganizationVo organizationVo) {
        this.organizationVo = organizationVo;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<String> getRoleids() {
        return roleids;
    }

    public void setRoleids(List<String> roleids) {
        this.roleids = roleids;
    }

    public String getSentryId() {
        return sentryId;
    }

    public void setSentryId(String sentryId) {
        this.sentryId = sentryId;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserCertificateType() {
        return userCertificateType;
    }

    public void setUserCertificateType(String userCertificateType) {
        this.userCertificateType = userCertificateType;
    }

    public String getUserPicurl() {
        return userPicurl;
    }

    public void setUserPicurl(String userPicurl) {
        this.userPicurl = userPicurl;
    }

    public String getIsActived() {
        return isActived;
    }

    public void setIsActived(String isActived) {
        this.isActived = isActived;
    }

    public String getOrganizationShortName() {
        return organizationShortName;
    }

    public void setOrganizationShortName(String organizationShortName) {
        this.organizationShortName = organizationShortName;
    }
    
    

}
