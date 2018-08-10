package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class NsSystemOrganizationVo implements Serializable {

    private static final long serialVersionUID = -8424773697857975789L;
    
    /** 组织ID */
    @ApiModelProperty(value = "组织ID")
    private Long organizationId;
    
    /** enterpriseId */
    @ApiModelProperty(value = "enterpriseId")
    private Long enterpriseId;
    
    /** 所属集团 */
    @ApiModelProperty(value = "所属集团")
    private Long groupId;
    
    /** 所属公司 */
    @ApiModelProperty(value = "所属公司")
    private Long companyId;
    
    /** 所属部门 */
    @ApiModelProperty(value = "所属部门")
    private Long departmentId;
    
    /** 父级组织ID */
    @ApiModelProperty(value = "父级组织ID")
    private Long organizationParentId;
    
    /** 组织名称 */
    @ApiModelProperty(value = "组织名称")
    private String organizationName;
    
    /** 组织编码 */
    @ApiModelProperty(value = "组织名称")
    private String organizationCode;
    
    @ApiModelProperty(value = "组织简称")
    private String organizationShortName;
    /** 组织类型：0 集团、1 公司、2 职能中心、3服务中心 */
    @ApiModelProperty(value = "组织类型：0 集团、1 公司、2 职能中心、3服务中心")
    private Integer organizationType;
    
    /** 启用状态 ：1 未启用、2 已启用、3 已停用 */
    @ApiModelProperty(value = "启用状态 ：1 未启用、2 已启用、3 已停用")
    private Integer organizationEnablestate;
    
    /** 路径（父级组织ID拼接） */
    @ApiModelProperty(value = "路径（父级组织ID拼接）")
    private String organizationPath;
    
    /** 层级 */
    @ApiModelProperty(value = "层级")
    private Integer organizationLevel;
    
    /** 排序 */
    @ApiModelProperty(value = "排序")
    private Integer organizationOrdercolumn;
    
    /** 备注 */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /** 子组织 */
    @ApiModelProperty(value = "子组织")
    private List<NsSystemOrganizationVo> childOrganizations;
    
    /** 组织_集团 */
    @ApiModelProperty(value = "组织_集团")
    private NsSystemGroupVo groupVo;
    
    /** 组织_公司 */
    @ApiModelProperty(value = "组织_公司")
    private NsSystemCompanyVo companyVo;
    
    /** 组织_部门 */
    @ApiModelProperty(value = "组织_部门")
    private NsSystemDepartmentVo departmentVo;
    
    private Boolean isHasChild;
    
    

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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

    public Long getOrganizationParentId() {
        return organizationParentId;
    }

    public void setOrganizationParentId(Long organizationParentId) {
        this.organizationParentId = organizationParentId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(Integer organizationType) {
        this.organizationType = organizationType;
    }

    public Integer getOrganizationEnablestate() {
        return organizationEnablestate;
    }

    public void setOrganizationEnablestate(Integer organizationEnablestate) {
        this.organizationEnablestate = organizationEnablestate;
    }

    public String getOrganizationPath() {
        return organizationPath;
    }

    public void setOrganizationPath(String organizationPath) {
        this.organizationPath = organizationPath;
    }

    public Integer getOrganizationLevel() {
        return organizationLevel;
    }

    public void setOrganizationLevel(Integer organizationLevel) {
        this.organizationLevel = organizationLevel;
    }

    public Integer getOrganizationOrdercolumn() {
        return organizationOrdercolumn;
    }

    public void setOrganizationOrdercolumn(Integer organizationOrdercolumn) {
        this.organizationOrdercolumn = organizationOrdercolumn;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<NsSystemOrganizationVo> getChildOrganizations() {
        return childOrganizations;
    }

    public void setChildOrganizations(List<NsSystemOrganizationVo> childOrganizations) {
        this.childOrganizations = childOrganizations;
    }

    public NsSystemGroupVo getGroupVo() {
        return groupVo;
    }

    public void setGroupVo(NsSystemGroupVo groupVo) {
        this.groupVo = groupVo;
    }

    public NsSystemCompanyVo getCompanyVo() {
        return companyVo;
    }

    public void setCompanyVo(NsSystemCompanyVo companyVo) {
        this.companyVo = companyVo;
    }

    public NsSystemDepartmentVo getDepartmentVo() {
        return departmentVo;
    }

    public void setDepartmentVo(NsSystemDepartmentVo departmentVo) {
        this.departmentVo = departmentVo;
    }

    public Boolean getIsHasChild() {
        return isHasChild;
    }

    public void setIsHasChild(Boolean isHasChild) {
        this.isHasChild = isHasChild;
    }

    public String getOrganizationShortName() {
        return organizationShortName;
    }

    public void setOrganizationShortName(String organizationShortName) {
        this.organizationShortName = organizationShortName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }
    
}
