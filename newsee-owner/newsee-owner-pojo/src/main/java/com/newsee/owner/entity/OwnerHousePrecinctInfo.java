package com.newsee.owner.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OwnerHousePrecinctInfo {
    /**
     * house_base_info表的ID
     *
     * @mbggenerated
     */
    private Long houseId;

    /**
     * 项目编号
     *
     * @mbggenerated
     */
    private String proNo;

    /**
     * 所属组织
     *
     * @mbggenerated
     */
    private Long organizationId;

    /**
     * 项目简称
     *
     * @mbggenerated
     */
    private String proShortName;

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
     * 经度
     *
     * @mbggenerated
     */
    private BigDecimal lon;

    /**
     * 纬度
     *
     * @mbggenerated
     */
    private BigDecimal lat;

    /**
     * 项目性质：0.自管 2.代管
     *
     * @mbggenerated
     */
    private String proNature;

    /**
     * 项目类型，记录最小ID
     *
     * @mbggenerated
     */
    private String proTypeId;

    /**
     * 履行状态：0履行 1暂停 2提前终止 3终止
     *
     * @mbggenerated
     */
    private String performanceStatus;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 创建者ID
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
     * 更新者ID
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

    private String createUserName;
    
    private String updateUserName;
    
    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getProShortName() {
        return proShortName;
    }

    public void setProShortName(String proShortName) {
        this.proShortName = proShortName;
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

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getProNature() {
        return proNature;
    }

    public void setProNature(String proNature) {
        this.proNature = proNature;
    }

    public String getProTypeId() {
        return proTypeId;
    }

    public void setProTypeId(String proTypeId) {
        this.proTypeId = proTypeId;
    }

    public String getPerformanceStatus() {
        return performanceStatus;
    }

    public void setPerformanceStatus(String performanceStatus) {
        this.performanceStatus = performanceStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getProNo() {
        return proNo;
    }

    public void setProNo(String proNo) {
        this.proNo = proNo;
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