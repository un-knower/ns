package com.newsee.owner.vo;

import java.util.Date;
import java.util.List;

import com.newsee.common.vo.BaseVo;
import com.newsee.common.vo.FileVo;
import com.newsee.common.vo.SelectVo;
import com.newsee.owner.entity.OwnerCustomerFamilyInfo;
import com.newsee.common.vo.ProvinceCityArea;

import io.swagger.annotations.ApiModelProperty;

public class CustomerVo extends BaseVo{

    private static final long serialVersionUID = -5358378996176099085L;

    @ApiModelProperty("结果表ID")
    private String customerResultId;

    @ApiModelProperty("客户编号")
    private Long ownerId;

    @ApiModelProperty("租户ID")
    private Long enterpriseId;

    @ApiModelProperty("公司ID")
    private Long organizationId;

    @ApiModelProperty("所属项目ID，即house_id")
    private String precinctId;

    @ApiModelProperty("客户名称")
    private String ownerName;
    
    private String picUrl;
    
    @ApiModelProperty("头像Url")
    private List<FileVo> pictures;
    
    @ApiModelProperty("客户类型：0个人 1企业")
    private String ownerType;

    private String ownerTypeName;
    
    @ApiModelProperty("客户性质：0业主 1租户 2住户 3开发商")
    private String ownerProperty;

    private String ownerPropertyName;

    @ApiModelProperty("客户等级：0VIP 1A 2B 3C")
    private String ownerLevel;

    private String ownerLevelName;

    @ApiModelProperty("证件类型 10身份证  11护照  20营业执照")
    private String certificateType;

    private String certificateTypeName;

    @ApiModelProperty("证件号")
    private String certificate;
    
    @ApiModelProperty("移动电话")
    private String mobile;

    @ApiModelProperty("家庭电话")
    private String phone;

    @ApiModelProperty("性别：0未知 1男 2女")
    private String gender;

    private String genderName;

    @ApiModelProperty("年龄")
    private Integer age;
    
    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("婚姻状况 0未婚  1已婚 2离异 ")
    private String maritalStatus;

    private String maritalStatusName;
    
    @ApiModelProperty("文化程度 0小学 1初中 2高中 3大学")
    private String education;
    
    private String educationName;

    @ApiModelProperty("国籍地区：0中国")
    private String region;

    private String regionName;

    @ApiModelProperty("行业ID")
    private String tradeId;

    private String tradeName;

    @ApiModelProperty("籍贯")
    private String nativePlace;

    @ApiModelProperty("民族")
    private String nation;

    private String nationName;

    @ApiModelProperty("兴趣爱好IDs，已逗号隔开")
    private String interestsIds;

    private List<String> interestsIdList;
    
    private String interestsNames;
    
    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("")
    private Long ownerBaseInfoOwnerId;

    @ApiModelProperty("省")
    private String provinceId;
    private String provinceName;
    @ApiModelProperty("市")
    private String cityId;
    private String cityName;
    @ApiModelProperty("区")
    private String areaId;
    private String areaName;
    @ApiModelProperty("街道")
    private String streetId;
    private String streetName;
    @ApiModelProperty("省市区控件参数")
    private ProvinceCityArea provinceCityArea;
    
    @ApiModelProperty("地域信息全称")
    private String regionalInfo;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("工作单位")
    private String company;

    @ApiModelProperty("单位电话")
    private String companyPhone;

    @ApiModelProperty("单位地址")
    private String companyAddress;

    @ApiModelProperty("电子邮箱")
    private String email;

    @ApiModelProperty("QQ")
    private String qq;

    @ApiModelProperty("紧急联系人")
    private String emergencyContact;

    @ApiModelProperty("紧急联系人电话")
    private String emergencyContactPhone;

    @ApiModelProperty("法人代表")
    private String legalRepresentative;

    @ApiModelProperty("企业性质")
    private String companyProperyty;

    @ApiModelProperty("公司人数")
    private Integer peopleCounts;

    @ApiModelProperty("注册地址")
    private String registeredAddress;

    @ApiModelProperty("公司传真")
    private String fax;

    @ApiModelProperty("联系人")
    private String linkman;

    @ApiModelProperty("联系电话")
    private String linkmanPhone;

	@ApiModelProperty("主房产IDList-客户房产关系IDList")
    private List<MainHouseVo> mainHouseList;
    
	@ApiModelProperty("客户房产关系List-编辑页面初始化")
	private SelectVo precinctHouse;
	
    @ApiModelProperty("家庭关系")
    private FamilyInfoVo familyInfoVo;
    
    List<OwnerCustomerFamilyInfo> familyInfoList;
    
    @ApiModelProperty("产权人与业主关系")
    private String ownerRelationship;
    
    @ApiModelProperty("操作人")
    private Long handlerId;
    private Long createUserId;
    private String createUserName;
    private Date createTime;
    private Long updateUserId;
    private String updateUserName;
    private Date updateTime;
    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

    public String getPrecinctId() {
        return precinctId;
    }

    public void setPrecinctId(String precinctId) {
        this.precinctId = precinctId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerProperty() {
        return ownerProperty;
    }

    public void setOwnerProperty(String ownerProperty) {
        this.ownerProperty = ownerProperty;
    }

    public String getOwnerLevel() {
        return ownerLevel;
    }

    public void setOwnerLevel(String ownerLevel) {
        this.ownerLevel = ownerLevel;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getInterestsIds() {
        return interestsIds;
    }

    public void setInterestsIds(String interestsIds) {
        this.interestsIds = interestsIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOwnerBaseInfoOwnerId() {
        return ownerBaseInfoOwnerId;
    }

    public void setOwnerBaseInfoOwnerId(Long ownerBaseInfoOwnerId) {
        this.ownerBaseInfoOwnerId = ownerBaseInfoOwnerId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getStreetId() {
        return streetId;
    }

    public void setStreetId(String streetId) {
        this.streetId = streetId;
    }

    public String getRegionalInfo() {
        return regionalInfo;
    }

    public void setRegionalInfo(String regionalInfo) {
        this.regionalInfo = regionalInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getCompanyProperyty() {
        return companyProperyty;
    }

    public void setCompanyProperyty(String companyProperyty) {
        this.companyProperyty = companyProperyty;
    }

    public Integer getPeopleCounts() {
        return peopleCounts;
    }

    public void setPeopleCounts(Integer peopleCounts) {
        this.peopleCounts = peopleCounts;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public String getCustomerResultId() {
        return customerResultId;
    }

    public void setCustomerResultId(String customerResultId) {
        this.customerResultId = customerResultId;
    }

    public List<MainHouseVo> getMainHouseList() {
        return mainHouseList;
    }

    public void setMainHouseList(List<MainHouseVo> mainHouseList) {
        this.mainHouseList = mainHouseList;
    }

    public FamilyInfoVo getFamilyInfoVo() {
        return familyInfoVo;
    }

    public void setFamilyInfoVo(FamilyInfoVo familyInfoVo) {
        this.familyInfoVo = familyInfoVo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getOwnerRelationship() {
        return ownerRelationship;
    }

    public void setOwnerRelationship(String ownerRelationship) {
        this.ownerRelationship = ownerRelationship;
    }

    public SelectVo getPrecinctHouse() {
        return precinctHouse;
    }

    public void setPrecinctHouse(SelectVo precinctHouse) {
        this.precinctHouse = precinctHouse;
    }

    public List<FileVo> getPictures() {
        return pictures;
    }

    public void setPictures(List<FileVo> pictures) {
        this.pictures = pictures;
    }

    public String getOwnerTypeName() {
        return ownerTypeName;
    }

    public void setOwnerTypeName(String ownerTypeName) {
        this.ownerTypeName = ownerTypeName;
    }

    public String getOwnerPropertyName() {
        return ownerPropertyName;
    }

    public void setOwnerPropertyName(String ownerPropertyName) {
        this.ownerPropertyName = ownerPropertyName;
    }

    public String getOwnerLevelName() {
        return ownerLevelName;
    }

    public void setOwnerLevelName(String ownerLevelName) {
        this.ownerLevelName = ownerLevelName;
    }

    public String getCertificateTypeName() {
        return certificateTypeName;
    }

    public void setCertificateTypeName(String certificateTypeName) {
        this.certificateTypeName = certificateTypeName;
    }

    public String getMaritalStatusName() {
        return maritalStatusName;
    }

    public void setMaritalStatusName(String maritalStatusName) {
        this.maritalStatusName = maritalStatusName;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getInterestsNames() {
        return interestsNames;
    }

    public void setInterestsNames(String interestsNames) {
        this.interestsNames = interestsNames;
    }

    public List<String> getInterestsIdList() {
        return interestsIdList;
    }

    public void setInterestsIdList(List<String> interestsIdList) {
        this.interestsIdList = interestsIdList;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public ProvinceCityArea getProvinceCityArea() {
        return provinceCityArea;
    }

    public void setProvinceCityArea(ProvinceCityArea provinceCityArea) {
        this.provinceCityArea = provinceCityArea;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public List<OwnerCustomerFamilyInfo> getFamilyInfoList() {
        return familyInfoList;
    }

    public void setFamilyInfoList(List<OwnerCustomerFamilyInfo> familyInfoList) {
        this.familyInfoList = familyInfoList;
    }
    
}
