package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

import com.newsee.common.entity.NsSystemUser;

import io.swagger.annotations.ApiModelProperty;

public class NsCoreRoleVo implements Serializable {

    private static final long serialVersionUID = 6609936623758483645L;
    
    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    /** 所属企业ID */
    @ApiModelProperty(value = "所属企业ID")
    private Long enterpriseId;
    
    /** 所属组织ID */
    @ApiModelProperty(value = "所属组织ID")
    private Long organizationId;

    /** 所属角色类别ID */
    private Long rolecategoryId;
    
    /** 角色ID */
    @ApiModelProperty(value = "角色ID")
    private String roleid;
    
    /** 聚合组编码 */
    @ApiModelProperty(value = "聚合组编码")
    private String groupcode;
    
    /** 聚合组 */
    @ApiModelProperty(value = "聚合组")
    private String groupname;
    
    /** 角色编码 */
    @ApiModelProperty(value = "角色编码")
    private String rolecode;
    
    /** 角色名称 */
    @ApiModelProperty(value = "角色名称")
    private String rolename;
    
    /** 角色类型 */
    @ApiModelProperty(value = "角色类型")
    private String roletype;
    
    /** 登记人编码 */
    @ApiModelProperty(value = "登记人编码")
    private String createuser;
    
    /** 登记人姓名 */
    @ApiModelProperty(value = "登记人姓名")
    private String createusername;
    
    /**功能 */
    @ApiModelProperty(value = "功能")
    List<NsCoreFuncinfoVo> funcinfoVos;
    
    /**菜单功能按钮 */
    @ApiModelProperty(value = "菜单功能、按钮")
    List<NsCoreMenuVo> menuVos;
    
    @ApiModelProperty(value = "授权人")
    List<NsSystemUser> authorizers;
    
    /** 多个授权人名称 */
    @ApiModelProperty(value = "多个授权人名称")
    private String authorizerNames;
    
    /** 所属组织名称 */
    @ApiModelProperty(value = "所属组织名称")
    private String organizationName;
    
    /** 所属组织角色组名称 */
    @ApiModelProperty(value = "所属组织角色组名称")
    private String rolecategoryName;
    
    /**前端控件用*/
    private Boolean disabled;
    private Boolean checkSw;
    
    private List<NsSystemMenuVo> menuSaveVos;
    
    /**角色拥有的房产*/
    private List<Long> houseIds;
    
    
    /** 操作人ID*/
    @ApiModelProperty(value = "操作人ID")
    private Long handlerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getGroupcode() {
        return groupcode;
    }

    public void setGroupcode(String groupcode) {
        this.groupcode = groupcode;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoletype() {
        return roletype;
    }

    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    public List<NsCoreFuncinfoVo> getFuncinfoVos() {
        return funcinfoVos;
    }

    public void setFuncinfoVos(List<NsCoreFuncinfoVo> funcinfoVos) {
        this.funcinfoVos = funcinfoVos;
    }

    public List<NsCoreMenuVo> getMenuVos() {
        return menuVos;
    }

    public void setMenuVos(List<NsCoreMenuVo> menuVos) {
        this.menuVos = menuVos;
    }

    public List<NsSystemUser> getAuthorizers() {
        return authorizers;
    }

    public void setAuthorizers(List<NsSystemUser> authorizers) {
        this.authorizers = authorizers;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getAuthorizerNames() {
        return authorizerNames;
    }

    public void setAuthorizerNames(String authorizerNames) {
        this.authorizerNames = authorizerNames;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Long getRolecategoryId() {
        return rolecategoryId;
    }

    public void setRolecategoryId(Long rolecategoryId) {
        this.rolecategoryId = rolecategoryId;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getCheckSw() {
        return checkSw;
    }

    public void setCheckSw(Boolean checkSw) {
        this.checkSw = checkSw;
    }

    public List<NsSystemMenuVo> getMenuSaveVos() {
        return menuSaveVos;
    }

    public void setMenuSaveVos(List<NsSystemMenuVo> menuSaveVos) {
        this.menuSaveVos = menuSaveVos;
    }

    public List<Long> getHouseIds() {
        return houseIds;
    }

    public void setHouseIds(List<Long> houseIds) {
        this.houseIds = houseIds;
    }

    public String getRolecategoryName() {
        return rolecategoryName;
    }

    public void setRolecategoryName(String rolecategoryName) {
        this.rolecategoryName = rolecategoryName;
    }
    
}
