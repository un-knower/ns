package com.newsee.owner.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.newsee.common.vo.BaseVo;

import io.swagger.annotations.ApiModelProperty;

public class HouseOperateSalesVo extends BaseVo implements Serializable{

    private static final long serialVersionUID = 8932540410459509614L;

    private Long detailId;
    
    private Long otherDetailId;
    
    @ApiModelProperty("项目ID")
    private Long precinctId;
    
    @ApiModelProperty("项目名称")
    private String precinctName;
    
    @ApiModelProperty("房产ID")
    private Long houseId;
    
    @ApiModelProperty("房号")
    private String houseName;
    
    @ApiModelProperty("房态操作动作：1售楼 2收房 3入住 4搬出 5出租 6转租 7退租 8延长空关 9转让 10装修")
    private String houseOperateType;
    
    @ApiModelProperty("是否转租 0否 1是")
    private Byte isSublet;
    
    @ApiModelProperty("原业主")
    private CustomerVo oldOwner;
    
    @ApiModelProperty("新业主")
    private CustomerVo newOwner;
    
    @ApiModelProperty("原租户")
    private CustomerVo oldRentOwner;
    
    @ApiModelProperty("新租户")
    private CustomerVo newRentOwner;
    
    @ApiModelProperty("是否主房产")
    private Byte isMainHouse;
    
    @ApiModelProperty("上次操作ID")
    private Long previousDetailId;

    @ApiModelProperty("原产权人List")
    private List<CustomerVo> oldPropertyOwnerList;
    
    @ApiModelProperty("新产权人List")
    private List<CustomerVo> newPropertyOwnerList;
    
    @ApiModelProperty("售楼/转让日期")
    private Date salesDate;
    
    @ApiModelProperty("收房日期")
    private Date takeDate;
    
    @ApiModelProperty("入住日期")
    private Date checkInDate;

    @ApiModelProperty("原业主入住日期")
    private Date oldCheckInDate;
    
    @ApiModelProperty("申请日期 yyyy-MM-dd")
    private Date applyDate;
    
//    @ApiModelProperty("开始时间")
//    private Date startTime;
//
//    @ApiModelProperty("结束时间")
//    private Date endTime;
    
    @ApiModelProperty("时间段数组")
    private List<Date> timeList;
    
    @ApiModelProperty("备注")
    private String remark;
    
    @ApiModelProperty("登记人")
    private String createUserName;

    private Long createUserId;

    @ApiModelProperty("登记日期")
    private Date createTime;

    @ApiModelProperty("修改人")
    private String updateUserName;

    private Long updateUserId;

    @ApiModelProperty("修改日期")
    private Date updateTime;
    
    @ApiModelProperty("业主名称")
    private String ownerName;
    @ApiModelProperty("业主证件号码")
    private String ownerCertificate;
    @ApiModelProperty("业主移动电话")
    private String ownerMobile;
    
    @ApiModelProperty("租户名称")
    private String lesseeName;
    @ApiModelProperty("租户证件号码")
    private String lesseeCertificate;
    @ApiModelProperty("租户移动电话")
    private String lesseeMobile;
    
    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public CustomerVo getOldOwner() {
        return oldOwner;
    }

    public void setOldOwner(CustomerVo oldOwner) {
        this.oldOwner = oldOwner;
    }

    public CustomerVo getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(CustomerVo newOwner) {
        this.newOwner = newOwner;
    }

    public Byte getIsMainHouse() {
        return isMainHouse;
    }

    public void setIsMainHouse(Byte isMainHouse) {
        this.isMainHouse = isMainHouse;
    }

    public List<CustomerVo> getOldPropertyOwnerList() {
        return oldPropertyOwnerList;
    }

    public void setOldPropertyOwnerList(List<CustomerVo> oldPropertyOwnerList) {
        this.oldPropertyOwnerList = oldPropertyOwnerList;
    }

    public List<CustomerVo> getNewPropertyOwnerList() {
        return newPropertyOwnerList;
    }

    public void setNewPropertyOwnerList(List<CustomerVo> newPropertyOwnerList) {
        this.newPropertyOwnerList = newPropertyOwnerList;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

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

    public String getHouseOperateType() {
        return houseOperateType;
    }

    public void setHouseOperateType(String houseOperateType) {
        this.houseOperateType = houseOperateType;
    }

    public Long getPreviousDetailId() {
        return previousDetailId;
    }

    public void setPreviousDetailId(Long previousDetailId) {
        this.previousDetailId = previousDetailId;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

//    public Date getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Date startTime) {
//        this.startTime = startTime;
//    }
//
//    public Date getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Byte getIsSublet() {
        return isSublet;
    }

    public void setIsSublet(Byte isSublet) {
        this.isSublet = isSublet;
    }

    public CustomerVo getOldRentOwner() {
        return oldRentOwner;
    }

    public void setOldRentOwner(CustomerVo oldRentOwner) {
        this.oldRentOwner = oldRentOwner;
    }

    public CustomerVo getNewRentOwner() {
        return newRentOwner;
    }

    public void setNewRentOwner(CustomerVo newRentOwner) {
        this.newRentOwner = newRentOwner;
    }

    public Long getOtherDetailId() {
        return otherDetailId;
    }

    public void setOtherDetailId(Long otherDetailId) {
        this.otherDetailId = otherDetailId;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerCertificate() {
        return ownerCertificate;
    }

    public void setOwnerCertificate(String ownerCertificate) {
        this.ownerCertificate = ownerCertificate;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName;
    }

    public String getLesseeMobile() {
        return lesseeMobile;
    }

    public void setLesseeMobile(String lesseeMobile) {
        this.lesseeMobile = lesseeMobile;
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
    public String getLesseeCertificate() {
        return lesseeCertificate;
    }

    public void setLesseeCertificate(String lesseeCertificate) {
        this.lesseeCertificate = lesseeCertificate;
    }

    public Date getOldCheckInDate() {
        return oldCheckInDate;
    }

    public void setOldCheckInDate(Date oldCheckInDate) {
        this.oldCheckInDate = oldCheckInDate;
    }

    public List<Date> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Date> timeList) {
        this.timeList = timeList;
    }
    
}
