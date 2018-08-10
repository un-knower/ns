package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class NsCoreDictionaryVo implements Serializable{

    private static final long serialVersionUID = -3051632713804541224L;
    
    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    /** 字典ID */
    @ApiModelProperty(value = "字典ID")
    private String jeCoreDictionaryId;
    
    /** 所属企业ID */
    @ApiModelProperty(value = "所属企业ID")
    private Long enterpriseId;
    
    /** 所属组织ID */
    @ApiModelProperty(value = "所属组织ID")
    private Long organizationId;
    
    /** 所属字典组ID */
    @ApiModelProperty(value = "所属字典组ID")
    private Long dictionaryGroupId;
    
    /** 适应范围: 0=全局，1=集团，2=公司，3=部门，4=集团+公司 */
    @ApiModelProperty(value = "适应范围: 0=全局，1=集团，2=公司，3=部门，4=集团+公司")
    private String dictionaryUseScope;
    
    /** 外部自定实体名称 */
    @ApiModelProperty(value = "外部自定实体名称")
    private String dictionaryClassname;
    
    /** 字典编码 */
    @ApiModelProperty(value = "字典编码")
    private String dictionaryDdcode;
    
    /** 字典名称 */
    @ApiModelProperty(value = "字典名称")
    private String dictionaryDdname;
    
    
    /** 类型 */
    @ApiModelProperty(value = "类型")
    private String dictionaryDictype;
    
    /** 字典值列表 */
    @ApiModelProperty(value = "字典值列表")
    List<NsCoreDictionaryitemVo> dictionaryitemVos;
    
    @ApiModelProperty(value = "操作人")
    private Long handlerId;

    public List<NsCoreDictionaryitemVo> getDictionaryitemVos() {
        return dictionaryitemVos;
    }

    public void setDictionaryitemVos(List<NsCoreDictionaryitemVo> dictionaryitemVos) {
        this.dictionaryitemVos = dictionaryitemVos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJeCoreDictionaryId() {
        return jeCoreDictionaryId;
    }

    public void setJeCoreDictionaryId(String jeCoreDictionaryId) {
        this.jeCoreDictionaryId = jeCoreDictionaryId;
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

    public Long getDictionaryGroupId() {
        return dictionaryGroupId;
    }

    public void setDictionaryGroupId(Long dictionaryGroupId) {
        this.dictionaryGroupId = dictionaryGroupId;
    }

    public String getDictionaryUseScope() {
        return dictionaryUseScope;
    }

    public void setDictionaryUseScope(String dictionaryUseScope) {
        this.dictionaryUseScope = dictionaryUseScope;
    }

    public String getDictionaryClassname() {
        return dictionaryClassname;
    }

    public void setDictionaryClassname(String dictionaryClassname) {
        this.dictionaryClassname = dictionaryClassname;
    }

    public String getDictionaryDdcode() {
        return dictionaryDdcode;
    }

    public void setDictionaryDdcode(String dictionaryDdcode) {
        this.dictionaryDdcode = dictionaryDdcode;
    }

    public String getDictionaryDdname() {
        return dictionaryDdname;
    }

    public void setDictionaryDdname(String dictionaryDdname) {
        this.dictionaryDdname = dictionaryDdname;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getDictionaryDictype() {
        return dictionaryDictype;
    }

    public void setDictionaryDictype(String dictionaryDictype) {
        this.dictionaryDictype = dictionaryDictype;
    }
    
}
