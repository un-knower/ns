package com.newsee.system.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class NsCoreDictionaryitemVo implements Serializable{

    private static final long serialVersionUID = 353032778763814420L;
    
    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    /** 所属企业ID */
    @ApiModelProperty(value = "所属企业ID")
    private Long enterpriseId;
    
    /** 所属组织ID */
    @ApiModelProperty(value = "所属组织ID")
    private Long organizationId;
    @ApiModelProperty(value = "所属组织名称")
    private String organizationName;
    /** 字典项ID */
    @ApiModelProperty(value = "字典项ID")
    private String jeCoreDictionaryitemId;
    
    /** 字典ID */
    @ApiModelProperty(value = "字典ID")
    private String dictionaryitemDictionaryId;
    
    @ApiModelProperty(value = "字典名称")
    private String dictionaryitemDictionaryName;
    /** 节点编码 */
    @ApiModelProperty(value = "节点编码")
    private String dictionaryitemItemcode;
    
    /** 节点名称 */
    @ApiModelProperty(value = "节点名称")
    private String dictionaryitemItemname;
    
    /** 英文 */
    @ApiModelProperty(value = "英文")
    private String dictionaryitemItemnameEn;
    
    /** 节点信息类型 */
    @ApiModelProperty(value = "节点信息类型")
    private String dictionaryitemNodeinfotype;
    
    /** 是否启用本条数据 */
    @ApiModelProperty(value = "是否启用本条数据")
    private String syFlag;
    
    /** 排序字段 */
    @ApiModelProperty(value = "排序字段")
    private Integer syOrderindex;
    
    @ApiModelProperty(value = "操作人")
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

    public String getJeCoreDictionaryitemId() {
        return jeCoreDictionaryitemId;
    }

    public void setJeCoreDictionaryitemId(String jeCoreDictionaryitemId) {
        this.jeCoreDictionaryitemId = jeCoreDictionaryitemId;
    }

    public String getDictionaryitemDictionaryId() {
        return dictionaryitemDictionaryId;
    }

    public void setDictionaryitemDictionaryId(String dictionaryitemDictionaryId) {
        this.dictionaryitemDictionaryId = dictionaryitemDictionaryId;
    }

    public String getDictionaryitemItemcode() {
        return dictionaryitemItemcode;
    }

    public void setDictionaryitemItemcode(String dictionaryitemItemcode) {
        this.dictionaryitemItemcode = dictionaryitemItemcode;
    }

    public String getDictionaryitemItemname() {
        return dictionaryitemItemname;
    }

    public void setDictionaryitemItemname(String dictionaryitemItemname) {
        this.dictionaryitemItemname = dictionaryitemItemname;
    }

    public String getDictionaryitemItemnameEn() {
        return dictionaryitemItemnameEn;
    }

    public void setDictionaryitemItemnameEn(String dictionaryitemItemnameEn) {
        this.dictionaryitemItemnameEn = dictionaryitemItemnameEn;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getSyFlag() {
        return syFlag;
    }

    public void setSyFlag(String syFlag) {
        this.syFlag = syFlag;
    }

    public String getDictionaryitemNodeinfotype() {
        return dictionaryitemNodeinfotype;
    }

    public void setDictionaryitemNodeinfotype(String dictionaryitemNodeinfotype) {
        this.dictionaryitemNodeinfotype = dictionaryitemNodeinfotype;
    }

    public Integer getSyOrderindex() {
        return syOrderindex;
    }

    public void setSyOrderindex(Integer syOrderindex) {
        this.syOrderindex = syOrderindex;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDictionaryitemDictionaryName() {
        return dictionaryitemDictionaryName;
    }

    public void setDictionaryitemDictionaryName(String dictionaryitemDictionaryName) {
        this.dictionaryitemDictionaryName = dictionaryitemDictionaryName;
    }
}
