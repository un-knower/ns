package com.newsee.log.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ImportLogEntity implements Serializable{

    private static final long serialVersionUID = 4205882978063340615L;

    private String importId;
    private String importName;
    private Long enterpriseId;
    private Long organizationId;
    /** 项目ID */
    private Long precinctId;
    /** 项目名称 */
    @ApiModelProperty("项目名称")
    private String precinctName;
    /** 房产ID */
    private Long houseId;
    /** 房产名称 */
    @ApiModelProperty("房产名称")
    private String houseName;
    /** 房产简称 */
    @ApiModelProperty("房产简称")
    private String houseShortName;
    /** 房产编号 */
    private String houseNo;
    /** 房产全称 */
    private String houseFullName;
    /** 是否更新 0新增 1更新 */
    private Byte updateFlag;
    /** 导入用户id */
    private Long userId;
    /** 导入用户名 */
    @ApiModelProperty("导入用户名")
    private String userName;
    /** 导入时间 */
    @ApiModelProperty("导入时间")
    private Date importDate;
    /** 导入行数 */
    @ApiModelProperty("导入行数")
    private Integer importIndex;
    /** 导入结果状态 */
    private Byte importResult;
    @ApiModelProperty("导入结果状态")
    private String importResultName;
    /** 导入结果日志 */
    @ApiModelProperty("导入结果日志")
    private String importRemark;

    public Long getPrecinctId() {
        return precinctId;
    }
    public void setPrecinctId(Long precinctId) {
        this.precinctId = precinctId;
    }
    public String getPrecinctName() {
        return precinctName;
    }
    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }
    public Long getHouseId() {
        return houseId;
    }
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
    public String getHouseName() {
        return houseName;
    }
    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
    public String getHouseShortName() {
        return houseShortName;
    }
    public void setHouseShortName(String houseShortName) {
        this.houseShortName = houseShortName;
    }
    public String getHouseNo() {
        return houseNo;
    }
    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }
    public String getHouseFullName() {
        return houseFullName;
    }
    public void setHouseFullName(String houseFullName) {
        this.houseFullName = houseFullName;
    }
    public Byte getUpdateFlag() {
        return updateFlag;
    }
    public void setUpdateFlag(Byte updateFlag) {
        this.updateFlag = updateFlag;
    }
    public Date getImportDate() {
        return importDate;
    }
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }
    public Integer getImportIndex() {
        return importIndex;
    }
    public void setImportIndex(Integer importIndex) {
        this.importIndex = importIndex;
    }
    public Byte getImportResult() {
        return importResult;
    }
    public void setImportResult(Byte importResult) {
        this.importResult = importResult;
    }
    public String getImportRemark() {
        return importRemark;
    }
    public void setImportRemark(String importRemark) {
        this.importRemark = importRemark;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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
    public String getImportResultName() {
        return importResultName;
    }
    public void setImportResultName(String importResultName) {
        this.importResultName = importResultName;
    }
    public String getImportId() {
        return importId;
    }
    public void setImportId(String importId) {
        this.importId = importId;
    }
    public String getImportName() {
        return importName;
    }
    public void setImportName(String importName) {
        this.importName = importName;
    }
    
}
