package com.newsee.owner.entity;

import java.util.Date;

public class OwnerCustomerInfo {
    /**
     * 
     *
     * @mbggenerated
     */
    private Long ownerId;

    /**
     * 移动电话
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * 家庭电话
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * 性别：0未知 1男 2女
     *
     * @mbggenerated
     */
    private String gender;

    private Integer age;
    
    /**
     * 生日
     *
     * @mbggenerated
     */
    private Date birthday;

    /**
     * 婚姻状况 0未婚  1已婚 2离异 
     *
     * @mbggenerated
     */
    private String maritalStatus;

    /**
     * 文化程度 0小学 1初中 2高中 3大学
     *
     * @mbggenerated
     */
    private String education;

    /**
     * 国籍地区：0中国
     *
     * @mbggenerated
     */
    private String region;

    /**
     * 行业ID
     *
     * @mbggenerated
     */
    private String tradeId;

    /**
     * 籍贯
     *
     * @mbggenerated
     */
    private String nativePlace;

    /**
     * 民族
     *
     * @mbggenerated
     */
    private String nation;

    /**
     * 兴趣爱好IDs，已逗号隔开
     *
     * @mbggenerated
     */
    private String interestsIds;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long ownerBaseInfoOwnerId;

    /**
     * 省
     *
     * @mbggenerated
     */
    private String provinceId;

    /**
     * 市
     *
     * @mbggenerated
     */
    private String cityId;

    /**
     * 区
     *
     * @mbggenerated
     */
    private String areaId;

    /**
     * 街道
     *
     * @mbggenerated
     */
    private String streetId;

    /**
     * 地域信息全称
     *
     * @mbggenerated
     */
    private String regionalInfo;

    /**
     * 地址
     *
     * @mbggenerated
     */
    private String address;

    /**
     * 工作单位
     *
     * @mbggenerated
     */
    private String company;

    /**
     * 单位电话
     *
     * @mbggenerated
     */
    private String companyPhone;

    /**
     * 单位地址
     *
     * @mbggenerated
     */
    private String companyAddress;

    /**
     * 电子邮箱
     *
     * @mbggenerated
     */
    private String email;

    /**
     * QQ
     *
     * @mbggenerated
     */
    private String qq;

    /**
     * 紧急联系人
     *
     * @mbggenerated
     */
    private String emergencyContact;

    /**
     * 紧急联系人电话
     *
     * @mbggenerated
     */
    private String emergencyContactPhone;

    /**
     * 法人代表
     *
     * @mbggenerated
     */
    private String legalRepresentative;

    /**
     * 企业性质
     *
     * @mbggenerated
     */
    private String companyProperyty;

    /**
     * 公司人数
     *
     * @mbggenerated
     */
    private Integer peopleCounts;

    /**
     * 注册地址
     *
     * @mbggenerated
     */
    private String registeredAddress;

    /**
     * 公司传真
     *
     * @mbggenerated
     */
    private String fax;

    /**
     * 联系人
     *
     * @mbggenerated
     */
    private String linkman;

    /**
     * 联系电话
     *
     * @mbggenerated
     */
    private String linkmanPhone;

    /**
     * 是否删除 0否 1是
     *
     * @mbggenerated
     */
    private Byte isDeleted;
    
    private String picUrl;
    
    /**
     * 
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 
     *
     * @mbggenerated
     */
    private Long updateUserId;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * 
     *
     * @mbggenerated
     */
    private Date sysTime;

    private String createUserName;
    
    private String updateUserName;
    
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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
    
}