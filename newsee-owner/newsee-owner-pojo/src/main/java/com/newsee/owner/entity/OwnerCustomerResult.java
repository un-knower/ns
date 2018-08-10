package com.newsee.owner.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.newsee.common.entity.BaseEntity;

public class OwnerCustomerResult extends BaseEntity implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -9038806736092920672L;

    private Long ownerId;

    private Long enterpriseId;

    private Long organizationId;

    private String precinctId;

//    private Long projectId;

    private String precinctName;
    
    private String mainHouseName;
    
    private String ownerName;

    private String ownerType;
    
//    private String ownerTypeName;

    private String ownerProperty;
    
//    private String ownerPropertyName;

    private String ownerLevel;

//    private String ownerLevelName;

    private String certificateType;

//    private String certificateTypeName;

    private String certificate;

    private Byte isDeleted;

    private Long createUserId;

    private String createUserName;
    
    private Date createTime;

    private Long updateUserId;

    private String updateUserName;

    private Date updateTime;

    private Date sysTime;

    private String mobile;

    private String phone;

    private String gender;

//    private String genderName;
    
    private Integer age;

    private Date birthday;

    private String maritalStatus;

//    private String maritalStatusName;

    private String education;

//    private String educationName;

    private String region;

//    private String regionName;

    private String tradeId;

//    private String tradeName;

    private String nativePlace;

    private String nation;

//    private String nationName;

    private String interestsIds;

    private String interestsNames;

    private String remark;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private Integer streetId;

    private String regionalInfo;

    private String address;

    private String company;

    private String companyPhone;

    private String companyAddress;

    private String email;

    private String qq;

    private String emergencyContact;

    private String emergencyContactPhone;

    private String legalRepresentative;

    private String companyProperyty;

    private Integer peopleCounts;

    private String registeredAddress;

    private String fax;

    private String linkman;

    private String linkmanPhone;

    private String picUrl;

    private String hasFamily;
    
    private List<OwnerHouseBaseInfo> precinctList;
      
    private List<OwnerHouseBaseInfo> mainHouseList;
    
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

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getSysTime() {
        return sysTime;
    }

    public void setSysTime(Date sysTime) {
        this.sysTime = sysTime;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getInterestsNames() {
        return interestsNames;
    }

    public void setInterestsNames(String interestsNames) {
        this.interestsNames = interestsNames;
    }

    public List<OwnerHouseBaseInfo> getPrecinctList() {
        return precinctList;
    }

    public void setPrecinctList(List<OwnerHouseBaseInfo> precinctList) {
        this.precinctList = precinctList;
    }

    public List<OwnerHouseBaseInfo> getMainHouseList() {
        return mainHouseList;
    }

    public void setMainHouseList(List<OwnerHouseBaseInfo> mainHouseList) {
        this.mainHouseList = mainHouseList;
    }

    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    public String getMainHouseName() {
        return mainHouseName;
    }

    public void setMainHouseName(String mainHouseName) {
        this.mainHouseName = mainHouseName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getHasFamily() {
        return hasFamily;
    }

    public void setHasFamily(String hasFamily) {
        this.hasFamily = hasFamily;
    }

}