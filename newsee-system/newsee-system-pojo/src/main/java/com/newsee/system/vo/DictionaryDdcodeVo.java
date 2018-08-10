package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class DictionaryDdcodeVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -1624182750016110899L;
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;
    @ApiModelProperty(value = "组织ID")
    private Long organizationId;
    @ApiModelProperty(value = "数据字典CODE")
    private List<String> dictionaryDdcodeList;
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
    public List<String> getDictionaryDdcodeList() {
        return dictionaryDdcodeList;
    }
    public void setDictionaryDdcodeList(List<String> dictionaryDdcodeList) {
        this.dictionaryDdcodeList = dictionaryDdcodeList;
    }
    
}
