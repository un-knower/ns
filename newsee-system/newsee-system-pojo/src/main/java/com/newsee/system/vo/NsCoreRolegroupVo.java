package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class NsCoreRolegroupVo implements Serializable{

    private static final long serialVersionUID = -1275274963843326526L;
    
    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    /** 所属企业ID */
    @ApiModelProperty(value = "所属企业ID")
    private Long enterpriseId;
    
    /** 所属组织ID */
    @ApiModelProperty(value = "所属组织ID")
    private Long organizationId;
    
    /** 权限组ID */
    @ApiModelProperty(value = "权限组ID")
    private String jeCoreRolegroupId;
    
    /** 角色组编码 */
    @ApiModelProperty(value = "角色组编码")
    private String rolegroupCode;
    
    /** 角色组名称 */
    @ApiModelProperty(value = "角色组名称")
    private String rolegroupText;
    
    /** 功能按钮 */
    @ApiModelProperty(value = "功能按钮")
    private  List<NsCoreFuncinfoVo> functionVos;
    
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


    public String getJeCoreRolegroupId() {
        return jeCoreRolegroupId;
    }

    public void setJeCoreRolegroupId(String jeCoreRolegroupId) {
        this.jeCoreRolegroupId = jeCoreRolegroupId;
    }

    public String getRolegroupCode() {
        return rolegroupCode;
    }

    public void setRolegroupCode(String rolegroupCode) {
        this.rolegroupCode = rolegroupCode;
    }

    public String getRolegroupText() {
        return rolegroupText;
    }

    public void setRolegroupText(String rolegroupText) {
        this.rolegroupText = rolegroupText;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public List<NsCoreFuncinfoVo> getFunctionVos() {
        return functionVos;
    }

    public void setFunctionVos(List<NsCoreFuncinfoVo> functionVos) {
        this.functionVos = functionVos;
    }

}
