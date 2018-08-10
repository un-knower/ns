package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class NsCoreDictionarygroupVo implements Serializable {

    private static final long serialVersionUID = -3390338426808629089L;
    
    /** 数据字典类型组ID */
    @ApiModelProperty(value = "数据字典类型组ID")
    private Long dictionaryGroupId;
    
    /** 所属企业ID */
    @ApiModelProperty(value = "所属企业ID")
    private Long enterpriseId;
    
    /** 所属组织ID */
    @ApiModelProperty(value = "所属组织ID")
    private Long organizationId;
    
    /** 数据字典类型组名称 */
    @ApiModelProperty(value = "数据字典类型组名称")
    private String dictionaryGroupName;
    
    /** 0：未删除，1：已删除 */
    @ApiModelProperty(value = "0：未删除，1：已删除")
    private Integer isDeleted;
    
    /** 备注 */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /** 数据字典列表 */
    @ApiModelProperty(value = "数据字典列表")
    private List<NsCoreDictionaryVo> dictionaryVos;
    
    /** 数据字典组列表 */
    @ApiModelProperty(value = "数据字典组列表")
    List<NsCoreDictionarygroupVo> dictionarygroupVos;
    
    @ApiModelProperty(value = "操作人")
    private Long handlerId;

    public Long getDictionaryGroupId() {
        return dictionaryGroupId;
    }

    public void setDictionaryGroupId(Long dictionaryGroupId) {
        this.dictionaryGroupId = dictionaryGroupId;
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

    public String getDictionaryGroupName() {
        return dictionaryGroupName;
    }

    public void setDictionaryGroupName(String dictionaryGroupName) {
        this.dictionaryGroupName = dictionaryGroupName;
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

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public List<NsCoreDictionaryVo> getDictionaryVos() {
        return dictionaryVos;
    }

    public void setDictionaryVos(List<NsCoreDictionaryVo> dictionaryVos) {
        this.dictionaryVos = dictionaryVos;
    }

    public List<NsCoreDictionarygroupVo> getDictionarygroupVos() {
        return dictionarygroupVos;
    }

    public void setDictionarygroupVos(List<NsCoreDictionarygroupVo> dictionarygroupVos) {
        this.dictionarygroupVos = dictionarygroupVos;
    }
    
}
