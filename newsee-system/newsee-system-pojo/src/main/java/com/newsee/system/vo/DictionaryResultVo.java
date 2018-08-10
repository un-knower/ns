package com.newsee.system.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class DictionaryResultVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -4941676422582214749L;
    @ApiModelProperty(value = "数据字典结果表ID")
    private String dictionaryResultId;
    @ApiModelProperty(value = "数据字典ID")
    private Integer dictionaryId;
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;
    @ApiModelProperty(value = "组织ID")
    private Long organizationId;
    @ApiModelProperty(value = "数据字典类型ID")
    private Long dictionaryTypeId;
    @ApiModelProperty(value = "数据字典类型名称")
    private String dictionaryTypeName;
    @ApiModelProperty(value = "数据字典类型名称拼音")
    private String dictionaryTypeNamePY;    
    @ApiModelProperty(value = "数据字典类型编码")
    private String dictionaryTypeCode;
    @ApiModelProperty(value = "数据字典类型组ID")
    private Long dictionaryGroupId;
    @ApiModelProperty(value = "数据字典组名称")
    private String dictionaryGroupName;
    @ApiModelProperty(value = "数据字典组名称拼音")
    private String dictionaryGroupNamePY;
    @ApiModelProperty(value = "是否内置，1：内置数据字典，不能修改，0：用户自定义数据字典，用户自己可以修改")
    private Byte isBuiltIn;
    @ApiModelProperty(value = "0：未停用，1：已停用")
    private Byte isDisable;
    @ApiModelProperty(value = "数据值")
    private Integer dataValue;
    @ApiModelProperty(value = "数据名称")
    private String dataLabel;
    @ApiModelProperty(value = "数据名称拼音")
    private String dataLabelPY;
    @ApiModelProperty(value = "备注")
    private String remark;

    public Integer getDictionaryId() {
        return dictionaryId;
    }
    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
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
    public Long getDictionaryTypeId() {
        return dictionaryTypeId;
    }
    public void setDictionaryTypeId(Long dictionaryTypeId) {
        this.dictionaryTypeId = dictionaryTypeId;
    }
    public String getDictionaryTypeName() {
        return dictionaryTypeName;
    }
    public void setDictionaryTypeName(String dictionaryTypeName) {
        this.dictionaryTypeName = dictionaryTypeName;
    }
    public String getDictionaryTypeCode() {
        return dictionaryTypeCode;
    }
    public void setDictionaryTypeCode(String dictionaryTypeCode) {
        this.dictionaryTypeCode = dictionaryTypeCode;
    }
    public Long getDictionaryGroupId() {
        return dictionaryGroupId;
    }
    public void setDictionaryGroupId(Long dictionaryGroupId) {
        this.dictionaryGroupId = dictionaryGroupId;
    }
    public String getDictionaryGroupName() {
        return dictionaryGroupName;
    }
    public void setDictionaryGroupName(String dictionaryGroupName) {
        this.dictionaryGroupName = dictionaryGroupName;
    }
    public Byte getIsBuiltIn() {
        return isBuiltIn;
    }
    public void setIsBuiltIn(Byte isBuiltIn) {
        this.isBuiltIn = isBuiltIn;
    }
    public Byte getIsDisable() {
        return isDisable;
    }
    public void setIsDisable(Byte isDisable) {
        this.isDisable = isDisable;
    }
    public Integer getDataValue() {
        return dataValue;
    }
    public void setDataValue(Integer dataValue) {
        this.dataValue = dataValue;
    }
    public String getDataLabel() {
        return dataLabel;
    }
    public void setDataLabel(String dataLabel) {
        this.dataLabel = dataLabel;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getDictionaryResultId() {
        return dictionaryResultId;
    }
    public void setDictionaryResultId(String dictionaryResultId) {
        this.dictionaryResultId = dictionaryResultId;
    }
    public String getDictionaryTypeNamePY() {
        return dictionaryTypeNamePY;
    }
    public void setDictionaryTypeNamePY(String dictionaryTypeNamePY) {
        this.dictionaryTypeNamePY = dictionaryTypeNamePY;
    }
    public String getDictionaryGroupNamePY() {
        return dictionaryGroupNamePY;
    }
    public void setDictionaryGroupNamePY(String dictionaryGroupNamePY) {
        this.dictionaryGroupNamePY = dictionaryGroupNamePY;
    }
    public String getDataLabelPY() {
        return dataLabelPY;
    }
    public void setDataLabelPY(String dataLabelPY) {
        this.dataLabelPY = dataLabelPY;
    }
    
}
