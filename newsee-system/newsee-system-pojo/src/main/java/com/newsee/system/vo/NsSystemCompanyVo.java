package com.newsee.system.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class NsSystemCompanyVo implements Serializable {

    private static final long serialVersionUID = 361448396326168630L;

    /** 公司ID */
    @ApiModelProperty(value = "公司ID")
    private Long companyId;
    
    /** 所属企业ID */
    @ApiModelProperty(value = "所属企业ID")
    private Long enterpriseId;
    
    /** 所属集团 */
    @ApiModelProperty(value = "所属集团")
    private Long groupId;
    
    /** 上级公司ID */
    @ApiModelProperty(value = "上级公司ID")
    private Long companyParentId;
    
    /** 上级公司名称 */
    private String companyParentName;
    
    /** 公司编号 */
    @ApiModelProperty(value = "公司编号")
    private String companyCode;
    
    /** 公司名称 */
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    
    /** 法定名称 */
    @ApiModelProperty(value = "法定名称")
    private String companyLegalName;
    
    /** 公司简称 */
    @ApiModelProperty(value = "公司简称")
    private String companyShortName;
    
    /** 公司负责人 */
    @ApiModelProperty(value = "公司负责人")
    private Long companyManagerId;
    
    @ApiModelProperty(value = "公司负责人姓名")
    private String companyManagerName;
    
    /** 公司成立日期 */
    @ApiModelProperty(value = "公司成立日期")
    private Date companyBuildDate;
    
    /** 公司路径 */
    @ApiModelProperty(value = "公司路径")
    private String companyPath;
    
    /** 公司性质 */
    @ApiModelProperty(value = "公司性质")
    private Integer companyNature;
    
    /** 省ID */
    @ApiModelProperty(value = "省ID")
    private Integer provinceId;
    
    /** 市ID */
    @ApiModelProperty(value = "市ID")
    private Integer cityId;
    
    /** 详细地址 */
    @ApiModelProperty(value = "详细地址")
    private String address;
    
    /** 排序编号 */
    @ApiModelProperty(value = "排序编号")
    private Integer orderNo;
    
    /** 备注 */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    
    //------传输数据------
    /** 对应的组织ID */
    @ApiModelProperty(value = "对应的组织ID")
    private Long organizationId;
    /** 上级组织ID */
    @ApiModelProperty(value = "上级组织ID")
    private Long parentOrganizationId;
    /** 上级组织名称 */
    @ApiModelProperty(value = "上级组织ID")
    private String parentOrganizationName;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public Long getCompanyParentId() {
        return companyParentId;
    }

    public void setCompanyParentId(Long companyParentId) {
        this.companyParentId = companyParentId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLegalName() {
        return companyLegalName;
    }

    public void setCompanyLegalName(String companyLegalName) {
        this.companyLegalName = companyLegalName;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public Long getCompanyManagerId() {
        return companyManagerId;
    }

    public void setCompanyManagerId(Long companyManagerId) {
        this.companyManagerId = companyManagerId;
    }

    public Date getCompanyBuildDate() {
        return companyBuildDate;
    }

    public void setCompanyBuildDate(Date companyBuildDate) {
        this.companyBuildDate = companyBuildDate;
    }

    public String getCompanyPath() {
        return companyPath;
    }

    public void setCompanyPath(String companyPath) {
        this.companyPath = companyPath;
    }

    public Integer getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(Integer companyNature) {
        this.companyNature = companyNature;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getParentOrganizationId() {
        return parentOrganizationId;
    }

    public void setParentOrganizationId(Long parentOrganizationId) {
        this.parentOrganizationId = parentOrganizationId;
    }

    public String getCompanyParentName() {
        return companyParentName;
    }

    public void setCompanyParentName(String companyParentName) {
        this.companyParentName = companyParentName;
    }

    public String getParentOrganizationName() {
        return parentOrganizationName;
    }

    public void setParentOrganizationName(String parentOrganizationName) {
        this.parentOrganizationName = parentOrganizationName;
    }

    public String getCompanyManagerName() {
        return companyManagerName;
    }

    public void setCompanyManagerName(String companyManagerName) {
        this.companyManagerName = companyManagerName;
    }
    
}
